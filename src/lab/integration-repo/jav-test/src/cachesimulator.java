/*    1:     */ import java.awt.Choice;
/*    2:     */ import java.awt.Color;
/*    3:     */ import java.awt.Container;
/*    4:     */ import java.awt.Cursor;
/*    5:     */ import java.awt.Dimension;
/*    6:     */ import java.awt.EventQueue;
/*    7:     */ import java.awt.Graphics;
/*    8:     */ import java.awt.Label;
/*    9:     */ import java.awt.event.ActionEvent;
/*   10:     */ import java.awt.event.ActionListener;
/*   11:     */ import java.awt.event.ItemEvent;
/*   12:     */ import java.awt.event.ItemListener;
/*   13:     */ import java.util.Random; import javax.swing.*;
/*   14:     */ import javax.swing.BorderFactory;
/*   15:     */ import javax.swing.ButtonGroup;
/*   16:     */ import javax.swing.GroupLayout;
/*   17:     */ import javax.swing.GroupLayout.Alignment;
/*   18:     */ import javax.swing.GroupLayout.ParallelGroup;
/*   19:     */ import javax.swing.GroupLayout.SequentialGroup;
/*   20:     */ import javax.swing.JApplet;
/*   21:     */ import javax.swing.JButton;
/*   22:     */ import javax.swing.JLabel;
/*   23:     */ import javax.swing.JPanel;
/*   24:     */ import javax.swing.JRadioButton;
/*   25:     */ import javax.swing.JScrollPane;
/*   26:     */ import javax.swing.JTable;
/*   27:     */ import javax.swing.JTextArea;
/*   28:     */ import javax.swing.JTextField;
/*   29:     */ import javax.swing.LayoutStyle.ComponentPlacement;
/*   30:     */ import javax.swing.table.DefaultTableModel;
/*   31:     */ 
/*   32:     */ public class cachesimulator
/*   33:     */   extends JApplet
/*   34:     */ {
/*   35:  23 */   Architecture Ark = new Architecture();
/*   36:  24 */   int Paint_flag = 0;
/*   37:  25 */   int Read_write = 0;
/*   38:  26 */   int TextArea1_counter = 0;
/*   39:  26 */   int TextArea2_counter = 0;
/*   40:  27 */   int newly_added_cache = 16;
/*   41:  27 */   int newly_added_main = 16;
/*   42:     */   String str_tag;
/*   43:     */   private ButtonGroup buttonGroup1;
/*   44:     */   private ButtonGroup buttonGroup2;
/*   45:     */   private ButtonGroup buttonGroup3;
/*   46:     */   private ButtonGroup buttonGroup4;
/*   47:     */   private ButtonGroup buttonGroup5;
/*   48:     */   private Choice choice2;
/*   49:     */   private Choice choice3;
/*   50:     */   private Choice choice4;
/*   51:     */   private Choice choice5;
/*   52:     */   private JButton jButton1;
/*   53:     */   private JButton jButton2;
/*   54:     */   private JButton jButton3;
/*   55:     */   private JButton jButton4;
/*   56:     */   private JLabel jLabel3;
/*   57:     */   private JLabel jLabel4;
/*   58:     */   private JLabel jLabel5;
/*   59:     */   private JPanel jPanel1;
/*   60:     */   private JPanel jPanel10;
/*   61:     */   private JPanel jPanel11;
/*   62:     */   private JPanel jPanel12;
/*   63:     */   private JPanel jPanel13;
/*   64:     */   private JPanel jPanel14;
/*   65:     */   private JPanel jPanel2;
/*   66:     */   private JPanel jPanel3;
/*   67:     */   private JPanel jPanel4;
/*   68:     */   private JPanel jPanel5;
/*   69:     */   private JPanel jPanel6;
/*   70:     */   private JPanel jPanel7;
/*   71:     */   private JPanel jPanel8;
/*   72:     */   private JPanel jPanel9;
/*   73:     */   private JRadioButton jRadioButton1;
/*   74:     */   private JRadioButton jRadioButton10;
/*   75:     */   private JRadioButton jRadioButton11;
/*   76:     */   private JRadioButton jRadioButton12;
/*   77:     */   private JRadioButton jRadioButton13;
/*   78:     */   private JRadioButton jRadioButton14;
/*   79:     */   private JRadioButton jRadioButton2;
/*   80:     */   private JRadioButton jRadioButton3;
/*   81:     */   private JRadioButton jRadioButton4;
/*   82:     */   private JRadioButton jRadioButton5;
/*   83:     */   private JRadioButton jRadioButton6;
/*   84:     */   private JRadioButton jRadioButton7;
/*   85:     */   private JRadioButton jRadioButton8;
/*   86:     */   private JRadioButton jRadioButton9;
/*   87:     */   private JScrollPane jScrollPane1;
/*   88:     */   private JScrollPane jScrollPane2;
/*   89:     */   private JScrollPane jScrollPane3;
/*   90:     */   private JTable jTable1;
/*   91:     */   private JTextArea jTextArea1;
/*   92:     */   private JTextArea jTextArea2;
/*   93:     */   private JTextField jTextField1;
/*   94:     */   private JTextField jTextField2;
/*   95:     */   private JTextField jTextField3;
/*   96:     */   private Label label1;
/*   97:     */   private Label label2;
/*   98:     */   private Label label3;
/*   99:     */   private Label label4;
/*  100:     */   
/*  101:     */   public void init()
/*  102:     */   {
/*  103:     */     try
/*  104:     */     {
/*  105:  35 */       EventQueue.invokeAndWait(new Runnable()
/*  106:     */       {
/*  107:     */         public void run()
/*  108:     */         {
/*  109:  37 */           cachesimulator.this.initComponents();
/*  110:     */         }
/*  111:     */       });
/*  112:     */     }
/*  113:     */     catch (Exception localException)
/*  114:     */     {
/*  115:  41 */       localException.printStackTrace();
/*  116:     */     }
/*  117:     */   }
/*  118:     */   
/*  119:     */   public void initComponents()
/*  120:     */   {
/*  121:  54 */     this.buttonGroup1 = new ButtonGroup();
/*  122:  55 */     this.buttonGroup2 = new ButtonGroup();
/*  123:  56 */     this.buttonGroup3 = new ButtonGroup();
/*  124:  57 */     this.buttonGroup4 = new ButtonGroup();
/*  125:  58 */     this.buttonGroup5 = new ButtonGroup();
/*  126:  59 */     this.jPanel1 = new JPanel();
/*  127:  60 */     this.jLabel5 = new JLabel();
/*  128:  61 */     this.jPanel2 = new JPanel();
/*  129:  62 */     this.choice4 = new Choice();
/*  130:  63 */     this.label3 = new Label();
/*  131:  64 */     this.label2 = new Label();
/*  132:  65 */     this.label4 = new Label();
/*  133:  66 */     this.choice3 = new Choice();
/*  134:  67 */     this.choice2 = new Choice();
/*  135:  68 */     this.jPanel3 = new JPanel();
/*  136:  69 */     this.jRadioButton1 = new JRadioButton();
/*  137:  70 */     this.jRadioButton2 = new JRadioButton();
/*  138:  71 */     this.choice5 = new Choice();
/*  139:  72 */     this.label1 = new Label();
/*  140:  73 */     this.jRadioButton11 = new JRadioButton();
/*  141:  74 */     this.jPanel8 = new JPanel();
/*  142:  75 */     this.jRadioButton3 = new JRadioButton();
/*  143:  76 */     this.jRadioButton5 = new JRadioButton();
/*  144:  77 */     this.jRadioButton4 = new JRadioButton();
/*  145:  78 */     this.jPanel11 = new JPanel();
/*  146:  79 */     this.jRadioButton8 = new JRadioButton();
/*  147:  80 */     this.jRadioButton9 = new JRadioButton();
/*  148:  81 */     this.jRadioButton10 = new JRadioButton();
/*  149:  82 */     this.jButton2 = new JButton();
/*  150:  83 */     this.jPanel4 = new JPanel();
/*  151:  84 */     this.jPanel10 = new JPanel();
/*  152:  85 */     this.jTextField1 = new JTextField();
/*  153:  86 */     this.jRadioButton6 = new JRadioButton();
/*  154:  87 */     this.jRadioButton7 = new JRadioButton();
/*  155:  88 */     this.jButton3 = new JButton();
/*  156:  89 */     this.jPanel14 = new JPanel();
/*  157:  90 */     this.jRadioButton12 = new JRadioButton();
/*  158:  91 */     this.jRadioButton13 = new JRadioButton();
/*  159:  92 */     this.jRadioButton14 = new JRadioButton();
/*  160:  93 */     this.jPanel13 = new JPanel();
/*  161:  94 */     this.jButton4 = new JButton();
/*  162:  95 */     this.jButton1 = new JButton();
/*  163:  96 */     this.jScrollPane1 = new JScrollPane();
/*  164:  97 */     this.jTable1 = new JTable();
/*  165:  98 */     this.jPanel5 = new JPanel();
/*  166:  99 */     this.jPanel7 = new JPanel();
/*  167: 100 */     this.jScrollPane3 = new JScrollPane();
/*  168: 101 */     this.jTextArea2 = new JTextArea();
/*  169: 102 */     this.jPanel6 = new JPanel();
/*  170: 103 */     this.jPanel9 = new JPanel();
/*  171: 104 */     this.jTextField2 = new JTextField();
/*  172: 105 */     this.jTextField3 = new JTextField();
/*  173: 106 */     this.jLabel3 = new JLabel();
/*  174: 107 */     this.jLabel4 = new JLabel();
/*  175: 108 */     this.jPanel12 = new JPanel();
/*  176: 109 */     this.jScrollPane2 = new JScrollPane();
/*  177: 110 */     this.jTextArea1 = new JTextArea();
/*  178:     */     
/*  179: 112 */     setBackground(new Color(240, 240, 240));
/*  180:     */     
/*  181: 114 */     this.jPanel1.setBackground(new Color(204, 204, 255));
/*  182: 115 */     this.jPanel1.setBorder(BorderFactory.createTitledBorder(""));
/*  183: 116 */     this.jPanel1.setMaximumSize(new Dimension(177, 750));
/*  184: 117 */     this.jPanel1.setMinimumSize(new Dimension(177, 750));
/*  185:     */     
/*  186: 119 */     this.jLabel5.setText("         CACHE ORGANIZATION");
/*  187:     */     
/*  188: 121 */     this.jPanel2.setBackground(new Color(204, 204, 255));
/*  189: 122 */     this.jPanel2.setBorder(BorderFactory.createTitledBorder("Parameters"));
/*  190: 123 */     this.jPanel2.setVisible(true);
/*  191:     */     
/*  192: 125 */     this.choice4.add("2");
/*  193: 126 */     this.choice4.add("4");
/*  194: 127 */     this.choice4.add("8");
/*  195: 128 */     this.choice4.add("16");
/*  196: 129 */     this.choice4.add("32");
/*  197: 130 */     this.choice4.addItemListener(new ItemListener()
/*  198:     */     {
/*  199:     */       public void itemStateChanged(ItemEvent paramAnonymousItemEvent)
/*  200:     */       {
/*  201: 132 */         cachesimulator.this.choice4ItemStateChanged(paramAnonymousItemEvent);
/*  202:     */       }
/*  203: 135 */     });
/*  204: 136 */     this.label3.setBackground(new Color(204, 204, 255));
/*  205: 137 */     this.label3.setForeground(new Color(0, 0, 0));
/*  206: 138 */     this.label3.setText("Cache(Bytes)");
/*  207:     */     
/*  208: 140 */     this.label2.setForeground(new Color(0, 0, 0));
/*  209: 141 */     this.label2.setText("Main(Bytes)");
/*  210:     */     
/*  211: 143 */     this.label4.setForeground(new Color(0, 0, 0));
/*  212: 144 */     this.label4.setText("Block(Bytes)");
/*  213:     */     
/*  214: 146 */     this.choice3.add("2");
/*  215: 147 */     this.choice3.add("4");
/*  216: 148 */     this.choice3.add("8");
/*  217: 149 */     this.choice3.add("16");
/*  218: 150 */     this.choice3.add("32");
/*  219: 151 */     this.choice3.addItemListener(new ItemListener()
/*  220:     */     {
/*  221:     */       public void itemStateChanged(ItemEvent paramAnonymousItemEvent)
/*  222:     */       {
/*  223: 153 */         cachesimulator.this.choice3ItemStateChanged(paramAnonymousItemEvent);
/*  224:     */       }
/*  225: 156 */     });
/*  226: 157 */     this.choice2.removeAll();
/*  227: 158 */     this.choice2.add("4");
/*  228: 159 */     this.choice2.add("8");
/*  229: 160 */     this.choice2.add("16");
/*  230: 161 */     this.choice2.add("32");
/*  231:     */     
/*  232: 163 */     GroupLayout localGroupLayout1 = new GroupLayout(this.jPanel2);
/*  233: 164 */     this.jPanel2.setLayout(localGroupLayout1);
/*  234: 165 */     localGroupLayout1.setHorizontalGroup(localGroupLayout1.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout1.createSequentialGroup().addContainerGap().addGroup(localGroupLayout1.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.label2, -2, -1, -2).addComponent(this.label3, -2, -1, -2).addComponent(this.label4, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(localGroupLayout1.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.choice4, -1, -1, 32767).addComponent(this.choice2, -1, -1, 32767).addComponent(this.choice3, -1, 43, 32767)).addContainerGap()));
/*  235:     */     
/*  236:     */ 
/*  237:     */ 
/*  238:     */ 
/*  239:     */ 
/*  240:     */ 
/*  241:     */ 
/*  242:     */ 
/*  243:     */ 
/*  244:     */ 
/*  245:     */ 
/*  246:     */ 
/*  247:     */ 
/*  248:     */ 
/*  249: 180 */     localGroupLayout1.setVerticalGroup(localGroupLayout1.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, localGroupLayout1.createSequentialGroup().addContainerGap().addGroup(localGroupLayout1.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.label4, -2, -1, -2).addComponent(this.choice3, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(localGroupLayout1.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.label2, -2, -1, -2).addComponent(this.choice2, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(localGroupLayout1.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.label3, -2, -1, -2).addComponent(this.choice4, -2, -1, -2)).addContainerGap(32, 32767)));
/*  250:     */     
/*  251:     */ 
/*  252:     */ 
/*  253:     */ 
/*  254:     */ 
/*  255:     */ 
/*  256:     */ 
/*  257:     */ 
/*  258:     */ 
/*  259:     */ 
/*  260:     */ 
/*  261:     */ 
/*  262:     */ 
/*  263:     */ 
/*  264:     */ 
/*  265:     */ 
/*  266:     */ 
/*  267: 198 */     this.jPanel3.setBackground(new Color(204, 204, 255));
/*  268: 199 */     this.jPanel3.setBorder(BorderFactory.createTitledBorder("Mapping"));
/*  269: 200 */     this.jPanel3.setVisible(true);
/*  270:     */     
/*  271: 202 */     this.jRadioButton1.setBackground(new Color(204, 204, 255));
/*  272: 203 */     this.buttonGroup1.add(this.jRadioButton1);
/*  273: 204 */     this.jRadioButton1.setSelected(true);
/*  274: 205 */     this.jRadioButton1.setText("Direct Mapping");
/*  275:     */     
/*  276: 207 */     this.jRadioButton2.setBackground(new Color(204, 204, 255));
/*  277: 208 */     this.buttonGroup1.add(this.jRadioButton2);
/*  278: 209 */     this.jRadioButton2.setText("Set Associative");
/*  279:     */     
/*  280: 211 */     this.choice5.add("2");
/*  281: 212 */     this.choice5.add("4");
/*  282: 213 */     this.choice5.add("8");
/*  283:     */     
/*  284: 215 */     this.label1.setText("Set Size");
/*  285:     */     
/*  286: 217 */     this.jRadioButton11.setBackground(new Color(204, 204, 255));
/*  287: 218 */     this.buttonGroup1.add(this.jRadioButton11);
/*  288: 219 */     this.jRadioButton11.setText("Fully Associative");
/*  289: 220 */     this.jRadioButton11.addActionListener(new ActionListener()
/*  290:     */     {
/*  291:     */       public void actionPerformed(ActionEvent paramAnonymousActionEvent)
/*  292:     */       {
/*  293: 222 */         cachesimulator.this.jRadioButton11ActionPerformed(paramAnonymousActionEvent);
/*  294:     */       }
/*  295: 225 */     });
/*  296: 226 */     GroupLayout localGroupLayout2 = new GroupLayout(this.jPanel3);
/*  297: 227 */     this.jPanel3.setLayout(localGroupLayout2);
/*  298: 228 */     localGroupLayout2.setHorizontalGroup(localGroupLayout2.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout2.createSequentialGroup().addGroup(localGroupLayout2.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout2.createSequentialGroup().addGap(25, 25, 25).addComponent(this.choice5, -2, 51, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.label1, -1, 88, 32767)).addComponent(this.jRadioButton1).addComponent(this.jRadioButton11).addComponent(this.jRadioButton2)).addContainerGap()));
/*  299:     */     
/*  300:     */ 
/*  301:     */ 
/*  302:     */ 
/*  303:     */ 
/*  304:     */ 
/*  305:     */ 
/*  306:     */ 
/*  307:     */ 
/*  308:     */ 
/*  309:     */ 
/*  310:     */ 
/*  311:     */ 
/*  312: 242 */     localGroupLayout2.setVerticalGroup(localGroupLayout2.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout2.createSequentialGroup().addContainerGap().addComponent(this.jRadioButton1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jRadioButton11).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jRadioButton2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(localGroupLayout2.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.choice5, -2, -1, -2).addComponent(this.label1, -2, -1, -2)).addContainerGap(16, 32767)));
/*  313:     */     
/*  314:     */ 
/*  315:     */ 
/*  316:     */ 
/*  317:     */ 
/*  318:     */ 
/*  319:     */ 
/*  320:     */ 
/*  321:     */ 
/*  322:     */ 
/*  323:     */ 
/*  324:     */ 
/*  325:     */ 
/*  326:     */ 
/*  327:     */ 
/*  328: 258 */     this.jPanel8.setBackground(new Color(204, 204, 255));
/*  329: 259 */     this.jPanel8.setBorder(BorderFactory.createTitledBorder("Replacement Policy"));
/*  330: 260 */     this.jPanel8.setVisible(true);
/*  331:     */     
/*  332: 262 */     this.jRadioButton3.setBackground(new Color(204, 204, 255));
/*  333: 263 */     this.buttonGroup2.add(this.jRadioButton3);
/*  334: 264 */     this.jRadioButton3.setSelected(true);
/*  335: 265 */     this.jRadioButton3.setText("LRU");
/*  336:     */     
/*  337: 267 */     this.jRadioButton5.setBackground(new Color(204, 204, 255));
/*  338: 268 */     this.buttonGroup2.add(this.jRadioButton5);
/*  339: 269 */     this.jRadioButton5.setText("FIFO");
/*  340:     */     
/*  341: 271 */     this.jRadioButton4.setBackground(new Color(204, 204, 255));
/*  342: 272 */     this.buttonGroup2.add(this.jRadioButton4);
/*  343: 273 */     this.jRadioButton4.setText("Random");
/*  344:     */     
/*  345: 275 */     GroupLayout localGroupLayout3 = new GroupLayout(this.jPanel8);
/*  346: 276 */     this.jPanel8.setLayout(localGroupLayout3);
/*  347: 277 */     localGroupLayout3.setHorizontalGroup(localGroupLayout3.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout3.createSequentialGroup().addGroup(localGroupLayout3.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.jRadioButton4, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.jRadioButton5, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.jRadioButton3, GroupLayout.Alignment.LEADING, -2, 88, -2)).addContainerGap(79, 32767)));
/*  348:     */     
/*  349:     */ 
/*  350:     */ 
/*  351:     */ 
/*  352:     */ 
/*  353:     */ 
/*  354:     */ 
/*  355:     */ 
/*  356: 286 */     localGroupLayout3.setVerticalGroup(localGroupLayout3.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout3.createSequentialGroup().addGap(17, 17, 17).addComponent(this.jRadioButton3).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jRadioButton5).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jRadioButton4).addContainerGap(36, 32767)));
/*  357:     */     
/*  358:     */ 
/*  359:     */ 
/*  360:     */ 
/*  361:     */ 
/*  362:     */ 
/*  363:     */ 
/*  364:     */ 
/*  365:     */ 
/*  366:     */ 
/*  367:     */ 
/*  368: 298 */     this.jPanel11.setBackground(new Color(204, 204, 255));
/*  369: 299 */     this.jPanel11.setBorder(BorderFactory.createTitledBorder("Write Policy"));
/*  370: 300 */     this.jPanel11.setMaximumSize(new Dimension(85, 96));
/*  371: 301 */     this.jPanel11.setMinimumSize(new Dimension(85, 96));
/*  372:     */     
/*  373: 303 */     this.jRadioButton8.setBackground(new Color(204, 204, 255));
/*  374: 304 */     this.buttonGroup3.add(this.jRadioButton8);
/*  375: 305 */     this.jRadioButton8.setSelected(true);
/*  376: 306 */     this.jRadioButton8.setText("Write-back");
/*  377:     */     
/*  378: 308 */     this.jRadioButton9.setBackground(new Color(204, 204, 255));
/*  379: 309 */     this.buttonGroup3.add(this.jRadioButton9);
/*  380: 310 */     this.jRadioButton9.setText("Write-through Allocate");
/*  381:     */     
/*  382: 312 */     this.jRadioButton10.setBackground(new Color(204, 204, 255));
/*  383: 313 */     this.buttonGroup3.add(this.jRadioButton10);
/*  384: 314 */     this.jRadioButton10.setText("Write-through No Allocate");
/*  385:     */     
/*  386: 316 */     GroupLayout localGroupLayout4 = new GroupLayout(this.jPanel11);
/*  387: 317 */     this.jPanel11.setLayout(localGroupLayout4);
/*  388: 318 */     localGroupLayout4.setHorizontalGroup(localGroupLayout4.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout4.createSequentialGroup().addContainerGap().addGroup(localGroupLayout4.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jRadioButton9, -1, 160, 32767).addComponent(this.jRadioButton10, GroupLayout.Alignment.TRAILING, -1, 160, 32767).addComponent(this.jRadioButton8, -1, 160, 32767)).addContainerGap()));
/*  389:     */     
/*  390:     */ 
/*  391:     */ 
/*  392:     */ 
/*  393:     */ 
/*  394:     */ 
/*  395:     */ 
/*  396:     */ 
/*  397:     */ 
/*  398: 328 */     localGroupLayout4.setVerticalGroup(localGroupLayout4.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout4.createSequentialGroup().addContainerGap().addComponent(this.jRadioButton8).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jRadioButton9).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jRadioButton10).addContainerGap(43, 32767)));
/*  399:     */     
/*  400:     */ 
/*  401:     */ 
/*  402:     */ 
/*  403:     */ 
/*  404:     */ 
/*  405:     */ 
/*  406:     */ 
/*  407:     */ 
/*  408:     */ 
/*  409:     */ 
/*  410: 340 */     this.jButton2.setText("Submit");
/*  411: 341 */     this.jButton2.addActionListener(new ActionListener()
/*  412:     */     {
/*  413:     */       public void actionPerformed(ActionEvent paramAnonymousActionEvent)
/*  414:     */       {
/*  415: 343 */         cachesimulator.this.jButton2ActionPerformed(paramAnonymousActionEvent);
/*  416:     */       }
/*  417: 346 */     });
/*  418: 347 */     GroupLayout localGroupLayout5 = new GroupLayout(this.jPanel1);
/*  419: 348 */     this.jPanel1.setLayout(localGroupLayout5);
/*  420: 349 */     localGroupLayout5.setHorizontalGroup(localGroupLayout5.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout5.createSequentialGroup().addContainerGap(60, 32767).addComponent(this.jPanel2, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel3, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel8, -2, -1, -2).addGap(6, 6, 6).addComponent(this.jPanel11, -2, -1, -2).addGap(57, 57, 57)).addGroup(localGroupLayout5.createSequentialGroup().addGap(338, 338, 338).addComponent(this.jLabel5, -2, 170, -2).addContainerGap(349, 32767)).addGroup(localGroupLayout5.createSequentialGroup().addGap(329, 329, 329).addComponent(this.jButton2, -2, 186, -2).addContainerGap(342, 32767)));
/*  421:     */     
/*  422:     */ 
/*  423:     */ 
/*  424:     */ 
/*  425:     */ 
/*  426:     */ 
/*  427:     */ 
/*  428:     */ 
/*  429:     */ 
/*  430:     */ 
/*  431:     */ 
/*  432:     */ 
/*  433:     */ 
/*  434:     */ 
/*  435:     */ 
/*  436:     */ 
/*  437:     */ 
/*  438:     */ 
/*  439:     */ 
/*  440:     */ 
/*  441: 370 */     localGroupLayout5.setVerticalGroup(localGroupLayout5.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout5.createSequentialGroup().addComponent(this.jLabel5).addGap(11, 11, 11).addGroup(localGroupLayout5.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel11, GroupLayout.Alignment.TRAILING, -2, -1, -2).addComponent(this.jPanel2, -2, -1, -2).addComponent(this.jPanel3, -2, -1, -2).addComponent(this.jPanel8, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 7, 32767).addComponent(this.jButton2).addContainerGap()));
/*  442:     */     
/*  443:     */ 
/*  444:     */ 
/*  445:     */ 
/*  446:     */ 
/*  447:     */ 
/*  448:     */ 
/*  449:     */ 
/*  450:     */ 
/*  451:     */ 
/*  452:     */ 
/*  453:     */ 
/*  454:     */ 
/*  455:     */ 
/*  456: 385 */     this.jPanel4.setBackground(new Color(204, 204, 255));
/*  457: 386 */     this.jPanel4.setBorder(BorderFactory.createTitledBorder("Address"));
/*  458: 387 */     this.jPanel4.setMaximumSize(new Dimension(668, 134));
/*  459: 388 */     this.jPanel4.setMinimumSize(new Dimension(668, 134));
/*  460: 389 */     this.jPanel4.setVisible(true);
/*  461:     */     
/*  462: 391 */     this.jPanel10.setBackground(new Color(204, 204, 255));
/*  463: 392 */     this.jPanel10.setBorder(BorderFactory.createTitledBorder("Manual Address Selection"));
/*  464:     */     
/*  465: 394 */     this.jTextField1.addActionListener(new ActionListener()
/*  466:     */     {
/*  467:     */       public void actionPerformed(ActionEvent paramAnonymousActionEvent)
/*  468:     */       {
/*  469: 396 */         cachesimulator.this.jTextField1ActionPerformed(paramAnonymousActionEvent);
/*  470:     */       }
/*  471: 399 */     });
/*  472: 400 */     this.jRadioButton6.setBackground(new Color(204, 204, 255));
/*  473: 401 */     this.buttonGroup4.add(this.jRadioButton6);
/*  474: 402 */     this.jRadioButton6.setSelected(true);
/*  475: 403 */     this.jRadioButton6.setText("Read");
/*  476:     */     
/*  477: 405 */     this.jRadioButton7.setBackground(new Color(204, 204, 255));
/*  478: 406 */     this.buttonGroup4.add(this.jRadioButton7);
/*  479: 407 */     this.jRadioButton7.setText("Write");
/*  480:     */     
/*  481: 409 */     this.jButton3.setText("Map");
/*  482: 410 */     this.jButton3.addActionListener(new ActionListener()
/*  483:     */     {
/*  484:     */       public void actionPerformed(ActionEvent paramAnonymousActionEvent)
/*  485:     */       {
/*  486: 412 */         cachesimulator.this.jButton3ActionPerformed(paramAnonymousActionEvent);
/*  487:     */       }
/*  488: 415 */     });
/*  489: 416 */     this.jPanel14.setBackground(new Color(204, 204, 255));
/*  490: 417 */     this.jPanel14.setBorder(BorderFactory.createTitledBorder("Address Type"));
/*  491:     */     
/*  492: 419 */     this.jRadioButton12.setBackground(new Color(204, 204, 255));
/*  493: 420 */     this.buttonGroup5.add(this.jRadioButton12);
/*  494: 421 */     this.jRadioButton12.setSelected(true);
/*  495: 422 */     this.jRadioButton12.setText("Decimal");
/*  496: 423 */     this.jRadioButton12.addActionListener(new ActionListener()
/*  497:     */     {
/*  498:     */       public void actionPerformed(ActionEvent paramAnonymousActionEvent)
/*  499:     */       {
/*  500: 425 */         cachesimulator.this.jRadioButton12ActionPerformed(paramAnonymousActionEvent);
/*  501:     */       }
/*  502: 428 */     });
/*  503: 429 */     this.jRadioButton13.setBackground(new Color(204, 204, 255));
/*  504: 430 */     this.buttonGroup5.add(this.jRadioButton13);
/*  505: 431 */     this.jRadioButton13.setText("Hexa Decimal");
/*  506:     */     
/*  507: 433 */     this.jRadioButton14.setBackground(new Color(204, 204, 255));
/*  508: 434 */     this.buttonGroup5.add(this.jRadioButton14);
/*  509: 435 */     this.jRadioButton14.setText("Binary");
/*  510:     */     
/*  511: 437 */     GroupLayout localGroupLayout6 = new GroupLayout(this.jPanel14);
/*  512: 438 */     this.jPanel14.setLayout(localGroupLayout6);
/*  513: 439 */     localGroupLayout6.setHorizontalGroup(localGroupLayout6.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout6.createSequentialGroup().addContainerGap().addGroup(localGroupLayout6.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jRadioButton12).addComponent(this.jRadioButton13).addComponent(this.jRadioButton14)).addContainerGap(44, 32767)));
/*  514:     */     
/*  515:     */ 
/*  516:     */ 
/*  517:     */ 
/*  518:     */ 
/*  519:     */ 
/*  520:     */ 
/*  521:     */ 
/*  522:     */ 
/*  523: 449 */     localGroupLayout6.setVerticalGroup(localGroupLayout6.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout6.createSequentialGroup().addComponent(this.jRadioButton12).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jRadioButton13).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jRadioButton14)));
/*  524:     */     
/*  525:     */ 
/*  526:     */ 
/*  527:     */ 
/*  528:     */ 
/*  529:     */ 
/*  530:     */ 
/*  531:     */ 
/*  532:     */ 
/*  533: 459 */     GroupLayout localGroupLayout7 = new GroupLayout(this.jPanel10);
/*  534: 460 */     this.jPanel10.setLayout(localGroupLayout7);
/*  535: 461 */     localGroupLayout7.setHorizontalGroup(localGroupLayout7.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout7.createSequentialGroup().addGap(18, 18, 18).addGroup(localGroupLayout7.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout7.createSequentialGroup().addGap(21, 21, 21).addComponent(this.jButton3, -2, 81, -2)).addGroup(localGroupLayout7.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.jPanel14, GroupLayout.Alignment.LEADING, -2, -1, -2).addComponent(this.jTextField1, GroupLayout.Alignment.LEADING, -1, 151, 32767).addGroup(GroupLayout.Alignment.LEADING, localGroupLayout7.createSequentialGroup().addComponent(this.jRadioButton6).addGap(18, 18, 18).addComponent(this.jRadioButton7)))).addContainerGap(19, 32767)));
/*  536:     */     
/*  537:     */ 
/*  538:     */ 
/*  539:     */ 
/*  540:     */ 
/*  541:     */ 
/*  542:     */ 
/*  543:     */ 
/*  544:     */ 
/*  545:     */ 
/*  546:     */ 
/*  547:     */ 
/*  548:     */ 
/*  549:     */ 
/*  550:     */ 
/*  551:     */ 
/*  552: 478 */     localGroupLayout7.setVerticalGroup(localGroupLayout7.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout7.createSequentialGroup().addContainerGap().addComponent(this.jTextField1, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel14, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(localGroupLayout7.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jRadioButton6).addComponent(this.jRadioButton7)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jButton3).addContainerGap(23, 32767)));
/*  553:     */     
/*  554:     */ 
/*  555:     */ 
/*  556:     */ 
/*  557:     */ 
/*  558:     */ 
/*  559:     */ 
/*  560:     */ 
/*  561:     */ 
/*  562:     */ 
/*  563:     */ 
/*  564:     */ 
/*  565:     */ 
/*  566:     */ 
/*  567:     */ 
/*  568: 494 */     this.jPanel13.setBackground(new Color(204, 204, 255));
/*  569: 495 */     this.jPanel13.setBorder(BorderFactory.createTitledBorder("Auto Selection"));
/*  570:     */     
/*  571: 497 */     this.jButton4.setText("Random Read");
/*  572: 498 */     this.jButton4.addActionListener(new ActionListener()
/*  573:     */     {
/*  574:     */       public void actionPerformed(ActionEvent paramAnonymousActionEvent)
/*  575:     */       {
/*  576: 500 */         cachesimulator.this.jButton4ActionPerformed(paramAnonymousActionEvent);
/*  577:     */       }
/*  578: 503 */     });
/*  579: 504 */     this.jButton1.setText("Random Write");
/*  580: 505 */     this.jButton1.addActionListener(new ActionListener()
/*  581:     */     {
/*  582:     */       public void actionPerformed(ActionEvent paramAnonymousActionEvent)
/*  583:     */       {
/*  584: 507 */         cachesimulator.this.jButton1ActionPerformed(paramAnonymousActionEvent);
/*  585:     */       }
/*  586: 510 */     });
/*  587: 511 */     GroupLayout localGroupLayout8 = new GroupLayout(this.jPanel13);
/*  588: 512 */     this.jPanel13.setLayout(localGroupLayout8);
/*  589: 513 */     localGroupLayout8.setHorizontalGroup(localGroupLayout8.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout8.createSequentialGroup().addGap(31, 31, 31).addGroup(localGroupLayout8.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.jButton1, GroupLayout.Alignment.LEADING, -1, 124, 32767).addComponent(this.jButton4, GroupLayout.Alignment.LEADING, -1, 124, 32767)).addGap(33, 33, 33)));
/*  590:     */     
/*  591:     */ 
/*  592:     */ 
/*  593:     */ 
/*  594:     */ 
/*  595:     */ 
/*  596:     */ 
/*  597:     */ 
/*  598: 522 */     localGroupLayout8.setVerticalGroup(localGroupLayout8.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout8.createSequentialGroup().addGap(19, 19, 19).addComponent(this.jButton4).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jButton1).addContainerGap(31, 32767)));
/*  599:     */     
/*  600:     */ 
/*  601:     */ 
/*  602:     */ 
/*  603:     */ 
/*  604:     */ 
/*  605:     */ 
/*  606:     */ 
/*  607:     */ 
/*  608: 532 */     this.jTable1.setBackground(new Color(204, 204, 255));
/*  609: 533 */     this.jTable1.setModel(new DefaultTableModel(new Object[][] { { null, null, null } }, new String[] { "Tag Bits", "Index Bits", "Offset bits" }));
/*  610:     */     
/*  611:     */ 
/*  612:     */ 
/*  613:     */ 
/*  614:     */ 
/*  615:     */ 
/*  616:     */ 
/*  617: 541 */     this.jScrollPane1.setViewportView(this.jTable1);
/*  618:     */     
/*  619: 543 */     GroupLayout localGroupLayout9 = new GroupLayout(this.jPanel4);
/*  620: 544 */     this.jPanel4.setLayout(localGroupLayout9);
/*  621: 545 */     localGroupLayout9.setHorizontalGroup(localGroupLayout9.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout9.createSequentialGroup().addContainerGap().addGroup(localGroupLayout9.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane1, -1, 200, 32767).addComponent(this.jPanel13, -2, -1, -2).addComponent(this.jPanel10, -2, -1, -2)).addContainerGap()));
/*  622:     */     
/*  623:     */ 
/*  624:     */ 
/*  625:     */ 
/*  626:     */ 
/*  627:     */ 
/*  628:     */ 
/*  629:     */ 
/*  630:     */ 
/*  631: 555 */     localGroupLayout9.setVerticalGroup(localGroupLayout9.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout9.createSequentialGroup().addComponent(this.jPanel10, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel13, -2, -1, -2).addGap(18, 18, 18).addComponent(this.jScrollPane1, -2, 43, -2).addContainerGap(114, 32767)));
/*  632:     */     
/*  633:     */ 
/*  634:     */ 
/*  635:     */ 
/*  636:     */ 
/*  637:     */ 
/*  638:     */ 
/*  639:     */ 
/*  640:     */ 
/*  641:     */ 
/*  642: 566 */     this.jPanel5.setBackground(new Color(204, 204, 255));
/*  643: 567 */     this.jPanel5.setBorder(BorderFactory.createTitledBorder("Mapping"));
/*  644: 568 */     this.jPanel5.setCursor(new Cursor(0));
/*  645: 569 */     this.jPanel5.setDebugGraphicsOptions(-1);
/*  646: 570 */     this.jPanel5.setMaximumSize(new Dimension(514, 502));
/*  647: 571 */     this.jPanel5.setMinimumSize(new Dimension(514, 502));
/*  648: 572 */     this.jPanel5.setVisible(true);
/*  649:     */     
/*  650: 574 */     this.jPanel7.setBackground(new Color(204, 204, 255));
/*  651: 575 */     this.jPanel7.setBorder(BorderFactory.createTitledBorder("explanation"));
/*  652: 576 */     this.jPanel7.setMaximumSize(new Dimension(482, 476));
/*  653: 577 */     this.jPanel7.setMinimumSize(new Dimension(482, 476));
/*  654:     */     
/*  655: 579 */     this.jTextArea2.setColumns(20);
/*  656: 580 */     this.jTextArea2.setRows(5);
/*  657: 581 */     this.jScrollPane3.setViewportView(this.jTextArea2);
/*  658:     */     
/*  659: 583 */     GroupLayout localGroupLayout10 = new GroupLayout(this.jPanel7);
/*  660: 584 */     this.jPanel7.setLayout(localGroupLayout10);
/*  661: 585 */     localGroupLayout10.setHorizontalGroup(localGroupLayout10.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane3, -1, 440, 32767));
/*  662:     */     
/*  663:     */ 
/*  664:     */ 
/*  665: 589 */     localGroupLayout10.setVerticalGroup(localGroupLayout10.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane3, -1, 83, 32767));
/*  666:     */     
/*  667:     */ 
/*  668:     */ 
/*  669:     */ 
/*  670: 594 */     GroupLayout localGroupLayout11 = new GroupLayout(this.jPanel5);
/*  671: 595 */     this.jPanel5.setLayout(localGroupLayout11);
/*  672: 596 */     localGroupLayout11.setHorizontalGroup(localGroupLayout11.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout11.createSequentialGroup().addContainerGap().addComponent(this.jPanel7, -2, 452, -2).addContainerGap(-1, 32767)));
/*  673:     */     
/*  674:     */ 
/*  675:     */ 
/*  676:     */ 
/*  677:     */ 
/*  678:     */ 
/*  679: 603 */     localGroupLayout11.setVerticalGroup(localGroupLayout11.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout11.createSequentialGroup().addComponent(this.jPanel7, -2, 110, -2).addContainerGap(448, 32767)));
/*  680:     */     
/*  681:     */ 
/*  682:     */ 
/*  683:     */ 
/*  684:     */ 
/*  685:     */ 
/*  686: 610 */     this.jPanel6.setBackground(new Color(204, 204, 255));
/*  687: 611 */     this.jPanel6.setMaximumSize(new Dimension(138, 502));
/*  688: 612 */     this.jPanel6.setMinimumSize(new Dimension(138, 502));
/*  689:     */     
/*  690: 614 */     this.jPanel9.setBackground(new Color(204, 204, 255));
/*  691: 615 */     this.jPanel9.setBorder(BorderFactory.createTitledBorder("Cache miss/hit"));
/*  692:     */     
/*  693: 617 */     this.jTextField2.setEditable(false);
/*  694: 618 */     this.jTextField2.setText("0");
/*  695:     */     
/*  696: 620 */     this.jTextField3.setEditable(false);
/*  697: 621 */     this.jTextField3.setText("0");
/*  698:     */     
/*  699: 623 */     this.jLabel3.setText("Cache hits");
/*  700:     */     
/*  701: 625 */     this.jLabel4.setText("Cache misses");
/*  702:     */     
/*  703: 627 */     GroupLayout localGroupLayout12 = new GroupLayout(this.jPanel9);
/*  704: 628 */     this.jPanel9.setLayout(localGroupLayout12);
/*  705: 629 */     localGroupLayout12.setHorizontalGroup(localGroupLayout12.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout12.createSequentialGroup().addContainerGap().addGroup(localGroupLayout12.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout12.createSequentialGroup().addComponent(this.jLabel3).addContainerGap(50, 32767)).addGroup(GroupLayout.Alignment.TRAILING, localGroupLayout12.createSequentialGroup().addComponent(this.jTextField2, -2, 62, -2).addContainerGap()).addGroup(localGroupLayout12.createSequentialGroup().addComponent(this.jLabel4).addContainerGap(36, 32767)).addGroup(GroupLayout.Alignment.TRAILING, localGroupLayout12.createSequentialGroup().addComponent(this.jTextField3, -2, 62, -2).addContainerGap()))));
/*  706:     */     
/*  707:     */ 
/*  708:     */ 
/*  709:     */ 
/*  710:     */ 
/*  711:     */ 
/*  712:     */ 
/*  713:     */ 
/*  714:     */ 
/*  715:     */ 
/*  716:     */ 
/*  717:     */ 
/*  718:     */ 
/*  719:     */ 
/*  720:     */ 
/*  721:     */ 
/*  722:     */ 
/*  723: 647 */     localGroupLayout12.setVerticalGroup(localGroupLayout12.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout12.createSequentialGroup().addComponent(this.jLabel3).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jTextField2, -2, -1, -2).addGap(18, 18, 18).addComponent(this.jLabel4).addGap(8, 8, 8).addComponent(this.jTextField3, -2, -1, -2).addContainerGap(-1, 32767)));
/*  724:     */     
/*  725:     */ 
/*  726:     */ 
/*  727:     */ 
/*  728:     */ 
/*  729:     */ 
/*  730:     */ 
/*  731:     */ 
/*  732:     */ 
/*  733:     */ 
/*  734:     */ 
/*  735:     */ 
/*  736: 660 */     this.jPanel12.setBackground(new Color(204, 204, 255));
/*  737: 661 */     this.jPanel12.setBorder(BorderFactory.createTitledBorder("Given Adresses"));
/*  738:     */     
/*  739: 663 */     this.jTextArea1.setColumns(20);
/*  740: 664 */     this.jTextArea1.setEditable(false);
/*  741: 665 */     this.jTextArea1.setRows(5);
/*  742: 666 */     this.jScrollPane2.setViewportView(this.jTextArea1);
/*  743:     */     
/*  744: 668 */     GroupLayout localGroupLayout13 = new GroupLayout(this.jPanel12);
/*  745: 669 */     this.jPanel12.setLayout(localGroupLayout13);
/*  746: 670 */     localGroupLayout13.setHorizontalGroup(localGroupLayout13.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane2, -1, 110, 32767));
/*  747:     */     
/*  748:     */ 
/*  749:     */ 
/*  750: 674 */     localGroupLayout13.setVerticalGroup(localGroupLayout13.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout13.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane2, -1, 286, 32767).addContainerGap()));
/*  751:     */     
/*  752:     */ 
/*  753:     */ 
/*  754:     */ 
/*  755:     */ 
/*  756:     */ 
/*  757:     */ 
/*  758: 682 */     GroupLayout localGroupLayout14 = new GroupLayout(this.jPanel6);
/*  759: 683 */     this.jPanel6.setLayout(localGroupLayout14);
/*  760: 684 */     localGroupLayout14.setHorizontalGroup(localGroupLayout14.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout14.createSequentialGroup().addContainerGap().addGroup(localGroupLayout14.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.jPanel12, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.jPanel9, GroupLayout.Alignment.LEADING, -1, -1, 32767)).addContainerGap(-1, 32767)));
/*  761:     */     
/*  762:     */ 
/*  763:     */ 
/*  764:     */ 
/*  765:     */ 
/*  766:     */ 
/*  767:     */ 
/*  768:     */ 
/*  769: 693 */     localGroupLayout14.setVerticalGroup(localGroupLayout14.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout14.createSequentialGroup().addContainerGap().addComponent(this.jPanel9, -2, -1, -2).addGap(18, 18, 18).addComponent(this.jPanel12, -2, -1, -2).addContainerGap(83, 32767)));
/*  770:     */     
/*  771:     */ 
/*  772:     */ 
/*  773:     */ 
/*  774:     */ 
/*  775:     */ 
/*  776:     */ 
/*  777:     */ 
/*  778:     */ 
/*  779: 703 */     GroupLayout localGroupLayout15 = new GroupLayout(getContentPane());
/*  780: 704 */     getContentPane().setLayout(localGroupLayout15);
/*  781: 705 */     localGroupLayout15.setHorizontalGroup(localGroupLayout15.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout15.createSequentialGroup().addContainerGap().addGroup(localGroupLayout15.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.jPanel1, -1, -1, 32767).addGroup(GroupLayout.Alignment.LEADING, localGroupLayout15.createSequentialGroup().addComponent(this.jPanel4, -2, 231, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel5, -2, 484, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel6, -1, -1, 32767))).addContainerGap(23, 32767)));
/*  782:     */     
/*  783:     */ 
/*  784:     */ 
/*  785:     */ 
/*  786:     */ 
/*  787:     */ 
/*  788:     */ 
/*  789:     */ 
/*  790:     */ 
/*  791:     */ 
/*  792:     */ 
/*  793:     */ 
/*  794:     */ 
/*  795: 719 */     localGroupLayout15.setVerticalGroup(localGroupLayout15.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout15.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -2, 228, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(localGroupLayout15.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel4, -2, -1, -2).addComponent(this.jPanel5, -2, -1, -2).addComponent(this.jPanel6, -2, -1, -2)).addGap(349, 349, 349)));
/*  796:     */   }
/*  797:     */   
/*  798:     */   private void choice4ItemStateChanged(ItemEvent paramItemEvent) {}
/*  799:     */   
/*  800:     */   private void choice3ItemStateChanged(ItemEvent paramItemEvent)
/*  801:     */   {
/*  802: 738 */     if (this.choice3.getSelectedIndex() == 0)
/*  803:     */     {
/*  804: 740 */       this.choice2.removeAll();
/*  805: 741 */       this.choice2.add("4");
/*  806: 742 */       this.choice2.add("8");
/*  807: 743 */       this.choice2.add("16");
/*  808: 744 */       this.choice2.add("32");
/*  809: 745 */       this.choice4.removeAll();
/*  810: 746 */       this.choice4.add("4");
/*  811: 747 */       this.choice4.add("8");
/*  812: 748 */       this.choice4.add("16");
/*  813: 749 */       this.choice4.add("32");
/*  814:     */     }
/*  815: 750 */     else if (this.choice3.getSelectedIndex() == 1)
/*  816:     */     {
/*  817: 751 */       this.choice2.removeAll();
/*  818: 752 */       this.choice2.add("8");
/*  819: 753 */       this.choice2.add("16");
/*  820: 754 */       this.choice2.add("32");
/*  821: 755 */       this.choice2.add("64");
/*  822: 756 */       this.choice4.removeAll();
/*  823: 757 */       this.choice4.add("8");
/*  824: 758 */       this.choice4.add("16");
/*  825: 759 */       this.choice4.add("32");
/*  826: 760 */       this.choice4.add("64");
/*  827:     */     }
/*  828: 761 */     else if (this.choice3.getSelectedIndex() == 2)
/*  829:     */     {
/*  830: 762 */       this.choice2.removeAll();
/*  831: 763 */       this.choice2.add("16");
/*  832: 764 */       this.choice2.add("32");
/*  833: 765 */       this.choice2.add("64");
/*  834: 766 */       this.choice2.add("128");
/*  835: 767 */       this.choice4.removeAll();
/*  836: 768 */       this.choice4.add("16");
/*  837: 769 */       this.choice4.add("32");
/*  838: 770 */       this.choice4.add("64");
/*  839: 771 */       this.choice4.add("128");
/*  840:     */     }
/*  841: 772 */     else if (this.choice3.getSelectedIndex() == 3)
/*  842:     */     {
/*  843: 773 */       this.choice2.removeAll();
/*  844: 774 */       this.choice2.add("32");
/*  845: 775 */       this.choice2.add("64");
/*  846: 776 */       this.choice2.add("128");
/*  847: 777 */       this.choice2.add("256");
/*  848: 778 */       this.choice4.removeAll();
/*  849: 779 */       this.choice4.add("32");
/*  850: 780 */       this.choice4.add("64");
/*  851: 781 */       this.choice4.add("128");
/*  852: 782 */       this.choice4.add("256");
/*  853:     */     }
/*  854: 783 */     else if (this.choice3.getSelectedIndex() == 4)
/*  855:     */     {
/*  856: 784 */       this.choice2.removeAll();
/*  857: 785 */       this.choice2.add("64");
/*  858: 786 */       this.choice2.add("128");
/*  859: 787 */       this.choice2.add("256");
/*  860: 788 */       this.choice2.add("512");
/*  861: 789 */       this.choice4.removeAll();
/*  862: 790 */       this.choice4.add("64");
/*  863: 791 */       this.choice4.add("128");
/*  864: 792 */       this.choice4.add("256");
/*  865: 793 */       this.choice4.add("512");
/*  866:     */     }
/*  867:     */   }
/*  868:     */   
/*  869:     */   private void jButton2ActionPerformed(ActionEvent paramActionEvent)
/*  870:     */   {
/*  871: 798 */     values_ini();this.jTextArea2.setText("");this.jTextArea1.setText("");this.TextArea1_counter = 0;this.TextArea2_counter = 1;
/*  872: 799 */     this.jTextField3.setText("0");this.jTextField2.setText("0");
/*  873:     */   }
/*  874:     */   
/*  875:     */   private void jButton3ActionPerformed(ActionEvent paramActionEvent)
/*  876:     */   {
/*  877: 803 */     String str1 = this.jTextField1.getText();String str2 = null;
/*  878: 804 */     str2 = Any_to_binary(str1);
/*  879: 806 */     if ((this.jRadioButton6.isSelected()) && (str2 != null))
/*  880:     */     {
/*  881: 808 */       this.Read_write = 1;
/*  882:     */       
/*  883: 810 */       Fill_table(str2);
/*  884: 812 */       if (this.Ark.Mapping == 1) {
/*  885: 813 */         Map_direct(str2);
/*  886: 814 */       } else if (this.Ark.Mapping == 2) {
/*  887: 815 */         Map_setassociate(str2);
/*  888:     */       }
/*  889:     */     }
/*  890: 817 */     else if ((this.jRadioButton7.isSelected()) && (str2 != null))
/*  891:     */     {
/*  892: 819 */       this.Read_write = 2;
/*  893:     */       
/*  894: 821 */       Fill_table(str2);
/*  895: 823 */       if (this.Ark.Write_policy == 1)
/*  896:     */       {
/*  897: 825 */         if (this.Ark.Mapping == 1) {
/*  898: 826 */           writeback_direct(str2);
/*  899: 827 */         } else if (this.Ark.Mapping == 2) {
/*  900: 828 */           writeback_setassociate(str2);
/*  901:     */         }
/*  902:     */       }
/*  903: 830 */       else if (this.Ark.Write_policy == 2)
/*  904:     */       {
/*  905: 832 */         if (this.Ark.Mapping == 1) {
/*  906: 833 */           writethrough_direct(str2);
/*  907: 834 */         } else if (this.Ark.Mapping == 2) {
/*  908: 835 */           writethrough_setassociate(str2);
/*  909:     */         }
/*  910:     */       }
/*  911: 837 */       else if (this.Ark.Write_policy == 3) {
/*  912: 839 */         if (this.Ark.Mapping == 1) {
/*  913: 840 */           writethroughno_direct(str2);
/*  914: 841 */         } else if (this.Ark.Mapping == 2) {
/*  915: 842 */           writethroughno_setassociate(str2);
/*  916:     */         }
/*  917:     */       }
/*  918:     */     }
/*  919:     */   }
/*  920:     */   
/*  921:     */   private void jButton4ActionPerformed(ActionEvent paramActionEvent)
/*  922:     */   {
/*  923: 848 */     this.Read_write = 1;this.newly_added_cache = 16;this.newly_added_main = 16;
/*  924: 849 */     Random localRandom = new Random();
/*  925: 850 */     int i = localRandom.nextInt(10000);
/*  926: 851 */     int j = i % this.Ark.MainMemory;
/*  927: 852 */     String str = Integer.toBinaryString(j);
/*  928: 853 */     this.jTextField1.setText(str);
/*  929: 854 */     Fill_table(str);
/*  930: 856 */     if (this.Ark.Mapping == 1) {
/*  931: 857 */       Map_direct(str);
/*  932: 858 */     } else if (this.Ark.Mapping == 2) {
/*  933: 859 */       Map_setassociate(str);
/*  934:     */     }
/*  935:     */   }
/*  936:     */   
/*  937:     */   private void jButton1ActionPerformed(ActionEvent paramActionEvent)
/*  938:     */   {
/*  939: 864 */     this.Read_write = 2;this.newly_added_cache = 16;this.newly_added_main = 16;
/*  940: 865 */     Random localRandom = new Random();
/*  941: 866 */     int i = localRandom.nextInt(10000);
/*  942: 867 */     int j = i % this.Ark.MainMemory;
/*  943: 868 */     String str = Integer.toBinaryString(j);
/*  944: 869 */     this.jTextField1.setText(str);
/*  945: 870 */     Fill_table(str);
/*  946: 872 */     if (this.Ark.Write_policy == 1)
/*  947:     */     {
/*  948: 874 */       if (this.Ark.Mapping == 1) {
/*  949: 875 */         writeback_direct(str);
/*  950: 876 */       } else if (this.Ark.Mapping == 2) {
/*  951: 877 */         writeback_setassociate(str);
/*  952:     */       }
/*  953:     */     }
/*  954: 879 */     else if (this.Ark.Write_policy == 2) {
/*  955: 881 */       if (this.Ark.Mapping == 1) {
/*  956: 882 */         writethrough_direct(str);
/*  957: 883 */       } else if (this.Ark.Mapping == 2) {
/*  958: 884 */         writethrough_setassociate(str);
/*  959:     */       }
/*  960:     */     }
/*  961: 886 */     if (this.Ark.Write_policy == 3) {
/*  962: 888 */       if (this.Ark.Mapping == 1) {
/*  963: 889 */         writethroughno_direct(str);
/*  964: 890 */       } else if (this.Ark.Mapping == 2) {
/*  965: 891 */         writethroughno_setassociate(str);
/*  966:     */       }
/*  967:     */     }
/*  968:     */   }
/*  969:     */   
/*  970:     */   private void jRadioButton11ActionPerformed(ActionEvent paramActionEvent) {}
/*  971:     */   
/*  972:     */   private void jTextField1ActionPerformed(ActionEvent paramActionEvent) {}
/*  973:     */   
/*  974:     */   private void jRadioButton12ActionPerformed(ActionEvent paramActionEvent) {}
/*  975:     */   
/*  976:     */   private void values_ini()
/*  977:     */   {
/*  978: 970 */     this.Ark.arrays_init();
/*  979: 971 */     this.Ark.MainMemory = Integer.parseInt(this.choice2.getSelectedItem());
/*  980: 972 */     this.Ark.BlockSize = Integer.parseInt(this.choice3.getSelectedItem());
/*  981: 973 */     this.Ark.CacheMemory = Integer.parseInt(this.choice4.getSelectedItem());
/*  982: 974 */     this.Ark.Associativity = Integer.parseInt(this.choice5.getSelectedItem());
/*  983: 975 */     this.Ark.Number_MainBlocks = (this.Ark.MainMemory / this.Ark.BlockSize);
/*  984: 976 */     this.Ark.Number_CacheBlocks = (this.Ark.CacheMemory / this.Ark.BlockSize);
/*  985: 978 */     if (this.jRadioButton1.isSelected())
/*  986:     */     {
/*  987: 980 */       this.Ark.Mapping = 1;
/*  988: 981 */       this.Ark.Associativity = 1;
/*  989:     */     }
/*  990: 983 */     else if (this.jRadioButton11.isSelected())
/*  991:     */     {
/*  992: 985 */       this.Ark.Mapping = 2;
/*  993: 986 */       this.Ark.Number_sets = 1;
/*  994: 987 */       this.Ark.Associativity = this.Ark.Number_CacheBlocks;
/*  995:     */     }
/*  996:     */     else
/*  997:     */     {
/*  998: 991 */       this.Ark.Mapping = 2;
/*  999: 992 */       this.Ark.Number_sets = (this.Ark.Number_CacheBlocks / this.Ark.Associativity);
/* 1000:     */     }
/* 1001: 995 */     if (this.jRadioButton3.isSelected()) {
/* 1002: 997 */       this.Ark.Replacement_policy = 1;
/* 1003: 999 */     } else if (this.jRadioButton4.isSelected()) {
/* 1004:1001 */       this.Ark.Replacement_policy = 2;
/* 1005:1003 */     } else if (this.jRadioButton5.isSelected()) {
/* 1006:1005 */       this.Ark.Replacement_policy = 3;
/* 1007:     */     }
/* 1008:1008 */     if (this.jRadioButton8.isSelected()) {
/* 1009:1010 */       this.Ark.Write_policy = 1;
/* 1010:1012 */     } else if (this.jRadioButton9.isSelected()) {
/* 1011:1014 */       this.Ark.Write_policy = 2;
/* 1012:1016 */     } else if (this.jRadioButton10.isSelected()) {
/* 1013:1018 */       this.Ark.Write_policy = 3;
/* 1014:     */     }
/* 1015:1020 */     Graphics localGraphics = getGraphics();
/* 1016:1021 */     paint(localGraphics);
/* 1017:1022 */     this.Paint_flag = 1;
/* 1018:1023 */     repaint();
/* 1019:     */   }
/* 1020:     */   
/* 1021:     */   public void paint(Graphics paramGraphics)
/* 1022:     */   {
/* 1023:1029 */     super.paintComponents(paramGraphics);
/* 1024:1031 */     if (this.Paint_flag == 1)
/* 1025:     */     {
/* 1026:1033 */       this.Ark.Draw_labels_nowriteback(paramGraphics);
/* 1027:1034 */       if (this.Ark.Mapping == 1) {
/* 1028:1035 */         this.Ark.Draw_ini_Direct(paramGraphics);
/* 1029:     */       } else {
/* 1030:1037 */         this.Ark.Draw_ini_setassociate(paramGraphics);
/* 1031:     */       }
/* 1032:1038 */       this.Ark.Draw_ini_Main(paramGraphics);
/* 1033:     */     }
/* 1034:1040 */     else if (this.Paint_flag == 2)
/* 1035:     */     {
/* 1036:1041 */       if (this.Ark.Write_policy == 1) {
/* 1037:1042 */         this.Ark.Draw_labels(paramGraphics);
/* 1038:     */       } else {
/* 1039:1044 */         this.Ark.Draw_labels_nowriteback(paramGraphics);
/* 1040:     */       }
/* 1041:1045 */       if (this.Ark.Mapping == 1)
/* 1042:     */       {
/* 1043:1047 */         Draw_map_Direct(paramGraphics);
/* 1044:1048 */         Draw_map_Main(paramGraphics);
/* 1045:     */       }
/* 1046:1050 */       else if (this.Ark.Mapping == 2)
/* 1047:     */       {
/* 1048:1052 */         Draw_map_setAssociate(paramGraphics);
/* 1049:1053 */         Draw_map_Main(paramGraphics);
/* 1050:     */       }
/* 1051:     */     }
/* 1052:1056 */     else if (this.Paint_flag == 3)
/* 1053:     */     {
/* 1054:1059 */       if (this.Ark.Write_policy == 1)
/* 1055:     */       {
/* 1056:1061 */         this.Ark.Draw_labels(paramGraphics);
/* 1057:1062 */         if (this.Ark.Mapping == 1) {
/* 1058:1063 */           Draw_map_Direct(paramGraphics);
/* 1059:1064 */         } else if (this.Ark.Mapping == 2) {
/* 1060:1065 */           Draw_map_setAssociate(paramGraphics);
/* 1061:     */         }
/* 1062:1066 */         Draw_map_Main(paramGraphics);
/* 1063:     */       }
/* 1064:1068 */       else if (this.Ark.Write_policy == 2)
/* 1065:     */       {
/* 1066:1070 */         this.Ark.Draw_labels_nowriteback(paramGraphics);
/* 1067:1071 */         if (this.Ark.Mapping == 1) {
/* 1068:1072 */           Draw_map_Direct(paramGraphics);
/* 1069:1073 */         } else if (this.Ark.Mapping == 2) {
/* 1070:1074 */           Draw_map_setAssociate(paramGraphics);
/* 1071:     */         }
/* 1072:1075 */         Draw_map_Main(paramGraphics);
/* 1073:     */       }
/* 1074:1077 */       if (this.Ark.Write_policy == 3)
/* 1075:     */       {
/* 1076:1079 */         this.Ark.Draw_labels(paramGraphics);
/* 1077:1080 */         if (this.Ark.Mapping == 1) {
/* 1078:1081 */           Draw_map_Direct(paramGraphics);
/* 1079:1082 */         } else if (this.Ark.Mapping == 2) {
/* 1080:1083 */           Draw_map_setAssociate(paramGraphics);
/* 1081:     */         }
/* 1082:1084 */         Draw_map_Main(paramGraphics);
/* 1083:     */       }
/* 1084:     */     }
/* 1085:     */   }
/* 1086:     */   
/* 1087:     */   private int no_bits(int paramInt)
/* 1088:     */   {
/* 1089:1092 */     int i = 0;
/* 1090:1093 */     while (paramInt != 1)
/* 1091:     */     {
/* 1092:1095 */       paramInt /= 2;
/* 1093:1096 */       i++;
/* 1094:     */     }
/* 1095:1098 */     return i;
/* 1096:     */   }
/* 1097:     */   
/* 1098:     */   void Fill_table(String paramString)
/* 1099:     */   {
/* 1100:1103 */     int i = 0;
/* 1101:1104 */     int j = this.Ark.BlockSize;
/* 1102:1105 */     int k = this.Ark.BlockSize;
/* 1103:1106 */     int n = 0;
/* 1104:1107 */     i = no_bits(j);
/* 1105:     */     
/* 1106:1109 */     j = this.Ark.Number_MainBlocks * this.Ark.BlockSize;
/* 1107:1110 */     int i1 = 0;
/* 1108:1111 */     i1 = no_bits(j);
/* 1109:     */     
/* 1110:     */ 
/* 1111:     */ 
/* 1112:1115 */     int m = this.Ark.Number_CacheBlocks / this.Ark.Associativity;
/* 1113:1116 */     j = m;
/* 1114:1117 */     n = no_bits(j);
/* 1115:     */     
/* 1116:     */ 
/* 1117:1120 */     String str1 = "0";
/* 1118:1121 */     int i2 = i1 - paramString.length();
/* 1119:1122 */     while (i2 > 0)
/* 1120:     */     {
/* 1121:1124 */       i2--;
/* 1122:1125 */       paramString = str1 + paramString;
/* 1123:     */     }
/* 1124:1127 */     int i3 = i + n;
/* 1125:     */     
/* 1126:1129 */     int i4 = paramString.length();
/* 1127:1130 */     Fill_TextArea1(paramString);
/* 1128:1131 */     String str2 = paramString.substring(i4 - i);
/* 1129:1132 */     String str3 = paramString.substring(i4 - (i + n), i4 - i);
/* 1130:1133 */     this.str_tag = paramString.substring(0, i4 - (i + n));
/* 1131:1134 */     this.jTable1.setValueAt(str2, 0, 2);
/* 1132:1135 */     this.jTable1.setValueAt(str3, 0, 1);
/* 1133:1136 */     this.jTable1.setValueAt(this.str_tag, 0, 0);
/* 1134:     */   }
/* 1135:     */   
/* 1136:     */   void Fill_TextArea1(String paramString)
/* 1137:     */   {
/* 1138:1140 */     if (this.Read_write == 1) {
/* 1139:1141 */       paramString = paramString + " - R";
/* 1140:     */     } else {
/* 1141:1143 */       paramString = paramString + " - w";
/* 1142:     */     }
/* 1143:1144 */     this.TextArea1_counter += 1;
/* 1144:1145 */     this.jTextArea1.setText(this.jTextArea1.getText() + "\n" + Integer.toString(this.TextArea1_counter) + ")  " + paramString);
/* 1145:     */   }
/* 1146:     */   
/* 1147:     */   void Fill_TextArea2(String paramString) {}
/* 1148:     */   
/* 1149:     */   void Map_direct(String paramString)
/* 1150:     */   {
/* 1151:1153 */     int i = Integer.parseInt(paramString, 2);
/* 1152:1154 */     int j = i / this.Ark.BlockSize;
/* 1153:1155 */     int k = j % (this.Ark.Number_CacheBlocks / this.Ark.Associativity);
/* 1154:1156 */     this.newly_added_cache = k;
/* 1155:1157 */     this.newly_added_main = j;
/* 1156:1158 */     this.Ark.array_tags[k] = this.str_tag;
/* 1157:1159 */     this.Paint_flag = 2;
/* 1158:1160 */     if ((this.Ark.main[j] == 16) && (this.Ark.cache[k] == 16))
/* 1159:     */     {
/* 1160:1162 */       cache_miss();
/* 1161:1163 */       this.Ark.cache[k] = j;
/* 1162:1164 */       this.Ark.main[j] = k;
/* 1163:1165 */       this.jTextArea2.setText(this.jTextArea2.getText() + "\n" + this.TextArea2_counter++ + ")" + "The block " + j + " is brought into cacheblock " + k);
/* 1164:     */     }
/* 1165:1167 */     else if (this.Ark.main[j] != 16)
/* 1166:     */     {
/* 1167:1169 */       cache_hit();
/* 1168:1170 */       this.jTextArea2.setText(this.jTextArea2.getText() + "\n" + this.TextArea2_counter++ + ")" + " The block required already exits in the cache");
/* 1169:     */     }
/* 1170:1172 */     else if ((this.Ark.main[j] == 16) && (this.Ark.cache[k] != 16))
/* 1171:     */     {
/* 1172:1174 */       cache_miss();
/* 1173:1175 */       if (this.Ark.array_dirtybits[k] == 1)
/* 1174:     */       {
/* 1175:1177 */         this.jTextArea2.setText(this.jTextArea2.getText() + "\n" + this.TextArea2_counter++ + ")" + "The block " + this.Ark.cache[k] + " is written back into the main memory first as its dirty bit is set to 1");
/* 1176:1178 */         this.jTextArea2.setText(this.jTextArea2.getText() + "\n" + " The block " + this.Ark.cache[k] + " is replaced by " + j + "  at cachebock " + k);
/* 1177:     */       }
/* 1178:     */       else
/* 1179:     */       {
/* 1180:1182 */         this.jTextArea2.setText(this.jTextArea2.getText() + "\n" + this.TextArea2_counter++ + ")" + " The block " + this.Ark.cache[k] + " is replaced by " + j + "  at cachebock " + k);
/* 1181:     */       }
/* 1182:1184 */       this.Ark.main[this.Ark.cache[k]] = 16;
/* 1183:1185 */       this.Ark.cache[k] = j;
/* 1184:1186 */       this.Ark.main[j] = k;
/* 1185:1187 */       this.Ark.array_dirtybits[k] = 0;
/* 1186:     */     }
/* 1187:1189 */     repaint();
/* 1188:     */   }
/* 1189:     */   
/* 1190:     */   void Map_setassociate(String paramString)
/* 1191:     */   {
/* 1192:1196 */     int i = Integer.parseInt(paramString, 2);
/* 1193:1197 */     int j = i / this.Ark.BlockSize;
/* 1194:1198 */     int k = j % (this.Ark.Number_CacheBlocks / this.Ark.Associativity);
/* 1195:1199 */     this.newly_added_cache = k;
/* 1196:1200 */     this.newly_added_main = j;
/* 1197:1201 */     this.Paint_flag = 2;
/* 1198:     */     
/* 1199:1203 */     int m = issetfree(k * this.Ark.Associativity);
/* 1200:1205 */     if (this.Ark.main[j] != 16)
/* 1201:     */     {
/* 1202:1207 */       cache_hit();
/* 1203:1208 */       this.jTextArea2.setText(this.jTextArea2.getText() + "\n" + this.TextArea2_counter++ + ")" + "The block required already exits in the cache");
/* 1204:1209 */       int n = find_newcache(k * this.Ark.Associativity);
/* 1205:1210 */       this.newly_added_cache = n;
/* 1206:1211 */       this.Ark.array_times_LRU[n] = 0;
/* 1207:     */     }
/* 1208:1213 */     else if ((this.Ark.main[j] == 16) && (m != 16))
/* 1209:     */     {
/* 1210:1215 */       cache_miss();
/* 1211:1216 */       this.Ark.array_tags[m] = this.str_tag;
/* 1212:1217 */       this.newly_added_cache = m;
/* 1213:1218 */       this.Ark.array_times_LRU[m] = 0;
/* 1214:1219 */       this.Ark.array_times_FIFO[m] = 0;
/* 1215:1220 */       this.Ark.main[j] = m;
/* 1216:1221 */       this.Ark.cache[m] = j;
/* 1217:1222 */       this.jTextArea2.setText(this.jTextArea2.getText() + "\n" + this.TextArea2_counter++ + ")" + "The block required is brought into set " + k);
/* 1218:     */     }
/* 1219:1224 */     else if ((this.Ark.main[j] == 16) && (m == 16))
/* 1220:     */     {
/* 1221:1226 */       cache_miss();
/* 1222:1227 */       int n = 0;
/* 1223:1228 */       if (this.Ark.Replacement_policy == 1)
/* 1224:     */       {
/* 1225:1230 */         n = LRU_Replaceable(k * this.Ark.Associativity);
/* 1226:     */         
/* 1227:1232 */         this.Ark.array_times_LRU[n] = 0;
/* 1228:     */       }
/* 1229:1234 */       else if (this.Ark.Replacement_policy == 2)
/* 1230:     */       {
/* 1231:1236 */         n = random_Replaceable(k * this.Ark.Associativity);
/* 1232:     */       }
/* 1233:     */       else
/* 1234:     */       {
/* 1235:1240 */         n = FIFO_Replaceable(k * this.Ark.Associativity);
/* 1236:1241 */         this.Ark.array_times_FIFO[n] = 0;
/* 1237:     */       }
/* 1238:1243 */       if (this.Ark.array_dirtybits[n] == 1) {
/* 1239:1244 */         this.jTextArea2.setText(this.jTextArea2.getText() + "\n" + this.TextArea2_counter++ + ")" + "The block in " + this.Ark.cache[n] + " is updated into the main memory before it is removed from cache as its dirty bit is set to 1");
/* 1240:     */       } else {
/* 1241:1246 */         this.jTextArea2.setText(this.jTextArea2.getText() + "\n" + this.TextArea2_counter++ + ")" + "The block in " + this.Ark.cache[n] + " is removed as it is being replaced");
/* 1242:     */       }
/* 1243:1247 */       this.Ark.array_tags[n] = this.str_tag;
/* 1244:1248 */       this.newly_added_cache = n;
/* 1245:1249 */       this.Ark.main[this.Ark.cache[n]] = 16;
/* 1246:1250 */       this.Ark.main[j] = n;
/* 1247:1251 */       this.Ark.cache[n] = j;
/* 1248:1252 */       this.Ark.array_dirtybits[n] = 0;
/* 1249:1253 */       this.jTextArea2.setText(this.jTextArea2.getText() + "\n" + "The required block is brought into set " + k);
/* 1250:     */     }
/* 1251:1255 */     for (int n = 0; n < 16; n++) {
/* 1252:1257 */       if (this.Ark.cache[n] != 16)
/* 1253:     */       {
/* 1254:1259 */         this.Ark.array_times_FIFO[n] += 1;
/* 1255:1260 */         this.Ark.array_times_LRU[n] += 1;
/* 1256:     */       }
/* 1257:     */     }
/* 1258:1264 */     repaint();
/* 1259:     */   }
/* 1260:     */   
/* 1261:     */   void Draw_map_Direct(Graphics paramGraphics)
/* 1262:     */   {
/* 1263:1271 */     int i = 0;
/* 1264:1272 */     for (int j = 0; j < this.Ark.Number_CacheBlocks; j++)
/* 1265:     */     {
/* 1266:1275 */       paramGraphics.clearRect(this.Ark.cache_xstart - 35, this.Ark.cache_ystart + i, 34, 20);
/* 1267:1276 */       paramGraphics.drawRect(this.Ark.cache_xstart - 35, this.Ark.cache_ystart + i, 34, 20);
/* 1268:1278 */       if (this.Ark.Write_policy == 1) {
/* 1269:1279 */         paramGraphics.drawString(Integer.toString(this.Ark.array_dirtybits[j]), this.Ark.cache_xstart - 50, this.Ark.cache_ystart + i + 15);
/* 1270:     */       }
/* 1271:1281 */       paramGraphics.clearRect(this.Ark.cache_xstart, this.Ark.cache_ystart + i, 100, 20);
/* 1272:1282 */       paramGraphics.drawRect(this.Ark.cache_xstart, this.Ark.cache_ystart + i, 100, 20);
/* 1273:1283 */       paramGraphics.drawString("" + j, this.Ark.cache_xstart + 110, this.Ark.cache_ystart + i + 15);
/* 1274:1286 */       if (this.Ark.cache[j] != 16)
/* 1275:     */       {
/* 1276:1288 */         if (j == this.newly_added_cache)
/* 1277:     */         {
/* 1278:1290 */           paramGraphics.setColor(Color.GRAY);
/* 1279:1291 */           paramGraphics.fillRect(this.Ark.cache_xstart, this.Ark.cache_ystart + i, 100, 20);
/* 1280:1292 */           paramGraphics.setColor(Color.BLACK);
/* 1281:     */         }
/* 1282:1294 */         paramGraphics.drawString(Integer.toString(this.Ark.cache[j]), this.Ark.cache_xstart + 40, this.Ark.cache_ystart + i + 15);
/* 1283:     */         
/* 1284:1296 */         paramGraphics.drawString(this.Ark.array_tags[j], this.Ark.cache_xstart - 30, this.Ark.cache_ystart + i + 13);
/* 1285:     */       }
/* 1286:1302 */       i += 20;
/* 1287:1303 */       if (j != this.Ark.Number_CacheBlocks - 1)
/* 1288:     */       {
/* 1289:1305 */         paramGraphics.clearRect(this.Ark.cache_xstart, this.Ark.cache_ystart + i, 100, 2);
/* 1290:1306 */         paramGraphics.drawRect(this.Ark.cache_xstart, this.Ark.cache_ystart + i, 100, 2);
/* 1291:1307 */         i += 2;
/* 1292:     */       }
/* 1293:     */     }
/* 1294:     */   }
/* 1295:     */   
/* 1296:     */   void Draw_map_setAssociate(Graphics paramGraphics)
/* 1297:     */   {
/* 1298:1313 */     int i = 0;
/* 1299:1314 */     int j = 0;
/* 1300:1315 */     for (int k = 0; k < this.Ark.Number_CacheBlocks;)
/* 1301:     */     {
/* 1302:1316 */       int m = this.Ark.cache_ystart + i + 10;
/* 1303:1317 */       paramGraphics.drawLine(this.Ark.cache_xstart + 100 + 3, this.Ark.cache_ystart + i + 10, this.Ark.cache_xstart + 100 + 10, this.Ark.cache_ystart + i + 10);
/* 1304:1318 */       for (int n = 0; n < this.Ark.Associativity; k++)
/* 1305:     */       {
/* 1306:1321 */         paramGraphics.clearRect(this.Ark.cache_xstart - 35, this.Ark.cache_ystart + i, 34, 20);
/* 1307:1322 */         paramGraphics.drawRect(this.Ark.cache_xstart - 35, this.Ark.cache_ystart + i, 34, 20);
/* 1308:1324 */         if (this.Ark.Write_policy == 1) {
/* 1309:1325 */           paramGraphics.drawString(Integer.toString(this.Ark.array_dirtybits[k]), this.Ark.cache_xstart - 50, this.Ark.cache_ystart + i + 15);
/* 1310:     */         }
/* 1311:1327 */         paramGraphics.clearRect(this.Ark.cache_xstart, this.Ark.cache_ystart + i, 100, 20);
/* 1312:1328 */         paramGraphics.drawRect(this.Ark.cache_xstart, this.Ark.cache_ystart + i, 100, 20);
/* 1313:1330 */         if (this.Ark.cache[k] != 16)
/* 1314:     */         {
/* 1315:1332 */           if (k == this.newly_added_cache)
/* 1316:     */           {
/* 1317:1334 */             paramGraphics.setColor(Color.GRAY);
/* 1318:1335 */             paramGraphics.fillRect(this.Ark.cache_xstart, this.Ark.cache_ystart + i, 100, 20);
/* 1319:1336 */             paramGraphics.setColor(Color.BLACK);
/* 1320:     */           }
/* 1321:1338 */           paramGraphics.drawString(Integer.toString(this.Ark.cache[k]), this.Ark.cache_xstart + 40, this.Ark.cache_ystart + i + 15);
/* 1322:     */           
/* 1323:1340 */           paramGraphics.drawString(this.Ark.array_tags[k], this.Ark.cache_xstart - 30, this.Ark.cache_ystart + i + 13);
/* 1324:     */         }
/* 1325:1345 */         i += 20;n++;
/* 1326:     */       }
/* 1327:1347 */       paramGraphics.drawLine(this.Ark.cache_xstart + 100 + 10, m, this.Ark.cache_xstart + 100 + 10, this.Ark.cache_ystart + i - 10);
/* 1328:1348 */       paramGraphics.drawLine(this.Ark.cache_xstart + 100 + 3, this.Ark.cache_ystart + i - 10, this.Ark.cache_xstart + 100 + 10, this.Ark.cache_ystart + i - 10);
/* 1329:1349 */       paramGraphics.drawString("set " + j, this.Ark.cache_xstart + 100 + 11, (m + this.Ark.cache_ystart + i - 10) / 2);
/* 1330:1350 */       j += 1;
/* 1331:1352 */       if (k != this.Ark.Number_CacheBlocks)
/* 1332:     */       {
/* 1333:1354 */         paramGraphics.clearRect(this.Ark.cache_xstart, this.Ark.cache_ystart + i, 100, 2);
/* 1334:1355 */         paramGraphics.drawRect(this.Ark.cache_xstart, this.Ark.cache_ystart + i, 100, 2);
/* 1335:1356 */         i += 2;
/* 1336:     */       }
/* 1337:     */     }
/* 1338:     */   }
/* 1339:     */   
/* 1340:     */   void Draw_map_Main(Graphics paramGraphics)
/* 1341:     */   {
/* 1342:1364 */     int i = 0;
/* 1343:1366 */     for (int j = 0; j < this.Ark.Number_MainBlocks; j++)
/* 1344:     */     {
/* 1345:1369 */       paramGraphics.clearRect(this.Ark.main_xstart, this.Ark.main_ystart + i, 100, 20);
/* 1346:1370 */       paramGraphics.drawRect(this.Ark.main_xstart, this.Ark.main_ystart + i, 100, 20);
/* 1347:1371 */       if (j == this.newly_added_main)
/* 1348:     */       {
/* 1349:1373 */         paramGraphics.setColor(Color.GRAY);
/* 1350:1374 */         paramGraphics.fillRect(this.Ark.main_xstart, this.Ark.main_ystart + i, 100, 20);
/* 1351:1375 */         paramGraphics.setColor(Color.BLACK);
/* 1352:     */       }
/* 1353:1377 */       paramGraphics.drawString("" + j, this.Ark.main_xstart + 40, this.Ark.main_ystart + i + 15);
/* 1354:1378 */       i += 20;
/* 1355:1379 */       if (j != this.Ark.Number_MainBlocks - 1)
/* 1356:     */       {
/* 1357:1381 */         paramGraphics.clearRect(this.Ark.main_xstart, this.Ark.main_ystart + i, 100, 2);
/* 1358:1382 */         paramGraphics.drawRect(this.Ark.main_xstart, this.Ark.main_ystart + i, 100, 2);
/* 1359:1383 */         i += 2;
/* 1360:     */       }
/* 1361:     */     }
/* 1362:     */   }
/* 1363:     */   
/* 1364:     */   int issetfree(int paramInt)
/* 1365:     */   {
/* 1366:1390 */     for (int i = paramInt; i < paramInt + this.Ark.Associativity; i++) {
/* 1367:1392 */       if (this.Ark.cache[i] == 16) {
/* 1368:1394 */         return i;
/* 1369:     */       }
/* 1370:     */     }
/* 1371:1397 */     return 16;
/* 1372:     */   }
/* 1373:     */   
/* 1374:     */   private int LRU_Replaceable(int paramInt)
/* 1375:     */   {
/* 1376:1403 */     int i = 0;int j = 0;
/* 1377:1405 */     for (int k = paramInt; k < paramInt + this.Ark.Associativity; k++) {
/* 1378:1407 */       if (i < this.Ark.array_times_LRU[k])
/* 1379:     */       {
/* 1380:1409 */         j = k;
/* 1381:1410 */         i = this.Ark.array_times_LRU[k];
/* 1382:     */       }
/* 1383:     */     }
/* 1384:1414 */     return j;
/* 1385:     */   }
/* 1386:     */   
/* 1387:     */   private int FIFO_Replaceable(int paramInt)
/* 1388:     */   {
/* 1389:1419 */     int i = 0;int j = 0;
/* 1390:1420 */     for (int k = paramInt; k < paramInt + this.Ark.Associativity; k++) {
/* 1391:1422 */       if (i < this.Ark.array_times_FIFO[k])
/* 1392:     */       {
/* 1393:1424 */         j = k;
/* 1394:1425 */         i = this.Ark.array_times_FIFO[k];
/* 1395:     */       }
/* 1396:     */     }
/* 1397:1429 */     return j;
/* 1398:     */   }
/* 1399:     */   
/* 1400:     */   private int random_Replaceable(int paramInt)
/* 1401:     */   {
/* 1402:1433 */     int i = 0;
/* 1403:1434 */     Random localRandom = new Random();
/* 1404:1435 */     int j = localRandom.nextInt(1000);
/* 1405:1436 */     int k = j % this.Ark.Associativity;
/* 1406:1437 */     i = paramInt * this.Ark.Associativity + k;
/* 1407:1438 */     return i;
/* 1408:     */   }
/* 1409:     */   
/* 1410:     */   private int find_newcache(int paramInt)
/* 1411:     */   {
/* 1412:1443 */     for (int i = paramInt; i < paramInt + this.Ark.Associativity; i++) {
/* 1413:1445 */       if (this.Ark.cache[i] == this.newly_added_main) {
/* 1414:1447 */         return i;
/* 1415:     */       }
/* 1416:     */     }
/* 1417:1450 */     return 0;
/* 1418:     */   }
/* 1419:     */   
/* 1420:     */   private void writeback_direct(String paramString)
/* 1421:     */   {
/* 1422:1455 */     int i = Integer.parseInt(paramString, 2);
/* 1423:1456 */     int j = i / this.Ark.BlockSize;
/* 1424:1457 */     int k = j % (this.Ark.Number_CacheBlocks / this.Ark.Associativity);
/* 1425:1458 */     this.newly_added_cache = k;
/* 1426:1459 */     this.newly_added_main = j;
/* 1427:1460 */     this.Ark.array_tags[k] = this.str_tag;
/* 1428:1461 */     this.Paint_flag = 3;
/* 1429:1462 */     if ((this.Ark.main[j] == 16) && (this.Ark.cache[k] == 16))
/* 1430:     */     {
/* 1431:1464 */       cache_miss();
/* 1432:1465 */       this.Ark.cache[k] = j;
/* 1433:1466 */       this.Ark.main[j] = k;
/* 1434:1467 */       this.Ark.array_dirtybits[k] = 1;
/* 1435:1468 */       this.jTextArea2.setText(this.jTextArea2.getText() + "\n" + this.TextArea2_counter++ + ")" + " The block " + j + " is brought into cacheblock " + k + " and its dirtybit is set to 1");
/* 1436:     */     }
/* 1437:1470 */     else if (this.Ark.main[j] != 16)
/* 1438:     */     {
/* 1439:1472 */       cache_hit();
/* 1440:1473 */       this.Ark.array_dirtybits[k] = 1;
/* 1441:1474 */       this.jTextArea2.setText(this.jTextArea2.getText() + "\n" + this.TextArea2_counter++ + ")" + " The block required " + j + " already exits in the cache and is updated only in the cacheblock and its dirty bit is set to 1");
/* 1442:     */     }
/* 1443:1476 */     else if ((this.Ark.main[j] == 16) && (this.Ark.cache[k] != 16))
/* 1444:     */     {
/* 1445:1478 */       cache_miss();
/* 1446:1479 */       if (this.Ark.array_dirtybits[k] == 1) {
/* 1447:1480 */         this.jTextArea2.setText(this.jTextArea2.getText() + "\n" + " The block " + this.Ark.cache[k] + " is written back into the main memory");
/* 1448:     */       }
/* 1449:1482 */       this.jTextArea2.setText(this.jTextArea2.getText() + "\n" + this.TextArea2_counter++ + ")" + " The block " + this.Ark.cache[k] + " is replaced by " + j + "  at cachebock" + k + " and its dirtybit is set to 0");
/* 1450:1483 */       this.Ark.main[this.Ark.cache[k]] = 16;
/* 1451:1484 */       this.Ark.cache[k] = j;
/* 1452:1485 */       this.Ark.main[j] = k;
/* 1453:1486 */       this.Ark.array_dirtybits[k] = 0;
/* 1454:     */     }
/* 1455:1489 */     repaint();
/* 1456:     */   }
/* 1457:     */   
/* 1458:     */   private void writeback_setassociate(String paramString)
/* 1459:     */   {
/* 1460:1494 */     int i = Integer.parseInt(paramString, 2);
/* 1461:1495 */     int j = i / this.Ark.BlockSize;
/* 1462:1496 */     int k = j % (this.Ark.Number_CacheBlocks / this.Ark.Associativity);
/* 1463:1497 */     this.newly_added_cache = k;
/* 1464:1498 */     this.newly_added_main = j;
/* 1465:1499 */     this.Paint_flag = 3;
/* 1466:     */     
/* 1467:1501 */     int m = issetfree(k * this.Ark.Associativity);
/* 1468:1503 */     if (this.Ark.main[j] != 16)
/* 1469:     */     {
/* 1470:1505 */       cache_hit();
/* 1471:1506 */       this.Ark.array_dirtybits[this.Ark.main[j]] = 1;
/* 1472:1507 */       this.jTextArea2.setText(this.jTextArea2.getText() + "\n" + this.TextArea2_counter++ + ")" + " The block required " + j + " already exits in the cache and is updated only in the cacheblock and its dirty bit is set to 1");
/* 1473:1508 */       int n = find_newcache(k * this.Ark.Associativity);
/* 1474:1509 */       this.newly_added_cache = n;
/* 1475:1510 */       this.Ark.array_times_LRU[n] = 0;
/* 1476:     */     }
/* 1477:1512 */     else if ((this.Ark.main[j] == 16) && (m != 16))
/* 1478:     */     {
/* 1479:1514 */       cache_miss();
/* 1480:1515 */       this.Ark.array_tags[m] = this.str_tag;
/* 1481:1516 */       this.newly_added_cache = m;
/* 1482:1517 */       this.Ark.array_times_LRU[m] = 0;
/* 1483:1518 */       this.Ark.array_times_FIFO[m] = 0;
/* 1484:1519 */       this.Ark.main[j] = m;
/* 1485:1520 */       this.Ark.cache[m] = j;
/* 1486:1521 */       this.jTextArea2.setText(this.jTextArea2.getText() + "\n" + this.TextArea2_counter++ + ")" + " The block " + j + " is brought into cacheset " + k + " and its dirtybit is set to 1");
/* 1487:1522 */       this.Ark.array_dirtybits[m] = 1;
/* 1488:     */     }
/* 1489:1525 */     else if ((this.Ark.main[j] == 16) && (m == 16))
/* 1490:     */     {
/* 1491:1527 */       cache_miss();
/* 1492:1528 */       int n = 0;
/* 1493:1529 */       if (this.Ark.Replacement_policy == 1)
/* 1494:     */       {
/* 1495:1531 */         n = LRU_Replaceable(k * this.Ark.Associativity);
/* 1496:1532 */         this.Ark.array_times_LRU[n] = 0;
/* 1497:     */       }
/* 1498:1534 */       else if (this.Ark.Replacement_policy == 2)
/* 1499:     */       {
/* 1500:1536 */         n = random_Replaceable(k * this.Ark.Associativity);
/* 1501:     */       }
/* 1502:     */       else
/* 1503:     */       {
/* 1504:1540 */         n = FIFO_Replaceable(k * this.Ark.Associativity);
/* 1505:1541 */         this.Ark.array_times_FIFO[n] = 0;
/* 1506:     */       }
/* 1507:1543 */       if (this.Ark.array_dirtybits[n] == 1) {
/* 1508:1544 */         this.jTextArea2.setText(this.jTextArea2.getText() + "\n" + this.TextArea2_counter++ + ")" + " The block in " + this.Ark.cache[n] + " is updated into the main memory before it is removed from cache as its dirty bit is set to 1");
/* 1509:     */       } else {
/* 1510:1546 */         this.jTextArea2.setText(this.jTextArea2.getText() + "\n" + this.TextArea2_counter++ + ")" + " The block in " + this.Ark.cache[n] + " is removed as it is being replaced");
/* 1511:     */       }
/* 1512:1547 */       this.Ark.array_tags[n] = this.str_tag;
/* 1513:1548 */       this.Ark.array_dirtybits[n] = 1;
/* 1514:1549 */       this.newly_added_cache = n;
/* 1515:1550 */       this.Ark.main[this.Ark.cache[n]] = 16;
/* 1516:1551 */       this.Ark.main[j] = n;
/* 1517:1552 */       this.Ark.cache[n] = j;
/* 1518:1553 */       this.Ark.array_dirtybits[n] = 0;
/* 1519:1554 */       this.jTextArea2.setText(this.jTextArea2.getText() + "\n" + " The main memory block required is updated and brought into this place ");
/* 1520:     */     }
/* 1521:1556 */     for (int n = 0; n < 16; n++) {
/* 1522:1558 */       if (this.Ark.cache[n] != 16)
/* 1523:     */       {
/* 1524:1560 */         this.Ark.array_times_FIFO[n] += 1;
/* 1525:1561 */         this.Ark.array_times_LRU[n] += 1;
/* 1526:     */       }
/* 1527:     */     }
/* 1528:1565 */     repaint();
/* 1529:     */   }
/* 1530:     */   
/* 1531:     */   private void writethrough_direct(String paramString)
/* 1532:     */   {
/* 1533:1570 */     int i = Integer.parseInt(paramString, 2);
/* 1534:1571 */     int j = i / this.Ark.BlockSize;
/* 1535:1572 */     int k = j % (this.Ark.Number_CacheBlocks / this.Ark.Associativity);
/* 1536:1573 */     this.newly_added_cache = k;
/* 1537:1574 */     this.newly_added_main = j;
/* 1538:1575 */     this.Ark.array_tags[k] = this.str_tag;
/* 1539:1576 */     this.Paint_flag = 3;
/* 1540:1577 */     if ((this.Ark.main[j] == 16) && (this.Ark.cache[k] == 16))
/* 1541:     */     {
/* 1542:1579 */       cache_miss();
/* 1543:1580 */       this.Ark.cache[k] = j;
/* 1544:1581 */       this.Ark.main[j] = k;
/* 1545:1582 */       this.jTextArea2.setText(this.jTextArea2.getText() + "\n" + this.TextArea2_counter++ + ")" + " The block " + j + " is brought into cacheblock " + k + " and both are updated");
/* 1546:     */     }
/* 1547:1584 */     else if (this.Ark.main[j] != 16)
/* 1548:     */     {
/* 1549:1586 */       cache_hit();
/* 1550:1587 */       this.jTextArea2.setText(this.jTextArea2.getText() + "\n" + this.TextArea2_counter++ + ")" + "The block required " + j + " already exits in the cache and is updated  in the cacheblock and corresponding main memoryblock" + j);
/* 1551:     */     }
/* 1552:1589 */     else if ((this.Ark.main[j] == 16) && (this.Ark.cache[k] != 16))
/* 1553:     */     {
/* 1554:1591 */       cache_miss();
/* 1555:1592 */       this.jTextArea2.setText(this.jTextArea2.getText() + "\n" + this.TextArea2_counter++ + ")" + " The block " + this.Ark.cache[k] + " is replaced by " + j + "  at cachebock" + k + " and both are updated");
/* 1556:1593 */       this.Ark.main[this.Ark.cache[k]] = 16;
/* 1557:1594 */       this.Ark.cache[k] = j;
/* 1558:1595 */       this.Ark.main[j] = k;
/* 1559:     */     }
/* 1560:1597 */     repaint();
/* 1561:     */   }
/* 1562:     */   
/* 1563:     */   private void writethrough_setassociate(String paramString)
/* 1564:     */   {
/* 1565:1602 */     int i = Integer.parseInt(paramString, 2);
/* 1566:1603 */     int j = i / this.Ark.BlockSize;
/* 1567:1604 */     int k = j % (this.Ark.Number_CacheBlocks / this.Ark.Associativity);
/* 1568:1605 */     this.newly_added_cache = k;
/* 1569:1606 */     this.newly_added_main = j;
/* 1570:1607 */     this.Paint_flag = 3;
/* 1571:     */     
/* 1572:1609 */     int m = issetfree(k * this.Ark.Associativity);
/* 1573:1611 */     if (this.Ark.main[j] != 16)
/* 1574:     */     {
/* 1575:1613 */       cache_hit();
/* 1576:1614 */       this.jTextArea2.setText(this.jTextArea2.getText() + "\n" + this.TextArea2_counter++ + ")" + " The block required " + j + " already exits in the cache and is updated in both the places");
/* 1577:     */       
/* 1578:1616 */       int n = find_newcache(k * this.Ark.Associativity);
/* 1579:1617 */       this.newly_added_cache = n;
/* 1580:1618 */       this.Ark.array_times_LRU[n] = 0;
/* 1581:     */     }
/* 1582:1620 */     else if ((this.Ark.main[j] == 16) && (m != 16))
/* 1583:     */     {
/* 1584:1622 */       cache_miss();
/* 1585:1623 */       this.Ark.array_tags[m] = this.str_tag;
/* 1586:1624 */       this.newly_added_cache = m;
/* 1587:1625 */       this.Ark.array_times_LRU[m] = 0;
/* 1588:1626 */       this.Ark.array_times_FIFO[m] = 0;
/* 1589:1627 */       this.Ark.main[j] = m;
/* 1590:1628 */       this.Ark.cache[m] = j;
/* 1591:1629 */       this.jTextArea2.setText(this.jTextArea2.getText() + "\n" + this.TextArea2_counter++ + ")" + " The block " + j + " is brought into cacheset " + k + " and both of them are updated");
/* 1592:     */     }
/* 1593:1633 */     else if ((this.Ark.main[j] == 16) && (m == 16))
/* 1594:     */     {
/* 1595:1635 */       cache_miss();
/* 1596:1636 */       int n = 0;
/* 1597:1637 */       if (this.Ark.Replacement_policy == 1)
/* 1598:     */       {
/* 1599:1639 */         n = LRU_Replaceable(k * this.Ark.Associativity);
/* 1600:1640 */         this.Ark.array_times_LRU[n] = 0;
/* 1601:     */       }
/* 1602:1642 */       else if (this.Ark.Replacement_policy == 2)
/* 1603:     */       {
/* 1604:1644 */         n = random_Replaceable(k * this.Ark.Associativity);
/* 1605:     */       }
/* 1606:     */       else
/* 1607:     */       {
/* 1608:1648 */         n = FIFO_Replaceable(k * this.Ark.Associativity);
/* 1609:1649 */         this.Ark.array_times_FIFO[n] = 0;
/* 1610:     */       }
/* 1611:1652 */       this.Ark.array_tags[n] = this.str_tag;
/* 1612:1653 */       this.newly_added_cache = n;
/* 1613:1654 */       this.Ark.main[this.Ark.cache[n]] = 16;
/* 1614:1655 */       this.Ark.main[j] = n;
/* 1615:1656 */       this.Ark.cache[n] = j;
/* 1616:1657 */       this.jTextArea2.setText(this.jTextArea2.getText() + "\n" + this.TextArea2_counter++ + ")" + " The block required is brought into this place and its dirty bit is set to 1");
/* 1617:     */     }
/* 1618:1659 */     for (int n = 0; n < 16; n++) {
/* 1619:1661 */       if (this.Ark.cache[n] != 16)
/* 1620:     */       {
/* 1621:1663 */         this.Ark.array_times_FIFO[n] += 1;
/* 1622:1664 */         this.Ark.array_times_LRU[n] += 1;
/* 1623:     */       }
/* 1624:     */     }
/* 1625:1668 */     repaint();
/* 1626:     */   }
/* 1627:     */   
/* 1628:     */   private void writethroughno_direct(String paramString)
/* 1629:     */   {
/* 1630:1673 */     int i = Integer.parseInt(paramString, 2);
/* 1631:1674 */     int j = i / this.Ark.BlockSize;
/* 1632:1675 */     int k = j % (this.Ark.Number_CacheBlocks / this.Ark.Associativity);
/* 1633:1676 */     this.newly_added_cache = k;
/* 1634:1677 */     this.newly_added_main = j;
/* 1635:1678 */     this.Ark.array_tags[k] = this.str_tag;
/* 1636:1679 */     this.Paint_flag = 3;
/* 1637:1681 */     if (this.Ark.main[j] != 16)
/* 1638:     */     {
/* 1639:1683 */       cache_hit();
/* 1640:1684 */       this.jTextArea2.setText(this.jTextArea2.getText() + "\n" + this.TextArea2_counter++ + ")" + " The block required " + j + " already exits in the cache and is updated  in the cacheblock and corresponding main memoryblock" + j);
/* 1641:     */     }
/* 1642:1686 */     else if (this.Ark.main[j] == 16)
/* 1643:     */     {
/* 1644:1688 */       cache_miss();
/* 1645:1689 */       this.newly_added_cache = 16;
/* 1646:1690 */       this.jTextArea2.setText(this.jTextArea2.getText() + "\n" + this.TextArea2_counter++ + ")" + "  The block " + j + " is updated and not brought into cache");
/* 1647:     */     }
/* 1648:1692 */     repaint();
/* 1649:     */   }
/* 1650:     */   
/* 1651:     */   private void writethroughno_setassociate(String paramString)
/* 1652:     */   {
/* 1653:1697 */     int i = Integer.parseInt(paramString, 2);
/* 1654:1698 */     int j = i / this.Ark.BlockSize;
/* 1655:1699 */     int k = j % (this.Ark.Number_CacheBlocks / this.Ark.Associativity);
/* 1656:1700 */     this.newly_added_cache = k;
/* 1657:1701 */     this.newly_added_main = j;
/* 1658:1702 */     this.Paint_flag = 3;
/* 1659:     */     
/* 1660:1704 */     int m = issetfree(k * this.Ark.Associativity);
/* 1661:1706 */     if (this.Ark.main[j] != 16)
/* 1662:     */     {
/* 1663:1708 */       cache_hit();
/* 1664:1709 */       this.jTextArea2.setText(this.jTextArea2.getText() + "\n" + this.TextArea2_counter++ + ")" + " The block required " + j + " already exits in the cache and is updated in both the places");
/* 1665:1710 */       int n = find_newcache(k * this.Ark.Associativity);
/* 1666:1711 */       this.newly_added_cache = n;
/* 1667:     */     }
/* 1668:1713 */     else if (this.Ark.main[j] == 16)
/* 1669:     */     {
/* 1670:1715 */       cache_miss();
/* 1671:1716 */       this.newly_added_cache = 16;
/* 1672:1717 */       this.jTextArea2.setText(this.jTextArea2.getText() + "\n" + this.TextArea2_counter++ + ")" + " The block " + j + " is updated and not brought into cache");
/* 1673:     */     }
/* 1674:1721 */     repaint();
/* 1675:     */   }
/* 1676:     */   
/* 1677:     */   void cache_hit()
/* 1678:     */   {
/* 1679:1725 */     int i = Integer.parseInt(this.jTextField2.getText());
/* 1680:1726 */     this.jTextField2.setText(Integer.toString(i + 1));
/* 1681:     */   }
/* 1682:     */   
/* 1683:     */   void cache_miss()
/* 1684:     */   {
/* 1685:1730 */     int i = Integer.parseInt(this.jTextField3.getText());
/* 1686:1731 */     this.jTextField3.setText(Integer.toString(i + 1));
/* 1687:     */   }
/* 1688:     */   
/* 1689:     */   private String Any_to_binary(String paramString)
/* 1690:     */   {
/* 1691:1735 */     String str = null;
/* 1692:1736 */     if (this.jRadioButton12.isSelected())
/* 1693:     */     {
/* 1694:1738 */       str = Integer.toBinaryString(Integer.parseInt(paramString));
/* 1695:     */     }
/* 1696:1740 */     else if (this.jRadioButton13.isSelected())
/* 1697:     */     {
/* 1698:1742 */       int i = Integer.parseInt(paramString, 16);
/* 1699:1743 */       str = Integer.toBinaryString(i);
/* 1700:     */     }
/* 1701:1745 */     else if (this.jRadioButton14.isSelected())
/* 1702:     */     {
/* 1703:1747 */       str = paramString;
/* 1704:     */     }
/* 1705:1749 */     return str;
/* 1706:     */   }
/* 1707:     */ }



/* Location:           E:\vlead-labs\computer-organization-iiith\src\lab\CacheOrganizations\applets\

 * Qualified Name:     cachesimulator

 * JD-Core Version:    0.7.0.1

 */