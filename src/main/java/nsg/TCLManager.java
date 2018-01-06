/*     */ package nsg;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.File;
/*     */ import java.io.PrintStream;
/*     */ import java.io.Writer;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JInternalFrame;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.JTextPane;
/*     */ import javax.swing.JToolBar;
/*     */ import javax.swing.filechooser.FileFilter;
/*     */ import javax.swing.text.Document;
/*     */ import javax.swing.text.SimpleAttributeSet;
/*     */ import javax.swing.text.StyleConstants;
/*     */ import nsg.component.Agent;
/*     */ import nsg.component.App;
/*     */ import nsg.component.Link;
/*     */ import nsg.component.Node;
/*     */ import nsg.component.Waypoint;
/*     */ import nsg.tool.Tool;
/*     */ 
/*     */ public class TCLManager extends JInternalFrame implements NSGParameters
/*     */ {
/*     */   static final long serialVersionUID = 0L;
/*  33 */   JTextPane tclArea = new JTextPane();
/*     */   
/*     */   File tclFile;
/*  36 */   JFileChooser tcljfc = new JFileChooser();
/*     */   
/*     */ 
/*  39 */   SimpleAttributeSet titleAttr = new SimpleAttributeSet();
/*  40 */   SimpleAttributeSet tclAttr = new SimpleAttributeSet();
/*  41 */   SimpleAttributeSet noteAttr = new SimpleAttributeSet();
/*     */   
/*     */   Document doc;
/*  44 */   JToolBar toolbar = new JToolBar();
/*     */   SceneManager sm;
/*     */   ParameterDialog pm;
/*     */   DataMaintainer dm;
/*     */   
/*     */   public TCLManager(SceneManager sm) {
/*  50 */     super("WiredTcl", true, true, true, true);
/*  51 */     this.sm = sm;
/*  52 */     this.dm = sm.sv.dm;
/*  53 */     this.pm = sm.parameters;
/*     */     
/*  55 */     this.tcljfc.addChoosableFileFilter(new FileFilter() {
/*     */       public boolean accept(File file) {
/*  57 */         if (file.isDirectory())
/*  58 */           return true;
/*  59 */         if (file.getName().endsWith(".tcl")) {
/*  60 */           return true;
/*     */         }
/*  62 */         return false;
/*     */       }
/*     */       
/*     */       public String getDescription() {
/*  66 */         return "TCL files (*.tcl)";
/*     */       }
/*  68 */     });
/*  69 */     StyleConstants.setFontSize(this.titleAttr, 14);
/*  70 */     StyleConstants.setFontFamily(this.titleAttr, "Courier New");
/*  71 */     StyleConstants.setBold(this.titleAttr, true);
/*  72 */     StyleConstants.setForeground(this.titleAttr, Color.BLUE);
/*     */     
/*  74 */     StyleConstants.setFontSize(this.tclAttr, 14);
/*  75 */     StyleConstants.setFontFamily(this.tclAttr, "Courier New");
/*  76 */     StyleConstants.setBold(this.tclAttr, false);
/*  77 */     StyleConstants.setForeground(this.tclAttr, Color.BLACK);
/*     */     
/*  79 */     StyleConstants.setFontSize(this.noteAttr, 14);
/*  80 */     StyleConstants.setFontFamily(this.noteAttr, "Courier New");
/*  81 */     StyleConstants.setBold(this.noteAttr, true);
/*  82 */     StyleConstants.setForeground(this.noteAttr, new Color(32, 158, 29));
/*  83 */     this.doc = this.tclArea.getDocument();
/*     */     
/*  85 */     JButton b = new JButton(" Save ");
/*  86 */     b.setMnemonic('S');
/*  87 */     b.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/*  89 */         TCLManager.this.saveTclFile();
/*     */       }
/*  91 */     });
/*  92 */     this.toolbar.add(b);
/*  93 */     b = new JButton(" Save as ");
/*  94 */     b.setMnemonic('A');
/*  95 */     b.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/*  97 */         TCLManager.this.saveTclFileAs();
/*     */       }
/*  99 */     });
/* 100 */     this.toolbar.add(b);
/* 101 */     this.toolbar.setFloatable(false);
/*     */     
/* 103 */     getContentPane().add(this.toolbar, "North");
/* 104 */     getContentPane().add(new javax.swing.JScrollPane(this.tclArea), "Center");
/* 105 */     generate();
/*     */   }
/*     */   
/*     */   private void generate() {
/* 109 */     appendTitle("# This script is created by NSG2 beta1\n");
/* 110 */     appendTitle("# <http://wushoupong.googlepages.com/nsg>\n");
/*     */     
/* 112 */     parametersSetup();
/* 113 */     initialization();
/* 114 */     if (this.sm.sceneMode == 2)
/* 115 */       mobileInit();
/* 116 */     nodesDefinition();
/* 117 */     if (this.sm.sceneMode == 2)
/* 118 */       generateWaypoint();
/* 119 */     if (this.sm.sceneMode == 1)
/* 120 */       linksDefinition();
/* 121 */     agentsDefinition();
/* 122 */     appDefinition();
/* 123 */     termination();
/*     */   }
/*     */   
/*     */   private void parametersSetup() {
/* 127 */     appendNote("\n#===================================\n");
/* 128 */     appendNote("#     Simulation parameters setup\n");
/* 129 */     appendNote("#===================================\n");
/*     */     
/* 131 */     if (this.sm.sceneMode == 2)
/*     */     {
/*     */ 
/* 134 */       if (this.pm.Gt_Box.isSelected())
/* 135 */         appendTCL("Antenna/OmniAntenna set Gt_ " + this.pm.Gt_.getText(), "              ;#Transmit antenna gain\n");
/* 136 */       if (this.pm.Gr_Box.isSelected())
/* 137 */         appendTCL("Antenna/OmniAntenna set Gr_ " + this.pm.Gr_.getText(), "              ;#Receive antenna gain\n");
/* 138 */       if (this.pm.L_Box.isSelected())
/* 139 */         appendTCL("Phy/WirelessPhy set L_ " + this.pm.L_.getText(), "                 ;#System Loss Factor\n");
/* 140 */       if (this.pm.freq_Box.isSelected())
/* 141 */         appendTCL("Phy/WirelessPhy set freq_ " + this.pm.freq_.getText(), "          ;#channel\n");
/* 142 */       if (this.pm.bandwidth_Box.isSelected())
/* 143 */         appendTCL("Phy/WirelessPhy set bandwidth_ " + this.pm.bandwidth_.getText(), "        ;#Data Rate\n");
/* 144 */       if (this.pm.Pt_Box.isSelected())
/* 145 */         appendTCL("Phy/WirelessPhy set Pt_ " + this.pm.Pt_.getText(), "        ;#Transmit Power\n");
/* 146 */       if (this.pm.CPThresh_Box.isSelected())
/* 147 */         appendTCL("Phy/WirelessPhy set CPThresh_ " + this.pm.CPThresh_.getText(), "         ;#Collision Threshold\n");
/* 148 */       if (this.pm.CSThresh_Box.isSelected())
/* 149 */         appendTCL("Phy/WirelessPhy set CSThresh_ " + this.pm.CSThresh_.getText(), " ;#Carrier Sense Power\n");
/* 150 */       if (this.pm.RXThresh_Box.isSelected())
/* 151 */         appendTCL("Phy/WirelessPhy set RXThresh_ " + this.pm.RXThresh_.getText(), "  ;#Receive Power Threshold\n");
/* 152 */       if (this.pm.dataRate_Box.isSelected())
/* 153 */         appendTCL("Mac/802_11 set dataRate_ " + this.pm.dataRate_.getText(), "              ;#Rate for Data Frames\n");
/* 154 */       if (this.pm.basicRate_Box.isSelected()) {
/* 155 */         appendTCL("Mac/802_11 set basicRate_ " + this.pm.basicRate_.getText(), "              ;#Rate for Control Frames\n\n");
/*     */       }
/* 157 */       appendTCL("set val(chan)   " + this.pm.channelItem.getSelectedItem() + "    ", ";# channel type\n");
/* 158 */       appendTCL("set val(prop)   " + this.pm.propagationItem.getSelectedItem() + "   ", ";# radio-propagation model\n");
/* 159 */       appendTCL("set val(netif)  " + this.pm.phyItem.getSelectedItem() + "            ", ";# network interface type\n");
/* 160 */       appendTCL("set val(mac)    " + this.pm.macItem.getSelectedItem() + "                 ", ";# MAC type\n");
/* 161 */       appendTCL("set val(ifq)    " + this.pm.queueItem.getSelectedItem() + "    ", ";# interface queue type\n");
/* 162 */       appendTCL("set val(ll)     " + this.pm.linkLayerItem.getSelectedItem() + "                         ", ";# link layer type\n");
/* 163 */       appendTCL("set val(ant)    " + this.pm.antennaItem.getSelectedItem() + "        ", ";# antenna model\n");
/* 164 */       appendTCL("set val(ifqlen) " + this.pm.maxPacketInIfqItem.getText() + "                         ", ";# max packet in ifq\n");
/* 165 */       if (this.dm.nodes.size() < 10) {
/* 166 */         appendTCL("set val(nn)     " + this.dm.nodes.size() + "                          ", ";# number of mobilenodes\n");
/* 167 */       } else if (this.dm.nodes.size() < 100) {
/* 168 */         appendTCL("set val(nn)     " + this.dm.nodes.size() + "                         ", ";# number of mobilenodes\n");
/* 169 */       } else if (this.dm.nodes.size() < 1000) {
/* 170 */         appendTCL("set val(nn)     " + this.dm.nodes.size() + "                        ", ";# number of mobilenodes\n");
/* 171 */       } else if (this.dm.nodes.size() < 10000) {
/* 172 */         appendTCL("set val(nn)     " + this.dm.nodes.size() + "                       ", ";# number of mobilenodes\n");
/*     */       }
/* 174 */       appendTCL("set val(rp)     " + this.pm.routingItem.getSelectedItem() + "                       ", ";# routing protocol\n");
/*     */       
/* 176 */       int maxX = 0;
/* 177 */       int maxY = 0;
/*     */       
/* 179 */       Object[] nodes = this.dm.getNodes();
/* 180 */       for (int i = 0; i < nodes.length; i++) {
/* 181 */         Node node = (Node)nodes[i];
/* 182 */         if (Tool.translateX(0, node.x, this.sm.sv.scale) > maxX)
/* 183 */           maxX = Tool.translateX(0, node.x, this.sm.sv.scale);
/* 184 */         if (Tool.translateY(0, node.y, this.sm.sv.scale) > maxY)
/* 185 */           maxY = Tool.translateY(0, node.y, this.sm.sv.scale);
/* 186 */         if (node.waypoints != null) {
/* 187 */           Object[] points = node.waypoints.toArray();
/* 188 */           for (int a = 0; a < points.length; a++) {
/* 189 */             if (Integer.parseInt(((Waypoint)points[a]).destX) > maxX)
/* 190 */               maxX = Integer.parseInt(((Waypoint)points[a]).destX);
/* 191 */             if (Integer.parseInt(((Waypoint)points[a]).destY) > maxY) {
/* 192 */               maxY = Integer.parseInt(((Waypoint)points[a]).destY);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 198 */       appendTCL("set val(x)      " + (maxX + 100) + "                      ", ";# X dimension of topography\n");
/* 199 */       appendTCL("set val(y)      " + (maxY + 100) + "                      ", ";# Y dimension of topography\n");
/*     */     }
/* 201 */     appendTCL("set val(stop)   " + this.pm.simTimeItem.getText() + "                         ", ";# time of simulation end\n");
/*     */   }
/*     */   
/*     */   private void initialization() {
/* 205 */     boolean enableTrace = !this.pm.traceFileItem.getText().equals("");
/* 206 */     boolean enableNAM = !this.pm.namFileItem.getText().equals("");
/*     */     
/* 208 */     appendNote("\n#===================================\n");
/* 209 */     appendNote("#        Initialization        \n");
/* 210 */     appendNote("#===================================\n");
/* 211 */     appendNote("#Create a ns simulator\n");
/* 212 */     appendTCL("set ns [new Simulator]\n\n");
/*     */     
/* 214 */     if (this.sm.sceneMode == 2) {
/* 215 */       appendNote("#Setup topography object\n");
/* 216 */       appendTCL("set topo       [new Topography]\n");
/* 217 */       appendTCL("$topo load_flatgrid $val(x) $val(y)\n");
/* 218 */       appendTCL("create-god $val(nn)\n\n");
/*     */     }
/*     */     
/* 221 */     if (enableTrace) {
/* 222 */       appendNote("#Open the NS trace file\n");
/* 223 */       appendTCL("set tracefile [open " + this.pm.traceFileItem.getText() + " w]\n");
/* 224 */       appendTCL("$ns trace-all $tracefile\n\n");
/*     */     }
/* 226 */     if (enableNAM) {
/* 227 */       appendNote("#Open the NAM trace file\n");
/* 228 */       appendTCL("set namfile [open " + this.pm.namFileItem.getText() + " w]\n");
/* 229 */       appendTCL("$ns namtrace-all $namfile\n");
/* 230 */       if (this.sm.sceneMode == 2) {
/* 231 */         appendTCL("$ns namtrace-all-wireless $namfile $val(x) $val(y)\n");
/*     */       }
/*     */     }
/* 234 */     if (this.sm.sceneMode == 2)
/* 235 */       appendTCL("set chan [new $val(chan)]", ";#Create wireless channel\n");
/*     */   }
/*     */   
/*     */   private void mobileInit() {
/* 239 */     appendNote("\n#===================================\n");
/* 240 */     appendNote("#     Mobile node parameter setup\n");
/* 241 */     appendNote("#===================================\n");
/* 242 */     appendTCL("$ns node-config -adhocRouting  $val(rp) \\\n");
/* 243 */     appendTCL("                -llType        $val(ll) \\\n");
/* 244 */     appendTCL("                -macType       $val(mac) \\\n");
/* 245 */     appendTCL("                -ifqType       $val(ifq) \\\n");
/* 246 */     appendTCL("                -ifqLen        $val(ifqlen) \\\n");
/* 247 */     appendTCL("                -antType       $val(ant) \\\n");
/* 248 */     appendTCL("                -propType      $val(prop) \\\n");
/* 249 */     appendTCL("                -phyType       $val(netif) \\\n");
/* 250 */     appendTCL("                -channel       $chan \\\n");
/* 251 */     appendTCL("                -topoInstance  $topo \\\n");
/* 252 */     appendTCL("                -agentTrace    " + this.pm.agentTraceItem.getSelectedItem() + " \\\n");
/* 253 */     appendTCL("                -routerTrace   " + this.pm.routerTraceItem.getSelectedItem() + " \\\n");
/* 254 */     appendTCL("                -macTrace      " + this.pm.macTraceItem.getSelectedItem() + " \\\n");
/* 255 */     appendTCL("                -movementTrace " + this.pm.movementTraceItem.getSelectedItem() + "\n");
/*     */   }
/*     */   
/*     */   private void nodesDefinition() {
/* 259 */     appendNote("\n#===================================\n");
/* 260 */     appendNote("#        Nodes Definition        \n");
/* 261 */     appendNote("#===================================\n");
/*     */     
/* 263 */     appendNote("#Create " + this.dm.nodes.size() + " nodes\n");
/*     */     
/* 265 */     Object[] nodes = this.dm.getNodes();
/* 266 */     for (int i = 0; i < nodes.length; i++) {
/* 267 */       Node node = (Node)nodes[i];
/* 268 */       appendTCL("set n" + node.id + " [$ns node]\n");
/* 269 */       if (this.sm.sceneMode == 2) {
/* 270 */         appendTCL("$n" + node.id + " set X_ " + Tool.translateX(node.x) + "\n");
/* 271 */         appendTCL("$n" + node.id + " set Y_ " + Tool.translateY(node.y) + "\n");
/* 272 */         appendTCL("$n" + node.id + " set Z_ 0.0\n");
/* 273 */         appendTCL("$ns initial_node_pos $n" + node.id + " 20\n");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void generateWaypoint()
/*     */   {
/* 280 */     Object[] nodes = this.dm.getNodes();
/* 281 */     boolean enable = false;
/*     */     
/*     */ 
/* 284 */     for (int i = 0; i < nodes.length; i++) {
/* 285 */       Node node = (Node)nodes[i];
/* 286 */       if (node.waypoints != null) {
/* 287 */         if (!enable) {
/* 288 */           appendNote("\n#===================================\n");
/* 289 */           appendNote("#        Generate movement          \n");
/* 290 */           appendNote("#===================================\n");
/* 291 */           enable = true;
/*     */         }
/* 293 */         Object[] points = node.waypoints.toArray();
/* 294 */         for (int a = 0; a < points.length; a++) {
/* 295 */           appendTCL("$ns at " + ((Waypoint)points[a]).startTime + " \" $n" + node.id + " setdest " + ((Waypoint)points[a]).destX + " " + ((Waypoint)points[a]).destY + " " + ((Waypoint)points[a]).speed + " \" \n");
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void linksDefinition() {
/* 302 */     appendNote("\n#===================================\n");
/* 303 */     appendNote("#        Links Definition        \n");
/* 304 */     appendNote("#===================================\n");
/*     */     
/* 306 */     appendNote("#Createlinks between nodes\n");
/* 307 */     String linkType = "";
/* 308 */     String queueType = "";
/*     */     
/* 310 */     Object[] links = this.dm.getLinks();
/* 311 */     for (int i = 0; i < links.length; i++) {
/* 312 */       Link link = (Link)links[i];
/* 313 */       switch (link.queueType) {
/*     */       case 0: 
/* 315 */         queueType = "DropTail";
/* 316 */         break;
/*     */       case 1: 
/* 318 */         queueType = "RED";
/* 319 */         break;
/*     */       case 2: 
/* 321 */         queueType = "FQ";
/* 322 */         break;
/*     */       case 3: 
/* 324 */         queueType = "DRR";
/* 325 */         break;
/*     */       case 4: 
/* 327 */         queueType = "SFQ";
/* 328 */         break;
/*     */       case 5: 
/* 330 */         queueType = "CBQ";
/*     */       }
/*     */       
/* 333 */       if (link.linkType == 0) {
/* 334 */         linkType = "duplex-link";
/*     */       } else {
/* 336 */         linkType = "simplex-link";
/*     */       }
/* 338 */       appendTCL("$ns " + linkType + " $n" + link.src.id + " $n" + link.dst.id + " " + link.capacity + "Mb " + link.propagationDelay + "ms " + queueType + "\n");
/* 339 */       if (link.queueSize != -1) {
/* 340 */         appendTCL("$ns queue-limit $n" + link.src.id + " $n" + link.dst.id + " " + link.queueSize + "\n");
/*     */       }
/*     */     }
/* 343 */     appendNote("\n#Give node position (for NAM)\n");
/*     */     
/* 345 */     for (int i = 0; i < links.length; i++) {
/* 346 */       Link link = (Link)links[i];
/* 347 */       if (link.linkType == 0) {
/* 348 */         linkType = "duplex-link";
/*     */       } else {
/* 350 */         linkType = "simplex-link";
/*     */       }
/* 352 */       if (link.src.x < link.dst.x) {
/* 353 */         if (link.dst.y - link.src.y > 20) {
/* 354 */           appendTCL("$ns " + linkType + "-op $n" + link.src.id + " $n" + link.dst.id + " orient right-down\n");
/* 355 */         } else if (link.dst.y - link.src.y < -20) {
/* 356 */           appendTCL("$ns " + linkType + "-op $n" + link.src.id + " $n" + link.dst.id + " orient right-up\n");
/*     */         } else {
/* 358 */           appendTCL("$ns " + linkType + "-op $n" + link.src.id + " $n" + link.dst.id + " orient right\n");
/*     */         }
/*     */       }
/* 361 */       else if (link.dst.y - link.src.y > 20) {
/* 362 */         appendTCL("$ns " + linkType + "-op $n" + link.src.id + " $n" + link.dst.id + " orient left-down\n");
/* 363 */       } else if (link.dst.y - link.src.y < -20) {
/* 364 */         appendTCL("$ns " + linkType + "-op $n" + link.src.id + " $n" + link.dst.id + " orient left-up\n");
/*     */       } else {
/* 366 */         appendTCL("$ns " + linkType + "-op $n" + link.src.id + " $n" + link.dst.id + " orient left\n\n");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private void agentsDefinition()
/*     */   {
/* 374 */     appendNote("\n#===================================\n");
/* 375 */     appendNote("#        Agents Definition        \n");
/* 376 */     appendNote("#===================================\n");
/*     */     
/*     */ 
/*     */ 
/* 380 */     Object[] agents = this.dm.getAgents();
/* 381 */     for (int i = 0; i < agents.length; i++) {
/* 382 */       Agent agent = (Agent)agents[i];
/* 383 */       if (agent.remoteAgent != null)
/*     */       {
/* 385 */         switch (agent.agentType) {
/*     */         case 0: 
/*     */         case 4: 
/*     */         case 8: 
/*     */         case 12: 
/*     */         case 16: 
/* 391 */           appendNote("#Setup a " + Agent.convertType(agent.agentType) + " connection\n");
/* 392 */           appendTCL("set tcp" + agent.id + " [new Agent/" + Agent.convertType(agent.agentType) + "]\n");
/* 393 */           appendTCL("$ns attach-agent $n" + agent.attachedNode.id + " $tcp" + agent.id + "\n");
/* 394 */           appendTCL("set sink" + agent.remoteAgent.id + " [new Agent/" + Agent.convertType(agent.remoteAgent.agentType) + "]\n");
/* 395 */           appendTCL("$ns attach-agent $n" + agent.remoteAgent.attachedNode.id + " $sink" + agent.remoteAgent.id + "\n");
/* 396 */           appendTCL("$ns connect $tcp" + agent.id + " $sink" + agent.remoteAgent.id + "\n");
/* 397 */           if (agent.packetSize != -1) appendTCL("$tcp" + agent.id + " set packetSize_ " + agent.packetSize + "\n");
/* 398 */           if (agent.window != -1) appendTCL("$tcp" + agent.id + " set window_ " + agent.window + "\n");
/* 399 */           if (agent.maxcwnd != -1) appendTCL("$tcp" + agent.id + " set maxcwnd_ " + agent.maxcwnd + "\n");
/* 400 */           if (agent.windowInit != -1) appendTCL("$tcp" + agent.id + " set windowInit_ " + agent.windowInit + "\n");
/* 401 */           if (agent.tcpTick != -1) appendTCL("$tcp" + agent.id + " set tcpTick_ " + agent.tcpTick + "\n");
/* 402 */           if (agent.maxburst != -1) appendTCL("$tcp" + agent.id + " set maxburst_ " + agent.maxburst + "\n");
/* 403 */           appendTCL("\n");
/* 404 */           break;
/*     */         case 2: 
/* 406 */           appendNote("#Setup a " + Agent.convertType(agent.agentType) + " connection\n");
/* 407 */           appendTCL("set udp" + agent.id + " [new Agent/" + Agent.convertType(agent.agentType) + "]\n");
/* 408 */           appendTCL("$ns attach-agent $n" + agent.attachedNode.id + " $udp" + agent.id + "\n");
/* 409 */           appendTCL("set null" + agent.remoteAgent.id + " [new Agent/" + Agent.convertType(agent.remoteAgent.agentType) + "]\n");
/* 410 */           appendTCL("$ns attach-agent $n" + agent.remoteAgent.attachedNode.id + " $null" + agent.remoteAgent.id + "\n");
/* 411 */           appendTCL("$ns connect $udp" + agent.id + " $null" + agent.remoteAgent.id + "\n");
/* 412 */           if (agent.packetSize != -1) appendTCL("$udp" + agent.id + " set packetSize_ " + agent.packetSize + "\n");
/* 413 */           appendTCL("\n");
/* 414 */           break;
/*     */         case 1: 
/*     */           break;
/*     */         case 3: 
/*     */           break;
/*     */         case 5: case 6: case 7: case 9: case 10: case 11: case 13: case 14: case 15: default: 
/* 420 */           System.out.println("Agents definition error");
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void appDefinition() {
/* 427 */     appendNote("\n#===================================\n");
/* 428 */     appendNote("#        Applications Definition        \n");
/* 429 */     appendNote("#===================================\n");
/*     */     
/*     */ 
/* 432 */     Object[] apps = this.dm.getApps();
/* 433 */     for (int i = 0; i < apps.length; i++) {
/* 434 */       App app = (App)apps[i];
/* 435 */       switch (app.appType) {
/*     */       case 0: 
/* 437 */         appendNote("#Setup a FTP Application over " + Agent.convertType(app.agent.agentType) + " connection\n");
/* 438 */         appendTCL("set ftp" + app.id + " [new Application/FTP]\n");
/* 439 */         if (app.agent.agentType % 4 == 0) {
/* 440 */           appendTCL("$ftp" + app.id + " attach-agent $tcp" + app.agent.id + "\n");
/*     */         } else {
/* 442 */           appendTCL("$ftp" + app.id + " attach-agent $udp" + app.agent.id + "\n");
/*     */         }
/* 444 */         appendTCL("$ns at " + app.startTime + " \"$ftp" + app.id + " start\"\n");
/* 445 */         appendTCL("$ns at " + app.stopTime + " \"$ftp" + app.id + " stop\"\n\n");
/* 446 */         break;
/*     */       case 1: 
/* 448 */         appendNote("#Setup a CBR Application over " + Agent.convertType(app.agent.agentType) + " connection\n");
/* 449 */         appendTCL("set cbr" + app.id + " [new Application/Traffic/CBR]\n");
/* 450 */         if (app.agent.agentType % 4 == 0) {
/* 451 */           appendTCL("$cbr" + app.id + " attach-agent $tcp" + app.agent.id + "\n");
/*     */         } else {
/* 453 */           appendTCL("$cbr" + app.id + " attach-agent $udp" + app.agent.id + "\n");
/*     */         }
/* 455 */         appendTCL("$cbr" + app.id + " set packetSize_ " + app.packetSize + "\n");
/* 456 */         appendTCL("$cbr" + app.id + " set rate_ " + app.rate + "Mb\n");
/* 457 */         appendTCL("$cbr" + app.id + " set random_ " + app.random + "\n");
/* 458 */         appendTCL("$ns at " + app.startTime + " \"$cbr" + app.id + " start\"\n");
/* 459 */         appendTCL("$ns at " + app.stopTime + " \"$cbr" + app.id + " stop\"\n\n");
/* 460 */         break;
/*     */       
/*     */ 
/*     */       case 2: 
/*     */         break;
/*     */       
/*     */ 
/*     */ 
/*     */       case 3: 
/*     */         break;
/*     */       
/*     */ 
/*     */ 
/*     */       case 4: 
/*     */         break;
/*     */       
/*     */ 
/*     */ 
/*     */       default: 
/* 479 */         System.out.println("Applications definition error");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void termination()
/*     */   {
/* 486 */     boolean enableTrace = !this.pm.traceFileItem.getText().equals("");
/* 487 */     boolean enableNAM = !this.pm.namFileItem.getText().equals("");
/*     */     
/* 489 */     appendNote("\n#===================================\n");
/* 490 */     appendNote("#        Termination        \n");
/* 491 */     appendNote("#===================================\n");
/* 492 */     appendNote("#Define a 'finish' procedure\n");
/*     */     
/* 494 */     appendTCL("proc finish {} {\n");
/* 495 */     appendTCL("    global ns");
/* 496 */     if (enableTrace)
/* 497 */       appendTCL(" tracefile");
/* 498 */     if (enableNAM) {
/* 499 */       appendTCL(" namfile\n");
/*     */     } else {
/* 501 */       appendTCL("\n");
/*     */     }
/* 503 */     if ((enableTrace | enableNAM))
/* 504 */       appendTCL("    $ns flush-trace\n");
/* 505 */     if (enableTrace)
/* 506 */       appendTCL("    close $tracefile\n");
/* 507 */     if (enableNAM) {
/* 508 */       appendTCL("    close $namfile\n");
/* 509 */       appendTCL("    exec nam " + this.pm.namFileItem.getText() + " &\n");
/*     */     }
/* 511 */     appendTCL("    exit 0\n");
/* 512 */     appendTCL("}\n");
/*     */     
/* 514 */     if (this.sm.sceneMode == 2)
/*     */     {
/* 516 */       appendTCL("for {set i 0} {$i < $val(nn) } { incr i } {\n");
/* 517 */       appendTCL("    $ns at $val(stop) \"\\$n$i reset\"\n");
/* 518 */       appendTCL("}\n");
/*     */     }
/*     */     
/*     */ 
/* 522 */     appendTCL("$ns at $val(stop) \"$ns nam-end-wireless $val(stop)\"\n");
/* 523 */     appendTCL("$ns at $val(stop) \"finish\"\n");
/* 524 */     appendTCL("$ns at $val(stop) \"puts \\\"done\\\" ; $ns halt\"\n");
/*     */     
/* 526 */     appendTCL("$ns run\n");
/*     */   }
/*     */   
/*     */   public void appendTitle(String s) {
/*     */     try {
/* 531 */       this.doc.insertString(this.tclArea.getCaretPosition(), s, this.titleAttr);
/*     */     } catch (Exception evt) {
/* 533 */       evt.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void appendNote(String note) {
/*     */     try {
/* 539 */       this.doc.insertString(this.tclArea.getCaretPosition(), note, this.noteAttr);
/*     */     } catch (Exception evt) {
/* 541 */       evt.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void appendTCL(String tcl) {
/*     */     try {
/* 547 */       this.doc.insertString(this.tclArea.getCaretPosition(), tcl, this.tclAttr);
/*     */     } catch (Exception evt) {
/* 549 */       evt.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void appendTCL(String tcl, String note) {
/*     */     try {
/* 555 */       this.doc.insertString(this.tclArea.getCaretPosition(), tcl, this.tclAttr);
/* 556 */       this.doc.insertString(this.tclArea.getCaretPosition(), note, this.noteAttr);
/*     */     } catch (Exception evt) {
/* 558 */       evt.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean saveTclFileAs() {
/*     */     try {
/* 564 */       this.tcljfc.setDialogTitle("Please select file");
/* 565 */       int m = this.tcljfc.showSaveDialog(this);
/* 566 */       if (m == 0) {
/* 567 */         this.tclFile = this.tcljfc.getSelectedFile();
/* 568 */         if (!this.tclFile.getAbsolutePath().endsWith("tcl")) {
/* 569 */           this.tclFile = new File(this.tclFile.getAbsolutePath() + ".tcl");
/*     */         }
/* 571 */         saveTclFile();
/* 572 */         return true;
/*     */       }
/* 574 */       return false;
/*     */     }
/*     */     catch (Exception evt) {
/* 577 */       System.out.println(evt.getMessage()); }
/* 578 */     return false;
/*     */   }
/*     */   
/*     */   public boolean saveTclFile()
/*     */   {
/*     */     try {
/* 584 */       if ((this.tclFile == null) && 
/* 585 */         (!saveTclFileAs())) {
/* 586 */         return false;
/*     */       }
/*     */       
/* 589 */       Writer out = new java.io.OutputStreamWriter(new java.io.FileOutputStream(this.tclFile));
/*     */       
/* 591 */       out.write(this.tclArea.getText());
/* 592 */       out.flush();
/* 593 */       out.close();
/* 594 */       setTitle(this.tclFile.getAbsolutePath());
/* 595 */       return true;
/*     */     } catch (Exception evt) {
/* 597 */       System.out.println(evt.getMessage()); }
/* 598 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Liudongdong\Desktop\NSG2.1.jar!\nsg\TCLManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */