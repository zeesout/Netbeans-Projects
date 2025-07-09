package com.Transaksi;

import com.Database.Mysql;
import java.awt.CardLayout;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager; 
import net.sf.jasperreports.engine.JasperPrint;       
import net.sf.jasperreports.view.JasperViewer;      

public class PembayaranKB extends javax.swing.JPanel {

    DefaultTableModel tabmode;

    public PembayaranKB() {
        initComponents();
        mainPanel.setLayout(new CardLayout());
        mainPanel.add(dataTransaksi, "dataTransaksi");
        mainPanel.add(tambahTransaksi, "tambahTransaksi");

        switchTo("dataTransaksi");
        kosong();
        aktif();
        datatable();
        initTanggalSpinner();
        loadComboNIK();
        loadComboPetugas();
        loadComboJenisLayanan();
        txtTotalBiaya.setHorizontalAlignment(javax.swing.JTextField.RIGHT); 
    }

    private void aktif() {
        txtId.setEditable(false);
        txtBiaya.setEditable(false);
        txtId.requestFocus();
    }

    private void kosong() {
        resetTambahForm();
    }

    private void switchTo(String panelName) {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, panelName);
    }
    
    private void resetTambahForm() {
        generateIdTransaksi(); // ID otomatis
        spinnerTanggal.setValue(new java.util.Date());
        comboNik.setSelectedIndex(0);
        comboJenis.setSelectedIndex(0);
        txtBiaya.setText("");
        comboPetugas.setSelectedIndex(0);
        txtTotalBiaya.setText("");
    }

    private void generateIdTransaksi() {
        try {
            Connection conn = Mysql.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(idtransaksi) FROM layanan_kb");
            if (rs.next()) {
                String lastId = rs.getString(1);
                if (lastId == null) {
                    txtId.setText("TRX001");
                } else {
                    int num = Integer.parseInt(lastId.substring(3)) + 1;
                    txtId.setText(String.format("TRX%03d", num));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal generate ID: " + e.getMessage());
        }
    }

    private void loadComboNIK() {
        comboNik.removeAllItems();
        comboNik.addItem("-- Pilih NIK --");
        try {
            Connection conn = Mysql.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT nik, nama FROM data_peserta_kb");
            while (rs.next()) {
                String item = rs.getString("nik") + " - " + rs.getString("nama");
                comboNik.addItem(item);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal memuat NIK peserta: " + e.getMessage());
        }
    }

    private void loadComboPetugas() {
        comboPetugas.removeAllItems();
        comboPetugas.addItem("-- Pilih Petugas --");
        try {
            Connection conn = Mysql.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_petugas, nama FROM data_petugas");
            while (rs.next()) {
                String item = rs.getString("id_petugas") + " - " + rs.getString("nama");
                comboPetugas.addItem(item);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal memuat petugas: " + e.getMessage());
        }
        
    }

    private void loadComboJenisLayanan() {
        comboJenis.removeAllItems();
        comboJenis.addItem("-- Pilih Layanan --");

        Map<String, Integer> hargaMap = new HashMap<>();

        try {
            Connection conn = Mysql.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT jenis_layanan, harga_default FROM data_layanan");

            while (rs.next()) {
                String jenis = rs.getString("jenis_layanan");
                int harga = rs.getInt("harga_default");
                comboJenis.addItem(jenis);
                hargaMap.put(jenis, harga); // simpan ke Map untuk lookup nanti
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal memuat jenis layanan: " + e.getMessage());
        }

        // txtBiaya tidak bisa diketik
        txtBiaya.setEditable(false);

        // Set listener untuk update txtBiaya
        comboJenis.addActionListener(evt -> {
            String selected = (String) comboJenis.getSelectedItem();
            if (selected != null && hargaMap.containsKey(selected)) {
                int harga = hargaMap.get(selected);
                txtBiaya.setText(String.valueOf(harga));
            } else {
                txtBiaya.setText("");
            }
        });
    }

    private void datatable() {
        String cari = txtCari.getText();
        Object[] kolom = {"ID Transaksi", "Tanggal", "Nama Peserta", "Jenis Layanan", "Biaya", "ID Petugas", "Status Bayar"};
        tabmode = new DefaultTableModel(null, kolom);
        tblTransaksi.setModel(tabmode);

        tblTransaksi.setRowHeight(40);
        tblTransaksi.setShowGrid(true);
        tblTransaksi.setGridColor(new java.awt.Color(230, 230, 230));
        tblTransaksi.setShowHorizontalLines(true);
        tblTransaksi.setShowVerticalLines(true);
        
        tblTransaksi.setRowHeight(35);
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tblTransaksi.getColumnCount(); i++) {
            tblTransaksi.getColumnModel().getColumn(i).setCellRenderer(center);
        }

        try {
            Connection conn = Mysql.getConnection();
            Statement st = conn.createStatement();

            String sql = "SELECT l.idtransaksi, l.tanggal_layanan, p.nama AS nama_peserta, " +
                "l.jenis_layanan, l.biaya, l.id_petugas, l.status " +
                "FROM layanan_kb l " +
                "JOIN data_peserta_kb p ON l.nik = p.nik ";

            if (!cari.isEmpty()) {
                sql += "WHERE l.idtransaksi LIKE '%" + cari + "%' " +
                       "OR p.nama LIKE '%" + cari + "%' " +
                       "OR l.jenis_layanan LIKE '%" + cari + "%' " +
                       "OR l.id_petugas LIKE '%" + cari + "%' " +
                       "OR l.status LIKE '%" + cari + "%'";
            }

            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                tabmode.addRow(new Object[]{
                    rs.getString("idtransaksi"),
                    rs.getString("tanggal_layanan"),
                    rs.getString("nama_peserta"),
                    rs.getString("jenis_layanan"),
                    rs.getString("biaya"),
                    rs.getString("id_petugas"),
                    rs.getString("status")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal ambil data: " + e.getMessage());
        }
    }
    private void initTanggalSpinner() {
        SpinnerDateModel model = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        spinnerTanggal.setModel(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerTanggal, "dd/MM/yyyy");
        spinnerTanggal.setEditor(editor);
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        dataTransaksi = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTambah = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransaksi = new javax.swing.JTable();
        jHapus = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        jCari = new javax.swing.JButton();
        jBayar = new javax.swing.JButton();
        jCetak = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtTotalBiaya = new javax.swing.JTextField();
        tambahTransaksi = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtBiaya = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        comboPetugas = new javax.swing.JComboBox<>();
        jSimpan = new javax.swing.JButton();
        jKembali = new javax.swing.JButton();
        spinnerTanggal = new javax.swing.JSpinner();
        comboJenis = new javax.swing.JComboBox<>();
        comboNik = new javax.swing.JComboBox<>();

        setPreferredSize(new java.awt.Dimension(1072, 658));
        setRequestFocusEnabled(false);

        mainPanel.setPreferredSize(new java.awt.Dimension(1072, 658));
        mainPanel.setLayout(new java.awt.CardLayout());

        dataTransaksi.setPreferredSize(new java.awt.Dimension(1072, 634));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1066, 658));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Pembayaran Layanan KB");

        jTambah.setBackground(new java.awt.Color(36, 160, 237));
        jTambah.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jTambah.setForeground(new java.awt.Color(255, 255, 255));
        jTambah.setText("Tambah");
        jTambah.setBorderPainted(false);
        jTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTambahActionPerformed(evt);
            }
        });

        tblTransaksi.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        tblTransaksi.setForeground(new java.awt.Color(62, 68, 74));
        tblTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Transaksi", "Tanggal", "Nama Peserta", "Jenis Layanan", "Biaya", "ID Petugas", "Status Bayar"
            }
        ));
        tblTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTransaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTransaksi);

        jHapus.setBackground(new java.awt.Color(190, 190, 190));
        jHapus.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jHapus.setForeground(new java.awt.Color(255, 255, 255));
        jHapus.setText("Hapus");
        jHapus.setBorderPainted(false);
        jHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHapusActionPerformed(evt);
            }
        });

        txtCari.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtCari.setForeground(new java.awt.Color(62, 68, 74));
        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCariKeyPressed(evt);
            }
        });

        jCari.setBackground(new java.awt.Color(190, 190, 190));
        jCari.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jCari.setForeground(new java.awt.Color(255, 255, 255));
        jCari.setText("Cari Data");
        jCari.setBorderPainted(false);
        jCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCariActionPerformed(evt);
            }
        });

        jBayar.setBackground(new java.awt.Color(36, 158, 101));
        jBayar.setForeground(new java.awt.Color(255, 255, 255));
        jBayar.setText("Bayar");
        jBayar.setBorderPainted(false);
        jBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBayarActionPerformed(evt);
            }
        });

        jCetak.setText("Cetak");
        jCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCetakActionPerformed(evt);
            }
        });

        jLabel11.setText("Total:");

        txtTotalBiaya.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtTotalBiaya.setForeground(new java.awt.Color(62, 68, 74));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 994, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotalBiaya, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCetak, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 475, Short.MAX_VALUE)
                                .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jCari, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jCari, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCetak, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalBiaya, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dataTransaksiLayout = new javax.swing.GroupLayout(dataTransaksi);
        dataTransaksi.setLayout(dataTransaksiLayout);
        dataTransaksiLayout.setHorizontalGroup(
            dataTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataTransaksiLayout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        dataTransaksiLayout.setVerticalGroup(
            dataTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        mainPanel.add(dataTransaksi, "card2");

        tambahTransaksi.setPreferredSize(new java.awt.Dimension(1076, 725));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1066, 725));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Tambah Transaksi");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(119, 119, 127));
        jLabel3.setText("ID Transaksi");

        txtId.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtId.setForeground(new java.awt.Color(119, 119, 127));

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(119, 119, 127));
        jLabel4.setText("Tanggal Layanan");

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(119, 119, 127));
        jLabel5.setText("Biaya");

        txtBiaya.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtBiaya.setForeground(new java.awt.Color(119, 119, 127));

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(119, 119, 127));
        jLabel6.setText("Petugas");

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(119, 119, 127));
        jLabel7.setText("NIK Peserta");

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(119, 119, 127));
        jLabel8.setText("Jenis Layanan");

        comboPetugas.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        comboPetugas.setForeground(new java.awt.Color(62, 68, 74));
        comboPetugas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "id - nama" }));

        jSimpan.setBackground(new java.awt.Color(255, 140, 0));
        jSimpan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jSimpan.setForeground(new java.awt.Color(255, 255, 255));
        jSimpan.setText("Simpan Transaksi");
        jSimpan.setBorderPainted(false);
        jSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSimpanActionPerformed(evt);
            }
        });

        jKembali.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jKembali.setForeground(new java.awt.Color(62, 68, 74));
        jKembali.setText("< Kembali");
        jKembali.setBorderPainted(false);
        jKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKembaliActionPerformed(evt);
            }
        });

        spinnerTanggal.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        spinnerTanggal.setModel(new javax.swing.SpinnerDateModel());

        comboJenis.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        comboJenis.setForeground(new java.awt.Color(62, 68, 74));
        comboJenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilihan layanan" }));

        comboNik.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        comboNik.setForeground(new java.awt.Color(62, 68, 74));
        comboNik.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "nik - nama" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(285, 285, 285)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtId)
                    .addComponent(txtBiaya)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboPetugas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addComponent(jKembali, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spinnerTanggal)
                    .addComponent(comboJenis, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboNik, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(285, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel3)
                .addGap(5, 5, 5)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel4)
                .addGap(5, 5, 5)
                .addComponent(spinnerTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel7)
                .addGap(5, 5, 5)
                .addComponent(comboNik, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel8)
                .addGap(5, 5, 5)
                .addComponent(comboJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel5)
                .addGap(5, 5, 5)
                .addComponent(txtBiaya, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel6)
                .addGap(5, 5, 5)
                .addComponent(comboPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout tambahTransaksiLayout = new javax.swing.GroupLayout(tambahTransaksi);
        tambahTransaksi.setLayout(tambahTransaksiLayout);
        tambahTransaksiLayout.setHorizontalGroup(
            tambahTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tambahTransaksiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1070, Short.MAX_VALUE))
        );
        tambahTransaksiLayout.setVerticalGroup(
            tambahTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainPanel.add(tambahTransaksi, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1072, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 897, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTambahActionPerformed
        resetTambahForm();

        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "tambahTransaksi");
    }//GEN-LAST:event_jTambahActionPerformed

    private void tblTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTransaksiMouseClicked
        int bar = tblTransaksi.getSelectedRow();
        System.out.println("Klik! Baris terpilih: " + bar);

        if (bar != -1) {
            jBayar.setEnabled(true);
            
            String biayaStr = tabmode.getValueAt(bar, 4).toString(); 
            txtTotalBiaya.setText(biayaStr);
            
            String id = tabmode.getValueAt(bar, 0).toString();
            String tanggal = tabmode.getValueAt(bar, 1).toString();
            String nik = tabmode.getValueAt(bar, 2).toString();
            String jenisLayanan = tabmode.getValueAt(bar, 3).toString();
            String biaya = tabmode.getValueAt(bar, 4).toString();
            String petugas = tabmode.getValueAt(bar, 5).toString();

            // Set data ke field di panel tambah/edit (di panel aktif saat ini)
            txtId.setText(id);

            // Format tanggal ke spinner
            try {
                java.util.Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(tanggal);
                spinnerTanggal.setValue(parsedDate);
            } catch (Exception e) {
                System.out.println("Gagal parsing tanggal: " + e.getMessage());
            }

            comboNik.setSelectedItem(nik);
            comboJenis.setSelectedItem(jenisLayanan);
            txtBiaya.setText(biaya);
            comboPetugas.setSelectedItem(petugas);
        }
    }//GEN-LAST:event_tblTransaksiMouseClicked

    private void jHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHapusActionPerformed
        int bar = tblTransaksi.getSelectedRow();
        if (bar == -1) {
            JOptionPane.showMessageDialog(null, "Pilih data yang ingin dihapus!");
            return;
        }
        // Ambil ID Transaksi dari kolom pertama (misalnya kolom 0)
        String idTransaksi = tabmode.getValueAt(bar, 0).toString();

        int konfirmasi = JOptionPane.showConfirmDialog(null, 
            "Hapus data Transaksi dengan ID: " + idTransaksi + "?", 
            "Konfirmasi", JOptionPane.YES_NO_OPTION);

        if (konfirmasi == JOptionPane.YES_OPTION) {
            try {
                Connection conn = Mysql.getConnection();
                String sql = "DELETE FROM layanan_kb WHERE idtransaksi=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, idTransaksi);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Data transaksi berhasil dihapus!");
                datatable();
                kosong();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Gagal menghapus: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_jHapusActionPerformed

    private void txtCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            datatable();
        }
    }//GEN-LAST:event_txtCariKeyPressed

    private void jCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCariActionPerformed
        datatable();
    }//GEN-LAST:event_jCariActionPerformed

    private void jSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSimpanActionPerformed
        try {
            String id = txtId.getText();
            Date tanggal = (Date) spinnerTanggal.getValue();
            // Ambil hanya NIK dari combo (format: "NIK - Nama")
            String nik = comboNik.getSelectedItem().toString().split(" - ")[0];
            // Ambil jenis layanan langsung (tidak perlu di-split)
            String jenis = comboJenis.getSelectedItem().toString();
            // Biaya (anggap TextField hanya angka)
            int biaya = Integer.parseInt(txtBiaya.getText());
            // Ambil hanya ID petugas dari combo (format: "ID - Nama")
            String petugas = comboPetugas.getSelectedItem().toString().split(" - ")[0];

            if (id.isEmpty() || nik.isEmpty() || jenis.isEmpty() || petugas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Lengkapi semua field!");
                return;
            }

            String tglFormatted = new SimpleDateFormat("yyyy-MM-dd").format(tanggal);
            String sql = "INSERT INTO layanan_kb (idtransaksi, tanggal_layanan, nik, jenis_layanan, biaya, id_petugas) VALUES (?, ?, ?, ?, ?, ?)";

            Connection conn = Mysql.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, tglFormatted);
            pst.setString(3, nik);
            pst.setString(4, jenis);
            pst.setInt(5, biaya);
            pst.setString(6, petugas);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Transaksi berhasil disimpan!");
            kosong(); datatable(); txtId.requestFocus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal menyimpan: " + e.getMessage());
        }
    }//GEN-LAST:event_jSimpanActionPerformed

    private void jKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKembaliActionPerformed
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "dataTransaksi");
    }//GEN-LAST:event_jKembaliActionPerformed

    private void jBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBayarActionPerformed
        int bar = tblTransaksi.getSelectedRow();
        if (bar == -1) {
            JOptionPane.showMessageDialog(null, "Pilih transaksi yang ingin dibayar!");
            return;
        }

        String id = tabmode.getValueAt(bar, 0).toString(); // ID Transaksi

        try {
            String sql = "UPDATE layanan_kb SET status='Lunas' WHERE idtransaksi=?";
            Connection conn = Mysql.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Transaksi berhasil dibayar.");
            datatable(); tblTransaksi.clearSelection();
            txtTotalBiaya.setText(""); // Kosongkan total
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal membayar: " + e.getMessage());
        }
    }//GEN-LAST:event_jBayarActionPerformed

    private void jCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCetakActionPerformed
      int bar = tblTransaksi.getSelectedRow();
    if (bar == -1) {
        JOptionPane.showMessageDialog(null, "Pilih transaksi yang ingin dicetak!");
        return;
    }

    String status = tabmode.getValueAt(bar, 6).toString(); // kolom status
    if (!status.equalsIgnoreCase("Lunas")) {
        JOptionPane.showMessageDialog(null, "Transaksi belum dibayar! Silakan bayar terlebih dahulu.");
        return;
    }

    try {
        // Ambil ID Transaksi
        String idtransaksi = tabmode.getValueAt(bar, 0).toString();
        System.out.println("ID Transaksi yang diambil dari tabel: [" + idtransaksi + "]");

        // Ambil koneksi dan file report
        Connection conn = Mysql.getConnection();

        InputStream report = getClass().getResourceAsStream("/com/Transaksi/Simple_Blue.jasper");
        if (report == null) {
            JOptionPane.showMessageDialog(null, "File laporan tidak ditemukan!");
            return;
        }

        // Parameter
        Map<String, Object> param = new HashMap<>();
        param.put("idtransaksi", idtransaksi); // Sesuai nama parameter di Jasper
        System.out.println("Parameter dikirim: idtransaksi = [" + idtransaksi + "]");

        // Proses cetak
        JasperPrint print = JasperFillManager.fillReport(report, param, conn);

        if (print.getPages().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Laporan tidak memiliki data untuk ID: " + idtransaksi);
        } else {
            JasperViewer viewer = new JasperViewer(print, false);
            viewer.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH); // Fullscreen
            viewer.setVisible(true);
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Gagal mencetak struk: " + e.getMessage());
        e.printStackTrace();
    }
    }//GEN-LAST:event_jCetakActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboJenis;
    private javax.swing.JComboBox<String> comboNik;
    private javax.swing.JComboBox<String> comboPetugas;
    private javax.swing.JPanel dataTransaksi;
    private javax.swing.JButton jBayar;
    private javax.swing.JButton jCari;
    private javax.swing.JButton jCetak;
    private javax.swing.JButton jHapus;
    private javax.swing.JButton jKembali;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jSimpan;
    private javax.swing.JButton jTambah;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JSpinner spinnerTanggal;
    private javax.swing.JPanel tambahTransaksi;
    private javax.swing.JTable tblTransaksi;
    private javax.swing.JTextField txtBiaya;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtTotalBiaya;
    // End of variables declaration//GEN-END:variables
}
