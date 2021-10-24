/*     */ package com.atlassian.extras.common.org.springframework.util;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Reader;
/*     */ import java.io.Writer;
/*     */ import java.util.Date;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Properties;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
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
/*     */ public class DefaultPropertiesPersister
/*     */ {
/*     */   public void load(Properties props, InputStream is) throws IOException {
/*  66 */     props.load(is);
/*     */   }
/*     */   
/*     */   public void load(Properties props, Reader reader) throws IOException {
/*  70 */     BufferedReader in = new BufferedReader(reader);
              Logger log = LoggerFactory.getLogger(DefaultPropertiesPersister.class);
/*     */     while (true) {
/*  72 */       String line = in.readLine();
/*  73 */       if (line == null) {
/*     */         return;
/*     */       }
/*  76 */       line = StringUtils.trimLeadingWhitespace(line);
/*  77 */       if (line.length() > 0) {
/*  78 */         char firstChar = line.charAt(0);
/*  79 */         if (firstChar != '#' && firstChar != '!') {
/*  80 */           while (endsWithContinuationMarker(line)) {
/*  81 */             String nextLine = in.readLine();
/*  82 */             line = line.substring(0, line.length() - 1);
/*  83 */             if (nextLine != null) {
/*  84 */               line = line + StringUtils.trimLeadingWhitespace(nextLine);
/*     */             }
/*     */           } 
/*  87 */           int separatorIndex = line.indexOf("=");
/*  88 */           if (separatorIndex == -1) {
/*  89 */             separatorIndex = line.indexOf(":");
/*     */           }
/*  91 */           String key = (separatorIndex != -1) ? line.substring(0, separatorIndex) : line;
/*  92 */           String value = (separatorIndex != -1) ? line.substring(separatorIndex + 1) : "";
/*  93 */           key = StringUtils.trimTrailingWhitespace(key);
/*  94 */           value = StringUtils.trimLeadingWhitespace(value);
/*  95 */           props.put(unescape(key), unescape(value));
                    log.warn("================ PROPS ===============\n");
                    log.warn("key: {}\nvalue: {}", unescape(key), unescape(value));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean endsWithContinuationMarker(String line) {
/* 102 */     boolean evenSlashCount = true;
/* 103 */     int index = line.length() - 1;
/* 104 */     while (index >= 0 && line.charAt(index) == '\\') {
/* 105 */       evenSlashCount = !evenSlashCount;
/* 106 */       index--;
/*     */     } 
/* 108 */     return !evenSlashCount;
/*     */   }
/*     */   
/*     */   protected String unescape(String str) {
/* 112 */     StringBuffer outBuffer = new StringBuffer(str.length());
/* 113 */     for (int index = 0; index < str.length(); ) {
/* 114 */       char c = str.charAt(index++);
/* 115 */       if (c == '\\') {
/* 116 */         c = str.charAt(index++);
/* 117 */         if (c == 't') {
/* 118 */           c = '\t';
/* 119 */         } else if (c == 'r') {
/* 120 */           c = '\r';
/* 121 */         } else if (c == 'n') {
/* 122 */           c = '\n';
/* 123 */         } else if (c == 'f') {
/* 124 */           c = '\f';
/*     */         } 
/*     */       } 
/* 127 */       outBuffer.append(c);
/*     */     } 
/* 129 */     return outBuffer.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public void store(Properties props, OutputStream os, String header) throws IOException {
/* 134 */     props.store(os, header);
/*     */   }
/*     */   
/*     */   public void store(Properties props, Writer writer, String header) throws IOException {
/* 138 */     BufferedWriter out = new BufferedWriter(writer);
/* 139 */     if (header != null) {
/* 140 */       out.write("#" + header);
/* 141 */       out.newLine();
/*     */     } 
/* 143 */     out.write("#" + new Date());
/* 144 */     out.newLine();
/* 145 */     for (Enumeration<Object> keys = props.keys(); keys.hasMoreElements(); ) {
/* 146 */       String key = (String)keys.nextElement();
/* 147 */       String val = props.getProperty(key);
/* 148 */       out.write(escape(key, true) + "=" + escape(val, false));
/* 149 */       out.newLine();
/*     */     } 
/* 151 */     out.flush();
/*     */   }
/*     */   
/*     */   protected String escape(String str, boolean isKey) {
/* 155 */     int len = str.length();
/* 156 */     StringBuffer outBuffer = new StringBuffer(len * 2);
/* 157 */     for (int index = 0; index < len; index++) {
/* 158 */       char c = str.charAt(index);
/* 159 */       switch (c) {
/*     */         case ' ':
/* 161 */           if (index == 0 || isKey) {
/* 162 */             outBuffer.append('\\');
/*     */           }
/* 164 */           outBuffer.append(' ');
/*     */           break;
/*     */         case '\\':
/* 167 */           outBuffer.append("\\\\");
/*     */           break;
/*     */         case '\t':
/* 170 */           outBuffer.append("\\t");
/*     */           break;
/*     */         case '\n':
/* 173 */           outBuffer.append("\\n");
/*     */           break;
/*     */         case '\r':
/* 176 */           outBuffer.append("\\r");
/*     */           break;
/*     */         case '\f':
/* 179 */           outBuffer.append("\\f");
/*     */           break;
/*     */         default:
/* 182 */           if ("=: \t\r\n\f#!".indexOf(c) != -1) {
/* 183 */             outBuffer.append('\\');
/*     */           }
/* 185 */           outBuffer.append(c); break;
/*     */       } 
/*     */     } 
/* 188 */     return outBuffer.toString();
/*     */   }
/*     */ }


/* Location:              C:\Trash\lib - Copy\atlassian-extras-common-3.4.1.jar!\com\atlassian\extras\common\org\springframewor\\util\DefaultPropertiesPersister.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */