package com.FormMaster;

import com.Database.Mysql;
import java.awt.CardLayout;
import java.sql.Connection;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class DataPetugas extends javax.swing.JPanel {

    DefaultTableModel tabmode;
    public DataPetugas() {
        initComponents();
        mainPanel.setLayout(new CardLayout());
        mainPanel.add(editPetugas, "editPetugas");
        mainPanel.add(dataPetugas, "dataPetugas");
        mainPanel.add(tambahPetugas, "tambahPetugas");
        switchTo("dataPetugas"); 
        kosong();
        aktif();
        datatable();
        txtId.setEditable(false);
        txtEditId.setEditable(false);
    }
    
    protected void aktif(){
        txtId.requestFocus();
    }
    protected void kosong(){
        txtId.setText("");
        txtNama.setText("");
        comboJabatan.setSelectedIndex(0);
        txtEditNohp.setText("");

    }
    private void resetEditForm() {
        txtEditId.setEditable(false);
        txtEditNama.setText("");
        comboEditJabatan.setSelectedIndex(0);
        txtEditNohp.setText("");

    }
    private void resetTambahForm() {
        generateIdPetugas();
        txtNama.setText("");
        comboJabatan.setSelectedIndex(0);
        txtEditNohp.setText("");
    }

    private void switchTo(String panelName) {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, panelName);
    }
    private void datatable() {
        String cari = txtCari.getText();
        Object[] kolom = {"ID Petugas", "Nama Petugas", "Jenis Kelamin", "Jabatan", "No. Handphone"};
        tabmode = new DefaultTableModel(null, kolom);
        tblPetugas.setModel(tabmode);
        
        tblPetugas.setModel(tabmode);
        tblPetugas.setRowHeight(40);
        tblPetugas.setShowGrid(true);
        tblPetugas.setGridColor(new java.awt.Color (230, 230, 230));
        tblPetugas.setShowHorizontalLines(true);
        tblPetugas.setShowVerticalLines(true);

        // Rata tengah semua kolom
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tblPetugas.getColumnCount(); i++) {
            tblPetugas.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        try {
            Connection conn = Mysql.getConnection();
            Statement st = conn.createStatement();
            
            String sql;
            if (cari.equals("")) {
                sql = "SELECT * FROM data_petugas";
            } else {
                sql = "SELECT * FROM data_petugas WHERE id_petugas LIKE '%" + cari + "%' OR nama LIKE '%" + cari + "%'";
            }

            System.out.println("QUERY: " + sql);
            
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                tabmode.addRow(new Object[]{
                    rs.getString("id_petugas"),
                    rs.getString("nama"),
                    rs.getString("jenis_kelamin"),
                    rs.getString("jabatan"),
                    rs.getString("no_hp"),
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mengambil data: " + e.getMessage());
        }
    }
    private void generateIdPetugas() {
        try {
            Connection conn = Mysql.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(id_petugas) FROM data_petugas");

            if (rs.next()) {
                String lastId = rs.getString(1);
                if (lastId == null) {
                    txtId.setText("PTG001");
                } else {
                    int num = Integer.parseInt(lastId.substring(3)) + 1;
                    txtId.setText(String.format("PTG%03d", num));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal generate ID petugas: " + e.getMessage());
        }
    }

    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        mainPanel = new javax.swing.JPanel();
        dataPetugas = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTambah = new javax.swing.JButton();
        jEdit = new javax.swing.JButton();
        jHapus = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        jCari = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPetugas = new javax.swing.JTable();
        tambahPetugas = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSimpan = new javax.swing.JButton();
        jKembali = new javax.swing.JButton();
        comboJabatan = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        txtNohp = new javax.swing.JTextField();
        rLaki = new javax.swing.JRadioButton();
        rPerempuan = new javax.swing.JRadioButton();
        editPetugas = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtEditId = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtEditNama = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jEditSimpan = new javax.swing.JButton();
        jKembali1 = new javax.swing.JButton();
        comboEditJabatan = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        txtEditNohp = new javax.swing.JTextField();
        rEditLaki = new javax.swing.JRadioButton();
        rEditPerempuan = new javax.swing.JRadioButton();

        mainPanel.setLayout(new java.awt.CardLayout());

        dataPetugas.setPreferredSize(new java.awt.Dimension(1076, 658));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1066, 658));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Data Petugas");

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

        tblPetugas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Petugas", "Nama Petugas", "Jenis Kelamin", "Jabatan", "No. Handphone"
            }
        ));
        tblPetugas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPetugasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblPetugas);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(361, 361, 361)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jCari, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dataPetugasLayout = new javax.swing.GroupLayout(dataPetugas);
        dataPetugas.setLayout(dataPetugasLayout);
        dataPetugasLayout.setHorizontalGroup(
            dataPetugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataPetugasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1070, Short.MAX_VALUE))
        );
        dataPetugasLayout.setVerticalGroup(
            dataPetugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainPanel.add(dataPetugas, "card2");

        tambahPetugas.setPreferredSize(new java.awt.Dimension(1076, 658));
        tambahPetugas.setVerifyInputWhenFocusTarget(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1066, 658));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Tambah Data Petugas");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(119, 119, 127));
        jLabel3.setText("ID Petugas");

        txtId.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtId.setForeground(new java.awt.Color(119, 119, 127));

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(119, 119, 127));
        jLabel4.setText("Nama Petugas");

        txtNama.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtNama.setForeground(new java.awt.Color(119, 119, 127));

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(119, 119, 127));
        jLabel9.setText("Jenis Kelamin");

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(119, 119, 127));
        jLabel10.setText("No. Handphone");

        jSimpan.setBackground(new java.awt.Color(255, 140, 0));
        jSimpan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jSimpan.setForeground(new java.awt.Color(255, 255, 255));
        jSimpan.setText("Simpan Data Petugas");
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

        comboJabatan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        comboJabatan.setForeground(new java.awt.Color(62, 68, 74));
        comboJabatan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bidan", "Perawat", "Dokter" }));

        jLabel20.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(119, 119, 127));
        jLabel20.setText("Jabatan");

        txtNohp.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtNohp.setForeground(new java.awt.Color(119, 119, 127));

        buttonGroup1.add(rLaki);
        rLaki.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        rLaki.setForeground(new java.awt.Color(62, 68, 74));
        rLaki.setText("Laki-laki");

        buttonGroup1.add(rPerempuan);
        rPerempuan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        rPerempuan.setForeground(new java.awt.Color(62, 68, 74));
        rPerempuan.setText("Perempuan");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(283, 283, 283)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtId)
                        .addComponent(txtNama)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                        .addComponent(jKembali, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(comboJabatan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNohp))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rLaki, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rPerempuan, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(283, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel3)
                .addGap(5, 5, 5)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel4)
                .addGap(5, 5, 5)
                .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel9)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rLaki)
                    .addComponent(rPerempuan))
                .addGap(10, 10, 10)
                .addComponent(jLabel20)
                .addGap(5, 5, 5)
                .addComponent(comboJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel10)
                .addGap(5, 5, 5)
                .addComponent(txtNohp, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tambahPetugasLayout = new javax.swing.GroupLayout(tambahPetugas);
        tambahPetugas.setLayout(tambahPetugasLayout);
        tambahPetugasLayout.setHorizontalGroup(
            tambahPetugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tambahPetugasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1070, Short.MAX_VALUE))
        );
        tambahPetugasLayout.setVerticalGroup(
            tambahPetugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainPanel.add(tambahPetugas, "card2");

        editPetugas.setPreferredSize(new java.awt.Dimension(1076, 658));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(1066, 658));

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Edit Data Petugas");

        jLabel13.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(119, 119, 127));
        jLabel13.setText("ID Petugas");

        txtEditId.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtEditId.setForeground(new java.awt.Color(119, 119, 127));

        jLabel14.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(119, 119, 127));
        jLabel14.setText("Nama Petugas");

        txtEditNama.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtEditNama.setForeground(new java.awt.Color(119, 119, 127));

        jLabel15.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(119, 119, 127));
        jLabel15.setText("Jumlah Anggota Keluarga");

        jLabel16.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(119, 119, 127));
        jLabel16.setText("No. Handphone");

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

        comboEditJabatan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        comboEditJabatan.setForeground(new java.awt.Color(62, 68, 74));
        comboEditJabatan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bidan", "Perawat", "Dokter" }));

        jLabel22.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(119, 119, 127));
        jLabel22.setText("Jabatan");

        txtEditNohp.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtEditNohp.setForeground(new java.awt.Color(119, 119, 127));

        buttonGroup1.add(rEditLaki);
        rEditLaki.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        rEditLaki.setForeground(new java.awt.Color(62, 68, 74));
        rEditLaki.setText("Laki-laki");

        buttonGroup1.add(rEditPerempuan);
        rEditPerempuan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        rEditPerempuan.setForeground(new java.awt.Color(62, 68, 74));
        rEditPerempuan.setText("Perempuan");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(283, 283, 283)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtEditId)
                        .addComponent(txtEditNama)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jEditSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                        .addComponent(jKembali1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(comboEditJabatan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtEditNohp))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(rEditLaki, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rEditPerempuan, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(283, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel13)
                .addGap(5, 5, 5)
                .addComponent(txtEditId, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel14)
                .addGap(5, 5, 5)
                .addComponent(txtEditNama, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel15)
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rEditLaki)
                    .addComponent(rEditPerempuan))
                .addGap(10, 10, 10)
                .addComponent(jLabel22)
                .addGap(5, 5, 5)
                .addComponent(comboEditJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel16)
                .addGap(5, 5, 5)
                .addComponent(txtEditNohp, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jEditSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jKembali1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout editPetugasLayout = new javax.swing.GroupLayout(editPetugas);
        editPetugas.setLayout(editPetugasLayout);
        editPetugasLayout.setHorizontalGroup(
            editPetugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editPetugasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 1070, Short.MAX_VALUE))
        );
        editPetugasLayout.setVerticalGroup(
            editPetugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainPanel.add(editPetugas, "card2");

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
        cl.show(mainPanel, "tambahPetugas");
    }//GEN-LAST:event_jTambahActionPerformed

    private void jEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEditActionPerformed
        System.out.println("Tombol Edit ditekan");
        int bar = tblPetugas.getSelectedRow();
        System.out.println("Baris terpilih: " + bar);

        if (bar != -1) {
            try {
                String id_petugas       = tabmode.getValueAt(bar, 0).toString();
                String nama             = tabmode.getValueAt(bar, 1).toString();
                String jenis_kelamin    = tabmode.getValueAt(bar, 2).toString();
                String jabatan          = tabmode.getValueAt(bar, 3).toString();
                String no_hp            = tabmode.getValueAt(bar, 4).toString();

                System.out.println("Data diambil: " + id_petugas + ", " + nama);

                txtEditId.setText(id_petugas);
                txtEditNama.setText(nama);
                if (jenis_kelamin.equalsIgnoreCase("Laki-laki")) {
                    rEditLaki.setSelected(true);
                } else if (jenis_kelamin.equalsIgnoreCase("Perempuan")) {
                    rEditPerempuan.setSelected(true);
                }
                comboEditJabatan.setSelectedItem(jabatan);
                txtEditNohp.setText(no_hp);
                
                // Coba paksa pindah panel
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "editPetugas");
                System.out.println("Berpindah ke panel editPetugas");

            } catch (Exception e) {
                System.out.println("Error parsing data: " + e.getMessage());
            }

        } else {
            JOptionPane.showMessageDialog(null, "Pilih data yang ingin diedit terlebih dahulu.");
        }
    }//GEN-LAST:event_jEditActionPerformed

    private void jHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHapusActionPerformed
        int bar = tblPetugas.getSelectedRow();
        if (bar == -1) {
            JOptionPane.showMessageDialog(null, "Pilih data yang ingin dihapus!");
            return;
        }

        String id_petugas = tabmode.getValueAt(bar, 0).toString();
        int konfirmasi = JOptionPane.showConfirmDialog(null, "Hapus data ID Petugas: " + id_petugas + "?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (konfirmasi == JOptionPane.YES_OPTION) {
            try {
                Connection conn = Mysql.getConnection();
                String sql = "DELETE FROM data_petugas WHERE id_petugas=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, id_petugas);
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

    private void tblPetugasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPetugasMouseClicked
        int bar = tblPetugas.getSelectedRow();
            System.out.println("Klik! Baris terpilih: " + bar);

            if (bar != -1) {
                jEdit.setEnabled(true);

                String id_petugas    = tabmode.getValueAt(bar, 0).toString();
                String nama          = tabmode.getValueAt(bar, 1).toString();
                String jenis_kelamin = tabmode.getValueAt(bar, 2).toString();
                String jabatan       = tabmode.getValueAt(bar, 3).toString();
                String no_hp         = tabmode.getValueAt(bar, 4).toString();

                txtEditId.setText(id_petugas);
                txtEditNama.setText(nama);
                if (jenis_kelamin.equalsIgnoreCase("Laki-laki")) {
                    rEditLaki.setSelected(true);
                } else if (jenis_kelamin.equalsIgnoreCase("Perempuan")) {
                    rEditPerempuan.setSelected(true);
                }
                comboEditJabatan.setSelectedItem(jabatan);
                txtEditNohp.setText(no_hp);
            }
    }//GEN-LAST:event_tblPetugasMouseClicked

    private void jSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSimpanActionPerformed
        try {
            String id_petugas    = txtId.getText();
            String nama          = txtNama.getText();
            String jenis_kelamin = "";
            String no_hp         = txtNohp.getText();
            String jabatan       = (String) comboJabatan.getSelectedItem();

            if (rLaki.isSelected()) {
                jenis_kelamin = "Laki-laki";
            } else if (rPerempuan.isSelected()) {
                jenis_kelamin = "Perempuan";
            }
            
            if (id_petugas.equals("") || nama.equals("") || jenis_kelamin.equals("") || no_hp.equals("")) {
                JOptionPane.showMessageDialog(null, "Lengkapi semua kolom!");
                return;
            }
            
            String sql = String.format(
                "INSERT INTO data_petugas (id_petugas, nama, jenis_kelamin, jabatan, no_hp) " +
                "VALUES ('%s', '%s', '%s', '%s', '%s')",
                id_petugas, nama, jenis_kelamin, jabatan, no_hp
            );

            com.Database.Mysql.execute(sql);
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
            kosong();
            datatable();
            txtId.requestFocus();
            tblPetugas.clearSelection();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal simpan data: " + e.getMessage());
        }
    }//GEN-LAST:event_jSimpanActionPerformed

    private void jKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKembaliActionPerformed
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "dataPetugas");
    }//GEN-LAST:event_jKembaliActionPerformed

    private void jEditSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEditSimpanActionPerformed
        try {
            String sql = "UPDATE data_petugas SET nama=?, jenis_kelamin=?, jabatan=?, no_hp=? WHERE id_petugas=?";
            Connection conn = Mysql.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            
            String id_petugas   = txtEditId.getText();
            String nama         = txtEditNama.getText();
            String no_hp        = txtEditNohp.getText();
            String jabatan      = comboEditJabatan.getSelectedItem().toString();
            String jenis_kelamin = "";

            if (rEditLaki.isSelected()) {
                jenis_kelamin = "Laki-laki";
            } else if (rEditPerempuan.isSelected()) {
                jenis_kelamin = "Perempuan";
            }

            pst.setString(1, nama);
            pst.setString(2, jenis_kelamin);
            pst.setString(3, jabatan);
            pst.setString(4, no_hp);
            pst.setString(5, id_petugas);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diperbarui.");

            datatable(); 
            tblPetugas.clearSelection();
            resetEditForm();

            CardLayout cl = (CardLayout) mainPanel.getLayout();
            cl.show(mainPanel, "dataPetugas");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Gagal menyimpan perubahan: " + e.getMessage());
        }
    }//GEN-LAST:event_jEditSimpanActionPerformed

    private void jKembali1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKembali1ActionPerformed
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "dataPetugas");
    }//GEN-LAST:event_jKembali1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> comboEditJabatan;
    private javax.swing.JComboBox<String> comboJabatan;
    private javax.swing.JPanel dataPetugas;
    private javax.swing.JPanel editPetugas;
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
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JRadioButton rEditLaki;
    private javax.swing.JRadioButton rEditPerempuan;
    private javax.swing.JRadioButton rLaki;
    private javax.swing.JRadioButton rPerempuan;
    private javax.swing.JPanel tambahPetugas;
    private javax.swing.JTable tblPetugas;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtEditId;
    private javax.swing.JTextField txtEditNama;
    private javax.swing.JTextField txtEditNohp;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNohp;
    // End of variables declaration//GEN-END:variables
}
