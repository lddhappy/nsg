/*     */ package nsg.interactive;
/*     */ 
/*     */ import nsg.DataMaintainer;
import nsg.NSGParameters;
import nsg.SceneManager;
import nsg.SceneVirtualizer;
import nsg.component.Agent;
import nsg.component.NSGComponent;
import nsg.component.Node;
import nsg.menu.AgentPopMenu;
import nsg.tool.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

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
/*     */
/*     */
/*     */
/*     */
/*     */ 
/*     */ public class CreatingAgentModeHandler implements java.awt.event.MouseListener, java.awt.event.MouseMotionListener, NSGParameters
/*     */ {
/*     */   public NSGComponent target;
/*     */   public Node src;
/*     */   public Agent agentSrc;
/*     */   int agentid;
/*     */   int mouseX;
/*     */   int mouseY;
/*     */   SceneManager sm;
/*     */   SceneVirtualizer sv;
/*     */   DataMaintainer dm;
/*     */   AgentPopMenu menu;
/*     */   
/*     */   public CreatingAgentModeHandler(SceneManager sm)
/*     */   {
/*  38 */     this.sm = sm;
/*  39 */     this.sv = sm.sv;
/*     */     
/*  41 */     this.dm = sm.dm;
/*  42 */     this.menu = new AgentPopMenu(sm);
/*     */   }
/*     */   
/*     */   public void mouseMoved(MouseEvent e) {
/*  46 */     this.sm.status.setText("Location : ( " + Tool.translateX(this.sv.shiftX, e.getX(), this.sv.scale) + ", " + Tool.translateY(this.sv.shiftY, e.getY(), this.sv.scale) + " )");
/*  47 */     if (this.sm.scroll(e.getX(), e.getY())) {
/*  48 */       return;
/*     */     }
/*  50 */     int X = (int)(e.getX() / this.sv.scale - this.sv.shiftX);
/*  51 */     int Y = (int)(e.getY() / this.sv.scale - this.sv.shiftY);
/*  52 */     this.mouseX = X;
/*  53 */     this.mouseY = Y;
/*     */     
/*  55 */     this.target = this.dm.findNode(X, Y);
/*  56 */     if (this.target != null) {
/*  57 */       this.sv.repaint();
/*  58 */       return;
/*     */     }
/*  60 */     this.target = this.dm.findAgent(X, Y);
/*     */     
/*  62 */     this.sm.repaint();
/*     */   }
/*     */   
/*     */   public void mousePressed(MouseEvent e) {
/*  66 */     if (SwingUtilities.isRightMouseButton(e)) {
/*  67 */       if ((this.target != null) && (this.target.type == 3)) {
/*  68 */         this.menu.setTarget((Agent)this.target);
/*  69 */         this.menu.show(this.sv, e.getX(), e.getY());
/*     */       }
/*  71 */       return;
/*     */     }
/*  73 */     int X = (int)(e.getX() / this.sv.scale - this.sv.shiftX);
/*  74 */     int Y = (int)(e.getY() / this.sv.scale - this.sv.shiftY);
/*  75 */     if (SwingUtilities.isLeftMouseButton(e)) {
/*  76 */       if (this.target == null) {
/*  77 */         if (this.src != null) {
/*  78 */           addAgent(this.src, X, Y);
/*  79 */           this.src = null;
/*  80 */           this.sv.repaint();
/*     */         }
/*  82 */         if (this.agentSrc != null) {
/*  83 */           this.agentSrc = null;
/*  84 */           this.sv.repaint();
/*     */         }
/*     */       } else {
/*  87 */         if (this.target.type == 1) {
/*  88 */           this.src = ((Node)this.target);
/*  89 */           this.agentSrc = null;
/*     */         }
/*  91 */         else if (this.agentSrc == null) {
/*  92 */           this.src = null;
/*  93 */           this.agentSrc = ((Agent)this.target);
/*     */         }
/*  95 */         else if ((this.agentSrc.agentType + ((Agent)this.target).agentType) % 4 == 1) {
/*  96 */           if (this.agentSrc.remoteAgent != null) {
/*  97 */             this.agentSrc.remoteAgent.remoteAgent = null;
/*     */           }
/*  99 */           if (((Agent)this.target).remoteAgent != null) {
/* 100 */             ((Agent)this.target).remoteAgent.remoteAgent = null;
/*     */           }
/* 102 */           ((Agent)this.target).remoteAgent = this.agentSrc;
/* 103 */           this.agentSrc.remoteAgent = ((Agent)this.target);
/* 104 */           this.agentSrc = null;
/*     */         } else {
/* 106 */           this.src = null;
/* 107 */           this.agentSrc = ((Agent)this.target);
/*     */         }
/*     */         
/*     */ 
/* 111 */         this.sv.repaint();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g) {
/* 117 */     g.setStroke(new java.awt.BasicStroke(1.0F, 1, 2));
/*     */     
/* 119 */     int R = 8;
/* 120 */     int Rdiv2 = R / 2;
/*     */     
/*     */ 
/* 123 */     if (this.target != null) {
/* 124 */       g.setColor(TARGET_COLOR);
/* 125 */       if (this.target.type == 1) {
/* 126 */         R = 30;
/* 127 */         Rdiv2 = R / 2;
/* 128 */         Ellipse2D shap = new Ellipse2D.Double(this.target.x - Rdiv2, this.target.y - Rdiv2, R, R);
/* 129 */         g.draw(shap);
/* 130 */         shap = new Ellipse2D.Double(this.target.x - Rdiv2 - 1, this.target.y - Rdiv2 - 1, R + 2, R + 2);
/* 131 */         g.draw(shap);
/*     */       } else {
/* 133 */         R = 8;
/* 134 */         Rdiv2 = R / 2;
/* 135 */         Ellipse2D shap = new Ellipse2D.Double(this.target.x - Rdiv2, this.target.y - Rdiv2, R, R);
/* 136 */         g.fill(shap);
/* 137 */         shap = new Ellipse2D.Double(this.target.x - Rdiv2 - 3, this.target.y - Rdiv2 - 3, R + 5, R + 5);
/* 138 */         g.draw(shap);
/*     */       }
/*     */     }
/* 141 */     if (this.src != null) {
/* 142 */       g.setColor(SRC_COLOR);
/* 143 */       R = 30;
/* 144 */       Rdiv2 = R / 2;
/* 145 */       Ellipse2D shap = new Ellipse2D.Double(this.src.x - Rdiv2, this.src.y - Rdiv2, R, R);
/* 146 */       g.draw(shap);
/* 147 */       shap = new Ellipse2D.Double(this.src.x - Rdiv2 - 1, this.src.y - Rdiv2 - 1, R + 2, R + 2);
/* 148 */       g.draw(shap);
/*     */       
/* 150 */       int X1 = this.src.x;
/* 151 */       int Y1 = this.src.y;
/* 152 */       int X2 = this.mouseX;
/* 153 */       int Y2 = this.mouseY;
/* 154 */       Line2D.Double line = new Line2D.Double(X1, Y1, X2, Y2);
/* 155 */       g.draw(line);
/*     */     }
/* 157 */     if (this.agentSrc != null) {
/* 158 */       R = 8;
/* 159 */       Rdiv2 = R / 2;
/* 160 */       g.setColor(SRC_COLOR);
/* 161 */       Ellipse2D shap = new Ellipse2D.Double(this.agentSrc.x - Rdiv2, this.agentSrc.y - Rdiv2, R, R);
/* 162 */       g.fill(shap);
/* 163 */       shap = new Ellipse2D.Double(this.agentSrc.x - Rdiv2 - 3, this.agentSrc.y - Rdiv2 - 3, R + 5, R + 5);
/* 164 */       g.draw(shap);
/*     */     }
/*     */   }
/*     */   
/*     */   public void addAgent(Node node, int x, int y) {
/* 169 */     Agent agent = new Agent(this.agentid, x, y);
/* 170 */     switch (this.sv.agentPanel.agentType.getSelectedIndex()) {
/*     */     case 0: 
/* 172 */       agent.agentType = 0;
/* 173 */       if (this.sv.agentPanel.packetSize.getText().equals("")) {
/* 174 */         agent.packetSize = -1;
/*     */       } else {
/* 176 */         agent.packetSize = Integer.parseInt(this.sv.agentPanel.packetSize.getText());
/*     */       }
/* 178 */       break;
/*     */     case 1: 
/* 180 */       agent.agentType = 4;
/* 181 */       if (this.sv.agentPanel.packetSize.getText().equals("")) {
/* 182 */         agent.packetSize = -1;
/*     */       } else {
/* 184 */         agent.packetSize = Integer.parseInt(this.sv.agentPanel.packetSize.getText());
/*     */       }
/* 186 */       break;
/*     */     case 2: 
/* 188 */       agent.agentType = 8;
/* 189 */       if (this.sv.agentPanel.packetSize.getText().equals("")) {
/* 190 */         agent.packetSize = -1;
/*     */       } else {
/* 192 */         agent.packetSize = Integer.parseInt(this.sv.agentPanel.packetSize.getText());
/*     */       }
/* 194 */       break;
/*     */     case 3: 
/* 196 */       agent.agentType = 12;
/* 197 */       if (this.sv.agentPanel.packetSize.getText().equals("")) {
/* 198 */         agent.packetSize = -1;
/*     */       } else {
/* 200 */         agent.packetSize = Integer.parseInt(this.sv.agentPanel.packetSize.getText());
/*     */       }
/* 202 */       break;
/*     */     case 4: 
/* 204 */       agent.agentType = 16;
/* 205 */       if (this.sv.agentPanel.packetSize.getText().equals("")) {
/* 206 */         agent.packetSize = -1;
/*     */       } else {
/* 208 */         agent.packetSize = Integer.parseInt(this.sv.agentPanel.packetSize.getText());
/*     */       }
/* 210 */       break;
/*     */     case 5: 
/* 212 */       agent.agentType = 1;
/* 213 */       break;
/*     */     case 6: 
/* 215 */       agent.agentType = 2;
/* 216 */       if (this.sv.agentPanel.packetSize.getText().equals("")) {
/* 217 */         agent.packetSize = -1;
/*     */       } else {
/* 219 */         agent.packetSize = Integer.parseInt(this.sv.agentPanel.packetSize.getText());
/*     */       }
/* 221 */       break;
/*     */     case 7: 
/* 223 */       agent.agentType = 3;
/* 224 */       break;
/*     */     default: 
/* 226 */       System.err.println("addAgent error");
/*     */     }
/*     */     
/* 229 */     agent.attachedNode = node;
/* 230 */     this.dm.agents.add(agent);
/* 231 */     this.agentid += 1;
/*     */   }
/*     */   
/*     */   public void mouseDragged(MouseEvent e) {}
/*     */   
/*     */   public void mouseClicked(MouseEvent e) {}
/*     */   
/*     */   public void mouseEntered(MouseEvent e) {}
/*     */   
/*     */   public void mouseExited(MouseEvent e) {}
/*     */   
/*     */   public void mouseReleased(MouseEvent e) {}
/*     */ }


/* Location:              C:\Users\Liudongdong\Desktop\NSG2.1.jar!\nsg\interactive\CreatingAgentModeHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */