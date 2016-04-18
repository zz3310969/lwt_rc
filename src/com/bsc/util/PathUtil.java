package com.bsc.util;

import java.io.File;
import java.net.URL;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

/**
 * 获取项目路径
 * @author
 *
 */
public class PathUtil 
{
	private static String path;
	
	/**
	 * 获得项目路径 ServletActionContext
	 * @return 绝对路径
	 */
	public static String getRootPath() {
		return path==null?ServletActionContext.getServletContext().getRealPath("")+File.separator:path;
	}

	/**
	 * 获得项目路径 
	 * @param context ServletContext
	 * @return 绝对路径
	 */
	public static String getRootPath(ServletContext context){
		return path=context.getRealPath("")+File.separator;
	}
	
	/**
	 * 获得项目路径 
	 * @return 绝对路径
	 */
	public static String getServletActionContextPath(){
		return path=ServletActionContext.getServletContext().getRealPath("")+File.separator;
	}
	
	/**
	 * 获得类的路径 相对�?classes 或�? bin 的路�?
	 * @param name 查找具有给定名称的资源�?资源是可以�?过类代码以与代码位置无关的方式访问的�?��数据（图像�?声音、文本等）�?资源名称是以 '/' 分隔的标识资源的路径名称�?
	 * <p>eg: org/bts/util/log4j.properties
	 * @return 
	 * <p>如果该资源没找到，则返回项目路径 
	 * <p>文件路径 绝对路径
	 */
	public static String getClassLoaderPath(String name){
		URL url = Thread.currentThread().getContextClassLoader().getResource(name);
		if(url==null)
			return path;
		else 
			return url.getPath().endsWith("/")?url.getPath().substring(1):url.getPath();
	}
	
	/**
	 * 获得类的路径 相对�?classes 或�? bin 的路�?
	 * @param name 查找具有给定名称的资源�?资源是可以�?过类代码以与代码位置无关的方式访问的�?��数据（图像�?声音、文本等）�?资源名称是以 '/' 分隔的标识资源的路径名称�?
	 * <p>eg: org/bts/util/log4j.properties
	 * @return URL 
	 */
	public static URL getClassLoaderAsResource(String name){
		return Thread.currentThread().getContextClassLoader().getResource(name);
	}
}
