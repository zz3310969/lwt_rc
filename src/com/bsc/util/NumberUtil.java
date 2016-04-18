package com.bsc.util;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据操作类<p>
 * 1、验证数据的格式<p>
 * 2、数据四舍五入
 * @author huojianping
 * @date 2010-09-18
 * @version 1.0
 */
public class NumberUtil 
{
	public static final String PATTEN="[0-9]";
	
	/**
	 * 公用方法。使用自己的正则表达式
	 * @param value 值
	 * @param regx 正则表达式
	 * @return
	 */
	public static boolean pattern(String value,String regx){
		Pattern  pattern = Pattern.compile(regx);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}
	
	/**
	 * 是否为数字
	 * @param value
	 * @return 是 true or 否 false
	 */
	public static boolean isNumber(String value){
		if(isInt(value)|| isFloat(value))
			return true;
		return false;
	}
	
	/**
	 * 是否为数字字符串
	 * @param value 字符串
	 * @param digits 字符串长度
	 * @return 是 true or 否 false 
	 */
	public static boolean isNumber(String value,int digits){
		return isInt(value,digits);
	}
	
	/**
	 * 是否是整数
	 * @param value 数字
	 * @param digits 位数
	 * @return
	 */
	public static boolean isInt(String value,int digits){
		return pattern(value,PATTEN+"{"+digits+"}$");
	}
	
	/**
	 * 是否为整数
	 * @param value
	 * @return
	 */
	public static boolean isInt(String value){
		return pattern(value,PATTEN+"+$");
	}
	
	/**
	 * 是否为浮点数
	 * @param value 必须有小数部分
	 * @return
	 */
	public static boolean isFloat(String value){
		return pattern(value,PATTEN+"*."+PATTEN+"+$");
	}
	
	/**
	 * 是否为浮点数
	 * @param value 
	 * @param number 整数位
	 * @param scale 小数位
	 * @return 
	 */
	public static boolean isFloat(String value,int number,int scale){
		return pattern(value,PATTEN+"{"+number+"}."+PATTEN+"{"+scale+"}$");
	}
	
	/**
	 * 是否为浮点数
	 * @param value 
	 * @param scale 小数位
	 * @return 
	 */
	public static boolean isFloat(String value,int scale){
		return pattern(value,PATTEN+"*."+PATTEN+"{"+scale+"}$");
	}
	
	/**
	 * 四舍六入，五凑偶，如果五前是偶数，但五后还有非零数，则还是要进位
	 * @param value 值 如果为null 则默认为0d
	 * @param scale 保留几位小数
	 * @return 返回四舍五入后的数值
	 */
	public static Double roundOfDoubleNew(Double value,int scale){
		if(scale<0) throw new IllegalArgumentException("scale 必须是大于或者等于0的整数");
		return new BigDecimal(ValidatorUtil.treatNullOnDouble(value)).setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 四舍五入 只要是大于5 就进位
	 * @param value 值 如果为null 则默认为0d
	 * @param scale 保留几位小数
	 * @return 返回四舍五入后的数值
	 */
	public static Double roundOfDouble(Double value,int scale){
		if(scale<0) throw new IllegalArgumentException("scale 必须是大于或者等于0的整数");
		BigDecimal bigDecimal =  new BigDecimal(ValidatorUtil.treatNullOnDouble(value).toString());
		BigDecimal one = new BigDecimal("1");
        return bigDecimal.divide(one,scale,BigDecimal.ROUND_HALF_UP).doubleValue(); 
	}
	
	/**
	 * 四舍五入 只要是大于5 就进位
	 * @param value 值 如果为null 则默认为0f
	 * @param scale 保留几位小数
	 * @return 返回四舍五入后的数值
	 */
	public static Float roundOfFloat(Float value,int scale){
		if(scale<0) throw new IllegalArgumentException("scale 必须是大于或者等于0的整数");
		BigDecimal bigDecimal =  new BigDecimal(ValidatorUtil.treatNullOnFloat(value).toString());
		BigDecimal one = new BigDecimal("1");
        return bigDecimal.divide(one,scale,BigDecimal.ROUND_HALF_UP).floatValue(); 
	}
	
	/**
	 * 四舍五入 只要是大于5 就进位
	 * @param value 值 如果为null 则默认为0l
	 * @param scale 保留几位小数
	 * @return 返回四舍五入后的数值
	 */
	public static Long roundOfLong(Long value,int scale){
		if(scale<0) throw new IllegalArgumentException("scale 必须是大于或者等于0的整数");
		BigDecimal bigDecimal =  new BigDecimal(ValidatorUtil.treatNullOnLong(value).toString());
		BigDecimal one = new BigDecimal("1");
        return bigDecimal.divide(one,scale,BigDecimal.ROUND_HALF_UP).longValue(); 
	}
	
	/**
	 * 获得double类型数据，如果非空则返回为0d
	 * @return
	 */
	public static double getDoubleValue(Object obj){
		if(!ValidatorUtil.isNullOnObject(obj) && isNumber(obj.toString())){
			return Double.parseDouble(obj.toString());
		}
		return 0d;
	}
	
	/**
	 * 获得int类型数据，如果非空则返回为0
	 * @return
	 */
	public static int getIntValue(Object obj){
		if(!ValidatorUtil.isNullOnObject(obj) && isNumber(obj.toString())){
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}
	
	/**
	 * 两个double数据比较
	 * @param d1 值
	 * @param d2 值
	 * @return 
	 * <p>d1==d2 return 0
	 * <p>d1<d2 return -1;
	 * <p>d1>d2 return 1; 
	 */
	public static int compare(double d1,double d2){
		if(d1==d2){
			return 0;
		}else if(d1<d2){
			return -1;
		}else {
			return 1;
		}
	}
	
	/**
	 * 两个int数据比较
	 * @param d1 值
	 * @param d2 值
	 * @return 
	 * <p>d1==d2 return 0
	 * <p>d1<d2 return -1;
	 * <p>d1>d2 return 1; 
	 */
	public static int compare(int d1,int d2){
		if(d1==d2){
			return 0;
		}else if(d1<d2){
			return -1;
		}else {
			return 1;
		}
	}
	
	/**
	 * 两个long数据比较
	 * @param d1 值
	 * @param d2 值
	 * @return 
	 * <p>d1==d2 return 0
	 * <p>d1<d2 return -1;
	 * <p>d1>d2 return 1; 
	 */
	public static int compare(long d1,long d2){
		if(d1==d2){
			return 0;
		}else if(d1<d2){
			return -1;
		}else {
			return 1;
		}
	}
	
	/**
	 * 判断是否为数字
	 * 用正则表达式
	 * @param str
	 * @return
	 */ 
	public static boolean isNumeric(String str){
		try {
			 Pattern pattern = Pattern.compile("[0-9]*");   
			 return pattern.matcher(str).matches();   
		} catch (Exception e) {
			return false;
		}
	}   
	
	
	/**
	 * 如果 num 为 null 则 num = 1
	 * 获取加<code>step</code>完整的字符串数字
	 * @param num 字符串 必须是能转化为整形的字符串
	 * @param com 需要几位的数字
	 * @param step 递增的长度
	 * @return eg: num = 1 , com= 4, step = 1, return 0002
	 */
	public static String getCompleteNum(String num,int com,int step){
		String resultStr = null;
		int result = 1;
		if(num==null)
			result = 1;
		else if(isInt(num)){
			result = getIntValue(num) + step;
		}else {
			return null;
		}
		resultStr = String.valueOf(result);
		for(int i = resultStr.length() ; i < com;i++ ){
			resultStr  = "0" + resultStr;
		}
		return resultStr;
	}
	
	/**
	 * 获取加1后完整的数字字符串
	 * @param num 字符串
	 * @param com 需要几位的数字<p> 
	 * 				see: getCompleteNum(String num,int com,int step)
	 * @return
	 */
	public static String getCompleteNum(String num,int com){
		return getCompleteNum(num,com,1);
	}
}
