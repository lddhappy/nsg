/*     */ package nsg;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.event.MouseWheelEvent;
/*     */ import java.awt.event.MouseWheelListener;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Ellipse2D.Double;
///*     */ import java.awt.geom.Line2D.Double;
/*     */ import java.awt.geom.Line2D;
import java.io.PrintStream;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JDesktopPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JSlider;
/*     */ import nsg.component.Agent;
/*     */ import nsg.component.App;
/*     */ import nsg.component.Link;
/*     */ import nsg.component.Node;
/*     */ import nsg.interactive.CreatingAgentModeHandler;
/*     */ import nsg.interactive.CreatingAppModeHandler;
/*     */ import nsg.interactive.CreatingLinkModeHandler;
/*     */ import nsg.interactive.CreatingNodeModeHandler;
/*     */ import nsg.interactive.HandModeHandler;
/*     */ import nsg.interactive.NormalModeHandler;
/*     */ import nsg.panels.AgentPanel;
/*     */ import nsg.panels.AppPanel;
/*     */ import nsg.panels.HandModePanel;
/*     */ import nsg.panels.LinkPanel;
/*     */ import nsg.panels.NodePanel;
/*     */ import nsg.panels.NormalModePanel;
/*     */ import nsg.tool.Tool;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SceneVirtualizer
/*     */   extends JComponent
/*     */   implements NSGParameters
/*     */ {
/*     */   static final long serialVersionUID = 0L;
/*     */   public int mainMode;
/*     */   public int shiftX;
/*     */   public int shiftY;
/*     */   public HandModeHandler handModeHandler;
/*     */   public CreatingNodeModeHandler creatingNodeModeHandler;
/*     */   public NormalModeHandler normalModeHandler;
/*     */   public CreatingLinkModeHandler creatingLinkModeHandler;
/*     */   public CreatingAgentModeHandler creatingAgentModeHandler;
/*     */   public CreatingAppModeHandler creatingAppModeHandler;
/*     */   public JPanel modePanel;
/*  56 */   public LinkPanel linkPanel = new LinkPanel(this);
/*  57 */   public NodePanel nodePanel = new NodePanel(this);
/*  58 */   public AgentPanel agentPanel = new AgentPanel(this);
/*  59 */   public AppPanel appPanel = new AppPanel(this);
/*  60 */   public NormalModePanel normalModePanel = new NormalModePanel();
/*  61 */   public HandModePanel handModePanel = new HandModePanel();
/*     */   
/*  63 */   public int transmissionRange = 250;
/*  64 */   public int interferenceRange = 550;
/*     */   
/*  66 */   public float scale = 1.0F;
/*     */   public SceneManager sm;
/*     */   DataMaintainer dm;
/*     */   
/*  70 */   public SceneVirtualizer(SceneManager sm) { this.sm = sm;
/*  71 */     sm.sv = this;
/*  72 */     this.dm = sm.dm;
/*     */     
/*  74 */     this.handModeHandler = new HandModeHandler(sm);
/*  75 */     this.creatingNodeModeHandler = new CreatingNodeModeHandler(sm);
/*     */     
/*  77 */     this.creatingLinkModeHandler = new CreatingLinkModeHandler(sm);
/*  78 */     this.creatingAgentModeHandler = new CreatingAgentModeHandler(sm);
/*  79 */     this.creatingAppModeHandler = new CreatingAppModeHandler(sm);
/*     */     
/*  81 */     if (sm.sceneMode == 1) {
/*  82 */       this.shiftX = 60536;
/*  83 */       this.shiftY = 60536;
/*     */     } else {
/*  85 */       this.shiftY = (55399 + NSG2.getMainDesktopPane().getHeight());
/*     */     }
/*  87 */     switchMode(2);
/*     */     
/*  89 */     addMouseWheelListener(new MouseWheelListener() {
/*     */       public void mouseWheelMoved(MouseWheelEvent e) {
/*  91 */         SceneVirtualizer.this.sm.slider.setValue(SceneVirtualizer.this.sm.slider.getValue() + e.getWheelRotation() * 10);
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   public void switchMode(int mode) {
/*  97 */     this.mainMode = mode;
/*  98 */     if (getMouseListeners().length != 0) {
/*  99 */       removeMouseListener(getMouseListeners()[0]);
/* 100 */       removeMouseMotionListener(getMouseMotionListeners()[0]);
/*     */     }
/* 102 */     if (this.modePanel != null) {
/* 103 */       this.sm.center.remove(this.modePanel);
/* 104 */       this.sm.validate();
/*     */     }
/* 106 */     this.sm.b1.setBackground(BUTTON_DISABLE_COLOR);
/* 107 */     this.sm.b2.setBackground(BUTTON_DISABLE_COLOR);
/* 108 */     this.sm.b3.setBackground(BUTTON_DISABLE_COLOR);
/* 109 */     this.sm.b4.setBackground(BUTTON_DISABLE_COLOR);
/* 110 */     this.sm.b5.setBackground(BUTTON_DISABLE_COLOR);
/* 111 */     switch (mode)
/*     */     {
/*     */     case 0: 
/*     */       break;
/*     */     
/*     */ 
/*     */     case 1: 
/* 118 */       this.sm.b1.setBackground(BUTTON_ENABLE_COLOR);
/* 119 */       this.modePanel = this.handModePanel;
/* 120 */       addMouseMotionListener(this.handModeHandler);
/* 121 */       addMouseListener(this.handModeHandler);
/* 122 */       break;
/*     */     case 2: 
/* 124 */       this.sm.b2.setBackground(BUTTON_ENABLE_COLOR);
/* 125 */       this.modePanel = this.nodePanel;
/* 126 */       addMouseMotionListener(this.creatingNodeModeHandler);
/* 127 */       addMouseListener(this.creatingNodeModeHandler);
/* 128 */       break;
/*     */     case 3: 
/* 130 */       this.sm.b4.setBackground(BUTTON_ENABLE_COLOR);
/*     */       
/* 132 */       this.modePanel = this.agentPanel;
/*     */       
/* 134 */       addMouseMotionListener(this.creatingAgentModeHandler);
/* 135 */       addMouseListener(this.creatingAgentModeHandler);
/* 136 */       break;
/*     */     case 4: 
/* 138 */       this.sm.b5.setBackground(BUTTON_ENABLE_COLOR);
/*     */       
/* 140 */       this.modePanel = this.appPanel;
/* 141 */       addMouseMotionListener(this.creatingAppModeHandler);
/* 142 */       addMouseListener(this.creatingAppModeHandler);
/* 143 */       break;
/*     */     case 5: 
/* 145 */       this.sm.b3.setBackground(BUTTON_ENABLE_COLOR);
/* 146 */       System.out.println(this.sm.b5.getBackground());
/* 147 */       this.modePanel = this.linkPanel;
/* 148 */       addMouseMotionListener(this.creatingLinkModeHandler);
/* 149 */       addMouseListener(this.creatingLinkModeHandler);
/* 150 */       break;
/*     */     default: 
/* 152 */       this.modePanel = this.normalModePanel;
/*     */       
/*     */ 
/* 155 */       System.err.println("Mode switching error!!!");
/*     */     }
/* 157 */     this.sm.center.add(this.modePanel, "North");
/* 158 */     this.sm.validate();
/*     */   }
/*     */   
/*     */   public void paintComponent(Graphics g2) {
/* 162 */     Graphics2D g = (Graphics2D)g2;
/* 163 */     g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 164 */     g.setColor(BACKGROUND_COLOR);
/* 165 */     g.scale(this.scale, this.scale);
/* 166 */     g.fillRect(0, 0, 10000, 10000);
/*     */     
/* 168 */     g.translate(this.shiftX, this.shiftY);
/*     */     
/*     */ 
/* 171 */     if (this.sm.drawGrid) drawGrid(g);
/* 172 */     if (this.sm.drawLink) drawLinks(g);
/* 173 */     if (this.sm.drawApp) drawApp(g);
/* 174 */     if (this.sm.drawAgent) drawAgents(g);
/* 175 */     if ((this.sm.sceneMode == 2) && (this.sm.drawConnectivity)) drawConnected(g);
/* 176 */     if (this.sm.drawNode) drawNodes(g);
/* 177 */     switch (this.mainMode)
/*     */     {
/*     */     case 0: 
/*     */       break;
/*     */     case 1: 
/*     */       break;
/*     */     
/*     */     case 2: 
/* 185 */       this.creatingNodeModeHandler.draw(g);
/* 186 */       break;
/*     */     case 3: 
/* 188 */       this.creatingAgentModeHandler.draw(g);
/* 189 */       break;
/*     */     case 4: 
/* 191 */       this.creatingAppModeHandler.draw(g);
/* 192 */       break;
/*     */     case 5: 
/* 194 */       this.creatingLinkModeHandler.draw(g);
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */   private void drawInfo(Graphics2D g)
/*     */   {
/* 201 */     g.translate(-this.shiftX, -this.shiftY);
/* 202 */     g.setColor(INFO_COLOR);
/* 203 */     g.drawString("Number of nodes: " + this.dm.getNodes().length, 20, 20);
/*     */   }
/*     */   
/* 206 */   private void drawGrid(Graphics2D g) { g.setColor(GRID_COLOR);
/* 207 */     g.drawRect(0, 0, 10000, 10000);
/* 208 */     for (int i = 100; i < 10000; i += 100) {
/* 209 */       g.drawLine(i, 0, i, 10000);
/*     */     }
/* 211 */     for (int i = 100; i < 10000; i += 100) {
/* 212 */       g.drawLine(0, i, 10000, i);
/*     */     }
/*     */   }
/*     */   
/*     */   private void drawNodes(Graphics2D g)
/*     */   {
/* 218 */     int R = 30;
/*     */     
/* 220 */     int Rdiv2 = R / 2;
/*     */     
/* 222 */     Object[] nodes = this.dm.getNodes();
/* 223 */     g.setStroke(new BasicStroke(1.0F, 1, 2));
/* 224 */     g.setColor(NODE_COLOR);
/* 225 */     for (int i = 0; i < nodes.length; i++) {
/* 226 */       Node p = (Node)nodes[i];
/* 227 */       Ellipse2D shap = new Ellipse2D.Double(p.x - Rdiv2, p.y - Rdiv2, R, R);
/*     */       
/* 229 */       g.setColor(NODE_COLOR);
/* 230 */       g.fill(shap);
/* 231 */       g.setColor(NODE_TEXT_COLOR);
/* 232 */       if (p.id < 10) {
/* 233 */         g.drawString("n" + String.valueOf(p.id), p.x - 6, p.y + 4);
/* 234 */       } else if (p.id < 100) {
/* 235 */         g.drawString("n" + String.valueOf(p.id), p.x - 10, p.y + 4);
/*     */       } else {
/* 237 */         g.drawString("n" + String.valueOf(p.id), p.x - 14, p.y + 4);
/*     */       }
/* 239 */       if (this.sm.sceneMode == 2) {
/* 240 */         if (this.sm.drawSR) {
/* 241 */           g.setColor(SR_COLOR);
/*     */           
/* 243 */           g.drawOval(p.x - this.transmissionRange, p.y - this.transmissionRange, this.transmissionRange * 2, this.transmissionRange * 2);
/*     */         }
/* 245 */         if (this.sm.drawIR) {
/* 246 */           g.setColor(IR_COLOR);
/*     */           
/* 248 */           g.drawOval(p.x - this.interferenceRange, p.y - this.interferenceRange, this.interferenceRange * 2, this.interferenceRange * 2);
/*     */         }
/* 250 */         if (this.sm.drawNodeLocation) {
/* 251 */           g.setColor(NODE_TEXT_COLOR);
/* 252 */           g.drawString("(" + Tool.translateX(p.x) + ", " + Tool.translateY(p.y) + ")", p.x - 25, p.y + 25);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void drawConnected(Graphics2D g)
/*     */   {
/* 260 */     g.setColor(CONNECTED_COLOR);
/* 261 */     Object[] nodes = this.dm.getNodes();
/*     */     
/*     */ 
/* 264 */     for (int i = 0; i < nodes.length; i++) {
/* 265 */       Node s = (Node)nodes[i];
/* 266 */       for (int j = i + 1; j < nodes.length; j++) {
/* 267 */         Node d = (Node)nodes[j];
/*     */         
/* 269 */         if ((Math.abs(s.x - d.x) <= this.transmissionRange) && (Math.abs(s.y - d.y) <= this.transmissionRange) && 
/* 270 */           (Tool.distance(s, d) < this.transmissionRange)) {
/* 271 */           g.drawLine(s.x, s.y, d.x, d.y);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void drawLinks(Graphics2D g) {
/* 278 */     g.setStroke(new BasicStroke(1.0F, 1, 2));
/* 279 */     g.setColor(LINK_COLOR);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 284 */     String type = "";
/*     */     
/* 286 */     Object[] links = this.dm.getLinks();
/* 287 */     for (int i = 0; i < links.length; i++) {
/* 288 */       Link link = (Link)links[i];
/* 289 */       int X1 = link.src.x;
/* 290 */       int Y1 = link.src.y;
/* 291 */       int X2 = link.dst.x;
/* 292 */       int Y2 = link.dst.y;
/* 293 */       Line2D.Double line = new Line2D.Double(X1, Y1, X2, Y2);
/* 294 */       g.draw(line);
/* 295 */       if (link.linkType == 0) {
/* 296 */         type = "DuplexLink";
/*     */       } else {
/* 298 */         type = "SimplexLink [" + link.src.id + "->" + link.dst.id + "]";
/*     */       }
/* 300 */       g.drawString(type, (X1 + X2) / 2, (Y1 + Y2) / 2 + 13);
/* 301 */       if (this.sm.drawLinkDetail) {
/* 302 */         if (link.queueSize != -1) {
/* 303 */           g.drawString("capacity:" + link.capacity + " propagationDelay:" + link.propagationDelay + " queueSize:" + link.queueSize + " queueType:" + link.queueType, (X1 + X2) / 2, (Y1 + Y2) / 2 + -11);
/*     */         } else {
/* 305 */           g.drawString("capacity:" + link.capacity + " propagationDelay:" + link.propagationDelay + " queueType:" + link.queueType, (X1 + X2) / 2, (Y1 + Y2) / 2 + -11);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void drawAgents(Graphics2D g)
/*     */   {
/* 313 */     int R = 8;
/*     */     
/* 315 */     int Rdiv2 = R / 2;
/*     */     
/*     */ 
/*     */ 
/* 319 */     g.setStroke(new BasicStroke(1.0F, 1, 2));
/*     */     
/* 321 */     Object[] agents = this.dm.getAgents();
/* 322 */     for (int i = 0; i < agents.length; i++) {
/* 323 */       Agent a = (Agent)agents[i];
/* 324 */       g.setColor(AGENT_COLOR);
/* 325 */       Ellipse2D shap = new Ellipse2D.Double(a.x - Rdiv2, a.y - Rdiv2, R, R);
/* 326 */       g.fill(shap);
/* 327 */       shap = new Ellipse2D.Double(a.x - Rdiv2 - 3, a.y - Rdiv2 - 3, R + 5, R + 5);
/* 328 */       g.draw(shap);
/* 329 */       switch (a.agentType) {
/*     */       case 0: 
/*     */       case 4: 
/*     */       case 8: 
/*     */       case 12: 
/*     */       case 16: 
/* 335 */         if (a.remoteAgent != null) {
/* 336 */           int X1 = a.x;
/* 337 */           int Y1 = a.y;
/* 338 */           int X2 = a.remoteAgent.x;
/* 339 */           int Y2 = a.remoteAgent.y;
/* 340 */           g.setColor(AGENT_LINK_COLOR);
/* 341 */           Line2D.Double line = new Line2D.Double(X1, Y1, X2, Y2);
/* 342 */           g.draw(line);
/*     */         }
/* 344 */         g.setColor(AGENT_COLOR);
/* 345 */         g.drawString("tcp" + String.valueOf(a.id), a.x - R + 2, a.y - R);
/* 346 */         if (this.sm.drawAgentDetail) {
/* 347 */           if (a.packetSize != -1) {
/* 348 */             g.drawString(Agent.convertType(a.agentType) + " size:" + a.packetSize, a.x - R + 2, a.y - 3 * R);
/*     */           } else {
/* 350 */             g.drawString(Agent.convertType(a.agentType) + " size:" + a.packetSize, a.x - R + 2, a.y - 3 * R);
/*     */           }
/*     */         }
/* 353 */         break;
/*     */       case 1: 
/* 355 */         g.setColor(AGENT_COLOR);
/* 356 */         g.drawString("sink" + String.valueOf(a.id), a.x - R + 2, a.y - R);
/* 357 */         if (this.sm.drawAgentDetail) g.drawString(Agent.convertType(a.agentType), a.x - R + 2, a.y - 3 * R);
/* 358 */         break;
/*     */       case 2: 
/* 360 */         if (a.remoteAgent != null) {
/* 361 */           int X1 = a.x;
/* 362 */           int Y1 = a.y;
/* 363 */           int X2 = a.remoteAgent.x;
/* 364 */           int Y2 = a.remoteAgent.y;
/* 365 */           g.setColor(AGENT_LINK_COLOR);
/* 366 */           Line2D.Double line = new Line2D.Double(X1, Y1, X2, Y2);
/* 367 */           g.draw(line);
/*     */         }
/* 369 */         g.setColor(AGENT_COLOR);
/* 370 */         g.drawString("udp" + String.valueOf(a.id), a.x - R + 2, a.y - R);
/* 371 */         if (this.sm.drawAgentDetail) g.drawString(Agent.convertType(a.agentType), a.x - R + 2, a.y - 3 * R);
/* 372 */         break;
/*     */       case 3: 
/* 374 */         g.setColor(AGENT_COLOR);
/* 375 */         g.drawString("null" + String.valueOf(a.id), a.x - R + 2, a.y - R);
/* 376 */         if (this.sm.drawAgentDetail) g.drawString(Agent.convertType(a.agentType), a.x - R + 2, a.y - 3 * R);
/*     */         break;
/*     */       }
/* 379 */       int X1 = a.x;
/* 380 */       int Y1 = a.y;
/* 381 */       int X2 = a.attachedNode.x;
/* 382 */       int Y2 = a.attachedNode.y;
/* 383 */       g.setColor(AGENT_COLOR);
/* 384 */       Line2D.Double line = new Line2D.Double(X1, Y1, X2, Y2);
/* 385 */       g.draw(line);
/*     */     }
/*     */   }
/*     */   
/*     */   private void drawApp(Graphics2D g)
/*     */   {
/* 391 */     int R = 8;
/*     */     
/* 393 */     int Rdiv2 = R / 2;
/*     */     
/*     */ 
/* 396 */     g.setStroke(new BasicStroke(1.0F, 1, 2));
/*     */     
/*     */ 
/* 399 */     String type = "";
/* 400 */     Object[] apps = this.dm.getApps();
/* 401 */     for (int i = 0; i < apps.length; i++) {
/* 402 */       App a = (App)apps[i];
/* 403 */       g.setColor(APP_COLOR);
/* 404 */       Ellipse2D shap = new Ellipse2D.Double(a.x - Rdiv2, a.y - Rdiv2, R, R);
/* 405 */       g.fill(shap);
/* 406 */       switch (a.appType) {
/*     */       case 0: 
/* 408 */         type = "ftp";
/* 409 */         break;
/*     */       case 1: 
/* 411 */         type = "cbr";
/* 412 */         break;
/*     */       case 2: 
/* 414 */         type = "ping";
/*     */       }
/*     */       
/* 417 */       g.drawString(type + String.valueOf(a.id), a.x - R + 2, a.y - R);
/* 418 */       int X1 = a.x;
/* 419 */       int Y1 = a.y;
/* 420 */       int X2 = a.agent.x;
/* 421 */       int Y2 = a.agent.y;
/* 422 */       Line2D.Double line = new Line2D.Double(X1, Y1, X2, Y2);
/* 423 */       g.draw(line);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Liudongdong\Desktop\NSG2.1.jar!\nsg\SceneVirtualizer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */