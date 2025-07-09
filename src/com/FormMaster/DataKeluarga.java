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

public class DataKeluarga extends javax.swing.JPanel {

    DefaultTableModel tabmode;
    public DataKeluarga() {
        initComponents();
        mainPanel.setLayout(new CardLayout());
        mainPanel.add(editKeluarga, "editKeluarga");
        mainPanel.add(dataKeluarga, "dataKeluarga");
        mainPanel.add(tambahKeluarga, "tambahKeluarga");
        switchTo("dataKeluarga"); 
        kosong();
        aktif();
        datatable();
        loadDataSuami();
        txtEditKK.setEditable(false);
        comboEditSuami.setEditable(false);
    }

    protected void aktif(){
        txtKK.requestFocus();
    }
    protected void kosong(){
        txtKK.setText("");
        comboSuami.setSelectedIndex(0);
        spinnerAnggota.setValue(0);
        comboPekerjaan.setSelectedIndex(0);
        comboEkonomi.setSelectedIndex(0);
    }
    private void resetEditForm() {
        txtEditKK.setEditable(false);
        comboEditSuami.setEditable(false);
        spinnerEditAnggota.setValue(0);
        comboEditPekerjaan.setSelectedIndex(0);
        comboEditEkonomi.setSelectedIndex(0);
    }
    private void resetTambahForm() {
        txtKK.setText("");
        comboSuami.setSelectedIndex(0);
        spinnerAnggota.setValue(0);
        comboPekerjaan.setSelectedIndex(0);
        comboEkonomi.setSelectedIndex(0);
    }
    private void switchTo(String panelName) {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, panelName);
    }
    private void datatable() {
        String cari = txtCari.getText();
        Object[] kolom = {"No. KK", "Kepala Keluarga", "Jumlah Anggota", "Pekerjaan Kepala Keluarga", "Status Ekonomi"};
        tabmode = new DefaultTableModel(null, kolom);
        tblKeluarga.setModel(tabmode);

        tblKeluarga.setModel(tabmode);
        tblKeluarga.setRowHeight(40);
        tblKeluarga.setShowGrid(true);
        tblKeluarga.setGridColor(new java.awt.Color (230, 230, 230));
        tblKeluarga.setShowHorizontalLines(true);
        tblKeluarga.setShowVerticalLines(true);

        // Rata tengah semua kolom
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tblKeluarga.getColumnCount(); i++) {
            tblKeluarga.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        try {
            Connection conn = Mysql.getConnection();
            Statement st = conn.createStatement();

            String sql;
            if (cari.equals("")) {
                sql = "SELECT * FROM data_keluarga";
            } else {
                sql = "SELECT * FROM data_keluarga WHERE no_kk LIKE '%" + cari + "%' OR kepala_keluarga LIKE '%" + cari + "%'";
            }

            System.out.println("QUERY: " + sql);

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                tabmode.addRow(new Object[]{
                    rs.getString("no_kk"),
                    rs.getString("kepala_keluarga"),
                    rs.getString("jumlah_anggota"),
                    rs.getString("pekerjaan"),
                    rs.getString("status_ekonomi"),
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mengambil data: " + e.getMessage());
        }
    }

    private void loadDataSuami() {
        comboSuami.removeAllItems();
        comboSuami.addItem("-- Pilih Suami --");

        try {
            Connection conn = Mysql.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT DISTINCT nama_suami FROM data_peserta_kb WHERE nama_suami IS NOT NULL AND nama_suami <> ''");

            while (rs.next()) {
                comboSuami.addItem(rs.getString("nama_suami"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal memuat data suami: " + e.getMessage());
        }
    }

    private void loadDataEditSuami(String namaTerpilih) {
        comboEditSuami.removeAllItems();
        comboEditSuami.addItem("-- Pilih Suami --");

        try {
            Connection conn = Mysql.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT DISTINCT nama_suami FROM data_peserta_kb WHERE nama_suami IS NOT NULL AND nama_suami <> ''");

            while (rs.next()) {
                String nama = rs.getString("nama_suami");
                comboEditSuami.addItem(nama);
            }

            comboEditSuami.setSelectedItem(namaTerpilih);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal memuat data suami (Edit): " + e.getMessage());
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        dataKeluarga = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTambah = new javax.swing.JButton();
        jEdit = new javax.swing.JButton();
        jHapus = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        jCari = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKeluarga = new javax.swing.JTable();
        tambahKeluarga = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtKK = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        spinnerAnggota = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        comboEkonomi = new javax.swing.JComboBox<>();
        jSimpan = new javax.swing.JButton();
        jKembali = new javax.swing.JButton();
        comboPekerjaan = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        comboSuami = new javax.swing.JComboBox<>();
        editKeluarga = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtEditKK = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        spinnerEditAnggota = new javax.swing.JSpinner();
        jLabel12 = new javax.swing.JLabel();
        comboEditEkonomi = new javax.swing.JComboBox<>();
        jEditSimpan = new javax.swing.JButton();
        jKembali1 = new javax.swing.JButton();
        comboEditPekerjaan = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        comboEditSuami = new javax.swing.JComboBox<>();

        mainPanel.setLayout(new java.awt.CardLayout());

        dataKeluarga.setPreferredSize(new java.awt.Dimension(1076, 658));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1066, 658));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Data Keluarga");

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

        tblKeluarga.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No. KK", "Nama Suami", "Jumlah Anggota", "Pekerjaan Kepala Keluarga", "Status Ekonomi"
            }
        ));
        tblKeluarga.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKeluargaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblKeluarga);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 366, Short.MAX_VALUE)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jCari, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCari, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTambah, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jHapus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCari, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dataKeluargaLayout = new javax.swing.GroupLayout(dataKeluarga);
        dataKeluarga.setLayout(dataKeluargaLayout);
        dataKeluargaLayout.setHorizontalGroup(
            dataKeluargaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataKeluargaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1070, Short.MAX_VALUE))
        );
        dataKeluargaLayout.setVerticalGroup(
            dataKeluargaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainPanel.add(dataKeluarga, "card2");

        tambahKeluarga.setPreferredSize(new java.awt.Dimension(1076, 658));
        tambahKeluarga.setVerifyInputWhenFocusTarget(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1066, 658));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Tambah Data Keluarga");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(119, 119, 127));
        jLabel3.setText("No. KK");

        txtKK.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtKK.setForeground(new java.awt.Color(119, 119, 127));

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(119, 119, 127));
        jLabel4.setText("Nama Suami");

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(119, 119, 127));
        jLabel9.setText("Jumlah Anggota Keluarga");

        spinnerAnggota.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        spinnerAnggota.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1));

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(119, 119, 127));
        jLabel10.setText("Status Ekonomi");

        comboEkonomi.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        comboEkonomi.setForeground(new java.awt.Color(62, 68, 74));
        comboEkonomi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pratama", "Madya", "Purnama", "Lain-lainnya" }));

        jSimpan.setBackground(new java.awt.Color(255, 140, 0));
        jSimpan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jSimpan.setForeground(new java.awt.Color(255, 255, 255));
        jSimpan.setText("Simpan Data Keluarga");
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

        comboPekerjaan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        comboPekerjaan.setForeground(new java.awt.Color(62, 68, 74));
        comboPekerjaan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Petani", "Pedagang", "Pegawai Negeri Sipil", "Wiraswasta", "Lainnya", " ", " " }));

        jLabel20.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(119, 119, 127));
        jLabel20.setText("Pekerjaan Kepala Keluarga");

        comboSuami.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        comboSuami.setForeground(new java.awt.Color(62, 68, 74));
        comboSuami.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Petani", "Pedagang", "Pegawai Negeri Sipil", "Wiraswasta", "Lainnya", " ", " " }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(283, 283, 283)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtKK)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spinnerAnggota)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboEkonomi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addComponent(jKembali, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboPekerjaan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboSuami, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(283, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel3)
                .addGap(5, 5, 5)
                .addComponent(txtKK, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel4)
                .addGap(5, 5, 5)
                .addComponent(comboSuami, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel9)
                .addGap(5, 5, 5)
                .addComponent(spinnerAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel20)
                .addGap(5, 5, 5)
                .addComponent(comboPekerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel10)
                .addGap(5, 5, 5)
                .addComponent(comboEkonomi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tambahKeluargaLayout = new javax.swing.GroupLayout(tambahKeluarga);
        tambahKeluarga.setLayout(tambahKeluargaLayout);
        tambahKeluargaLayout.setHorizontalGroup(
            tambahKeluargaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tambahKeluargaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1070, Short.MAX_VALUE))
        );
        tambahKeluargaLayout.setVerticalGroup(
            tambahKeluargaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainPanel.add(tambahKeluarga, "card2");

        editKeluarga.setPreferredSize(new java.awt.Dimension(1076, 658));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(1066, 658));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Edit Data Keluarga");

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(119, 119, 127));
        jLabel6.setText("No. KK");

        txtEditKK.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtEditKK.setForeground(new java.awt.Color(119, 119, 127));

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(119, 119, 127));
        jLabel7.setText("Nama Suami");

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(119, 119, 127));
        jLabel11.setText("Jumlah Anggota Keluarga");

        spinnerEditAnggota.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        spinnerEditAnggota.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1));

        jLabel12.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(119, 119, 127));
        jLabel12.setText("Status Ekonomi");

        comboEditEkonomi.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        comboEditEkonomi.setForeground(new java.awt.Color(62, 68, 74));
        comboEditEkonomi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pratama", "Madya", "Purnama", "Lain-lainnya" }));

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

        comboEditPekerjaan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        comboEditPekerjaan.setForeground(new java.awt.Color(62, 68, 74));
        comboEditPekerjaan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Petani", "Pedagang", "Pegawai Negeri Sipil", "Wiraswasta", "Lainnya", " ", " " }));

        jLabel21.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(119, 119, 127));
        jLabel21.setText("Pekerjaan Kepala Keluarga");

        comboEditSuami.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        comboEditSuami.setForeground(new java.awt.Color(62, 68, 74));
        comboEditSuami.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Petani", "Pedagang", "Pegawai Negeri Sipil", "Wiraswasta", "Lainnya", " ", " " }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(283, 283, 283)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEditKK)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spinnerEditAnggota)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboEditEkonomi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jEditSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addComponent(jKembali1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboEditPekerjaan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboEditSuami, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(283, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel6)
                .addGap(5, 5, 5)
                .addComponent(txtEditKK, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel7)
                .addGap(5, 5, 5)
                .addComponent(comboEditSuami, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel11)
                .addGap(5, 5, 5)
                .addComponent(spinnerEditAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel21)
                .addGap(5, 5, 5)
                .addComponent(comboEditPekerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel12)
                .addGap(5, 5, 5)
                .addComponent(comboEditEkonomi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jEditSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jKembali1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout editKeluargaLayout = new javax.swing.GroupLayout(editKeluarga);
        editKeluarga.setLayout(editKeluargaLayout);
        editKeluargaLayout.setHorizontalGroup(
            editKeluargaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editKeluargaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 1070, Short.MAX_VALUE))
        );
        editKeluargaLayout.setVerticalGroup(
            editKeluargaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainPanel.add(editKeluarga, "card2");

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
        cl.show(mainPanel, "tambahKeluarga");
    }//GEN-LAST:event_jTambahActionPerformed

    private void jEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEditActionPerformed
        System.out.println("Tombol Edit ditekan");
        int bar = tblKeluarga.getSelectedRow();
        System.out.println("Baris terpilih: " + bar);

        if (bar != -1) {
            try {
                String no_kk           = tabmode.getValueAt(bar, 0).toString();
                String kepala_keluarga = tabmode.getValueAt(bar, 1).toString();
                String jumlah_anggota  = tabmode.getValueAt(bar, 2).toString();
                String pekerjaan       = tabmode.getValueAt(bar, 3).toString();
                String status_ekonomi  = tabmode.getValueAt(bar, 4).toString();

                System.out.println("Data diambil: " + no_kk + ", " + kepala_keluarga);

                // Isi data ke form edit
                txtEditKK.setText(no_kk);
                loadDataEditSuami(kepala_keluarga); // Memuat nama-nama suami dari DB ke comboEditSuami
                comboEditSuami.setSelectedItem(kepala_keluarga); // Pilih nama suami yang sesuai

                spinnerEditAnggota.setValue(Integer.parseInt(jumlah_anggota));
                comboEditPekerjaan.setSelectedItem(pekerjaan);
                comboEditEkonomi.setSelectedItem(status_ekonomi);

                // Beralih ke panel edit
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "editKeluarga");
                System.out.println("Berpindah ke panel editKeluarga");

            } catch (Exception e) {
                System.out.println("Error parsing data: " + e.getMessage());
            }

        } else {
            JOptionPane.showMessageDialog(null, "Pilih data yang ingin diedit terlebih dahulu.");
        }
    }//GEN-LAST:event_jEditActionPerformed

    private void jHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHapusActionPerformed
        int bar = tblKeluarga.getSelectedRow();
        if (bar == -1) {
            JOptionPane.showMessageDialog(null, "Pilih data yang ingin dihapus!");
            return;
        }

        String no_kk = tabmode.getValueAt(bar, 0).toString();
        int konfirmasi = JOptionPane.showConfirmDialog(null, "Hapus data No. KK: " + no_kk + "?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (konfirmasi == JOptionPane.YES_OPTION) {
            try {
                Connection conn = Mysql.getConnection();
                String sql = "DELETE FROM data_keluarga WHERE no_kk=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, no_kk);
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

    private void jSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSimpanActionPerformed
        try {
            String no_kk = txtKK.getText();
            String kepala_keluarga = (String) comboSuami.getSelectedItem();
            int jumlah_anggota = (Integer) spinnerAnggota.getValue();
            String pekerjaan = (String) comboPekerjaan.getSelectedItem();
            String status_ekonomi = (String) comboEkonomi.getSelectedItem();

            // Validasi sederhana
            if (no_kk.equals("") || kepala_keluarga == null || kepala_keluarga.equals("-- Pilih Suami --")) {
                JOptionPane.showMessageDialog(null, "Lengkapi semua kolom!");
                return;
            }

            String sql = String.format(
                "INSERT INTO data_keluarga (no_kk, kepala_keluarga, jumlah_anggota, pekerjaan, status_ekonomi) " +
                "VALUES ('%s', '%s', %d, '%s', '%s')",
                no_kk, kepala_keluarga, jumlah_anggota, pekerjaan, status_ekonomi
            );

            com.Database.Mysql.execute(sql);
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
            kosong();
            datatable();
            txtKK.requestFocus();
            tblKeluarga.clearSelection();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal simpan data: " + e.getMessage());
        }
    }//GEN-LAST:event_jSimpanActionPerformed

    private void jKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKembaliActionPerformed
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "dataKeluarga");
    }//GEN-LAST:event_jKembaliActionPerformed

    private void tblKeluargaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKeluargaMouseClicked
        int bar = tblKeluarga.getSelectedRow();
        System.out.println("Klik! Baris terpilih: " + bar);

        if (bar != -1) {
            jEdit.setEnabled(true);

            String no_kk = tabmode.getValueAt(bar, 0).toString();
            String kepala_keluarga = tabmode.getValueAt(bar, 1).toString();
            String jumlah_anggota = tabmode.getValueAt(bar, 2).toString();
            String pekerjaan = tabmode.getValueAt(bar, 3).toString();
            String status_ekonomi = tabmode.getValueAt(bar, 4).toString();

            // Set ke komponen input
            txtKK.setText(no_kk);
            comboSuami.setSelectedItem(kepala_keluarga);
            spinnerAnggota.setValue(Integer.parseInt(jumlah_anggota));
            comboPekerjaan.setSelectedItem(pekerjaan);
            comboEkonomi.setSelectedItem(status_ekonomi);
        }
    }//GEN-LAST:event_tblKeluargaMouseClicked

    private void jEditSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEditSimpanActionPerformed
        try {
        String kepala_keluarga = (String) comboEditSuami.getSelectedItem();

        // Validasi input
        if (kepala_keluarga == null || kepala_keluarga.equals("-- Pilih Suami --")) {
            JOptionPane.showMessageDialog(null, "Pilih kepala keluarga (suami) terlebih dahulu.");
            return;
        }

        String sql = "UPDATE data_keluarga SET kepala_keluarga=?, jumlah_anggota=?, pekerjaan=?, status_ekonomi=? WHERE no_kk=?";
        Connection conn = Mysql.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, kepala_keluarga);
        pst.setInt(2, Integer.parseInt(spinnerEditAnggota.getValue().toString()));
        pst.setString(3, comboEditPekerjaan.getSelectedItem().toString());
        pst.setString(4, comboEditEkonomi.getSelectedItem().toString());
        pst.setString(5, txtEditKK.getText());

        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data berhasil diperbarui.");

        datatable(); 
        tblKeluarga.clearSelection();
        resetEditForm();

        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "dataKeluarga");

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Gagal menyimpan perubahan: " + e.getMessage());
    }
    }//GEN-LAST:event_jEditSimpanActionPerformed

    private void jKembali1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKembali1ActionPerformed
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "dataKeluarga");
    }//GEN-LAST:event_jKembali1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboEditEkonomi;
    private javax.swing.JComboBox<String> comboEditPekerjaan;
    private javax.swing.JComboBox<String> comboEditSuami;
    private javax.swing.JComboBox<String> comboEkonomi;
    private javax.swing.JComboBox<String> comboPekerjaan;
    private javax.swing.JComboBox<String> comboSuami;
    private javax.swing.JPanel dataKeluarga;
    private javax.swing.JPanel editKeluarga;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jSimpan;
    private javax.swing.JButton jTambah;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JSpinner spinnerAnggota;
    private javax.swing.JSpinner spinnerEditAnggota;
    private javax.swing.JPanel tambahKeluarga;
    private javax.swing.JTable tblKeluarga;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtEditKK;
    private javax.swing.JTextField txtKK;
    // End of variables declaration//GEN-END:variables
}
