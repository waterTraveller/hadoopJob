package com.topeastic.hadoop.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 字符串相关工具类
 * @author root
 *
 */
public class StringUtils {
	
	public static void main(String[] args) {
		System.out.println(strIsNotNull(null));
	}

	/**
	 * 检查字符串是否为空
	 * @param str 需要检查的字符串
	 * true 为不空 false为空或者null
	 * @return True Or False
	 */
	public static boolean strIsNotNull(String str){
		return str != null && !"".equals(str) ? true : false;
	}
	
	/**
	 * 将null转换为""输出
	 * @param str
	 * @return str Or ""
	 */
	public static String Null2Blank(String str){
		return str == null ? "" : str;
	}
	
	/**
	 * MD5 加密
	 */
	public static String getMD5Str(String str) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught!");
			System.exit(-1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}
		return md5StrBuff.toString();
	}
}	
