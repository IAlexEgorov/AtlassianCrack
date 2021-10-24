/*    */ package com.atlassian.extras.common;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DateParsingException
/*    */   extends LicenseException
/*    */ {
/*    */   private final String dateString;
/*    */   
/*    */   public DateParsingException(String dateString) {
/* 14 */     this.dateString = dateString;
/*    */   }
/*    */   
/*    */   public DateParsingException(String dateString, Throwable throwable) {
/* 18 */     super(throwable);
/* 19 */     this.dateString = dateString;
/*    */   }
/*    */   
/*    */   public String getMessage() {
/* 23 */     return "Could NOT parse <" + this.dateString + "> into a 'license' date";
/*    */   }
/*    */ }


/* Location:              C:\Trash\lib - Copy\atlassian-extras-common-3.4.1.jar!\com\atlassian\extras\common\DateParsingException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */