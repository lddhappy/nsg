/*    */ package nsg.menu;
/*    */ 
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.JMenuItem;
/*    */ import javax.swing.JPopupMenu;
/*    */ import nsg.SceneManager;
/*    */ import nsg.component.Agent;
/*    */ 
/*    */ public class AgentPopMenu extends JPopupMenu
/*    */ {
/*    */   static final long serialVersionUID = 0L;
/*    */   Agent target;
/*    */   SceneManager sm;
/*    */   
/*    */   public void setTarget(Agent target)
/*    */   {
/* 18 */     this.target = target;
/*    */   }
/*    */   
/*    */   public AgentPopMenu(SceneManager sm) {
/* 22 */     this.sm = sm;
/*    */     
/* 24 */     JMenuItem item = new JMenuItem("Delete");
/* 25 */     item.addActionListener(new ActionListener() {
/*    */       public void actionPerformed(ActionEvent e) {
/* 27 */         AgentPopMenu.this.sm.dm.removeAgent(AgentPopMenu.this.target);
/*    */       }
/* 29 */     });
/* 30 */     add(item);
/*    */     
/* 32 */     item = new JMenuItem("Setup");
/* 33 */     item.addActionListener(new ActionListener() {
/*    */       public void actionPerformed(ActionEvent e) {
/* 35 */         AgentPopMenu.this.sm.agentmenu.setTarget(AgentPopMenu.this.target);
/* 36 */         AgentPopMenu.this.sm.agentmenu.setVisible(true);
/*    */       }
/* 38 */     });
/* 39 */     add(item);
/*    */   }
/*    */ }


/* Location:              C:\Users\Liudongdong\Desktop\NSG2.1.jar!\nsg\menu\AgentPopMenu.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */