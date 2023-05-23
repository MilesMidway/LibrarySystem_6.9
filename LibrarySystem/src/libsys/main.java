package libsys;

import java.sql.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Random;
import java.awt.Window;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class main extends javax.swing.JFrame {

    // init connection to databases
    Connection con;
    Statement stmt;
    ResultSet rs;
    
    // displaying the database table
    DefaultTableModel LoginModel = new DefaultTableModel();
    
    // universal variables for accounts database 
    String usiEmail, usiSex, usiAddress, usiFullName, usiPass, usicPass, 
           usiUsertype;
    int usiID, usiContact;
    boolean matchAcc = false, matchPass = false, matchType = false;
    int randID, aUserID;
   
    // variables for books databases
    String t;
    public static int currBookID;
    
    // personalization variables
    public static String currFullName, currUserType, currEmail, currPass;
    public static int currUserID;

    // Connects to the reffered database
    public void databaseConnect(String dbName) 
    {
        try {
            String host = "jdbc:derby://localhost:1527/" + dbName;
            String uName = "userdb";
            String uPass = "0000";

            con = DriverManager.getConnection(host, uName, uPass);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM USERDB." + dbName.toUpperCase();
            rs = stmt.executeQuery(sql);
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(main.this, "Failed to connect to the database: " + err.getMessage());
        }
    }

    // Refreshes the referred database contents
    public void refreshRsStmt(String dbName) {
        try {
            closeConnection(); 
            databaseConnect(dbName); 
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM USERDB." + dbName.toUpperCase();
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close(); // Close connection last
            }
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Gets called in every end of a JFrame so everything goes through the main
    // rather than being thrown and samely adjusted from JFrame to JFrame
    public static void sendDisplaySignal(JFrame sig) 
    {
        JFrame[] jframeArr = {
            new MainWindow(), new AdminSignIn(), new LibrarianSignIn(), 
            new ReaderSignIn(), new GuestSignUp(), new AdminBase(), 
            new BookRegistry(), new LibrarianBase(), new BookBorrowMan(),
            new BookEditor(), new BookViewer(), new ReaderBase(),
            new MemberSignUp(), new BookLocationMan(),
        };
        for (JFrame jframe : jframeArr) {
            if (jframe.getClass().equals(sig.getClass())) {
                jframe.setLocationRelativeTo(null);
                jframe.setVisible(true);
            }
        }
    }
    
    //An attempt to make an inverse sendDisplaySignal function
    public static void sendCloseSignal(JFrame sig) 
    {
        for (Window window : Window.getWindows()) {
            if (window instanceof JFrame) {
                JFrame frame = (JFrame) window;
                if (frame.getClass().equals(sig.getClass())) {
                    frame.dispose();
                }
            }
        }
    }    
    
    // When called, it provides a random number for the unique ID of databases
    public int randNumGen(String dbName, String dbId) {
        Random random = new Random();
        int randNum = random.nextInt(9999);
         try {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery("SELECT " + dbId.toUpperCase() + " FROM " + dbName.toUpperCase() + " WHERE " + dbId.toUpperCase() + "="+randNum);
            while (rs.next()) {
                randNum = random.nextInt(9999);
                rs = stmt.executeQuery("SELECT " + dbId.toUpperCase() + " FROM " + dbName.toUpperCase() + " WHERE " + dbId.toUpperCase() + "="+ randNum);
            }
            refreshRsStmt("accounts");
        } 
        catch (SQLException err) 
        {
            System.out.println(err.getMessage());
        }
        return randNum;
    }
    
    // Generic SignIn functionality
    public void signIn(String usertype, JTextField txtLogEmail, JPasswordField txtLogPass) throws Exception 
    {
        usiEmail = txtLogEmail.getText();       
        usiPass = txtLogPass.getText();
        try 
        {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT PASSWORD, USERID FROM ACCOUNTS WHERE EMAIL='" + usiEmail + "'");
            if (rs.next()) 
            {
                usicPass = rs.getString("PASSWORD");
                if (usiPass.equals(usicPass)) 
                {
                    usiID = rs.getInt("USERID");
                    stmt = con.createStatement();
                    rs = stmt.executeQuery("SELECT * FROM ACCOUNTS WHERE USERID=" + usiID);
                    if (rs.next()) 
                    {
                        usiUsertype = rs.getString("USERTYPE");
                        if (usiUsertype.equals(usertype))
                        {
                            matchAcc = true; 
                            matchPass = true;
                            matchType = true;
                            databaseConnect("accounts");
                            getCurrProp();
                        }
                        else
                        {
                            matchAcc = true; 
                            matchPass = true;
                            matchType = false;
                        }
                    }
                }
                else 
                {
                    matchAcc = true;
                }
            }
            refreshRsStmt("accounts");
        } 
        catch (SQLException e) 
        {
            System.out.println(e);
        }
        
        if (matchAcc && matchPass && matchType)
        {
            JOptionPane.showMessageDialog(null, "Successfully Logged in!");
            this.dispose();
            toUsertypeBases(usertype);
        }
        else if (matchAcc && !matchPass)
        {
            txtLogEmail.setText("");
            txtLogPass.setText("");
            JOptionPane.showMessageDialog(null, "Incorrect Password!");
        }
        else
        {
            txtLogEmail.setText("");
            txtLogPass.setText("");
            JOptionPane.showMessageDialog(null, "Account not found!", "", JOptionPane.ERROR_MESSAGE);
        }
    }
        
    // Sign in functionality for ReaderSignIn only
    public void signIn(String usertype1, String usertype2, JTextField txtLogEmail, JPasswordField txtLogPass) throws Exception 
    {
        usiEmail = txtLogEmail.getText();
        usiPass = txtLogPass.getText();
        try 
        {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT PASSWORD, USERID FROM ACCOUNTS WHERE EMAIL='" + usiEmail + "'");
            if (rs.next()) 
            {
                usicPass = rs.getString("PASSWORD");
                if (usiPass.equals(usicPass)) 
                {
                    usiID = rs.getInt("USERID");
                    stmt = con.createStatement();
                    rs = stmt.executeQuery("SELECT * FROM ACCOUNTS WHERE USERID=" + usiID);
                    if (rs.next()) 
                    {
                        usiUsertype = rs.getString("USERTYPE");
                        if (usiUsertype.equals(usertype1) || usiUsertype.equals(usertype2)) 
                        {
                            matchAcc = true; 
                            matchPass = true;
                            matchType = true;
                            databaseConnect("accounts");
                            getCurrProp();
                        } else 
                        {
                            matchAcc = true; 
                            matchPass = true;
                            matchType = false;
                        }
                    }
                } 
                else 
                {
                    matchAcc = true;
                }
            }
            refreshRsStmt("accounts");
        } 
        catch (SQLException e) 
        {
            System.out.println(e);
        }

        if (matchAcc && matchPass && matchType) 
        {
            JOptionPane.showMessageDialog(null, "Successfully Logged in!");
            this.dispose();
            toUsertypeBases(currUserType);
        } 
        else if (matchAcc && !matchPass) 
        {
            txtLogEmail.setText("");
            txtLogPass.setText("");
            JOptionPane.showMessageDialog(null, "Incorrect Password!");
        } 
        else 
        {
            txtLogEmail.setText("");
            txtLogPass.setText("");
            JOptionPane.showMessageDialog(null, "Account not found!", "", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Provides the next destination for different Usertypes onced signed in or up
    public void toUsertypeBases(String usertype)
    {
        ReaderBase reader = new ReaderBase();
        System.out.println(usertype);
        switch (usertype) 
        {
            case "ADMIN":
                sendDisplaySignal(new AdminBase()); 
                break;
            case "LIBRARIAN":
                sendDisplaySignal(new LibrarianBase()); 
                break;
            case "GUEST":
                sendCloseSignal(reader);
                reader.setVisible(true);
                reader.btnShowBorrowed.setVisible(false);
                break;                
            case "MEMBER":
                sendCloseSignal(reader);
                reader.setVisible(true);
                reader.btnMember.setVisible(false);
                break;
        }
    }
    
    // Provides Log out functionality for every usertype
    public void logOut()
    {
        int logoutoption = JOptionPane.YES_NO_OPTION;
        int logoutresult = JOptionPane.showConfirmDialog(null, "Log Out?", "Log Out Confirmation", logoutoption);
        if(logoutresult == 0)
        {
            sendDisplaySignal(new MainWindow());
            this.dispose();
            
        }
    }
    
    public void getCurrProp() throws SQLException
    {
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT FULLNAME, USERTYPE, EMAIL, USERID, PASSWORD FROM ACCOUNTS WHERE EMAIL='" + usiEmail + "'");
        if (rs.next())
        {
            if (rs.getString("USERTYPE").equals("GUEST"))
            {
                currFullName = "GUEST";
            }
            else if (rs.getString("USERTYPE").equals("MEMBER"))
            {
                currFullName = rs.getString("FULLNAME");
            }
            currEmail = rs.getString("EMAIL");
            currPass = rs.getString("PASSWORD");
            currUserType = rs.getString("USERTYPE");
            currUserID = rs.getInt("USERID");
            
        }
        refreshRsStmt("accounts");
    }
     
    // Returns true if the length of string in a textfield is less than the limit.
    public boolean lessthanLength(int limit, JTextField textfield)
    {
        return textfield.getText().length() < limit;
    }
    //Date 1 must be after date 2 to get a positive result.
    public static long dateDiff(Date date1, Date date2){
        long millDiff = date1.getTime() - date2.getTime();
        long daysDiff = millDiff/(1000 * 60 * 60 * 24);
        return daysDiff;
    }
    //To get a positive result, Date 1 must be after Date 2. Rate should be something like 15% = 0.15.
    public static double penaltyCost(Date date1, Date date2, double rate, int baseCost){
        double penaltyCost;
        long days = dateDiff(date1, date2);
        System.out.print(days);
        try{
            penaltyCost = baseCost*Math.pow(1+rate, days-1);
            DecimalFormat df = new DecimalFormat("#######.##");
            penaltyCost = Double.parseDouble(df.format(penaltyCost));
            return penaltyCost;
        } catch(NumberFormatException e){
            System.out.println("An error occurred: " + e.getMessage());
            return 0.0f;
        }
    }    
    public boolean emailTaken(String usiEmail)
    {
        try {
            databaseConnect("accounts");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT EMAIL FROM ACCOUNTS WHERE EMAIL='" + usiEmail + "'");
            if (rs.next())
            {
                refreshRsStmt("accounts");
                return true; 
            }
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshRsStmt("accounts");
        return false;
    }
    //Returns true if date is after now.
    public boolean isOverDue(Date date, Date now){
        return now.after(date);
    }
    
    // The first statement/s to be called
    public static void main(String[] args) {
        sendDisplaySignal(new MainWindow());
    }
    
}