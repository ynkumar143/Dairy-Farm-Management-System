/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dairyvsr;

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
import javax.swing.JTextField;

/**
 *
 * @author Puppy
 */
public class ModReg extends javax.swing.JFrame {
    String host="jdbc:derby://localhost:1527/D:/Dairy_Database/.netbeans-derby/DairyDatabase";
           String uName="admin";
           String uPass="admin";
Connection con,con1,con4;
ResultSet res,res1,res4;
Statement stmt,stmt1,stmt4;
    /**
     * Creates new form ModReg
     */
    public ModReg() {
        initComponents();
    }
    public void resetupdate(){
       // txtuptid.setText("");
        txtupt_name.setText("");
        txtuptmob.setText("");
        txtuptadr.setText("");
        txtuptdate.setText("");
    }
       public void registerupdate()
{
   
   
    try{
        con=DriverManager.getConnection(host,uName,uPass);
        stmt=con.createStatement();
       // +txtemp_id.getText()+","+txtemp_name.getText()+","+txtemp_mob.getText()+","+txtemp_dob.getText()+","+txtemp_doj.getText() 
        stmt.executeUpdate("UPDATE CUSTOMERS SET CUST_NAME='"+txtupt_name.getText()+"',MOB_NUM="+txtuptmob.getText()+",CUST_ADDR='"+txtuptadr.getText()+"',DATE='"+txtuptdate.getText()+"' WHERE CARD_NUM="+txtuptid.getText()+"");
        JOptionPane.showMessageDialog(ModReg.this,"Updation of "+txtuptid.getText()+" is done successfully");
       resetupdate();
        stmt.close();
        con.close();
    }
    catch (SQLException err)
    {
        JOptionPane.showMessageDialog(ModReg.this,err);
    }
   
}
public void getdetails()
{
     try{
        con1=DriverManager.getConnection(host,uName,uPass);
        stmt1=con1.createStatement();
                     stmt1.executeQuery("SELECT * FROM CUSTOMERS where CARD_NUM="+txtuptid.getText()+""); 
                     res1=stmt1.getResultSet();
                             if(res1.next())
                                 {  
                                     
                                      String name=res1.getString("CUST_NAME");
                                      String mob=res1.getString("MOB_NUM");
                                      String addr=res1.getString("CUST_ADDR");
                                      String date=res1.getString("DATE");
                                      txtupt_name.setEnabled(true);
                              txtuptmob.setEnabled(true);
                              txtuptdate.setEnabled(true);
                              txtuptadr.setEnabled(true);
                                      txtupt_name.setText(name);
                                      txtuptmob.setText(mob);
                                      txtuptadr.setText(addr);
                                      txtuptdate.setText(date);
                                 }
                             else
                             {
                               
                              JOptionPane.showMessageDialog(ModReg.this, "No Result Found");
                              txtupt_name.setEnabled(false);
                              txtuptmob.setEnabled(false);
                              txtuptdate.setEnabled(false);
                              txtuptadr.setEnabled(false);
                              resetupdate();
                             }
                              
                     stmt1.close();
                     res1.close();
                     con1.close();
                  
        }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(ModReg.this,err);
        }
}
public void reset()
{
    txtuptid.setText("");
    txtupt_name.setText("");
    txtuptmob.setText("");
    txtuptadr.setText("");
    txtuptdate.setText("");
    
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
        stmt4.executeUpdate("DELETE FROM CUSTOMERS WHERE CARD_NUM="+txtuptid.getText()+"");
        JOptionPane.showMessageDialog(ModReg.this,"Deletion of "+txtuptid.getText()+" is done successfully");
        }
        
        reset();
        stmt4.close();
        con4.close();
    }
    catch (SQLException err)
    {
        JOptionPane.showMessageDialog(ModReg.this,err);
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

        jPanel1 = new javax.swing.JPanel();
        txtuptmob = new javax.swing.JTextField();
        deskpane = new javax.swing.JDesktopPane();
        lblmob = new javax.swing.JLabel();
        txtupt_name = new javax.swing.JTextField();
        Cust_name = new javax.swing.JLabel();
        lblcustaddr = new javax.swing.JLabel();
        txtuptadr = new javax.swing.JTextField();
        lblRegisterdate = new javax.swing.JLabel();
        txtuptdate = new javax.swing.JTextField();
        Cust_id = new javax.swing.JLabel();
        txtuptid = new javax.swing.JTextField();
        Cust_Reg_Head = new javax.swing.JLabel();
        btncancel = new javax.swing.JButton();
        btnReg = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setForeground(new java.awt.Color(204, 255, 204));

        txtuptmob.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtuptmob.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtuptmobFocusLost(evt);
            }
        });
        txtuptmob.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtuptmobKeyPressed(evt);
            }
        });

        deskpane.setBackground(new java.awt.Color(204, 204, 255));
        deskpane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deskpaneMouseClicked(evt);
            }
        });
        deskpane.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                deskpaneKeyPressed(evt);
            }
        });

        lblmob.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblmob.setForeground(new java.awt.Color(102, 102, 255));
        lblmob.setText("Mobile Number:");

        txtupt_name.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        Cust_name.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Cust_name.setForeground(new java.awt.Color(102, 102, 255));
        Cust_name.setText("Customer Name:");

        lblcustaddr.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblcustaddr.setForeground(new java.awt.Color(102, 102, 255));
        lblcustaddr.setText("Customer Address:");

        txtuptadr.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        lblRegisterdate.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblRegisterdate.setForeground(new java.awt.Color(102, 102, 255));
        lblRegisterdate.setText("Date of Registration:");

        txtuptdate.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtuptdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtuptdateActionPerformed(evt);
            }
        });
        txtuptdate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtuptdateFocusLost(evt);
            }
        });

        Cust_id.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Cust_id.setForeground(new java.awt.Color(102, 102, 255));
        Cust_id.setText("Customer ID:");

        txtuptid.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtuptid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtuptidFocusLost(evt);
            }
        });

        Cust_Reg_Head.setFont(new java.awt.Font("JasmineUPC", 1, 30)); // NOI18N
        Cust_Reg_Head.setForeground(new java.awt.Color(255, 51, 255));
        Cust_Reg_Head.setText("HNSpec Dairy Farm Customer Editing Registration");

        btncancel.setBackground(new java.awt.Color(153, 255, 153));
        btncancel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btncancel.setForeground(new java.awt.Color(51, 51, 51));
        btncancel.setText("Delete");
        btncancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelActionPerformed(evt);
            }
        });

        btnReg.setBackground(new java.awt.Color(153, 255, 153));
        btnReg.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnReg.setForeground(new java.awt.Color(51, 51, 51));
        btnReg.setText("Update");
        btnReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Cust_id)
                    .addComponent(Cust_Reg_Head)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtuptid, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(Cust_name)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtupt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(lblmob)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtuptmob, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(lblcustaddr)
                                    .addGap(32, 32, 32)
                                    .addComponent(txtuptadr, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(lblRegisterdate)
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtuptdate, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(btnReg)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btncancel)
                                            .addGap(31, 31, 31))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deskpane, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(deskpane, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(Cust_Reg_Head, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Cust_id, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtuptid, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Cust_name)
                            .addComponent(txtupt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblmob)
                            .addComponent(txtuptmob, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblcustaddr)
                            .addComponent(txtuptadr, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtuptdate, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRegisterdate))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btncancel)
                    .addComponent(btnReg))
                .addGap(0, 14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(400, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 109, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtuptdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtuptdateActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtuptdateActionPerformed

    private void btnRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegActionPerformed

      registerupdate();

    }//GEN-LAST:event_btnRegActionPerformed

    private void btncancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelActionPerformed
        // TODO add your handling code here:
        registerdelete();
       // this.setVisible(false);
//        new DairyHome().setVisible(true);

    }//GEN-LAST:event_btncancelActionPerformed

    private void txtuptidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtuptidFocusLost
        // TODO add your handling code here:
         if(txtuptid.getText().equals(""))
        {
            
            JOptionPane.showMessageDialog(ModReg.this,"Please Fill Customer ID");
            
            //txtID.getAction();
        }
        else
        {
        getdetails();
    }                 
       
    }//GEN-LAST:event_txtuptidFocusLost

    private void txtuptmobKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtuptmobKeyPressed
 char c=evt.getKeyChar();
        if(!((c>='0')&&(c<='9')||(c == evt.VK_BACK_SPACE) ||(c == evt.VK_DELETE)))
        {
             JOptionPane.showMessageDialog(ModReg.this,"Enter only integers");
             //txtMob_Num.removeKeyListener(evt);
             //txtMob_Num.setText("");
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_txtuptmobKeyPressed
  class ObservingTextField extends JTextField implements Observer {
    public void update(Observable o, Object arg) {
        final String DATE_FORMAT_NOW="yyyy-MM-dd";
        Calendar calendar = (Calendar) arg;
        SimpleDateFormat sdf=new SimpleDateFormat(DATE_FORMAT_NOW);
        
        DatePicker dp = (DatePicker) o;
        txtuptdate.setText(sdf.format(calendar.getTime()));
       // System.out.println(sdf.format(calendar.getTime()));
       // System.out.println("picked=" + dp.formatDate(calendar));
       // txtDate_Reg.setText(dp.formatDate(calendar));
    }
   }
    private void txtuptdateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtuptdateFocusLost
  if(txtuptdate.getText().equals(""))
        {
             final ObservingTextField txtuptdate = new ObservingTextField();
      //   public void actionPerformed(ActionEvent e) {
                // instantiate the DatePicker
                DatePicker dp = new DatePicker(txtuptdate);
                //JFrame fixdate=new SampleFrame();
                //datepane.removeAll();
                deskpane.add(txtuptdate);
                //datepane.add(dp.addObserver(Object o));
                // previously selected date
                Date selectedDate = dp.parseDate(txtuptdate.getText());
                dp.setSelectedDate(selectedDate);
                dp.start(txtuptdate);
        }
        else
        {
            
        }
                 // TODO add your handling code here:
    }//GEN-LAST:event_txtuptdateFocusLost

    private void deskpaneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deskpaneKeyPressed
 final ObservingTextField txtuptdate = new ObservingTextField();
      //   public void actionPerformed(ActionEvent e) {
                // instantiate the DatePicker
                DatePicker dp = new DatePicker(txtuptdate);
                //JFrame fixdate=new SampleFrame();
                deskpane.removeAll();
               // datepane.getUI();
                deskpane.add(txtuptdate);
                //datepane.add(dp.addObserver(Object o));
                // previously selected date
                Date selectedDate = dp.parseDate(txtuptdate.getText());
                dp.setSelectedDate(selectedDate);
                dp.start(txtuptdate);        // TODO add your handling code here:
    }//GEN-LAST:event_deskpaneKeyPressed

    private void deskpaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deskpaneMouseClicked
 final ObservingTextField txtuptdate = new ObservingTextField();
      //   public void actionPerformed(ActionEvent e) {
                // instantiate the DatePicker
                DatePicker dp = new DatePicker(txtuptdate);
                //JFrame fixdate=new SampleFrame();
                //datepane.removeAll();
                deskpane.add(txtuptdate);
                //datepane.add(dp.addObserver(Object o));
                // previously selected date
                Date selectedDate = dp.parseDate(txtuptdate.getText());
                dp.setSelectedDate(selectedDate);
                dp.start(txtuptdate);        // TODO add your handling code here:
    }//GEN-LAST:event_deskpaneMouseClicked

    private void txtuptmobFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtuptmobFocusLost
        // TODO add your handling code here:
        String lengthmob=txtuptmob.getText();
        int length=lengthmob.length();
        if(!(length==10))
        {
            JOptionPane.showMessageDialog(ModReg.this,"Please Enter Valid Mobile Number");
            
        }
    }//GEN-LAST:event_txtuptmobFocusLost

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
            java.util.logging.Logger.getLogger(ModReg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModReg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModReg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModReg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModReg().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Cust_Reg_Head;
    private javax.swing.JLabel Cust_id;
    private javax.swing.JLabel Cust_name;
    private javax.swing.JButton btnReg;
    private javax.swing.JButton btncancel;
    private javax.swing.JDesktopPane deskpane;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblRegisterdate;
    private javax.swing.JLabel lblcustaddr;
    private javax.swing.JLabel lblmob;
    private javax.swing.JTextField txtupt_name;
    private javax.swing.JTextField txtuptadr;
    private javax.swing.JTextField txtuptdate;
    private javax.swing.JTextField txtuptid;
    private javax.swing.JTextField txtuptmob;
    // End of variables declaration//GEN-END:variables
}
