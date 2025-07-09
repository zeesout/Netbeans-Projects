package com.LogReg;

import com.Database.Mysql;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class RegisterPage extends javax.swing.JPanel {
    
    private final MainLogReg mainlogreg;
    public RegisterPage(MainLogReg mainlogreg) {
        initComponents();
        this.mainlogreg = mainlogreg;
        txtNama.setText("Masukan Nama Lengkap");
        txtEmail.setText("Masukan Email");
        
        txtPassword.setText("Masukan Password");
        txtPassword.setEchoChar((char) 0);  
        
        txtKonfirmasiPassword.setText("Konfirmasi Password Anda");
        txtKonfirmasiPassword.setEchoChar((char) 0);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel8 = new javax.swing.JLabel();
        PanelRegister = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Register = new javax.swing.JButton();
        jbutton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtTelp = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        rlaki = new javax.swing.JRadioButton();
        rperempuan = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        txtKonfirmasiPassword = new javax.swing.JPasswordField();

        jLabel8.setText("jLabel8");

        PanelRegister.setBackground(new java.awt.Color(249, 249, 249));
        PanelRegister.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(245, 245, 245)));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(62, 68, 74));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Register");
        jLabel1.setPreferredSize(new java.awt.Dimension(30, 15));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(119, 119, 127));
        jLabel2.setText("Email");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(119, 119, 127));
        jLabel3.setText("Nomor Telepon");

        Register.setBackground(new java.awt.Color(255, 140, 0));
        Register.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        Register.setForeground(new java.awt.Color(255, 255, 255));
        Register.setText("Register");
        Register.setAutoscrolls(true);
        Register.setBorderPainted(false);
        Register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterActionPerformed(evt);
            }
        });

        jbutton2.setBackground(new java.awt.Color(249, 249, 249));
        jbutton2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jbutton2.setForeground(new java.awt.Color(62, 68, 74));
        jbutton2.setText("Already Have an account? Log In");
        jbutton2.setAutoscrolls(true);
        jbutton2.setBorder(null);
        jbutton2.setBorderPainted(false);
        jbutton2.setOpaque(false);
        jbutton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbutton2ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(119, 119, 127));
        jLabel4.setText("Nama Lengkap");

        txtNama.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtNama.setForeground(new java.awt.Color(119, 119, 127));
        txtNama.setText("Masukan Nama Lengkap");
        txtNama.setPreferredSize(new java.awt.Dimension(260, 40));
        txtNama.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNamaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNamaFocusLost(evt);
            }
        });

        txtEmail.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(119, 119, 127));
        txtEmail.setText("Masukan Email");
        txtEmail.setPreferredSize(new java.awt.Dimension(260, 40));
        txtEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEmailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEmailFocusLost(evt);
            }
        });

        txtTelp.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtTelp.setForeground(new java.awt.Color(119, 119, 127));
        txtTelp.setText("Masukan Nomor Telepon");
        txtTelp.setPreferredSize(new java.awt.Dimension(260, 40));
        txtTelp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTelpFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTelpFocusLost(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(119, 119, 127));
        jLabel5.setText("Password");

        txtPassword.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(119, 119, 127));
        txtPassword.setText("Masukan Password Anda");
        txtPassword.setPreferredSize(new java.awt.Dimension(260, 40));
        txtPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPasswordFocusLost(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(119, 119, 127));
        jLabel6.setText("Jenis Kelamin");

        buttonGroup1.add(rlaki);
        rlaki.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        rlaki.setForeground(new java.awt.Color(62, 68, 74));
        rlaki.setText("Laki-laki");
        rlaki.setOpaque(false);

        buttonGroup1.add(rperempuan);
        rperempuan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        rperempuan.setForeground(new java.awt.Color(62, 68, 74));
        rperempuan.setText("Perempuan");
        rperempuan.setOpaque(false);

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(119, 119, 127));
        jLabel7.setText("Konfirmasi Password");

        txtKonfirmasiPassword.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        txtKonfirmasiPassword.setForeground(new java.awt.Color(119, 119, 127));
        txtKonfirmasiPassword.setText("Konfirmasi Password Anda");
        txtKonfirmasiPassword.setPreferredSize(new java.awt.Dimension(260, 40));
        txtKonfirmasiPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtKonfirmasiPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtKonfirmasiPasswordFocusLost(evt);
            }
        });

        javax.swing.GroupLayout PanelRegisterLayout = new javax.swing.GroupLayout(PanelRegister);
        PanelRegister.setLayout(PanelRegisterLayout);
        PanelRegisterLayout.setHorizontalGroup(
            PanelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelRegisterLayout.createSequentialGroup()
                .addContainerGap(94, Short.MAX_VALUE)
                .addGroup(PanelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelRegisterLayout.createSequentialGroup()
                        .addComponent(rlaki)
                        .addGap(10, 10, 10)
                        .addComponent(rperempuan))
                    .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTelp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Register, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbutton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtKonfirmasiPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        PanelRegisterLayout.setVerticalGroup(
            PanelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelRegisterLayout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel4)
                .addGap(5, 5, 5)
                .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel6)
                .addGap(5, 5, 5)
                .addGroup(PanelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rlaki)
                    .addComponent(rperempuan))
                .addGap(5, 5, 5)
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel3)
                .addGap(5, 5, 5)
                .addComponent(txtTelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel5)
                .addGap(5, 5, 5)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel7)
                .addGap(5, 5, 5)
                .addComponent(txtKonfirmasiPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(Register, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jbutton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelRegister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelRegister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void RegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisterActionPerformed
        registerpage();
    }//GEN-LAST:event_RegisterActionPerformed

    private void jbutton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbutton2ActionPerformed
        mainlogreg.setLoginPage();
    }//GEN-LAST:event_jbutton2ActionPerformed

    private void txtNamaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNamaFocusGained
        if (txtNama.getText().equals("Masukan Nama Lengkap")) {
            txtNama.setText("");
        }
    }//GEN-LAST:event_txtNamaFocusGained

    private void txtNamaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNamaFocusLost
        if (txtNama.getText().isEmpty()) {
            txtNama.setText("Masukan Nama Lengkap");
        }
    }//GEN-LAST:event_txtNamaFocusLost

    private void txtEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailFocusGained
        if (txtEmail.getText().equals("Masukan Email")) {
            txtEmail.setText("");
        }
    }//GEN-LAST:event_txtEmailFocusGained

    private void txtEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailFocusLost
        if (txtEmail.getText().isEmpty()) {
            txtEmail.setText("Masukan Email");
        }
    }//GEN-LAST:event_txtEmailFocusLost

    private void txtTelpFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTelpFocusGained
        if (txtTelp.getText().equals("Masukan Nomor Telepon")) {
            txtTelp.setText("");
        }
    }//GEN-LAST:event_txtTelpFocusGained

    private void txtTelpFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTelpFocusLost
        if (txtTelp.getText().isEmpty()) {
            txtTelp.setText("Masukan Nomor Telepon");
        }
    }//GEN-LAST:event_txtTelpFocusLost

    private void txtPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPasswordFocusGained
        String pass = String.valueOf(txtPassword.getPassword());
        if (pass.equals("Masukan Password")) {
            txtPassword.setText("");
            txtPassword.setEchoChar('•'); // tampilkan titik
        }
    }//GEN-LAST:event_txtPasswordFocusGained

    private void txtPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPasswordFocusLost
        String pass = String.valueOf(txtPassword.getPassword());
        if (pass.isEmpty()) {
            txtPassword.setText("Masukan Password");
            txtPassword.setEchoChar((char) 0); // tampilkan teks biasa
        }
    }//GEN-LAST:event_txtPasswordFocusLost

    private void txtKonfirmasiPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtKonfirmasiPasswordFocusGained
        String pass = String.valueOf(txtKonfirmasiPassword.getPassword());
        if (pass.equals("Konfirmasi Password Anda")) {
            txtKonfirmasiPassword.setText("");
            txtKonfirmasiPassword.setEchoChar('•');
        }
    }//GEN-LAST:event_txtKonfirmasiPasswordFocusGained

    private void txtKonfirmasiPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtKonfirmasiPasswordFocusLost
        String pass = String.valueOf(txtKonfirmasiPassword.getPassword());
        if (pass.isEmpty()) {
            txtKonfirmasiPassword.setText("Konfirmasi Password Anda");
            txtKonfirmasiPassword.setEchoChar((char) 0);
        }
    }//GEN-LAST:event_txtKonfirmasiPasswordFocusLost
                
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelRegister;
    private javax.swing.JButton Register;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JButton jbutton2;
    private javax.swing.JRadioButton rlaki;
    private javax.swing.JRadioButton rperempuan;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtKonfirmasiPassword;
    private javax.swing.JTextField txtNama;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtTelp;
    // End of variables declaration//GEN-END:variables

    private void registerpage() {
        String nama = txtNama.getText().trim();
        String jenisKelamin = "";
        if (rlaki.isSelected()) {
            jenisKelamin = "Laki-laki";
        } else if (rperempuan.isSelected()) {
            jenisKelamin = "Perempuan";
        }
        String email = txtEmail.getText().trim();
        String noTelp = txtTelp.getText().trim();
        String password = String.valueOf(txtPassword.getPassword());
        String konfirmasiPassword = String.valueOf(txtKonfirmasiPassword.getPassword());

        if (nama.isEmpty() || nama.equals("Masukan Nama Lengkap")) {
            JOptionPane.showMessageDialog(this, "Nama Anda dibutuhkan", "Eheemm'  Ada Pesan Baru Untuk Kamu!", JOptionPane.INFORMATION_MESSAGE);
        } else if (jenisKelamin.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Jenis Kelamin Anda dibutuhkan", "Eheemm'  Ada Pesan Baru Untuk Kamu!", JOptionPane.INFORMATION_MESSAGE);
        } else if (email.isEmpty() || email.equals("Masukan Email")) {
            JOptionPane.showMessageDialog(this, "Email Anda dibutuhkan", "Eheemm'  Ada Pesan Baru Untuk Kamu!", JOptionPane.INFORMATION_MESSAGE);
        } else if (noTelp.isEmpty() || noTelp.equals("Masukan Nomor Telepon")) {
            JOptionPane.showMessageDialog(this, "Nomor Telepon dibutuhkan", "Eheemm'  Ada Pesan Baru Untuk Kamu!", JOptionPane.INFORMATION_MESSAGE);
        } else if (password.isEmpty() || password.equals("Masukan Password")) {
            JOptionPane.showMessageDialog(this, "Password Anda dibutuhkan", "Eheemm'  Ada Pesan Baru Untuk Kamu!", JOptionPane.INFORMATION_MESSAGE);
        } else if (konfirmasiPassword.isEmpty() || konfirmasiPassword.equals("Masukan Password Lagi")) {
        JOptionPane.showMessageDialog(this, "Konfirmasi Password Anda dibutuhkan", "Eheemm'  Ada Pesan Baru Untuk Kamu!", JOptionPane.INFORMATION_MESSAGE);
        } else if (!password.equals(konfirmasiPassword)) {
            JOptionPane.showMessageDialog(this, "Password Anda Tidak Sama", "Eheemm'  Ada Pesan Baru Untuk Kamu!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                // Simpan ke database
                Mysql.execute(String.format(
                    "INSERT INTO `login` (`nama`, `jenis`, `email`, `telp`, `password`) VALUES('%s','%s','%s','%s','%s')",
                    nama, jenisKelamin, email, noTelp, password
                ));
                JOptionPane.showMessageDialog(this,
                    "\n Selamat, akun Anda sudah aktif!\n Silakan login dengan email dan password yang sudah didaftarkan.\n\n",
                    "Eheemm'  Ada Pesan Baru Untuk Kamu!", JOptionPane.INFORMATION_MESSAGE);
                resetInput();
                mainlogreg.setLoginPage(); // Kembali ke halaman login
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this,
                    "Terjadi kesalahan saat registrasi: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void resetInput() {
        txtNama.setText("");
        txtEmail.setText("");
        txtTelp.setText("");
        txtPassword.setText("");
        txtKonfirmasiPassword.setText("");
        rlaki.setSelected(false);
        rperempuan.setSelected(false);
    }
}
