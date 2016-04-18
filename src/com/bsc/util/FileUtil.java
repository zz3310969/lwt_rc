package com.bsc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

/**
 * 文件操作
 * @version 1.0
 *
 */
public class FileUtil 
{
	
	private static Logger log = Logger.getLogger(FileUtil.class);
	
	/**
	 * 创建文件
	 * @param directory 文件目录 绝对路径
	 * @return <i>true</i> create success or <i>false</i> create failure
	 */
	public static boolean createDirectory(String directory){
		File file = new File(directory);
		if(file.exists() && file.isDirectory()){
			return true;
		}
		return file.mkdirs();
	}
	
	/**
	 * 获得文件输入
	 * @param filepath 
	 * <p> 如果路径不包�?<strong>:</strong>  则认为是相对路径 Root/Directory/.../File格式的为：Directory/.../File
	 * <p> 如果路径包含 <strong>:</strong>   则认为是绝对路径 D:\\test\\.....\\File
	 * @return
	 */
	public static InputStream getFileInputStream(String filepath){
		InputStream is = null;
		try {
			is = new FileInputStream(getFile(filepath));
		} catch (FileNotFoundException e) {
			log.error(e);
		}
		return is;
	}
	
	/**
	 * 获得文件输入
	 * @param file 文件
	 * @return
	 */
	public static InputStream getFileInputStream(File file){
		InputStream is = null;
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			log.error(e);
		}
		return is;
	}
	
	/**
	 * 获得文件输入
	 * @param url 文件url
	 * @return
	 * @throws IOException 
	 */
	public static InputStream getFileInputStream(URL url){
		if(url==null)return null;
		try {
			return url.openStream();
		} catch (IOException e) {
			log.error(e);
		}
		return null;
	}
	
	/**
	 * <b>获得文件 相对与Root</b>
	 * @param filepath  
	 * <p> 如果路径不包�?<strong>:</strong>  则认为是相对路径 Root/Directory/.../File格式的为：Directory/.../File
	 * <p> 如果路径包含 <strong>:</strong>   则认为是绝对路径 D:\\test\\.....\\File
	 * @return  文件存在 则返回文�?<p>不存�?创建文件并返�?p>
	 */
	public static File getFile(String filepath){
		if(filepath==null)return null;
		File file = new File(isContainColon(filepath)==true?filepath:PathUtil.getRootPath()+filepath.replaceAll("/", File.separator));
		if(!file.exists()){//文件不存�?1、查看上�?��文件夹路径是否存在，不存在则创建 2、创建文�?
			if(createDirectory(file.getParent())){
				try {
					file.createNewFile();
				} catch (IOException e) {
					log.error(e);
				}
			}
		}
		return file;
	}
	
	/**
	 * 是否包含<strong>:</strong> 并且不能出现在开始位�?
	 * @param filepath
	 * @return
	 */
	private static boolean isContainColon(String filepath){
		if(filepath!=null && filepath.indexOf(":")>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 把字符串写入文件
	 * @param s 字符
	 * @param file 文件
	 * @param append 是否追加 
	 */
	public static void write(String s , File file , boolean append){
		try {
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file,append),"utf-8");
			out.write(s);
			out.flush();
			out.close();
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	/**
	 * 把字符串写入文件
	 * @param s 字符�?
	 * @param filepath  
	 * <p> 如果路径不包�?<strong>:</strong>  则认为是相对路径 Root/Directory/.../File格式的为：Directory/.../File
	 * <p> 如果路径包含 <strong>:</strong>   则认为是绝对路径 D:\\test\\.....\\File
	 * @param append 是否追加 true 追加 or false 不追�?
	 */
	public static void  write(String s,String filepath,boolean append){
		write(s,FileUtil.getFile(filepath),append);
	}
	
	/**
	 * 文件上传
	 * @param file 本地文件路径
	 * @param filepath 
	 * <p> 如果路径不包�?<strong>:</strong>  则认为是相对路径 Root/Directory/.../File格式的为：Directory/.../File
	 * <p> 如果路径包含 <strong>:</strong>   则认为是绝对路径 D:\\test\\.....\\File
	 * @return 远程文件路径 or null 
	 */
	public static String upload(File file,String filepath){
		FileInputStream input;
		FileOutputStream output;
		try {
			input = new FileInputStream(file);
			output = new FileOutputStream(isContainColon(filepath)==true?filepath:PathUtil.getRootPath()+filepath);
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = input.read(b)) != -1) {
				output.write(b, 0, len);
			}
			output.flush();
			output.close();
			input.close();
		} catch (Exception e) {
			filepath = null;
			log.error(e);
		}
		return filepath;
	}
	
	/**
	 * 文件上传
	 * @param resourcepath 本地文件路径 为绝对路�?
	 * @param filepath 
	 * <p> 如果路径不包�?<strong>:</strong>  则认为是相对路径 Root/Directory/.../File格式的为：Directory/.../File
	 * <p> 如果路径包含 <strong>:</strong>   则认为是绝对路径 D:\\test\\.....\\File
	 * @return 远程文件路径 or null 
	 */
	public static String upload(String resourcepath,String filepath){
		FileInputStream input;
		FileOutputStream output;
		try {
			input = new FileInputStream(getFile(filepath));
			output = new FileOutputStream(isContainColon(filepath)==true?filepath:PathUtil.getRootPath()+filepath);
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = input.read(b)) != -1) {
				output.write(b, 0, len);
			}
			output.flush();
			output.close();
			input.close();
		} catch (Exception e) {
			filepath = null;
			log.error(e);
		}
		return filepath;
	}
	
	/**
	 * 删除文件
	 * @param file
	 * @return
	 */
	public static boolean deleteFile(File file){
		return file.delete();
	}
	
	/**
	 * 删除文件
	 * @param filepath
	 * <p> 如果路径不包�?<strong>:</strong>  则认为是相对路径 Root/Directory/.../File格式的为：Directory/.../File
	 * <p> 如果路径包含 <strong>:</strong>   则认为是绝对路径 D:\\test\\.....\\File
	 * @return
	 */
	public static boolean deleteFile(String filepath){
		return getFile(filepath).delete();
	}
	
	/**
	 * 文件复制
	 * @param resourse 原文�?绝对路径
	 * @param targetpath 目标 
	 * <p> 如果路径不包�?<strong>:</strong>  则认为是相对路径 Root/Directory/.../File格式的为：Directory/.../File
	 * <p> 如果路径包含 <strong>:</strong>   则认为是绝对路径 D:\\test\\.....\\File
	 * @return
	 */
	public static boolean copyFile(String resourse,String targetpath){
		return upload(resourse,targetpath)==null;
	}

	/**
	 * 压缩方法 没解决中文路径问�?windows gbk linux utf8<p>
	 * 不使用中文路�?p>
	 * 压缩文件与压缩文件夹在本质上是一样的�?p>
	 * 压缩文件夹的关键在于递归的调用�?<p>
	 * 保证输出流是�?��打开的�?<p>
	 * 输入流中若为文件则写到输出流中，若为文件夹则遍历文件夹中的各个文件名，若为文件则写到输出流中，若为文件夹则继续�?归调用�?
	 * @param inputFile 源文件夹
	 * @param zipFileName  目的文件
	 * @throws Exception
	 */
	public static void zip(String inputFile, String zipFileName){
		if(isDirectory(inputFile,zipFileName)){
			File tempFile = new File(zipFileName);
			//获得文件名，带后�?��
			String fileName = tempFile.getName();
			fileName = fileName.substring(0,fileName.indexOf("."));
			String temp = tempFile.getParent()+ fileName+"-"+DateUtil.DATE_FORMAT.format(DateUtil.getNowDate())+ ".zip"; 
			tempFile = new File(temp);
			if(tempFile.exists()){
				tempFile.delete();
			}
			File f = new File(inputFile);
			ZipOutputStream out;
			try {
				out = new ZipOutputStream(new FileOutputStream(temp));
				zip(out,f,null);
				out.close();
			} catch (FileNotFoundException e) {
				log.error("压缩错误：未找到文件");
			} catch (IOException e) {
				log.error("压缩错误：写入异常");
			}
		}
	 }
	 
	/**
	 * 压缩
	 * @param out ZipOutputStream
	 * @param f File
	 * @param base 
	 * @throws IOException 
	 * @throws Exception
	 */
	private static void zip(ZipOutputStream out,File f,String base)throws IOException{
		System.out.println("zipping " + f.getAbsolutePath());
	    if (f.isDirectory()) {
	    	 File[] fc =f.listFiles();
			 if(base!=null)
			    out.putNextEntry(new ZipEntry(base+File.separator));
			 base=base==null?"":base+File.separator;
			 for (int i=0;i<fc.length ;i++ ) {
			    zip(out,fc[i],base+fc[i].getName());
			 }
	    }else {
		   out.putNextEntry(new ZipEntry(base));
		   FileInputStream in = new FileInputStream(f);
		   int b;
		   byte[] by=new byte[1024];
		   while((b=in.read(by)) != -1)
			   out.write(b);
		   in.close();
		  }
	}
	
	/**
	 * 验证源文件路径是否存在，输出文件夹不存在则创�?
	 * @param sourcePath 源文件路�?
	 * @param destinationPath 输出文件路径 格式�?zip
	 * @return
	 */
	private static boolean isDirectory(String sourcePath,String destinationPath){
		File file = new File(sourcePath);
		if(!file.exists()){
			System.err.println("源路径不存在");
			return false;
		}
		//格式�?zip
		if(!".zip".equals(destinationPath.substring(destinationPath.lastIndexOf(".")).toLowerCase())){
			return false;
		}
		file = new File(destinationPath);
		return createDirectory(file.getParent());
	}
	
	/**
	 * 验证是否为对应类型的文件,前提是该文件必须存在 否则返回false
	 * @param type eg: xml ,xls , doc or docx
	 * @return correct : true or false
 	 */
	public static boolean isCorrentFile(String filepath,String type){
		if(isExist(filepath) && type.equalsIgnoreCase(filepath.substring(filepath.lastIndexOf(".")+1)))
			return true;
		return false;
	}
	
	/**
	 * 文件是否存在
	 * @param filepath if null 则返回false
	 * @return
	 */
	public static boolean isExist(String filepath){
		return filepath!=null && new File(filepath).exists();
	}
}
