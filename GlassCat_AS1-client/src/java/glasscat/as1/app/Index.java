/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.app;

import glasscat.as1.dao.impl.ItemDao;
import glasscat.as1.dao.impl.UserDao;
import glasscat.as1.entity.ItemEntity;
import glasscat.as1.util.StringUtil;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sun Yeqing
 */
public class Index extends javax.swing.JFrame {
    private JFrame loginFrame;
    private JFrame ProfileFrame;
    private static UserDao userDao;
    private static ItemDao itemDao;
    private String currentUserId;
    
    public Index(UserDao _userDao, ItemDao _itemDao, JFrame _loginFrame, String userId) {
        this.loginFrame = _loginFrame;
        this.currentUserId = userId;
        userDao = _userDao;
        itemDao = _itemDao;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Edit you profile!");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jLabel1.setText("Welcome to Girly!");

        jLabel2.setText(" Combination Search");

        jCheckBox1.setText("Title");

        jCheckBox2.setText("Type");

        jCheckBox3.setText("Price");

        jLabel3.setText("to");

        jButton2.setText("Search!");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title", "Type", "Price", "Stock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jCheckBox2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jCheckBox3, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField1)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel3)
                                        .addGap(12, 12, 12)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextField2)))
                            .addComponent(jLabel1))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jCheckBox1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox2)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox3)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // jump to profile edit frame
        this.setVisible(false);
        this.ProfileFrame = new Profile(userDao, this, this.currentUserId);
        this.ProfileFrame.setVisible(true);
        
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // create the criteria to queary the database
        boolean titleSelected = this.jCheckBox1.isSelected();
        boolean typeSelected = this.jCheckBox2.isSelected();
        boolean priceSelected = this.jCheckBox3.isSelected();
        
        String title = this.jTextField1.getText();
        String type = this.jTextField2.getText();
        String low = this.jTextField3.getText();
        String high = this.jTextField4.getText();        
        
        Double lowPrice = null;
        Double highPrice = null;
        if (priceSelected) {
            if (!StringUtil.isBlank(low)) {
                try {
                    lowPrice = Double.parseDouble(low);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Invalid price, please enter a numiric number", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            if (!StringUtil.isBlank(high)) {
                try {
                    highPrice = Double.parseDouble(high);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Invalid price, please enter a numiric number", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }        
            if (lowPrice != null && highPrice != null && lowPrice > highPrice) {
                JOptionPane.showMessageDialog(this, "Invalid price section", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        
        // search database
        List<ItemEntity> items = itemDao.findBy3Attributes(
                StringUtil.isBlank(title) || !titleSelected ? null : title, 
                StringUtil.isBlank(type) || !typeSelected ? null : type,
                lowPrice, highPrice);
        
        // set to datatable
        if (items == null || items.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No match item found!", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        System.out.println(items.size());
        
        DefaultTableModel dtm = new DefaultTableModel();
        
        // fill in the table
        String[][] data = new String[items.size()][4];
        int index = 0;
        for (ItemEntity item : items) {
            String[] s = new String[4];
            s[0] = item.getTitle();
            s[1] = item.getCategory();
            s[2] = new DecimalFormat("0.00").format(item.getPrice());
            s[3] = item.getStock().toString();
            data[index] = s;
            index += 1;
        }
       
//        System.out.println("The table has " + jTable1.getRowCount()  + "rows.");
//        System.out.println(jTable1.getAutoCreateColumnsFromModel());
        // Column Names 
        String[] columnNames = { "Title", "Type", "Price","Stock" }; 
        jTable1 = new JTable(data, columnNames); 
        // fresh jScrollPane
        jScrollPane1.setViewportView(jTable1);
        jScrollPane1.setBorder(new LineBorder(Color.BLACK));
        
     
    }//GEN-LAST:event_jButton2MouseClicked

    private JTable j;
    private JScrollPane sp;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
