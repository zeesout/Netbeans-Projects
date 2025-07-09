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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class DataJenisLayanan extends javax.swing.JPanel {

    DefaultTableModel tabmode;
    public DataJenisLayanan() {
        initComponents();
        initCombo();
        mainPanel.setLayout(new CardLayout());
        mainPanel.add(editLayanan, "editLayanan");
        mainPanel.add(dataLayanan, "dataLayanan");
        mainPanel.add(tambahLayanan, "tambahLayanan");
        switchTo("dataLayanan"); 
        kosong();
        aktif();
        datatable();
        txtKodeyan.setEditable(false);
        txtEditKodeyan.setEditable(false);
    }
    
    protected void aktif(){
        txtKodeyan.requestFocus();
    }
    protected void kosong(){
        txtKodeyan.setText("");
        txtLayananyan.setText("");
        comboTipeyan.setSelectedIndex(0);
        txtHargayan.setText("");
        }
    private void resetEditForm() {
        txtEditKodeyan.setEditable(false);
        txtLayananyan.setText(""); // ganti dari comboLayananyan
        comboTipeyan.setSelectedIndex(0);
        txtHargayan.setText("");
    }
    private void resetTambahForm() {
        generateKodeLayanan();
        txtLayananyan.setText(""); // ganti dari comboLayananyan
        comboTipeyan.setSelectedIndex(0);
        txtHargayan.setText("");
        }
    private void switchTo(String panelName) {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, panelName);
    }
    private void datatable() {
        String cari = txtCari.getText();
        Object[] kolom = {"Kode Layanan", "Jenis Layanan", "Tipe Layanan", "Harga Default"};
        tabmode = new DefaultTableModel(null, kolom);
        tblLayanan.setModel(tabmode);
        
        tblLayanan.setModel(tabmode);
        tblLayanan.setRowHeight(40);
        tblLayanan.setShowGrid(true);
        tblLayanan.setGridColor(new java.awt.Color (230, 230, 230));
        tblLayanan.setShowHorizontalLines(true);
        tblLayanan.setShowVerticalLines(true);

        // Rata tengah semua kolom
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tblLayanan.getColumnCount(); i++) {
            tblLayanan.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        try {
            Connection conn = Mysql.getConnection();
            Statement st = conn.createStatement();
            
            String sql;
            if (cari.equals("")) {
                sql = "SELECT * FROM data_layanan";
            } else {
                sql = "SELECT * FROM data_layanan WHERE kode_layanan LIKE '%" + cari + "%' OR jenis_layanan LIKE '%" + cari + "%'";
            }
            
            System.out.println("QUERY: " + sql);
            
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                tabmode.addRow(new Object[]{
                    rs.getString("kode_layanan"),
                    rs.getString("jenis_layanan"),
                    rs.getString("tipe"),
                    rs.getString("harga_default"),
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mengambil data: " + e.getMessage());
        }
    }
    private void initCombo() {
        if ("Gratis".equals(comboTipeyan.getSelectedItem().toString())) {
        txtHargayan.setText("0");
        txtHargayan.setEditable(false);
        }
        comboTipeyan.setModel(new DefaultComboBoxModel<>(new String[] {
            "Gratis", "Berbayar"
        }));
        comboEditTipeyan.setModel(new DefaultComboBoxModel<>(new String[] {
            "Gratis", "Berbayar"
        }));

        setupHargaOtomatis(comboTipeyan, txtHargayan);
        setupHargaOtomatis(comboEditTipeyan, txtEditHargayan);
    }
    private void generateKodeLayanan() {
        try {
            Connection conn = Mysql.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(kode_layanan) FROM data_layanan");

            if (rs.next()) {
                String lastKode = rs.getString(1);
                if (lastKode == null) {
                    txtKodeyan.setText("LYN001");
                } else {
                    int num = Integer.parseInt(lastKode.substring(3)) + 1;
                    txtKodeyan.setText(String.format("LYN%03d", num));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal generate kode layanan: " + e.getMessage());
        }
    }
    
    private void setupHargaOtomatis(JComboBox<String> combo, JTextField txtHarga) {
        combo.addActionListener(e -> {
            if ("Gratis".equals(combo.getSelectedItem().toString())) {
                txtHarga.setText("0");
                txtHarga.setEditable(false);
            } else {
                txtHarga.setText("");
                txtHarga.setEditable(true);
            }
        });
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        dataLayanan = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTambah = new javax.swing.JButton();
        jEdit = new javax.swing.JButton();
        jHapus = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        jCari = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLayanan = new javax.swing.JTable();
        tambahLayanan = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtKodeyan = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        comboTipeyan = new javax.swing.JComboBox<>();
        jSimpan = new javax.swing.JButton();
        jKembali = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtHargayan = new javax.swing.JTextField();
        txtLayananyan = new javax.swing.JTextField();
        editLayanan = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtEditKodeyan = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        comboEditTipeyan = new javax.swing.JComboBox<>();
        jEditSimpan = new javax.swing.JButton();
        jKembali1 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtEditHargayan = new javax.swing.JTextField();
        txtEditLayananyan = new javax.swing.JTextField();

        mainPanel.setLayout(new java.awt.CardLayout());

        dataLayanan.setPreferredSize(new java.awt.Dimension(1076, 658));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1066, 658));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Data Jenis Layanan");

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

        tblLayanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Kode Layanan", "Jenis Layanan", "Tipe Layanan", "Harga Default"
            }
        ));
        jScrollPane1.setViewportView(tblLayanan);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
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
                .addGap(40, 40, 40))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCari, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dataLayananLayout = new javax.swing.GroupLayout(dataLayanan);
        dataLayanan.setLayout(dataLayananLayout);
        dataLayananLayout.setHorizontalGroup(
            dataLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataLayananLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dataLayananLayout.setVerticalGroup(
            dataLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainPanel.add(dataLayanan, "card2");

        tambahLayanan.setPreferredSize(new java.awt.Dimension(1076, 658));
        tambahLayanan.setVerifyInputWhenFocusTarget(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1066, 658));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Tambah Data Layanan");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(119, 119, 127));
        jLabel3.setText("Kode Layanan");

        txtKodeyan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtKodeyan.setForeground(new java.awt.Color(119, 119, 127));
        txtKodeyan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKodeyanActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(119, 119, 127));
        jLabel10.setText("Tipe Layanan");

        comboTipeyan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        comboTipeyan.setForeground(new java.awt.Color(62, 68, 74));
        comboTipeyan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gratis", "Berbayar" }));

        jSimpan.setBackground(new java.awt.Color(255, 140, 0));
        jSimpan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jSimpan.setForeground(new java.awt.Color(255, 255, 255));
        jSimpan.setText("Simpan Data Layanan");
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

        jLabel20.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(119, 119, 127));
        jLabel20.setText("Jenis Layanan");

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(119, 119, 127));
        jLabel8.setText("Harga Default");

        txtHargayan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtHargayan.setForeground(new java.awt.Color(119, 119, 127));

        txtLayananyan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtLayananyan.setForeground(new java.awt.Color(119, 119, 127));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(283, 283, 283)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8)
                    .addComponent(txtKodeyan)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboTipeyan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addComponent(jKembali, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtHargayan)
                    .addComponent(txtLayananyan))
                .addContainerGap(283, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(jLabel3)
                .addGap(5, 5, 5)
                .addComponent(txtKodeyan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel20)
                .addGap(5, 5, 5)
                .addComponent(txtLayananyan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel10)
                .addGap(5, 5, 5)
                .addComponent(comboTipeyan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel8)
                .addGap(5, 5, 5)
                .addComponent(txtHargayan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tambahLayananLayout = new javax.swing.GroupLayout(tambahLayanan);
        tambahLayanan.setLayout(tambahLayananLayout);
        tambahLayananLayout.setHorizontalGroup(
            tambahLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tambahLayananLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tambahLayananLayout.setVerticalGroup(
            tambahLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainPanel.add(tambahLayanan, "card2");

        editLayanan.setPreferredSize(new java.awt.Dimension(1076, 658));
        editLayanan.setVerifyInputWhenFocusTarget(false);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(1066, 658));

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Edit Data Layanan");

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(119, 119, 127));
        jLabel9.setText("Kode Layanan");

        txtEditKodeyan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtEditKodeyan.setForeground(new java.awt.Color(119, 119, 127));

        jLabel13.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(119, 119, 127));
        jLabel13.setText("Tipe Layanan");

        comboEditTipeyan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        comboEditTipeyan.setForeground(new java.awt.Color(62, 68, 74));
        comboEditTipeyan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gratis", "Berbayar" }));

        jEditSimpan.setBackground(new java.awt.Color(255, 140, 0));
        jEditSimpan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jEditSimpan.setForeground(new java.awt.Color(255, 255, 255));
        jEditSimpan.setText("Edit Data Layanan");
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

        jLabel22.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(119, 119, 127));
        jLabel22.setText("Jenis Layanan");

        jLabel14.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(119, 119, 127));
        jLabel14.setText("Harga Default");

        txtEditHargayan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtEditHargayan.setForeground(new java.awt.Color(119, 119, 127));

        txtEditLayananyan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtEditLayananyan.setForeground(new java.awt.Color(119, 119, 127));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(283, 283, 283)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14)
                    .addComponent(txtEditKodeyan)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboEditTipeyan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jEditSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addComponent(jKembali1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtEditHargayan)
                    .addComponent(txtEditLayananyan))
                .addContainerGap(283, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(jLabel9)
                .addGap(5, 5, 5)
                .addComponent(txtEditKodeyan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel22)
                .addGap(5, 5, 5)
                .addComponent(txtEditLayananyan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel13)
                .addGap(5, 5, 5)
                .addComponent(comboEditTipeyan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel14)
                .addGap(5, 5, 5)
                .addComponent(txtEditHargayan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jEditSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jKembali1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout editLayananLayout = new javax.swing.GroupLayout(editLayanan);
        editLayanan.setLayout(editLayananLayout);
        editLayananLayout.setHorizontalGroup(
            editLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editLayananLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        editLayananLayout.setVerticalGroup(
            editLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainPanel.add(editLayanan, "card2");

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
        cl.show(mainPanel, "tambahLayanan");
    }//GEN-LAST:event_jTambahActionPerformed

    private void jEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEditActionPerformed
        System.out.println("Tombol Edit ditekan");
        int bar = tblLayanan.getSelectedRow();
        System.out.println("Baris terpilih: " + bar);

        if (bar != -1) {
            try {
                String kode_layanan   = tabmode.getValueAt(bar, 0).toString();
                String jenis_layanan  = tabmode.getValueAt(bar, 1).toString();
                String tipe           = tabmode.getValueAt(bar, 2).toString();
                String harga_default  = tabmode.getValueAt(bar, 3).toString();

                System.out.println("Data diambil: " + kode_layanan + ", " + jenis_layanan);

                txtEditKodeyan.setText(kode_layanan);
                txtEditLayananyan.setText(jenis_layanan); // Ganti dari combo ke textfield
                comboEditTipeyan.setSelectedItem(tipe);
                txtEditHargayan.setText(harga_default);

                // Otomatis kunci harga jika tipe Gratis
                if ("Gratis".equals(tipe)) {
                    txtEditHargayan.setEditable(false);
                } else {
                    txtEditHargayan.setEditable(true);
                }

                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "editLayanan");
                System.out.println("Berpindah ke panel editLayanan");

            } catch (Exception e) {
                System.out.println("Error parsing data: " + e.getMessage());
            }

        } else {
            JOptionPane.showMessageDialog(null, "Pilih data yang ingin diedit terlebih dahulu.");
        }
    }//GEN-LAST:event_jEditActionPerformed

    private void jHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHapusActionPerformed
        int bar = tblLayanan.getSelectedRow();
        if (bar == -1) {
            JOptionPane.showMessageDialog(null, "Pilih data yang ingin dihapus!");
            return;
        }

        String kode_layanan = tabmode.getValueAt(bar, 0).toString();
        int konfirmasi = JOptionPane.showConfirmDialog(null, "Hapus data Kode Layanan: " + kode_layanan + "?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (konfirmasi == JOptionPane.YES_OPTION) {
            try {
                Connection conn = Mysql.getConnection();
                String sql = "DELETE FROM data_layanan WHERE kode_layanan=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, kode_layanan);
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
            String kode_layanan   = txtKodeyan.getText();
            String jenis_layanan  = txtLayananyan.getText(); // <- ini pakai JTextField
            String tipe           = (String) comboTipeyan.getSelectedItem();
            String harga_default  = txtHargayan.getText();

            // Validasi
            if (kode_layanan.equals("") || jenis_layanan.equals("") || harga_default.equals("")) {
                JOptionPane.showMessageDialog(null, "Lengkapi semua kolom!");
                return;
            }

            String sql = "INSERT INTO data_layanan (kode_layanan, jenis_layanan, tipe, harga_default) VALUES (?, ?, ?, ?)";
            Connection conn = Mysql.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, kode_layanan);
            pst.setString(2, jenis_layanan);
            pst.setString(3, tipe);
            pst.setString(4, harga_default);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
            kosong();
            datatable();
            resetTambahForm(); 
            switchTo("dataLayanan");
            txtKodeyan.requestFocus();
            tblLayanan.clearSelection();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal simpan data: " + e.getMessage());
        }
    }//GEN-LAST:event_jSimpanActionPerformed

    private void jKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKembaliActionPerformed
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "dataLayanan");
    }//GEN-LAST:event_jKembaliActionPerformed

    private void jEditSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEditSimpanActionPerformed
        try {
        String sql = "UPDATE data_layanan SET jenis_layanan=?, tipe=?, harga_default=? WHERE kode_layanan=?";
        Connection conn = Mysql.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, txtEditLayananyan.getText()); // GANTI dari comboEditLayananyan
        pst.setString(2, comboEditTipeyan.getSelectedItem().toString());
        pst.setString(3, txtEditHargayan.getText());
        pst.setString(4, txtEditKodeyan.getText()); // WHERE kode_layanan = ?

        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data berhasil diperbarui.");

        datatable(); 
        tblLayanan.clearSelection();
        resetEditForm();

        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "dataLayanan");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Gagal menyimpan perubahan: " + e.getMessage());
    }
    }//GEN-LAST:event_jEditSimpanActionPerformed

    private void jKembali1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKembali1ActionPerformed
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "dataLayanan");
    }//GEN-LAST:event_jKembali1ActionPerformed

    private void txtKodeyanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodeyanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKodeyanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboEditTipeyan;
    private javax.swing.JComboBox<String> comboTipeyan;
    private javax.swing.JPanel dataLayanan;
    private javax.swing.JPanel editLayanan;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jSimpan;
    private javax.swing.JButton jTambah;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel tambahLayanan;
    private javax.swing.JTable tblLayanan;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtEditHargayan;
    private javax.swing.JTextField txtEditKodeyan;
    private javax.swing.JTextField txtEditLayananyan;
    private javax.swing.JTextField txtHargayan;
    private javax.swing.JTextField txtKodeyan;
    private javax.swing.JTextField txtLayananyan;
    // End of variables declaration//GEN-END:variables
}
