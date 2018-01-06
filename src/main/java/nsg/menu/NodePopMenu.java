/*    */ package nsg.menu;
/*    */ 
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.JMenuItem;
/*    */ import javax.swing.JPopupMenu;
/*    */ import nsg.DataMaintainer;
/*    */ import nsg.NSGParameters;
/*    */ import nsg.SceneManager;
/*    */ import nsg.component.Node;
/*    */ 
/*    */ public class NodePopMenu extends JPopupMenu implements NSGParameters
/*    */ {
/*    */   static final long serialVersionUID = 0L;
/*    */   Node target;
/*    */   SceneManager sm;
/*    */   
/*    */   public void setTarget(Node target)
/*    */   {
/* 20 */     this.target = target;
/*    */   }
/*    */   
/*    */   public NodePopMenu(SceneManager sm) {
/* 24 */     this.sm = sm;
/*    */     
/* 26 */     JMenuItem item = new JMenuItem("Delete");
/* 27 */     item.addActionListener(new ActionListener() {
/*    */       public void actionPerformed(ActionEvent e) {
/* 29 */         NodePopMenu.this.sm.dm.removeNode(NodePopMenu.this.target);
/*    */       }
/* 31 */     });
/* 32 */     add(item);
/* 33 */     if (this.sm.sceneMode == 2) {
/* 34 */       item = new JMenuItem("Waypoint");
/* 35 */       item.addActionListener(new ActionListener() {
/*    */         public void actionPerformed(ActionEvent e) {
/* 37 */           NodePopMenu.this.sm.waypointMenu.setTarget(NodePopMenu.this.target);
/* 38 */           NodePopMenu.this.sm.waypointMenu.setVisible(true);
/*    */         }
/* 40 */       });
/* 41 */       add(item);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Liudongdong\Desktop\NSG2.1.jar!\nsg\menu\NodePopMenu.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */