
package libsys;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class AdminBase extends main {
    DefaultTableModel tblAccounts = new DefaultTableModel();
    int x = 0;
    
    String username, password, usertype, stringuserid, searchUserID;
    int userid, ids;
    
    public AdminBase() {
        initComponents();
        
        mainTable.setDefaultEditor(Object.class, null);
    }
    
    public int id;

    void randNum() {
        id = randNumGen("accounts", "userid");
        txtUserID.setText(String.valueOf(id));
    }
    void Default() {
        txtUsername.setText(null);
        txtPassword.setText(null);
        cbUserType.setSelectedIndex(0);
    }
    
    void editing() {
        txtUsername.setEditable(true);
        txtPassword.setEditable(true);
        cbUserType.setEnabled(true);
        randomNum.setVisible(true);
        
        btnAdd.setVisible(false);
    }
    
    void adding() {
        btnAdd.setEnabled(false);
        txtUsername.setEditable(true);
        txtPassword.setEditable(true);
        cbUserType.setEnabled(true);
        
        btnSave.setVisible(false);
        btnEdit.setVisible(false);
        btnDelete.setVisible(false);
        randomNum.setVisible(true);
        btnAdd.setVisible(true);
        
        txtUserID.setEditable(false);
    }
    
    void notEditing() {
        txtUsername.setEditable(false);
        txtPassword.setEditable(false);
        cbUserType.setEnabled(false);
        btnSave.setEnabled(false);
        
        btnSave.setVisible(true);
        btnEdit.setVisible(true);
        btnDelete.setVisible(true);
        randomNum.setVisible(false);
        btnAdd.setVisible(false);
    }
    
    public void Table() {
        String[] columnNames = {"User ID", "Fullname", "Password", "User Type"};
        tblAccounts.setColumnIdentifiers(columnNames);
        tblAccounts.setRowCount(0);
        
        try {
            databaseConnect("accounts");
            while(rs.next()) {
                tblAccounts.addRow(new Object[] 
                {
                    rs.getInt("USERID"), 
                    rs.getString("FULLNAME"), 
                    rs.getString("PASSWORD"), 
                    rs.getString("USERTYPE")
                });
                x++;
            }
            refreshRsStmt("accounts");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        mainTable = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        txtUserID = new javax.swing.JTextField();
        cbUserType = new javax.swing.JComboBox<>();
        btnSave = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        randomNum = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainTable.setModel(tblAccounts);
        mainTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mainTableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(mainTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 51, 640, 193));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 262, 695, 10));

        jLabel1.setText("Username:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 298, -1, -1));

        jLabel2.setText("Password:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 340, -1, -1));

        jLabel3.setText("User ID:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(138, 386, -1, -1));

        jLabel4.setText("User Type:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 433, -1, -1));

        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsernameKeyTyped(evt);
            }
        });
        getContentPane().add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(214, 294, 285, -1));

        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPasswordKeyTyped(evt);
            }
        });
        getContentPane().add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(214, 336, 285, -1));
        getContentPane().add(txtUserID, new org.netbeans.lib.awtextra.AbsoluteConstraints(214, 382, 285, -1));

        cbUserType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MEMBER", "GUEST", "LIBRARIAN", "ADMIN" }));
        getContentPane().add(cbUserType, new org.netbeans.lib.awtextra.AbsoluteConstraints(214, 428, 285, -1));

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        getContentPane().add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 480, -1, -1));

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(201, 480, -1, -1));

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        getContentPane().add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(271, 480, -1, -1));

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        getContentPane().add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(349, 480, -1, -1));

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        getContentPane().add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(502, 480, -1, -1));

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(418, 480, -1, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel5.setText("User's Accounts:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 7, -1, -1));

        randomNum.setText("Random Number");
        randomNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomNumActionPerformed(evt);
            }
        });
        getContentPane().add(randomNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(539, 378, -1, -1));

        btnLogout.setText("Log out");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 270, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        username = txtUsername.getText();
        password = txtPassword.getText();
        userid = Integer.parseInt(txtUserID.getText());
        usertype = String.valueOf(cbUserType.getSelectedItem());
        
        try {
            databaseConnect("accounts");
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fill in the blanks!");
                // JOPTIONPANE
            } else {
                if(rs.next()) {
                    rs.moveToInsertRow();
                    rs.updateInt("USERID", userid);
                    rs.updateString("FULLNAME", username);
                    rs.updateString("PASSWORD", password);
                    rs.updateString("USERTYPE", usertype);
                    rs.insertRow();
                    refreshRsStmt("accounts");

                    JOptionPane.showMessageDialog(null, "Account has been Added!");
                    btnAdd.setEnabled(false);
                    Default();
                    randNum();
                    Table();
                }
            }
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
        
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        txtUserID.setText(String.valueOf(id));
        
        Default();
        adding();
        randNum();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        searchUserID = JOptionPane.showInputDialog(null, "Search for the User's Account: ", "Finding Account"
                , JOptionPane.QUESTION_MESSAGE);
        
        if (searchUserID != null) {
            try {
                databaseConnect("accounts");
                int newId = Integer.parseInt(searchUserID);
                
                try {
                    ResultSet rs = stmt.executeQuery("SELECT * FROM ACCOUNTS WHERE USERID = " + searchUserID);
                    if (rs.next()) {
                        notEditing();
                        
                        txtUsername.setText(rs.getString("FULLNAME"));
                        txtPassword.setText(rs.getString("PASSWORD"));
                        txtUserID.setText(String.valueOf(rs.getInt("USERID")));
                        cbUserType.setSelectedItem(rs.getString("USERTYPE"));
                    } else {
                        JOptionPane.showMessageDialog(null, "Account not Found.");
                        Default();
                        adding();
                    }
                } catch (SQLException err) {
                        JOptionPane.showMessageDialog(null, err.getMessage());
                }
            } catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter an integer value.");
            }
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void mainTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainTableMousePressed
        // TODO add your handling code here:
        ids = Integer.parseInt(mainTable.getValueAt(mainTable.getSelectedRow(), 0).toString());
        
        try {
            databaseConnect("accounts");
            ResultSet rs = stmt.executeQuery("SELECT * FROM ACCOUNTS WHERE USERID = " + ids);
            if (rs.next()) {
                txtUsername.setText(rs.getString("FULLNAME"));
                txtPassword.setText(rs.getString("PASSWORD"));
                txtUserID.setText(String.valueOf(rs.getInt("USERID")));
                cbUserType.setSelectedItem(rs.getString("USERTYPE"));
               
                notEditing();
            }
            refreshRsStmt("accounts");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }//GEN-LAST:event_mainTableMousePressed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        editing();
    }//GEN-LAST:event_btnEditActionPerformed

    private void randomNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_randomNumActionPerformed
        // TODO add your handling code here:
        randNum();
    }//GEN-LAST:event_randomNumActionPerformed

    private void txtUsernameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyTyped
        // TODO add your handling code here:
        if (txtUsername.getText().length() > 0 && txtPassword.getText().length() > 0) {
            // JOPTIONPANE
            btnAdd.setEnabled(true);
            btnSave.setEnabled(true);
        } else {
            // JOPTIONPANE
            btnAdd.setEnabled(false);
            btnSave.setEnabled(false);
        }
    }//GEN-LAST:event_txtUsernameKeyTyped

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        userid = Integer.parseInt(txtUserID.getText());
        username = txtUsername.getText();
        password = txtPassword.getText();
        usertype = String.valueOf(cbUserType.getSelectedItem());
        
        if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fill in the blanks!");
                // JOPTIONPANE
        } else {
            try {
                databaseConnect("accounts");
                ResultSet rs = stmt.executeQuery("SELECT * FROM ACCOUNTS WHERE USERID = " + userid);
                if (rs.next()) {
                    rs.updateString("FULLNAME", username);
                    rs.updateString("PASSWORD", password);
                    rs.updateRow();
                    rs.updateString("USERTYPE", usertype);
                    refreshRsStmt("accounts");

                    JOptionPane.showMessageDialog(null, "Account has been updated!");
                    adding();
                    Default();
                    Table();
                }
            } catch (SQLException err) {
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        userid = Integer.parseInt(txtUserID.getText());
        
        try {
            databaseConnect("accounts");
            ResultSet rs = stmt.executeQuery("SELECT * FROM ACCOUNTS WHERE USERID = " + userid);
            if (rs.next()) {
                int del = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this account?", "Confirmation"
                        , JOptionPane.YES_NO_OPTION);
                if (del == JOptionPane.YES_OPTION) {
                    boolean hasBorrowedBooks = checkBorrowedBooks(userid);
                    databaseConnect("accounts");
                    if (hasBorrowedBooks) {
                        int confirm = JOptionPane.showConfirmDialog(null, "This account has borrowed books."
                                + "\nAre you sure to delete this account?", "Confirmation",
                                JOptionPane.YES_NO_OPTION);
                        if (confirm == 0) {
                            updateBorrowedBooks(userid);
                            rs.deleteRow();
                            
                            JOptionPane.showMessageDialog(null, "Account has been Deleted.");

                            adding();
                            Default();
                            randNum();
                            Table();
                            refreshRsStmt("accounts");
                        }
                    } else {
                        rs.deleteRow();
                        
                        JOptionPane.showMessageDialog(null, "Account has been Deleted.");

                        adding();
                        Default();
                        randNum();
                        Table();
                        refreshRsStmt("accounts");
                    }
                }
            }
            refreshRsStmt("accounts");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtPasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyTyped
        // TODO add your handling code here:
        if (txtUsername.getText().length() > 0 && txtPassword.getText().length() > 0) {
            // JOPTIONPANE
            btnAdd.setEnabled(true);
            btnSave.setEnabled(true);
        } else {
            // JOPTIONPANE
            btnAdd.setEnabled(false);
            btnSave.setEnabled(false);
        }
    }//GEN-LAST:event_txtPasswordKeyTyped

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        logOut();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Table();
        adding();
        randNum();
    }//GEN-LAST:event_formWindowOpened

    private boolean checkBorrowedBooks(int userid) {
        try {
            databaseConnect("books");
            ResultSet res = stmt.executeQuery("SELECT AVAILABILITY FROM BOOKS WHERE BORROWER = " + userid);
            if (res.next()) {
                String avail = res.getString("AVAILABILITY");
                if (avail.equals("BORROWED") || avail.equals("RETURNING")) {
                    refreshRsStmt("books");
                    return true;
                } else {
                    refreshRsStmt("books");
                    return false;
                }
            }
            refreshRsStmt("books");
            return false;
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
            return false;
        }     
    }
    
    private void updateBorrowedBooks(int userid) {
        try {
            databaseConnect("books");
            ResultSet res = stmt.executeQuery("SELECT AVAILABILITY, BORROWER, DUEDATE FROM BOOKS WHERE BORROWER = " + userid);
            if (res.next()) {
                res.updateString("AVAILABILITY", "AVAILABLE");
                res.updateNull("BORROWER");
                res.updateNull("DUEDATE");
                res.updateRow();
                refreshRsStmt("books");
            }
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cbUserType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable mainTable;
    private javax.swing.JButton randomNum;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUserID;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
