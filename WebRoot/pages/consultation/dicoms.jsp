<%@ page language="java" import="java.util.*,com.bsc.bean.*,com.bsc.util.*,javax.servlet.jsp.JspWriter;" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%

	List<Digitalimage> imgs = (List<Digitalimage>)pageContext.getRequest().getAttribute("images");
	String cid = (String)pageContext.getRequest().getAttribute("id");
	Iterator<Digitalimage> iter = imgs.iterator();
	JspWriter mout=pageContext.getOut();
	boolean flag = false;
	while(iter.hasNext()){
		
		Digitalimage dig = iter.next();
		String url = dig.getUrl();
		url = url.substring(url.indexOf("lwt_rc")+7);
		//获得下载路径
		String filepath = basePath+url.replace("\\", "/");
		//保存的名称
		String name = filepath.substring(filepath.lastIndexOf("/")+1);
		
		DownloadUtil.downloadNet(filepath, name, cid);  //下载文件
		flag = true;
	}
	if(flag){
		Runtime rn = Runtime.getRuntime();
		
		Process p = null;
		try {
			p = rn.exec("C:\\clear\\ClearCanvas.Desktop.Executable.exe -d C:\\dicom\\"+cid);
			mout.write("<script>document.getElementById('loading').style.display='none';</script>");
		} catch (Exception e) {
			System.out.println("Error exec!");
		}
	}else{
		
		mout.write("没有上传病历资料！");
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

  </head>
  
  <body>
   		<div id="loading" align="center">
			<img alt="" src="<%=path%>/content/img/loading.gif"><br/>
			正在加载中....
		</div>
  </body>
</html>
