/*    */ package nsg.menu;
/*    */ 
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.JMenuItem;
/*    */ import javax.swing.JPopupMenu;
/*    */ import nsg.SceneManager;
/*    */ import nsg.component.Link;
/*    */ 
/*    */ public class LinkPopMenu extends JPopupMenu
/*    */ {
/*    */   static final long serialVersionUID = 0L;
/*    */   Link target;
/*    */   SceneManager sm;
/*    */   
/*    */   public void setTarget(Link target)
/*    */   {
/* 18 */     this.target = target;
/*    */   }
/*    */   
/*    */   public LinkPopMenu(SceneManager sm) {
/* 22 */     this.sm = sm;
/*    */     
/* 24 */     JMenuItem item = new JMenuItem("Delete");
/* 25 */     item.addActionListener(new ActionListener() {
/*    */       public void actionPerformed(ActionEvent e) {
/* 27 */         LinkPopMenu.this.sm.dm.removeLink(LinkPopMenu.this.target);
/*    */       }
/* 29 */     });
/* 30 */     add(item);
/* 31 */     item = new JMenuItem("Setup");
/* 32 */     item.addActionListener(new ActionListener() {
/*    */       public void actionPerformed(ActionEvent e) {
/* 34 */         LinkPopMenu.this.sm.linkmenu.setTarget(LinkPopMenu.this.target);
/* 35 */         LinkPopMenu.this.sm.linkmenu.setVisible(true);
/*    */       }
/* 37 */     });
/* 38 */     add(item);
/*    */   }
/*    */ }


/* Location:              C:\Users\Liudongdong\Desktop\NSG2.1.jar!\nsg\menu\LinkPopMenu.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */