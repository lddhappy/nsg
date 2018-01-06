/*     */ package nsg.analysis;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.Vector;
/*     */ import javax.swing.JFileChooser;
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
/*     */ public class TraceAnalyzer
/*     */ {
/*  24 */   ArrayList data = new ArrayList();
/*     */   
/*     */   public static void main(String[] args)
/*     */   {
/*  28 */     JFileChooser tcljfc = new JFileChooser();
/*     */     try {
/*  30 */       tcljfc.setDialogTitle("Please select file");
/*  31 */       int m = tcljfc.showSaveDialog(null);
/*  32 */       if (m == 0) {
/*  33 */         System.out.println("start analysis");
/*  34 */         File tclFile = tcljfc.getSelectedFile();
/*  35 */         TraceAnalyzer a = new TraceAnalyzer();
/*  36 */         a.analysisFile(tclFile);
/*     */       } else {
/*  38 */         System.out.println("Exit");
/*  39 */         return;
/*     */       }
/*     */     } catch (Exception evt) {
/*  42 */       System.out.println(evt.getMessage()); return;
/*     */     }
/*     */     
/*     */     File tclFile;
/*     */   }
/*     */   
/*     */   public void analysisFile(File f)
/*     */   {
/*     */     try
/*     */     {
/*  52 */       BufferedReader in = new BufferedReader(new InputStreamReader(
/*  53 */         new FileInputStream(f)));
/*     */       
/*  55 */       String line = in.readLine();
/*  56 */       while (line != null) {
/*  57 */         analysisLine(line.getBytes());
/*  58 */         line = in.readLine();
/*     */       }
/*     */     } catch (Exception e) {
/*  61 */       System.out.println(e.getMessage());
/*     */     }
/*  63 */     dump();
/*     */   }
/*     */   
/*     */   private void analysisLine(byte[] line)
/*     */   {
/*  68 */     Vector v = new Vector();
/*  69 */     String tmp = "";
/*  70 */     for (int i = 0; i < line.length; i++) {
/*  71 */       if (line[i] == 32) {
/*  72 */         if (!tmp.equals(""))
/*     */         {
/*     */ 
/*  75 */           v.add(tmp);
/*  76 */           tmp = "";
/*     */         }
/*     */       } else {
/*  79 */         tmp = tmp + String.valueOf((char)line[i]);
/*     */       }
/*     */     }
/*     */     
/*  83 */     String[] token = new String[v.size()];
/*  84 */     for (int i = 0; i < v.size(); i++) {
/*  85 */       token[i] = ((String)v.get(i));
/*     */     }
/*     */     
/*  88 */     Iterator it = this.data.iterator();
/*     */     
/*  90 */     while (it.hasNext()) {
/*  91 */       TraceItem p = (TraceItem)it.next();
/*  92 */       if ((p.event.equals(token[0])) && (p.node.equals(token[2])) && (p.layer.equals(token[3])) && (p.type.equals(token[6]))) {
/*  93 */         p.count += 1L;
/*  94 */         p.totalSize += Integer.parseInt(token[7]);
/*  95 */         return;
/*     */       }
/*     */     }
/*     */     
/*  99 */     TraceItem item = new TraceItem();
/* 100 */     item.event = token[0];
/* 101 */     item.node = token[2];
/* 102 */     item.layer = token[3];
/* 103 */     item.type = token[6];
/* 104 */     item.count = 1L;
/* 105 */     item.totalSize = Integer.parseInt(token[7]);
/* 106 */     this.data.add(item);
/*     */   }
/*     */   
/*     */   private void dump() {}
/*     */ }


/* Location:              C:\Users\Liudongdong\Desktop\NSG2.1.jar!\nsg\analysis\TraceAnalyzer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */