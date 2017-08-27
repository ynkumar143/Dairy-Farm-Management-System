/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dairyvsr;

import java.awt.Color;
import java.awt.Font;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Puppy
 */
public class FillCans extends javax.swing.JFrame {
    String driver = "org.apache.derby.jdbc.ClientDriver";
        String host="jdbc:derby://localhost:1527/D:/Dairy_Database/.netbeans-derby/DairyDatabase;user=admin;password=admin";
         final DateFormat timeFormat=new SimpleDateFormat("HH:mm:ss");
         DefaultTableModel dtm=new DefaultTableModel(0,0);
          final DecimalFormat df = new DecimalFormat("#.##");
          final String DATE_FORMAT_NOW="yyyy-MM-dd";
Connection con,con1,con2,con3,con4,con5;
Statement stmt,stmt1,stmt2,stmt3,stmt4,stmt5;
ResultSet res,res1,res2,res3,res4,res5;

    /**
     * Creates new form FillCans
     */
    public FillCans() {
        initComponents();
        
        displaydate();
        
        displaydairyname();
        Function();
        filltablecan();
        btnupd.setEnabled(false);
        btndel.setEnabled(false);
        
    }
    public void filltablecan()
    { 
        milklit2.setText("0");
        milkkg2.setText("0");
      try
          
      {
          tblcan.setModel(dtm);
             tblcan.setAutoResizeMode(tblcan.AUTO_RESIZE_OFF);
            con3=DriverManager.getConnection(host);
            stmt3=con3.createStatement();
            stmt3.executeQuery("SELECT CAN_NUM,KGCALC,LITCALC,DAIRY_NAME,DATE,SESSION FROM DAILYCANDATA where (DATE='"+txtDate.getText()+"' and SESSION='"+cmbevent.getSelectedItem()+"')");
            res3=stmt3.getResultSet();
            
            int codd=tblcan.getColumnCount();
            if(codd<2)
            {
                dtm.addColumn("Can");
                dtm.addColumn("Dairy Name");
                dtm.addColumn("LIT");
                dtm.addColumn("KG");
            }
            dtm.setRowCount(0);
            int i=0;
            Double totalmilkkg=0.0;
            Double totalmilklit=0.0;
            while(res3.next())
             {      
                    
                    Object cann0=res3.getInt("CAN_NUM");
                    Object dairyname=res3.getString("DAIRY_NAME");
                    Object milkkg=res3.getDouble("KGCALC");
                    Object milklit=res3.getDouble("LITCALC");
                
                        dtm.addRow(new Object[] {cann0,dairyname,milklit,milkkg});
                                
                    String milkkgsum=tblcan.getValueAt(i, 3).toString();                 
                    Double milkkgsum1=Double.parseDouble(milkkgsum);
                    totalmilkkg=totalmilkkg+milkkgsum1;
                    String totlamilkkg1=df.format(totalmilkkg);
                    //JOptionPane.showMessageDialog(CustomerData.this,milksum1);
                    milkkg2.setText(totlamilkkg1);
                   
                    
                    String milklitsum=tblcan.getValueAt(i, 2).toString();
                    Double milklitsum1=Double.parseDouble(milklitsum);
                    totalmilklit=totalmilklit+milklitsum1;
                    String totalmilklit1=df.format(totalmilklit);
                    //JOptionPane.showMessageDialog(CustomerData.this,milksum1);
                    milklit2.setText(totalmilklit1);
                    i++; 
             }
            String countcan=Integer.toString(i);
            cans.setText(countcan);
               DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
rightRenderer.setHorizontalAlignment( JLabel.CENTER );
tblcan.getColumnModel().getColumn(0).setCellRenderer( rightRenderer );
tblcan.getColumnModel().getColumn(1).setCellRenderer( rightRenderer );
tblcan.getColumnModel().getColumn(2).setCellRenderer( rightRenderer );
tblcan.getColumnModel().getColumn(3).setCellRenderer( rightRenderer );
  tblcan.getColumnModel().getColumn(0).setPreferredWidth(60);
                     //tblcan.getColumnModel().getColumn(1).setCellRenderer( rightRenderer );
                     tblcan.getColumnModel().getColumn(1).setPreferredWidth(160);
                     tblcan.getColumnModel().getColumn(2).setPreferredWidth(62);
                     tblcan.getColumnModel().getColumn(3).setPreferredWidth(62);
                     //tblcan.getColumnModel().getColumn(3).setCellRenderer( rightRenderer );
                     //tblcu[w].getColumnModel().getColumn(days2+5).setPreferredWidth(290);
                     //tblcu[w].getColumnModel().getColumn(days2+5).setCellRenderer( rightRenderer );
tblcan.setBackground(Color.WHITE) ;
                 // head.getColumnModel().setColumnMargin();
                  tblcan.setShowVerticalLines(false);
                  tblcan.setRowHeight(45);
             tblcan.setForeground(Color.BLACK);
             tblcan.setFont(new Font("Times New Roman",Font.PLAIN,14));
             //tblcan.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,2));
//tblcan.getColumnModel().getColumn(4).setCellRenderer( rightRenderer );
            res3.close();
            stmt3.close();
            con3.close();
          }
           
             catch(SQLException err)
                {
            JOptionPane.showMessageDialog(FillCans.this,err);
                }
    }
    public void UpdateCanData()
    {
           try
                 {
                     con4=DriverManager.getConnection(host);
                     stmt4=con4.createStatement();
                     String date1=txtDate.getText().toString();
                     
                     stmt4.executeUpdate("UPDATE DAILYCANDATA SET LITCALC="+txtlit.getText()+",DAIRY_NAME='"+cmbdairy.getSelectedItem()+"',KGCALC="+txtkg.getText()+" WHERE DATE='"+date1+"' and SESSION='"+cmbevent.getSelectedItem()+"' and CAN_NUM="+txtcan.getText()); 
                     JOptionPane.showMessageDialog(FillCans.this,"Data Updated Successfully");
                     //txtcanname.setText("");
                     txtkg.setText("");
                     txtlit.setText("");
                     stmt4.close();
                     con4.close();
                }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(FillCans.this,err);
        }
    }
     public void CustomerSalesName1()
    {
            try{
                     con5=DriverManager.getConnection(host);
                     stmt5=con5.createStatement();
                     java.sql.Timestamp sqlDate= new java.sql.Timestamp(new java.util.Date().getTime());
                     stmt5.executeQuery("SELECT CAN_NUM FROM DAILYCANDATA WHERE DATE='"+txtDate.getText()+"' and SESSION='"+cmbevent.getSelectedItem()+"' and CAN_NUM="+txtcan.getText()); 
                     res5=stmt5.getResultSet();
                             if(res5.next())
                                 {  
                                       JOptionPane.showMessageDialog(FillCans.this,"Can "+txtcan.getText()+" is already filled") ;
                                       btnfill.setEnabled(false);
                                 }
                             else
                             {
                                 
                                 btnfill.setEnabled(true);
                                 
                             }
                              res3.close();
                     stmt3.close();
                    
                     con3.close();
                  
                }
             catch(SQLException err)
                 {
                     JOptionPane.showMessageDialog(FillCans.this,err);
                    }
            }  
     public void cansfill()
   {
       
        try{
             con2=DriverManager.getConnection(host);
             stmt2=con2.createStatement();
             java.sql.Timestamp sqlDate= new java.sql.Timestamp(new java.util.Date().getTime());
             stmt2.executeUpdate("insert into DAILYCANDATA(CAN_NUM,DAIRY_NAME,KGCALC,LITCALC,DATE,SESSION,TIMESTAMP) values("+txtcan.getText()+",'"+cmbdairy.getSelectedItem()+"',"+txtlit.getText()+","+txtkg.getText()+",'"+txtDate.getText()+"','"+cmbevent.getSelectedItem()+"','"+sqlDate+"')");
           
             JOptionPane.showMessageDialog(FillCans.this,"Can "+txtcan.getText()+" Filled Accurately");
             txtcan.setText("");
             //txtcanname.setText("");
             txtlit.setText("");
             txtkg.setText("");
             stmt.close();
             con.close();
        }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(FillCans.this,err);
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
    public void displaydairyname()
    {
      try
        {
            con1=DriverManager.getConnection(host);
            stmt1=con1.createStatement();
            //java.sql.Timestamp sqlDate= new java.sql.Timestamp(new java.util.Date().getTime());
            stmt1.executeQuery("SELECT DARIY_NAME FROM DAIRYDATA"); 
            res1=stmt1.getResultSet();
//            int i=1;
//            String ival1=Integer.toString(i);
//            txtcan.setText(ival1);
            while(res1.next())
            {
                Object Date=res1.getString("DARIY_NAME");
                cmbdairy.addItem(Date);
                
            }
            res1.close();
            stmt1.close();
            con1.close();
        }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(FillCans.this,err);
        }   
    }
public void countsessioncan()
    {
        try
        {
            con=DriverManager.getConnection(host);
            stmt=con.createStatement();
            java.sql.Timestamp sqlDate= new java.sql.Timestamp(new java.util.Date().getTime());
            Calendar cal=Calendar.getInstance();
            String date=txtDate.getText();
            
            stmt.executeQuery("SELECT CAN_NUM,DATE,SESSION FROM DAILYCANDATA"); 
            res=stmt.getResultSet();
            int i=1;
            String ival1=Integer.toString(i);
            txtcan.setText(ival1);
            while(res.next())
            {
                Object Date=res.getString("DATE");
                Object session=res.getString("SESSION");
                Object datec=txtDate.getText();
                Object sessionc=cmbevent.getSelectedItem();
                if((datec.equals(Date))&&(sessionc.equals(session)))
                        {
                            i=i+1;
                         
                        }
                String ival=Integer.toString(i);
                txtcan.setText(ival);
            }
            res.close();
            stmt.close();
            con.close();
        }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(FillCans.this,err);
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

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtcan = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtlit = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtkg = new javax.swing.JTextField();
        btnfill = new javax.swing.JButton();
        cmbevent = new javax.swing.JComboBox();
        txtDate = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        cmbdairy = new javax.swing.JComboBox();
        datepane = new javax.swing.JDesktopPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnupd = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btndel = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblcan = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cans = new javax.swing.JLabel();
        milkkg2 = new javax.swing.JLabel();
        milklit2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1370, 700));

        jLabel1.setFont(new java.awt.Font("Monotype Corsiva", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("Dairy Daily Cans Filling Form");

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        txtcan.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtcan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtcanFocusGained(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Traditional Arabic", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(51, 0, 51));
        jLabel32.setText("Can N0:");

        jLabel28.setFont(new java.awt.Font("Traditional Arabic", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(51, 0, 51));
        jLabel28.setText("Dairy Name:");

        jLabel30.setFont(new java.awt.Font("Traditional Arabic", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 0, 51));
        jLabel30.setText("Milk in Litres:");

        txtlit.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        jLabel31.setFont(new java.awt.Font("Traditional Arabic", 1, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(51, 0, 51));
        jLabel31.setText("Milk in Kilograms:");

        txtkg.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        btnfill.setBackground(new java.awt.Color(153, 255, 153));
        btnfill.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnfill.setForeground(new java.awt.Color(51, 0, 0));
        btnfill.setText("Fill Details");
        btnfill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfillActionPerformed(evt);
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

        jLabel33.setFont(new java.awt.Font("Traditional Arabic", 1, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 51, 51));
        jLabel33.setText("Select Data & Session:");

        cmbdairy.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        datepane.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(btnfill, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(243, 243, 243))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel32)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel30))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtkg)
                                        .addComponent(txtcan)
                                        .addComponent(cmbdairy, 0, 232, Short.MAX_VALUE))
                                    .addComponent(txtlit, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(datepane, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)))
                        .addComponent(cmbevent, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel33)
                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbevent, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(datepane, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(txtcan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel28)
                    .addComponent(cmbdairy, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(txtlit))
                        .addGap(31, 31, 31))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtkg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(btnfill, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Settings");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 255));
        jLabel4.setText("To Add/Delete Dairy Name Goto AddDairy Page ");

        btnupd.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnupd.setText("Update Can");
        btnupd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 255));
        jLabel6.setText("Select Required Row in Below Table to Update Cans Data");

        btndel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btndel.setText("Delete Can");
        btndel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnupd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                                .addComponent(btndel)))))
                .addGap(33, 33, 33))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(17, 17, 17)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btndel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnupd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 204, 204));
        jPanel3.setForeground(new java.awt.Color(255, 204, 204));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Today's Cans Filled");

        tblcan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblcan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblcanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblcan);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setText("Total:");

        cans.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cans.setText("Nag");

        milkkg2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        milkkg2.setText("Kilog");

        milklit2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        milklit2.setText("Litres");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(52, 52, 52)
                .addComponent(cans)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(milklit2)
                .addGap(63, 63, 63)
                .addComponent(milkkg2)
                .addGap(60, 60, 60))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cans)
                    .addComponent(milkkg2)
                    .addComponent(milklit2))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jLabel3)
                        .addGap(0, 152, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
            .addGroup(layout.createSequentialGroup()
                .addGap(340, 340, 340)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(141, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnfillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfillActionPerformed
        // TODO add your handling code here:
        if(txtlit.getText().equals("")||(txtkg.getText().equals("")))
        {
            JOptionPane.showMessageDialog(FillCans.this,"Enter Milk Quantities");
        }
        else
        {
cansfill();
filltablecan();
        }
     //   Validatecan();

    }//GEN-LAST:event_btnfillActionPerformed

    private void txtcanFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcanFocusGained
        // TODO add your handling code here:
       countsessioncan();
    }//GEN-LAST:event_txtcanFocusGained

    private void txtDateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDateFocusLost
        // TODO add your handling code here:
        filltablecan();
    }//GEN-LAST:event_txtDateFocusLost

    private void cmbeventItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbeventItemStateChanged
        // TODO add your handling code here:
        filltablecan();
    }//GEN-LAST:event_cmbeventItemStateChanged

    private void tblcanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblcanMouseClicked
        // TODO add your handling code here:
        try
        {
         int row=tblcan.getSelectedRow();
       String valu1=tblcan.getValueAt(row,0).toString();
       Object valu2=tblcan.getValueAt(row,1);
       String valu3=tblcan.getValueAt(row,2).toString();
       String valu4=tblcan.getValueAt(row,3).toString();
       //String valu5=tblevening.getValueAt(row,4).toString();
       txtcan.setText(valu1);
       cmbdairy.setSelectedItem(valu2);
       txtlit.setText(valu4);
       txtkg.setText(valu3);
       btnfill.setEnabled(false);
       btnupd.setEnabled(true);
       btndel.setEnabled(true);
       }
         catch(NullPointerException err)
      {
         // int row1=tblmorning.getSelectedRow();
          
          JOptionPane.showMessageDialog(FillCans.this,"Please Fill Details of Customer on this day");
            
             }
    }//GEN-LAST:event_tblcanMouseClicked
public void registerdelete()
{
   
   
   
    try{
        con4=DriverManager.getConnection(host);
        stmt4=con4.createStatement();
       // +txtemp_id.getText()+","+txtemp_name.getText()+","+txtemp_mob.getText()+","+txtemp_dob.getText()+","+txtemp_doj.getText() 
       //if()
        int reply=JOptionPane.showConfirmDialog(null, "Are You sure want to delete?");
        if(reply==JOptionPane.YES_OPTION)
        {
        stmt4.executeUpdate("DELETE FROM DAILYCANDATA WHERE DATE='"+txtDate.getText()+"' and SESSION='"+cmbevent.getSelectedItem()+"' and CAN_NUM="+txtcan.getText());
        JOptionPane.showMessageDialog(FillCans.this,"Deletion of "+txtcan.getText()+" on "+txtDate.getText()+"-"+cmbevent.getSelectedItem()+" is done successfully");
        }
        
        //reset();
        stmt4.close();
        con4.close();
    }
    catch (SQLException err)
    {
        JOptionPane.showMessageDialog(FillCans.this,err);
    }
}
    private void btnupdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdActionPerformed
        // TODO add your handling code here:
       if(txtlit.getText().equals("")){
           JOptionPane.showMessageDialog(FillCans.this, "Select Row to Update Data");
       }
       else
       {
        UpdateCanData();
        filltablecan();
        txtlit.setText("");
        txtkg.setText("");
        btnfill.setEnabled(true);
         btndel.setEnabled(false);
        btnupd.setEnabled(false);
       }
    }//GEN-LAST:event_btnupdActionPerformed

    private void btndelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndelActionPerformed
        // TODO add your handling code here:
        registerdelete();
        filltablecan();
        txtlit.setText("");
        txtkg.setText("");
        btnfill.setEnabled(true);
        btndel.setEnabled(false);
        btnupd.setEnabled(false);
    }//GEN-LAST:event_btndelActionPerformed

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
            java.util.logging.Logger.getLogger(FillCans.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FillCans.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FillCans.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FillCans.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FillCans().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btndel;
    private javax.swing.JButton btnfill;
    private javax.swing.JButton btnupd;
    private javax.swing.JLabel cans;
    private javax.swing.JComboBox cmbdairy;
    private javax.swing.JComboBox cmbevent;
    private javax.swing.JDesktopPane datepane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel milkkg2;
    private javax.swing.JLabel milklit2;
    private javax.swing.JTable tblcan;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtcan;
    private javax.swing.JTextField txtkg;
    private javax.swing.JTextField txtlit;
    // End of variables declaration//GEN-END:variables
}
