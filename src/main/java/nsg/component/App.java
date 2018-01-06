/*    */ package nsg.component;
/*    */ 
/*    */ public class App
/*    */   extends NSGComponent
/*    */ {
/*    */   public int id;
/*    */   public Agent agent;
/*    */   public int appType;
/*    */   public int packetSize;
/*    */   public float rate;
/*    */   public String random;
/*    */   public float startTime;
/*    */   public float stopTime;
/*    */   
/*    */   public App(int id, int x, int y, Agent agent)
/*    */   {
/* 17 */     super(4);
/* 18 */     this.id = id;
/* 19 */     this.x = x;
/* 20 */     this.y = y;
/* 21 */     this.agent = agent;
/*    */   }
/*    */ }


/* Location:              C:\Users\Liudongdong\Desktop\NSG2.1.jar!\nsg\component\App.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */