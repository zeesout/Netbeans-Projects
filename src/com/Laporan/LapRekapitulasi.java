package com.Laporan;

import com.Database.Mysql;
import java.awt.CardLayout;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class LapRekapitulasi extends javax.swing.JPanel {

    DefaultTableModel tabmode;
    private String tahunTerakhirDifilter = null;
    
    public LapRekapitulasi() {
        initComponents();
        mainPanel1.setLayout(new CardLayout());
        mainPanel1.add(dataLapRekap, "dataLapRekap");

        switchTo("dataLapRekap");
        loadComboTahun();
        datatable();
    }

    private void switchTo(String panelName) {
        CardLayout cl = (CardLayout) mainPanel1.getLayout();
        cl.show(mainPanel1, panelName);
    }
    private void loadComboTahun() {
        comboTahun.removeAllItems();
        comboTahun.addItem("Semua");
        comboTahun.addItem("2021");
        comboTahun.addItem("2022");
        comboTahun.addItem("2023");
        comboTahun.addItem("2024");
        comboTahun.addItem("2025");
    }
    public void datatable() {
        Object[] kolom = {"Total Peserta", "Layanan Populer", "Total Komplikasi", "Total Transaksi Layanan"};
        tabmode = new DefaultTableModel(null, kolom);
        tblLapRekap.setModel(tabmode);

        tblLapRekap.setRowHeight(40);
        tblLapRekap.setShowGrid(true);
        tblLapRekap.setGridColor(new java.awt.Color(230, 230, 230));
        tblLapRekap.setShowHorizontalLines(true);
        tblLapRekap.setShowVerticalLines(true);

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tblLapRekap.getColumnCount(); i++) {
            tblLapRekap.getColumnModel().getColumn(i).setCellRenderer(center);
        }

        try {
            Connection conn = Mysql.getConnection();

            // Total Peserta
            int totalPeserta = 0;
            String sqlPeserta = "SELECT COUNT(DISTINCT l.nik) " +
                                "FROM layanan_kb l " +
                                "JOIN data_peserta_kb p ON l.nik = p.nik " +
                                "WHERE YEAR(l.tanggal_layanan) = ?";
            PreparedStatement pst1 = conn.prepareStatement(sqlPeserta);
            pst1.setString(1, tahunTerakhirDifilter); // pastikan ini bukan null
            ResultSet rs1 = pst1.executeQuery();
            if (rs1.next()) totalPeserta = rs1.getInt(1);

            // Layanan Populer
            String layananPopuler = "-";
            PreparedStatement pst2 = conn.prepareStatement(
                "SELECT jenis_layanan, COUNT(*) AS jumlah FROM layanan_kb GROUP BY jenis_layanan ORDER BY jumlah DESC LIMIT 1"
            );
            ResultSet rs2 = pst2.executeQuery();
            if (rs2.next()) layananPopuler = rs2.getString("jenis_layanan");

            // Total Komplikasi
            int totalKomplikasi = 0;
            PreparedStatement pst3 = conn.prepareStatement(
                "SELECT COUNT(*) FROM pemeriksaan_kb WHERE status_pemeriksaan = 'Komplikasi'"
            );
            ResultSet rs3 = pst3.executeQuery();
            if (rs3.next()) totalKomplikasi = rs3.getInt(1);

            // Total Transaksi Layanan
            int totalTransaksi = 0;
            String sqlTransaksi = "SELECT COUNT(*) FROM layanan_kb";
            if (!"Semua".equals(tahunTerakhirDifilter)) {
                sqlTransaksi += " WHERE YEAR(tanggal_layanan) = ?";
                PreparedStatement pst4 = conn.prepareStatement(sqlTransaksi);
                pst4.setString(1, tahunTerakhirDifilter);
                ResultSet rs4 = pst4.executeQuery();
                if (rs4.next()) totalTransaksi = rs4.getInt(1);
            } else {
                PreparedStatement pst4 = conn.prepareStatement(sqlTransaksi);
                ResultSet rs4 = pst4.executeQuery();
                if (rs4.next()) totalTransaksi = rs4.getInt(1);
            }


            // Masukkan ke tabel
            tabmode.addRow(new Object[]{
                totalPeserta,
                layananPopuler,
                totalKomplikasi,
                totalTransaksi
            });

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mengambil data: " + e.getMessage());
        }
    }

    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel1 = new javax.swing.JPanel();
        dataLapRekap = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnCetak1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLapRekap = new javax.swing.JTable();
        btnFilter = new javax.swing.JButton();
        comboTahun = new javax.swing.JComboBox<>();

        setPreferredSize(new java.awt.Dimension(1076, 658));

        mainPanel1.setPreferredSize(new java.awt.Dimension(1076, 658));
        mainPanel1.setLayout(new java.awt.CardLayout());

        dataLapRekap.setPreferredSize(new java.awt.Dimension(1066, 658));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(1066, 658));

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

        tblLapRekap.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        tblLapRekap.setForeground(new java.awt.Color(62, 68, 74));
        tblLapRekap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Total Peserta", "Layanan Populer", "Total Komplikasi", "Total Transaksi Layanan"
            }
        ));
        tblLapRekap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLapRekapMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblLapRekap);

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
                .addContainerGap(35, Short.MAX_VALUE))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dataLapRekapLayout = new javax.swing.GroupLayout(dataLapRekap);
        dataLapRekap.setLayout(dataLapRekapLayout);
        dataLapRekapLayout.setHorizontalGroup(
            dataLapRekapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataLapRekapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 1056, Short.MAX_VALUE))
        );
        dataLapRekapLayout.setVerticalGroup(
            dataLapRekapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainPanel1.add(dataLapRekap, "card2");

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
                    .addComponent(mainPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE)
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

            InputStream report = getClass().getResourceAsStream("/com/Laporan/laporanrekapitulasi.jasper");
            if (report == null) {
                JOptionPane.showMessageDialog(null, "File laporan tidak ditemukan!");
                return;
            }

            Map<String, Object> param = new HashMap<>();
            param.put("tahunFilter", Integer.parseInt(selectedTahun));

            Connection conn = Mysql.getConnection();
            JasperPrint print = JasperFillManager.fillReport(report, param, conn);

            if (print.getPages().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Laporan kosong untuk tahun " + selectedTahun);
            } else {
                // Tambahkan tampilan full screen
                JasperViewer viewer = new JasperViewer(print, false);
                viewer.setExtendedState(JFrame.MAXIMIZED_BOTH); // fullscreen
                viewer.setVisible(true);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mencetak laporan: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnCetak1ActionPerformed

    private void tblLapRekapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLapRekapMouseClicked
        int baris = tblLapRekap.getSelectedRow();
        if (baris != -1) {
            String totalPeserta = tabmode.getValueAt(baris, 0).toString();
            String layananPopuler = tabmode.getValueAt(baris, 1).toString();
            String totalKomplikasi = tabmode.getValueAt(baris, 2).toString();
            String totalTransaksi = tabmode.getValueAt(baris, 3).toString();

            JOptionPane.showMessageDialog(null,
                "Total Peserta: " + totalPeserta +
                "\nLayanan Populer: " + layananPopuler +
                "\nTotal Komplikasi: " + totalKomplikasi +
                "\nTotal Transaksi Layanan: " + totalTransaksi
            );
        }
    }//GEN-LAST:event_tblLapRekapMouseClicked

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        String selectedTahun = comboTahun.getSelectedItem().toString();
        tahunTerakhirDifilter = selectedTahun; // update filter terakhir
        datatable();
    }//GEN-LAST:event_btnFilterActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCetak1;
    private javax.swing.JButton btnFilter;
    private javax.swing.JComboBox<String> comboTahun;
    private javax.swing.JPanel dataLapRekap;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel1;
    private javax.swing.JTable tblLapRekap;
    // End of variables declaration//GEN-END:variables
}
