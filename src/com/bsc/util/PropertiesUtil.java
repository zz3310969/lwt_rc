package com.bsc.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * 资源文件�?
 * @version 1.0
 */
public class PropertiesUtil 
{
	private static Logger log = Logger.getLogger(PropertiesUtil.class);
	
	/**
	 * 获取对应filepath属�?�?
	 * @param key key
	 * @param filepath 
	 * <p> 如果路径不包�?<strong>:</strong>  则认为是相对路径 Root/Directory/.../File格式的为：Directory/.../File
	 * <p> 如果路径包含 <strong>:</strong>   则认为是绝对路径 D:\\test\\.....\\File
	 * @return value
	 */
	public static String getProperties(String key,String filepath){
		Properties properties = new Properties();
		try {
			properties.load(FileUtil.getFileInputStream(FileUtil.getFile(filepath)));
			return properties.getProperty(key);
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}
	
	/**
	 * 获取对应inputStream属�?�?
	 * @param key 
	 * @param inputStream 
	 * @return
	 */
	public static String getProperties(String key,InputStream inputStream){
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
			return properties.getProperty(key);
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}

	/**
	 * 设置对应filepath属�?�?
	 * @param key key
	 * @param value set的�?
	 * @param filepath 文件路径 
	 * <p> 如果路径不包�?<strong>:</strong>  则认为是相对路径 Root/Directory/.../File格式的为：Directory/.../File
	 * <p> 如果路径包含 <strong>:</strong>   则认为是绝对路径 D:\\test\\.....\\File
	 * @return true or false
	 */
	public static boolean putProperties(String key,String value,String filepath){
		Properties properties = new Properties();
		boolean result = true;
		try {
			File file = FileUtil.getFile(filepath);
			properties.load(FileUtil.getFileInputStream(file));
			properties.setProperty(key,value);
			OutputStream fos = new FileOutputStream(file.getPath());
			properties.store(fos,"");
			fos.close();
		} catch (Exception e) {
			log.error(e);
			result = false;
		}
		return result;
	}
	
	/**
	 * 提供对一个资源文件整体设�?
	 * @param object 对象
	 * @param filepath 文件路径
	 * <p> 如果路径不包�?<strong>:</strong>  则认为是相对路径 Root/Directory/.../File格式的为：Directory/.../File
	 * <p> 如果路径包含 <strong>:</strong>   则认为是绝对路径 D:\\test\\.....\\File
	 * @return
	 */
	public static boolean putProperties(Object object,String filepath){
		Properties properties = new Properties();
		boolean result = true;
		try {
			File file = FileUtil.getFile(filepath);
			properties.load(FileUtil.getFileInputStream(file));
			OutputStream fos = new FileOutputStream(file.getPath());
			String[] fields = ReflectUtil.getDecarledFields(object);
			Object value;
			for(String s:fields){
				value = ReflectUtil.getFieldValue(s, object);
				properties.setProperty(s, value==null?"":value.toString());
			}
			properties.store(fos, "");
			fos.close();
		} catch (Exception e) {
			log.error(e);
			result = false;
		}
		return result;
	}
	
	/**
	 * 提供对一个资源文件整体设�?
	 * @param object 目标对象
	 * @param filepath 文件路径
	 * <p> 如果路径不包�?<strong>:</strong>  则认为是相对路径 Root/Directory/.../File格式的为：Directory/.../File
	 * <p> 如果路径包含 <strong>:</strong>   则认为是绝对路径 D:\\test\\.....\\File
	 * @return 
	 */
	public static Object getProperties(Object object,String filepath) {
		Properties properties = new Properties();
		Object result = null;
		try {
			properties.load(FileUtil.getFileInputStream(FileUtil.getFile(filepath)));
			String[] fields = ReflectUtil.getDecarledFields(object);
			for(String s:fields){
				ReflectUtil.setFieldValue(s, result, ReflectUtil.getFieldValue(s, object));
			}
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}
}
