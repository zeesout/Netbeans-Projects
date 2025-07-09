package com.Laporan;

import com.Database.Mysql;
import java.awt.CardLayout;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class LapPenggunaanLayanan extends javax.swing.JPanel {

    DefaultTableModel tabmode;
    private String tahunTerakhirDifilter = null;

    public LapPenggunaanLayanan() {
        initComponents();
        mainPanel1.setLayout(new CardLayout());
        mainPanel1.add(dataLapLayanan, "dataLapLayanan");

        switchTo("dataLapLayanan");
        loadComboTahun();
        datatable();
    }

    private void switchTo(String panelName) {
        CardLayout cl = (CardLayout) mainPanel1.getLayout();
        cl.show(mainPanel1, panelName);
    }

    private void loadComboTahun() {
        comboTahun.removeAllItems(); // Pastikan dibersihkan dulu
        comboTahun.addItem("Semua");
        comboTahun.addItem("2021");
        comboTahun.addItem("2022");
        comboTahun.addItem("2023");
        comboTahun.addItem("2024");
        comboTahun.addItem("2025");
    }

    private void datatable() {
        Object[] kolom = {"Jenis Layanan", "Jumlah Penggunaan"};
        tabmode = new DefaultTableModel(null, kolom);
        tblLapLayanan.setModel(tabmode);

        tblLapLayanan.setModel(tabmode);
        tblLapLayanan.setRowHeight(40);
        tblLapLayanan.setShowGrid(true);
        tblLapLayanan.setGridColor(new java.awt.Color (230, 230, 230));
        tblLapLayanan.setShowHorizontalLines(true);
        tblLapLayanan.setShowVerticalLines(true);
        
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tblLapLayanan.getColumnCount(); i++) {
            tblLapLayanan.getColumnModel().getColumn(i).setCellRenderer(center);
        }

        try {
            Connection conn = Mysql.getConnection();
            String tahun = comboTahun.getSelectedItem().toString();

            String sql = "SELECT jenis_layanan, COUNT(*) AS jumlah_penggunaan " +
                         "FROM layanan_kb ";

            if (!"Semua".equals(tahun)) {
                sql += "WHERE YEAR(tanggal_layanan) = ? ";
            }

            sql += "GROUP BY jenis_layanan";

            PreparedStatement ps = conn.prepareStatement(sql);
            if (!"Semua".equals(tahun)) {
                ps.setString(1, tahun);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tabmode.addRow(new Object[] {
                    rs.getString("jenis_layanan"),
                    rs.getInt("jumlah_penggunaan")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mengambil data: " + e.getMessage());
        }
    }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel1 = new javax.swing.JPanel();
        dataLapLayanan = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnCetak1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLapLayanan = new javax.swing.JTable();
        btnFilter = new javax.swing.JButton();
        comboTahun = new javax.swing.JComboBox<>();

        mainPanel1.setPreferredSize(new java.awt.Dimension(1076, 658));
        mainPanel1.setLayout(new java.awt.CardLayout());

        dataLapLayanan.setPreferredSize(new java.awt.Dimension(1066, 725));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(1066, 725));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Laporan Penggunaan Layanan");

        btnCetak1.setBackground(new java.awt.Color(36, 160, 237));
        btnCetak1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        btnCetak1.setForeground(new java.awt.Color(255, 255, 255));
        btnCetak1.setText("Cetak");
        btnCetak1.setBorderPainted(false);
        btnCetak1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetak1ActionPerformed(evt);
            }
        });

        tblLapLayanan.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        tblLapLayanan.setForeground(new java.awt.Color(62, 68, 74));
        tblLapLayanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Jenis Layanan", "Jumlah Penggunaan"
            }
        ));
        tblLapLayanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLapLayananMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblLapLayanan);

        btnFilter.setBackground(new java.awt.Color(190, 190, 190));
        btnFilter.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        btnFilter.setForeground(new java.awt.Color(255, 255, 255));
        btnFilter.setText("Filter");
        btnFilter.setBorderPainted(false);
        btnFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterActionPerformed(evt);
            }
        });

        comboTahun.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua", "Suntik KB", "IUD", "Implan", "Konsultasi" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btnCetak1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 981, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCetak1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout dataLapLayananLayout = new javax.swing.GroupLayout(dataLapLayanan);
        dataLapLayanan.setLayout(dataLapLayananLayout);
        dataLapLayananLayout.setHorizontalGroup(
            dataLapLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataLapLayananLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dataLapLayananLayout.setVerticalGroup(
            dataLapLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainPanel1.add(dataLapLayanan, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1076, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(mainPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1076, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 725, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(mainPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCetak1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetak1ActionPerformed
        try {
            String selectedTahun = comboTahun.getSelectedItem().toString();

            if (selectedTahun.equals("Semua")) {
                JOptionPane.showMessageDialog(null, "Silakan pilih tahun tertentu untuk mencetak laporan.");
                return;
            }

            if (tahunTerakhirDifilter == null || !selectedTahun.equals(tahunTerakhirDifilter)) {
                JOptionPane.showMessageDialog(null, "Silakan klik tombol Filter terlebih dahulu sebelum mencetak.");
                return;
            }

            InputStream report = getClass().getResourceAsStream("/com/Laporan/laporanpenggunaanlayanan.jasper");
            if (report == null) {
                JOptionPane.showMessageDialog(null, "File laporan tidak ditemukan!");
                return;
            }

            Map<String, Object> param = new HashMap<>();
            param.put("tahunFilter", selectedTahun); // biarkan tetap String

            Connection conn = Mysql.getConnection();
            JasperPrint print = JasperFillManager.fillReport(report, param, conn);

            if (print.getPages().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Laporan tidak memiliki data untuk tahun " + selectedTahun);
            } else {
                JasperViewer viewer = new JasperViewer(print, false);
                viewer.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH); // Full screen
                viewer.setVisible(true);
            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mencetak: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnCetak1ActionPerformed

    private void tblLapLayananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLapLayananMouseClicked
        int baris = tblLapLayanan.getSelectedRow();
        if (baris != -1) {
            String jenisLayanan = tabmode.getValueAt(baris, 0).toString();
            String jumlah = tabmode.getValueAt(baris, 1).toString();

            JOptionPane.showMessageDialog(null,
                "Jenis Layanan: " + jenisLayanan +
                "\nJumlah Penggunaan: " + jumlah);
        }
    }//GEN-LAST:event_tblLapLayananMouseClicked

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        tahunTerakhirDifilter = comboTahun.getSelectedItem().toString();
        datatable();
    }//GEN-LAST:event_btnFilterActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnCetak1;
    private javax.swing.JButton btnFilter;
    private javax.swing.JComboBox<String> comboTahun;
    private javax.swing.JPanel dataLapLayanan;
    private javax.swing.JPanel dataLaporan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel mainPanel1;
    private javax.swing.JTable tblLapLayanan;
    // End of variables declaration//GEN-END:variables
}
