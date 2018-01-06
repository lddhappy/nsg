/*     */ package nsg.interactive;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.SwingUtilities;
/*     */ import nsg.DataMaintainer;
/*     */ import nsg.NSGParameters;
/*     */ import nsg.SceneManager;
/*     */ import nsg.SceneVirtualizer;
/*     */ import nsg.component.Node;
/*     */ import nsg.component.WiredNode;
/*     */ import nsg.menu.NodePopMenu;
/*     */ import nsg.panels.NodePanel;
/*     */ import nsg.tool.Tool;
/*     */ 
/*     */ public class CreatingNodeModeHandler implements java.awt.event.MouseListener, java.awt.event.MouseMotionListener, NSGParameters
/*     */ {
/*     */   NodePopMenu menu;
/*  24 */   int nodeid = 0;
/*     */   SceneManager sm;
/*     */   SceneVirtualizer sv;
/*     */   DataMaintainer dm;
/*     */   Node target;
/*     */   
/*     */   public CreatingNodeModeHandler(SceneManager sm) {
/*  31 */     this.sm = sm;
/*  32 */     this.dm = sm.dm;
/*  33 */     this.sv = sm.sv;
/*  34 */     this.nodeid = 0;
/*  35 */     this.menu = new NodePopMenu(sm);
/*     */   }
/*     */   
/*     */   public void mouseDragged(MouseEvent e) {
/*  39 */     if (SwingUtilities.isRightMouseButton(e)) return;
/*  40 */     if (this.target == null) return;
/*  41 */     this.sm.status.setText("Location : ( " + Tool.translateX(this.sv.shiftX, e.getX(), this.sv.scale) + ", " + Tool.translateY(this.sv.shiftY, e.getY(), this.sv.scale) + " )");
/*  42 */     int X = (int)(e.getX() / this.sv.scale - this.sv.shiftX);
/*  43 */     int Y = (int)(e.getY() / this.sv.scale - this.sv.shiftY);
/*  44 */     this.target.x = X;
/*  45 */     this.target.y = Y;
/*  46 */     this.sm.repaint();
/*     */   }
/*     */   
/*     */   public void mouseMoved(MouseEvent e)
/*     */   {
/*  51 */     this.sm.status.setText("Location : ( " + Tool.translateX(this.sv.shiftX, e.getX(), this.sv.scale) + ", " + Tool.translateY(this.sv.shiftY, e.getY(), this.sv.scale) + " )");
/*  52 */     if (this.sm.scroll(e.getX(), e.getY())) {
/*  53 */       return;
/*     */     }
/*  55 */     int X = (int)(e.getX() / this.sv.scale - this.sv.shiftX);
/*  56 */     int Y = (int)(e.getY() / this.sv.scale - this.sv.shiftY);
/*  57 */     this.target = this.dm.findNode(X, Y);
/*  58 */     this.sm.repaint();
/*     */   }
/*     */   
/*     */   public void mousePressed(MouseEvent e) {
/*  62 */     if (SwingUtilities.isRightMouseButton(e)) {
/*  63 */       if (this.target != null) {
/*  64 */         this.menu.setTarget(this.target);
/*  65 */         this.menu.show(this.sv, e.getX(), e.getY());
/*     */       }
/*  67 */       return;
/*     */     }
/*  69 */     if (SwingUtilities.isLeftMouseButton(e))
/*     */     {
/*  71 */       if (this.target != null) { return;
/*     */       }
/*     */       
/*  74 */       int X = (int)(e.getX() / this.sv.scale - this.sv.shiftX);
/*  75 */       int Y = (int)(e.getY() / this.sv.scale - this.sv.shiftY);
/*  76 */       switch (this.sv.nodePanel.type.getSelectedIndex()) {
/*     */       case 0: 
/*  78 */         addNode(X, Y);
/*  79 */         break;
/*     */       case 1: 
/*  81 */         for (int i = 0; i < Integer.parseInt(this.sv.nodePanel.vNodes.getText()); i++) {
/*  82 */           addNode(X, Y);
/*  83 */           Y += Integer.parseInt(this.sv.nodePanel.vDistance.getText());
/*     */         }
/*  85 */         break;
/*     */       case 2: 
/*  87 */         for (int i = 0; i < Integer.parseInt(this.sv.nodePanel.hNodes.getText()); i++) {
/*  88 */           addNode(X, Y);
/*  89 */           X += Integer.parseInt(this.sv.nodePanel.hDistance.getText());
/*     */         }
/*  91 */         break;
/*     */       case 3: 
/*  93 */         int tmp = X;
/*  94 */         for (int i = 0; i < Integer.parseInt(this.sv.nodePanel.vNodes.getText()); i++) {
/*  95 */           X = tmp;
/*  96 */           for (int j = 0; j < Integer.parseInt(this.sv.nodePanel.hNodes.getText()); j++) {
/*  97 */             addNode(X, Y);
/*  98 */             X += Integer.parseInt(this.sv.nodePanel.vDistance.getText());
/*     */           }
/* 100 */           Y += Integer.parseInt(this.sv.nodePanel.hDistance.getText());
/*     */         }
/* 102 */         break;
/*     */       case 4: 
/* 104 */         for (int i = 0; i < Integer.parseInt(this.sv.nodePanel.vNodes.getText()); i++) {
/* 105 */           int randX = (int)(Math.random() * Integer.parseInt(this.sv.nodePanel.xRange.getText()));
/* 106 */           int randY = (int)(Math.random() * Integer.parseInt(this.sv.nodePanel.yRange.getText()));
/* 107 */           addNode(X + randX, Y + randY);
/*     */         }
/*     */       }
/*     */       
/* 111 */       this.sv.repaint();
/* 112 */       return;
/*     */     }
/*     */   }
/*     */   
/* 116 */   public void draw(Graphics2D g) { if (this.target != null) {
/* 117 */       g.setStroke(new java.awt.BasicStroke(1.0F, 1, 2));
/*     */       
/* 119 */       int R = 30;
/* 120 */       int Rdiv2 = R / 2;
/* 121 */       g.setColor(TARGET_COLOR);
/* 122 */       java.awt.geom.Ellipse2D shap = new Ellipse2D.Double(this.target.x - Rdiv2, this.target.y - Rdiv2, R, R);
/* 123 */       g.draw(shap);
/* 124 */       shap = new Ellipse2D.Double(this.target.x - Rdiv2 - 1, this.target.y - Rdiv2 - 1, R + 2, R + 2);
/* 125 */       g.draw(shap);
/*     */     }
/*     */   }
/*     */   
/*     */   public void addNode(int x, int y) {
/* 130 */     this.dm.nodes.add(new WiredNode(this.nodeid, x, y));
/* 131 */     this.nodeid += 1;
/*     */   }
/*     */   
/*     */   public void mouseClicked(MouseEvent e) {}
/*     */   
/*     */   public void mouseEntered(MouseEvent e) {}
/*     */   
/*     */   public void mouseExited(MouseEvent e) {}
/*     */   
/*     */   public void mouseReleased(MouseEvent e) {}
/*     */ }


/* Location:              C:\Users\Liudongdong\Desktop\NSG2.1.jar!\nsg\interactive\CreatingNodeModeHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */