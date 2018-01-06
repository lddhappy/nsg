/*    */ package nsg.component;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import nsg.NSGParameters;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Agent
/*    */   extends NSGComponent
/*    */   implements NSGParameters
/*    */ {
/*    */   public int id;
/*    */   public int agentType;
/*    */   public Node attachedNode;
/*    */   public Agent remoteAgent;
/* 19 */   public int packetSize = -1;
/* 20 */   public int window = -1;
/* 21 */   public int maxcwnd = -1;
/* 22 */   public int windowInit = -1;
/* 23 */   public int tcpTick = -1;
/* 24 */   public int maxburst = -1;
/*    */   
/*    */   public Agent(int id, int x, int y)
/*    */   {
/* 28 */     super(3);
/* 29 */     this.id = id;
/* 30 */     this.x = x;
/* 31 */     this.y = y;
/*    */   }
/*    */   
/*    */   public static String convertType(int type) {
/* 35 */     switch (type) {
/*    */     case 0: 
/* 37 */       return "TCP";
/*    */     case 1: 
/* 39 */       return "TCPSink";
/*    */     case 2: 
/* 41 */       return "UDP";
/*    */     case 3: 
/* 43 */       return "Null";
/*    */     case 4: 
/* 45 */       return "TCP/FullTcp/Tahoe";
/*    */     case 8: 
/* 47 */       return "TCP/Reno";
/*    */     case 12: 
/* 49 */       return "TCP/Newreno";
/*    */     case 16: 
/* 51 */       return "TCP/Vegas";
/*    */     }
/* 53 */     System.out.println("Agent convertType error");
/* 54 */     return "unknow";
/*    */   }
/*    */ }


/* Location:              C:\Users\Liudongdong\Desktop\NSG2.1.jar!\nsg\component\Agent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */