/*     */ package com.atlassian.extras.common.org.springframework.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Set;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.TreeSet;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class StringUtils
/*     */ {
/*     */   private static final String FOLDER_SEPARATOR = "/";
/*     */   private static final String WINDOWS_FOLDER_SEPARATOR = "\\";
/*     */   private static final String TOP_PATH = "..";
/*     */   private static final String CURRENT_PATH = ".";
/*     */   
/*     */   public static boolean hasLength(String str) {
/*  78 */     return (str != null && str.length() > 0);
/*     */   }
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
/*     */   public static boolean hasText(String str) {
/*     */     int strLen;
/* 100 */     if (str == null || (strLen = str.length()) == 0) {
/* 101 */       return false;
/*     */     }
/* 103 */     for (int i = 0; i < strLen; i++) {
/* 104 */       if (!Character.isWhitespace(str.charAt(i))) {
/* 105 */         return true;
/*     */       }
/*     */     } 
/* 108 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String trimLeadingWhitespace(String str) {
/* 119 */     if (str.length() == 0) {
/* 120 */       return str;
/*     */     }
/* 122 */     StringBuffer buf = new StringBuffer(str);
/* 123 */     while (buf.length() > 0 && Character.isWhitespace(buf.charAt(0))) {
/* 124 */       buf.deleteCharAt(0);
/*     */     }
/* 126 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String trimTrailingWhitespace(String str) {
/* 137 */     if (str.length() == 0) {
/* 138 */       return str;
/*     */     }
/* 140 */     StringBuffer buf = new StringBuffer(str);
/* 141 */     while (buf.length() > 0 && Character.isWhitespace(buf.charAt(buf.length() - 1))) {
/* 142 */       buf.deleteCharAt(buf.length() - 1);
/*     */     }
/* 144 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int countOccurrencesOf(String str, String sub) {
/* 155 */     if (str == null || sub == null || str.length() == 0 || sub.length() == 0) {
/* 156 */       return 0;
/*     */     }
/* 158 */     int count = 0, pos = 0, idx = 0;
/* 159 */     while ((idx = str.indexOf(sub, pos)) != -1) {
/* 160 */       count++;
/* 161 */       pos = idx + sub.length();
/*     */     } 
/* 163 */     return count;
/*     */   }
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
/*     */   public static String replace(String inString, String oldPattern, String newPattern) {
/* 176 */     if (inString == null) {
/* 177 */       return null;
/*     */     }
/* 179 */     if (oldPattern == null || newPattern == null) {
/* 180 */       return inString;
/*     */     }
/*     */     
/* 183 */     StringBuffer sbuf = new StringBuffer();
/*     */     
/* 185 */     int pos = 0;
/* 186 */     int index = inString.indexOf(oldPattern);
/*     */     
/* 188 */     int patLen = oldPattern.length();
/* 189 */     while (index >= 0) {
/* 190 */       sbuf.append(inString.substring(pos, index));
/* 191 */       sbuf.append(newPattern);
/* 192 */       pos = index + patLen;
/* 193 */       index = inString.indexOf(oldPattern, pos);
/*     */     } 
/* 195 */     sbuf.append(inString.substring(pos));
/*     */ 
/*     */     
/* 198 */     return sbuf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String delete(String inString, String pattern) {
/* 207 */     return replace(inString, pattern, "");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String deleteAny(String inString, String chars) {
/* 217 */     if (inString == null || chars == null) {
/* 218 */       return inString;
/*     */     }
/* 220 */     StringBuffer out = new StringBuffer();
/* 221 */     for (int i = 0; i < inString.length(); i++) {
/* 222 */       char c = inString.charAt(i);
/* 223 */       if (chars.indexOf(c) == -1) {
/* 224 */         out.append(c);
/*     */       }
/*     */     } 
/* 227 */     return out.toString();
/*     */   }
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
/*     */   public static String[] tokenizeToStringArray(String str, String delimiters) {
/* 248 */     return tokenizeToStringArray(str, delimiters, true, true);
/*     */   }
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
/*     */   public static String[] tokenizeToStringArray(String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens) {
/* 273 */     StringTokenizer st = new StringTokenizer(str, delimiters);
/* 274 */     List<String> tokens = new ArrayList();
/* 275 */     while (st.hasMoreTokens()) {
/* 276 */       String token = st.nextToken();
/* 277 */       if (trimTokens) {
/* 278 */         token = token.trim();
/*     */       }
/* 280 */       if (!ignoreEmptyTokens || token.length() > 0) {
/* 281 */         tokens.add(token);
/*     */       }
/*     */     } 
/* 284 */     return tokens.<String>toArray(new String[tokens.size()]);
/*     */   }
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
/*     */   public static String[] delimitedListToStringArray(String str, String delimiter) {
/* 300 */     if (str == null) {
/* 301 */       return new String[0];
/*     */     }
/* 303 */     if (delimiter == null) {
/* 304 */       return new String[] { str };
/*     */     }
/*     */     
/* 307 */     List<String> result = new ArrayList();
/* 308 */     int pos = 0;
/* 309 */     int delPos = 0;
/* 310 */     while ((delPos = str.indexOf(delimiter, pos)) != -1) {
/* 311 */       result.add(str.substring(pos, delPos));
/* 312 */       pos = delPos + delimiter.length();
/*     */     } 
/* 314 */     if (str.length() > 0 && pos <= str.length())
/*     */     {
/* 316 */       result.add(str.substring(pos));
/*     */     }
/*     */     
/* 319 */     return result.<String>toArray(new String[result.size()]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String[] commaDelimitedListToStringArray(String str) {
/* 329 */     return delimitedListToStringArray(str, ",");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Set commaDelimitedListToSet(String str) {
/* 340 */     Set<String> set = new TreeSet();
/* 341 */     String[] tokens = commaDelimitedListToStringArray(str);
/* 342 */     for (int i = 0; i < tokens.length; i++) {
/* 343 */       set.add(tokens[i]);
/*     */     }
/* 345 */     return set;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String arrayToDelimitedString(Object[] arr, String delim) {
/* 357 */     if (arr == null) {
/* 358 */       return "null";
/*     */     }
/* 360 */     StringBuffer sb = new StringBuffer();
/* 361 */     for (int i = 0; i < arr.length; i++) {
/* 362 */       if (i > 0) {
/* 363 */         sb.append(delim);
/*     */       }
/* 365 */       sb.append(arr[i]);
/*     */     } 
/* 367 */     return sb.toString();
/*     */   }
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
/*     */   public static String collectionToDelimitedString(Collection c, String delim, String prefix, String suffix) {
/* 382 */     if (c == null) {
/* 383 */       return "null";
/*     */     }
/* 385 */     StringBuffer sb = new StringBuffer();
/* 386 */     Iterator<String> it = c.iterator();
/* 387 */     int i = 0;
/* 388 */     while (it.hasNext()) {
/* 389 */       if (i++ > 0) {
/* 390 */         sb.append(delim);
/*     */       }
/* 392 */       sb.append(prefix + it.next() + suffix);
/*     */     } 
/* 394 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String collectionToDelimitedString(Collection coll, String delim) {
/* 405 */     return collectionToDelimitedString(coll, delim, "", "");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String arrayToCommaDelimitedString(Object[] arr) {
/* 416 */     return arrayToDelimitedString(arr, ",");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String collectionToCommaDelimitedString(Collection coll) {
/* 426 */     return collectionToDelimitedString(coll, ",");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String[] addStringToArray(String[] arr, String str) {
/* 438 */     String[] newArr = new String[arr.length + 1];
/* 439 */     System.arraycopy(arr, 0, newArr, 0, arr.length);
/* 440 */     newArr[arr.length] = str;
/* 441 */     return newArr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String[] sortStringArray(String[] source) {
/* 451 */     if (source == null) {
/* 452 */       return new String[0];
/*     */     }
/* 454 */     Arrays.sort((Object[])source);
/* 455 */     return source;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String unqualify(String qualifiedName) {
/* 466 */     return unqualify(qualifiedName, '.');
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String unqualify(String qualifiedName, char separator) {
/* 477 */     return qualifiedName.substring(qualifiedName.lastIndexOf(separator) + 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String capitalize(String str) {
/* 489 */     return changeFirstCharacterCase(true, str);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String uncapitalize(String str) {
/* 501 */     return changeFirstCharacterCase(false, str);
/*     */   }
/*     */   
/*     */   private static String changeFirstCharacterCase(boolean capitalize, String str) {
/*     */     int strLen;
/* 506 */     if (str == null || (strLen = str.length()) == 0) {
/* 507 */       return str;
/*     */     }
/* 509 */     StringBuffer buf = new StringBuffer(strLen);
/* 510 */     if (capitalize) {
/* 511 */       buf.append(Character.toUpperCase(str.charAt(0)));
/*     */     } else {
/* 513 */       buf.append(Character.toLowerCase(str.charAt(0)));
/*     */     } 
/* 515 */     buf.append(str.substring(1));
/* 516 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getFilename(String path) {
/* 527 */     int separatorIndex = path.lastIndexOf("/");
/* 528 */     return (separatorIndex != -1) ? path.substring(separatorIndex + 1) : path;
/*     */   }
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
/*     */   public static String applyRelativePath(String path, String relativePath) {
/* 541 */     int separatorIndex = path.lastIndexOf("/");
/* 542 */     if (separatorIndex != -1) {
/* 543 */       String newPath = path.substring(0, separatorIndex);
/* 544 */       if (!relativePath.startsWith("/")) {
/* 545 */         newPath = newPath + "/";
/*     */       }
/* 547 */       return newPath + relativePath;
/*     */     } 
/* 549 */     return relativePath;
/*     */   }
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
/*     */   public static String cleanPath(String path) {
/* 563 */     String pathToUse = replace(path, "\\", "/");
/* 564 */     String[] pathArray = delimitedListToStringArray(pathToUse, "/");
/* 565 */     List<String> pathElements = new LinkedList();
/* 566 */     int tops = 0;
/* 567 */     for (int i = pathArray.length - 1; i >= 0; i--) {
/* 568 */       if (!".".equals(pathArray[i]))
/*     */       {
/* 570 */         if ("..".equals(pathArray[i])) {
/* 571 */           tops++;
/*     */         }
/* 573 */         else if (tops > 0) {
/* 574 */           tops--;
/*     */         } else {
/* 576 */           pathElements.add(0, pathArray[i]);
/*     */         } 
/*     */       }
/*     */     } 
/* 580 */     return collectionToDelimitedString(pathElements, "/");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean pathEquals(String path1, String path2) {
/* 591 */     return cleanPath(path1).equals(cleanPath(path2));
/*     */   }
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
/*     */   public static Locale parseLocaleString(String localeString) {
/* 604 */     String[] parts = tokenizeToStringArray(localeString, "_ ", false, false);
/* 605 */     String language = (parts.length > 0) ? parts[0] : "";
/* 606 */     String country = (parts.length > 1) ? parts[1] : "";
/* 607 */     String variant = (parts.length > 2) ? parts[2] : "";
/* 608 */     return (language.length() > 0) ? new Locale(language, country, variant) : null;
/*     */   }
/*     */ }


/* Location:              C:\Trash\lib - Copy\atlassian-extras-common-3.4.1.jar!\com\atlassian\extras\common\org\springframewor\\util\StringUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */