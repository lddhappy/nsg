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
/*    */ 
/*    */ public class AppPanel
/*    */   extends JPanel
/*    */   implements NSGParameters
/*    */ {
/*    */   static final long serialVersionUID = 0L;
/* 19 */   JLabel l1 = new JLabel("Aplication type");
/* 20 */   public JComboBox appType = new JComboBox(new String[] { "FTP", "CBR" });
/* 21 */   JLabel l2 = new JLabel("   Start time");
/* 22 */   public JTextField startTime = new JTextField("1", 4);
/* 23 */   JLabel l3 = new JLabel("   Stop time");
/* 24 */   public JTextField stopTime = new JTextField("2", 4);
/*    */   
/*    */ 
/* 27 */   JLabel l4 = new JLabel("   Packet size");
/* 28 */   public JTextField packetSize = new JTextField("1000", 4);
/* 29 */   JLabel l5 = new JLabel("   Rate");
/* 30 */   public JTextField rate = new JTextField("1", 4);
/* 31 */   JLabel l6 = new JLabel("   Interval");
/* 32 */   public JTextField interval = new JTextField("0.005", 4);
/*    */   
/*    */ 
/*    */ 
/*    */ 
/* 37 */   JLabel l8 = new JLabel("   Burst time");
/* 38 */   public JTextField burstTime = new JTextField("200", 4);
/* 39 */   JLabel l9 = new JLabel("   Idle time");
/* 40 */   public JTextField idleTime = new JTextField("400", 4);
/* 41 */   JLabel l10 = new JLabel("   Shape");
/* 42 */   public JTextField shape = new JTextField("1.5", 4);
/*    */   SceneVirtualizer sv;
/*    */   
/*    */   public AppPanel(SceneVirtualizer f)
/*    */   {
/* 47 */     setLayout(new FlowLayout(0, 0, 0));
/* 48 */     this.sv = f;
/* 49 */     ((FlowLayout)getLayout()).setAlignment(0);
/* 50 */     setBackground(NSGParameters.PANEL_COLOR);
/* 51 */     add(this.l1);
/* 52 */     add(this.appType);
/* 53 */     add(this.l2);
/* 54 */     add(this.startTime);
/* 55 */     add(this.l3);
/* 56 */     add(this.stopTime);
/* 57 */     add(this.l4);
/* 58 */     add(this.packetSize);
/* 59 */     add(this.l5);
/* 60 */     add(this.rate);
/* 61 */     add(this.l6);
/* 62 */     add(this.interval);
/*    */     
/*    */ 
/* 65 */     add(this.l8);
/* 66 */     add(this.burstTime);
/* 67 */     add(this.l9);
/* 68 */     add(this.idleTime);
/* 69 */     add(this.l10);
/* 70 */     add(this.shape);
/* 71 */     this.l4.setVisible(false);
/* 72 */     this.packetSize.setVisible(false);
/* 73 */     this.l5.setVisible(false);
/* 74 */     this.rate.setVisible(false);
/* 75 */     this.l6.setVisible(false);
/* 76 */     this.interval.setVisible(false);
/*    */     
/*    */ 
/* 79 */     this.l8.setVisible(false);
/* 80 */     this.burstTime.setVisible(false);
/* 81 */     this.l9.setVisible(false);
/* 82 */     this.idleTime.setVisible(false);
/* 83 */     this.l10.setVisible(false);
/* 84 */     this.shape.setVisible(false);
/* 85 */     this.appType.addItemListener(new ItemListener() {
/*    */       public void itemStateChanged(ItemEvent e) {
/* 87 */         AppPanel.this.l3.setVisible(false);
/* 88 */         AppPanel.this.stopTime.setVisible(false);
/* 89 */         AppPanel.this.l4.setVisible(false);
/* 90 */         AppPanel.this.packetSize.setVisible(false);
/* 91 */         AppPanel.this.l5.setVisible(false);
/* 92 */         AppPanel.this.rate.setVisible(false);
/* 93 */         AppPanel.this.l6.setVisible(false);
/* 94 */         AppPanel.this.interval.setVisible(false);
/*    */         
/*    */ 
/* 97 */         AppPanel.this.l8.setVisible(false);
/* 98 */         AppPanel.this.burstTime.setVisible(false);
/* 99 */         AppPanel.this.l9.setVisible(false);
/* :0 */         AppPanel.this.idleTime.setVisible(false);
/* :1 */         AppPanel.this.l10.setVisible(false);
/* :2 */         AppPanel.this.shape.setVisible(false);
/* :3 */         switch (AppPanel.this.appType.getSelectedIndex()) {
/*    */         case 0: 
/* :5 */           AppPanel.this.l3.setVisible(true);
/* :6 */           AppPanel.this.stopTime.setVisible(true);
/*    */           
/* :8 */           break;
/*    */         case 1: 
/* ;0 */           AppPanel.this.l3.setVisible(true);
/* ;1 */           AppPanel.this.stopTime.setVisible(true);
/* ;2 */           AppPanel.this.l4.setVisible(true);
/* ;3 */           AppPanel.this.packetSize.setVisible(true);
/* ;4 */           AppPanel.this.l5.setVisible(true);
/* ;5 */           AppPanel.this.rate.setVisible(true);
/* ;6 */           AppPanel.this.l6.setVisible(true);
/* ;7 */           AppPanel.this.interval.setVisible(true);
/*    */           
/*    */ 
/* <0 */           break;
/*    */         case 2: 
/*    */           break;
/*    */         
/*    */         case 3: 
/* <5 */           AppPanel.this.l3.setVisible(true);
/* <6 */           AppPanel.this.stopTime.setVisible(true);
/* <7 */           AppPanel.this.l8.setVisible(true);
/* <8 */           AppPanel.this.burstTime.setVisible(true);
/* <9 */           AppPanel.this.l9.setVisible(true);
/* =0 */           AppPanel.this.idleTime.setVisible(true);
/* =1 */           AppPanel.this.l10.setVisible(true);
/* =2 */           AppPanel.this.shape.setVisible(true);
/* =3 */           break;
/*    */         case 4: 
/* =5 */           AppPanel.this.l3.setVisible(true);
/* =6 */           AppPanel.this.stopTime.setVisible(true);
/* =7 */           break;
/*    */         default: 
/* =9 */           System.out.println("AppPanel switch error");
/* >0 */           return;
/*    */         }
/*    */       }
/*    */     });
/*    */   }
/*    */ }


/* Location:              C:\Users\Liudongdong\Desktop\NSG2.1.jar!\nsg\panels\AppPanel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */