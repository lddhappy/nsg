/*     */ package nsg;
/*     */ 
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class ParameterDialog extends JDialog implements NSGParameters
/*     */ {
/*     */   static final long serialVersionUID = 0L;
/*     */   
/*     */   private void saveDefault()
/*     */   {
/*  31 */     String userHome = System.getProperty("user.home");
/*  32 */     File f = new File(userHome + "/nsg/");
/*  33 */     if (!f.exists()) {
/*  34 */       f.mkdirs();
/*     */     }
/*     */     try {
/*  37 */       Writer out = new java.io.BufferedWriter(new FileWriter(userHome + 
/*  38 */         "/nsg/wirelessDefault.txt"));
/*     */       
/*  40 */       out.write(this.simTimeItem.getText() + " ");
/*  41 */       out.write(this.traceFileItem.getText() + " ");
/*  42 */       out.write(this.namFileItem.getText() + " ");
/*     */       
/*     */ 
/*  45 */       out.write(this.channelItem.getSelectedIndex() + " ");
/*  46 */       out.write(this.propagationItem.getSelectedIndex() + " ");
/*  47 */       out.write(this.phyItem.getSelectedIndex() + " ");
/*  48 */       out.write(this.macItem.getSelectedIndex() + " ");
/*  49 */       out.write(this.queueItem.getSelectedIndex() + " ");
/*  50 */       out.write(this.linkLayerItem.getSelectedIndex() + " ");
/*  51 */       out.write(this.antennaItem.getSelectedIndex() + " ");
/*  52 */       out.write(this.maxPacketInIfqItem.getText() + " ");
/*  53 */       out.write(this.routingItem.getSelectedIndex() + " ");
/*  54 */       out.write(this.agentTraceItem.getSelectedIndex() + " ");
/*  55 */       out.write(this.routerTraceItem.getSelectedIndex() + " ");
/*  56 */       out.write(this.channelItem.getSelectedIndex() + " ");
/*  57 */       out.write(this.macTraceItem.getSelectedIndex() + " ");
/*  58 */       out.write(this.movementTraceItem.getSelectedIndex() + " ");
/*     */       
/*     */ 
/*  61 */       out.write(this.Gt_Box.isSelected() + " ");
/*  62 */       out.write(this.Gr_Box.isSelected() + " ");
/*  63 */       out.write(this.L_Box.isSelected() + " ");
/*  64 */       out.write(this.freq_Box.isSelected() + " ");
/*  65 */       out.write(this.bandwidth_Box.isSelected() + " ");
/*  66 */       out.write(this.Pt_Box.isSelected() + " ");
/*  67 */       out.write(this.CPThresh_Box.isSelected() + " ");
/*  68 */       out.write(this.CSThresh_Box.isSelected() + " ");
/*  69 */       out.write(this.RXThresh_Box.isSelected() + " ");
/*  70 */       out.write(this.dataRate_Box.isSelected() + " ");
/*  71 */       out.write(this.basicRate_Box.isSelected() + " ");
/*     */       
/*  73 */       out.write(this.Gt_.getText() + " ");
/*  74 */       out.write(this.Gr_.getText() + " ");
/*  75 */       out.write(this.L_.getText() + " ");
/*  76 */       out.write(this.freq_.getText() + " ");
/*  77 */       out.write(this.bandwidth_.getText() + " ");
/*  78 */       out.write(this.Pt_.getText() + " ");
/*  79 */       out.write(this.CPThresh_.getText() + " ");
/*  80 */       out.write(this.CSThresh_.getText() + " ");
/*  81 */       out.write(this.RXThresh_.getText() + " ");
/*  82 */       out.write(this.dataRate_.getText() + " ");
/*  83 */       out.write(this.basicRate_.getText() + " ");
/*     */       
/*  85 */       out.flush();
/*  86 */       out.close();
/*     */     } catch (IOException e) {
/*  88 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   private void loadDefault() {
/*  93 */     String userHome = System.getProperty("user.home");
/*  94 */     File f = new File(userHome + "/nsg/wirelessDefault.txt");
/*  95 */     if (!f.exists()) {
/*  96 */       return;
/*     */     }
/*     */     try {
/*  99 */       BufferedReader in = new BufferedReader(new FileReader(userHome + 
/* 100 */         "/nsg/wirelessDefault.txt"));
/* 101 */       String[] result = in.readLine().split(" ");
/* 102 */       this.simTimeItem.setText(result[0]);
/* 103 */       this.traceFileItem.setText(result[1]);
/* 104 */       this.namFileItem.setText(result[2]);
/*     */       
/* 106 */       this.channelItem.setSelectedIndex(Integer.valueOf(result[3]).intValue());
/* 107 */       this.propagationItem.setSelectedIndex(Integer.valueOf(result[4]).intValue());
/* 108 */       this.phyItem.setSelectedIndex(Integer.valueOf(result[5]).intValue());
/* 109 */       this.macItem.setSelectedIndex(Integer.valueOf(result[6]).intValue());
/* 110 */       this.queueItem.setSelectedIndex(Integer.valueOf(result[7]).intValue());
/* 111 */       this.linkLayerItem.setSelectedIndex(Integer.valueOf(result[8]).intValue());
/* 112 */       this.antennaItem.setSelectedIndex(Integer.valueOf(result[9]).intValue());
/* 113 */       this.maxPacketInIfqItem.setText(result[10]);
/* 114 */       this.routingItem.setSelectedIndex(Integer.valueOf(result[11]).intValue());
/* 115 */       this.agentTraceItem.setSelectedIndex(Integer.valueOf(result[12]).intValue());
/* 116 */       this.routerTraceItem.setSelectedIndex(Integer.valueOf(result[13]).intValue());
/* 117 */       this.channelItem.setSelectedIndex(Integer.valueOf(result[14]).intValue());
/* 118 */       this.macTraceItem.setSelectedIndex(Integer.valueOf(result[15]).intValue());
/* 119 */       this.movementTraceItem.setSelectedIndex(Integer.valueOf(result[16]).intValue());
/*     */       
/*     */ 
/* 122 */       if (Boolean.valueOf(result[17]).booleanValue())
/* 123 */         this.Gt_Box.setSelected(true);
/* 124 */       if (Boolean.valueOf(result[18]).booleanValue())
/* 125 */         this.Gr_Box.setSelected(true);
/* 126 */       if (Boolean.valueOf(result[19]).booleanValue())
/* 127 */         this.L_Box.setSelected(true);
/* 128 */       if (Boolean.valueOf(result[20]).booleanValue())
/* 129 */         this.freq_Box.setSelected(true);
/* 130 */       if (Boolean.valueOf(result[21]).booleanValue())
/* 131 */         this.bandwidth_Box.setSelected(true);
/* 132 */       if (Boolean.valueOf(result[22]).booleanValue())
/* 133 */         this.Pt_Box.setSelected(true);
/* 134 */       if (Boolean.valueOf(result[23]).booleanValue())
/* 135 */         this.CPThresh_Box.setSelected(true);
/* 136 */       if (Boolean.valueOf(result[24]).booleanValue())
/* 137 */         this.CSThresh_Box.setSelected(true);
/* 138 */       if (Boolean.valueOf(result[25]).booleanValue())
/* 139 */         this.RXThresh_Box.setSelected(true);
/* 140 */       if (Boolean.valueOf(result[26]).booleanValue())
/* 141 */         this.dataRate_Box.setSelected(true);
/* 142 */       if (Boolean.valueOf(result[27]).booleanValue()) {
/* 143 */         this.basicRate_Box.setSelected(true);
/*     */       }
/* 145 */       this.Gt_.setText(result[28]);
/* 146 */       this.Gr_.setText(result[29]);
/* 147 */       this.L_.setText(result[30]);
/* 148 */       this.freq_.setText(result[31]);
/* 149 */       this.bandwidth_.setText(result[32]);
/* 150 */       this.Pt_.setText(result[33]);
/* 151 */       this.CPThresh_.setText(result[34]);
/* 152 */       this.CSThresh_.setText(result[35]);
/* 153 */       this.RXThresh_.setText(result[36]);
/* 154 */       this.dataRate_.setText(result[37]);
/* 155 */       this.basicRate_.setText(result[38]);
/*     */       
/* 157 */       in.close();
/*     */     } catch (IOException e) {
/* 159 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   private void loadConfig()
/*     */   {
/* 165 */     String userHome = System.getProperty("user.home");
/* 166 */     File f = new File(userHome + "/nsg/");
/* 167 */     if (!f.exists()) {
/* 168 */       f.mkdirs();
/*     */     }
/* 170 */     f = new File(userHome + "/nsg/wirelessConfig.txt");
/* 171 */     if (!f.exists()) {
/*     */       try {
/* 173 */         Writer out = new java.io.BufferedWriter(new FileWriter(f));
/* 174 */         out.write("1 Channel/WirelessChannel\n");
/* 175 */         out.write("2 Propagation/TwoRayGround\n");
/* 176 */         out.write("3 Phy/WirelessPhy\n");
/* 177 */         out.write("4 Mac/802_11\n");
/* 178 */         out.write("5 Queue/DropTail/PriQueue\n");
/* 179 */         out.write("6 LL\n");
/* 180 */         out.write("7 Antenna/OmniAntenna\n");
/* 181 */         out.write("8 DSDV DSR AODV TORA\n");
/* 182 */         out.flush();
/* 183 */         out.close();
/*     */       } catch (Exception e) {
/* 185 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */     
/*     */     try
/*     */     {
/* 191 */       BufferedReader in = new BufferedReader(new FileReader(f));
/* 192 */       String line = in.readLine();
/* 193 */       while (line != null) {
/* 194 */         String[] result = line.split(" ");
/* 195 */         int type = Integer.valueOf(result[0]).intValue();
/* 196 */         switch (type) {
/*     */         case 1: 
/* 198 */           for (int i = 1; i < result.length; i++) {
/* 199 */             this.channelItem.addItem(result[i]);
/*     */           }
/* 201 */           break;
/*     */         case 2: 
/* 203 */           for (int i = 1; i < result.length; i++) {
/* 204 */             this.propagationItem.addItem(result[i]);
/*     */           }
/* 206 */           break;
/*     */         case 3: 
/* 208 */           for (int i = 1; i < result.length; i++) {
/* 209 */             this.phyItem.addItem(result[i]);
/*     */           }
/* 211 */           break;
/*     */         case 4: 
/* 213 */           for (int i = 1; i < result.length; i++) {
/* 214 */             this.macItem.addItem(result[i]);
/*     */           }
/* 216 */           break;
/*     */         case 5: 
/* 218 */           for (int i = 1; i < result.length; i++) {
/* 219 */             this.queueItem.addItem(result[i]);
/*     */           }
/* 221 */           break;
/*     */         case 6: 
/* 223 */           for (int i = 1; i < result.length; i++) {
/* 224 */             this.linkLayerItem.addItem(result[i]);
/*     */           }
/* 226 */           break;
/*     */         case 7: 
/* 228 */           for (int i = 1; i < result.length; i++) {
/* 229 */             this.antennaItem.addItem(result[i]);
/*     */           }
/* 231 */           break;
/*     */         case 8: 
/* 233 */           for (int i = 1; i < result.length; i++) {
/* 234 */             this.routingItem.addItem(result[i]);
/*     */           }
/*     */         }
/*     */         
/* 238 */         line = in.readLine();
/*     */       }
/* 240 */       in.close();
/*     */     } catch (IOException e) {
/* 242 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 249 */   JTextField simTimeItem = new JTextField("10.0");
/* 250 */   JTextField traceFileItem = new JTextField("out.tr");
/* 251 */   JTextField namFileItem = new JTextField("out.nam");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 256 */   JComboBox channelItem = new JComboBox();
/* 257 */   JComboBox propagationItem = new JComboBox();
/* 258 */   JComboBox phyItem = new JComboBox();
/* 259 */   JComboBox macItem = new JComboBox();
/* 260 */   JComboBox queueItem = new JComboBox();
/* 261 */   JComboBox linkLayerItem = new JComboBox();
/* 262 */   JComboBox antennaItem = new JComboBox();
/* 263 */   JTextField maxPacketInIfqItem = new JTextField("50");
/* 264 */   JComboBox routingItem = new JComboBox();
/* 265 */   JComboBox agentTraceItem = new JComboBox(new String[] { "ON", "OFF" });
/* 266 */   JComboBox routerTraceItem = new JComboBox(new String[] { "ON", "OFF" });
/* 267 */   JComboBox macTraceItem = new JComboBox(new String[] { "ON", "OFF" });
/* 268 */   JComboBox movementTraceItem = new JComboBox(new String[] { "ON", "OFF" });
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 273 */   JCheckBox Gt_Box = new JCheckBox();
/* 274 */   JCheckBox Gr_Box = new JCheckBox();
/* 275 */   JCheckBox L_Box = new JCheckBox();
/* 276 */   JCheckBox freq_Box = new JCheckBox();
/* 277 */   JCheckBox bandwidth_Box = new JCheckBox();
/* 278 */   JCheckBox Pt_Box = new JCheckBox();
/* 279 */   JCheckBox CPThresh_Box = new JCheckBox();
/* 280 */   JCheckBox CSThresh_Box = new JCheckBox();
/* 281 */   JCheckBox RXThresh_Box = new JCheckBox();
/* 282 */   JCheckBox dataRate_Box = new JCheckBox();
/* 283 */   JCheckBox basicRate_Box = new JCheckBox();
/*     */   
/* 285 */   JTextField Gt_ = new JTextField("1", 10);
/* 286 */   JTextField Gr_ = new JTextField("1", 10);
/* 287 */   JTextField L_ = new JTextField("1.0", 10);
/* 288 */   JTextField freq_ = new JTextField("2.472e9", 10);
/* 289 */   JTextField bandwidth_ = new JTextField("11Mb", 10);
/* 290 */   JTextField Pt_ = new JTextField("0.031622777", 10);
/* 291 */   JTextField CPThresh_ = new JTextField("10.0", 10);
/* 292 */   JTextField CSThresh_ = new JTextField("5.011872e-12", 10);
/* 293 */   JTextField RXThresh_ = new JTextField("5.82587e-09", 10);
/* 294 */   JTextField dataRate_ = new JTextField("11Mb", 10);
/* 295 */   JTextField basicRate_ = new JTextField("1Mb", 10);
/*     */   
/* 297 */   JButton done = new JButton("Done");
/* 298 */   JButton save = new JButton("Save as default");
/* 299 */   JButton load = new JButton("Load");
/*     */   
/* 301 */   JTabbedPane tab = new JTabbedPane();
/*     */   
/*     */   public ParameterDialog(JFrame p, int mode) {
/* 304 */     super(p, true);
/* 305 */     loadConfig();
/* 306 */     loadDefault();
/*     */     
/* 308 */     setTitle("Simulation parameters setup");
/*     */     
/* 310 */     int w = Toolkit.getDefaultToolkit().getScreenSize().width;
/* 311 */     int h = Toolkit.getDefaultToolkit().getScreenSize().height;
/* 312 */     setBounds(w / 2 - 250, h / 2 - 300, 600, 600);
/*     */     
/* 314 */     JPanel panel = new JPanel();
/* 315 */     panel.setLayout(new java.awt.FlowLayout());
/* 316 */     JPanel innerPanel = new JPanel();
/* 317 */     innerPanel.setLayout(new GridLayout(3, 2));
/* 318 */     innerPanel.add(new JLabel("Simulation time"));
/* 319 */     innerPanel.add(this.simTimeItem);
/* 320 */     innerPanel.add(new JLabel("Trace File"));
/* 321 */     innerPanel.add(this.traceFileItem);
/* 322 */     innerPanel.add(new JLabel("Nam File"));
/* 323 */     innerPanel.add(this.namFileItem);
/* 324 */     panel.add(innerPanel);
/* 325 */     this.tab.add("Simulation", panel);
/*     */     
/* 327 */     if (mode == 2) {
/* 328 */       panel = new JPanel();
/* 329 */       panel.setLayout(new java.awt.FlowLayout());
/* 330 */       innerPanel = new JPanel();
/* 331 */       innerPanel.setLayout(new GridLayout(14, 2));
/* 332 */       innerPanel.add(new JLabel("Channel type"));
/* 333 */       innerPanel.add(this.channelItem);
/* 334 */       innerPanel.add(new JLabel("Propagation model"));
/* 335 */       innerPanel.add(this.propagationItem);
/* 336 */       innerPanel.add(new JLabel("Phy type"));
/* 337 */       innerPanel.add(this.phyItem);
/* 338 */       innerPanel.add(new JLabel("Mac protocol type"));
/* 339 */       innerPanel.add(this.macItem);
/* 340 */       innerPanel.add(new JLabel("Queue type"));
/* 341 */       innerPanel.add(this.queueItem);
/* 342 */       innerPanel.add(new JLabel("Link layer type"));
/* 343 */       innerPanel.add(this.linkLayerItem);
/* 344 */       innerPanel.add(new JLabel("Antenna type"));
/* 345 */       innerPanel.add(this.antennaItem);
/* 346 */       innerPanel.add(new JLabel("Max packet in queue"));
/* 347 */       innerPanel.add(this.maxPacketInIfqItem);
/* 348 */       innerPanel.add(new JLabel("Routing protocol"));
/* 349 */       innerPanel.add(this.routingItem);
/* 350 */       innerPanel.add(new JLabel("Agent trace"));
/* 351 */       innerPanel.add(this.agentTraceItem);
/* 352 */       innerPanel.add(new JLabel("Router trace"));
/* 353 */       innerPanel.add(this.routerTraceItem);
/* 354 */       innerPanel.add(new JLabel("Mac trace"));
/* 355 */       innerPanel.add(this.macTraceItem);
/* 356 */       innerPanel.add(new JLabel("Movement trace"));
/* 357 */       innerPanel.add(this.movementTraceItem);
/* 358 */       panel.add(innerPanel);
/* 359 */       this.tab.add("Wireless", panel);
/*     */       
/* 361 */       panel = new JPanel();
/* 362 */       panel.setLayout(new java.awt.BorderLayout());
/* 363 */       innerPanel = new JPanel();
/*     */       
/*     */ 
/*     */ 
/* 367 */       JPanel innerPanel2 = new JPanel(new GridLayout(14, 2));
/* 368 */       innerPanel2.add(this.Gt_Box);
/* 369 */       innerPanel2.add(new JLabel("Gt_"));
/* 370 */       innerPanel2.add(this.Gt_);
/* 371 */       innerPanel2.add(this.Gr_Box);
/* 372 */       innerPanel2.add(new JLabel("Gr_"));
/* 373 */       innerPanel2.add(this.Gr_);
/* 374 */       innerPanel2.add(this.L_Box);
/* 375 */       innerPanel2.add(new JLabel("L_"));
/* 376 */       innerPanel2.add(this.L_);
/* 377 */       innerPanel2.add(this.freq_Box);
/* 378 */       innerPanel2.add(new JLabel("freq_"));
/* 379 */       innerPanel2.add(this.freq_);
/* 380 */       innerPanel2.add(this.bandwidth_Box);
/* 381 */       innerPanel2.add(new JLabel("bandwidth_"));
/* 382 */       innerPanel2.add(this.bandwidth_);
/* 383 */       innerPanel2.add(this.Pt_Box);
/* 384 */       innerPanel2.add(new JLabel("Pt_"));
/* 385 */       innerPanel2.add(this.Pt_);
/* 386 */       innerPanel2.add(this.CPThresh_Box);
/* 387 */       innerPanel2.add(new JLabel("CPThresh_"));
/* 388 */       innerPanel2.add(this.CPThresh_);
/* 389 */       innerPanel2.add(this.CSThresh_Box);
/* 390 */       innerPanel2.add(new JLabel("CSThresh_"));
/* 391 */       innerPanel2.add(this.CSThresh_);
/* 392 */       innerPanel2.add(this.RXThresh_Box);
/* 393 */       innerPanel2.add(new JLabel("RXThresh_"));
/* 394 */       innerPanel2.add(this.RXThresh_);
/* 395 */       innerPanel2.add(this.dataRate_Box);
/* 396 */       innerPanel2.add(new JLabel("dataRate_"));
/* 397 */       innerPanel2.add(this.dataRate_);
/* 398 */       innerPanel2.add(this.basicRate_Box);
/* 399 */       innerPanel2.add(new JLabel("basicRate_"));
/* 400 */       innerPanel2.add(this.basicRate_);
/* 401 */       innerPanel.add(innerPanel2);
/*     */       
/* 403 */       panel.add(innerPanel, "Center");
/*     */       
/* 405 */       panel
/* 406 */         .add(
/* 407 */         new JLabel(
/* 408 */         "<html><center><font color=#ff0000>The default values are based on Wu Xiuchao's technical report.<br>Those valuses can be used in simulating Orinoco 802.11b 11Mbps<br>PC card with 22.5m transmission range. However, Wu Xiuchao has pointed<br>out that some parameters should be adjusted based on simulation environment.<br>For more details please refer to </font><br><font color=#0000ff>http://www.comp.nus.edu.sg/~wuxiucha/research/reactive/report/80211ChannelinNS2_new.pdf</font>", 
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 414 */         0), "South");
/* 415 */       this.tab.add("Channel", panel);
/*     */     }
/*     */     
/* 418 */     panel = new JPanel();
/* 419 */     panel.add(this.done);
/* 420 */     this.done.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 422 */         ParameterDialog.this.setVisible(false);
/*     */       }
/* 424 */     });
/* 425 */     panel.add(this.save);
/* 426 */     this.save.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 428 */         ParameterDialog.this.saveDefault();
/*     */ 
/*     */ 
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 436 */     });
/* 437 */     getContentPane().add(panel, "South");
/* 438 */     getContentPane().add(this.tab, "Center");
/*     */   }
/*     */ }


/* Location:              C:\Users\Liudongdong\Desktop\NSG2.1.jar!\nsg\ParameterDialog.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */