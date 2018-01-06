/*     */ package nsg;
/*     */ 
/*     */ import java.awt.Container;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.beans.PropertyVetoException;
/*     */ import javax.swing.JDesktopPane;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JInternalFrame;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuBar;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.KeyStroke;
/*     */ import nsg.analysis.PacketCounterFrame;
/*     */ import nsg.p2p.ItmTclFrame;
/*     */ import nsg.p2p.P2PAnalysisFrame;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NSG2
/*     */   extends JFrame
/*     */   implements NSGParameters
/*     */ {
/*     */   static final long serialVersionUID = 0L;
/*     */   public static final int WIRED_FRAME = 1;
/*     */   public static final int WIRELESS_FRAME = 2;
/*     */   public static final int PACKET_COUNTER_FRAME = 3;
/*     */   public static final int ITM_P2P_FRAME = 4;
/*     */   public static final int P2P_ANALYSIS_FRAME = 5;
/*  35 */   private static JDesktopPane jdp = new JDesktopPane();
/*     */   
/*  37 */   public static JDesktopPane getMainDesktopPane() { return jdp; }
/*     */   
/*  39 */   JMenuBar menubar = new JMenuBar();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static NSG2 mainFrame;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public NSG2()
/*     */   {
/*  57 */     super("NSG2_TCLGenerator Modified by Alvin Yang zyzprime@gmail.com");
/*  58 */     mainFrame = this;
/*  59 */     jdp.setDragMode(1);
/*  60 */     jdp.setBackground(NSGParameters.DSEKTOP_BACKGROUND_COLOR);
/*  61 */     getContentPane().add(jdp, "Center");
/*  62 */     createMenuBar();
/*     */   }
/*     */   
/*     */   private void createMenuBar() {
/*  66 */     setJMenuBar(this.menubar);
/*     */     
/*     */ 
/*     */ 
/*  70 */     JMenu menu = new JMenu("System");
/*  71 */     menu.setMnemonic('S');
/*  72 */     JMenuItem item = new JMenuItem("Exit");
/*  73 */     item.setMnemonic('X');
/*  74 */     item.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/*  76 */         System.exit(0);
/*     */       }
/*  78 */     });
/*  79 */     menu.add(item);
/*  80 */     this.menubar.add(menu);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  85 */     menu = new JMenu("Scenario");
/*  86 */     menu.setMnemonic('C');
/*  87 */     item = new JMenuItem("New wired scenario");
/*  88 */     item.setAccelerator(KeyStroke.getKeyStroke(87, 2));
/*  89 */     item.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/*  91 */         NSG2.this.createInternalFrame(1);
/*     */       }
/*  93 */     });
/*  94 */     menu.add(item);
/*  95 */     item = new JMenuItem("New wireless scenario");
/*  96 */     item.setAccelerator(KeyStroke.getKeyStroke(76, 2));
/*  97 */     item.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/*  99 */         NSG2.this.createInternalFrame(2);
/*     */       }
/* 101 */     });
/* 102 */     menu.add(item);
/* 103 */     this.menubar.add(menu);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 146 */     menu = new JMenu("Window");
/* 147 */     menu.setMnemonic('W');
/* 148 */     item = new JMenuItem("Iconify all");
/* 149 */     item.setAccelerator(KeyStroke.getKeyStroke(73, 2));
/* 150 */     item.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 152 */         JInternalFrame[] all = NSG2.jdp.getAllFrames();
/* 153 */         for (int i = 0; i < all.length; i++) {
/*     */           try {
/* 155 */             all[i].setIcon(true);
/*     */           } catch (PropertyVetoException localPropertyVetoException) {}
/*     */         }
/*     */       }
/* 159 */     });
/* 160 */     menu.add(item);
/*     */     
/* 162 */     item = new JMenuItem("Deiconify all");
/* 163 */     item.setAccelerator(KeyStroke.getKeyStroke(68, 2));
/* 164 */     item.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 166 */         JInternalFrame[] all = NSG2.jdp.getAllFrames();
/* 167 */         for (int i = 0; i < all.length; i++) {
/*     */           try {
/* 169 */             all[i].setIcon(false);
/*     */           } catch (PropertyVetoException localPropertyVetoException) {}
/*     */         }
/*     */       }
/* 173 */     });
/* 174 */     menu.add(item);
/*     */     
/* 176 */     item = new JMenuItem("Close all");
/* 177 */     item.setAccelerator(KeyStroke.getKeyStroke(67, 2));
/* 178 */     item.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 180 */         JInternalFrame[] all = NSG2.jdp.getAllFrames();
/* 181 */         for (int i = 0; i < all.length; i++) {
/*     */           try {
/* 183 */             all[i].setClosed(true);
/* 184 */             all[i].dispose();
/*     */           } catch (PropertyVetoException localPropertyVetoException) {}
/*     */         }
/*     */       }
/* 188 */     });
/* 189 */     menu.add(item);
/*     */     
/* 191 */     this.menubar.add(menu);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 196 */     menu = new JMenu("About");
/* 197 */     menu.setMnemonic('A');
/* 198 */     item = new JMenuItem("Version");
/* 199 */     item.setAccelerator(KeyStroke.getKeyStroke(86, 2));
/* 200 */     item.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 202 */         JOptionPane.showMessageDialog(NSG2.mainFrame, " Version:2.1 (Dec. 25, 2017)\n Author: Alvin Yang \nhttps://sites.google.com/view/zyzprime/", "About NSG2", 1);
/*     */       }
/* 204 */     });
/* 205 */     menu.add(item);
/* 206 */     this.menubar.add(menu);
/*     */   }
/*     */   
/*     */   public void createInternalFrame(int type) {
/* 210 */     JInternalFrame iframe = new JInternalFrame();
/* 211 */     switch (type) {
/*     */     case 1: 
/* 213 */       iframe = new SceneManager(this, 1);
/* 214 */       iframe.setBounds(0, 0, jdp.getWidth(), jdp.getHeight());
/* 215 */       break;
/*     */     case 2: 
/* 217 */       iframe = new SceneManager(this, 2);
/* 218 */       iframe.setBounds(0, 0, jdp.getWidth(), jdp.getHeight());
/* 219 */       break;
/*     */     case 3: 
/* 221 */       iframe = new PacketCounterFrame();
/* 222 */       iframe.setBounds(jdp.getWidth() / 4, 0, jdp.getWidth() / 2, jdp.getHeight());
/* 223 */       break;
/*     */     case 4: 
/* 225 */       iframe = new ItmTclFrame();
/* 226 */       iframe.setBounds(0, 0, jdp.getWidth() / 2, jdp.getHeight());
/* 227 */       break;
/*     */     case 5: 
/* 229 */       iframe = new P2PAnalysisFrame();
/* 230 */       iframe.setBounds(jdp.getWidth() / 2, 0, jdp.getWidth() / 2, jdp.getHeight());
/* 231 */       break;
/*     */     default: 
/* 233 */       iframe = new JInternalFrame();
/* 234 */       iframe.setBounds(640, 0, 720, 512);
/*     */     }
/* 236 */     iframe.setDefaultCloseOperation(2);
/* 237 */     iframe.setVisible(true);
/*     */     
/* 239 */     jdp.add(iframe);
/*     */     try
/*     */     {
/* 242 */       iframe.setSelected(true);
/*     */     } catch (PropertyVetoException e) {
/* 244 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void createTCLManager(SceneManager sm)
/*     */   {
/* 286 */     TCLManager f = new TCLManager(sm);
/*     */     
/* 288 */     f.setBounds(0, 0, jdp.getWidth() / 2, jdp.getHeight());
/* 289 */     f.setVisible(true);
/* 290 */     jdp.add(f);
/*     */     try {
/* 292 */       f.setSelected(true);
/*     */     } catch (PropertyVetoException localPropertyVetoException) {}
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 297 */     NSG2 f = new NSG2();
/*     */     
/* 299 */     f.setDefaultCloseOperation(3);
/* 300 */     f.setExtendedState(6);
/* 301 */     f.setVisible(true);
/*     */   }
/*     */ }


/* Location:              C:\Users\Liudongdong\Desktop\NSG2.1.jar!\nsg\NSG2.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */