/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dairyvsr;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Puppy
 */
public class DisplayData extends javax.swing.JFrame {
    String driver = "org.apache.derby.jdbc.ClientDriver";
String host="jdbc:derby://localhost:1527/D:/Dairy_Database/.netbeans-derby/DairyDatabase;user=admin;password=admin";
DefaultTableModel dtm = new DefaultTableModel(0,0); 
static DefaultTableModel dtm1 = new DefaultTableModel(0,0); 
Connection con,con1,con2;
ResultSet res,res1,res2;
Statement stmt,stmt1,stmt2;
   static String checkname;

    /**
     * Creates new form DisplayData
     */
    public DisplayData() {
        initComponents();
          DisplayEmployee();
          tblemp.setShowVerticalLines(false);
          
    }
     public void searchvalidate()
    {
        if(txtquery.getText().equals(""))
        {
            dtm.setRowCount(0);
        DisplayEmployee();
        }
        else
        {
      try
           {
            con2=DriverManager.getConnection(host);
            stmt2=con2.createStatement(); 
            
            res2=stmt2.executeQuery("SELECT * FROM CUSTOMERS WHERE CARD_NUM="+txtquery.getText());
            
                    if(res2.next())
                     {
                         searchresult();
                     }
                    else
                    {
                        JOptionPane.showMessageDialog(DisplayData.this,"Customer Id is not found in database");
                        dtm.setRowCount(0);
                    }
                     res2.close();
                     con2.close();
                     stmt2.close();
           }
           
    catch(Exception e)
    {
        JOptionPane.showMessageDialog(DisplayData.this,"Please Enter Customer Id");
    }  
    }
    }
    public void searchresult()
{
    if(txtquery.getText().equals(""))
    {
        dtm.setRowCount(0);
        DisplayEmployee();
    }
    else
    {
    try
           {
            con1=DriverManager.getConnection(host);
            stmt1=con1.createStatement(); 
            
            res1=stmt1.executeQuery("SELECT * FROM CUSTOMERS");
            tblemp.setModel(dtm1);
             int columncount=tblemp.getColumnCount();
          dtm1.setRowCount(0);
             if(columncount<2)
             {
            
                   dtm1.addColumn(" Card N0");
                  // tblsearch.getColumn(" ").setWidth(1);
                   dtm1.addColumn("         Customer Name");
                   dtm1.addColumn("         Mobile Num");
                   dtm1.addColumn("         Address");
                   dtm1.addColumn("         Date");
                   
             }    
             
                    while(res1.next())
                     {
                         String id=res1.getString("CARD_NUM");
                         String name=res1.getString("CUST_NAME");
                         String mob=res1.getString("MOB_NUM");
                         String addr=res1.getString("CUST_ADDR");
                         String date=res1.getString("DATE");
                        // String name=res1.getString(2);
                         /*tblsearch.setRowHeight(80);
                         
                         tblsearch.setSelectionForeground(Color.white);
                         tblsearch.setSelectionBackground(Color.black);
                         tblsearch.setFont(new Font("Comic Sans MS",Font.BOLD,15));
                        // tblsearch.setCol
                         tblsearch.setForeground(Color.blue);
                         int col=tblsearch.getColumnCount();
                         int row=tblsearch.getRowCount();*/
                         
                         if ((name.contains(txtquery.getText())) || (id.contains(txtquery.getText())))
                        {
                           repaint();
                           //dtm.addRow(new Object[]{sdf4.format(cal4.getTime())}); 
                           dtm1.addRow(new Object[]{id,name,mob,addr,date});
                           //String row=" "+id+"        "+name;
                          // list.addElement(row);
                           //list.
                           //searchlist.setModel(list);
                           //searchlist.setForeground(Color.MAGENTA);
                          // searchlist.setFont(new Font("Comic Sans MS",Font.BOLD,15));
                          // searchlist.setFixedCellHeight(44);
                        //   list.setSize(6);
                           //searchlist.set
                           //repaint();
                           DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
rightRenderer.setHorizontalAlignment( JLabel.CENTER );
tblemp.getColumnModel().getColumn(0).setCellRenderer( rightRenderer );
tblemp.getColumnModel().getColumn(1).setCellRenderer( rightRenderer );
tblemp.getColumnModel().getColumn(2).setCellRenderer( rightRenderer );
tblemp.getColumnModel().getColumn(3).setCellRenderer( rightRenderer );
tblemp.getColumnModel().getColumn(4).setCellRenderer( rightRenderer );
tblemp.getColumnModel().getColumn(0).setPreferredWidth(80);
 tblemp.getColumnModel().getColumn(1).setPreferredWidth(280);
 tblemp.getColumnModel().getColumn(2).setPreferredWidth(160);
 tblemp.getColumnModel().getColumn(3).setPreferredWidth(200);  
 tblemp.getColumnModel().getColumn(4).setPreferredWidth(150);
                           
                        }
                         
                        
                     }
                
                   
                     res1.close();
                     con1.close();
                     stmt1.close();
           }
           
    catch(Exception e)
    {
        JOptionPane.showMessageDialog(DisplayData.this,e);
    }
    }
}
public void DisplayEmployee()
     {
         int count=0;
           try
           {
            con=DriverManager.getConnection(host);
            stmt=con.createStatement();
            stmt.executeQuery("SELECT * FROM CUSTOMERS");
            res=stmt.getResultSet();
             tblemp.setModel(dtm);
             int columncount=tblemp.getColumnCount();
             if(columncount<2)
             {
                    dtm.addColumn("       C.N0");
                    dtm.addColumn("                    Name");
                    dtm.addColumn("               Mob Num");
                    dtm.addColumn("               Address");
                    dtm.addColumn("               Date");
                   // dtm.addColumn("Emp_DOJ");
             
             }
                     while(res.next())
                     {
                         Object id=res.getString("CARD_NUM");
                         Object name=res.getString("CUST_NAME");
                         Object role=res.getString("MOB_NUM");
                         Object mob=res.getString("CUST_ADDR");
                         Object dob=res.getString("DATE");
                        // Object doj=res.getString("EMP_DOJ");
                        tblemp.setRowHeight(60);
                       // tblemp.
                        tblemp.setSelectionForeground(Color.white);
                         tblemp.setSelectionBackground(Color.black);
                         tblemp.setFont(new Font("Andalus",Font.PLAIN,17));
                        // tblsearch.setCol
                         tblemp.setForeground(Color.BLACK);
                     //   tblemp.setFont("Times New Roman");
                           dtm.addRow(new Object[]{id,name,role,mob,dob});
                           tblemp.setAutoResizeMode(tblemp.AUTO_RESIZE_OFF);  
                           DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
rightRenderer.setHorizontalAlignment( JLabel.CENTER );
tblemp.getColumnModel().getColumn(0).setCellRenderer( rightRenderer );
tblemp.getColumnModel().getColumn(1).setCellRenderer( rightRenderer );
tblemp.getColumnModel().getColumn(2).setCellRenderer( rightRenderer );
tblemp.getColumnModel().getColumn(3).setCellRenderer( rightRenderer );
tblemp.getColumnModel().getColumn(4).setCellRenderer( rightRenderer );
tblemp.getColumnModel().getColumn(0).setPreferredWidth(80);
 tblemp.getColumnModel().getColumn(1).setPreferredWidth(280);
 tblemp.getColumnModel().getColumn(2).setPreferredWidth(160);
 tblemp.getColumnModel().getColumn(3).setPreferredWidth(200);  
 tblemp.getColumnModel().getColumn(4).setPreferredWidth(150); 
 count++;
                     }
                     String count1=Integer.toString(count);
                     txtcount.setText(count1);
                    
                     res.close();
                     con.close();
                     stmt.close();
           }
           
    catch(Exception e)
    {
        JOptionPane.showMessageDialog(DisplayData.this,e);
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
        tblemp = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtquery = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtcount = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1370, 700));

        tblemp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblemp.setAlignmentX(100.0F);
        tblemp.setAlignmentY(100.0F);
        jScrollPane1.setViewportView(tblemp);

        jLabel1.setFont(new java.awt.Font("Monotype Corsiva", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("View Details of Customer");

        txtquery.setBackground(new java.awt.Color(255, 255, 204));
        txtquery.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        txtquery.setToolTipText("Enter Customer Name or Card N0");
        txtquery.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtqueryFocusLost(evt);
            }
        });
        txtquery.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtqueryKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Gisha", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setText("Search Database:");

        jLabel3.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 255));
        jLabel3.setText("Total Customer Count:");

        txtcount.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        txtcount.setForeground(new java.awt.Color(255, 0, 51));
        txtcount.setText("Bhavani");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtcount)
                .addGap(50, 50, 50))
            .addGroup(layout.createSequentialGroup()
                .addGap(364, 364, 364)
                .addComponent(jLabel1)
                .addContainerGap(415, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(60, 60, 60)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addComponent(txtquery, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 887, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(77, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtcount))
                .addContainerGap(510, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(66, 66, 66)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtquery, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(75, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtqueryFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtqueryFocusLost
        // TODO add your handling code here:
        dtm1.setRowCount(0);
        searchvalidate();

        // searchresult();
    }//GEN-LAST:event_txtqueryFocusLost

    private void txtqueryKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtqueryKeyPressed
        // TODO add your handling code here:
        int c=evt.getKeyCode();
        if(c==KeyEvent.VK_RIGHT)
        { //JOptionPane.showMessageDialog(ViewData.this,"Right arrow is pressed");
            dtm1.setRowCount(0);
            searchvalidate();

        }
        if(c==KeyEvent.VK_LEFT)
        {
            //  txtmdcardn0.requestFocus(true);
        }
        // searchresult();
    }//GEN-LAST:event_txtqueryKeyPressed

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
            java.util.logging.Logger.getLogger(DisplayData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DisplayData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DisplayData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DisplayData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DisplayData().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblemp;
    private javax.swing.JLabel txtcount;
    private javax.swing.JTextField txtquery;
    // End of variables declaration//GEN-END:variables
}
