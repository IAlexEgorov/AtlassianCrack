/*    */ package com.atlassian.extras.common;
/*    */ 
/*    */ import com.atlassian.extras.api.LicenseException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LicenseException
/*    */   extends LicenseException
/*    */ {
/*    */   public LicenseException() {}
/*    */   
/*    */   public LicenseException(String message) {
/* 16 */     super(message);
/*    */   }
/*    */   
/*    */   public LicenseException(String message, Throwable cause) {
/* 20 */     super(message, cause);
/*    */   }
/*    */   
/*    */   public LicenseException(Throwable cause) {
/* 24 */     super(cause);
/*    */   }
/*    */ }


/* Location:              C:\Trash\lib - Copy\atlassian-extras-common-3.4.1.jar!\com\atlassian\extras\common\LicenseException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */