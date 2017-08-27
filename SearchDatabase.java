/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dairyvsr;

import java.awt.Color;
import java.awt.Font;
import java.awt.*;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.DefaultListModel;

/**
 *
 * @author Puppy
 */
public class SearchDatabase extends javax.swing.JFrame {
      static String checkname;
    private DefaultListModel list;
Connection con,con1;
Statement stmt,stmt1;
ResultSet res,res1;
String driver = "org.apache.derby.jdbc.ClientDriver";
String host="jdbc:derby://localhost:1527/D:/Dairy_Database/.netbeans-derby/DairyDatabase;user=admin;password=admin";
    /**
     * Creates new form SearchDatabase
     */
    public SearchDatabase() {
        initComponents();
          list=new DefaultListModel();
          searchlist.setModel(list);
    }
    
public void searchresult()
{
    if(txtquery.getText().equals(""))
    {
        
    }
    else
    {
    try
           {
            con=DriverManager.getConnection(host);
            stmt=con.createStatement(); 
            
            res=stmt.executeQuery("SELECT CARD_NUM,CUST_NAME FROM CUSTOMERS");
            while(res.next())
                     {
                         String id=res.getString(1);
                         String name=res.getString(2);
                         if ((name.contains(checkname)) || (id.contains(checkname)))
                        {
                           repaint();
                           String row="           "+id+"            "+name;
                           String row1="---------------------------------------";
                           list.addElement(row);
                           list.addElement(row1);
                           searchlist.setModel(list);
                           searchlist.setForeground(Color.BLACK);
                           searchlist.setFont(new Font("ANDALUS",Font.PLAIN,20));
                           searchlist.setFixedCellHeight(44);
                           repaint();
                        }
                     }
                    
                     res.close();
                     con.close();
                     stmt.close();
           }
      
    catch(Exception e)
    {
        JOptionPane.showMessageDialog(SearchDatabase.this,e);
    }
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
            con1=DriverManager.getConnection(host);
            stmt1=con1.createStatement(); 
            
            res1=stmt1.executeQuery("SELECT CARD_NUM,CUST_NAME FROM CUSTOMERS");
              
                    while(res1.next())
                     {
                         String id=res1.getString(1);
                         String name=res1.getString(2);
                         if ((name.equals(checkname)) || (id.equals(checkname)))
                        {
                           repaint();
                           String row="           "+id+"            "+name;
                           String row1="---------------------------------------";
                           list.addElement(row);
                           list.addElement(row1);
                           searchlist.setModel(list);
                           searchlist.setForeground(Color.BLACK);
                           searchlist.setFont(new Font("ANDALUS",Font.PLAIN,20));
                           searchlist.setFixedCellHeight(44);
                           repaint();
                        }
                     }
                    
                     res1.close();
                     con1.close();
                     stmt1.close();
           }
           
    catch(Exception e)
    {
        JOptionPane.showMessageDialog(SearchDatabase.this,e);
    }
    }
}
public void searchvalidate()
{
    if(checkname.equals(""))
    {
              //  resettable();
        
    }
    else
    {
      
       // System.out.println(checkname);
        searchresult();
        
        
    }
}
public void searchvalidate2()
{
    if(txtquery.getText().equals(""))
    {
              //  resettable();
        JOptionPane.showMessageDialog(SearchDatabase.this,"Please enter Card Number/Name of Customer");
        //txtquery.requestFocus();
    }
    else
    {
      
       // System.out.println(checkname);
        searchresult1();
        
        
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchpanel = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        txtquery = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        searchlist = new javax.swing.JList();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1030, 600));

        searchpanel.setBackground(new java.awt.Color(204, 204, 255));

        jLabel27.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel27.setText("Enter Query:");

        txtquery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtqueryActionPerformed(evt);
            }
        });
        txtquery.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtqueryFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtqueryFocusLost(evt);
            }
        });
        txtquery.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtqueryInputMethodTextChanged(evt);
            }
        });
        txtquery.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtqueryKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtqueryKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtqueryKeyTyped(evt);
            }
        });

        searchlist.setSelectionBackground(new java.awt.Color(0, 0, 0));
        searchlist.setSelectionForeground(new java.awt.Color(255, 255, 204));
        searchlist.setVisibleRowCount(0);
        jScrollPane1.setViewportView(searchlist);

        javax.swing.GroupLayout searchpanelLayout = new javax.swing.GroupLayout(searchpanel);
        searchpanel.setLayout(searchpanelLayout);
        searchpanelLayout.setHorizontalGroup(
            searchpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchpanelLayout.createSequentialGroup()
                .addGroup(searchpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchpanelLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))
                    .addGroup(searchpanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtquery)))
                .addGap(33, 33, 33))
        );
        searchpanelLayout.setVerticalGroup(
            searchpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchpanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(searchpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(txtquery, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 204));
        jLabel11.setText("Searching Database ......");

        jLabel1.setFont(new java.awt.Font("Monotype Corsiva", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("Search Database");

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("Note:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("Enter Card Number or Customer Name to get  details ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addContainerGap(345, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(searchpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(367, 367, 367)
                        .addComponent(jLabel1)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(81, 81, 81)
                .addComponent(jLabel11)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtqueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtqueryActionPerformed
        // TODO add your handling code here:
        //searchresult();
    }//GEN-LAST:event_txtqueryActionPerformed

    private void txtqueryFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtqueryFocusGained
        // TODO add your handling code here:
        //txtquery.setText(txtquery.getText());

    }//GEN-LAST:event_txtqueryFocusGained

    private void txtqueryFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtqueryFocusLost
        // TODO add your handling code here:
        /*resettable();
        searchvalidate();
        */
        resettable();
        searchvalidate2();
        SearchThread.stop=true;
    }//GEN-LAST:event_txtqueryFocusLost

    private void txtqueryInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtqueryInputMethodTextChanged
        // TODO add your handling code here:
        // searchvalidate();
    }//GEN-LAST:event_txtqueryInputMethodTextChanged

    private void txtqueryKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtqueryKeyPressed
        // TODO add your handling code here:
        //  txtquery.setText(txtquery.getText());
        /*resettable();
        searchvalidate();*/

    }//GEN-LAST:event_txtqueryKeyPressed

    private void txtqueryKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtqueryKeyReleased
        // TODO add your handling code here:
        // txtquery.setText(txtquery.getText());
        resettable();
        this.checkname=txtquery.getText();
        searchvalidate();
        //System.out.println(txtquery.getText());
        /*SearchThread.stop=false;
        SearchThread searchthread=new SearchThread();
        Thread searchth=new Thread(searchthread);
        searchth.start();*/

    }//GEN-LAST:event_txtqueryKeyReleased

    private void txtqueryKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtqueryKeyTyped
        // TODO add your handling code here:
        //searchresult();
        // searchvalidate();
    }//GEN-LAST:event_txtqueryKeyTyped

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked

        // TODO add your handling code here:
       
    }//GEN-LAST:event_jPanel1MouseClicked

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
            java.util.logging.Logger.getLogger(SearchDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchDatabase().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList searchlist;
    private javax.swing.JPanel searchpanel;
    static javax.swing.JTextField txtquery;
    // End of variables declaration//GEN-END:variables
}
class SearchThread extends Thread
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