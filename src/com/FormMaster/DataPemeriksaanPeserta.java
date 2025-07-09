package com.FormMaster;

import com.Database.Mysql;
import java.awt.CardLayout;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;

public class DataPemeriksaanPeserta extends javax.swing.JPanel {

    DefaultTableModel tabmode;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public DataPemeriksaanPeserta() {
        initComponents();
        mainPanel.setLayout(new CardLayout());
        mainPanel.add(editPeriksa, "editPeriksa");
        mainPanel.add(tambahPeriksa, "tambahPeriksa");
        mainPanel.add(dataPeriksa, "dataPeriksa");
        switchTo("dataPeriksa");
        resetTambahForm();
        initTanggal();
        initTanggalEdit(); 
        loadComboNIK();
        loadComboPetugas();
        loadComboEditNIK();
        loadComboEditPetugas();
        datatable("");
        txtId.setEditable(false);
        txtEditId.setEditable(false);
    }

    private void switchTo(String name) {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, name);
    }

    private void initTanggal() {
        SpinnerDateModel model = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        spinnerTanggal.setModel(model);
        spinnerTanggal.setEditor(new JSpinner.DateEditor(spinnerTanggal, "dd/MM/yyyy"));
    }
    private void initTanggalEdit() {
        SpinnerDateModel model = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        spinnerEditTanggal.setModel(model);
        spinnerEditTanggal.setEditor(new JSpinner.DateEditor(spinnerEditTanggal, "dd/MM/yyyy"));
    }


    private void resetTambahForm() {
        generateIdPemeriksaan(); 
        comboNIK.setSelectedIndex(0);
        comboJenis.setSelectedIndex(0);
        txtBerat.setText("");
        txtTekanan.setText("");
        txtCatatan.setText("");
        comboPetugas.setSelectedIndex(0);
        spinnerTanggal.setValue(new java.util.Date());

    }
    private void resetEditForm() {
        txtEditId.setEditable(false);
        txtEditBerat.setText("");
        txtEditTekanan.setText("");
        txtEditCatatan.setText("");
        comboEditNIK.setSelectedIndex(0);
        comboEditJenis.setSelectedIndex(0);
        comboEditPetugas.setSelectedIndex(0);
        spinnerEditTanggal.setValue(new java.util.Date());
    }


    private void loadComboNIK() {
        comboNIK.removeAllItems();
        comboNIK.addItem("-- Pilih NIK --");
        try {
            Connection conn = Mysql.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT nik, nama FROM data_peserta_kb");
            while (rs.next()) {
                String item = rs.getString("nik") + " - " + rs.getString("nama");
                comboNIK.addItem(item);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal memuat NIK peserta: " + e.getMessage());
        }
    }
    private void loadComboEditNIK() {
        comboEditNIK.removeAllItems();
        comboEditNIK.addItem("-- Pilih NIK --");
        try {
            Connection conn = Mysql.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT nik, nama FROM data_peserta_kb");
            while (rs.next()) {
                String item = rs.getString("nik") + " - " + rs.getString("nama");
                comboEditNIK.addItem(item);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal memuat NIK peserta (edit): " + e.getMessage());
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
    private void loadComboEditPetugas() {
        comboEditPetugas.removeAllItems();
        comboEditPetugas.addItem("-- Pilih Petugas --");
        try {
            Connection conn = Mysql.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_petugas, nama FROM data_petugas");
            while (rs.next()) {
                String item = rs.getString("id_petugas") + " - " + rs.getString("nama");
                comboEditPetugas.addItem(item);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal memuat data petugas (edit): " + e.getMessage());
        }
    }

    private void datatable(String keyword) {
        String[] kolom = {"ID Pemeriksaan", "Tanggal Pemeriksaan", "NIK Peserta", "Jenis Pemeriksaan",
                          "Berat Badan", "Tekanan Darah", "Catatan Periksa", "ID Petugas", "Status Pemeriksaan"};
        tabmode = new DefaultTableModel(null, kolom);
        tblPemeriksaan.setModel(tabmode);

        tblPemeriksaan.setRowHeight(40);
        tblPemeriksaan.setShowGrid(true);
        tblPemeriksaan.setGridColor(new java.awt.Color(230, 230, 230));
        tblPemeriksaan.setShowHorizontalLines(true);
        tblPemeriksaan.setShowVerticalLines(true);

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tblPemeriksaan.getColumnCount(); i++) {
            tblPemeriksaan.getColumnModel().getColumn(i).setCellRenderer(center);
        }

        try {
            Connection conn = Mysql.getConnection();
            PreparedStatement ps;

            // Query dengan filter keyword ke semua kolom yang dicari
            String sql = "SELECT * FROM pemeriksaan_kb WHERE " +
                         "id_pemeriksaan LIKE ? OR nik LIKE ? OR jenis_pemeriksaan LIKE ? OR " +
                         "berat_badan LIKE ? OR tekanan_darah LIKE ? OR catatan LIKE ? OR id_petugas LIKE ? " +
                         "ORDER BY tanggal ASC";

            ps = conn.prepareStatement(sql);
            for (int i = 1; i <= 7; i++) {
                ps.setString(i, "%" + keyword + "%");
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tabmode.addRow(new Object[] {
                    rs.getString("id_pemeriksaan"),
                    rs.getString("tanggal"),
                    rs.getString("nik"),
                    rs.getString("jenis_pemeriksaan"),
                    rs.getString("berat_badan"),
                    rs.getString("tekanan_darah"),
                    rs.getString("catatan"),
                    rs.getString("id_petugas"),
                    rs.getString("status_pemeriksaan")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal memuat data: " + e.getMessage());
        }
    }

    private void generateIdPemeriksaan() {
        try {
            Connection conn = Mysql.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(id_pemeriksaan) FROM pemeriksaan_kb");

            if (rs.next()) {
                String lastId = rs.getString(1);
                if (lastId == null) {
                    txtId.setText("PMR001");
                } else {
                    int num = Integer.parseInt(lastId.substring(3)) + 1;
                    txtId.setText(String.format("PMR%03d", num));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal generate ID Pemeriksaan: " + e.getMessage());
        }
    }
    private String hitungStatus(String tekanan, int berat, String catatan) {
        if (tekanan.matches(".*(1[4-9][0-9]/[8-9][0-9]|1[0-9]{2}/1[0-9]{2}).*")) return "Perlu Rujukan";
        if (berat < 40 || berat > 100) return "Perlu Rujukan";
        if (catatan.toLowerCase().matches(".*(nyeri|demam|pusing|mual).*")) return "Komplikasi";
        return "Sehat";
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        dataPeriksa = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTambah = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPemeriksaan = new javax.swing.JTable();
        jEdit = new javax.swing.JButton();
        jHapus = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        jCari = new javax.swing.JButton();
        tambahPeriksa = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtCatatan = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jSimpan = new javax.swing.JButton();
        jKembali = new javax.swing.JButton();
        spinnerTanggal = new javax.swing.JSpinner();
        comboNIK = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        comboJenis = new javax.swing.JComboBox<>();
        txtBerat = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtTekanan = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        comboPetugas = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        editPeriksa = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtEditId = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtEditCatatan = new javax.swing.JTextArea();
        jLabel24 = new javax.swing.JLabel();
        jEditSimpan = new javax.swing.JButton();
        jKembali2 = new javax.swing.JButton();
        spinnerEditTanggal = new javax.swing.JSpinner();
        comboEditNIK = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        comboEditJenis = new javax.swing.JComboBox<>();
        txtEditBerat = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtEditTekanan = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        comboEditPetugas = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();

        setLayout(new java.awt.CardLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Data Pemeriksaan Peserta");

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

        tblPemeriksaan.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        tblPemeriksaan.setForeground(new java.awt.Color(62, 68, 74));
        tblPemeriksaan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Pemeriksaan", "Tanggal Pemeriksaan", "NIK Peserta", "Jenis Pemeriksaan", "Berat Badan", "Tekanan Darah", "Catatan Periksa", "ID Pertugas", "Status Pemeriksaan"
            }
        ));
        tblPemeriksaan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPemeriksaanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPemeriksaan);

        jEdit.setBackground(new java.awt.Color(190, 190, 190));
        jEdit.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jEdit.setForeground(new java.awt.Color(255, 255, 255));
        jEdit.setText("Edit");
        jEdit.setBorderPainted(false);
        jEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEditActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jCari, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 981, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCari, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dataPeriksaLayout = new javax.swing.GroupLayout(dataPeriksa);
        dataPeriksa.setLayout(dataPeriksaLayout);
        dataPeriksaLayout.setHorizontalGroup(
            dataPeriksaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataPeriksaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dataPeriksaLayout.setVerticalGroup(
            dataPeriksaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Tambah Data Pemeriksa");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(119, 119, 127));
        jLabel3.setText("ID Pemeriksaan");

        txtId.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtId.setForeground(new java.awt.Color(119, 119, 127));

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(119, 119, 127));
        jLabel4.setText("Tanggal Pemeriksaan");

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(119, 119, 127));
        jLabel5.setText("Berat Badan");

        txtCatatan.setColumns(20);
        txtCatatan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtCatatan.setForeground(new java.awt.Color(119, 119, 127));
        txtCatatan.setRows(5);
        jScrollPane2.setViewportView(txtCatatan);

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(119, 119, 127));
        jLabel7.setText("NIK Peserta");

        jSimpan.setBackground(new java.awt.Color(255, 140, 0));
        jSimpan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jSimpan.setForeground(new java.awt.Color(255, 255, 255));
        jSimpan.setText("Simpan Data Peserta");
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

        comboNIK.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        comboNIK.setForeground(new java.awt.Color(62, 68, 74));
        comboNIK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aktif", "Non-Aktif", "Komplikasi", " " }));

        jLabel20.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(119, 119, 127));
        jLabel20.setText("Jenis Pemeriksaan");

        comboJenis.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        comboJenis.setForeground(new java.awt.Color(62, 68, 74));
        comboJenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Konsultasi", "Pemeriksaan Rutin", "Lainnya" }));

        txtBerat.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtBerat.setForeground(new java.awt.Color(119, 119, 127));

        jLabel21.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(119, 119, 127));
        jLabel21.setText("Tekanan Darah");

        txtTekanan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtTekanan.setForeground(new java.awt.Color(119, 119, 127));

        jLabel22.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(119, 119, 127));
        jLabel22.setText("Catatan Pemeriksaan");

        comboPetugas.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        comboPetugas.setForeground(new java.awt.Color(62, 68, 74));
        comboPetugas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aktif", "Non-Aktif", "Komplikasi", " " }));

        jLabel23.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(119, 119, 127));
        jLabel23.setText("ID Petugas");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(283, 283, 283)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtId)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
                    .addComponent(jKembali, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spinnerTanggal)
                    .addComponent(comboNIK, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboJenis, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBerat)
                    .addComponent(jScrollPane2)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTekanan)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboPetugas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(194, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
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
                .addComponent(comboNIK, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel20)
                .addGap(5, 5, 5)
                .addComponent(comboJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel5)
                .addGap(5, 5, 5)
                .addComponent(txtBerat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel21)
                .addGap(5, 5, 5)
                .addComponent(txtTekanan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel22)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel23)
                .addGap(5, 5, 5)
                .addComponent(comboPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );

        javax.swing.GroupLayout tambahPeriksaLayout = new javax.swing.GroupLayout(tambahPeriksa);
        tambahPeriksa.setLayout(tambahPeriksaLayout);
        tambahPeriksaLayout.setHorizontalGroup(
            tambahPeriksaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tambahPeriksaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tambahPeriksaLayout.setVerticalGroup(
            tambahPeriksaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Edit Data Pemeriksa");

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(119, 119, 127));
        jLabel8.setText("ID Pemeriksaan");

        txtEditId.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtEditId.setForeground(new java.awt.Color(119, 119, 127));

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(119, 119, 127));
        jLabel9.setText("Tanggal Pemeriksaan");

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(119, 119, 127));
        jLabel10.setText("Berat Badan");

        txtEditCatatan.setColumns(20);
        txtEditCatatan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtEditCatatan.setForeground(new java.awt.Color(119, 119, 127));
        txtEditCatatan.setRows(5);
        jScrollPane4.setViewportView(txtEditCatatan);

        jLabel24.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(119, 119, 127));
        jLabel24.setText("NIK Peserta");

        jEditSimpan.setBackground(new java.awt.Color(255, 140, 0));
        jEditSimpan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jEditSimpan.setForeground(new java.awt.Color(255, 255, 255));
        jEditSimpan.setText("Update");
        jEditSimpan.setBorderPainted(false);
        jEditSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEditSimpanActionPerformed(evt);
            }
        });

        jKembali2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jKembali2.setForeground(new java.awt.Color(62, 68, 74));
        jKembali2.setText("< Kembali");
        jKembali2.setBorderPainted(false);
        jKembali2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKembali2ActionPerformed(evt);
            }
        });

        spinnerEditTanggal.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        spinnerEditTanggal.setModel(new javax.swing.SpinnerDateModel());

        comboEditNIK.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        comboEditNIK.setForeground(new java.awt.Color(62, 68, 74));
        comboEditNIK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aktif", "Non-Aktif", "Komplikasi", " " }));

        jLabel25.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(119, 119, 127));
        jLabel25.setText("Jenis Pemeriksaan");

        comboEditJenis.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        comboEditJenis.setForeground(new java.awt.Color(62, 68, 74));
        comboEditJenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Konsultasi", "Pemeriksaan Rutin", "Lainnya" }));

        txtEditBerat.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtEditBerat.setForeground(new java.awt.Color(119, 119, 127));

        jLabel26.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(119, 119, 127));
        jLabel26.setText("Tekanan Darah");

        txtEditTekanan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtEditTekanan.setForeground(new java.awt.Color(119, 119, 127));

        jLabel27.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(119, 119, 127));
        jLabel27.setText("Catatan Pemeriksaan");

        comboEditPetugas.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        comboEditPetugas.setForeground(new java.awt.Color(62, 68, 74));
        comboEditPetugas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aktif", "Non-Aktif", "Komplikasi", " " }));

        jLabel28.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(119, 119, 127));
        jLabel28.setText("ID Petugas");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(283, 283, 283)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEditId)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jEditSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
                    .addComponent(jKembali2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spinnerEditTanggal)
                    .addComponent(comboEditNIK, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboEditJenis, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtEditBerat)
                    .addComponent(jScrollPane4)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtEditTekanan)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboEditPetugas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(194, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel8)
                .addGap(5, 5, 5)
                .addComponent(txtEditId, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel9)
                .addGap(5, 5, 5)
                .addComponent(spinnerEditTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel24)
                .addGap(5, 5, 5)
                .addComponent(comboEditNIK, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel25)
                .addGap(5, 5, 5)
                .addComponent(comboEditJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel10)
                .addGap(5, 5, 5)
                .addComponent(txtEditBerat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel26)
                .addGap(5, 5, 5)
                .addComponent(txtEditTekanan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel27)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel28)
                .addGap(5, 5, 5)
                .addComponent(comboEditPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jEditSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jKembali2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );

        javax.swing.GroupLayout editPeriksaLayout = new javax.swing.GroupLayout(editPeriksa);
        editPeriksa.setLayout(editPeriksaLayout);
        editPeriksaLayout.setHorizontalGroup(
            editPeriksaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editPeriksaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        editPeriksaLayout.setVerticalGroup(
            editPeriksaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editPeriksa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(tambahPeriksa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(dataPeriksa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editPeriksa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(tambahPeriksa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(dataPeriksa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        add(mainPanel, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void jTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTambahActionPerformed
        resetTambahForm();

        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "tambahPeriksa");
    }//GEN-LAST:event_jTambahActionPerformed

    private void tblPemeriksaanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPemeriksaanMouseClicked
        int bar = tblPemeriksaan.getSelectedRow();
        if (bar != -1) {
            if (jEdit != null) jEdit.setEnabled(true);

            try {
                String id_pemeriksaan = tabmode.getValueAt(bar, 0).toString();
                String tanggalStr     = tabmode.getValueAt(bar, 1).toString();
                String nik            = tabmode.getValueAt(bar, 2).toString();
                String jenis          = tabmode.getValueAt(bar, 3).toString();
                String berat          = tabmode.getValueAt(bar, 4).toString();
                String tekanan        = tabmode.getValueAt(bar, 5).toString();
                String catatan        = tabmode.getValueAt(bar, 6).toString();
                String id_petugas     = tabmode.getValueAt(bar, 7).toString();

                txtEditId.setText(id_pemeriksaan);
                comboEditJenis.setSelectedItem(jenis);
                txtEditBerat.setText(berat);
                txtEditTekanan.setText(tekanan);
                txtEditCatatan.setText(catatan);

                // Atur comboEditNIK
                for (int i = 0; i < comboEditNIK.getItemCount(); i++) {
                    if (comboEditNIK.getItemAt(i).startsWith(nik + " ")) {
                        comboEditNIK.setSelectedIndex(i);
                        break;
                    }
                }

                // Atur comboEditPetugas
                for (int i = 0; i < comboEditPetugas.getItemCount(); i++) {
                    if (comboEditPetugas.getItemAt(i).startsWith(id_petugas + " ")) {
                        comboEditPetugas.setSelectedIndex(i);
                        break;
                    }
                }

                // Format tanggal
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date tanggal = sdf.parse(tanggalStr);
                spinnerEditTanggal.setValue(tanggal);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Gagal memuat data ke form edit: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_tblPemeriksaanMouseClicked

    private void jEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEditActionPerformed
        int bar = tblPemeriksaan.getSelectedRow();
        if (bar != -1) {
            try {
                String id_pemeriksaan = tabmode.getValueAt(bar, 0).toString();
                String tanggal = tabmode.getValueAt(bar, 1).toString();
                String nik = tabmode.getValueAt(bar, 2).toString(); // hanya nik
                String jenis = tabmode.getValueAt(bar, 3).toString();
                String berat = tabmode.getValueAt(bar, 4).toString();
                String tekanan = tabmode.getValueAt(bar, 5).toString();
                String catatan = tabmode.getValueAt(bar, 6).toString();
                String id_petugas = tabmode.getValueAt(bar, 7).toString(); // hanya id_petugas

                txtEditId.setText(id_pemeriksaan);
                comboEditJenis.setSelectedItem(jenis);
                txtEditBerat.setText(berat);
                txtEditTekanan.setText(tekanan);
                txtEditCatatan.setText(catatan);

                // Cari item yang mengandung NIK
                for (int i = 0; i < comboEditNIK.getItemCount(); i++) {
                    if (comboEditNIK.getItemAt(i).startsWith(nik + " ")) {
                        comboEditNIK.setSelectedIndex(i);
                        break;
                    }
                }

                // Cari item yang mengandung ID Petugas
                for (int i = 0; i < comboEditPetugas.getItemCount(); i++) {
                    if (comboEditPetugas.getItemAt(i).startsWith(id_petugas + " ")) {
                        comboEditPetugas.setSelectedIndex(i);
                        break;
                    }
                }

                // Disable agar tidak bisa diganti
                comboEditNIK.setEnabled(false);
                comboEditPetugas.setEnabled(false);

                // Tanggal
                spinnerEditTanggal.setValue(new SimpleDateFormat("yyyy-MM-dd").parse(tanggal));

                switchTo("editPeriksa");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Gagal mengambil data untuk edit: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pilih data yang ingin diedit terlebih dahulu.");
        }
    }//GEN-LAST:event_jEditActionPerformed

    private void jHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHapusActionPerformed
        int bar = tblPemeriksaan.getSelectedRow();
        if (bar == -1) {
            JOptionPane.showMessageDialog(null, "Pilih data yang ingin dihapus terlebih dahulu!");
            return;
        }

        String id_pemeriksaan = tabmode.getValueAt(bar, 0).toString();

        int konfirmasi = JOptionPane.showConfirmDialog(
            null,
            "Yakin ingin menghapus data dengan ID Pemeriksaan: " + id_pemeriksaan + "?",
            "Konfirmasi Hapus",
            JOptionPane.YES_NO_OPTION
        );

        if (konfirmasi == JOptionPane.YES_OPTION) {
            try {
                Connection conn = Mysql.getConnection();
                String sql = "DELETE FROM pemeriksaan_kb WHERE id_pemeriksaan = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, id_pemeriksaan);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Data berhasil dihapus.");
                datatable("");
                resetTambahForm();  // Atau resetEditForm() jika hapus dilakukan saat dalam form edit

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Gagal menghapus data: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_jHapusActionPerformed

    private void txtCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            datatable(txtCari.getText().trim());
        }
    }//GEN-LAST:event_txtCariKeyPressed

    private void jCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCariActionPerformed
        String keyword = txtCari.getText().trim();
        datatable(keyword);
    }//GEN-LAST:event_jCariActionPerformed

    private void jKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKembaliActionPerformed
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "dataPeriksa");
    }//GEN-LAST:event_jKembaliActionPerformed

    private void jSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSimpanActionPerformed
        try {
            String id_Pemeriksaan = txtId.getText(); // pastikan sudah di-generate
            String tanggal = sdf.format(spinnerTanggal.getValue());
            String nik = comboNIK.getSelectedItem().toString().split(" - ")[0]; // Ambil NIK saja
            String jenis = comboJenis.getSelectedItem().toString();
            double berat = Double.parseDouble(txtBerat.getText());
            String tekanan = txtTekanan.getText();
            String catatan = txtCatatan.getText();

            String id_petugas = comboPetugas.getSelectedItem().toString().split(" - ")[0]; // Ambil ID Petugas saja

            // Hitung status otomatis
            String status = hitungStatus(tekanan, (int)berat, catatan);

            // Validasi
            if (id_Pemeriksaan.equals("") || nik.equals("-- Pilih NIK --") || id_petugas.equals("-- Pilih Petugas --")) {
                JOptionPane.showMessageDialog(null, "Lengkapi semua kolom wajib!");
                return;
            }

            // INSERT lengkap termasuk ID Pemeriksaan
            String sql = "INSERT INTO pemeriksaan_kb (id_pemeriksaan, tanggal, nik, jenis_pemeriksaan, berat_badan, tekanan_darah, catatan, id_petugas, status_pemeriksaan) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            Connection conn = Mysql.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, id_Pemeriksaan);
            pst.setString(2, tanggal);
            pst.setString(3, nik);
            pst.setString(4, jenis);
            pst.setDouble(5, berat);
            pst.setString(6, tekanan);
            pst.setString(7, catatan);
            pst.setString(8, id_petugas);
            pst.setString(9, status);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data pemeriksaan berhasil disimpan!");
            datatable("");
            resetTambahForm();
            generateIdPemeriksaan(); // Generate lagi untuk input berikutnya

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal simpan data: " + e.getMessage());
        }
    }//GEN-LAST:event_jSimpanActionPerformed

    private void jEditSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEditSimpanActionPerformed
        try {
            String id_pemeriksaan = txtEditId.getText().trim();
            String nikItem = (String) comboEditNIK.getSelectedItem();
            String jenis = (String) comboEditJenis.getSelectedItem();
            String tekanan = txtEditTekanan.getText().trim();
            String catatan = txtEditCatatan.getText().trim();
            String petugasItem = (String) comboEditPetugas.getSelectedItem();
            String tanggal = sdf.format(spinnerEditTanggal.getValue());

            // Ambil NIK dari format: "0000111122223333 - Nama"
            String nik = nikItem != null && nikItem.contains(" - ") ? nikItem.split(" - ")[0] : "";
            String id_petugas = petugasItem != null && petugasItem.contains(" - ") ? petugasItem.split(" - ")[0] : "";

            if (id_pemeriksaan.isEmpty() || nik.isEmpty() || jenis == null || tekanan.isEmpty() || catatan.isEmpty() || id_petugas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Lengkapi semua kolom!");
                return;
            }

            int berat = Integer.parseInt(txtEditBerat.getText().trim());
            String status = hitungStatus(tekanan, berat, catatan);

            Connection conn = Mysql.getConnection();
            String sql = "UPDATE pemeriksaan_kb SET tanggal=?, nik=?, jenis_pemeriksaan=?, berat_badan=?, tekanan_darah=?, catatan=?, id_petugas=?, status_pemeriksaan=? WHERE id_pemeriksaan=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, tanggal);
            pst.setString(2, nik);
            pst.setString(3, jenis);
            pst.setDouble(4, berat);
            pst.setString(5, tekanan);
            pst.setString(6, catatan);
            pst.setString(7, id_petugas);
            pst.setString(8, status);
            pst.setString(9, id_pemeriksaan);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data berhasil diperbarui!");
            datatable("");
            resetEditForm();
            switchTo("dataPeriksa");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Berat badan harus berupa angka!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal edit data: " + e.getMessage());
        }
    }//GEN-LAST:event_jEditSimpanActionPerformed

    private void jKembali2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKembali2ActionPerformed
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "dataPeriksa");
    }//GEN-LAST:event_jKembali2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboEditJenis;
    private javax.swing.JComboBox<String> comboEditNIK;
    private javax.swing.JComboBox<String> comboEditPetugas;
    private javax.swing.JComboBox<String> comboJenis;
    private javax.swing.JComboBox<String> comboNIK;
    private javax.swing.JComboBox<String> comboPetugas;
    private javax.swing.JPanel dataPeriksa;
    private javax.swing.JPanel editPeriksa;
    private javax.swing.JButton jCari;
    private javax.swing.JButton jEdit;
    private javax.swing.JButton jEditSimpan;
    private javax.swing.JButton jHapus;
    private javax.swing.JButton jKembali;
    private javax.swing.JButton jKembali2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton jSimpan;
    private javax.swing.JButton jTambah;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JSpinner spinnerEditTanggal;
    private javax.swing.JSpinner spinnerTanggal;
    private javax.swing.JPanel tambahPeriksa;
    private javax.swing.JTable tblPemeriksaan;
    private javax.swing.JTextField txtBerat;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextArea txtCatatan;
    private javax.swing.JTextField txtEditBerat;
    private javax.swing.JTextArea txtEditCatatan;
    private javax.swing.JTextField txtEditId;
    private javax.swing.JTextField txtEditTekanan;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtTekanan;
    // End of variables declaration//GEN-END:variables
}
