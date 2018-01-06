/*    */ package nsg.component;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class Node extends NSGComponent
/*    */ {
/*    */   static final long serialVersionUID = 0L;
/*    */   public int id;
/*    */   public ArrayList waypoints;
/*    */   
/*    */   public Node(int id, int x, int y) {
/* 12 */     super(1);
/* 13 */     this.id = id;
/* 14 */     this.x = x;
/* 15 */     this.y = y;
/*    */   }
/*    */ }


/* Location:              C:\Users\Liudongdong\Desktop\NSG2.1.jar!\nsg\component\Node.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */