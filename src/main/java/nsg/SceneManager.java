/*     */ package nsg;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.ItemListener;
/*     */ import java.io.PrintStream;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBoxMenuItem;
/*     */ import javax.swing.JInternalFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuBar;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JSlider;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.JToolBar;
/*     */ import javax.swing.KeyStroke;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
/*     */ import nsg.menu.AgentMenu;
/*     */ import nsg.menu.AppMenu;
/*     */ import nsg.menu.LinkMenu;
/*     */ import nsg.menu.WayPointMenu;
/*     */ 
/*     */ public class SceneManager
/*     */   extends JInternalFrame implements NSGParameters
/*     */ {
/*     */   static final long serialVersionUID = 0L;
/*  37 */   public boolean drawGrid = true;
/*  38 */   public boolean drawNode = true;
/*  39 */   public boolean drawLink = true;
/*  40 */   public boolean drawLinkDetail = false;
/*  41 */   public boolean drawAgent = true;
/*  42 */   public boolean drawAgentDetail = false;
/*  43 */   public boolean drawApp = true;
/*     */   
/*  45 */   public boolean drawIR = false;
/*  46 */   public boolean drawSR = true;
/*  47 */   public boolean drawNodeLocation = true;
/*  48 */   public boolean drawConnectivity = true;
/*     */   
/*  50 */   public JButton b1 = new JButton("Hand");
/*  51 */   public JButton b2 = new JButton("Node");
/*  52 */   public JButton b3 = new JButton("Link");
/*  53 */   public JButton b4 = new JButton("Agent");
/*  54 */   public JButton b5 = new JButton("Application");
/*  55 */   public JButton b6 = new JButton("Parameters");
/*  56 */   public JButton b7 = new JButton("TCL");
/*     */   
/*  58 */   JMenuBar menubar = new JMenuBar();
/*  59 */   public float centerX = -1.0F;
/*  60 */   public float centerY = -1.0F;
/*     */   
/*     */   public int sceneMode;
/*  63 */   JToolBar toolbar = new JToolBar();
/*     */   NSG2 nsg;
/*  65 */   JPanel center = new JPanel(new BorderLayout());
/*  66 */   public JLabel status = new JLabel();
/*  67 */   JSlider slider = new JSlider(8, 500, 100);
/*     */   
/*     */   ParameterDialog parameters;
/*     */   
/*     */   public DataMaintainer dm;
/*     */   public SceneVirtualizer sv;
/*  73 */   public LinkMenu linkmenu = new LinkMenu(this.nsg);
/*  74 */   public AgentMenu agentmenu = new AgentMenu(this.nsg);
/*  75 */   public AppMenu appmenu = new AppMenu(this.nsg);
/*  76 */   public WayPointMenu waypointMenu = new WayPointMenu(this.nsg);
/*     */   
/*  78 */   public int goEast = 0;
/*  79 */   public int goWest = 0;
/*  80 */   public int goNorth = 0;
/*  81 */   public int goSouth = 0;
/*     */   
/*  83 */   JTextField trBox = new JTextField("250", 5);
/*  84 */   JTextField irBox = new JTextField("550", 5);
/*  85 */   JButton setRange = new JButton("Set");
/*     */   public Thread thread;
/*     */   
/*     */   public class MoveThread extends Thread {
/*     */     public MoveThread() {}
/*     */     
/*     */     public void run() {
/*  92 */       try { Thread.sleep(800L);
/*     */       } catch (Exception e) {
/*  94 */         e.printStackTrace();
/*     */       }
/*  96 */       while ((SceneManager.this.goEast != 0) || (SceneManager.this.goWest != 0) || (SceneManager.this.goNorth != 0) || (SceneManager.this.goSouth != 0)) {
/*  97 */         if (SceneManager.this.goEast != 0) {
/*  98 */           SceneManager.this.sv.shiftX -= (int)(SceneManager.this.goEast / SceneManager.this.sv.scale);
/*     */         }
/* 100 */         if (SceneManager.this.goWest != 0) {
/* 101 */           SceneManager.this.sv.shiftX += (int)(SceneManager.this.goWest / SceneManager.this.sv.scale);
/*     */         }
/* 103 */         if (SceneManager.this.goSouth != 0) {
/* 104 */           SceneManager.this.sv.shiftY -= (int)(SceneManager.this.goSouth / SceneManager.this.sv.scale);
/*     */         }
/* 106 */         if (SceneManager.this.goNorth != 0) {
/* 107 */           SceneManager.this.sv.shiftY += (int)(SceneManager.this.goNorth / SceneManager.this.sv.scale);
/*     */         }
/* 109 */         SceneManager.this.centerX = (SceneManager.this.sv.getWidth() / 2 / SceneManager.this.sv.scale - SceneManager.this.sv.shiftX);
/* 110 */         SceneManager.this.centerY = (SceneManager.this.sv.getHeight() / 2 / SceneManager.this.sv.scale - SceneManager.this.sv.shiftY);
/* 111 */         SwingUtilities.invokeLater(new Runnable() {
/*     */           public void run() {
/* 113 */             SceneManager.this.repaint();
/*     */           }
/*     */         });
/*     */         try {
/* 117 */           Thread.sleep(100L);
/*     */         } catch (Exception e) {
/* 119 */           e.printStackTrace();
/*     */         }
/*     */       }
/* 122 */       System.out.println("Thread stop");
/* 123 */       SceneManager.this.thread = null;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean scroll(int x, int y) {
/* 128 */     return false;
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
/*     */   public SceneManager(NSG2 f, int mode)
/*     */   {
/* 164 */     super("", true, true, true, true);
/* 165 */     this.nsg = f;
/* 166 */     this.sceneMode = mode;
/* 167 */     this.dm = new DataMaintainer();
/* 168 */     this.sv = new SceneVirtualizer(this);
/*     */     
/*     */ 
/*     */ 
/* 172 */     if (mode == 1) {
/* 173 */       setTitle("Wired scenario");
/* 174 */       this.parameters = new ParameterDialog(this.nsg, 1);
/*     */     } else {
/* 176 */       setTitle("Wireless scenario");
/* 177 */       this.parameters = new ParameterDialog(this.nsg, 2);
/*     */     }
/*     */     
/* 180 */     createMenuBar();
/* 181 */     createToolBar();
/*     */     
/*     */ 
/*     */ 
/* 185 */     Box p = Box.createHorizontalBox();
/*     */     
/* 187 */     this.status.setPreferredSize(new Dimension(200, 20));
/* 188 */     this.status.setMaximumSize(new Dimension(200, 20));
/* 189 */     this.status.setMinimumSize(new Dimension(200, 20));
/*     */     
/* 191 */     this.slider.setPreferredSize(new Dimension(200, 20));
/* 192 */     this.slider.setMaximumSize(new Dimension(200, 20));
/* 193 */     this.slider.setMinimumSize(new Dimension(200, 20));
/* 194 */     this.slider.addChangeListener(new ChangeListener() {
/*     */       public void stateChanged(ChangeEvent e) {
/* 196 */         if (SceneManager.this.centerX == -1.0F) {
/* 197 */           SceneManager.this.centerX = (SceneManager.this.sv.getWidth() / 2 / SceneManager.this.sv.scale - SceneManager.this.sv.shiftX);
/* 198 */           SceneManager.this.centerY = (SceneManager.this.sv.getHeight() / 2 / SceneManager.this.sv.scale - SceneManager.this.sv.shiftY);
/*     */         }
/* 200 */         SceneManager.this.sv.scale = (SceneManager.this.slider.getValue() / 100.0F);
/* 201 */         SceneManager.this.sv.shiftX = ((int)(SceneManager.this.sv.getWidth() / 2 / SceneManager.this.sv.scale - SceneManager.this.centerX));
/* 202 */         SceneManager.this.sv.shiftY = ((int)(SceneManager.this.sv.getHeight() / 2 / SceneManager.this.sv.scale - SceneManager.this.centerY));
/* 203 */         SceneManager.this.sv.repaint();
/*     */       }
/*     */     });
/* 206 */     if (mode == 2) {
/* 207 */       p.add(this.status);
/* 208 */       p.add(new JLabel("Transmission range"));
/* 209 */       p.add(this.trBox);
/* 210 */       p.add(new JLabel("Interference range"));
/* 211 */       p.add(this.irBox);
/* 212 */       p.add(this.setRange);
/* 213 */       this.setRange.addActionListener(new ActionListener() {
/* 214 */         boolean showMessage = true;
/*     */         
/* 216 */         public void actionPerformed(ActionEvent e) { if (this.showMessage) {
/* 217 */             this.showMessage = false;
/* 218 */             JOptionPane.showInternalMessageDialog(SceneManager.this.sv, "Note that the transmission/interference range setting here only affects the display,\nnot the actual simulation parameters.\nThe actual transmission/interference ranges in NS2 simulator\nare affected by wireless channel parameters\nthat can be setup in parameter mode.\n(press \"parameters\" button and select channel tab for further information.)", "Information", 1);
/*     */           }
/* 220 */           SceneManager.this.sv.transmissionRange = Integer.valueOf(SceneManager.this.trBox.getText()).intValue();
/* 221 */           SceneManager.this.sv.interferenceRange = Integer.valueOf(SceneManager.this.irBox.getText()).intValue();
/* 222 */           SceneManager.this.sv.repaint();
/*     */         }
/*     */       });
/*     */     }
/*     */     
/* 227 */     p.setBackground(STATUS_LABEL_COLOR);
/* 228 */     p.setOpaque(true);
/* 229 */     this.slider.setOpaque(false);
/* 230 */     p.add(Box.createHorizontalGlue());
/* 231 */     p.add(new JLabel("Zoom"));
/* 232 */     p.add(this.slider);
/* 233 */     this.center.add(p, "South");
/* 234 */     this.center.add(this.sv, "Center");
/* 235 */     getContentPane().add(this.center, "Center");
/*     */   }
/*     */   
/* 238 */   public void createToolBar() { this.toolbar.setBackground(TOOLBAR_COLOR);
/* 239 */     this.toolbar.setOpaque(true);
/* 240 */     this.toolbar.setFloatable(false);
/* 241 */     getContentPane().add(this.toolbar, "North");
/*     */     
/*     */ 
/* 244 */     this.b1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 246 */         SceneManager.this.sv.switchMode(1);
/*     */       }
/* 248 */     });
/* 249 */     this.toolbar.add(this.b1);
/*     */     
/*     */ 
/*     */ 
/* 253 */     this.b2.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 255 */         SceneManager.this.sv.switchMode(2);
/*     */       }
/* 257 */     });
/* 258 */     this.toolbar.add(this.b2);
/*     */     
/* 260 */     if (this.sceneMode == 1)
/*     */     {
/*     */ 
/* 263 */       this.b3.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 265 */           SceneManager.this.sv.switchMode(5);
/*     */         }
/* 267 */       });
/* 268 */       this.toolbar.add(this.b3);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 273 */     this.b4.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 275 */         SceneManager.this.sv.switchMode(3);
/*     */       }
/* 277 */     });
/* 278 */     this.toolbar.add(this.b4);
/*     */     
/*     */ 
/*     */ 
/* 282 */     this.b5.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 284 */         SceneManager.this.sv.switchMode(4);
/*     */       }
/* 286 */     });
/* 287 */     this.toolbar.add(this.b5);
/*     */     
/*     */ 
/* 290 */     this.b6.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 292 */         SceneManager.this.parameters.setVisible(true);
/*     */       }
/* 294 */     });
/* 295 */     this.toolbar.add(this.b6);
/*     */     
/*     */ 
/*     */ 
/* 299 */     this.b7.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 301 */         SceneManager.this.nsg.createTCLManager(SceneManager.this);
/*     */       }
/* 303 */     });
/* 304 */     this.toolbar.add(this.b7);
/*     */   }
/*     */   
/* 307 */   public void createMenuBar() { setJMenuBar(this.menubar);
/*     */     
/*     */ 
/*     */ 
/* 311 */     JMenu menu = new JMenu("File");
/* 312 */     menu.setMnemonic('F');
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
/* 342 */     JMenuItem item = new JMenuItem("Close");
/* 343 */     item.setMnemonic('C');
/* 344 */     item.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 346 */         SceneManager.this.dispose();
/*     */       }
/* 348 */     });
/* 349 */     menu.add(item);
/*     */     
/* 351 */     this.menubar.add(menu);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 356 */     menu = new JMenu("Draw");
/* 357 */     menu.setMnemonic('D');
/* 358 */     JCheckBoxMenuItem checkItem = new JCheckBoxMenuItem("Draw grid");
/* 359 */     checkItem.setAccelerator(KeyStroke.getKeyStroke(112, 0));
/* 360 */     checkItem.setSelected(true);
/* 361 */     checkItem.addItemListener(new ItemListener() {
/*     */       public void itemStateChanged(ItemEvent evt) {
/* 363 */         if (evt.getStateChange() == 1) {
/* 364 */           SceneManager.this.drawGrid = true;
/*     */         } else {
/* 366 */           SceneManager.this.drawGrid = false;
/*     */         }
/* 368 */         SceneManager.this.repaint();
/*     */       }
/* 370 */     });
/* 371 */     menu.add(checkItem);
/*     */     
/* 373 */     checkItem = new JCheckBoxMenuItem("Draw node");
/* 374 */     checkItem.setAccelerator(KeyStroke.getKeyStroke(113, 0));
/* 375 */     checkItem.setSelected(true);
/* 376 */     checkItem.addItemListener(new ItemListener() {
/*     */       public void itemStateChanged(ItemEvent evt) {
/* 378 */         if (evt.getStateChange() == 1) {
/* 379 */           SceneManager.this.drawNode = true;
/*     */         } else {
/* 381 */           SceneManager.this.drawNode = false;
/*     */         }
/* 383 */         SceneManager.this.repaint();
/*     */       }
/* 385 */     });
/* 386 */     menu.add(checkItem);
/*     */     
/* 388 */     if (this.sceneMode == 1) {
/* 389 */       checkItem = new JCheckBoxMenuItem("Draw link");
/* 390 */       checkItem.setAccelerator(KeyStroke.getKeyStroke(114, 0));
/* 391 */       checkItem.setSelected(true);
/* 392 */       checkItem.addItemListener(new ItemListener() {
/*     */         public void itemStateChanged(ItemEvent evt) {
/* 394 */           if (evt.getStateChange() == 1) {
/* 395 */             SceneManager.this.drawLink = true;
/*     */           } else {
/* 397 */             SceneManager.this.drawLink = false;
/*     */           }
/* 399 */           SceneManager.this.repaint();
/*     */         }
/* 401 */       });
/* 402 */       menu.add(checkItem);
/*     */       
/* 404 */       checkItem = new JCheckBoxMenuItem("Draw link detail");
/* 405 */       checkItem.setAccelerator(KeyStroke.getKeyStroke(115, 0));
/* 406 */       checkItem.setSelected(false);
/* 407 */       checkItem.addItemListener(new ItemListener() {
/*     */         public void itemStateChanged(ItemEvent evt) {
/* 409 */           if (evt.getStateChange() == 1) {
/* 410 */             SceneManager.this.drawLinkDetail = true;
/*     */           } else {
/* 412 */             SceneManager.this.drawLinkDetail = false;
/*     */           }
/* 414 */           SceneManager.this.repaint();
/*     */         }
/* 416 */       });
/* 417 */       menu.add(checkItem);
/*     */     }
/*     */     
/* 420 */     checkItem = new JCheckBoxMenuItem("Draw agent");
/* 421 */     checkItem.setAccelerator(KeyStroke.getKeyStroke(116, 0));
/* 422 */     checkItem.setSelected(true);
/* 423 */     checkItem.addItemListener(new ItemListener() {
/*     */       public void itemStateChanged(ItemEvent evt) {
/* 425 */         if (evt.getStateChange() == 1) {
/* 426 */           SceneManager.this.drawAgent = true;
/*     */         } else {
/* 428 */           SceneManager.this.drawAgent = false;
/*     */         }
/* 430 */         SceneManager.this.repaint();
/*     */       }
/* 432 */     });
/* 433 */     menu.add(checkItem);
/*     */     
/* 435 */     checkItem = new JCheckBoxMenuItem("Draw agent detail");
/* 436 */     checkItem.setAccelerator(KeyStroke.getKeyStroke(117, 0));
/* 437 */     checkItem.setSelected(false);
/* 438 */     checkItem.addItemListener(new ItemListener() {
/*     */       public void itemStateChanged(ItemEvent evt) {
/* 440 */         if (evt.getStateChange() == 1) {
/* 441 */           SceneManager.this.drawAgentDetail = true;
/*     */         } else {
/* 443 */           SceneManager.this.drawAgentDetail = false;
/*     */         }
/* 445 */         SceneManager.this.repaint();
/*     */       }
/* 447 */     });
/* 448 */     menu.add(checkItem);
/*     */     
/* 450 */     checkItem = new JCheckBoxMenuItem("Draw application");
/* 451 */     checkItem.setAccelerator(KeyStroke.getKeyStroke(118, 0));
/* 452 */     checkItem.setSelected(true);
/* 453 */     checkItem.addItemListener(new ItemListener() {
/*     */       public void itemStateChanged(ItemEvent evt) {
/* 455 */         if (evt.getStateChange() == 1) {
/* 456 */           SceneManager.this.drawApp = true;
/*     */         } else {
/* 458 */           SceneManager.this.drawApp = false;
/*     */         }
/* 460 */         SceneManager.this.repaint();
/*     */       }
/* 462 */     });
/* 463 */     menu.add(checkItem);
/*     */     
/* 465 */     if (this.sceneMode == 2) {
/* 466 */       menu.addSeparator();
/*     */       
/* 468 */       checkItem = new JCheckBoxMenuItem("Draw node location");
/* 469 */       checkItem.setAccelerator(KeyStroke.getKeyStroke(119, 0));
/* 470 */       checkItem.setSelected(true);
/* 471 */       checkItem.addItemListener(new ItemListener() {
/*     */         public void itemStateChanged(ItemEvent evt) {
/* 473 */           if (evt.getStateChange() == 1) {
/* 474 */             SceneManager.this.drawNodeLocation = true;
/*     */           } else {
/* 476 */             SceneManager.this.drawNodeLocation = false;
/*     */           }
/* 478 */           SceneManager.this.repaint();
/*     */         }
/* 480 */       });
/* 481 */       menu.add(checkItem);
/*     */       
/* 483 */       checkItem = new JCheckBoxMenuItem("Draw transmission range");
/* 484 */       checkItem.setAccelerator(KeyStroke.getKeyStroke(120, 0));
/* 485 */       checkItem.setSelected(true);
/* 486 */       checkItem.addItemListener(new ItemListener() {
/*     */         public void itemStateChanged(ItemEvent evt) {
/* 488 */           if (evt.getStateChange() == 1) {
/* 489 */             SceneManager.this.drawSR = true;
/*     */           } else {
/* 491 */             SceneManager.this.drawSR = false;
/*     */           }
/* 493 */           SceneManager.this.repaint();
/*     */         }
/* 495 */       });
/* 496 */       menu.add(checkItem);
/*     */       
/* 498 */       checkItem = new JCheckBoxMenuItem("Draw interference range");
/* 499 */       checkItem.setAccelerator(KeyStroke.getKeyStroke(121, 0));
/* 500 */       checkItem.setSelected(false);
/* 501 */       checkItem.addItemListener(new ItemListener() {
/*     */         public void itemStateChanged(ItemEvent evt) {
/* 503 */           if (evt.getStateChange() == 1) {
/* 504 */             SceneManager.this.drawIR = true;
/*     */           } else {
/* 506 */             SceneManager.this.drawIR = false;
/*     */           }
/* 508 */           SceneManager.this.repaint();
/*     */         }
/* 510 */       });
/* 511 */       menu.add(checkItem);
/*     */       
/* 513 */       checkItem = new JCheckBoxMenuItem("Draw connectivity");
/* 514 */       checkItem.setAccelerator(KeyStroke.getKeyStroke(122, 0));
/* 515 */       checkItem.setSelected(true);
/* 516 */       checkItem.addItemListener(new ItemListener() {
/*     */         public void itemStateChanged(ItemEvent evt) {
/* 518 */           if (evt.getStateChange() == 1) {
/* 519 */             SceneManager.this.drawConnectivity = true;
/*     */           } else {
/* 521 */             SceneManager.this.drawConnectivity = false;
/*     */           }
/* 523 */           SceneManager.this.repaint();
/*     */         }
/* 525 */       });
/* 526 */       menu.add(checkItem);
/*     */     }
/* 528 */     this.menubar.add(menu);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 533 */     menu = new JMenu("Mode");
/* 534 */     menu.setMnemonic('M');
/* 535 */     item = new JMenuItem("Hand mode");
/* 536 */     item.setAccelerator(KeyStroke.getKeyStroke(49, 2));
/* 537 */     item.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 539 */         SceneManager.this.sv.switchMode(1);
/*     */       }
/* 541 */     });
/* 542 */     menu.add(item);
/*     */     
/* 544 */     item = new JMenuItem("Node mode");
/* 545 */     item.setAccelerator(KeyStroke.getKeyStroke(50, 2));
/* 546 */     item.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 548 */         SceneManager.this.sv.switchMode(2);
/*     */       }
/* 550 */     });
/* 551 */     menu.add(item);
/*     */     
/* 553 */     if (this.sceneMode == 1) {
/* 554 */       item = new JMenuItem("Link mode");
/* 555 */       item.setAccelerator(KeyStroke.getKeyStroke(51, 2));
/* 556 */       item.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent evt) {
/* 558 */           SceneManager.this.sv.switchMode(5);
/*     */         }
/* 560 */       });
/* 561 */       menu.add(item);
/*     */     }
/*     */     
/* 564 */     item = new JMenuItem("Agent mode");
/* 565 */     item.setAccelerator(KeyStroke.getKeyStroke(52, 2));
/* 566 */     item.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 568 */         SceneManager.this.sv.switchMode(3);
/*     */       }
/* 570 */     });
/* 571 */     menu.add(item);
/*     */     
/* 573 */     item = new JMenuItem("Application mode");
/* 574 */     item.setAccelerator(KeyStroke.getKeyStroke(53, 2));
/* 575 */     item.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 577 */         SceneManager.this.sv.switchMode(4);
/*     */       }
/* 579 */     });
/* 580 */     menu.add(item);
/*     */     
/* 582 */     item = new JMenuItem("Parameter");
/* 583 */     item.setAccelerator(KeyStroke.getKeyStroke(54, 2));
/* 584 */     item.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 586 */         SceneManager.this.parameters.setVisible(true);
/*     */       }
/* 588 */     });
/* 589 */     menu.add(item);
/*     */     
/* 591 */     item = new JMenuItem("Generate TCL");
/* 592 */     item.setAccelerator(KeyStroke.getKeyStroke(55, 2));
/* 593 */     item.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 595 */         SceneManager.this.nsg.createTCLManager(SceneManager.this);
/*     */       }
/* 597 */     });
/* 598 */     menu.add(item);
/*     */     
/* 600 */     this.menubar.add(menu);
/*     */   }
/*     */ }


/* Location:              C:\Users\Liudongdong\Desktop\NSG2.1.jar!\nsg\SceneManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */