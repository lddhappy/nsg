/*    */ package nsg.tool;
/*    */ 
/*    */ import nsg.component.Node;
/*    */ 
/*    */ public class Tool {
/*    */   public static double distance(Node s, Node d) {
/*  7 */     int x = s.x - d.x;
/*  8 */     int y = s.y - d.y;
/*  9 */     double dis = Math.sqrt(x * x + y * y);
/* 10 */     return dis;
/*    */   }
/*    */   
/*    */   public static int translateX(int shiftX, int x, float scale) {
/* 14 */     return (int)(x / scale - shiftX);
/*    */   }
/*    */   
/*    */   public static int translateY(int shiftY, int y, float scale)
/*    */   {
/* 19 */     return (int)(10000.0F - (y / scale - shiftY));
/*    */   }
/*    */   
/*    */   public static int translateX(int x)
/*    */   {
/* 24 */     return x;
/*    */   }
/*    */   
/*    */   public static int translateY(int y) {
/* 28 */     return 10000 - y;
/*    */   }
/*    */ }


/* Location:              C:\Users\Liudongdong\Desktop\NSG2.1.jar!\nsg\tool\Tool.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */