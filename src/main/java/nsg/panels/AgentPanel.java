/*    */ package nsg.panels;
/*    */ 
/*    */ import java.awt.FlowLayout;
/*    */ import javax.swing.JComboBox;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JTextField;
/*    */ import nsg.NSGParameters;
/*    */ import nsg.SceneVirtualizer;
/*    */ 
/*    */ public class AgentPanel
/*    */   extends JPanel
/*    */   implements NSGParameters
/*    */ {
/*    */   static final long serialVersionUID = 0L;
/* 16 */   public JComboBox agentType = new JComboBox(new String[] { "TCP", "TCP/Tahoe", "TCP/Reno", "TCP/Newreno", "TCP/Vegas", "TCPSink", "UDP", "NULL" });
/* 17 */   public JTextField packetSize = new JTextField("1500", 4);
/* 18 */   JLabel l1 = new JLabel("Agent type");
/* 19 */   JLabel l2 = new JLabel("Packet size");
/* 20 */   JLabel l3 = new JLabel("bytes  ");
/*    */   SceneVirtualizer f;
/*    */   
/*    */   public AgentPanel(SceneVirtualizer f) {
/* 24 */     setLayout(new FlowLayout(0, 0, 0));
/* 25 */     this.f = f;
/* 26 */     ((FlowLayout)getLayout()).setAlignment(0);
/* 27 */     setBackground(NSGParameters.PANEL_COLOR);
/* 28 */     add(this.l1);
/* 29 */     add(this.agentType);
/* 30 */     add(this.l2);
/* 31 */     add(this.packetSize);
/* 32 */     add(this.l3);
/*    */   }
/*    */ }


/* Location:              C:\Users\Liudongdong\Desktop\NSG2.1.jar!\nsg\panels\AgentPanel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */