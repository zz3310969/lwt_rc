package com.bsc.util;

import java.util.List;

/**
 * 字符串操作类
 *
 */
public class StringUtil 
{
	
	/**
	 * 字符串截�?
	 * @param str
	 * @param sign 分割符号
	 * @return
	 */
	public static String[] split(String str, String sign){
		return str.split(sign);
	}

	/**
	 * 字符串截�? 默认分割符号 "," �?
	 * @param str
	 * @return 数组
	 */
	public static String[] split(String str){
		return split(str, ",");
	}

	/**
	 * 指定位置的字符替�?
	 * @param str 要替换的字符
	 * @param place 被替换字符的位置
	 * @param character 要替换的字符
	 * @return 
	 */
	public static String ToCharArray(String str, int place, char character){
		str = complement(str, place);  
		place = place - 1;
		char[] ch=str.toCharArray();
		ch[place]=character;
		return String.valueOf(ch);
	}

	/**
	 * 取字�?
	 * @param str 字符�?
	 * @param place 字符位置
	 * @return 返回指定位置的字�?
	 */
	public static String fgetc(String str, int place){
		str = complement(str, place); // 统一从这里进行处理，为了不出现错�?
		place = place - 1;
		char[] ch = str.toCharArray();
		return String.valueOf(ch[place]); 
	}

	/***
	 * 字符串截�? �?str == null 返回 �?�� �?size 长度�?数组
	 * @param str
	 * @param sign 分割符号
	 * @param size  数组 �?��长度
	 * @return 返回 �?�� �?size 长度�?数组
	 */
	public static String[] split(String str, String sign, int size){
		if(ValidatorUtil.isBlankOnString(str))
			return supplySurplus(new String[size], size, size);
		return supplySurplus(str.split(sign), size);
	}

	/**
	 *  对数组不足的补位�?如果�?null 则用 "" 代替
	 * @param arr
	 * @param size 数组长度
	 * @return
	 */
	private static String[] supplySurplus(String []arr , int size){
		String[] arrN = new String[size];
		if(arr == null || arr.length == 0){
			return supplySurplus(arrN, 0, size);
		}else if(arr.length > size){
			return supplySurplus(arr, arr.length, 0);
		}else {
			for (int i = 0; i < arr.length; i++) {
				arrN[i] = arr[i];
			}
			return supplySurplus(arrN, arr.length, arrN.length);
		}

	}
	
	/**
	 *  对数组不足的补位�?如果�?null 则用 "" 代替
	 * @param arr
	 * @param size  数组长度
	 * @param datum 已经使用了的长度
	 * @return
	 */
	public static String[] supplySurplus( String []arr, int datum , int size){
		for (int i = 0; i < datum ; i++) { // 对已经存在的字符串数据进行检�?
			if(null == arr[i]){ /// 如果�?null 则用 "" 代替
				arr[i] = "";
			}
		}
		for (; datum < size; ) {
			arr[datum ++ ] = "";
		}
		return arr;
	}

	/**
	 * 对以(sign)存储的字符串进行分割，返�?字符�?数组
	 * @param str 
	 * @param place 想要取的位置
	 * @param sign 分割符号
	 * @return [0]-�?��要的字符串�?[1]-该字符串前的字符串�?[2]-该字符串后的字符�?
	 */
	public static String[] split(String str ,int place, String sign){
		place = place - 1;
		String[] value = split(str, sign, place);
		int len = value.length;
		String[] result = new String []{"","",""};
		for (int i = 0; i < len ; i++) {
			String tem = value[i];
			if(place == i ){
				result[0] = tem;
			}else if(place > i ){
				result[1] += tem + sign;
			}else {
				result[2] += tem + sign;
			}
		}
		return result;
	}

	/**
	 * 获取�?�� 不小�?place 位置的字符串
	 * @param str
	 * @param place
	 * @return 不小�?place 位置的字符串
	 */
	public static String complement(String str, int place){
		if(str == null){
			return supply("", 0, place);
		}
		char[] ch = str.toCharArray();
		int leng = ch.length;
		if(leng < place){
			return supply(str, leng, place);
		}
		return str;

	}

	/**
	 * �?字符�?不足�?进行�?0 补位�?
	 * @param str
	 * @param begin
	 * @param end
	 * @return
	 */
	private static String supply(String str, int begin, int end){
		if(str == null){
			str = "";
		}
		for (int i = begin; i < end; i++) {
			str += "0";
		}
		return str;

	}
	
	
	/***
	 * �?a、b进行拼凑，用 , 间隔
	 * @param a 
	 * @param b 
	 * @return a,b
	 */
	public static String spell(String a, String b){
		return spell(a, b, ",");
	}
	
	/***
	 * �?a、b进行拼凑
	 * @param a
	 * @param b
	 * @param splt a-b间的区分�?
	 * @return a、b进行拼凑后的字符�?
	 */
	public static String spell(String a, String b, String splt){
		if(ValidatorUtil.isBlankOnString(a)){
			return ValidatorUtil.treatNullOnString(b);
		}
		if(ValidatorUtil.isBlankOnString(b)){
			return ValidatorUtil.treatNullOnString(a);
		}
		return a + splt + b;
	}
	/**
	 * 是否为空
	 * 
	 * @param str
	 * @return true 如果为空
	 */
	public static boolean isCommonEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

	/**
	 * 是否不为�?
	 * 
	 * @param str
	 * @return true 如果不为�?
	 */
	public static boolean isNotEmpty(String str) {
		return str != null && str.trim().length() != 0;
	}
 

	private static final String trueFlag = "1";

	/**
	 * 
	 * @param str
	 *            �?��判断的字符串
	 * @param pos
	 *            �?��判断的位 �?�?��
	 * @return true 如果该位�?
	 */
	public static boolean getBoolean(String str, int pos) {
		return getBoolean(str, pos, trueFlag);
	}

	/**
	 * @param str
	 *            �?��判断的字符串
	 * @param pos
	 *            �?��判断的位 �?�?��
	 * @param defaultTrueFlag
	 *            默认为真的标�?
	 * @return true 如果该位等于defaultTrueFlag
	 */
	public static boolean getBoolean(String str, int pos, String defaultTrueFlag) {

		if (defaultTrueFlag.equals(getChar(str, pos)))
			return true;

		return false;
	}

	/**
	 * 获取字符串指定位置的�?
	 * 
	 * @param str
	 *            STATE_FLAG
	 * @param index
	 *            位置 from 1 to str.length()
	 * @return null if get false
	 */
	public static String getChar(String str, int index) {
		if (isCommonEmpty(str))
			return null;
		if (index > str.length())
			return null;
		char[] strArray = str.toCharArray();
		return String.valueOf(strArray[index - 1]);
	}
	
	/**
	 * 去掉结尾�?symbol
	 * eg 1,2,3,4,5, return 1,2,3,4,5
	 * @param symbol
	 * @return
	 */
	public static String trimLastSymbol(String str,String symbol){
		if (str==null) return null;
		if (symbol==null) return str;
		if(str.endsWith(symbol)){
			return str.substring(0,str.length()-1);
		}
		return str;
	}
	
	/**
	 * 
	 * @param list
	 * @return
	 */
	public static String listToString(List list){
		if(list==null)return null;
		if(list.size()==0)return "";
		String result =  list.toString();
		return result.substring(1,result.length()-1);
	}
	
	/**
	 * 
	 * 单�?框字符转�?
	 */
	public static String travseStr(String str){
		if(str==null){
			return ",";
		}
		char[] ch = str.toCharArray();
		String result="";
		for(int i=0;i<ch.length;i++){
			if(ch[i]=='1'){
				result+=(i+1)+",";
			}
		}
		return result;
	}
	
	/**
	 * 查找两�?的最大�?�?
	 * @param max1
	 * @param max2
	 * @return
	 */
	public static Double takeMaxHistory(Double max1, Double max2){
		if(max1 == null){
			max1 = 0d ;
		}
		if(max2 == null ){
			max2 = 0d ;
		}
		if(max1 < max2){
			max1 = max2 ;
		}
		return Math.max(max1, max2) ;
	}
}
