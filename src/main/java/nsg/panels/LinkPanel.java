/*    */ package nsg.panels;
/*    */ 
/*    */ import java.awt.FlowLayout;
/*    */ import javax.swing.JComboBox;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JTextField;
/*    */ import nsg.NSGParameters;
/*    */ import nsg.SceneVirtualizer;
/*    */ import nsg.component.Link;
/*    */ 
/*    */ 
/*    */ public class LinkPanel
/*    */   extends JPanel
/*    */ {
/*    */   static final long serialVersionUID = 0L;
/* 17 */   public JComboBox queueType = new JComboBox(new String[] { "DropTail", "RED", "FQ", "DRR", "SFQ", "CBQ" });
/* 18 */   public JComboBox linkType = new JComboBox(new String[] { "duplex-link", "simplex-link" });
/* 19 */   public JTextField capacity = new JTextField("100");
/* 20 */   public JTextField propagationDelay = new JTextField("10");
/* 21 */   public JTextField queueSize = new JTextField("50");
/*    */   Link link;
/*    */   SceneVirtualizer sv;
/*    */   
/*    */   public LinkPanel(SceneVirtualizer sv)
/*    */   {
/* 27 */     setLayout(new FlowLayout(0, 0, 0));
/* 28 */     this.sv = sv;
/* 29 */     this.capacity.setColumns(4);
/* 30 */     this.propagationDelay.setColumns(4);
/* 31 */     this.queueSize.setColumns(4);
/*    */     
/* 33 */     ((FlowLayout)getLayout()).setAlignment(0);
/* 34 */     setBackground(NSGParameters.PANEL_COLOR);
/* 35 */     add(new JLabel("Lnik type"));
/* 36 */     add(this.linkType);
/* 37 */     add(new JLabel("   Queue type"));
/* 38 */     add(this.queueType);
/* 39 */     add(new JLabel("   Capacity"));
/* 40 */     add(this.capacity);
/* 41 */     add(new JLabel("Mbps"));
/* 42 */     add(new JLabel("   Propagation Delay"));
/* 43 */     add(this.propagationDelay);
/* 44 */     add(new JLabel("ms"));
/* 45 */     add(new JLabel("   Queue Size"));
/* 46 */     add(this.queueSize);
/*    */   }
/*    */ }


/* Location:              C:\Users\Liudongdong\Desktop\NSG2.1.jar!\nsg\panels\LinkPanel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */