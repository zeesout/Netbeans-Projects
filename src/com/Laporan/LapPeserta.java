package com.Laporan;

import com.Database.Mysql;
import java.awt.CardLayout;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class LapPeserta extends javax.swing.JPanel {

    DefaultTableModel tabmode;

    public LapPeserta() {
        initComponents();
        mainPanel.setLayout(new CardLayout());
        mainPanel.add(dataLaporan, "dataLaporan");

        switchTo("dataLaporan");
        loadComboJenisLayanan();
        datatable();
    }

    private void switchTo(String panelName) {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, panelName);
    }

    private void loadComboJenisLayanan() {
        comboJenis.removeAllItems();
        comboJenis.addItem("Semua");

        try {
            Connection conn = Mysql.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT DISTINCT jenis_layanan FROM data_layanan ORDER BY jenis_layanan ASC");

            while (rs.next()) {
                comboJenis.addItem(rs.getString("jenis_layanan"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal memuat data layanan: " + e.getMessage());
        }
    }

    private void datatable() {
        Object[] kolom = {"NIK", "Nama", "Tanggal Daftar", "Jenis Layanan Terakhir", "Status"};
        tabmode = new DefaultTableModel(null, kolom);
        tblLaporan.setModel(tabmode);

        tblLaporan.setModel(tabmode);
        tblLaporan.setRowHeight(40);
        tblLaporan.setShowGrid(true);
        tblLaporan.setGridColor(new java.awt.Color (230, 230, 230));
        tblLaporan.setShowHorizontalLines(true);
        tblLaporan.setShowVerticalLines(true);
        
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tblLaporan.getColumnCount(); i++) {
            tblLaporan.getColumnModel().getColumn(i).setCellRenderer(center);
        }

        try {
            Connection conn = Mysql.getConnection();
            String jenis = comboJenis.getSelectedItem().toString();

            String sql =    "SELECT p.nik, p.nama, " +
                            "       COALESCE(d.tanggal_daftar, '-') AS tanggal_daftar, " +
                            "       COALESCE(l.jenis_layanan, '-') AS layanan_terakhir, " +
                            "       p.status_kb " +
                            "FROM data_peserta_kb p " +
                            "LEFT JOIN ( " +
                            "    SELECT nik, MAX(tanggal_daftar) AS tanggal_daftar " +
                            "    FROM pendaftaran_kb GROUP BY nik " +
                            ") d ON p.nik = d.nik " +
                            "LEFT JOIN ( " +
                            "    SELECT nik, jenis_layanan, MAX(tanggal_layanan) AS tanggal_terakhir " +
                            "    FROM layanan_kb GROUP BY nik " +
                            ") l ON p.nik = l.nik " +
                            "WHERE (? = 'Semua' OR l.jenis_layanan = ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, jenis);
            ps.setString(2, jenis);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tabmode.addRow(new Object[] {
                    rs.getString("nik"),
                    rs.getString("nama"),
                    rs.getString("tanggal_daftar"),
                    rs.getString("layanan_terakhir"),
                    rs.getString("status_kb")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mengambil data: " + e.getMessage());
        }
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        dataLaporan = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnCetak = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLaporan = new javax.swing.JTable();
        btnFilter = new javax.swing.JButton();
        comboJenis = new javax.swing.JComboBox<>();

        mainPanel.setPreferredSize(new java.awt.Dimension(1076, 658));
        mainPanel.setLayout(new java.awt.CardLayout());

        dataLaporan.setPreferredSize(new java.awt.Dimension(1066, 725));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1066, 725));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Laporan Peserta KB");

        btnCetak.setBackground(new java.awt.Color(36, 160, 237));
        btnCetak.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        btnCetak.setForeground(new java.awt.Color(255, 255, 255));
        btnCetak.setText("Cetak");
        btnCetak.setBorderPainted(false);
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });

        tblLaporan.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        tblLaporan.setForeground(new java.awt.Color(62, 68, 74));
        tblLaporan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "NIK", "Nama", "Tanggal Daftar", "Jenis Layanan Terakhir", "Status"
            }
        ));
        tblLaporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLaporanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblLaporan);

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

        comboJenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua", "Suntik KB", "IUD", "Implan", "Konsultasi" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnCetak, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 981, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCetak, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout dataLaporanLayout = new javax.swing.GroupLayout(dataLaporan);
        dataLaporan.setLayout(dataLaporanLayout);
        dataLaporanLayout.setHorizontalGroup(
            dataLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataLaporanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dataLaporanLayout.setVerticalGroup(
            dataLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainPanel.add(dataLaporan, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1076, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1076, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 725, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
        try {
            String jenis = comboJenis.getSelectedItem().toString();
            
            Map<String, Object> param = new HashMap<>();
            param.put("layananFilter", jenis);

            InputStream reportStream = getClass().getResourceAsStream("/com/Laporan/laporanpeserta.jasper");
                        
            if (reportStream == null) {
                JOptionPane.showMessageDialog(null, "File laporan tidak ditemukan!");
                return;
            }

            JasperPrint jp = JasperFillManager.fillReport(reportStream, param, Mysql.getConnection());
            JasperViewer viewer = new JasperViewer(jp, false);
            viewer.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH); 
            viewer.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Mencetak Laporan: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnCetakActionPerformed

    private void tblLaporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLaporanMouseClicked
        int baris = tblLaporan.getSelectedRow();
            if (baris != -1) {
                String nik      = tabmode.getValueAt(baris, 0).toString();
                String nama     = tabmode.getValueAt(baris, 1).toString();
                String tglDaftar= tabmode.getValueAt(baris, 2).toString();  // Tanggal Daftar
                String layanan  = tabmode.getValueAt(baris, 3).toString();
                String status   = tabmode.getValueAt(baris, 4).toString();

                JOptionPane.showMessageDialog(null, 
                    "NIK: " + nik + 
                    "\nNama: " + nama + 
                    "\nTanggal Daftar: " + tglDaftar +
                    "\nLayanan Terakhir: " + layanan + 
                    "\nStatus: " + status);
        }
    }//GEN-LAST:event_tblLaporanMouseClicked

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        datatable();
    }//GEN-LAST:event_btnFilterActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnFilter;
    private javax.swing.JComboBox<String> comboJenis;
    private javax.swing.JPanel dataLaporan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTable tblLaporan;
    // End of variables declaration//GEN-END:variables
}
