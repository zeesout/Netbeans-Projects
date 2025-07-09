package com.Transaksi;

import com.Database.Mysql;
import java.awt.CardLayout;
import java.sql.Connection;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.util.Calendar;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class DaftarPeserta extends javax.swing.JPanel {

    DefaultTableModel tabmode;
    private Map<String, String> nikToHp = new HashMap<>();

    public DaftarPeserta() {
        initComponents();
        mainPanel.setLayout(new CardLayout());
        mainPanel.add(editPesertaKB, "editPesertaKB");
        mainPanel.add(dataPesertaKB, "dataPesertaKB");
        mainPanel.add(tambahPesertaKB, "tambahPesertaKB");
        switchTo("dataPesertaKB"); 
        kosong();
        aktif();
        datatable();
        initTanggalLahirSpinners();
        txtId.setEditable(false);
        txtNoHP.setEditable(false);
        txtEditId.setEditable(false);
        comboEditNik.setEditable(false);
        generateIdPendaftaran();
        loadNIKPeserta();
        setupComboNikListener();
    }

    protected void aktif(){
        txtId.requestFocus();
    }
    protected void kosong(){
        txtId.setText("");
        Date now = new Date();
        spinnerDaftar.setValue(now);
        comboNik.setSelectedItem(null);
        txtEditNoHP.setText("");
    }
    private void resetEditForm() {
        txtEditId.setEditable(false); 
        txtEditId.setText("");     
        spinnerEditDaftar.setValue(new Date());
        comboEditNik.removeAllItems();
        try {
            Connection conn = Mysql.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT nik FROM data_peserta_kb");
            while (rs.next()) {
                comboEditNik.addItem(rs.getString("nik"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal load NIK edit: " + e.getMessage());
        }

        txtEditNoHP.setText("");
    }
    private void resetTambahForm() {
        generateIdPendaftaran();
        txtId.setEditable(false);
        comboNik.removeAllItems();
        loadNIKPeserta();
        spinnerDaftar.setValue(new Date());
        txtEditNoHP.setText("");
    }
    private void resetEditTambahForm() {
        txtEditId.setEditable(false);
        comboEditNik.setEditable(false);
        spinnerEditDaftar.setValue(new Date());
        txtEditNoHP.setText("");
    }
    private void switchTo(String panelName) {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, panelName);
    }
    private void datatable() {
        String cari = txtCari.getText();
        Object[] kolom = {"ID Pendaftaran", "Tanggal Daftar", "NIK Peserta", "No. Handphone"};
        tabmode = new DefaultTableModel(null, kolom);
        tblPendaftaran.setModel(tabmode);

        tblPendaftaran.setRowHeight(40);
        tblPendaftaran.setShowGrid(true);
        tblPendaftaran.setGridColor(new java.awt.Color(230, 230, 230));
        tblPendaftaran.setShowHorizontalLines(true);
        tblPendaftaran.setShowVerticalLines(true);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tblPendaftaran.getColumnCount(); i++) {
            tblPendaftaran.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        try {
            Connection conn = Mysql.getConnection();
            Statement st = conn.createStatement();

            String sql;
            if (cari.equals("")) {
                sql = "SELECT p.id_pendaftaran, p.tanggal_daftar, p.nik, d.telepon FROM pendaftaran_kb p JOIN data_peserta_kb d ON p.nik = d.nik";
            } else {
                sql = "SELECT p.id_pendaftaran, p.tanggal_daftar, p.nik, d.telepon FROM pendaftaran_kb p JOIN data_peserta_kb d ON p.nik = d.nik WHERE p.id_pendaftaran LIKE '%" + cari + "%' OR p.nik LIKE '%" + cari + "%'";
            }

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                tabmode.addRow(new Object[]{
                    rs.getString("id_pendaftaran"),
                    rs.getString("tanggal_daftar"),
                    rs.getString("nik"),
                    rs.getString("telepon")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mengambil data: " + e.getMessage());
        }
    }
    private void initTanggalLahirSpinners() {
        SpinnerDateModel modelTambah = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        spinnerDaftar.setModel(modelTambah);
        JSpinner.DateEditor editorTambah = new JSpinner.DateEditor(spinnerDaftar, "dd/MM/yyyy");
        spinnerDaftar.setEditor(editorTambah);

        SpinnerDateModel modelEdit = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        spinnerEditDaftar.setModel(modelEdit);
        JSpinner.DateEditor editorEdit = new JSpinner.DateEditor(spinnerDaftar, "dd/MM/yyyy");
        spinnerEditDaftar.setEditor(editorEdit);
    }
    private void loadNIKPeserta() {
        comboNik.removeAllItems();
        comboNik.addItem("-- Pilih NIK --");
        try {
            Connection conn = Mysql.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT nik, nama, telepon FROM data_peserta_kb");
            while (rs.next()) {
                String nik = rs.getString("nik");
                String nama = rs.getString("nama");
                String noHp = rs.getString("telepon");
                String item = nik + " - " + nama;
                comboNik.addItem(item);
                nikToHp.put(nik, noHp);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal memuat NIK peserta: " + e.getMessage());
        }
    }
    private void loadEditNIKPeserta() {
        comboEditNik.removeAllItems();
        comboEditNik.addItem("-- Pilih NIK --");
        try {
            Connection conn = Mysql.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT nik, nama FROM data_peserta_kb");
            while (rs.next()) {
                String item = rs.getString("nik") + " - " + rs.getString("nama");
                comboEditNik.addItem(item);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal memuat NIK peserta (edit): " + e.getMessage());
        }
    }
    private void setupComboNikListener() {
            comboNik.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) comboNik.getSelectedItem();
                if (selectedItem != null && selectedItem.contains(" - ")) {
                    String selectedNik = selectedItem.split(" - ")[0];
                    String noHp = nikToHp.get(selectedNik);
                    txtNoHP.setText(noHp != null ? noHp : "");
                }
            }
        });
    }
    private void generateIdPendaftaran() {
        try {
            Connection conn = Mysql.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(id_pendaftaran) FROM pendaftaran_kb");

            if (rs.next()) {
                String lastId = rs.getString(1);
                if (lastId == null) {
                    txtId.setText("DFT001");
                } else {
                    int num = Integer.parseInt(lastId.substring(3)) + 1;
                    txtId.setText(String.format("DFT%03d", num));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal generate ID: " + e.getMessage());
        }
    }

    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        dataPesertaKB = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTambah = new javax.swing.JButton();
        jEdit = new javax.swing.JButton();
        jHapus = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        jCari = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPendaftaran = new javax.swing.JTable();
        tambahPesertaKB = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        spinnerDaftar = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        jSimpan = new javax.swing.JButton();
        jKembali = new javax.swing.JButton();
        comboNik = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        txtNoHP = new javax.swing.JTextField();
        editPesertaKB = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtEditId = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        spinnerEditDaftar = new javax.swing.JSpinner();
        jLabel14 = new javax.swing.JLabel();
        jEditSimpan = new javax.swing.JButton();
        jKembali1 = new javax.swing.JButton();
        comboEditNik = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        txtEditNoHP = new javax.swing.JTextField();

        mainPanel.setLayout(new java.awt.CardLayout());

        dataPesertaKB.setPreferredSize(new java.awt.Dimension(1076, 658));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1066, 658));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Data Pendaftaran Peserta KB");

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

        tblPendaftaran.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        tblPendaftaran.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Pendaftaran", "Tanggal Daftar", "NIK Peserta", "Telepon"
            }
        ));
        tblPendaftaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPendaftaranMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblPendaftaran);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(366, 366, 366)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jCari, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 986, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dataPesertaKBLayout = new javax.swing.GroupLayout(dataPesertaKB);
        dataPesertaKB.setLayout(dataPesertaKBLayout);
        dataPesertaKBLayout.setHorizontalGroup(
            dataPesertaKBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataPesertaKBLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dataPesertaKBLayout.setVerticalGroup(
            dataPesertaKBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainPanel.add(dataPesertaKB, "card2");

        tambahPesertaKB.setPreferredSize(new java.awt.Dimension(1076, 658));
        tambahPesertaKB.setVerifyInputWhenFocusTarget(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1066, 658));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Tambah Pendaftaran Peserta KB");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(119, 119, 127));
        jLabel3.setText("ID Pendaftaran");

        txtId.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtId.setForeground(new java.awt.Color(119, 119, 127));

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(119, 119, 127));
        jLabel9.setText("Tanggal Daftar");

        spinnerDaftar.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        spinnerDaftar.setModel(new javax.swing.SpinnerDateModel());

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(119, 119, 127));
        jLabel10.setText("Nomor Telepon");

        jSimpan.setBackground(new java.awt.Color(255, 140, 0));
        jSimpan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jSimpan.setForeground(new java.awt.Color(255, 255, 255));
        jSimpan.setText("Simpan Data Pendaftar");
        jSimpan.setBorderPainted(false);
        jSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSimpanActionPerformed(evt);
            }
        });

        jKembali.setBackground(new java.awt.Color(255, 255, 255));
        jKembali.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jKembali.setForeground(new java.awt.Color(62, 68, 74));
        jKembali.setText("< Kembali");
        jKembali.setBorderPainted(false);
        jKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKembaliActionPerformed(evt);
            }
        });

        comboNik.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        comboNik.setForeground(new java.awt.Color(62, 68, 74));

        jLabel20.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(119, 119, 127));
        jLabel20.setText("NIK Peserta");

        txtNoHP.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtNoHP.setForeground(new java.awt.Color(119, 119, 127));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(283, 283, 283)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtId)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spinnerDaftar)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addComponent(jKembali, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboNik, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNoHP))
                .addContainerGap(283, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(jLabel3)
                .addGap(5, 5, 5)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel9)
                .addGap(5, 5, 5)
                .addComponent(spinnerDaftar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel20)
                .addGap(5, 5, 5)
                .addComponent(comboNik, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel10)
                .addGap(5, 5, 5)
                .addComponent(txtNoHP, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tambahPesertaKBLayout = new javax.swing.GroupLayout(tambahPesertaKB);
        tambahPesertaKB.setLayout(tambahPesertaKBLayout);
        tambahPesertaKBLayout.setHorizontalGroup(
            tambahPesertaKBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tambahPesertaKBLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tambahPesertaKBLayout.setVerticalGroup(
            tambahPesertaKBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainPanel.add(tambahPesertaKB, "card2");

        editPesertaKB.setPreferredSize(new java.awt.Dimension(1076, 658));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(1066, 658));

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Edit Pendaftaran Peserta KB");

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(119, 119, 127));
        jLabel8.setText("ID Pendaftaran");

        txtEditId.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtEditId.setForeground(new java.awt.Color(119, 119, 127));

        jLabel13.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(119, 119, 127));
        jLabel13.setText("Tanggal Daftar");

        spinnerEditDaftar.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        spinnerEditDaftar.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1));

        jLabel14.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(119, 119, 127));
        jLabel14.setText("Nomor Telepon");

        jEditSimpan.setBackground(new java.awt.Color(255, 140, 0));
        jEditSimpan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jEditSimpan.setForeground(new java.awt.Color(255, 255, 255));
        jEditSimpan.setText("Edit Data Pendaftar");
        jEditSimpan.setBorderPainted(false);
        jEditSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEditSimpanActionPerformed(evt);
            }
        });

        jKembali1.setBackground(new java.awt.Color(255, 255, 255));
        jKembali1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jKembali1.setForeground(new java.awt.Color(62, 68, 74));
        jKembali1.setText("< Kembali");
        jKembali1.setBorderPainted(false);
        jKembali1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKembali1ActionPerformed(evt);
            }
        });

        comboEditNik.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        comboEditNik.setForeground(new java.awt.Color(62, 68, 74));

        jLabel22.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(119, 119, 127));
        jLabel22.setText("NIK Peserta");

        txtEditNoHP.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtEditNoHP.setForeground(new java.awt.Color(119, 119, 127));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(283, 283, 283)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEditId)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spinnerEditDaftar)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jEditSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addComponent(jKembali1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboEditNik, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtEditNoHP))
                .addContainerGap(283, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(jLabel8)
                .addGap(5, 5, 5)
                .addComponent(txtEditId, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel13)
                .addGap(5, 5, 5)
                .addComponent(spinnerEditDaftar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel22)
                .addGap(5, 5, 5)
                .addComponent(comboEditNik, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel14)
                .addGap(5, 5, 5)
                .addComponent(txtEditNoHP, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jEditSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jKembali1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout editPesertaKBLayout = new javax.swing.GroupLayout(editPesertaKB);
        editPesertaKB.setLayout(editPesertaKBLayout);
        editPesertaKBLayout.setHorizontalGroup(
            editPesertaKBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editPesertaKBLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        editPesertaKBLayout.setVerticalGroup(
            editPesertaKBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainPanel.add(editPesertaKB, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1076, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 658, Short.MAX_VALUE)
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
        cl.show(mainPanel, "tambahPesertaKB");
    }//GEN-LAST:event_jTambahActionPerformed

    private void jEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEditActionPerformed
        System.out.println("Tombol Edit ditekan");
        int bar = tblPendaftaran.getSelectedRow();
        System.out.println("Baris terpilih: " + bar);

        if (bar != -1) {
            try {
                String id_pendaftaran   = tabmode.getValueAt(bar, 0).toString();
                String tanggal_daftar   = tabmode.getValueAt(bar, 1).toString();
                String nik_peserta      = tabmode.getValueAt(bar, 2).toString();
                String noHp             = tabmode.getValueAt(bar, 3).toString();

                loadEditNIKPeserta();

                System.out.println("Data diambil: " + id_pendaftaran + ", " + nik_peserta);

                txtEditId.setText(id_pendaftaran);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date parsedDate = sdf.parse(tanggal_daftar);
                spinnerEditDaftar.setValue(parsedDate);

                for (int i = 0; i < comboEditNik.getItemCount(); i++) {
                    String item = comboEditNik.getItemAt(i);
                    if (item.startsWith(nik_peserta)) {
                        comboEditNik.setSelectedIndex(i);
                        break;
                    }
                }

                txtEditNoHP.setText(noHp);

                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "editPesertaKB");
                System.out.println("Berpindah ke panel editPesertaKB");

            } catch (Exception e) {
                System.out.println("Error parsing data: " + e.getMessage());
            }

        } else {
            JOptionPane.showMessageDialog(null, "Pilih data yang ingin diedit terlebih dahulu.");
        }
    }//GEN-LAST:event_jEditActionPerformed

    private void jHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHapusActionPerformed
        int bar = tblPendaftaran.getSelectedRow();
        if (bar == -1) {
            JOptionPane.showMessageDialog(null, "Pilih data yang ingin dihapus!");
            return;
        }

        String id_pendaftaran = tabmode.getValueAt(bar, 0).toString();
        int konfirmasi = JOptionPane.showConfirmDialog(null, "Hapus data ID Pendaftaran: " + id_pendaftaran + "?", "Konfirmasi", JOptionPane.YES_NO_OPTION);

        if (konfirmasi == JOptionPane.YES_OPTION) {
            try {
                Connection conn = Mysql.getConnection();
                String sql = "DELETE FROM pendaftaran_kb WHERE id_pendaftaran=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, id_pendaftaran);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
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

    private void tblPendaftaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPendaftaranMouseClicked
        int bar = tblPendaftaran.getSelectedRow();
        System.out.println("Klik! Baris terpilih: " + bar);

        if (bar != -1) {
            jEdit.setEnabled(true);
        }

        if (bar != -1) {
            String id_pendaftaran = tabmode.getValueAt(bar, 0).toString();
            String tanggal_daftar = tabmode.getValueAt(bar, 1).toString();
            String nik = tabmode.getValueAt(bar, 2).toString();
            String noHp = tabmode.getValueAt(bar, 3).toString();

            txtId.setText(id_pendaftaran);

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date tgl = sdf.parse(tanggal_daftar);
                spinnerDaftar.setValue(tgl);
            } catch (Exception e) {
                System.out.println("Gagal parsing tanggal: " + e.getMessage());
            }

            // Temukan item NIK yang cocok (karena comboNIK formatnya: "nik - nama")
            for (int i = 0; i < comboNik.getItemCount(); i++) {
                String item = (String) comboNik.getItemAt(i);
                if (item.startsWith(nik)) {
                    comboNik.setSelectedIndex(i);
                    break;
                }
            }

            txtNoHP.setText(noHp);
        }                                        
    }//GEN-LAST:event_tblPendaftaranMouseClicked

    private void jSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSimpanActionPerformed
        try {
        String id_pendaftaran = txtId.getText();
        Date tanggal = (Date) spinnerDaftar.getValue();
        String selected = (String) comboNik.getSelectedItem();
        String noHp = txtNoHP.getText();

        if (tanggal == null || selected == null || selected.equals("-- Pilih NIK --")) {
            JOptionPane.showMessageDialog(null, "Lengkapi semua kolom!");
            return;
        }

        String nik = selected.split(" - ")[0]; // Ambil hanya NIK-nya
        String tglFormatted = new SimpleDateFormat("yyyy-MM-dd").format(tanggal);

        String sql = "INSERT INTO pendaftaran_kb (id_pendaftaran, tanggal_daftar, nik, telepon) VALUES (?, ?, ?, ?)";
        Connection conn = Mysql.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, id_pendaftaran);
        pst.setString(2, tglFormatted);
        pst.setString(3, nik);
        pst.setString(4, noHp); // simpan langsung ke kolom 'no_hp'
        pst.executeUpdate();

        JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
        kosong();
        datatable();
        txtId.requestFocus();
        tblPendaftaran.clearSelection();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Gagal simpan data: " + e.getMessage());
    }
    }//GEN-LAST:event_jSimpanActionPerformed

    private void jKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKembaliActionPerformed
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "dataPesertaKB");
    }//GEN-LAST:event_jKembaliActionPerformed

    private void jEditSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEditSimpanActionPerformed
        try {
        String sql = "UPDATE pendaftaran_kb SET tanggal_daftar = ?, nik = ?, telepon = ? WHERE id_pendaftaran = ?";
        Connection conn = Mysql.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);

        Date tanggal = (Date) spinnerEditDaftar.getValue();
        String tglFormatted = new SimpleDateFormat("yyyy-MM-dd").format(tanggal);

        String selected = comboEditNik.getSelectedItem().toString();
        String nik = selected.split(" - ")[0]; // Ambil hanya NIK-nya

        pst.setString(1, tglFormatted);
        pst.setString(2, nik);
        pst.setString(3, txtEditNoHP.getText()); // field no_hp
        pst.setString(4, txtEditId.getText());

        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data berhasil diperbarui.");

        datatable(); 
        tblPendaftaran.clearSelection();
        resetEditForm();

        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "dataPesertaKB");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Gagal menyimpan perubahan: " + e.getMessage());
    }
    }//GEN-LAST:event_jEditSimpanActionPerformed

    private void jKembali1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKembali1ActionPerformed
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "dataPesertaKB");
    }//GEN-LAST:event_jKembali1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboEditNik;
    private javax.swing.JComboBox<String> comboNik;
    private javax.swing.JPanel dataPesertaKB;
    private javax.swing.JPanel editPesertaKB;
    private javax.swing.JButton jCari;
    private javax.swing.JButton jEdit;
    private javax.swing.JButton jEditSimpan;
    private javax.swing.JButton jHapus;
    private javax.swing.JButton jKembali;
    private javax.swing.JButton jKembali1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jSimpan;
    private javax.swing.JButton jTambah;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JSpinner spinnerDaftar;
    private javax.swing.JSpinner spinnerEditDaftar;
    private javax.swing.JPanel tambahPesertaKB;
    private javax.swing.JTable tblPendaftaran;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtEditId;
    private javax.swing.JTextField txtEditNoHP;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNoHP;
    // End of variables declaration//GEN-END:variables
}
