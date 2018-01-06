/*    */ package nsg.component;
/*    */ 
/*    */ import nsg.NSGParameters;
/*    */ 
/*    */ public class Link
/*    */   extends NSGComponent
/*    */   implements NSGParameters
/*    */ {
/*    */   public int id;
/*    */   public Node src;
/*    */   public Node dst;
/*    */   public int linkType;
/*    */   public int queueType;
/*    */   public float capacity;
/*    */   public int propagationDelay;
/*    */   public int queueSize;
/*    */   
/*    */   public Link(int id, Node src, Node dst)
/*    */   {
/* 20 */     super(2);
/* 21 */     this.id = id;
/* 22 */     this.src = src;
/* 23 */     this.dst = dst;
/* 24 */     this.linkType = 0;
/* 25 */     this.queueType = 0;
/* 26 */     this.propagationDelay = 20;
/* 27 */     this.queueSize = 50;
/* 28 */     this.x = ((int)((src.x + dst.x) / 2.0F));
/* 29 */     this.y = ((int)((src.y + dst.y) / 2.0F));
/*    */   }
/*    */ }


/* Location:              C:\Users\Liudongdong\Desktop\NSG2.1.jar!\nsg\component\Link.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */