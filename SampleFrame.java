/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dairyvsr;
import com.qt.datapicker.DatePicker;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import javax.swing.table.TableColumn;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JOptionPane;
import java.lang.Double;
import java.util.Observable;
import java.util.Observer;
/**
 *
 * @authocard_n */
public final class SampleFrame extends javax.swing.JFrame {
 String driver = "org.apache.derby.jdbc.ClientDriver";
 String host="jdbc:derby://localhost:1527/D:/Dairy_Database/.netbeans-derby/DairyDatabase;create=true;user=admin;password=admin";    
 final static DateFormat timeFormat=new SimpleDateFormat("HH:mm:ss");
 final String DATE_FORMAT_NOW="yyyy-MM-dd";
 DefaultTableModel dtm = new DefaultTableModel(0,0); 
 DefaultTableModel dtm1 = new DefaultTableModel(0,0); 
 DefaultTableModel dtm2 = new DefaultTableModel(0,0);
 DefaultTableModel dtm3 = new DefaultTableModel(0,0);
 DefaultTableModel dtm4 = new DefaultTableModel(0,0);
 DefaultTableModel dtm5 = new DefaultTableModel(0,0);
 DefaultTableModel dtm6 = new DefaultTableModel(0,0);
  DefaultTableModel dtm7 = new DefaultTableModel(0,0);
   DefaultTableModel dtm8 = new DefaultTableModel(0,0);
   DefaultTableModel dtm9 = new DefaultTableModel(0,0);
 final String DATE_FORMAT_NOW1="EEEE";
 final DecimalFormat df = new DecimalFormat("#.##");
 final DecimalFormat df2 = new DecimalFormat("#.#");
 Double val_2;
 Double val_3;
 Connection con;
 ResultSet res;
 Statement stmt;

    /**
     * Creates new form SampleFrame
     */
    public SampleFrame() {
        
        initComponents(); 
        lblcount2.setText("0");
        lblmoney2.setText("0");
        DisplayDate();
        DisplayMilkRate();
        DisplaySalesRate();
        SessionDisplay();
        filltablecustomer();
        filltablesales();
        countsessiondata();
        countsessioncansales();
    } 
     public void DisplayDate(){
            Calendar cal=Calendar.getInstance();
            SimpleDateFormat sdf=new SimpleDateFormat(DATE_FORMAT_NOW);
            txtDate.setText(sdf.format(cal.getTime()));
            txttodate.setEditable(false);
            DateDifference();
    } 
     public void filltablesales()
    { 
      try
       
      {
            tblsales.setModel(dtm6);
            con=DriverManager.getConnection(host);
            stmt=con.createStatement();
            stmt.executeQuery("SELECT SAMPLE,MONEY,MILK,DATE,SESSION FROM DAILYSALES1 where (DATE='"+txtDate.getText()+"' and SESSION='"+cmbevent.getSelectedItem()+"')");
            res=stmt.getResultSet();
            int cold=tblsales.getColumnCount();
            if (cold<2)
            {
                    dtm6.addColumn("SAMPLE");
                    dtm6.addColumn("MILK");
                    dtm6.addColumn("MONEY");
                    
                    //dtm.addColumn("S.TIME");
            }
            int i=0;
            Double totalmilk=0.0;
            Double totalmoney=0.0;
            dtm6.setRowCount(0);
            while(res.next())
             {      
                    
                    String sample=res.getString("SAMPLE");
                    
                    Object milk=res.getString("MILK");
                    Object money=res.getString("MONEY");
                                dtm6.addRow(new Object[] {sample,milk,money});
                    String milksum=tblsales.getValueAt(i, 1).toString();
                    Double milksum1=Double.parseDouble(milksum);
                    totalmilk=totalmilk+milksum1;
                     String totlamilk1=df.format(totalmilk);
                    lblmilk1.setText(totlamilk1);
                    String moneysum=tblsales.getValueAt(i, 2).toString();
                    Double moneysum1=Double.parseDouble(moneysum);
                    totalmoney=totalmoney+moneysum1;
                    String totalmoney1=df.format(totalmoney);
                    lblmoney1.setText(totalmoney1);
                    i++;
             }
             DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
rightRenderer.setHorizontalAlignment( JLabel.CENTER );
tblsales.setShowHorizontalLines(false);
tblsales.setShowVerticalLines(false);
tblsales.getColumnModel().getColumn(0).setCellRenderer( rightRenderer );
tblsales.getColumnModel().getColumn(1).setCellRenderer( rightRenderer );
tblsales.getColumnModel().getColumn(2).setCellRenderer( rightRenderer );
            String count=Integer.toString(i);
            Double totalfatcount=Double.parseDouble(count);
            lblcount1.setText(count);
           // String totalmilk1=Double.toString(totalmilk);
            
           // tblcustomer.setAutoCreateColumnsFromModel(false);
            res.close();
            stmt.close();
            con.close();
            
//            
//           tblcustomer.repaint();
          }
           
             catch(SQLException err)
                {
            JOptionPane.showMessageDialog(SampleFrame.this,err);
                }
    }
     
  public void filltablecustomer()
    { 
      try
      {
            tblcustomer.setModel(dtm4);
            con=DriverManager.getConnection(host);
            stmt=con.createStatement();
            stmt.executeQuery("SELECT CARD_NUM,SAMPLE_N0,MILK,FAT,MONEY,DATE,SESSION FROM DAILYDATA where (DATE='"+txtDate.getText()+"' and SESSION='"+cmbevent.getSelectedItem()+"')");
            res=stmt.getResultSet();
            int cold=tblcustomer.getColumnCount();
            if (cold<2)
            {
                    dtm4.addColumn("C.N0");
                    dtm4.addColumn("S.N0");
                    dtm4.addColumn("Milk");
                    dtm4.addColumn("FAT");
                    dtm4.addColumn("MONEY");
            }
            int i=0;
            Double totalmilk=0.0;
            Double totalfat=0.0;
            Double totalmoney=0.0;
            dtm4.setRowCount(0);
            while(res.next())
             {      
                    String cardn02=res.getString("CARD_NUM");
                    Object S_n0=res.getInt("SAMPLE_N0");
                    Object Milk=res.getDouble("MILK");
                    Object Fat=res.getString("FAT");
                    Object Money=res.getString("MONEY");
                    dtm4.addRow(new Object[] {cardn02,S_n0,Milk,Fat,Money});
                    String milksum=tblcustomer.getValueAt(i, 2).toString();
                    Double milksum1=Double.parseDouble(milksum);
                    totalmilk=totalmilk+milksum1;
                    String totlamilk1=df.format(totalmilk);
                    lblmilk.setText(totlamilk1);
                    String fatag=tblcustomer.getValueAt(i,3).toString();
                    Double fatag1=Double.parseDouble(fatag);
                    totalfat=totalfat+fatag1;
                    String moneysum=tblcustomer.getValueAt(i, 4).toString();
                    Double moneysum1=Double.parseDouble(moneysum);
                    totalmoney=totalmoney+moneysum1;
                    String totalmoney1=df.format(totalmoney);
                    lblmoney.setText(totalmoney1);
                    i++;
             }
             DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
             rightRenderer.setHorizontalAlignment( JLabel.CENTER );
             tblcustomer.setShowVerticalLines(false);
             tblcustomer.setShowHorizontalLines(false);
             tblcustomer.getColumnModel().getColumn(0).setCellRenderer( rightRenderer );
             tblcustomer.getColumnModel().getColumn(1).setCellRenderer( rightRenderer );
             tblcustomer.getColumnModel().getColumn(2).setCellRenderer( rightRenderer );
             tblcustomer.getColumnModel().getColumn(3).setCellRenderer( rightRenderer );
             tblcustomer.getColumnModel().getColumn(4).setCellRenderer( rightRenderer );
            String count=Integer.toString(i);
            Double totalfatcount=Double.parseDouble(count);
            lblcount.setText(count);
            Double fataverg=totalfat/totalfatcount;
            String fatavrg=df.format(fataverg);
            lblfat.setText(fatavrg);
            res.close();
            stmt.close();
            con.close();
          }
           
             catch(SQLException err)
                {
                     JOptionPane.showMessageDialog(SampleFrame.this,err);
                }
    }
     public void DateDifference()
     {
         String dateds=txtDate.getText();
         Date d2=null;
         try{
                SimpleDateFormat sdf1=new SimpleDateFormat(DATE_FORMAT_NOW);
                d2=sdf1.parse(dateds);
                txttodate.setText(dateds);
                Calendar cal1=Calendar.getInstance();
                cal1.setTime(d2);
                cal1.add(Calendar.DATE,-9);
                txtfromdate.setText(sdf1.format(cal1.getTime()));
            }
            
                 catch(Exception e)
            {
                
            }
     }
     public void txtfalsemade()
     {
        txtmdcardn0.setEditable(false);
        txtmdsn0.setEditable(false);
        txtmdmilk.setEditable(false);
        txtmdfat.setEditable(false);
        txtmdmoney.setEditable(false);
        txtfromdate.setEditable(false);
   
     }
    public void SessionDisplay()
    {
         Date date=new Date();
         String time=timeFormat.format(date);
         String startmrng="00:00:00";
         String endmrng="11:59:59";
    if((startmrng.compareTo(time)<0)&&(endmrng.compareTo(time)>0))
    {
         cmbevent.setSelectedItem("Morning"); 
    }
    else
    {
         cmbevent.setSelectedItem("Evening");
    }
    txtsample.setEditable(false);
    txtmilk.setText("");
    txtmdsession.setEditable(false);
    txtmilk.setEditable(false);
    txtfat.setEditable(false);
    txtmoney.setEditable(false);
    txtmddate.setEditable(false);
    txtmdcardn0.setEditable(false);
    txtmdsn0.setEditable(false);
    txtmdmilk.setEditable(false);
    txtmdfat.setEditable(false);
    txtmdmoney.setEditable(false);
    txtfromdate.setEditable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txttodate = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtfromdate = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblevening = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblmorning = new javax.swing.JTable();
        panel4 = new java.awt.Panel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtmddate = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtmdcardn0 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtmdsession = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtmdsn0 = new javax.swing.JTextField();
        txtmdmilk = new javax.swing.JTextField();
        txtmdfat = new javax.swing.JTextField();
        txtmdmoney = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        lblalert = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblcustomer = new javax.swing.JTable();
        panel5 = new java.awt.Panel();
        jLabel19 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtcard_n0 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtsample = new javax.swing.JTextField();
        txtmilk = new javax.swing.JTextField();
        txtfat = new javax.swing.JTextField();
        txtmoney = new javax.swing.JTextField();
        btnenter = new javax.swing.JButton();
        txtDate = new javax.swing.JTextField();
        cmbevent = new javax.swing.JComboBox();
        lblday1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblcount = new javax.swing.JLabel();
        lblmilk = new javax.swing.JLabel();
        lblfat = new javax.swing.JLabel();
        lblmoney = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        datepane = new javax.swing.JDesktopPane();
        jPanel2 = new javax.swing.JPanel();
        lblcount1 = new javax.swing.JLabel();
        lblmilk1 = new javax.swing.JLabel();
        lblmoney1 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        salesmilk = new javax.swing.JTextField();
        salesmoney = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblsales = new javax.swing.JTable();
        btnsenter = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        lblday2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        salesno = new javax.swing.JTextField();
        lblname = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        givemoneyam = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        lblcount2 = new javax.swing.JLabel();
        lblmilk2 = new javax.swing.JLabel();
        lblmoney2 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lblmilk3 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        givemoneypm = new javax.swing.JTable();
        txtgivemoney = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Dairy Farm Daily Data--Y V SUBBA RAO");
        setPreferredSize(new java.awt.Dimension(1370, 700));

        txttodate.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txttodate.setForeground(new java.awt.Color(0, 0, 255));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 0, 0));
        jLabel16.setText("View Data (10 days)  From ");

        txtfromdate.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtfromdate.setForeground(new java.awt.Color(51, 51, 255));

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 0, 0));
        jLabel18.setText("To");

        tblevening.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblevening.setRowHeight(28);
        tblevening.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbleveningMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblevening);

        tblmorning.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblmorning.setRowHeight(28);
        tblmorning.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblmorningMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblmorning);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("Modify Customer Data");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 0, 51));
        jLabel9.setText("Date");

        txtmddate.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtmddate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmddateKeyPressed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 0, 51));
        jLabel11.setText("Session");

        txtmdcardn0.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtmdcardn0.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmdcardn0KeyPressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 0, 51));
        jLabel10.setText("C.No");

        txtmdsession.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtmdsession.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmdsessionKeyPressed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 0, 51));
        jLabel13.setText("S.n0");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 0, 51));
        jLabel14.setText("Milk");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 0, 51));
        jLabel12.setText("Fat");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 0, 51));
        jLabel15.setText("Money");

        txtmdsn0.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtmdsn0.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmdsn0KeyPressed(evt);
            }
        });

        txtmdmilk.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtmdmilk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmdmilkActionPerformed(evt);
            }
        });
        txtmdmilk.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtmdmilkFocusLost(evt);
            }
        });
        txtmdmilk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmdmilkKeyPressed(evt);
            }
        });

        txtmdfat.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtmdfat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtmdfatFocusLost(evt);
            }
        });
        txtmdfat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmdfatKeyPressed(evt);
            }
        });

        txtmdmoney.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtmdmoney.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmdmoneyKeyPressed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(153, 255, 153));
        jButton7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(51, 0, 0));
        jButton7.setText("Update");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jButton7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton7KeyPressed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(255, 204, 204));
        jButton9.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton9.setForeground(new java.awt.Color(51, 0, 0));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dairyvsr/Exit.png"))); // NOI18N
        jButton9.setText("Delete");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jButton9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton9KeyPressed(evt);
            }
        });

        lblalert.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblalert.setForeground(new java.awt.Color(255, 0, 255));

        javax.swing.GroupLayout panel4Layout = new javax.swing.GroupLayout(panel4);
        panel4.setLayout(panel4Layout);
        panel4Layout.setHorizontalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel4Layout.createSequentialGroup()
                        .addGroup(panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel4Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel9))
                            .addComponent(txtmddate, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtmdsession, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel4Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel11)))))
                .addGap(16, 16, 16)
                .addGroup(panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel4Layout.createSequentialGroup()
                        .addComponent(txtmdcardn0, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addGroup(panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtmdsn0, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(8, 8, 8)
                .addGroup(panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel4Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel14)
                        .addGap(49, 49, 49)
                        .addComponent(jLabel12))
                    .addGroup(panel4Layout.createSequentialGroup()
                        .addComponent(txtmdmilk, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtmdfat, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel4Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel15))
                    .addGroup(panel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtmdmoney, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 49, 49))
            .addGroup(panel4Layout.createSequentialGroup()
                .addGap(215, 215, 215)
                .addComponent(lblalert, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panel4Layout.setVerticalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(13, 13, 13))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel4Layout.createSequentialGroup()
                        .addComponent(lblalert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel4Layout.createSequentialGroup()
                        .addGroup(panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(jLabel11)
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel14)
                                .addComponent(jLabel12)
                                .addComponent(jLabel15)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(panel4Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txtmdsn0, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                            .addComponent(txtmdsession, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtmddate, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtmdmilk, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtmdcardn0, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtmdfat, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtmdmoney, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(28, 28, 28))
                    .addGroup(panel4Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        tblcustomer.setAutoCreateRowSorter(true);
        tblcustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblcustomer.setRowHeight(32);
        tblcustomer.getTableHeader().setReorderingAllowed(false);
        tblcustomer.setUpdateSelectionOnSort(false);
        tblcustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblcustomerMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblcustomer);

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 0, 51));
        jLabel19.setText("Money");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 0, 51));
        jLabel7.setText("Sample");

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 0, 51));
        jLabel22.setText("Milk");

        jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(51, 0, 51));
        jLabel34.setText("Fat");

        txtcard_n0.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        txtcard_n0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcard_n0ActionPerformed(evt);
            }
        });
        txtcard_n0.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtcard_n0FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtcard_n0FocusLost(evt);
            }
        });
        txtcard_n0.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcard_n0KeyPressed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(51, 0, 51));
        jLabel35.setText("Card N0.");

        txtsample.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        txtsample.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsampleActionPerformed(evt);
            }
        });
        txtsample.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtsampleFocusLost(evt);
            }
        });
        txtsample.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsampleKeyPressed(evt);
            }
        });

        txtmilk.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        txtmilk.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtmilkFocusLost(evt);
            }
        });
        txtmilk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmilkKeyPressed(evt);
            }
        });

        txtfat.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        txtfat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfatActionPerformed(evt);
            }
        });
        txtfat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtfatFocusLost(evt);
            }
        });
        txtfat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtfatKeyPressed(evt);
            }
        });

        txtmoney.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        txtmoney.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtmoneyFocusLost(evt);
            }
        });
        txtmoney.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmoneyKeyPressed(evt);
            }
        });

        btnenter.setBackground(new java.awt.Color(153, 255, 153));
        btnenter.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnenter.setForeground(new java.awt.Color(51, 0, 0));
        btnenter.setText("Enter");
        btnenter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnenterActionPerformed(evt);
            }
        });
        btnenter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnenterKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout panel5Layout = new javax.swing.GroupLayout(panel5);
        panel5.setLayout(panel5Layout);
        panel5Layout.setHorizontalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel5Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel35)
                        .addGap(65, 65, 65)
                        .addComponent(jLabel7)
                        .addGap(89, 89, 89)
                        .addComponent(jLabel22)
                        .addGap(70, 70, 70)
                        .addComponent(jLabel34)
                        .addGap(92, 92, 92)
                        .addComponent(jLabel19)
                        .addContainerGap())
                    .addGroup(panel5Layout.createSequentialGroup()
                        .addComponent(txtcard_n0, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(txtsample, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(txtmilk, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(txtfat, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(txtmoney, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnenter, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 20, Short.MAX_VALUE))))
        );
        panel5Layout.setVerticalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addComponent(jLabel7)
                    .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22)
                        .addComponent(jLabel34)
                        .addComponent(jLabel19)))
                .addGap(18, 18, 18)
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtcard_n0, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsample, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtmilk, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtfat, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtmoney, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnenter, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
        txtDate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDateKeyPressed(evt);
            }
        });

        cmbevent.setFont(new java.awt.Font("Bell MT", 1, 18)); // NOI18N
        cmbevent.setForeground(new java.awt.Color(0, 0, 255));
        cmbevent.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Morning", "Evening" }));
        cmbevent.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbeventItemStateChanged(evt);
            }
        });
        cmbevent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbeventActionPerformed(evt);
            }
        });
        cmbevent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbeventKeyPressed(evt);
            }
        });

        lblday1.setFont(new java.awt.Font("Arabic Typesetting", 1, 18)); // NOI18N
        lblday1.setForeground(new java.awt.Color(255, 0, 51));
        lblday1.setText("Day");
        lblday1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblday1MouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblcount.setBackground(new java.awt.Color(255, 255, 255));
        lblcount.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblcount.setText("No");

        lblmilk.setBackground(new java.awt.Color(255, 255, 255));
        lblmilk.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblmilk.setText("Milk");

        lblfat.setBackground(new java.awt.Color(255, 255, 255));
        lblfat.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblfat.setText("Fat");

        lblmoney.setBackground(new java.awt.Color(255, 255, 255));
        lblmoney.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblmoney.setText("Money");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setText("M Total:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                .addGap(84, 84, 84)
                .addComponent(lblcount, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84)
                .addComponent(lblmilk, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addGap(65, 65, 65)
                .addComponent(lblfat, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addGap(52, 52, 52)
                .addComponent(lblmoney, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel5)
                .addComponent(lblcount)
                .addComponent(lblmilk)
                .addComponent(lblfat)
                .addComponent(lblmoney))
        );

        jToggleButton1.setText("V");
        jToggleButton1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jToggleButton1ItemStateChanged(evt);
            }
        });

        datepane.setBackground(new java.awt.Color(204, 204, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lblcount1.setBackground(new java.awt.Color(255, 255, 255));
        lblcount1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblcount1.setText("No");

        lblmilk1.setBackground(new java.awt.Color(255, 255, 255));
        lblmilk1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblmilk1.setText("Milk");

        lblmoney1.setBackground(new java.awt.Color(255, 255, 255));
        lblmoney1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblmoney1.setText("Money");

        jLabel23.setBackground(new java.awt.Color(255, 255, 255));
        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel23.setText("Sales Total:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblcount1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(121, 121, 121)
                .addComponent(lblmilk1, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addGap(119, 119, 119)
                .addComponent(lblmoney1, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addGap(35, 35, 35))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel23)
                .addComponent(lblcount1)
                .addComponent(lblmilk1)
                .addComponent(lblmoney1))
        );

        salesmilk.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        salesmilk.setForeground(new java.awt.Color(0, 0, 255));
        salesmilk.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                salesmilkFocusLost(evt);
            }
        });
        salesmilk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                salesmilkKeyPressed(evt);
            }
        });

        salesmoney.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        salesmoney.setForeground(new java.awt.Color(0, 0, 255));
        salesmoney.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                salesmoneyFocusLost(evt);
            }
        });
        salesmoney.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                salesmoneyKeyPressed(evt);
            }
        });

        tblsales.setAutoCreateRowSorter(true);
        tblsales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblsales.setRowHeight(32);
        tblsales.getTableHeader().setReorderingAllowed(false);
        tblsales.setUpdateSelectionOnSort(false);
        tblsales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblsalesMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblsales);

        btnsenter.setBackground(new java.awt.Color(153, 255, 153));
        btnsenter.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnsenter.setForeground(new java.awt.Color(51, 0, 0));
        btnsenter.setText("E");
        btnsenter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsenterActionPerformed(evt);
            }
        });
        btnsenter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnsenterKeyPressed(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(204, 204, 255));
        jButton11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton11.setForeground(new java.awt.Color(51, 0, 0));
        jButton11.setText("U");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jButton11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton11KeyPressed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(255, 204, 204));
        jButton12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton12.setForeground(new java.awt.Color(51, 0, 0));
        jButton12.setText("D");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jButton12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton12KeyPressed(evt);
            }
        });

        lblday2.setFont(new java.awt.Font("Arabic Typesetting", 1, 18)); // NOI18N
        lblday2.setForeground(new java.awt.Color(0, 0, 255));
        lblday2.setText("Day");
        lblday2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblday2MouseClicked(evt);
            }
        });

        jLabel1.setText("Milk:");

        jLabel2.setText("Money:");

        salesno.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        salesno.setForeground(new java.awt.Color(0, 0, 255));
        salesno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salesnoMouseClicked(evt);
            }
        });
        salesno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                salesnoFocusLost(evt);
            }
        });
        salesno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                salesnoKeyPressed(evt);
            }
        });

        lblname.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        givemoneyam.setAutoCreateRowSorter(true);
        givemoneyam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        givemoneyam.setRowHeight(28);
        givemoneyam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                givemoneyamMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(givemoneyam);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        lblcount2.setBackground(new java.awt.Color(255, 255, 255));
        lblcount2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblcount2.setText("Total");

        lblmilk2.setBackground(new java.awt.Color(255, 255, 255));
        lblmilk2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblmilk2.setText("PM");

        lblmoney2.setBackground(new java.awt.Color(255, 255, 255));
        lblmoney2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblmoney2.setText("Total");

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel24.setText("AM");

        lblmilk3.setBackground(new java.awt.Color(255, 255, 255));
        lblmilk3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblmilk3.setForeground(new java.awt.Color(51, 51, 255));
        lblmilk3.setText("SUM");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(138, 138, 138)
                .addComponent(lblcount2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lblmilk3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblmilk2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(118, 118, 118)
                .addComponent(lblmoney2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel24)
                .addComponent(lblcount2)
                .addComponent(lblmilk3)
                .addComponent(lblmilk2)
                .addComponent(lblmoney2))
        );

        givemoneypm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        givemoneypm.setRowHeight(28);
        givemoneypm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                givemoneypmMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(givemoneypm);

        txtgivemoney.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtgivemoney.setForeground(new java.awt.Color(0, 0, 255));
        txtgivemoney.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtgivemoneyFocusLost(evt);
            }
        });
        txtgivemoney.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtgivemoneyKeyPressed(evt);
            }
        });

        jLabel3.setText("Money:");

        jButton13.setBackground(new java.awt.Color(204, 255, 204));
        jButton13.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton13.setForeground(new java.awt.Color(51, 0, 0));
        jButton13.setText("G");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jButton13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton13KeyPressed(evt);
            }
        });

        jButton14.setBackground(new java.awt.Color(204, 255, 204));
        jButton14.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton14.setForeground(new java.awt.Color(51, 0, 0));
        jButton14.setText("U");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jButton14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton14KeyPressed(evt);
            }
        });

        jButton15.setBackground(new java.awt.Color(255, 204, 204));
        jButton15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton15.setForeground(new java.awt.Color(51, 0, 0));
        jButton15.setText("D");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jButton15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton15KeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("Enter Daily Customer Milk Of");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblday1)
                                .addGap(15, 15, 15)
                                .addComponent(lblday2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jToggleButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(datepane, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbevent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(salesno, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addGap(2, 2, 2)
                                .addComponent(salesmilk, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(salesmoney, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnsenter)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton11))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton14))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(10, 10, 10)
                                .addComponent(lblname, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtgivemoney, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel16)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtfromdate, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel18)
                                    .addGap(29, 29, 29)
                                    .addComponent(txttodate, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblday1)
                                .addComponent(jToggleButton1)
                                .addComponent(lblday2))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnsenter, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cmbevent, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(datepane, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(salesmilk, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(salesmoney, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(salesno, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton13)
                            .addComponent(jButton14))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblname, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtgivemoney, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(21, 21, 21)
                        .addComponent(panel5, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(panel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtfromdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)
                            .addComponent(txttodate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
public void txteditabletrue()
{
                        txtsample.setEditable(true);
                        txtmilk.setEditable(true);
                        txtfat.setEditable(true);
                        txtmoney.setEditable(true);
}
public void txteditablefalse()
{
                       
                                txtsample.setEditable(false);
                                 txtmilk.setEditable(false);
                                 txtfat.setEditable(false);
                                 txtmoney.setEditable(false);
}
    public void Validatecard()
    {
        try
        {
            con=DriverManager.getConnection(host);
            stmt=con.createStatement();
            stmt.executeQuery("SELECT  CARD_NUM,DATE,SAMPLE_N0,MILK,FAT,MONEY,SESSION FROM DAILYDATA WHERE CARD_NUM="+txtcard_n0.getText());
            res=stmt.getResultSet();  
                while(res.next())
                {
                    Object Date=res.getString("DATE");
                    Object session=res.getString("SESSION");
                    Object cardn0=res.getString("CARD_NUM");
                    String datec=txtDate.getText();
                    Object sessionc=cmbevent.getSelectedItem();
                    String cardn0c=txtcard_n0.getText();
                    if((Date.equals(datec))&&(session.equals(sessionc))&&(cardn0.equals(cardn0c)))
                    {
                        txteditablefalse();
                        lblalert.setText("Data Already Entered on ("+txtDate.getText()+"--"+cmbevent.getSelectedItem()+")");
                        txteditablefalse();
                    }  
                   else
                    {    
                    }
                 }
            res.close();
            stmt.close();
            con.close();
                
        }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(SampleFrame.this,"Enter Customer Data to get Name");
            lblname.setText("");
        }
    }
   
    public void CustomerName()
    {
            try{
                     con=DriverManager.getConnection(host);
                     stmt=con.createStatement();
                     java.sql.Timestamp sqlDate= new java.sql.Timestamp(new java.util.Date().getTime());
                     stmt.executeQuery("SELECT CARD_NUM,CUST_NAME FROM CUSTOMERS where CARD_NUM="+txtcard_n0.getText()); 
                     res=stmt.getResultSet();
                             if(res.next())
                                 {  
                                    String name=res.getString("CUST_NAME");
                                    lblname.setText(name);
                                    txteditabletrue();
                                    lblname.setToolTipText("Editing Possible in Registration Page"); 
                                       if(name.equals(""))
                                       {
                                           txteditabletrue();
                                           lblname.setText("Name is empty");
                                       }
                                 }
                             else
                             {
                                 lblname.setText("");
                                 txteditablefalse();
                                 lblalert.setText("No Customer Data Found");
                              
                             }
                              res.close();
                              stmt.close();
                              con.close();
                }
             catch(SQLException err)
                 {
                     JOptionPane.showMessageDialog(SampleFrame.this,"Enter Customer card number");
                    }
            }  
    public void countsessiondata()
    {
        try
        {
            con=DriverManager.getConnection(host);
            stmt=con.createStatement();
            java.sql.Timestamp sqlDate= new java.sql.Timestamp(new java.util.Date().getTime());
            Calendar cal=Calendar.getInstance();
            String date=txtDate.getText();
            stmt.executeQuery("SELECT CARD_NUM,SAMPLE_N0,DATE,SESSION FROM DAILYDATA"); 
            res=stmt.getResultSet();
            int i=1;
            String ival1=Integer.toString(i);
            txtsample.setText(ival1);
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
                txtsample.setText(ival);
            }
            res.close();
            stmt.close();
            con.close();
        }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(SampleFrame.this,err);
        }
    }
   public void InsertData()
    {
        try{
             con=DriverManager.getConnection(host);
             stmt=con.createStatement();
             java.sql.Timestamp sqlDate= new java.sql.Timestamp(new java.util.Date().getTime());
             if(txtfat.getText().equals(""))
             {
                txtfat.setText("0"); 
             }
             if(txtmoney.getText().equals(""))
             {
                 txtmoney.setText("0");
             }
             stmt.executeUpdate("insert into DAILYDATA(CARD_NUM,SAMPLE_N0,MILK,FAT,MONEY,DATE,SESSION,TIMESTAMP) values("+txtcard_n0.getText()+","+txtsample.getText()+","+txtmilk.getText()+",'"+txtfat.getText()+"','"+txtmoney.getText()+"','"+txtDate.getText()+"','"+cmbevent.getSelectedItem()+"','"+sqlDate+"')");
             lblalert.setText("Daily Data of Customer "+lblname.getText()+" Filled Successfully");
             //JOptionPane.showMessageDialog(SampleFrame.this,"Daily Data of Customer "+lblname.getText()+" Filled Successfully.");
             txtmilk.setText("");
             txtfat.setText("");
             txtmoney.setText("");
             stmt.close();
             con.close();
          }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(SampleFrame.this,err);
        }
    }
   public void validateme()
         {
             try
             {
                 
                 con=DriverManager.getConnection(host);
                     stmt=con.createStatement();
                     stmt.executeQuery("SELECT * FROM DAILYDATA"); 
                     res=stmt.getResultSet();  
                     if(!res.next())
                     {
                         InsertData();
                     }
             }
              catch(SQLException err)
            {
            JOptionPane.showMessageDialog(SampleFrame.this,err);
            }
    }
      public void Validatecard1()
    {
           try
                 {
                     con=DriverManager.getConnection(host);
                     stmt=con.createStatement();
                     stmt.executeQuery("SELECT * FROM DAILYDATA"); 
                     res=stmt.getResultSet();
                         if(res.next())
                        {
                            Object Date=res.getString("DATE");
                            Object session=res.getString("SESSION");
                            Object cardn0=res.getString("CARD_NUM");
                            String datec=txtDate.getText();
                            Object sessionc=cmbevent.getSelectedItem();
                            String cardn0c=txtcard_n0.getText();
                            Object sample=txtsample.getText();
                            String samplec=res.getString("SAMPLE_N0");
                            if((Date.equals(datec))&&(session.equals(sessionc))&&(cardn0.equals(cardn0c))&&(samplec.equals(sample)))
                                {
                                    JOptionPane.showMessageDialog(SampleFrame.this,"Data Entry is not Possible");
                                
                                } 
                            else if((Date.equals(datec))&&(session.equals(sessionc))&&(cardn0.equals(cardn0c)))
                                {
                                    JOptionPane.showMessageDialog(SampleFrame.this,"Data Entry is not Possible");
                                } 
                            else 
                            {
                                InsertData();
                            }
                        }
                            validateme();
                            res.close();
                            stmt.close();
                            con.close();
                }
         
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(SampleFrame.this,err);
        }
    }
      public void UpdateSalesData()
    {
        if(salesmilk.getText().equals(""))
        {
           JOptionPane.showMessageDialog(SampleFrame.this,"Please Select row to update");
        }
        else
        {
           try
                 {
                     con=DriverManager.getConnection(host);
                     stmt=con.createStatement();
                   
                     //int date2=Integer.parseInt(date1);
                    
//                     if((txtmdsession.getText()).equals("Morning"))
//                             { 
//                                JOptionPane.showMessageDialog(SampleFrame.this,"Morning"); 
//                         }
//                     else 
//                     {
//                         JOptionPane.showMessageDialog(SampleFrame.this,"Evening"); 
//                   
                     String date=txtDate.getText();
                     String session=cmbevent.getSelectedItem().toString();
                  String date1=salesno.getText();
                     stmt.executeUpdate("UPDATE DAILYSALES1 SET SAMPLE="+salesno.getText()+",MILK='"+salesmilk.getText()+"',MONEY='"+salesmoney.getText()+"' WHERE SAMPLE="+date1); 
                     JOptionPane.showMessageDialog(SampleFrame.this,"Data Updated Successfully");
                      stmt.close();
                      con.close();
                      btnsenter.setEnabled(true);
                      
                }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(SampleFrame.this,err);
        }
    }}
      public void UpdateData()
    {
           try
                 {
                     con=DriverManager.getConnection(host);
                     stmt=con.createStatement();
                    String date1=txtmddate.getText().toString();
                     stmt.executeUpdate("UPDATE DAILYDATA SET MILK="+txtmdmilk.getText()+",FAT='"+txtmdfat.getText()+"',MONEY='"+txtmdmoney.getText()+"' WHERE DATE='"+date1+"' and SESSION='"+txtmdsession.getText()+"' and CARD_NUM="+txtmdcardn0.getText()); 
                     JOptionPane.showMessageDialog(SampleFrame.this,"Data Updated Successfully");
                      stmt.close();
                      con.close();
                }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(SampleFrame.this,err);
        }
    }
        public void UpdatemoneyData()
    {
           try
                 {
                     con=DriverManager.getConnection(host);
                     stmt=con.createStatement();
                      String date1=txtmddate.getText().toString();
                     stmt.executeUpdate("UPDATE DAILYSALES SET MONEY='"+txtgivemoney.getText()+"' WHERE DATE='"+date1+"' and SESSION='"+txtmdsession.getText()+"' and CARD_NUM="+txtmdcardn0.getText()); 
                     JOptionPane.showMessageDialog(SampleFrame.this,"Data Of money given Updated Successfully");
                      stmt.close();
                      con.close();
                }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(SampleFrame.this,err);
        }
    }

   
      class ObservingTextField extends JTextField implements Observer {
    public void update(Observable o, Object arg) {
        final String DATE_FORMAT_NOW="yyyy-MM-dd";
        Calendar calendar = (Calendar) arg;
        SimpleDateFormat sdf=new SimpleDateFormat(DATE_FORMAT_NOW);
        
        DatePicker dp = (DatePicker) o;
        txtDate.setText(sdf.format(calendar.getTime()));
    }
   }
      public void countsessioncansales()
    {
        try
        {
            con=DriverManager.getConnection(host);
            stmt=con.createStatement();
            java.sql.Timestamp sqlDate= new java.sql.Timestamp(new java.util.Date().getTime());
            Calendar cal=Calendar.getInstance();
            String date=txtDate.getText();
            
            stmt.executeQuery("SELECT SAMPLE,DATE,SESSION FROM DAILYSALES1"); 
            res=stmt.getResultSet();
            int i=1;
            String ival1=Integer.toString(i);
            salesno.setText(ival1);
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
                salesno.setText(ival);
            }
            res.close();
            stmt.close();
            con.close();
        }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(SampleFrame.this,err);
        }
    }
  public void salesorgive()
   {
       if(salesmilk.getText().equals("") && salesmoney.getText().equals(""))
       {
           JOptionPane.showMessageDialog(SampleFrame.this,"Please Enter Milk amount");
       }
       else
       {
        try{
             con=DriverManager.getConnection(host);
             stmt=con.createStatement();
             java.sql.Timestamp sqlDate= new java.sql.Timestamp(new java.util.Date().getTime());
             stmt.executeUpdate("insert into DAILYSALES1(SAMPLE,MONEY,MILK,DATE,SESSION,TIMESTAMP) values("+salesno.getText()+",'"+salesmoney.getText()+"','"+salesmilk.getText()+"','"+txtDate.getText()+"','"+cmbevent.getSelectedItem()+"','"+sqlDate+"')");
             lblalert.setText("Milk Successfully Sold");
             salesmilk.setText("");
             salesmoney.setText("");
             stmt.close();
             con.close();
        }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(SampleFrame.this,err);
        }  
   }
   }   
        public void DeletegiveData()
    {
           try
                 {
                     con=DriverManager.getConnection(host);
                     stmt=con.createStatement();
                      String date1=txtmddate.getText().toString();
                    //  JOptionPane.showMessageDialog(SampleFrame.this,"YES");
                    stmt.executeUpdate("DELETE FROM DAILYSALES WHERE DATE='"+date1+"' and SESSION='"+txtmdsession.getText()+"' and CARD_NUM="+txtmdcardn0.getText()); 
                     JOptionPane.showMessageDialog(SampleFrame.this,"Data Of given Deleted Successfully");
                      stmt.close();
                      con.close();
                }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(SampleFrame.this,err);
        }
    }
     
      public void DeleteData()
    {
           try
                 {
                     con=DriverManager.getConnection(host);
                     stmt=con.createStatement();
                    String date1=txtmddate.getText().toString();
                     stmt.executeUpdate("DELETE FROM DAILYDATA WHERE DATE='"+date1+"' and SESSION='"+txtmdsession.getText()+"' and CARD_NUM="+txtmdcardn0.getText()); 
                     JOptionPane.showMessageDialog(SampleFrame.this,"Data Deleted Successfully");
                      stmt.close();
                      con.close();
                }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(SampleFrame.this,err);
        }
    }
     
        public void DeleteSalesData()
    {
        if(salesmilk.getText().equals(""))
        {
           JOptionPane.showMessageDialog(SampleFrame.this,"Please Select row to delete");
        }
        else
        {
           try
                 {
                     con=DriverManager.getConnection(host);
                     stmt=con.createStatement();
                     String date1=txtmddate.getText().toString();
                     String val1=salesno.getText();
                     String val2=salesmoney.getText();
                     String val3=salesmilk.getText();
                     stmt.executeUpdate("DELETE FROM DAILYSALES1 WHERE MONEY='"+val2+"' and MILK='"+val3+"' and SAMPLE="+val1); 
                     JOptionPane.showMessageDialog(SampleFrame.this,"Data Deleted Successfully");
                      stmt.close();
                      con.close();
                     btnsenter.setEnabled(true);
                }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(SampleFrame.this,err);
        }
        }
        
    }
          public void DeleteSalesgiveData()
    {
        if(txtgivemoney.getText().equals(""))
        {
           JOptionPane.showMessageDialog(SampleFrame.this,"Please Select row to delete");
        }
        else
        {
           try
                 {
                     con=DriverManager.getConnection(host);
                     stmt=con.createStatement();
                     String date1=txtmddate.getText().toString();
                     String dateses=txtmdsession.getText().toString();
                     String val1=salesno.getText();
                     String val2=txtgivemoney.getText();
                     String val3=salesmilk.getText();
                     stmt.executeUpdate("DELETE FROM DAILYSALES WHERE MONEY='"+val2+"',DATE='"+date1+"' and SESSION='"+dateses+"' "); 
                     JOptionPane.showMessageDialog(SampleFrame.this,"Data Deleted Successfully");
                      stmt.close();
                      con.close();
                     btnsenter.setEnabled(true);
                }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(SampleFrame.this,err);
        }
        }
        
    }
     public void filltablemorning()
     {
          tblmorning.setModel(dtm);
          String dateget=txtDate.getText();
          Date d1=null;
          SimpleDateFormat sdf4=new SimpleDateFormat(DATE_FORMAT_NOW);
           try
           {
            d1=sdf4.parse(dateget);
            tblmorning.setAutoResizeMode(tblmorning.AUTO_RESIZE_OFF);               
            int columncount=tblmorning.getColumnCount();
            if(columncount<2)
                 {
                     dtm.addColumn("Date");
                     dtm.addColumn("S.N0");
                     dtm.addColumn("Milk");
                     dtm.addColumn("Fat");
                     dtm.addColumn("Money");
                 
           tblmorning.getColumnModel().getColumn(0).setPreferredWidth(80);
           tblmorning.getColumnModel().getColumn(1).setPreferredWidth(40);
           tblmorning.getColumnModel().getColumn(2).setPreferredWidth(60);
           tblmorning.getColumnModel().getColumn(3).setPreferredWidth(70); 
                 }  
                          dtm.setRowCount(0); 
                          for(int j=0;j<10;j++)
                            {
                            Calendar cal4=Calendar.getInstance();
                            cal4.setTime(d1);
                            cal4.add(Calendar.DATE,-j);
                            dtm.addRow(new Object[]{sdf4.format(cal4.getTime())});
                           }
                          
                   DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
                    rightRenderer.setHorizontalAlignment( JLabel.CENTER );
                    tblmorning.setShowVerticalLines(false);
                    tblmorning.setShowHorizontalLines(false);
                    tblmorning.getColumnModel().getColumn(0).setCellRenderer( rightRenderer );
                    tblmorning.getColumnModel().getColumn(1).setCellRenderer( rightRenderer );
                    tblmorning.getColumnModel().getColumn(2).setCellRenderer( rightRenderer );
                    tblmorning.getColumnModel().getColumn(3).setCellRenderer( rightRenderer );
                    tblmorning.getColumnModel().getColumn(4).setCellRenderer( rightRenderer );
                    tblmorning.setAutoCreateColumnsFromModel(false);
     }
      catch(Exception e)
                            {
                                
                            }
     }
     public void toalcalc()
     {
      String moneyam=lblcount2.getText();
      String moneypm= lblmoney2.getText();
      Double moneyamc=Double.parseDouble(moneyam);
      Double moneypmc=Double.parseDouble(moneypm);
      Double diiferenceamount=moneyamc+moneypmc;
      String differencecaldc=df.format(diiferenceamount);
      lblmilk3.setText(differencecaldc);
     }
     public void filltablegivemoneypm()
     {
          givemoneypm.setModel(dtm7);
          String dateget=txtDate.getText();
          Date d1=null;
          SimpleDateFormat sdf4=new SimpleDateFormat(DATE_FORMAT_NOW);
           try
           {
            d1=sdf4.parse(dateget);
            givemoneypm.setAutoResizeMode(givemoneypm.AUTO_RESIZE_OFF);               
            int columncount=givemoneypm.getColumnCount();
            if(columncount<2)
                 {
                     dtm7.addColumn("Date");
                     dtm7.addColumn("Money");
                 
           givemoneypm.getColumnModel().getColumn(0).setPreferredWidth(150);
           givemoneypm.getColumnModel().getColumn(1).setPreferredWidth(150);
           givemoneypm.setAutoCreateColumnsFromModel(false);
                 }  
                          dtm7.setRowCount(0); 
                          for(int j=0;j<10;j++)
                            {
                            Calendar cal4=Calendar.getInstance();
                            cal4.setTime(d1);
                            cal4.add(Calendar.DATE,-j);
                            dtm7.addRow(new Object[]{sdf4.format(cal4.getTime())});
                           }
                          
                   DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
                    rightRenderer.setHorizontalAlignment( JLabel.CENTER );
                    givemoneypm.setShowVerticalLines(false);
                    givemoneypm.setShowHorizontalLines(false);
                    givemoneypm.getColumnModel().getColumn(0).setCellRenderer( rightRenderer );
                    givemoneypm.getColumnModel().getColumn(1).setCellRenderer( rightRenderer );
                    givemoneypm.setAutoCreateColumnsFromModel(false);
     }
      catch(Exception e)
                            {
                                
                            }
     }
     public void filltablegivemoney()
     {
          givemoneyam.setModel(dtm8);
          String dateget=txtDate.getText();
          Date d1=null;
          SimpleDateFormat sdf4=new SimpleDateFormat(DATE_FORMAT_NOW);
           try
           {
            d1=sdf4.parse(dateget);
            givemoneyam.setAutoResizeMode(givemoneyam.AUTO_RESIZE_OFF);               
            int columncount=givemoneyam.getColumnCount();
            if(columncount<2)
                 {
                     dtm8.addColumn("Date");
                     dtm8.addColumn("Money");
         
          givemoneyam.getColumnModel().getColumn(0).setPreferredWidth(150);
          givemoneyam.getColumnModel().getColumn(1).setPreferredWidth(150);
         
                 }  
                          dtm8.setRowCount(0); 
                          for(int j=0;j<10;j++)
                            {
                            Calendar cal4=Calendar.getInstance();
                            cal4.setTime(d1);
                            cal4.add(Calendar.DATE,-j);
                            dtm8.addRow(new Object[]{sdf4.format(cal4.getTime())});
                           }
                          
                   DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
                    rightRenderer.setHorizontalAlignment( JLabel.CENTER );
                    givemoneyam.setShowVerticalLines(false);
                    givemoneyam.setShowHorizontalLines(false);
                    givemoneyam.getColumnModel().getColumn(0).setCellRenderer( rightRenderer );
                    givemoneyam.getColumnModel().getColumn(1).setCellRenderer( rightRenderer );
                    givemoneyam.setAutoCreateColumnsFromModel(false);
     }
      catch(Exception e)
                            {
                                
                            }
     }
     public void filltableevening()
     {
          tblevening.setModel(dtm3);
          String dateget=txtDate.getText();
          Date d1=null;
          SimpleDateFormat sdf4=new SimpleDateFormat(DATE_FORMAT_NOW);
           try
           {
             d1=sdf4.parse(dateget);
             tblevening.setAutoResizeMode(tblevening.AUTO_RESIZE_OFF);               
             int columncount=tblevening.getColumnCount();
              if(columncount<2)
                 {
                     dtm3.addColumn("Date");
                     dtm3.addColumn("S.N0");
                     dtm3.addColumn("Milk");
                     dtm3.addColumn("Fat");
                     dtm3.addColumn("Money");
                 
           tblevening.getColumnModel().getColumn(0).setPreferredWidth(80);
           tblevening.getColumnModel().getColumn(1).setPreferredWidth(40);
           tblevening.getColumnModel().getColumn(2).setPreferredWidth(60);
           tblevening.getColumnModel().getColumn(3).setPreferredWidth(70);   
                 }  
                          dtm3.setRowCount(0); 
                          for(int j=0;j<10;j++)
                            {
                            Calendar cal4=Calendar.getInstance();
                            cal4.setTime(d1);
                            cal4.add(Calendar.DATE,-j);
                            dtm3.addRow(new Object[]{sdf4.format(cal4.getTime())}); 
                           }
               DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setHorizontalAlignment( JLabel.CENTER );
            tblevening.setShowVerticalLines(false);
            tblevening.setShowHorizontalLines(false);
            tblevening.getColumnModel().getColumn(0).setCellRenderer( rightRenderer );
            tblevening.getColumnModel().getColumn(1).setCellRenderer( rightRenderer );
            tblevening.getColumnModel().getColumn(2).setCellRenderer( rightRenderer );
            tblevening.getColumnModel().getColumn(3).setCellRenderer( rightRenderer );
            tblevening.getColumnModel().getColumn(4).setCellRenderer( rightRenderer );
            tblevening.setAutoCreateColumnsFromModel(false);
     }
      catch(Exception e)
                            {
                                
                            }
     }
       public void filltablegivemoneyam()
    { 
      try
          
      {
          givemoneyam.setModel(dtm8);
          givemoneyam.setAutoCreateColumnsFromModel( false );  
          con=DriverManager.getConnection(host);
          stmt=con.createStatement();
          stmt.executeQuery("SELECT CARD_NUM,MONEY,DATE,SESSION FROM DAILYSALES where CARD_NUM="+txtcard_n0.getText());
          res=stmt.getResultSet();
          givemoneyam.setAutoResizeMode(givemoneyam.AUTO_RESIZE_OFF);
         
          Double totalmoneyam=0.0;
           for(int j=0;j<=9;j++)
                     {
                         givemoneyam.getModel().setValueAt("",j,1);
                     } 
                     while(res.next())
                    {
                    String value1=res.getString("DATE");
                    String session=res.getString("SESSION");
                    String csession="Morning";
                    Object Money=res.getString("MONEY");
         
                     for(int k=0;k<=9;k++)
                     {
                          //givemoneyam.getModel().setValueAt("0",k,1); 
                         if((session.equals(csession))&&(value1.equals(givemoneyam.getValueAt(k,0))))
                         {
                                
                                 givemoneyam.getModel().setValueAt(Money,k,1); 
                                 
                         }
                          
                    }
                    
                 }
            res.close();
            stmt.close();
            con.close();
                 }
           
             catch(SQLException err)
                {
            JOptionPane.showMessageDialog(SampleFrame.this,err);
                }
    }
        public void filltablegivemoneyampm()
    { 
      try
          
      {
          givemoneypm.setModel(dtm7);
          givemoneypm.setAutoCreateColumnsFromModel( false );  
          con=DriverManager.getConnection(host);
          stmt=con.createStatement();
          stmt.executeQuery("SELECT CARD_NUM,MONEY,DATE,SESSION FROM DAILYSALES where CARD_NUM="+txtcard_n0.getText());
          res=stmt.getResultSet();
          givemoneypm.setAutoResizeMode(givemoneypm.AUTO_RESIZE_OFF);
          for(int j=0;j<=9;j++)
                     {
                         givemoneypm.getModel().setValueAt("",j,1);
                     } 
                     while(res.next())
                    {
                    String value1=res.getString("DATE");
                    String session=res.getString("SESSION");
                    String csession="Evening";
                    Object Money=res.getString("MONEY");
                     
                     for(int k=0;k<=9;k++)
                     {
                           
                         if((session.equals(csession))&&(value1.equals(givemoneypm.getValueAt(k,0))))
                         {
                              
                                 givemoneypm.getModel().setValueAt(Money,k,1);    
                                 
                         }
                    }
                 }
            res.close();
            stmt.close();
            con.close();
                 }
           
             catch(SQLException err)
                {
            JOptionPane.showMessageDialog(SampleFrame.this,err);
                }
    }
        public void filltablevaluesmorning()
    { 
      try
          
      {
          tblmorning.setModel(dtm);
          tblmorning.setAutoCreateColumnsFromModel( false );  
          con=DriverManager.getConnection(host);
          stmt=con.createStatement();
          stmt.executeQuery("SELECT CARD_NUM,SAMPLE_N0,MILK,FAT,MONEY,DATE,SESSION FROM DAILYDATA where CARD_NUM="+txtcard_n0.getText());
          res=stmt.getResultSet();
          tblmorning.setAutoResizeMode(tblmorning.AUTO_RESIZE_OFF);
                     while(res.next())
                    {
                    String value1=res.getString("DATE");
                    Object S_n0=res.getInt("SAMPLE_N0");
                    Object Milk=res.getDouble("MILK");
                    Object Fat=res.getString("FAT");
                    String session=res.getString("SESSION");
                    String csession="Morning";
                    Object Money=res.getString("MONEY");
         
                     for(int k=0;k<=9;k++)
                     {
                         if((session.equals(csession))&&(value1.equals(tblmorning.getValueAt(k,0))))
                         {
                                 tblmorning.getModel().setValueAt(S_n0,k,1);
                                 tblmorning.getModel().setValueAt(Milk,k,2);
                                 tblmorning.getModel().setValueAt(Fat,k,3);
                                 tblmorning.getModel().setValueAt(Money,k,4);                                
                         }
                    }
                 }
            res.close();
            stmt.close();
            con.close();
                 }
           
             catch(SQLException err)
                {
            JOptionPane.showMessageDialog(SampleFrame.this,err);
                }
    }
        public void filltablevaluesevening()
    { 
      try
        {
            con=DriverManager.getConnection(host);
            stmt=con.createStatement();
            stmt.executeQuery("SELECT CARD_NUM,SAMPLE_N0,MILK,FAT,MONEY,DATE,SESSION FROM DAILYDATA where CARD_NUM="+txtcard_n0.getText());
            res=stmt.getResultSet();
            tblevening.setModel(dtm3);
            tblevening.setAutoResizeMode(tblevening.AUTO_RESIZE_OFF);
            while(res.next())
             {
                    String value1=res.getString("DATE");
                    Object S_n0=res.getInt("SAMPLE_N0");
                    Object Milk=res.getDouble("MILK");
                    Object Fat=res.getString("FAT");
                    String session=res.getString("SESSION");
                    String csession="Evening";
                    Object Money=res.getString("MONEY");
                     for(int k=0;k<=9;k++)
                     {
                         if((csession.equals(session))&&(value1.equals(tblevening.getValueAt(k,0))))
                         {
                                 tblevening.getModel().setValueAt(S_n0,k,1);
                                 tblevening.getModel().setValueAt(Milk,k,2);
                                 tblevening.getModel().setValueAt(Fat,k,3);
                                 tblevening.getModel().setValueAt(Money,k,4);
                         }
                     }
                 }
            res.close();
            stmt.close();
            con.close();
                 }
           
             catch(SQLException err)
                {
            JOptionPane.showMessageDialog(SampleFrame.this,err);
                }
    }
        public void totalcalcdam()
        {
            int k=0;
            Double totalmoneyam=0.0;
            for (k=0;k<10;k++)
            {
                   //   J11   OptionPane.showMessageDialog(SampleFrame.this,givemoneyam.getValueAt(k, 1));
                if(givemoneyam.getValueAt(k, 1).equals(""))
                {
                      //JOptionPane.showMessageDialog(SampleFrame.this,"YES");
                }
                else{
                    String moneysumam=givemoneyam.getValueAt(k, 1).toString();
                    Double moneysumam1=Double.parseDouble(moneysumam);
                    totalmoneyam=totalmoneyam+moneysumam1;
                    String totalmoney1=df.format(totalmoneyam);
                 // JOptionPane.showMessageDialog(SampleFrame.this,totalmoney1);
                    lblcount2.setText(totalmoney1);
                }
//                    
        }
        }
        public void totalcalcdpm()
        {
            int k=0;
            Double totalmoneyam=0.0;
            for (k=0;k<10;k++)
            {
                   //   J11   OptionPane.showMessageDialog(SampleFrame.this,givemoneyam.getValueAt(k, 1));
                if(givemoneypm.getValueAt(k, 1).equals(""))
                {
                      //JOptionPane.showMessageDialog(SampleFrame.this,"YES");
                }
                else{
                    String moneysumam=givemoneypm.getValueAt(k, 1).toString();
                    Double moneysumam1=Double.parseDouble(moneysumam);
                    totalmoneyam=totalmoneyam+moneysumam1;
                    String totalmoney1=df.format(totalmoneyam);
                  //  JOptionPane.showMessageDialog(SampleFrame.this,totalmoney1);
                    lblmoney2.setText(totalmoney1);
                }
//                    
        }
        }
    public void moneycal()
        {
        
            
        if(txtfat.getText().equals(""))
            {
            }
    else
    {
        String value=txtfat.getText();
        Double value_1=Double.parseDouble(value);
        Double[] myList = {5.0,5.0,5.1,5.2,5.2,5.2,5.3,5.2,5.4,5.5,5.5,5.5,5.6,5.5,5.7,5.8,5.8,5.8,5.9,5.8,
                           6.0,6.1,6.1,6.1,6.2,6.1,6.3,6.4,6.4,6.4,6.5,6.4,6.6,6.7,6.7,6.7,6.8,6.7,6.9,7.0,
                           7.0,7.0,7.1,7.0,7.2,7.3,7.3,7.3,7.4,7.3,7.5,7.6,7.6,7.6,7.7,7.6,7.8,7.9,7.9,
                           7.9,8.0,7.9,8.1,8.2,8.2,8.2,8.3,8.2,8.4,8.5,8.5,8.5,8.6,8.5,8.7,8.8,8.8,8.8,8.9,
                           8.8,9.0,9.1,9.1,9.1,9.2,9.1,9.3,9.4,9.4,9.4,9.5,9.4,9.6,9.7,9.7,9.7,9.8,9.7,9.9,
                           10.0,10.0,10.0,10.1,10.0,10.2,10.3,10.3,10.3,10.4,10.3,10.5,10.6,10.6,10.6,10.7,
                           10.6};
        if (value_1 < 5.0) {
            val_2 = 5.0;
        }
        else if (value_1 > 10.7)
        {
            val_2 = 10.7;
        }
        else
        {
        for(int i=0;i<myList.length;i++)
        {
            if (Double.compare(value_1, myList[i]) == 0)
            {
            
             val_2 = myList[i+1];
            break;
            }
         i++;   
        }
        }
        String value2=txtmilk.getText();
        Double value_2=Double.parseDouble(value2);
        String value3=lblday1.getText();
        Double value_3=Double.parseDouble(value3);
        Double result=((value_3)*val_2)/10;
        Double f_result=result*value_2;
        String fin_money=df.format(f_result);
        txtmoney.setText(fin_money);
    }
}
    public void DisplayMilkRate()
     {
         try
         {
          con=DriverManager.getConnection(host);
          stmt=con.createStatement();
          stmt.executeQuery("SELECT * FROM Data_Logic WHERE ID=1"); 
          res=stmt.getResultSet();
              while(res.next())
                     {
                         String  moneyval=res.getString("D_Val");
                         lblday1.setText(moneyval);
                     }
           }
                     catch(SQLException err)
                     {
                         
                     }
     }
    public void DisplaySalesRate()
     {
         try
         {
          con=DriverManager.getConnection(host);
          stmt=con.createStatement();
          stmt.executeQuery("SELECT * FROM Data_Logic WHERE ID=2"); 
          res=stmt.getResultSet();
             while(res.next())
                  {
                     String  moneyval1=res.getString("D_Val");
                         lblday2.setText(moneyval1);
                     }
         }
         catch(SQLException err)
                     {
                         
                     }
     }
public void moneycal1()
 {
    if(txtmdfat.getText().equals(""))
    {
        
    }
    else
    {
        String value=txtmdfat.getText();
        Double value_1=Double.parseDouble(value);
        Double[] myList = {5.0,5.0,5.1,5.2,5.2,5.2,5.3,5.2,5.4,5.5,5.5,5.5,5.6,5.5,5.7,5.8,5.8,5.8,5.9,5.8,
                           6.0,6.1,6.1,6.1,6.2,6.1,6.3,6.4,6.4,6.4,6.5,6.4,6.6,6.7,6.7,6.7,6.8,6.7,6.9,7.0,
                           7.0,7.0,7.1,7.0,7.2,7.3,7.3,7.3,7.4,7.3,7.5,7.6,7.6,7.6,7.7,7.6,7.8,7.9,7.9,
                           7.9,8.0,7.9,8.1,8.2,8.2,8.2,8.3,8.2,8.4,8.5,8.5,8.5,8.6,8.5,8.7,8.8,8.8,8.8,8.9,
                           8.8,9.0,9.1,9.1,9.1,9.2,9.1,9.3,9.4,9.4,9.4,9.5,9.4,9.6,9.7,9.7,9.7,9.8,9.7,9.9,
                           10.0,10.0,10.0,10.1,10.0,10.2,10.3,10.3,10.3,10.4,10.3,10.5,10.6,10.6,10.6,10.7,
                           10.6};
        if (value_1 < 5.0) {
            val_3 = 5.0;
        }
        else if (value_1 > 10.7)
        {
            val_3 = 10.7;
        }
        else
        {
        for(int i=0;i<myList.length;i++)
        {
            if (Double.compare(value_1, myList[i]) == 0)
            {
            
             val_3 = myList[i+1];
            break;
            }
         i++;   
        }
        }
        
        String value2=txtmdmilk.getText();
        Double value_2=Double.parseDouble(value2);
        String value3=lblday1.getText();
        Double value_3=Double.parseDouble(value3);
        Double result=((value_3)*val_3)/10;
        Double f_result=result*value_2;
        df.format(f_result);
        String fin_money=df.format(f_result);;
        txtmdmoney.setText(fin_money);
    }
}
    private void tblmorningMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblmorningMouseClicked
      int row=tblmorning.getSelectedRow();
      try
      {
       String valu1=tblmorning.getValueAt(row,0).toString();
       String valu2=tblmorning.getValueAt(row,1).toString();
       String valu3=tblmorning.getValueAt(row,2).toString();
       String valu4=tblmorning.getValueAt(row,3).toString();
       String valu5=tblmorning.getValueAt(row,4).toString();
       txtmddate.setText(valu1);
       txtmdsession.setText("Morning");
       txtmdcardn0.setText(txtcard_n0.getText());
       txtmdsn0.setText(valu2);
       txtmdmilk.setText(valu3);
       txtmdfat.setText(valu4);
       txtmdmoney.setText(valu5);
      //  txtmdsn0.setEditable(true);
       txtmdcardn0.setEditable(true);
       txtmdmilk.setEditable(true);
       txtmdfat.setEditable(true);
       txtmdmoney.setEditable(true);
      }
      
       catch(NullPointerException err)
      {
          JOptionPane.showMessageDialog(SampleFrame.this,"Please Fill Details of Customer on this day");
            
       }
    }//GEN-LAST:event_tblmorningMouseClicked

    private void tbleveningMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbleveningMouseClicked

        try
        {
       int row=tblevening.getSelectedRow();
       String valu1=tblevening.getValueAt(row,0).toString();
       String valu2=tblevening.getValueAt(row,1).toString();
       String valu3=tblevening.getValueAt(row,2).toString();
       String valu4=tblevening.getValueAt(row,3).toString();
       String valu5=tblevening.getValueAt(row,4).toString();
       txtmddate.setText(valu1);
       txtmdsession.setText("Evening");
       txtmdcardn0.setText(txtcard_n0.getText());
       txtmdsn0.setText(valu2);
       txtmdmilk.setText(valu3);
       txtmdfat.setText(valu4);
       txtmdmoney.setText(valu5);
       txtmdcardn0.setEditable(true);
       txtmdmilk.setEditable(true);
       txtmdfat.setEditable(true);
       txtmdmoney.setEditable(true);
       }
         catch(NullPointerException err)
      {
          JOptionPane.showMessageDialog(SampleFrame.this,"Please Fill Details of Customer on this day");
      }
    }//GEN-LAST:event_tbleveningMouseClicked
public void moneygive()
{
    if(txtgivemoney.getText().equals(""))
             {
                 JOptionPane.showMessageDialog(SampleFrame.this, "Please Enter Money");
             }
    else{
         try{
             con=DriverManager.getConnection(host);
             stmt=con.createStatement();
             java.sql.Timestamp sqlDate= new java.sql.Timestamp(new java.util.Date().getTime());
             stmt.executeUpdate("insert into DAILYSALES(CARD_NUM,SALES_NAME,MILK,MONEY,RECIEVED,DATE,SESSION,TIMESTAMP) values("+txtcard_n0.getText()+",'"+lblname.getText()+"','0','"+txtgivemoney.getText()+"','YES','"+txtDate.getText()+"','"+cmbevent.getSelectedItem()+"','"+sqlDate+"')");
             
             stmt.close();
             con.close();
        }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(SampleFrame.this,err);
        }
    }
}
    public void resetupdate()
{
    txtmddate.setText("");
    txtmdsession.setText("");
    txtmdcardn0.setText("");
    txtmdsn0.setText("");
    txtmdmilk.setText("");
    txtmdfat.setText("");
    txtmdmoney.setText("");
}
public void updateoption()
{
     if(txtmdcardn0.getText().equals(""))
        {
           JOptionPane.showMessageDialog(SampleFrame.this,"Please Select row to update");
        }
        else{
        UpdateData();
        filltablecustomer();
       filltablemorning();
       filltableevening();
       filltablevaluesmorning();
       filltablevaluesevening();
       resetupdate();
        
        }
}
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
       updateoption();
       txtfalsemade();
       txtcard_n0.requestFocus();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void txtcard_n0FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcard_n0FocusLost
     if(txtcard_n0.getText().equals(""))
            {
              JOptionPane.showMessageDialog(SampleFrame.this,"Please enter customer number;"); 
            } 
     else 
        {
        lblmilk3.setText("0");
        lblcount2.setText("0");
        lblmoney2.setText("0");
        CustomerName();
        filltablecustomer();
        filltableevening();
        filltablemorning();
        filltablevaluesmorning();
        filltablevaluesevening();
        Validatecard();    
        filltablegivemoney();
        filltablegivemoneypm();
        filltablegivemoneyam();
        filltablegivemoneyampm();
        totalcalcdam();
        totalcalcdpm();
        toalcalc();
        }

    }//GEN-LAST:event_txtcard_n0FocusLost

    private void txtsampleFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtsampleFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsampleFocusLost

    private void txtmilkFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtmilkFocusLost
        // TODO add your handling code here:
  // moneycal();
    }//GEN-LAST:event_txtmilkFocusLost

    private void txtfatFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtfatFocusLost
        // TODO add your handling code here:
           moneycal();
    }//GEN-LAST:event_txtfatFocusLost

    private void txtmoneyFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtmoneyFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmoneyFocusLost
public void enteroption()
{
    if(txtmilk.getText().equals(""))
    {
      JOptionPane.showMessageDialog(SampleFrame.this,"Please Enter Milk Quantity");
    }
    else
    {
        Validatecard1();
        filltablecustomer();
        filltableevening();
        filltablemorning();
        filltablevaluesmorning();
        filltablevaluesevening();
        filltablegivemoney();
        filltablegivemoneypm();
        filltablegivemoneyam();
        filltablegivemoneyampm();
        totalcalcdam();
        totalcalcdpm();
        toalcalc();
        countsessiondata();
        lblalert.setText(" ");
    }
    }
    private void btnenterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnenterActionPerformed
       enteroption(); 
    }//GEN-LAST:event_btnenterActionPerformed

    private void txtmdmilkFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtmdmilkFocusLost
        // TODO add your handling code here:
        moneycal1();
    }//GEN-LAST:event_txtmdmilkFocusLost

    private void txtmdfatFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtmdfatFocusLost
        // TODO add your handling code here:
        moneycal1();
    }//GEN-LAST:event_txtmdfatFocusLost

    private void txtmdmilkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmdmilkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmdmilkActionPerformed

    private void txtcard_n0KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcard_n0KeyPressed
        // TODO add your handling code here:
        int c=evt.getKeyCode();
        if(c==KeyEvent.VK_RIGHT)
        {
            if(txtcard_n0.getText().equals(""))
            {
                JOptionPane.showMessageDialog(SampleFrame.this,"Please Enter Customer ID");              
            }
            else
            {
            txtmilk.requestFocus(true);
            }
        }
        if(c==KeyEvent.VK_LEFT)
        {
            salesmilk.requestFocus(true);
        }
        if(c==KeyEvent.VK_UP)
        {
            txtgivemoney.requestFocus(true);
        }
    }//GEN-LAST:event_txtcard_n0KeyPressed

    private void txtsampleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsampleKeyPressed
        // TODO add your handling code here:
        int c=evt.getKeyCode();
        if(c==KeyEvent.VK_RIGHT)
        {// JOptionPane.showMessageDialog(SampleData.this,"Right arrow is pressed");
            txtmilk.requestFocus(true);
        }
        if(c==KeyEvent.VK_LEFT)
        {
            txtcard_n0.requestFocus(true);
            txtcard_n0.setText("");
        }
    }//GEN-LAST:event_txtsampleKeyPressed

    private void txtmilkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmilkKeyPressed
        // TODO add your handling code here:
        int c=evt.getKeyCode();
        if(c==KeyEvent.VK_RIGHT)
        {// JOptionPane.showMessageDialog(SampleData.this,"Right arrow is pressed");
            txtfat.requestFocus(true);
        }
        if(c==KeyEvent.VK_LEFT)
        {
            txtsample.requestFocus(true);
        }
    }//GEN-LAST:event_txtmilkKeyPressed

    private void txtfatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfatKeyPressed
        // TODO add your handling code hsamplere:
        int c=evt.getKeyCode();
        if(c==KeyEvent.VK_RIGHT)
        {// JOptionPane.showMessageDialog(SampleData.this,"Right arrow is pressed");
            btnenter.requestFocus(true);
        }
        if(c==KeyEvent.VK_LEFT)
        {
            txtmilk.requestFocus(true);
        }
    }//GEN-LAST:event_txtfatKeyPressed

    private void txtmoneyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmoneyKeyPressed
        // TODO add your handling code here:
        int c=evt.getKeyCode();
        if(c==KeyEvent.VK_RIGHT)
        {// JOptionPane.showMessageDialog(SampleData.this,"Right arrow is pressed");
            btnenter.requestFocus(true);
        }
        if(c==KeyEvent.VK_LEFT)
        {
            txtfat.requestFocus(true);
        }
    }//GEN-LAST:event_txtmoneyKeyPressed

    private void btnenterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnenterKeyPressed
        // TODO add your handling code here:
        int c=evt.getKeyCode();
        if(c==KeyEvent.VK_RIGHT)
        {// JOptionPane.showMessageDialog(SampleData.this,"Right arrow is pressed");
            txtcard_n0.requestFocus(true);
             txtcard_n0.setText("");
        }
        if(c==KeyEvent.VK_LEFT)
        {
            txtmoney.requestFocus(true);
        }
        if(c==KeyEvent.VK_DOWN)
        {
            txtmddate.requestFocus();
        }
        if(c==KeyEvent.VK_UP)
        {
            txtDate.requestFocus();
        }
        if(c==KeyEvent.VK_ENTER)
        {
            enteroption();
            txteditablefalse();
             txtcard_n0.setText("");
            txtcard_n0.requestFocus(); 
        }
    }//GEN-LAST:event_btnenterKeyPressed

    private void lblday1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblday1MouseClicked
        // TODO add your handling code here:
        lblday1.setVisible(false);
    }//GEN-LAST:event_lblday1MouseClicked

    private void txtDateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDateKeyPressed
      //  int c=evt.get
        int c=evt.getKeyCode();
        if(c==KeyEvent.VK_RIGHT)
        {// JOptionPane.showMessageDialog(SampleData.this,"Right arrow is pressed");
            txtcard_n0.requestFocus(true);
            txtcard_n0.setText("");
        }
        if(c==KeyEvent.VK_LEFT)
        {
            cmbevent.requestFocus(true);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtDateKeyPressed

    private void txtDateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDateFocusLost
        DateDifference();
        filltablecustomer();
        filltablesales();   
     
    }//GEN-LAST:event_txtDateFocusLost

    private void tblcustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblcustomerMouseClicked
     
        try
        {
         int row=tblcustomer.getSelectedRow();
       String valu6=txtDate.getText();
       String valu7=cmbevent.getSelectedItem().toString();
       String valu1=tblcustomer.getValueAt(row,0).toString();
       String valu2=tblcustomer.getValueAt(row,1).toString();
       String valu3=tblcustomer.getValueAt(row,2).toString();
       String valu4=tblcustomer.getValueAt(row,3).toString();
       String valu5=tblcustomer.getValueAt(row,4).toString();
       txtmddate.setText(valu6);
       txtcard_n0.setText(valu1);
       txtmdsession.setText(valu7);
       txtmdcardn0.setText(valu1);
       txtmdsn0.setText(valu2);
       txtmdmilk.setText(valu3);
       txtmdfat.setText(valu4);
       txtmdmoney.setText(valu5);
      // txtmdsn0.setEditable(true);
       txtmdcardn0.setEditable(true);
       txtmdmilk.setEditable(true);
       txtmdfat.setEditable(true);
       txtmdmoney.setEditable(true);
        }
         catch(NullPointerException err)
      {
         // int row1=tblmorning.getSelectedRow();
          
          JOptionPane.showMessageDialog(SampleFrame.this,"Please Fill Details of Customer on this day");
            
             }
               
    }//GEN-LAST:event_tblcustomerMouseClicked
public void deleteoption()
{
    
        if(txtmdcardn0.getText().equals(""))
        {
           JOptionPane.showMessageDialog(SampleFrame.this,"Please Select row to delete");
        }
        else{
        int reply=JOptionPane.showConfirmDialog(null, "Are You sure want to delete?");
        if(reply==JOptionPane.YES_OPTION)
        {
        DeleteData();
        resetupdate();
        filltablemorning();
        filltableevening();
        filltablevaluesmorning();
        filltablevaluesevening();
        filltablecustomer();
        }
        else
        {
            
        }
         }
}
        
public void deletegiveoption()
{
    
        if(txtgivemoney.getText().equals(""))
        {
           JOptionPane.showMessageDialog(SampleFrame.this,"Please Select row to delete");
        }
        else{
        int reply=JOptionPane.showConfirmDialog(null, "Are You sure want to delete?");
        if(reply==JOptionPane.YES_OPTION)
        {
        DeletegiveData();
        filltablegivemoneyam();
        filltablegivemoneyampm();
        lblcount2.setText("0");
        lblmoney2.setText(("0"));
         totalcalcdam();
        totalcalcdpm();
        toalcalc();
        txtcard_n0.setText("");
             txtgivemoney.setText("");
        txtcard_n0.requestFocus(true);
        }
        else
        {
            
        }
         }
}
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
      deleteoption();
      txtfalsemade();
      countsessiondata();
      txtcard_n0.requestFocus();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton9KeyPressed
        // TODO add your handling code here:
        
        int c=evt.getKeyCode();
        if(c==KeyEvent.VK_RIGHT)
        {// JOptionPane.showMessageDialog(SampleData.this,"Right arrow is pressed");
            txtcard_n0.requestFocus(true);
             txtcard_n0.setText("");
        }
        if(c==KeyEvent.VK_LEFT)
        {
            txtmoney.requestFocus(true);
        }
        if(c==KeyEvent.VK_DOWN)
        {
            jButton7.requestFocus();
        }
        if(c==KeyEvent.VK_UP)
        {
            btnenter.requestFocus();
        }
        if(c==KeyEvent.VK_ENTER)
        {
            deleteoption();
            txtfalsemade();
        }
        if(c==KeyEvent.VK_DELETE)
        {
            deleteoption();
            txtfalsemade();
        }
    }//GEN-LAST:event_jButton9KeyPressed

    private void cmbeventKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbeventKeyPressed
        // TODO add your handling code here:
         int c=evt.getKeyCode();
        if(c==KeyEvent.VK_RIGHT)
        {// JOptionPane.showMessageDialog(SampleData.this,"Right arrow is pressed");
            txtcard_n0.requestFocus(true);
             txtcard_n0.setText("");
        }
        if(c==KeyEvent.VK_LEFT)
        {
            txtDate.requestFocus(true);
        }
        if(c==KeyEvent.VK_DOWN)
        {
            cmbevent.setSelectedItem(evt);
        }
        if(c==KeyEvent.VK_UP)
        {
            cmbevent.setSelectedItem(evt);
        }
       
    }//GEN-LAST:event_cmbeventKeyPressed

    private void txtmddateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmddateKeyPressed
        // TODO add your handling code here:
           int c=evt.getKeyCode();
        if(c==KeyEvent.VK_RIGHT)
        {// JOptionPane.showMessageDialog(SampleData.this,"Right arrow is pressed");
            txtmdsession.requestFocus(true);
             
        }
        if(c==KeyEvent.VK_LEFT)
        {
            btnenter.requestFocus(true);
        }
    }//GEN-LAST:event_txtmddateKeyPressed

    private void txtmdsessionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmdsessionKeyPressed
        // TODO add your handling code here:
           int c=evt.getKeyCode();
        if(c==KeyEvent.VK_RIGHT)
        {// JOptionPane.showMessageDialog(SampleData.this,"Right arrow is pressed");
            txtmdcardn0.requestFocus(true);
             
        }
        if(c==KeyEvent.VK_LEFT)
        {
            txtmddate.requestFocus(true);
        }
    }//GEN-LAST:event_txtmdsessionKeyPressed

    private void txtmdcardn0KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmdcardn0KeyPressed
   int c=evt.getKeyCode();
        if(c==KeyEvent.VK_RIGHT)
        {// JOptionPane.showMessageDialog(SampleData.this,"Right arrow is pressed");
            txtmdsn0.requestFocus(true);
             
        }
        if(c==KeyEvent.VK_LEFT)
        {
            txtmdsession.requestFocus(true);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmdcardn0KeyPressed

    private void txtmdsn0KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmdsn0KeyPressed
   int c=evt.getKeyCode();
        if(c==KeyEvent.VK_RIGHT)
        {// JOptionPane.showMessageDialog(SampleData.this,"Right arrow is pressed");
            txtmdmilk.requestFocus(true);
             
        }
        if(c==KeyEvent.VK_LEFT)
        {
            txtmdcardn0.requestFocus(true);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtmdsn0KeyPressed

    private void txtmdfatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmdfatKeyPressed
   int c=evt.getKeyCode();
        if(c==KeyEvent.VK_RIGHT)
        {// JOptionPane.showMessageDialog(SampleData.this,"Right arrow is pressed");
            txtmdmoney.requestFocus(true);
             
        }
        if(c==KeyEvent.VK_LEFT)
        {
            txtmdmilk.requestFocus(true);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmdfatKeyPressed

    private void txtmdmoneyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmdmoneyKeyPressed
   int c=evt.getKeyCode();
        if(c==KeyEvent.VK_RIGHT)
        {// JOptionPane.showMessageDialog(SampleData.this,"Right arrow is pressed");
            jButton7.requestFocus(true);
             
        }
        if(c==KeyEvent.VK_LEFT)
        {
            txtmdfat.requestFocus(true);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtmdmoneyKeyPressed

    private void jButton7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton7KeyPressed
   int c=evt.getKeyCode();
        if(c==KeyEvent.VK_RIGHT)
        {// JOptionPane.showMessageDialog(SampleData.this,"Right arrow is pressed");
            txtmddate.requestFocus(true);
             
        }
        if(c==KeyEvent.VK_LEFT)
        {
            txtmdmoney.requestFocus(true);
        }
        if(c==KeyEvent.VK_UP)
        {// JOptionPane.showMessageDialog(SampleData.this,"Right arrow is pressed");
            jButton9.requestFocus(true);
             
        }
        if(c==KeyEvent.VK_DOWN)
        {
            txtcard_n0.requestFocus(true);
            txtcard_n0.setText("");
        }
         if(c==KeyEvent.VK_ENTER)
        {
            updateoption();
            txtfalsemade();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7KeyPressed

    private void txtmdmilkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmdmilkKeyPressed
int c=evt.getKeyCode();
        if(c==KeyEvent.VK_RIGHT)
        {// JOptionPane.showMessageDialog(SampleData.this,"Right arrow is pressed");
            txtmdfat.requestFocus(true);
             
        }
        if(c==KeyEvent.VK_LEFT)
        {
            txtmdsn0.requestFocus(true);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtmdmilkKeyPressed
public void calculatemilk()
{
        if(salesmoney.getText().equals(""))
    {
        
    }
    else
    {
        
        String value2=salesmoney.getText();
        Double value_2=Double.parseDouble(value2);
        String value3=lblday2.getText();
        Double value_3=Double.parseDouble(value3);
        Double result=(value_2)/(value_3);
        String fin_money=df.format(result);;
        salesmilk.setText(fin_money);
    }
}
    @SuppressWarnings("empty-statement")
public void calculatemoney()
{
        if((salesmilk.getText().equals(""))&&(salesmoney.getText().equals("")))
    {
        
    }
    else
    {
        
        String value2=salesmilk.getText();
        Double value_2=Double.parseDouble(value2);
        String value3=lblday2.getText();
        Double value_3=Double.parseDouble(value3);
        Double result=(value_2)*(value_3);
        String fin_money=df.format(result);;
        salesmoney.setText(fin_money);
    }
}
    private void jToggleButton1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jToggleButton1ItemStateChanged
        // TODO add your handling code here:
        
        if(jToggleButton1.isSelected())
        {
         lblday1.setVisible(false);
         lblday2.setVisible(false);
        }
        else
        {
            
            lblday1.setVisible(true);
            lblday2.setVisible(true);
        }
    }//GEN-LAST:event_jToggleButton1ItemStateChanged

    private void cmbeventItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbeventItemStateChanged
        filltablecustomer();    
        filltablesales();
    }//GEN-LAST:event_cmbeventItemStateChanged

    private void txtDateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDateMouseClicked
        // TODO add your handling code here:
         final ObservingTextField txtDate_Reg = new ObservingTextField();
                DatePicker dp = new DatePicker(txtDate_Reg);
                txtDate.add(txtDate_Reg);
                Date selectedDate = dp.parseDate(txtDate_Reg.getText());
                dp.setSelectedDate(selectedDate);
                dp.start(txtDate_Reg);
    }//GEN-LAST:event_txtDateMouseClicked

    private void tblsalesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblsalesMouseClicked
        // TODO add your handling cod e here:
      jButton11.setEnabled(true);
      jButton12.setEnabled(true);
         try
        {
       int row3=tblsales.getSelectedRow();
       String val3=tblsales.getValueAt(row3,0).toString();
       String val4=tblsales.getValueAt(row3,1).toString();
       String val5=tblsales.getValueAt(row3,2).toString();
       salesno.setText(val3);
       salesmoney.setText(val5);
       salesmilk.setText(val4);
       btnsenter.setEnabled(false);
        }
         catch(NullPointerException err)
      {
          JOptionPane.showMessageDialog(SampleFrame.this,"Please Fill Details of Customer on this day");
      }
                                              
    }//GEN-LAST:event_tblsalesMouseClicked

    private void btnsenterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsenterActionPerformed
        salesorgive();
        filltablesales();
        countsessioncansales();
    }//GEN-LAST:event_btnsenterActionPerformed

    private void btnsenterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnsenterKeyPressed
        // TODO add your handling code here:
          int c=evt.getKeyCode();
        if(c==KeyEvent.VK_RIGHT)
        {// JOptionPane.showMessageDialog(SampleData.this,"Right arrow is pressed");
            jButton11.requestFocus(true);
             
        }
        if(c==KeyEvent.VK_LEFT)
        {
            salesmoney.requestFocus(true);
        } 
        if(c==KeyEvent.VK_DOWN)
        {
            jButton12.requestFocus(true);
        } 
        if(c==KeyEvent.VK_ENTER)
        {
        dtm6.setColumnCount(0);
        dtm6.setRowCount(0);
        salesorgive();
        filltablesales();
        countsessioncansales();
        txtcard_n0.requestFocus();
        }  
       
        
    }//GEN-LAST:event_btnsenterKeyPressed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        UpdateSalesData();
        filltablesales();
        countsessioncansales();
        salesmoney.setText("");
        salesmilk.setText("");
        jButton11.setEnabled(false);
        jButton12.setEnabled(false);
        txtcard_n0.requestFocus();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton11KeyPressed
        // TODO add your handling code here:
        int c=evt.getKeyCode();
        if(c==KeyEvent.VK_RIGHT)
        {// JOptionPane.showMessageDialog(SampleData.this,"Right arrow is pressed");
            jButton12.requestFocus(true);
             
        }
        if(c==KeyEvent.VK_LEFT)
        {
            btnsenter.requestFocus(true);
        } 
        if(c==KeyEvent.VK_DOWN)
        {
            jButton12.requestFocus(true);
        } 
        if(c==KeyEvent.VK_U)
        {
        UpdateSalesData();
        filltablesales();
        salesmoney.setText("");
        salesmilk.setText("");
        salesno.setText("");
        
        }  
    }//GEN-LAST:event_jButton11KeyPressed
  public void resetsales()
  {
     tblsales.setModel(dtm6);  
    }
    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        DeleteSalesData();
        filltablesales();
        countsessioncansales();
        salesmoney.setText("");
        salesmilk.setText("");
      jButton11.setEnabled(false);
      jButton12.setEnabled(false);
       txtcard_n0.requestFocus();
        //resetsales();
       // 
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton12KeyPressed
        // TODO add your handling code here:
         int c=evt.getKeyCode();
        if(c==KeyEvent.VK_RIGHT)
        {// JOptionPane.showMessageDialog(SampleData.this,"Right arrow is pressed");
            jButton11.requestFocus(true);
             
        }
        if(c==KeyEvent.VK_LEFT)
        {
            btnsenter.requestFocus(true);
        } 
        if(c==KeyEvent.VK_DELETE)
        {
        DeleteSalesData();
        filltablesales();
        salesmoney.setText("");
        salesmilk.setText("");
        salesno.setText("");
        } 
        
    }//GEN-LAST:event_jButton12KeyPressed

    private void salesmoneyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_salesmoneyKeyPressed
        // TODO add your handling code here:
          int c=evt.getKeyCode();
        if(c==KeyEvent.VK_RIGHT)
        {// JOptionPane.showMessageDialog(SampleData.this,"Right arrow is pressed");
            btnsenter.requestFocus(true);
             
        }
        if(c==KeyEvent.VK_LEFT)
        {
            salesmilk.requestFocus(true);
        }  
    }//GEN-LAST:event_salesmoneyKeyPressed

    private void salesmilkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_salesmilkKeyPressed
        // TODO add your handling code here:
          int c=evt.getKeyCode();
        if(c==KeyEvent.VK_RIGHT)
        {// JOptionPane.showMessageDialog(SampleData.this,"Right arrow is pressed");
            btnsenter.requestFocus(true);
             
        }
        if(c==KeyEvent.VK_LEFT)
        {
            txtcard_n0.requestFocus(true);
        }  
    }//GEN-LAST:event_salesmilkKeyPressed

    private void salesmoneyFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_salesmoneyFocusLost
        // TODO add your handling code here:
        calculatemilk();
    }//GEN-LAST:event_salesmoneyFocusLost

    private void lblday2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblday2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblday2MouseClicked

    private void salesmilkFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_salesmilkFocusLost
        // TODO add your handling code here:
        calculatemoney();
    }//GEN-LAST:event_salesmilkFocusLost

    private void salesnoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_salesnoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_salesnoFocusLost

    private void salesnoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_salesnoKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_salesnoKeyPressed

    private void salesnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salesnoMouseClicked
        // TODO add your handling code here:
        countsessioncansales();
    }//GEN-LAST:event_salesnoMouseClicked

    private void givemoneyamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_givemoneyamMouseClicked
        // TODO add your handling code here:
       
        try
        {
       int row=givemoneyam.getSelectedRow();
       String valu1=givemoneyam.getValueAt(row,0).toString();
       String valu2=givemoneyam.getValueAt(row,1).toString();
       txtmddate.setText(valu1);
       txtmdsession.setText("Morning");
       txtmdcardn0.setText(txtcard_n0.getText());
       txtgivemoney.setText(valu2);
       
       }
         catch(NullPointerException err)
      {
          JOptionPane.showMessageDialog(SampleFrame.this,"Please Fill Details of Customer on this day");
      }
    }//GEN-LAST:event_givemoneyamMouseClicked

    private void givemoneypmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_givemoneypmMouseClicked
        // TODO add your handling code here:
         try
        {
       int row=givemoneypm.getSelectedRow();
       String valu1=givemoneypm.getValueAt(row,0).toString();
       String valu2=givemoneypm.getValueAt(row,1).toString();
       txtmddate.setText(valu1);
       txtmdsession.setText("Evening");
       txtmdcardn0.setText(txtcard_n0.getText());
       txtgivemoney.setText(valu2);
       
       }
         catch(NullPointerException err)
      {
          JOptionPane.showMessageDialog(SampleFrame.this,"Please Fill Details of Customer on this day");
      }
    }//GEN-LAST:event_givemoneypmMouseClicked

    private void txtgivemoneyFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtgivemoneyFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtgivemoneyFocusLost

    private void txtgivemoneyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtgivemoneyKeyPressed
 int c=evt.getKeyCode();
        if(c==KeyEvent.VK_RIGHT)
        {// JOptionPane.showMessageDialog(SampleData.this,"Right arrow is pressed");
            jButton13.requestFocus(true);
             
        }
        if(c==KeyEvent.VK_LEFT)
        {
            txtcard_n0.requestFocus(true);
        }  
    }//GEN-LAST:event_txtgivemoneyKeyPressed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your hand1  1ling code here:
   moneygive();
   filltablegivemoney();
        filltablegivemoneypm();
        filltablegivemoneyam();
        filltablegivemoneyampm();
        lblcount2.setText("0");
        lblmoney2.setText(("0"));
      
         totalcalcdam();
        totalcalcdpm();
        toalcalc();
   txtgivemoney.setText("");
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton13KeyPressed
        // TODO add your handling code here:
         int c=evt.getKeyCode();
        if(c==KeyEvent.VK_RIGHT)
        {// JOptionPane.showMessageDialog(SampleData.this,"Right arrow is pressed");
            jButton14.requestFocus(true);
             
        }
         if(c==KeyEvent.VK_ENTER)
        {// JOptionPane.showMessageDialog(SampleData.this,"Right arrow is pressed");
            moneygive();
   filltablegivemoney();
        filltablegivemoneypm();
        filltablegivemoneyam();
        filltablegivemoneyampm();
        lblcount2.setText("0");
        lblmoney2.setText(("0"));
         totalcalcdam();
        totalcalcdpm();
        toalcalc();
   txtgivemoney.setText("");
   
     txtcard_n0.requestFocus(true);
     txtcard_n0.setText("");
        }
        if(c==KeyEvent.VK_DOWN)
        {// JOptionPane.showMessageDialog(SampleData.this,"Right arrow is pressed");
            jButton15.requestFocus(true);
             
        }
        if(c==KeyEvent.VK_LEFT)
        {
            txtgivemoney.requestFocus(true);
        }  
    }//GEN-LAST:event_jButton13KeyPressed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your hand1  ling code here:
     
     if(txtgivemoney.getText().equals(""))
        {
           JOptionPane.showMessageDialog(SampleFrame.this,"Please Select row to update");
        }
        else{
        UpdatemoneyData();
        filltablegivemoneyam();
        filltablegivemoneyampm();
        lblcount2.setText("0");
        lblmoney2.setText(("0"));
         totalcalcdam();
        totalcalcdpm();
        toalcalc();
     }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton14KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton14KeyPressed
        // TODO add your handling code here:
        int c=evt.getKeyCode();
        if(c==KeyEvent.VK_RIGHT)
        {// JOptionPane.showMessageDialog(SampleData.this,"Right arrow is pressed");
            jButton15.requestFocus(true);
             
        }
        if(c==KeyEvent.VK_ENTER)
        {// JOptionPane.showMessageDialog(SampleData.this,"Right arrow is pressed");
           if(txtgivemoney.getText().equals(""))
        {
           JOptionPane.showMessageDialog(SampleFrame.this,"Please Select row to update");
        }
        else{
        UpdatemoneyData();
        filltablegivemoneyam();
        filltablegivemoneyampm();
        lblcount2.setText("0");
        lblmoney2.setText(("0"));
         totalcalcdam();
        totalcalcdpm();
        toalcalc();
     }
             
        }
        if(c==KeyEvent.VK_DOWN)
        {// JOptionPane.showMessageDialog(SampleData.this,"Right arrow is pressed");
            jButton15.requestFocus(true);
             
        }
        if(c==KeyEvent.VK_LEFT)
        {
            jButton13.requestFocus(true);
        }  
    }//GEN-LAST:event_jButton14KeyPressed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        deletegiveoption();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton15KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton15KeyPressed
        // TODO add your handling code here:
          int c=evt.getKeyCode();
        if(c==KeyEvent.VK_ENTER)
        {
             deletegiveoption();
             
        } 
    }//GEN-LAST:event_jButton15KeyPressed

    private void txtcard_n0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcard_n0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcard_n0ActionPerformed

    private void txtcard_n0FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcard_n0FocusGained
        // TODO add your handling code here:
       
    }//GEN-LAST:event_txtcard_n0FocusGained

    private void cmbeventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbeventActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbeventActionPerformed

    private void txtsampleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsampleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsampleActionPerformed

    private void txtfatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfatActionPerformed
 public void test()
 {
    JOptionPane.showMessageDialog(SampleFrame.this,"success"); 
 }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws InterruptedException {
       
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SampleFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SampleFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SampleFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SampleFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
 
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new SampleFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnenter;
    private javax.swing.JButton btnsenter;
    private javax.swing.JComboBox cmbevent;
    private javax.swing.JDesktopPane datepane;
    private javax.swing.JTable givemoneyam;
    private javax.swing.JTable givemoneypm;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel lblalert;
    private javax.swing.JLabel lblcount;
    private javax.swing.JLabel lblcount1;
    private javax.swing.JLabel lblcount2;
    private javax.swing.JLabel lblday1;
    private javax.swing.JLabel lblday2;
    private javax.swing.JLabel lblfat;
    private javax.swing.JLabel lblmilk;
    private javax.swing.JLabel lblmilk1;
    private javax.swing.JLabel lblmilk2;
    private javax.swing.JLabel lblmilk3;
    private javax.swing.JLabel lblmoney;
    private javax.swing.JLabel lblmoney1;
    private javax.swing.JLabel lblmoney2;
    private javax.swing.JTextField lblname;
    private java.awt.Panel panel4;
    private java.awt.Panel panel5;
    private javax.swing.JTextField salesmilk;
    private javax.swing.JTextField salesmoney;
    private javax.swing.JTextField salesno;
    private javax.swing.JTable tblcustomer;
    private javax.swing.JTable tblevening;
    private javax.swing.JTable tblmorning;
    private javax.swing.JTable tblsales;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtcard_n0;
    private javax.swing.JTextField txtfat;
    private javax.swing.JTextField txtfromdate;
    private javax.swing.JTextField txtgivemoney;
    private javax.swing.JTextField txtmdcardn0;
    private javax.swing.JTextField txtmddate;
    private javax.swing.JTextField txtmdfat;
    private javax.swing.JTextField txtmdmilk;
    private javax.swing.JTextField txtmdmoney;
    private javax.swing.JTextField txtmdsession;
    private javax.swing.JTextField txtmdsn0;
    private javax.swing.JTextField txtmilk;
    private javax.swing.JTextField txtmoney;
    private javax.swing.JTextField txtsample;
    private javax.swing.JTextField txttodate;
    // End of variables declaration//GEN-END:variables
}

