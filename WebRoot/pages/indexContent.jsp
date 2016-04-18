<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>蓝卫通远程医疗平台</title>

	</head>
	<frameset rows="64,*" frameborder="no" border="0" framespacing="0">
		<frame src="/lwt_rc/pages/tpage.jsp" name="top" scrolling="no"></frame>
		<frameset rows="*" cols="195,*">
			<frame src="/lwt_rc/pages/lpage.jsp" name="leftframe" scrolling="yes"
				style="overflow-x: hidden;"></frame>
			<frame src="/lwt_rc/pages/main.jsp" name="mainframe" scrolling="yes"
				style="overflow-x: hidden;"></frame>
		</frameset>
	</frameset>
	<noframes>
		<p>
			对不起，您的浏览器不支持frameset！请使用其它浏览器
		</p>
	</noframes>
</html>
