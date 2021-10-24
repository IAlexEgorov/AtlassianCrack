/*    */ package com.atlassian.extras.common.log;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Logger
/*    */ {
/*    */   private static final Class<?> LOG4J_LOGGER_CLASS;
/*    */   
/*    */   static {
/* 12 */     Class<?> log4jLogger = null;
/*    */     try {
/* 14 */       log4jLogger = Class.forName("com.atlassian.extras.common.log.Log4jLogger");
/* 15 */     } catch (ClassNotFoundException classNotFoundException) {
/*    */     
/*    */     } finally {
/* 18 */       LOG4J_LOGGER_CLASS = log4jLogger;
/*    */     } 
/*    */   } private static Log logger; public static interface Log {
/*    */     void setClass(Class param1Class); void debug(Object param1Object); void debug(Object param1Object, Throwable param1Throwable); void info(Object param1Object); void info(Object param1Object, Throwable param1Throwable); void warn(Object param1Object); void warn(Object param1Object, Throwable param1Throwable); void error(Object param1Object); void error(Object param1Object, Throwable param1Throwable); void fatal(Object param1Object);
/*    */     void fatal(Object param1Object, Throwable param1Throwable); }
/* 23 */   public enum Level { DEBUG, INFO, WARN, ERROR, FATAL; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 52 */   private static Level stdErrLogLevel = Level.INFO;
/*    */   
/*    */   public static void setInstance(Log logger) {
/* 55 */     Logger.logger = logger;
/*    */   }
/*    */ 
/*    */   
/*    */   public static Log getInstance(Class clazz) {
/* 60 */     if (logger != null) {
/* 61 */       return logger;
/*    */     }
/*    */ 
/*    */     
/*    */     try {
/* 66 */       if (LOG4J_LOGGER_CLASS != null) {
/* 67 */         Log log4j = (Log)LOG4J_LOGGER_CLASS.newInstance();
/* 68 */         log4j.setClass(clazz);
/* 69 */         return log4j;
/*    */       }
/*    */     
/* 72 */     } catch (IllegalAccessException illegalAccessException) {
/*    */     
/* 74 */     } catch (InstantiationException instantiationException) {
/*    */     
/* 76 */     } catch (NoClassDefFoundError noClassDefFoundError) {}
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 81 */     logger = new StdErrLogger(stdErrLogLevel);
/* 82 */     return logger;
/*    */   }
/*    */   
/*    */   public static void setStdErrLogLevel(Level stdErrLogLevel) {
/* 86 */     if (stdErrLogLevel == null) {
/* 87 */       throw new IllegalArgumentException("StdErrLogger Log Level must not be null.");
/*    */     }
/* 89 */     Logger.stdErrLogLevel = stdErrLogLevel;
/* 90 */     logger = null;
/*    */   }
/*    */ }


/* Location:              C:\Trash\lib - Copy\atlassian-extras-common-3.4.1.jar!\com\atlassian\extras\common\log\Logger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */