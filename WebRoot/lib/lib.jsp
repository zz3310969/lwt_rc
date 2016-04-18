<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()	+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>远程会诊管理系统</title>
<meta http-equiv="description" content="远程会诊管理系统" />
<link rel="stylesheet" type="text/css" href="<%=path%>/content/css/com.css?qqa" />
<link rel="stylesheet" type="text/css" href="<%=path%>/content/css/layer.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/content/css/modelWindow.css" />
<script type="text/javascript">
		var rootpath = "<%=path%>";
</script>
<script type="text/javascript" src="<%=path%>/content/js/function.js?sss"></script>
<script type="text/javascript" src="<%=path%>/content/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/content/js/pagination.js"></script>
<script type="text/javascript" src="<%=path%>/content/js/modelWindow.js"></script>
</head>
</html>
