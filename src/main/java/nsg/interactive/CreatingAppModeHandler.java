/*     */ package nsg.interactive;
/*     */ 
/*     */ import nsg.DataMaintainer;
import nsg.NSGParameters;
import nsg.SceneManager;
import nsg.SceneVirtualizer;
import nsg.component.Agent;
import nsg.component.App;
import nsg.menu.AppPopMenu;
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
/*     */ public class CreatingAppModeHandler implements java.awt.event.MouseListener, java.awt.event.MouseMotionListener, NSGParameters
/*     */ {
/*     */   public Agent target;
/*     */   public App appTarget;
/*     */   public Agent src;
/*     */   int mouseX;
/*     */   int mouseY;
/*  28 */   int appid = 0;
/*     */   
/*     */   AppPopMenu menu;
/*     */   SceneManager sm;
/*     */   SceneVirtualizer sv;
/*     */   DataMaintainer dm;
/*     */   
/*     */   public CreatingAppModeHandler(SceneManager sm)
/*     */   {
/*  37 */     this.sm = sm;
/*  38 */     this.sv = sm.sv;
/*  39 */     this.dm = sm.dm;
/*  40 */     this.appid = 0;
/*  41 */     this.menu = new AppPopMenu(sm);
/*     */   }
/*     */   
/*     */   public void mouseDragged(MouseEvent arg0) {}
/*     */   
/*     */   public void mouseClicked(MouseEvent arg0) {}
/*     */   
/*     */   public void mouseEntered(MouseEvent arg0) {}
/*     */   
/*     */   public void mouseExited(MouseEvent arg0) {}
/*     */   
/*     */   public void mouseReleased(MouseEvent arg0) {}
/*     */   
/*  54 */   public void mouseMoved(MouseEvent e) { this.sm.status.setText("Location : ( " + Tool.translateX(this.sv.shiftX, e.getX(), this.sv.scale) + ", " + Tool.translateY(this.sv.shiftY, e.getY(), this.sv.scale) + " )");
/*  55 */     if (this.sm.scroll(e.getX(), e.getY())) {
/*  56 */       return;
/*     */     }
/*     */     
/*  59 */     int X = (int)(e.getX() / this.sv.scale - this.sv.shiftX);
/*  60 */     int Y = (int)(e.getY() / this.sv.scale - this.sv.shiftY);
/*  61 */     this.mouseX = X;
/*  62 */     this.mouseY = Y;
/*     */     
/*  64 */     this.target = this.dm.findAgent(X, Y);
/*  65 */     if ((this.target != null) && (this.target.agentType % 2 == 1)) {
/*  66 */       this.target = null;
/*     */     }
/*  68 */     if (this.target != null) {
/*  69 */       Object[] apps = this.dm.getApps();
/*     */       
/*  71 */       for (int j = 0; j < apps.length; j++) {
/*  72 */         App app = (App)apps[j];
/*  73 */         if (app.agent == this.target) {
/*  74 */           this.target = null;
/*  75 */           break;
/*     */         }
/*     */       }
/*     */     }
/*  79 */     if (this.target == null) {
/*  80 */       this.appTarget = this.dm.findApp(X, Y);
/*     */     }
/*  82 */     this.sm.repaint();
/*     */   }
/*     */   
/*  85 */   public void mousePressed(MouseEvent e) { if (SwingUtilities.isRightMouseButton(e)) {
/*  86 */       if (this.appTarget != null) {
/*  87 */         this.menu.setTarget(this.appTarget);
/*  88 */         this.menu.show(this.sv, e.getX(), e.getY());
/*     */       }
/*  90 */       return;
/*     */     }
/*  92 */     int X = (int)(e.getX() / this.sv.scale - this.sv.shiftX);
/*  93 */     int Y = (int)(e.getY() / this.sv.scale - this.sv.shiftY);
/*  94 */     if (SwingUtilities.isLeftMouseButton(e)) {
/*  95 */       if (this.target != null) {
/*  96 */         this.src = this.target;
/*  97 */       } else if (this.src != null) {
/*  98 */         addApp(X, Y, this.src);
/*  99 */         this.src = null;
/*     */       }
/* 101 */       this.sv.repaint();
/*     */     }
/*     */   }
/*     */   
/*     */   public void addApp(int x, int y, Agent agent) {
/* 106 */     App app = new App(this.appid, x, y, agent);
/* 107 */     switch (this.sv.appPanel.appType.getSelectedIndex()) {
/*     */     case 0: 
/* 109 */       app.appType = 0;
/* 110 */       app.startTime = Float.parseFloat(this.sv.appPanel.startTime.getText());
/* 111 */       app.stopTime = Float.parseFloat(this.sv.appPanel.stopTime.getText());
/* 112 */       app.packetSize = Integer.parseInt(this.sv.appPanel.packetSize.getText());
/*     */       
/* 114 */       break;
/*     */     case 1: 
/* 116 */       app.appType = 1;
/* 117 */       app.startTime = Float.parseFloat(this.sv.appPanel.startTime.getText());
/* 118 */       app.stopTime = Float.parseFloat(this.sv.appPanel.stopTime.getText());
/* 119 */       app.packetSize = Integer.parseInt(this.sv.appPanel.packetSize.getText());
/* 120 */       app.rate = Float.parseFloat(this.sv.appPanel.rate.getText());
/*     */       
/* 122 */       break;
/*     */     case 2: 
/* 124 */       app.appType = 2;
/* 125 */       app.startTime = Float.parseFloat(this.sv.appPanel.startTime.getText());
/* 126 */       break;
/*     */     case 3: 
/* 128 */       app.appType = 3;
/* 129 */       break;
/*     */     case 4: 
/* 131 */       app.appType = 4;
/* 132 */       break;
/*     */     default: 
/* 134 */       System.out.println("addApp error");
/* 135 */       return;
/*     */     }
/* 137 */     this.dm.apps.add(app);
/* 138 */     this.appid += 1;
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g)
/*     */   {
/* 143 */     g.setStroke(new java.awt.BasicStroke(1.0F, 1, 2));
/* 144 */     int R = 8;
/* 145 */     int Rdiv2 = R / 2;
/*     */     
/* 147 */     g.setColor(TARGET_COLOR);
/* 148 */     if (this.target != null) {
/* 149 */       Ellipse2D shap = new Ellipse2D.Double(this.target.x - Rdiv2, this.target.y - Rdiv2, R, R);
/* 150 */       g.fill(shap);
/* 151 */       shap = new Ellipse2D.Double(this.target.x - Rdiv2 - 3, this.target.y - Rdiv2 - 3, R + 5, R + 5);
/* 152 */       g.draw(shap);
/*     */     }
/* 154 */     if (this.appTarget != null)
/*     */     {
/* 156 */       Ellipse2D shap = new Ellipse2D.Double(this.appTarget.x - Rdiv2, this.appTarget.y - Rdiv2, R, R);
/* 157 */       g.fill(shap);
/*     */     }
/* 159 */     g.setColor(SRC_COLOR);
/* 160 */     if (this.src != null) {
/* 161 */       Ellipse2D shap = new Ellipse2D.Double(this.src.x - Rdiv2, this.src.y - Rdiv2, R, R);
/* 162 */       g.fill(shap);
/* 163 */       shap = new Ellipse2D.Double(this.src.x - Rdiv2 - 3, this.src.y - Rdiv2 - 3, R + 5, R + 5);
/* 164 */       g.draw(shap);
/*     */       
/* 166 */       int X1 = this.src.x;
/* 167 */       int Y1 = this.src.y;
/* 168 */       int X2 = this.mouseX;
/* 169 */       int Y2 = this.mouseY;
/* 170 */       Line2D.Double line = new Line2D.Double(X1, Y1, X2, Y2);
/* 171 */       g.draw(line);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Liudongdong\Desktop\NSG2.1.jar!\nsg\interactive\CreatingAppModeHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */