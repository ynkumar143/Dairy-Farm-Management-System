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
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.JOptionPane;
import java.lang.Float;
import java.lang.Double;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.print.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 *
 * @author HOME
 */
public class PaymentData extends javax.swing.JFrame {
 String driver = "org.apache.derby.jdbc.ClientDriver";
            String host="jdbc:derby://localhost:1527/D:/Dairy_Database/.netbeans-derby/DairyDatabase;create=true;user=admin;password=admin";
        DefaultTableModel dtm = new DefaultTableModel(0,0); 
        DefaultTableModel dtm1=new DefaultTableModel(0,0);
        DefaultTableModel dtm2=new DefaultTableModel(0,0);
        DefaultTableModel dtm3=new DefaultTableModel(0,0);
 final DateFormat timeFormat=new SimpleDateFormat("HH:mm:ss");
 final String DATE_FORMAT_NOW="yyyy-MM-dd";
 private JTable nag;
  final String DATE_FORMAT_NOW1="EEEE";
 final DecimalFormat df = new DecimalFormat("#.##");
 final DecimalFormat df1 = new DecimalFormat("#");
 Connection con1,con2,con3,con4,con5,con7,con6,con9,con10,con11;
 ResultSet res1,res2,rs5,res3,res4,res5,res6,res7,res9,res10,res11;
 Statement stmt1,stmt2,stmt6,stmt3,stmt4,stmt5,stmt7,stmt9,stmt10,stmt11;
 
 
    /**
     * Creates new form PaymentData
     */
    public PaymentData() {
        initComponents();
        DisplayTime();
        datediff();
        fillcustomerdates();
        fillcustomersalesdates();
      
    }
     public void DisplayTime(){
            Calendar cal=Calendar.getInstance();
            SimpleDateFormat sdf=new SimpleDateFormat(DATE_FORMAT_NOW);
            txttodate.setText(sdf.format(cal.getTime()));
            
            Calendar cal1=Calendar.getInstance();
            cal1.add(Calendar.DATE,-10);      
            SimpleDateFormat sdf1=new SimpleDateFormat(DATE_FORMAT_NOW);
            txtfromdate.setText(sdf1.format(cal1.getTime()));
            
}
     public void datediff()
     {
         Calendar cal3=Calendar.getInstance();
         SimpleDateFormat sdf2=new SimpleDateFormat(DATE_FORMAT_NOW);
         String today=sdf2.format(cal3.getTime());
            String date1=txttodate.getText();
            String date2=txtfromdate.getText();
            SimpleDateFormat sdf3=new SimpleDateFormat(DATE_FORMAT_NOW);
            Date d1=null;
            Date d2=null;
            Date d3=null;
            try{
                d1=sdf3.parse(date1);
                d2=sdf3.parse(date2);
                d3=sdf3.parse(today);
                double diff=d1.getTime()-d2.getTime();
                double diff1=d3.getTime()-d1.getTime();
                double diffSeconds = diff / 1000 % 60;
		double diffMinutes = diff / (60 * 1000) % 60;
		double diffHours = diff / (60 * 60 * 1000) % 24;
		double diffDays = diff / (24 * 60 * 60 * 1000);
                double diffDays2 = diff1 / (24 * 60 * 60 * 1000);
                if(diffDays2<0)
                {
                    JOptionPane.showMessageDialog(PaymentData.this,"Date Selection is not possible! Date after present day is not allowed");
                }
                else
                {
                String days=df1.format(diffDays+1);
                daysdiff.setText(days);
                }
            }
            catch(Exception e)
            {
                
            }
     }
     public void cleardata()
    {
                     for(int i=0;i<365;i++)
                     {
                                tblpayment.getModel().setValueAt("",i,0);
                                 tblpayment.getModel().setValueAt("",i,1);
                                 tblpayment.getModel().setValueAt("",i,2);
                                 tblpayment.getModel().setValueAt("",i,3);
                                 tblpayment.getModel().setValueAt("",i,4);
                                 tblpayment.getModel().setValueAt("",i,5);
                                 
                                 
                                 
                     }
                     for(int j=0;j<200;j++)
                     {
                         tblsales.getModel().setValueAt("",j,0);
                                 tblsales.getModel().setValueAt("",j,1);
                     tblsales.getModel().setValueAt("",j,2);
                                 tblsales.getModel().setValueAt("",j,3);
                                 tblsales.getModel().setValueAt("",j,4);
                                 
                     
                     }
    }
//    
     
     public void fillcustomerdates()
     {
        // tblmorning.revalidate();
         
          tblpayment.setModel(dtm1);
          dtm1.setRowCount(0);
          String daysdi=daysdiff.getText();
          int daysdi1=Integer.parseInt(daysdi);
          //  tblmorning.setAutoCreateColumnsFromModel( true ); 
         String dateget=txtfromdate.getText();
                            Date d1=null;
            SimpleDateFormat sdf4=new SimpleDateFormat(DATE_FORMAT_NOW);
           try{
               
                                d1=sdf4.parse(dateget);
             tblpayment.setAutoResizeMode(tblpayment.AUTO_RESIZE_OFF);               
           int columncount=tblpayment.getColumnCount();
           if(columncount<2)
                 {
                dtm1.addColumn("Date");
                dtm1.addColumn("Session");
                dtm1.addColumn("S.Num");
                dtm1.addColumn("Milk");
                dtm1.addColumn("Fat");
                dtm1.addColumn("Money");
                 
           tblpayment.getColumnModel().getColumn(0).setPreferredWidth(80);
           tblpayment.getColumnModel().getColumn(1).setPreferredWidth(80);
           tblpayment.getColumnModel().getColumn(2).setPreferredWidth(70);
           tblpayment.getColumnModel().getColumn(3).setPreferredWidth(70);  
           tblpayment.getColumnModel().getColumn(4).setPreferredWidth(75); 
           tblpayment.getColumnModel().getColumn(5).setPreferredWidth(70);  
          // JOptionPane.showMessageDialog(SampleFrame.this,"HI");  
                 }  
                          
                          dtm1.setRowCount(0); 
                          for(int j=0;j<daysdi1;j++)
                            {
                                //JOptionPane.showMessageDialog(SampleFrame.this,"HI");
                            Calendar cal4=Calendar.getInstance();
                            cal4.setTime(d1);
                            cal4.add(Calendar.DATE,+j);
                            dtm1.addRow(new Object[]{sdf4.format(cal4.getTime()),"Morning"});  
                            dtm1.addRow(new Object[]{sdf4.format(cal4.getTime()),"Evening"}); 
                           
                           }
                          
                   DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
rightRenderer.setHorizontalAlignment( JLabel.CENTER );
tblpayment.getColumnModel().getColumn(0).setCellRenderer( rightRenderer );
tblpayment.getColumnModel().getColumn(1).setCellRenderer( rightRenderer );
tblpayment.getColumnModel().getColumn(2).setCellRenderer( rightRenderer );
tblpayment.getColumnModel().getColumn(3).setCellRenderer( rightRenderer );
tblpayment.getColumnModel().getColumn(4).setCellRenderer( rightRenderer );
tblpayment.getColumnModel().getColumn(5).setCellRenderer( rightRenderer );
                 // head.getColumnModel().setColumnMargin();
                  tblpayment.setShowVerticalLines(false);
                  tblpayment.setRowHeight(27);
             tblpayment.setForeground(Color.BLACK);
             tblpayment.setFont(new Font("Times New Roman",Font.PLAIN,14));
          // tblmorning.setColumnModel(null);
           tblpayment.setAutoCreateColumnsFromModel(false);
           tblpayment.repaint();
     }
      catch(Exception e)
                            {
                                
                            }
     }
     public void fillcustomersalesdates()
     {
        // tblmorning.revalidate();
         
          tblsales.setModel(dtm2);
          dtm2.setRowCount(0);
          String daysdi=daysdiff.getText();
          int daysdi1=Integer.parseInt(daysdi);
          //  tblmorning.setAutoCreateColumnsFromModel( true ); 
         String dateget=txtfromdate.getText();
                            Date d1=null;
            SimpleDateFormat sdf4=new SimpleDateFormat(DATE_FORMAT_NOW);
           try{
               
                                d1=sdf4.parse(dateget);
             tblsales.setAutoResizeMode(tblsales.AUTO_RESIZE_OFF);               
           int columncount=tblsales.getColumnCount();
           if(columncount<2)
                 {
                dtm2.addColumn("Date");
                dtm2.addColumn("Session");
                dtm2.addColumn("Milk");
                dtm2.addColumn("Money");
                dtm2.addColumn("Status");
                 
           tblsales.getColumnModel().getColumn(0).setPreferredWidth(80);
           tblsales.getColumnModel().getColumn(1).setPreferredWidth(60);
           tblsales.getColumnModel().getColumn(2).setPreferredWidth(50);
           tblsales.getColumnModel().getColumn(3).setPreferredWidth(50);  
           tblsales.getColumnModel().getColumn(4).setPreferredWidth(50); 
          // JOptionPane.showMessageDialog(SampleFrame.this,"HI");  
                 }  
                          
                          dtm2.setRowCount(0); 
                          for(int j=0;j<daysdi1;j++)
                            {
                                //JOptionPane.showMessageDialog(SampleFrame.this,"HI");
                            Calendar cal4=Calendar.getInstance();
                            cal4.setTime(d1);
                            cal4.add(Calendar.DATE,+j);
                            dtm2.addRow(new Object[]{sdf4.format(cal4.getTime()),"Morning"});  
                            dtm2.addRow(new Object[]{sdf4.format(cal4.getTime()),"Evening"}); 
               
                           }
                          
                   DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
rightRenderer.setHorizontalAlignment( JLabel.CENTER );
tblsales.getColumnModel().getColumn(0).setCellRenderer( rightRenderer );
tblsales.getColumnModel().getColumn(1).setCellRenderer( rightRenderer );
tblsales.getColumnModel().getColumn(2).setCellRenderer( rightRenderer );
tblsales.getColumnModel().getColumn(3).setCellRenderer( rightRenderer );
tblsales.getColumnModel().getColumn(4).setCellRenderer( rightRenderer );

   
                 // head.getColumnModel().setColumnMargin();
                  tblsales.setShowVerticalLines(false);
                  tblsales.setRowHeight(45);
             tblsales.setForeground(Color.BLACK);
             tblsales.setFont(new Font("Times New Roman",Font.PLAIN,14));
          // tblmorning.setColumnModel(null);
           tblsales.setAutoCreateColumnsFromModel(false);
           tblsales.repaint();
     }
      catch(Exception e)
                            {
                                
                            }
     }
     
     public void filltabledatesmorning()
    { 
      try
          
      {
          String days=daysdiff.getText();
          int daysd=Integer.parseInt(days);
          int daysds=(2*(daysd-1)+1);
           // tblpayment.setModel(dtm);
          //tblpayment.setAutoCreateColumnsFromModel( false );  
          //String days1=daysdiff.getText();
        // int days2=Integer.parseInt(days1);
          SimpleDateFormat sdf4=new SimpleDateFormat(DATE_FORMAT_NOW);
             con10=DriverManager.getConnection(host);
            stmt10=con10.createStatement();
            stmt10.executeQuery("SELECT CARD_NUM,SAMPLE_N0,MILK,FAT,MONEY,DATE,SESSION FROM DAILYDATA where CARD_NUM="+txtcardn0.getText());
            res10=stmt10.getResultSet();
         
           tblpayment.setAutoResizeMode(tblpayment.AUTO_RESIZE_OFF);
           int columncount=tblpayment.getColumnCount();
           //JOptionPane.showMessageDialog(SampleFrame.this,columncount);
           
//                 if(Session_morning.equals(Session))
//                 { 
//                     for(int j=0;j<10;j++)
//                    {
//                         Calendar cal4=Calendar.getInstance();
//                         //cal4.setTime(d1);
//                           cal4.add(Calendar.DATE,-j);
//                           dtm.addRow(new Object[]{sdf4.format(cal4.getTime())});    
//                    }
//                     for(int j=0;j<=9;j++)
//                        {
//                            Calendar cal2=Calendar.getInstance();
//                            cal2.add(Calendar.DATE,-j);      
//                            SimpleDateFormat sdf1=new SimpleDateFormat(DATE_FORMAT_NOW);
                            //dtm.addRow(new Object[] {Date});
                               //  tblmorning.getModel().setValueAt((sdf1.format(cal2.getTime())),j,0);
                                 
                      int x=0;  
                      Double milkmetotal=0.0;
                      Double totalfat=0.0;
                      Double totalmoney=0.0;
                     while(res10.next())
                    {
//               
                    
                    String value1=res10.getString("DATE");
                    Object S_n0=res10.getInt("SAMPLE_N0");
                    Object Milk=res10.getDouble("MILK");
                    Object Fat=res10.getString("FAT");
                    String session=res10.getString("SESSION");
                    String m_session="Morning";
                    String e_session="Evening";
                    Object Money=res10.getString("MONEY");
         
                     for(int k=0;k<=daysds;k++)
                     {
                         if((session.equals(tblpayment.getValueAt(k, 1)))&&(value1.equals(tblpayment.getValueAt(k,0))))
                         {
                                 tblpayment.getModel().setValueAt(S_n0,k,2);
                                 tblpayment.getModel().setValueAt(Milk,k,3);
                                 tblpayment.getModel().setValueAt(Fat,k,4);
                                 tblpayment.getModel().setValueAt(Money,k,5); 
                                 String milktotalksum=tblpayment.getValueAt(k,3).toString();
                                 Double milksum=Double.parseDouble(milktotalksum);
                                 milkmetotal=milkmetotal+milksum;
                                 String fatag=tblpayment.getValueAt(k,4).toString();
                                 Double fatag1=Double.parseDouble(fatag);
                                 totalfat=totalfat+fatag1;
                                String moneysum=tblpayment.getValueAt(k, 5).toString();
                                Double moneysum1=Double.parseDouble(moneysum);
                                totalmoney=totalmoney+moneysum1;
                                 x=x+1;
                                
                         }
                    }
                 }
                     
                 // head.getColumnModel().setColumnMargin();
                  tblpayment.setShowVerticalLines(false);
                  tblpayment.setRowHeight(27);
              
             tblpayment.setForeground(Color.BLACK);
             tblpayment.setFont(new Font("Times New Roman",Font.PLAIN,14));
                  String valuex=Integer.toString(x);
                 txtsessioncount.setText(valuex);
                 String milktotalvalue=df.format(milkmetotal);
                 txttmilk.setText(milktotalvalue);
                Double result=totalfat/x;
                String fatavgvalue=df.format(result);
                txtfatavg.setText(fatavgvalue);
                String totalmoney1=df.format(totalmoney);
                txttmoney.setText(totalmoney1);
                // JOptionPane.showMessageDialog(PaymentData.this,milkmetotal);
      
             //cleartablemorning();
            res10.close();
            stmt10.close();
            con10.close();
                 }
           
             catch(SQLException err)
                {
            JOptionPane.showMessageDialog(PaymentData.this,err);
                }
    }
     public void cleartablesales()
     {
         String dated=daysdiff.getText();
         int dates=Integer.parseInt(dated);
         int datsd=2*(dates-1);
         for(int k=0;k<=datsd;k++)
         {
             tblpayment.setValueAt("", k, 2);
             tblpayment.setValueAt("", k, 3);
             tblpayment.setValueAt("", k, 4);
             tblpayment.setValueAt("", k, 5);
             tblsales.setValueAt("", k, 2);
             tblsales.setValueAt("", k, 3);
             tblsales.setValueAt("", k, 4);
             
         }
         
     }
     public void filltabledatessales()
    { 
      try
          
      {
          String days=daysdiff.getText();
          int daysd=Integer.parseInt(days);
          int daysds=(2*(daysd-1)+1);
           // tblpayment.setModel(dtm);
          //tblpayment.setAutoCreateColumnsFromModel( false );  
          //String days1=daysdiff.getText();
        // int days2=Integer.parseInt(days1);
          SimpleDateFormat sdf4=new SimpleDateFormat(DATE_FORMAT_NOW);
             con11=DriverManager.getConnection(host);
            stmt11=con11.createStatement();
            stmt11.executeQuery("SELECT MILK,MONEY,RECIEVED,DATE,SESSION FROM DAILYSALES where CARD_NUM="+txtcardn0.getText());
            res11=stmt11.getResultSet();
         
           tblsales.setAutoResizeMode(tblsales.AUTO_RESIZE_OFF);
           int columncount=tblsales.getColumnCount();
           //JOptionPane.showMessageDialog(SampleFrame.this,columncount);
           
//                 if(Session_morning.equals(Session))
//                 { 
//                     for(int j=0;j<10;j++)
//                    {
//                         Calendar cal4=Calendar.getInstance();
//                         //cal4.setTime(d1);
//                           cal4.add(Calendar.DATE,-j);
//                           dtm.addRow(new Object[]{sdf4.format(cal4.getTime())});    
//                    }
//                     for(int j=0;j<=9;j++)
//                        {
//                            Calendar cal2=Calendar.getInstance();
//                            cal2.add(Calendar.DATE,-j);      
//                            SimpleDateFormat sdf1=new SimpleDateFormat(DATE_FORMAT_NOW);
                            //dtm.addRow(new Object[] {Date});
                               //  tblmorning.getModel().setValueAt((sdf1.format(cal2.getTime())),j,0);
                                 
                      Double salesnot=0.0;
                      Double moneygiven3=0.0;
                      Double actualsales=0.0;
                     while(res11.next())
                    {
//               
                    
                    String value1=res11.getString("DATE");
                    Object milk=res11.getInt("MILK");
                    Object money=res11.getDouble("MONEY");
                    Object status=res11.getString("RECIEVED");
                    String session=res11.getString("SESSION");
                    //String csession="Morning";
                   // Object Money=res11.getString("MONEY");
         
                     for(int k=0;k<=daysds;k++)
                     {
                         if((session.equals(tblsales.getValueAt(k, 1)))&&(value1.equals(tblsales.getValueAt(k,0))))
                         {
                                 tblsales.getModel().setValueAt(milk,k,2);
                                 tblsales.getModel().setValueAt(money,k,3);
                                 tblsales.getModel().setValueAt(status,k,4);
                                 if(status.equals("YES"))
                                 {
                                 String salesnotgiven=tblsales.getValueAt(k,3).toString();
                                 Double salesnotgiven1=Double.parseDouble(salesnotgiven);
                                 salesnot=salesnot+salesnotgiven1;
                                 }
                                 else if(status.equals(" "))
                                 {
                                 String moneygiven=tblsales.getValueAt(k,3).toString();
                                 Double moneygiven1=Double.parseDouble(moneygiven);
                                 moneygiven3=moneygiven3+moneygiven1;
                                 }
                                // tblsales.getModel().setValueAt(Money,k,5);                                
                         }
                    }
                 }
                     tblsales.setBackground(Color.WHITE) ;
                 // head.getColumnModel().setColumnMargin();
                  tblsales.setShowVerticalLines(false);
                  tblsales.setRowHeight(45);
             tblsales.setForeground(Color.BLACK);
             tblsales.setFont(new Font("Times New Roman",Font.PLAIN,14));
              String salesnotgiven2=df.format(salesnot);
                txtsales.setText(salesnotgiven2);  
                String moneygiven4=df.format(moneygiven3);
                txtgiven.setText(moneygiven4);
             actualsales=salesnot+moneygiven3;
            String actualsales1=df.format(actualsales);
            txttogivem.setText(actualsales1);   
      
             //cleartablemorning();
            res11.close();
            stmt11.close();
            con11.close();
                 }
           
             catch(SQLException err)
                {
            JOptionPane.showMessageDialog(PaymentData.this,err);
                }
    }
public void clearresultsetevening()
      {
          
          try
          {
            //tblpayment.setModel(dtm1);  
            con1=DriverManager.getConnection(host);
            stmt1=con1.createStatement();
            stmt1.executeQuery("SELECT CARD_NUM,SAMPLE_N0,MILK,FAT,MONEY,DATE,SESSION FROM DAILYDATA where CARD_NUM="+txtcardn0.getText());
            res1=stmt1.getResultSet();
           
             double milkmetotal=0.0;
             double totalfat=0.0;
             double totalmoney=0.0;
            int x=0;
          
           while(res1.next())
             {
                
                 String Dateget=txttodate.getText();
                 
                 String Session_morning="Morning";
                 String Session_evening="Evening";
               
                    String value1=res1.getString("DATE");
                    String session=res1.getString("SESSION");
                    Object S_n0=res1.getInt("SAMPLE_N0");
                    Object Milk=res1.getDouble("MILK");
                    Object Fat=res1.getString("FAT");
                    Object Money=res1.getString("MONEY");
                      
                     for(int l=0;l<=180;l++)
                     {
                         if((value1.equals(tblpayment.getValueAt(l,0)))&&(session.equals(Session_evening)))
                         {
                           
                                 // tblpayment.getModel().setValueAt(session,m,1);
                                 tblpayment.setBackground(Color.ORANGE);
                                 tblpayment.getModel().setValueAt(S_n0,l+1,2);
                                 tblpayment.getModel().setValueAt(Milk,l+1,3);
                                 tblpayment.getModel().setValueAt(Fat,l+1,4);
                                 tblpayment.getModel().setValueAt(Money,l+1,5);  
                                 String milktotalksum=tblpayment.getValueAt(l+1,3).toString();
                                 Double milksum=Double.parseDouble(milktotalksum);
                                 milkmetotal=milkmetotal+milksum;
                                 String fatag=tblpayment.getValueAt(l+1,4).toString();
                                 Double fatag1=Double.parseDouble(fatag);
                                 totalfat=totalfat+fatag1;
                                String moneysum=tblpayment.getValueAt(l+1, 5).toString();
                                Double moneysum1=Double.parseDouble(moneysum);
                                totalmoney=totalmoney+moneysum1;
                    
                                 l=l+2;
                                 x=x+1;
                         }
                         
                     }
                     
                     for(int m=0;m<=180;m++)
                     {
                         if((value1.equals(tblpayment.getValueAt(m,0)))&&(session.equals(Session_morning)))
                         {
                                 // tblpayment.getModel().setValueAt(session,m,1);
                                 tblpayment.setBackground(Color.GREEN);
                                  tblpayment.getModel().setValueAt(S_n0,m,2);
                                   tblpayment.getModel().setValueAt(Milk,m,3);
                                 tblpayment.getModel().setValueAt(Fat,m,4);
                                 tblpayment.getModel().setValueAt(Money,m,5);  
                                 String milktotalksum=tblpayment.getValueAt(m,3).toString();
                                 Double milksum=Double.parseDouble(milktotalksum);
                                 milkmetotal=milkmetotal+milksum;
                                 
                                 String fatag=tblpayment.getValueAt(m,4).toString();
                                 Double fatag1=Double.parseDouble(fatag);
                                 totalfat=totalfat+fatag1;
                                 
                                 String moneysum=tblpayment.getValueAt(m , 5).toString();
                    Double moneysum1=Double.parseDouble(moneysum);
                    totalmoney=totalmoney+moneysum1;
                                 m=m+2;
                                 x=x+1;
                         }
                         
                     }
                     
             }
           String valuex=Integer.toString(x);
           txtsessioncount.setText(valuex);
           String milktotalvalue=df.format(milkmetotal);
           txttmilk.setText(milktotalvalue);
           Double result=totalfat/x;
           String fatavgvalue=df.format(result);
           txtfatavg.setText(fatavgvalue);
           String totalmoney1=df.format(totalmoney);
           txttmoney.setText(totalmoney1);
            res1.close();
            stmt1.close();
            con1.close();
          }
           
             catch(SQLException err)
                {
            JOptionPane.showMessageDialog(PaymentData.this,err);
                }
      }
public void resetallvalues()
{
    txtsessioncount.setText("0");
    txttmilk.setText("0");
    txtfatavg.setText("0");
    txttmoney.setText("0");
    txtsales.setText("0");
    txtgiven.setText("0");
    txttogivem.setText("0");
    txtnetamt.setText("0");
}
public void clearresultsetevening2()
      {
          
          try
          {
            con9=DriverManager.getConnection(host);
            stmt9=con9.createStatement();
            stmt9.executeQuery("SELECT CARD_NUM,SAMPLE_N0,MILK,FAT,MONEY,DATE,SESSION FROM DAILYDATA where CARD_NUM="+txtcardn0.getText());           
            res9=stmt9.getResultSet();
            
             
             while(res9.next())
             {
                 
                 
                    String value1=res9.getString("DATE");
                    String session=res9.getString("SESSION");
                    Object S_n0=res9.getInt("SAMPLE_N0");
                    Object Milk=res9.getDouble("MILK");
                    Object Fat=res9.getString("FAT");
                    String Session_morning="Morning";
                    String Session_evening="Evening";
                    Object Money=res9.getString("MONEY");
//for(int j=0;j<6;j++)
//{
                  //  tblpayment.setValueAt(S_n0,j,3);
                //     JOptionPane.showMessageDialog(PaymentData.this,rowcount);
                    int rowcount=tblpayment.getRowCount();
                    while(rowcount!=0)
                    {
                    for(int i=0;i<rowcount;i++)
                    {
                        if(value1.equals(tblpayment.getValueAt(i,0)))
                         {
                          tblpayment.setValueAt("success",i,3);
                         }
                        else
                        {
                            tblpayment.setValueAt("fail",i,3);
                        }
                    }
                    rowcount--;
                    }
                        
//                         if((value1.equals(tblpayment.getValueAt(i,0)))&&(session.equals(Session_morning))&&(tblpayment.getValueAt(i,0).equals("AM")))
//                         {
//                                  tblpayment.getModel().setValueAt(S_n0,i,2);
//                                   tblpayment.getModel().setValueAt(Milk,i,3);
//                                 tblpayment.getModel().setValueAt(Fat,i,4);
//                                 tblpayment.getModel().setValueAt(Money,i,5);  
//                                 String milktotalksum=tblpayment.getValueAt(i,3).toString();
//                                 Double milksum=Double.parseDouble(milktotalksum);
//                                 milkmetotal=milkmetotal+milksum;
//                                 
//                                 String fatag=tblpayment.getValueAt(i,4).toString();
//                                 Double fatag1=Double.parseDouble(fatag);
//                                 totalfat=totalfat+fatag1;
//                                 
//                                 String moneysum=tblpayment.getValueAt(i , 5).toString();
//                                Double moneysum1=Double.parseDouble(moneysum);
//                                totalmoney=totalmoney+moneysum1;  
//                                i=i+1;
//                         }
//                         else
//                         {
//                             //JOptionPane.showMessageDialog(PaymentData.this,"nothing");
//                         }
//                       
//            String valuex=Integer.toString(x);
//           txtsessioncount.setText(valuex);
//           String milktotalvalue=df.format(milkmetotal);
//           txttmilk.setText(milktotalvalue);
//           Double result=totalfat/x;
//           String fatavgvalue=df.format(result);
//           txtfatavg.setText(fatavgvalue);
//           String totalmoney1=df.format(totalmoney);
//           txttmoney.setText(totalmoney1);
//           
             }
            res9.close();
            stmt9.close();
            con9.close();
          }
           
             catch(SQLException err)
                {
            JOptionPane.showMessageDialog(PaymentData.this,err);
                }
      }
 public void CusotomerName()
    {
            try{
                     con6=DriverManager.getConnection(host);
                     stmt6=con6.createStatement();
                     java.sql.Timestamp sqlDate= new java.sql.Timestamp(new java.util.Date().getTime());
                     stmt6.executeQuery("SELECT CARD_NUM,CUST_NAME FROM CUSTOMERS where CARD_NUM="+txtcardn0.getText()); 
                     rs5=stmt6.getResultSet();
                             if(rs5.next())
                                 {  
                                         String name2=rs5.getString("CUST_NAME");
                                         lblname.setText(name2);
                                         lblname.setToolTipText("Editing Possible in Registration Page"); 
                                       if(name2.equals(""))
                                       {
                                          
                                           lblname.setText("Name is empty");
                                       }
                                 }
                             else
                             {
                                 
                                 lblname.setText("");
                                 
                             }
                              rs5.close();
                     stmt6.close();
                    
                     con6.close();
                  
                }
             catch(SQLException err)
                 {
                    // JOptionPane.showMessageDialog(SampleFrame.this,err);
                    }
            }  
public void verifysales1()
      {
          try
          {
            con2=DriverManager.getConnection(host);
            stmt2=con2.createStatement();
            stmt2.executeQuery("SELECT CARD_NUM,SALES_NAME,MILK,MONEY,DATE,SESSION,RECIEVED FROM DAILYSALES where CARD_NUM="+txtcardn0.getText());
            res2=stmt2.getResultSet();
            
             double milkmetotal=0.0;
             double totalfat=0.0;
             double totalmoney=0.0;
            int x=0;
           Double salesnot=0.0;
           Double moneygiven3=0.0;
           Double actualsales;
           while(res2.next())
             {
                 String Date=res2.getString("DATE");
                 String Dateget=txttodate.getText();
                 String Session=res2.getString("SESSION");
                 String Session_morning="Morning";
                 String Session_evening="Evening";
                 int i=1;
                  DateDisplay();
                     i=i+1;
                  
                    String value1=res2.getString("DATE");
                    String session=res2.getString("SESSION");
                    Object salesname=res2.getString("SALES_NAME");
                    Object Milk=res2.getString("MILK");
                     Object status=res2.getString("RECIEVED");
                    Object Money=res2.getString("MONEY");
                   
                      
                     for(int l=0;l<=180;l++)
                     {
                         if((value1.equals(tblsales.getValueAt(l,0)))&&(session.equals(Session_evening)))
                         {
                             
                                // tblpayment.getModel().setValueAt(session,m,1);
                        
                                 tblsales.getModel().setValueAt(Milk,l+1,2);
                                 tblsales.getModel().setValueAt(Money,l+1,3); 
                                 tblsales.getModel().setValueAt(status,l+1,4);
                                 if(status.equals("NO"))
                                 {
                                 String salesnotgiven=tblsales.getValueAt(l+1,3).toString();
                                 Double salesnotgiven1=Double.parseDouble(salesnotgiven);
                                 salesnot=salesnot+salesnotgiven1;
                                 }
                                 else if(status.equals(" "))
                                 {
                                 String moneygiven=tblsales.getValueAt(l+1,3).toString();
                                 Double moneygiven1=Double.parseDouble(moneygiven);
                                 moneygiven3=moneygiven3+moneygiven1;
                                 }
                                 
                                 l=l+2;
                                 x=x+1;
                         }
                         
                     }
            
                     for(int m=0;m<=180;m++)
                     {
                         if((value1.equals(tblsales.getValueAt(m,0)))&&(session.equals(Session_morning)))
                         {
                                 // tblpayment.getModel().setValueAt(session,m,1);
                                 
                                   tblsales.getModel().setValueAt(Milk,m,2);
                                 
                                 tblsales.getModel().setValueAt(Money,m,3);  
                                 tblsales.getModel().setValueAt(status,m,4);
                                 if(status.equals("NO"))
                                 {
                                 String salesnotgiven=tblsales.getValueAt(m,3).toString();
                                 Double salesnotgiven1=Double.parseDouble(salesnotgiven);
                                 salesnot=salesnot+salesnotgiven1;
                                  
                                 }
                                  else if(status.equals(" "))
                                 {
                                 String moneygiven=tblsales.getValueAt(m,3).toString();
                                 Double moneygiven1=Double.parseDouble(moneygiven);
                                 moneygiven3=moneygiven3+moneygiven1;
                                 }
                                 
                                 m=m+2;
                                 x=x+1;
                                
                         }
                        
                         
                     }
              
             }
          String salesnotgiven2=df.format(salesnot);
          txtsales.setText(salesnotgiven2);  
          String moneygiven4=df.format(moneygiven3);
          txtgiven.setText(moneygiven4);
          actualsales=salesnot+moneygiven3;
          String actualsales1=df.format(actualsales);
          txttogivem.setText(actualsales1);
            res2.close();
            stmt2.close();
            con2.close();
          }
           
             catch(SQLException err)
                {
            JOptionPane.showMessageDialog(PaymentData.this,err);
                }
      }
    class ObservingTextField extends JTextField implements Observer {
    public void update(Observable o, Object arg) {
        final String DATE_FORMAT_NOW="yyyy-MM-dd";
        Calendar calendar = (Calendar) arg;
        SimpleDateFormat sdf=new SimpleDateFormat(DATE_FORMAT_NOW);
        
        DatePicker dp = (DatePicker) o;
        txttodate.setText(sdf.format(calendar.getTime()));
       // System.out.println(sdf.format(calendar.getTime()));
       // System.out.println("picked=" + dp.formatDate(calendar));
       // txtDate_Reg.setText(dp.formatDate(calendar));
    }
   }
public void calculatenet()
{
    String money=txttmoney.getText();
    Double money1=Double.parseDouble(money);
    String salesm=txttogivem.getText();
    Double salesm1=Double.parseDouble(salesm);
    Double result=money1-salesm1;
    String result_val=df.format(result);
    txtnetamt.setText(result_val);
    
}
public void verifysales()
      {
          try
          {
            con2=DriverManager.getConnection(host);
            stmt2=con2.createStatement();
            stmt2.executeQuery("SELECT CARD_NUM,SALES_NAME,MILK,MONEY,DATE,SESSION,RECIEVED FROM DAILYDATA where CARD_NUM="+txtcardn0.getText());
            res2=stmt2.getResultSet();
            
             double milkmetotal=0.0;
             double totalfat=0.0;
             double totalmoney=0.0;
            int x=0;
           Double salesnot=0.0;
           Double moneygiven3=0.0;
           Double actualsales;
           while(res2.next())
             {
                 String Date=res2.getString("DATE");
                 String Dateget=txttodate.getText();
                 String Session=res2.getString("SESSION");
                 String Session_morning="Morning";
                 String Session_evening="Evening";
                 int i=1;
              
                     i=i+1;
                  
                    String value1=res2.getString("DATE");
                    String session=res2.getString("SESSION");
                    Object S_n0=res2.getString("SALES_NAME");
                    Object Milk=res2.getString("MILK");
                   
                    Object Money=res2.getString("MONEY");
                    Object status=res2.getString("RECIEVED");
                      
                     for(int l=0;l<=180;l++)
                     {
                         if((value1.equals(tblsales.getValueAt(l,0)))&&(session.equals(Session_evening)))
                         {
                             
                                 // tblpayment.getModel().setValueAt(session,m,1);
                                 
                                 tblsales.getModel().setValueAt(Milk,l+1,2);
                                 tblsales.getModel().setValueAt(Money,l+1,3); 
                                 tblsales.getModel().setValueAt(status,l+1,4); 
                                 if(status.equals("NO"))
                                 {
                                 String salesnotgiven=tblsales.getValueAt(l+1,3).toString();
                                 Double salesnotgiven1=Double.parseDouble(salesnotgiven);
                                 salesnot=salesnot+salesnotgiven1;
                                 }
                                 else if(status.equals(" "))
                                 {
                                 String moneygiven=tblsales.getValueAt(l+1,3).toString();
                                 Double moneygiven1=Double.parseDouble(moneygiven);
                                 moneygiven3=moneygiven3+moneygiven1;
                                 }
                                 
                                 l=l+2;
                                 x=x+1;
                         }
                         
                     }
            
                     for(int m=0;m<=180;m++)
                     {
                         if((value1.equals(tblsales.getValueAt(m,0)))&&(session.equals(Session_morning)))
                         {
                                 // tblpayment.getModel().setValueAt(session,m,1);
                                 
                                   tblsales.getModel().setValueAt(Milk,m,2);
                                 
                                 tblsales.getModel().setValueAt(Money,m,3);  
                                 tblsales.getModel().setValueAt(status,m,4);
                                 if(status.equals("NO"))
                                 {
                                 String salesnotgiven=tblsales.getValueAt(m,3).toString();
                                 Double salesnotgiven1=Double.parseDouble(salesnotgiven);
                                 salesnot=salesnot+salesnotgiven1;
                                  
                                 }
                                  else if(status.equals(" "))
                                 {
                                 String moneygiven=tblsales.getValueAt(m,3).toString();
                                 Double moneygiven1=Double.parseDouble(moneygiven);
                                 moneygiven3=moneygiven3+moneygiven1;
                                 }
                                 
                                 m=m+2;
                                 x=x+1;
                                
                         }
                        
                         
                     }
              
             }
          String salesnotgiven2=df.format(salesnot);
          txtsales.setText(salesnotgiven2);  
          String moneygiven4=df.format(moneygiven3);
          txtgiven.setText(moneygiven4);
          actualsales=salesnot+moneygiven3;
          String actualsales1=df.format(actualsales);
          txttogivem.setText(actualsales1);
            res2.close();
            stmt2.close();
            con2.close();
          }
           
             catch(SQLException err)
                {
            JOptionPane.showMessageDialog(PaymentData.this,err);
                }
      }
public void calculatenet2()
{
    String money=txttmoney.getText();
    Double money1=Double.parseDouble(money);
    String salesm=txttogivem.getText();
    Double salesm1=Double.parseDouble(salesm);
    Double result=money1-salesm1;
    String result_val=df.format(result);
    txtnetamt.setText(result_val);
}
 

 public void DateDisplay()
     {
      String days1=daysdiff.getText();
      int days2=Integer.parseInt(days1);
         int k=0;
            String date1=txtfromdate.getText();
            SimpleDateFormat sdf3=new SimpleDateFormat(DATE_FORMAT_NOW);
            Date d1=null;
           try
           {
            
            d1=sdf3.parse(date1);
                     for(int j=0;j<days2;j++)
                        {
                           Calendar cal4=Calendar.getInstance();
                           cal4.setTime(d1);
                           cal4.add(Calendar.DATE,+j);
                           tblpayment.getModel().setValueAt(sdf3.format(cal4.getTime()),k,0);
                           tblpayment.getModel().setValueAt(sdf3.format(cal4.getTime()),k+1,0);
                           tblpayment.getModel().setValueAt("AM",k,1);
                           tblpayment.getModel().setValueAt("PM",k+1,1);
                          k=k+2;  
                        }
                     res3.close();
                     con3.close();
                     stmt3.close();
           }
           
    catch(Exception e)
    {

    }
     }
 public void DateDispalysales()
     {
      String days1=daysdiff.getText();
      int days2=Integer.parseInt(days1);
         int k=0;
            String date1=txtfromdate.getText();
            SimpleDateFormat sdf3=new SimpleDateFormat(DATE_FORMAT_NOW);
            Date d1=null;
           try
           {
            
                d1=sdf3.parse(date1);
                     for(int j=0;j<days2;j++)
                        {
                           Calendar cal4=Calendar.getInstance();
                           cal4.setTime(d1);
                           cal4.add(Calendar.DATE,+j);
                           tblsales.getModel().setValueAt(sdf3.format(cal4.getTime()),k,0);
                           tblsales.getModel().setValueAt(sdf3.format(cal4.getTime()),k+1,0);
                           tblsales.getModel().setValueAt("AM",k,1);
                           tblsales.getModel().setValueAt("PM",k+1,1);
                            k=k+2;  
                        }
                     res3.close();
                     con3.close();
                     stmt3.close();
           }
           
    catch(Exception e)
    {

    }
     }
// 
// public void DateDisplaySales()
//     {
//      String days1=daysdiff.getText();
//      int days2=Integer.parseInt(days1);
//         int k=0;
//            String date1=txttodate.getText();
//            SimpleDateFormat sdf3=new SimpleDateFormat(DATE_FORMAT_NOW);
//            Date d1=null;
//           try
//           {
//            con3=DriverManager.getConnection(host);
//            stmt3=con3.createStatement();
//            stmt3.executeQuery("SELECT CARD_NUM,SAMPLE_N0,MILK,FAT,MONEY,DATE,SESSION FROM DAILYDATA");
//            res3=stmt3.getResultSet();
//             tblsales.setModel(dtm);
//             int columncount=tblsales.getColumnCount();
//             if(columncount<6)
//             {
//                    dtm.addColumn("Date");
//                    dtm.addColumn("Session");
//                    dtm.addColumn("Sample_n0");
//                    dtm.addColumn("Milk");
//                    dtm.addColumn("Fat");
//                    dtm.addColumn("Money");
//             }
//                d1=sdf3.parse(date1);
//                     for(int j=0;j<days2;j++)
//                        {
//                           Calendar cal4=Calendar.getInstance();
//                           cal4.setTime(d1);
//                           cal4.add(Calendar.DATE,-j);
//                           dtm.addRow(new Object[]{sdf3.format(cal4.getTime()),"AM","","","",""});
//                           dtm.addRow(new Object[]{sdf3.format(cal4.getTime()),"PM","","","",""});
//                           // k=k+2;  
//                        }
//                     res3.close();
//                     con3.close();
//                     stmt3.close();
//           }
//           
//    catch(Exception e)
//    {
//
//    }
//     }
 public void originalDateDisplay()
     {
      String days1=daysdiff.getText();
      int days2=Integer.parseInt(days1);
         int k=0;
            String date1=txtfromdate.getText();
            SimpleDateFormat sdf3=new SimpleDateFormat(DATE_FORMAT_NOW);
            Date d1=null;
           try
           {
                d1=sdf3.parse(date1);
                     for(int j=0;j<days2;j++)
                        {
                           Calendar cal4=Calendar.getInstance();
                           
                           cal4.add(Calendar.DATE,+j);
                           tblpayment.getModel().setValueAt(sdf3.format(cal4.getTime()),k,0);
                           tblpayment.getModel().setValueAt(sdf3.format(cal4.getTime()),k+1,0);
                           tblpayment.getModel().setValueAt("AM",k,1);
                           tblpayment.getModel().setValueAt("PM",k+1,1);
                            k=k+2;  
                        }
     res4.close();
     stmt4.close();
     con4.close();
           }
           
    catch(Exception e)
    {

    }
     }
 public void originalDateDisplaysales()
     {
          DefaultTableModel dtm2 = new DefaultTableModel(0,0);
      String days1=daysdiff.getText();
      int days2=Integer.parseInt(days1);
         int k=0;
            String date1=txtfromdate.getText();
            SimpleDateFormat sdf3=new SimpleDateFormat(DATE_FORMAT_NOW);
            Date d1=null;
           try
           {
            
                d1=sdf3.parse(date1);
                     for(int j=0;j<days2;j++)
                        {
                           Calendar cal4=Calendar.getInstance();
                           
                           cal4.add(Calendar.DATE,+j);
                           tblsales.getModel().setValueAt(sdf3.format(cal4.getTime()),k,0);
                           tblsales.getModel().setValueAt(sdf3.format(cal4.getTime()),k+1,0);
                           tblsales.getModel().setValueAt("AM",k,1);
                           tblsales.getModel().setValueAt("PM",k+1,1);
                            k=k+2;  
                        }
                    con5.close();
                    stmt5.close();
                    res5.close();
           }
           
    catch(Exception e)
    {

    }
     }

 
// public void clearDateDisplaySALES()
//     {
//      String days1=daysdiff.getText();
//      int days2=Integer.parseInt(days1);
//      int days3=2*days2;
//         int k=0;
//            String date1=txttodate.getText();
//            SimpleDateFormat sdf3=new SimpleDateFormat(DATE_FORMAT_NOW);
//            Date d1=null;
//           try
//           {
//            con7=DriverManager.getConnection(host);
//            stmt7=con7.createStatement();
//            stmt7.executeQuery("SELECT CARD_NUM,SALES_NAME,MILK,MONEY,DATE,SESSION,RECIEVED FROM DAILYSALES");
//            res6=stmt7.getResultSet();
//             tblpayment.setModel(dtm);
//             int columncount3=tblsales.getColumnCount();
//             int rowcount3=tblsales.getRowCount();
//             if(columncount3<4)
//             {
//                    dtm.addColumn("Date");
//                    dtm.addColumn("Session");
//                    dtm.addColumn("Sample_n0");
//                    dtm.addColumn("Milk");
//                    dtm.addColumn("Fat");
//                    dtm.addColumn("Money");
//             }
//                d1=sdf3.parse(date1);
//                while(rowcount3!=0)
//                {
//                     for(int p=0;p<rowcount3;p++)
//                        {
//                           dtm.removeRow(p);
//                           rowcount3--;
//                        }
//                }
//     res7.close();
//           stmt7.close();
//           con7.close();
//           }
//    catch(Exception e)
//    {
//
//    }
//     }
// public void clearDateDisplay()
//     {
//      String days1=daysdiff.getText();
//      int days2=Integer.parseInt(days1);
//      int days3=2*days2;
//         int k=0;
//            String date1=txttodate.getText();
//            SimpleDateFormat sdf3=new SimpleDateFormat(DATE_FORMAT_NOW);
//            Date d1=null;
//           try
//           {
//            con7=DriverManager.getConnection(host);
//            stmt7=con7.createStatement();
//            stmt7.executeQuery("SELECT CARD_NUM,SAMPLE_N0,MILK,FAT,MONEY,DATE,SESSION FROM DAILYDATA");
//            res6=stmt7.getResultSet();
//             tblpayment.setModel(dtm);
//             int columncount=tblpayment.getColumnCount();
//             int rowcount=tblpayment.getRowCount();
//             if(columncount<6)
//             {
//                    dtm.addColumn("Date");
//                    dtm.addColumn("Session");
//                    dtm.addColumn("Sample_n0");
//                    dtm.addColumn("Milk");
//                    dtm.addColumn("Fat");
//                    dtm.addColumn("Money");
//             }
//                d1=sdf3.parse(date1);
//                while(rowcount!=0)
//                {
//                     for(int p=0;p<rowcount;p++)
//                        {
//                           dtm.removeRow(p);
//                           rowcount--;
//                        }
//                }
//     res7.close();
//           stmt7.close();
//           con7.close();
//           }
//    catch(Exception e)
//    {
//
//    }
  //   }
//public void clearDateDisplay()
//     {
//      String days1=daysdiff.getText();
//      int days2=Integer.parseInt(days1);
//         int k=0;
//            String date1=txttodate.getText();
//            SimpleDateFormat sdf3=new SimpleDateFormat(DATE_FORMAT_NOW);
//            Date d1=null;
//           try
//           {
//            con1=DriverManager.getConnection(host);
//            stmt1=con1.createStatement();
//            stmt1.executeQuery("SELECT CARD_NUM,SAMPLE_N0,MILK,FAT,MONEY,DATE,SESSION FROM DAILYDATA where CARD_NUM="+txtcardn0.getText());
//            res1=stmt1.getResultSet();
//             //tblpayment.setModel(dtm);
//             tblpayment.setModel(dtm);
//             int columncount=tblpayment.getColumnCount();
//             JOptionPane.showMessageDialog(PaymentData.this,columncount);
//             if(columncount<6)
//             {
//                    dtm.addColumn("Date");
//                    dtm.addColumn("Session");
//                    dtm.addColumn("Sample_n0");
//                    dtm.addColumn("Milk");
//                    dtm.addColumn("Fat");
//                    dtm.addColumn("Money");
//             
//                d1=sdf3.parse(date1);
//                     for(int j=0;j<days2;j++)
//                        {
//                           Calendar cal4=Calendar.getInstance();
//                           cal4.setTime(d1);
//                           cal4.add(Calendar.DATE,-j);
//                           tblpayment.getModel().setValueAt((sdf3.format(cal4.getTime())),k,0);
//                           tblpayment.getModel().setValueAt((sdf3.format(cal4.getTime())),k+1,0);
//                           tblpayment.getModel().setValueAt("AM",k,1);
//                           tblpayment.getModel().setValueAt("PM",k+1,1);
//                           dtm.addRow(new Object[]{sdf3.format(cal4.getTime()),"AM","","","",""});
//                           dtm.addRow(new Object[]{sdf3.format(cal4.getTime()),"PM","","","",""});
//                            k=k+2;  
////                        }
//     }
//    catch(Exception e)
//    {
//
//    }
     
 
//  public void verifyresult()
//    { 
//      try
//          
//      {
//            con2=DriverManager.getConnection(host);
//            stmt2=con2.createStatement();
//            stmt2.executeQuery("SELECT CARD_NUM,SALES_NAME,MONEY,MILK,RECIEVED,DATE,SESSION FROM DAILYSALES WHERE CARD_NUM='"+txtcardn0.getText()+"'");
//            res2=stmt2.getResultSet();
//       
//            int i=0;
//            Double moneysales=0.0;
//            while(res2.next())
//             {      
//                    String date=res2.getString("DATE");
//                    String session=res2.getString("DATE");
//                    String cardn02=res2.getString("CARD_NUM");
//                    Object Milk=res2.getString("MILK");
//                    Object status=res2.getString("RECIEVED");
//             if(date.equals(tblpayment.getValueAt(i,0)))      
//                     {
//                if(status.equals("NO"))
//                        {
//                         String Money=res2.getString("MONEY");
//                        Double moneypaidtotal=Double.parseDouble(Money);
//                        moneysales=moneysales+moneypaidtotal;
//                    String moneytotalpaid=df.format(moneysales);
//                    txtsales.setText(moneytotalpaid);
//                        }   
//                     }
//             else
//             {
//                 txtsales.setText("Nothing");
//             }
//             i++;
//                     
//             }
//                  else if(status.equals(" "))
//                            {
//                      
//                    String tocustomer=tblsales.getValueAt(i, 3).toString();
//                    Double tocustomer1=Double.parseDouble(tocustomer);
//                    tocustomerto=tocustomerto+tocustomer1;
//                    String tocustomerpaid=df.format(tocustomerto);
//                    txttocard.setText(tocustomerpaid);
//                  
//            res2.close();
//            stmt2.close();
//            con2.close();
//          }
//           
//             catch(SQLException err)
//                {
//            JOptionPane.showMessageDialog(PaymentData.this,err);
//                }
//    }
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
        tblpayment = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        txtcardn0 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        daysdiff = new javax.swing.JTextField();
        panel1 = new java.awt.Panel();
        jLabel19 = new javax.swing.JLabel();
        txtsessioncount = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txttmilk = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtfatavg = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txttmoney = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtsales = new javax.swing.JTextField();
        txtgiven = new javax.swing.JTextField();
        txttogivem = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        txtnetamt = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblsales = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        lblname = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        datepane = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setPreferredSize(new java.awt.Dimension(1375, 700));
        setResizable(false);

        txttodate.setFont(new java.awt.Font("Andalus", 1, 14)); // NOI18N
        txttodate.setForeground(new java.awt.Color(0, 0, 255));
        txttodate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txttodateMouseClicked(evt);
            }
        });
        txttodate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txttodateFocusLost(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 0, 0));
        jLabel16.setText("Milk Bill Payment From");

        txtfromdate.setFont(new java.awt.Font("Andalus", 1, 14)); // NOI18N
        txtfromdate.setForeground(new java.awt.Color(51, 51, 255));
        txtfromdate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtfromdateFocusLost(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 0, 0));
        jLabel18.setText("To");

        tblpayment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblpayment.setRowHeight(28);
        jScrollPane1.setViewportView(tblpayment);

        jLabel17.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 255));
        jLabel17.setText("Enter Customer Card N0:");

        txtcardn0.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtcardn0.setForeground(new java.awt.Color(51, 51, 255));
        txtcardn0.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtcardn0FocusLost(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 0, 0));
        jLabel20.setText("No of Days:");

        daysdiff.setFont(new java.awt.Font("Andalus", 1, 14)); // NOI18N

        panel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel19.setFont(new java.awt.Font("Andalus", 0, 18)); // NOI18N
        jLabel19.setText("Sessions:");

        txtsessioncount.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        txtsessioncount.setForeground(new java.awt.Color(51, 51, 255));
        txtsessioncount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtsessioncountFocusLost(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Andalus", 0, 18)); // NOI18N
        jLabel21.setText("Total Milk:");

        txttmilk.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        txttmilk.setForeground(new java.awt.Color(51, 51, 255));
        txttmilk.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txttmilkFocusLost(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Andalus", 0, 18)); // NOI18N
        jLabel22.setText("Avg Fat:");

        txtfatavg.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        txtfatavg.setForeground(new java.awt.Color(51, 51, 255));
        txtfatavg.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtfatavgFocusLost(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Andalus", 0, 18)); // NOI18N
        jLabel23.setText("Total M:");

        txttmoney.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        txttmoney.setForeground(new java.awt.Color(255, 51, 255));
        txttmoney.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txttmoneyFocusLost(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Monotype Corsiva", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 0, 255));
        jLabel30.setText("Customer Report");

        txtsales.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        txtsales.setForeground(new java.awt.Color(51, 51, 255));
        txtsales.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtsalesFocusLost(evt);
            }
        });

        txtgiven.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        txtgiven.setForeground(new java.awt.Color(51, 51, 255));
        txtgiven.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtgivenFocusLost(evt);
            }
        });

        txttogivem.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        txttogivem.setForeground(new java.awt.Color(255, 0, 255));
        txttogivem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txttogivemFocusLost(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Andalus", 0, 18)); // NOI18N
        jLabel29.setText("Money:");

        jLabel31.setFont(new java.awt.Font("Andalus", 0, 18)); // NOI18N
        jLabel31.setText("Given:");

        jLabel32.setFont(new java.awt.Font("Andalus", 0, 18)); // NOI18N
        jLabel32.setText("Sales:");

        jLabel33.setFont(new java.awt.Font("Andalus", 0, 18)); // NOI18N
        jLabel33.setText("Net:");

        txtnetamt.setBackground(new java.awt.Color(0, 0, 0));
        txtnetamt.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        txtnetamt.setForeground(new java.awt.Color(255, 255, 102));
        txtnetamt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtnetamtFocusLost(evt);
            }
        });

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel30))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel23)
                                .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel22))
                            .addComponent(jLabel32)
                            .addComponent(jLabel33)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtsessioncount, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtnetamt, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                                .addComponent(txtgiven)
                                .addComponent(txtsales)
                                .addComponent(txttmoney)
                                .addComponent(txtfatavg)
                                .addComponent(txttmilk)
                                .addComponent(txttogivem)))))
                .addContainerGap(110, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addGap(26, 26, 26)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsessioncount)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttmilk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addGap(20, 20, 20)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(txtfatavg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttmoney, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))))
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(txtsales, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtgiven, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31))
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttogivem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29))
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtnetamt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33))
                        .addGap(10, 10, 10))))
        );

        jButton1.setBackground(new java.awt.Color(255, 204, 204));
        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton1.setText("Print Cusotmer Data");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tblsales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblsales.setRowHeight(28);
        jScrollPane3.setViewportView(tblsales);

        jLabel24.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(51, 51, 255));
        jLabel24.setText("Name:");

        lblname.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        lblname.setText(" ");

        jButton2.setBackground(new java.awt.Color(255, 204, 204));
        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton2.setText("Print Sales data");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        datepane.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel16)
                            .addGap(16, 16, 16)
                            .addComponent(txtfromdate, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(15, 15, 15)
                            .addComponent(jLabel18)
                            .addGap(18, 18, 18)
                            .addComponent(txttodate, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(datepane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(88, 88, 88)
                            .addComponent(jLabel20)
                            .addGap(48, 48, 48)
                            .addComponent(daysdiff, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel17)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtcardn0, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(13, 13, 13)
                                    .addComponent(jLabel24))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(datepane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(txtfromdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18)
                        .addComponent(txttodate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20)
                        .addComponent(daysdiff, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtcardn0)
                    .addComponent(jLabel24)
                    .addComponent(lblname))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(40, 40, 40))
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtcardn0FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcardn0FocusLost
        // TODO add your handling code here:
        
      // cleardata();
        if(txtcardn0.getText().equals(""))
        {
            JOptionPane.showMessageDialog(PaymentData.this, "Please Enter Customer ID");
        }
        else
        {
            
        
        resetallvalues();
       CusotomerName();
       cleartablesales();
       filltabledatesmorning();
       filltabledatessales();
       calculatenet();
        //clearresultsetevening();   
        }
       // DateDisplay();
        
       // DateDispalysales();
       // verifysales1();
       // clearresultsetevening();
       // txtnetamt.setText("");
        //verifysales();
    }//GEN-LAST:event_txtcardn0FocusLost

    private void txtsessioncountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtsessioncountFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsessioncountFocusLost

    private void txttmilkFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txttmilkFocusLost
        // TODO add your handling code here:
//        calculate();
    }//GEN-LAST:event_txttmilkFocusLost

    private void txtfatavgFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtfatavgFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfatavgFocusLost

    private void txttmoneyFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txttmoneyFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txttmoneyFocusLost

    private void txtsalesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtsalesFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsalesFocusLost

    private void txtgivenFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtgivenFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtgivenFocusLost

    private void txttogivemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txttogivemFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txttogivemFocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 final MessageFormat header = new MessageFormat("Card N0:"+txtcardn0.getText()+"  ("+txtfromdate.getText()+" to "+txttodate.getText()+")  Total:"+txtnetamt.getText());
        try {
    tblpayment.print(JTable.PrintMode.FIT_WIDTH, header,null);
} catch (java.awt.print.PrinterException e) {
    System.err.format("Cannot print %s%n", e.getMessage());
}   
    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtnetamtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtnetamtFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnetamtFocusLost

    private void txtfromdateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtfromdateFocusLost
        // TODO add your handling code here:
        //cleardata();
     
        datediff();
        fillcustomerdates();
        fillcustomersalesdates();
        
       // DateDisplay();
        
       // DateDispalysales();
    }//GEN-LAST:event_txtfromdateFocusLost

    private void txttodateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txttodateFocusLost
        // TODO add your handling code here
       //cleardata();
      //  datediff();
      //  DateDisplay();
         datediff();
        fillcustomerdates();
        fillcustomersalesdates();
      //  DateDispalysales();
    }//GEN-LAST:event_txttodateFocusLost

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         final MessageFormat header = new MessageFormat("Card N0:"+txtcardn0.getText()+"  ("+txtfromdate.getText()+" to "+txttodate.getText()+")  Total:"+txtnetamt.getText());
        try {
    tblsales.print(JTable.PrintMode.FIT_WIDTH, header,null);
} catch (java.awt.print.PrinterException e) {
    System.err.format("Cannot print %s%n", e.getMessage());
}   
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txttodateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttodateMouseClicked
        // TODO add your handling code here:
             final ObservingTextField txtDate_Reg = new ObservingTextField();
      //   public void actionPerformed(ActionEvent e) {
                // instantiate the DatePicker
                DatePicker dp = new DatePicker(txtDate_Reg);
                //JFrame fixdate=new SampleFrame();
                //datepane.removeAll();
                txttodate.add(txtDate_Reg);
                //datepane.add(dp.addObserver(Object o));
                // previously selected date
                Date selectedDate = dp.parseDate(txtDate_Reg.getText());
                dp.setSelectedDate(selectedDate);
                dp.start(txtDate_Reg);
    }//GEN-LAST:event_txttodateMouseClicked

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
            java.util.logging.Logger.getLogger(PaymentData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PaymentData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PaymentData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PaymentData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new PaymentData().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane datepane;
    private javax.swing.JTextField daysdiff;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblname;
    private java.awt.Panel panel1;
    private javax.swing.JTable tblpayment;
    private javax.swing.JTable tblsales;
    private javax.swing.JTextField txtcardn0;
    private javax.swing.JTextField txtfatavg;
    private javax.swing.JTextField txtfromdate;
    private javax.swing.JTextField txtgiven;
    private javax.swing.JTextField txtnetamt;
    private javax.swing.JTextField txtsales;
    private javax.swing.JTextField txtsessioncount;
    private javax.swing.JTextField txttmilk;
    private javax.swing.JTextField txttmoney;
    private javax.swing.JTextField txttodate;
    private javax.swing.JTextField txttogivem;
    // End of variables declaration//GEN-END:variables
}
