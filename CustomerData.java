/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dairyvsr;
import java.awt.Color;
import java.awt.Font;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.JOptionPane;
import java.lang.Float;
import java.lang.Double;
import java.lang.*;
import java.util.Observable;
import java.util.Observer;


/**
 *
 * @author HOME
 */
public class CustomerData extends javax.swing.JFrame {
 String driver = "org.apache.derby.jdbc.ClientDriver";
        String host="jdbc:derby://localhost:1527/D:/Dairy_Database/.netbeans-derby/DairyDatabase;create=true;user=admin;password=admin";
        
 final DateFormat timeFormat=new SimpleDateFormat("HH:mm:ss");
 final String DATE_FORMAT_NOW="yyyy-MM-dd";
  final String DATE_FORMAT_NOW1="EEEE";
 final DecimalFormat df = new DecimalFormat("#.##");
  DefaultTableModel dtm = new DefaultTableModel(0,0); 
  DefaultTableModel dtm1 = new DefaultTableModel(0,0); 
  DefaultTableModel dtm2 = new DefaultTableModel(0,0);
  DefaultTableModel dtm6 = new DefaultTableModel(0,0);
 Connection con,con1,con2,con3,con4,con5,con6,con7,con8,con9,con10,con21,con11,con12,con13,con14,con15,con16,con17,con18;
 ResultSet rs,res,res2,res3,res4,res5,rs5,rs7,res9,res11,res12,res1,res21,res13,res14,res15,res16,res17,res18;
 Statement stmt,stmt1,stmt2,stmt3,stmt4,stmt5,stmt6,stmt7,stmt8,stmt9,stmt21,stmt10,stmt11,stmt12,stmt13,stmt14,stmt15,stmt16,stmt17,stmt18;
 
    /**
     * Creates new form CustomerData
     */
    public CustomerData() {
        initComponents();
        makezeroes();
        DisplayTime();
        fillcustomerdata();
        filltablesales();
        Function();
        calculatenetamount();
        
    }
     public void filltablesales()
    { 
      try
       
      {
            tblsales1.setModel(dtm6);
            con21=DriverManager.getConnection(host);
            stmt21=con21.createStatement();
            stmt21.executeQuery("SELECT SAMPLE,MONEY,MILK,DATE,SESSION FROM DAILYSALES1 where (DATE='"+txtDate.getText()+"' and SESSION='"+cmbevent1.getSelectedItem()+"')");
            res21=stmt21.getResultSet();
            int cold=tblsales1.getColumnCount();
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
            while(res21.next())
             {      
                    
                    String sample=res21.getString("SAMPLE");
                    
                    Object milk=res21.getString("MILK");
                    Object money=res21.getString("MONEY");
                                dtm6.addRow(new Object[] {sample,milk,money});
                    String milksum=tblsales1.getValueAt(i, 1).toString();
                    Double milksum1=Double.parseDouble(milksum);
                    totalmilk=totalmilk+milksum1;
                     String totlamilk1=df.format(totalmilk);
                    txtmilkamt.setText(totlamilk1);
                    String moneysum=tblsales1.getValueAt(i, 2).toString();
                    Double moneysum1=Double.parseDouble(moneysum);
                    totalmoney=totalmoney+moneysum1;
                    String totalmoney1=df.format(totalmoney);
                    txtmoneypaid.setText(totalmoney1);
                    txtsalesamount.setText(totalmoney1);
                    i++;
             }
             DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
rightRenderer.setHorizontalAlignment( JLabel.CENTER );
tblsales1.setShowHorizontalLines(false);
tblsales1.setShowVerticalLines(false);
tblsales1.getColumnModel().getColumn(0).setCellRenderer( rightRenderer );
tblsales1.getColumnModel().getColumn(1).setCellRenderer( rightRenderer );
tblsales1.getColumnModel().getColumn(2).setCellRenderer( rightRenderer );
            String count=Integer.toString(i);
            Double totalfatcount=Double.parseDouble(count);
            txtsalescount.setText(count);
           // String totalmilk1=Double.toString(totalmilk);
            
           // tblcustomer.setAutoCreateColumnsFromModel(false);
            res21.close();
            stmt21.close();
            con21.close();
            
//            
//           tblcustomer.repaint();
          }
           
             catch(SQLException err)
                {
            JOptionPane.showMessageDialog(CustomerData.this,err);
                }
    }
     
    public void makezeroes()
    {
        txtcustomers.setText("0");
        milktotal.setText("0");
        fatavg.setText("0");
        txtmoneytotal.setText("0");
        txtcancount.setText("0");
        txtmilklit.setText("0");
        txtmilkkg.setText("0");
        txtsalescount.setText("0");
        txtsalesamount.setText("0");
        txtmilkamt.setText("0");
        txtmoneypaid.setText("0");
        txtnetmilk.setText("0");
        txtnetamount.setText("0");
        txtsalescount.setText("0");
        txtmilkamt.setText("0");
        txtsalesamount.setText("0");
    }
         public void DisplayTime(){
            Calendar cal=Calendar.getInstance();
            SimpleDateFormat sdf=new SimpleDateFormat(DATE_FORMAT_NOW);
           
            txtDate.setText(sdf.format(cal.getTime()));
            SimpleDateFormat sdf2=new SimpleDateFormat(DATE_FORMAT_NOW1);
//            lblday.setText(sdf2.format(cal.getTime()));
           
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
         cmbevent1.setSelectedItem("Morning"); 
    }
    else
    {
         cmbevent1.setSelectedItem("Evening");
    } 
    }
 public void filltabledatesevening()
    { 
      try
          
      {
            tblcustomer.setModel(dtm);
            con1=DriverManager.getConnection(host);
            stmt1=con1.createStatement();
            stmt1.executeQuery("SELECT CARD_NUM,SAMPLE_N0,MILK,FAT,MONEY,DATE,SESSION FROM DAILYDATA where (DATE='"+txtDate.getText()+"' and SESSION='"+cmbevent1.getSelectedItem()+"')");
            res1=stmt1.getResultSet();
            int i=0;
            Double totalmilk=0.0;
            Double totalfat=0.0;
            Double totalmoney=0.0;
            dtm.setRowCount(0);
            int cod=tblcustomer.getColumnCount();
            if(cod<2)
            {
                    dtm.addColumn("          C.N0");
                    dtm.addColumn("          S.N0");
                    dtm.addColumn("          Milk");
                    dtm.addColumn("          FAT");
                    dtm.addColumn("       MONEY");
            }
            while(res1.next())
             {      
                    
                    String cardn02=res1.getString("CARD_NUM");
                    Object S_n0=res1.getInt("SAMPLE_N0");
                    Object Milk=res1.getDouble("MILK");
                    Object Fat=res1.getString("FAT");
                    Object Money=res1.getString("MONEY");
         
                     dtm.addRow(new Object[] {cardn02,S_n0,Milk,Fat,Money});
//                                 tblcustomer.getModel().setValueAt(cardn02,i,0);
//                                 tblcustomer.getModel().setValueAt(S_n0,i,1);
//                                 tblcustomer.getModel().setValueAt(Milk,i,2);
//                                 tblcustomer.getModel().setValueAt(Fat,i,3);
//                                 tblcustomer.getModel().setValueAt(Money,i,4);
//                     
                    String milksum=tblcustomer.getValueAt(i, 2).toString();
                    Double milksum1=Double.parseDouble(milksum);
                    totalmilk=totalmilk+milksum1;
                    String totlamilk1=df.format(totalmilk);
                    //JOptionPane.showMessageDialog(CustomerData.this,milksum1);
                    milktotal.setText(totlamilk1);
                    String fatag=tblcustomer.getValueAt(i,3).toString();
                    Double fatag1=Double.parseDouble(fatag);
                    totalfat=totalfat+fatag1;
                    
                    String moneysum=tblcustomer.getValueAt(i, 4).toString();
                    Double moneysum1=Double.parseDouble(moneysum);
                    totalmoney=totalmoney+moneysum1;
                    String totalmoney1=df.format(totalmoney);
                    //JOptionPane.showMessageDialog(CustomerData.this,milksum1);
                    txtmoneytotal.setText(totalmoney1);
                    i++;
             }
             DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
rightRenderer.setHorizontalAlignment( JLabel.CENTER );
tblcustomer.getColumnModel().getColumn(0).setCellRenderer( rightRenderer );
tblcustomer.getColumnModel().getColumn(1).setCellRenderer( rightRenderer );
tblcustomer.getColumnModel().getColumn(2).setCellRenderer( rightRenderer );
tblcustomer.getColumnModel().getColumn(3).setCellRenderer( rightRenderer );
tblcustomer.getColumnModel().getColumn(4).setCellRenderer( rightRenderer );
tblcustomer.setBackground(Color.WHITE) ;
                 // head.getColumnModel().setColumnMargin();
                  tblcustomer.setShowVerticalLines(false);
                  tblcustomer.setRowHeight(45);
             tblcustomer.setForeground(Color.BLACK);
             tblcustomer.setFont(new Font("Times New Roman",Font.PLAIN,14));
            String count=Integer.toString(i);
            Double totalfatcount=Double.parseDouble(count);
            txtcustomers.setText(count);
           // String totalmilk1=Double.toString(totalmilk);
            Double fataverg=totalfat/totalfatcount;
            
            String fatavrg=df.format(fataverg);
            fatavg.setText(fatavrg);
            res1.close();
            stmt1.close();
            con1.close();
          }
           
             catch(SQLException err)
                {
            JOptionPane.showMessageDialog(CustomerData.this,err);
                }
    }
 
 
  public void filltablecustomersales()
    { 
      try
          
      {
            tblsales.setModel(dtm1);
             con2=DriverManager.getConnection(host);
            stmt2=con2.createStatement();
            stmt2.executeQuery("SELECT CARD_NUM,SALES_NAME,MONEY,MILK,RECIEVED,DATE,SESSION FROM DAILYSALES where (DATE='"+txtDate.getText()+"' and SESSION='"+cmbevent1.getSelectedItem()+"')");
            res2=stmt2.getResultSet();
            int colds=tblsales.getColumnCount();
            if(colds<2)
            {
                
                    dtm1.addColumn("          C.N0");
                    dtm1.addColumn("          NAME");
                    dtm1.addColumn("          Milk");
                    dtm1.addColumn("          Fat");
                    dtm1.addColumn("          Status");
            }
            dtm1.setRowCount(0);
            int i=0;
            int count=0;
            int coun2=0;
            Double totalsales=0.0;
            Double milktotalsale=0.0;
            Double moneypaid1=0.0;
            Double moneytobepaid1=0.0;
            Double tocustomerto=0.0;
            Double totalsalesgive=0.0;
            while(res2.next())
             {      
                    
                    String cardn02=res2.getString("CARD_NUM");
                    Object salesname=res2.getString("SALES_NAME");
                    Object Milk=res2.getString("MILK");
                    Object status=res2.getString("RECIEVED");
                    Object Money=res2.getString("MONEY");
         
                        dtm1.addRow(new Object[] {cardn02,salesname,Milk,Money,status});
//                                 tblsales.getModel().setValueAt(cardn02,i,0);
//                                 tblsales.getModel().setValueAt(salesname,i,1);
//                                 tblsales.getModel().setValueAt(Milk,i,2);
//                                 tblsales.getModel().setValueAt(Money,i,3);
//                                 tblsales.getModel().setValueAt(status,i,4);
                        
                  if(status.equals("YES")||(status.equals("NO")))
                  {
                      if(status.equals("YES"))
                        {
                   String moneypaid=tblsales.getValueAt(i, 3).toString();
                    Double moneypaidtotal=Double.parseDouble(moneypaid);
                    moneypaid1=moneypaid1+moneypaidtotal;
                    String moneytotalpaid=df.format(moneypaid1);
                  //  txtmoneypaid.setText(moneytotalpaid);
                        }
                      else
                         {
                   String moneytobepaid=tblsales.getValueAt(i, 3).toString();
                    Double moneytobepaidtotal=Double.parseDouble(moneytobepaid);
                    moneytobepaid1=moneytobepaid1+moneytobepaidtotal;
                    String moneytotaltobepaid=df.format(moneytobepaid1);
                        }
                   
                   
                    String milktotalsum=tblsales.getValueAt(i, 2).toString();
                    Double milktotalsum1=Double.parseDouble(milktotalsum);
                    milktotalsale=milktotalsale+milktotalsum1;
                    String milktotalsumamt=df.format(milktotalsale);
                    //txtmilkamt.setText(milktotalsumamt);
                      
                    String totalsalessum=tblsales.getValueAt(i, 3).toString();
                    Double totalsalessum1=Double.parseDouble(totalsalessum);
                    totalsales=totalsales+totalsalessum1;
                    String totalsalesmoney1=df.format(totalsales);
                    //txtsalesamount.setText(totalsalesmoney1);
                    count++;
                  }
                  
                    //JOptionPane.showMessageDialog(CustomerData.this,milksum1);
                    
                  else if(status.equals(" "))
                  {
                      
                    String tocustomer=tblsales.getValueAt(i, 3).toString();
                    Double tocustomer1=Double.parseDouble(tocustomer);
                    tocustomerto=tocustomerto+tocustomer1;
                    String tocustomerpaid=df.format(tocustomerto);
                   coun2++;
                  }     
             String count1=Integer.toString(count);
            //txtsalescount.setText(count1);
             String count3=Integer.toString(coun2);
             
                   String totalsalesrgive=tblsales.getValueAt(i, 3).toString();
                    Double totalsalesrgive1=Double.parseDouble(totalsalesrgive);
                    totalsalesgive=totalsalesgive+totalsalesrgive1;
                    String totalsalesgive2=df.format(totalsalesgive);
              i++; 
             }
            res2.close();
            stmt2.close();
            con2.close();
            DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
rightRenderer.setHorizontalAlignment( JLabel.CENTER );
tblsales.getColumnModel().getColumn(0).setCellRenderer( rightRenderer );
tblsales.getColumnModel().getColumn(1).setCellRenderer( rightRenderer );
tblsales.getColumnModel().getColumn(2).setCellRenderer( rightRenderer );
tblsales.getColumnModel().getColumn(3).setCellRenderer( rightRenderer );
tblsales.getColumnModel().getColumn(4).setCellRenderer( rightRenderer );
tblsales.setBackground(Color.WHITE) ;
                 // head.getColumnModel().setColumnMargin();
                  tblsales.setShowVerticalLines(false);
                  tblsales.setRowHeight(45);
             tblsales.setForeground(Color.BLACK);
             tblsales.setFont(new Font("Times New Roman",Font.PLAIN,14));
          }
           
             catch(SQLException err)
                {
            JOptionPane.showMessageDialog(CustomerData.this,err);
                }
    }
 
  public void filltablecan()
    { 
      try
          
      {
          tblcan.setModel(dtm2);
            con3=DriverManager.getConnection(host);
            stmt3=con3.createStatement();
            stmt3.executeQuery("SELECT CAN_NUM,KGCALC,LITCALC,DAIRY_NAME,DATE,SESSION FROM DAILYCANDATA where (DATE='"+txtDate.getText()+"' and SESSION='"+cmbevent1.getSelectedItem()+"')");
            res3=stmt3.getResultSet();
            int codd=tblcan.getColumnCount();
            if(codd<2)
            {
                dtm2.addColumn("Can N0");
                dtm2.addColumn("Dairy Name");
                dtm2.addColumn("Kilograms");
                dtm2.addColumn("Litres");
            }
            dtm2.setRowCount(0);
            int i=0;
            Double totalmilkkg=0.0;
            Double totalmilklit=0.0;
            while(res3.next())
             {      
                    
                    Object cann0=res3.getInt("CAN_NUM");
                    Object dairyname=res3.getString("DAIRY_NAME");
                    Object milkkg=res3.getDouble("KGCALC");
                    Object milklit=res3.getDouble("LITCALC");
                
                        dtm2.addRow(new Object[] {cann0,dairyname,milkkg,milklit});
                                
                    String milkkgsum=tblcan.getValueAt(i, 2).toString();                 
                    Double milkkgsum1=Double.parseDouble(milkkgsum);
                    totalmilkkg=totalmilkkg+milkkgsum1;
                    String totlamilkkg1=df.format(totalmilkkg);
                    //JOptionPane.showMessageDialog(CustomerData.this,milksum1);
                    txtmilkkg.setText(totlamilkkg1);
                   
                    
                    String milklitsum=tblcan.getValueAt(i, 3).toString();
                    Double milklitsum1=Double.parseDouble(milklitsum);
                    totalmilklit=totalmilklit+milklitsum1;
                    String totalmilklit1=df.format(totalmilklit);
                    //JOptionPane.showMessageDialog(CustomerData.this,milksum1);
                    txtmilklit.setText(totalmilklit1);
                    i++; 
             }
            String countcan=Integer.toString(i);
            txtcancount.setText(countcan);
               DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
rightRenderer.setHorizontalAlignment( JLabel.CENTER );
tblcan.getColumnModel().getColumn(0).setCellRenderer( rightRenderer );
tblcan.getColumnModel().getColumn(1).setCellRenderer( rightRenderer );
tblcan.getColumnModel().getColumn(2).setCellRenderer( rightRenderer );
tblcan.getColumnModel().getColumn(3).setCellRenderer( rightRenderer );
tblcan.setBackground(Color.WHITE) ;
                 // head.getColumnModel().setColumnMargin();
                  tblcan.setShowVerticalLines(false);
                  tblcan.setRowHeight(45);
             tblcan.setForeground(Color.BLACK);
             tblcan.setFont(new Font("Times New Roman",Font.PLAIN,14));
//tblcan.getColumnModel().getColumn(4).setCellRenderer( rightRenderer );
            res3.close();
            stmt3.close();
            con3.close();
          }
           
             catch(SQLException err)
                {
            JOptionPane.showMessageDialog(CustomerData.this,err);
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
public void cleardata()
    {
                     for(int i=0;i<200;i++)
                     {
                                 tblcustomer.getModel().setValueAt("",i,0);
                                 tblcustomer.getModel().setValueAt("",i,1);
                                 tblcustomer.getModel().setValueAt("",i,2);
                                 tblcustomer.getModel().setValueAt("",i,3);
                                 tblcustomer.getModel().setValueAt("",i,4);
                                 tblsales.getModel().setValueAt("",i,0);
                                 tblsales.getModel().setValueAt("",i,1);
                                 tblsales.getModel().setValueAt("",i,2);
                                 tblsales.getModel().setValueAt("",i,3);
                                 tblsales.getModel().setValueAt("",i,4);
                                 tblcan.getModel().setValueAt("",i,0);
                                 tblcan.getModel().setValueAt("",i,1);
                                 tblcan.getModel().setValueAt("",i,2);
                                 tblcan.getModel().setValueAt("",i,3);
                                 
                     }
                     txtcustomers.setText("");
                     milktotal.setText("");
                     fatavg.setText("");
                     txtmoneytotal.setText("");
                     txtmilklit.setText("");
                     txtmilkkg.setText("");
                     txtsalesamount.setText("");
                     txtnetmilk.setText("");
                     txtnetamount.setText("");
                     txtsalescount.setText("");
                     txtmilkamt.setText("");
                     txtmoneypaid.setText("");
             
    }
 public void calculatenetamount()
  {
      
      String value1=txtmoneytotal.getText();
      Double value2=Double.parseDouble(value1);
      String value3=txtsalesamount.getText();
      Double value4=Double.parseDouble(value3);
      Double value5=value2-value4;
      String value6=df.format(value5);
      txtnetamount.setText(value6);
      String value7=milktotal.getText();
      Double value8=Double.parseDouble(value7);
      String value9=txtmilkamt.getText();
      Double value10=Double.parseDouble(value9);
      Double value11=value8-value10;
      String value12=df.format(value11);
      txtnetmilk.setText(value12);
  }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbevent = new javax.swing.JComboBox();
        txtDate = new javax.swing.JTextField();
        lblday1 = new javax.swing.JLabel();
        cmbevent1 = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblcustomer = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblsales = new javax.swing.JTable();
        lblday9 = new javax.swing.JLabel();
        lblday10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblcan = new javax.swing.JTable();
        panel1 = new java.awt.Panel();
        lblday3 = new javax.swing.JLabel();
        txtcustomers = new javax.swing.JTextField();
        lblday12 = new javax.swing.JLabel();
        milktotal = new javax.swing.JTextField();
        lblday13 = new javax.swing.JLabel();
        lblday14 = new javax.swing.JLabel();
        fatavg = new javax.swing.JTextField();
        lblday15 = new javax.swing.JLabel();
        txtmoneytotal = new javax.swing.JTextField();
        lblday16 = new javax.swing.JLabel();
        txtcancount = new javax.swing.JTextField();
        lblday17 = new javax.swing.JLabel();
        txtmilklit = new javax.swing.JTextField();
        txtmilkkg = new javax.swing.JTextField();
        lblday18 = new javax.swing.JLabel();
        lblday19 = new javax.swing.JLabel();
        txtsalesamount = new javax.swing.JTextField();
        panel3 = new java.awt.Panel();
        lblday4 = new javax.swing.JLabel();
        txtsalescount = new javax.swing.JTextField();
        lblday23 = new javax.swing.JLabel();
        lblday24 = new javax.swing.JLabel();
        txtmilkamt = new javax.swing.JTextField();
        lblday25 = new javax.swing.JLabel();
        txtmoneypaid = new javax.swing.JTextField();
        panel2 = new java.awt.Panel();
        lblday20 = new javax.swing.JLabel();
        lblday21 = new javax.swing.JLabel();
        txtnetmilk = new javax.swing.JTextField();
        txtnetamount = new javax.swing.JTextField();
        datepane = new javax.swing.JDesktopPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblsales1 = new javax.swing.JTable();

        cmbevent.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        cmbevent.setForeground(new java.awt.Color(255, 51, 51));
        cmbevent.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Morning", "Evening" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);

        txtDate.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
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

        lblday1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblday1.setForeground(new java.awt.Color(0, 0, 255));

        cmbevent1.setFont(new java.awt.Font("Bell MT", 1, 18)); // NOI18N
        cmbevent1.setForeground(new java.awt.Color(0, 0, 255));
        cmbevent1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Morning", "Evening" }));
        cmbevent1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbevent1ItemStateChanged(evt);
            }
        });

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
        jScrollPane1.setViewportView(tblcustomer);

        tblsales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblsales.setRowHeight(22);
        tblsales.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblsales);

        lblday9.setFont(new java.awt.Font("Courier New", 1, 20)); // NOI18N
        lblday9.setForeground(new java.awt.Color(0, 0, 255));
        lblday9.setText("Customer Sales");

        lblday10.setFont(new java.awt.Font("Courier New", 1, 20)); // NOI18N
        lblday10.setForeground(new java.awt.Color(0, 0, 255));
        lblday10.setText("Daily Milk Customer Data");

        tblcan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblcan.setRowHeight(25);
        tblcan.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblcan);

        panel1.setBackground(java.awt.SystemColor.controlHighlight);
        panel1.setPreferredSize(new java.awt.Dimension(1320, 86));

        lblday3.setFont(new java.awt.Font("Andalus", 1, 14)); // NOI18N
        lblday3.setForeground(new java.awt.Color(51, 0, 51));
        lblday3.setText("Customers");

        txtcustomers.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        txtcustomers.setForeground(new java.awt.Color(51, 51, 255));

        lblday12.setFont(new java.awt.Font("Andalus", 1, 14)); // NOI18N
        lblday12.setForeground(new java.awt.Color(51, 0, 51));
        lblday12.setText("Milk");

        milktotal.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        milktotal.setForeground(new java.awt.Color(255, 102, 102));

        lblday13.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblday13.setForeground(new java.awt.Color(51, 0, 51));
        lblday13.setText("Total:");

        lblday14.setFont(new java.awt.Font("Andalus", 1, 14)); // NOI18N
        lblday14.setForeground(new java.awt.Color(51, 0, 51));
        lblday14.setText("Fat Avg");

        fatavg.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        fatavg.setForeground(new java.awt.Color(51, 51, 255));

        lblday15.setFont(new java.awt.Font("Andalus", 1, 14)); // NOI18N
        lblday15.setForeground(new java.awt.Color(51, 0, 51));
        lblday15.setText("Money");

        txtmoneytotal.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        txtmoneytotal.setForeground(new java.awt.Color(255, 0, 255));

        lblday16.setFont(new java.awt.Font("Andalus", 1, 14)); // NOI18N
        lblday16.setForeground(new java.awt.Color(51, 0, 51));
        lblday16.setText("Cans");

        txtcancount.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        txtcancount.setForeground(new java.awt.Color(51, 51, 255));

        lblday17.setFont(new java.awt.Font("Andalus", 1, 14)); // NOI18N
        lblday17.setForeground(new java.awt.Color(51, 0, 51));
        lblday17.setText("Total KG");

        txtmilklit.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        txtmilklit.setForeground(new java.awt.Color(51, 51, 255));

        txtmilkkg.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        txtmilkkg.setForeground(new java.awt.Color(51, 51, 255));

        lblday18.setFont(new java.awt.Font("Andalus", 1, 14)); // NOI18N
        lblday18.setForeground(new java.awt.Color(51, 0, 51));
        lblday18.setText("Total lit");

        lblday19.setFont(new java.awt.Font("Andalus", 1, 14)); // NOI18N
        lblday19.setForeground(new java.awt.Color(51, 0, 51));
        lblday19.setText("Sales Amt");

        txtsalesamount.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        txtsalesamount.setForeground(new java.awt.Color(255, 0, 255));

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(lblday13)
                        .addGap(18, 18, 18)
                        .addComponent(txtcustomers, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(lblday3)))
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(lblday12)
                        .addGap(63, 63, 63)
                        .addComponent(lblday14)
                        .addGap(67, 67, 67)
                        .addComponent(lblday15)
                        .addGap(44, 44, 44))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(milktotal, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fatavg, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtmoneytotal, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblday16)
                    .addComponent(txtcancount, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(lblday17)
                        .addGap(18, 18, 18)
                        .addComponent(lblday18))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(txtmilklit, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtmilkkg)))
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtsalesamount, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblday19))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblday12)
                    .addComponent(lblday3)
                    .addComponent(lblday14)
                    .addComponent(lblday15)
                    .addComponent(lblday16)
                    .addComponent(lblday17)
                    .addComponent(lblday18)
                    .addComponent(lblday19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcustomers, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(milktotal, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblday13)
                    .addComponent(fatavg, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmoneytotal, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcancount, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmilklit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmilkkg, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsalesamount, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        panel3.setBackground(java.awt.SystemColor.controlHighlight);

        lblday4.setFont(new java.awt.Font("Andalus", 1, 14)); // NOI18N
        lblday4.setText("Sales");

        txtsalescount.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        txtsalescount.setForeground(new java.awt.Color(51, 51, 255));

        lblday23.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblday23.setText("Sales Statistics:");

        lblday24.setFont(new java.awt.Font("Andalus", 1, 14)); // NOI18N
        lblday24.setText("Milk sold");

        txtmilkamt.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        txtmilkamt.setForeground(new java.awt.Color(255, 102, 102));

        lblday25.setFont(new java.awt.Font("Andalus", 1, 14)); // NOI18N
        lblday25.setText("Money Paid");

        txtmoneypaid.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        txtmoneypaid.setForeground(new java.awt.Color(51, 51, 255));

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(lblday4)
                        .addGap(57, 57, 57)
                        .addComponent(lblday24))
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addComponent(lblday23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtsalescount, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(txtmilkamt, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtmoneypaid, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblday25))
                .addGap(423, 423, 423))
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblday24)
                            .addComponent(lblday25)
                            .addComponent(lblday4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtsalescount, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtmilkamt, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtmoneypaid, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblday23)
                        .addGap(20, 20, 20))))
        );

        panel2.setBackground(new java.awt.Color(204, 255, 204));

        lblday20.setFont(new java.awt.Font("Andalus", 1, 14)); // NOI18N
        lblday20.setForeground(new java.awt.Color(51, 51, 255));
        lblday20.setText("Net Milk");

        lblday21.setFont(new java.awt.Font("Andalus", 1, 14)); // NOI18N
        lblday21.setForeground(new java.awt.Color(51, 51, 255));
        lblday21.setText("Net Amount");

        txtnetmilk.setBackground(new java.awt.Color(0, 0, 0));
        txtnetmilk.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        txtnetmilk.setForeground(new java.awt.Color(255, 102, 102));

        txtnetamount.setBackground(new java.awt.Color(0, 0, 0));
        txtnetamount.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        txtnetamount.setForeground(new java.awt.Color(255, 0, 255));

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtnetmilk, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblday21)
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(lblday20)))
                        .addGap(36, 36, 36))
                    .addComponent(txtnetamount)))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblday20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnetmilk, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblday21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnetamount, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        datepane.setBackground(new java.awt.Color(204, 204, 255));

        tblsales1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblsales1.setRowHeight(22);
        tblsales1.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tblsales1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(297, 297, 297)
                                .addComponent(datepane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmbevent1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(124, 124, 124)
                                .addComponent(lblday10)
                                .addGap(441, 441, 441)
                                .addComponent(lblday9))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 824, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(90, 90, 90)
                        .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(770, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 1332, Short.MAX_VALUE)
                    .addComponent(lblday1)
                    .addGap(0, 790, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(lblday9))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(datepane, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbevent1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblday10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(120, 120, 120))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 397, Short.MAX_VALUE)
                    .addComponent(lblday1)
                    .addGap(0, 381, Short.MAX_VALUE)))
        );

        jScrollPane1.getAccessibleContext().setAccessibleParent(jScrollPane1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
public void fillcustomerdata()
{
      //  cleardata();  
        
        filltabledatesevening();
        filltablecustomersales();
        filltablesales();
        filltablecan();
}
    private void txtDateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDateFocusLost
        // TODO add your handling code here:
        makezeroes();
        fillcustomerdata();
        filltablesales();
        calculatenetamount();
     //  calculatenetamount();
    }//GEN-LAST:event_txtDateFocusLost

    private void cmbevent1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbevent1ItemStateChanged
        makezeroes();
        fillcustomerdata();
        filltablesales();
        calculatenetamount();
      //    calculatenetamount();
    }//GEN-LAST:event_cmbevent1ItemStateChanged

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
            java.util.logging.Logger.getLogger(CustomerData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new CustomerData().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbevent;
    private javax.swing.JComboBox cmbevent1;
    private javax.swing.JDesktopPane datepane;
    private javax.swing.JTextField fatavg;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblday1;
    private javax.swing.JLabel lblday10;
    private javax.swing.JLabel lblday12;
    private javax.swing.JLabel lblday13;
    private javax.swing.JLabel lblday14;
    private javax.swing.JLabel lblday15;
    private javax.swing.JLabel lblday16;
    private javax.swing.JLabel lblday17;
    private javax.swing.JLabel lblday18;
    private javax.swing.JLabel lblday19;
    private javax.swing.JLabel lblday20;
    private javax.swing.JLabel lblday21;
    private javax.swing.JLabel lblday23;
    private javax.swing.JLabel lblday24;
    private javax.swing.JLabel lblday25;
    private javax.swing.JLabel lblday3;
    private javax.swing.JLabel lblday4;
    private javax.swing.JLabel lblday9;
    private javax.swing.JTextField milktotal;
    private java.awt.Panel panel1;
    private java.awt.Panel panel2;
    private java.awt.Panel panel3;
    private javax.swing.JTable tblcan;
    private javax.swing.JTable tblcustomer;
    private javax.swing.JTable tblsales;
    private javax.swing.JTable tblsales1;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtcancount;
    private javax.swing.JTextField txtcustomers;
    private javax.swing.JTextField txtmilkamt;
    private javax.swing.JTextField txtmilkkg;
    private javax.swing.JTextField txtmilklit;
    private javax.swing.JTextField txtmoneypaid;
    private javax.swing.JTextField txtmoneytotal;
    private javax.swing.JTextField txtnetamount;
    private javax.swing.JTextField txtnetmilk;
    private javax.swing.JTextField txtsalesamount;
    private javax.swing.JTextField txtsalescount;
    // End of variables declaration//GEN-END:variables
}
