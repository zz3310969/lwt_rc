<%@ page language="java" import="java.util.*,com.bsc.util.*,javax.servlet.jsp.JspWriter;" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head></head>
	<body>
		<div id="loading" align="center">
			<img alt="" src="<%=path%>/content/img/loading.gif"><br/>
			正在加载中....
		</div>
	</body>
</html>

<%
	String filepath = request.getParameter("filepath"); //取出全部路径
	
	String name = filepath.substring(filepath.lastIndexOf("/")+1);  //获得文件名
	
	DownloadUtil.downloadNet(filepath, name);
	
	Runtime rn = Runtime.getRuntime();
	
	Process p = null;
	try {
		p = rn.exec("C:\\clear\\ClearCanvas.Desktop.Executable.exe -f C:\\dicom\\"+name);
	} catch (Exception e) {
		System.out.println("Error exec!");
	}
%>