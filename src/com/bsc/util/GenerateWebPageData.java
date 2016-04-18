package com.bsc.util;

import java.util.*;
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class GenerateWebPageData{

     private String username = "root";
     private String password = "root";
     private String driverclass = "com.mysql.jdbc.Driver";
     private String databaseUrl = "jdbc:mysql://192.168.0.236:3306/remoteconsulation";
     private DirFilter df = new DirFilter();
     private BscFilter bf = new BscFilter();
     private ArrayList list = new ArrayList();
     private String ext = System.getProperty("user.dir").replace("bin", "webapps\\lwt_rc");
     

	public GenerateWebPageData(){
    	 
    	 Properties prop = new Properties();
    	 
    	 try {
			prop.load(GenerateWebPageData.class.getResourceAsStream("/config.properties"));
			
			this.username = prop.getProperty("user");
			this.password = prop.getProperty("pwd");
			this.databaseUrl = prop.getProperty("url"); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
     }
    

    public boolean insertData(ArrayList list) {
    	boolean flag = false;
        PreparedStatement ps;
        ResultSet rs;
        String path;
        StringBuilder builder;
        String sql;

        try {
			Class.forName(driverclass);
			Connection conn = DriverManager.getConnection(databaseUrl, username, password);
	        Statement state = conn.createStatement();
	        for (Object obj : list) {
	            path = obj.toString();
	            if (ifHavaPage(path)) {
	                continue;
	            }
	            sql="insert into webpage(pageId,url,pageName,description,addTime,updateTime) values(";
	            builder = new StringBuilder(sql);
	            builder.append("'").append(UUID.randomUUID().toString()).append("',");
	            builder.append("'").append(path).append("',");
	            builder.append("'").append("生成页面").append("',");
	            builder.append("'").append("自动生成页面，存的是页面的相对路径。").append("',");
	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            builder.append("'").append(dateFormat.format(new Date())).append("',");
	            builder.append("'").append(dateFormat.format(new Date())).append("')");
	            sql = builder.toString();
	            state.executeUpdate(sql);
	        }

	        state.close();
	        conn.close();
	        flag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
        return flag;
    }

    public boolean ifHavaPage(String path) throws Exception {
        Connection conn = DriverManager.getConnection(databaseUrl, username, password);
        Statement state = conn.createStatement();
        String sql = "select count(*) from webpage where url='" + path + "'";
        ResultSet rs = state.executeQuery(sql);
        int rowCount = 0;
        while (rs.next()) {
            rowCount = rs.getInt(1);
        }
        rs.close();
        state.close();
        conn.close();
        if (rowCount == 1) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList generateFileList(File tar) throws Exception {
        File[] dirs = tar.listFiles(df);
        File[] files = tar.listFiles(bf);
        for (File dir : dirs) {
            generateFileList(dir);
        }
        for (File file : files) {
            String fileName = file.getName();
            if (fileName.toLowerCase().endsWith(".jsp") || fileName.toLowerCase().endsWith(".html") || fileName.toLowerCase().endsWith(".htm")) {
                String url = file.getPath();
                url = url.substring(ext.length(), url.length());
                url = url.replace("\\", "/");
                this.list.add(url);
            }
        }
        return this.list;
    }
    
    public String getDriverclass() {
    	return driverclass;
    }


    public void setDriverclass(String driverclass) {
    	this.driverclass = driverclass;
    }



    public DirFilter getDf() {
    	return df;
    }



    public void setDf(DirFilter df) {
    	this.df = df;
    }



    public BscFilter getBf() {
    	return bf;
    }



    public void setBf(BscFilter bf) {
    	this.bf = bf;
    }



    public ArrayList getList() {
    	return list;
    }



    public void setList(ArrayList list) {
    	this.list = list;
    }



    public String getExt() {
    	return ext;
    }



    public void setExt(String ext) {
    	this.ext = ext;
    }
    
}


class DirFilter implements FileFilter {

    public boolean accept(File file) {
        return file.isDirectory();
    }
}

class BscFilter implements FileFilter {

    public boolean accept(File file) {
        return file.isFile();
    }
}
