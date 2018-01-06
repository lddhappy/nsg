/*     */ package nsg.analysis;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.Vector;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JInternalFrame;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextPane;
/*     */ import javax.swing.text.Document;
/*     */ import javax.swing.text.SimpleAttributeSet;
/*     */ import javax.swing.text.StyleConstants;
/*     */ 
/*     */ public class PacketCounterFrame
/*     */   extends JInternalFrame
/*     */ {
/*     */   Container c;
/*  29 */   JButton b = new JButton("Analyze trace file");
/*     */   
/*  31 */   JTextPane message = new JTextPane();
/*     */   
/*  33 */   SimpleAttributeSet outputAttr = new SimpleAttributeSet();
/*     */   
/*     */ 
/*  36 */   Document doc = this.message.getDocument();
/*     */   
/*     */   File traceFile;
/*     */   
/*  40 */   JFileChooser tracejfc = new JFileChooser();
/*     */   ArrayList data;
/*     */   
/*     */   public PacketCounterFrame()
/*     */   {
/*  45 */     super("PacketCounterFrame", true, true, true, true);
/*  46 */     StyleConstants.setFontSize(this.outputAttr, 14);
/*  47 */     StyleConstants.setFontFamily(this.outputAttr, "Courier New");
/*  48 */     StyleConstants.setBold(this.outputAttr, true);
/*  49 */     StyleConstants.setForeground(this.outputAttr, Color.BLACK);
/*     */     
/*     */ 
/*  52 */     this.c = getContentPane();
/*  53 */     JPanel up = new JPanel();
/*  54 */     this.c.add(up, "North");
/*  55 */     this.c.add(new JScrollPane(this.message), "Center");
/*     */     
/*  57 */     up.add(this.b);
/*  58 */     this.b.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/*     */         try {
/*  61 */           PacketCounterFrame.this.tracejfc.setDialogTitle("Please select file");
/*  62 */           int m = PacketCounterFrame.this.tracejfc.showOpenDialog(null);
/*  63 */           if (m == 0) {
/*  64 */             PacketCounterFrame.this.appendNote("start analysis\n");
/*  65 */             PacketCounterFrame.this.traceFile = PacketCounterFrame.this.tracejfc.getSelectedFile();
/*  66 */             PacketCounterFrame.this.analysisFile(PacketCounterFrame.this.traceFile);
/*     */           }
/*     */           else {}
/*     */         }
/*     */         catch (Exception evt)
/*     */         {
/*  72 */           System.out.println(evt.getMessage());
/*  73 */           return;
/*     */         }
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   public void analysisFile(File f) {
/*  80 */     this.data = new ArrayList();
/*     */     try
/*     */     {
/*  83 */       BufferedReader in = new BufferedReader(new InputStreamReader(
/*  84 */         new FileInputStream(f)));
/*     */       
/*  86 */       String line = in.readLine();
/*  87 */       while (line != null) {
/*  88 */         analysisLine(line.getBytes());
/*  89 */         line = in.readLine();
/*     */       }
/*     */     } catch (Exception e) {
/*  92 */       System.out.println(e.getMessage());
/*     */     }
/*  94 */     dump(this.data);
/*     */   }
/*     */   
/*     */ 
/*     */   private void analysisLine(byte[] line)
/*     */   {
/* 100 */     Vector v = new Vector();
/* 101 */     String tmp = "";
/* 102 */     for (int i = 0; i < line.length; i++) {
/* 103 */       if (line[i] == 32) {
/* 104 */         if (!tmp.equals(""))
/*     */         {
/*     */ 
/* 107 */           v.add(tmp);
/* 108 */           tmp = "";
/*     */         }
/*     */       } else {
/* 111 */         tmp = tmp + String.valueOf((char)line[i]);
/*     */       }
/*     */     }
/*     */     
/* 115 */     String[] token = new String[v.size()];
/* 116 */     for (int i = 0; i < v.size(); i++) {
/* 117 */       token[i] = ((String)v.get(i));
/*     */     }
/*     */     
/* 120 */     Iterator it = this.data.iterator();
/*     */     
/* 122 */     while (it.hasNext()) {
/* 123 */       TraceItem p = (TraceItem)it.next();
/* 124 */       if ((p.event.equals(token[0])) && (p.node.equals(token[2])) && 
/* 125 */         (p.layer.equals(token[3])) && (p.type.equals(token[6]))) {
/* 126 */         p.count += 1L;
/* 127 */         p.totalSize += Integer.parseInt(token[7]);
/* 128 */         return;
/*     */       }
/*     */     }
/*     */     
/* 132 */     TraceItem item = new TraceItem();
/* 133 */     item.event = token[0];
/* 134 */     item.node = token[2];
/* 135 */     item.layer = token[3];
/* 136 */     item.type = token[6];
/* 137 */     item.count = 1L;
/* 138 */     item.totalSize = Integer.parseInt(token[7]);
/* 139 */     this.data.add(item);
/*     */   }
/*     */   
/*     */ 
/*     */   private void dump(ArrayList data)
/*     */   {
/* 145 */     Iterator it = data.iterator();
/*     */     
/* 147 */     while (it.hasNext()) {
/* 148 */       TraceItem p = (TraceItem)it.next();
/* 149 */       appendNote(p.event + " " + p.node + " " + p.layer + " " + p.type + " " + p.count + " " + p.totalSize + "\n");
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
/*     */   public void appendNote(String note)
/*     */   {
/*     */     try
/*     */     {
/* 168 */       this.doc.insertString(this.message.getCaretPosition(), note, this.outputAttr);
/*     */     } catch (Exception evt) {
/* 170 */       evt.printStackTrace();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Liudongdong\Desktop\NSG2.1.jar!\nsg\analysis\PacketCounterFrame.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */