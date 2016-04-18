package com.bsc.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 验证
 * @author huojianping
 *
 */
public class ValidatorUtil 
{
	public static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  //短日期格�?
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //完整日期格式
	public static DecimalFormat df = new DecimalFormat("#.#");


	///--------- isNullOnValue --- �?�� value 是否�? null  --------------
	
	/**
	 * �?�� String 是否�?null
	 * @param value
	 * @return null 返回 true；其他返�?false
	 */
	public static boolean isNullOnString (String value){
		if(value == null)
			return true;	
		return false;
	}
	
	/**
	 * �?�� String 的数据是否为 null �?"";
	 * @param value
	 * @return null, "" , 长度等于 0 返回 true；其他返�?false
	 */
	public static boolean isBlankOnString (String value){
		if(value == null || value.trim().length() == 0 || "".equals(value.trim())){
			return true;
		}
		return false;
	}

	/**
	 * �?�� Integer 是否�?null
	 * @param value 
	 * @return null 返回 true；其他返�?false
	 */
	public static boolean isNullOnIntger(Integer value){
		if(value == null)
			return true;	
		return false;
	}

	/**
	 * �?�� Long 是否�?null
	 * @param value 
	 * @return null 返回 true；其他返�?false
	 */
	public static boolean isNullOnLong(Long value){
		if(value == null)
			return true;	
		return false;
	}

	/**
	 * �?�� Double 是否�?null
	 * @param value 
	 * @return null 返回 true；其他返�?false
	 */
	public static boolean isNullOnDouble(Double value) {
		if(value == null)
			return true;	
		return false;
	}

	/**
	 * �?�� Object 是否�?null (通用)
	 * @param value 
	 * @return null 返回 true；其他返�?false
	 */
	public static boolean isNullOnObject(Object value) {
		if(value == null)
			return true;	
		return false;
	}

	/**
	 * �?�� Date 时间 是否�?null 
	 * @param date 
	 * @return null 返回 true；其他返�?false
	 */
	public static boolean isNullOnDate(Date date) {
		if(date == null)
			return true;	
		return false;
	}

	/**
	 * �?�� 字符串是否是 标准�?时间格式 
	 * @param date 2008-08-08
	 * @return null 返回 true；其他返�?false
	 */
	public static boolean isNullOnDate(String date) {
		try {
			sdf.parse(date);
		} catch (ParseException e) {
//			e.printStackTrace();
			return false;
		}
			return true;	
	}
	
	
	///--------- treatNullOnValue --- 处理  null --------------

	/**
	 * 处理 String 中的 中得�?非空�?字符�?
	 * @param val
	 * @return null 返回 "" ，其他返�?String
	 */
	public static String treatNullOnString (String val){
		if(val == null)
			return "";	
		return val;
	}

	/**
	 * 处理 Integer 中得�?非空�?整型�?
	 * @param val 
	 * @return null 返回 0；其他返�?整型�?
	 */
	public static Integer treatNullOnIntger(Integer val){
		if(val == null)
			val = 0;	
		return val;
	}
	
	/**
	 * 只有数字才返回对应的String类型，其他的返回 "0"
	 * @param obj
	 * @return
	 */
	public static String treatNotNumber(Object obj){
		if(!isNullOnObject(obj) && NumberUtil.isNumber(obj.toString())){
			return obj.toString();
		}else {
			return "0";
		}
	}
	
	/**
	 * 返回int类型 
	 * @param obj
	 * @return
	 */
	public static Integer getIntNotNum(Object obj){
		return Integer.parseInt(treatNotNumber(obj));
	}

	/**
	 * 返回double类型
	 * @param obj
	 * @return
	 */
	public static Double getDouNotNum(Object obj){
		return Double.parseDouble(treatNotNumber(obj));
	}
	/**
	 * 处理 Long 中得�?非空�?长整型�?
	 * @param val 
	 * @return null 返回 0l；其他返�?长整型�?
	 */
	public static Long treatNullOnLong(Long val){
		if(val == null)
			val = 0l;	
		return val;

	}

	/**
	 * �?Double 中得�?非空�?Double�?
	 * @param val 
	 * @return null 返回 0d；其他返�?Double�?
	 */
	public static Double treatNullOnDouble(Double val) {
		if (val == null)
			return 0d;
		return val;
	}
	
	/**
	 * �?Double 中得�?非空�?Double�?
	 * @param val 
	 * @return null 返回 0d；其他返�?Double�?
	 */
	public static Float treatNullOnFloat(Float val) {
		if (val == null)
			return 0f;
		return val;
	}
	
	
	///--------- valueOf ---�? Integer、Long�?Double 转换�? String  -------------

	/**
	 * �?Integer 转化�?String
	 * @param val
	 * @return null 返回 "" ，其他返�?String
	 */
	public static String valueOfInteger (Integer val){
		if(isNullOnIntger(val))
			return "";	
		return String.valueOf(val);
	}

	/**
	 * �?Long 转化 �?String
	 * @param val
	 * @return null 返回 "" ，其他返回String
	 */
	public static String valueOfLong (Long val){
		if(isNullOnLong(val))
			return "";	
		return String.valueOf(val);
	}
	
	/**
	 * �?Double 转化 �?String
	 * @param val
	 * @return null 返回 ""，其他返回String
	 */
	public static String valueOfDouble(Double val){
		if(isNullOnDouble(val))
			return "";	
		return String.valueOf(val);
	}
	/**
	 * �?null 进行处理，返�?""
	 * @param obj
	 * @return
	 */
	public static String valueOfS(Object obj){
		if(null == obj){
			return "";
		}
		return obj.toString();
	}
	
	/**
	 * �?String 转化�?Integer �?�?null 
	 * @param value
	 * @return null 返回 null �?其他返回 Integer
	 */
	public static Integer parseIntByString(String value){
		if(isBlankOnString(value))
			return null;
		return Integer.parseInt(value.trim());
	}
	
	/***
	 * �?String 转化�?Integer 
	 * @param value
	 * @return int if value is int ,else 1.
	 */
	public static Integer parseIntByString2(String value){
		if(isBlankOnString(value))
			return 1;
		return Integer.parseInt(value.trim());
	}

	/**
	 * �?String 转化�?Integer 
	 * @param value
	 * @return null 返回 0 �?其他返回 Integer
	 */
	public static Integer parseIntByString0(String value){
		if(isBlankOnString(value))
			return 0;
		return Integer.parseInt(value.trim());
	}
	
	/**
	 * �?存储为Double �?String 转化�?Integer 
	 * @param value
	 * @return null�?.0 返回 null ，小�?1 返回 1 其他返回 Integer
	 */
	public static Integer parseIntByString1(String value){
		if("NULL".equals(value ) || "null".equals(value)){
			return null;
		}
		Double d = parseDoubleByString(value);
		if(d == null || "0.0".equals(value)){
			return null;
		}
		if(d < 1){
			return 1;
		}
		return d.intValue();
	}
	
	/**
	 * �?String 转化�?Double �?�?�?null 
	 * @param value
	 * @return null 返回 null �?其他返回 Double
	 */
	public static Double parseDoubleByString(String value){
		if(isBlankOnString(value))
			return null;
		return Double.parseDouble(value);
	}
	
	/**
	 * �?String 转化�?Double �?
	 * @param value
	 * @return null 返回 0d �?其他返回 Double
	 */
	public static Double parseDoubleByString0(String value){
		if(isBlankOnString(value))
			return 0d;
		return Double.parseDouble(value);
	}
	/**
	 * �?int 转化�?Double �?
	 * @param value
	 * @return null 返回 0d �?其他返回 Double
	 */
	public static Double parseDoubleByString0(Integer value){
		if(isNullOnIntger(value))
			return 0d;
		return value.doubleValue();
	}

	///------------ dateFormat -------------
	
	/**
	 * 给的时间是null, 则返�?"" 
	 * @return 2008-11-03 or ""
	 */
	public static String dateFormatOnBlank(Date date){
		if(isNullOnDate(date))
			return "";
		return sdf.format(date);
	}
	
	/**
	 * 给的时间是null, 则返�?<当前系统时间>
	 * @return 2008-11-03 or <当前系统时间>
	 */
	public static String dateFormat(Date date){
		if(isNullOnDate(date))
			date = DateUtil.getNowDate();
		return sdf.format(date);
	}
	
	/**
	 * 处理时间,返回长度 10  (2008-11-03)
	 * @param reg
	 * @return if null return "" , 其他返回长度 10  (2008-11-03)
	 */
	public static String dateFormat(Object reg){
		String result = "";
		if(null == reg){
			return result ;
		}
		result = String.valueOf(reg);
		if(result.length() > 10){
			result = result.substring(0, 10);
		}
		return result ;
	}
	 
	/**
	 * 判断当前输入的是不是为空
	 * @param blank
	 * @return
	 * true：为空格
	 * false：不是空�?
	 */
	public static boolean addListenerByBlank (String blank){
		boolean falg = false;
		if("".equals(blank.trim())){
			if(blank.length() == 1){//对删除的判断管理
				falg =  true;
			}
		}
		return falg;
	}
	
	/**
	 * 比较两个字符串是否形�?
	 * @param firststr
	 * @param secondstr
	 * @param equalsnull if <i>true</i> then (firststr==null && secondstr==null) will return true ,else return false;
	 * @return if firststr==null && secondstr==null return true;
	 */
	public static boolean equals(String firststr, String secondstr,boolean equalsnull){
		if(firststr!=null){
			return firststr.equals(secondstr);
		}else {
			if(firststr==null&&secondstr==null&&equalsnull)return true;
		}
		return false;
	}

	/**
	 * 截取字符串指导的第几位，判断其�?是否大于零，如果大于零返回指定�?�?
	 * 如果不大于零则返�?
	 * @param str
	 * @param index begin 0
	 * @return
	 */
	public static int substr(String str,int index){
		String strnow=getchar(str,index);
		int intstr=Integer.parseInt(strnow);
		if(intstr>0)
			return intstr;
		else 
			return 0;
	}
	
	/**
	 * 指定位置字符�?
	 * @param str
	 * @param index  begin 0
	 * @return
	 */
	public static String getchar(String str,int index){
		return str.substring(index, index+1);
	}
	
}
