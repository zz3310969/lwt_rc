package com.bsc.util;

import java.util.regex.Pattern;

public class Verify {

	/**
	 * 验证字符串是否为空
	 * 
	 * @param Str
	 * @return
	 */
	public static boolean IsValidString(String Str) {

		if (Str == null || "".equals(Str))
			return false;

		return true;
	}

	/**
	 * 验证手机号码是否有效
	 * 
	 * @param Str
	 * @return
	 */
	public static boolean IsValidMobile(String Str) {

		Pattern pattern = Pattern.compile("^1(3|4|5|8|)\\d{9}$");
		return pattern.matcher(Str).matches();
	}

	/**
	 * 验证有效是否有效
	 * 
	 * @param Str
	 * @return
	 */
	public static boolean IsValidEmail(String Str) {

		Pattern pattern = Pattern
				.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
		return pattern.matcher(Str).matches();
	}

	/**
	 * 验证身份证信息是否有效
	 * 
	 * @param Str
	 * @return
	 */
	public static boolean IsValidIDCard(String Str) {

		Pattern pattern = Pattern.compile("^\\d{15}|\\d{18}$");
		return pattern.matcher(Str).matches();
	}

	/**
	 * 验证是否是有效的电话号码 格式 如
	 * ："XXX-XXXXXXX"、"XXXX-XXXXXXXX"、"XXX-XXXXXXX"、"XXX-XXXXXXXX"
	 * 、"XXXXXXX"和"XXXXXXXX"
	 * 
	 * @param Str
	 * @return
	 */
	public static boolean IsValidTelPhone(String Str) {

		Pattern pattern = Pattern
				.compile("^(\\(\\d{3,4}-)|\\d{3.4}-)?\\d{7,8}$");
		return pattern.matcher(Str).matches();
	}

	/**
	 * 验证是否是全数字
	 * 
	 * @param Str
	 * @return
	 */
	public static boolean IsAllNumber(String Str) {

		Pattern pattern = Pattern.compile("^[0-9]*$");
		return pattern.matcher(Str).matches();
	}

	/**
	 * 验证是否是全数字
	 * 
	 * @param Str
	 * @return
	 */
	public static boolean IsAllNumber(String Str, int length) {

		Pattern pattern = Pattern.compile("^\\d{" + length + "}$");
		return pattern.matcher(Str).matches();
	}

	/**
	 * 判断是否是汉字
	 * 
	 * @param Str
	 * @return
	 */
	public static boolean IsChinese(String Str) {

		Pattern pattern = Pattern.compile("^[\\u4e00-\\u9fa5]{0,}$");
		return pattern.matcher(Str).matches();
	}

	/**
	 * 判断是否是有效的URL地址
	 * 
	 * @param Str
	 * @return
	 */
	public static boolean IsValidUrl(String Str) {

		Pattern pattern = Pattern
				.compile("^http://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$");
		return pattern.matcher(Str).matches();
	}

	public static void main(String[] args) {
		boolean d = IsValidMobile("13581533260");
		
		System.out.print(d);
		
	}
}
