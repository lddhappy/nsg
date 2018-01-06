/*     */ package nsg.p2p;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Container;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.util.Vector;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JInternalFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class P2PAnalysisFrame
/*     */   extends JInternalFrame
/*     */ {
/*     */   File file;
/*     */   Container c;
/*  28 */   JTextField filenameField = new JTextField(30);
/*  29 */   JTextField widthField = new JTextField(5);
/*  30 */   JTextField heightField = new JTextField(5);
/*  31 */   JButton start = new JButton("Start");
/*  32 */   JTextArea output1 = new JTextArea();
/*  33 */   JTextArea output2 = new JTextArea();
/*  34 */   JTextArea output3 = new JTextArea();
/*  35 */   JTextArea output4 = new JTextArea();
/*  36 */   JTextArea msgBoard = new JTextArea(6, 10);
/*     */   
/*  38 */   double[] packetMap = new double[1000000];
/*     */   int width;
/*     */   int height;
/*     */   int peers;
/*     */   String message;
/*     */   long[] receive;
/*     */   long[] drop;
/*     */   double[] delay;
/*     */   long[] count;
/*     */   
/*     */   public P2PAnalysisFrame() {
/*  49 */     super("LossAnalysisFrame", true, true, true, true);
/*  50 */     uiInit();
/*  51 */     this.start.addActionListener(new ActionListener()
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       public void actionPerformed(ActionEvent e)
/*     */       {
/*     */ 
/*     */ 
/*  60 */         new Thread()
/*     */         {
/*     */           public void run()
/*     */           {
/*  55 */             P2PAnalysisFrame.this.start.setEnabled(false);
/*  56 */             P2PAnalysisFrame.this.init();
/*  57 */             P2PAnalysisFrame.this.analysis();
/*  58 */             P2PAnalysisFrame.this.start.setEnabled(true);
/*     */           }
/*     */         }.start();
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   public void init()
/*     */   {
/*  67 */     this.width = Integer.parseInt(this.widthField.getText());
/*  68 */     this.height = Integer.parseInt(this.heightField.getText());
/*  69 */     this.peers = (this.width * this.height);
/*     */     
/*  71 */     this.delay = new double[this.peers];
/*  72 */     this.count = new long[this.peers];
/*  73 */     this.receive = new long[this.peers];
/*  74 */     this.drop = new long[this.peers];
/*     */   }
/*     */   
/*     */   public void analysis()
/*     */   {
/*     */     try {
/*  80 */       if (this.filenameField.getText().equals("")) {
/*  81 */         JFileChooser tcljfc = new JFileChooser();
/*  82 */         tcljfc.setDialogTitle("Please select file");
/*  83 */         int m = tcljfc.showSaveDialog(null);
/*  84 */         if (m == 0) {
/*  85 */           this.file = tcljfc.getSelectedFile();
/*  86 */           this.filenameField.setText(this.file.getAbsolutePath());
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/*  91 */         this.file = new File(this.filenameField.getText());
/*     */       }
/*  93 */       this.output1.setText("");
/*  94 */       this.output2.setText("");
/*  95 */       this.output3.setText("");
/*  96 */       this.output4.setText("");
/*  97 */       this.msgBoard.setText("");
/*     */       
/*     */ 
/* 100 */       analysisFile(this.file);
/*     */     } catch (Exception e) {
/* 102 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void analysisFile(File f) {
/*     */     try {
/* 108 */       BufferedReader in = new BufferedReader(new InputStreamReader(
/* 109 */         new FileInputStream(f)));
/*     */       
/* 111 */       String line = in.readLine();
/* 112 */       while (line != null) {
/* 113 */         if (!analysisLine(line.getBytes())) {
/* 114 */           this.msgBoard.append(line + "\n");
/*     */         }
/* 116 */         line = in.readLine();
/*     */       }
/*     */       
/* 119 */       dump();
/*     */     } catch (Exception e) {
/* 121 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean analysisLine(byte[] line)
/*     */   {
/* 127 */     Vector<String> v = new Vector();
/* 128 */     String tmp = "";
/* 129 */     for (int i = 0; i < line.length; i++) {
/* 130 */       if (line[i] == 32) {
/* 131 */         if (!tmp.equals("")) {
/* 132 */           v.add(tmp);
/* 133 */           tmp = "";
/*     */         }
/*     */       } else
/* 136 */         tmp = tmp + String.valueOf((char)line[i]);
/*     */     }
/* 138 */     if (tmp.length() != 0) { v.add(tmp);
/*     */     }
/*     */     
/* 141 */     String[] token = new String[v.size()];
/* 142 */     for (int i = 0; i < v.size(); i++) {
/* 143 */       token[i] = ((String)v.get(i));
/*     */     }
/*     */     
/*     */ 
/* 147 */     int type = Integer.parseInt(token[0]);
/* 148 */     int id = Integer.parseInt(token[1]);
/*     */     
/*     */ 
/*     */ 
/* 152 */     switch (type)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */     case 1: 
/* 158 */       int pid = Integer.parseInt(token[3]);
/* 159 */       double time = Double.parseDouble(token[4]);
/* 160 */       if (id == 0) {
/* 161 */         this.packetMap[pid] = time;
/*     */       } else {
/* 163 */         this.delay[(id - 1)] += time - this.packetMap[pid];
/* 164 */         this.count[(id - 1)] += 1L;
/*     */       }
/* 166 */       return true;
/*     */     
/*     */ 
/*     */     case 2: 
/* 170 */       int rcv = Integer.parseInt(token[2]);
/* 171 */       int drp = Integer.parseInt(token[3]);
/*     */       
/* 173 */       if (id == 0) { return false;
/*     */       }
/* 175 */       this.receive[(id - 1)] = rcv;
/* 176 */       this.drop[(id - 1)] = drp;
/* 177 */       return true; }
/*     */     
/* 179 */     return false;
/*     */   }
/*     */   
/*     */   private void dump()
/*     */   {
/* 184 */     for (int i = 0; i < this.peers; i++) {
/* 185 */       if (this.count[i] == 0L) {
/* 186 */         this.output1.append("-1\n");
/*     */       } else {
/* 188 */         this.output1.append(String.valueOf(this.delay[i] / this.count[i]) + "\n");
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 193 */     double totalDelay = 0.0D;
/* 194 */     int counter = 0;
/* 195 */     for (int i = 0; i < this.peers; i++) {
/* 196 */       if (this.count[i] != 0L) {
/* 197 */         totalDelay += this.delay[i] / this.count[i];
/* 198 */         counter++;
/*     */       }
/* 200 */       if ((i + 1) % this.width == 0) {
/* 201 */         this.output2.append(String.valueOf(totalDelay / counter) + "\n");
/* 202 */         totalDelay = 0.0D;
/* 203 */         counter = 0;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 208 */     for (int i = 0; i < this.peers; i++) {
/* 209 */       if (this.receive[i] == 0L) {
/* 210 */         this.output3.append("1\n");
/*     */       } else {
/* 212 */         this.output3.append(String.valueOf(this.drop[i] / (this.drop[i] + this.receive[i])) + "\n");
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 217 */     double totalRate = 0.0D;
/* 218 */     for (int i = 0; i < this.peers; i++) {
/* 219 */       if (this.receive[i] == 0L) {
/* 220 */         totalRate += 1.0D;
/*     */       } else {
/* 222 */         totalRate += this.drop[i] / (this.drop[i] + this.receive[i]);
/*     */       }
/*     */       
/* 225 */       if ((i + 1) % this.width == 0) {
/* 226 */         this.output4.append(String.valueOf(totalRate / this.width) + "\n");
/* 227 */         totalRate = 0.0D;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void uiInit() {
/* 233 */     this.c = getContentPane();
/*     */     
/* 235 */     this.filenameField.setText(System.getProperty("user.home") + "/csvt-sim/");
/* 236 */     this.widthField.setText("50");
/* 237 */     this.heightField.setText("20");
/*     */     
/* 239 */     JPanel up = new JPanel();
/* 240 */     up.setLayout(new GridLayout(2, 1));
/* 241 */     JPanel up1 = new JPanel(new FlowLayout(0));
/* 242 */     up1.add(new JLabel("Filename"));
/* 243 */     up1.add(this.filenameField);
/* 244 */     up.add(up1);
/* 245 */     JPanel up2 = new JPanel(new FlowLayout(0));
/* 246 */     up2.add(new JLabel("Width"));
/* 247 */     up2.add(this.widthField);
/* 248 */     up2.add(new JLabel("Height"));
/* 249 */     up2.add(this.heightField);
/* 250 */     up2.add(this.start);
/* 251 */     up.add(up2);
/* 252 */     this.c.add(up, "North");
/*     */     
/* 254 */     JPanel center = new JPanel(new BorderLayout());
/* 255 */     JPanel label = new JPanel(new GridLayout(1, 4));
/* 256 */     label.add(new JLabel("Delay"));
/* 257 */     label.add(new JLabel("Avg. delay"));
/* 258 */     label.add(new JLabel("Loss rate"));
/* 259 */     label.add(new JLabel("Avg. loss rate"));
/* 260 */     center.add(label, "North");
/*     */     
/* 262 */     JPanel output = new JPanel(new GridLayout(1, 4));
/* 263 */     output.add(new JScrollPane(this.output1));
/* 264 */     output.add(new JScrollPane(this.output2));
/* 265 */     output.add(new JScrollPane(this.output3));
/* 266 */     output.add(new JScrollPane(this.output4));
/* 267 */     center.add(output, "Center");
/* 268 */     this.c.add(center, "Center");
/*     */     
/* 270 */     JPanel down = new JPanel(new BorderLayout());
/* 271 */     down.add(new JLabel("Message Board"), "North");
/* 272 */     down.add(new JScrollPane(this.msgBoard), "Center");
/* 273 */     this.c.add(down, "South");
/*     */   }
/*     */ }


/* Location:              C:\Users\Liudongdong\Desktop\NSG2.1.jar!\nsg\p2p\P2PAnalysisFrame.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */