<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>远程会诊管理系统</title>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="description" content="远程会诊管理系统" />

		<link href="<%=path%>/Styles/managementPage.css" rel="stylesheet"
			type="text/css" />
		<link href="<%=path%>/Styles/thickbox.css" rel="stylesheet"
			type="text/css" />
		<link href="<%=path%>/Styles/addOrEditPage.css" rel="stylesheet"
			type="text/css" />
		<link
			href="<%=path%>/Scripts/jquery-ui-1.10.0/themes/base/jquery-ui.css"
			rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="/lwt_rc/Styles/verify.jquery.css"
			type="text/css" />
		<script src="/lwt_rc/Scripts/jquery-1.9.1.min.js" type="text/javascript"></script>
		<script src="/lwt_rc/Scripts/thickbox.js" type="text/javascript"></script>
		<script src="/lwt_rc/Scripts/jquery-ui-1.10.0/ui/jquery-ui.js"
			type="text/javascript"></script>
		<script type="text/javascript" src="/lwt_rc/Scripts/jquery.verify.js"></script>
		<script type="text/javascript"
			src="/lwt_rc/Scripts/jquery.verify-cn.js"></script>
		<script src="/lwt_rc/Scripts/super.js" type="text/javascript"></script>
	</head>
</html>
