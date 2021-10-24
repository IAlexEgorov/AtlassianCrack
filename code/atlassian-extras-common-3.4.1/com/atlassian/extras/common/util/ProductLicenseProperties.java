/*    */ package com.atlassian.extras.common.util;
/*    */ 
/*    */ import com.atlassian.extras.api.Product;
/*    */ import com.atlassian.extras.common.DateEditor;
/*    */ import com.atlassian.extras.common.LicensePropertiesConstants;
/*    */ import java.util.Date;
/*    */ import java.util.Enumeration;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.Properties;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ProductLicenseProperties
/*    */   implements LicenseProperties
/*    */ {
/*    */   private final Product product;
/*    */   private final Properties properties;
/*    */   
/*    */   public ProductLicenseProperties(Product product, Properties properties) {
/* 25 */     this.product = product;
/* 26 */     if (product == null) {
/* 27 */       throw new IllegalArgumentException("Product must NOT be null!");
/*    */     }
/* 29 */     if (properties == null) {
/* 30 */       throw new IllegalArgumentException("Properties must NOT be null!");
/*    */     }
/*    */     
/* 33 */     this.properties = properties;
/*    */   }
/*    */   
/*    */   public String getProperty(String s) {
/* 37 */     return getProperty(s, null);
/*    */   }
/*    */   
/*    */   public String getProperty(String s, String defaultValue) {
/* 41 */     String o = this.properties.getProperty(LicensePropertiesConstants.getKey(this.product, s));
/* 42 */     return (o != null) ? o : this.properties.getProperty(s, defaultValue);
/*    */   }
/*    */   
/*    */   public int getInt(String propertyName, int defaultValue) {
/* 46 */     String stringValue = getProperty(propertyName);
/* 47 */     if (stringValue == null || stringValue.length() == 0) {
/* 48 */       return defaultValue;
/*    */     }
/*    */     
/*    */     try {
/* 52 */       return Integer.parseInt(stringValue);
/* 53 */     } catch (NumberFormatException e) {
/* 54 */       return defaultValue;
/*    */     } 
/*    */   }
/*    */   
/*    */   public Date getDate(String key, Date defaultValue) {
/* 59 */     String stringValue = getProperty(key);
/* 60 */     if (stringValue == null || stringValue.length() == 0) {
/* 61 */       return defaultValue;
/*    */     }
/* 63 */     return DateEditor.getDate(stringValue);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean getBoolean(String key) {
/* 68 */     return Boolean.valueOf(getProperty(key)).booleanValue();
/*    */   }
/*    */   
/*    */   public Map<String, String> getPropertiesEndingWith(String ending) {
/* 72 */     Map<String, String> props = new HashMap<>();
/* 73 */     for (Enumeration<?> enumeration = this.properties.propertyNames(); enumeration.hasMoreElements(); ) {
/* 74 */       String propName = enumeration.nextElement().toString();
/* 75 */       if (propName.endsWith(ending)) {
/* 76 */         props.put(propName, getProperty(propName));
/*    */       }
/*    */     } 
/* 79 */     return props;
/*    */   }
/*    */ }


/* Location:              C:\Trash\lib - Copy\atlassian-extras-common-3.4.1.jar!\com\atlassian\extras\commo\\util\ProductLicenseProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */