/*    */ package nsg.interactive;
/*    */ 
/*    */ import java.awt.event.MouseEvent;
/*    */ import java.awt.event.MouseListener;
/*    */ import java.awt.event.MouseMotionListener;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.SwingUtilities;
/*    */ import nsg.SceneManager;
/*    */ import nsg.SceneVirtualizer;
/*    */ import nsg.tool.Tool;
/*    */ 
/*    */ public class HandModeHandler
/*    */   implements MouseListener, MouseMotionListener
/*    */ {
/*    */   SceneVirtualizer sv;
/*    */   SceneManager sm;
/*    */   int referenceX;
/*    */   int referenceY;
/*    */   
/*    */   public HandModeHandler(SceneManager sm)
/*    */   {
/* 22 */     this.sm = sm;
/* 23 */     this.sv = sm.sv;
/*    */   }
/*    */   
/*    */   public void mouseDragged(MouseEvent e) {
/* 27 */     if (SwingUtilities.isLeftMouseButton(e))
/*    */     {
/* 29 */       this.sv.shiftX += (int)((e.getX() - this.referenceX) / this.sv.scale);
/* 30 */       this.sv.shiftY += (int)((e.getY() - this.referenceY) / this.sv.scale);
/* 31 */       this.referenceX = e.getX();
/* 32 */       this.referenceY = e.getY();
/* 33 */       this.sm.centerX = (this.sv.getWidth() / 2 / this.sv.scale - this.sv.shiftX);
/* 34 */       this.sm.centerY = (this.sv.getHeight() / 2 / this.sv.scale - this.sv.shiftY);
/* 35 */       this.sm.repaint();
/* 36 */       return;
/*    */     }
/*    */   }
/*    */   
/*    */   public void mouseMoved(MouseEvent e) {
/* 41 */     this.sm.status.setText("Location : ( " + Tool.translateX(this.sv.shiftX, e.getX(), this.sv.scale) + ", " + Tool.translateY(this.sv.shiftY, e.getY(), this.sv.scale) + " )");
/* 42 */     if (this.sm.scroll(e.getX(), e.getY())) {
/* 43 */       return;
/*    */     }
/* 45 */     this.sm.repaint();
/*    */   }
/*    */   
/*    */   public void mouseClicked(MouseEvent arg0) {}
/*    */   
/*    */   public void mouseEntered(MouseEvent arg0) {}
/*    */   
/*    */   public void mouseExited(MouseEvent arg0) {}
/*    */   
/* 54 */   public void mousePressed(MouseEvent e) { if (SwingUtilities.isLeftMouseButton(e))
/*    */     {
/* 56 */       this.referenceX = e.getX();
/* 57 */       this.referenceY = e.getY();
/* 58 */       return;
/*    */     }
/*    */   }
/*    */   
/*    */   public void mouseReleased(MouseEvent arg0) {}
/*    */ }


/* Location:              C:\Users\Liudongdong\Desktop\NSG2.1.jar!\nsg\interactive\HandModeHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */