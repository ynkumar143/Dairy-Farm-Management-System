/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Cust_Reg.java
 *
 * Created on Jan 2, 2014, 5:05:54 PM
 */

package dairyvsr;
//import dairyform.DairyHome;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;
import java.text.*;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.util.Calendar;
import javax.swing.JOptionPane;
import java.sql.Timestamp;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JTextField;
/**
 *
 * @author Puppy
 */
public class Cust_Reg extends javax.swing.JFrame {
    final DateFormat timeFormat=new SimpleDateFormat("HH:mm:ss");
     final String DATE_FORMAT_NOW="yyyy-MM-dd";
     final ObservingTextField textField = new ObservingTextField();
String host="jdbc:derby://localhost:1527/D:/Dairy_Database/.netbeans-derby/DairyDatabase";
           String uName="admin";
           String uPass="admin";
Connection con,con1,con2;
Statement stmt,stmt1,stmt2;
ResultSet rs,res1,res2;
   
    /** Creates new form Cust_Reg */
    public Cust_Reg() {
        initComponents();
        DisplayTime();
        
    }
public void DisplayTime(){
Calendar cal=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat(DATE_FORMAT_NOW);
       // txtDate_Reg.setText(sdf.format(cal.getTime()));
        
}
public void register()
{
    try{
           String host="jdbc:derby://localhost:1527/D:/Dairy_Database/.netbeans-derby/DairyDatabase";
           String uName="admin";
           String uPass="admin";
           con=DriverManager.getConnection(host,uName,uPass);
           stmt=con.createStatement();
           java.sql.Timestamp  sqlDate = new java.sql.Timestamp(new java.util.Date().getTime());
           stmt.executeUpdate("insert into CUSTOMERS(CARD_NUM,CUST_NAME,MOB_NUM,CUST_ADDR,DATE,TIMESTAMP) values("+txtID.getText()+",'"+txtCust_Name.getText()+"',"+txtMob_Num.getText()+",'"+txtCust_Addr.getText()+"','"+txtDate_Reg.getText()+"','"+sqlDate+"')");

          
           JOptionPane.showMessageDialog(Cust_Reg.this,"Successfully Registered");

       }
       catch (SQLException err)
       {
         JOptionPane.showMessageDialog(Cust_Reg.this,err.getMessage());
       }
}
public void getdetails()
{
     try{
        con1=DriverManager.getConnection(host,uName,uPass);
        stmt1=con1.createStatement();
                     stmt1.executeQuery("SELECT * FROM CUSTOMERS where CARD_NUM="+txtID.getText()+""); 
                     res1=stmt1.getResultSet();
                             if(res1.next())
                                 {  
                                     
                                     //JOptionPane.showMessageDialog(Cust_Reg.this,"");
                                      String name=res1.getString("CUST_NAME");
                                      JOptionPane.showMessageDialog(Cust_Reg.this,"Employee "+name+" is already registered");
                                    txtCust_Name.setEnabled(false);
                                    txtMob_Num.setEnabled(false);
                                    txtCust_Addr.setEnabled(false);
                                    txtDate_Reg.setEnabled(false);
                                      String mob=res1.getString("MOB_NUM");
//                                      String addr=res1.getString("CUST_ADDR");
//                                      String date=res1.getString("DATE");
                                        
//                                      txtupt_name.setText(name);
//                                      txtuptmob.setText(mob);
//                                      txtuptadr.setText(addr);
//                                      txtuptdate.setText(date);
                                 }
                             else
                             {
                                 txtCust_Name.setEnabled(true);
                                    txtMob_Num.setEnabled(true);
                                    txtCust_Addr.setEnabled(true);
                                    txtDate_Reg.setEnabled(true);
                              // register();
                              //JOptionPane.showMessageDialog(Cust_Reg.this, "No Result Found");
                              //resetupdate();
                             }
                              
                     stmt1.close();
                     res1.close();
                     con1.close();
                  
        }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(Cust_Reg.this,err);
        }
}
 public void registerupdate()
    {
           try
                 {
                  con1=DriverManager.getConnection(host,uName,uPass);
                     stmt1=con1.createStatement();
                     stmt1.executeUpdate("UPDATE CUSTOMERS SET CUST_NAME='"+txtCust_Name.getText()+"',MOB_NUM="+txtMob_Num.getText()+",CUST_ADDR='"+txtCust_Addr.getText()+"',DATE='"+txtDate_Reg.getText()+"' WHERE CARD_NUM="+txtID.getText()); 
                     JOptionPane.showMessageDialog(Cust_Reg.this,"Data Updated Successfully");
                     txtID.setText("");
                     txtCust_Name.setText("");
                     txtMob_Num.setText("");
                     txtCust_Addr.setText("");
                     con1.close();
                     stmt1.close();
                }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(Cust_Reg.this,err);
        }
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        txtMob_Num = new javax.swing.JTextField();
        txtCust_Addr = new javax.swing.JTextField();
        Cust_Reg_Head = new javax.swing.JLabel();
        btncancel = new javax.swing.JButton();
        txtCust_Name = new javax.swing.JTextField();
        txtDate_Reg = new javax.swing.JTextField();
        txtID = new javax.swing.JTextField();
        datepane = new javax.swing.JDesktopPane();
        Cust_id = new javax.swing.JLabel();
        Cust_name = new javax.swing.JLabel();
        lblmob = new javax.swing.JLabel();
        lblcustaddr = new javax.swing.JLabel();
        lblRegisterdate = new javax.swing.JLabel();
        btnReg = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dairy Farm Customer Registration");
        setBounds(new java.awt.Rectangle(0, 0, 1366, 768));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));
        jPanel2.setForeground(new java.awt.Color(204, 255, 204));

        txtMob_Num.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtMob_Num.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMob_NumFocusLost(evt);
            }
        });
        txtMob_Num.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMob_NumKeyPressed(evt);
            }
        });

        txtCust_Addr.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        Cust_Reg_Head.setFont(new java.awt.Font("JasmineUPC", 1, 36)); // NOI18N
        Cust_Reg_Head.setForeground(new java.awt.Color(255, 102, 204));
        Cust_Reg_Head.setText("HNSpec Dairy Farm Customer Registration");

        btncancel.setBackground(new java.awt.Color(153, 255, 153));
        btncancel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btncancel.setText("Cancel");
        btncancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelActionPerformed(evt);
            }
        });

        txtCust_Name.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

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

        txtID.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIDFocusLost(evt);
            }
        });

        datepane.setBackground(new java.awt.Color(204, 204, 255));

        Cust_id.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Cust_id.setForeground(new java.awt.Color(102, 102, 255));
        Cust_id.setText("Customer ID:");

        Cust_name.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Cust_name.setForeground(new java.awt.Color(102, 102, 255));
        Cust_name.setText("Customer Name:");

        lblmob.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblmob.setForeground(new java.awt.Color(102, 102, 255));
        lblmob.setText("Mobile Number:");

        lblcustaddr.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblcustaddr.setForeground(new java.awt.Color(102, 102, 255));
        lblcustaddr.setText("Customer Address:");

        lblRegisterdate.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblRegisterdate.setForeground(new java.awt.Color(102, 102, 255));
        lblRegisterdate.setText("Date of Registration:");

        btnReg.setBackground(new java.awt.Color(153, 255, 153));
        btnReg.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnReg.setText("Register");
        btnReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(Cust_id)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Cust_Reg_Head)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblcustaddr)
                            .addComponent(lblmob)
                            .addComponent(lblRegisterdate)
                            .addComponent(Cust_name))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnReg)
                                .addGap(105, 105, 105)
                                .addComponent(btncancel))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCust_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMob_Num, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtCust_Addr, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtDate_Reg, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(datepane, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 126, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(Cust_Reg_Head, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Cust_id, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCust_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Cust_name))
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMob_Num, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblmob))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblcustaddr)
                    .addComponent(txtCust_Addr, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(datepane)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtDate_Reg, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblRegisterdate))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReg, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncancel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(101, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(153, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegActionPerformed
      
       
                    register();
                    txtID.setText("");
                    txtCust_Name.setText("");
                    txtMob_Num.setText("");
                    txtCust_Addr.setText("");
                
           
    }//GEN-LAST:event_btnRegActionPerformed

    private void btncancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelActionPerformed
        // TODO add your handling code here:
         this.setVisible(false);
//      new DairyHome().setVisible(true);
     
    }//GEN-LAST:event_btncancelActionPerformed

    private void txtDate_RegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDate_RegActionPerformed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_txtDate_RegActionPerformed

    private void txtIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIDFocusLost
        // TODO add your handling code here:
        if(txtID.getText().equals(""))
        {
            
            JOptionPane.showMessageDialog(Cust_Reg.this,"Please Fill Customer ID");
            
            //txtID.getAction();
        }
        else
        {
        getdetails();
    }//GEN-LAST:event_txtIDFocusLost
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
    private void txtDate_RegMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDate_RegMouseClicked
        // TODO add your handling code here:
         
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
       
    }//GEN-LAST:event_txtDate_RegMouseClicked

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        // TODO add your handling code here:
      
        
    }//GEN-LAST:event_formMouseEntered

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

    private void txtMob_NumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMob_NumKeyPressed
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if(!((c>='0')&&(c<='9')||(c == evt.VK_BACK_SPACE) ||(c == evt.VK_DELETE)))
        {
             JOptionPane.showMessageDialog(Cust_Reg.this,"Enter only integers");
             //txtMob_Num.removeKeyListener(evt);
             //txtMob_Num.setText("");
        }
        
    }//GEN-LAST:event_txtMob_NumKeyPressed

    private void txtMob_NumFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMob_NumFocusLost
        // TODO add your handling code here:
        if(txtMob_Num.getText().length()!=10)
        {
            JOptionPane.showMessageDialog(Cust_Reg.this,"Please Fill Correct Mobile Number");
        }
    }//GEN-LAST:event_txtMob_NumFocusLost
 
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cust_Reg().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Cust_Reg_Head;
    private javax.swing.JLabel Cust_id;
    private javax.swing.JLabel Cust_name;
    private javax.swing.JButton btnReg;
    private javax.swing.JButton btncancel;
    private javax.swing.JDesktopPane datepane;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblRegisterdate;
    private javax.swing.JLabel lblcustaddr;
    private javax.swing.JLabel lblmob;
    private javax.swing.JTextField txtCust_Addr;
    private javax.swing.JTextField txtCust_Name;
    private javax.swing.JTextField txtDate_Reg;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtMob_Num;
    // End of variables declaration//GEN-END:variables

}
