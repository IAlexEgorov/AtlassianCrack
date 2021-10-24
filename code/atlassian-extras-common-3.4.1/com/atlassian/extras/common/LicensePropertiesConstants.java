/*     */ package com.atlassian.extras.common;
/*     */ 
/*     */ import com.atlassian.extras.api.Product;
/*     */ import java.util.Date;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class LicensePropertiesConstants
/*     */ {
/*     */   public static final String LICENSE_VERSION = "licenseVersion";
/*     */   public static final String ACTIVE_FLAG = "active";
/*     */   public static final String ACTIVE_VALUE = "true";
/*     */   public static final String ORGANISATION = "Organisation";
/*     */   public static final String CONTACT_NAME = "ContactName";
/*     */   public static final String CONTACT_EMAIL = "ContactEMail";
/*     */   public static final String LICENSE_EXPIRY_DATE = "LicenseExpiryDate";
/*     */   public static final String GRACE_PERIOD = "GracePeriod";
/*     */   public static final int DEFAULT_GRACE_PERIOD = 0;
/*     */   public static final String MAINTENANCE_EXPIRY_DATE = "MaintenanceExpiryDate";
/*     */   public static final String PURCHASE_DATE = "PurchaseDate";
/*     */   public static final String CREATION_DATE = "CreationDate";
/*     */   public static final String SERVER_ID = "ServerID";
/*     */   public static final String LICENSE_ID = "LicenseID";
/*     */   public static final String LICENSE_TYPE = "LicenseType";
/*     */   public static final String LICENSE_TYPE_NAME = "LicenseTypeName";
/*     */   public static final String MAX_NUMBER_OF_USERS = "NumberOfUsers";
/*     */   public static final String PARTNER_NAME = "PartnerName";
/*     */   public static final String EVALUATION_LICENSE = "Evaluation";
/*     */   public static final String STARTER_LICENSE = "Starter";
/*     */   public static final String SUBSCRIPTION_LICENSE = "Subscription";
/*     */   public static final String DESCRIPTION = "Description";
/*     */   public static final String SUPPORT_ENTITLEMENT_NUMBER = "SEN";
/*     */   public static final String NAMESPACE_SEPARATOR = ".";
/*  63 */   public static final Date DEFAULT_CREATION_DATE = DateEditor.getDate("1970-01-01");
/*  64 */   public static final Date DEFAULT_EXPIRY_DATE = DEFAULT_CREATION_DATE;
/*     */ 
/*     */   
/*     */   public static final int UNLIMITED_USERS = -1;
/*     */ 
/*     */   
/*     */   public static final int DEFAULT_MAX_USERS = 0;
/*     */ 
/*     */   
/*     */   public static final String MAX_NUMBER_CONF_CLUSTER_NODES = "NumberOfClusterNodes";
/*     */ 
/*     */   
/*     */   public static final int UNLIMITED_CONF_CLUSTER_NODES = -1;
/*     */ 
/*     */   
/*     */   public static final int DEFAULT_CONF_CLUSTER_NODES = 0;
/*     */ 
/*     */   
/*     */   public static final String MAX_NUMBER_BAM_REMOTE_AGENTS = "NumberOfBambooRemoteAgents";
/*     */ 
/*     */   
/*     */   public static final String MAX_NUMBER_BAM_LOCAL_AGENTS = "NumberOfBambooLocalAgents";
/*     */ 
/*     */   
/*     */   public static final String MAX_NUMBER_BAM_PLANS = "NumberOfBambooPlans";
/*     */ 
/*     */   
/*     */   public static final int DEFAULT_BAM_REMOTE_AGENTS = 1;
/*     */ 
/*     */   
/*     */   public static final int DEFAULT_BAM_LOCAL_AGENTS = -1;
/*     */ 
/*     */   
/*     */   public static final int DEFAULT_BAM_PLANS = -1;
/*     */   
/*     */   public static final int BAMBOO_UNLIMITED = -1;
/*     */   
/*     */   public static final String LICENSE_EDITION = "LicenseEdition";
/*     */   
/*     */   public static final String DATA_CENTER = "DataCenter";
/*     */ 
/*     */   
/*     */   public static String getKey(Product product, String key) {
/* 107 */     return product.getNamespace() + "." + key;
/*     */   }
/*     */ }


/* Location:              C:\Trash\lib - Copy\atlassian-extras-common-3.4.1.jar!\com\atlassian\extras\common\LicensePropertiesConstants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */