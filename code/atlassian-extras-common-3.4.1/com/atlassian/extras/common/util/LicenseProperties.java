package com.atlassian.extras.common.util;

import java.util.Date;
import java.util.Map;

public interface LicenseProperties {
  String getProperty(String paramString);
  
  String getProperty(String paramString1, String paramString2);
  
  int getInt(String paramString, int paramInt);
  
  Date getDate(String paramString, Date paramDate);
  
  boolean getBoolean(String paramString);
  
  Map<String, String> getPropertiesEndingWith(String paramString);
}


/* Location:              C:\Trash\lib - Copy\atlassian-extras-common-3.4.1.jar!\com\atlassian\extras\commo\\util\LicenseProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */