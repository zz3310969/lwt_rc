package com.bsc.util;


/**
 * sql连接字符串安�?
 * 此类为连接字符串进行验证安全
 * @author hjp
 * @version 1.0
 *
 */
public class ParamaterSecutity 
{
	//默认编码位数�?6
	public static Integer DIGITIS = 16;
	
	static{
		String idLength = PropertiesUtil.getProperties("id_length", FileUtil.getFileInputStream(PathUtil.getClassLoaderAsResource("org/bts/util/set.properties")));
		if(idLength!=null && NumberUtil.isInt(idLength)){
			DIGITIS = Integer.parseInt(idLength);
		}
	}
	
	/**
	 * 验证是否为数字组成的字符�?
	 * @param paramater
	 * @return
	 */
	public static boolean isId(String paramater){
		if(NumberUtil.isNumber(paramater,DIGITIS)){
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 获取该jar文件org/bts/util/set.properties设置的key的�?
	 * @param key
	 * @return value
	 */
	public static String getProperty(String key){
		return PropertiesUtil.getProperties(key, FileUtil.getFileInputStream(PathUtil.getClassLoaderAsResource("org/bts/util/set.properties")));
	}
	
}
