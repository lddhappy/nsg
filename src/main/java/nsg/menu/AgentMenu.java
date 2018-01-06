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
/*     */ import nsg.component.Agent;
/*     */ 
/*     */ public class AgentMenu extends JDialog implements NSGParameters
/*     */ {
/*     */   static final long serialVersionUID = 0L;
/*  26 */   JButton done = new JButton("Done");
/*     */   
/*     */   Agent target;
/*  29 */   JLabel l1 = new JLabel("Agent type");
/*  30 */   public JComboBox agentType = new JComboBox(new String[] { "TCP", "TCP/Tahoe", "TCP/Reno", "TCP/Newreno", "TCP/Vegas", "TCPSink", "UDP", "NULL" });
/*  31 */   JLabel l2 = new JLabel("Packet size");
/*  32 */   public JTextField packetSize = new JTextField("1500");
/*  33 */   JLabel l3 = new JLabel("Advertised window");
/*  34 */   public JTextField window = new JTextField("");
/*  35 */   JLabel l4 = new JLabel("Congestion window maximum value");
/*  36 */   public JTextField maxcwnd = new JTextField("");
/*  37 */   JLabel l5 = new JLabel("Congestion window init. value");
/*  38 */   public JTextField windowInit = new JTextField("");
/*  39 */   JLabel l6 = new JLabel("Timeout");
/*  40 */   public JTextField tcpTick = new JTextField("");
/*  41 */   JLabel l7 = new JLabel("Maximum burst");
/*  42 */   public JTextField maxburst = new JTextField("");
/*     */   
/*     */   public AgentMenu(JFrame p) {
/*  45 */     super(p, true);
/*  46 */     setTitle("Agent parameters setup");
/*     */     
/*  48 */     int w = Toolkit.getDefaultToolkit().getScreenSize().width;
/*  49 */     int h = Toolkit.getDefaultToolkit().getScreenSize().height;
/*  50 */     setBounds(w / 2 - 250, h / 2 - 300, 500, 400);
/*     */     
/*  52 */     JPanel panel = new JPanel();
/*  53 */     panel.setLayout(new FlowLayout());
/*  54 */     JPanel innerPanel = new JPanel();
/*  55 */     innerPanel.setLayout(new GridLayout(7, 2));
/*  56 */     innerPanel.add(this.l1);innerPanel.add(this.agentType);
/*  57 */     innerPanel.add(this.l2);innerPanel.add(this.packetSize);
/*  58 */     innerPanel.add(this.l3);innerPanel.add(this.window);
/*  59 */     innerPanel.add(this.l4);innerPanel.add(this.maxcwnd);
/*  60 */     innerPanel.add(this.l5);innerPanel.add(this.windowInit);
/*  61 */     innerPanel.add(this.l6);innerPanel.add(this.tcpTick);
/*  62 */     innerPanel.add(this.l7);innerPanel.add(this.maxburst);
/*  63 */     panel.add(innerPanel);
/*  64 */     getContentPane().add(panel, "Center");
/*  65 */     panel = new JPanel();
/*  66 */     panel.add(this.done);
/*  67 */     getContentPane().add(panel, "South");
/*     */     
/*  69 */     this.done.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/*  71 */         switch (AgentMenu.this.agentType.getSelectedIndex()) {
/*     */         case 0: 
/*  73 */           AgentMenu.this.target.agentType = 0;
/*  74 */           AgentMenu.this.setupTCP();
/*  75 */           break;
/*     */         case 1: 
/*  77 */           AgentMenu.this.target.agentType = 4;
/*  78 */           AgentMenu.this.setupTCP();
/*  79 */           break;
/*     */         case 2: 
/*  81 */           AgentMenu.this.target.agentType = 8;
/*  82 */           AgentMenu.this.setupTCP();
/*  83 */           break;
/*     */         case 3: 
/*  85 */           AgentMenu.this.target.agentType = 12;
/*  86 */           AgentMenu.this.setupTCP();
/*  87 */           break;
/*     */         case 4: 
/*  89 */           AgentMenu.this.target.agentType = 16;
/*  90 */           AgentMenu.this.setupTCP();
/*  91 */           break;
/*     */         case 5: 
/*  93 */           AgentMenu.this.target.agentType = 1;
/*  94 */           AgentMenu.this.setupSink();
/*  95 */           break;
/*     */         case 6: 
/*  97 */           AgentMenu.this.target.agentType = 2;
/*  98 */           AgentMenu.this.setupUDP();
/*  99 */           break;
/*     */         case 7: 
/* 101 */           AgentMenu.this.target.agentType = 3;
/* 102 */           AgentMenu.this.setupNull();
/* 103 */           break;
/*     */         default: 
/* 105 */           System.err.println("Agent error");
/*     */         }
/* 107 */         AgentMenu.this.target = null;
/* 108 */         AgentMenu.this.setVisible(false);
/*     */       }
/*     */       
/* 111 */     });
/* 112 */     this.agentType.addItemListener(new ItemListener() {
/*     */       public void itemStateChanged(ItemEvent e) {
/* 114 */         switch (AgentMenu.this.agentType.getSelectedIndex()) {
/*     */         case 0: 
/*     */         case 1: 
/*     */         case 2: 
/*     */         case 3: 
/*     */         case 4: 
/* 120 */           AgentMenu.this.switchToTCP();
/* 121 */           break;
/*     */         case 5: 
/* 123 */           AgentMenu.this.switchToSink();
/* 124 */           break;
/*     */         case 6: 
/* 126 */           AgentMenu.this.switchToUDP();
/* 127 */           break;
/*     */         case 7: 
/* 129 */           AgentMenu.this.switchToNull();
/* 130 */           break;
/*     */         default: 
/* 132 */           System.out.println("app type error");
/* 133 */           return;
/*     */         }
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   public void setTarget(Agent target) {
/* 140 */     this.target = target;
/* 141 */     this.packetSize.setEditable(true);
/* 142 */     switch (target.agentType) {
/*     */     case 0: 
/* 144 */       this.agentType.setSelectedIndex(0);
/* 145 */       break;
/*     */     case 4: 
/* 147 */       this.agentType.setSelectedIndex(1);
/* 148 */       break;
/*     */     case 8: 
/* 150 */       this.agentType.setSelectedIndex(2);
/* 151 */       break;
/*     */     case 12: 
/* 153 */       this.agentType.setSelectedIndex(3);
/* 154 */       break;
/*     */     case 16: 
/* 156 */       this.agentType.setSelectedIndex(4);
/* 157 */       break;
/*     */     case 1: 
/* 159 */       this.agentType.setSelectedIndex(5);
/* 160 */       break;
/*     */     case 2: 
/* 162 */       this.agentType.setSelectedIndex(6);
/* 163 */       break;
/*     */     case 3: 
/* 165 */       this.agentType.setSelectedIndex(7);
/* 166 */       break;
/*     */     case 5: case 6: case 7: case 9: case 10: case 11: case 13: case 14: case 15: default: 
/* 168 */       System.err.println("addAgent error");
/*     */     }
/* 170 */     this.packetSize.setText(String.valueOf(target.packetSize));
/* 171 */     this.window.setText(String.valueOf(target.window));
/* 172 */     this.maxcwnd.setText(String.valueOf(target.maxcwnd));
/* 173 */     this.windowInit.setText(String.valueOf(target.windowInit));
/* 174 */     this.tcpTick.setText(String.valueOf(target.tcpTick));
/* 175 */     this.maxburst.setText(String.valueOf(target.maxburst));
/*     */   }
/*     */   
/*     */   public void setupTCP()
/*     */   {
/* 180 */     this.target.packetSize = Integer.parseInt(this.packetSize.getText());
/* 181 */     this.target.window = Integer.parseInt(this.window.getText());
/* 182 */     this.target.maxcwnd = Integer.parseInt(this.maxcwnd.getText());
/* 183 */     this.target.windowInit = Integer.parseInt(this.windowInit.getText());
/* 184 */     this.target.tcpTick = Integer.parseInt(this.tcpTick.getText());
/* 185 */     this.target.maxburst = Integer.parseInt(this.maxburst.getText());
/*     */   }
/*     */   
/* 188 */   public void setupSink() { this.target.packetSize = -1;
/* 189 */     this.target.window = -1;
/* 190 */     this.target.maxcwnd = -1;
/* 191 */     this.target.windowInit = -1;
/* 192 */     this.target.tcpTick = -1;
/* 193 */     this.target.maxburst = -1;
/*     */   }
/*     */   
/* 196 */   public void setupUDP() { this.target.packetSize = Integer.parseInt(this.packetSize.getText());
/* 197 */     this.target.window = -1;
/* 198 */     this.target.maxcwnd = -1;
/* 199 */     this.target.windowInit = -1;
/* 200 */     this.target.tcpTick = -1;
/* 201 */     this.target.maxburst = -1;
/*     */   }
/*     */   
/* 204 */   public void setupNull() { this.target.packetSize = -1;
/* 205 */     this.target.window = -1;
/* 206 */     this.target.maxcwnd = -1;
/* 207 */     this.target.windowInit = -1;
/* 208 */     this.target.tcpTick = -1;
/* 209 */     this.target.maxburst = -1;
/*     */   }
/*     */   
/*     */   public void switchToTCP() {
/* 213 */     this.l1.setVisible(true);
/* 214 */     this.agentType.setVisible(true);
/* 215 */     this.l2.setVisible(true);
/* 216 */     this.packetSize.setVisible(true);
/* 217 */     this.l3.setVisible(true);
/* 218 */     this.window.setVisible(true);
/* 219 */     this.l4.setVisible(true);
/* 220 */     this.maxcwnd.setVisible(true);
/* 221 */     this.l5.setVisible(true);
/* 222 */     this.windowInit.setVisible(true);
/* 223 */     this.l6.setVisible(true);
/* 224 */     this.tcpTick.setVisible(true);
/* 225 */     this.l7.setVisible(true);
/* 226 */     this.maxburst.setVisible(true);
/*     */   }
/*     */   
/*     */   public void switchToUDP() {
/* 230 */     this.l1.setVisible(true);
/* 231 */     this.agentType.setVisible(true);
/* 232 */     this.l2.setVisible(true);
/* 233 */     this.packetSize.setVisible(true);
/* 234 */     this.l3.setVisible(false);
/* 235 */     this.window.setVisible(false);
/* 236 */     this.l4.setVisible(false);
/* 237 */     this.maxcwnd.setVisible(false);
/* 238 */     this.l5.setVisible(false);
/* 239 */     this.windowInit.setVisible(false);
/* 240 */     this.l6.setVisible(false);
/* 241 */     this.tcpTick.setVisible(false);
/* 242 */     this.l7.setVisible(false);
/* 243 */     this.maxburst.setVisible(false);
/*     */   }
/*     */   
/*     */   public void switchToNull() {
/* 247 */     this.l1.setVisible(true);
/* 248 */     this.agentType.setVisible(true);
/* 249 */     this.l2.setVisible(false);
/* 250 */     this.packetSize.setVisible(false);
/* 251 */     this.l3.setVisible(false);
/* 252 */     this.window.setVisible(false);
/* 253 */     this.l4.setVisible(false);
/* 254 */     this.maxcwnd.setVisible(false);
/* 255 */     this.l5.setVisible(false);
/* 256 */     this.windowInit.setVisible(false);
/* 257 */     this.l6.setVisible(false);
/* 258 */     this.tcpTick.setVisible(false);
/* 259 */     this.l7.setVisible(false);
/* 260 */     this.maxburst.setVisible(false);
/*     */   }
/*     */   
/*     */   public void switchToSink() {
/* 264 */     this.l1.setVisible(true);
/* 265 */     this.agentType.setVisible(true);
/* 266 */     this.l2.setVisible(false);
/* 267 */     this.packetSize.setVisible(false);
/* 268 */     this.l3.setVisible(false);
/* 269 */     this.window.setVisible(false);
/* 270 */     this.l4.setVisible(false);
/* 271 */     this.maxcwnd.setVisible(false);
/* 272 */     this.l5.setVisible(false);
/* 273 */     this.windowInit.setVisible(false);
/* 274 */     this.l6.setVisible(false);
/* 275 */     this.tcpTick.setVisible(false);
/* 276 */     this.l7.setVisible(false);
/* 277 */     this.maxburst.setVisible(false);
/*     */   }
/*     */ }


/* Location:              C:\Users\Liudongdong\Desktop\NSG2.1.jar!\nsg\menu\AgentMenu.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */