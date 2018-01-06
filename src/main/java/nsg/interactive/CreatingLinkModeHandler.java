/*     */ package nsg.interactive;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Ellipse2D.Double;
///*     */ import java.awt.geom.Line2D.Double;
/*     */ import java.awt.geom.Line2D;
import java.util.ArrayList;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.SwingUtilities;
/*     */ import nsg.DataMaintainer;
/*     */ import nsg.NSGParameters;
/*     */ import nsg.SceneManager;
/*     */ import nsg.SceneVirtualizer;
/*     */ import nsg.component.Link;
/*     */ import nsg.component.Node;
/*     */ import nsg.menu.LinkPopMenu;
/*     */ import nsg.panels.LinkPanel;
/*     */ import nsg.tool.Tool;
/*     */ 
/*     */ public class CreatingLinkModeHandler implements java.awt.event.MouseListener, java.awt.event.MouseMotionListener, NSGParameters
/*     */ {
/*     */   public Node target;
/*     */   Link targetLink;
/*     */   public Node src;
/*     */   public Node dst;
/*  29 */   int linkid = 0;
/*     */   
/*     */   LinkPopMenu menu;
/*     */   SceneManager sm;
/*     */   SceneVirtualizer sv;
/*     */   DataMaintainer dm;
/*     */   
/*     */   public CreatingLinkModeHandler(SceneManager sm)
/*     */   {
/*  38 */     this.sm = sm;
/*  39 */     this.dm = sm.dm;
/*  40 */     this.sv = sm.sv;
/*  41 */     this.linkid = 0;
/*  42 */     this.menu = new LinkPopMenu(sm);
/*     */   }
/*     */   
/*     */   public void mousePressed(MouseEvent e) {
/*  46 */     if (SwingUtilities.isRightMouseButton(e)) {
/*  47 */       if (this.targetLink != null) {
/*  48 */         this.menu.setTarget(this.targetLink);
/*  49 */         this.menu.show(this.sv, e.getX(), e.getY());
/*     */       }
/*  51 */       return;
/*     */     }
/*  53 */     if (SwingUtilities.isLeftMouseButton(e))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  62 */       if (this.target == null) {
/*  63 */         if (this.src != null) {
/*  64 */           this.src = null;
/*     */         }
/*     */       }
/*  67 */       else if (this.src == null) {
/*  68 */         this.src = this.target;
/*     */       }
/*  70 */       else if (this.src == this.target) {
/*  71 */         this.src = null;
/*     */       } else {
/*  73 */         addLink(this.src, this.target);
/*  74 */         this.src = null;
/*     */       }
/*     */       
/*     */ 
/*  78 */       this.sv.repaint();
/*  79 */       return;
/*     */     }
/*     */   }
/*     */   
/*     */   public void mouseMoved(MouseEvent e) {
/*  84 */     this.sm.status.setText("Location : ( " + Tool.translateX(this.sv.shiftX, e.getX(), this.sv.scale) + ", " + Tool.translateY(this.sv.shiftY, e.getY(), this.sv.scale) + " )");
/*  85 */     if (this.sm.scroll(e.getX(), e.getY())) {
/*  86 */       return;
/*     */     }
/*  88 */     int X = (int)(e.getX() / this.sv.scale - this.sv.shiftX);
/*  89 */     int Y = (int)(e.getY() / this.sv.scale - this.sv.shiftY);
/*     */     
/*  91 */     this.target = this.dm.findNode(X, Y);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  99 */     this.targetLink = this.dm.findLink(X, Y);
/* 100 */     this.sm.repaint();
/*     */   }
/*     */   
/*     */   public void addLink(Node src, Node dst) {
/* 104 */     Link link = new Link(this.linkid, src, dst);
/* 105 */     if ((this.sv.linkPanel.capacity.getText().equals("")) || 
/* 106 */       (this.sv.linkPanel.propagationDelay.getText().equals(""))) {
/* 107 */       javax.swing.JOptionPane.showMessageDialog(this.sv, "Capacity and propagation delay are necessary");
/* 108 */       return;
/*     */     }
/* 110 */     link.capacity = Float.parseFloat(this.sv.linkPanel.capacity.getText());
/* 111 */     link.linkType = this.sv.linkPanel.linkType.getSelectedIndex();
/* 112 */     link.propagationDelay = Integer.parseInt(this.sv.linkPanel.propagationDelay.getText());
/* 113 */     if (this.sv.linkPanel.queueSize.getText().equals("")) {
/* 114 */       link.queueSize = -1;
/*     */     } else {
/* 116 */       link.queueSize = Integer.parseInt(this.sv.linkPanel.queueSize.getText());
/*     */     }
/* 118 */     link.queueType = this.sv.linkPanel.queueType.getSelectedIndex();
/* 119 */     this.dm.links.add(link);
/* 120 */     this.linkid += 1;
/*     */   }
/*     */   
/*     */ 
/*     */   public void draw(Graphics2D g)
/*     */   {
/* 126 */     g.setStroke(new java.awt.BasicStroke(1.0F, 1, 2));
/*     */     
/* 128 */     int R = 30;
/* 129 */     int Rdiv2 = R / 2;
/*     */     
/* 131 */     if (this.target != null) {
/* 132 */       g.setColor(TARGET_COLOR);
/* 133 */       Ellipse2D shap = new Ellipse2D.Double(this.target.x - Rdiv2, this.target.y - Rdiv2, R, R);
/* 134 */       g.draw(shap);
/* 135 */       shap = new Ellipse2D.Double(this.target.x - Rdiv2 - 1, this.target.y - Rdiv2 - 1, R + 2, R + 2);
/* 136 */       g.draw(shap);
/*     */     }
/* 138 */     if (this.src != null) {
/* 139 */       g.setColor(SRC_COLOR);
/* 140 */       Ellipse2D shap = new Ellipse2D.Double(this.src.x - Rdiv2, this.src.y - Rdiv2, R, R);
/* 141 */       g.draw(shap);
/* 142 */       shap = new Ellipse2D.Double(this.src.x - Rdiv2 - 1, this.src.y - Rdiv2 - 1, R + 2, R + 2);
/* 143 */       g.draw(shap);
/*     */     }
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
/* 159 */     if (this.targetLink != null) {
/* 160 */       g.setColor(SRC_COLOR);
/*     */       
/* 162 */       int X1 = this.targetLink.src.x;
/* 163 */       int Y1 = this.targetLink.src.y;
/* 164 */       int X2 = this.targetLink.dst.x;
/* 165 */       int Y2 = this.targetLink.dst.y;
/* 166 */       Line2D.Double line = new Line2D.Double(X1, Y1, X2, Y2);
/* 167 */       g.draw(line);
/* 168 */       line = new Line2D.Double(X1, Y1 - 1, X2, Y2 - 1);
/* 169 */       g.draw(line);
/* 170 */       line = new Line2D.Double(X1, Y1 + 1, X2, Y2 + 1);
/* 171 */       g.draw(line);
/*     */     }
/*     */   }
/*     */   
/*     */   public void mouseClicked(MouseEvent e) {}
/*     */   
/*     */   public void mouseEntered(MouseEvent e) {}
/*     */   
/*     */   public void mouseExited(MouseEvent e) {}
/*     */   
/*     */   public void mouseReleased(MouseEvent e) {}
/*     */   
/*     */   public void mouseDragged(MouseEvent e) {}
/*     */ }


/* Location:              C:\Users\Liudongdong\Desktop\NSG2.1.jar!\nsg\interactive\CreatingLinkModeHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */