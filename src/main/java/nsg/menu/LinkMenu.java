/*    */ package nsg.menu;
/*    */ 
/*    */ import java.awt.Container;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.FlowLayout;
/*    */ import java.awt.GridLayout;
/*    */ import java.awt.Toolkit;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JComboBox;
/*    */ import javax.swing.JDialog;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JTextField;
/*    */ import nsg.NSGParameters;
/*    */ import nsg.component.Link;
/*    */ 
/*    */ public class LinkMenu
/*    */   extends JDialog
/*    */   implements NSGParameters
/*    */ {
/*    */   static final long serialVersionUID = 0L;
/* 25 */   JButton done = new JButton("Done");
/*    */   
/*    */   Link target;
/* 28 */   public JComboBox queueType = new JComboBox(new String[] { "DropTail", "RED", "FQ", "DRR", "SFQ", "CBQ" });
/* 29 */   public JComboBox linkType = new JComboBox(new String[] { "duplex-link", "simplex-link" });
/* 30 */   public JTextField capacity = new JTextField("100");
/* 31 */   public JTextField propagationDelay = new JTextField("10");
/* 32 */   public JTextField queueSize = new JTextField("50");
/*    */   
/*    */   public LinkMenu(JFrame p) {
/* 35 */     super(p, true);
/* 36 */     setTitle("Link parameters setup");
/*    */     
/* 38 */     int w = Toolkit.getDefaultToolkit().getScreenSize().width;
/* 39 */     int h = Toolkit.getDefaultToolkit().getScreenSize().height;
/* 40 */     setBounds(w / 2 - 250, h / 2 - 300, 300, 250);
/*    */     
/* 42 */     JPanel panel = new JPanel();
/* 43 */     panel.setLayout(new FlowLayout());
/* 44 */     JPanel innerPanel = new JPanel();
/* 45 */     innerPanel.setLayout(new GridLayout(5, 2));
/* 46 */     innerPanel.add(new JLabel("Queue type"));innerPanel.add(this.queueType);
/* 47 */     innerPanel.add(new JLabel("Link type"));innerPanel.add(this.linkType);
/* 48 */     innerPanel.add(new JLabel("Capacity"));innerPanel.add(this.capacity);
/* 49 */     innerPanel.add(new JLabel("Propagation delay"));innerPanel.add(this.propagationDelay);
/* 50 */     innerPanel.add(new JLabel("Queue size"));innerPanel.add(this.queueSize);
/* 51 */     panel.add(innerPanel);
/* 52 */     getContentPane().add(panel, "Center");
/* 53 */     panel = new JPanel();
/* 54 */     panel.add(this.done);
/* 55 */     getContentPane().add(panel, "South");
/*    */     
/* 57 */     this.done.addActionListener(new ActionListener()
/*    */     {
/*    */ 
/*    */ 
/*    */       public void actionPerformed(ActionEvent e)
/*    */       {
/*    */ 
/* 64 */         LinkMenu.this.target.capacity = Float.parseFloat(LinkMenu.this.capacity.getText());
/* 65 */         LinkMenu.this.target.linkType = LinkMenu.this.linkType.getSelectedIndex();
/* 66 */         LinkMenu.this.target.propagationDelay = Integer.parseInt(LinkMenu.this.propagationDelay.getText());
/* 67 */         LinkMenu.this.target.queueSize = Integer.parseInt(LinkMenu.this.queueSize.getText());
/* 68 */         LinkMenu.this.target.queueType = LinkMenu.this.queueType.getSelectedIndex();
/* 69 */         LinkMenu.this.target = null;
/* 70 */         LinkMenu.this.setVisible(false);
/*    */       }
/*    */     });
/*    */   }
/*    */   
/* 75 */   public void setTarget(Link target) { this.target = target;
/* 76 */     this.queueType.setSelectedIndex(target.queueType);
/* 77 */     this.linkType.setSelectedIndex(target.linkType);
/* 78 */     this.capacity.setText(String.valueOf(target.capacity));
/* 79 */     this.propagationDelay.setText(String.valueOf(target.propagationDelay));
/* 80 */     this.queueSize.setText(String.valueOf(target.queueSize));
/*    */   }
/*    */ }


/* Location:              C:\Users\Liudongdong\Desktop\NSG2.1.jar!\nsg\menu\LinkMenu.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */