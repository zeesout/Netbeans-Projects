package com.LogReg;

import com.Database.Mysql;
import com.Tampilan.Menu_Utama;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JCheckBox;         // Untuk checkbox Remember Me
import java.io.FileWriter;            // Untuk menyimpan ke file
import java.io.BufferedReader;        // Untuk membaca dari file
import java.io.FileReader;            // Untuk membaca file
import java.io.IOException;           // Untuk penanganan error I/O
import javax.swing.JOptionPane;

public class LoginPage extends javax.swing.JPanel {
    
    private final MainLogReg mainlogreg;
    public LoginPage(MainLogReg mainlogreg) {
        initComponents();
        this.mainlogreg = mainlogreg;
        
        txtpassword.setText("Masukan Password Anda");
        txtpassword.setEchoChar((char) 0);// supaya tidak muncul titik-titik
        
        Jremember = new JCheckBox("Remember Me");
        PanelLogin.add(Jremember); // tambahkan ke panel atau sesuai layout kamu

        try (BufferedReader reader = new BufferedReader(new FileReader("rememberme.txt"))) {
            String savedUser = reader.readLine();
            String savedPass = reader.readLine();
            txtemail.setText(savedUser);
            txtpassword.setText(savedPass);
            Jremember.setSelected(true);
        } catch (IOException e) {
            // file tidak ada = normal, abaikan saja
        }    
    }

    void bersih(){
        txtemail.setText("Masukan Email Anda");
        txtpassword.setText("Masukan Password Anda");
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelLogin = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Jforget = new javax.swing.JLabel();
        Login = new javax.swing.JButton();
        jbutton2 = new javax.swing.JButton();
        txtpassword = new javax.swing.JPasswordField();
        txtemail = new javax.swing.JTextField();
        Jremember = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        PanelLogin.setBackground(new java.awt.Color(249, 249, 249));
        PanelLogin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(245, 245, 245)));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(62, 68, 74));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Log In");
        jLabel1.setPreferredSize(new java.awt.Dimension(30, 15));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(119, 119, 127));
        jLabel2.setText("Email");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(119, 119, 127));
        jLabel3.setText("Password");

        Jforget.setFont(new java.awt.Font("Segoe UI Semibold", 0, 11)); // NOI18N
        Jforget.setForeground(new java.awt.Color(119, 119, 127));
        Jforget.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Jforget.setText("Forget Password?");
        Jforget.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JforgetMouseClicked(evt);
            }
        });

        Login.setBackground(new java.awt.Color(36, 158, 101));
        Login.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        Login.setForeground(new java.awt.Color(255, 255, 255));
        Login.setText("Log In");
        Login.setAutoscrolls(true);
        Login.setBorderPainted(false);
        Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginActionPerformed(evt);
            }
        });

        jbutton2.setBackground(new java.awt.Color(62, 68, 74));
        jbutton2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jbutton2.setForeground(new java.awt.Color(255, 255, 255));
        jbutton2.setText("Do not have a account? Register");
        jbutton2.setAutoscrolls(true);
        jbutton2.setBorderPainted(false);
        jbutton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbutton2ActionPerformed(evt);
            }
        });

        txtpassword.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtpassword.setForeground(new java.awt.Color(119, 119, 127));
        txtpassword.setText("Masukan Password Anda");
        txtpassword.setPreferredSize(new java.awt.Dimension(250, 40));
        txtpassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtpasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtpasswordFocusLost(evt);
            }
        });

        txtemail.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtemail.setForeground(new java.awt.Color(119, 119, 127));
        txtemail.setText("Masukan Email Anda");
        txtemail.setPreferredSize(new java.awt.Dimension(260, 40));
        txtemail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtemailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtemailFocusLost(evt);
            }
        });

        Jremember.setFont(new java.awt.Font("Segoe UI Semibold", 0, 11)); // NOI18N
        Jremember.setForeground(new java.awt.Color(119, 119, 127));
        Jremember.setText("Remember me");
        Jremember.setOpaque(false);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/LogKiri.png"))); // NOI18N
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/LogKanan.png"))); // NOI18N
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/LogBawah.png"))); // NOI18N
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/LogAtas.png"))); // NOI18N
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout PanelLoginLayout = new javax.swing.GroupLayout(PanelLogin);
        PanelLogin.setLayout(PanelLoginLayout);
        PanelLoginLayout.setHorizontalGroup(
            PanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelLoginLayout.createSequentialGroup()
                .addContainerGap(94, Short.MAX_VALUE)
                .addGroup(PanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLoginLayout.createSequentialGroup()
                            .addComponent(Jremember)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Jforget))
                        .addComponent(Login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbutton2, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                        .addComponent(txtpassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(94, Short.MAX_VALUE))
            .addGroup(PanelLoginLayout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLoginLayout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PanelLoginLayout.setVerticalGroup(
            PanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLoginLayout.createSequentialGroup()
                .addGroup(PanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel3)
                .addGap(5, 5, 5)
                .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(PanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Jforget)
                    .addComponent(Jremember))
                .addGap(20, 20, 20)
                .addComponent(Login, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jbutton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(PanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbutton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbutton2ActionPerformed
        mainlogreg.setRegisterPage();
    }//GEN-LAST:event_jbutton2ActionPerformed

    private void LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginActionPerformed
        loginpage();
    }//GEN-LAST:event_LoginActionPerformed

    private void JforgetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JforgetMouseClicked
        forgetPassword();
    }//GEN-LAST:event_JforgetMouseClicked

    private void txtpasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtpasswordFocusLost
        String password = String.valueOf(txtpassword.getPassword());
        if (password.isEmpty()) {
            txtpassword.setText("Masukan Password Anda");
            txtpassword.setEchoChar((char) 0); // tampilkan teks biasa
        }
    }//GEN-LAST:event_txtpasswordFocusLost

    private void txtpasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtpasswordFocusGained
        String password = String.valueOf(txtpassword.getPassword());
        if (password.equals("Masukan Password Anda")) {
            txtpassword.setText("");
            txtpassword.setEchoChar('•'); // tampilkan titik-titik saat ngetik
        }
    }//GEN-LAST:event_txtpasswordFocusGained

    private void txtemailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtemailFocusLost
        String MasukanEmailAnda=txtemail.getText();
        if(MasukanEmailAnda.equals("")||MasukanEmailAnda.equals("Masukan Email Anda")){
            txtemail.setText("Masukan Email Anda");
        }
    }//GEN-LAST:event_txtemailFocusLost

    private void txtemailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtemailFocusGained
        String MasukanEmailAnda=txtemail.getText();
        if(MasukanEmailAnda.equals("Masukan Email Anda")){
            txtemail.setText("");
        }
    }//GEN-LAST:event_txtemailFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Jforget;
    private javax.swing.JCheckBox Jremember;
    private javax.swing.JButton Login;
    private javax.swing.JPanel PanelLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JButton jbutton2;
    private javax.swing.JTextField txtemail;
    private javax.swing.JPasswordField txtpassword;
    // End of variables declaration//GEN-END:variables

    private void loginpage() {
        String email = txtemail.getText().trim();
        String password = String.valueOf(txtpassword.getPassword());
        if(email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email is required");
            return;
        }
        if(password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Password is required");
            return;
        }

        try {
            // Query cek data user berdasarkan email dan password
            String query = String.format(
                "SELECT * FROM login WHERE email='%s' AND password='%s'",
                email, password
            );

            ResultSet rs = Mysql.execute(query);
            if(rs != null && rs.next()) {

                // ✅ SIMPAN DATA USER KE SESSION
                com.LogReg.UserProfile.setProfile(
                    rs.getString("nama"),
                    rs.getString("jenis"),
                    rs.getString("email"),
                    rs.getString("telp")
                );

                JOptionPane.showMessageDialog(this,"\n Login Success! Selamat Datang " + rs.getString("nama") 
                        +"      \n\n","Eheemm'  Ada Pesan Baru Untuk Kamu!",JOptionPane.INFORMATION_MESSAGE);

                if (Jremember.isSelected()) {
                    try (FileWriter writer = new FileWriter("rememberme.txt")) {
                        writer.write(email + "\n");
                        writer.write(password + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                // Tampilkan jendela utama
                new Menu_Utama().setVisible(true);
                // Tutup jendela login-register
                mainlogreg.dispose();
            } else {
                // Tidak ditemukan user cocok
                JOptionPane.showMessageDialog(this,"\n Sepertinya Kamu belum punya akun.\n Yuk, Registrasi dulu - hanya butuh 1 menit!           \n\n","Eheemm'  Ada Pesan Baru Untuk Kamu!",JOptionPane.INFORMATION_MESSAGE);
            }
            if(rs != null) rs.close();

        } catch (SQLException ex) {
            String errorMessage = ex.getMessage().toLowerCase();
            if (errorMessage.contains("doesn't exist") && errorMessage.contains("users")) {
                JOptionPane.showMessageDialog(this,"\n Sepertinya Kamu belum punya akun.\n Yuk, Registrasi dulu - hanya butuh 1 menit!           \n\n","Eheemm'  Ada Pesan Baru Untuk Kamu!",JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error saat login: " + ex.getMessage());
            }
        }
    }
    
    private void forgetPassword() {
        String email = JOptionPane.showInputDialog(this, "Masukkan Email yang Terdaftar:\n\n", "Ganti Password", JOptionPane.INFORMATION_MESSAGE);

        if (email == null || email.trim().isEmpty()) {
            return; // user cancel atau tidak isi
        }

        try {
            // Cek apakah email ada di database
            ResultSet rs = Mysql.execute("SELECT * FROM login WHERE email = '" + email + "'");
            if (rs != null && rs.next()) {
                String newPassword = JOptionPane.showInputDialog(this, "Masukkan password baru:", "Ganti Password", JOptionPane.INFORMATION_MESSAGE);
                if (newPassword == null || newPassword.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Password baru tidak boleh kosong.", "Ganti Password", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                // Update password di database
                Mysql.execute("UPDATE login SET password = '" + newPassword + "' WHERE email = '" + email + "'");

                JOptionPane.showMessageDialog(this, "Password berhasil diperbarui,\nSilakan login dengan password baru.", "Ganti Password", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Email tidak ditemukan,\nPastikan email sudah terdaftar.", "Ganti Password", JOptionPane.INFORMATION_MESSAGE);
            }

            if (rs != null) rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal mengatur ulang password: ", "Ganti Password", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
