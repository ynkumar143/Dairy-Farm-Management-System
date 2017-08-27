/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dairyvsr;

import java.awt.Color;
import java.awt.Font;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;

/**
 *
 * @author Puppy
 */
public class AddDairy extends javax.swing.JFrame {
     String host="jdbc:derby://localhost:1527/D:/Dairy_Database/.netbeans-derby/DairyDatabase";
           String uName="admin";
           String uPass="admin";
Connection con,con2,con1,con3,con4;
ResultSet res,res2,res1,res3,res4;
Statement stmt,stmt2,stmt1,stmt3,stmt4;
        static String checkname;
    private DefaultListModel list;
    /**
     * Creates new form AddDairy
     */
    public AddDairy() {
        initComponents();
        btnupdate.setEnabled(false);
        btndelete.setEnabled(false);
        list=new DefaultListModel();
          searchlist.setModel(list);
    }
     public void registerupdate()
{
   
   
    try{
        con1=DriverManager.getConnection(host,uName,uPass);
        stmt1=con1.createStatement();
       // +txtemp_id.getText()+","+txtemp_name.getText()+","+txtemp_mob.getText()+","+txtemp_dob.getText()+","+txtemp_doj.getText() 
        stmt1.executeUpdate("UPDATE DAIRYDATA SET DARIY_NAME='"+txtCust_Name.getText()+"',MOB_NUM="+txtMob_Num.getText()+",DAIRY_ADDR='"+txtCust_Addr.getText()+"',DATE='"+txtDate_Reg.getText()+"' WHERE DID="+txtID.getText()+"");
        JOptionPane.showMessageDialog(AddDairy.this,"Updation of "+txtID.getText()+" is done successfully");
       //resetupdate();
        stmt1.close();
        con1.close();
    }
    catch (SQLException err)
    {
        JOptionPane.showMessageDialog(AddDairy.this,err);
    }
   
}
public void searchresult1()
{
    if(txtquery.getText().equals(""))
    {
        
    }
    else
    {
    try
           {
            con3=DriverManager.getConnection(host,uName,uPass);
            stmt3=con3.createStatement(); 
            
            res3=stmt3.executeQuery("SELECT DID,DARIY_NAME FROM DAIRYDATA WHERE DID="+txtquery.getText());
              
                    if(res3.next())
                     {
                         String id=res3.getString("DID");
                         String name=res3.getString("DARIY_NAME");
                         if ((name.equals(txtquery.getText())) || (id.equals(txtquery.getText())))
                        {
                           repaint();
                           String row="    "+id+"     "+name;
                           String row1="-------------------------";
                           list.addElement(row);
                           list.addElement(row1);
                           searchlist.setModel(list);
                           searchlist.setForeground(Color.BLACK);
                           searchlist.setFont(new Font("ANDALUS",Font.PLAIN,20));
                           searchlist.setFixedCellHeight(44);
                           repaint();
                        }
                         
                     }
                    else
                         {
                             JOptionPane.showMessageDialog(AddDairy.this, "No Dairy Found");
                         }
                    
                     res3.close();
                     con3.close();
                     stmt3.close();
           }
           
    catch(Exception e)
    {
        JOptionPane.showMessageDialog(AddDairy.this,e);
    }
    }
}
private void resettable()
{
    list.removeAllElements();
    searchlist.setModel(list);
    //tblsearch.setModel(dtm);
   /*int n=tblsearch.getRowCount();  
   int m=tblsearch.getColumnCount();*/
   //JOptionPane.showMessageDialog(emp_reg.this,n);
   /*if((n>0)&&(m>0))
   {
   for(int i=0;i<n;i++)
   {
       for(int j=0;j<m;j++)
       {
           dtm.removeRow(j);
       }
   }
   }*/
}
public void register()
{
    try{
          
           con=DriverManager.getConnection(host,uName,uPass);
           stmt=con.createStatement();
           java.sql.Timestamp  sqlDate = new java.sql.Timestamp(new java.util.Date().getTime());
           stmt.executeUpdate("insert into DAIRYDATA(DID,DARIY_NAME,MOB_NUM,DAIRY_ADDR,DATE,TIMESTAMP) values("+txtID.getText()+",'"+txtCust_Name.getText()+"',"+txtMob_Num.getText()+",'"+txtCust_Addr.getText()+"','"+txtDate_Reg.getText()+"','"+sqlDate+"')");

          
           JOptionPane.showMessageDialog(AddDairy.this,"Successfully Registered");

       }
       catch (SQLException err)
       {
         JOptionPane.showMessageDialog(AddDairy.this,err.getMessage());
       }
}
public void validatedairy()
{
    
            try{
                     con2=DriverManager.getConnection(host,uName,uPass);
                     stmt2=con2.createStatement();
                 ///    java.sql.Timestamp sqlDate= new java.sql.Timestamp(new java.util.Date().getTime());
                     stmt2.executeQuery("SELECT * FROM DAIRYDATA where DID="+txtID.getText()); 
                     res2=stmt2.getResultSet();
                             if(res2.next())
                                 {  
                                     
                                         String name=res2.getString("DARIY_NAME");
                                         String Mob=res2.getString("MOB_NUM");
                                         String addr=res2.getString("DAIRY_ADDR");
                                         String date=res2.getString("DATE");
                                         txtCust_Name.setText(name);
                                         txtMob_Num.setText(Mob);
                                         txtCust_Addr.setText(addr);
                                         txtDate_Reg.setText(date);
                                         
                                         btnReg.setEnabled(false);
                                         btnupdate.setEnabled(true);
                                         btndelete.setEnabled(true);
                                 }
                             else
                             {
                                 resetvalues();
                                 btnReg.setEnabled(true);
                                 btnupdate.setEnabled(false);
                                 btndelete.setEnabled(false);
                                 
                             }
                              res2.close();
                     stmt2.close();
                    
                     con2.close();
                  
                }
             catch(SQLException err)
                 {
                     JOptionPane.showMessageDialog(AddDairy.this,err);
                    }
           
}
public void searchvalidate2()
{
    if(txtquery.getText().equals(""))
    {
              //  resettable();
        JOptionPane.showMessageDialog(AddDairy.this,"Please enter Card Number/Name of Customer");
        //txtquery.requestFocus();
    }
    else
    {
      
       // System.out.println(checkname);
         
        searchresult1();
        
        
    }
}
public void resetvalues()
{
    
    txtCust_Name.setText("");
    txtMob_Num.setText("");
    txtCust_Addr.setText("");
    txtDate_Reg.setText("");
   
}
 public void registerdelete()
{
   
   
   
    try{
        con4=DriverManager.getConnection(host,uName,uPass);
        stmt4=con4.createStatement();
       // +txtemp_id.getText()+","+txtemp_name.getText()+","+txtemp_mob.getText()+","+txtemp_dob.getText()+","+txtemp_doj.getText() 
       //if()
        int reply=JOptionPane.showConfirmDialog(null, "Are You sure want to delete?");
        if(reply==JOptionPane.YES_OPTION)
        {
        stmt4.executeUpdate("DELETE FROM DAIRYDATA WHERE DID="+txtID.getText()+"");
        JOptionPane.showMessageDialog(AddDairy.this,"Deletion of "+txtID.getText()+" is done successfully");
        }
        
        //reset();
        stmt4.close();
        con4.close();
    }
    catch (SQLException err)
    {
        JOptionPane.showMessageDialog(AddDairy.this,err);
    }
   
   
}
    class ObservingTextField extends JTextField implements Observer {
    public void update(Observable o, Object arg) {
        final String DATE_FORMAT_NOW="yyyy-MM-dd";
        Calendar calendar = (Calendar) arg;
        SimpleDateFormat sdf=new SimpleDateFormat(DATE_FORMAT_NOW);
        
        DatePicker dp = (DatePicker) o;
        txtDate_Reg.setText(sdf.format(calendar.getTime()));
       // System.out.println(sdf.format(calendar.getTime()));
       // System.out.println("picked=" + dp.formatDate(calendar));
       // txtDate_Reg.setText(dp.formatDate(calendar));
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

        Cust_Reg_Head = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtID = new javax.swing.JTextField();
        txtCust_Name = new javax.swing.JTextField();
        txtMob_Num = new javax.swing.JTextField();
        txtCust_Addr = new javax.swing.JTextField();
        txtDate_Reg = new javax.swing.JTextField();
        lblmob = new javax.swing.JLabel();
        Cust_id = new javax.swing.JLabel();
        Cust_name = new javax.swing.JLabel();
        lblcustaddr = new javax.swing.JLabel();
        lblRegisterdate = new javax.swing.JLabel();
        btnReg = new javax.swing.JButton();
        btncancel = new javax.swing.JButton();
        datepane = new javax.swing.JDesktopPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnupdate = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        searchlist = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        txtquery = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1375, 760));

        Cust_Reg_Head.setFont(new java.awt.Font("Monotype Corsiva", 1, 36)); // NOI18N
        Cust_Reg_Head.setForeground(new java.awt.Color(0, 0, 255));
        Cust_Reg_Head.setText("HNSpec Dairy Farm Dairy Registration");

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        txtID.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIDFocusLost(evt);
            }
        });

        txtCust_Name.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        txtMob_Num.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtMob_Num.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMob_NumKeyPressed(evt);
            }
        });

        txtCust_Addr.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        txtDate_Reg.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtDate_Reg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDate_RegMouseClicked(evt);
            }
        });
        txtDate_Reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDate_RegActionPerformed(evt);
            }
        });
        txtDate_Reg.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDate_RegFocusLost(evt);
            }
        });
        txtDate_Reg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDate_RegKeyPressed(evt);
            }
        });

        lblmob.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblmob.setText("Mobile Number:");

        Cust_id.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Cust_id.setText("Dairy ID:");

        Cust_name.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Cust_name.setText("Dairy Name:");

        lblcustaddr.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblcustaddr.setText("Dairy Address:");

        lblRegisterdate.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblRegisterdate.setText("Date of Registration:");

        btnReg.setBackground(new java.awt.Color(153, 255, 153));
        btnReg.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnReg.setText("Register");
        btnReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegActionPerformed(evt);
            }
        });

        btncancel.setBackground(new java.awt.Color(153, 255, 153));
        btncancel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btncancel.setText("Reset");
        btncancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelActionPerformed(evt);
            }
        });

        datepane.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblcustaddr)
                            .addComponent(lblmob)
                            .addComponent(Cust_name)
                            .addComponent(Cust_id)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(lblRegisterdate)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCust_Addr, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMob_Num, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCust_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnReg, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btncancel, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtDate_Reg, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addComponent(datepane, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Cust_id, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cust_name)
                    .addComponent(txtCust_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMob_Num, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblmob))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblcustaddr)
                        .addGap(32, 32, 32))
                    .addComponent(txtCust_Addr, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(datepane)
                    .addComponent(txtDate_Reg, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(lblRegisterdate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReg, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncancel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Admin Panel");

        btnupdate.setBackground(new java.awt.Color(204, 255, 204));
        btnupdate.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnupdate.setForeground(new java.awt.Color(51, 51, 255));
        btnupdate.setText("Update");
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        btndelete.setBackground(new java.awt.Color(204, 255, 204));
        btndelete.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btndelete.setForeground(new java.awt.Color(51, 51, 255));
        btndelete.setText("Delete");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 204, 204));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Search Panel");

        searchlist.setSelectionBackground(new java.awt.Color(0, 0, 0));
        searchlist.setSelectionForeground(new java.awt.Color(255, 255, 204));
        searchlist.setVisibleRowCount(0);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Enter Query:");

        txtquery.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtquery.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtqueryFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(jLabel2)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtquery))
                    .addComponent(searchlist, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(37, 37, 37)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtquery, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(searchlist, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 365, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(Cust_Reg_Head, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(96, 96, 96))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(337, 337, 337))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Cust_Reg_Head, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 163, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDate_RegMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDate_RegMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_txtDate_RegMouseClicked

    private void txtDate_RegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDate_RegActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtDate_RegActionPerformed

    private void txtDate_RegFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDate_RegFocusLost
        // TODO add your handling code here:
    if(txtDate_Reg.getText().equals(""))
        {
             final ObservingTextField txtDate_Reg = new ObservingTextField();
      //   public void actionPerformed(ActionEvent e) {
                // instantiate the DatePicker
                DatePicker dp = new DatePicker(txtDate_Reg);
                //JFrame fixdate=new SampleFrame();
                //datepane.removeAll();
                datepane.add(txtDate_Reg);
                //datepane.add(dp.addObserver(Object o));
                // previously selected date
                Date selectedDate = dp.parseDate(txtDate_Reg.getText());
                dp.setSelectedDate(selectedDate);
                dp.start(txtDate_Reg);
        }
        else
        {
            
        }
         

    }//GEN-LAST:event_txtDate_RegFocusLost

    private void txtDate_RegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDate_RegKeyPressed
        // TODO add your handling code here:
       final ObservingTextField txtDate_Reg = new ObservingTextField();
      //   public void actionPerformed(ActionEvent e) {
                // instantiate the DatePicker
                DatePicker dp = new DatePicker(txtDate_Reg);
                //JFrame fixdate=new SampleFrame();
                datepane.removeAll();
               // datepane.getUI();
                datepane.add(txtDate_Reg);
                //datepane.add(dp.addObserver(Object o));
                // previously selected date
                Date selectedDate = dp.parseDate(txtDate_Reg.getText());
                dp.setSelectedDate(selectedDate);
                dp.start(txtDate_Reg);
    }//GEN-LAST:event_txtDate_RegKeyPressed

    private void btnRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegActionPerformed
register();
resetvalues();

    }//GEN-LAST:event_btnRegActionPerformed

    private void btncancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        //      new DairyHome().setVisible(true);

    }//GEN-LAST:event_btncancelActionPerformed

    private void txtIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIDFocusLost
        // TODO add your handling code here:
        if(txtID.getText().equals(""))
        {
           JOptionPane.showMessageDialog(AddDairy.this,"Please Enter Dairy ID"); 
        }
        else
        {
        validatedairy();
        }
    }//GEN-LAST:event_txtIDFocusLost

    private void txtqueryFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtqueryFocusLost
        // TODO add your handling code here:
          resettable();
        searchvalidate2();
        SearchThread2.stop=true;
    }//GEN-LAST:event_txtqueryFocusLost

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:
        registerupdate();
        resetvalues();
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        // TODO add your handling code here:
        registerdelete();
        resetvalues();
    }//GEN-LAST:event_btndeleteActionPerformed

    private void txtMob_NumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMob_NumKeyPressed
        // TODO add your handling code here:
          char c=evt.getKeyChar();
        if(!((c>='0')&&(c<='9')||(c == evt.VK_BACK_SPACE) ||(c == evt.VK_DELETE)))
        {
             JOptionPane.showMessageDialog(AddDairy.this,"Enter only integers");
             //txtMob_Num.removeKeyListener(evt);
             //txtMob_Num.setText("");
        }
    }//GEN-LAST:event_txtMob_NumKeyPressed

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
            java.util.logging.Logger.getLogger(AddDairy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddDairy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddDairy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddDairy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddDairy().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Cust_Reg_Head;
    private javax.swing.JLabel Cust_id;
    private javax.swing.JLabel Cust_name;
    private javax.swing.JButton btnReg;
    private javax.swing.JButton btncancel;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnupdate;
    private javax.swing.JDesktopPane datepane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblRegisterdate;
    private javax.swing.JLabel lblcustaddr;
    private javax.swing.JLabel lblmob;
    private javax.swing.JList searchlist;
    private javax.swing.JTextField txtCust_Addr;
    private javax.swing.JTextField txtCust_Name;
    private javax.swing.JTextField txtDate_Reg;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtMob_Num;
    private javax.swing.JTextField txtquery;
    // End of variables declaration//GEN-END:variables
}
class SearchThread2 extends Thread
{
    static boolean stop=false;
    public void run()
    {
                    //emp_reg search=new emp_reg();
                    //search.resettable();
                    //search.searchvalidate();
                    //String s=emp_reg.txtquery.getText();
                    //System.out.println(s);
                    
        /*while(true)
                {
                   // emp_reg search=new emp_reg();
                    //search.resettable();
                    //search.searchvalidate();
                    //String s=search.txtquery.getText();
                    //System.out.println(s);
                    if(stop) return;
                }*/
        
    }
}