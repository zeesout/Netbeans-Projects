package com.FormMaster;

import com.Database.Mysql;
import java.awt.CardLayout;
import java.sql.Connection;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.util.Calendar;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class DataPeserta extends javax.swing.JPanel {

    DefaultTableModel tabmode;
    public DataPeserta() {
        initComponents();
        mainPanel.setLayout(new CardLayout());
        mainPanel.add(editPeserta, "editPeserta");
        mainPanel.add(dataPeserta, "dataPeserta");
        mainPanel.add(tambahPeserta, "tambahPeserta");
        switchTo("dataPeserta"); 
        kosong();
        aktif();
        datatable();
        initTanggalLahirSpinners();
    }
    
    protected void aktif(){
        txtNIK.requestFocus();
    }
    protected void kosong(){
        txtNIK.setText("");
        txtNama.setText("");
        txtAlamat.setText("");
        spinnerTanggalLahir.setValue(new java.util.Date());
        txtTelepon.setText("");
        txtSuami.setText("");
        spinnerAnak.setValue(0);
        comboStatus.setSelectedIndex(0);
    }
    private void resetEditForm() {
        txtEditNIK.setText("");
        txtEditNama.setText("");
        txtEditAlamat.setText("");
        spinnerEditTanggalLahir.setValue(new java.util.Date()); // default ke hari ini
        txtEditTelepon.setText("");
        txtEditSuami.setText("");
        spinnerEditAnak.setValue(0);
        comboEditStatus.setSelectedIndex(0); // Atau sesuai default pertama
    }
    private void resetTambahForm() {
        txtNIK.setText("");
        txtNama.setText("");
        txtAlamat.setText("");
        spinnerTanggalLahir.setValue(new java.util.Date()); // tanggal default
        txtTelepon.setText("");
        txtSuami.setText("");
        spinnerAnak.setValue(0);
        comboStatus.setSelectedIndex(0);
    }
    private void switchTo(String panelName) {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, panelName);
    }
    private void datatable() {
        String cari = txtCari.getText();
        Object[] kolom = {"NIK", "Nama", "Alamat", "Tanggal Lahir", "Telepon", "Nama Suami", "Jumlah Anak", "Status KB"};
        tabmode = new DefaultTableModel(null, kolom);
        tblPeserta.setModel(tabmode);
        
        tblPeserta.setModel(tabmode);
        tblPeserta.setRowHeight(40);
        tblPeserta.setShowGrid(true);
        tblPeserta.setGridColor(new java.awt.Color (230, 230, 230));
        tblPeserta.setShowHorizontalLines(true);
        tblPeserta.setShowVerticalLines(true);

        // Rata tengah semua kolom
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tblPeserta.getColumnCount(); i++) {
            tblPeserta.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        try {
            Connection conn = Mysql.getConnection();
            Statement st = conn.createStatement();
            
            String sql;
            if (cari.equals("")) {
                sql = "SELECT * FROM data_peserta_kb";
            } else {
                sql = "SELECT * FROM data_peserta_kb WHERE nik LIKE '%" + cari + "%' OR nama LIKE '%" + cari + "%'";
            }

            System.out.println("QUERY: " + sql);
            
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                tabmode.addRow(new Object[]{
                    rs.getString("nik"),
                    rs.getString("nama"),
                    rs.getString("alamat"),
                    rs.getString("tanggal_lahir"),
                    rs.getString("telepon"),
                    rs.getString("nama_suami"),
                    rs.getInt("jumlah_anak"),
                    rs.getString("status_kb")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mengambil data: " + e.getMessage());
        }
    }
    
    private void initTanggalLahirSpinners() {
        // Spinner untuk Tambah Peserta
        SpinnerDateModel modelTambah = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        spinnerTanggalLahir.setModel(modelTambah);
        JSpinner.DateEditor editorTambah = new JSpinner.DateEditor(spinnerTanggalLahir, "dd/MM/yyyy");
        spinnerTanggalLahir.setEditor(editorTambah);

        // Spinner untuk Edit Peserta
        SpinnerDateModel modelEdit = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        spinnerEditTanggalLahir.setModel(modelEdit);
        JSpinner.DateEditor editorEdit = new JSpinner.DateEditor(spinnerEditTanggalLahir, "dd/MM/yyyy");
        spinnerEditTanggalLahir.setEditor(editorEdit);
    }
    
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        dataPeserta = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTambah = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPeserta = new javax.swing.JTable();
        jEdit = new javax.swing.JButton();
        jHapus = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        jCari = new javax.swing.JButton();
        tambahPeserta = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNIK = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTelepon = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtSuami = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        spinnerAnak = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        comboStatus = new javax.swing.JComboBox<>();
        jSimpan = new javax.swing.JButton();
        jKembali = new javax.swing.JButton();
        spinnerTanggalLahir = new javax.swing.JSpinner();
        editPeserta = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtEditNIK = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtEditNama = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtEditTelepon = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtEditSuami = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtEditAlamat = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        spinnerEditAnak = new javax.swing.JSpinner();
        jLabel19 = new javax.swing.JLabel();
        comboEditStatus = new javax.swing.JComboBox<>();
        jEditSimpan = new javax.swing.JButton();
        jKembali1 = new javax.swing.JButton();
        spinnerEditTanggalLahir = new javax.swing.JSpinner();

        setLayout(new java.awt.CardLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Data Peserta KB");

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

        tblPeserta.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        tblPeserta.setForeground(new java.awt.Color(62, 68, 74));
        tblPeserta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "NIK", "Nama", "Alamat", "Tanggal Lahir", "Telepon", "Nama Suami", "Jumlah Anak", "Status KB"
            }
        ));
        tblPeserta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPesertaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPeserta);

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

        javax.swing.GroupLayout dataPesertaLayout = new javax.swing.GroupLayout(dataPeserta);
        dataPeserta.setLayout(dataPesertaLayout);
        dataPesertaLayout.setHorizontalGroup(
            dataPesertaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataPesertaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dataPesertaLayout.setVerticalGroup(
            dataPesertaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Tambah Data Peserta KB");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(119, 119, 127));
        jLabel3.setText("NIK");

        txtNIK.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtNIK.setForeground(new java.awt.Color(119, 119, 127));

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(119, 119, 127));
        jLabel4.setText("Nama");

        txtNama.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtNama.setForeground(new java.awt.Color(119, 119, 127));

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(119, 119, 127));
        jLabel5.setText("Nomor Telepon");

        txtTelepon.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtTelepon.setForeground(new java.awt.Color(119, 119, 127));

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(119, 119, 127));
        jLabel6.setText("Nama Suami");

        txtSuami.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtSuami.setForeground(new java.awt.Color(119, 119, 127));

        txtAlamat.setColumns(20);
        txtAlamat.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtAlamat.setForeground(new java.awt.Color(119, 119, 127));
        txtAlamat.setRows(5);
        jScrollPane2.setViewportView(txtAlamat);

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(119, 119, 127));
        jLabel7.setText("Alamat");

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(119, 119, 127));
        jLabel8.setText("Tanggal Lahir");

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(119, 119, 127));
        jLabel9.setText("Jumlah Anak");

        spinnerAnak.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        spinnerAnak.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1));

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(119, 119, 127));
        jLabel10.setText("Pilih Status KB");

        comboStatus.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        comboStatus.setForeground(new java.awt.Color(62, 68, 74));
        comboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aktif", "Non-Aktif", "Komplikasi", " " }));

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

        spinnerTanggalLahir.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        spinnerTanggalLahir.setModel(new javax.swing.SpinnerDateModel());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(283, 283, 283)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNIK)
                    .addComponent(txtNama)
                    .addComponent(txtTelepon)
                    .addComponent(txtSuami)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spinnerAnak)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jKembali, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spinnerTanggalLahir))
                .addContainerGap(283, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel3)
                .addGap(5, 5, 5)
                .addComponent(txtNIK, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel4)
                .addGap(5, 5, 5)
                .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel7)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel8)
                .addGap(5, 5, 5)
                .addComponent(spinnerTanggalLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel5)
                .addGap(5, 5, 5)
                .addComponent(txtTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel6)
                .addGap(5, 5, 5)
                .addComponent(txtSuami, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel9)
                .addGap(5, 5, 5)
                .addComponent(spinnerAnak, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel10)
                .addGap(5, 5, 5)
                .addComponent(comboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        javax.swing.GroupLayout tambahPesertaLayout = new javax.swing.GroupLayout(tambahPeserta);
        tambahPeserta.setLayout(tambahPesertaLayout);
        tambahPesertaLayout.setHorizontalGroup(
            tambahPesertaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tambahPesertaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tambahPesertaLayout.setVerticalGroup(
            tambahPesertaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Edit Data Peserta KB");

        jLabel12.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(119, 119, 127));
        jLabel12.setText("NIK");

        txtEditNIK.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtEditNIK.setForeground(new java.awt.Color(119, 119, 127));

        jLabel13.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(119, 119, 127));
        jLabel13.setText("Nama");

        txtEditNama.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtEditNama.setForeground(new java.awt.Color(119, 119, 127));

        jLabel14.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(119, 119, 127));
        jLabel14.setText("Nomor Telepon");

        txtEditTelepon.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtEditTelepon.setForeground(new java.awt.Color(119, 119, 127));

        jLabel15.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(119, 119, 127));
        jLabel15.setText("Nama Suami");

        txtEditSuami.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtEditSuami.setForeground(new java.awt.Color(119, 119, 127));

        txtEditAlamat.setColumns(20);
        txtEditAlamat.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtEditAlamat.setForeground(new java.awt.Color(119, 119, 127));
        txtEditAlamat.setRows(5);
        jScrollPane3.setViewportView(txtEditAlamat);

        jLabel16.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(119, 119, 127));
        jLabel16.setText("Alamat");

        jLabel17.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(119, 119, 127));
        jLabel17.setText("Tanggal Lahir");

        jLabel18.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(119, 119, 127));
        jLabel18.setText("Jumlah Anak");

        spinnerEditAnak.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        spinnerEditAnak.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1));

        jLabel19.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(119, 119, 127));
        jLabel19.setText("Pilih Status KB");

        comboEditStatus.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        comboEditStatus.setForeground(new java.awt.Color(62, 68, 74));
        comboEditStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aktif", "Non-Aktif", "Komplikasi", " " }));

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

        jKembali1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jKembali1.setForeground(new java.awt.Color(62, 68, 74));
        jKembali1.setText("< Kembali");
        jKembali1.setBorderPainted(false);
        jKembali1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKembali1ActionPerformed(evt);
            }
        });

        spinnerEditTanggalLahir.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        spinnerEditTanggalLahir.setModel(new javax.swing.SpinnerDateModel());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(283, 283, 283)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEditNIK)
                    .addComponent(txtEditNama)
                    .addComponent(txtEditTelepon)
                    .addComponent(txtEditSuami)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spinnerEditAnak)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboEditStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jEditSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jKembali1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spinnerEditTanggalLahir))
                .addContainerGap(283, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel12)
                .addGap(5, 5, 5)
                .addComponent(txtEditNIK, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel13)
                .addGap(5, 5, 5)
                .addComponent(txtEditNama, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel16)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel17)
                .addGap(5, 5, 5)
                .addComponent(spinnerEditTanggalLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel14)
                .addGap(5, 5, 5)
                .addComponent(txtEditTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel15)
                .addGap(5, 5, 5)
                .addComponent(txtEditSuami, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel18)
                .addGap(5, 5, 5)
                .addComponent(spinnerEditAnak, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel19)
                .addGap(5, 5, 5)
                .addComponent(comboEditStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jEditSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jKembali1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        javax.swing.GroupLayout editPesertaLayout = new javax.swing.GroupLayout(editPeserta);
        editPeserta.setLayout(editPesertaLayout);
        editPesertaLayout.setHorizontalGroup(
            editPesertaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editPesertaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        editPesertaLayout.setVerticalGroup(
            editPesertaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editPeserta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(tambahPeserta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(dataPeserta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editPeserta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(tambahPeserta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(dataPeserta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        add(mainPanel, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void jTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTambahActionPerformed
        resetTambahForm();
       
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "tambahPeserta");
    }//GEN-LAST:event_jTambahActionPerformed

    private void jEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEditActionPerformed
        System.out.println("Tombol Edit ditekan");
        int bar = tblPeserta.getSelectedRow();
        System.out.println("Baris terpilih: " + bar);

        if (bar != -1) {
            try {
                String nik = tabmode.getValueAt(bar, 0).toString();
                String nama = tabmode.getValueAt(bar, 1).toString();
                String alamat = tabmode.getValueAt(bar, 2).toString();
                String tanggal = tabmode.getValueAt(bar, 3).toString();
                String telepon = tabmode.getValueAt(bar, 4).toString();
                String suami = tabmode.getValueAt(bar, 5).toString();
                String anak = tabmode.getValueAt(bar, 6).toString();
                String status = tabmode.getValueAt(bar, 7).toString();

                System.out.println("Data diambil: " + nik + ", " + nama);

                txtEditNIK.setText(nik);
                txtEditNama.setText(nama);
                txtEditAlamat.setText(alamat);
                spinnerEditTanggalLahir.setValue(java.sql.Date.valueOf(tanggal));
                txtEditTelepon.setText(telepon);
                txtEditSuami.setText(suami);
                spinnerEditAnak.setValue(Integer.parseInt(anak));
                comboEditStatus.setSelectedItem(status);

                // Coba paksa pindah panel
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "editPeserta");
                System.out.println("Berpindah ke panel editPeserta");

            } catch (Exception e) {
                System.out.println("Error parsing data: " + e.getMessage());
            }

        } else {
            JOptionPane.showMessageDialog(null, "Pilih data yang ingin diedit terlebih dahulu.");
        }
    }//GEN-LAST:event_jEditActionPerformed

    private void jHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHapusActionPerformed
        int bar = tblPeserta.getSelectedRow();
        if (bar == -1) {
        JOptionPane.showMessageDialog(null, "Pilih data yang ingin dihapus!");
        return;
    }

    String nik = tabmode.getValueAt(bar, 0).toString();
    int konfirmasi = JOptionPane.showConfirmDialog(null, "Hapus data NIK: " + nik + "?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
    if (konfirmasi == JOptionPane.YES_OPTION) {
        try {
            Connection conn = Mysql.getConnection();
            String sql = "DELETE FROM data_peserta_kb WHERE nik=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nik);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
            datatable(); 
            kosong(); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal menghapus: " + e.getMessage());
        }
    } 
    }//GEN-LAST:event_jHapusActionPerformed

    private void jCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCariActionPerformed
        datatable();
    }//GEN-LAST:event_jCariActionPerformed

    private void jSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSimpanActionPerformed
        try {
            String nik = txtNIK.getText();
            String nama = txtNama.getText();
            String alamat = txtAlamat.getText();
            String telepon = txtTelepon.getText();
            String suami = txtSuami.getText();
            int jumlahAnak = (Integer) spinnerAnak.getValue();
            String status = (String) comboStatus.getSelectedItem();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String tanggalLahir = sdf.format(spinnerTanggalLahir.getValue());
        
            // Validasi sederhana
            if (nik.equals("") || nama.equals("") || alamat.equals("") || telepon.equals("") || suami.equals("")) {
                JOptionPane.showMessageDialog(null, "Lengkapi semua kolom!");
                return;
            }

            String sql = String.format(
                "INSERT INTO data_peserta_kb (nik, nama, alamat, tanggal_lahir, telepon, nama_suami, jumlah_anak, status_kb) " +
                "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', %d, '%s')",
                nik, nama, alamat, tanggalLahir, telepon, suami, jumlahAnak, status
            );

            com.Database.Mysql.execute(sql);
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
            kosong();
            datatable(); 
            txtNIK.requestFocus(); 
            tblPeserta.clearSelection();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal simpan data: " + e.getMessage());
        }
    }//GEN-LAST:event_jSimpanActionPerformed

    private void jKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKembaliActionPerformed
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "dataPeserta");
    }//GEN-LAST:event_jKembaliActionPerformed

    private void txtCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            datatable();
        }
    }//GEN-LAST:event_txtCariKeyPressed

    private void tblPesertaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPesertaMouseClicked
        int bar = tblPeserta.getSelectedRow();
        System.out.println("Klik! Baris terpilih: " + bar);

        if (bar != -1) {
            jEdit.setEnabled(true);
        }
     
        if (bar != -1) {
            String nik = tabmode.getValueAt(bar, 0).toString();
            String nama = tabmode.getValueAt(bar, 1).toString();
            String alamat = tabmode.getValueAt(bar, 2).toString();
            String tanggal = tabmode.getValueAt(bar, 3).toString();
            String telepon = tabmode.getValueAt(bar, 4).toString();
            String suami = tabmode.getValueAt(bar, 5).toString();
            String anak = tabmode.getValueAt(bar, 6).toString();
            String status = tabmode.getValueAt(bar, 7).toString();

            // Set ke komponen input
            txtNIK.setText(nik);
            txtNama.setText(nama);
            txtAlamat.setText(alamat);
            spinnerTanggalLahir.setValue(java.sql.Date.valueOf(tanggal));
            txtTelepon.setText(telepon);
            txtSuami.setText(suami);
            spinnerAnak.setValue(Integer.parseInt(anak));
            comboStatus.setSelectedItem(status);
        }
    }//GEN-LAST:event_tblPesertaMouseClicked

    private void jEditSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEditSimpanActionPerformed
        try {
            String sql = "UPDATE data_peserta_kb SET nama=?, alamat=?, tanggal_lahir=?, telepon=?, nama_suami=?, jumlah_anak=?, status_kb=? WHERE nik=?";
            Connection conn = Mysql.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, txtEditNama.getText());
            pst.setString(2, txtEditAlamat.getText());
            
            Date utilDate = (Date) spinnerEditTanggalLahir.getValue();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            pst.setDate(3, sqlDate);
            
            pst.setString(4, txtEditTelepon.getText());
            pst.setString(5, txtEditSuami.getText());
            pst.setInt(6, Integer.parseInt(spinnerEditAnak.getValue().toString()));
            pst.setString(7, comboEditStatus.getSelectedItem().toString());
            pst.setString(8, txtEditNIK.getText());

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diperbarui.");

            datatable(); 
            tblPeserta.clearSelection();
            resetEditForm();

            CardLayout cl = (CardLayout) mainPanel.getLayout();
            cl.show(mainPanel, "dataPeserta");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal menyimpan perubahan: " + e.getMessage());
        }
    }//GEN-LAST:event_jEditSimpanActionPerformed

    private void jKembali1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKembali1ActionPerformed
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "dataPeserta");
    }//GEN-LAST:event_jKembali1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboEditStatus;
    private javax.swing.JComboBox<String> comboStatus;
    private javax.swing.JPanel dataPeserta;
    private javax.swing.JPanel editPeserta;
    private javax.swing.JButton jCari;
    private javax.swing.JButton jEdit;
    private javax.swing.JButton jEditSimpan;
    private javax.swing.JButton jHapus;
    private javax.swing.JButton jKembali;
    private javax.swing.JButton jKembali1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jSimpan;
    private javax.swing.JButton jTambah;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JSpinner spinnerAnak;
    private javax.swing.JSpinner spinnerEditAnak;
    private javax.swing.JSpinner spinnerEditTanggalLahir;
    private javax.swing.JSpinner spinnerTanggalLahir;
    private javax.swing.JPanel tambahPeserta;
    private javax.swing.JTable tblPeserta;
    private javax.swing.JTextArea txtAlamat;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextArea txtEditAlamat;
    private javax.swing.JTextField txtEditNIK;
    private javax.swing.JTextField txtEditNama;
    private javax.swing.JTextField txtEditSuami;
    private javax.swing.JTextField txtEditTelepon;
    private javax.swing.JTextField txtNIK;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtSuami;
    private javax.swing.JTextField txtTelepon;
    // End of variables declaration//GEN-END:variables
}
