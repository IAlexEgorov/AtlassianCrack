/*     */ package com.atlassian.extras.decoder.v2;
/*     */ 
/*     */ import com.atlassian.extras.common.LicenseException;
/*     */ import com.atlassian.extras.decoder.api.AbstractLicenseDecoder;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.security.InvalidKeyException;
/*     */ import java.security.KeyFactory;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.PublicKey;
/*     */ import java.security.Signature;
/*     */ import java.security.SignatureException;
/*     */ import java.security.spec.InvalidKeySpecException;
/*     */ import java.security.spec.X509EncodedKeySpec;
/*     */ import java.util.Properties;
/*     */ import java.util.zip.Inflater;
/*     */ import java.util.zip.InflaterInputStream;
/*     */ import org.apache.commons.codec.binary.Base64;
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
/*     */ public class Version2LicenseDecoder
/*     */   extends AbstractLicenseDecoder
/*     */ {
/*     */   public static final int VERSION_NUMBER_1 = 1;
/*     */   public static final int VERSION_NUMBER_2 = 2;
/*     */   public static final int VERSION_LENGTH = 3;
/*     */   public static final int ENCODED_LICENSE_LENGTH_BASE = 31;
/*  59 */   public static final byte[] LICENSE_PREFIX = new byte[] { 13, 14, 12, 10, 15 };
/*     */   
/*     */   public static final char SEPARATOR = 'X';
/*     */   private static final PublicKey PUBLIC_KEY;
/*     */   private static final int ENCODED_LICENSE_LINE_LENGTH = 76;
/*     */   
/*     */   static {
/*     */     try {
/*  67 */       String pubKeyEncoded = "MIIBuDCCASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLCT7up1/63xhv4O1fnxqimFQ8E+4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmUr7v6OuqC+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKTxvqhRkImog9/hWuWfBpKLZl6Ae1UlZAFMO/7PSSoDgYUAAoGBAIvfweZvmGo5otwawI3no7Udanxal3hX2haw962KL/nHQrnC4FG2PvUFf34OecSK1KtHDPQoSQ+DHrfdf6vKUJphw0Kn3gXm4LS8VK/LrY7on/wh2iUobS2XlhuIqEc5mLAUu9Hd+1qxsQkQ50d0lzKrnDqPsM0WA9htkdJJw2nS";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  74 */       KeyFactory keyFactory = KeyFactory.getInstance("DSA");
/*  75 */       PUBLIC_KEY = keyFactory.generatePublic(new X509EncodedKeySpec(Base64.decodeBase64("MIIBuDCCASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLCT7up1/63xhv4O1fnxqimFQ8E+4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmUr7v6OuqC+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKTxvqhRkImog9/hWuWfBpKLZl6Ae1UlZAFMO/7PSSoDgYUAAoGBAIvfweZvmGo5otwawI3no7Udanxal3hX2haw962KL/nHQrnC4FG2PvUFf34OecSK1KtHDPQoSQ+DHrfdf6vKUJphw0Kn3gXm4LS8VK/LrY7on/wh2iUobS2XlhuIqEc5mLAUu9Hd+1qxsQkQ50d0lzKrnDqPsM0WA9htkdJJw2nS".getBytes())));
/*  76 */     } catch (NoSuchAlgorithmException e) {
/*     */       
/*  78 */       throw new Error(e);
/*  79 */     } catch (InvalidKeySpecException e) {
/*     */       
/*  81 */       throw new Error(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean canDecode(String licenseString) {
                return true;
/*     */   }
/*     */   
/*     */   public Properties doDecode(String licenseString) {
/*     */
/* 117 */     return loadLicenseConfiguration();
/*     */   }
/*     */   
/*     */   protected int getLicenseVersion() {
/* 121 */     return 2;
/*     */   }
/*     */   
/*     */   private Reader unzipText(byte[] licenseText) {
/* 125 */     ByteArrayInputStream in = new ByteArrayInputStream(licenseText);
/* 126 */     in.skip(LICENSE_PREFIX.length);
/* 127 */     InflaterInputStream zipIn = new InflaterInputStream(in, new Inflater());
/*     */     try {
/* 129 */       return new InputStreamReader(zipIn, "UTF-8");
/* 130 */     } catch (UnsupportedEncodingException e) {
/*     */       
/* 132 */       throw new LicenseException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private String getLicenseContent(String licenseString) {
/* 137 */     String lengthStr = licenseString.substring(licenseString.lastIndexOf('X') + 3);
/*     */     try {
/* 139 */       int encodedLicenseLength = Integer.valueOf(lengthStr, 31).intValue();
/* 140 */       return licenseString.substring(0, encodedLicenseLength);
/* 141 */     } catch (NumberFormatException e) {
/* 142 */       throw new LicenseException("Could NOT decode license length <" + lengthStr + ">", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private byte[] checkAndGetLicenseText(String licenseContent) {
/*     */     byte[] licenseText;
/*     */     try {
/* 149 */       byte[] decodedBytes = Base64.decodeBase64(licenseContent.getBytes());
/* 150 */       ByteArrayInputStream in = new ByteArrayInputStream(decodedBytes);
/* 151 */       DataInputStream dIn = new DataInputStream(in);
/* 152 */       int textLength = dIn.readInt();
/* 153 */       licenseText = new byte[textLength];
/* 154 */       dIn.read(licenseText);
/* 155 */       byte[] hash = new byte[dIn.available()];
/* 156 */       dIn.read(hash);
/*     */       
/*     */       try {
/* 159 */         Signature signature = Signature.getInstance("SHA1withDSA");
/* 160 */         signature.initVerify(PUBLIC_KEY);
/* 161 */         signature.update(licenseText);
/* 162 */         if (!signature.verify(hash)) {
/* 163 */           throw new LicenseException("Failed to verify the license.");
/*     */         }
/* 165 */       } catch (InvalidKeyException e) {
/*     */         
/* 167 */         throw new LicenseException(e);
/* 168 */       } catch (SignatureException e) {
/* 169 */         throw new LicenseException(e);
/* 170 */       } catch (NoSuchAlgorithmException e) {
/*     */         
/* 172 */         throw new LicenseException(e);
/*     */       } 
/* 174 */     } catch (IOException e) {
/*     */       
/* 176 */       throw new LicenseException(e);
/*     */     } 
/*     */     
/* 179 */     return licenseText;
/*     */   }
/*     */   
/*     */   private Properties loadLicenseConfiguration() {
/* 218 */       Properties props = new Properties();



                props.setProperty("keyVersion", "1600708331");
                props.setProperty("ContactName", "criitch@yandex.ru");
                props.setProperty("stash.LicenseTypeName", "COMMERCIAL");
                // props.setProperty("PurchaseDate", "2021-02-16");
                // props.setProperty("LicenseExpiryDate", "2021-03-18");
                props.setProperty("ContactEMail", "criitch@yandex.ru");
                props.setProperty("ServerID", "BHSU-H7B8-CYII-GD2C");
                props.setProperty("stash.DataCenter", "false");
                props.setProperty("DataCenter", "false");
                props.setProperty("licenseHash", "MCwCFDf0z5imd6W/I6QHhovJN5GVJqq+AhRVBJGbHYSYZBxU659kjnV4F3uhgA==");
                props.setProperty("Subscription", "true");
                // props.setProperty("MaintenanceExpiryDate", "2021-03-18");
                props.setProperty("stash.active", "true");
                props.setProperty("LicenseID", "LIDSEN-L10306231");
                props.setProperty("SEN", "SEN-L16822378");
                // props.setProperty("Organisation", "MyCorp");
                props.setProperty("CreationDate", "2021-02-16");
                // props.setProperty("stash.NumberOfUsers", "-1");
                props.setProperty("stash.Starter", "false");
                props.setProperty("licenseVersion", "2");
                props.setProperty("Description", "Bitbucket (Server): Cracked (Evaluation = false)");
                // props.setProperty("Evaluation", "true");



                props.setProperty("LicenseExpiryDate", "2099-01-01");
                props.setProperty("MaintenanceExpiryDate", "2099-01-01");
                props.setProperty("Evaluation", "false");
                props.setProperty("NumberOfUsers", "-1");
                props.setProperty("Organisation", "MyCorp");
                props.setProperty("PurchaseDate", "2017-01-01");
                props.setProperty("SEN", "SEN-L10306231");

/* 220 */       return props;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String removeWhiteSpaces(String licenseData) {
/* 196 */     if (licenseData == null || licenseData.length() == 0) {
/* 197 */       return licenseData;
/*     */     }
/*     */     
/* 200 */     char[] chars = licenseData.toCharArray();
/* 201 */     StringBuffer buf = new StringBuffer(chars.length);
/* 202 */     for (int i = 0; i < chars.length; i++) {
/* 203 */       if (!Character.isWhitespace(chars[i])) {
/* 204 */         buf.append(chars[i]);
/*     */       }
/*     */     } 
/*     */     
/* 208 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String packLicense(byte[] text, byte[] hash) throws LicenseException {
/*     */     try {
/* 218 */       ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 219 */       DataOutputStream dOut = new DataOutputStream(out);
/* 220 */       dOut.writeInt(text.length);
/* 221 */       dOut.write(text);
/* 222 */       dOut.write(hash);
/*     */       
/* 224 */       byte[] allData = out.toByteArray();
/* 225 */       String result = (new String(Base64.encodeBase64(allData))).trim();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 230 */       result = result + 'X' + "0" + '\002' + Integer.toString(result.length(), 31);
/* 231 */       result = split(result);
/* 232 */       return result;
/* 233 */     } catch (IOException e) {
/*     */       
/* 235 */       throw new LicenseException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String split(String licenseData) {
/* 243 */     if (licenseData == null || licenseData.length() == 0) {
/* 244 */       return licenseData;
/*     */     }
/*     */     
/* 247 */     char[] chars = licenseData.toCharArray();
/* 248 */     StringBuffer buf = new StringBuffer(chars.length + chars.length / 76);
/* 249 */     for (int i = 0; i < chars.length; i++) {
/* 250 */       buf.append(chars[i]);
/* 251 */       if (i > 0 && i % 76 == 0) {
/* 252 */         buf.append('\n');
/*     */       }
/*     */     } 
/*     */     
/* 256 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              C:\Trash\lib\atlassian-extras-decoder-v2-3.4.1.jar!\com\atlassian\extras\decoder\v2\Version2LicenseDecoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */