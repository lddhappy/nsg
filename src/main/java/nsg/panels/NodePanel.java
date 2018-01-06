/*    */ package nsg.panels;
/*    */ 
/*    */ import java.awt.FlowLayout;
/*    */ import java.awt.event.ItemEvent;
/*    */ import java.awt.event.ItemListener;
/*    */ import java.io.PrintStream;
/*    */ import javax.swing.JComboBox;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JTextField;
/*    */ import nsg.NSGParameters;
/*    */ import nsg.SceneVirtualizer;
/*    */ import nsg.component.Node;
/*    */ 
/*    */ public class NodePanel
/*    */   extends JPanel
/*    */ {
/*    */   static final long serialVersionUID = 0L;
/* 19 */   public JComboBox type = new JComboBox(new String[] { "Single", "Vertical chain", "Horizontal chain", "Grid", "Random" });
/* 20 */   JLabel l1 = new JLabel("   Number of nodes");
/* 21 */   public JTextField vNodes = new JTextField("5", 4);
/* 22 */   JLabel l2 = new JLabel(" x ");
/* 23 */   public JTextField hNodes = new JTextField("5", 4);
/* 24 */   JLabel l3 = new JLabel("   Vertical distance");
/* 25 */   public JTextField vDistance = new JTextField("200", 4);
/* 26 */   JLabel l4 = new JLabel("   Horizontal distance");
/* 27 */   public JTextField hDistance = new JTextField("200", 4);
/* 28 */   JLabel l5 = new JLabel("   Range");
/* 29 */   public JTextField xRange = new JTextField("1000", 4);
/* 30 */   JLabel l6 = new JLabel(" x ");
/* 31 */   public JTextField yRange = new JTextField("1000", 4);
/*    */   Node node;
/*    */   SceneVirtualizer sv;
/*    */   
/* 35 */   public void clearTarget() { this.node = null; }
/*    */   
/*    */   public void setTarget(Node node)
/*    */   {
/* 39 */     this.node = node;
/*    */   }
/*    */   
/*    */ 
/*    */   public NodePanel(SceneVirtualizer sv)
/*    */   {
/* 45 */     setLayout(new FlowLayout(0, 0, 0));
/* 46 */     this.sv = sv;
/* 47 */     ((FlowLayout)getLayout()).setAlignment(0);
/* 48 */     setBackground(NSGParameters.PANEL_COLOR);
/* 49 */     add(this.type);
/* 50 */     add(this.l1);
/* 51 */     add(this.vNodes);
/* 52 */     add(this.l2);
/* 53 */     add(this.hNodes);
/* 54 */     add(this.l3);
/* 55 */     add(this.vDistance);
/* 56 */     add(this.l4);
/* 57 */     add(this.hDistance);
/* 58 */     add(this.l5);
/* 59 */     add(this.xRange);
/* 60 */     add(this.l6);
/* 61 */     add(this.yRange);
/* 62 */     this.l1.setVisible(false);
/* 63 */     this.vNodes.setVisible(false);
/* 64 */     this.l2.setVisible(false);
/* 65 */     this.hNodes.setVisible(false);
/* 66 */     this.l3.setVisible(false);
/* 67 */     this.vDistance.setVisible(false);
/* 68 */     this.l4.setVisible(false);
/* 69 */     this.hDistance.setVisible(false);
/* 70 */     this.l5.setVisible(false);
/* 71 */     this.xRange.setVisible(false);
/* 72 */     this.l6.setVisible(false);
/* 73 */     this.yRange.setVisible(false);
/* 74 */     this.type.addItemListener(new ItemListener() {
/*    */       public void itemStateChanged(ItemEvent e) {
/* 76 */         NodePanel.this.l1.setVisible(false);
/* 77 */         NodePanel.this.vNodes.setVisible(false);
/* 78 */         NodePanel.this.l2.setVisible(false);
/* 79 */         NodePanel.this.hNodes.setVisible(false);
/* 80 */         NodePanel.this.l3.setVisible(false);
/* 81 */         NodePanel.this.vDistance.setVisible(false);
/* 82 */         NodePanel.this.l4.setVisible(false);
/* 83 */         NodePanel.this.hDistance.setVisible(false);
/* 84 */         NodePanel.this.l5.setVisible(false);
/* 85 */         NodePanel.this.xRange.setVisible(false);
/* 86 */         NodePanel.this.l6.setVisible(false);
/* 87 */         NodePanel.this.yRange.setVisible(false);
/* 88 */         switch (NodePanel.this.type.getSelectedIndex())
/*    */         {
/*    */         case 0: 
/*    */           break;
/*    */         
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         case 1: 
/* :0 */           NodePanel.this.l1.setVisible(true);
/* :1 */           NodePanel.this.vNodes.setVisible(true);
/*    */           
/*    */ 
/* :4 */           NodePanel.this.l3.setVisible(true);
/* :5 */           NodePanel.this.vDistance.setVisible(true);
/*    */           
/*    */ 
/* :8 */           break;
/*    */         case 2: 
/* ;0 */           NodePanel.this.l1.setVisible(true);
/*    */           
/*    */ 
/* ;3 */           NodePanel.this.hNodes.setVisible(true);
/*    */           
/*    */ 
/* ;6 */           NodePanel.this.l4.setVisible(true);
/* ;7 */           NodePanel.this.hDistance.setVisible(true);
/* ;8 */           break;
/*    */         case 3: 
/* <0 */           NodePanel.this.l1.setVisible(true);
/* <1 */           NodePanel.this.vNodes.setVisible(true);
/* <2 */           NodePanel.this.l2.setVisible(true);
/* <3 */           NodePanel.this.hNodes.setVisible(true);
/* <4 */           NodePanel.this.l3.setVisible(true);
/* <5 */           NodePanel.this.vDistance.setVisible(true);
/* <6 */           NodePanel.this.l4.setVisible(true);
/* <7 */           NodePanel.this.hDistance.setVisible(true);
/* <8 */           break;
/*    */         case 4: 
/* =0 */           NodePanel.this.l1.setVisible(true);
/* =1 */           NodePanel.this.vNodes.setVisible(true);
/*    */           
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* =8 */           NodePanel.this.l5.setVisible(true);
/* =9 */           NodePanel.this.xRange.setVisible(true);
/* >0 */           NodePanel.this.l6.setVisible(true);
/* >1 */           NodePanel.this.yRange.setVisible(true);
/* >2 */           break;
/*    */         default: 
/* >4 */           System.out.println("NodePanel switch error");
/* >5 */           return;
/*    */         }
/*    */       }
/*    */     });
/*    */   }
/*    */ }


/* Location:              C:\Users\Liudongdong\Desktop\NSG2.1.jar!\nsg\panels\NodePanel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */