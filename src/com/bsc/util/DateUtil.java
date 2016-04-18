package com.bsc.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期操作
 * @version 1.0
 * 1、当前日期
 * 2、字符串转化为日期格式
 * 3、获得年份
 * 4、获得几天后的日期
 * 5、两日期比较<p>
 * 6、获得当月的
 */
public class DateUtil 
{

	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
//	private static final Calendar RIGHT_NOW = Calendar.getInstance();
	
//	/**
//	 * 系统当前日期
//	 */
//	public static final Date NOW_DATE = RIGHT_NOW.getTime();
//	
//	/**
//	 * 系统当前年份
//	 */
//	public static final int NOW_YEAR = RIGHT_NOW.get(Calendar.YEAR);
	
	public static final Date getNowDate(){
		return Calendar.getInstance().getTime();
	}
	
	/**
	 * 系统当前年份
	 */
	public static final int getNowYear(){
		return Calendar.getInstance().YEAR;
	}
	
	/**
	 * 字符串转日期
	 * @param string yyyy-MM-dd
	 * @return 出错情况下返回当前日�?
	 */
	public static Date parseString(String string) {
		if(StringUtil.isCommonEmpty(string))
			return getNowDate();
		try {			
			return DATE_FORMAT.parse(string);
		} catch (Exception e) {
			return  getNowDate();
		}
	}
	
	/**
	 * 字符串转日期
	 * @param string yyyy-MM-dd
	 * @return 出错情况下返�?null
	 */
	public static Date parseStringWithNull(String string) {
		if(StringUtil.isCommonEmpty(string))
			return null;
		try {			
			return DATE_FORMAT.parse(string);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 日期转字符串
	 * @param date 
	 * @return if date is null return ""
	 */
	public static String parseDate(Date date) {
		if(date == null)
			return "";
		try {			
			return DATE_FORMAT.format(date);
		} catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * 当前日期减去年龄
	 * @param age 年龄（周岁）
	 * @return 日期 String
	 */
	public static String getBirthDate(Integer age){
		return DATE_FORMAT.format(treatDateTime(null,age,0,0));
	}
	
	/**
	 * 获得当前日期�?i>day</i>天的日期
	 * @param day 整形 可以是负�?
	 * @return 字符�?
	 */
	public static String getOtherDate(Integer day){
		return DATE_FORMAT.format(treatDateTime(null,0,0,day));
	}
	
	/**
	 * 根据生日计算年龄，包含年月日判断
	 * @param birthDay 生日
	 * @return 年龄
	 */
	public static int getAge(Date birthDay) {
		Calendar cal = Calendar.getInstance();
		if (birthDay == null || cal.before(birthDay)) {
			throw new IllegalArgumentException("The birthDay is Illegal!");
		}

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthDay);

		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				} else {
					// do nothing
				}
			} else {
				// monthNow>monthBirth
				age--;
			}
		} else {
			// monthNow<monthBirth
			// donothing
		}

		return age;
	}
	
	/**    
	 * 计算两日期的�?可返回相差多少年，多少月，多少天
	 * @param startDay �?��比较的时�?不能为空(null),�?��正确的日期格�?,如：2009-09-12   
	 * @param endDay 被比较的时间  为空(null)则为当前时间    
	 * @param stype 返回值类�?  0为多少天�?为多少个月，2为多少年    
	 * @return    
	 * 举例�?<p>
	 * compareDate("2009-09-12", null, 0);//比较�? <p>
	 * compareDate("2009-09-12", null, 1);//比较�? <p>
	 * compareDate("2009-09-12", null, 2);//比较�? 
	 */
	public static int compareDate(String startDay, String endDay, int stype) {
		int n = 0;
		String formatStyle = stype == 1 ? "yyyy-MM" : "yyyy-MM-dd";
		
		endDay = endDay == null ? DATE_FORMAT.format(getNowDate()) : endDay;

		DateFormat df = new SimpleDateFormat(formatStyle);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(df.parse(startDay));
			c2.setTime(df.parse(endDay));
		} catch (Exception e3) {
			System.out.println("wrong occured");
		}
			//List list = new ArrayList();     
		while (!c1.after(c2)) { // 循环对比，直到相等，n 就是�?��的结�?    
			//list.add(df.format(c1.getTime()));    // 这里可以把间隔的日期存到数组�?打印出来     
			n++;
			if (stype == 1) {
				c1.add(Calendar.MONTH, 1); // 比较月份，月�?1     
			} else {
				c1.add(Calendar.DATE, 1); // 比较天数，日�?1     
			}
		}
		n = n - 1;
		if (stype == 2) {
			n = (int) n / 365;
		}
		return n;
	}
	
	/**
	 * 计算日期
	 * @param date �?��处理的时�? nowDate if null
	 * @param year �?
	 * @param month �?
	 * @param day �?
	 * @return date 处理完成后的时间�?整数为增加时间，0 为不改变，负数为减少时间�?
	 */
	public static Date treatDateTime(Date date, int year, int month, int day){
		Calendar c = Calendar.getInstance();
		if(null == date){
			date =  getNowDate();
		}
		c.setTime(date);
		c.add(Calendar.YEAR, year);
		c.add(Calendar.MONTH, month);
		c.add(Calendar.DAY_OF_MONTH, day);
		date= c.getTime();
		return date;
	}
	
	/**
	 * 获得该日期的对应月的�?���?��
	 * @param date
	 * @return
	 */
	public static Date getLastdayOfMonth(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE));
		return c.getTime();
	}
	
	/**
	 * 获得该日期的对应月的�?���?��
	 * @param value  yyyy-MM-dd
	 * @return
	 */
	public static Date getLastdayOfMonth(String value){
		return getLastdayOfMonth(parseString(value));
	}
	
	public static String treatDateTime(String date){
		Date dd = getNowDate();
		try {
			dd = DATE_FORMAT.parse(date);
		} catch (ParseException e) {
		}
		return DATE_FORMAT.format(treatDateTime(dd, 0, 0, 1));
	}
}
