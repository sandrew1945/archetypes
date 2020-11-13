#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.core.common;

import java.security.MessageDigest;

/**
 * 
 * Function    : MD5生成工具
 * @author     : Administrator
 * CreateDate  : 2014年5月26日
 * @version    :
 */
public class MD5Encrypt
{

	public static String MD5Encode(String sourceString)
	{
		String resultString = null;
		try
		{
			resultString = new String(sourceString);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byte2hexString(md.digest(resultString.getBytes()));
		}
		catch (Exception ex)
		{
		}
		return resultString;
	}

	public static final String byte2hexString(byte[] bytes)
	{
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++)
		{
			if (((int) bytes[i] & 0xff) < 0x10)
			{
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString();
	}
}
