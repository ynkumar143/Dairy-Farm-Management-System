/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dairyvsr;
import java.util.*;
import java.awt.*;
import java.awt.print.*;
import java.awt.print.PageFormat;
import javax.swing.*;
import javax.swing.table.*;
 import java.awt.print.*;
import java.awt.geom.Rectangle2D;
import javax.swing.plaf.basic.*;
import java.awt.Color;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.Font;
import java.awt.*;     
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import javax.swing.*;     
import javax.swing.JFrame;
import java.lang.Integer;
import javax.swing.border.Border;  
import javax.swing.border.LineBorder;  
import javax.swing.border.TitledBorder;  
import javax.swing.table.*;    
import java.awt.event.*;  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.lang.Integer;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.table.TableColumn;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
 import java.io.PrintWriter;
import java.awt.*;
import java.awt.event.*;
import java.util.EventObject;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Puppy
 */
public class PaymentDisplay extends javax.swing.JFrame {
String driver = "org.apache.derby.jdbc.ClientDriver";
        String host="jdbc:derby://localhost:1527/D:/Dairy_Database/.netbeans-derby/DairyDatabase;user=admin;password=admin";
        DefaultTableModel dtm = new DefaultTableModel(0,0); 
        DefaultTableModel dtm1 = new DefaultTableModel(0,0); 
        DefaultTableModel dtm3 = new DefaultTableModel(0,0); 
         DefaultTableModel dtm5 = new DefaultTableModel(0,0); 
       //DefaultTableModel dtm1;       
  TableColumn tc;  
  int xw;
  JTable tblcu[];
   JTable tblcus[];
     JTable tblcud[];
   JTable tblcust=new JTable();
   JTableHeader head=tblcust.getTableHeader();
   private static Boolean[] states;
     private static Boolean[] states1;
      JPanel columnpanel2 = new JPanel();
  // private static Boolean[] checks;
      
   private static JCheckBox cb[];
   private static Vector arr[];
   private static Vector arrd[];
   Double sum=0.0;
   double[] vald;
   private static Boolean state;
 //    private static JCheckBox[] cb;
//     private static JCheckBox cb[]=new JCheckBox[getcound1]; 
//  JTableHeaderCheckBox.MyItemListener it;  
//  JTableHeaderCheckBox.CheckBoxHeader cbh;  
//  JTableHeaderCheckBox.CheckBoxHeader rendererComponent;  
        DefaultTableModel dtm2 = new DefaultTableModel(0,0); 
         DefaultTableModel dtm4 = new DefaultTableModel(0,0); 
 final DateFormat timeFormat=new SimpleDateFormat("HH:mm:ss");
 final String DATE_FORMAT_NOW="yyyy-MM-dd";
 DefaultListModel list=new DefaultListModel();
 int getcound1;
  private static JButton[] buttons;
   int w;
   //  private JTable CompTable = null;
// private PanelTableModel CompModel = new PanelTableModel();
 //   private JButton addButton = null;
  //private PanelTableModel CompModel = null;
//private JTable CompTable = null;
   int val1;
   int n;
   //private PanelTableModel CompModel = null;
  final String DATE_FORMAT_NOW1="yyyy-MM-dd";
  final DecimalFormat df = new DecimalFormat("#.##");
 final DecimalFormat df1 = new DecimalFormat("#");
 Connection con1,con2,con3,con4,con5,con6;
 ResultSet res1,res2,res3,res4,res5,res6;
 Statement stmt1,stmt2,stmt3,stmt4,stmt5,stmt6;
 int[] print;
 int[] arrdd;
 int[] ardd;
 int totaltables=0;
 int[] xwstates;
 int[] compare;
    /**
     * Creates new form PaymentDisplay
     */

    public PaymentDisplay() {
        
     
        initComponents();
        System.out.println("hahi1");
//        tabletest=Createcomptable();
        Createcomptable2();
         Createcomptable();
      System.out.println("hahi1");
     
        custlbl.setVisible(false);
        borderlayoutpanel1.setVisible(false);
        //columnpanel2.addMouseListener(this);
       // borderlayoutpanel1.action(PaymentDisplay,false);
//        jScrollPane1.setVisible(false);
      //  tabletest=Createcomptable();
      // ImagePanel panelinsert = new ImagePanel(new ImageIcon("images/background.png").getImage());  
        DateDisplay();
        
        countdatabase();
        String getcound=custlbl.getText();
        getcound1=Integer.parseInt(getcound);
       // JOptionPane.showMessageDialog(PaymentDisplay.this,getcound1);
        this.states=new Boolean[getcound1];
        this.cb=new JCheckBox[getcound1];
        this.tblcu=new JTable[getcound1];
        this.tblcus=new JTable[getcound1];
          this.tblcud=new JTable[getcound1];
        this.arrd=new Vector[getcound1];
        this.arrdd=new int[getcound1];
        this.ardd=new int[getcound1];
        this.print=new int[getcound1];
        this.xwstates=new int[getcound1];
        this.compare=new int[getcound1];
        this.vald=new double[getcound1];
        
         for(int mmm=0;mmm<getcound1;mmm++)
         {
             xwstates[mmm]=0;
             
         }
          
        //declareArray(getcound1);
        displaydatad();
        getresult();
        showresult1();
         addRow2();
       // addRow();
        datediff();
         
        
    }
    public void Createcomptable2()
{
  
  // addRow();
   
   // JTable tabletest=new JTable(CompModel);
    tabletest1.setModel(dtm5);
     tabletest1.setAutoResizeMode(tabletest.AUTO_RESIZE_OFF);
    tabletest1.setRowHeight(180);
    tabletest1.setTableHeader(null);
   
   // tabletest.setDefaultEditor(Object.class, PanelCellEditorRenderer);
   // return tabletest;
}
 public void Createcomptable()
{
  
  // addRow();
   
   // JTable tabletest=new JTable(CompModel);
    
    tabletest.setModel(dtm4);
   
     tabletest.setAutoResizeMode(tabletest.AUTO_RESIZE_OFF);
    tabletest.setRowHeight(200);
    tabletest.setTableHeader(null);
   tabletest.setRowSelectionAllowed(false);
   tabletest.setColumnSelectionAllowed(false);
  
   
   // tabletest.setDefaultEditor(Object.class, PanelCellEditorRenderer);
   // return tabletest;
}
public class Renderer extends DefaultTableCellRenderer{
 
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
         boolean hasFocus, int row, int column)
     {
 
        if(value instanceof JTable){
           //This time return only the JLabel without icon
            return (JTable)value;
        }
 
        else
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
 
     }
}
public class Renderer2 extends DefaultTableCellRenderer{
 
   // @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
         boolean hasFocus, int row, int column)
     {
 
       if(value instanceof JPanel){
           // JLabel label = (JPanel)value;
            //you can add the image here
           // label.setIcon(new ImageIcon(getClass().getResource("folder.png")));
          //label.setOpaque(true);
           // fillColor(table,label,isSelected);
 
            return (JPanel)value;
        }
 
        else
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
 
     }
}

public void addRow2() {
     //tabletest.setAutoCreateColumnsFromModel(true);
  
   int bav=tabletest1.getRowCount();
   //JOptionPane.showMessageDialog(PaymentDisplay.this,bav);
   Renderer2 PanelCellEditorRenderer = new Renderer2();
    tabletest1.setDefaultRenderer(Object.class, PanelCellEditorRenderer);
   int nag=tabletest1.getColumnCount();
     
    if(nag<1)
         {
        Object[] col = {"Kumar"};
        for(Object c: col)
           dtm5.addColumn(c);
         }
       if(bav==0)
       {
         Object[][] row1={{paneld}};
         for(Object[] r1:row1)
         dtm5.addRow(r1);
         tabletest1.getColumnModel().getColumn(0).setPreferredWidth(1600);
       }
     //  JOptionPane.showMessageDialog(PaymentDisplay.this,"hi");
        
       
        //super.addRow(new Object[]{new Comp()});
    }
 public void addRow() {
     //tabletest.setAutoCreateColumnsFromModel(true);
  
 
  // JOptionPane.showMessageDialog(PaymentDisplay.this,bav);
   Renderer PanelCellEditorRenderer = new Renderer();
    tabletest.setDefaultRenderer(Object.class, PanelCellEditorRenderer);
 int nag=tabletest.getColumnCount();
    dtm4.setRowCount(0);
     int bav=tabletest.getRowCount();
    //addRow2();
    if(nag<1)
         {
        Object[] col = {"Kumar"};
        for(Object c: col)
           dtm4.addColumn(c);
         
         }
    if(bav==0)
    {
         Object[][] row1={{tabletest1}};
         for(Object[] r1:row1)
              dtm4.addRow(r1);
    }
  
     // int nag1=tabletest.getColumnCount();
    //  JOptionPane.showMessageDialog(PaymentDisplay.this,"hi");
      
      for(w=0;w<getcound1;w++)
      {
          if(states[w]==true)
          {
               
             Object[][] row = {{(tblcu[w])}};
              for(Object[] r: row)
               dtm4.addRow(r);
//        
//          Object[][] row2 = {{(tblcu[7])}};
//        for(Object[] r2: row2)
//            dtm4.addRow(r2);
       
          }
      }
       tabletest.getColumnModel().getColumn(0).setPreferredWidth(1600);
       
        //super.addRow(new Object[]{new Comp()});
    }
      
        //JPanel panel = new JPanel(new GridBagLayout());
        //panel.add(buttontest);
        //return panel;
    //}


// class CompCellPanel extends JPanel {
//   CompCellPanel() {
//        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        //enableUpper(false);
//        tabletest.getColumnModel().getColumn(0).setPreferredWidth(1600);
//        //JOptionPane.showMessageDialog(PaymentDisplay.this,"hi");
//       // removeAll();
//        add(paneld);
//        
//       JOptionPane.showMessageDialog(PaymentDisplay.this,"hjjjki");
//     //  add(tblcu[xw]);
//      //  System.out.println("xw="+xw);
//        try{
//            
////        System.out.println(""); 
////    for (z=0;z<totaltables;z++ )  
////        {
////       //      
////                System.out.print(z+"--"+print[z]+"  ");
////              add(tblcu[print[z]]);
////              
////              // JTable xtb=tblcu[print[0]];
////
////              // add()
////         }
//   }
//   catch(Exception e){ System.out.println(e);}
//        //add(typeCombo);
//        //add(relationCombo);
//       // add(lowerField);
//     //   add(labelAnd);
//       // add(upperField);
//       // add(Box.createHorizontalStrut(100));
//       // add(removeButton);
//    }
////
////   
////
//    public void setComp(Comp Comp) {
//       
//       // enableUpper(Comp.relation == 2);
//    }
//
//    public Comp getComp() {
//        return new Comp(0,0,"","");
//    }
////
//}
// class PanelCellEditorRenderer  implements TableCellRenderer {
//
//    private static final long serialVersionUID = 1L;
//    private CompCellPanel renderer = new CompCellPanel();
//    //private CompCellPanel editor = new CompCellPanel();
//
//    @Override
//    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//        renderer.setComp((Comp) value);
//        return renderer;
//    }
//}
//class PanelTableModel extends DefaultTableModel {
//
//    private static final long serialVersionUID = 1L;
//
//     @Override
// 
//    public Class getColumnClass(int columnIndex) {
//        if(columnIndex == 0)return getValueAt(0, columnIndex).getClass();
// 
//        else return super.getColumnClass(columnIndex);
// 
//    }
//
//    public void addRow() {
//        
//        JOptionPane.showMessageDialog(PaymentDisplay.this,"Hi");
////        Object[][] row = {{new Comp(0, 0, "", "")}};
////        Object[] col = {"Column 1"};
////        for(Object c: col)
////            this.addColumn(c);
//// 
////    //Adding rows
////        for(Object[] r: row)
////            addRow(r);
//       super.addRow(new Object[]{new Comp(0,0,"","")});
//    }
//}
//  class MyModel extends javax.swing.table.DefaultTableModel{
// 
//    Object[][] row = {{tblcu[7]}};
// 
//    Object[] col = {"Column 1"};
// 
//    public MyModel (){
// 
//    //Adding columns
//        for(Object c: col)
//            this.addColumn(c);
// 
//    //Adding rows
//        for(Object[] r: row)
//            addRow(r);
// 
//    }
// 
//    @Override
// 
//    public Class getColumnClass(int columnIndex) {
//        if(columnIndex == 0)return getValueAt(0, columnIndex).getClass();
// 
//        else return super.getColumnClass(columnIndex);
// 
//    }
// 
//}
//class Comp {
//
//    public int type;
//    public int relation;
//    public String lower;
//    public String upper;
//
//    public Comp(int type, int relation, String lower, String upper) {
//        this.type = type;
//        this.relation = relation;
//        this.lower = lower;
//        this.upper = upper;
//    }
//}
//public void Createcomptable()
//{
//    
//   // CompModel.addRow();
//  CompModel = new PanelTableModel();
//
//   // JTable tabletest=new JTable(CompModel);
//    tabletest=new JTable(CompModel);
////    tabletest.setValueAt(tblcu[xw], 0, 0);
//    //JScrollPane CompTableScrollpane = new JScrollPane(tabletest, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
//     //           JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//   // CompTableScrollpane.add(tabletest);
//  //  tabletest.setAutoCreateColumnsFromModel(true);
//    tabletest.setAutoResizeMode(tabletest.AUTO_RESIZE_OFF);
//   
//    //tabletest.setRowHeight(800);
//     tabletest.setRowHeight(200);
//    tabletest.setAutoscrolls(true);
//    tabletest.setTableHeader(null);
//    
//         
//   // tabletest.setDefaultEditor(Object.class, PanelCellEditorRenderer);
////    return tabletest;
//}
   public void declareArray(int t)
    {
        int[] print=new int[getcound1];
        int totaltables=0;
    }
    public void DateDisplay(){
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
                    JOptionPane.showMessageDialog(PaymentDisplay.this,"Date Selection is not possible! Date after present day is not allowed");
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
//     public void CusotomerName()
//    {
//            try{
//                     con1=DriverManager.getConnection(host);
//                     stmt1=con1.createStatement();
//                     //java.sql.Timestamp sqlDate= new java.sql.Timestamp(new java.util.Date().getTime());
//                     stmt1.executeQuery("SELECT CARD_NUM,CUST_NAME FROM CUSTOMERS where CARD_NUM="+txtcardn0.getText()); 
//                     res1=stmt1.getResultSet();
//                             if(res1.next())
//                                 {  
//                                         String name2=res1.getString("CUST_NAME");
//                                         //tblcust.setValueAt(name2, 0, 1);
//                                         lblname.setText(name2);
//                                         lblname.setToolTipText("Editing Possible in Registration Page"); 
//                                       if(name2.equals(""))
//                                       {
//                                          
//                                           lblname.setText("Name is empty");
//                                       }
//                                 }
//                             else
//                             {
//                                 
//                                 lblname.setText("");
//                                 
//                             }
//                              res1.close();
//                     stmt1.close();
//                    
//                     con1.close();
//                  
//                }
//             catch(SQLException err)
//                 {
//                    JOptionPane.showMessageDialog(PaymentDisplay.this,err);
//                    }
//            }  
     
     public void deletecolumnd()
     {
           tblcu[w].setModel(dtm);
String days1=daysdiff.getText();
         int days2=Integer.parseInt(days1);
         TableColumn delcold=tblcu[w].getColumnModel().getColumn(tblcu[w].getColumnCount()-1);
         for(int z=0;z<(days2+6);z++)
        {
                 tblcu[w].removeColumn(delcold );
        }
         int columnd=tblcu[w].getColumnCount();
          tblcu[w].setAutoCreateColumnsFromModel( false );  
        //JOptionPane.showMessageDialog(PaymentDisplay.this,columnd);
       
     }
     public void cleartable()
     {
            
        // tblcust.re
       //  JOptionPane.showMessageDialog(PaymentDisplay.this,"hi");
         String days1=daysdiff.getText();
         int days2=Integer.parseInt(days1);
        for(int z=0;z<(days2+6);z++)
        {
          
         //JOptionPane.showMessageDialog(PaymentDisplay.this,days2);
        deletecolumnd();
        }
     }
//         int m=tblcust.getColumnCount();
//        for(int j=0;j<m;j++)
//                {
//         for(int i=0;i<n;i++)
//             {
//                
//                    dtm.removeRow(i);
//                }
//   }
//        dtm.removeRow(0);
//        dtm.removeRow(1);
     public void topaint()
     {
//         paneld.repaint();
        // paneld.repaint(paneld);
     }
 
     public void checkpanel()
     {
          JPanel pnl = new JPanel();
	 //set layout
	 pnl.setLayout(null);
	 	 
	 //create scroll panel
	 JScrollPane scroll = new JScrollPane(pnl);
	 
	 //set size of frame and panel to the size of the monitor
	 Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	 //f.setSize(dim.width,100);
	 pnl.setSize(dim.width,100);
	// pnl.add(scroll);
	 //add panel to scroll and scroll to frame
	 //f.add(scroll);
	 
	 //set scroll policy
	 scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	 scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	 
	 //add LABEL
	 JLabel lbl = new JLabel();
	 lbl.setSize((dim.width+500),50);
	 lbl.setBackground(Color.red);
	 lbl.setLocation(0,0);
	 lbl.setOpaque(true);
	 
	 //add to panel
	 pnl.add(lbl);
	
	 //make frame visible
//	 f.setVisible(true);
//        Container c=getContentPane();
//       //c.setBackground(Color.black);
//       // c.setForeground(Color.white);
//        paneld=new JPanel(new GridLayout(4,1));
//        int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
//        int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
//        JScrollPane jsb=new JScrollPane(paneld,v,h);
//        c.add(jsb);
//        //JScrollBar hbar = new JScrollBar(JScrollBar.HORIZONTAL, 30, 20, 0, 300);
//         //add(hbar, BorderLayout.SOUTH);
//          JCheckBox cb[]=new JCheckBox[10]; 
//        //   paneld.setLayout(new BoxLayout(paneld, BoxLayout.PAGE_AXIS));
//         //JPanel paneld = new JPanel(new GridLayout(2, 2, 20, 20));
//         //JPanel paneld=new JPanel();
//        for(int w=0;w<10;w++)
//            
//        {
//                cb[w]=new JCheckBox("checkbox:"+w);
//                cb[w].setVisible(true);
//        }
//         
//        for(int i=0;i<10;i++) {
//             paneld.add(cb[i]);
//           }
//         
//        JScrollPane sc1 = new JScrollPane(paneld);
//        sc1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        sc1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
       // sc1.setPreferredSize(new java.awt.Dimension(250, 250));
        // sc1.setViewportView(paneld);
//         paneld.repaint();
//         JCheckBox redCB, blueCB, greenCB, yellowCB;
//          //JPanel checkBoxPanel = new JPanel();
//        
//       
//        paneld.add(Box.createRigidArea(new Dimension(0,40)));
//        //paneld.add(Box.createHorizontalBox();
//        redCB = new JCheckBox("Red");
//        //redCB.setSelected(true); 
//        paneld.add(redCB);
       topaint();
      
         //JFrame frame = new JFrame(title);
//       GridBagLayout gbl = new GridBagLayout(); 
//     //  PaymentDisplay.se
//       JCheckBox check_OilChange = new JCheckBox("Oil Change");
//      
//        check_OilChange.setLocation(185,48);
//
//        //JPanel panel = new JPanel();
//        paneld.add(check_OilChange);
//         javax.swing.JCheckBox[] jCheckboxArray;
//            //
//            paneld.setLayout(new javax.swing.BoxLayout(paneld, javax.swing.BoxLayout.Y_AXIS));
//            int CheckBoxNumber = 5;
//            JPanel paneld = new JPanel(new GridLayout(0, 1));
//            Border border = BorderFactory.createTitledBorder("Pizza Toppings");
//            paneld.setBorder(border);
//            JCheckBox newcheck=new JCheckBox("Nagendra");
//                 paneld.add(newcheck );
//               jCheckboxArray = new javax.swing.JCheckBox[CheckBoxNumber];
//            for(int x = 0; x < CheckBoxNumber ; x++) {
//                jCheckboxArray[x] = new javax.swing.JCheckBox();
//                jCheckboxArray[x].setText("CheckBox " + x);
//                paneld.add(jCheckboxArray[x]);
               
   ////contentPane.add(button, BorderLayout.SOUTH);
               
    // }
     }
     public void countdatabase()
     {
         try
         {
          con6=DriverManager.getConnection(host);
          stmt6=con6.createStatement();
                  //   int srow=tbldata.getSelectedRow();
                  //   int scol=tbldata.getSelectedColumn();
                     
                     //java.sql.Timestamp sqlDate= new java.sql.Timestamp(new java.util.Date().getTime());
                     stmt6.executeQuery("SELECT * FROM CUSTOMERS"); 
                     res6=stmt6.getResultSet();
                     int cound=0;
                     while(res6.next())
                     {
                         cound=cound+1;
                         String counds=Integer.toString(cound);
                         custlbl.setText(counds);
                     }
         }
                     catch(SQLException err)
                     {
                         
                     }
     }
     public void getresult()
     {try{
          con5=DriverManager.getConnection(host);
          stmt5=con5.createStatement();
                  //   int srow=tbldata.getSelectedRow();
                  //   int scol=tbldata.getSelectedColumn();
                     
                     //java.sql.Timestamp sqlDate= new java.sql.Timestamp(new java.util.Date().getTime());
                     stmt5.executeQuery("SELECT * FROM CUSTOMERS"); 
                     res5=stmt5.getResultSet();
                     w=0;     
        while(res5.next())
                     {
                        
        String id=res5.getString("CARD_NUM");
        int ids=Integer.parseInt(id);
        ardd[w]=ids;
         w++;
    }
     sortlist1();
            // calculatemode(w);
             
           //  state=cb[w].isSelected();
         //  JOptionPane.showMessageDialog(PaymentDisplay.this,"dsadf");
           //  n=w;
     // n=w;
            // chItemStateChanged();
           
       
    }
    catch(SQLException err)
    {
        JOptionPane.showMessageDialog(PaymentDisplay.this,err);
    }
     }
    
public void showresult1(){
      //  setBounds(100,100,778,426);
    
         
                     
        getContentPane().setLayout(null);
       // JScrollPane scrollPane=new JScrollPane();
       // scrollPane.setBounds(0, 0, 70, 276);
       getContentPane().add(scrollPane);
        
        scrollPane.setViewportView(borderlaoutpanel);
        borderlaoutpanel.setLayout(new BorderLayout(0, 0));
        JPanel columnpanel = new JPanel();
        countdatabase();
        
//        =new JCheckBox[getcound1]; 
        borderlaoutpanel.add(columnpanel, BorderLayout.CENTER);
        columnpanel.setLayout(new GridLayout(0, getcound1, 25, 1));
       // columnpanel.setSize(3000,50);
        columnpanel.setBackground(Color.lightGray);
       // this.cb=new JCheckBox[getcound1];
       
      //  hbar.setBlockIncrement(1);
       // for( w=0;w<getcound1;w++)
                  
       // showresult1();
        for(int r=0;r<getcound1;r++)
        {
         cb[r]=new JCheckBox(""+ardd[r]+"");
          //JOptionPane.showMessageDialog(PaymentDisplay.this, cb[w]);
                cb[r].setVisible(true);
                 //panel1.add(cb[w]);
                columnpanel.add(cb[r]);
                //cb[w].setSelected(false);
             cb[r].setBackground(Color.lightGray) ;
             cb[r].setForeground(Color.BLACK);
             cb[r].setFont(new Font("Comic Sans MS",Font.BOLD,14));
             //n=w;
             //n=w;
             //JOptionPane.showMessageDialog(PaymentDisplay.this,cb[w].getText());
          cb[r].addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chItemStateChanged(evt);
            }
        });
        }
}
public void sortlist()
{
//   for(int c=0;c<getcound1;c++)
//   {
     
    ardd[w]=arrdd[w];
    //JOptionPane.showMessageDialog(PaymentDisplay.this, ardd[w]);
}
public void sortlist1(){
    int t;
        for(int r=1;r<=(getcound1);r++)
        {
            //JOptionPane.showMessageDialog(PaymentDisplay.this,getcound1-1);
            for(int q=0;q<(getcound1-r);q++)
            {
                if(ardd[q]>=ardd[q+1])
                {
                    t=ardd[q];
                    ardd[q]=ardd[q+1];
                    ardd[q+1]=t;
                }
                else
                    continue;
            }
            // System.out.println(ardd[r]);
        }
       // l++;
        for(int r=0;r<getcound1;r++)
        {
            System.out.println(ardd[r]);
        }
}
   //}

public void CusotomerName1()
    {
            try{
                
                     con3=DriverManager.getConnection(host);
                     stmt3=con3.createStatement();
                  //   int srow=tbldata.getSelectedRow();
                  //   int scol=tbldata.getSelectedColumn();
                     //java.sql.Timestamp sqlDate= new java.sql.Timestamp(new java.util.Date().getTime());
                     stmt3.executeQuery("SELECT CARD_NUM,CUST_NAME FROM CUSTOMERS where CARD_NUM="+val1); 
                     res3=stmt3.getResultSet();
                    // JOptionPane.showMessageDialog(PaymentDisplay.this,tblcu[w]);
                     tblcu[w].setModel(dtm);
                      tblcu[w].setAutoResizeMode(tblcu[w].AUTO_RESIZE_OFF);
                            dtm.addColumn("C.N0");
                            dtm.addColumn("C.NAME");
                             if(res3.next())
                                 {  
                                         String name2=res3.getString("CUST_NAME");
                                         String cardd=res3.getString("CARD_NUM");
                                         dtm.addRow(new Object[] {cardd,name2});
                                 }
                            
                              res3.close();
                     stmt3.close();
                    
                     con3.close();
                  
                }
             catch(SQLException err)
                 {
                    JOptionPane.showMessageDialog(PaymentDisplay.this,err);
                    }
            }  
public void showresult2(){
      //  setBounds(100,100,778,426);
      
                     
        //getContentPane().setLayout(null);
       // JScrollPane scrollPane=new JScrollPane();
       // scrollPane.setBounds(0, 0, 70, 276);
       getContentPane().add(scrollPane2);
        
        scrollPane2.setViewportView(borderlaoutpane2);
        //scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        borderlaoutpane2.setLayout(new BorderLayout(0, 0));
       
        countdatabase();
        
//        =new JCheckBox[getcound1]; 
          borderlaoutpane2.setSize(100,50);
        borderlaoutpane2.add(columnpanel2,BorderLayout.PAGE_START);
      // tabletest=Createcomptable();
      
       // Createcomptable();
        columnpanel2.setLayout(new GridLayout(0, 1, 25, 25));
        // CompModel.setRowCount(0);
       // dtm4.setRowCount(0);
       // tabletest=Createcomptable();
        columnpanel2.setBackground(Color.LIGHT_GRAY);
        
       // this.cb=new JCheckBox[getcound1];
       
      //  hbar.setBlockIncrement(1);
       // for( w=0;w<getcound1;w++)
     int i=0;
     for(i=0;i<getcound1;i++)
     {
         print[i]=0;
     }
     int m=0,l=0; int ccount=0;
     i=0;
     for(w=0;w<getcound1;w++)
       {
            
               //cb[w].getItemListeners();
          
         states[w]=cb[w].getModel().isSelected();
           
        
         if(states[w]==true)
         {
             
            print[i]=w;
            i++;
             String val=cb[w].getText();
             
             //   JOptionPane.showMessageDialog(PaymentDisplay.this,val);
           //  JOptionPane.showMessageDialog(PaymentDisplay.this,w);
             val1=Integer.parseInt(val);
             Vector<Vector> data=new Vector<Vector>();
             Vector<String> row=new Vector<String>();
             Vector<String> row0=new Vector<String>();
              Vector<String> row1=new Vector<String>();
               Vector<String> row2=new Vector<String>();
                  Vector<String> row3=new Vector<String>();
             Vector<String> cols=new Vector<String>();
             
             //cols.add("");
            // row.add("");
//            // data.add(row);
//              String days1=daysdiff.getText();
//          //JOptionPane.showMessageDialog(PaymentDisplay.this,days1);
//         int days2=Integer.parseInt(days1);
////         
//         //int k=0;
//            String date1=txttodate.getText();
//            SimpleDateFormat sdf3=new SimpleDateFormat(DATE_FORMAT_NOW1);
//            SimpleDateFormat sdf4=new SimpleDateFormat(DATE_FORMAT_NOW);
          //s  Date d1=null;
//             Double sum=0.0;
//              Double sum2=0.0;
//               Double sum3=0.0;
//           try
//           {
//             con2=DriverManager.getConnection(host);
//                     stmt2=con2.createStatement();
////                    int srow= tbldata.getSelectedRow();
//                    //int scol= tbldata.getSelectedColumn();
//                     //java.sql.Timestamp sqlDate= new java.sql.Timestamp(new java.util.Date().getTime());
//                    stmt2.executeQuery("SELECT * FROM DAILYDATA where CARD_NUM="+val1); 
//                     res2=stmt2.getResultSet();
//                          
//            // tblcu[w].setModel(dtm);
//            tblcu[w].setAutoResizeMode(tblcu[w].AUTO_RESIZE_OFF);
//            // int columncount=tblcu[w].getColumnCount();
//            // JOptionPane.showMessageDialog(PaymentDisplay.this,columncount);
//              d1=sdf3.parse(date1);
//           //  if(columncount<5)
//           //  {
//                    cols.add("C.N0");
//                    cols.add("C.NAME");
//                    cols.add("S.TIME");
//                    for(int j=0;j<days2;j++)
//                    {
//                         Calendar cal4=Calendar.getInstance();
//                         cal4.setTime(d1);
//                           cal4.add(Calendar.DATE,-j);
//                           cols.add(sdf3.format(cal4.getTime()));    
//                    }
//                    cols.add("S.TOTAL");
//                    cols.add("TOTAL");
//                    cols.add("SIGNATURE");
//             
//             
//                        // tblcu[w].getColumnModel().getColumn(columnd);
//                                
//                               
//             }
//                     tblcu[w].getColumnModel().getColumn(0).setPreferredWidth(50);
//                     tblcu[w].getColumnModel().getColumn(1).setPreferredWidth(50);
//                      tblcu[w].getColumnModel().getColumn(1).setPreferredWidth(170);
//                      for(int j=3;j<(days2+3);j++)
//                    {
//                     tblcu[w].getColumnModel().getColumn(j).setPreferredWidth(75);
//                    }
//                     tblcu[w].getColumnModel().getColumn(days2+3).setPreferredWidth(90);
//                     tblcu[w].getColumnModel().getColumn(days2+4).setPreferredWidth(90);                     
//                     tblcu[w].getColumnModel().getColumn(days2+5).setPreferredWidth(290);
////                                          for(int q=3;q<(days2+3);q++)
////                                          {
////                                             //String values= tblcu[w].getValueAt(0, m).toString();
////                                            // Double values1=Double.parseDouble(values);
////                                             tblcu[w].setValueAt("0", 0, m);
////                                             tblcu[w].setValueAt("0",1,m);
//////                                             if((tblcu[w].getValueAt(0, 0)).equals(null))
//////                                             {
//////                                              // JOptionPane.showMessageDialog(PaymentDisplay.this,"hi" );
//////                                                 //tblcu[w].setValueAt("0", 0, m);
////////                                                 tblcu[w].setValueAt("0", 1, m);
//////                                             }
////                                          }
//                                          
//                       while(res2.next())
//                                 {  
//                                         String id=res2.getString("CARD_NUM");
//                                         String date2=res2.getString("DATE");
//                                         Object money=res2.getString("MONEY");
//                                         String sess=res2.getString("SESSION");
//                                         //String name=res2.getString("CUST_NAME");
//                                         for(int j=3;j<(days2+3);j++)
//                                 {
//                                        // JOptionPane.showMessageDialog(PaymentDisplay.this,tblcu[w].getColumnName(j));
//                                         String date4=tblcu[w].getColumnName(j);
//                                         Date date=sdf4.parse(date4);
//                                        // JOptionPane.showMessageDialog(PaymentDisplay.this,date);
//                                         Date dated=sdf4.parse(date2);
//                                         //JOptionPane.showMessageDialog(PaymentDisplay.this,dated);
//                                         
//                                            if((dated.equals(date))&&(sess.equals("Evening")))
//                                         {
//                                              row.add(1, money);
//                                              tblcu[w].setValueAt(money, 1, j);
//                                             
//                                              //JOptionPane.showMessageDialog(PaymentDisplay.this,"Equal");
//                                         }
//                                          else if((dated.equals(date))&&(sess.equals("Morning")))
//                                         {
//                                             tblcu[w].setValueAt(money, 0, j);
//                                             
//                                              //JOptionPane.showMessageDialog(PaymentDisplay.this,"Unequal");
//                                         } 
//                                          
//                                          
//                                         //if((id.equals(txtcardn0.getText()))&&()
//                    
//                                        
//                                 }
//                    //dtm.addRow(new Object[] {1,2,3,4,5});
//                   // tblcu[w].getC
//                  //scroll1.getViewport().add(tblcu[w]);                    
//                 //  tblcu[w].setR
//                        for(int l=3;l<days2+3;l++)
//         {
//             Object result=tblcu[w].getValueAt(0, l);
//             Object resultpm=tblcu[w].getValueAt(1,l);
//             String result1=result.toString();
//             String resultpm1=resultpm.toString();
//            // lblname.setText(result1);
//            Double result2=Double.parseDouble(result1);
//            Double resultpm2=Double.parseDouble(resultpm1);
//          //  int valued=Integer.parseInt(result1);
//             sum=sum+result2;
//             String result_val=df.format(sum);
//             
//             sum2=sum2+resultpm2;
//             String result_val1=df.format(sum2);
//             sum3=sum+sum2;
//             String result_fval=df.format(sum3);
//             //String sumd=
//             
//             tblcu[w].setValueAt(result_val, 0, (days2+3));
//             tblcu[w].setValueAt(result_val1, 1, (days2+3));
//             tblcu[w].setValueAt(result_fval, 0, (days2+4));
//             
//           //  JOptionPane.showMessageDialog(PaymentDisplay.this,sum);
//         }
//                       //tblcu[w].getValueAt(, columncount)
//                        tblcu[w].setRowHeight(70);
//                       // tblcu[w].getC
//                       // tblemp.
//                        tblcu[w].setSelectionForeground(Color.white);                    
//                         tblcu[w].setSelectionBackground(Color.black);
//                         tblcu[w].setFont(new Font("Comic Sans MS",Font.BOLD,14));                        
//                        // tblsearch.setCol
//                         tblcu[w].setForeground(Color.BLACK);
//                         CusotomerName1(); 
//                         tblcu[w].setAutoCreateColumnsFromModel(false);
//                          //tblcu[w].removeAll(); 
//                     //   tblemp.setFont("Times New Roman");
//                       
//             }          
//                       tblcu[w].removeAll(); 
//                       tblcu[w].setAutoCreateColumnsFromModel(false);
//                        
//                     }
//            catch(Exception e)
//    {
//
//    }
             String days1=daysdiff.getText();
                     int days2=Integer.parseInt(days1);
              try
                    {
                     //columnpanel2.repaint();
                     con3=DriverManager.getConnection(host);
                     stmt3=con3.createStatement();
                    // int srow=tbldata.getSelectedRow();
                   //  int scol=tbldata.getSelectedColumn();
                   //  java.sql.Timestamp sqlDate= new java.sql.Timestamp(new java.util.Date().getTime());
                     stmt3.executeQuery("SELECT CARD_NUM,CUST_NAME FROM CUSTOMERS where CARD_NUM="+val1); 
                     res3=stmt3.getResultSet();
                    // JOptionPane.showMessageDialog(PaymentDisplay.this,tblcu[w]);
                     
                     // tblcu[w].setAutoResizeMode(tblcu[w].AUTO_RESIZE_OFF);
                           
                             //cols.add("C.N0");
                     
                     Date d1=null;
                    
                  
                 //   cols.setSize(100);
                     cols.add("S.TIME");
                    cols.add("C.NAME");
                    
                    cols.add("C.N0");
                    SimpleDateFormat sdf3=new SimpleDateFormat(DATE_FORMAT_NOW1);
                     
                    String date1=txtfromdate.getText();
                    try
                    {
                    d1=sdf3.parse(date1);
                    }
                    catch(Exception e)
                    {
                        
                    }
                    for(int j=0;j<days2;j++)
                    {
                         Calendar cal4=Calendar.getInstance();
                         cal4.setTime(d1);
                           cal4.add(Calendar.DATE,+j);
                           cols.add(sdf3.format(cal4.getTime()));    
                    }
                    cols.add("S.TOTAL");
                    cols.add("TOTAL");
                    cols.add("SIGNATURE");
                    row.add("S.TIME");
                    row.add("C.NAME");
                    row.add("C.N0");
                    
                 //   cols.setSize(100);
                    
                    
                    
                    for(int j=0;j<days2;j++)
                    {
                         Calendar cal4=Calendar.getInstance();
                         cal4.setTime(d1);
                           cal4.add(Calendar.DATE,+j);
                           row.add(sdf3.format(cal4.getTime()));    
                    }
                    row.add("S.TOTAL");
                    row.add("TOTAL");
                    row.add("SIGNATURE");
                    data.add(row);
                    row0.add("---------");
                 //   cols.setSize(100);
                    row0.add("---------------------------------");
                    row0.add("--------------");
                    
                    for(int j=0;j<days2;j++)
                    {
                         Calendar cal4=Calendar.getInstance();
                         cal4.setTime(d1);
                           cal4.add(Calendar.DATE,+j);
                           row0.add("--------------");    
                    }
                    row0.add("-----------------");
                    row0.add("-----------------");
                    row0.add("---------------------------------------------------------");
                     
                   data.add(row0);                  
                      //cols.setElementAt(val, 5);
                        //     dtm.setRowCount(0);
                             while(res3.next())
                                 {  
                                    // columnpanel2.repaint();
                                         String name2=res3.getString("CUST_NAME");
                                         String cardd=res3.getString("CARD_NUM");
                                         row1.add("Morning");
                                         row1.add(name2);
                                         row1.add(cardd);
                                         data.add(row1);
                                         row2.add("Evening");
                                         row2.add("");
                                         
                                         row2.add("");
                                         
                                         data.add(row2);
                                         //data.setSize(5);
                                         //data.setSize(30);
                                      //  tblcu[w].setValueAt("Nagendra", 0, l);
                                 }
                             //tblcu[w].setAutoCreateColumnsFromModel(true);
                                    
                  res3.close();
                  stmt3.close();
                  con3.close();
                     
                     
                }
                 catch(SQLException err)
                 {
                    JOptionPane.showMessageDialog(PaymentDisplay.this,err);
                    }
             
             tblcu[w]=new JTable(data,cols);
             JTableHeader head=tblcu[w].getTableHeader();
             //columnpanel2.add(head);
            // columnpanel2.add(head);
             columnpanel2.add(tblcu[w]);
             Double sum=0.0;
              Double sum2=0.0;
               Double sum3=0.0;
             try{
                 
             
             con2=DriverManager.getConnection(host);
                     stmt2=con2.createStatement();
                     SimpleDateFormat sdf4=new SimpleDateFormat(DATE_FORMAT_NOW1);
//                    int srow= tbldata.getSelectedRow();
                    //int scol= tbldata.getSelectedColumn();
                     //java.sql.Timestamp sqlDate= new java.sql.Timestamp(new java.util.Date().getTime());
                    stmt2.executeQuery("SELECT * FROM DAILYDATA where CARD_NUM="+val1); 
                     res2=stmt2.getResultSet();
                   //  JOptionPane.showMessageDialog(PaymentDisplay.this,"hi" );
             for(int p=3;p<(days2+3);p++)
                                          {
                                             //String values= tblcust.getValueAt(0, m).toString();
                                            // Double values1=Double.parseDouble(values);
                                             tblcu[w].setValueAt("0", 2, p);
                                             tblcu[w].setValueAt("0",3,p);
//                                             if((tblcust.getValueAt(0, 0)).equals(null))
//                                             {
//                                              // JOptionPane.showMessageDialog(PaymentDisplay.this,"hi" );
//                                                 //tblcust.setValueAt("0", 0, m);
////                                                 tblcust.setValueAt("0", 1, m);
//                                             }
                                          }
                                          
                       while(res2.next())
                                 {  
                                         String id=res2.getString("CARD_NUM");
                                         String date2=res2.getString("DATE");
                                         Object money=res2.getString("MONEY");
                                         String sess=res2.getString("SESSION");
                                         //String name=res2.getString("CUST_NAME");
                                         for(int j=3;j<(days2+3);j++)
                    {
                                        // JOptionPane.showMessageDialog(PaymentDisplay.this,tblcust.getColumnName(j));
                                         String date4=tblcu[w].getColumnName(j);
                                         try
                                         {
                                         Date date=sdf4.parse(date4);
                                         
                                        // JOptionPane.showMessageDialog(PaymentDisplay.this,date);
                                         Date dated=sdf4.parse(date2);
                                        
                                         //JOptionPane.showMessageDialog(PaymentDisplay.this,dated);
                                         
                                            if((dated.equals(date))&&(sess.equals("Evening")))
                                         {
                                            
                                              tblcu[w].setValueAt(money, 3, j);
                                             
                                              //JOptionPane.showMessageDialog(PaymentDisplay.this,"Equal");
                                         }
                                          else if((dated.equals(date))&&(sess.equals("Morning")))
                                         {
                                             tblcu[w].setValueAt(money, 2, j);
                                             
                                              //JOptionPane.showMessageDialog(PaymentDisplay.this,"Unequal");
                                         } 
                                          
                                          
                                         //if((id.equals(txtcardn0.getText()))&&()
                     }
                                         catch(Exception e)
                                         {
                                             
                                         }
                    }
                                 }
                                 
                    //dtm.addRow(new Object[] {1,2,3,4,5});
                   // tblcust.getC
                  //scroll1.getViewport().add(tblcust);                    
                 //  tblcust.setR
                        for(int q=3;q<days2+3;q++)
         {
             Object result=tblcu[w].getValueAt(2, q);
             Object resultpm=tblcu[w].getValueAt(3,q);
            // JOptionPane.showMessageDialog(PaymentDisplay.this,result);
             String result1=result.toString();
             String resultpm1=resultpm.toString();
             
            // lblname.setText(result1);
            Double result2=Double.parseDouble(result1);
            Double resultpm2=Double.parseDouble(resultpm1);
          //  int valued=Integer.parseInt(result1);
             sum=sum+result2;
             String result_val=df.format(sum);
             
             sum2=sum2+resultpm2;
             String result_val1=df.format(sum2);
             sum3=sum+sum2;
             String result_fval=df.format(sum3);
             //String sumd=
             
             tblcu[w].setValueAt(result_val, 2, (days2+3));
             tblcu[w].setValueAt(result_val1, 3, (days2+3));
             tblcu[w].setValueAt(result_fval, 2, (days2+4));
             
           //  JOptionPane.showMessageDialog(PaymentDisplay.this,sum);
         }
                    
                                 
             }
             catch(SQLException err)
             {
                 
             }
             DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
rightRenderer.setHorizontalAlignment( JLabel.CENTER );
            // tblcu[w].setValueAt("Nagendra", 1, 3);
               tblcu[w].getColumnModel().getColumn(0).setPreferredWidth(70);
               tblcu[w].getColumnModel().getColumn(0).setCellRenderer( rightRenderer );
                     tblcu[w].getColumnModel().getColumn(1).setPreferredWidth(50);
                     tblcu[w].getColumnModel().getColumn(1).setCellRenderer( rightRenderer );
                      tblcu[w].getColumnModel().getColumn(1).setPreferredWidth(170);
                      tblcu[w].getColumnModel().getColumn(2).setCellRenderer( rightRenderer );
                      for(int j=3;j<(days2+3);j++)
                    {
                     tblcu[w].getColumnModel().getColumn(j).setPreferredWidth(75);
                     tblcu[w].getColumnModel().getColumn(j).setCellRenderer( rightRenderer );
                    }
                     tblcu[w].getColumnModel().getColumn(days2+3).setPreferredWidth(90);
                     tblcu[w].getColumnModel().getColumn(days2+3).setCellRenderer( rightRenderer );
                     tblcu[w].getColumnModel().getColumn(days2+4).setPreferredWidth(90);
                     tblcu[w].getColumnModel().getColumn(days2+4).setCellRenderer( rightRenderer );
                     tblcu[w].getColumnModel().getColumn(days2+5).setPreferredWidth(290);
                     tblcu[w].getColumnModel().getColumn(days2+5).setCellRenderer( rightRenderer );
                 // tblcu[w].set
                     // tblcu[w].setDefaultRenderer(MyRenderer);
                     String nag1=tblcu[w].getValueAt(2, days2+4).toString();
             Double nada=Double.parseDouble(nag1);
            
            // resetarray();
                 vald[w]=nada;
                  tblcu[w].setBackground(Color.WHITE) ;
                 // head.getColumnModel().setColumnMargin();
                  tblcu[w].setShowHorizontalLines(false);
                  tblcu[w].setRowHeight(45);
             tblcu[w].setForeground(Color.BLACK);
             tblcu[w].setFont(new Font("Times New Roman",Font.BOLD,14));
             tblcu[w].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,2));
           // Createcomptable();
           //  tabletest.setValueAt(tblcu[xw], 0, 0);
//              tabletest.setModel(new MyModel());//invoking our custom model
             
            
             
  //           tabletest.setDefaultRenderer(JLabel.class,  new Renderer());
             ccount=ccount+1;
          
              //tblcu[w].setLayout(null);
            
             //  tblcu[w].setValueAt(val, 0, l);
           //  cleartable();
             //tblcu[w]=new JTable(dtm3);
            // 
              
            
              
              //tblcu[w].getModel().
              // tblcu[w].setModel(dtm);
              
            
           
                
              
              // dtm3.setRowCount(0);
                
                 
            //   xxx();
               
                 // DisplayTableData();
                // try
                 //   {
                     
                     //con3=DriverManager.getConnection(host);
                   //  stmt3=con3.createStatement();
                  //   int srow=tbldata.getSelectedRow();
                  //   int scol=tbldata.getSelectedColumn();
                     //java.sql.Timestamp sqlDate= new java.sql.Timestamp(new java.util.Date().getTime());
                    // stmt3.executeQuery("SELECT CARD_NUM,CUST_NAME FROM CUSTOMERS where CARD_NUM="+val1); 
                     //res3=stmt3.getResultSet();
                    // JOptionPane.showMessageDialog(PaymentDisplay.this,tblcu[w]);
                     
                    //  tblcu[w].setAutoResizeMode(tblcu[w].AUTO_RESIZE_OFF);
                           
                          //  dtm.addColumn("C.NAME");
                             //dtm.setRowCount(0);
                            // while(res3.next())
                             //    {  
                                        // String name2=res3.getString("CUST_NAME");
                                       //  String cardd=res3.getString("CARD_NUM");
                                         
                             //    }
                           //  tblcu[w].setAutoCreateColumnsFromModel(true);
                           
                  
               // }
                 //catch(SQLException err)
                // {
                //    JOptionPane.showMessageDialog(PaymentDisplay.this,err);
                  //  }
             
         }
        
       }
             //  for(int c=0;c<2;c++)
 // CompModel.addRow();
   //  CompCellPanel();
     //JOptionPane.showMessageDialog(PaymentDisplay.this,"Hjji");
    addRow(); 
   totaltables=i;      
       
}
 public void resetarray()
     {
        for(int j=0;j<getcound1;j++)
        {
            vald[j]=0.0;
        
        } 
     }
     public void calculatesum()
     {
         sum=0.0;
        for(int j=0;j<getcound1;j++)
        {
            sum=sum+vald[j];
        
        }
           // String sum1=sum.toString();
            String sum3=df.format(sum);
            //JOptionPane.showMessageDialog(PaymentDisplay1.this, sum3);
            lbltotaltxt1.setText(sum3);
        
     }

public class PrintUtilities implements Printable {
private Component columpanel2;

public  void printComponent(Component c) {
        new PrintUtilities(c).print4();
}

public PrintUtilities(Component JPanel) {
this.columpanel2 = columnpanel2;
}
public void sendToPrinter(){
        PrinterJob printJob = PrinterJob.getPrinterJob();
        printJob.setPrintable(this);
        if(printJob.printDialog())
            try { printJob.print(); } catch (Exception PrintException) { }
    }
public void print4() {
PrinterJob printJob = PrinterJob.getPrinterJob();
printJob.setPrintable(this);
if (printJob.printDialog())
try {
printJob.print();
} catch(PrinterException pe) {
System.out.println("Error printing: " + pe);
}
}
 public int print(Graphics g, PageFormat pageFormat, int pageIndex) {
     int response = NO_SUCH_PAGE;
Graphics2D g2 = (Graphics2D) g;
// for faster printing, turn off double buffering
disableDoubleBuffering(columnpanel2);
Dimension d = columnpanel2.getSize(); //get size of document

double panelWidth = d.width; //width in pixels
double panelHeight = d.height; //height in pixels
double pageHeight = pageFormat.getImageableHeight(); //height of printer page
double pageWidth = pageFormat.getImageableWidth(); //width of printer page
double scale = 1.00; //Scale of output
//total number of pages across.
int totalPagesX = (int) Math.ceil(scale * panelWidth/pageWidth);
//total number of pages down.
int totalPagesY = (int) Math.ceil(scale * panelHeight/pageHeight);
int totalNumPages = totalPagesX + totalPagesY; //total number of pages
int xOffset = pageIndex % totalPagesX; //which page across to print
int yOffset = pageIndex / totalPagesX; //which page down to print
// make sure not print empty pages
if (pageIndex >= totalNumPages) {
response = NO_SUCH_PAGE;
}
else {
// shift Graphic to line up with beginning of print-imageable region
g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
// shift Graphic to line up with beginning of next page to print
g2.translate(-xOffset * pageWidth, -yOffset * pageHeight);
// scale the page so the width fits...
g2.scale(scale, scale);
columnpanel2.paint(g2); //repaint the page for printing
enableDoubleBuffering(columnpanel2);
response = Printable.PAGE_EXISTS;
}
return response;
}

public  void disableDoubleBuffering(Component c) {
RepaintManager currentManager = RepaintManager.currentManager(c);
currentManager.setDoubleBufferingEnabled(false);
}

public  void enableDoubleBuffering(Component c) {
RepaintManager currentManager = RepaintManager.currentManager(c);
currentManager.setDoubleBufferingEnabled(true);
}
} 
public void printTables2()
{
       
  
    try
    {
        //Graphics2D graphics;
        Graphics2D g2 ;
        PrinterJob printer2=PrinterJob.getPrinterJob();
        PageFormat pf1=printer2.defaultPage();
        pf1.setOrientation(PageFormat.PORTRAIT);
        Paper paper=new Paper();
         double margin = 36; // half inch
    paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight() - margin * 2);
    pf1.setPaper(paper);

   Book printJob = new Book();
    //Document printJob=new Document();
    
    MyPrintable prt = new MyPrintable( tblcu[print[0]].getPrintable(JTable.PrintMode.FIT_WIDTH, null, null));
    //printJob.append(new MyPrintable(prt2);
     MyPrintable prt2 = new MyPrintable( tblcu[print[1]].getPrintable(JTable.PrintMode.FIT_WIDTH, null, null));
    // Printable p2=new ComponentPrinter().
      //  prt.print(g2, pf1, WIDTH);
//    int e=0;
//    while (prt.print(g,pf1,e) == PAGE_EXISTS){
//if (prt.getSpaceLeft() < 100){
//// create a new page or a new graphics
//// reset page index to 0
//}
//e++;
//}
       printer2.setPageable(printJob);
    // prt.print(g2, pf1, 1);
    // printer2.setPageable();
     printer2.setPrintable(prt);
    
   //(prt2, pf1);
  //  System.out.println(printJob.getNumberOfPages());

    if (printer2.printDialog())
        printer2.print();
    }
    catch(PrinterException e)
    {
        e.printStackTrace();
    }
}

public void printTables()
{
    
  try {       
    PrinterJob printer = PrinterJob.getPrinterJob();

   // Set 1/2 " margins and orientation
    PageFormat pf = printer.defaultPage();
    pf.setOrientation(PageFormat.LANDSCAPE);
    Paper paper = new Paper();
    double margin = 18; // half inch
    paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight() - margin * 2);
    pf.setPaper(paper);

    Book printJob = new Book();

    // Note for next line: getAllTables() returns an ArrayList of JTables
    int z=0;
//    for (z=0;z<totaltables;z++ )  
//    {
        Printable p = tabletest.getPrintable(JTable.PrintMode.FIT_WIDTH, null, null);
       // Printable p1 = tblcu[print[z+1]].getPrintable(JTable.PrintMode.FIT_WIDTH, null, null);
       
        for (int y=0; y < 100000; y++)
            printJob.append(new PageWrapper(p,y), pf);
  //  }
  //  PrinterJob printJob = PrinterJob.getPrinterJob();
///printJob.setP(this);


    printer.setPageable(printJob);

    System.out.println(printJob.getNumberOfPages());

    if (printer.printDialog())
        printer.print();
    } catch (PrinterException e) {
        e.printStackTrace();
}
    
}
public void printTables3()
{
    
  try {       
    PrinterJob printer = PrinterJob.getPrinterJob();

   // Set 1/2 " margins and orientation
    PageFormat pf = printer.defaultPage();
    pf.setOrientation(PageFormat.PORTRAIT);
    Paper paper = new Paper();
    double margin = 18; // half inch
    paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight() - margin * 2);
    pf.setPaper(paper);

    Book printJob = new Book();

    // Note for next line: getAllTables() returns an ArrayList of JTables
    int z=0;
//    for (z=0;z<totaltables;z++ )  
//    {
        Printable p = tabletest.getPrintable(JTable.PrintMode.FIT_WIDTH, null, null);
       // Printable p1 = tblcu[print[z+1]].getPrintable(JTable.PrintMode.FIT_WIDTH, null, null);
       
        for (int y=0; y < 100000; y++)
            printJob.append(new PageWrapper(p,y), pf);
  //  }
  //  PrinterJob printJob = PrinterJob.getPrinterJob();
///printJob.setP(this);


    printer.setPageable(printJob);

    System.out.println(printJob.getNumberOfPages());

    if (printer.printDialog())
        printer.print();
    } catch (PrinterException e) {
        e.printStackTrace();
}
    
}
public class MyPrintable implements Printable {

Printable delegate;
double spaceLeft = 0;
double minimumRequired = 72;
//static int debugindex = 0;

public MyPrintable(Printable p) {
this.delegate = p;
}

public MyPrintable(Printable p, double minimumHeight) {
this.delegate = p;
this.minimumRequired = minimumHeight;
}

public void setMinimumRequired(double height) {
minimumRequired = height;
}

public double getMinimumRequired() {
return minimumRequired;
}


public double getSpaceLeft() {
return spaceLeft;
}

private int detectLastLine(BufferedImage img) {
int lastIndex = 0;
int[] data = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
for (int i = data.length - 1; i > 0; i--) {
if (data[i] != 0) {
lastIndex = i;
break;
}
}
return (lastIndex / img.getWidth());
}

@Override
public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
BufferedImage img = new BufferedImage((int) pageFormat.getWidth(), (int) pageFormat.getHeight(), BufferedImage.TRANSLUCENT);
Graphics2D g = img.createGraphics();
int retValue = delegate.print(g, pageFormat, pageIndex);
if (retValue == PAGE_EXISTS) {
//Find out bound of printing...
//System.out.println("Last Line drawn is : " + detectLastLine(img));
// try {
// ImageIO.write(img, "jpg", new File("img" + debugindex++ + ".jpg"));
// } catch (IOException ex) {
// }
spaceLeft = (pageFormat.getImageableY() + pageFormat.getImageableHeight()) - detectLastLine(img);
retValue = delegate.print(graphics, pageFormat, pageIndex);
//Updating paper after the hardprint
Paper paper = pageFormat.getPaper();
paper.setImageableArea(paper.getImageableX(), paper.getImageableY() + paper.getImageableHeight() - spaceLeft, paper.getImageableWidth(), spaceLeft);
pageFormat.setPaper(paper);
}
return retValue;
}
}
//
//public int getNumberOfPages(Printable delegate, PageFormat pageFormat) throws PrinterException 
//    {BufferedImage img = new BufferedImage((int) pageFormat.getWidth(), (int) pageFormat.getHeight(), BufferedImage.TRANSLUCENT);
//Graphics2D g = img.createGraphics();
//        //Graphics g = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB).createGraphics();
//        int numPages = 0;
//        while (true) {
//            int result = delegate.print(g, pageFormat, numPages);
//            if (result == Printable.PAGE_EXISTS) {
//                spaceLeft = (pageFormat.getImageableY() + pageFormat.getImageableHeight()) - detectLastLine(img);
//                result = delegate.print(g, pageFormat, numPages);
//                Paper paper = pageFormat.getPaper();
//paper.setImageableArea(paper.getImageableX(), paper.getImageableY() + paper.getImageableHeight() - spaceLeft, paper.getImageableWidth(), spaceLeft);
//pageFormat.setPaper(paper);
//}
//                ++numPages;
//                return result;
//            }
//        
    //
//}
        
//        
//         for(int c=0;c<ccount;c++)
//         {
//             tblcus[c]=new JTable(data,cols);
//             tblcus[c]=tblcu[w];
//             
//                 final MessageFormat header = new MessageFormat("Card N0:   Total:");
//        try {
//            
//                 tblcus[c].getPrintable(JTable.PrintMode.FIT_WIDTH, header,null);
//                // tblcus[1].print(JTable.PrintMode.FIT_WIDTH, header,null);
//                // tblcus[2].print(JTable.PrintMode.FIT_WIDTH, header,null);
//            
//            }
//         
//        catch (java.awt.print.PrinterException e) {
//                    System.err.format("Cannot print %s%n", e.getMessage());
//        }
//        //JOptionPane.showMessageDialog(PaymentDisplay.this,c);
//         
//////             checkresult32();
////         
//         }
   
            
//             Vector<Vector> data=new Vector<Vector>();
//             Vector<String> row=new Vector<String>();
//             row.add("Nagendra");
//             row.add("Analyst");
//             data.add(row);
//             row=new Vector<String>();
//             row.add("nagendrakumar");
//             row.add("Programmer");
//             data.add(row);
//             Vector<String> cols=new Vector<String>();
//             cols.add("Emplooyeee");
//             cols.add("Position");
             //data.add(cols);
             //System.out.println(cb[w].getText());
          //  JOptionPane.showMessageDialog(PaymentDisplay.this,w);
             
            // System.out.println(val1);
//             table.setLayout(null);
//             JTableHeader head=table.getTableHeader();
//           //  head.setLayout(new BorderLayout(this,BorderLayout.PAGE_START));
//             table.setModel(dtm3);
//             dtm3.addColumn("Nagendra");
//             dtm3.addRow(new Object[] {cb[w].getText()});
            
            
             // String id=res5.getString("CARD_NUM");
        //int ids=Integer.parseInt(id);
            //  tblcu[w].setModel(dtm3);
              
                //cb[w].setSelected(false);
        
             
            
             //table.setRowHeight(30);
//            // borderlaoutpane2.add(columnpanel2,BorderLayout.PAGE_START);
//            // head.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
//            // head.setColumnModel();
//            getContentPane().setLaout(new BorderLayout());
         // columnpanel2.add(head,BorderLayout.AFTER_LINE_ENDS);
//          
             
            //columnpanel2.add(tblcu[w]);
            //DisplayTableData();
            
            
            
            
//             JButton button=new JButton(cb[w].getText());
//            // table.setModel(dtm);
//              button.setSize(40,50);
             // table.setSize(100, 100);
             //dtm.addColumn("Nagendra");
            // columnpanel2.add(table);
            // button.setBounds(m, l, 30, 10);
            
           //  m=m+100;
        //int x=w;
         //JOptionPane.showMessageDialog(PaymentDisplay.this,cb[w].getText()); 
         
             
         
        //JOptionPane.showMessageDialog(PaymentDisplay.this,val1);
       

             public class MyRenderer extends DefaultTableCellRenderer  
{ 
    public Component getTableCellRendererComponent(JTable table, Object value, boolean   isSelected, boolean hasFocus, int row, int column) 
{ 
    Component c = super.getTableCellRendererComponent(tblcu[w], value, isSelected, hasFocus, row, column); 

    if(row == 2 && column == 2)
        c.setBackground(new java.awt.Color(0, 0, 255)); 

    return c; 
} 

}
    
   public void printpdf2()
   {
      
   }
public void createtabled()
{
   // getContentPane().setLayout(null);
    // JScrollPane scrollpane2=new JScrollPane();
    getContentPane().setLayout(null);
 
       // columnpanel.setLayout(new GridLayout(0, getcound1, 25, 1));
       
      scrollpane2.setBounds(10, 200, 1000, 400);
       getContentPane().add(scrollpane2);
//      
      // scrollpane2.setHorizontalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
       scrollpane2.setViewportView(borderlayoutpanel1);
        borderlayoutpanel1.setLayout(new BorderLayout(0, 0));
        JPanel columnpanel1 = new JPanel();
//       // countdatabase();
//        
////        =new JCheckBox[getcound1]; 
        borderlayoutpanel1.add(columnpanel1, BorderLayout.CENTER);
        columnpanel1.setLayout(new GridLayout(0,10,3,1));
      
       // columnpanel1.setSize(30,50);
        columnpanel1.setBackground(Color.RED);
    System.out.println("hai");
    
        JButton button=new JButton();
        button.setBounds(0,0,50,100);
        columnpanel1.add(button);
        //m=m+60;
       // borderlayoutpanel1.add(button);
       // l=l+60;
    
    //columnpanel1.repaint();
}
public void calculatemode(int x)
    
{
    n=x;
    cb[x].addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chItemStateChanged(evt);
                //state=cb[x].getModel().isSelected();
      // JOptionPane.showMessageDialog(PaymentDisplay.this,"False");  
            }
        });
}
public void checkresult32()
{
    for(int c=0;c<2;c++)
    {
    tblcus[c]=tblcu[w];
    JOptionPane.showMessageDialog(PaymentDisplay.this,"HI");
            }
//      final MessageFormat header = new MessageFormat("");
//        try {
//           int n=tblcu[w].getColumnCount();
//    JOptionPane.showMessageDialog(PaymentDisplay.this, tblcu[w].getColumnCount());
//    tblcu[w].print(JTable.PrintMode.FIT_WIDTH, header,null);
//    
//        } catch (java.awt.print.PrinterException e) {
//    System.err.format("Cannot print %s%n", e.getMessage());
    
//}   
    
 
 
}
//private void chItemStateChanged2(java.awt.event.ItemEvent evt) {  
////   
////     for(w=0;w<getcound1;w++)
////     {
////         cb[w].getAccessibleContext();
//                //state=cb[x].getModel().isSelected();
//      // JOptionPane.showMessageDialog(PaymentDisplay.this,"False");  
//          //  }
//     //   });
//    // }
//     
//     columnpanel2.removeAll();
//     checkresult32();
//     columnpanel2.repaint();
//}
 private void chItemStateChanged(java.awt.event.ItemEvent evt) {  
//   
//     for(w=0;w<getcound1;w++)
//     {
//         cb[w].getAccessibleContext();
                //state=cb[x].getModel().isSelected();
      // JOptionPane.showMessageDialog(PaymentDisplay.this,"False");  
          //  }
     //   });
    // }
     
     
     columnpanel2.removeAll();
    
     for(w=0;w<getcound1;w++)
       {
            
               //cb[w].getItemListeners();
           
          if(cb[w].getModel().isSelected()==true)
          { 
              int checkck=xwstates[w];
              xwstates[w]=1;
              if(checkck!=xwstates[w])
              {
                 // addRow();
                  
                  xw=w;
              }
          }
       }
    
     //System.out.println("xw="+xw);
     showresult2();
    
     columnpanel2.repaint();
     
         calculatesum();  
//          if(cb[w].getModel().isSelected()==true)
//          {
//              
//          }
           
//          else
//          {
//           
//       }
       
       // Object nag=cb[2].getAction();
          
     
        // TODO add your handling code here:
       // int i=0;
//        for(int i=0;i<getcound1;i++)
//        {
//            states[i]=cb[i].getModel().isSelected();
//        }
     //for(int w=0;w<getcound1;w++)
    // {
     
        //createTables();
   // } 
 }
       
     public void validdisplay()
     {
         
         
//         int coldd=tbldata.getColumnCount();
        // for(int s=0;s<coldd;s++)
         {
    //        tbldata.getColumnModel().getColumn(s).setCellRenderer(new CheckBoxRenderer());  
    //        tbldata.getColumnModel().getColumn(s).setCellEditor(new CheckBoxEditor(new JCheckBox()));
         }
//         tbldata.repaint();
  }
    
     public void displaydatad()
     {
       //  tbldata.setAutoCreateColumnsFromModel( false );  
   //      tbldata.setAutoResizeMode(tblcust.AUTO_RESIZE_OFF);
       //   listdata.setModel(list);
       try
           {
             con4=DriverManager.getConnection(host);
             stmt4=con4.createStatement();
                     //java.sql.Timestamp sqlDate= new java.sql.Timestamp(new java.util.Date().getTime());
             stmt4.executeQuery("SELECT * FROM CUSTOMERS"); 
             res4=stmt4.getResultSet();
//             tbldata.setModel(dtm1);
             dtm1.addRow(new Object[] {""});
             int i=0;
             while(res4.next())
             {
//                 Object id=res4.getString("CARD_NUM");
//                 String id1=id.toString();
//                        String row=""+ id1 +"";
//                       list.addElement(row);
//                           
//                       listdata.setModel(list);
//                       listdata.setForeground(Color.MAGENTA);
                
                 Object id=res4.getString("CARD_NUM");
                 dtm1.addColumn(""); 
               // JCheckBox renderercomponent=new JCheckBox();
         
       
        
      // tbldata.setValueAt(id, 0, i);    
      
            
                // Object ada=tbldata.getColumn(id);
               // Object id1=res4.getString("CARD_NUM");
                
               
           // .//    tbldata.setComponentPopupMenu(); 
                  i=i+1;
                // dtm1.addRow(new Object[] {1,2,3});
                // tbldata.getColumnModel().getColumn(0).setPreferredWidth(300);
        
                // validdisplay();
             }
//             int columndd=tbldata.getColumnCount();
         //    for(int c=0;c<columndd;c++ )
             {
        //     tbldata.getColumnModel().getColumn(c).setPreferredWidth(50);  
             }
            //             tbldata.setRowHeight(43);
                       // tblcust.getC
                       // tblemp.
                       // tbldata.setSelectionForeground(Color.white);                    
                       //  tbldata.setSelectionBackground(Color.black);
//                         tbldata.setFont(new Font("Comic Sans MS",Font.BOLD,14));                        
                        // tblsearch.setCol
//                         tbldata.setForeground(Color.BLACK);
             //            int srow= tbldata.getSelectedRow();
             //      int scol= tbldata.getSelectedColumn();
                   
             
    
//                           //dtm.insertRow(dtm.getRowCount(),new Object[]{id,name});
//                           String row="Nagendra ";
//                           list.addElement(row);
//                           //list.
//                           listdata.setModel(list);
//                           listdata.setForeground(Color.MAGENTA);
                           //listdata.setFont(new Font("Comic Sans MS",Font.BOLD,5));
                         //  listdata.setFixedCellHeight(44);
                        //   list.setSize(6);
                           //searchlist.set
         //           tbldata.setAutoCreateColumnsFromModel( false );     
//            
           }
       catch(SQLException err)
       {
           
       }
           
     }
class CheckBoxRenderer  implements TableCellRenderer {  
  
        private static final long serialVersionUID = 1L;  
        protected Border columnBorder;  
        private final Color SelectedChboxTableBGColor1 = new Color(255, 255, 240);  
          
        //used in the first column of the PCF Selection Window Table to display the border and its color.  
        public CheckBoxRenderer() {  
            //columnBorder = new LineBorder(PcfConstants.bordercolor);  
            columnBorder = new LineBorder(SelectedChboxTableBGColor1);  
        }  
  
        public Component getTableCellRendererComponent(JTable tbldata, Object value,  
                boolean isSelected, boolean hasFocus, int row, int column) {  
  
             JCheckBox rendererComponent = new JCheckBox("1");  
            if (value instanceof JCheckBox) {          
                rendererComponent = (JCheckBox) value;  
            }  
            //chkBox.setBackground(table.getBackground());  
//          chkBox.setBackground(Color.DARK_GRAY);  
//          chkBox.setBorder(columnBorder);  
            if (value == null)  
                return null;  
              
              
               
            return rendererComponent;  
  
              
        }  
    }  
      
    class CheckBoxEditor extends DefaultCellEditor implements ItemListener {  
        private static final long serialVersionUID = 1L;  
        private JCheckBox button;  
        protected Border columnBorder;  
        private JCheckBox rendererComponent;  
  
        public CheckBoxEditor(JCheckBox checkBox) {  
            super(checkBox);  
        }  
  
          
        public Component getTableCellEditorComponent(JTable tbldata, Object value,  
                boolean isSelected, int row, int column) {  
            value = new JCheckBox();  
              
              rendererComponent = new JCheckBox("Select the old value");  
            if (value == null){  
                return null;  
            }  
            rendererComponent = (JCheckBox) value;  
            rendererComponent.addItemListener(this);  
              
            return rendererComponent;  
        }  
  
        public Object getCellEditorValue() {  
            rendererComponent.removeItemListener(this);  
            return rendererComponent;  
        }  
  
        public void itemStateChanged(ItemEvent e) {  
              
            super.fireEditingStopped();  
  
                 
        }  
    }  
  public void createcheck()
  {
     // private JCheckBox button;
//      tbldata.setCellSelectionEnabled(true);
      
  }
     
 public class YourTableCellRenderer1
       extends DefaultTableCellRenderer {
  public Component getTableCellRendererComponent(JTable tbldata,
                                                 Object value,
                                                 boolean isSelected,
                                                 boolean hasFocus,
                                                 int row,
                                                 int column) {
    Component c = 
      super.getTableCellRendererComponent(tbldata, value,
                                          isSelected, hasFocus,
                                          row, column);
    
 int srow= tbldata.getSelectedRow();
                   int scol= tbldata.getSelectedColumn();
    // Only for specific cell
     value=new JCheckBox();
     JCheckBox checkd=new JCheckBox();
    if (row == srow && column == scol) {
        c.addComponentListener(null);
       // tbldata.setC
       c.setFont(new Font("Comic Sans MS",Font.BOLD,14));
       // you may want to address isSelected here too
       c.setForeground(Color.GREEN);
       c.setBackground(Color.RED);
       //c.s;
       repaint();
    }
    return c;
    
  }
  
  
}
      public class YourTableCellRenderer
       extends DefaultTableCellRenderer {
  public Component getTableCellRendererComponent(JTable tbldata,
                                                 Object value,
                                                 boolean isSelected,
                                                 boolean hasFocus,
                                                 int row,
                                                 int column) {
    Component c = 
      super.getTableCellRendererComponent(tbldata, value,
                                          isSelected, hasFocus,
                                          row, column);
    
 int srow= tbldata.getSelectedRow();
                   int scol= tbldata.getSelectedColumn();
    // Only for specific cell
    if (row == srow && column == scol) {
        c.addComponentListener(null);
       c.setFont(new Font("Comic Sans MS",Font.BOLD,14));
       // you may want to address isSelected here too
       c.setForeground(Color.GREEN);
       c.setBackground(Color.RED);
       //c.s;
       repaint();
    }
    return c;
    
  }
  
  
}
      
public void xxx()
{
      try{
                     con3=DriverManager.getConnection(host);
                     stmt3=con3.createStatement();
                  //   int srow=tbldata.getSelectedRow();
                  //   int scol=tbldata.getSelectedColumn();
                     
                     //java.sql.Timestamp sqlDate= new java.sql.Timestamp(new java.util.Date().getTime());
                     stmt3.executeQuery("SELECT CARD_NUM,CUST_NAME FROM CUSTOMERS where CARD_NUM="+val1); 
                     int i=0;
                     res3=stmt3.getResultSet();
                             while(res3.next())
                                 {  int cmnum=Integer.parseInt(res3.getString("CARD_NUM"));
                                     if(cmnum==val1) 
                                     {
                                                String name2=res3.getString("CUST_NAME");
                                                    String cardd=res3.getString("CARD_NUM");
                                                    //tblcu[w].setValueAt(name2, 0, 1);
                                                   // tblcu[w].setValueAt(cardd,0,0);
                                                   System.out.println(cardd+" "+name2); 
                                                  /*button[i]=new JButton();
                                                  button[i].setText(cardd+" "+name2);
                                                   columnpanel2.add(button[i]);*/

                                                //   Vector<Vector> data=new Vector<Vector>();
                                                  // Vector<String> row=new Vector<String>();
                                                   //row.add(cardd);
                                                   //row.add(name2);
                                                   //data.add(row);
                                                   //row=new Vector<String>();
                                                   //row.add("nagendrakumar");
                                                   //row.add("Programmer");
                                                   //data.add(row);
                                                   //Vector<String> cols=new Vector<String>();
                                                   //cols.add("Emplooyeee");
                                                   //cols.add("Position");
                                                   
                                                  // JTable table=new JTable(data,cols);
                                                    //table.setLayout(null);
                                                    //JTableHeader head=table.getTableHeader();
                                                    //columnpanel2.add(head);
                                                    //columnpanel2.add(table);
                                        
                                        
                                     }
                                 }
                          
                              res3.close();
                     stmt3.close();
                    
                     con3.close();
                  
                }
             catch(SQLException err)
                 {
                    JOptionPane.showMessageDialog(PaymentDisplay.this,err);
                    }
}
      
public void visitd()
{
 
 }
     public void DisplayTableData()
     {
         //JOptionPane.showMessageDialog(PaymentDisplay.this,"Success");
         
         
        // tblcu[w].setAutoCreateColumnsFromModel( false );  
         
          String days1=daysdiff.getText();
          //JOptionPane.showMessageDialog(PaymentDisplay.this,days1);
         int days2=Integer.parseInt(days1);
         
         //int k=0;
            String date1=txtfromdate.getText();
            SimpleDateFormat sdf3=new SimpleDateFormat(DATE_FORMAT_NOW1);
            SimpleDateFormat sdf4=new SimpleDateFormat(DATE_FORMAT_NOW);
            Date d1=null;
             Double sum=0.0;
              Double sum2=0.0;
               Double sum3=0.0;
           try
           {
             con2=DriverManager.getConnection(host);
                     stmt2=con2.createStatement();
//                    int srow= tbldata.getSelectedRow();
                    //int scol= tbldata.getSelectedColumn();
                     //java.sql.Timestamp sqlDate= new java.sql.Timestamp(new java.util.Date().getTime());
                    stmt2.executeQuery("SELECT * FROM DAILYDATA where CARD_NUM="+val1); 
                     res2=stmt2.getResultSet();
                          
             tblcu[w].setModel(dtm);
            tblcu[w].setAutoResizeMode(tblcu[w].AUTO_RESIZE_OFF);
             int columncount=tblcu[w].getColumnCount();
            // JOptionPane.showMessageDialog(PaymentDisplay.this,columncount);
              d1=sdf3.parse(date1);
             if(columncount<5)
             {
                    
                    dtm.addColumn("S.TIME");
                    dtm.addColumn("C.NAME");
                    dtm.addColumn("C.N0");
                    for(int j=0;j<days2;j++)
                    {
                         Calendar cal4=Calendar.getInstance();
                         cal4.setTime(d1);
                           cal4.add(Calendar.DATE,+j);
                           dtm.addColumn(sdf3.format(cal4.getTime()));    
                    }
                    dtm.addColumn("S.TOTAL");
                    dtm.addColumn("TOTAL");
                    dtm.addColumn("SIGNATURE");
             
             
                        // tblcu[w].getColumnModel().getColumn(columnd);
                                
                                dtm.addRow(new Object[] {"","","AM"});
                                 dtm.addRow(new Object[] {"","","PM"});
             
             }
              tblcu[w].getColumnModel().getColumn(0).setPreferredWidth(70);
                     tblcu[w].getColumnModel().getColumn(1).setPreferredWidth(50);
                      tblcu[w].getColumnModel().getColumn(1).setPreferredWidth(170);
                      for(int j=3;j<(days2+3);j++)
                    {
                     tblcu[w].getColumnModel().getColumn(j).setPreferredWidth(75);
                    }
                     tblcu[w].getColumnModel().getColumn(days2+3).setPreferredWidth(90);
                     tblcu[w].getColumnModel().getColumn(days2+4).setPreferredWidth(90);
                     tblcu[w].getColumnModel().getColumn(days2+5).setPreferredWidth(290);
                                          for(int m=3;m<(days2+3);m++)
                                          {
                                             //String values= tblcu[w].getValueAt(0, m).toString();
                                            // Double values1=Double.parseDouble(values);
                                             tblcu[w].setValueAt("0", 0, m);
                                             tblcu[w].setValueAt("0",1,m);
//                                             if((tblcu[w].getValueAt(0, 0)).equals(null))
//                                             {
//                                              // JOptionPane.showMessageDialog(PaymentDisplay.this,"hi" );
//                                                 //tblcu[w].setValueAt("0", 0, m);
////                                                 tblcu[w].setValueAt("0", 1, m);
//                                             }
                                          }
                                          
                       while(res2.next())
                                 {  
                                         String id=res2.getString("CARD_NUM");
                                         String date2=res2.getString("DATE");
                                         Object money=res2.getString("MONEY");
                                         String sess=res2.getString("SESSION");
                                         //String name=res2.getString("CUST_NAME");
                                         for(int j=3;j<(days2+3);j++)
                    {
                                        // JOptionPane.showMessageDialog(PaymentDisplay.this,tblcu[w].getColumnName(j));
                                         String date4=tblcu[w].getColumnName(j);
                                         Date date=sdf4.parse(date4);
                                        // JOptionPane.showMessageDialog(PaymentDisplay.this,date);
                                         Date dated=sdf4.parse(date2);
                                         //JOptionPane.showMessageDialog(PaymentDisplay.this,dated);
                                         
                                            if((dated.equals(date))&&(sess.equals("Evening")))
                                         {
                                            
                                              tblcu[w].setValueAt(money, 1, j);
                                             
                                              //JOptionPane.showMessageDialog(PaymentDisplay.this,"Equal");
                                         }
                                          else if((dated.equals(date))&&(sess.equals("Morning")))
                                         {
                                             tblcu[w].setValueAt(money, 0, j);
                                             
                                              //JOptionPane.showMessageDialog(PaymentDisplay.this,"Unequal");
                                         } 
                                          
                                          
                                         //if((id.equals(txtcardn0.getText()))&&()
                    
                                        
                                 }
                    //dtm.addRow(new Object[] {1,2,3,4,5});
                   // tblcu[w].getC
                  //scroll1.getViewport().add(tblcu[w]);                    
                 //  tblcu[w].setR
                        for(int l=3;l<days2+3;l++)
         {
             Object result=tblcu[w].getValueAt(0, l);
             Object resultpm=tblcu[w].getValueAt(1,l);
             String result1=result.toString();
             String resultpm1=resultpm.toString();
            // lblname.setText(result1);
            Double result2=Double.parseDouble(result1);
            Double resultpm2=Double.parseDouble(resultpm1);
          //  int valued=Integer.parseInt(result1);
             sum=sum+result2;
             String result_val=df.format(sum);
             
             sum2=sum2+resultpm2;
             String result_val1=df.format(sum2);
             sum3=sum+sum2;
             String result_fval=df.format(sum3);
             //String sumd=
             
             tblcu[w].setValueAt(result_val, 0, (days2+3));
             tblcu[w].setValueAt(result_val1, 1, (days2+3));
             tblcu[w].setValueAt(result_fval, 0, (days2+4));
             
           //  JOptionPane.showMessageDialog(PaymentDisplay.this,sum);
         }
                       //tblcu[w].getValueAt(, columncount)
                        tblcu[w].setRowHeight(70);
                       // tblcu[w].getC
                       // tblemp.
                        tblcu[w].setSelectionForeground(Color.white);                    
                         tblcu[w].setSelectionBackground(Color.black);
                         tblcu[w].setFont(new Font("Comic Sans MS",Font.BOLD,14));                        
                        // tblsearch.setCol
                         tblcu[w].setForeground(Color.BLACK);
                         CusotomerName1(); 
                         tblcu[w].setAutoCreateColumnsFromModel(false);
                          //tblcu[w].removeAll(); 
                     //   tblemp.setFont("Times New Roman");
                       
             }          
                       tblcu[w].removeAll(); 
                       tblcu[w].setAutoCreateColumnsFromModel(false);
                        
                     }
            catch(Exception e)
    {

    }
        
           
   
       
     }

      public void printpage()
    {
//      PrinterJob printjob = PrinterJob.getPrinterJob();
//    printjob.setJobName(" CUSTOMER CARD ");
//
//    printjob.setPrintable (new Printable() {      
//         public int print(Graphics pg, PageFormat pf, int pageNum){                  
//
//             pf.setOrientation(PageFormat.LANDSCAPE);
//
//             if (pageNum > 0){
//                return Printable.NO_SUCH_PAGE;
//             }
//
//             Graphics2D g2 = (Graphics2D) pg;
//             g2.translate(pf.getImageableX(), pf.getImageableY());
//             g2.translate( 0f, 0f );
//             columnpanel2.paint(g2);
//
//             return Printable.PAGE_EXISTS;
//         }
//    });
//      if (printjob.printDialog() == false)
//       return;
//
//    try {
//       printjob.print();
//    }
//    catch (PrinterException ex) {
//       System.out.println("NO PAGE FOUND."+ex);
//    }
//      JPanel panel = new JPanel();
//        JLabel label = new JLabel();
//        label.setFont(new Font("Serif",Font.PLAIN,14));
//        label.setText("<html>This is an example of <i>a JLabel</i><br>"
//                    + "displayed inside <u>a JPanel</u> and<br>"
//                   + "printed using <b>J2PrinterWorks</b></html>");
//        columnpanel2.add(label);
//        panel.setBackground(Color.white); 
//        panel.setPreferredSize(new Dimension(200,75)); 
//
//        JFrame frame = new JFrame("J2PanelPrinter test"); 
//        frame.getContentPane().add(panel); 
//        frame.pack(); 
//        frame.setVisible(true); 

//        J2Printer printer = new J2Printer(); 
//        printer.setSeparatePrintThread(false); 
//      //  J2PanelPrinter panelPrinter1 = new J2PanelPrinter(label);
//      //  printer.add(panelPrinter1); 
//        // J2PanelPrinter panelPrinter1= new J2PanelPrinter(columnpanel2); 
//       // printer.addPageable(panelPrinter); 
//        J2PanelPrinter panelPrinter = new J2PanelPrinter(columnpanel2); 
//        printer.addPageable(panelPrinter); 
//        printer.print(); 
//       // System.exit(0); 
//        Document document = new Document(PageSize.FLSA);
//try {
//    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C://Users/Puppy/Desktop/Nagendra.pdf"));
//    document.open();
//    PdfContentByte contentByte = writer.getDirectContent();
//   // contentByte.saveState();
//   // contentByte.concatCTM(0, 1, -1, 0, 841, 0); 
//    PdfTemplate template = contentByte.createTemplate(500, 500);
//    Graphics2D g2 = template.createGraphics(500, 500);
//    columnpanel2.printAll(g2);
// 
//   // columnpanel2.print(JTable.FIT_WIDTH, header,null);
//    g2.dispose();
//   // contentByte.restoreState();
//    contentByte.addTemplate(template, 30, 300);
//} catch (Exception e) {
//    e.printStackTrace();
//}
//finally{
//    if(document.isOpen()){
//        document.close();
//    }
//}
    }
    
//        try{
////            OutputStream file=new FileOutputStream(new File("C://Users/Puppy/Desktop/Nagendra.pdf"));
////             Document document=new Document();
////             PdfWriter.getInstance(document,file);
////              // PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\temp\\test.pdf"));
////    document.open();
////    PdfContentByte contentByte = writer.getDirectContent();
////    PdfTemplate template = contentByte.createTemplate(500, 500);
////    Graphics2D g2 = template.createGraphics(500, 500);
////    panel.print(g2);
////    g2.dispose();
//   Document document = new Document();
//try {
//    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\temp\\test.pdf"));
//    document.open();
//    PdfContentByte contentByte = writer.getDirectContent();
//    PdfTemplate template = contentByte.createTemplate(500, 500);
//    Graphics2D g2 = template.createGraphics(500, 500);
//    panel.print(g2);
//    g2.dispose();
//    contentByte.addTemplate(template, 30, 300);
//} catch (Exception e) {
//    e.printStackTrace();
//}
//finally{
//    if(document.isOpen()){
//        document.close();
//    }
//}
//           // cb.addTemplate(tp
//    //contentByte.addTemplate(template, 30, 300);
////             Chunk chunk=new Chunk("HNSPEC");
////             chunk.setFont();
////             chunk.setFont(null);
////             chunk.setBackground(BaseColor.WHITE);
////             Chunk chunk1=new Chunk("");
////             con1=DriverManager.getConnection(host);
////        stmt1=con1.createStatement();
////       // +txtemp_id.getText()+","+txtemp_name.getText()+","+txtemp_mob.getText()+","+txtemp_dob.getText()+","+txtemp_doj.getText() 
////       stmt1.executeQuery("SELECT * FROM HNSPEC_EMP WHERE EMP_ID='"+txtemp_ID.getText()+"'");
////      //  reset();
////        res1=stmt1.getResultSet();
////               if(res1.next())
////             
//                                              
////                Paragraph para9=new Paragraph(" Nagendra");
////                
////                document.open();
////             // document.add(chunk);
////              
////             // document.add(chunk.NEWLINE);
////             // document.add(columnpanel2);
////              document.add(para9);
////              document.newPage();
//              
//              //file.close();
//        
//               
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
   // }
    
//public void paint(Graphics g,JComponent c)
//{
//    Rectangle oldClipBounds = g.getClipBounds();
//    Rectangle clipBounds    = new Rectangle(oldClipBounds);
//    int tableWidth   = tblcust.getColumnModel().getTotalColumnWidth();
//    clipBounds.width = Math.min(clipBounds.width, tableWidth);
//    g.setClip(clipBounds);
//
//    int firstIndex = tblcust.rowAtPoint(new Point(0, clipBounds.y));
//    int  lastIndex = tblcust.getRowCount()-1;
//
//    Rectangle rowRect = new Rectangle(0,0,
//      tableWidth, tblcust.getRowHeight() + tblcust.getRowMargin());
//    rowRect.y = firstIndex*rowRect.height;
//
//    for (int index = firstIndex; index <= lastIndex; index++) {
//      if (rowRect.intersects(clipBounds)) {
//        //System.out.println();                  // debug
//        //System.out.print("" + index +": ");    // row
//	paintRow(g, index);
//      }
//      rowRect.y += rowRect.height;
//    }
//    g.setClip(oldClipBounds);
//  }
//private void paintRow(Graphics g, int row) {
//    Rectangle rect = g.getClipBounds();
//    boolean drawn  = false;
//    
//    AttributiveCellTableModel tableModel = (AttributiveCellTableModel)tblcust.getModel();
//    CellSpan cellAtt = (CellSpan)tableModel.getCellAttribute();
//    int numColumns = tblcust.getColumnCount();
//
//    for (int column = 0; column < numColumns; column++) {
//      Rectangle cellRect = tblcust.getCellRect(row,column,true);
//      int cellRow,cellColumn;
//      if (cellAtt.isVisible(row,column)) {
//	cellRow    = row;
//	cellColumn = column;
//	  //  System.out.print("   "+column+" ");  // debug
//      } else {
//	cellRow    = row + cellAtt.getSpan(row,column)[CellSpan.ROW];
//	cellColumn = column + cellAtt.getSpan(row,column)[CellSpan.COLUMN];
//	  //  System.out.print("  ("+column+")");  // debug
//      }
//      if (cellRect.intersects(rect)) {
//	drawn = true;
//	paintCell(g, cellRect, cellRow, cellColumn);
//      } else {
//	if (drawn) break;
//      } 
//    }
//}
//
//  private void paintCell(Graphics g, Rectangle cellRect, int row, int column) {
//    int spacingHeight = tblcust.getRowMargin();
//    int spacingWidth  = tblcust.getColumnModel().getColumnMargin();
//
//    Color c = g.getColor();
//    g.setColor(tblcust.getGridColor());
//    g.drawRect(cellRect.x,cellRect.y,cellRect.width-1,cellRect.height-1);
//    g.setColor(c);
//
//    cellRect.setBounds(cellRect.x + spacingWidth/2, cellRect.y + spacingHeight/2,
//		       cellRect.width - spacingWidth, cellRect.height - spacingHeight);
//
//    if (tblcust.isEditing() && tblcust.getEditingRow()==row &&
//	tblcust.getEditingColumn()==column) {
//      Component component = tblcust.getEditorComponent();
//      component.setBounds(cellRect);
//      component.validate();
//    }
//    else {
//      TableCellRenderer renderer = tblcust.getCellRenderer(row, column);
//      Component component = tblcust.prepareRenderer(renderer, row, column);
//
//      if (component.getParent() == null) {
//	rendererPane.add(component);
//      }
//      rendererPane.paintComponent(g, component, tblcust, cellRect.x, cellRect.y,
//				  cellRect.width, cellRect.height, true);
//    }
//  }    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel16 = new javax.swing.JLabel();
        txtfromdate = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txttodate = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        daysdiff = new javax.swing.JTextField();
        borderlayoutpanel1 = new javax.swing.JPanel();
        scrollpane2 = new javax.swing.JScrollPane();
        scrollPane = new javax.swing.JScrollPane();
        borderlaoutpanel = new javax.swing.JPanel();
        custlbl = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        scrollPane2 = new javax.swing.JScrollPane();
        borderlaoutpane2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btntst = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabletest = new javax.swing.JTable();
        buttontest = new javax.swing.JButton();
        paneld = new javax.swing.JPanel();
        lable2 = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        labed = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabletest1 = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        lbltotaltxt1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dairy Payment");
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 0, 0));
        jLabel16.setText("Milk Bill Payment From");

        txtfromdate.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtfromdate.setForeground(new java.awt.Color(51, 51, 255));
        txtfromdate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtfromdateFocusLost(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 0, 0));
        jLabel18.setText("To");

        txttodate.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txttodate.setForeground(new java.awt.Color(0, 0, 255));
        txttodate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txttodateFocusLost(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 0, 0));
        jLabel20.setText("No of Days:");

        daysdiff.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        borderlayoutpanel1.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout borderlayoutpanel1Layout = new javax.swing.GroupLayout(borderlayoutpanel1);
        borderlayoutpanel1.setLayout(borderlayoutpanel1Layout);
        borderlayoutpanel1Layout.setHorizontalGroup(
            borderlayoutpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, borderlayoutpanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrollpane2, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );
        borderlayoutpanel1Layout.setVerticalGroup(
            borderlayoutpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borderlayoutpanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(scrollpane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        borderlaoutpanel.setBackground(new java.awt.Color(0, 0, 0));
        borderlaoutpanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                borderlaoutpanelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout borderlaoutpanelLayout = new javax.swing.GroupLayout(borderlaoutpanel);
        borderlaoutpanel.setLayout(borderlaoutpanelLayout);
        borderlaoutpanelLayout.setHorizontalGroup(
            borderlaoutpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1344, Short.MAX_VALUE)
        );
        borderlaoutpanelLayout.setVerticalGroup(
            borderlaoutpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 68, Short.MAX_VALUE)
        );

        scrollPane.setViewportView(borderlaoutpanel);

        custlbl.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        custlbl.setForeground(new java.awt.Color(255, 0, 0));
        custlbl.setText("No of Days:");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setText("View Data of Customers");

        javax.swing.GroupLayout borderlaoutpane2Layout = new javax.swing.GroupLayout(borderlaoutpane2);
        borderlaoutpane2.setLayout(borderlaoutpane2Layout);
        borderlaoutpane2Layout.setHorizontalGroup(
            borderlaoutpane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1052, Short.MAX_VALUE)
        );
        borderlaoutpane2Layout.setVerticalGroup(
            borderlaoutpane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 670, Short.MAX_VALUE)
        );

        scrollPane2.setViewportView(borderlaoutpane2);

        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dairyvsr/print.png"))); // NOI18N
        jButton2.setText("Print-L");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btntst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dairyvsr/print.png"))); // NOI18N
        btntst.setText("Print-P");
        btntst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntstActionPerformed(evt);
            }
        });

        tabletest.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabletest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabletestMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabletest);

        buttontest.setText("Add");
        buttontest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttontestActionPerformed(evt);
            }
        });

        paneld.setBackground(new java.awt.Color(255, 255, 255));

        lable2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dairyvsr/Dairy.png"))); // NOI18N

        javax.swing.GroupLayout paneldLayout = new javax.swing.GroupLayout(paneld);
        paneld.setLayout(paneldLayout);
        paneldLayout.setHorizontalGroup(
            paneldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneldLayout.createSequentialGroup()
                .addContainerGap(771, Short.MAX_VALUE)
                .addComponent(lable2))
        );
        paneldLayout.setVerticalGroup(
            paneldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneldLayout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addComponent(lable2)
                .addGap(28, 28, 28))
        );

        labed.setText("Nagendra Kumar");

        tabletest1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tabletest1);

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 255));
        jLabel19.setText("Select Customers to Get Payment Data");

        jButton3.setBackground(new java.awt.Color(255, 204, 204));
        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton3.setText("Reset");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Select All");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        lbltotaltxt1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setText("Total:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(30, 30, 30)
                                .addComponent(txtfromdate, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txttodate, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel20))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(18, 18, 18)
                                .addComponent(btntst)
                                .addGap(36, 36, 36)
                                .addComponent(jButton2)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(daysdiff, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jCheckBox1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(custlbl)
                                .addGap(14, 14, 14)
                                .addComponent(borderlayoutpanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbltotaltxt1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jButton3)
                                .addGap(545, 545, 545)
                                .addComponent(jButton1))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(989, 989, 989))
                            .addComponent(jScrollPane1)
                            .addComponent(scrollPane))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(paneld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(546, 546, 546)
                                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(91, 91, 91)
                                .addComponent(labed))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(205, 205, 205)
                                .addComponent(buttontest))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(scrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(172, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(txtfromdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18)
                        .addComponent(txttodate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20)
                        .addComponent(daysdiff, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(custlbl))
                    .addComponent(borderlayoutpanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(paneld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbltotaltxt1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel19)
                                        .addComponent(btntst)
                                        .addComponent(jButton2))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBox1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addGap(22, 22, 22)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel17))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(labed)
                                .addGap(5, 5, 5)
                                .addComponent(buttontest)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(235, 235, 235)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))))
                .addGap(264, 264, 264)
                .addComponent(scrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(228, 228, 228))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtfromdateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtfromdateFocusLost
        // TODO add your handling code here:
        datediff();

    }//GEN-LAST:event_txtfromdateFocusLost

    private void txttodateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txttodateFocusLost
        // TODO add your handling code here
        addRow2();
       // addRow();
        datediff();
    }//GEN-LAST:event_txttodateFocusLost

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseClicked

    private void borderlaoutpanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_borderlaoutpanelMouseClicked
        // TODO add your handling code here:
       // chItemStateChanged(evt);
    }//GEN-LAST:event_borderlaoutpanelMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        printpage();
    }//GEN-LAST:event_jButton1ActionPerformed
// class PrintableWrapper implements Printable
//    {
//        private Printable delegate;
//        private int offset;
//
//        public PrintableWrapper(Printable delegate, int offset) {
//            this.offset = offset;
//            this.delegate = delegate;
//        }
//
//        @Override
//        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
//            return delegate.print(graphics, pageFormat, pageIndex-offset);
//        }
//    }  
 class PageWrapper implements Printable {
    private Printable delegate;
    private int localPageIndex;

    public PageWrapper(Printable delegate, int pageIndex) {
        this.localPageIndex = pageIndex;
        this.delegate = delegate;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        return delegate.print(graphics, pageFormat, localPageIndex);
    }
}
public class CustomTablePrintable implements Printable {

    Printable tablePrintable;
    JTable table;
    MessageFormat header; 
    MessageFormat footer;

    public CustomTablePrintable(MessageFormat header, MessageFormat footer) {
        this.header = header;
        this.footer = footer;
    }

    public void setTablePrintable(JTable table, Printable printable) {
        tablePrintable = printable;        
        this.table = table;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, 
            int pageIndex) throws PrinterException {
        // grab an untainted graphics
        Graphics2D g2d = (Graphics2D)graphics.create();
        // calculate the offsets and wrap the pageFormat
        double headerOffset = calculateHeaderHeight(g2d, pageIndex);
        CustomPageFormat wrappingPageFormat = new CustomPageFormat(pageFormat, headerOffset, 0);
        // feed the wrapped pageFormat into the default printable
        int exists = tablePrintable.print(graphics, wrappingPageFormat, pageIndex);
        if (exists != PAGE_EXISTS) {
            g2d.dispose();
            return exists;
        }
        // translate the graphics to the start of the original pageFormat and draw header
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        printHeader(g2d, pageIndex, (int) pageFormat.getImageableWidth());
        g2d.dispose();

        return PAGE_EXISTS;        
    }


    protected double calculateHeaderHeight(Graphics2D g, int pageIndex) {
        if (header == null) return 0;
        Object[] pageNumber = new Object[]{new Integer(pageIndex + 1)};
        String text = header.format(pageNumber);
        Font headerFont = table.getFont().deriveFont(Font.BOLD, 18f);
        g.setFont(headerFont);
        Rectangle2D rect = g.getFontMetrics().getStringBounds(text, g);
        return rect.getHeight();
    }

    protected void printHeader(Graphics2D g, int pageIndex, int imgWidth) {
        Object[] pageNumber = new Object[]{new Integer(pageIndex + 1)};
        String text = header.format(pageNumber);
        Font headerFont = table.getFont().deriveFont(Font.BOLD, 18f);
        g.setFont(headerFont);
        Rectangle2D rect = g.getFontMetrics().getStringBounds(text, g);
        // following is c&p from TablePrintable printText
        int tx;

        // if the text is small enough to fit, center it
        if (rect.getWidth() < imgWidth) {
            tx = (int) ((imgWidth - rect.getWidth()) / 2);

            // otherwise, if the table is LTR, ensure the left side of
            // the text shows; the right can be clipped
        } else if (table.getComponentOrientation().isLeftToRight()) {
            tx = 0;

            // otherwise, ensure the right side of the text shows
        } else {
            tx = -(int) (Math.ceil(rect.getWidth()) - imgWidth);
        }

        int ty = (int) Math.ceil(Math.abs(rect.getY()));
        g.setColor(Color.BLACK);
        g.drawString(text, tx, ty);

    }
}
public class CustomPageFormat extends PageFormat {

    private PageFormat delegate;
    private double headerHeight;
    private double footerHeight;

    public CustomPageFormat(PageFormat format, double headerHeight, double footerHeight) {
        this.delegate = format;
        this.headerHeight = headerHeight;
        this.footerHeight = footerHeight;
    }
    /** 
     * @inherited <p>
     */
    @Override
    public double getImageableY() {
        return delegate.getImageableY() + headerHeight;
    }

    /** 
     * @inherited <p>
     */
    @Override
    public double getImageableHeight() {
        return delegate.getImageableHeight() - headerHeight - footerHeight;
    }
}

        /** 
         * @inherited <p>
         */
      
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
printTables(); 
      //  pird();
        
        
        // TODO add your handling code here:
////        try{
////            OutputStream file=new FileOutputStream(new File("C://Users/Puppy/Desktop/Nagendra.pdf"));
////             Document document=new Document();
////             PdfWriter nagendra=PdfWriter.getInstance(document,file);
////              // PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\temp\\test.pdf"));
////    document.open();
////    PdfContentByte contentByte = nagendra.getDirectContent();
////    PdfTemplate template = contentByte.createTemplate(500, 500);
////    Graphics2D g2 = template.createGraphics(500, 500);
////    columnpanel2.print(g2);
////    g2.dispose();
////   document.close();
////        }
////        catch (Exception e) {
////    e.printStackTrace();
////}
//
////          int totalPages = 0;
////            for (DragNDropTable t : getAllTables() )
////            {
////                int pages = getNumberOfPages(t.getPrintable(PrintMode.FIT_WIDTH, null, null), pf);
////                Printable p = t.getPrintable(PrintMode.FIT_WIDTH, null, null);
////                printJob.append(new PrintableWrapper(p,totalPages), pf, pages);
////                totalPages += pages;
////            }
////            printer.setPageable(printJob);
////
////            if (printer.printDialog())
////                printer.print();
////   Document document = new Document();
////try {
////    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C://Users/Puppy/Desktop/Nagendra.pdf"));
////    document.open();
////    PdfContentByte contentByte = writer.getDirectContent();
////    PdfTemplate template = contentByte.createTemplate(500, 500);
////    Graphics2D g2 = template.createGraphics(500, 500);
////    columnpanel2.print(g2);
////    g2.dispose();
////    contentByte.addTemplate(template, 30, 300);
////    document.close();
////} catch (Exception e) {
////    e.printStackTrace();
////}
//     try {       
//    PrinterJob printer = PrinterJob.getPrinterJob();
//
//    //Set 1/2 " margins and orientation
//    PageFormat pf = printer.defaultPage();
//    pf.setOrientation(PageFormat.LANDSCAPE);
//    Paper paper = new Paper();
//    double margin = 36; // half inch
//    paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight() - margin * 2);
//    pf.setPaper(paper);
//
//    Book printJob = new Book();
//
//    // Note for next line: getAllTables() returns an ArrayList of JTables
//   // for (JTable t : tblcust() )  
//   // {
//        Printable p = tblcu[w].getPrintable(JTable.PrintMode.FIT_WIDTH, null, null);
//        int pages = getNumberOfPages(p, pf);
//
//        for (int i=0; i < pages; i++)
//            printJob.append(new PageWrapper(p,i), pf);
//   // }
//
//    printer.setPageable(printJob);
//
//    System.out.println(printJob.getNumberOfPages());
//
//    if (printer.printDialog())
//        printer.print();
//    } catch (PrinterException e) {
//        e.printStackTrace();
//}
//    
    }//GEN-LAST:event_jButton2ActionPerformed

//     public int print (Graphics graphics, PageFormat pageFormat, int pageIndex) {
//                                          Graphics2D graphics2D = (Graphics2D) graphics;
//                                          Rectangle2D.Double rectangle = new Rectangle2D.Double ();
//                                          rectangle.setRect (pageFormat.getImageableX () + 72,
//                                                             pageFormat.getImageableY () + 72,
//                                                             72,
//                                                             72);
//                                          graphics2D.draw (rectangle);
//                                         return (PAGE_EXISTS);
//     }
     
     
    private void btntstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntstActionPerformed
        // TODO add your handling code here:
        printTables3();
//        final MessageFormat header = new MessageFormat("Milk Bill Payment From ("+txtfromdate.getText()+" to "+txttodate.getText()+")");
//        try {
//    tabletest.print(JTable.PrintMode.FIT_WIDTH, header,null);
//} catch (java.awt.print.PrinterException e) {
//    System.err.format("Cannot print %s%n", e.getMessage());
//} 
//                       // printTables();                                             
    }//GEN-LAST:event_btntstActionPerformed

    private void buttontestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttontestActionPerformed
        // TODO add your handling code here:
        addRow();
      // tabletest=Createcomptable();
//       CompModel.addRow();
//         Createcomptable();
//            Object source = evt.getSource();
//                if (source == buttontest) {
//                    JOptionPane.showMessageDialog(PaymentDisplay.this,"hi");
//                   
        //CompModel.addRow();
//                }
    }//GEN-LAST:event_buttontestActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         lbltotaltxt1.setText("0");
      resetarray();
      // sortlist1();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        if(jCheckBox1.getModel().isSelected())
        {
        for(int r=0;r<getcound1;r++)
        {
            cb[r].setSelected(true);
        }
        }
        else
        {
            for(int r=0;r<getcound1;r++)
            {
                cb[r].setSelected(false);
            }
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed
public boolean isCellEditable(int row, int column) {
       //all cells false
       return false;
}
    private void tabletestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabletestMouseClicked
        // TODO add your handling code here:
        int row=tabletest.getSelectedRow();
        int col=tabletest.getSelectedColumn();
        tabletest.isCellEditable(row,col);
        tabletest.setEnabled(false);
       // JOptionPane.showMessageDialog(PaymentDisplay.this,"You are not allowed to utilize this privilize!");
               
    }//GEN-LAST:event_tabletestMouseClicked

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
            java.util.logging.Logger.getLogger(PaymentDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PaymentDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PaymentDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PaymentDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PaymentDisplay().setVisible(true);
            //    new JTableHeaderCheckBox().buildGUI(); 
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel borderlaoutpane2;
    private javax.swing.JPanel borderlaoutpanel;
    private javax.swing.JPanel borderlayoutpanel1;
    private javax.swing.JButton btntst;
    private javax.swing.JButton buttontest;
    private javax.swing.JLabel custlbl;
    private javax.swing.JTextField daysdiff;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labed;
    private javax.swing.JLabel lable2;
    private javax.swing.JTextField lbltotaltxt1;
    private javax.swing.JPanel paneld;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JScrollPane scrollPane2;
    private javax.swing.JScrollPane scrollpane2;
    private javax.swing.JTable tabletest;
    private javax.swing.JTable tabletest1;
    private javax.swing.JTextField txtfromdate;
    private javax.swing.JTextField txttodate;
    // End of variables declaration//GEN-END:variables
    JButton[] button=new JButton[100];
    
}
