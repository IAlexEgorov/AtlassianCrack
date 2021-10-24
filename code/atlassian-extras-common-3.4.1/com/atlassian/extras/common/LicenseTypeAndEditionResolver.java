/*    */ package com.atlassian.extras.common;
/*    */ 
/*    */ import com.atlassian.extras.api.LicenseEdition;
/*    */ import com.atlassian.extras.api.LicenseType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LicenseTypeAndEditionResolver
/*    */ {
/*    */   public static LicenseEdition getLicenseEdition(String editionName) {
/*    */     try {
/* 13 */       return LicenseEdition.valueOf(editionName.toUpperCase());
/* 14 */     } catch (IllegalArgumentException e) {
/* 15 */       throw new LicenseException("Failed to lookup license edition <" + editionName + ">");
/* 16 */     } catch (NullPointerException e) {
/* 17 */       throw new LicenseException("Failed to lookup license edition <" + editionName + ">");
/*    */     } 
/*    */   }
/*    */   
/*    */   public static LicenseType getLicenseType(String typeName) {
/*    */     try {
/* 23 */       return LicenseType.valueOf(typeName.toUpperCase());
/* 24 */     } catch (IllegalArgumentException e) {
/* 25 */       throw new LicenseException("Failed to lookup license type <" + typeName + ">");
/* 26 */     } catch (NullPointerException e) {
/* 27 */       throw new LicenseException("Failed to lookup license type <" + typeName + ">");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Trash\lib - Copy\atlassian-extras-common-3.4.1.jar!\com\atlassian\extras\common\LicenseTypeAndEditionResolver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */