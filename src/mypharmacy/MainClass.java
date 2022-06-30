package mypharmacy;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.*;

import static java.awt.Font.BOLD;

public class MainClass extends JFrame {

    private ImageIcon icon = new ImageIcon("logo.png");

    //DB sql opjects
    static Connection c;
    static Statement s;
    static ResultSet r;
    String query;

    //frame panels
    private JPanel employeePanel, adminPanel, loginPanel, billPanel, purchasesPanel,
            searchPanel, drugsPanel, salesPurchases, settingsPanel, registerPanel, welcomePanel;

    //login and register panels
    private JButton login,reg;
    private JLabel lusername, lpassword , userkind, fN, ln, loginWrong;
    private JTextField unField, fnTf, lnTf,reunField;
    private JComboBox cb;
    private JPasswordField passwordField, repasswordField;
    private String[] uk = {"admin","employee"};
    public static String Name = "", Password = "", firstName = "", secondName = ""
            , userk = "", userN = "", rePassword = "";
    private String loginWrongString = "";

    //welcome panel
    private JLabel hello, made, asmaa;

    //admin panel
    private JButton bills, purchases, searchUpdate, drugs, settings, earnings, register;

    //employee panel
    private JButton billsE, purchasesE, searchE, drugsE, settingsE;

    //billPanel components
    private JButton addToBill, printBill, delete;
    private JLabel codeLabel, amountLabel;
    private JTextField codeField, amountField;
    private JTable billTable;
    private int billAmount, billCode;
    private String[] billTableColumnNames = {" Amount "," Name "," Kind"," Unit price "," Total price "};
    private String billRow[] = new String[5];
    private DefaultTableModel model1 = new DefaultTableModel();

    //purshasesPanel components
    private JLabel code, name, amount, price, expiry, kind;
    private JTextField codeF, nameF, amountF, priceF;
    private JComboBox kindCbox,dayBox,monthBox,yearBox;
    private JButton addProduct;
    private String[] drugKind = {"drink","injection","tablets"};
    private int codeInt, expiryDay, expiryMonth, expiryYear, amountInt;
    private float priceFloat;
    private String nameString, productKind,expirydate;

    //searchPanel components
    private JLabel codeS, nameS, amountS, priceS, expiryS,kindS;
    private JComboBox kindCboxS;
    private JTextField codeFs, nameFs, amountFs, priceFs, expiryFs;
    private JButton search, update ,deleteS;

    //drugsPanel components
    private JLabel sort;
    private JComboBox sortingBy;
    private JTable productsTable;
    private DefaultTableModel model = new DefaultTableModel();
    private String[] productsTableColumnNames = {" Code "," Name "," Kind"," Amount "," Price "," Expiry "};
    private Object[] productsRows = new Object[6];

    //salesPurshases panel combonents
    private JLabel date, quaryKind, total;
    private String[] dayS = {"Day","1","2","3","4","5","6","7","8","9","10",
            "11","12","13","14","15","16","17","18","19","20","21"
            ,"22","23","24","25","26","27","28","29","30","31"};
    private String[] monthS = {"Month","1","2","3","4","5","6","7","8","9","10","11","12"};
    private String[] yearS = {"Year","2010","2011","2012","2013","2014","2015","2016","2017"
            ,"2018","2019","2020","2021","2022","2023","2024","2025"};
    private String[] kindQuary = {"sales","purshases"};
    private JComboBox QKCB,day,month,year;
    private JTextField totalField;
    private DefaultTableModel model2 = new DefaultTableModel();
    private JTable SPtable;
    private String selectedKind;

    //settings panel
    private JLabel enter, fnLabel, lnLabel, unLabel, pLabel;
    private JButton fnButton, lnButton, unButton, pButton, infoButton;
    private JTextField  fnF, lnF, unF;
    private JPasswordField enterF, pF;
    static String enteredPassword = "", uFn = "", uLn = "", uUn = "",uP = "";

    //Fonts
    private Font buttonsFont = new Font("Arial", BOLD, 24);
    private Font f = new Font("Tw Cen MT Condensed",BOLD,32);
    private Font fnames = new Font("Tw Cen MT Condensed",BOLD,43);
    private Font fn = new Font("Tw Cen MT Condensed",BOLD,40);
    private Font ff = new Font("Arial", BOLD, 22);
    private Font fff = new Font("Arial", BOLD, 18);
    private Font ffff = new Font("Arial", BOLD, 13);

    //    constructor   //
    MainClass() {
        //frame properties
        createView();
        setTitle(" Pharmacy ");
        setLayout(null);
        setResizable(false);
        setIconImage(icon.getImage());
        setSize(1200,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createView() {
        setBackgroundImage();
        createLoginPanel();
        createEmployeesPanel();
        createAdminPanel();
        createBillPanel();
        createPurchasesPanel();
        createSearchPanel();
        createDrugsPanel();
        createSalesPurchasesPanel();
        createSettingsPanel();
        createRegisterPanel();
    }

    private void setBackgroundImage() {
        try {
            final Image backgroundImage = javax.imageio.ImageIO.read(new File("image.png"));
            setContentPane(new JPanel() {
                @Override public void paintComponent(Graphics g) {
                    g.drawImage(backgroundImage, 0, 0, null);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getContentPane().setLayout(null); //contentPane null layout
    }

    private void createLoginPanel() {
        loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBounds(300,200,600,400);
        loginPanel.setBackground(new Color(200,0,0,100));
        loginPanel.setVisible(true);
        add(loginPanel);

        //-------------------------------//

        lusername = new JLabel("Username : ");
        lusername.setFont(f);
        lusername.setForeground(Color.WHITE);
        lusername.setBounds(100,100,140,50);
        loginPanel.add(lusername);

        unField = new JTextField();
        unField.setFont(fff);
        unField.setBounds(250,100,250,50);
        loginPanel.add(unField);

        lpassword = new JLabel("Password : ");
        lpassword.setFont(f);
        lpassword.setForeground(Color.WHITE);
        lpassword.setBounds(100,170,130,50);
        loginPanel.add(lpassword);

        passwordField = new JPasswordField();
        passwordField.setFont(fff);
        passwordField.setBounds(250,170,250,50);
        loginPanel.add(passwordField);

        loginWrong = new JLabel("");
        loginWrong.setForeground(Color.WHITE);
        loginWrong.setBounds(100,300,400,40);
        loginWrong.setFont(fff);
        loginWrong.setVisible(true);
        loginPanel.add(loginWrong);

        login = new JButton("Login");
        login.setBounds(100,250,400,50);
        login.setBackground(new Color(255,153,153));
        login.setFont(buttonsFont);
        login.setForeground(Color.white);
        login.setFocusPainted(false);
        login.setBorderPainted(false);
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Password = passwordField.getText();//get text from user and put it in string to use in sql
                System.out.println("password = "+ Password);

                Name = unField.getText();//get text from user and put it in string to use in sql
                createWelcomePanel(Name);

                System.out.println("username = "+ Name);

                try
                {
                    c = DriverManager.getConnection("jdbc:sqlite:my_database.sqlite");
                    System.out.println("connected with DB");
                    s = c.createStatement();
                    query = "select password from users where username = '" + Name + "';";
                    r = s.executeQuery(query);
                    if(r.isClosed())
                    {
                        loginWrong.setText(Name+" dosn't exist, please try again.");
                        unField.setText("");
                        System.out.println("username dosn't exist");
                    }
                    else if(r.getString("password").equals(Password))
                    {
                        loginPanel.setVisible(false);
                        welcomePanel.setVisible(true);
                        query = "select password from users "
                                + "where username = '"+Name+"' and userkind = 'admin';";
                        r = s.executeQuery(query);
                        if(r.isClosed())
                        {
                            employeePanel.setVisible(true);
                            adminPanel.setVisible(false);
                        }
                        else
                        {
                            employeePanel.setVisible(false);
                            adminPanel.setVisible(true);
                        }
                        System.out.println("login successfully");
                    }
                    else
                    {
                        loginWrong.setText("Password isn't correct, please try again.");
                        passwordField.setText("");
                        System.out.println("password isn't correct");
                    }

                }catch(SQLException ex){
                    System.out.println("Error durring Login");
                    System.out.println(ex.getMessage());
                }
                finally{
                    try{
                        s.close();
                        r.close();
                        c.close();
                    }
                    catch(SQLException ex){
                        System.out.println(ex.getMessage());
                    }
                }
                System.out.println("");
            }
        });
        loginPanel.add(login);
    }

    private void createWelcomePanel(String name) {

        welcomePanel = new JPanel();
        welcomePanel.setBounds(410, 50, 720, 660);
        welcomePanel.setLayout(null);
        welcomePanel.setVisible(false);
        welcomePanel.setBackground(new Color(100,0,0,150));
        add(welcomePanel);

        //-----------------------------------//

        hello = new JLabel("Hello " + name + ", Welcome in our application!");
        hello.setBounds(37,50,645,60);
        hello.setFont(fnames);
        hello.setForeground(Color.WHITE);
        welcomePanel.add(hello);

        made = new JLabel("This application developed by :");
        made.setBounds(60,120,570,60);
        made.setFont(fnames);
        made.setForeground(Color.WHITE);
        welcomePanel.add(made);

        asmaa = new JLabel("- Asmaa Mahmoud Khalaf");
        asmaa.setBounds(70,190,570,60);
        asmaa.setFont(fn);
        asmaa.setForeground(Color.WHITE);
        welcomePanel.add(asmaa);

    }

    private void createEmployeesPanel() {

        employeePanel = new JPanel();
        employeePanel.setLayout(null);
        employeePanel.setBounds(0,0,350,800);
        employeePanel.setBackground(new Color(100,0,0,100));
        employeePanel.setVisible(false);
        add(employeePanel);

        billsE = new JButton("Making Bills");
        billsE.setBackground(new Color(255,153,153));
        billsE.setBounds(50,230,250,50);
        billsE.setFont(buttonsFont);
        billsE.setForeground(Color.white);
        billsE.setFocusPainted(false);
        billsE.setBorderPainted(false);
        billsE.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                billsE.setBackground(new Color(255,102,102));
                purchasesE.setBackground(new Color(255,153,153));
                searchE.setBackground(new Color(255,153,153));
                drugsE.setBackground(new Color(255,153,153));
                settingsE.setBackground(new Color(255,153,153));

                welcomePanel.setVisible(false);
                settingsPanel.setVisible(false);
                drugsPanel.setVisible(false);
                searchPanel.setVisible(false);
                purchasesPanel.setVisible(false);
                billPanel.setVisible(true);
            }
        });
        employeePanel.add(billsE);

        purchasesE = new JButton("Add Purchases");
        purchasesE.setBackground(new Color(255,153,153));
        purchasesE.setBounds(50,295,250,50);
        purchasesE.setFont(buttonsFont);
        purchasesE.setForeground(Color.white);
        purchasesE.setFocusPainted(false);
        purchasesE.setBorderPainted(false);
        purchasesE.addActionListener(new ActionListener (){
            public void actionPerformed(ActionEvent e){
                billsE.setBackground(new Color(255,153,153));
                purchasesE.setBackground(new Color(255,102,102));
                searchE.setBackground(new Color(255,153,153));
                drugsE.setBackground(new Color(255,153,153));
                settingsE.setBackground(new Color(255,153,153));

                welcomePanel.setVisible(false);
                settingsPanel.setVisible(false);
                drugsPanel.setVisible(false);
                searchPanel.setVisible(false);
                billPanel.setVisible(false);
                purchasesPanel.setVisible(true);
            }
        });
        employeePanel.add(purchasesE);

        searchE = new JButton("Search - UPdate");
        searchE.setBackground(new Color(255,153,153));
        searchE.setBounds(50,360,250,50);
        searchE.setFont(buttonsFont);
        searchE.setForeground(Color.white);
        searchE.setFocusPainted(false);
        searchE.setBorderPainted(false);
        searchE.addActionListener(new ActionListener (){
            public void actionPerformed(ActionEvent e){
                billsE.setBackground(new Color(255,153,153));
                purchasesE.setBackground(new Color(255,153,153));
                searchE.setBackground(new Color(255,102,102));
                drugsE.setBackground(new Color(255,153,153));
                settingsE.setBackground(new Color(255,153,153));

                welcomePanel.setVisible(false);
                settingsPanel.setVisible(false);
                drugsPanel.setVisible(false);
                billPanel.setVisible(false);
                purchasesPanel.setVisible(false);
                searchPanel.setVisible(true);
            }
        });
        employeePanel.add(searchE);

        drugsE = new JButton("Showing Drugs");
        drugsE.setBackground(new Color(255,153,153));
        drugsE.setBounds(50,425,250,50);
        drugsE.setFont(buttonsFont);
        drugsE.setForeground(Color.white);
        drugsE.setFocusPainted(false);
        drugsE.setBorderPainted(false);
        drugsE.addActionListener(new ActionListener (){
            public void actionPerformed(ActionEvent e){
                billsE.setBackground(new Color(255,153,153));
                purchasesE.setBackground(new Color(255,153,153));
                searchE.setBackground(new Color(255,153,153));
                drugsE.setBackground(new Color(255,102,102));
                settingsE.setBackground(new Color(255,153,153));

                welcomePanel.setVisible(false);
                settingsPanel.setVisible(false);
                billPanel.setVisible(false);
                purchasesPanel.setVisible(false);
                searchPanel.setVisible(false);
                drugsPanel.setVisible(true);

                model.setColumnIdentifiers(productsTableColumnNames);
                productsTable = new JTable(model);
                JScrollPane tableScroll = new JScrollPane(productsTable);
                tableScroll.setBounds(100,50,500,570);
                drugsPanel.add(tableScroll);

                //selecting data in the table
                try
                {
                    c = DriverManager.getConnection("jdbc:sqlite:my_database.sqlite");
                    System.out.println("connected with DB");
                    s = c.createStatement();
                    query = "select * from products;";
                    r = s.executeQuery(query);
                    while (r.next())
                    {
                        productsRows[0] = r.getInt("code");
                        productsRows[1] = r.getString("p_name");
                        productsRows[2] = r.getString("kind");
                        productsRows[3] = r.getInt("amount");
                        productsRows[4] = r.getFloat("price");
                        productsRows[5] = r.getString("expiry");
                        model.addRow(productsRows);
                    }

                }
                catch(SQLException ex) {
                    System.out.println("Error durring insert");
                    System.out.println(ex.getMessage());
                }
                finally{
                    try{
                        s.close();
                        r.close();
                        c.close();
                    }
                    catch(SQLException ex){
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });
        employeePanel.add(drugsE);

        settingsE = new JButton("User settings");
        settingsE.setBackground(new Color(255,153,153));
        settingsE.setBounds(50,490,250,50);
        settingsE.setFont(buttonsFont);
        settingsE.setForeground(Color.white);
        settingsE.setFocusPainted(false);
        settingsE.setBorderPainted(false);
        settingsE.addActionListener(new ActionListener (){
            public void actionPerformed(ActionEvent e){
                billsE.setBackground(new Color(255,153,153));
                purchasesE.setBackground(new Color(255,153,153));
                searchE.setBackground(new Color(255,153,153));
                drugsE.setBackground(new Color(255,153,153));
                settingsE.setBackground(new Color(255,102,102));

                welcomePanel.setVisible(false);
                billPanel.setVisible(false);
                purchasesPanel.setVisible(false);
                searchPanel.setVisible(false);
                drugsPanel.setVisible(false);
                settingsPanel.setVisible(true);
            }
        });
        employeePanel.add(settingsE);
    }

    private void createAdminPanel() {

        adminPanel = new JPanel();
        adminPanel.setLayout(null);
        adminPanel.setBounds(0,0,350,800);
        adminPanel.setBackground(new Color(170,0,0,100));
        adminPanel.setVisible(false);
        add(adminPanel);


        bills = new JButton("Making Bills");
        bills.setBackground(new Color(255,153,153));
        bills.setBounds(50,150,250,50);
        bills.setFont(buttonsFont);
        bills.setForeground(Color.white);
        bills.setFocusPainted(false);
        bills.setBorderPainted(false);
        bills.addActionListener(new ActionListener (){
            public void actionPerformed(ActionEvent e){
                bills.setBackground(new Color(255,102,102));
                purchases.setBackground(new Color(255,153,153));
                searchUpdate.setBackground(new Color(255,153,153));
                drugs.setBackground(new Color(255,153,153));
                earnings.setBackground(new Color(255,153,153));
                register.setBackground(new Color(255,153,153));
                settings.setBackground(new Color(255,153,153));

                welcomePanel.setVisible(false);
                registerPanel.setVisible(false);
                settingsPanel.setVisible(false);
                salesPurchases.setVisible(false);
                drugsPanel.setVisible(false);
                searchPanel.setVisible(false);
                purchasesPanel.setVisible(false);
                billPanel.setVisible(true);
            }
        });
        adminPanel.add(bills);

        purchases = new JButton("Add Purchases");
        purchases.setBackground(new Color(255,153,153));
        purchases.setBounds(50,220,250,50);
        purchases.setFont(buttonsFont);
        purchases.setForeground(Color.white);
        purchases.setFocusPainted(false);
        purchases.setBorderPainted(false);
        purchases.addActionListener(new ActionListener (){
            public void actionPerformed(ActionEvent e){
                bills.setBackground(new Color(255,153,153));
                purchases.setBackground(new Color(255,102,102));
                searchUpdate.setBackground(new Color(255,153,153));
                drugs.setBackground(new Color(255,153,153));
                earnings.setBackground(new Color(255,153,153));
                register.setBackground(new Color(255,153,153));
                settings.setBackground(new Color(255,153,153));

                welcomePanel.setVisible(false);
                registerPanel.setVisible(false);
                settingsPanel.setVisible(false);
                salesPurchases.setVisible(false);
                drugsPanel.setVisible(false);
                searchPanel.setVisible(false);
                billPanel.setVisible(false);
                purchasesPanel.setVisible(true);
            }
        });
        adminPanel.add(purchases);

        searchUpdate = new JButton("Search - UPdate");
        searchUpdate.setBackground(new Color(255,153,153));
        searchUpdate.setBounds(50,290,250,50);
        searchUpdate.setFont(buttonsFont);
        searchUpdate.setForeground(Color.white);
        searchUpdate.setFocusPainted(false);
        searchUpdate.setBorderPainted(false);
        searchUpdate.addActionListener(new ActionListener (){
            public void actionPerformed(ActionEvent e){
                bills.setBackground(new Color(255,153,153));
                purchases.setBackground(new Color(255,153,153));
                searchUpdate.setBackground(new Color(255,102,102));
                drugs.setBackground(new Color(255,153,153));
                earnings.setBackground(new Color(255,153,153));
                register.setBackground(new Color(255,153,153));
                settings.setBackground(new Color(255,153,153));

                welcomePanel.setVisible(false);
                registerPanel.setVisible(false);
                settingsPanel.setVisible(false);
                salesPurchases.setVisible(false);
                drugsPanel.setVisible(false);
                billPanel.setVisible(false);
                purchasesPanel.setVisible(false);
                searchPanel.setVisible(true);
            }
        });
        adminPanel.add(searchUpdate);

        drugs = new JButton("Showing Drugs");
        drugs.setBackground(new Color(255,153,153));
        drugs.setBounds(50,360,250,50);
        drugs.setFont(buttonsFont);
        drugs.setForeground(Color.white);
        drugs.setFocusPainted(false);
        drugs.setBorderPainted(false);
        drugs.addActionListener(new ActionListener (){
            public void actionPerformed(ActionEvent e){
                bills.setBackground(new Color(255,153,153));
                purchases.setBackground(new Color(255,153,153));
                searchUpdate.setBackground(new Color(255,153,153));
                drugs.setBackground(new Color(255,102,102));
                earnings.setBackground(new Color(255,153,153));
                register.setBackground(new Color(255,153,153));
                settings.setBackground(new Color(255,153,153));

                welcomePanel.setVisible(false);
                registerPanel.setVisible(false);
                settingsPanel.setVisible(false);
                salesPurchases.setVisible(false);
                billPanel.setVisible(false);
                purchasesPanel.setVisible(false);
                searchPanel.setVisible(false);
                drugsPanel.setVisible(true);

                model.setColumnIdentifiers(productsTableColumnNames);
                productsTable = new JTable(model);
                JScrollPane tableScroll = new JScrollPane(productsTable);
                tableScroll.setBounds(100,50,500,570);
                drugsPanel.add(tableScroll);

                //selecting data in the table
                try
                {
                    c = DriverManager.getConnection("jdbc:sqlite:my_database.sqlite");
                    System.out.println("connected with DB");
                    s = c.createStatement();
                    query = "select * from products;";
                    r = s.executeQuery(query);
                    while (r.next())
                    {
                        productsRows[0] = r.getInt("code");
                        productsRows[1] = r.getString("p_name");
                        productsRows[2] = r.getString("kind");
                        productsRows[3] = r.getInt("amount");
                        productsRows[4] = r.getFloat("price");
                        productsRows[5] = r.getString("expiry");
                        model.addRow(productsRows);
                    }

                }
                catch(SQLException ex) {
                    System.out.println("Error durring insert");
                    System.out.println(ex.getMessage());
                }
                finally{
                    try{
                        s.close();
                        r.close();
                        c.close();
                    }
                    catch(SQLException ex){
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });
        adminPanel.add(drugs);

        earnings = new JButton("Sales - Purchases");
        earnings.setBackground(new Color(255,153,153));
        earnings.setBounds(50,430,250,50);
        earnings.setFont(buttonsFont);
        earnings.setForeground(Color.white);
        earnings.setFocusPainted(false);
        earnings.setBorderPainted(false);
        earnings.addActionListener(new ActionListener (){
            public void actionPerformed(ActionEvent e){
                bills.setBackground(new Color(255,153,153));
                purchases.setBackground(new Color(255,153,153));
                searchUpdate.setBackground(new Color(255,153,153));
                drugs.setBackground(new Color(255,153,153));
                earnings.setBackground(new Color(255,102,102));
                register.setBackground(new Color(255,153,153));
                settings.setBackground(new Color(255,153,153));

                welcomePanel.setVisible(false);
                registerPanel.setVisible(false);
                settingsPanel.setVisible(false);
                billPanel.setVisible(false);
                purchasesPanel.setVisible(false);
                searchPanel.setVisible(false);
                drugsPanel.setVisible(false);
                salesPurchases.setVisible(true);
            }
        });
        adminPanel.add(earnings);

        settings = new JButton("User settings");
        settings.setBackground(new Color(255,153,153));
        settings.setBounds(50,500,250,50);
        settings.setFont(buttonsFont);
        settings.setForeground(Color.white);
        settings.setFocusPainted(false);
        settings.setBorderPainted(false);
        settings.addActionListener(new ActionListener (){
            public void actionPerformed(ActionEvent e){
                bills.setBackground(new Color(255,153,153));
                purchases.setBackground(new Color(255,153,153));
                searchUpdate.setBackground(new Color(255,153,153));
                drugs.setBackground(new Color(255,153,153));
                earnings.setBackground(new Color(255,153,153));
                register.setBackground(new Color(255,153,153));
                settings.setBackground(new Color(255,102,102));

                welcomePanel.setVisible(false);
                registerPanel.setVisible(false);
                billPanel.setVisible(false);
                purchasesPanel.setVisible(false);
                searchPanel.setVisible(false);
                drugsPanel.setVisible(false);
                salesPurchases.setVisible(false);
                settingsPanel.setVisible(true);
            }
        });
        adminPanel.add(settings);

        register = new JButton("User Register");
        register.setBackground(new Color(255,153,153));
        register.setBounds(50,570,250,50);
        register.setFont(buttonsFont);
        register.setForeground(Color.white);
        register.setFocusPainted(false);
        register.setBorderPainted(false);
        register.addActionListener(new ActionListener (){
            public void actionPerformed(ActionEvent e){
                bills.setBackground(new Color(255,153,153));
                purchases.setBackground(new Color(255,153,153));
                searchUpdate.setBackground(new Color(255,153,153));
                drugs.setBackground(new Color(255,153,153));
                earnings.setBackground(new Color(255,153,153));
                register.setBackground(new Color(255,102,102));
                settings.setBackground(new Color(255,153,153));

                welcomePanel.setVisible(false);
                billPanel.setVisible(false);
                purchasesPanel.setVisible(false);
                searchPanel.setVisible(false);
                drugsPanel.setVisible(false);
                salesPurchases.setVisible(false);
                settingsPanel.setVisible(false);
                registerPanel.setVisible(true);
            }
        });
        adminPanel.add(register);
    }

    private void createBillPanel() {

        billPanel = new JPanel();
        billPanel.setBounds(410, 50, 720, 660);
        billPanel.setLayout(null);
        billPanel.setVisible(false);
        billPanel.setBackground(new Color(150,0,0,100));
        add(billPanel);

        //------------------------------------------------//

        codeLabel = new JLabel("Code");
        codeLabel.setBounds(100,50,80,30);
        codeLabel.setForeground(Color.WHITE);
        codeLabel.setFont(ff);
        billPanel.add(codeLabel);

        amountLabel = new JLabel("Amount");
        amountLabel.setBounds(275,50,95,30);
        amountLabel.setForeground(Color.WHITE);
        amountLabel.setFont(ff);
        billPanel.add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(370,50,95,34);
        amountField.setFont(fff);
        billPanel.add(amountField);

        codeField = new JTextField();
        codeField.setBounds(165,50,100,34);
        codeField.setFont(fff);
        billPanel.add(codeField);

        addToBill = new JButton("Add");
        addToBill.setBounds(475,50,60,35);
        addToBill.setBackground(new Color(255,153,153));
        addToBill.setForeground(Color.WHITE);
        addToBill.setFocusPainted(false);
        addToBill.setBorderPainted(false);
        addToBill.setFont(ffff);
        addToBill.addActionListener(new ActionListener (){
            public void actionPerformed(ActionEvent e){

                try
                {
                    billCode = Integer.parseInt(codeField.getText());
                    billAmount = Integer.parseInt(amountField.getText());

                    c = DriverManager.getConnection("jdbc:sqlite:my_database.sqlite");
                    System.out.println("connected with DB");
                    s = c.createStatement();
                    query = "select * from products where code = " + billCode + ";";
                    r = s.executeQuery(query);

                    if(r.isClosed())
                    {
                        System.out.println("this product isn't exist ");
                    }
                    else
                    {
                        /* private String columnNames[] = {" Amount "," Name "," Kind"," Unit price "," Total price "};
                             private String billRow[] = new String[5]; */
                        float totalPrice = billAmount * Float.parseFloat(r.getString("price"));

                        //select ---> table
                        billRow[0] = ""+billAmount+"";
                        billRow[1] = r.getString("p_name");
                        billRow[2] = r.getString("kind");
                        billRow[3] = r.getString("price");
                        billRow[4] = ""+totalPrice+"";
                        model1.addRow(billRow);
                        System.out.println("selected successfully");

                        int newAmount = Integer.parseInt(r.getString("amount")) - billAmount;

                        //update ---> amount products
                        query = "update products set amount = "+newAmount+" where code = "+billCode+";";
                        s.execute(query);
                        System.out.println("update amount successfully");

                        //insert ---> sales
                        query = "insert into selling (p_code,amount)" +
                                "values ("+billCode+","+billAmount+");";
                        s.execute(query);
                        System.out.println("inseted in sales successfully");

                    }

                }catch(NumberFormatException ee){
                    System.out.println("this code isn't a number ");
                }
                catch(SQLException ex){
                    System.out.println("Error durring insert");
                    System.out.println(ex.getMessage());
                }
                finally{
                    try{
                        s.close();
                        r.close();
                        c.close();
                    }
                    catch(SQLException ex){
                        System.out.println(ex.getMessage());
                    }
                }
                System.out.println("");
            }
        });
        billPanel.add(addToBill);

        delete = new JButton("Delete");
        delete.setBounds(545,50,75,35);
        delete.setBackground(new Color(255,153,153));
        delete.setForeground(Color.WHITE);
        delete.setFocusPainted(false);
        delete.setBorderPainted(false);
        delete.setFont(ffff);
        delete.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {

                int i = billTable.getSelectedRow();
                if (i >= 0) {
                    // remove a row from jtable
                    model1.removeRow(i);
                }
                else {
                    System.out.println("Delete Error");
                }
            }
        });
        billPanel.add(delete);

        printBill = new JButton("Print Bill");
        printBill.setBounds(100,580,520,40);
        printBill.setBackground(new Color(255,153,153));
        printBill.setForeground(Color.WHITE);
        printBill.setFocusPainted(false);
        printBill.setBorderPainted(false);
        printBill.setFont(fff);
        billPanel.add(printBill);

        model1.setColumnIdentifiers(billTableColumnNames);
        billTable = new JTable(model1);
        JScrollPane table1Scroll = new JScrollPane(billTable);
        table1Scroll.setBounds(100,95,520,475);
        billPanel.add(table1Scroll);

    }

    private void createPurchasesPanel() {

        purchasesPanel = new JPanel();
        purchasesPanel.setBounds(410, 50, 720, 660);
        purchasesPanel.setLayout(null);
        purchasesPanel.setVisible(false);
        purchasesPanel.setBackground(new Color(150,0,0,100));
        add(purchasesPanel);

        //----------------------------------------//

        code = new JLabel("Code :");
        code.setBounds(110,150,120,40);
        code.setForeground(Color.white);
        code.setFont(ff);
        purchasesPanel.add(code);

        name = new JLabel("Name :");
        name.setBounds(110,200,120,40);
        name.setForeground(Color.white);
        name.setFont(ff);
        purchasesPanel.add(name);

        kind = new JLabel("Kind :");
        kind.setBounds(110,250,120,40);
        kind.setForeground(Color.white);
        kind.setFont(ff);
        purchasesPanel.add(kind);

        amount = new JLabel("Amount :");
        amount.setBounds(110,300,120,40);
        amount.setForeground(Color.white);
        amount.setFont(ff);
        purchasesPanel.add(amount);

        price = new JLabel("Price :");
        price.setBounds(110,350,120,40);
        price.setForeground(Color.white);
        price.setFont(ff);
        purchasesPanel.add(price);

        expiry = new JLabel("Expiry :");
        expiry.setBounds(110,400,120,40);
        expiry.setForeground(Color.white);
        expiry.setFont(ff);
        purchasesPanel.add(expiry);

        codeF = new JTextField();
        codeF.setBounds(220,150,390,40);
        codeF.setFont(fff);
        purchasesPanel.add(codeF);

        nameF = new JTextField();
        nameF.setBounds(220,200,390,40);
        nameF.setFont(fff);
        purchasesPanel.add(nameF);

        kindCbox = new JComboBox(drugKind);
        kindCbox.setBounds(220,250,390,40);
        kindCbox.setFont(fff);
        purchasesPanel.add(kindCbox);

        amountF = new JTextField();
        amountF.setBounds(220,300,390,40);
        amountF.setFont(fff);
        purchasesPanel.add(amountF);

        priceF = new JTextField();
        priceF.setBounds(220,350,390,40);
        priceF.setFont(fff);
        purchasesPanel.add(priceF);

        dayBox = new JComboBox(dayS);
        dayBox.setBounds(220,400,123,40);
        dayBox.setFont(fff);
        purchasesPanel.add(dayBox);

        monthBox = new JComboBox(monthS);
        monthBox.setBounds(353,400,124,40);
        monthBox.setFont(fff);
        purchasesPanel.add(monthBox);

        yearBox = new JComboBox(yearS);
        yearBox.setBounds(486,400,123,40);
        yearBox.setFont(fff);
        purchasesPanel.add(yearBox);

        addProduct = new JButton("Add Product");
        addProduct.setBounds(110,450,500,40);
        addProduct.setBackground(new Color(255,153,153));
        addProduct.setForeground(Color.WHITE);
        addProduct.setFocusPainted(false);
        addProduct.setBorderPainted(false);
        addProduct.setFont(fff);
        addProduct.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent e){

                try
                {
                    c = DriverManager.getConnection("jdbc:sqlite:my_database.sqlite");
                    System.out.println("connected with DB");
                    s = c.createStatement();
                    query = "select code from products where code = "+Integer.parseInt(codeF.getText())+";";
                    r = s.executeQuery(query);

                    codeInt = Integer.parseInt(codeF.getText());
                    System.out.println("entered code = "+codeInt);
                    expiryDay = Integer.parseInt(dayBox.getSelectedItem().toString());
                    expiryMonth = Integer.parseInt(monthBox.getSelectedItem().toString());
                    expiryYear = Integer.parseInt(yearBox.getSelectedItem().toString());
                    productKind = kindCbox.getSelectedItem().toString();
                    amountInt = Integer.parseInt(amountF.getText());
                    priceFloat = Float.parseFloat(priceF.getText());
                    nameString = nameF.getText();

                    if(r.isClosed())
                    {
                        //insert
                        query = "insert into products " +
                                "values ("+codeInt+",'"+nameString+"','"+productKind+"',"+amountInt+","
                                +priceFloat+",'"+expiryDay+"/"+expiryMonth+"/"+expiryYear+"');";
                        s.execute(query);

                        query = "insert into purchasing (p_code,amount)" +
                                "values ("+codeInt+","+amountInt+");";
                        s.execute(query);
                        System.out.println("inserted successfully");
                    }
                    else
                    {
                        System.out.println("this product is exist already ");
                    }

                }catch(NumberFormatException ee){
                    System.out.println("this code isn't a number ");
                }
                catch(SQLException ex){
                    System.out.println("Error durring insert");
                    System.out.println(ex.getMessage());
                }
                finally{
                    try{
                        s.close();
                        r.close();
                        c.close();
                    }
                    catch(SQLException ex){
                        System.out.println(ex.getMessage());
                    }
                }
                System.out.println("");
            }
        });
        purchasesPanel.add(addProduct);

    }

    private void createSearchPanel() {

        searchPanel = new JPanel();
        searchPanel.setBounds(410, 50, 720, 660);
        searchPanel.setLayout(null);
        searchPanel.setVisible(false);
        searchPanel.setBackground(new Color(150,0,0,100));
        add(searchPanel);

        //-------------------------------------------//

        codeS = new JLabel("Code  :");
        codeS.setBounds(110,150,80,40);
        codeS.setForeground(Color.WHITE);
        codeS.setFont(ff);
        searchPanel.add(codeS);

        nameS = new JLabel("Name :");
        nameS.setBounds(110,205,120,40);
        nameS.setForeground(Color.WHITE);
        nameS.setFont(ff);
        searchPanel.add(nameS);

        amountS = new JLabel("Amount :");
        amountS.setBounds(110,260,120,40);
        amountS.setForeground(Color.WHITE);
        amountS.setFont(ff);
        searchPanel.add(amountS);

        priceS = new JLabel("Price :");
        priceS.setBounds(110,315,120,40);
        priceS.setForeground(Color.WHITE);
        priceS.setFont(ff);
        searchPanel.add(priceS);

        expiryS = new JLabel("Expiry :");
        expiryS.setBounds(110,370,120,40);
        expiryS.setForeground(Color.WHITE);
        expiryS.setFont(ff);
        searchPanel.add(expiryS);

        codeFs = new JTextField();
        codeFs.setBounds(220,150,250,40);
        codeFs.setFont(fff);
        searchPanel.add(codeFs);

        nameFs = new JTextField();
        nameFs.setBounds(220,205,380,40);
        nameFs.setFont(fff);
        searchPanel.add(nameFs);

        amountFs = new JTextField();
        amountFs.setBounds(220,260,380,40);
        amountFs.setFont(fff);
        searchPanel.add(amountFs);

        priceFs = new JTextField();
        priceFs.setBounds(220,315,380,40);
        priceFs.setFont(fff);
        searchPanel.add(priceFs);

        expiryFs = new JTextField();
        expiryFs.setBounds(220,370,380,40);
        expiryFs.setFont(fff);
        searchPanel.add(expiryFs);

        search = new JButton("Search");
        search.setBounds(480,150,120,40);
        search.setBackground(new Color(255,153,153));
        search.setForeground(Color.WHITE);
        search.setFocusPainted(false);
        search.setBorderPainted(false);
        search.setFont(fff);
        search.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                try
                {
                    codeInt = Integer.parseInt(codeFs.getText());

                    c = DriverManager.getConnection("jdbc:sqlite:my_database.sqlite");
                    System.out.println("connected with DB");
                    s = c.createStatement();
                    query = "select * from products where code = "+Integer.parseInt(codeFs.getText())+";";
                    r = s.executeQuery(query);

                    if(r.isClosed())
                    {
                        System.out.println("this product isn't exist in the database ");
                    }
                    else
                    {
                        nameFs.setText(r.getString("p_name"));
                        amountFs.setText(r.getString("amount"));
                        priceFs.setText(r.getString("price"));
                        expiryFs.setText(r.getString("expiry"));
                        System.out.println("selected successfully");
                    }

                }catch(NumberFormatException ee){
                    System.out.println(ee.getMessage());
                    System.out.println("this code isn't a number ");
                }
                catch(SQLException ex){
                    System.out.println("Error durring insert");
                    System.out.println(ex.getMessage());
                }
                finally{
                    try{
                        s.close();
                        r.close();
                        c.close();
                    }
                    catch(SQLException ex){
                        System.out.println(ex.getMessage());
                    }
                }
                System.out.println("");
            }
        });
        searchPanel.add(search);

        update = new JButton("UPdate");
        update.setBounds(110,425,240,40);
        update.setBackground(new Color(255,153,153));
        update.setForeground(Color.WHITE);
        update.setFocusPainted(false);
        update.setBorderPainted(false);
        update.setFont(fff);
        update.addActionListener(new ActionListener (){
            public void actionPerformed(ActionEvent e){


                try
                {
                    codeInt = Integer.parseInt(codeFs.getText());
                    System.out.println("code = "+codeFs.getText());
                    amountInt = Integer.parseInt(amountFs.getText());
                    System.out.println("amount = "+amountFs.getText());
                    priceFloat = Float.parseFloat(priceFs.getText());
                    System.out.println("price = "+priceFs.getText());
                    nameString = nameFs.getText();
                    System.out.println("name = "+nameFs.getText());
                    expirydate = expiryFs.getText();
                    System.out.println("expiry = "+expiryFs.getText());

                    c = DriverManager.getConnection("jdbc:sqlite:my_database.sqlite");
                    System.out.println("connected with DB");
                    s = c.createStatement();
                    query = "select * from products where code = "+Integer.parseInt(codeFs.getText())+";";
                    r = s.executeQuery(query);

                    if(r.isClosed())
                    {
                        System.out.println("please enter a valid data");
                    }
                    else
                    {
                        query = "update products set p_name = '"+nameString+"' where code = "+codeInt+";";
                        s.execute(query);
                        query = "update products set amount = '"+amountInt+"' where code = "+codeInt+";";
                        s.execute(query);
                        query = "update products set price = '"+priceFloat+"' where code = "+codeInt+";";
                        s.execute(query);
                        query = "update products set expiry = '"+expiryDay+"/"+expiryMonth+"/"+expiryYear+"' where code = "+codeInt+";";
                        s.execute(query);
                        nameFs.setText("");
                        amountFs.setText("");
                        priceFs.setText("");
                        expiryFs.setText("");
                        System.out.println("updated successfully");
                    }

                }catch(NumberFormatException ee){
                    System.out.println("this code isn't a number ");
                }
                catch(SQLException ex){
                    System.out.println("Error durring insert");
                    System.out.println(ex.getMessage());
                }
                finally{
                    try{
                        s.close();
                        r.close();
                        c.close();
                    }
                    catch(SQLException ex){
                        System.out.println(ex.getMessage());
                    }
                }
                System.out.println("");
            }
        });
        searchPanel.add(update);

        deleteS = new JButton("Delete");
        deleteS.setBounds(360,425,240,40);
        deleteS.setBackground(new Color(255,153,153));
        deleteS.setForeground(Color.WHITE);
        deleteS.setFocusPainted(false);
        deleteS.setBorderPainted(false);
        deleteS.setFont(fff);
        deleteS.addActionListener(new ActionListener (){
            public void actionPerformed(ActionEvent e){


                try
                {
                    codeInt = Integer.parseInt(codeFs.getText());

                    c = DriverManager.getConnection("jdbc:sqlite:my_database.sqlite");
                    System.out.println("connected with DB");
                    s = c.createStatement();
                    query = "select * from products where code = "+Integer.parseInt(codeFs.getText())+";";
                    r = s.executeQuery(query);

                    if(r.isClosed())
                    {
                        System.out.println("this product isn't exist ");
                    }
                    else
                    {
                        query = "delete from products where code = "+Integer.parseInt(codeFs.getText())+";";
                        s.execute(query);
                        nameFs.setText("");
                        amountFs.setText("");
                        priceFs.setText("");
                        expiryFs.setText("");
                        System.out.println("deleted successfully");
                    }

                }catch(NumberFormatException ee){
                    System.out.println("this code isn't a number ");
                }
                catch(SQLException ex){
                    System.out.println("Error durring insert");
                    System.out.println(ex.getMessage());
                }
                finally{
                    try{
                        s.close();
                        r.close();
                        c.close();
                    }
                    catch(SQLException ex){
                        System.out.println(ex.getMessage());
                    }
                }
                System.out.println("");
            }
        });
        searchPanel.add(deleteS);

    }

    private void createDrugsPanel() {

        drugsPanel = new JPanel();
        drugsPanel.setBounds(410, 50, 720, 660);
        drugsPanel.setLayout(null);
        drugsPanel.setVisible(false);
        drugsPanel.setBackground(new Color(150,0,0,100));
        add(drugsPanel);

    }

    private void createSalesPurchasesPanel() {

        salesPurchases = new JPanel();
        salesPurchases.setBounds(410, 50, 720, 660);
        salesPurchases.setLayout(null);
        salesPurchases.setVisible(false);
        salesPurchases.setBackground(new Color(150,0,0,100));
        add(salesPurchases);

        //---------------------------------------//

        date = new JLabel("Date :");
        date.setBounds(100,37,150,40);
        date.setFont(ff);
        date.setForeground(Color.WHITE);
        salesPurchases.add(date);

        quaryKind = new JLabel("Quary Kind :");
        quaryKind.setBounds(100,80,150,40);
        quaryKind.setFont(ff);
        quaryKind.setForeground(Color.WHITE);
        salesPurchases.add(quaryKind);

        day = new JComboBox(dayS);
        day.setFont(fff);
        day.setBounds(175,40,135,30);
        salesPurchases.add(day);

        month = new JComboBox(monthS);
        month.setFont(fff);
        month.setBounds(320,40,135,30);
        salesPurchases.add(month);

        year = new JComboBox(yearS);
        year.setFont(fff);
        year.setBounds(465,40,135,30);
        salesPurchases.add(year);

        QKCB = new JComboBox(kindQuary);
        QKCB.setFont(fff);
        QKCB.setBounds(255,80,200,40);
        salesPurchases.add(QKCB);

        JButton show = new JButton("show");
        show.setBounds(465,80,135,40);
        show.setBackground(new Color(255,153,153));
        show.setForeground(Color.WHITE);
        show.setFocusPainted(false);
        show.setBorderPainted(false);
        show.setFont(fff);
        show.addActionListener(new ActionListener (){
            public void actionPerformed(ActionEvent e){

                try
                {
                    expiryDay = Integer.parseInt(dayBox.getSelectedItem().toString());
                    expiryMonth = Integer.parseInt(monthBox.getSelectedItem().toString());
                    expiryYear = Integer.parseInt(yearBox.getSelectedItem().toString());
                    selectedKind = QKCB.getSelectedItem().toString();

                    c = DriverManager.getConnection("jdbc:sqlite:my_database.sqlite");
                    System.out.println("connected with DB");
                    s = c.createStatement();

                    if(selectedKind == "sales")
                        query = "select code from products where code = "+Integer.parseInt(codeF.getText())+";";
                    r = s.executeQuery(query);

                    if(r.isClosed())
                    {
                        //insert
                        query = "insert into products " +
                                "values ("+codeInt+",'"+nameString+"','"+productKind+"',"+amountInt+","
                                +priceFloat+",'"+expiryDay+"/"+expiryMonth+"/"+expiryYear+"');";
                        s.execute(query);

                        query = "insert into purchasing (p_code,amount)" +
                                "values ("+codeInt+","+amountInt+");";
                        s.execute(query);
                        System.out.println("inserted successfully");
                    }
                    else
                    {
                        System.out.println("this product is exist already ");
                    }

                }catch(NumberFormatException ee){
                    System.out.println("this code isn't a number ");
                }
                catch(SQLException ex){
                    System.out.println("Error durring insert");
                    System.out.println(ex.getMessage());
                }
                finally{
                    try{
                        s.close();
                        r.close();
                        c.close();
                    }
                    catch(SQLException ex){
                        System.out.println(ex.getMessage());
                    }
                }
                System.out.println("");
            }
        });
        salesPurchases.add(show);

        model2.setColumnIdentifiers(billTableColumnNames);
        SPtable = new JTable(model2);
        JScrollPane tableScroll2 = new JScrollPane(SPtable);
        tableScroll2.setBounds(100,130,500,485);
        salesPurchases.add(tableScroll2);

    }

    private void createSettingsPanel() {

        settingsPanel = new JPanel();
        settingsPanel.setBounds(410, 50, 720, 660);
        settingsPanel.setLayout(null);
        settingsPanel.setVisible(false);
        settingsPanel.setBackground(new Color(150,0,0,100));
        add(settingsPanel);

        //------------------------------------------//

        enter = new JLabel("Enter your password :");
        enter.setFont(ff);
        enter.setForeground(Color.WHITE);
        enter.setBounds(100,200,300,40);
        settingsPanel.add(enter);

        fnLabel = new JLabel("First name :");
        fnLabel.setFont(ff);
        fnLabel.setForeground(Color.WHITE);
        fnLabel.setBounds(100,250,200,40);
        settingsPanel.add(fnLabel);

        lnLabel = new JLabel("Last name :");
        lnLabel.setFont(ff);
        lnLabel.setForeground(Color.WHITE);
        lnLabel.setBounds(100,300,200,40);
        settingsPanel.add(lnLabel);

        unLabel = new JLabel("Username :");
        unLabel.setFont(ff);
        unLabel.setForeground(Color.WHITE);
        unLabel.setBounds(100,350,200,40);
        settingsPanel.add(unLabel);

        pLabel = new JLabel("Password :");
        pLabel.setFont(ff);
        pLabel.setForeground(Color.WHITE);
        pLabel.setBounds(100,400,200,40);
        settingsPanel.add(pLabel);

        fnF = new JTextField();
        fnF.setFont(fff);
        fnF.setBounds(250,250,230,40);
        settingsPanel.add(fnF);

        lnF = new JTextField();
        lnF.setFont(fff);
        lnF.setBounds(250,300,230,40);
        settingsPanel.add(lnF);

        unF = new JTextField();
        unF.setFont(fff);
        unF.setBounds(250,350,230,40);
        settingsPanel.add(unF);

        enterF = new JPasswordField();
        enterF.setFont(fff);
        enterF.setBounds(360,200,180,40);
        settingsPanel.add(enterF);

        pF = new JPasswordField();
        pF.setFont(fff);
        pF.setBounds(250,400,230,40);
        settingsPanel.add(pF);

        infoButton = new JButton("Info");
        infoButton.setBounds(550,200,60,40);
        infoButton.setBackground(new Color(255,153,153));
        infoButton.setForeground(Color.WHITE);
        infoButton.setFocusPainted(false);
        infoButton.setBorderPainted(false);
        infoButton.setFont(ffff);
        infoButton.addActionListener(new ActionListener (){
            public void actionPerformed(ActionEvent e){

                enteredPassword = enterF.getText();
                System.out.println("the entered password = "+enteredPassword);

                try
                {
                    c = DriverManager.getConnection("jdbc:sqlite:my_database.sqlite");
                    System.out.println("connected with DB");

                    if(enteredPassword.equals(Password))
                    {
                        query = "select * from users where password = '"+enteredPassword+"';";
                        s = c.createStatement();
                        r = s.executeQuery(query);
                        fnF.setText(r.getString("fn"));
                        System.out.println("first name = "+r.getString("fn"));
                        lnF.setText(r.getString("ln"));
                        System.out.println("last name = "+r.getString("ln"));
                        unF.setText(r.getString("username"));
                        System.out.println("user name = "+r.getString("username"));
                        pF.setText(r.getString("password"));
                        System.out.println("pass = "+r.getString("password"));
                        System.out.println("info selected successfully");
                    }
                    else
                    {
                        System.out.println("the entered password isn't correct");
                        fnF.setText("");
                        lnF.setText("");
                        unF.setText("");
                        pF.setText("");
                    }

                }catch(SQLException ex){
                    System.out.println("Error durring select");
                    System.out.println(ex.getMessage());
                }
                finally{
                    try{
                        s.close();
                        r.close();
                        c.close();
                    }
                    catch(SQLException ex){
                        System.out.println(ex.getMessage());
                    }
                }
                System.out.println("");
            }
        });
        settingsPanel.add(infoButton);

        fnButton = new JButton("update");
        fnButton.setBounds(490,250,120,40);
        fnButton.setBackground(new Color(255,153,153));
        fnButton.setForeground(Color.WHITE);
        fnButton.setFocusPainted(false);
        fnButton.setBorderPainted(false);
        fnButton.setFont(fff);
        fnButton.addActionListener(new ActionListener (){
            public void actionPerformed(ActionEvent e){
                //enteredPassword = "", uFn = "", uLn = "", uUn = "",uP = "";

                uFn = fnF.getText();
                try
                {
                    if(uFn.equals(""))
                    {
                        System.out.println("your first name can't be empty");

                    }
                    else
                    {
                        c = DriverManager.getConnection("jdbc:sqlite:my_database.sqlite");
                        System.out.println("connected with DB");
                        query = "update users set fn = '"+uFn+"' where password = '"+enteredPassword+"';";
                        s = c.createStatement();
                        s.execute(query);
                        System.out.println("updated successfully = "+uFn);
                    }

                }catch(SQLException ex){
                    System.out.println("Error durring update");
                    System.out.println(ex.getMessage());
                }
                finally{
                    try{
                        s.close();
                        r.close();
                        c.close();
                    }
                    catch(SQLException ex){
                        System.out.println(ex.getMessage());
                    }
                }
                System.out.println("");
            }
        });
        settingsPanel.add(fnButton);

        lnButton = new JButton("update");
        lnButton.setBounds(490,300,120,40);
        lnButton.setBackground(new Color(255,153,153));
        lnButton.setForeground(Color.WHITE);
        lnButton.setFocusPainted(false);
        lnButton.setBorderPainted(false);
        lnButton.setFont(fff);
        lnButton.addActionListener(new ActionListener (){
            public void actionPerformed(ActionEvent e){
                //enteredPassord = "", uFn = "", uLn = "", uUn = "",uP = "";

                uLn = lnF.getText();
                try
                {
                    if(uLn.equals(""))
                    {
                        System.out.println("your last name can't be empty");

                    }
                    else
                    {
                        c = DriverManager.getConnection("jdbc:sqlite:my_database.sqlite");
                        System.out.println("connected with DB");
                        query = "update users set ln = '"+uLn+"' where password = '"+enteredPassword+"';";
                        s = c.createStatement();
                        s.execute(query);
                        System.out.println("updated successfully = "+uLn);
                    }

                }catch(SQLException ex){
                    System.out.println("Error durring update");
                    System.out.println(ex.getMessage());
                }
                finally{
                    try{
                        s.close();
                        r.close();
                        c.close();
                    }
                    catch(SQLException ex){
                        System.out.println(ex.getMessage());
                    }
                }
                System.out.println("");
            }
        });
        settingsPanel.add(lnButton);

        unButton = new JButton("update");
        unButton.setBounds(490,350,120,40);
        unButton.setBackground(new Color(255,153,153));
        unButton.setForeground(Color.WHITE);
        unButton.setFocusPainted(false);
        unButton.setBorderPainted(false);
        unButton.setFont(fff);
        unButton.addActionListener(new ActionListener (){
            public void actionPerformed(ActionEvent e){

                uUn = unF.getText();
                try
                {
                    c = DriverManager.getConnection("jdbc:sqlite:my_database.sqlite");
                    System.out.println("connected with DB");
                    query = "select * from users where username = '"+uUn+"';";
                    s = c.createStatement();
                    r = s.executeQuery(query);
                    if(uFn.equals(""))
                    {
                        System.out.println("your user name can't be empty");

                    }
                    else if(r.isClosed())
                    {
                        query = "update users set username = '"+uUn+"' where password = '"+enteredPassword+"';";
                        s = c.createStatement();
                        s.execute(query);
                        System.out.println("updated successfully = "+uUn);
                    }
                    else
                    {
                        System.out.println("this user name exist already in the database");
                    }

                }catch(SQLException ex){
                    System.out.println("Error durring update");
                    System.out.println(ex.getMessage());
                }
                finally{
                    try{
                        s.close();
                        r.close();
                        c.close();
                    }
                    catch(SQLException ex){
                        System.out.println(ex.getMessage());
                    }
                }
                System.out.println("");
            }
        });
        settingsPanel.add(unButton);

        pButton = new JButton("update");
        pButton.setBounds(490,400,120,40);
        pButton.setBackground(new Color(255,153,153));
        pButton.setForeground(Color.WHITE);
        pButton.setFocusPainted(false);
        pButton.setBorderPainted(false);
        pButton.setFont(fff);
        pButton.addActionListener(new ActionListener (){
            public void actionPerformed(ActionEvent e){
                uP = pF.getText();
                try
                {
                    c = DriverManager.getConnection("jdbc:sqlite:my_database.sqlite");
                    System.out.println("connected with DB");
                    query = "select * from users where password = '"+uP+"';";
                    s = c.createStatement();
                    r = s.executeQuery(query);
                    if(uP.equals(""))
                    {
                        System.out.println("your password can't be empty");

                    }
                    else if(r.isClosed())
                    {
                        query = "update users set password = '"+uP+"' where password = '"+enteredPassword+"';";
                        s = c.createStatement();
                        s.execute(query);
                        System.out.println("updated successfully = "+uP);
                    }
                    else
                    {
                        System.out.println("this password exist already in the database");
                    }

                }catch(SQLException ex){
                    System.out.println("Error durring update");
                    System.out.println(ex.getMessage());
                }
                finally{
                    try{
                        s.close();
                        r.close();
                        c.close();
                    }
                    catch(SQLException ex){
                        System.out.println(ex.getMessage());
                    }
                }
                System.out.println("");
            }
        });
        settingsPanel.add(pButton);
    }

    private void createRegisterPanel() {

        registerPanel = new JPanel();
        registerPanel.setBounds(410, 50, 720, 660);
        registerPanel.setLayout(null);
        registerPanel.setVisible(false);
        registerPanel.setBackground(new Color(150,0,0,100));
        add(registerPanel);

        //---------------------------------------//

        fN = new JLabel("First name :");
        fN.setFont(ff);
        fN.setForeground(Color.WHITE);
        fN.setBounds(150,150,150,40);
        registerPanel.add(fN);

        ln = new JLabel("Last name :");
        ln.setFont(ff);
        ln.setForeground(Color.WHITE);
        ln.setBounds(150,200,150,40);
        registerPanel.add(ln);

        userkind = new JLabel("User kind :");
        userkind.setFont(ff);
        userkind.setForeground(Color.WHITE);
        userkind.setBounds(150,250,150,40);
        registerPanel.add(userkind);

        lusername = new JLabel("Username :");
        lusername.setFont(ff);
        lusername.setForeground(Color.WHITE);
        lusername.setBounds(150,300,150,40);
        registerPanel.add(lusername);

        lpassword = new JLabel("Password :");
        lpassword.setFont(ff);
        lpassword.setForeground(Color.WHITE);
        lpassword.setBounds(150,350,150,40);
        registerPanel.add(lpassword);

        fnTf = new JTextField();
        fnTf.setBounds(300,150,270,40);
        registerPanel.add(fnTf);

        lnTf = new JTextField();
        lnTf.setBounds(300,200,270,40);
        registerPanel.add(lnTf);

        cb = new JComboBox(uk);
        cb.setBounds(300,250,270,40);
        registerPanel.add(cb);

        reunField = new JTextField();
        reunField.setBounds(300,300,270,40);
        registerPanel.add(reunField);

        repasswordField = new JPasswordField();
        repasswordField.setBounds(300,350,270,40);
        registerPanel.add(repasswordField);

        reg = new JButton("Register");
        reg.setBounds(150,400,420,45);
        reg.setBackground(new Color(255,153,153));
        reg.setForeground(Color.WHITE);
        reg.setFocusPainted(false);
        reg.setBorderPainted(false);
        reg.setFont(fff);
        reg.addActionListener(new ActionListener (){
            public void actionPerformed(ActionEvent e){

                firstName = fnTf.getText();
                System.out.println("first name = "+firstName);
                secondName = lnTf.getText();
                System.out.println("second name = "+secondName);
                userk = cb.getSelectedItem().toString();
                System.out.println("user kind = "+userk);
                userN = reunField.getText();
                System.out.println("user name = "+userN);
                rePassword = repasswordField.getText();
                System.out.println("password = "+rePassword);

                try
                {

                    c = DriverManager.getConnection("jdbc:sqlite:my_database.sqlite");
                    System.out.println("connected with DB");
                    s = c.createStatement();
                    query = "select * from users where username = '"+userN+"';";
                    r = s.executeQuery(query);
                    if(firstName.equals("")||secondName.equals("")||userk.equals("")||userN.equals("")||rePassword.equals(""))
                    {
                        System.out.println("you can't enter a null value in your info during registeration");

                    }
                    else if(r.isClosed())
                    {
                        query = "select * from users where password = '"+rePassword+"';";
                        r = s.executeQuery(query);
                        if(r.isClosed())
                        {
                            query = "insert into users " +
                                    "values ('"+fnTf.getText()+"','"+lnTf.getText()+"','"+
                                    cb.getSelectedItem().toString()+"','"+reunField.getText()+
                                    "','"+repasswordField.getText()+"');";
                            s.execute(query);
                            System.out.println("registered successfully");
                        }
                        else{
                            System.out.println("this password is exist already in the database");

                        }
                    }
                    else{
                        System.out.println("this username is exist already in the database");

                    }

                }catch(SQLException ex){
                    System.out.println("Error durring register");
                    System.out.println(ex.getMessage());
                }
                finally{
                    try{
                        s.close();
                        r.close();
                        c.close();
                    }
                    catch(SQLException ex){
                        System.out.println(ex.getMessage());
                    }
                }

                fnTf.setText("");
                lnTf.setText("");
                reunField.setText("");
                repasswordField.setText("");
            }
        });
        registerPanel.add(reg);
    }

}
