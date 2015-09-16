package mipsparser_new;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPopupMenu.Separator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.undo.UndoManager;
import mipsparser_new.MIPSProgram;
import mipsparser_new.BMSFS.Error;
import mipsparser_new.BMSFS.Instruction;
import mipsparser_new.BMSFS.Memory;
import mipsparser_new.BMSFS.Register;

public class Interface extends JApplet {
   MIPSProgram mipssimulator;
   boolean once_assemble = false;
   final String Projectname = "MipsParser";
   final double version = 1.0D;
   UndoManager undoManager = new UndoManager();
   Integer no_files = Integer.valueOf(0);
   Integer nameoffset = Integer.valueOf(1);
   FileDialog fc;
   JTable mainTable;
   JTable dataTable;
   static final int maxtabs = 20;
   String[] filepath = new String[20];
   JPanel[] tempanel = new JPanel[20];
   JTextPane[] tempTextPane = new JTextPane[20];
   JScrollPane[] tempScrollPane = new JScrollPane[20];
   private JMenuItem Exit;
   private JPanel Main;
   private JMenuItem clearbp;
   private JPanel codePanel;
   private JPanel editorPanel;
   private JPanel executePanel;
   private JTextPane helpPane;
   private JPanel helpPanel;
   private JButton ioClear;
   private JTextPane ioPane;
   private JButton jButton1;
   private JInternalFrame jInternalFrame1;
   private JInternalFrame jInternalFrame2;
   private JMenu jMenu1;
   private JMenu jMenu2;
   private JMenu jMenu3;
   private JMenu jMenu4;
   private JMenuBar jMenuBar1;
   private JMenuItem jMenuItem1;
   private JMenuItem jMenuItem2;
   private JPanel jPanel1;
   private JPanel jPanel2;
   private JPanel jPanel3;
   private JPanel jPanel4;
   private JPanel jPanel5;
   private JPanel jPanel6;
   private JPanel jPanel7;
   private JScrollPane jScrollPane1;
   private JScrollPane jScrollPane2;
   private JScrollPane jScrollPane3;
   private JScrollPane jScrollPane4;
   private JScrollPane jScrollPane5;
   private JScrollPane jScrollPane6;
   private JScrollPane jScrollPane9;
   private Separator jSeparator1;
   private Separator jSeparator2;
   private Separator jSeparator3;
   private Separator jSeparator4;
   private Separator jSeparator5;
   private Separator jSeparator6;
   private JTabbedPane jTabbedPane2;
   private JTabbedPane jTabbedPane3;
   private JTabbedPane jTabbedPane4;
   private JTable jTable1;
   private JTextPane jTextPane1;
   private JTextPane jTextPane2;
   private JTextPane jTextPane5;
   private JMenuItem mAssemble;
   private JMenuItem mClose;
   private JMenuItem mCloseAll;
   private JMenuItem mCopy;
   private JMenuItem mCut;
   private JMenuItem mNew;
   private JMenuItem mOpen;
   private JMenuItem mPaste;
   private JMenuItem mRedo;
   private JMenuItem mReset;
   private JMenuItem mRun;
   private JMenuItem mSave;
   private JMenuItem mSaveAs;
   private JMenuItem mSelectAll;
   private JMenuItem mStep;
   private JMenuItem mUndo;
   private JTabbedPane mainTabbedPane;
   private JButton msgClear;
   private JTextPane msgPane;
   private JButton run_button;
   private JTabbedPane samplecodepane;
   private JButton step_button;
   private JMenuItem togglebp;

   public void init() {
      for(int ex = 0; ex < 20; ++ex) {
         this.filepath[ex] = "";
      }

      try {
         try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
         } catch (UnsupportedLookAndFeelException var2) {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
         }

         EventQueue.invokeAndWait(new Runnable() {
            public void run() {
               Interface.this.initComponents();
            }
         });
      } catch (Exception var3) {
         var3.printStackTrace();
      }

      this.mainTabbedPane.setSelectedComponent(this.editorPanel);
      if(this.no_files.intValue() < 20) {
         this.addMaintab("");
      }

      this.tempTextPane[0].setFocusable(true);
      this.tempTextPane[0].requestFocusInWindow();
   }

   private void initComponents() {
      this.Main = new JPanel();
      this.mainTabbedPane = new JTabbedPane();
      this.editorPanel = new JPanel();
      this.jTabbedPane2 = new JTabbedPane();
      this.executePanel = new JPanel();
      this.jInternalFrame1 = new JInternalFrame();
      this.jInternalFrame2 = new JInternalFrame();
      this.jTabbedPane4 = new JTabbedPane();
      this.jScrollPane2 = new JScrollPane();
      this.jTable1 = new JTable();
      this.jPanel1 = new JPanel();
      this.jPanel2 = new JPanel();
      this.run_button = new JButton();
      this.step_button = new JButton();
      this.codePanel = new JPanel();
      this.samplecodepane = new JTabbedPane();
      this.jPanel3 = new JPanel();
      this.jScrollPane5 = new JScrollPane();
      this.jTextPane1 = new JTextPane();
      this.jPanel4 = new JPanel();
      this.jScrollPane6 = new JScrollPane();
      this.jTextPane2 = new JTextPane();
      this.jPanel7 = new JPanel();
      this.jScrollPane9 = new JScrollPane();
      this.jTextPane5 = new JTextPane();
      this.jButton1 = new JButton();
      this.helpPanel = new JPanel();
      this.jScrollPane1 = new JScrollPane();
      this.helpPane = new JTextPane();
      this.jTabbedPane3 = new JTabbedPane();
      this.jPanel6 = new JPanel();
      this.ioClear = new JButton();
      this.jScrollPane4 = new JScrollPane();
      this.ioPane = new JTextPane();
      this.jPanel5 = new JPanel();
      this.jScrollPane3 = new JScrollPane();
      this.msgPane = new JTextPane();
      this.msgClear = new JButton();
      this.jMenuBar1 = new JMenuBar();
      this.jMenu1 = new JMenu();
      this.mNew = new JMenuItem();
      this.mOpen = new JMenuItem();
      this.mClose = new JMenuItem();
      this.mCloseAll = new JMenuItem();
      this.jSeparator1 = new Separator();
      this.mSave = new JMenuItem();
      this.mSaveAs = new JMenuItem();
      this.jSeparator2 = new Separator();
      this.Exit = new JMenuItem();
      this.jMenu2 = new JMenu();
      this.mUndo = new JMenuItem();
      this.mRedo = new JMenuItem();
      this.jSeparator3 = new Separator();
      this.mCut = new JMenuItem();
      this.mCopy = new JMenuItem();
      this.mPaste = new JMenuItem();
      this.jSeparator4 = new Separator();
      this.mSelectAll = new JMenuItem();
      this.jMenu3 = new JMenu();
      this.mAssemble = new JMenuItem();
      this.mRun = new JMenuItem();
      this.mStep = new JMenuItem();
      this.mReset = new JMenuItem();
      this.jSeparator5 = new Separator();
      this.clearbp = new JMenuItem();
      this.togglebp = new JMenuItem();
      this.jMenu4 = new JMenu();
      this.jMenuItem1 = new JMenuItem();
      this.jSeparator6 = new Separator();
      this.jMenuItem2 = new JMenuItem();
      this.setFont(new Font("Tahoma", 0, 12));
      this.addKeyListener(new KeyAdapter() {
         public void keyPressed(KeyEvent evt) {
            Interface.this.formKeyPressed(evt);
         }
      });
      this.Main.setBackground(new Color(255, 255, 255));
      this.Main.setFont(new Font("Tahoma", 0, 12));
      this.Main.setMinimumSize(new Dimension(400, 400));
      this.Main.addKeyListener(new KeyAdapter() {
         public void keyPressed(KeyEvent evt) {
            Interface.this.MainKeyPressed(evt);
         }
      });
      this.mainTabbedPane.setBackground(new Color(230, 230, 250));
      this.mainTabbedPane.setCursor(new Cursor(12));
      this.mainTabbedPane.setFont(new Font("Segoe UI", 0, 13));
      this.mainTabbedPane.addFocusListener(new FocusAdapter() {
         public void focusGained(FocusEvent evt) {
            Interface.this.mainTabbedPaneFocusGained(evt);
         }
      });
      this.editorPanel.setBackground(new Color(230, 230, 250));
      this.editorPanel.setFocusable(false);
      this.editorPanel.setFont(new Font("Tahoma", 0, 12));
      this.editorPanel.addFocusListener(new FocusAdapter() {
         public void focusGained(FocusEvent evt) {
            Interface.this.editorPanelFocusGained(evt);
         }
      });
      this.jTabbedPane2.setBackground(new Color(255, 255, 255));
      this.jTabbedPane2.setTabLayoutPolicy(1);
      this.jTabbedPane2.setCursor(new Cursor(0));
      this.jTabbedPane2.setFont(new Font("Tahoma", 1, 12));
      GroupLayout editorPanelLayout = new GroupLayout(this.editorPanel);
      this.editorPanel.setLayout(editorPanelLayout);
      editorPanelLayout.setHorizontalGroup(editorPanelLayout.createParallelGroup(Alignment.LEADING).addComponent(this.jTabbedPane2, -1, 778, 32767));
      editorPanelLayout.setVerticalGroup(editorPanelLayout.createParallelGroup(Alignment.LEADING).addComponent(this.jTabbedPane2, -1, 343, 32767));
      this.mainTabbedPane.addTab(" Editor  ", this.editorPanel);
      this.executePanel.setBackground(new Color(255, 255, 255));
      this.executePanel.setCursor(new Cursor(0));
      this.jInternalFrame1.setTitle("Text Segment");
      this.jInternalFrame1.setFont(new Font("Tahoma", 0, 12));
      this.jInternalFrame1.setVisible(true);
      GroupLayout jInternalFrame1Layout = new GroupLayout(this.jInternalFrame1.getContentPane());
      this.jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
      jInternalFrame1Layout.setHorizontalGroup(jInternalFrame1Layout.createParallelGroup(Alignment.LEADING).addGap(0, 435, 32767));
      jInternalFrame1Layout.setVerticalGroup(jInternalFrame1Layout.createParallelGroup(Alignment.LEADING).addGap(0, 193, 32767));
      this.jInternalFrame2.setTitle("Data Segment");
      this.jInternalFrame2.setFont(new Font("Tahoma", 0, 12));
      this.jInternalFrame2.setVisible(true);
      GroupLayout jInternalFrame2Layout = new GroupLayout(this.jInternalFrame2.getContentPane());
      this.jInternalFrame2.getContentPane().setLayout(jInternalFrame2Layout);
      jInternalFrame2Layout.setHorizontalGroup(jInternalFrame2Layout.createParallelGroup(Alignment.LEADING).addGap(0, 501, 32767));
      jInternalFrame2Layout.setVerticalGroup(jInternalFrame2Layout.createParallelGroup(Alignment.LEADING).addGap(0, 86, 32767));
      this.jTabbedPane4.setBackground(new Color(255, 255, 255));
      this.jTabbedPane4.setTabLayoutPolicy(1);
      this.jTabbedPane4.setFont(new Font("Tahoma", 1, 12));
      this.jTable1.setFont(new Font("Segoe UI", 0, 12));
      this.jTable1.setModel(new DefaultTableModel(new Object[][]{{"$zero", new Integer(0), new Integer(0)}, {"$at", new Integer(1), new Integer(0)}, {"$v0", new Integer(2), new Integer(0)}, {"$v1", new Integer(3), new Integer(0)}, {"$a0", new Integer(4), new Integer(0)}, {"$a1", new Integer(5), new Integer(0)}, {"$a2", new Integer(6), new Integer(0)}, {"$a3", new Integer(7), new Integer(0)}, {"$t0", new Integer(8), new Integer(0)}, {"$t1", new Integer(9), new Integer(0)}, {"$t2", new Integer(10), new Integer(0)}, {"$t3", new Integer(11), new Integer(0)}, {"$t4", new Integer(12), new Integer(0)}, {"$t5", new Integer(13), new Integer(0)}, {"$t6", new Integer(14), new Integer(0)}, {"$t7", new Integer(15), new Integer(0)}, {"$s0", new Integer(16), new Integer(0)}, {"$s1", new Integer(17), new Integer(0)}, {"$s2", new Integer(18), new Integer(0)}, {"$s3", new Integer(19), new Integer(0)}, {"$s4", new Integer(20), new Integer(0)}, {"$s5", new Integer(21), new Integer(0)}, {"$s6", new Integer(22), new Integer(0)}, {"$s7", new Integer(23), new Integer(0)}, {"$t8", new Integer(24), new Integer(0)}, {"$t9", new Integer(25), new Integer(0)}, {"$k0", new Integer(26), new Integer(0)}, {"$k1", new Integer(27), new Integer(0)}, {"$gp", new Integer(28), new Integer(0)}, {"$sp", new Integer(29), new Integer(0)}, {"$fp", new Integer(30), new Integer(0)}, {"$ra", new Integer(31), new Integer(0)}, {"$hi", new Integer(32), new Integer(0)}, {"$lo", new Integer(33), new Integer(0)}, {"$pc", new Integer(34), new Integer(0)}}, new String[]{"Name", "Number", "Value"}) {
         Class[] types = new Class[]{String.class, Integer.class, Integer.class};
         boolean[] canEdit = new boolean[]{false, false, true};

         public Class getColumnClass(int columnIndex) {
            return this.types[columnIndex];
         }

         public boolean isCellEditable(int rowIndex, int columnIndex) {
            return this.canEdit[columnIndex];
         }
      });
      this.jTable1.getTableHeader().setReorderingAllowed(false);
      this.jScrollPane2.setViewportView(this.jTable1);
      this.jTable1.getColumnModel().getColumn(0).setResizable(false);
      this.jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
      this.jTable1.getColumnModel().getColumn(1).setResizable(false);
      this.jTable1.getColumnModel().getColumn(1).setPreferredWidth(20);
      this.jTable1.getColumnModel().getColumn(2).setResizable(false);
      this.jTable1.getColumnModel().getColumn(2).setPreferredWidth(60);
      this.jTable1.getModel().addTableModelListener(new TableModelListener() {
         int counter = 0;

         public void tableChanged(TableModelEvent e) {
            ++this.counter;
            int row = e.getFirstRow();
            int column = e.getColumn();
            TableModel model = (TableModel)e.getSource();
            model.getColumnName(column);
            Object data = model.getValueAt(row, column);
            Register.updateRegister(row, Integer.parseInt(data.toString()));
         }
      });
      this.jTabbedPane4.addTab("Registers   ", this.jScrollPane2);
      this.jPanel1.setBackground(new Color(255, 255, 255));
      this.jPanel2.setBackground(new Color(255, 255, 255));
      this.run_button.setText("Run");
      this.run_button.setEnabled(false);
      this.run_button.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            Interface.this.run_buttonActionPerformed(evt);
         }
      });
      this.step_button.setText("Step");
      this.step_button.setEnabled(false);
      this.step_button.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            Interface.this.step_buttonActionPerformed(evt);
         }
      });
      GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
      this.jPanel2.setLayout(jPanel2Layout);
      jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addComponent(this.run_button, -1, 66, 32767).addComponent(this.step_button, -1, 66, 32767));
      jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, jPanel2Layout.createSequentialGroup().addGap(38, 38, 38).addComponent(this.step_button, -2, 51, -2).addPreferredGap(ComponentPlacement.RELATED, 40, 32767).addComponent(this.run_button, -2, 51, -2).addGap(42, 42, 42)));
      GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
      this.jPanel1.setLayout(jPanel1Layout);
      jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGap(0, 66, 32767).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(0, 0, 0).addComponent(this.jPanel2, -1, -1, 32767))));
      jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGap(0, 222, 32767).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jPanel2, -1, -1, 32767)));
      GroupLayout executePanelLayout = new GroupLayout(this.executePanel);
      this.executePanel.setLayout(executePanelLayout);
      executePanelLayout.setHorizontalGroup(executePanelLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, executePanelLayout.createSequentialGroup().addGroup(executePanelLayout.createParallelGroup(Alignment.LEADING).addComponent(this.jInternalFrame2).addGroup(Alignment.TRAILING, executePanelLayout.createSequentialGroup().addComponent(this.jInternalFrame1).addGap(0, 0, 0).addComponent(this.jPanel1, -2, -1, -2))).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jTabbedPane4, -2, 255, -2)));
      executePanelLayout.setVerticalGroup(executePanelLayout.createParallelGroup(Alignment.LEADING).addGroup(executePanelLayout.createSequentialGroup().addGroup(executePanelLayout.createParallelGroup(Alignment.TRAILING).addComponent(this.jInternalFrame1).addComponent(this.jPanel1, -1, -1, 32767)).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jInternalFrame2)).addComponent(this.jTabbedPane4, -1, 343, 32767));
      this.mainTabbedPane.addTab(" Execute  ", this.executePanel);
      this.codePanel.setBackground(new Color(255, 255, 255));
      this.codePanel.setCursor(new Cursor(0));
      this.samplecodepane.setBackground(new Color(255, 255, 255));
      this.samplecodepane.setTabLayoutPolicy(1);
      this.samplecodepane.setFont(new Font("Tahoma", 0, 12));
      this.jTextPane1.setBorder((Border)null);
      this.jTextPane1.setEditable(false);
      this.jTextPane1.setFont(new Font("Tahoma", 0, 12));
      this.jTextPane1.setText("#Program to add two numbers\n\n\t.data\n\tsum: .word 0\n\n\t.text\n\tmain:\n\tli $t0, 10\n\tli $t1, 15\n\tadd $t2, $t0, $t1 \t# compute the sum.\t\n\tsw $t2, sum");
      this.jTextPane1.setPreferredSize(new Dimension(0, 100));
      this.jScrollPane5.setViewportView(this.jTextPane1);
      GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
      this.jPanel3.setLayout(jPanel3Layout);
      jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jScrollPane5, -1, 773, 32767));
      jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addComponent(this.jScrollPane5, -1, 274, 32767).addGap(0, 0, 0)));
      this.samplecodepane.addTab("Program 1", this.jPanel3);
      this.jTextPane2.setBorder((Border)null);
      this.jTextPane2.setEditable(false);
      this.jTextPane2.setFont(new Font("Tahoma", 0, 12));
      this.jTextPane2.setText("#Program to convert a string to int\n\n.data \nstring: .asciiz \"13245\"\nnewline: .word 10\n.text\nmain:\n\nla $t0, string \t\t\t# Initialize S.\nli $t2, 0 \t\t\t\t# Initialize sum = 0.\nlw $t5, newline \nsum_loop:\n\t lb $t1, ($t0) \t\t\t# load the byte at addr S into $t1,\n\t addu $t0, $t0, 1 \t\t# and increment S.\n\t beq $t1, $t5, end_sum_loop\n\n\tmul $t2, $t2, 10 \t\t\t# t2 *= 10.\n\n \tsub $t1, $t1, 48\t \t\t# t1 -= \'0\'.\n\tadd $t2, $t2, $t1 \t\t\t# t2 += t1.\n\n\tb sum_loop # and repeat the loop.\nend_sum_loop:\n");
      this.jScrollPane6.setViewportView(this.jTextPane2);
      GroupLayout jPanel4Layout = new GroupLayout(this.jPanel4);
      this.jPanel4.setLayout(jPanel4Layout);
      jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jScrollPane6, -1, 773, 32767));
      jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addComponent(this.jScrollPane6, -1, 274, 32767).addGap(0, 0, 0)));
      this.samplecodepane.addTab("Program 2", this.jPanel4);
      this.jTextPane5.setBorder((Border)null);
      this.jTextPane5.setEditable(false);
      this.jTextPane5.setFont(new Font("Tahoma", 0, 12));
      this.jTextPane5.setText("#compute length of a string\n\n.data\nstring: .asciiz \"This is a string\"\nlength: .word 0\n\n.text\nla $t1, string\nli $t2, 0\nlength_loop:\n\tlb $t3, ($t1)\n\tbeqz $t3, endloop\n\taddu $t2, $t2, 1\n\taddu $t1, $t1, 1\n\tb length_loop\nendloop:\n\tsub $t2, $t2, 1\t\t#subtract 1 to ignore \\0\n\tsw $t2, length\n");
      this.jScrollPane9.setViewportView(this.jTextPane5);
      GroupLayout jPanel7Layout = new GroupLayout(this.jPanel7);
      this.jPanel7.setLayout(jPanel7Layout);
      jPanel7Layout.setHorizontalGroup(jPanel7Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jScrollPane9, -1, 773, 32767));
      jPanel7Layout.setVerticalGroup(jPanel7Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jScrollPane9, -1, 274, 32767));
      this.samplecodepane.addTab("Program 3", this.jPanel7);
      this.jButton1.setText("Copy");
      this.jButton1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            Interface.this.jButton1ActionPerformed(evt);
         }
      });
      GroupLayout codePanelLayout = new GroupLayout(this.codePanel);
      this.codePanel.setLayout(codePanelLayout);
      codePanelLayout.setHorizontalGroup(codePanelLayout.createParallelGroup(Alignment.LEADING).addGroup(codePanelLayout.createSequentialGroup().addGap(0, 0, 0).addGroup(codePanelLayout.createParallelGroup(Alignment.LEADING).addGroup(codePanelLayout.createSequentialGroup().addComponent(this.jButton1).addContainerGap()).addGroup(codePanelLayout.createSequentialGroup().addComponent(this.samplecodepane).addGap(0, 0, 0)))));
      codePanelLayout.setVerticalGroup(codePanelLayout.createParallelGroup(Alignment.LEADING).addGroup(codePanelLayout.createSequentialGroup().addGap(0, 0, 0).addComponent(this.samplecodepane, -1, 303, 32767).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jButton1).addContainerGap()));
      this.mainTabbedPane.addTab(" Codes   ", this.codePanel);
      this.helpPane.setBorder((Border)null);
      this.helpPane.setContentType("text/html");
      this.helpPane.setEditable(false);
      this.helpPane.setFont(new Font("Tahoma", 0, 12));
      this.helpPane.setText("<html>\n  \n <head>\n \n   </head>\n   \n<body>\n<h3>\n<u>User\'s Guide</u></h3> <br>\n<p style=\"margin-top: 0; margin-left:10\">\n<b>1. Introduction</b></p>\n  \n<p style=\"margin-left: 20; margin-top:0\">\nThis simulator is designed to run MIPS codes, programed in MIPS assmebly language.  \n</p>\n<p style=\"margin-top: 0; margin-left:10\"><br>\n<b>2. Layout</b></p>\n <p style=\"margin-left: 20; margin-top:0\">\nAt the top of the simulator there is a <b>MenuBar</b>. The Menubar provides following functionalities .. \n<ul  style=\"margin-top:0\">\n\t<li>File</li>\n\t<ul>\n\t\t<li>Options for writing a new code; opening a file; closing or saving the current code etc</li>\n\t\t<li>Exiting the Simulator</li>\n\t</ul>\n\t<li>Edit</li>\n\t<ul  style=\"margin-top:0\">\n\t\t<li>Editor options like undo, redo, select all etc.\n\t</ul>\n\t<li>Run</li>\n\t<ul style=\"margin-top:0\">\n\t\t<li>Assemble the code</li>\n\t\t<li>Run the whole program</li>\n\t\t<li>Run the current instruction of the program</li>\n\t</ul>\n\n</ul></p>\n <p style=\"margin-left: 20; margin-top:0\">\n The main panel consistes of 4 tabs.\n<ul>\n\t<li><b>Editor</b> : Here the user will write their programs. The editor can have multiple tabs of code section. In each tab user can write their programs.</li><br>\n\t<li><b>Execute</b> : Assembling and running of the user code will be done here. It consists of various sections like the register table, text section, memory table etc.</li><br>\n\t<li><b>Sample Codes</b> : Various sample Mips Programs are provided here for the user help.</li><br>\n\t<li><b>Help</b> : User guide to understand the simulator.<br>\n</ul>\n</p>\n<p style=\"margin-left: 20; margin-top:0\">\n The bottom I/O panel is to generate the assembler messages. Assembly errors, runtime errors, input/output messages etc will be generated here.\n</p>\n<p style=\"margin-top: 0; margin-left:10\"><br>\n<b>3. Writing and Running a sample program</b></p>\n <p style=\"margin-left: 20; margin-top:0\">\n<ul style=\"margin-top:0\">\n\t<li> To write a program, the first thing we have to do, is to open a new editor where we can write our program. To do that ..\n\t<ol> <li>Click on the <b>File</b> button from the menu. A list will appear.</li>\n\t\t<li>Click on the <b>New</b> button from the list. A new editor will appear in the editor tab. Here we will write our program.\n\t</ol></li>\n\t<li>Next, we will write our code in the editor. We can either start from scratch and write the program or we can copy the sample code, present in the sample codes tab, and modify it to our needs.</li>\n\t<li>After writing our program, we will assemble it. To do that ..\n\t<ol>\n\t<li>Click on the <b>Run</b> buttion from the menu. A list will appear.</li>\n\t<li>Click on the <b>Assemble</b> button from the list.</li>\n\t</ol>\n\tThis will assemble our arm program and the control will be transfered from the editor window to the execute window. If there are error(s) in our program, the subsequent messages will be displayed in the bottom I/O panel.
</li>\n\t<li>After the program has been successfuly assebled, we can run it. We can either choose to run the whole program in one shot, or we can run one instruction at a time. To do the, the execute panel has two buttons, <b>Run</b> and <b>Step</b></li>\n</ul>\n</p>\n<p style=\"margin-top: 0; margin-left:10\"><br>\n<b>4. Supported Directives/Instructions</b></p>\n <p style=\"margin-left: 20; margin-top:0\">\nThe registers names are the same as defined in MIPS.\n<ol><li>In this version following directives are supported :</li>\n<ul>\n<li><b>.word</b> : To define a single variable or an array. If inital value(s) is not provided, no memory space will be reserved and the variable name will be an alias to the previously defined variable.</li>\n<li><b>.space</b> : To allocate a memory chunk of given size to a variable. A memory chunk might be composed of several words.</li>\n<li><b>.asciiz</b> : To define a null terminated String</li>\n<li><b>.ascii</b> : To define a String that is not null-terminated.</li>\n<li><b>.text</b> : To specify the begining of the text section.</li>\n<li><b>.data</b> : To specify the beginning of the data section.</li>\n</ul>\n<li>Since this is a simulator form MIPS, All the instructions and their format is same as defined by MIPS assembly language. The functionality of every instruction is in accordance with the MIPS architecture. In this version, following instructions are supported :\n<br><table border=\"1\" style=\"margin-left: 50; font-size: 14\" cell-padding=\"10\">\n   <tr><td>add</td> \n            \n          <td>addi</td> \n            \n          <td>addiu</td> \n            \n          <td>addu</td> \n            \n          <td>and</td> \n     </tr><tr>       \n          <td>andi</td> \n            \n          <td>b</td> \n            \n          <td>beq</td> \n            \n          <td>beqz</td> \n            \n          <td>bge</td> \n            \n     </tr><tr>     <td>bgez</td> \n            \n          <td>bgezal</td> \n            \n          <td>bgt</td> \n            \n          <td>bgtz</td> \n            \n          <td>ble</td> \n          </tr><tr>  \n          <td>blez</td> \n            \n          <td>blt</td> \n            \n          <td>bltz</td> \n            \n          <td>bltzal</td> \n            \n          <td>bne</td> \n            </tr><tr>\n          <td>bnez</td> \n            \n          <td>clo</td> \n            \n          <td>clz</td> \n            \n          <td>div</td> \n            \n          <td>divu</td> \n            </tr><tr>\n          <td>j</td> \n            \n          <td>jal</td> \n            \n          <td>jalr</td> \n            \n          <td>jr</td> \n            \n          <td>la</td></tr><tr>\n          <td>lb</td> \n            \n          <td>lh</td> \n            \n          <td>li</td> \n          <td>lw</td> \n            \n          <td>mfhi</td> \n            </tr><tr>\n          <td>mflo</td> \n            \n          <td>mthi</td> \n            \n          <td>mtlo</td> \n            \n          <td>move</td> 
\n            \n          <td>movn</td> \n            </tr><tr>\n          <td>movz</td> \n            \n          <td>mul</td> \n            \n          <td>mulo</td> \n            \n          <td>mult</td> \n            \n          <td>neg</td> \n            </tr><tr>\n          <td>nor</td> \n            \n          <td>not</td> \n            \n          <td>or</td> \n            \n          <td>ori</td> \n            \n          <td>sll</td> \n            </tr><tr>\n          <td>sllv</td> \n            \n          <td>slt</td> \n            \n          <td>slti</td> \n            \n          <td>sltiu</td> \n            \n          <td>sltu</td> \n            </tr><tr>\n          <td>srl</td> \n            \n          <td>srlv</td> \n            \n          <td>sb</td> \n            \n          <td>seb</td> \n            \n          <td>seh</td> \n            </tr><tr>\n          <td>sh</td> \n            \n          <td>sub</td> \n            \n          <td>sw</td> \n          <td>xor</td> \n            \n          <td>xori</td> \n   </tr>\n</table><br>\nTo add comments use \"#\" or \"//\"\n</p>\n</body>\n</html>\n ");
      this.helpPane.setCursor(new Cursor(0));
      this.jScrollPane1.setViewportView(this.helpPane);
      GroupLayout helpPanelLayout = new GroupLayout(this.helpPanel);
      this.helpPanel.setLayout(helpPanelLayout);
      helpPanelLayout.setHorizontalGroup(helpPanelLayout.createParallelGroup(Alignment.LEADING).addComponent(this.jScrollPane1, -1, 778, 32767));
      helpPanelLayout.setVerticalGroup(helpPanelLayout.createParallelGroup(Alignment.LEADING).addComponent(this.jScrollPane1, -1, 343, 32767));
      this.mainTabbedPane.addTab(" Help   ", this.helpPanel);
      this.jTabbedPane3.setBackground(new Color(255, 255, 255));
      this.jTabbedPane3.setFont(new Font("Segoe UI", 0, 13));
      this.jPanel6.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
      this.jPanel6.setAlignmentY(0.0F);
      this.ioClear.setFont(new Font("Tahoma", 0, 14));
      this.ioClear.setText("Clear");
      this.ioClear.setCursor(new Cursor(12));
      this.ioClear.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            Interface.this.ioClearActionPerformed(evt);
         }
      });
      this.ioPane.setEditable(false);
      this.ioPane.setFont(new Font("Tahoma", 0, 12));
      this.jScrollPane4.setViewportView(this.ioPane);
      GroupLayout jPanel6Layout = new GroupLayout(this.jPanel6);
      this.jPanel6.setLayout(jPanel6Layout);
      jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel6Layout.createSequentialGroup().addContainerGap().addComponent(this.ioClear).addGap(18, 18, 18).addComponent(this.jScrollPane4, -1, 685, 32767)));
      jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel6Layout.createSequentialGroup().addGap(28, 28, 28).addComponent(this.ioClear).addContainerGap(37, 32767)).addComponent(this.jScrollPane4, -1, 90, 32767));
      this.jTabbedPane3.addTab(" Run Input/Output    ", this.jPanel6);
      this.jPanel5.setBorder(new LineBorder(new Color(204, 204, 204), 1, true));
      this.msgPane.setEditable(false);
      this.msgPane.setFont(new Font("Tahoma", 0, 12));
      this.jScrollPane3.setViewportView(this.msgPane);
      this.msgClear.setFont(new Font("Tahoma", 0, 14));
      this.msgClear.setText("Clear");
      this.msgClear.setCursor(new Cursor(12));
      this.msgClear.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            Interface.this.msgClearActionPerformed(evt);
         }
      });
      GroupLayout jPanel5Layout = new GroupLayout(this.jPanel5);
      this.jPanel5.setLayout(jPanel5Layout);
      jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel5Layout.createSequentialGroup().addContainerGap().addComponent(this.msgClear).addGap(18, 18, 18).addComponent(this.jScrollPane3, -1, 685, 32767)));
      jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel5Layout.createSequentialGroup().addGap(28, 28, 28).addComponent(this.msgClear).addContainerGap(37, 32767)).addComponent(this.jScrollPane3, -1, 90, 32767));
      this.jTabbedPane3.addTab(" Parser Messages     ", this.jPanel5);
      GroupLayout MainLayout = new GroupLayout(this.Main);
      this.Main.setLayout(MainLayout);
      MainLayout.setHorizontalGroup(MainLayout.createParallelGroup(Alignment.LEADING).addComponent(this.mainTabbedPane, -1, 783, 32767).addComponent(this.jTabbedPane3, -1, 783, 32767));
      MainLayout.setVerticalGroup(MainLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, MainLayout.createSequentialGroup().addComponent(this.mainTabbedPane, -1, 375, 32767).addGap(0, 0, 0).addComponent(this.jTabbedPane3, -2, 124, -2)));
      this.jMenuBar1.setFont(new Font("Tahoma", 0, 12));
      this.jMenu1.setText("File");
      this.jMenu1.setFont(new Font("Segoe UI", 1, 12));
      this.mNew.setFont(new Font("Segoe UI", 1, 12));
      this.mNew.setText("New");
      this.mNew.setMargin(new Insets(3, 0, 0, 25));
      this.mNew.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            Interface.this.mNewActionPerformed(evt);
         }
      });
      this.jMenu1.add(this.mNew);
      this.mOpen.setFont(new Font("Segoe UI", 1, 12));
      this.mOpen.setText("Open");
      this.mOpen.setMargin(new Insets(3, 0, 0, 25));
      this.mOpen.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            Interface.this.mOpenActionPerformed(evt);
         }
      });
      this.jMenu1.add(this.mOpen);
      this.mClose.setFont(new Font("Segoe UI", 1, 12));
      this.mClose.setText("Close");
      this.mClose.setMargin(new Insets(3, 0, 0, 25));
      this.mClose.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            Interface.this.mCloseActionPerformed(evt);
         }
      });
      this.mClose.setEnabled(false);
      this.jMenu1.add(this.mClose);
      this.mCloseAll.setFont(new Font("Segoe UI", 1, 12));
      this.mCloseAll.setText("Close All");
      this.mCloseAll.setMargin(new Insets(3, 0, 0, 25));
      this.mCloseAll.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            Interface.this.mCloseAllActionPerformed(evt);
         }
      });
      this.mCloseAll.setEnabled(false);
      this.jMenu1.add(this.mCloseAll);
      this.jMenu1.add(this.jSeparator1);
      this.mSave.setFont(new Font("Segoe UI", 1, 12));
      this.mSave.setText("Save");
      this.mSave.setMargin(new Insets(3, 0, 0, 25));
      this.mSave.setEnabled(false);
      this.mSave.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            Interface.this.mSaveActionPerformed(evt);
         }
      });
      this.jMenu1.add(this.mSave);
      this.mSaveAs.setFont(new Font("Segoe UI", 1, 12));
      this.mSaveAs.setText("Save As");
      this.mSaveAs.setMargin(new Insets(3, 0, 0, 25));
      this.mSaveAs.setEnabled(false);
      this.mSaveAs.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            Interface.this.mSaveAsActionPerformed(evt);
         }
      });
      this.jMenu1.add(this.mSaveAs);
      this.jMenu1.add(this.jSeparator2);
      this.Exit.setFont(new Font("Segoe UI", 1, 12));
      this.Exit.setText("Exit");
      this.Exit.setMargin(new Insets(3, 0, 0, 25));
      this.Exit.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            Interface.this.ExitActionPerformed(evt);
         }
      });
      this.jMenu1.add(this.Exit);
      this.jMenuBar1.add(this.jMenu1);
      this.jMenu2.setText("Edit");
      this.jMenu2.setFont(new Font("Segoe UI", 1, 12));
      this.mUndo.setFont(new Font("Segoe UI", 1, 12));
      this.mUndo.setText("Undo");
      this.mUndo.setMargin(new Insets(3, 0, 0, 25));
      this.mUndo.setEnabled(false);
      this.mUndo.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            Interface.this.mUndoActionPerformed(evt);
         }
      });
      this.jMenu2.add(this.mUndo);
      this.mRedo.setFont(new Font("Segoe UI", 1, 12));
      this.mRedo.setText("Redo");
      this.mRedo.setMargin(new Insets(3, 0, 0, 25));
      this.mRedo.setEnabled(false);
      this.mRedo.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            Interface.this.mRedoActionPerformed(evt);
         }
      });
      this.jMenu2.add(this.mRedo);
      this.jMenu2.add(this.jSeparator3);
      this.mCut.setFont(new Font("Segoe UI", 1, 12));
      this.mCut.setText("Cut");
      this.mCut.setMargin(new Insets(3, 0, 0, 25));
      this.mCut.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            Interface.this.mCutActionPerformed(evt);
         }
      });
      this.mCut.setEnabled(false);
      this.jMenu2.add(this.mCut);
      this.mCopy.setFont(new Font("Segoe UI", 1, 12));
      this.mCopy.setText("Copy");
      this.mCopy.setMargin(new Insets(3, 0, 0, 25));
      this.mCopy.setEnabled(false);
      this.mCopy.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            Interface.this.mCopyActionPerformed(evt);
         }
      });
      this.jMenu2.add(this.mCopy);
      this.mPaste.setFont(new Font("Segoe UI", 1, 12));
      this.mPaste.setText("Paste");
      this.mPaste.setMargin(new Insets(3, 0, 0, 25));
      this.mPaste.setEnabled(false);
      this.mPaste.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            Interface.this.mPasteActionPerformed(evt);
         }
      });
      this.jMenu2.add(this.mPaste);
      this.jMenu2.add(this.jSeparator4);
      this.mSelectAll.setFont(new Font("Segoe UI", 1, 12));
      this.mSelectAll.setText("Select All");
      this.mSelectAll.setMargin(new Insets(3, 0, 0, 25));
      this.mSelectAll.setEnabled(false);
      this.mSelectAll.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            Interface.this.mSelectAllActionPerformed(evt);
         }
      });
      this.jMenu2.add(this.mSelectAll);
      this.jMenuBar1.add(this.jMenu2);
      this.jMenu3.setText("Run");
      this.jMenu3.setFont(new Font("Segoe UI", 1, 12));
      this.mAssemble.setFont(new Font("Segoe UI", 1, 12));
      this.mAssemble.setText("Assemble");
      this.mAssemble.setMargin(new Insets(3, 0, 0, 15));
      this.mAssemble.setEnabled(false);
      this.mAssemble.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            Interface.this.mAssembleActionPerformed(evt);
         }
      });
      this.jMenu3.add(this.mAssemble);
      this.mRun.setFont(new Font("Segoe UI", 1, 12));
      this.mRun.setText("Run");
      this.mRun.setMargin(new Insets(3, 0, 0, 15));
      this.mRun.setEnabled(false);
      this.mRun.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            Interface.this.mRunActionPerformed(evt);
         }
      });
      this.jMenu3.add(this.mRun);
      this.mStep.setFont(new Font("Segoe UI", 1, 12));
      this.mStep.setText("Step");
      this.mStep.setMargin(new Insets(3, 0, 0, 15));
      this.mStep.setEnabled(false);
      this.mStep.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            Interface.this.mStepActionPerformed(evt);
         }
      });
      this.jMenu3.add(this.mStep);
      this.mReset.setFont(new Font("Segoe UI", 1, 12));
      this.mReset.setText("Reset");
      this.mReset.setMargin(new Insets(3, 0, 0, 15));
      this.mReset.setEnabled(false);
      this.mReset.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            Interface.this.mResetActionPerformed(evt);
         }
      });
      this.jMenu3.add(this.mReset);
      this.jMenu3.add(this.jSeparator5);
      this.clearbp.setFont(new Font("Segoe UI", 1, 12));
      this.clearbp.setText("Clear BreakPoints");
      this.clearbp.setMargin(new Insets(3, 0, 0, 15));
      this.clearbp.setEnabled(false);
      this.clearbp.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            Interface.this.clearbpActionPerformed(evt);
         }
      });
      this.jMenu3.add(this.clearbp);
      this.togglebp.setFont(new Font("Segoe UI", 1, 12));
      this.togglebp.setText("Toggle Breakpoints");
      this.togglebp.setMargin(new Insets(3, 0, 0, 15));
      this.togglebp.setEnabled(false);
      this.togglebp.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            Interface.this.togglebpActionPerformed(evt);
         }
      });
      this.jMenu3.add(this.togglebp);
      this.jMenuBar1.add(this.jMenu3);
      this.jMenu4.setText("Help");
      this.jMenu4.setFont(new Font("Segoe UI", 1, 12));
      this.jMenuItem1.setFont(new Font("Segoe UI", 1, 12));
      this.jMenuItem1.setLabel(" Help      ");
      this.jMenuItem1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            Interface.this.jMenuItem1ActionPerformed(evt);
         }
      });
      this.jMenu4.add(this.jMenuItem1);
      this.jMenu4.add(this.jSeparator6);
      this.jMenuItem2.setFont(new Font("Segoe UI", 1, 12));
      this.jMenuItem2.setLabel(" About      ");
      this.jMenuItem2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            Interface.this.jMenuItem2ActionPerformed(evt);
         }
      });
      this.jMenu4.add(this.jMenuItem2);
      this.jMenuBar1.add(this.jMenu4);
      this.setJMenuBar(this.jMenuBar1);
      GroupLayout layout = new GroupLayout(this.getContentPane());
      this.getContentPane().setLayout(layout);
      layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.Main, -1, -1, 32767));
      layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.Main, -1, -1, 32767));
   }

   private void mCutActionPerformed(ActionEvent evt) {
      if(this.mainTabbedPane.getSelectedIndex() == 0) {
         int index = this.jTabbedPane2.getSelectedIndex();
         this.tempTextPane[index].cut();
      }
   }

   void addMaintab(String name) {
      if(this.no_files.intValue() == 0) {
         this.menu1v(Boolean.valueOf(true));
      }

      this.filepath[this.no_files.intValue()] = "";
      this.tempTextPane[this.no_files.intValue()] = new JTextPane();
      this.tempTextPane[this.no_files.intValue()].getDocument().addUndoableEditListener(new UndoableEditListener() {
         public void undoableEditHappened(UndoableEditEvent e) {
            Interface.this.undoManager.addEdit(e.getEdit());
         }
      });
      this.tempTextPane[this.no_files.intValue()].setFont(new Font("Segoe UI", 0, 13));
      this.tempScrollPane[this.no_files.intValue()] = new JScrollPane();
      this.tempScrollPane[this.no_files.intValue()].setViewportView(this.tempTextPane[this.no_files.intValue()]);
      this.tempScrollPane[this.no_files.intValue()].setBorder(BorderFactory.createEmptyBorder());
      this.tempanel[this.no_files.intValue()] = new JPanel();
      this.tempanel[this.no_files.intValue()].setBorder(BorderFactory.createEmptyBorder());
      GroupLayout tempLayout = new GroupLayout(this.tempanel[this.no_files.intValue()]);
      this.tempanel[this.no_files.intValue()].setLayout(tempLayout);
      tempLayout.setHorizontalGroup(tempLayout.createParallelGroup(Alignment.LEADING).addGap(0, 754, 32767).addGroup(tempLayout.createParallelGroup(Alignment.LEADING).addComponent(this.tempScrollPane[this.no_files.intValue()], -1, 754, 32767)));
      tempLayout.setVerticalGroup(tempLayout.createParallelGroup(Alignment.LEADING).addGap(0, 391, 32767).addGroup(tempLayout.createParallelGroup(Alignment.LEADING).addComponent(this.tempScrollPane[this.no_files.intValue()], -1, 391, 32767)));
      if(name.equals("")) {
         this.jTabbedPane2.addTab("mips" + this.nameoffset + ".asm", this.tempanel[this.no_files.intValue()]);
         this.jTabbedPane2.setFont(new Font("Tahoma", 0, 12));
         this.nameoffset = Integer.valueOf(this.nameoffset.intValue() + 1);
      } else {
         this.jTabbedPane2.addTab(name, this.tempanel[this.no_files.intValue()]);
      }

      this.jTabbedPane2.setSelectedIndex(this.no_files.intValue());
      this.tempTextPane[this.no_files.intValue()].requestFocus();
      this.no_files = Integer.valueOf(this.no_files.intValue() + 1);
   }

   void menu1v(Boolean a) {
      this.mClose.setEnabled(a.booleanValue());
      this.mCloseAll.setEnabled(a.booleanValue());
      this.mSave.setEnabled(a.booleanValue());
      this.mSaveAs.setEnabled(a.booleanValue());
      this.mUndo.setEnabled(a.booleanValue());
      this.mRedo.setEnabled(a.booleanValue());
      this.mCut.setEnabled(a.booleanValue());
      this.mCopy.setEnabled(a.booleanValue());
      this.mPaste.setEnabled(a.booleanValue());
      this.mSelectAll.setEnabled(a.booleanValue());
      this.mAssemble.setEnabled(a.booleanValue());
   }

   private void mNewActionPerformed(ActionEvent evt) {
      this.mainTabbedPane.setSelectedComponent(this.editorPanel);
      if(this.no_files.intValue() < 20) {
         this.addMaintab("");
      }

   }

   private void mCloseActionPerformed(ActionEvent evt) {
      this.mainTabbedPane.setSelectedComponent(this.editorPanel);
      int index = this.jTabbedPane2.getSelectedIndex();
      if(index != -1) {
         this.jTabbedPane2.remove(index);
         this.no_files = Integer.valueOf(this.no_files.intValue() - 1);
         if(this.no_files.intValue() == 0) {
            this.menu1v(Boolean.valueOf(false));
         }

      }
   }

   private void ExitActionPerformed(ActionEvent evt) {
      this.Main.removeAll();
      System.exit(1);
   }

   private void mCloseAllActionPerformed(ActionEvent evt) {
      this.mainTabbedPane.setSelectedComponent(this.editorPanel);
      this.no_files = Integer.valueOf(0);
      this.menu1v(Boolean.valueOf(false));

      while(true) {
         int index = this.jTabbedPane2.getSelectedIndex();
         if(index == -1) {
            return;
         }

         this.jTabbedPane2.remove(index);
      }
   }

   private void mOpenActionPerformed(ActionEvent evt) {
      this.mainTabbedPane.setSelectedComponent(this.editorPanel);
      String content = "";
      this.fc = new FileDialog(new Frame(), "Open", 0);
      this.fc.setVisible(true);
      String File = this.fc.getFile();
      if(File != null) {
         File file = new File(this.fc.getDirectory() + this.fc.getFile());

         try {
            Scanner index;
            for(index = new Scanner(file); index.hasNextLine(); content = content + index.nextLine()) {
               if(content.length() > 0) {
                  content = content + "\n";
               }
            }

            index.close();
         } catch (Exception var8) {
            JOptionPane jOptionPane1 = new JOptionPane();
            String message = var8.getMessage();
            JOptionPane.showMessageDialog((Component)null, message);
         }

         if(this.no_files.intValue() < 20) {
            this.addMaintab(this.fc.getFile());
            int index1 = this.jTabbedPane2.getSelectedIndex();
            this.tempTextPane[index1].setText(content);
            this.filepath[index1] = this.fc.getDirectory() + this.fc.getFile();
         }

      }
   }

   private void mSaveAsActionPerformed(ActionEvent evt) {
      int index = this.jTabbedPane2.getSelectedIndex();
      if(index != -1) {
         String content = this.tempTextPane[index].getText();
         this.fc = new FileDialog(new Frame(), "Save As", 1);
         this.fc.setVisible(true);
         String file = this.fc.getFile();
         if(file != null) {
            String filep = this.fc.getDirectory() + this.fc.getFile();

            try {
               BufferedWriter e = new BufferedWriter(new FileWriter(filep));
               e.write(content);
               e.close();
               this.jTabbedPane2.setTitleAt(index, file);
               this.filepath[index] = filep;
            } catch (Exception var7) {
               ;
            }

         }
      }
   }

   private void mSaveActionPerformed(ActionEvent evt) {
      int index = this.jTabbedPane2.getSelectedIndex();
      if(index != -1) {
         String content = this.tempTextPane[index].getText();
         if(this.filepath[index].equals("")) {
            this.fc = new FileDialog(new Frame(), "Save", 1);
            this.fc.setVisible(true);
            String e = this.fc.getFile();
            if(e == null) {
               return;
            }

            String out = this.fc.getDirectory() + this.fc.getFile();

            try {
               BufferedWriter e1 = new BufferedWriter(new FileWriter(out));
               e1.write(content);
               e1.close();
               this.jTabbedPane2.setTitleAt(index, e);
               this.filepath[index] = out;
            } catch (Exception var8) {
               ;
            }
         } else {
            try {
               FileWriter e2 = new FileWriter(this.filepath[index]);
               BufferedWriter out1 = new BufferedWriter(e2);
               out1.write(content);
               out1.close();
            } catch (Exception var7) {
               ;
            }
         }

      }
   }

   private void mCopyActionPerformed(ActionEvent evt) {
      if(this.mainTabbedPane.getSelectedIndex() == 0) {
         int index = this.jTabbedPane2.getSelectedIndex();
         this.tempTextPane[index].copy();
      }
   }

   private void mPasteActionPerformed(ActionEvent evt) {
      if(this.mainTabbedPane.getSelectedIndex() == 0) {
         int index = this.jTabbedPane2.getSelectedIndex();
         this.tempTextPane[index].paste();
      }
   }

   private void mSelectAllActionPerformed(ActionEvent evt) {
      if(this.mainTabbedPane.getSelectedIndex() == 0) {
         int index = this.jTabbedPane2.getSelectedIndex();
         if(index != -1) {
            this.tempTextPane[index].selectAll();
         }
      }
   }

   private void mUndoActionPerformed(ActionEvent evt) {
      if(this.mainTabbedPane.getSelectedIndex() == 0) {
         int index = this.jTabbedPane2.getSelectedIndex();
         if(index != -1) {
            if(this.undoManager.canUndo()) {
               this.undoManager.undo();
            }

         }
      }
   }

   private void mRedoActionPerformed(ActionEvent evt) {
      if(this.mainTabbedPane.getSelectedIndex() == 0) {
         int index = this.jTabbedPane2.getSelectedIndex();
         if(index != -1) {
            if(this.undoManager.canRedo()) {
               this.undoManager.redo();
            }

         }
      }
   }

   private void ioClearActionPerformed(ActionEvent evt) {
      this.ioPane.setText("");
   }

   private void msgClearActionPerformed(ActionEvent evt) {
      this.msgPane.setText("");
   }

   private void formKeyPressed(KeyEvent evt) {
   }

   private void KeyPressed(KeyEvent evt) {
   }

   private void MainKeyPressed(KeyEvent evt) {
   }

   void enableRMenu(Boolean a) {
      this.mRun.setEnabled(a.booleanValue());
      this.mStep.setEnabled(a.booleanValue());
      this.mReset.setEnabled(a.booleanValue());
      this.clearbp.setEnabled(a.booleanValue());
      this.togglebp.setEnabled(a.booleanValue());
      this.step_button.setEnabled(a.booleanValue());
      this.run_button.setEnabled(a.booleanValue());
   }

   void assembleAction(JTable mainTable, JTable dataTable) {
      this.mainTabbedPane.setSelectedComponent(this.executePanel);
      JScrollPane tableScrollPanel = new JScrollPane();
      mainTable.setModel(new DefaultTableModel(new Object[0][], new String[]{"Bkpt", "Address", "Source"}) {
         boolean[] canEdit = new boolean[]{true, false, false};

         public boolean isCellEditable(int rowIndex, int columnIndex) {
            return this.canEdit[columnIndex];
         }

         public Class getColumnClass(int c) {
            return c == 0?Boolean.class:this.getValueAt(0, c).getClass();
         }
      });
      tableScrollPanel.setViewportView(mainTable);
      mainTable.getColumnModel().getColumn(0).setResizable(false);
      mainTable.getColumnModel().getColumn(0).setPreferredWidth(50);
      mainTable.getColumnModel().getColumn(1).setResizable(false);
      mainTable.getColumnModel().getColumn(1).setPreferredWidth(150);
      mainTable.getColumnModel().getColumn(2).setPreferredWidth(800);
      GroupLayout tempLayout = (GroupLayout)this.jInternalFrame1.getContentPane().getLayout();
      tempLayout.setHorizontalGroup(tempLayout.createParallelGroup(Alignment.LEADING).addComponent(tableScrollPanel, -1, 497, 32767));
      tempLayout.setVerticalGroup(tempLayout.createParallelGroup(Alignment.LEADING).addComponent(tableScrollPanel, -1, 130, 32767));
      JScrollPane dtableScrollPanel = new JScrollPane();
      dataTable.setModel(new DefaultTableModel(new Object[0][], new String[]{"Address", "Value(+0)", "Value(+4)", "Value(+8)", "Value(+12)", "Value(+16)", "Value(+20)"}) {
         boolean[] canEdit = new boolean[]{false, true, true, true, true, true, true};

         public boolean isCellEditable(int rowIndex, int columnIndex) {
            return this.canEdit[columnIndex];
         }

         public Class getColumnClass(int c) {
            return this.getValueAt(0, c).getClass();
         }
      });
      dtableScrollPanel.setViewportView(dataTable);
      dataTable.getColumnModel().getColumn(0).setResizable(false);
      dataTable.getColumnModel().getColumn(1).setResizable(false);
      dataTable.getColumnModel().getColumn(2).setResizable(false);
      dataTable.getColumnModel().getColumn(3).setResizable(false);
      dataTable.getColumnModel().getColumn(4).setResizable(false);
      dataTable.getColumnModel().getColumn(5).setResizable(false);
      dataTable.getColumnModel().getColumn(6).setResizable(false);
      GroupLayout dtempLayout = (GroupLayout)this.jInternalFrame2.getContentPane().getLayout();
      dtempLayout.setHorizontalGroup(dtempLayout.createParallelGroup(Alignment.LEADING).addComponent(dtableScrollPanel, -1, 497, 32767));
      dtempLayout.setVerticalGroup(dtempLayout.createParallelGroup(Alignment.LEADING).addComponent(dtableScrollPanel, -1, 130, 32767));
      dataTable.getModel().addTableModelListener(new TableModelListener() {
         int counter = 0;

         public void tableChanged(TableModelEvent e) {
            ++this.counter;
            int row = e.getFirstRow();
            int column = e.getColumn();
            if(column != -1) {
               TableModel model = (TableModel)e.getSource();
               model.getColumnName(column);
               Object data = model.getValueAt(row, column);
               int mem_offset = Memory.startAddressOfDynamicData;
               int Offset = mem_offset + 24 * row + 4 * (column - 1);
               Memory.storeWord(Offset, Integer.parseInt(data.toString()));
            }
         }
      });
      dataTable.setRowSelectionAllowed(false);
      dataTable.setSelectionBackground(Color.blue);
   }

   private void updateRegTable() {
      DefaultTableModel r_model = (DefaultTableModel)this.jTable1.getModel();

      for(int i = 0; i < 35; ++i) {
         r_model.setValueAt(Integer.valueOf(Register.getRegValue(i)), i, 2);
      }

   }

   private void mAssembleActionPerformed(ActionEvent evt) {
      this.mainTabbedPane.setSelectedComponent(this.executePanel);
      this.jTable1.clearSelection();
      this.clearAllbp();
      this.msgPane.setText("");
      new String();
      int tab_index = this.jTabbedPane2.getSelectedIndex();
      String source = this.tempTextPane[tab_index].getText();
      if(!this.once_assemble) {
         this.mainTable = new JTable();
         this.dataTable = new JTable();
         this.assembleAction(this.mainTable, this.dataTable);
         this.once_assemble = true;
      } else {
         ListSelectionModel ins_offset = this.mainTable.getSelectionModel();
         ins_offset.clearSelection();
      }

      DefaultTableModel mainModel;
      try {
         this.mipssimulator = new MIPSProgram(source);
         Error var15 = this.mipssimulator.getAssembleTimeError();
         if(!var15.isOk()) {
            this.msgPane.setForeground(Color.red);
            this.jTabbedPane3.setSelectedComponent(this.jPanel5);
            this.msgPane.setText(this.msgPane.getText() + "\n" + var15.getErrorMsg());
            DefaultTableModel var17 = (DefaultTableModel)this.mainTable.getModel();
            mainModel = (DefaultTableModel)this.dataTable.getModel();

            while(var17.getRowCount() > 0) {
               var17.removeRow(0);
            }

            while(mainModel.getRowCount() > 0) {
               mainModel.removeRow(0);
            }

            return;
         }
      } catch (Exception var14) {
         ;
      }

      this.updateRegTable();
      int var16 = Instruction.startAddressOfInst;
      int mem_offset = Memory.startAddressOfDynamicData;
      this.enableRMenu(Boolean.valueOf(true));
      mainModel = (DefaultTableModel)this.mainTable.getModel();
      DefaultTableModel dataModel = (DefaultTableModel)this.dataTable.getModel();

      while(mainModel.getRowCount() > 0) {
         mainModel.removeRow(0);
      }

      while(dataModel.getRowCount() > 0) {
         dataModel.removeRow(0);
      }

      new ArrayList();
      ArrayList sourceofTextSection = this.mipssimulator.getsourceOfTextSection();

      int i;
      for(i = 0; i < sourceofTextSection.size(); ++i) {
         Object[] value = new Object[]{Boolean.valueOf(false), Integer.valueOf(var16 + i), " " + ((String)sourceofTextSection.get(i)).toString()};
         mainModel.addRow(value);
      }

      for(i = 0; i < 15; ++i) {
         Integer[] var18 = new Integer[6];
         Integer printableAddr = Integer.valueOf(mem_offset + 24 * i);

         for(int j = 0; j < 6; ++j) {
            int addr = printableAddr.intValue() + 4 * j;
            var18[j] = Integer.valueOf(Memory.readWord(addr));
         }

         dataModel.addRow(new Object[]{printableAddr.toString(), var18[0].toString(), var18[1].toString(), var18[2].toString(), var18[3].toString(), var18[4].toString(), var18[5].toString()});
      }

   }

   private void jMenuItem1ActionPerformed(ActionEvent evt) {
      this.mainTabbedPane.setSelectedComponent(this.helpPanel);
   }

   private void jMenuItem2ActionPerformed(ActionEvent evt) {
      JOptionPane jOptionPane1 = new JOptionPane();
      String message = "MipsParser Version 1.0 Build 100\nDesigned by -\n\tLakshya Jain and Rishikesh Dwivedi\n\nMipsParser is a Mips Assembler and\nRuntime Simulator.";
      JOptionPane.showMessageDialog((Component)null, message);
   }

   private void mainTabbedPaneFocusGained(FocusEvent evt) {
   }

   private void editorPanelFocusGained(FocusEvent evt) {
      this.jTabbedPane2.requestFocus();
   }

   private void mRunActionPerformed(ActionEvent evt) {
      DefaultTableModel mainModel = (DefaultTableModel)this.mainTable.getModel();
      int index = -1;

      int mem_offset;
      int selectionModel;
      do {
         mem_offset = Register.getPC();
         int dataModel = Instruction.startAddressOfInst;
         selectionModel = (mem_offset - dataModel) / 4;
         Error rmodel = this.mipssimulator.runCurrentInstruction();
         this.msgPane.setForeground(Color.red);
         if(!rmodel.isOk()) {
            this.jTabbedPane3.setSelectedComponent(this.jPanel5);
            this.msgPane.setText(this.msgPane.getText() + "\n" + rmodel.getErrorMsg());
         } else if(rmodel.isWarning()) {
            this.jTabbedPane3.setSelectedComponent(this.jPanel5);
            this.msgPane.setText(this.msgPane.getText() + "\n" + rmodel.getErrorMsg());
         }

         if(selectionModel == index) {
            break;
         }

         index = selectionModel;
      } while(selectionModel < mainModel.getRowCount() && !mainModel.getValueAt(selectionModel, 0).equals(Boolean.valueOf(true)));

      mem_offset = Memory.startAddressOfDynamicData;
      DefaultTableModel var12 = (DefaultTableModel)this.dataTable.getModel();

      int regModel;
      int i;
      for(selectionModel = 0; selectionModel < 15; ++selectionModel) {
         int[] var14 = new int[6];
         int tempReg = mem_offset + 24 * selectionModel;

         for(regModel = 0; regModel < 6; ++regModel) {
            int first = tempReg + 4 * regModel;
            var14[regModel] = Memory.readWord(first);
            i = Integer.parseInt(var12.getValueAt(selectionModel, regModel + 1).toString());
            var12.setValueAt(Integer.valueOf(var14[regModel]), selectionModel, regModel + 1);
         }
      }

      ListSelectionModel var13 = this.mainTable.getSelectionModel();
      var13.setSelectionInterval(index, index);
      this.mainTable.scrollRectToVisible(new Rectangle(this.mainTable.getCellRect(index, 0, true)));
      DefaultTableModel var15 = (DefaultTableModel)this.jTable1.getModel();
      int[] var16 = new int[35];

      for(regModel = 0; regModel < 35; ++regModel) {
         var16[regModel] = ((Integer)var15.getValueAt(regModel, 2)).intValue();
      }

      ListSelectionModel var17 = this.jTable1.getSelectionModel();
      var17.setSelectionMode(2);
      Boolean var18 = Boolean.valueOf(false);

      for(i = 0; i < 35; ++i) {
         var15.setValueAt(Integer.valueOf(Register.getRegValue(i)), i, 2);
         if(var16[i] != Register.getRegValue(i)) {
            if(!var18.booleanValue()) {
               var17.setSelectionInterval(i, i);
               var18 = Boolean.valueOf(true);
            } else {
               var17.addSelectionInterval(i, i);
            }
         }
      }

   }

   private void mStepActionPerformed(ActionEvent evt) {
      int currentPC = Register.getPC();
      int startPC = Instruction.startAddressOfInst;
      int index = (currentPC - startPC) / 4;
      Error err = this.mipssimulator.runCurrentInstruction();
      int new_index = (Register.getPC() - Instruction.startAddressOfInst) / 4;
      this.msgPane.setForeground(Color.red);
      if(!err.isOk()) {
         this.jTabbedPane3.setSelectedComponent(this.jPanel5);
         this.msgPane.setText(this.msgPane.getText() + "\n" + err.getErrorMsg());
      } else if(err.isWarning()) {
         this.jTabbedPane3.setSelectedComponent(this.jPanel5);
         this.msgPane.setText(this.msgPane.getText() + "\n" + err.getErrorMsg());
      }

      int mem_offset = Memory.startAddressOfDynamicData;
      DefaultTableModel dataModel = (DefaultTableModel)this.dataTable.getModel();

      int regModel;
      for(int selectionModel = 0; selectionModel < 15; ++selectionModel) {
         int[] rmodel = new int[6];
         int tempReg = mem_offset + 24 * selectionModel;

         for(regModel = 0; regModel < 6; ++regModel) {
            int first = tempReg + 4 * regModel;
            rmodel[regModel] = Memory.readWord(first);
            dataModel.setValueAt(Integer.valueOf(rmodel[regModel]), selectionModel, regModel + 1);
         }
      }

      ListSelectionModel var15 = this.mainTable.getSelectionModel();
      var15.setSelectionInterval(index, index);
      this.mainTable.scrollRectToVisible(new Rectangle(this.mainTable.getCellRect(index, 0, true)));
      DefaultTableModel var16 = (DefaultTableModel)this.jTable1.getModel();
      int[] var17 = new int[35];

      for(regModel = 0; regModel < 35; ++regModel) {
         var17[regModel] = ((Integer)var16.getValueAt(regModel, 2)).intValue();
      }

      ListSelectionModel var18 = this.jTable1.getSelectionModel();
      var18.setSelectionMode(2);
      Boolean var19 = Boolean.valueOf(false);

      for(int i = 0; i < 35; ++i) {
         var16.setValueAt(Integer.valueOf(Register.getRegValue(i)), i, 2);
         if(var17[i] != Register.getRegValue(i)) {
            if(!var19.booleanValue()) {
               var18.setSelectionInterval(i, i);
               var19 = Boolean.valueOf(true);
            } else {
               var18.addSelectionInterval(i, i);
            }
         }
      }

   }

   private void mResetActionPerformed(ActionEvent evt) {
      this.mainTabbedPane.setSelectedComponent(this.executePanel);
      this.msgPane.setText("");
      this.jTable1.clearSelection();
      this.clearAllbp();
      new String();
      int tab_index = this.jTabbedPane2.getSelectedIndex();
      String source = this.tempTextPane[tab_index].getText();
      if(!this.once_assemble) {
         this.mainTable = new JTable();
         this.dataTable = new JTable();
         this.assembleAction(this.mainTable, this.dataTable);
         this.once_assemble = true;
      }

      try {
         this.mipssimulator = new MIPSProgram(source);
      } catch (Exception var14) {
         ;
      }

      this.updateRegTable();
      int ins_offset = Instruction.startAddressOfInst;
      int mem_offset = Memory.startAddressOfDynamicData;
      this.enableRMenu(Boolean.valueOf(true));
      DefaultTableModel mainModel = (DefaultTableModel)this.mainTable.getModel();
      DefaultTableModel dataModel = (DefaultTableModel)this.dataTable.getModel();

      while(mainModel.getRowCount() > 0) {
         mainModel.removeRow(0);
      }

      while(dataModel.getRowCount() > 0) {
         dataModel.removeRow(0);
      }

      new ArrayList();
      ArrayList sourceofTextSection = this.mipssimulator.getsourceOfTextSection();

      int i;
      for(i = 0; i < sourceofTextSection.size(); ++i) {
         Object[] value = new Object[]{Boolean.valueOf(false), Integer.valueOf(ins_offset + i), " " + ((String)sourceofTextSection.get(i)).toString()};
         mainModel.addRow(value);
      }

      for(i = 0; i < 15; ++i) {
         Integer[] var15 = new Integer[6];
         Integer printableAddr = Integer.valueOf(mem_offset + 24 * i);

         for(int j = 0; j < 6; ++j) {
            int addr = printableAddr.intValue() + 4 * j;
            var15[j] = Integer.valueOf(Memory.readWord(addr));
         }

         dataModel.addRow(new Object[]{printableAddr.toString(), var15[0].toString(), var15[1].toString(), var15[2].toString(), var15[3].toString(), var15[4].toString(), var15[5].toString()});
      }

   }

   void clearAllbp() {
      if(this.once_assemble) {
         DefaultTableModel mainModel = (DefaultTableModel)this.mainTable.getModel();
         int rows = mainModel.getRowCount();

         for(int i = 0; i < rows; ++i) {
            mainModel.setValueAt(Boolean.valueOf(false), i, 0);
         }

      }
   }

   private void clearbpActionPerformed(ActionEvent evt) {
      this.clearAllbp();
   }

   private void togglebpActionPerformed(ActionEvent evt) {
      DefaultTableModel mainModel = (DefaultTableModel)this.mainTable.getModel();
      int rows = mainModel.getRowCount();

      for(int i = 0; i < rows; ++i) {
         if(mainModel.getValueAt(i, 0).equals(Boolean.valueOf(true))) {
            mainModel.setValueAt(Boolean.valueOf(false), i, 0);
         } else {
            mainModel.setValueAt(Boolean.valueOf(true), i, 0);
         }
      }

   }

   private void run_buttonActionPerformed(ActionEvent evt) {
      DefaultTableModel mainModel = (DefaultTableModel)this.mainTable.getModel();
      int index = -1;
      int testcnt = 0;
      boolean aerr = false;

      int mem_offset;
      int selectionModel;
      while(true) {
         ++testcnt;
         mem_offset = Register.getPC();
         int dataModel = Instruction.startAddressOfInst;
         selectionModel = (mem_offset - dataModel) / 4;
         Error rmodel = this.mipssimulator.runCurrentInstruction();
         if(!rmodel.isOk()) {
            this.jTabbedPane3.setSelectedComponent(this.jPanel5);
            this.msgPane.setText(this.msgPane.getText() + "\n" + rmodel.getErrorMsg());
         } else if(rmodel.isWarning()) {
            this.jTabbedPane3.setSelectedComponent(this.jPanel5);
            this.msgPane.setText(this.msgPane.getText() + "\n" + rmodel.getErrorMsg());
         }

         this.msgPane.setForeground(Color.red);
         this.jTabbedPane3.setSelectedComponent(this.jPanel5);
         Error tempReg;
         if(selectionModel == index && index < mainModel.getRowCount()) {
            tempReg = new Error("Infinite Loop detected", 2);
            if(!tempReg.isOk()) {
               this.jTabbedPane3.setSelectedComponent(this.jPanel5);
               this.msgPane.setText(this.msgPane.getText() + "\n" + tempReg.getErrorMsg());
               this.msgPane.setForeground(Color.red);
            }

            aerr = true;
            break;
         }

         index = selectionModel;
         if(selectionModel >= mainModel.getRowCount() || mainModel.getValueAt(selectionModel, 0).equals(Boolean.valueOf(true))) {
            break;
         }

         if(testcnt > 2000) {
            tempReg = new Error("Infinite Loop detected", 2);
            if(!tempReg.isOk()) {
               this.jTabbedPane3.setSelectedComponent(this.jPanel5);
               this.msgPane.setText(this.msgPane.getText() + "\n" + tempReg.getErrorMsg());
               this.msgPane.setForeground(Color.red);
            }

            aerr = true;
            break;
         }
      }

      if(!aerr) {
         this.msgPane.setText("Program Completed Successfully!");
         this.msgPane.setForeground(Color.green);
      }

      mem_offset = Memory.startAddressOfDynamicData;
      DefaultTableModel var14 = (DefaultTableModel)this.dataTable.getModel();

      int regModel;
      int i;
      for(selectionModel = 0; selectionModel < 15; ++selectionModel) {
         int[] var16 = new int[6];
         int var18 = mem_offset + 24 * selectionModel;

         for(regModel = 0; regModel < 6; ++regModel) {
            int first = var18 + 4 * regModel;
            var16[regModel] = Memory.readWord(first);
            i = Integer.parseInt(var14.getValueAt(selectionModel, regModel + 1).toString());
            var14.setValueAt(Integer.valueOf(var16[regModel]), selectionModel, regModel + 1);
         }
      }

      ListSelectionModel var15 = this.mainTable.getSelectionModel();
      var15.setSelectionInterval(index, index);
      this.mainTable.scrollRectToVisible(new Rectangle(this.mainTable.getCellRect(index, 0, true)));
      DefaultTableModel var17 = (DefaultTableModel)this.jTable1.getModel();
      int[] var19 = new int[35];

      for(regModel = 0; regModel < 35; ++regModel) {
         var19[regModel] = ((Integer)var17.getValueAt(regModel, 2)).intValue();
      }

      ListSelectionModel var20 = this.jTable1.getSelectionModel();
      var20.setSelectionMode(2);
      Boolean var21 = Boolean.valueOf(false);

      for(i = 0; i < 35; ++i) {
         var17.setValueAt(Integer.valueOf(Register.getRegValue(i)), i, 2);
         if(var19[i] != Register.getRegValue(i)) {
            if(!var21.booleanValue()) {
               var20.setSelectionInterval(i, i);
               var21 = Boolean.valueOf(true);
            } else {
               var20.addSelectionInterval(i, i);
            }
         }
      }

   }

   private void step_buttonActionPerformed(ActionEvent evt) {
      int currentPC = Register.getPC();
      int startPC = Instruction.startAddressOfInst;
      int index = (currentPC - startPC) / 4;
      Error err = this.mipssimulator.runCurrentInstruction();
      int new_index = (Register.getPC() - Instruction.startAddressOfInst) / 4;
      this.jTabbedPane3.setSelectedComponent(this.jPanel5);
      this.msgPane.setText("");
      if(new_index == index) {
         if(new_index >= this.mainTable.getModel().getRowCount()) {
            this.msgPane.setText("Program Completed Successfully!");
         }

         this.msgPane.setForeground(Color.green);
      }

      int mem_offset = Memory.startAddressOfDynamicData;
      DefaultTableModel dataModel = (DefaultTableModel)this.dataTable.getModel();

      int regModel;
      for(int selectionModel = 0; selectionModel < 15; ++selectionModel) {
         int[] rmodel = new int[6];
         int tempReg = mem_offset + 24 * selectionModel;

         for(regModel = 0; regModel < 6; ++regModel) {
            int first = tempReg + 4 * regModel;
            rmodel[regModel] = Memory.readWord(first);
            dataModel.setValueAt(Integer.valueOf(rmodel[regModel]), selectionModel, regModel + 1);
         }
      }

      ListSelectionModel var15 = this.mainTable.getSelectionModel();
      var15.setSelectionInterval(index, index);
      this.mainTable.scrollRectToVisible(new Rectangle(this.mainTable.getCellRect(index, 0, true)));
      DefaultTableModel var16 = (DefaultTableModel)this.jTable1.getModel();
      int[] var17 = new int[35];

      for(regModel = 0; regModel < 35; ++regModel) {
         var17[regModel] = ((Integer)var16.getValueAt(regModel, 2)).intValue();
      }

      ListSelectionModel var18 = this.jTable1.getSelectionModel();
      var18.setSelectionMode(2);
      Boolean var19 = Boolean.valueOf(false);

      for(int i = 0; i < 35; ++i) {
         var16.setValueAt(Integer.valueOf(Register.getRegValue(i)), i, 2);
         if(var17[i] != Register.getRegValue(i)) {
            if(!var19.booleanValue()) {
               var18.setSelectionInterval(i, i);
               var19 = Boolean.valueOf(true);
            } else {
               var18.addSelectionInterval(i, i);
            }
         }
      }

   }

   private void jButton1ActionPerformed(ActionEvent evt) {
      String str = "";
      int index = this.samplecodepane.getSelectedIndex();
      if(index == 0) {
         this.jTextPane1.selectAll();
         this.jTextPane1.copy();
      } else if(index == 1) {
         this.jTextPane2.selectAll();
         this.jTextPane2.copy();
      } else if(index == 2) {
         this.jTextPane5.selectAll();
         this.jTextPane5.copy();
      }

   }
}
