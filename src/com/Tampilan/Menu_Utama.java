package com.Tampilan;

import com.FormMaster.DataJenisLayanan;
import com.FormMaster.DataKeluarga;
import com.FormMaster.DataPemeriksaanPeserta;
import com.FormMaster.DataPeserta;
import com.FormMaster.DataPetugas;

import com.Transaksi.DaftarPeserta;
import com.Transaksi.PembayaranKB;

import com.Laporan.LapPenggunaanLayanan;
import com.Laporan.LapPemeriksaanRutin;
import com.Laporan.LapPeserta;
import com.Laporan.LapRekapitulasi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

public class Menu_Utama extends javax.swing.JFrame {

    public Menu_Utama() {
        initComponents();
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        pn_menu.setLayout(new BoxLayout(pn_menu, BoxLayout.Y_AXIS));
        execute();
        setTime();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelNav = new javax.swing.JPanel();
        jDate = new javax.swing.JLabel();
        jTime = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        PanelSide = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pn_menu = new javax.swing.JPanel();
        PanelTampilan = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        pn_utama = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        PanelNav.setBackground(new java.awt.Color(255, 255, 255));
        PanelNav.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220)));
        PanelNav.setPreferredSize(new java.awt.Dimension(1366, 80));

        jDate.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jTime.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jTime.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/BKKBN-Old.jpg"))); // NOI18N

        javax.swing.GroupLayout PanelNavLayout = new javax.swing.GroupLayout(PanelNav);
        PanelNav.setLayout(PanelNavLayout);
        PanelNavLayout.setHorizontalGroup(
            PanelNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelNavLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 933, Short.MAX_VALUE)
                .addComponent(jTime, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jDate, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        PanelNavLayout.setVerticalGroup(
            PanelNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTime, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
        );

        getContentPane().add(PanelNav, java.awt.BorderLayout.PAGE_START);

        PanelSide.setBackground(new java.awt.Color(255, 153, 204));
        PanelSide.setPreferredSize(new java.awt.Dimension(290, 638));

        jScrollPane1.setBorder(null);

        pn_menu.setBackground(new java.awt.Color(250, 250, 250));
        pn_menu.setLayout(new javax.swing.BoxLayout(pn_menu, javax.swing.BoxLayout.LINE_AXIS));
        jScrollPane1.setViewportView(pn_menu);

        javax.swing.GroupLayout PanelSideLayout = new javax.swing.GroupLayout(PanelSide);
        PanelSide.setLayout(PanelSideLayout);
        PanelSideLayout.setHorizontalGroup(
            PanelSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
        );
        PanelSideLayout.setVerticalGroup(
            PanelSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
        );

        getContentPane().add(PanelSide, java.awt.BorderLayout.LINE_START);

        PanelTampilan.setBackground(new java.awt.Color(255, 102, 102));

        jScrollPane2.setBackground(new java.awt.Color(153, 255, 102));
        jScrollPane2.setBorder(null);

        pn_utama.setLayout(new java.awt.BorderLayout());
        jScrollPane2.setViewportView(pn_utama);

        javax.swing.GroupLayout PanelTampilanLayout = new javax.swing.GroupLayout(PanelTampilan);
        PanelTampilan.setLayout(PanelTampilanLayout);
        PanelTampilanLayout.setHorizontalGroup(
            PanelTampilanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1076, Short.MAX_VALUE)
        );
        PanelTampilanLayout.setVerticalGroup(
            PanelTampilanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
        );

        getContentPane().add(PanelTampilan, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(1380, 746));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        pn_utama.add(new DataPeserta());
        pn_utama.repaint();
        pn_utama.revalidate();
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu_Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu_Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu_Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu_Utama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelNav;
    private javax.swing.JPanel PanelSide;
    private javax.swing.JPanel PanelTampilan;
    private javax.swing.JLabel jDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jTime;
    private javax.swing.JPanel pn_menu;
    private javax.swing.JPanel pn_utama;
    // End of variables declaration//GEN-END:variables

    private void execute() {
        //Icon Side Menu
        ImageIcon iconHome            = new ImageIcon(getClass().getResource("/img/home.png"));
        ImageIcon iconMaster            = new ImageIcon(getClass().getResource("/img/files.png"));
        ImageIcon iconTransaksi         = new ImageIcon(getClass().getResource("/img/transaksi.png"));
        ImageIcon iconLaporan           = new ImageIcon(getClass().getResource("/img/report.png"));
        
        //Sub Icon Menu = Master
        ImageIcon iconPeserta           = new ImageIcon(getClass().getResource("/img/peserta.png"));
        ImageIcon iconKeluarga          = new ImageIcon(getClass().getResource("/img/keluarga.png"));
        ImageIcon iconLayanan           = new ImageIcon(getClass().getResource("/img/layanan.png"));
        ImageIcon iconPetugas           = new ImageIcon(getClass().getResource("/img/petugas.png"));
        ImageIcon iconPemeriksaan           = new ImageIcon(getClass().getResource("/img/audit.png"));
        
        //Sub Icon Menu = Transaksi
        ImageIcon iconDaftar            = new ImageIcon(getClass().getResource("/img/pendaftaranKB.png"));
        ImageIcon iconPembayaran        = new ImageIcon(getClass().getResource("/img/transaksiKB.png"));
        
        //Sub Icon Menu = Laporan
        ImageIcon iconLPRNPeserta       = new ImageIcon(getClass().getResource("/img/laporanKB.png"));
        ImageIcon iconLPRNPakaiAlat     = new ImageIcon(getClass().getResource("/img/pemakaianKB.png"));
        ImageIcon iconLPRNPeriksa       = new ImageIcon(getClass().getResource("/img/pemeriksaanKB.png"));
        ImageIcon iconLPRNRekap         = new ImageIcon(getClass().getResource("/img/rekapKB.png"));
        
            
        //Penampil Isi Sub Menu = Master
        Menu_Item masDataPST  = new Menu_Item(null, true, iconPeserta, "Data Peserta KB", 
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pn_utama.removeAll();
                pn_utama.add(new DataPeserta());
                pn_utama.repaint();
                pn_utama.revalidate();
            } 
        });
        Menu_Item masKeluarga = new Menu_Item(null, true, iconKeluarga, "Data Keluarga", new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent e) {
                pn_utama.removeAll();
                pn_utama.add(new DataKeluarga());
                pn_utama.repaint();
                pn_utama.revalidate();
            }
        });
        Menu_Item masJenis  = new Menu_Item(null, true, iconLayanan, "Data Jenis Layanan KB", 
                new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                pn_utama.removeAll();
                pn_utama.add(new DataJenisLayanan());
                pn_utama.repaint();
                pn_utama.revalidate();
            }
        }); 
        Menu_Item masPetugas = new Menu_Item(null, true, iconPetugas, "Data Petugas Kesehatan", 
                new ActionListener(){
         @Override
            public void actionPerformed(ActionEvent e) {
                pn_utama.removeAll();
                pn_utama.add(new DataPetugas());
                pn_utama.repaint();
                pn_utama.revalidate();
            }
        });
        Menu_Item masPeriksa = new Menu_Item(null, true, iconPemeriksaan, "Data Pemeriksaan Peserta", 
                new ActionListener(){
         @Override
            public void actionPerformed(ActionEvent e) {
                pn_utama.removeAll();
                pn_utama.add(new DataPemeriksaanPeserta());
                pn_utama.repaint();
                pn_utama.revalidate();
            }
        });
        
        
        //Penampil Isi Sub Menu = Transaksi
        Menu_Item traPeserta  = new Menu_Item(null, true, iconDaftar, "Pendaftaran Peserta KB", 
                new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent e) {
                pn_utama.removeAll();
                pn_utama.add(new DaftarPeserta());
                pn_utama.repaint();
                pn_utama.revalidate();
            } 
        }); 
        Menu_Item traLayanan  = new Menu_Item(null, true, iconPembayaran, "Pembayaran Layanan KB", new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent e) {
                pn_utama.removeAll();
                pn_utama.add(new PembayaranKB());
                pn_utama.repaint();
                pn_utama.revalidate();
            }
        }); 
 
        
        //Penampil Isi Sub Menu = Laporan
        Menu_Item lapPesertaKB = new Menu_Item(null, true, iconLPRNPeserta, "Peserta KB", new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent e) {
                pn_utama.removeAll();
                pn_utama.add(new LapPeserta());
                pn_utama.repaint();
                pn_utama.revalidate();
            }  
        });
        Menu_Item lapPemakaian  = new Menu_Item(null, true, iconLPRNPakaiAlat, "Pemakaian Alat KB", 
                new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent e) {
                pn_utama.removeAll();
                pn_utama.add(new LapPenggunaanLayanan());
                pn_utama.repaint();
                pn_utama.revalidate();
            }
        });
        Menu_Item lapPemeriksaan = new Menu_Item(null, true, iconLPRNPeriksa, "Pemeriksaan Rutin", 
                new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent e) {
                pn_utama.removeAll();
                pn_utama.add(new LapPemeriksaanRutin());
                pn_utama.repaint();
                pn_utama.revalidate();
            }
        });
        Menu_Item lapRekap = new Menu_Item(null, true, iconLPRNRekap, "Rekapitulasi", 
                new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent e) {
                pn_utama.removeAll();
                pn_utama.add(new LapRekapitulasi());
                pn_utama.repaint();
                pn_utama.revalidate();
            }
        });
        
        
        //Menu Side Bar
        Menu_Item menuMaster     = new Menu_Item(iconMaster, false, null, "Form Master", null, masDataPST, masKeluarga, masJenis, masPetugas, masPeriksa);
        Menu_Item menuTransaksi  = new Menu_Item(iconTransaksi, false, null, "Transaksi", null, traPeserta, traLayanan);
        Menu_Item menuLaporan    = new Menu_Item(iconLaporan, false, null, "Laporan", null, lapPesertaKB, lapPemakaian, lapPemeriksaan, lapRekap);
        
        //Tampilkan Menu
        addMenu(menuMaster);
        addMenu(menuTransaksi);
        addMenu(menuLaporan);
    }
    
    private void addMenu(Menu_Item... menu) {
        for (int i = 0; i <menu.length; i++) {
            pn_menu.add(menu[i]);
            ArrayList<Menu_Item> subMenu = menu[i].getSubMenu();
            for (Menu_Item m : subMenu) {
                addMenu(m);
            }
        }
        pn_menu.revalidate();
    }
    
    public void setTime(){
        new Thread(new Runnable() {
            @Override
            public void run(){
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Menu_Utama.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Date date = new Date();
                    SimpleDateFormat tf = new SimpleDateFormat("h:mm:ss aa");
                    SimpleDateFormat df = new SimpleDateFormat("EEEE, dd-MM-yyyy", new Locale("id", "ID"));
                    String time = tf.format(date);
                    jTime.setText(time.split(" ")[0]+" "+time.split(" ")[1]);
                    jDate.setText(df.format(date));
                }
            }
        }).start();
    }
}