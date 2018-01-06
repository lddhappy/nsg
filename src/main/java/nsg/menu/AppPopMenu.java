/*    */ package nsg.menu;
/*    */ 
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.JMenuItem;
/*    */ import javax.swing.JPopupMenu;
/*    */ import nsg.SceneManager;
/*    */ import nsg.component.App;
/*    */ 
/*    */ public class AppPopMenu extends JPopupMenu
/*    */ {
/*    */   static final long serialVersionUID = 0L;
/*    */   App target;
/*    */   SceneManager sm;
/*    */   
/*    */   public void setTarget(App target)
/*    */   {
/* 18 */     this.target = target;
/*    */   }
/*    */   
/*    */   public AppPopMenu(SceneManager sm) {
/* 22 */     this.sm = sm;
/*    */     
/* 24 */     JMenuItem item = new JMenuItem("Delete");
/* 25 */     item.addActionListener(new ActionListener() {
/*    */       public void actionPerformed(ActionEvent e) {
/* 27 */         AppPopMenu.this.sm.dm.removeApp(AppPopMenu.this.target);
/*    */       }
/* 29 */     });
/* 30 */     add(item);
/* 31 */     item = new JMenuItem("Setup");
/* 32 */     item.addActionListener(new ActionListener() {
/*    */       public void actionPerformed(ActionEvent e) {
/* 34 */         AppPopMenu.this.sm.appmenu.setTarget(AppPopMenu.this.target);
/* 35 */         AppPopMenu.this.sm.appmenu.setVisible(true);
/*    */       }
/* 37 */     });
/* 38 */     add(item);
/*    */   }
/*    */ }


/* Location:              C:\Users\Liudongdong\Desktop\NSG2.1.jar!\nsg\menu\AppPopMenu.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */