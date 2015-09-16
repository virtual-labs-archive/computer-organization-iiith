 import java.awt.Choice;
 import java.awt.Color;
 import java.awt.Container;
 import java.awt.Cursor;
 import java.awt.Dimension;
 import java.awt.EventQueue;
 import java.awt.Graphics;
 import java.awt.Label;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.awt.event.ItemEvent;
 import java.awt.event.ItemListener;
 import java.util.Random; 
 import javax.swing.*;
 import javax.swing.BorderFactory;
 import javax.swing.ButtonGroup;
 import javax.swing.GroupLayout;
 import javax.swing.GroupLayout.Alignment;
 import javax.swing.GroupLayout.ParallelGroup;
 import javax.swing.GroupLayout.SequentialGroup;
 import javax.swing.JApplet;
 import javax.swing.JButton;
 import javax.swing.JLabel;
 import javax.swing.JPanel;
 import javax.swing.JRadioButton;
 import javax.swing.JScrollPane;
 import javax.swing.JTable;
 import javax.swing.JTextArea;
 import javax.swing.JTextField;
 import javax.swing.LayoutStyle.ComponentPlacement;
 import javax.swing.table.DefaultTableModel;

public class cachesimulator extends javax.swing.JApplet {
	Architecture Ark = new Architecture();
	int Paint_flag = 0;
	int Read_write = 0;
	int TextArea1_counter = 0;
	int TextArea2_counter = 0;
	int newly_added_cache = 16;
	int newly_added_main = 16;
	String str_tag;
	private javax.swing.ButtonGroup buttonGroup1;
	private javax.swing.ButtonGroup buttonGroup2;
	private javax.swing.ButtonGroup buttonGroup3;
	private javax.swing.ButtonGroup buttonGroup4;
	private javax.swing.ButtonGroup buttonGroup5;
	private java.awt.Choice choice2;
	private java.awt.Choice choice3;
	private java.awt.Choice choice4;
	private java.awt.Choice choice5;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel10;
	private javax.swing.JPanel jPanel11;
	private javax.swing.JPanel jPanel12;
	private javax.swing.JPanel jPanel13;
	private javax.swing.JPanel jPanel14;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JPanel jPanel6;
	private javax.swing.JPanel jPanel7;
	private javax.swing.JPanel jPanel8;
	private javax.swing.JPanel jPanel9;
	private javax.swing.JRadioButton jRadioButton1;
	private javax.swing.JRadioButton jRadioButton10;
	private javax.swing.JRadioButton jRadioButton11;
	private javax.swing.JRadioButton jRadioButton12;
	private javax.swing.JRadioButton jRadioButton13;
	private javax.swing.JRadioButton jRadioButton14;
	private javax.swing.JRadioButton jRadioButton2;
	private javax.swing.JRadioButton jRadioButton3;
	private javax.swing.JRadioButton jRadioButton4;
	private javax.swing.JRadioButton jRadioButton5;
	private javax.swing.JRadioButton jRadioButton6;
	private javax.swing.JRadioButton jRadioButton7;
	private javax.swing.JRadioButton jRadioButton8;
	private javax.swing.JRadioButton jRadioButton9;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JTable jTable1;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JTextArea jTextArea2;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField jTextField3;
	private java.awt.Label label1;
	private java.awt.Label label2;
	private java.awt.Label label3;
	private java.awt.Label label4;

	public void init() {
		java.awt.EventQueue.invokeAndWait(new cachesimulator$1(this));
	}

	private void initComponents() {
		this.buttonGroup1 = new javax.swing.ButtonGroup();
		this.buttonGroup2 = new javax.swing.ButtonGroup();
		this.buttonGroup3 = new javax.swing.ButtonGroup();
		this.buttonGroup4 = new javax.swing.ButtonGroup();
		this.buttonGroup5 = new javax.swing.ButtonGroup();
		this.jPanel1 = new javax.swing.JPanel();
		this.jLabel5 = new javax.swing.JLabel();
		this.jPanel2 = new javax.swing.JPanel();
		this.choice4 = new java.awt.Choice();
		this.label3 = new java.awt.Label();
		this.label2 = new java.awt.Label();
		this.label4 = new java.awt.Label();
		this.choice3 = new java.awt.Choice();
		this.choice2 = new java.awt.Choice();
		this.jPanel3 = new javax.swing.JPanel();
		this.jRadioButton1 = new javax.swing.JRadioButton();
		this.jRadioButton2 = new javax.swing.JRadioButton();
		this.choice5 = new java.awt.Choice();
		this.label1 = new java.awt.Label();
		this.jRadioButton11 = new javax.swing.JRadioButton();
		this.jPanel8 = new javax.swing.JPanel();
		this.jRadioButton3 = new javax.swing.JRadioButton();
		this.jRadioButton5 = new javax.swing.JRadioButton();
		this.jRadioButton4 = new javax.swing.JRadioButton();
		this.jPanel11 = new javax.swing.JPanel();
		this.jRadioButton8 = new javax.swing.JRadioButton();
		this.jRadioButton9 = new javax.swing.JRadioButton();
		this.jRadioButton10 = new javax.swing.JRadioButton();
		this.jButton2 = new javax.swing.JButton();
		this.jPanel4 = new javax.swing.JPanel();
		this.jPanel10 = new javax.swing.JPanel();
		this.jTextField1 = new javax.swing.JTextField();
		this.jRadioButton6 = new javax.swing.JRadioButton();
		this.jRadioButton7 = new javax.swing.JRadioButton();
		this.jButton3 = new javax.swing.JButton();
		this.jPanel14 = new javax.swing.JPanel();
		this.jRadioButton12 = new javax.swing.JRadioButton();
		this.jRadioButton13 = new javax.swing.JRadioButton();
		this.jRadioButton14 = new javax.swing.JRadioButton();
		this.jPanel13 = new javax.swing.JPanel();
		this.jButton4 = new javax.swing.JButton();
		this.jButton1 = new javax.swing.JButton();
		this.jScrollPane1 = new javax.swing.JScrollPane();
		this.jTable1 = new javax.swing.JTable();
		this.jPanel5 = new javax.swing.JPanel();
		this.jPanel7 = new javax.swing.JPanel();
		this.jScrollPane3 = new javax.swing.JScrollPane();
		this.jTextArea2 = new javax.swing.JTextArea();
		this.jPanel6 = new javax.swing.JPanel();
		this.jPanel9 = new javax.swing.JPanel();
		this.jTextField2 = new javax.swing.JTextField();
		this.jTextField3 = new javax.swing.JTextField();
		this.jLabel3 = new javax.swing.JLabel();
		this.jLabel4 = new javax.swing.JLabel();
		this.jPanel12 = new javax.swing.JPanel();
		this.jScrollPane2 = new javax.swing.JScrollPane();
		this.jTextArea1 = new javax.swing.JTextArea();
		setBackground(new java.awt.Color(240, 240, 240));
		this.jPanel1.setBackground(new java.awt.Color(204, 204, 255));
		this.jPanel1
				.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
		this.jPanel1.setMaximumSize(new java.awt.Dimension(177, 750));
		this.jPanel1.setMinimumSize(new java.awt.Dimension(177, 750));
		this.jLabel5.setText("         CACHE ORGANIZATION");
		this.jPanel2.setBackground(new java.awt.Color(204, 204, 255));
		this.jPanel2.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Parameters"));
		this.jPanel2.setVisible(true);
		this.choice4.add("2");
		this.choice4.add("4");
		this.choice4.add("8");
		this.choice4.add("16");
		this.choice4.add("32");
		this.choice4.addItemListener(new cachesimulator$2(this));
		this.label3.setBackground(new java.awt.Color(204, 204, 255));
		this.label3.setForeground(new java.awt.Color(0, 0, 0));
		this.label3.setText("Cache(Bytes)");
		this.label2.setForeground(new java.awt.Color(0, 0, 0));
		this.label2.setText("Main(Bytes)");
		this.label4.setForeground(new java.awt.Color(0, 0, 0));
		this.label4.setText("Block(Bytes)");
		this.choice3.add("2");
		this.choice3.add("4");
		this.choice3.add("8");
		this.choice3.add("16");
		this.choice3.add("32");
		this.choice3.addItemListener(new cachesimulator$3(this));
		this.choice2.removeAll();
		this.choice2.add("4");
		this.choice2.add("8");
		this.choice2.add("16");
		this.choice2.add("32");
		r1 = new javax.swing.GroupLayout(this.jPanel2);
		this.jPanel2.setLayout(r1);
		r1.setHorizontalGroup(r1
				.createParallelGroup(javax.swing.GroupLayout$Alignment.LEADING)
				.addGroup(
						r1.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										r1.createParallelGroup(
												javax.swing.GroupLayout$Alignment.LEADING)
												.addComponent(this.label2, -2,
														-1, -2)
												.addComponent(this.label3, -2,
														-1, -2)
												.addComponent(this.label4, -2,
														-1, -2))
								.addPreferredGap(
										javax.swing.LayoutStyle$ComponentPlacement.RELATED)
								.addGroup(
										r1.createParallelGroup(
												javax.swing.GroupLayout$Alignment.LEADING,
												false)
												.addComponent(this.choice4, -1,
														-1, Short.MAX_VALUE)
												.addComponent(this.choice2, -1,
														-1, Short.MAX_VALUE)
												.addComponent(this.choice3, -1,
														43, Short.MAX_VALUE))
								.addContainerGap()));
		r1.setVerticalGroup(r1
				.createParallelGroup(javax.swing.GroupLayout$Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout$Alignment.TRAILING,
						r1.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										r1.createParallelGroup(
												javax.swing.GroupLayout$Alignment.LEADING)
												.addComponent(this.label4, -2,
														-1, -2)
												.addComponent(this.choice3, -2,
														-1, -2))
								.addPreferredGap(
										javax.swing.LayoutStyle$ComponentPlacement.RELATED)
								.addGroup(
										r1.createParallelGroup(
												javax.swing.GroupLayout$Alignment.LEADING)
												.addComponent(this.label2, -2,
														-1, -2)
												.addComponent(this.choice2, -2,
														-1, -2))
								.addPreferredGap(
										javax.swing.LayoutStyle$ComponentPlacement.RELATED)
								.addGroup(
										r1.createParallelGroup(
												javax.swing.GroupLayout$Alignment.LEADING)
												.addComponent(this.label3, -2,
														-1, -2)
												.addComponent(this.choice4, -2,
														-1, -2))
								.addContainerGap(32, Short.MAX_VALUE)));
		this.jPanel3.setBackground(new java.awt.Color(204, 204, 255));
		this.jPanel3.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Mapping"));
		this.jPanel3.setVisible(true);
		this.jRadioButton1.setBackground(new java.awt.Color(204, 204, 255));
		this.buttonGroup1.add(this.jRadioButton1);
		this.jRadioButton1.setSelected(true);
		this.jRadioButton1.setText("Direct Mapping");
		this.jRadioButton2.setBackground(new java.awt.Color(204, 204, 255));
		this.buttonGroup1.add(this.jRadioButton2);
		this.jRadioButton2.setText("Set Associative");
		this.choice5.add("2");
		this.choice5.add("4");
		this.choice5.add("8");
		this.label1.setText("Set Size");
		this.jRadioButton11.setBackground(new java.awt.Color(204, 204, 255));
		this.buttonGroup1.add(this.jRadioButton11);
		this.jRadioButton11.setText("Fully Associative");
		this.jRadioButton11.addActionListener(new cachesimulator$4(this));
		var r2 = new javax.swing.GroupLayout(this.jPanel3);
		this.jPanel3.setLayout(r2);
		r2.setHorizontalGroup(r2
				.createParallelGroup(javax.swing.GroupLayout$Alignment.LEADING)
				.addGroup(
						r2.createSequentialGroup()
								.addGroup(
										r2.createParallelGroup(
												javax.swing.GroupLayout$Alignment.LEADING)
												.addGroup(
														r2.createSequentialGroup()
																.addGap(25, 25,
																		25)
																.addComponent(
																		this.choice5,
																		-2, 51,
																		-2)
																.addPreferredGap(
																		javax.swing.LayoutStyle$ComponentPlacement.RELATED)
																.addComponent(
																		this.label1,
																		-1,
																		88,
																		Short.MAX_VALUE))
												.addComponent(
														this.jRadioButton1)
												.addComponent(
														this.jRadioButton11)
												.addComponent(
														this.jRadioButton2))
								.addContainerGap()));
		r2.setVerticalGroup(r2
				.createParallelGroup(javax.swing.GroupLayout$Alignment.LEADING)
				.addGroup(
						r2.createSequentialGroup()
								.addContainerGap()
								.addComponent(this.jRadioButton1)
								.addPreferredGap(
										javax.swing.LayoutStyle$ComponentPlacement.RELATED)
								.addComponent(this.jRadioButton11)
								.addPreferredGap(
										javax.swing.LayoutStyle$ComponentPlacement.RELATED)
								.addComponent(this.jRadioButton2)
								.addPreferredGap(
										javax.swing.LayoutStyle$ComponentPlacement.RELATED)
								.addGroup(
										r2.createParallelGroup(
												javax.swing.GroupLayout$Alignment.LEADING)
												.addComponent(this.choice5, -2,
														-1, -2)
												.addComponent(this.label1, -2,
														-1, -2))
								.addContainerGap(16, Short.MAX_VALUE)));
		this.jPanel8.setBackground(new java.awt.Color(204, 204, 255));
		this.jPanel8.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Replacement Policy"));
		this.jPanel8.setVisible(true);
		this.jRadioButton3.setBackground(new java.awt.Color(204, 204, 255));
		this.buttonGroup2.add(this.jRadioButton3);
		this.jRadioButton3.setSelected(true);
		this.jRadioButton3.setText("LRU");
		this.jRadioButton5.setBackground(new java.awt.Color(204, 204, 255));
		this.buttonGroup2.add(this.jRadioButton5);
		this.jRadioButton5.setText("FIFO");
		this.jRadioButton4.setBackground(new java.awt.Color(204, 204, 255));
		this.buttonGroup2.add(this.jRadioButton4);
		this.jRadioButton4.setText("Random");
		var r3 = new javax.swing.GroupLayout(this.jPanel8);
		this.jPanel8.setLayout(r3);
		r3.setHorizontalGroup(r3
				.createParallelGroup(javax.swing.GroupLayout$Alignment.LEADING)
				.addGroup(
						r3.createSequentialGroup()
								.addGroup(
										r3.createParallelGroup(
												javax.swing.GroupLayout$Alignment.TRAILING,
												false)
												.addComponent(
														this.jRadioButton4,
														javax.swing.GroupLayout$Alignment.LEADING,
														-1, -1, Short.MAX_VALUE)
												.addComponent(
														this.jRadioButton5,
														javax.swing.GroupLayout$Alignment.LEADING,
														-1, -1, Short.MAX_VALUE)
												.addComponent(
														this.jRadioButton3,
														javax.swing.GroupLayout$Alignment.LEADING,
														-2, 88, -2))
								.addContainerGap(79, Short.MAX_VALUE)));
		r3.setVerticalGroup(r3
				.createParallelGroup(javax.swing.GroupLayout$Alignment.LEADING)
				.addGroup(
						r3.createSequentialGroup()
								.addGap(17, 17, 17)
								.addComponent(this.jRadioButton3)
								.addPreferredGap(
										javax.swing.LayoutStyle$ComponentPlacement.RELATED)
								.addComponent(this.jRadioButton5)
								.addPreferredGap(
										javax.swing.LayoutStyle$ComponentPlacement.RELATED)
								.addComponent(this.jRadioButton4)
								.addContainerGap(36, Short.MAX_VALUE)));
		this.jPanel11.setBackground(new java.awt.Color(204, 204, 255));
		this.jPanel11.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Write Policy"));
		this.jPanel11.setMaximumSize(new java.awt.Dimension(85, 96));
		this.jPanel11.setMinimumSize(new java.awt.Dimension(85, 96));
		this.jRadioButton8.setBackground(new java.awt.Color(204, 204, 255));
		this.buttonGroup3.add(this.jRadioButton8);
		this.jRadioButton8.setSelected(true);
		this.jRadioButton8.setText("Write-back");
		this.jRadioButton9.setBackground(new java.awt.Color(204, 204, 255));
		this.buttonGroup3.add(this.jRadioButton9);
		this.jRadioButton9.setText("Write-through Allocate");
		this.jRadioButton10.setBackground(new java.awt.Color(204, 204, 255));
		this.buttonGroup3.add(this.jRadioButton10);
		this.jRadioButton10.setText("Write-through No Allocate");
		r4 = new javax.swing.GroupLayout(this.jPanel11);
		this.jPanel11.setLayout(r4);
		r4.setHorizontalGroup(r4
				.createParallelGroup(javax.swing.GroupLayout$Alignment.LEADING)
				.addGroup(
						r4.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										r4.createParallelGroup(
												javax.swing.GroupLayout$Alignment.LEADING)
												.addComponent(
														this.jRadioButton9, -1,
														160, Short.MAX_VALUE)
												.addComponent(
														this.jRadioButton10,
														javax.swing.GroupLayout$Alignment.TRAILING,
														-1, 160,
														Short.MAX_VALUE)
												.addComponent(
														this.jRadioButton8, -1,
														160, Short.MAX_VALUE))
								.addContainerGap()));
		r4.setVerticalGroup(r4
				.createParallelGroup(javax.swing.GroupLayout$Alignment.LEADING)
				.addGroup(
						r4.createSequentialGroup()
								.addContainerGap()
								.addComponent(this.jRadioButton8)
								.addPreferredGap(
										javax.swing.LayoutStyle$ComponentPlacement.RELATED)
								.addComponent(this.jRadioButton9)
								.addPreferredGap(
										javax.swing.LayoutStyle$ComponentPlacement.UNRELATED)
								.addComponent(this.jRadioButton10)
								.addContainerGap(43, Short.MAX_VALUE)));
		this.jButton2.setText("Submit");
		this.jButton2.addActionListener(new cachesimulator$5(this));
		r5 = new javax.swing.GroupLayout(this.jPanel1);
		this.jPanel1.setLayout(r5);
		r5.setHorizontalGroup(r5
				.createParallelGroup(javax.swing.GroupLayout$Alignment.LEADING)
				.addGroup(
						r5.createSequentialGroup()
								.addContainerGap(60, Short.MAX_VALUE)
								.addComponent(this.jPanel2, -2, -1, -2)
								.addPreferredGap(
										javax.swing.LayoutStyle$ComponentPlacement.RELATED)
								.addComponent(this.jPanel3, -2, -1, -2)
								.addPreferredGap(
										javax.swing.LayoutStyle$ComponentPlacement.RELATED)
								.addComponent(this.jPanel8, -2, -1, -2)
								.addGap(6, 6, 6)
								.addComponent(this.jPanel11, -2, -1, -2)
								.addGap(57, 57, 57))
				.addGroup(
						r5.createSequentialGroup().addGap(338, 338, 338)
								.addComponent(this.jLabel5, -2, 170, -2)
								.addContainerGap(349, Short.MAX_VALUE))
				.addGroup(
						r5.createSequentialGroup().addGap(329, 329, 329)
								.addComponent(this.jButton2, -2, 186, -2)
								.addContainerGap(342, Short.MAX_VALUE)));
		r5.setVerticalGroup(r5
				.createParallelGroup(javax.swing.GroupLayout$Alignment.LEADING)
				.addGroup(
						r5.createSequentialGroup()
								.addComponent(this.jLabel5)
								.addGap(11, 11, 11)
								.addGroup(
										r5.createParallelGroup(
												javax.swing.GroupLayout$Alignment.LEADING)
												.addComponent(
														this.jPanel11,
														javax.swing.GroupLayout$Alignment.TRAILING,
														-2, -1, -2)
												.addComponent(this.jPanel2, -2,
														-1, -2)
												.addComponent(this.jPanel3, -2,
														-1, -2)
												.addComponent(this.jPanel8, -2,
														-1, -2))
								.addPreferredGap(
										javax.swing.LayoutStyle$ComponentPlacement.RELATED,
										7, Short.MAX_VALUE)
								.addComponent(this.jButton2).addContainerGap()));
		this.jPanel4.setBackground(new java.awt.Color(204, 204, 255));
		this.jPanel4.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Address"));
		this.jPanel4.setMaximumSize(new java.awt.Dimension(668, 134));
		this.jPanel4.setMinimumSize(new java.awt.Dimension(668, 134));
		this.jPanel4.setVisible(true);
		this.jPanel10.setBackground(new java.awt.Color(204, 204, 255));
		this.jPanel10.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Manual Address Selection"));
		this.jTextField1.addActionListener(new cachesimulator$6(this));
		this.jRadioButton6.setBackground(new java.awt.Color(204, 204, 255));
		this.buttonGroup4.add(this.jRadioButton6);
		this.jRadioButton6.setSelected(true);
		this.jRadioButton6.setText("Read");
		this.jRadioButton7.setBackground(new java.awt.Color(204, 204, 255));
		this.buttonGroup4.add(this.jRadioButton7);
		this.jRadioButton7.setText("Write");
		this.jButton3.setText("Map");
		this.jButton3.addActionListener(new cachesimulator$7(this));
		this.jPanel14.setBackground(new java.awt.Color(204, 204, 255));
		this.jPanel14.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Address Type"));
		this.jRadioButton12.setBackground(new java.awt.Color(204, 204, 255));
		this.buttonGroup5.add(this.jRadioButton12);
		this.jRadioButton12.setSelected(true);
		this.jRadioButton12.setText("Decimal");
		this.jRadioButton12.addActionListener(new cachesimulator$8(this));
		this.jRadioButton13.setBackground(new java.awt.Color(204, 204, 255));
		this.buttonGroup5.add(this.jRadioButton13);
		this.jRadioButton13.setText("Hexa Decimal");
		this.jRadioButton14.setBackground(new java.awt.Color(204, 204, 255));
		this.buttonGroup5.add(this.jRadioButton14);
		this.jRadioButton14.setText("Binary");
		r6 = new javax.swing.GroupLayout(this.jPanel14);
		this.jPanel14.setLayout(r6);
		r6.setHorizontalGroup(r6
				.createParallelGroup(javax.swing.GroupLayout$Alignment.LEADING)
				.addGroup(
						r6.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										r6.createParallelGroup(
												javax.swing.GroupLayout$Alignment.LEADING)
												.addComponent(
														this.jRadioButton12)
												.addComponent(
														this.jRadioButton13)
												.addComponent(
														this.jRadioButton14))
								.addContainerGap(44, Short.MAX_VALUE)));
		r6.setVerticalGroup(r6
				.createParallelGroup(javax.swing.GroupLayout$Alignment.LEADING)
				.addGroup(
						r6.createSequentialGroup()
								.addComponent(this.jRadioButton12)
								.addPreferredGap(
										javax.swing.LayoutStyle$ComponentPlacement.RELATED)
								.addComponent(this.jRadioButton13)
								.addPreferredGap(
										javax.swing.LayoutStyle$ComponentPlacement.RELATED)
								.addComponent(this.jRadioButton14)));
		r7 = new javax.swing.GroupLayout(this.jPanel10);
		this.jPanel10.setLayout(r7);
		r7.setHorizontalGroup(r7
				.createParallelGroup(javax.swing.GroupLayout$Alignment.LEADING)
				.addGroup(
						r7.createSequentialGroup()
								.addGap(18, 18, 18)
								.addGroup(
										r7.createParallelGroup(
												javax.swing.GroupLayout$Alignment.LEADING)
												.addGroup(
														r7.createSequentialGroup()
																.addGap(21, 21,
																		21)
																.addComponent(
																		this.jButton3,
																		-2, 81,
																		-2))
												.addGroup(
														r7.createParallelGroup(
																javax.swing.GroupLayout$Alignment.TRAILING,
																false)
																.addComponent(
																		this.jPanel14,
																		javax.swing.GroupLayout$Alignment.LEADING,
																		-2, -1,
																		-2)
																.addComponent(
																		this.jTextField1,
																		javax.swing.GroupLayout$Alignment.LEADING,
																		-1,
																		151,
																		Short.MAX_VALUE)
																.addGroup(
																		javax.swing.GroupLayout$Alignment.LEADING,
																		r7.createSequentialGroup()
																				.addComponent(
																						this.jRadioButton6)
																				.addGap(18,
																						18,
																						18)
																				.addComponent(
																						this.jRadioButton7))))
								.addContainerGap(19, Short.MAX_VALUE)));
		r7.setVerticalGroup(r7
				.createParallelGroup(javax.swing.GroupLayout$Alignment.LEADING)
				.addGroup(
						r7.createSequentialGroup()
								.addContainerGap()
								.addComponent(this.jTextField1, -2, -1, -2)
								.addPreferredGap(
										javax.swing.LayoutStyle$ComponentPlacement.RELATED)
								.addComponent(this.jPanel14, -2, -1, -2)
								.addPreferredGap(
										javax.swing.LayoutStyle$ComponentPlacement.UNRELATED)
								.addGroup(
										r7.createParallelGroup(
												javax.swing.GroupLayout$Alignment.BASELINE)
												.addComponent(
														this.jRadioButton6)
												.addComponent(
														this.jRadioButton7))
								.addPreferredGap(
										javax.swing.LayoutStyle$ComponentPlacement.UNRELATED)
								.addComponent(this.jButton3)
								.addContainerGap(23, Short.MAX_VALUE)));
		this.jPanel13.setBackground(new java.awt.Color(204, 204, 255));
		this.jPanel13.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Auto Selection"));
		this.jButton4.setText("Random Read");
		this.jButton4.addActionListener(new cachesimulator$9(this));
		this.jButton1.setText("Random Write");
		this.jButton1.addActionListener(new cachesimulator$10(this));
		r8 = new javax.swing.GroupLayout(this.jPanel13);
		this.jPanel13.setLayout(r8);
		r8.setHorizontalGroup(r8
				.createParallelGroup(javax.swing.GroupLayout$Alignment.LEADING)
				.addGroup(
						r8.createSequentialGroup()
								.addGap(31, 31, 31)
								.addGroup(
										r8.createParallelGroup(
												javax.swing.GroupLayout$Alignment.TRAILING)
												.addComponent(
														this.jButton1,
														javax.swing.GroupLayout$Alignment.LEADING,
														-1, 124,
														Short.MAX_VALUE)
												.addComponent(
														this.jButton4,
														javax.swing.GroupLayout$Alignment.LEADING,
														-1, 124,
														Short.MAX_VALUE))
								.addGap(33, 33, 33)));
		r8.setVerticalGroup(r8
				.createParallelGroup(javax.swing.GroupLayout$Alignment.LEADING)
				.addGroup(
						r8.createSequentialGroup()
								.addGap(19, 19, 19)
								.addComponent(this.jButton4)
								.addPreferredGap(
										javax.swing.LayoutStyle$ComponentPlacement.UNRELATED)
								.addComponent(this.jButton1)
								.addContainerGap(31, Short.MAX_VALUE)));
		this.jTable1.setBackground(new java.awt.Color(204, 204, 255));
		this.jTable1.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { new Object[] { null, null, null } },
				new String[] { "Tag Bits", "Index Bits", "Offset bits" }));
		this.jScrollPane1.setViewportView(this.jTable1);
		r9 = new javax.swing.GroupLayout(this.jPanel4);
		this.jPanel4.setLayout(r9);
		r9.setHorizontalGroup(r9
				.createParallelGroup(javax.swing.GroupLayout$Alignment.LEADING)
				.addGroup(
						r9.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										r9.createParallelGroup(
												javax.swing.GroupLayout$Alignment.LEADING)
												.addComponent(
														this.jScrollPane1, -1,
														200, Short.MAX_VALUE)
												.addComponent(this.jPanel13,
														-2, -1, -2)
												.addComponent(this.jPanel10,
														-2, -1, -2))
								.addContainerGap()));
		r9.setVerticalGroup(r9
				.createParallelGroup(javax.swing.GroupLayout$Alignment.LEADING)
				.addGroup(
						r9.createSequentialGroup()
								.addComponent(this.jPanel10, -2, -1, -2)
								.addPreferredGap(
										javax.swing.LayoutStyle$ComponentPlacement.RELATED)
								.addComponent(this.jPanel13, -2, -1, -2)
								.addGap(18, 18, 18)
								.addComponent(this.jScrollPane1, -2, 43, -2)
								.addContainerGap(114, Short.MAX_VALUE)));
		this.jPanel5.setBackground(new java.awt.Color(204, 204, 255));
		this.jPanel5.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Mapping"));
		this.jPanel5.setCursor(new java.awt.Cursor(0));
		this.jPanel5.setDebugGraphicsOptions(-1);
		this.jPanel5.setMaximumSize(new java.awt.Dimension(514, 502));
		this.jPanel5.setMinimumSize(new java.awt.Dimension(514, 502));
		this.jPanel5.setVisible(true);
		this.jPanel7.setBackground(new java.awt.Color(204, 204, 255));
		this.jPanel7.setBorder(javax.swing.BorderFactory
				.createTitledBorder("explanation"));
		this.jPanel7.setMaximumSize(new java.awt.Dimension(482, 476));
		this.jPanel7.setMinimumSize(new java.awt.Dimension(482, 476));
		this.jTextArea2.setColumns(20);
		this.jTextArea2.setRows(5);
		this.jScrollPane3.setViewportView(this.jTextArea2);
		r10 = new javax.swing.GroupLayout(this.jPanel7);
		this.jPanel7.setLayout(r10);
		r10.setHorizontalGroup(r10.createParallelGroup(
				javax.swing.GroupLayout$Alignment.LEADING).addComponent(
				this.jScrollPane3, -1, 440, Short.MAX_VALUE));
		r10.setVerticalGroup(r10.createParallelGroup(
				javax.swing.GroupLayout$Alignment.LEADING).addComponent(
				this.jScrollPane3, -1, 83, Short.MAX_VALUE));
		r11 = new javax.swing.GroupLayout(this.jPanel5);
		this.jPanel5.setLayout(r11);
		r11.setHorizontalGroup(r11.createParallelGroup(
				javax.swing.GroupLayout$Alignment.LEADING).addGroup(
				r11.createSequentialGroup().addContainerGap()
						.addComponent(this.jPanel7, -2, 452, -2)
						.addContainerGap(-1, Short.MAX_VALUE)));
		r11.setVerticalGroup(r11.createParallelGroup(
				javax.swing.GroupLayout$Alignment.LEADING).addGroup(
				r11.createSequentialGroup()
						.addComponent(this.jPanel7, -2, 110, -2)
						.addContainerGap(448, Short.MAX_VALUE)));
		this.jPanel6.setBackground(new java.awt.Color(204, 204, 255));
		this.jPanel6.setMaximumSize(new java.awt.Dimension(138, 502));
		this.jPanel6.setMinimumSize(new java.awt.Dimension(138, 502));
		this.jPanel9.setBackground(new java.awt.Color(204, 204, 255));
		this.jPanel9.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Cache miss/hit"));
		this.jTextField2.setEditable(false);
		this.jTextField2.setText("0");
		this.jTextField3.setEditable(false);
		this.jTextField3.setText("0");
		this.jLabel3.setText("Cache hits");
		this.jLabel4.setText("Cache misses");
		r12 = new javax.swing.GroupLayout(this.jPanel9);
		this.jPanel9.setLayout(r12);
		r12.setHorizontalGroup(r12
				.createParallelGroup(javax.swing.GroupLayout$Alignment.LEADING)
				.addGroup(
						r12.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										r12.createParallelGroup(
												javax.swing.GroupLayout$Alignment.LEADING)
												.addGroup(
														r12.createSequentialGroup()
																.addComponent(
																		this.jLabel3)
																.addContainerGap(
																		50,
																		Short.MAX_VALUE))
												.addGroup(
														javax.swing.GroupLayout$Alignment.TRAILING,
														r12.createSequentialGroup()
																.addComponent(
																		this.jTextField2,
																		-2, 62,
																		-2)
																.addContainerGap())
												.addGroup(
														r12.createSequentialGroup()
																.addComponent(
																		this.jLabel4)
																.addContainerGap(
																		36,
																		Short.MAX_VALUE))
												.addGroup(
														javax.swing.GroupLayout$Alignment.TRAILING,
														r12.createSequentialGroup()
																.addComponent(
																		this.jTextField3,
																		-2, 62,
																		-2)
																.addContainerGap()))));
		r12.setVerticalGroup(r12
				.createParallelGroup(javax.swing.GroupLayout$Alignment.LEADING)
				.addGroup(
						r12.createSequentialGroup()
								.addComponent(this.jLabel3)
								.addPreferredGap(
										javax.swing.LayoutStyle$ComponentPlacement.RELATED)
								.addComponent(this.jTextField2, -2, -1, -2)
								.addGap(18, 18, 18).addComponent(this.jLabel4)
								.addGap(8, 8, 8)
								.addComponent(this.jTextField3, -2, -1, -2)
								.addContainerGap(-1, Short.MAX_VALUE)));
		this.jPanel12.setBackground(new java.awt.Color(204, 204, 255));
		this.jPanel12.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Given Adresses"));
		this.jTextArea1.setColumns(20);
		this.jTextArea1.setEditable(false);
		this.jTextArea1.setRows(5);
		this.jScrollPane2.setViewportView(this.jTextArea1);
		r13 = new javax.swing.GroupLayout(this.jPanel12);
		this.jPanel12.setLayout(r13);
		r13.setHorizontalGroup(r13.createParallelGroup(
				javax.swing.GroupLayout$Alignment.LEADING).addComponent(
				this.jScrollPane2, -1, 110, Short.MAX_VALUE));
		r13.setVerticalGroup(r13.createParallelGroup(
				javax.swing.GroupLayout$Alignment.LEADING).addGroup(
				r13.createSequentialGroup()
						.addContainerGap()
						.addComponent(this.jScrollPane2, -1, 286,
								Short.MAX_VALUE).addContainerGap()));
		r14 = new javax.swing.GroupLayout(this.jPanel6);
		this.jPanel6.setLayout(r14);
		r14.setHorizontalGroup(r14
				.createParallelGroup(javax.swing.GroupLayout$Alignment.LEADING)
				.addGroup(
						r14.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										r14.createParallelGroup(
												javax.swing.GroupLayout$Alignment.TRAILING,
												false)
												.addComponent(
														this.jPanel12,
														javax.swing.GroupLayout$Alignment.LEADING,
														-1, -1, Short.MAX_VALUE)
												.addComponent(
														this.jPanel9,
														javax.swing.GroupLayout$Alignment.LEADING,
														-1, -1, Short.MAX_VALUE))
								.addContainerGap(-1, Short.MAX_VALUE)));
		r14.setVerticalGroup(r14.createParallelGroup(
				javax.swing.GroupLayout$Alignment.LEADING).addGroup(
				r14.createSequentialGroup().addContainerGap()
						.addComponent(this.jPanel9, -2, -1, -2)
						.addGap(18, 18, 18)
						.addComponent(this.jPanel12, -2, -1, -2)
						.addContainerGap(83, Short.MAX_VALUE)));
		r15 = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(r15);
		r15.setHorizontalGroup(r15
				.createParallelGroup(javax.swing.GroupLayout$Alignment.LEADING)
				.addGroup(
						r15.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										r15.createParallelGroup(
												javax.swing.GroupLayout$Alignment.TRAILING,
												false)
												.addComponent(this.jPanel1, -1,
														-1, Short.MAX_VALUE)
												.addGroup(
														javax.swing.GroupLayout$Alignment.LEADING,
														r15.createSequentialGroup()
																.addComponent(
																		this.jPanel4,
																		-2,
																		231, -2)
																.addPreferredGap(
																		javax.swing.LayoutStyle$ComponentPlacement.RELATED)
																.addComponent(
																		this.jPanel5,
																		-2,
																		484, -2)
																.addPreferredGap(
																		javax.swing.LayoutStyle$ComponentPlacement.RELATED)
																.addComponent(
																		this.jPanel6,
																		-1,
																		-1,
																		Short.MAX_VALUE)))
								.addContainerGap(23, Short.MAX_VALUE)));
		r15.setVerticalGroup(r15
				.createParallelGroup(javax.swing.GroupLayout$Alignment.LEADING)
				.addGroup(
						r15.createSequentialGroup()
								.addContainerGap()
								.addComponent(this.jPanel1, -2, 228, -2)
								.addPreferredGap(
										javax.swing.LayoutStyle$ComponentPlacement.RELATED)
								.addGroup(
										r15.createParallelGroup(
												javax.swing.GroupLayout$Alignment.LEADING)
												.addComponent(this.jPanel4, -2,
														-1, -2)
												.addComponent(this.jPanel5, -2,
														-1, -2)
												.addComponent(this.jPanel6, -2,
														-1, -2))
								.addGap(349, 349, 349)));
	}

	private void choice4ItemStateChanged(java.awt.event.ItemEvent arg0) {
	}

	private void choice3ItemStateChanged(java.awt.event.ItemEvent arg0) {
		if (this.choice3.getSelectedIndex() == 0) {
			this.choice2.removeAll();
			this.choice2.add("4");
			this.choice2.add("8");
			this.choice2.add("16");
			this.choice2.add("32");
			this.choice4.removeAll();
			this.choice4.add("4");
			this.choice4.add("8");
			this.choice4.add("16");
			this.choice4.add("32");
		} else {
			if (this.choice3.getSelectedIndex() == 1) {
				this.choice2.removeAll();
				this.choice2.add("8");
				this.choice2.add("16");
				this.choice2.add("32");
				this.choice2.add("64");
				this.choice4.removeAll();
				this.choice4.add("8");
				this.choice4.add("16");
				this.choice4.add("32");
				this.choice4.add("64");
			}
			if (this.choice3.getSelectedIndex() == 2) {
				this.choice2.removeAll();
				this.choice2.add("16");
				this.choice2.add("32");
				this.choice2.add("64");
				this.choice2.add("128");
				this.choice4.removeAll();
				this.choice4.add("16");
				this.choice4.add("32");
				this.choice4.add("64");
				this.choice4.add("128");
			}
			if (this.choice3.getSelectedIndex() == 3) {
				this.choice2.removeAll();
				this.choice2.add("32");
				this.choice2.add("64");
				this.choice2.add("128");
				this.choice2.add("256");
				this.choice4.removeAll();
				this.choice4.add("32");
				this.choice4.add("64");
				this.choice4.add("128");
				this.choice4.add("256");
			}
			if (this.choice3.getSelectedIndex() == 4) {
				this.choice2.removeAll();
				this.choice2.add("64");
				this.choice2.add("128");
				this.choice2.add("256");
				this.choice2.add("512");
				this.choice4.removeAll();
				this.choice4.add("64");
				this.choice4.add("128");
				this.choice4.add("256");
				this.choice4.add("512");
			}
		}
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent arg0) {
		super.values_ini();
		this.jTextArea2.setText("");
		this.jTextArea1.setText("");
		this.TextArea1_counter = 0;
		this.TextArea2_counter = 1;
		this.jTextField3.setText("0");
		this.jTextField2.setText("0");
	}

	private void jButton3ActionPerformed(java.awt.event.ActionEvent arg0) {
		r2 = this.jTextField1.getText();
		r3 = null;
		r3 = super.Any_to_binary(r2);
		if (this.jRadioButton6.isSelected() && r3 != null) {
			this.Read_write = 1;
			Fill_table(r3);
			if (this.Ark.Mapping == 1)
				Map_direct(r3);
			if (this.Ark.Mapping == 2)
				Map_setassociate(r3);
		} else if (this.jRadioButton7.isSelected() && r3 != null) {
			this.Read_write = 2;
			Fill_table(r3);
			if (this.Ark.Write_policy == 1) {
				if (this.Ark.Mapping == 1)
					super.writeback_direct(r3);
				if (this.Ark.Mapping == 2)
					super.writeback_setassociate(r3);
			}
			if (this.Ark.Write_policy == 2) {
				if (this.Ark.Mapping == 1)
					super.writethrough_direct(r3);
				if (this.Ark.Mapping == 2)
					super.writethrough_setassociate(r3);
			}
			if (this.Ark.Write_policy == 3) {
				if (this.Ark.Mapping == 1)
					super.writethroughno_direct(r3);
				if (this.Ark.Mapping == 2)
					super.writethroughno_setassociate(r3);
			}
		}
	}

	private void jButton4ActionPerformed(java.awt.event.ActionEvent arg0) {
		this.Read_write = 1;
		this.newly_added_cache = 16;
		this.newly_added_main = 16;
		r2 = new java.util.Random();
		r3 = r2.nextInt(10000);
		r4 = r3 % this.Ark.MainMemory;
		r5 = Integer.toBinaryString(r4);
		this.jTextField1.setText(r5);
		Fill_table(r5);
		if (this.Ark.Mapping == 1)
			Map_direct(r5);
		else if (this.Ark.Mapping == 2)
			Map_setassociate(r5);
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent arg0) {
		this.Read_write = 2;
		this.newly_added_cache = 16;
		this.newly_added_main = 16;
		r2 = new java.util.Random();
		r3 = r2.nextInt(10000);
		r4 = r3 % this.Ark.MainMemory;
		r5 = Integer.toBinaryString(r4);
		this.jTextField1.setText(r5);
		Fill_table(r5);
		if (this.Ark.Write_policy == 1) {
			if (this.Ark.Mapping == 1)
				super.writeback_direct(r5);
			if (this.Ark.Mapping == 2)
				super.writeback_setassociate(r5);
		} else if (this.Ark.Write_policy == 2) {
			if (this.Ark.Mapping == 1)
				super.writethrough_direct(r5);
			if (this.Ark.Mapping == 2)
				super.writethrough_setassociate(r5);
		}
		if (this.Ark.Write_policy == 3) {
			if (this.Ark.Mapping == 1)
				super.writethroughno_direct(r5);
			if (this.Ark.Mapping == 2)
				super.writethroughno_setassociate(r5);
		}
	}

	private void jRadioButton11ActionPerformed(java.awt.event.ActionEvent arg0) {
	}

	private void jTextField1ActionPerformed(java.awt.event.ActionEvent arg0) {
	}

	private void jRadioButton12ActionPerformed(java.awt.event.ActionEvent arg0) {
	}

	private void values_ini() {
		this.Ark.arrays_init();
		this.Ark.MainMemory = Integer.parseInt(this.choice2.getSelectedItem());
		this.Ark.BlockSize = Integer.parseInt(this.choice3.getSelectedItem());
		this.Ark.CacheMemory = Integer.parseInt(this.choice4.getSelectedItem());
		this.Ark.Associativity = Integer.parseInt(this.choice5
				.getSelectedItem());
		this.Ark.Number_MainBlocks = this.Ark.MainMemory / this.Ark.BlockSize;
		this.Ark.Number_CacheBlocks = this.Ark.CacheMemory / this.Ark.BlockSize;
		if (this.jRadioButton1.isSelected()) {
			this.Ark.Mapping = 1;
			this.Ark.Associativity = 1;
		} else {
			if (this.jRadioButton11.isSelected()) {
				this.Ark.Mapping = 2;
				this.Ark.Number_sets = 1;
				this.Ark.Associativity = this.Ark.Number_CacheBlocks;
			}
			this.Ark.Mapping = 2;
			this.Ark.Number_sets = this.Ark.Number_CacheBlocks
					/ this.Ark.Associativity;
		}
		if (this.jRadioButton3.isSelected())
			this.Ark.Replacement_policy = 1;
		else {
			if (this.jRadioButton4.isSelected())
				this.Ark.Replacement_policy = 2;
			if (this.jRadioButton5.isSelected())
				this.Ark.Replacement_policy = 3;
		}
		if (this.jRadioButton8.isSelected())
			this.Ark.Write_policy = 1;
		else {
			if (this.jRadioButton9.isSelected())
				this.Ark.Write_policy = 2;
			if (this.jRadioButton10.isSelected())
				this.Ark.Write_policy = 3;
		}
		r1 = getGraphics();
		paint(r1);
		this.Paint_flag = 1;
		repaint();
	}

	public void paint(java.awt.Graphics arg0) {
		super.paintComponents(arg0);
		if (this.Paint_flag == 1) {
			this.Ark.Draw_labels_nowriteback(arg0);
			if (this.Ark.Mapping == 1)
				this.Ark.Draw_ini_Direct(arg0);
			else
				this.Ark.Draw_ini_setassociate(arg0);
			this.Ark.Draw_ini_Main(arg0);
		} else {
			if (this.Paint_flag != 2)
				if (this.Paint_flag == 3) {
					if (this.Ark.Write_policy == 1) {
						this.Ark.Draw_labels(arg0);
						if (this.Ark.Mapping == 1)
							Draw_map_Direct(arg0);
						else if (this.Ark.Mapping == 2)
							Draw_map_setAssociate(arg0);
						Draw_map_Main(arg0);
					} else if (this.Ark.Write_policy == 2) {
						this.Ark.Draw_labels_nowriteback(arg0);
						if (this.Ark.Mapping == 1)
							Draw_map_Direct(arg0);
						else if (this.Ark.Mapping == 2)
							Draw_map_setAssociate(arg0);
						Draw_map_Main(arg0);
					}
					if (this.Ark.Write_policy == 3) {
						this.Ark.Draw_labels(arg0);
						if (this.Ark.Mapping == 1)
							Draw_map_Direct(arg0);
						else if (this.Ark.Mapping == 2)
							Draw_map_setAssociate(arg0);
						Draw_map_Main(arg0);
					}
				}
			if (this.Ark.Write_policy == 1)
				this.Ark.Draw_labels(arg0);
			else
				this.Ark.Draw_labels_nowriteback(arg0);
			if (this.Ark.Mapping == 1) {
				Draw_map_Direct(arg0);
				Draw_map_Main(arg0);
			}
			if (this.Ark.Mapping == 2) {
				Draw_map_setAssociate(arg0);
				Draw_map_Main(arg0);
			}
		}
	}

	private int no_bits(int arg0) {
		r2 = 0;
		while (arg0 != 1) {
			arg0 = arg0 / 2;
			++r2;
		}
		return r2;
	}

	void Fill_table(String arg0) {
		r2 = 0;
		r3 = this.Ark.BlockSize;
		r4 = this.Ark.BlockSize;
		r6 = 0;
		r2 = super.no_bits(r3);
		r3 = this.Ark.Number_MainBlocks * this.Ark.BlockSize;
		r7 = 0;
		r7 = super.no_bits(r3);
		r5 = this.Ark.Number_CacheBlocks / this.Ark.Associativity;
		r3 = r5;
		r6 = super.no_bits(r3);
		r8 = "0";
		r9 = r7 - arg0.length();
		while (r9 > 0) {
			--r9;
			arg0 = r8 + arg0;
		}
		r10 = r2 + r6;
		r11 = arg0.length();
		Fill_TextArea1(arg0);
		r12 = arg0.substring(r11 - r2);
		r13 = arg0.substring(r11 - (r2 + r6), r11 - r2);
		this.str_tag = arg0.substring(0, r11 - (r2 + r6));
		this.jTable1.setValueAt(r12, 0, 2);
		this.jTable1.setValueAt(r13, 0, 1);
		this.jTable1.setValueAt(this.str_tag, 0, 0);
	}

	void Fill_TextArea1(String arg0) {
		if (this.Read_write == 1)
			arg0 = arg0 + " - R";
		else
			arg0 = arg0 + " - w";
		this.TextArea1_counter = this.TextArea1_counter + 1;
		this.jTextArea1.setText(this.jTextArea1.getText() + "\n"
				+ Integer.toString(this.TextArea1_counter) + ")  " + arg0);
	}

	void Fill_TextArea2(String arg0) {
	}

	void Map_direct(String arg0) {
		r2 = Integer.parseInt(arg0, 2);
		r3 = r2 / this.Ark.BlockSize;
		r4 = r3 % (this.Ark.Number_CacheBlocks / this.Ark.Associativity);
		this.newly_added_cache = r4;
		this.newly_added_main = r3;
		this.Ark.array_tags[r4] = this.str_tag;
		this.Paint_flag = 2;
		if (this.Ark.main[r3] == 16 && this.Ark.cache[r4] == 16) {
			cache_miss();
			this.Ark.cache[r4] = r3;
			this.Ark.main[r3] = r4;
			this.jTextArea2.setText(this.jTextArea2.getText() + "\n"
					+ this.TextArea2_counter++ + ")" + "The block " + r3
					+ " is brought into cacheblock " + r4);
		} else {
			if (this.Ark.main[r3] != 16) {
				cache_hit();
				this.jTextArea2.setText(this.jTextArea2.getText() + "\n"
						+ this.TextArea2_counter++ + ")"
						+ " The block required already exits in the cache");
			}
			if (this.Ark.main[r3] == 16 && this.Ark.cache[r4] != 16) {
				cache_miss();
				if (this.Ark.array_dirtybits[r4] == 1) {
					this.jTextArea2
							.setText(this.jTextArea2.getText()
									+ "\n"
									+ this.TextArea2_counter++
									+ ")"
									+ "The block "
									+ this.Ark.cache[r4]
									+ " is written back into the main memory first as its dirty bit is set to 1");
					this.jTextArea2.setText(this.jTextArea2.getText() + "\n"
							+ " The block " + this.Ark.cache[r4]
							+ " is replaced by " + r3 + "  at cachebock " + r4);
				} else
					this.jTextArea2.setText(this.jTextArea2.getText() + "\n"
							+ this.TextArea2_counter++ + ")" + " The block "
							+ this.Ark.cache[r4] + " is replaced by " + r3
							+ "  at cachebock " + r4);
				this.Ark.main[this.Ark.cache[r4]] = 16;
				this.Ark.cache[r4] = r3;
				this.Ark.main[r3] = r4;
				this.Ark.array_dirtybits[r4] = 0;
			}
		}
		repaint();
	}

	void Map_setassociate(String arg0) {
		r2 = Integer.parseInt(arg0, 2);
		r3 = r2 / this.Ark.BlockSize;
		r4 = r3 % (this.Ark.Number_CacheBlocks / this.Ark.Associativity);
		this.newly_added_cache = r4;
		this.newly_added_main = r3;
		this.Paint_flag = 2;
		r5 = issetfree(r4 * this.Ark.Associativity);
		if (this.Ark.main[r3] != 16) {
			cache_hit();
			this.jTextArea2.setText(this.jTextArea2.getText() + "\n"
					+ this.TextArea2_counter++ + ")"
					+ "The block required already exits in the cache");
			r6 = super.find_newcache(r4 * this.Ark.Associativity);
			this.newly_added_cache = r6;
			this.Ark.array_times_LRU[r6] = 0;
		} else {
			if (this.Ark.main[r3] == 16 && r5 != 16) {
				cache_miss();
				this.Ark.array_tags[r5] = this.str_tag;
				this.newly_added_cache = r5;
				this.Ark.array_times_LRU[r5] = 0;
				this.Ark.array_times_FIFO[r5] = 0;
				this.Ark.main[r3] = r5;
				this.Ark.cache[r5] = r3;
				this.jTextArea2.setText(this.jTextArea2.getText() + "\n"
						+ this.TextArea2_counter++ + ")"
						+ "The block required is brought into set " + r4);
			}
			if (this.Ark.main[r3] == 16 && r5 == 16) {
				cache_miss();
				r6 = 0;
				if (this.Ark.Replacement_policy == 1) {
					r6 = super.LRU_Replaceable(r4 * this.Ark.Associativity);
					this.Ark.array_times_LRU[r6] = 0;
				} else {
					if (this.Ark.Replacement_policy == 2)
						r6 = super.random_Replaceable(r4
								* this.Ark.Associativity);
					r6 = super.FIFO_Replaceable(r4 * this.Ark.Associativity);
					this.Ark.array_times_FIFO[r6] = 0;
				}
				if (this.Ark.array_dirtybits[r6] == 1)
					this.jTextArea2
							.setText(this.jTextArea2.getText()
									+ "\n"
									+ this.TextArea2_counter++
									+ ")"
									+ "The block in "
									+ this.Ark.cache[r6]
									+ " is updated into the main memory before it is removed from cache as its dirty bit is set to 1");
				else
					this.jTextArea2.setText(this.jTextArea2.getText() + "\n"
							+ this.TextArea2_counter++ + ")" + "The block in "
							+ this.Ark.cache[r6]
							+ " is removed as it is being replaced");
				this.Ark.array_tags[r6] = this.str_tag;
				this.newly_added_cache = r6;
				this.Ark.main[this.Ark.cache[r6]] = 16;
				this.Ark.main[r3] = r6;
				this.Ark.cache[r6] = r3;
				this.Ark.array_dirtybits[r6] = 0;
				this.jTextArea2.setText(this.jTextArea2.getText() + "\n"
						+ "The required block is brought into set " + r4);
			}
		}
		r6 = 0;
		while (r6 < 16) {
			if (this.Ark.cache[r6] != 16) {
				this.Ark.array_times_FIFO[r6] = this.Ark.array_times_FIFO[r6] + 1;
				this.Ark.array_times_LRU[r6] = this.Ark.array_times_LRU[r6] + 1;
			}
			++r6;
		}
		repaint();
	}

	void Draw_map_Direct(java.awt.Graphics arg0) {
		r2 = 0;
		r3 = 0;
		while (r3 < this.Ark.Number_CacheBlocks) {
			arg0.clearRect(this.Ark.cache_xstart - 35, this.Ark.cache_ystart
					+ r2, 34, 20);
			arg0.drawRect(this.Ark.cache_xstart - 35, this.Ark.cache_ystart
					+ r2, 34, 20);
			if (this.Ark.Write_policy == 1)
				arg0.drawString(Integer.toString(this.Ark.array_dirtybits[r3]),
						this.Ark.cache_xstart - 50, this.Ark.cache_ystart + r2
								+ 15);
			arg0.clearRect(this.Ark.cache_xstart, this.Ark.cache_ystart + r2,
					100, 20);
			arg0.drawRect(this.Ark.cache_xstart, this.Ark.cache_ystart + r2,
					100, 20);
			arg0.drawString("" + r3, this.Ark.cache_xstart + 110,
					this.Ark.cache_ystart + r2 + 15);
			if (this.Ark.cache[r3] != 16) {
				if (r3 == this.newly_added_cache) {
					arg0.setColor(java.awt.Color.GRAY);
					arg0.fillRect(this.Ark.cache_xstart, this.Ark.cache_ystart
							+ r2, 100, 20);
					arg0.setColor(java.awt.Color.BLACK);
				}
				arg0.drawString(Integer.toString(this.Ark.cache[r3]),
						this.Ark.cache_xstart + 40, this.Ark.cache_ystart + r2
								+ 15);
				arg0.drawString(this.Ark.array_tags[r3],
						this.Ark.cache_xstart - 30, this.Ark.cache_ystart + r2
								+ 13);
			}
			r2 = r2 + 20;
			if (r3 != this.Ark.Number_CacheBlocks - 1) {
				arg0.clearRect(this.Ark.cache_xstart, this.Ark.cache_ystart
						+ r2, 100, 2);
				arg0.drawRect(this.Ark.cache_xstart,
						this.Ark.cache_ystart + r2, 100, 2);
				r2 = r2 + 2;
			}
			++r3;
		}
	}

	void Draw_map_setAssociate(java.awt.Graphics arg0) {
		r2 = 0;
		r3 = 0;
		r4 = 0;
		while (r4 < this.Ark.Number_CacheBlocks) {
			r5 = this.Ark.cache_ystart + r2 + 10;
			arg0.drawLine(this.Ark.cache_xstart + 100 + 3,
					this.Ark.cache_ystart + r2 + 10,
					this.Ark.cache_xstart + 100 + 10, this.Ark.cache_ystart
							+ r2 + 10);
			r6 = 0;
			while (r6 < this.Ark.Associativity) {
				arg0.clearRect(this.Ark.cache_xstart - 35,
						this.Ark.cache_ystart + r2, 34, 20);
				arg0.drawRect(this.Ark.cache_xstart - 35, this.Ark.cache_ystart
						+ r2, 34, 20);
				if (this.Ark.Write_policy == 1)
					arg0.drawString(
							Integer.toString(this.Ark.array_dirtybits[r4]),
							this.Ark.cache_xstart - 50, this.Ark.cache_ystart
									+ r2 + 15);
				arg0.clearRect(this.Ark.cache_xstart, this.Ark.cache_ystart
						+ r2, 100, 20);
				arg0.drawRect(this.Ark.cache_xstart,
						this.Ark.cache_ystart + r2, 100, 20);
				if (this.Ark.cache[r4] != 16) {
					if (r4 == this.newly_added_cache) {
						arg0.setColor(java.awt.Color.GRAY);
						arg0.fillRect(this.Ark.cache_xstart,
								this.Ark.cache_ystart + r2, 100, 20);
						arg0.setColor(java.awt.Color.BLACK);
					}
					arg0.drawString(Integer.toString(this.Ark.cache[r4]),
							this.Ark.cache_xstart + 40, this.Ark.cache_ystart
									+ r2 + 15);
					arg0.drawString(this.Ark.array_tags[r4],
							this.Ark.cache_xstart - 30, this.Ark.cache_ystart
									+ r2 + 13);
				}
				r2 = r2 + 20;
				++r6;
				++r4;
			}
			arg0.drawLine(this.Ark.cache_xstart + 100 + 10, r5,
					this.Ark.cache_xstart + 100 + 10, this.Ark.cache_ystart
							+ r2 - 10);
			arg0.drawLine(this.Ark.cache_xstart + 100 + 3,
					this.Ark.cache_ystart + r2 - 10,
					this.Ark.cache_xstart + 100 + 10, this.Ark.cache_ystart
							+ r2 - 10);
			arg0.drawString("set " + r3, this.Ark.cache_xstart + 100 + 11, (r5
					+ this.Ark.cache_ystart + r2 - 10) / 2);
			r3 = r3 + 1;
			if (r4 != this.Ark.Number_CacheBlocks) {
				arg0.clearRect(this.Ark.cache_xstart, this.Ark.cache_ystart
						+ r2, 100, 2);
				arg0.drawRect(this.Ark.cache_xstart,
						this.Ark.cache_ystart + r2, 100, 2);
				r2 = r2 + 2;
			}
		}
	}

	void Draw_map_Main(java.awt.Graphics arg0) {
		r2 = 0;
		r3 = 0;
		while (r3 < this.Ark.Number_MainBlocks) {
			arg0.clearRect(this.Ark.main_xstart, this.Ark.main_ystart + r2,
					100, 20);
			arg0.drawRect(this.Ark.main_xstart, this.Ark.main_ystart + r2, 100,
					20);
			if (r3 == this.newly_added_main) {
				arg0.setColor(java.awt.Color.GRAY);
				arg0.fillRect(this.Ark.main_xstart, this.Ark.main_ystart + r2,
						100, 20);
				arg0.setColor(java.awt.Color.BLACK);
			}
			arg0.drawString("" + r3, this.Ark.main_xstart + 40,
					this.Ark.main_ystart + r2 + 15);
			r2 = r2 + 20;
			if (r3 != this.Ark.Number_MainBlocks - 1) {
				arg0.clearRect(this.Ark.main_xstart, this.Ark.main_ystart + r2,
						100, 2);
				arg0.drawRect(this.Ark.main_xstart, this.Ark.main_ystart + r2,
						100, 2);
				r2 = r2 + 2;
			}
			++r3;
		}
	}

	int issetfree(int arg0) {
		r2 = arg0;
		while (r2 < arg0 + this.Ark.Associativity) {
			if (this.Ark.cache[r2] == 16)
				return r2;
			++r2;
		}
		return 16;
	}

	private int LRU_Replaceable(int arg0) {
		r2 = 0;
		r3 = 0;
		r4 = arg0;
		while (r4 < arg0 + this.Ark.Associativity) {
			if (r2 < this.Ark.array_times_LRU[r4]) {
				r3 = r4;
				r2 = this.Ark.array_times_LRU[r4];
			}
			++r4;
		}
		return r3;
	}

	private int FIFO_Replaceable(int arg0) {
		r2 = 0;
		r3 = 0;
		r4 = arg0;
		while (r4 < arg0 + this.Ark.Associativity) {
			if (r2 < this.Ark.array_times_FIFO[r4]) {
				r3 = r4;
				r2 = this.Ark.array_times_FIFO[r4];
			}
			++r4;
		}
		return r3;
	}

	private int random_Replaceable(int arg0) {
		r2 = 0;
		r3 = new java.util.Random();
		r4 = r3.nextInt(1000);
		r5 = r4 % this.Ark.Associativity;
		r2 = arg0 * this.Ark.Associativity + r5;
		return r2;
	}

	private int find_newcache(int arg0) {
		r2 = arg0;
		while (r2 < arg0 + this.Ark.Associativity) {
			if (this.Ark.cache[r2] == this.newly_added_main)
				return r2;
			++r2;
		}
		return 0;
	}

	private void writeback_direct(String arg0) {
		r2 = Integer.parseInt(arg0, 2);
		r3 = r2 / this.Ark.BlockSize;
		r4 = r3 % (this.Ark.Number_CacheBlocks / this.Ark.Associativity);
		this.newly_added_cache = r4;
		this.newly_added_main = r3;
		this.Ark.array_tags[r4] = this.str_tag;
		this.Paint_flag = 3;
		if (this.Ark.main[r3] == 16 && this.Ark.cache[r4] == 16) {
			cache_miss();
			this.Ark.cache[r4] = r3;
			this.Ark.main[r3] = r4;
			this.Ark.array_dirtybits[r4] = 1;
			this.jTextArea2.setText(this.jTextArea2.getText() + "\n"
					+ this.TextArea2_counter++ + ")" + " The block " + r3
					+ " is brought into cacheblock " + r4
					+ " and its dirtybit is set to 1");
		} else {
			if (this.Ark.main[r3] != 16) {
				cache_hit();
				this.Ark.array_dirtybits[r4] = 1;
				this.jTextArea2
						.setText(this.jTextArea2.getText()
								+ "\n"
								+ this.TextArea2_counter++
								+ ")"
								+ " The block required "
								+ r3
								+ " already exits in the cache and is updated only in the cacheblock and its dirty bit is set to 1");
			}
			if (this.Ark.main[r3] == 16 && this.Ark.cache[r4] != 16) {
				cache_miss();
				if (this.Ark.array_dirtybits[r4] == 1)
					this.jTextArea2.setText(this.jTextArea2.getText() + "\n"
							+ " The block " + this.Ark.cache[r4]
							+ " is written back into the main memory");
				this.jTextArea2.setText(this.jTextArea2.getText() + "\n"
						+ this.TextArea2_counter++ + ")" + " The block "
						+ this.Ark.cache[r4] + " is replaced by " + r3
						+ "  at cachebock" + r4
						+ " and its dirtybit is set to 0");
				this.Ark.main[this.Ark.cache[r4]] = 16;
				this.Ark.cache[r4] = r3;
				this.Ark.main[r3] = r4;
				this.Ark.array_dirtybits[r4] = 0;
			}
		}
		repaint();
	}

	private void writeback_setassociate(String arg0) {
		r2 = Integer.parseInt(arg0, 2);
		r3 = r2 / this.Ark.BlockSize;
		r4 = r3 % (this.Ark.Number_CacheBlocks / this.Ark.Associativity);
		this.newly_added_cache = r4;
		this.newly_added_main = r3;
		this.Paint_flag = 3;
		r5 = issetfree(r4 * this.Ark.Associativity);
		if (this.Ark.main[r3] != 16) {
			cache_hit();
			this.Ark.array_dirtybits[this.Ark.main[r3]] = 1;
			this.jTextArea2
					.setText(this.jTextArea2.getText()
							+ "\n"
							+ this.TextArea2_counter++
							+ ")"
							+ " The block required "
							+ r3
							+ " already exits in the cache and is updated only in the cacheblock and its dirty bit is set to 1");
			r6 = super.find_newcache(r4 * this.Ark.Associativity);
			this.newly_added_cache = r6;
			this.Ark.array_times_LRU[r6] = 0;
		} else {
			if (this.Ark.main[r3] == 16 && r5 != 16) {
				cache_miss();
				this.Ark.array_tags[r5] = this.str_tag;
				this.newly_added_cache = r5;
				this.Ark.array_times_LRU[r5] = 0;
				this.Ark.array_times_FIFO[r5] = 0;
				this.Ark.main[r3] = r5;
				this.Ark.cache[r5] = r3;
				this.jTextArea2.setText(this.jTextArea2.getText() + "\n"
						+ this.TextArea2_counter++ + ")" + " The block " + r3
						+ " is brought into cacheset " + r4
						+ " and its dirtybit is set to 1");
				this.Ark.array_dirtybits[r5] = 1;
			}
			if (this.Ark.main[r3] == 16 && r5 == 16) {
				cache_miss();
				r6 = 0;
				if (this.Ark.Replacement_policy == 1) {
					r6 = super.LRU_Replaceable(r4 * this.Ark.Associativity);
					this.Ark.array_times_LRU[r6] = 0;
				} else {
					if (this.Ark.Replacement_policy == 2)
						r6 = super.random_Replaceable(r4
								* this.Ark.Associativity);
					r6 = super.FIFO_Replaceable(r4 * this.Ark.Associativity);
					this.Ark.array_times_FIFO[r6] = 0;
				}
				if (this.Ark.array_dirtybits[r6] == 1)
					this.jTextArea2
							.setText(this.jTextArea2.getText()
									+ "\n"
									+ this.TextArea2_counter++
									+ ")"
									+ " The block in "
									+ this.Ark.cache[r6]
									+ " is updated into the main memory before it is removed from cache as its dirty bit is set to 1");
				else
					this.jTextArea2.setText(this.jTextArea2.getText() + "\n"
							+ this.TextArea2_counter++ + ")" + " The block in "
							+ this.Ark.cache[r6]
							+ " is removed as it is being replaced");
				this.Ark.array_tags[r6] = this.str_tag;
				this.Ark.array_dirtybits[r6] = 1;
				this.newly_added_cache = r6;
				this.Ark.main[this.Ark.cache[r6]] = 16;
				this.Ark.main[r3] = r6;
				this.Ark.cache[r6] = r3;
				this.Ark.array_dirtybits[r6] = 0;
				this.jTextArea2
						.setText(this.jTextArea2.getText()
								+ "\n"
								+ " The main memory block required is updated and brought into this place ");
			}
		}
		r6 = 0;
		while (r6 < 16) {
			if (this.Ark.cache[r6] != 16) {
				this.Ark.array_times_FIFO[r6] = this.Ark.array_times_FIFO[r6] + 1;
				this.Ark.array_times_LRU[r6] = this.Ark.array_times_LRU[r6] + 1;
			}
			++r6;
		}
		repaint();
	}

	private void writethrough_direct(String arg0) {
		r2 = Integer.parseInt(arg0, 2);
		r3 = r2 / this.Ark.BlockSize;
		r4 = r3 % (this.Ark.Number_CacheBlocks / this.Ark.Associativity);
		this.newly_added_cache = r4;
		this.newly_added_main = r3;
		this.Ark.array_tags[r4] = this.str_tag;
		this.Paint_flag = 3;
		if (this.Ark.main[r3] == 16 && this.Ark.cache[r4] == 16) {
			cache_miss();
			this.Ark.cache[r4] = r3;
			this.Ark.main[r3] = r4;
			this.jTextArea2.setText(this.jTextArea2.getText() + "\n"
					+ this.TextArea2_counter++ + ")" + " The block " + r3
					+ " is brought into cacheblock " + r4
					+ " and both are updated");
		} else {
			if (this.Ark.main[r3] != 16) {
				cache_hit();
				this.jTextArea2
						.setText(this.jTextArea2.getText()
								+ "\n"
								+ this.TextArea2_counter++
								+ ")"
								+ "The block required "
								+ r3
								+ " already exits in the cache and is updated  in the cacheblock and corresponding main memoryblock"
								+ r3);
			}
			if (this.Ark.main[r3] == 16 && this.Ark.cache[r4] != 16) {
				cache_miss();
				this.jTextArea2.setText(this.jTextArea2.getText() + "\n"
						+ this.TextArea2_counter++ + ")" + " The block "
						+ this.Ark.cache[r4] + " is replaced by " + r3
						+ "  at cachebock" + r4 + " and both are updated");
				this.Ark.main[this.Ark.cache[r4]] = 16;
				this.Ark.cache[r4] = r3;
				this.Ark.main[r3] = r4;
			}
		}
		repaint();
	}

	private void writethrough_setassociate(String arg0) {
		r2 = Integer.parseInt(arg0, 2);
		r3 = r2 / this.Ark.BlockSize;
		r4 = r3 % (this.Ark.Number_CacheBlocks / this.Ark.Associativity);
		this.newly_added_cache = r4;
		this.newly_added_main = r3;
		this.Paint_flag = 3;
		r5 = issetfree(r4 * this.Ark.Associativity);
		if (this.Ark.main[r3] != 16) {
			cache_hit();
			this.jTextArea2
					.setText(this.jTextArea2.getText()
							+ "\n"
							+ this.TextArea2_counter++
							+ ")"
							+ " The block required "
							+ r3
							+ " already exits in the cache and is updated in both the places");
			r6 = super.find_newcache(r4 * this.Ark.Associativity);
			this.newly_added_cache = r6;
			this.Ark.array_times_LRU[r6] = 0;
		} else {
			if (this.Ark.main[r3] == 16 && r5 != 16) {
				cache_miss();
				this.Ark.array_tags[r5] = this.str_tag;
				this.newly_added_cache = r5;
				this.Ark.array_times_LRU[r5] = 0;
				this.Ark.array_times_FIFO[r5] = 0;
				this.Ark.main[r3] = r5;
				this.Ark.cache[r5] = r3;
				this.jTextArea2.setText(this.jTextArea2.getText() + "\n"
						+ this.TextArea2_counter++ + ")" + " The block " + r3
						+ " is brought into cacheset " + r4
						+ " and both of them are updated");
			}
			if (this.Ark.main[r3] == 16 && r5 == 16) {
				cache_miss();
				r6 = 0;
				if (this.Ark.Replacement_policy == 1) {
					r6 = super.LRU_Replaceable(r4 * this.Ark.Associativity);
					this.Ark.array_times_LRU[r6] = 0;
				} else {
					if (this.Ark.Replacement_policy == 2)
						r6 = super.random_Replaceable(r4
								* this.Ark.Associativity);
					r6 = super.FIFO_Replaceable(r4 * this.Ark.Associativity);
					this.Ark.array_times_FIFO[r6] = 0;
				}
				this.Ark.array_tags[r6] = this.str_tag;
				this.newly_added_cache = r6;
				this.Ark.main[this.Ark.cache[r6]] = 16;
				this.Ark.main[r3] = r6;
				this.Ark.cache[r6] = r3;
				this.jTextArea2
						.setText(this.jTextArea2.getText()
								+ "\n"
								+ this.TextArea2_counter++
								+ ")"
								+ " The block required is brought into this place and its dirty bit is set to 1");
			}
		}
		r6 = 0;
		while (r6 < 16) {
			if (this.Ark.cache[r6] != 16) {
				this.Ark.array_times_FIFO[r6] = this.Ark.array_times_FIFO[r6] + 1;
				this.Ark.array_times_LRU[r6] = this.Ark.array_times_LRU[r6] + 1;
			}
			++r6;
		}
		repaint();
	}

	private void writethroughno_direct(String arg0) {
		r2 = Integer.parseInt(arg0, 2);
		r3 = r2 / this.Ark.BlockSize;
		r4 = r3 % (this.Ark.Number_CacheBlocks / this.Ark.Associativity);
		this.newly_added_cache = r4;
		this.newly_added_main = r3;
		this.Ark.array_tags[r4] = this.str_tag;
		this.Paint_flag = 3;
		if (this.Ark.main[r3] != 16) {
			cache_hit();
			this.jTextArea2
					.setText(this.jTextArea2.getText()
							+ "\n"
							+ this.TextArea2_counter++
							+ ")"
							+ " The block required "
							+ r3
							+ " already exits in the cache and is updated  in the cacheblock and corresponding main memoryblock"
							+ r3);
		} else if (this.Ark.main[r3] == 16) {
			cache_miss();
			this.newly_added_cache = 16;
			this.jTextArea2.setText(this.jTextArea2.getText() + "\n"
					+ this.TextArea2_counter++ + ")" + "  The block " + r3
					+ " is updated and not brought into cache");
		}
		repaint();
	}

	private void writethroughno_setassociate(String arg0) {
		r2 = Integer.parseInt(arg0, 2);
		r3 = r2 / this.Ark.BlockSize;
		r4 = r3 % (this.Ark.Number_CacheBlocks / this.Ark.Associativity);
		this.newly_added_cache = r4;
		this.newly_added_main = r3;
		this.Paint_flag = 3;
		r5 = issetfree(r4 * this.Ark.Associativity);
		if (this.Ark.main[r3] != 16) {
			cache_hit();
			this.jTextArea2
					.setText(this.jTextArea2.getText()
							+ "\n"
							+ this.TextArea2_counter++
							+ ")"
							+ " The block required "
							+ r3
							+ " already exits in the cache and is updated in both the places");
			r6 = super.find_newcache(r4 * this.Ark.Associativity);
			this.newly_added_cache = r6;
		} else if (this.Ark.main[r3] == 16) {
			cache_miss();
			this.newly_added_cache = 16;
			this.jTextArea2.setText(this.jTextArea2.getText() + "\n"
					+ this.TextArea2_counter++ + ")" + " The block " + r3
					+ " is updated and not brought into cache");
		}
		repaint();
	}

	void cache_hit() {
		r1 = Integer.parseInt(this.jTextField2.getText());
		this.jTextField2.setText(Integer.toString(r1 + 1));
	}

	void cache_miss() {
		r1 = Integer.parseInt(this.jTextField3.getText());
		this.jTextField3.setText(Integer.toString(r1 + 1));
	}

	private String Any_to_binary(String arg0) {
		r2 = null;
		if (this.jRadioButton12.isSelected())
			r2 = Integer.toBinaryString(Integer.parseInt(arg0));
		else {
			if (this.jRadioButton13.isSelected()) {
				r3 = Integer.parseInt(arg0, 16);
				r2 = Integer.toBinaryString(r3);
			}
			if (this.jRadioButton14.isSelected())
				r2 = arg0;
		}
		return r2;
	}
}

/*
 * Generated by DecoJer 0.9.1, a Java-bytecode decompiler.
 * DecoJer Copyright (C) 2009-2013 André Pankraz. All Rights Reserved.
 *
 * Class File Version: 50 (Java 6)
 * Source File Name: cachesimulator.java
 */