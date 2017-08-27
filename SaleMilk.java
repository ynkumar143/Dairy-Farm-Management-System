/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dairyvsr;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Puppy
 */
public class SaleMilk extends javax.swing.JFrame {
     String driver = "org.apache.derby.jdbc.ClientDriver";
        String host="jdbc:derby://localhost:1527/D:/Dairy_Database/.netbeans-derby/DairyDatabase;user=admin;password=admin";
         final DateFormat timeFormat=new SimpleDateFormat("HH:mm:ss");
         DefaultTableModel dtm=new DefaultTableModel(0,0);
          final DecimalFormat df = new DecimalFormat("#.##");
          final String DATE_FORMAT_NOW="yyyy-MM-dd";
Connection con,con1,con2,con3,con4;
Statement stmt,stmt1,stmt2,stmt3,stmt4;
ResultSet res,res1,res2,res3,res4;


    /**
     * Creates new form SaleMilk
     */
    public SaleMilk() {
        initComponents();
        lblday.setVisible(false);
        displaydate();
        Function();
        countdatabase();
    }
     public void CustomerSalesName1()
    {
            try{
                     con3=DriverManager.getConnection(host);
                     stmt3=con3.createStatement();
                     java.sql.Timestamp sqlDate= new java.sql.Timestamp(new java.util.Date().getTime());
                     stmt3.executeQuery("SELECT CARD_NUM,CUST_NAME FROM CUSTOMERS where CARD_NUM="+txtcardn0_sales1.getText()); 
                     res3=stmt3.getResultSet();
                             if(res3.next())
                                 {  
                                         String name2=res3.getString("CUST_NAME");
                                         txtname_sales1.setText(name2);
                                         txtname_sales1.setToolTipText("Editing Possible in Registration Page"); 
                                       if(name2.equals(""))
                                       {
                                          
                                           txtname_sales1.setText("Name is empty");
                                       }
                                 }
                             else
                             {
                                 
                                 txtname_sales1.setText("");
                                 
                             }
                              res3.close();
                     stmt3.close();
                    
                     con3.close();
                  
                }
             catch(SQLException err)
                 {
                     JOptionPane.showMessageDialog(SaleMilk.this,err);
                    }
            }  
     class ObservingTextField extends JTextField implements Observer {
    public void update(Observable o, Object arg) {
        final String DATE_FORMAT_NOW="yyyy-MM-dd";
        Calendar calendar = (Calendar) arg;
        SimpleDateFormat sdf=new SimpleDateFormat(DATE_FORMAT_NOW);
        
        DatePicker dp = (DatePicker) o;
        txtDate.setText(sdf.format(calendar.getTime()));
       // System.out.println(sdf.format(calendar.getTime()));
       // System.out.println("picked=" + dp.formatDate(calendar));
       // txtDate_Reg.setText(dp.formatDate(calendar));
    }
   }
    public void CustomerSalesName()
    {
            try{
                     con2=DriverManager.getConnection(host);
                     stmt2=con2.createStatement();
                     java.sql.Timestamp sqlDate= new java.sql.Timestamp(new java.util.Date().getTime());
                     stmt2.executeQuery("SELECT CARD_NUM,CUST_NAME FROM CUSTOMERS where CARD_NUM="+txtcardn0_sales.getText()); 
                     res2=stmt2.getResultSet();
                             if(res2.next())
                                 {  
                                         String name2=res2.getString("CUST_NAME");
                                         txtname_sales.setText(name2);
                                         txtname_sales.setToolTipText("Editing Possible in Registration Page"); 
                                       if(name2.equals(""))
                                       {
                                          
                                           txtname_sales.setText("Name is empty");
                                       }
                                 }
                             else
                             {
                                 
                                 txtname_sales.setText("");
                                 
                             }
                              res2.close();
                     stmt2.close();
                    
                     con2.close();
                  
                }
             catch(SQLException err)
                 {
                     JOptionPane.showMessageDialog(SaleMilk.this,err);
                    }
            }  
     public void displaydate()
    {
         Calendar cal=Calendar.getInstance();
            SimpleDateFormat sdf=new SimpleDateFormat(DATE_FORMAT_NOW);
            //txttodate.setText(sdf.format(cal.getTime()));
            txtDate.setText(sdf.format(cal.getTime()));
    }
    public void Function()
    {
         Date date=new Date();
         String time=timeFormat.format(date);
  
         String startmrng="00:00:00";
         String endmrng="11:59:59";
         String startevng="12:00:00";
         String endevng="23:59:59";
         String nagendra="kumar";
         String kumar="kumakr";
      
    if((startmrng.compareTo(time)<0)&&(endmrng.compareTo(time)>0))
    {
         cmbevent.setSelectedItem("Morning"); 
    }
    else
    {
         cmbevent.setSelectedItem("Evening");
    }   
    }
      public void salesorgive()
   {
       
        try{
             con1=DriverManager.getConnection(host);
             stmt1=con1.createStatement();
             java.sql.Timestamp sqlDate= new java.sql.Timestamp(new java.util.Date().getTime());
             stmt1.executeUpdate("insert into DAILYSALES(CARD_NUM,SALES_NAME,MILK,MONEY,RECIEVED,DATE,SESSION,TIMESTAMP) values("+txtcardn0_sales.getText()+",'"+txtname_sales.getText()+"','"+txtmilk_sales.getText()+"','"+txtmoney_sales.getText()+"','"+cmbrecieved.getSelectedItem()+"','"+txtDate.getText()+"','"+cmbevent.getSelectedItem()+"','"+sqlDate+"')");
             JOptionPane.showMessageDialog(SaleMilk.this,"Milk Successfully Sold");
             txtmilk_sales.setText("");
             txtcardn0_sales.setText("");
             cmbrecieved.setSelectedItem(" ");
             cmbrecieved.setEnabled(false);
             txtmoney_sales.setText("");
             txtname_sales.setText("");
             stmt1.close();
             con1.close();
        }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(SaleMilk.this,err);
        }
   }
      
      public void salesorgivetogive()
   {
       
        try{
             con1=DriverManager.getConnection(host);
             stmt1=con1.createStatement();
             java.sql.Timestamp sqlDate= new java.sql.Timestamp(new java.util.Date().getTime());
             stmt1.executeUpdate("insert into DAILYSALES(CARD_NUM,SALES_NAME,MILK,MONEY,RECIEVED,DATE,SESSION,TIMESTAMP) values("+txtcardn0_sales1.getText()+",'"+txtname_sales1.getText()+"','0','"+txtmoney_sales1.getText()+"','"+cmbrecieved1.getSelectedItem()+"','"+txtDate.getText()+"','"+cmbevent.getSelectedItem()+"','"+sqlDate+"')");
             JOptionPane.showMessageDialog(SaleMilk.this,"Milk Successfully Sold");
            // txtmilk_sales.setText("");
             txtcardn0_sales1.setText("");
             cmbrecieved1.setSelectedItem(" ");
             cmbrecieved1.setEnabled(false);
             txtmoney_sales1.setText("");
             txtname_sales1.setText("");
             stmt1.close();
             con1.close();
        }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(SaleMilk.this,err);
        }
   }
    public void countdatabase()
     {
         try
         {
          con=DriverManager.getConnection(host);
          stmt=con.createStatement();
                  //   int srow=tbldata.getSelectedRow();
                  //   int scol=tbldata.getSelectedColumn();
                     
                     //java.sql.Timestamp sqlDate= new java.sql.Timestamp(new java.util.Date().getTime());
                     stmt.executeQuery("SELECT * FROM Data_Logic WHERE ID=2"); 
                     res=stmt.getResultSet();
                    // int cound=0;
                     while(res.next())
                     {
                         String  moneyval=res.getString("D_Val");
                       //  Double moneyvalue1=Double.parseDouble(moneyval);
                       //  cound=cound+1;
                        // String counds=Integer.toString(cound);
                         lblday.setText(moneyval);
                     }
                        }
                     catch(SQLException err)
                     {
                         JOptionPane.showMessageDialog(SaleMilk.this,err);
                     }
     }
 public void milktomoneysales()
{
    txtmoney_sales.setText("");
    String milk_sales1=txtmilk_sales.getText();
    Double milksales2=Double.parseDouble(milk_sales1);
    String milk_vald=lblday.getText();
    Double milkvald=Double.parseDouble(milk_vald);
    Double result=(milkvald)*milksales2;
    df.format(result);
    //String result_money=Double.toString(result);
    String result_final=df.format(result);
    txtmoney_sales.setText(result_final);  
    cmbrecieved.setEnabled(true);
    cmbrecieved.setSelectedIndex(0);
    
        
         }
public void moneytomilksales()
{
    

//    txtmilk_sales.setText("");
//    String money_sales=txtmoney_sales.getText();
//    Double moneysales2=Double.parseDouble(money_sales);
//    Double result1=(0.025)*moneysales2;
//    String resultmilk=Double.toString(result1);
//    txtmilk_sales.setText(resultmilk);  
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblday = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        cmbevent = new javax.swing.JComboBox();
        datepane = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        txtcardn0_sales = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtname_sales = new javax.swing.JTextField();
        txtmilk_sales = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtmoney_sales = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        cmbrecieved = new javax.swing.JComboBox();
        jLabel40 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtcardn0_sales1 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtname_sales1 = new javax.swing.JTextField();
        txtmoney_sales1 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        cmbrecieved1 = new javax.swing.JComboBox();
        jLabel41 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1375, 700));

        lblday.setText("Count");

        jLabel2.setFont(new java.awt.Font("Monotype Corsiva", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setText("Milk Sales and Customer Given Money");

        jLabel4.setFont(new java.awt.Font("Traditional Arabic", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 102, 102));
        jLabel4.setText("Select Date & Session:");

        txtDate.setFont(new java.awt.Font("Traditional Arabic", 1, 18)); // NOI18N
        txtDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDateMouseClicked(evt);
            }
        });
        txtDate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDateFocusLost(evt);
            }
        });

        cmbevent.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        cmbevent.setForeground(new java.awt.Color(255, 51, 51));
        cmbevent.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Morning", "Evening" }));
        cmbevent.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbeventItemStateChanged(evt);
            }
        });

        datepane.setBackground(new java.awt.Color(204, 204, 255));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        txtcardn0_sales.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtcardn0_sales.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtcardn0_salesFocusLost(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 0, 51));
        jLabel23.setText("Card N0:");

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(51, 0, 51));
        jLabel27.setText("Name:");

        txtname_sales.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txtmilk_sales.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtmilk_sales.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtmilk_salesFocusLost(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 0, 51));
        jLabel25.setText("MIlk:");

        txtmoney_sales.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtmoney_sales.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtmoney_salesFocusLost(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 0, 51));
        jLabel26.setText("Money:");

        cmbrecieved.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmbrecieved.setForeground(new java.awt.Color(255, 51, 51));
        cmbrecieved.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "YES", "NO" }));

        jLabel40.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(51, 0, 51));
        jLabel40.setText("Received:");

        jButton6.setBackground(new java.awt.Color(153, 255, 153));
        jButton6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(51, 0, 0));
        jButton6.setText("SALE");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6jButton5ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Monotype Corsiva", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 102));
        jLabel1.setText("Milk Sales");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel27)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel26))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtmoney_sales, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                                    .addComponent(txtname_sales)
                                    .addComponent(txtcardn0_sales)
                                    .addComponent(txtmilk_sales)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel40)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                .addComponent(cmbrecieved, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(jLabel1)))
                .addGap(0, 32, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(jButton6)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtcardn0_sales, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtname_sales, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtmilk_sales, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtmoney_sales, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(cmbrecieved, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        txtcardn0_sales1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtcardn0_sales1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtcardn0_sales1FocusLost(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(51, 0, 51));
        jLabel24.setText("Card N0:");

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(51, 0, 51));
        jLabel28.setText("Name:");

        txtname_sales1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txtmoney_sales1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtmoney_sales1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtmoney_sales1FocusLost(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 0, 51));
        jLabel30.setText("Money:");

        cmbrecieved1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmbrecieved1.setForeground(new java.awt.Color(255, 51, 51));
        cmbrecieved1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));

        jLabel41.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(51, 0, 51));
        jLabel41.setText("Received:");

        jButton7.setBackground(new java.awt.Color(153, 255, 153));
        jButton7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(51, 0, 0));
        jButton7.setText("GIVE");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7jButton5ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Monotype Corsiva", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 102, 102));
        jLabel3.setText("Money Given");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel41)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbrecieved1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel28))
                                .addGap(9, 9, 9)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtname_sales1)
                                    .addComponent(txtcardn0_sales1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addGap(18, 18, 18)
                                .addComponent(txtmoney_sales1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(104, 104, 104))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addComponent(jButton7)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtcardn0_sales1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtname_sales1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtmoney_sales1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(cmbrecieved1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(95, 95, 95)
                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(datepane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(cmbevent, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(datepane, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmbevent, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(182, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(179, 179, 179)
                .addComponent(lblday)
                .addGap(92, 92, 92))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblday)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(102, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtmilk_salesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtmilk_salesFocusLost
        // TODO add your handling code here:
        milktomoneysales();
    }//GEN-LAST:event_txtmilk_salesFocusLost

    private void txtmoney_salesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtmoney_salesFocusLost
        // TODO add your handling code here:
        moneytomilksales();
    }//GEN-LAST:event_txtmoney_salesFocusLost

    private void txtcardn0_salesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcardn0_salesFocusLost
        // TODO add your handling code here:
        CustomerSalesName();

    }//GEN-LAST:event_txtcardn0_salesFocusLost

    private void jButton6jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6jButton5ActionPerformed
        // TODO add your handling code here:
        salesorgive();
    }//GEN-LAST:event_jButton6jButton5ActionPerformed

    private void txtcardn0_sales1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcardn0_sales1FocusLost
        // TODO add your handling code here:
        CustomerSalesName1();
    }//GEN-LAST:event_txtcardn0_sales1FocusLost

    private void txtmoney_sales1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtmoney_sales1FocusLost
        // TODO add your handling code here:
        cmbrecieved1.setEnabled(false);
    }//GEN-LAST:event_txtmoney_sales1FocusLost

    private void jButton7jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7jButton5ActionPerformed
        // TODO add your handling code here:
        salesorgivetogive();
    }//GEN-LAST:event_jButton7jButton5ActionPerformed

    private void txtDateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDateFocusLost
        // TODO add your handling code here:
      //  filltablecan();
    }//GEN-LAST:event_txtDateFocusLost

    private void cmbeventItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbeventItemStateChanged
        // TODO add your handling code here:
      //  filltablecan();
    }//GEN-LAST:event_cmbeventItemStateChanged

    private void txtDateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDateMouseClicked
        // TODO add your handling code here:
           final ObservingTextField txtDate_Reg = new ObservingTextField();
      //   public void actionPerformed(ActionEvent e) {
                // instantiate the DatePicker
                DatePicker dp = new DatePicker(txtDate_Reg);
                //JFrame fixdate=new SampleFrame();
                //datepane.removeAll();
                txtDate.add(txtDate_Reg);
                //datepane.add(dp.addObserver(Object o));
                // previously selected date
                Date selectedDate = dp.parseDate(txtDate_Reg.getText());
                dp.setSelectedDate(selectedDate);
                dp.start(txtDate_Reg);
    }//GEN-LAST:event_txtDateMouseClicked

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
            java.util.logging.Logger.getLogger(SaleMilk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SaleMilk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SaleMilk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SaleMilk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SaleMilk().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbevent;
    private javax.swing.JComboBox cmbrecieved;
    private javax.swing.JComboBox cmbrecieved1;
    private javax.swing.JDesktopPane datepane;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblday;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtcardn0_sales;
    private javax.swing.JTextField txtcardn0_sales1;
    private javax.swing.JTextField txtmilk_sales;
    private javax.swing.JTextField txtmoney_sales;
    private javax.swing.JTextField txtmoney_sales1;
    private javax.swing.JTextField txtname_sales;
    private javax.swing.JTextField txtname_sales1;
    // End of variables declaration//GEN-END:variables
}
