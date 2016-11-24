package com.sharebo.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.util.StringUtils;

public class MD5Util
{
  private static final char[] strDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
  private static final String encoding = "MD5";
  private static String characterEncoding;

  public static String encode(String password)
  {
    if (password == null) {
      return null;
    }
    try
    {
      MessageDigest messageDigest = MessageDigest.getInstance("MD5");

      if (StringUtils.hasText(characterEncoding))
        messageDigest.update(password.getBytes(characterEncoding));
      else {
        messageDigest.update(password.getBytes());
      }

      byte[] digest = messageDigest.digest();

      return getFormattedText(digest);
    } catch (NoSuchAlgorithmException e) {
      throw new SecurityException(e);
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }

  private static String getFormattedText(byte[] bytes) {
    StringBuilder buf = new StringBuilder(bytes.length * 2);

    for (int j = 0; j < bytes.length; j++) {
      buf.append(strDigits[(bytes[j] >> 4 & 0xF)]);
      buf.append(strDigits[(bytes[j] & 0xF)]);
    }
    return buf.toString();
  }
    public static String getEncoding() {
	return encoding;
	}
    private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname))
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes()));
			else
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes(charsetname)));
		} catch (Exception exception) {
		}
		return resultString;
	}
	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5","6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
}