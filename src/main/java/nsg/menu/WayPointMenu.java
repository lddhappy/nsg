/*     */ package nsg.menu;
/*     */ 
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JPopupMenu;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.table.DefaultTableModel;
/*     */ import nsg.NSGParameters;
/*     */ import nsg.component.Node;
/*     */ import nsg.component.Waypoint;
/*     */ 
/*     */ public class WayPointMenu
/*     */   extends JDialog
/*     */   implements NSGParameters
/*     */ {
/*     */   static final long serialVersionUID = 0L;
/*  33 */   JButton done = new JButton("Done");
/*  34 */   JButton add = new JButton("Add");
/*     */   
/*     */   Node target;
/*  37 */   public JTextField startTime = new JTextField("", 4);
/*  38 */   public JTextField destX = new JTextField("", 4);
/*  39 */   public JTextField destY = new JTextField("", 4);
/*  40 */   public JTextField speed = new JTextField("", 4);
/*     */   
/*     */   public DefaultTableModel model;
/*  43 */   public JTable table = new JTable();
/*     */   
/*  45 */   TablePopMenu tablePopMenu = new TablePopMenu();
/*     */   
/*  47 */   public WayPointMenu(JFrame p) { super(p, true);
/*  48 */     int w = Toolkit.getDefaultToolkit().getScreenSize().width;
/*  49 */     int h = Toolkit.getDefaultToolkit().getScreenSize().height;
/*  50 */     setBounds(w / 2 - 250, h / 2 - 300, 550, 250);
/*     */     
/*  52 */     JPanel panel = new JPanel();
/*  53 */     panel.setLayout(new FlowLayout());
/*  54 */     panel.add(new JLabel("Start time:"));panel.add(this.startTime);
/*  55 */     panel.add(new JLabel("  Destination:("));panel.add(this.destX);
/*  56 */     panel.add(new JLabel(", "));panel.add(this.destY);
/*  57 */     panel.add(new JLabel(")  Speed:"));panel.add(this.speed);
/*  58 */     panel.add(this.add);
/*  59 */     getContentPane().add(panel, "North");
/*  60 */     getContentPane().add(new JScrollPane(this.table), "Center");
/*     */     
/*  62 */     panel = new JPanel();
/*  63 */     panel.add(this.done);
/*  64 */     getContentPane().add(panel, "South");
/*  65 */     this.add.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/*  67 */         String[] data = new String[4];
/*  68 */         data[0] = WayPointMenu.this.startTime.getText();
/*  69 */         data[1] = WayPointMenu.this.destX.getText();
/*  70 */         data[2] = WayPointMenu.this.destY.getText();
/*  71 */         data[3] = WayPointMenu.this.speed.getText();
/*  72 */         WayPointMenu.this.model.addRow(data);
/*     */       }
/*     */       
/*  75 */     });
/*  76 */     this.done.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/*  78 */         if (WayPointMenu.this.model.getRowCount() != 0) {
/*  79 */           WayPointMenu.this.target.waypoints = new ArrayList();
/*     */           
/*  81 */           for (int a = 0; a < WayPointMenu.this.model.getRowCount(); a++) {
/*  82 */             Waypoint point = new Waypoint();
/*  83 */             point.startTime = ((String)WayPointMenu.this.model.getValueAt(a, 0));
/*  84 */             point.destX = ((String)WayPointMenu.this.model.getValueAt(a, 1));
/*  85 */             point.destY = ((String)WayPointMenu.this.model.getValueAt(a, 2));
/*  86 */             point.speed = ((String)WayPointMenu.this.model.getValueAt(a, 3));
/*  87 */             WayPointMenu.this.target.waypoints.add(point);
/*     */           }
/*     */         } else {
/*  90 */           WayPointMenu.this.target.waypoints = null;
/*     */         }
/*  92 */         WayPointMenu.this.target = null;
/*  93 */         WayPointMenu.this.setVisible(false);
/*     */       }
/*  95 */     });
/*  96 */     this.table.addMouseListener(new MouseAdapter() {
/*     */       public void mousePressed(MouseEvent e) {
/*  98 */         if (SwingUtilities.isRightMouseButton(e)) {
/*  99 */           WayPointMenu.this.tablePopMenu.show(WayPointMenu.this.table, e.getX(), e.getY());
/*     */         }
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   public void setTarget(Node target) {
/* 106 */     this.startTime.setText("");
/* 107 */     this.destX.setText("");
/* 108 */     this.destY.setText("");
/* 109 */     this.speed.setText("");
/* 110 */     this.target = target;
/* 111 */     setTitle("n" + target.id + "'s waypoint setup");
/* 112 */     this.model = new DefaultTableModel();
/* 113 */     this.model.addColumn("Start time");
/* 114 */     this.model.addColumn("X");
/* 115 */     this.model.addColumn("Y");
/* 116 */     this.model.addColumn("Speed");
/* 117 */     this.table.setModel(this.model);
/* 118 */     if (target.waypoints != null) {
/* 119 */       Object[] points = target.waypoints.toArray();
/* 120 */       String[] data = new String[4];
/* 121 */       for (int a = 0; a < points.length; a++) {
/* 122 */         data[0] = ((Waypoint)points[a]).startTime;
/* 123 */         data[1] = ((Waypoint)points[a]).destX;
/* 124 */         data[2] = ((Waypoint)points[a]).destY;
/* 125 */         data[3] = ((Waypoint)points[a]).speed;
/* 126 */         this.model.addRow(data);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public class TablePopMenu extends JPopupMenu
/*     */   {
/*     */     public TablePopMenu() {
/* 134 */       JMenuItem item = new JMenuItem("Delete");
/* 135 */       item.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 137 */           if (WayPointMenu.this.table.getSelectedRow() >= 0) {
/* 138 */             WayPointMenu.this.model.removeRow(WayPointMenu.this.table.getSelectedRow());
/*     */           }
/*     */         }
/* 141 */       });
/* 142 */       add(item);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Liudongdong\Desktop\NSG2.1.jar!\nsg\menu\WayPointMenu.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */