/*     */ package nsg.menu;
/*     */ 
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.ItemListener;
/*     */ import java.io.PrintStream;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import nsg.NSGParameters;
/*     */ import nsg.component.App;
/*     */ 
/*     */ public class AppMenu extends JDialog implements NSGParameters
/*     */ {
/*     */   static final long serialVersionUID = 0L;
/*  26 */   JButton done = new JButton("Done");
/*     */   
/*     */   App target;
/*  29 */   JLabel l1 = new JLabel("Application type");
/*  30 */   public JComboBox appType = new JComboBox(new String[] { "FTP", "CBR" });
/*  31 */   JLabel l2 = new JLabel("Start time");
/*  32 */   public JTextField startTime = new JTextField("1", 4);
/*  33 */   JLabel l3 = new JLabel("Stop time");
/*  34 */   public JTextField stopTime = new JTextField("2", 4);
/*  35 */   JLabel l4 = new JLabel("Packet size");
/*  36 */   public JTextField packetSize = new JTextField("1000", 4);
/*  37 */   JLabel l5 = new JLabel("Rate");
/*  38 */   public JTextField rate = new JTextField("1", 4);
/*  39 */   JLabel l6 = new JLabel("Interval");
/*  40 */   public JTextField interval = new JTextField("0.005", 4);
/*  41 */   JLabel l7 = new JLabel("Random");
/*  42 */   public JTextField random = new JTextField("false", 4);
/*     */   
/*     */   public AppMenu(JFrame p) {
/*  45 */     super(p, true);
/*  46 */     setTitle("Application parameters setup");
/*     */     
/*  48 */     int w = Toolkit.getDefaultToolkit().getScreenSize().width;
/*  49 */     int h = Toolkit.getDefaultToolkit().getScreenSize().height;
/*  50 */     setBounds(w / 2 - 250, h / 2 - 300, 300, 350);
/*     */     
/*  52 */     JPanel panel = new JPanel();
/*  53 */     panel.setLayout(new FlowLayout());
/*  54 */     JPanel innerPanel = new JPanel();
/*  55 */     innerPanel.setLayout(new GridLayout(7, 2));
/*  56 */     innerPanel.add(this.l1);innerPanel.add(this.appType);
/*  57 */     innerPanel.add(this.l2);innerPanel.add(this.startTime);
/*  58 */     innerPanel.add(this.l3);innerPanel.add(this.stopTime);
/*  59 */     innerPanel.add(this.l4);innerPanel.add(this.packetSize);
/*  60 */     innerPanel.add(this.l5);innerPanel.add(this.rate);
/*     */     
/*  62 */     innerPanel.add(this.l7);innerPanel.add(this.random);
/*  63 */     panel.add(innerPanel);
/*  64 */     getContentPane().add(panel, "Center");
/*  65 */     panel = new JPanel();
/*  66 */     panel.add(this.done);
/*  67 */     getContentPane().add(panel, "South");
/*     */     
/*  69 */     this.done.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/*  71 */         switch (AppMenu.this.appType.getSelectedIndex()) {
/*     */         case 0: 
/*  73 */           AppMenu.this.target.appType = 0;
/*  74 */           AppMenu.this.setupFTP();
/*  75 */           break;
/*     */         case 1: 
/*  77 */           AppMenu.this.target.appType = 1;
/*  78 */           AppMenu.this.setupCBR();
/*  79 */           break;
/*     */         case 2: 
/*  81 */           AppMenu.this.target.appType = 2;
/*  82 */           AppMenu.this.setupPing();
/*  83 */           break;
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         default: 
/*  92 */           System.out.println("App error");
/*  93 */           return;
/*     */         }
/*     */         
/*  96 */         AppMenu.this.target = null;
/*  97 */         AppMenu.this.setVisible(false);
/*     */       }
/*     */       
/* 100 */     });
/* 101 */     this.appType.addItemListener(new ItemListener() {
/*     */       public void itemStateChanged(ItemEvent e) {
/* 103 */         switch (AppMenu.this.appType.getSelectedIndex()) {
/*     */         case 0: 
/* 105 */           AppMenu.this.switchToFTP();
/* 106 */           break;
/*     */         case 1: 
/* 108 */           AppMenu.this.switchToCBR();
/* 109 */           break;
/*     */         case 2: 
/* 111 */           AppMenu.this.switchToPing();
/* 112 */           break;
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */         default: 
/* 118 */           System.out.println("addApp error");
/* 119 */           return;
/*     */         }
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   public void setTarget(App target) {
/* 126 */     this.target = target;
/* 127 */     this.appType.setSelectedIndex(target.appType);
/*     */     
/* 129 */     switch (this.appType.getSelectedIndex()) {
/*     */     case 0: 
/* 131 */       switchToFTP();
/* 132 */       break;
/*     */     case 1: 
/* 134 */       switchToCBR();
/* 135 */       break;
/*     */     case 2: 
/*     */       break;
/*     */     case 3: 
/*     */       break;
/*     */     case 4: 
/*     */       break;
/*     */     default: 
/* 143 */       System.out.println("addApp error");
/* 144 */       return;
/*     */     }
/* 146 */     this.startTime.setText(String.valueOf(target.startTime));
/* 147 */     this.stopTime.setText(String.valueOf(target.stopTime));
/* 148 */     this.packetSize.setText(String.valueOf(target.packetSize));
/* 149 */     this.rate.setText(String.valueOf(target.rate));
/* 150 */     this.random.setText(target.random);
/*     */   }
/*     */   
/*     */   public void setupFTP() {
/* 154 */     this.target.startTime = Float.parseFloat(this.startTime.getText());
/* 155 */     this.target.stopTime = Float.parseFloat(this.stopTime.getText());
/* 156 */     this.target.packetSize = -1;
/* 157 */     this.target.rate = -1.0F;
/* 158 */     this.target.random = "false";
/*     */   }
/*     */   
/* 161 */   public void setupCBR() { this.target.startTime = Float.parseFloat(this.startTime.getText());
/* 162 */     this.target.stopTime = Float.parseFloat(this.stopTime.getText());
/* 163 */     this.target.packetSize = Integer.parseInt(this.packetSize.getText());
/* 164 */     this.target.rate = Float.parseFloat(this.rate.getText());
/* 165 */     this.target.random = this.random.getText();
/*     */   }
/*     */   
/* 168 */   public void setupPing() { this.target.startTime = Float.parseFloat(this.startTime.getText());
/* 169 */     this.target.stopTime = -1.0F;
/* 170 */     this.target.packetSize = -1;
/* 171 */     this.target.rate = -1.0F;
/* 172 */     this.target.random = "false";
/*     */   }
/*     */   
/*     */ 
/*     */   public void switchToCBR()
/*     */   {
/* 178 */     this.l1.setVisible(true);
/* 179 */     this.appType.setVisible(true);
/* 180 */     this.l2.setVisible(true);
/* 181 */     this.startTime.setVisible(true);
/* 182 */     this.l3.setVisible(true);
/* 183 */     this.stopTime.setVisible(true);
/* 184 */     this.l4.setVisible(true);
/* 185 */     this.packetSize.setVisible(true);
/* 186 */     this.l5.setVisible(true);
/* 187 */     this.rate.setVisible(true);
/*     */     
/*     */ 
/* 190 */     this.l7.setVisible(true);
/* 191 */     this.random.setVisible(true);
/*     */   }
/*     */   
/*     */   public void switchToFTP() {
/* 195 */     this.l1.setVisible(true);
/* 196 */     this.appType.setVisible(true);
/* 197 */     this.l2.setVisible(true);
/* 198 */     this.startTime.setVisible(true);
/* 199 */     this.l3.setVisible(true);
/* 200 */     this.stopTime.setVisible(true);
/* 201 */     this.l4.setVisible(false);
/* 202 */     this.packetSize.setVisible(false);
/* 203 */     this.l5.setVisible(false);
/* 204 */     this.rate.setVisible(false);
/*     */     
/*     */ 
/* 207 */     this.l7.setVisible(false);
/* 208 */     this.random.setVisible(false);
/*     */   }
/*     */   
/*     */   public void switchToPing() {
/* 212 */     this.l1.setVisible(true);
/* 213 */     this.appType.setVisible(true);
/* 214 */     this.l2.setVisible(false);
/* 215 */     this.startTime.setVisible(false);
/* 216 */     this.l3.setVisible(false);
/* 217 */     this.stopTime.setVisible(false);
/* 218 */     this.l4.setVisible(false);
/* 219 */     this.packetSize.setVisible(false);
/* 220 */     this.l5.setVisible(false);
/* 221 */     this.rate.setVisible(false);
/*     */     
/*     */ 
/* 224 */     this.l7.setVisible(false);
/* 225 */     this.random.setVisible(false);
/*     */   }
/*     */ }


/* Location:              C:\Users\Liudongdong\Desktop\NSG2.1.jar!\nsg\menu\AppMenu.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */