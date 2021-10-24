/*    */ package com.atlassian.extras.common.log;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ 
/*    */ class StdErrLogger
/*    */   implements Logger.Log
/*    */ {
/*    */   private final Logger.Level level;
/*    */   
/*    */   StdErrLogger() {
/* 12 */     this(Logger.Level.INFO);
/*    */   }
/*    */   
/*    */   StdErrLogger(Logger.Level level) {
/* 16 */     this.level = level;
/*    */   }
/*    */   
/* 19 */   private static final PrintStream PRINT_STREAM = System.err;
/*    */ 
/*    */   
/*    */   public void setClass(Class clazz) {}
/*    */ 
/*    */   
/*    */   public void debug(Object msg) {
/* 26 */     if (this.level.compareTo(Logger.Level.DEBUG) <= 0) {
/* 27 */       PRINT_STREAM.println(msg);
/*    */     }
/*    */   }
/*    */   
/*    */   public void debug(Object msg, Throwable t) {
/* 32 */     if (this.level.compareTo(Logger.Level.DEBUG) <= 0) {
/* 33 */       PRINT_STREAM.println(msg);
/* 34 */       t.printStackTrace(PRINT_STREAM);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void info(Object msg) {
/* 39 */     if (this.level.compareTo(Logger.Level.INFO) <= 0) {
/* 40 */       PRINT_STREAM.println(msg);
/*    */     }
/*    */   }
/*    */   
/*    */   public void info(Object msg, Throwable t) {
/* 45 */     if (this.level.compareTo(Logger.Level.INFO) <= 0) {
/* 46 */       PRINT_STREAM.println(msg);
/* 47 */       t.printStackTrace(PRINT_STREAM);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void warn(Object msg) {
/* 52 */     if (this.level.compareTo(Logger.Level.WARN) <= 0) {
/* 53 */       PRINT_STREAM.println(msg);
/*    */     }
/*    */   }
/*    */   
/*    */   public void warn(Object msg, Throwable t) {
/* 58 */     if (this.level.compareTo(Logger.Level.WARN) <= 0) {
/* 59 */       PRINT_STREAM.println(msg);
/* 60 */       t.printStackTrace(PRINT_STREAM);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void error(Object msg) {
/* 65 */     if (this.level.compareTo(Logger.Level.ERROR) <= 0) {
/* 66 */       PRINT_STREAM.println(msg);
/*    */     }
/*    */   }
/*    */   
/*    */   public void error(Object msg, Throwable t) {
/* 71 */     if (this.level.compareTo(Logger.Level.ERROR) <= 0) {
/* 72 */       PRINT_STREAM.println(msg);
/* 73 */       t.printStackTrace(PRINT_STREAM);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void error(Throwable t) {
/* 78 */     if (this.level.compareTo(Logger.Level.ERROR) <= 0) {
/* 79 */       t.printStackTrace(PRINT_STREAM);
/*    */     }
/*    */   }
/*    */   
/*    */   public void fatal(Object msg) {
/* 84 */     if (this.level.compareTo(Logger.Level.FATAL) <= 0) {
/* 85 */       PRINT_STREAM.println(msg);
/*    */     }
/*    */   }
/*    */   
/*    */   public void fatal(Object msg, Throwable t) {
/* 90 */     if (this.level.compareTo(Logger.Level.FATAL) <= 0) {
/* 91 */       PRINT_STREAM.println(msg);
/* 92 */       t.printStackTrace(PRINT_STREAM);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void fatal(Throwable t) {
/* 97 */     if (this.level.compareTo(Logger.Level.FATAL) <= 0)
/* 98 */       t.printStackTrace(PRINT_STREAM); 
/*    */   }
/*    */ }


/* Location:              C:\Trash\lib - Copy\atlassian-extras-common-3.4.1.jar!\com\atlassian\extras\common\log\StdErrLogger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */