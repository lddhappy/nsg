/*     */ package nsg.p2p;
/*     */ 
/*     */ import java.awt.Container;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JInternalFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ 
/*     */ public class ItmTclFrame
/*     */   extends JInternalFrame
/*     */ {
/*     */   String path;
/*  26 */   int routers = 2050;
/*  27 */   int transit = 50;
/*  28 */   int fecN = 6;
/*  29 */   int fecK = 4;
/*  30 */   int bufferSize = 800;
/*  31 */   int serviceTime = 180;
/*  32 */   int recoveryTime = 6;
/*  33 */   int depth = 10;
/*  34 */   int width = 200;
/*  35 */   int numParent = 6;
/*  36 */   int simTime = 300;
/*  37 */   int numSim = 10;
/*     */   
/*  39 */   JTextField pathField = new JTextField("~/csvt-sim/");
/*  40 */   JTextField routerField = new JTextField("990");
/*  41 */   JTextField transitField = new JTextField("30");
/*  42 */   JTextField fecNField = new JTextField("6");
/*  43 */   JTextField fecKField = new JTextField("4");
/*  44 */   JTextField bufferField = new JTextField("800");
/*  45 */   JTextField serviceField = new JTextField("180");
/*  46 */   JTextField recoveryField = new JTextField("6");
/*  47 */   JTextField depthField = new JTextField("20");
/*  48 */   JTextField widthField = new JTextField("50");
/*  49 */   JTextField numParentField = new JTextField("6");
/*  50 */   JTextField simTimeField = new JTextField("180");
/*  51 */   JTextField numSimField = new JTextField("5");
/*  52 */   JButton batchButton = new JButton("Create batch");
/*  53 */   JButton tclButton = new JButton("Create TCL");
/*     */   
/*     */   public ItmTclFrame() {
/*  56 */     super("ItmTclFrame", true, true, true, true);
/*  57 */     uiInit();
/*     */     
/*  59 */     this.batchButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/*  61 */         ItmTclFrame.this.parameterInit();
/*  62 */         File f = new File(ItmTclFrame.this.path);
/*  63 */         if (!f.exists()) f.mkdirs();
/*     */         try
/*     */         {
/*  66 */           ItmTclFrame.this.createBatch();
/*     */         } catch (Exception e) {
/*  68 */           e.printStackTrace();
/*     */         }
/*     */         
/*     */       }
/*  72 */     });
/*  73 */     this.tclButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/*  75 */         ItmTclFrame.this.parameterInit();
/*     */         try {
/*  77 */           for (int i = 0; i < ItmTclFrame.this.numSim; i++) ItmTclFrame.this.createTCL(i);
/*     */         } catch (Exception e) {
/*  79 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void createBatch()
/*     */     throws Exception
/*     */   {
/*  90 */     String filename = this.path + "sim-go";
/*  91 */     PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(filename)));
/*  92 */     for (int i = 0; i < this.numSim; i++) out.println("ns sim" + i + ".tcl > res" + i + ".txt;echo \"done sim" + i + "\";");
/*  93 */     out.flush();
/*  94 */     out.close();
/*  95 */     new File(this.path + "sim-go").setExecutable(true);
/*     */     
/*     */ 
/*  98 */     filename = this.path + "gt-go";
/*  99 */     out = new PrintStream(new BufferedOutputStream(new FileOutputStream(filename)));
/* 100 */     out.println("itm ts" + this.routers + ";");
/* 101 */     for (int i = 0; i < this.numSim; i++) out.println("sgb2ns ts" + this.routers + "-" + i + ".gb ts" + this.routers + "-" + i + ".tcl;rm ts990-" + i + ".gb;");
/* 102 */     out.flush();
/* 103 */     out.close();
/* 104 */     new File(this.path + "gt-go").setExecutable(true);
/*     */     
/*     */ 
/* 107 */     filename = this.path + "ts990";
/* 108 */     out = new PrintStream(new BufferedOutputStream(new FileOutputStream(filename)));
/* 109 */     out.println("## <#method keyword> <#number of graphs> [<#initial seed>]");
/* 110 */     out.println("## <#stubs/xit> <#t-s edges> <#s-s edges>");
/* 111 */     out.println("## <#n> <#scale> <#edgemethod> <#alpha> [<#beta>] [<#gamma>]");
/* 112 */     out.println("## number of nodes = 1*8* (1 + 4*6) = 200");
/* 113 */     out.println("ts " + this.numSim + " 66");
/* 114 */     out.println("8 0 0");
/* 115 */     out.println("6 20 3 1.0");
/* 116 */     out.println("5 20 3 0.5");
/* 117 */     out.println("4 10 3 0.5");
/* 118 */     out.flush();
/* 119 */     out.close();
/*     */   }
/*     */   
/*     */   public void createTCL(int index) throws Exception {
/* 123 */     String filename = this.path + "sim" + index + ".tcl";
/*     */     
/* 125 */     if (this.width < this.numParent) { System.out.println("width<numParent");return; }
/* 126 */     PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(filename)));
/*     */     
/* 128 */     out.println("\n#===================================");
/* 129 */     out.println("#        Initialization               ");
/* 130 */     out.println("#===================================  ");
/* 131 */     out.println("set ns [new Simulator]");
/*     */     
/* 133 */     out.println("\n#===================================");
/* 134 */     out.println("#        Routers Definition           ");
/* 135 */     out.println("#===================================  ");
/* 136 */     for (int a = 0; a < this.routers; a++) {
/* 137 */       out.println("set rt" + a + " [$ns node]");
/*     */     }
/*     */     
/* 140 */     out.println("\n#===================================");
/* 141 */     out.println("#        Put GT-ITM script here       ");
/* 142 */     out.println("#===================================  ");
/* 143 */     InputStream in = new BufferedInputStream(new FileInputStream(this.path + "ts" + this.routers + "-" + index + ".tcl"));
/* 144 */     int ch = in.read();
/* 145 */     while (ch != -1) {
/* 146 */       out.write(ch);
/* 147 */       ch = in.read();
/*     */     }
/* 149 */     in.close();
/*     */     
/* 151 */     out.println("\n#===================================");
/* 152 */     out.println("#        Nodes Definition             ");
/* 153 */     out.println("#===================================  ");
/* 154 */     out.println("# setup VIDEO SERVER");
/* 155 */     out.println("set n0 [$ns node]");
/* 156 */     int routerID = (int)(Math.random() * (this.routers - this.transit));
/* 157 */     routerID += this.transit;
/* 158 */     out.println("$ns duplex-link $n0 $rt" + routerID + " 100.0Mb 1ms DropTail");
/*     */     
/* 160 */     out.println("# setup PEERS");
/* 161 */     for (int a = 1; a <= this.depth * this.width; a++) {
/* 162 */       out.println("set n" + a + " [$ns node]");
/* 163 */       routerID = (int)(Math.random() * (this.routers - this.transit));
/* 164 */       routerID += this.transit;
/* 165 */       out.println("$ns duplex-link $n" + a + " $rt" + routerID + " 100.0Mb 1ms DropTail");
/*     */     }
/*     */     
/* 168 */     out.println("\n#===================================");
/* 169 */     out.println("#        Apps Definition              ");
/* 170 */     out.println("#===================================  ");
/* 171 */     for (int appID = 0; appID <= this.depth * this.width; appID++) {
/* 172 */       out.println("set PeerApp" + appID + " [new Application/PeerApp]");
/* 173 */       out.println("$PeerApp" + appID + " set_id " + appID);
/* 174 */       out.println("$PeerApp" + appID + " set_fecN " + this.fecN);
/* 175 */       out.println("$PeerApp" + appID + " set_fecK " + this.fecK);
/* 176 */       out.println("$PeerApp" + appID + " set_degree " + this.numParent);
/* 177 */       out.println("$PeerApp" + appID + " set_buffer " + this.bufferSize);
/* 178 */       out.println("$PeerApp" + appID + " set_serviceTime " + this.serviceTime);
/* 179 */       out.println("$PeerApp" + appID + " set_recoveryTime " + this.recoveryTime);
/*     */     }
/*     */     
/* 182 */     out.println("\n#===================================");
/* 183 */     out.println("#Peers Definition (depth=0)           ");
/* 184 */     out.println("#===================================  ");
/* 185 */     int parentID = 0;
/* 186 */     for (int childID = 1; childID <= this.width; childID++) {
/* 187 */       out.println("set sAgent" + childID + " [new Agent/UDP]");
/* 188 */       out.println("set rAgent" + childID + " [new Agent/UDP]");
/* 189 */       out.println("$sAgent" + childID + " set packetSize_ 1500");
/* 190 */       out.println("$rAgent" + childID + " set packetSize_ 1500");
/* 191 */       out.println("$ns attach-agent $n" + parentID + " $sAgent" + childID);
/* 192 */       out.println("$ns attach-agent $n" + childID + " $rAgent" + childID);
/* 193 */       out.println("$ns connect $sAgent" + childID + " $rAgent" + childID);
/* 194 */       out.println("$PeerApp" + parentID + " attach-agent $sAgent" + childID);
/* 195 */       out.println("$PeerApp" + childID + " attach-agent $rAgent" + childID);
/* 196 */       out.println("$PeerApp" + parentID + " add_child $sAgent" + childID + " -1");
/* 197 */       out.println();
/*     */     }
/* 199 */     out.println("\n#===================================");
/* 200 */     out.println("#Peers Definition (depth>0)           ");
/* 201 */     out.println("#===================================  ");
/*     */     
/* 203 */     for (int dep = 2; dep <= this.depth; dep++) {
/* 204 */       for (int wid = 1; wid <= this.width; wid++) {
/* 205 */         int childID = (dep - 1) * this.width + wid;
/* 206 */         int[] parents = selectParent(dep);
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 221 */         for (int channel = 0; channel < this.numParent; channel++) {
/* 222 */           parentID = parents[channel];
/* 223 */           out.println("set s" + channel + "Agent" + childID + " [new Agent/UDP]");
/* 224 */           out.println("set r" + channel + "Agent" + childID + " [new Agent/UDP]");
/* 225 */           out.println("$s" + channel + "Agent" + childID + " set packetSize_ 1500");
/* 226 */           out.println("$r" + channel + "Agent" + childID + " set packetSize_ 1500");
/* 227 */           out.println("$ns attach-agent $n" + parentID + " $s" + channel + "Agent" + childID);
/* 228 */           out.println("$ns attach-agent $n" + childID + " $r" + channel + "Agent" + childID);
/* 229 */           out.println("$ns connect $s" + channel + "Agent" + childID + " $r" + channel + "Agent" + childID);
/* 230 */           out.println("$PeerApp" + parentID + " attach-agent $s" + channel + "Agent" + childID);
/* 231 */           out.println("$PeerApp" + childID + " attach-agent $r" + channel + "Agent" + childID);
/* 232 */           out.println("$PeerApp" + parentID + " add_child $s" + channel + "Agent" + childID + " " + channel);
/* 233 */           out.println();
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 238 */     out.println("\n#===================================");
/* 239 */     out.println("#        Cross Traffic (Exponential Traffic over UDP connection)               ");
/* 240 */     out.println("#===================================  ");
/* 241 */     for (int peerID = 1; peerID <= this.depth * this.width; peerID++) {
/*     */       int randomID;
/*     */       do {
/* 244 */         randomID = (int)(Math.random() * (this.depth * this.width));
/* 245 */       } while ((randomID == peerID) || (randomID == 0));
/*     */       
/* 247 */       out.println("set sCrossAgent" + peerID + " [new Agent/UDP]");
/* 248 */       out.println("set rCrossAgent" + peerID + " [new Agent/Null]");
/* 249 */       out.println("$sCrossAgent" + peerID + " set packetSize_ 1500");
/* 250 */       out.println("$ns attach-agent $n" + peerID + " $sCrossAgent" + peerID);
/* 251 */       out.println("$ns attach-agent $n" + randomID + " $rCrossAgent" + peerID);
/* 252 */       out.println("$ns connect $sCrossAgent" + peerID + " $rCrossAgent" + peerID);
/*     */       
/* 254 */       out.println("set crossTf" + peerID + " [new Application/Traffic/Exponential]");
/* 255 */       out.println("$crossTf" + peerID + " set packetSize_ 1024");
/* 256 */       out.println("$crossTf" + peerID + " set burst_time_ 500ms");
/* 257 */       out.println("$crossTf" + peerID + " set idle_time_ 500ms");
/* 258 */       out.println("$crossTf" + peerID + " set rate_ 200k");
/* 259 */       out.println("$crossTf" + peerID + " attach-agent $sCrossAgent" + peerID);
/* 260 */       out.println("$ns at 1.0 \"$crossTf" + peerID + " start\"");
/* 261 */       out.println("$ns at " + this.simTime + ".0 \"$crossTf" + peerID + " stop\"\n");
/*     */     }
/*     */     
/* 264 */     out.println("\n#=====Start video server=====");
/* 265 */     out.println("$ns at 1.0 \"$PeerApp0 start\"");
/* 266 */     out.println("$ns at " + this.simTime + ".0 \"$PeerApp0 stop\"");
/*     */     
/* 268 */     for (int a = 0; a <= this.depth * this.width; a++) {
/* 269 */       out.println("$ns at " + this.simTime + ".0 \"$PeerApp" + a + " end_sim\"");
/*     */     }
/*     */     
/* 272 */     out.println("$ns at " + this.simTime + ".0 \"$ns halt\"");
/* 273 */     out.println("$ns run");
/* 274 */     out.flush();
/* 275 */     out.close();
/*     */   }
/*     */   
/*     */   public int[] selectParent(int depth)
/*     */   {
/* 280 */     int[] result = new int[this.numParent];
/* 281 */     int[] tmp = new int[this.width];
/* 282 */     for (int a = 0; a < this.numParent; a++) {
/*     */       int pid;
/* 284 */       do { pid = (int)(Math.random() * this.width);
/* 285 */       } while (tmp[pid] != 0);
/* 286 */       tmp[pid] = 1;
/* 287 */       result[a] = ((depth - 2) * this.width + pid + 1);
/*     */     }
/* 289 */     return result;
/*     */   }
/*     */   
/* 292 */   public void parameterInit() { this.path = this.pathField.getText();
/* 293 */     this.routers = Integer.parseInt(this.routerField.getText());
/* 294 */     this.transit = Integer.parseInt(this.transitField.getText());
/* 295 */     this.fecN = Integer.parseInt(this.fecNField.getText());
/* 296 */     this.fecK = Integer.parseInt(this.fecKField.getText());
/* 297 */     this.bufferSize = Integer.parseInt(this.bufferField.getText());
/* 298 */     this.serviceTime = Integer.parseInt(this.serviceField.getText());
/* 299 */     this.recoveryTime = Integer.parseInt(this.recoveryField.getText());
/* 300 */     this.depth = Integer.parseInt(this.depthField.getText());
/* 301 */     this.width = Integer.parseInt(this.widthField.getText());
/* 302 */     this.numParent = Integer.parseInt(this.numParentField.getText());
/* 303 */     this.simTime = Integer.parseInt(this.simTimeField.getText());
/* 304 */     this.numSim = Integer.parseInt(this.numSimField.getText());
/*     */   }
/*     */   
/* 307 */   public void uiInit() { this.pathField.setText(System.getProperty("user.home") + "/csvt-sim/");
/*     */     
/* 309 */     Container c = getContentPane();
/*     */     
/* 311 */     JPanel up = new JPanel();
/* 312 */     up.setLayout(new FlowLayout());
/* 313 */     JPanel p = new JPanel();
/* 314 */     p.setLayout(new GridLayout(0, 2));
/*     */     
/* 316 */     p.add(new JLabel("Built date  ", 4));
/* 317 */     p.add(new JLabel("Dec. 11, 2007  ", 2));
/* 318 */     p.add(new JLabel("Path  ", 4));
/* 319 */     p.add(this.pathField);
/* 320 */     p.add(new JLabel("# of routers  ", 4));
/* 321 */     p.add(this.routerField);
/* 322 */     p.add(new JLabel("# of transits  ", 4));
/* 323 */     p.add(this.transitField);
/* 324 */     p.add(new JLabel("fecN  ", 4));
/* 325 */     p.add(this.fecNField);
/* 326 */     p.add(new JLabel("fecK  ", 4));
/* 327 */     p.add(this.fecKField);
/* 328 */     p.add(new JLabel("Buffer size", 4));
/* 329 */     p.add(this.bufferField);
/* 330 */     p.add(new JLabel("Service time", 4));
/* 331 */     p.add(this.serviceField);
/* 332 */     p.add(new JLabel("Recovery time", 4));
/* 333 */     p.add(this.recoveryField);
/* 334 */     p.add(new JLabel("Depth  ", 4));
/* 335 */     p.add(this.depthField);
/* 336 */     p.add(new JLabel("Width  ", 4));
/* 337 */     p.add(this.widthField);
/* 338 */     p.add(new JLabel("NumParent  ", 4));
/* 339 */     p.add(this.numParentField);
/* 340 */     p.add(new JLabel("SimTime  ", 4));
/* 341 */     p.add(this.simTimeField);
/* 342 */     p.add(new JLabel("NumSim  ", 4));
/* 343 */     p.add(this.numSimField);
/*     */     
/* 345 */     p.add(new JPanel().add(this.batchButton));
/* 346 */     p.add(new JPanel().add(this.tclButton));
/* 347 */     up.add(p);
/* 348 */     c.add(up, "North");
/*     */   }
/*     */ }


/* Location:              C:\Users\Liudongdong\Desktop\NSG2.1.jar!\nsg\p2p\ItmTclFrame.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */