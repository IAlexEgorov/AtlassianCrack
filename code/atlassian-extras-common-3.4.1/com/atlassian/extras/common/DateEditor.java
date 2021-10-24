/*     */ package com.atlassian.extras.common;
/*     */ 
/*     */ import java.text.DateFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.TimeZone;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class DateEditor
/*     */ {
/*     */   private static final String DATE_FORMAT = "yyyy-MM-dd";
/*     */   static final String PERIOD_PREFIX = "P";
/*     */   private static final long MILLIS_IN_HOUR = 3600000L;
/*     */   public static final String UNLIMITED = "unlimited";
/*  24 */   private static final Pattern DURATION_PATTERN = Pattern.compile("Duration\\:([0-9]+)");
/*     */   
/*  26 */   private static final Pattern PERIOD_PATTERN = Pattern.compile("P([0-9]+)H");
/*     */   
/*  28 */   private static final Pattern DATE_IN_MILLIS_PATTERN = Pattern.compile("[0-9]+");
/*     */   
/*  30 */   private static final Pattern ISO_DATE_PATTERN = Pattern.compile("^([1-2][0-9]{3}-(0[1-9]|1[0-2])-([0-2][0-9]|3[0-1]))(\\s|[T])?.*");
/*     */   
/*  32 */   static final TimeZone TIME_ZONE = TimeZone.getTimeZone("Australia/Sydney");
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
/*     */   public static Date getDate(String dateString) {
/*  51 */     if (dateString == null || dateString.length() == 0) {
/*  52 */       throw new DateParsingException(dateString);
/*     */     }
/*     */ 
/*     */     
/*  56 */     if (dateString.equals("unlimited")) {
/*  57 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  61 */     Matcher durationMatcher = DURATION_PATTERN.matcher(dateString);
/*  62 */     if (durationMatcher.matches()) {
/*  63 */       long dateInMillis = System.currentTimeMillis() + Long.parseLong(durationMatcher.group(1));
/*  64 */       return new Date(dateInMillis);
/*     */     } 
/*     */ 
/*     */     
/*  68 */     Matcher periodMatcher = PERIOD_PATTERN.matcher(dateString);
/*  69 */     if (periodMatcher.matches()) {
/*     */       
/*  71 */       long dateInMillis = System.currentTimeMillis() + Integer.parseInt(periodMatcher.group(1)) * 3600000L;
/*     */       
/*  73 */       return new Date(dateInMillis);
/*     */     } 
/*     */ 
/*     */     
/*  77 */     Matcher dateInMillisMatcher = DATE_IN_MILLIS_PATTERN.matcher(dateString);
/*  78 */     if (dateInMillisMatcher.matches()) {
/*  79 */       return new Date(Long.parseLong(dateString));
/*     */     }
/*     */ 
/*     */     
/*  83 */     Matcher isoDateMatcher = ISO_DATE_PATTERN.matcher(dateString);
/*  84 */     if (isoDateMatcher.matches()) {
/*     */       try {
/*  86 */         return getDateFormat().parse(isoDateMatcher.group(1));
/*  87 */       } catch (ParseException e) {
/*     */ 
/*     */         
/*  90 */         throw new DateParsingException(dateString, e);
/*     */       } 
/*     */     }
/*     */     
/*  94 */     throw new DateParsingException(dateString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getString(Date date) {
/* 104 */     return (date != null) ? getDateFormat().format(date) : "unlimited";
/*     */   }
/*     */   
/*     */   private static DateFormat getDateFormat() {
/* 108 */     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
/* 109 */     dateFormat.setTimeZone(TIME_ZONE);
/* 110 */     return dateFormat;
/*     */   }
/*     */ }


/* Location:              C:\Trash\lib - Copy\atlassian-extras-common-3.4.1.jar!\com\atlassian\extras\common\DateEditor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */