import com.atlassian.extras.common.LicenseException;
import com.atlassian.extras.common.org.springframework.util.DefaultPropertiesPersister;
import com.atlassian.extras.decoder.api.AbstractLicenseDecoder;
import com.atlassian.extras.decoder.v2.Version2LicenseDecoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;


import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Properties;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import org.apache.commons.codec.binary.Base64;

public class Version2LicenseDecoder
  extends AbstractLicenseDecoder
{
  public static final int VERSION_NUMBER_1 = 1;
  public static final int VERSION_NUMBER_2 = 2;
  public static final int VERSION_LENGTH = 3;
  public static final int ENCODED_LICENSE_LENGTH_BASE = 31;
  public static final byte[] LICENSE_PREFIX = { 13, 14, 12, 10, 15 };

  public static final char SEPARATOR = 'X';
  private static final PublicKey PUBLIC_KEY;
  private static final int ENCODED_LICENSE_LINE_LENGTH = 76;


  static  {
    try {
      String str = "MIIBuDCCASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLCT7up1/63xhv4O1fnxqimFQ8E+4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmUr7v6OuqC+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKTxvqhRkImog9/hWuWfBpKLZl6Ae1UlZAFMO/7PSSoDgYUAAoGBAIvfweZvmGo5otwawI3no7Udanxal3hX2haw962KL/nHQrnC4FG2PvUFf34OecSK1KtHDPQoSQ+DHrfdf6vKUJphw0Kn3gXm4LS8VK/LrY7on/wh2iUobS2XlhuIqEc5mLAUu9Hd+1qxsQkQ50d0lzKrnDqPsM0WA9htkdJJw2nS";

      KeyFactory keyFactory = KeyFactory.getInstance("DSA");
      PUBLIC_KEY = keyFactory.generatePublic(new X509EncodedKeySpec(Base64.decodeBase64("MIIBuDCCASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLCT7up1/63xhv4O1fnxqimFQ8E+4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmUr7v6OuqC+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKTxvqhRkImog9/hWuWfBpKLZl6Ae1UlZAFMO/7PSSoDgYUAAoGBAIvfweZvmGo5otwawI3no7Udanxal3hX2haw962KL/nHQrnC4FG2PvUFf34OecSK1KtHDPQoSQ+DHrfdf6vKUJphw0Kn3gXm4LS8VK/LrY7on/wh2iUobS2XlhuIqEc5mLAUu9Hd+1qxsQkQ50d0lzKrnDqPsM0WA9htkdJJw2nS".getBytes())));
    }
    catch (NoSuchAlgorithmException noSuchAlgorithmException) {

      throw new Error(noSuchAlgorithmException);
    }
    catch (InvalidKeySpecException invalidKeySpecException) {

      throw new Error(invalidKeySpecException);
    }
  }

  public boolean canDecode(String paramString) {
    paramString = removeWhiteSpaces(paramString);

    int i = paramString.lastIndexOf('X');
    if (i == -1 || i + 3 >= paramString.length())
    {
      return false;
    }


    try {
      int j = Integer.parseInt(paramString.substring(i + 1, i + 3));
      if (j != 1 && j != 2)
      {
        return false;
      }

      String str = paramString.substring(i + 3);
      int k = Integer.valueOf(str, 31).intValue();
      if (i != k)
      {
        return false;
      }

      return true;
    }
    catch (NumberFormatException numberFormatException) {

      return false;
    }
  }

  public Properties doDecode(String paramString) {
    String str = getLicenseContent(removeWhiteSpaces(paramString));
    byte[] arrayOfByte = checkAndGetLicenseText(str);
    Reader reader = unzipText(arrayOfByte);

    return loadLicenseConfiguration(reader);
  }

  protected int getLicenseVersion() { return 2; }

  private Reader unzipText(byte[] paramArrayOfByte) {
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
    byteArrayInputStream.skip(LICENSE_PREFIX.length);
    InflaterInputStream inflaterInputStream = new InflaterInputStream(byteArrayInputStream, new Inflater());

    try {
      return new InputStreamReader(inflaterInputStream, "UTF-8");
    }
    catch (UnsupportedEncodingException unsupportedEncodingException) {

      throw new LicenseException(unsupportedEncodingException);
    }
  }

  private String getLicenseContent(String paramString) {
    String str = paramString.substring(paramString.lastIndexOf('X') + 3);

    try {
      int i = Integer.valueOf(str, 31).intValue();
      return paramString.substring(0, i);
    }
    catch (NumberFormatException numberFormatException) {

      throw new LicenseException("Could NOT decode license length <" + str + ">", numberFormatException);
    }
  }

  private byte[] checkAndGetLicenseText(String paramString) {
    byte[] arrayOfByte;
    try {
      byte[] arrayOfByte1 = Base64.decodeBase64(paramString.getBytes());
      ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(arrayOfByte1);
      DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
      int i = dataInputStream.readInt();
      arrayOfByte = new byte[i];
      dataInputStream.read(arrayOfByte);
      byte[] arrayOfByte2 = new byte[dataInputStream.available()];
      dataInputStream.read(arrayOfByte2);


    }
    catch (IOException iOException) {

      throw new LicenseException(iOException);
    }

    return arrayOfByte;
  }

  private Properties loadLicenseConfiguration(Reader paramReader) {
    try {
      Properties properties = new Properties();
      (new DefaultPropertiesPersister()).load(properties, paramReader);
      return properties;
    }
    catch (IOException iOException) {

      throw new LicenseException("Could NOT load properties from reader", iOException);
    }
  }

  private static String removeWhiteSpaces(String paramString) {
    if (paramString == null || paramString.length() == 0)
    {
      return paramString;
    }

    char[] arrayOfChar = paramString.toCharArray();
    StringBuffer stringBuffer = new StringBuffer(arrayOfChar.length);
    for (byte b = 0; b < arrayOfChar.length; b++) {

      if (!Character.isWhitespace(arrayOfChar[b]))
      {
        stringBuffer.append(arrayOfChar[b]);
      }
    }

    return stringBuffer.toString();
  }

  public static String packLicense(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2) throws LicenseException {
    try {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
      dataOutputStream.writeInt(paramArrayOfByte1.length);
      dataOutputStream.write(paramArrayOfByte1);
      dataOutputStream.write(paramArrayOfByte2);

      byte[] arrayOfByte = byteArrayOutputStream.toByteArray();
      String str = (new String(Base64.encodeBase64(arrayOfByte))).trim();

      str = str + 'X' + "0" + '\002' + Integer.toString(str.length(), 31);
      return split(str);
    }
    catch (IOException iOException) {

      throw new LicenseException(iOException);
    }
  }

  private static String split(String paramString) {
    if (paramString == null || paramString.length() == 0)
    {
      return paramString;
    }

    char[] arrayOfChar = paramString.toCharArray();
    StringBuffer stringBuffer = new StringBuffer(arrayOfChar.length + arrayOfChar.length / 76);
    for (byte b = 0; b < arrayOfChar.length; b++) {

      stringBuffer.append(arrayOfChar[b]);
      if (b && b % 76 == 0)
      {
        stringBuffer.append('\n');
      }
    }

    return stringBuffer.toString();
  }

}
