/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Basic;

import DB.DBConnection;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form AdminWindow
     */
    String user = "", type = "";

    public MainWindow() {
        initComponents();
        CurrentDate();
    }

    /* public MainWindow(String str) {
        initComponents();
        user = str;
        CurrentDate();
        
    } */
    public MainWindow(String str, String typ) {
        initComponents();
        user = str;
        type = typ;  // Admin or member 

        if (type.equals("Member")) {

            jMenu1.setVisible(false);
            jButton1.setVisible(false);
            jButton2.setVisible(false);
            jButton3.setVisible(false);
            jMenuItem4.setVisible(false);
            jButton4.setVisible(false);
            jMenuItem12.setVisible(false);
        }
        
        if (type.equals("Admin")) {
            jMenuItem17.setVisible(false);
        }

        CurrentDate();
    }

    public void CurrentDate() {

        Thread clock = new Thread() {
            public void run() {
                for (;;) {
                    Calendar cal = new GregorianCalendar();
                    int month = cal.get(Calendar.MONTH);
                    int year = cal.get(Calendar.YEAR);
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    jLabel98.setText(day + "-" + (month + 1) + "-" + year);

                    int second = cal.get(Calendar.SECOND);
                    int minute = cal.get(Calendar.MINUTE);
                    int hour = cal.get(Calendar.HOUR);
                    jLabel97.setText(hour + ":" + (minute) + ":" + second);

                    try {
                        sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        clock.start();

    }

    public void employeeList() {
        // Code to Display Employee List
        try {
            Vector<String> header = new Vector<String>();
            header.add("Employee code");
            header.add("Employee Name");
            header.add("Post");
            header.add("Date of Birth");
            header.add("Qualification");
            header.add("Gender");
            header.add("Address");
            header.add("Contact");
            header.add("E-Mail");

            Vector<Vector<String>> data = new Vector<Vector<String>>();

            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("select * from emp_mstr");
            db.rst = db.pstmt.executeQuery();
            while (db.rst.next()) {
                Vector<String> temp = new Vector<String>();
                temp.add(db.rst.getString(1));
                temp.add(db.rst.getString(2));
                temp.add(db.rst.getString(3));
                temp.add(db.rst.getString(4));
                temp.add(db.rst.getString(5));
                temp.add(db.rst.getString(6));
                temp.add(db.rst.getString(7));
                temp.add(db.rst.getString(8));
                temp.add(db.rst.getString(9));
                data.add(temp);
            }
            jTable1.setModel(new DefaultTableModel(data, header));
            EmployeeList.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewRegisteredMed() {
        // Code to veiw registered medicine
        try {
            Vector<String> header = new Vector<String>();
            header.add("Medicine Name");
            header.add("Medicine Company");
            header.add("Medicine Formula");
            header.add("Medicine Supply");
            header.add("Company Contact");
            header.add("Supplier Contact");
            header.add("Medicine Purpose");

            Vector<Vector<String>> data = new Vector<Vector<String>>();

            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("select * from product_mstr");
            db.rst = db.pstmt.executeQuery();
            while (db.rst.next()) {
                Vector<String> temp = new Vector<String>();
                temp.add(db.rst.getString(1));
                temp.add(db.rst.getString(2));
                temp.add(db.rst.getString(3));
                temp.add(db.rst.getString(4));
                temp.add(db.rst.getString(5));
                temp.add(db.rst.getString(6));
                temp.add(db.rst.getString(7));
                data.add(temp);
            }
            jTable2.setModel(new DefaultTableModel(data, header));
            ViewRegisteredMedicine.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewStock() {

        //code to view Stock 
        try {
            Vector<String> header = new Vector<String>();
            header.add("Medicine Name");
            header.add("Cost Price");
            header.add("Selling Price");
            header.add("Manufacturing Date");
            header.add("Expiry Date");
            header.add("Re-Order Level");
            header.add("Medicine Quantity");

            Vector<Vector<String>> data = new Vector<Vector<String>>();

            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("select * from stock_detail");
            db.rst = db.pstmt.executeQuery();
            while (db.rst.next()) {
                Vector<String> temp = new Vector<String>();
                temp.add(db.rst.getString(1));
                temp.add(db.rst.getString(2));
                temp.add(db.rst.getString(3));
                temp.add(db.rst.getString(4));
                temp.add(db.rst.getString(5));
                temp.add(db.rst.getString(6));
                temp.add(db.rst.getString(7));
                data.add(temp);
            }
            jTable3.setModel(new DefaultTableModel(data, header));
            ViewStock.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewReorderList() {
        // Code to veiw Reorder List -------------->
        try {
            Vector<String> header = new Vector<String>();
            header.add("Medicine Name");
            header.add("Medicine Formula");
            header.add("Supplier Name");
            header.add("Supplier Contact");
            header.add("Company");
            header.add("Company Contact");
            header.add("Medicine Quantity");

            Vector<Vector<String>> data = new Vector<Vector<String>>();

            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("SELECT product_mstr.med_name, product_mstr.med_formula, product_mstr.med_sup, product_mstr.sup_cont, product_mstr.med_company, product_mstr.comp_contact, stock_detail.med_qty FROM product_mstr, stock_detail WHERE product_mstr.med_name = stock_detail.med_name AND stock_detail.med_qty <= stock_detail.reorder_level");
            db.rst = db.pstmt.executeQuery();
            while (db.rst.next()) {
                Vector<String> temp = new Vector<String>();
                temp.add(db.rst.getString(1));
                temp.add(db.rst.getString(2));
                temp.add(db.rst.getString(3));
                temp.add(db.rst.getString(4));
                temp.add(db.rst.getString(5));
                temp.add(db.rst.getString(6));
                temp.add(db.rst.getString(7));
                data.add(temp);
            }
            jTable4.setModel(new DefaultTableModel(data, header));
            ViewReorderList.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewAllInvoices() {
        // Code to View All Invoices
        try {
            Vector<String> header = new Vector<String>();
            header.add("Invoice ID");
            header.add("Medicine Name");
            header.add("Company");
            header.add("Medicine Formula");
            header.add("Medicine Quantity");
            header.add("Invoice Date and Time");
            header.add("Customer Name");
            header.add("Customer Contact");
            header.add("Bill Amount");

            Vector<Vector<String>> data = new Vector<Vector<String>>();

            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("SELECT invoice.invoice_id, invoice.med_name, product_mstr.med_company, product_mstr.med_formula, invoice.med_qty, invoice.invc_date_time, invoice.`cust_name`, invoice.`cust_contact`, bill_detail.`bill_amt` \n"
                    + "FROM ((invoice\n"
                    + "INNER JOIN product_mstr ON invoice.med_name = product_mstr.med_name)\n"
                    + "INNER JOIN bill_detail ON invoice.invoice_id = bill_detail.invc_no) ORDER BY invoice_id ASC;");
            db.rst = db.pstmt.executeQuery();
            while (db.rst.next()) {
                Vector<String> temp = new Vector<String>();
                temp.add(db.rst.getString(1));
                temp.add(db.rst.getString(2));
                temp.add(db.rst.getString(3));
                temp.add(db.rst.getString(4));
                temp.add(db.rst.getString(5));
                temp.add(db.rst.getString(6));
                temp.add(db.rst.getString(7));
                temp.add(db.rst.getString(8));
                temp.add(db.rst.getString(9));
                data.add(temp);
            }
            jTable5.setModel(new DefaultTableModel(data, header));
            ViewAllInvoices.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel2 = new javax.swing.JPanel();
        jDesktopPane2 = new javax.swing.JDesktopPane();
        AddEmployee = new javax.swing.JInternalFrame();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        SearchUpdateDeleteEmployee = new javax.swing.JInternalFrame();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jTextField6 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jPasswordField2 = new javax.swing.JPasswordField();
        jToggleButton1 = new javax.swing.JToggleButton();
        jLabel63 = new javax.swing.JLabel();
        EmployeeList = new javax.swing.JInternalFrame();
        jPanel4 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        RegisterMedicine = new javax.swing.JInternalFrame();
        jPanel6 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        SearchUpdateDeleteMedicine = new javax.swing.JInternalFrame();
        jPanel7 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jTextField24 = new javax.swing.JTextField();
        jTextField25 = new javax.swing.JTextField();
        jTextField26 = new javax.swing.JTextField();
        jTextField27 = new javax.swing.JTextField();
        jTextField28 = new javax.swing.JTextField();
        jTextField29 = new javax.swing.JTextField();
        jTextField30 = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        AddStock = new javax.swing.JInternalFrame();
        jPanel8 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jTextField32 = new javax.swing.JTextField();
        jTextField33 = new javax.swing.JTextField();
        jTextField34 = new javax.swing.JTextField();
        jTextField35 = new javax.swing.JTextField();
        jTextField36 = new javax.swing.JTextField();
        jTextField37 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        UpdateStock = new javax.swing.JInternalFrame();
        jPanel9 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jTextField39 = new javax.swing.JTextField();
        jTextField40 = new javax.swing.JTextField();
        jTextField41 = new javax.swing.JTextField();
        jTextField42 = new javax.swing.JTextField();
        jTextField43 = new javax.swing.JTextField();
        jTextField44 = new javax.swing.JTextField();
        jTextField45 = new javax.swing.JTextField();
        jTextField46 = new javax.swing.JTextField();
        jTextField47 = new javax.swing.JTextField();
        jTextField48 = new javax.swing.JTextField();
        jTextField49 = new javax.swing.JTextField();
        jTextField50 = new javax.swing.JTextField();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel99 = new javax.swing.JLabel();
        jTextField31 = new javax.swing.JTextField();
        ViewRegisteredMedicine = new javax.swing.JInternalFrame();
        jPanel10 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        ViewStock = new javax.swing.JInternalFrame();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel58 = new javax.swing.JLabel();
        ViewReorderList = new javax.swing.JInternalFrame();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel59 = new javax.swing.JLabel();
        ViewAllInvoices = new javax.swing.JInternalFrame();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel60 = new javax.swing.JLabel();
        ViewInvoiceByCustomerMobileNumber = new javax.swing.JInternalFrame();
        jPanel14 = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jTextField51 = new javax.swing.JTextField();
        jButton34 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        ViewInvoiceByInvoiceNumber = new javax.swing.JInternalFrame();
        jPanel16 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jTextField52 = new javax.swing.JTextField();
        jButton36 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        ChangePassword = new javax.swing.JInternalFrame();
        jPanel19 = new javax.swing.JPanel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jPasswordField3 = new javax.swing.JPasswordField();
        jPasswordField4 = new javax.swing.JPasswordField();
        jLabel96 = new javax.swing.JLabel();
        jPasswordField5 = new javax.swing.JPasswordField();
        GenerateInvoice = new javax.swing.JInternalFrame();
        jPanel18 = new javax.swing.JPanel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jTextField38 = new javax.swing.JTextField();
        jTextField53 = new javax.swing.JTextField();
        jTextField54 = new javax.swing.JTextField();
        jTextField55 = new javax.swing.JTextField();
        jTextField56 = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jTextField57 = new javax.swing.JTextField();
        jTextField58 = new javax.swing.JTextField();
        jButton33 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        Invoice = new javax.swing.JInternalFrame();
        jPanel21 = new javax.swing.JPanel();
        jLabel110 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jLabel117 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel123 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jPanel22 = new javax.swing.JPanel();
        jButton42 = new javax.swing.JButton();
        jLabel98 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(0, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToolBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jToolBar1.setRollover(true);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/addEMP.png"))); // NOI18N
        jButton1.setToolTipText("Add Employee");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search4.png"))); // NOI18N
        jButton2.setToolTipText("Search/ Update/ Delete Employee Reord");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/users.jpg"))); // NOI18N
        jButton3.setToolTipText("View Employee List");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add.png"))); // NOI18N
        jButton4.setToolTipText("Register Medicine");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton4);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Search.png"))); // NOI18N
        jButton5.setToolTipText("Search/ Update/ Delete Medicine");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton5);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/stock2.png"))); // NOI18N
        jButton6.setToolTipText("Add Stock");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton6);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/view-icon-png-2.png"))); // NOI18N
        jButton7.setToolTipText("Update Stock");
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton7);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/UI Details.png"))); // NOI18N
        jButton8.setToolTipText("Generate Invoice");
        jButton8.setFocusable(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton8);

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/030.png"))); // NOI18N
        jButton9.setToolTipText("View Register Medicine");
        jButton9.setFocusable(false);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton9);

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Info2.png"))); // NOI18N
        jButton10.setToolTipText("View Stock");
        jButton10.setFocusable(false);
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton10);

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_info.png"))); // NOI18N
        jButton11.setToolTipText("View Reorder List ");
        jButton11.setFocusable(false);
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton11);

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/invoice (1).png"))); // NOI18N
        jButton12.setToolTipText("View All Invoices");
        jButton12.setFocusable(false);
        jButton12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton12);

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Person.png"))); // NOI18N
        jButton13.setToolTipText("View Invoices by Customer Mobile Number");
        jButton13.setFocusable(false);
        jButton13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton13.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton13);

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/stock-vector-finger-pressing-button-2343252.jpg"))); // NOI18N
        jButton14.setToolTipText("View Invoices by Invoice Number");
        jButton14.setFocusable(false);
        jButton14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton14.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton14);

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/changepasswordwidget4.png"))); // NOI18N
        jButton15.setToolTipText("Change Password");
        jButton15.setFocusable(false);
        jButton15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton15.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton15);

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/62849_40060_logout_128x128.png"))); // NOI18N
        jButton16.setToolTipText("Log Out");
        jButton16.setFocusable(false);
        jButton16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton16.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton16);

        jPanel1.add(jToolBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 30));

        jDesktopPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDesktopPane2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AddEmployee.setBorder(null);
        AddEmployee.setClosable(true);
        AddEmployee.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        AddEmployee.setTitle("Add Employee");
        AddEmployee.setVisible(false);

        jPanel3.setBackground(new java.awt.Color(224, 200, 224));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel3.setText("Employee Code");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 73, -1, -1));

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel4.setText("Name");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 113, -1, -1));

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel5.setText("Post ");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 153, -1, -1));

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel6.setText("Date Of Birth");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 193, -1, -1));

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel7.setText("Qualification");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 233, -1, -1));

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel8.setText("Gender");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, -1, -1));

        jLabel9.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel9.setText("Address");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 310, -1, -1));

        jLabel10.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel10.setText("Contact");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 353, -1, -1));

        jLabel11.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel11.setText("Email");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 393, -1, -1));

        jLabel12.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel12.setText("Password");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 433, -1, -1));

        jTextField1.setBackground(new java.awt.Color(237, 226, 237));
        jTextField1.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jPanel3.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 506, -1));

        jTextField2.setBackground(new java.awt.Color(237, 226, 237));
        jTextField2.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jPanel3.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 506, -1));

        jTextField3.setBackground(new java.awt.Color(237, 226, 237));
        jTextField3.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jPanel3.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, 506, -1));

        jTextField4.setBackground(new java.awt.Color(237, 226, 237));
        jTextField4.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jPanel3.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 190, 506, -1));

        jTextField5.setBackground(new java.awt.Color(237, 226, 237));
        jTextField5.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jPanel3.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 230, 506, -1));

        jTextField7.setBackground(new java.awt.Color(237, 226, 237));
        jTextField7.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jPanel3.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 307, 506, -1));

        jTextField8.setBackground(new java.awt.Color(237, 226, 237));
        jTextField8.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField8KeyTyped(evt);
            }
        });
        jPanel3.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 350, 506, -1));

        jTextField9.setBackground(new java.awt.Color(237, 226, 237));
        jTextField9.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jTextField9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField9FocusLost(evt);
            }
        });
        jPanel3.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 390, 506, -1));

        jPasswordField1.setBackground(new java.awt.Color(237, 226, 237));
        jPasswordField1.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jPanel3.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 430, 506, -1));

        jButton17.setBackground(new java.awt.Color(0, 0, 0));
        jButton17.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jButton17.setForeground(new java.awt.Color(255, 255, 255));
        jButton17.setText("Submit");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(273, 500, -1, 39));

        jButton18.setBackground(new java.awt.Color(0, 0, 0));
        jButton18.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jButton18.setForeground(new java.awt.Color(255, 255, 255));
        jButton18.setText("Reset");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(531, 500, 77, 39));

        jRadioButton1.setBackground(new java.awt.Color(224, 200, 224));
        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jRadioButton1.setText("Male");
        jPanel3.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(318, 270, 71, -1));

        jRadioButton2.setBackground(new java.awt.Color(224, 200, 224));
        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jRadioButton2.setText("Female");
        jPanel3.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 270, -1, -1));

        jLabel2.setFont(new java.awt.Font("Broadway", 0, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 0, 51));
        jLabel2.setText("Add New Employee");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 11, -1, -1));

        javax.swing.GroupLayout AddEmployeeLayout = new javax.swing.GroupLayout(AddEmployee.getContentPane());
        AddEmployee.getContentPane().setLayout(AddEmployeeLayout);
        AddEmployeeLayout.setHorizontalGroup(
            AddEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        AddEmployeeLayout.setVerticalGroup(
            AddEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jDesktopPane2.add(AddEmployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 870, 630));

        SearchUpdateDeleteEmployee.setBorder(null);
        SearchUpdateDeleteEmployee.setClosable(true);
        SearchUpdateDeleteEmployee.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        SearchUpdateDeleteEmployee.setTitle("Search/ Update/ Delete Employee");
        SearchUpdateDeleteEmployee.setVisible(false);

        jPanel5.setBackground(new java.awt.Color(224, 200, 224));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Gill Sans Ultra Bold", 0, 28)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 0, 51));
        jLabel13.setText("Search/ Update/ Delete Employee");
        jPanel5.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, -1, -1));

        jLabel14.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel14.setText("Employee Code");
        jPanel5.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, -1, -1));

        jLabel15.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel15.setText("Name");
        jPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, -1, -1));

        jLabel16.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel16.setText("Post");
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, -1, -1));

        jLabel17.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel17.setText("Date of Birth");
        jPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, -1, -1));

        jLabel18.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel18.setText("Qualification");
        jPanel5.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 270, -1, -1));

        jLabel19.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel19.setText("Gender");
        jPanel5.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 310, -1, -1));

        jLabel20.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel20.setText("Address");
        jPanel5.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 360, -1, -1));

        jLabel21.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel21.setText("Contact");
        jPanel5.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 410, -1, -1));

        jLabel22.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel22.setText("Email");
        jPanel5.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 460, -1, -1));

        jLabel23.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel23.setText("Account Status :");
        jPanel5.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 550, -1, -1));

        jRadioButton3.setBackground(new java.awt.Color(224, 200, 224));
        jRadioButton3.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jRadioButton3.setText("Male");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        jPanel5.add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 310, -1, -1));

        jRadioButton4.setBackground(new java.awt.Color(224, 200, 224));
        jRadioButton4.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jRadioButton4.setText("Female");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(jRadioButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 310, -1, -1));

        jTextField6.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jPanel5.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 540, -1));

        jTextField10.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jPanel5.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, 540, -1));

        jTextField11.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jPanel5.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, 540, -1));

        jTextField12.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jPanel5.add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, 540, -1));

        jTextField13.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jPanel5.add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, 540, -1));

        jTextField14.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jPanel5.add(jTextField14, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 350, 540, -1));

        jTextField15.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jPanel5.add(jTextField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 400, 540, -1));

        jTextField16.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jTextField16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField16ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField16, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 450, 540, -1));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel5.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(828, 70, -1, 460));

        jButton19.setBackground(new java.awt.Color(0, 0, 0));
        jButton19.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jButton19.setForeground(new java.awt.Color(255, 255, 255));
        jButton19.setText("Search");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 120, 90, 40));

        jButton20.setBackground(new java.awt.Color(0, 0, 0));
        jButton20.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jButton20.setForeground(new java.awt.Color(255, 255, 255));
        jButton20.setText("Update");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 240, 90, 40));

        jButton21.setBackground(new java.awt.Color(0, 0, 0));
        jButton21.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jButton21.setForeground(new java.awt.Color(255, 255, 255));
        jButton21.setText("Delete");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton21, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 350, 90, 40));

        jButton22.setBackground(new java.awt.Color(0, 0, 0));
        jButton22.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jButton22.setForeground(new java.awt.Color(255, 255, 255));
        jButton22.setText("Reset");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton22, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 460, 90, 40));

        jPasswordField2.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jPasswordField2.setToolTipText("");
        jPanel5.add(jPasswordField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 500, 540, -1));

        jToggleButton1.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jToggleButton1.setText("Active");
        jToggleButton1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jToggleButton1ItemStateChanged(evt);
            }
        });
        jPanel5.add(jToggleButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 540, 140, 30));

        jLabel63.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel63.setText("Password");
        jPanel5.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 510, -1, -1));

        javax.swing.GroupLayout SearchUpdateDeleteEmployeeLayout = new javax.swing.GroupLayout(SearchUpdateDeleteEmployee.getContentPane());
        SearchUpdateDeleteEmployee.getContentPane().setLayout(SearchUpdateDeleteEmployeeLayout);
        SearchUpdateDeleteEmployeeLayout.setHorizontalGroup(
            SearchUpdateDeleteEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        SearchUpdateDeleteEmployeeLayout.setVerticalGroup(
            SearchUpdateDeleteEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
        );

        jDesktopPane2.add(SearchUpdateDeleteEmployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 990, 620));

        EmployeeList.setBorder(null);
        EmployeeList.setClosable(true);
        EmployeeList.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        EmployeeList.setTitle("View Employee List");
        EmployeeList.setVisible(false);

        jPanel4.setBackground(new java.awt.Color(255, 19, 66));

        jLabel24.setFont(new java.awt.Font("Broadway", 0, 24)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("View Employee List");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(378, 378, 378)
                .addComponent(jLabel24)
                .addContainerGap(473, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel24)
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(141, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout EmployeeListLayout = new javax.swing.GroupLayout(EmployeeList.getContentPane());
        EmployeeList.getContentPane().setLayout(EmployeeListLayout);
        EmployeeListLayout.setHorizontalGroup(
            EmployeeListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        EmployeeListLayout.setVerticalGroup(
            EmployeeListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EmployeeListLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jDesktopPane2.add(EmployeeList, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 1100, 520));

        RegisterMedicine.setBorder(null);
        RegisterMedicine.setClosable(true);
        RegisterMedicine.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        RegisterMedicine.setTitle("Register Medicine");
        RegisterMedicine.setVisible(false);

        jPanel6.setBackground(new java.awt.Color(224, 200, 224));

        jLabel25.setFont(new java.awt.Font("Broadway", 1, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 0, 51));
        jLabel25.setText("Register Medicine");

        jLabel26.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel26.setText("Medicine Name");

        jLabel27.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel27.setText("Medicine Company");

        jLabel28.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel28.setText("Medicine Formula");

        jLabel29.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel29.setText(" Supplier Name");

        jLabel30.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel30.setText("Company Contact");

        jLabel31.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel31.setText("Supplier Contact");

        jLabel32.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel32.setText("Medicine Purpose");

        jTextField17.setBackground(new java.awt.Color(224, 200, 224));
        jTextField17.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jTextField17.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jTextField18.setBackground(new java.awt.Color(224, 200, 224));
        jTextField18.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jTextField18.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jTextField19.setBackground(new java.awt.Color(224, 200, 224));
        jTextField19.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jTextField19.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jTextField20.setBackground(new java.awt.Color(224, 200, 224));
        jTextField20.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jTextField20.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jTextField21.setBackground(new java.awt.Color(224, 200, 224));
        jTextField21.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jTextField21.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jTextField22.setBackground(new java.awt.Color(224, 200, 224));
        jTextField22.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jTextField22.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jTextField23.setBackground(new java.awt.Color(224, 200, 224));
        jTextField23.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jTextField23.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButton23.setBackground(new java.awt.Color(51, 0, 51));
        jButton23.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jButton23.setForeground(new java.awt.Color(255, 255, 255));
        jButton23.setText("Submit");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton24.setBackground(new java.awt.Color(51, 0, 51));
        jButton24.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jButton24.setForeground(new java.awt.Color(255, 255, 255));
        jButton24.setText("Reset");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32))
                .addGap(67, 67, 67)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel25)
                .addGap(326, 326, 326))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextField17, jTextField18, jTextField19, jTextField20, jTextField21, jTextField22, jTextField23});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel25)
                        .addGap(32, 32, 32)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(jButton23)
                        .addGap(153, 153, 153)
                        .addComponent(jButton24)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout RegisterMedicineLayout = new javax.swing.GroupLayout(RegisterMedicine.getContentPane());
        RegisterMedicine.getContentPane().setLayout(RegisterMedicineLayout);
        RegisterMedicineLayout.setHorizontalGroup(
            RegisterMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        RegisterMedicineLayout.setVerticalGroup(
            RegisterMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDesktopPane2.add(RegisterMedicine, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 930, 590));

        SearchUpdateDeleteMedicine.setBackground(new java.awt.Color(0, 0, 0));
        SearchUpdateDeleteMedicine.setBorder(null);
        SearchUpdateDeleteMedicine.setClosable(true);
        SearchUpdateDeleteMedicine.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        SearchUpdateDeleteMedicine.setForeground(new java.awt.Color(255, 255, 255));
        SearchUpdateDeleteMedicine.setTitle("Search/ Update/ Delete Medicine");
        SearchUpdateDeleteMedicine.setVisible(false);

        jPanel7.setBackground(new java.awt.Color(224, 200, 224));

        jLabel33.setFont(new java.awt.Font("Broadway", 0, 24)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(51, 0, 51));
        jLabel33.setText("Search/ Update/ Delete Medicine");

        jLabel34.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel34.setText("Medicine Name");

        jLabel35.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel35.setText("Medicine Company");

        jLabel36.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel36.setText("Medicine Formula");

        jLabel37.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel37.setText("Medicine Supply");

        jLabel38.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel38.setText("Company Contact");

        jLabel39.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel39.setText("Supplier Contact");

        jLabel40.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel40.setText("Medicine Purpose");

        jTextField24.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N

        jTextField25.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N

        jTextField26.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N

        jTextField27.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N

        jTextField28.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N

        jTextField29.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N

        jTextField30.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButton25.setBackground(new java.awt.Color(51, 0, 51));
        jButton25.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jButton25.setForeground(new java.awt.Color(255, 255, 255));
        jButton25.setText("Search");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jButton26.setBackground(new java.awt.Color(51, 0, 51));
        jButton26.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jButton26.setForeground(new java.awt.Color(255, 255, 255));
        jButton26.setText("Update");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jButton27.setBackground(new java.awt.Color(51, 0, 51));
        jButton27.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jButton27.setForeground(new java.awt.Color(255, 255, 255));
        jButton27.setText("Delete");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jButton28.setBackground(new java.awt.Color(51, 0, 51));
        jButton28.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jButton28.setForeground(new java.awt.Color(255, 255, 255));
        jButton28.setText("Reset");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34)
                            .addComponent(jLabel35)
                            .addComponent(jLabel36)
                            .addComponent(jLabel37)
                            .addComponent(jLabel38)
                            .addComponent(jLabel39)
                            .addComponent(jLabel40))
                        .addGap(67, 67, 67)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton26, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(jButton27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(272, 272, 272)
                        .addComponent(jLabel33)))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextField24, jTextField25, jTextField26, jTextField27, jTextField28, jTextField29, jTextField30});

        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel33)
                        .addGap(34, 34, 34)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jSeparator4)))
                .addGap(46, 46, 46))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(jButton25)
                .addGap(71, 71, 71)
                .addComponent(jButton26)
                .addGap(69, 69, 69)
                .addComponent(jButton27)
                .addGap(68, 68, 68)
                .addComponent(jButton28)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout SearchUpdateDeleteMedicineLayout = new javax.swing.GroupLayout(SearchUpdateDeleteMedicine.getContentPane());
        SearchUpdateDeleteMedicine.getContentPane().setLayout(SearchUpdateDeleteMedicineLayout);
        SearchUpdateDeleteMedicineLayout.setHorizontalGroup(
            SearchUpdateDeleteMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        SearchUpdateDeleteMedicineLayout.setVerticalGroup(
            SearchUpdateDeleteMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDesktopPane2.add(SearchUpdateDeleteMedicine, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 980, 590));

        AddStock.setBorder(null);
        AddStock.setClosable(true);
        AddStock.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        AddStock.setTitle("Add Stock");
        AddStock.setVisible(false);

        jPanel8.setBackground(new java.awt.Color(224, 200, 224));

        jLabel41.setFont(new java.awt.Font("Broadway", 0, 27)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(51, 0, 51));
        jLabel41.setText("Add Stock");

        jLabel42.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel42.setText("Medicine Name");

        jLabel43.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel43.setText("Cost Price");

        jLabel44.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel44.setText("Selling Price");

        jLabel45.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel45.setText("Manufactured Date");

        jLabel46.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel46.setText("Expiry date");

        jLabel47.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel47.setText("Re-Order Level");

        jLabel48.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel48.setText("Medicine Quantity");

        jButton29.setBackground(new java.awt.Color(51, 0, 51));
        jButton29.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jButton29.setForeground(new java.awt.Color(255, 255, 255));
        jButton29.setText("Submit");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jButton30.setBackground(new java.awt.Color(51, 0, 51));
        jButton30.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jButton30.setForeground(new java.awt.Color(255, 255, 255));
        jButton30.setText("Reset");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        jTextField32.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jTextField32.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));

        jTextField33.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jTextField33.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));

        jTextField34.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jTextField34.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));

        jTextField35.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jTextField35.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));

        jTextField36.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jTextField36.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));

        jTextField37.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jTextField37.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));

        jComboBox1.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42)
                            .addComponent(jLabel43)
                            .addComponent(jLabel46)
                            .addComponent(jLabel48)
                            .addComponent(jLabel44)
                            .addComponent(jLabel47))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField32)
                            .addComponent(jTextField33)
                            .addComponent(jTextField35)
                            .addComponent(jTextField36)
                            .addComponent(jTextField37)
                            .addComponent(jLabel41)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel45)
                        .addGap(42, 42, 42)
                        .addComponent(jTextField34)))
                .addContainerGap(89, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 169, Short.MAX_VALUE)
                .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(144, 144, 144))
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton29, jButton30});

        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel41)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel43)
                            .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel44)
                            .addComponent(jTextField33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel45)
                            .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel46)
                            .addComponent(jTextField35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel47)
                            .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel48)
                            .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(122, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton29)
                            .addComponent(jButton30))
                        .addGap(39, 39, 39))))
        );

        javax.swing.GroupLayout AddStockLayout = new javax.swing.GroupLayout(AddStock.getContentPane());
        AddStock.getContentPane().setLayout(AddStockLayout);
        AddStockLayout.setHorizontalGroup(
            AddStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        AddStockLayout.setVerticalGroup(
            AddStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDesktopPane2.add(AddStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, 640, 600));

        UpdateStock.setBorder(null);
        UpdateStock.setClosable(true);
        UpdateStock.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        UpdateStock.setTitle("Update Stock");
        UpdateStock.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/img/stock.png"))); // NOI18N
        UpdateStock.setVisible(false);

        jPanel9.setBackground(new java.awt.Color(224, 200, 224));
        jPanel9.setForeground(new java.awt.Color(102, 102, 102));

        jLabel49.setFont(new java.awt.Font("Broadway", 0, 27)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(51, 0, 51));
        jLabel49.setText("Update Stock");

        jLabel50.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel50.setText("Medicine Name");

        jLabel51.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel51.setText("Cost Price");

        jLabel52.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel52.setText("Selling Price");

        jLabel53.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel53.setText("Manufactured Date");

        jLabel54.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel54.setText("Expiary date");

        jLabel55.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel55.setText("Re-Order Level");

        jLabel56.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel56.setText("Available Quantity");

        jTextField39.setBackground(new java.awt.Color(204, 255, 102));
        jTextField39.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jTextField39.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        jTextField40.setBackground(new java.awt.Color(204, 255, 102));
        jTextField40.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jTextField40.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        jTextField41.setBackground(new java.awt.Color(204, 255, 102));
        jTextField41.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jTextField41.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        jTextField42.setBackground(new java.awt.Color(204, 255, 102));
        jTextField42.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jTextField42.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        jTextField43.setBackground(new java.awt.Color(204, 255, 102));
        jTextField43.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jTextField43.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        jTextField44.setBackground(new java.awt.Color(204, 255, 102));
        jTextField44.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jTextField44.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        jTextField45.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N

        jTextField46.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N

        jTextField47.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N

        jTextField48.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N

        jTextField49.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N

        jTextField50.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N

        jButton31.setBackground(new java.awt.Color(51, 0, 51));
        jButton31.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jButton31.setForeground(new java.awt.Color(255, 255, 255));
        jButton31.setText("Update");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        jButton32.setBackground(new java.awt.Color(51, 0, 51));
        jButton32.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jButton32.setForeground(new java.awt.Color(255, 255, 255));
        jButton32.setText("Reset");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        jComboBox2.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        jLabel99.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel99.setText("Net Quantity");

        jTextField31.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jTextField31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField31ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(182, 182, 182)
                .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(224, 224, 224)
                .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel53)
                        .addGap(42, 42, 42)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jTextField47, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField48, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(118, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel50)
                            .addComponent(jLabel51)
                            .addComponent(jLabel54)
                            .addComponent(jLabel56)
                            .addComponent(jLabel52)
                            .addComponent(jLabel55)
                            .addComponent(jLabel99))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addGap(101, 101, 101)
                                    .addComponent(jLabel49))
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField45, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                                        .addComponent(jTextField46))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField49, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField50, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField42, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField43, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField44, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jPanel9Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton31, jButton32});

        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel49)
                .addGap(29, 29, 29)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(jTextField41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(jTextField42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(jTextField43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jTextField44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel56))))
                .addGap(28, 28, 28)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel99)
                    .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton31)
                    .addComponent(jButton32))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout UpdateStockLayout = new javax.swing.GroupLayout(UpdateStock.getContentPane());
        UpdateStock.getContentPane().setLayout(UpdateStockLayout);
        UpdateStockLayout.setHorizontalGroup(
            UpdateStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        UpdateStockLayout.setVerticalGroup(
            UpdateStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDesktopPane2.add(UpdateStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 790, 620));

        ViewRegisteredMedicine.setBorder(null);
        ViewRegisteredMedicine.setClosable(true);
        ViewRegisteredMedicine.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        ViewRegisteredMedicine.setTitle("View Registered Medicine");
        ViewRegisteredMedicine.setVisible(false);

        jPanel10.setBackground(new java.awt.Color(224, 200, 224));

        jLabel57.setFont(new java.awt.Font("Broadway", 0, 36)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(51, 0, 51));
        jLabel57.setText("View Registered Medicine");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1010, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel57)
                .addGap(254, 254, 254))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel57)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout ViewRegisteredMedicineLayout = new javax.swing.GroupLayout(ViewRegisteredMedicine.getContentPane());
        ViewRegisteredMedicine.getContentPane().setLayout(ViewRegisteredMedicineLayout);
        ViewRegisteredMedicineLayout.setHorizontalGroup(
            ViewRegisteredMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ViewRegisteredMedicineLayout.setVerticalGroup(
            ViewRegisteredMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDesktopPane2.add(ViewRegisteredMedicine, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 1010, 570));

        ViewStock.setBorder(null);
        ViewStock.setClosable(true);
        ViewStock.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        ViewStock.setTitle("View Stock");
        ViewStock.setVisible(false);

        jPanel11.setBackground(new java.awt.Color(224, 200, 224));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jLabel58.setFont(new java.awt.Font("Broadway", 0, 36)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(51, 0, 51));
        jLabel58.setText("View Stock");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1040, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(386, 386, 386)
                .addComponent(jLabel58)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel58)
                .addGap(53, 53, 53)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ViewStockLayout = new javax.swing.GroupLayout(ViewStock.getContentPane());
        ViewStock.getContentPane().setLayout(ViewStockLayout);
        ViewStockLayout.setHorizontalGroup(
            ViewStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ViewStockLayout.setVerticalGroup(
            ViewStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDesktopPane2.add(ViewStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 1040, 550));

        ViewReorderList.setBorder(null);
        ViewReorderList.setClosable(true);
        ViewReorderList.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        ViewReorderList.setTitle("View Reorder List");
        ViewReorderList.setVisible(false);

        jPanel12.setBackground(new java.awt.Color(224, 200, 224));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable4);

        jLabel59.setFont(new java.awt.Font("Broadway", 0, 36)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(51, 0, 51));
        jLabel59.setText(" Reorder List");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1040, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(413, 413, 413)
                .addComponent(jLabel59)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel59)
                .addGap(62, 62, 62)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ViewReorderListLayout = new javax.swing.GroupLayout(ViewReorderList.getContentPane());
        ViewReorderList.getContentPane().setLayout(ViewReorderListLayout);
        ViewReorderListLayout.setHorizontalGroup(
            ViewReorderListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ViewReorderListLayout.setVerticalGroup(
            ViewReorderListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDesktopPane2.add(ViewReorderList, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 1040, 550));

        ViewAllInvoices.setBorder(null);
        ViewAllInvoices.setClosable(true);
        ViewAllInvoices.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        ViewAllInvoices.setTitle("View All Invoices");
        ViewAllInvoices.setVisible(false);

        jPanel13.setBackground(new java.awt.Color(153, 204, 255));

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(jTable5);

        jLabel60.setFont(new java.awt.Font("Broadway", 0, 36)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(0, 0, 51));
        jLabel60.setText("View All Invoices");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1040, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(349, 349, 349)
                .addComponent(jLabel60)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel60)
                .addGap(61, 61, 61)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ViewAllInvoicesLayout = new javax.swing.GroupLayout(ViewAllInvoices.getContentPane());
        ViewAllInvoices.getContentPane().setLayout(ViewAllInvoicesLayout);
        ViewAllInvoicesLayout.setHorizontalGroup(
            ViewAllInvoicesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ViewAllInvoicesLayout.setVerticalGroup(
            ViewAllInvoicesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDesktopPane2.add(ViewAllInvoices, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 1040, 550));

        ViewInvoiceByCustomerMobileNumber.setBorder(null);
        ViewInvoiceByCustomerMobileNumber.setClosable(true);
        ViewInvoiceByCustomerMobileNumber.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        ViewInvoiceByCustomerMobileNumber.setTitle("View Invoice by Customer Mobile Number");
        ViewInvoiceByCustomerMobileNumber.setVisible(false);

        jPanel14.setBackground(new java.awt.Color(224, 200, 224));

        jLabel61.setFont(new java.awt.Font("Broadway", 0, 36)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(51, 0, 51));
        jLabel61.setText("View Invoices by Customer Mobile Number");

        jLabel62.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel62.setText("Customer Contact ");

        jTextField51.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N

        jButton34.setBackground(new java.awt.Color(51, 0, 51));
        jButton34.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jButton34.setForeground(new java.awt.Color(255, 255, 255));
        jButton34.setText("Search");
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        jButton35.setBackground(new java.awt.Color(51, 0, 51));
        jButton35.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jButton35.setForeground(new java.awt.Color(255, 255, 255));
        jButton35.setText("Reset");
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });

        jTable7.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(jTable7);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(jLabel62)
                .addGap(48, 48, 48)
                .addComponent(jTextField51, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                .addComponent(jButton34)
                .addGap(73, 73, 73)
                .addComponent(jButton35)
                .addGap(115, 115, 115))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7)
                .addContainerGap())
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(jLabel61)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel61)
                .addGap(49, 49, 49)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(jTextField51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton34)
                    .addComponent(jButton35))
                .addGap(63, 63, 63)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ViewInvoiceByCustomerMobileNumberLayout = new javax.swing.GroupLayout(ViewInvoiceByCustomerMobileNumber.getContentPane());
        ViewInvoiceByCustomerMobileNumber.getContentPane().setLayout(ViewInvoiceByCustomerMobileNumberLayout);
        ViewInvoiceByCustomerMobileNumberLayout.setHorizontalGroup(
            ViewInvoiceByCustomerMobileNumberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ViewInvoiceByCustomerMobileNumberLayout.setVerticalGroup(
            ViewInvoiceByCustomerMobileNumberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDesktopPane2.add(ViewInvoiceByCustomerMobileNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 1060, 590));

        ViewInvoiceByInvoiceNumber.setBorder(null);
        ViewInvoiceByInvoiceNumber.setClosable(true);
        ViewInvoiceByInvoiceNumber.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        ViewInvoiceByInvoiceNumber.setTitle("View Invoice by Invoice Number");
        ViewInvoiceByInvoiceNumber.setVisible(false);

        jPanel16.setBackground(new java.awt.Color(224, 200, 224));

        jLabel77.setFont(new java.awt.Font("Broadway", 0, 36)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(51, 0, 51));
        jLabel77.setText("View Invoices by Invoice Number");

        jLabel78.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel78.setText("Invoice ID ");

        jTextField52.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N

        jButton36.setBackground(new java.awt.Color(51, 0, 51));
        jButton36.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jButton36.setForeground(new java.awt.Color(255, 255, 255));
        jButton36.setText("Search");
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });

        jButton37.setBackground(new java.awt.Color(51, 0, 51));
        jButton37.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jButton37.setForeground(new java.awt.Color(255, 255, 255));
        jButton37.setText("Reset");
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        jTable8.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane8.setViewportView(jTable8);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(jLabel78)
                .addGap(75, 75, 75)
                .addComponent(jTextField52, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101)
                .addComponent(jButton36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addComponent(jButton37)
                .addGap(124, 124, 124))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8)
                .addContainerGap())
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addComponent(jLabel77)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel77)
                .addGap(49, 49, 49)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel78)
                    .addComponent(jTextField52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton36)
                    .addComponent(jButton37))
                .addGap(54, 54, 54)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(154, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ViewInvoiceByInvoiceNumberLayout = new javax.swing.GroupLayout(ViewInvoiceByInvoiceNumber.getContentPane());
        ViewInvoiceByInvoiceNumber.getContentPane().setLayout(ViewInvoiceByInvoiceNumberLayout);
        ViewInvoiceByInvoiceNumberLayout.setHorizontalGroup(
            ViewInvoiceByInvoiceNumberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ViewInvoiceByInvoiceNumberLayout.setVerticalGroup(
            ViewInvoiceByInvoiceNumberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDesktopPane2.add(ViewInvoiceByInvoiceNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 1040, 620));

        ChangePassword.setBorder(null);
        ChangePassword.setClosable(true);
        ChangePassword.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        ChangePassword.setTitle("Change Password");
        ChangePassword.setVisible(false);

        jPanel19.setBackground(new java.awt.Color(224, 200, 224));

        jLabel93.setFont(new java.awt.Font("Broadway", 0, 36)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(51, 0, 51));
        jLabel93.setText("Change Password");

        jLabel94.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel94.setText("Old Password");

        jLabel95.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel95.setText("New Password");

        jButton38.setBackground(new java.awt.Color(51, 0, 51));
        jButton38.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jButton38.setForeground(new java.awt.Color(255, 255, 255));
        jButton38.setText("Change");
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });

        jButton39.setBackground(new java.awt.Color(51, 0, 51));
        jButton39.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jButton39.setForeground(new java.awt.Color(255, 255, 255));
        jButton39.setText("Reset");
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });

        jPasswordField3.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N

        jPasswordField4.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N

        jLabel96.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel96.setText("Confirm Password");

        jPasswordField5.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel94)
                            .addComponent(jLabel95)
                            .addComponent(jLabel96))
                        .addGap(67, 67, 67)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPasswordField3, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                            .addComponent(jPasswordField4)
                            .addComponent(jPasswordField5)))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(jButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(159, 159, 159)
                        .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(129, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel93)
                .addGap(196, 196, 196))
        );

        jPanel19Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton38, jButton39});

        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel94))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel93)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                        .addComponent(jPasswordField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(55, 55, 55)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPasswordField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel95))
                .addGap(60, 60, 60)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel96)
                    .addComponent(jPasswordField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton38)
                    .addComponent(jButton39))
                .addGap(69, 69, 69))
        );

        javax.swing.GroupLayout ChangePasswordLayout = new javax.swing.GroupLayout(ChangePassword.getContentPane());
        ChangePassword.getContentPane().setLayout(ChangePasswordLayout);
        ChangePasswordLayout.setHorizontalGroup(
            ChangePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ChangePasswordLayout.setVerticalGroup(
            ChangePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDesktopPane2.add(ChangePassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, 750, 540));

        GenerateInvoice.setClosable(true);
        GenerateInvoice.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        GenerateInvoice.setTitle("Generate Invoice");
        GenerateInvoice.setVisible(false);

        jPanel18.setBackground(new java.awt.Color(204, 204, 255));

        jLabel100.setFont(new java.awt.Font("Broadway", 0, 36)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(51, 0, 102));
        jLabel100.setText("Generate Invoice");

        jLabel101.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel101.setText("Medicine Name");

        jLabel102.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel102.setText("Price");

        jLabel103.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel103.setText("Manufacturing Date");

        jLabel104.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel104.setText("Expiry Date");

        jLabel105.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel105.setText("Available Quantity");

        jLabel106.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel106.setText("Purchasing Quantity");

        jComboBox3.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });

        jTextField38.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N

        jTextField53.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jTextField53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField53ActionPerformed(evt);
            }
        });

        jTextField54.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N

        jTextField55.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N

        jTextField56.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N

        jPanel20.setBackground(new java.awt.Color(153, 153, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Customer Detail", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 24))); // NOI18N

        jLabel108.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel108.setText("Customer Name");

        jLabel109.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel109.setText("Mobile Number");

        jTextField57.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N

        jTextField58.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel108)
                    .addComponent(jLabel109))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField57, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField58, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel108)
                    .addComponent(jTextField57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel109)
                    .addComponent(jTextField58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(97, Short.MAX_VALUE))
        );

        jButton33.setBackground(new java.awt.Color(0, 0, 51));
        jButton33.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jButton33.setForeground(new java.awt.Color(255, 255, 255));
        jButton33.setText("Add to Cart");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        jButton40.setBackground(new java.awt.Color(0, 0, 51));
        jButton40.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jButton40.setForeground(new java.awt.Color(255, 255, 255));
        jButton40.setText("Generate Invoice");
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(394, 394, 394)
                        .addComponent(jLabel100))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel101)
                            .addComponent(jLabel102)
                            .addComponent(jLabel103)
                            .addComponent(jLabel104)
                            .addComponent(jLabel105)
                            .addComponent(jLabel106))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jTextField56, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                                .addComponent(jButton33)
                                .addGap(67, 67, 67)
                                .addComponent(jButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField54, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField53, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField38, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField55, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(33, 33, 33))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel100)
                .addGap(26, 26, 26)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel101))
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel102))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel103)
                            .addComponent(jTextField53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel104)
                            .addComponent(jTextField54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(58, 58, 58)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel105)))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel106))))
                .addGap(41, 41, 41))
        );

        javax.swing.GroupLayout GenerateInvoiceLayout = new javax.swing.GroupLayout(GenerateInvoice.getContentPane());
        GenerateInvoice.getContentPane().setLayout(GenerateInvoiceLayout);
        GenerateInvoiceLayout.setHorizontalGroup(
            GenerateInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        GenerateInvoiceLayout.setVerticalGroup(
            GenerateInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDesktopPane2.add(GenerateInvoice, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 1050, 600));

        Invoice.setClosable(true);
        Invoice.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        Invoice.setTitle("Invoice");
        Invoice.setVisible(false);

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel110.setFont(new java.awt.Font("Broadway", 0, 24)); // NOI18N
        jLabel110.setText("Invoice");
        jPanel21.add(jLabel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jSeparator5.setForeground(new java.awt.Color(102, 102, 102));
        jPanel21.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 64, 100, -1));

        jLabel111.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel111.setText("Pharmacy Store");
        jPanel21.add(jLabel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 20, -1, -1));

        jLabel112.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel112.setText("Indira Nagar Lucknow, 9234340880");
        jPanel21.add(jLabel112, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 40, -1, 20));

        jLabel113.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jLabel113.setText("Invoice Number");
        jPanel21.add(jLabel113, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 123, -1, -1));

        jLabel114.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jLabel114.setText("Invoice Date");
        jPanel21.add(jLabel114, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 120, -1, -1));

        jLabel115.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jLabel115.setText("Customer Name");
        jPanel21.add(jLabel115, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 201, -1, -1));

        jLabel116.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jLabel116.setText("Contact Number");
        jPanel21.add(jLabel116, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 200, -1, -1));

        jSeparator6.setForeground(new java.awt.Color(51, 51, 51));
        jSeparator6.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 0, 0, new java.awt.Color(102, 102, 102)));
        jPanel21.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 251, 12));

        jSeparator7.setForeground(new java.awt.Color(51, 51, 51));
        jSeparator7.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 0, 0, new java.awt.Color(102, 102, 102)));
        jPanel21.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, 250, 10));

        jSeparator8.setForeground(new java.awt.Color(51, 51, 51));
        jSeparator8.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 0, 0, new java.awt.Color(102, 102, 102)));
        jPanel21.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 140, 290, 10));

        jSeparator9.setForeground(new java.awt.Color(51, 51, 51));
        jSeparator9.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 0, 0, new java.awt.Color(102, 102, 102)));
        jPanel21.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 220, 270, 10));

        jTable6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(jTable6);

        jPanel21.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 270, 960, 140));

        jLabel117.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel117.setText("jLabel117");
        jPanel21.add(jLabel117, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, -1, -1));

        jLabel118.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel118.setText("jLabel118");
        jPanel21.add(jLabel118, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 120, -1, -1));

        jLabel119.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel119.setText("jLabel119");
        jPanel21.add(jLabel119, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, -1, -1));

        jLabel120.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel120.setText("jLabel120");
        jPanel21.add(jLabel120, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 200, -1, -1));

        jLabel121.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel121.setText("Payable Amount");
        jPanel21.add(jLabel121, new org.netbeans.lib.awtextra.AbsoluteConstraints(628, 447, -1, -1));

        jLabel122.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel122.setText(" Due Amount");
        jPanel21.add(jLabel122, new org.netbeans.lib.awtextra.AbsoluteConstraints(646, 483, -1, -1));

        jSeparator10.setForeground(new java.awt.Color(102, 102, 102));
        jSeparator10.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(153, 153, 153)));
        jPanel21.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(756, 494, 139, -1));

        jSeparator11.setForeground(new java.awt.Color(102, 102, 102));
        jSeparator11.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(153, 153, 153)));
        jPanel21.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(756, 465, 155, -1));

        jLabel123.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel123.setText("jLabel123");
        jPanel21.add(jLabel123, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 440, -1, -1));

        jSeparator12.setForeground(new java.awt.Color(51, 51, 51));
        jPanel21.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(766, 62, 230, -1));

        jPanel22.setBackground(new java.awt.Color(224, 224, 224));

        jButton42.setBackground(new java.awt.Color(0, 0, 0));
        jButton42.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jButton42.setForeground(new java.awt.Color(255, 255, 255));
        jButton42.setText("Print");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap(794, Short.MAX_VALUE)
                .addComponent(jButton42, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(151, 151, 151))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton42, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel21.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 518, 1059, -1));

        javax.swing.GroupLayout InvoiceLayout = new javax.swing.GroupLayout(Invoice.getContentPane());
        Invoice.getContentPane().setLayout(InvoiceLayout);
        InvoiceLayout.setHorizontalGroup(
            InvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1018, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        InvoiceLayout.setVerticalGroup(
            InvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
        );

        jDesktopPane2.add(Invoice, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 1020, 610));

        jLabel98.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(242, 30, 30));
        jDesktopPane2.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 50, 180, 20));

        jLabel97.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(242, 30, 30));
        jLabel97.setText(" ");
        jLabel97.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jDesktopPane2.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 20, 170, -1));

        jLabel1.setBackground(new java.awt.Color(204, 255, 102));
        jLabel1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pexels-castorly.jpg"))); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jDesktopPane2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 670));

        jPanel2.add(jDesktopPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 670));

        jDesktopPane1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 670));

        jPanel1.add(jDesktopPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1080, 670));

        jMenu1.setText("Employee");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setText("Add Employee");
        jMenuItem1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jMenuItem1AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem2.setText("Search/ Update/ Delete Employee");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("View Employee List");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Medicine");

        jMenuItem4.setText("Register Medicine");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("Search/ Update/ Delete Medicine");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem6.setText("Add Stock");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuItem7.setText("Update Stock");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Billing");

        jMenuItem8.setText("Generate Invoice");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Medicine Report");

        jMenuItem9.setText("View Register Medicine");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem9);

        jMenuItem10.setText("View Stock");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem10);

        jMenuItem11.setText("View Reorder List ");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem11);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Invoice Report");

        jMenuItem12.setText("View All Invoices");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem12);

        jMenuItem13.setText("View Invoice by Customer Mobile Number");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem13);

        jMenuItem14.setText("View Invoice by Invoice Number");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem14);

        jMenuBar1.add(jMenu5);

        jMenu6.setText("Account");

        jMenuItem17.setText("My Profile");
        jMenu6.add(jMenuItem17);

        jMenuItem15.setText("Change Password");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem15);

        jMenuItem16.setText("Log Out");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem16);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1081, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // Update Stock Menu to ````display med name in combo box`````
        try {
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("select med_name from stock_detail ");
            db.rst = db.pstmt.executeQuery();
            jComboBox2.removeAllItems();
            jComboBox2.addItem("Select Medicine");
            while (db.rst.next()) {
                jComboBox2.addItem(db.rst.getString(1));
            }

            UpdateStock.setVisible(true);
            jTextField31.setEditable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        viewRegisteredMed();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // Code to View All Invoices
        viewAllInvoices();
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        // Code to View Invoices by Customer Mobile Number

        ViewInvoiceByCustomerMobileNumber.setVisible(true);

    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        AddEmployee.setVisible(true);
        SearchUpdateDeleteEmployee.setVisible(false);
        EmployeeList.setVisible(false);
        RegisterMedicine.setVisible(false);
        SearchUpdateDeleteMedicine.setVisible(false);
        AddStock.setVisible(false);
        UpdateStock.setVisible(false);


    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        AddEmployee.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        SearchUpdateDeleteEmployee.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        SearchUpdateDeleteEmployee.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jMenuItem1AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1AncestorAdded

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // Code to Display Employee List
        employeeList();   // method calling
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Code to Display Employee List
        employeeList();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // Code for reset
        jTextField6.setText("");
        jTextField10.setText("");
        jTextField11.setText("");
        jTextField11.setText("");
        jTextField12.setText("");
        //radiobtn --
        jRadioButton3.setSelected(false);
        jRadioButton4.setSelected(false);
        jTextField13.setText("");
        jTextField14.setText("");
        jTextField15.setText("");
        jTextField16.setText("");
        jPasswordField2.setText("");
        jTextField6.setEditable(true);
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // Code to Delete
        try {
            String code = jTextField6.getText();
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("delete from emp_mstr where ecode=?");
            db.pstmt.setString(1, code);
            int a = JOptionPane.showConfirmDialog(this, "Are you sure you Want to Delete this Employee Record ?");
            if (a == 0) {

                int i = db.pstmt.executeUpdate();
                if (i > 0) {
                    JOptionPane.showMessageDialog(this, "Record Deleted Successfully");
                    jTextField6.setText("");
                    jTextField10.setText("");
                    jTextField11.setText("");
                    jTextField12.setText("");
                    jTextField13.setText("");
                    //radiobtn ---
                    jRadioButton3.setSelected(false);
                    jRadioButton4.setSelected(false);
                    jTextField14.setText("");
                    jTextField15.setText("");
                    jTextField16.setText("");
                    jPasswordField2.setText("");
                    jTextField6.setEditable(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Error in Deletion !");
                }

            } else if (a == 1) {
                SearchUpdateDeleteEmployee.setVisible(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // Code to Update Employee
        try {
            String code = jTextField6.getText();
            String name = jTextField10.getText();
            String post = jTextField11.getText();
            String dob = jTextField12.getText();
            String qua = jTextField13.getText();

            boolean flag = false;
            String gender = "";
            if (jRadioButton3.isSelected()) {
                gender = "Male";
                flag = true;
            } else if (jRadioButton4.isSelected()) {
                gender = "Female";
                flag = true;
            } else {
                JOptionPane.showMessageDialog(this, "Please select your Gender <<");
            }

            String addr = jTextField14.getText();
            String contact = jTextField15.getText();
            String email = jTextField16.getText();
            String password = jPasswordField2.getText();
            String status = jToggleButton1.getText();

            if (flag) {
                DBConnection db = new DBConnection();

                db.pstmt = db.con.prepareStatement("update emp_mstr set ename=?, epost=?, edob=?, equa=?, egender=?, eaddr=?, econtact=?, email=?, password=?, ac_status=? where ecode=? ");
                db.pstmt.setString(1, name);
                db.pstmt.setString(2, post);
                db.pstmt.setString(3, dob);
                db.pstmt.setString(4, qua);
                db.pstmt.setString(5, gender);
                db.pstmt.setString(6, addr);
                db.pstmt.setString(7, contact);
                db.pstmt.setString(8, email);
                db.pstmt.setString(9, password);
                db.pstmt.setString(10, status);
                db.pstmt.setString(11, code);

                int i = db.pstmt.executeUpdate();

                if (i > 0) {
                    JOptionPane.showMessageDialog(this, "Employee Record Updated Successfully");
                } else {
                    JOptionPane.showMessageDialog(this, "Employee code does not Match !");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        //Code to search Employee
        try {
            if (jTextField6.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter Employee Code.");
                jTextField6.requestFocus();
            } else {

                int code = Integer.parseInt(jTextField6.getText());
                DBConnection db = new DBConnection();
                db.pstmt = db.con.prepareStatement("select * from emp_mstr where ecode=?");
                db.pstmt.setInt(1, code);
                db.rst = db.pstmt.executeQuery();

                if (db.rst.next()) {
                    jTextField10.setText(db.rst.getString(2));
                    jTextField11.setText(db.rst.getString(3));
                    jTextField12.setText(db.rst.getString(4));
                    jTextField13.setText(db.rst.getString(5));
                    if (db.rst.getString(6).equals("Male")) {
                        jRadioButton3.setSelected(true);
                    }
                    if (db.rst.getString(6).equals("Female")) {
                        jRadioButton4.setSelected(true);
                    }
                    jTextField14.setText(db.rst.getString(7));
                    jTextField15.setText(db.rst.getString(8));
                    jTextField16.setText(db.rst.getString(9));
                    jPasswordField2.setText(db.rst.getString(10));
                    jToggleButton1.setText(db.rst.getString(11));

                    jTextField6.setEditable(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Employee Code does not Match !");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
        jRadioButton3.setSelected(false);
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
        jRadioButton4.setSelected(false);
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // Code for reset
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        //radiobtn --

        jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jPasswordField1.setText("");
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        //------> Code To Add New Employee
        try {
            if (jTextField1.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter Employee Code.");
                jTextField1.requestFocus();
            } else if (jTextField2.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter Employee Code.");
                jTextField2.requestFocus();
            } else if (jTextField3.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter Employee Code.");
                jTextField3.requestFocus();
            } else if (jTextField4.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter Employee Code.");
                jTextField4.requestFocus();
            } else if (jTextField5.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter Employee Code.");
                jTextField5.requestFocus();
            } else if (jTextField7.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter Employee Code.");
                jTextField7.requestFocus();
            } else if (jTextField8.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter Employee Code.");
                jTextField8.requestFocus();
            } else if (jTextField9.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter Employee Code.");
                jTextField9.requestFocus();
            } else if (jPasswordField1.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter Employee Code.");
                jPasswordField1.requestFocus();
            } else {

                String code = jTextField1.getText();
                String name = jTextField2.getText();
                String post = jTextField3.getText();
                String dob = jTextField4.getText();
                String qua = jTextField5.getText();
                String addr = jTextField7.getText();
                String contact = jTextField8.getText();
                String email = jTextField9.getText();
                String password = jPasswordField1.getText();
                boolean flag = false;
                String gender = "";
                if (jRadioButton1.isSelected()) {
                    gender = "Male";
                    flag = true;
                } else if (jRadioButton2.isSelected()) {
                    gender = "Female";
                    flag = true;
                } else {
                    JOptionPane.showMessageDialog(this, "Please select your Gender.");
                }
                if (flag) {

                    DBConnection db = new DBConnection();

                    db.pstmt = db.con.prepareStatement("insert into emp_mstr(ecode, ename, epost, edob, equa, egender, eaddr, econtact, email, password) values(?,?,?,?,?,?,?,?,?,?) ");
                    db.pstmt.setString(1, code);
                    db.pstmt.setString(2, name);
                    db.pstmt.setString(3, post);
                    db.pstmt.setString(4, dob);
                    db.pstmt.setString(5, qua);
                    db.pstmt.setString(6, gender);
                    db.pstmt.setString(7, addr);
                    db.pstmt.setString(8, contact);
                    db.pstmt.setString(9, email);
                    db.pstmt.setString(10, password);

                    int i = db.pstmt.executeUpdate();

                    if (i > 0) {
                        JOptionPane.showMessageDialog(this, "New Employee Added :) ");
                    } else {
                        JOptionPane.showMessageDialog(this, "Error in Addition of New Employee !");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        RegisterMedicine.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        SearchUpdateDeleteMedicine.setVisible(true);

    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        RegisterMedicine.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // Code To Add /Register Medicine
        try {
            if (jTextField17.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter Medicine Name.");
                jTextField17.requestFocus();
            } else if (jTextField18.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter Medicine Company.");
                jTextField18.requestFocus();
            } else if (jTextField19.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter Medicine Formula.");
                jTextField19.requestFocus();
            } else if (jTextField20.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter Supplier Name.");
                jTextField20.requestFocus();
            } else if (jTextField21.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter Company Contact.");
                jTextField21.requestFocus();
            } else if (jTextField22.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter Supplier Contact.");
                jTextField22.requestFocus();
            } else if (jTextField23.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter Medicine Purpose.");
                jTextField23.requestFocus();
            } else {
                String name = jTextField17.getText();
                String company = jTextField18.getText();
                String formula = jTextField19.getText();
                String sup = jTextField20.getText();
                String comp_contact = jTextField21.getText();
                String sup_cont = jTextField22.getText();
                String purpose = jTextField23.getText();
                DBConnection db = new DBConnection();
                db.pstmt = db.con.prepareStatement("insert into product_mstr values(?,?,?,?,?,?,?) ");
                db.pstmt.setString(1, name);
                db.pstmt.setString(2, company);
                db.pstmt.setString(3, formula);
                db.pstmt.setString(4, sup);
                db.pstmt.setString(5, comp_contact);
                db.pstmt.setString(6, sup_cont);
                db.pstmt.setString(7, purpose);

                int i = db.pstmt.executeUpdate();
                if (i > 0) {
                    JOptionPane.showMessageDialog(this, "Medicine Registered Successfully :)");
                } else {
                    JOptionPane.showMessageDialog(this, "Error in Addition of New Medicine !");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
        jTextField17.setText("");
        jTextField18.setText("");
        jTextField19.setText("");
        jTextField20.setText("");
        jTextField21.setText("");
        jTextField22.setText("");
        jTextField23.setText("");
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        //Code to Search Medicine
        try {
            String name = jTextField24.getText();
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("select * from product_mstr where med_name=?");
            db.pstmt.setString(1, name);
            db.rst = db.pstmt.executeQuery();

            if (db.rst.next()) {
                jTextField25.setText(db.rst.getString(2));
                jTextField26.setText(db.rst.getString(3));
                jTextField27.setText(db.rst.getString(4));
                jTextField28.setText(db.rst.getString(5));
                jTextField29.setText(db.rst.getString(6));
                jTextField30.setText(db.rst.getString(7));

                jTextField24.setEditable(false);

            } else {
                JOptionPane.showMessageDialog(this, "No Medicine found with this Name !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        // Code to Update Medicine
        try {
            String name = jTextField24.getText();
            String company = jTextField25.getText();
            String formula = jTextField26.getText();
            String sup = jTextField27.getText();
            String comp_contact = jTextField28.getText();
            String sup_cont = jTextField29.getText();
            String purpose = jTextField30.getText();
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("update product_mstr set med_company=?, med_formula=?, med_sup=?, comp_contact=?, sup_cont=?, med_purpose=? where med_name=? ");
            db.pstmt.setString(1, company);
            db.pstmt.setString(2, formula);
            db.pstmt.setString(3, sup);
            db.pstmt.setString(4, comp_contact);
            db.pstmt.setString(5, sup_cont);
            db.pstmt.setString(6, purpose);
            db.pstmt.setString(7, name);

            int i = db.pstmt.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(this, "Medicine Updated Successfully :) ");
            } else {
                JOptionPane.showMessageDialog(this, "Error in Medicine Updation !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        // Code to Delete Medicine
        try {
            String name = jTextField24.getText();
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("delete from product_mstr where med_name=?");
            db.pstmt.setString(1, name);
            int a = JOptionPane.showConfirmDialog(this, "Are you sure you Want to Delete this Medicine Record ?");
            if (a == 0) {
                int i = db.pstmt.executeUpdate();
                if (i > 0) {
                    JOptionPane.showMessageDialog(this, "Medicine Record Deleted Successfully");
                    jTextField24.setText("");
                    jTextField25.setText("");
                    jTextField26.setText("");
                    jTextField27.setText("");
                    jTextField28.setText("");
                    jTextField29.setText("");
                    jTextField30.setText("");
                    jTextField24.setEditable(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Error in Medicine Record Deletion !");
                }
            } else if (a == 1) {
                SearchUpdateDeleteMedicine.setVisible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        jTextField24.setText("");
        jTextField25.setText("");
        jTextField26.setText("");
        jTextField27.setText("");
        jTextField28.setText("");
        jTextField29.setText("");
        jTextField30.setText("");
        jTextField24.setEditable(true);
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        SearchUpdateDeleteMedicine.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // Add Stock Menu 
        //code to display medicines in combo box 
        try {
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("select * from product_mstr ");
            db.rst = db.pstmt.executeQuery();
            jComboBox1.removeAllItems();
            jComboBox1.addItem("Select Medicine");
            while (db.rst.next()) {
                jComboBox1.addItem(db.rst.getString(1));
            }

            AddStock.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // Add Stock Button
        try {
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("select * from product_mstr ");
            db.rst = db.pstmt.executeQuery();
            jComboBox1.removeAllItems();
            jComboBox1.addItem("Select Medicine");
            while (db.rst.next()) {
                jComboBox1.addItem(db.rst.getString(1));
            }

            AddStock.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        // Code to Add Stock
        try {

            String name = "" + jComboBox1.getSelectedItem();
            int cp = Integer.parseInt(jTextField32.getText());
            int sp = Integer.parseInt(jTextField33.getText());
            String mfd = jTextField34.getText();
            String exd = jTextField35.getText();
            int reoder_level = Integer.parseInt(jTextField36.getText());
            int qty = Integer.parseInt(jTextField37.getText());
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("insert into stock_detail values(?,?,?,?,?,?,?) ");
            db.pstmt.setString(1, name);
            db.pstmt.setInt(2, cp);
            db.pstmt.setInt(3, sp);
            db.pstmt.setString(4, mfd);
            db.pstmt.setString(5, exd);
            db.pstmt.setInt(6, reoder_level);
            db.pstmt.setInt(7, qty);

            int i = db.pstmt.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(this, "New Stock Added Successfully :)");
                jComboBox1.setSelectedIndex(0);
                jTextField32.setText("");
                jTextField33.setText("");
                jTextField34.setText("");
                jTextField35.setText("");
                jTextField36.setText("");
                jTextField37.setText("");

            } else {
                JOptionPane.showMessageDialog(this, "Error in Addition of New Stock !");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please provide input in correct format : " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        // code To Reset
        jComboBox1.setSelectedIndex(0);
        jTextField32.setText("");
        jTextField33.setText("");
        jTextField34.setText("");
        jTextField35.setText("");
        jTextField36.setText("");
        jTextField37.setText("");
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        // Code to Update Stock
        try {
            String name = "" + jComboBox2.getSelectedItem();

            int cp = Integer.parseInt(jTextField45.getText());
            int sp = Integer.parseInt(jTextField46.getText());
            String mfd = jTextField47.getText();
            String exd = jTextField48.getText();
            int reoder_level = Integer.parseInt(jTextField49.getText());

            int avl_qty = Integer.parseInt(jTextField50.getText());
            int net_qty = Integer.parseInt(jTextField31.getText());
            int qty = avl_qty + net_qty;

            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("update stock_detail set med_cp=?, med_sp=?, med_mfd=?, med_exd=?, reorder_level=?, med_qty=? where med_name=? ");
            db.pstmt.setInt(1, cp);
            db.pstmt.setInt(2, sp);
            db.pstmt.setString(3, mfd);
            db.pstmt.setString(4, exd);
            db.pstmt.setInt(5, reoder_level);
            db.pstmt.setInt(6, qty);
            db.pstmt.setString(7, name);

            int i = db.pstmt.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(this, "Stock Updated Successfully :) ");
            } else {
                JOptionPane.showMessageDialog(this, "Error in Stock Updation !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        // code to reset
        //jComboBox2.setEditable(true);
        jComboBox2.setEnabled(true);

        jComboBox2.setSelectedIndex(0);
        jTextField45.setText("");
        jTextField46.setText("");
        jTextField47.setText("");
        jTextField48.setText("");
        jTextField49.setText("");
        jTextField50.setText("");
        jTextField31.setText("");


    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // Update Stock Menu
        try {
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("select * from product_mstr ");
            db.rst = db.pstmt.executeQuery();
            jComboBox2.removeAllItems();
            jComboBox2.addItem("Select Medicine");
            while (db.rst.next()) {
                jComboBox2.addItem(db.rst.getString(1));
            }

            UpdateStock.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // Code to veiw registered medicine
        viewRegisteredMed();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // Code to veiw Stock
        viewStock();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // Code to veiw Stock
        viewStock();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // Code to veiw Reorder List -------------->
        viewReorderList();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // Code to View All Invoices
        viewAllInvoices();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        // code to reset
        jTextField51.setText("");

    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        // Code to search invoice by Customer Mobile number
        try {
            String contact = jTextField51.getText();
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("select * from invoice where cust_contact=?");
            db.pstmt.setString(1, contact);
            db.rst = db.pstmt.executeQuery();

            if (db.rst.next()) {
                Vector<String> header = new Vector<String>();
                header.add("Invoice ID");
                header.add("Medicine Name");
                header.add("Company");
                header.add("Medicine Formula");
                header.add("Medicine Quantity");
                header.add("Invoice Date and Time");
                header.add("Customer Name");
                header.add("Customer Contact");
                header.add("Bill Amount");

                Vector<Vector<String>> data = new Vector<Vector<String>>();

                db.pstmt = db.con.prepareStatement("SELECT invoice.invoice_id, invoice.med_name, product_mstr.med_company, product_mstr.med_formula, invoice.med_qty, invoice.invc_date_time, invoice.`cust_name`, invoice.`cust_contact`, bill_detail.`bill_amt` \n"
                        + "FROM ((invoice\n"
                        + "INNER JOIN product_mstr ON invoice.med_name = product_mstr.med_name)\n"
                        + "INNER JOIN bill_detail ON invoice.invoice_id = bill_detail.invc_no) WHERE cust_contact = ?");
                db.pstmt.setString(1, contact);
                db.rst = db.pstmt.executeQuery();
                while (db.rst.next()) {
                    Vector<String> temp = new Vector<String>();
                    temp.add(db.rst.getString(1));
                    temp.add(db.rst.getString(2));
                    temp.add(db.rst.getString(3));
                    temp.add(db.rst.getString(4));
                    temp.add(db.rst.getString(5));
                    temp.add(db.rst.getString(6));
                    temp.add(db.rst.getString(7));
                    temp.add(db.rst.getString(8));
                    temp.add(db.rst.getString(9));
                    data.add(temp);
                }
                jTable7.setModel(new DefaultTableModel(data, header));

            } else {
                JOptionPane.showMessageDialog(this, "No Maching Contact Found !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        // Code to search invoice by Invoice number
        try {
            String invoiceId = jTextField52.getText();
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("select * from invoice where invoice_id=?");
            db.pstmt.setString(1, invoiceId);
            db.rst = db.pstmt.executeQuery();

            if (db.rst.next()) {
                Vector<String> header = new Vector<String>();
                header.add("Invoice ID");
                header.add("Medicine Name");
                header.add("Company");
                header.add("Medicine Formula");
                header.add("Medicine Quantity");
                header.add("Invoice Date and Time");
                header.add("Customer Name");
                header.add("Customer Contact");
                header.add("Bill Amount");

                Vector<Vector<String>> data = new Vector<Vector<String>>();

                db.pstmt = db.con.prepareStatement("SELECT invoice.invoice_id, invoice.med_name, product_mstr.med_company, product_mstr.med_formula, invoice.med_qty, invoice.invc_date_time, invoice.`cust_name`, invoice.`cust_contact`, bill_detail.`bill_amt` \n"
                        + "FROM ((invoice\n"
                        + "INNER JOIN product_mstr ON invoice.med_name = product_mstr.med_name)\n"
                        + "INNER JOIN bill_detail ON invoice.invoice_id = bill_detail.invc_no) WHERE invoice_id = ?");
                db.pstmt.setString(1, invoiceId);
                db.rst = db.pstmt.executeQuery();
                while (db.rst.next()) {
                    Vector<String> temp = new Vector<String>();
                    temp.add(db.rst.getString(1));
                    temp.add(db.rst.getString(2));
                    temp.add(db.rst.getString(3));
                    temp.add(db.rst.getString(4));
                    temp.add(db.rst.getString(5));
                    temp.add(db.rst.getString(6));
                    temp.add(db.rst.getString(7));
                    temp.add(db.rst.getString(8));
                    temp.add(db.rst.getString(9));
                    data.add(temp);
                }
                jTable8.setModel(new DefaultTableModel(data, header));

            } else {
                JOptionPane.showMessageDialog(this, "No Maching Invoice Number /ID Found !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        // code to reset
        jTextField52.setText("");

    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        //Code for View Invoices by Customer Mobile Number

        ViewInvoiceByCustomerMobileNumber.setVisible(true);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        // Code to View Invoices by Invoice Number

        ViewInvoiceByInvoiceNumber.setVisible(true);
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // Code to View Invoices by Invoice Number

        ViewInvoiceByInvoiceNumber.setVisible(true);

    }//GEN-LAST:event_jButton14ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        // Code to log out
        int a = JOptionPane.showConfirmDialog(this, "Want to Log Out now ?");
        if (a == 0) {
            this.setVisible(false);
            new LoginWindow().setVisible(true);
        } else if (a == 1) {
            this.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // Code to log out
        int a = JOptionPane.showConfirmDialog(this, "Want to Log Out now ?");
        if (a == 0) {
            this.setVisible(false);
            new LoginWindow().setVisible(true);
        } else if (a == 1) {
            this.setVisible(true);
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        // Code to Change Password
        try {

            String old_pswd = jPasswordField3.getText();
            String new_pswd = jPasswordField4.getText();
            String conf_pswd = jPasswordField5.getText();

            DBConnection db = new DBConnection();
            if (type.equals("Admin")) {
                db.pstmt = db.con.prepareStatement("select * from admin where user=? and password=? ");
                db.pstmt.setString(1, user);
                db.pstmt.setString(2, old_pswd);
                db.rst = db.pstmt.executeQuery();
                if (db.rst.next()) {
                    if (new_pswd.equals(conf_pswd)) {
                        db.pstmt = db.con.prepareStatement("update admin set password=? where user=? ");
                        db.pstmt.setString(1, new_pswd);
                        db.pstmt.setString(2, user);
                        int i = db.pstmt.executeUpdate();
                        if (i > 0) {
                            JOptionPane.showMessageDialog(this, "Password Updated Successfully :)");
                        } else {
                            JOptionPane.showMessageDialog(this, "Error in Password Updation !");
                        }

                    } else {
                        JOptionPane.showMessageDialog(this, "New Password & Confirm Password does not Match !");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Old Password does not Match !");
                }

            } else if (type.equals("Member")) {
                db.pstmt = db.con.prepareStatement("select * from emp_mstr where email=? and password=? ");
                db.pstmt.setString(1, user);
                db.pstmt.setString(2, old_pswd);
                db.rst = db.pstmt.executeQuery();
                if (db.rst.next()) {
                    if (new_pswd.equals(conf_pswd)) {
                        db.pstmt = db.con.prepareStatement("update emp_mstr set password=? where email=? ");
                        db.pstmt.setString(1, new_pswd);
                        db.pstmt.setString(2, user);
                        int i = db.pstmt.executeUpdate();
                        if (i > 0) {
                            JOptionPane.showMessageDialog(this, "Password Updated Successfully :)");
                        } else {
                            JOptionPane.showMessageDialog(this, "Error in Password Updation !");
                        }

                    } else {
                        JOptionPane.showMessageDialog(this, "New Password & Confirm Password does not Match !");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Old Password does not Match !");
                }

            } else {
                JOptionPane.showMessageDialog(this, "Please Login First !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        // TODO add your handling code here:
        jPasswordField3.setText("");
        jPasswordField4.setText("");
        jPasswordField5.setText("");
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        // TODO add your handling code here:
        ChangePassword.setVisible(true);
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        ChangePassword.setVisible(true);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        //Code to Search Stock and displaying detaits of med
        try {
            String name = "" + jComboBox2.getSelectedItem();

            jTextField31.setEditable(true);
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("select * from stock_detail where med_name=?");
            db.pstmt.setString(1, name);
            db.rst = db.pstmt.executeQuery();

            if (db.rst.next()) {
                jTextField45.setText(db.rst.getString(2));
                jTextField46.setText(db.rst.getString(3));
                jTextField47.setText(db.rst.getString(4));
                jTextField48.setText(db.rst.getString(5));
                jTextField49.setText(db.rst.getString(6));
                jTextField50.setText(db.rst.getString(7));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jTextField31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField31ActionPerformed
    int invc;          //  instance variable
    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // Code for display medicine name in combo box on Generate invoice MenuItem
        try {
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("select med_name from stock_detail");
            db.rst = db.pstmt.executeQuery();
            jComboBox3.removeAllItems();
            jComboBox3.addItem("Select Medicine");
            while (db.rst.next()) {
                jComboBox3.addItem(db.rst.getString(1));
            }
            GenerateInvoice.setVisible(true);
            db.pstmt = db.con.prepareStatement("Select max(invc_no) from bill_detail");
            db.rst = db.pstmt.executeQuery();
            if (db.rst.next()) {
                invc = db.rst.getInt(1);
                invc++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        // Code to display the Generate Bill Page 
        try {
            int amt = 0, price, qty;
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("select med_price,med_qty from invoice where invoice_id = ?");
            db.pstmt.setInt(1, invc);
            db.rst = db.pstmt.executeQuery();
            while (db.rst.next()) {
                price = db.rst.getInt(1);
                qty = db.rst.getInt(2);
                amt = amt + (price * qty);
            }
            String cust_name = jTextField57.getText();
            String cust_contact = jTextField58.getText();
            db.pstmt = db.con.prepareStatement("Insert into bill_detail values (?,?,?,?,?)");
            db.pstmt.setInt(1, invc);
            db.pstmt.setString(2, new java.util.Date().toString());
            db.pstmt.setString(3, cust_name);
            db.pstmt.setString(4, cust_contact);
            db.pstmt.setInt(5, amt);
            int i1 = db.pstmt.executeUpdate();
            Vector<String> header = new Vector<String>();
            header.add("Medicine Name");
            header.add("Manufacturing Date");
            header.add("Expiry Date");
            header.add("Price");
            header.add("Quantity");
            header.add("Amount");
            Vector<Vector<String>> data = new Vector<Vector<String>>();
            db.pstmt = db.con.prepareStatement("Select invoice.med_name,stock_detail.med_mfd,stock_detail.med_exd,invoice.med_price,invoice.med_qty from invoice,stock_detail where invoice.med_name = stock_detail.med_name and invoice.invoice_id = ?");
            db.pstmt.setInt(1, invc);
            db.rst = db.pstmt.executeQuery();
            while (db.rst.next()) {
                Vector<String> temp = new Vector<String>();
                temp.add(db.rst.getString(1));
                temp.add(db.rst.getString(2));
                temp.add(db.rst.getString(3));
                temp.add(db.rst.getString(4));
                temp.add(db.rst.getString(5));
                int amt1 = Integer.parseInt(db.rst.getString(4)) * Integer.parseInt(db.rst.getString(5));
                temp.add("" + amt1);
                data.add(temp);
            }
            jTable6.setModel(new DefaultTableModel(data, header));
            db.pstmt = db.con.prepareStatement("select date_time,cust_name,cust_cont,bill_amt from bill_detail where invc_no = ?");
            db.pstmt.setInt(1, invc);
            db.rst = db.pstmt.executeQuery();
            if (db.rst.next()) {
                jLabel117.setText("" + invc);
                jLabel118.setText(db.rst.getString(1));
                jLabel119.setText(db.rst.getString(2));
                jLabel120.setText(db.rst.getString(3));
                jLabel123.setText("₹ " + db.rst.getString(4));

            }
            GenerateInvoice.setVisible(false);
            Invoice.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jTextField53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField53ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField53ActionPerformed

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        // Code to Get Details of med
        try {
            String med_name = "" + jComboBox3.getSelectedItem();
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("select * from stock_detail where med_name=?");
            db.pstmt.setString(1, med_name);

            db.rst = db.pstmt.executeQuery();

            if (db.rst.next()) {
                jTextField38.setText(db.rst.getString(3));
                jTextField53.setText(db.rst.getString(4));
                jTextField54.setText(db.rst.getString(5));
                jTextField55.setText(db.rst.getString(7));
                jTextField38.setEditable(false);
                jTextField53.setEditable(false);
                jTextField54.setEditable(false);
                jTextField55.setEditable(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        // Code on Add to Cart Button

        int avl_qty = Integer.parseInt(jTextField55.getText());
        int qty = Integer.parseInt(jTextField56.getText());       //purchasing quantity
        if (avl_qty >= qty) {
            try {
                String med_name = jComboBox3.getSelectedItem().toString();
                String med_price = jTextField38.getText();
                String med_qty_purchase = jTextField56.getText();  //purchasing quantity
                String cust_name = jTextField57.getText();
                String cust_contact = jTextField58.getText();
                String emp_id = "Empxyz";

                DBConnection db = new DBConnection();

                db.pstmt = db.con.prepareStatement("insert into invoice values(?,?,?,?,?,?,?,?) ");
                db.pstmt.setInt(1, invc);
                db.pstmt.setString(2, med_name);
                db.pstmt.setString(3, med_price);
                db.pstmt.setString(4, med_qty_purchase);
                db.pstmt.setString(5, new java.util.Date().toString());
                db.pstmt.setString(6, cust_name);
                db.pstmt.setString(7, cust_contact);
                db.pstmt.setString(8, emp_id);

                int i1 = db.pstmt.executeUpdate();

                db.pstmt = db.con.prepareStatement("update stock_detail set med_qty=? where med_name=? ");
                db.pstmt.setInt(1, (avl_qty - qty));
                db.pstmt.setString(2, med_name);

                int i2 = db.pstmt.executeUpdate();

                if (i1 > 0 && i2 > 0) {
                    JOptionPane.showMessageDialog(this, "Medicine Added to Cart Succesfully");
                    jComboBox3.setSelectedIndex(0);
                    jTextField38.setText("");
                    jTextField53.setText("");
                    jTextField54.setText("");
                    jTextField55.setText("");
                    jTextField56.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Error in Addition of Medicine to Cart");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Quantity");
            jTextField56.setText("");
        }


    }//GEN-LAST:event_jButton33ActionPerformed

    private void jTextField16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField16ActionPerformed

    private void jToggleButton1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jToggleButton1ItemStateChanged
        // TODO add your handling code here:
        if (jToggleButton1.isSelected()) {
            jToggleButton1.setText("Inactive");
        } else {
            jToggleButton1.setText("Active");
        }
    }//GEN-LAST:event_jToggleButton1ItemStateChanged

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed

        // Code for display medicine name in combo box on Generate invoice MenuItem
        try {
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("select med_name from stock_detail");
            db.rst = db.pstmt.executeQuery();
            jComboBox3.removeAllItems();
            jComboBox3.addItem("Select Medicine");
            while (db.rst.next()) {
                jComboBox3.addItem(db.rst.getString(1));
            }
            GenerateInvoice.setVisible(true);
            db.pstmt = db.con.prepareStatement("Select max(invc_no) from bill_detail");
            db.rst = db.pstmt.executeQuery();
            if (db.rst.next()) {
                invc = db.rst.getInt(1);
                invc++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // Code to veiw Reorder List -------------->
        viewReorderList();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jTextField8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyTyped
        // Validating Mobile Number/ Contact 
        String str = jTextField8.getText();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            int asc = ch;
            if (!(asc >= 48 && asc <= 57)) {
                JOptionPane.showMessageDialog(this, "Invalid Mobile Number");
                jTextField8.setText("");
            }
        }
        String str2 = jTextField8.getText();
        if (str2.length() > 9) {
            JOptionPane.showMessageDialog(this, "Mobile Number cannot be of more than 10 digits.");
            jTextField8.setText(str2.substring(0, 9));
        }
    }//GEN-LAST:event_jTextField8KeyTyped

    private void jTextField9FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField9FocusLost
        // Email varification
        String str = jTextField9.getText();
        int a;
        a = str.indexOf('@');
        if (a == -1) {
            JOptionPane.showMessageDialog(this, "Invalid Email.");
            jTextField9.requestFocus();
        }

    }//GEN-LAST:event_jTextField9FocusLost

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
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JInternalFrame AddEmployee;
    private javax.swing.JInternalFrame AddStock;
    private javax.swing.JInternalFrame ChangePassword;
    private javax.swing.JInternalFrame EmployeeList;
    private javax.swing.JInternalFrame GenerateInvoice;
    private javax.swing.JInternalFrame Invoice;
    private javax.swing.JInternalFrame RegisterMedicine;
    private javax.swing.JInternalFrame SearchUpdateDeleteEmployee;
    private javax.swing.JInternalFrame SearchUpdateDeleteMedicine;
    private javax.swing.JInternalFrame UpdateStock;
    private javax.swing.JInternalFrame ViewAllInvoices;
    private javax.swing.JInternalFrame ViewInvoiceByCustomerMobileNumber;
    private javax.swing.JInternalFrame ViewInvoiceByInvoiceNumber;
    private javax.swing.JInternalFrame ViewRegisteredMedicine;
    private javax.swing.JInternalFrame ViewReorderList;
    private javax.swing.JInternalFrame ViewStock;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JPasswordField jPasswordField4;
    private javax.swing.JPasswordField jPasswordField5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField35;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JTextField jTextField37;
    private javax.swing.JTextField jTextField38;
    private javax.swing.JTextField jTextField39;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField40;
    private javax.swing.JTextField jTextField41;
    private javax.swing.JTextField jTextField42;
    private javax.swing.JTextField jTextField43;
    private javax.swing.JTextField jTextField44;
    private javax.swing.JTextField jTextField45;
    private javax.swing.JTextField jTextField46;
    private javax.swing.JTextField jTextField47;
    private javax.swing.JTextField jTextField48;
    private javax.swing.JTextField jTextField49;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField50;
    private javax.swing.JTextField jTextField51;
    private javax.swing.JTextField jTextField52;
    private javax.swing.JTextField jTextField53;
    private javax.swing.JTextField jTextField54;
    private javax.swing.JTextField jTextField55;
    private javax.swing.JTextField jTextField56;
    private javax.swing.JTextField jTextField57;
    private javax.swing.JTextField jTextField58;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
