package com.bsc.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 对象反射
 *
 * @version 1.0
 */
public class ReflectUtil 
{

	/**
	 * 获取该Object的所有字�?（不包括继承�?
	 * @param object 对象
	 * @return �?��字段
	 * @throws Exception
	 */
	public static String[] getDecarledFields(Object object) throws Exception{
		Field[] fields =  object.getClass().getDeclaredFields();
		String[] result = new String[fields.length];
		fields[0].getType();
		for(int i =0;i<fields.length;i++){
			result[i] = fields[i].getName().toString();
		}
		return result;
	}
	
	/**
	 * 获取字段类型
	 * @param field
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public static String getFieldType(String field,Object object)throws Exception{
		Field temp =  object.getClass().getDeclaredField(field);
		return temp.getType().getName();
	}
	
	/**
	 * 获取该字段的�?
	 * @param field 字段名称
	 * @param object 对象
	 * @return value(Object) 
	 * @throws Exception
	 */
	public static Object getFieldValue(String field,Object object) throws Exception{
		PropertyDescriptor pd = new PropertyDescriptor(field,object.getClass());
		Method method = pd.getReadMethod();
		return method.invoke(object);
	}
	
	/**
	 * 设置字段的�?
	 * @param field 字段名称
	 * @param object 对象
	 * @param values 对应的�?（可能参数有多个，按照set方法对应的顺序写�?
	 * @return Object 
	 * @throws Exception
	 */
	public static Object setFieldValue(String field,Object object,Object... values) throws Exception{
		PropertyDescriptor pd = new PropertyDescriptor(field,object.getClass());
		Method method = pd.getWriteMethod();
		return method.invoke(object, values);
	}
	
	
	
}
