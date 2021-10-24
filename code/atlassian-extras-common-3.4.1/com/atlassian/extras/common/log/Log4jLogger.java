/*    */ package com.atlassian.extras.common.log;
/*    */ 
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ class Log4jLogger
/*    */   implements Logger.Log
/*    */ {
/*    */   private Logger logger;
/*    */   
/*    */   public Log4jLogger() {}
/*    */   
/*    */   public Log4jLogger(Class clazz) {
/* 13 */     this.logger = Logger.getLogger(clazz);
/*    */   }
/*    */   
/*    */   public void setClass(Class clazz) {
/* 17 */     this.logger = Logger.getLogger(clazz);
/*    */   }
/*    */   
/*    */   public void debug(Object o) {
/* 21 */     this.logger.debug(o);
/*    */   }
/*    */   
/*    */   public void debug(Object o, Throwable t) {
/* 25 */     this.logger.debug(o, t);
/*    */   }
/*    */   
/*    */   public void info(Object o) {
/* 29 */     this.logger.info(o);
/*    */   }
/*    */   
/*    */   public void info(Object o, Throwable t) {
/* 33 */     this.logger.info(o, t);
/*    */   }
/*    */   
/*    */   public void warn(Object o) {
/* 37 */     this.logger.info(o);
/*    */   }
/*    */   
/*    */   public void warn(Object o, Throwable t) {
/* 41 */     this.logger.info(o, t);
/*    */   }
/*    */   
/*    */   public void error(Object o) {
/* 45 */     this.logger.error(o);
/*    */   }
/*    */   
/*    */   public void error(Object o, Throwable t) {
/* 49 */     this.logger.error(o, t);
/*    */   }
/*    */   
/*    */   public void fatal(Object o) {
/* 53 */     this.logger.fatal(o);
/*    */   }
/*    */   
/*    */   public void fatal(Object o, Throwable t) {
/* 57 */     this.logger.fatal(o, t);
/*    */   }
/*    */ }


/* Location:              C:\Trash\lib - Copy\atlassian-extras-common-3.4.1.jar!\com\atlassian\extras\common\log\Log4jLogger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */