package com.Tampilan;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Menu_Item extends javax.swing.JPanel {

    private static Menu_Item selectedMenu = null; // untuk lacak menu aktif
    private boolean isActive = false;             // status aktif menu ini

    public ArrayList<Menu_Item> getSubMenu() {
        if (subMenu == null) {
        subMenu = new ArrayList<>();
        } return subMenu; }
    
        private boolean showing = false;
        private javax.swing.Timer timer;
        private int index = 0;
        private ArrayList<Menu_Item> subMenu = new ArrayList<>();
        private ActionListener act;
        public Menu_Item(Icon icon, boolean sbm, Icon iconSub, String menuName, 
                ActionListener act, Menu_Item... subMenu) {
            
        initComponents();
        lb_icon.setIcon(icon);
        lb_menuName.setText(menuName);
        lb_iconSub.setIcon(iconSub);
        lb_iconSub.setVisible(sbm);
        lb_menuName.setForeground(Color.GRAY);
        
        if (act != null) {
            this.act = act;
        }

        int menuWidth = 290;
        int menuHeight = 50;
        Dimension fixedSize = new Dimension(menuWidth, menuHeight);
        this.setPreferredSize(fixedSize);
        this.setMaximumSize(fixedSize);
        this.setMinimumSize(fixedSize);
        this.setSize(fixedSize);
        for (int i = 0; i < subMenu.length; i++) {
            this.subMenu.add(subMenu[i]);
            subMenu[i].setVisible(false);
        }
        
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (!isActive) {
                    setBackground(new java.awt.Color(230, 230, 230)); // Hover bg
                    lb_menuName.setForeground(new Color(0, 0, 0));                }
                setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (!isActive) {
                    setBackground(new java.awt.Color(255, 255, 255)); // Normal bg
                    lb_menuName.setForeground(new Color(117, 117, 117)); // Text Abu
                }
                setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                // Nonaktifkan menu sebelumnya
                if (selectedMenu != null && selectedMenu != Menu_Item.this) {
                    selectedMenu.setBackground(new java.awt.Color(255, 255, 255));
                    selectedMenu.lb_menuName.setForeground(new Color(117, 117, 117));
                    selectedMenu.isActive = false;
                }

                // Aktifkan menu ini
                setBackground(new java.awt.Color(200, 200, 200)); // Aktif bg
                lb_menuName.setForeground(new Color(0, 0, 0));           // Aktif text
                isActive = true;
                selectedMenu = Menu_Item.this;

                if (act != null) {
                    act.actionPerformed(null);
                }
            }
        });
    }
        
    private void setActiveMenu() {
        if (selectedMenu != null) {
            selectedMenu.setBackground(new java.awt.Color(255, 255, 255)); // reset yang lama
            selectedMenu.isActive = false;
        }
        setBackground(new java.awt.Color(230, 230, 230)); // aktif
        isActive = true;
        selectedMenu = this;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_icon = new javax.swing.JLabel();
        lb_iconSub = new javax.swing.JLabel();
        lb_menuName = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        lb_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_icon.setPreferredSize(new java.awt.Dimension(30, 30));

        lb_iconSub.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_iconSub.setPreferredSize(new java.awt.Dimension(30, 30));

        lb_menuName.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        lb_menuName.setForeground(new java.awt.Color(62, 68, 74));
        lb_menuName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_menuName.setText("Menu Item...");
        lb_menuName.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lb_icon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lb_iconSub, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lb_menuName, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_menuName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_iconSub, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_icon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        setBackground (new java.awt.Color (255,255,255));
        if (showing) {
            hideMenu();
        } else {
            showMenu();
        }
        if (act != null) {
            act.actionPerformed(null);
        }
    }//GEN-LAST:event_formMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lb_icon;
    private javax.swing.JLabel lb_iconSub;
    private javax.swing.JLabel lb_menuName;
    // End of variables declaration//GEN-END:variables

    private void animateExpand(JPanel panel, int targetHeight) {
        panel.setPreferredSize(new Dimension(panel.getWidth(), 0));
        panel.setVisible(true);

        Timer timer = new Timer(1, null); //seberapa cepat animasi diulang
        timer.addActionListener(new ActionListener() {
            int height = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                height += 1; //seberapa banyak tinggi naik tiap frame
                if (height >= targetHeight) {
                    height = targetHeight;
                    timer.stop();
                }
                panel.setPreferredSize(new Dimension(panel.getWidth(), height));
                panel.revalidate();
            }
        });
        timer.start();
    }

    private void showMenu() {
        new Thread(() -> {
            for (int i = 0; i < getSubMenu().size(); i++) {
                Menu_Item item = getSubMenu().get(i);
                javax.swing.SwingUtilities.invokeLater(() -> {
                    animateExpand(item, 50); // targetHeight 50px (tinggi panel Menu_Item)
                });
                sleep(); // jeda antar item
            }
            showing = true;
        }).start();
    }
    
    private void hideMenu() {
        new Thread(() -> {
            for (int i = getSubMenu().size() - 1; i >= 0; i--) {
                Menu_Item item = getSubMenu().get(i);
                javax.swing.SwingUtilities.invokeLater(() -> {
                    item.setVisible(false);
                    item.hideMenu(); // biar nested sub menu ikut nutup
                });
                sleep();
            }
            showing = false;
        }).start();
    }
    
    private void sleep() {
        try {
            Thread.sleep(60); // Sesuaikan jeda animasi antar menu
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
