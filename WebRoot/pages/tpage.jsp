<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="SystemConfig/includeFile.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>蓝卫通远程医疗平台</title>
<script type="text/javascript">
	function logout() {

		$.ajax({
			type : "post",
			url : rootpath + "/user/systemUserManagement.action",
			data : {
				"action" : "clearSession"
			},
			success : function(messsage) {
				top.location.href = "index.jsp";

			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(errorThrown);
			}

		});

	}
</script>

<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

.top {
	background: url(/lwt_rc/content/img/top-right1.png) repeat-x scroll;
	width: 100%;
	height: 64px;
}

.top img {
	position: absolute;
	left: 0;
	top: 0;
}

.top ul {
	float: right;
	overflow: hidden;
}

.top ul li {
	float: left;
	list-style: none;
	margin-right: 10px;
	overflow: hidden;
	line-height: 40px;
}

.top ul li a {
	text-decoration: none;
	color: #E6E6E6;
	font-size: 12px;
}

.top ul .out {
	background: url(/lwt_rc/content/img/icon01.png) no-repeat scroll 4px
		-57px;
	padding-left: 28px;
}
</style>
</head>
<body>
	<div class="top">
		<img src="/lwt_rc/content/img/logo2.png" alt="" />
		<ul>
			<li><a href="tmain.html" target="mainframe">返回首页</a></li>
			<li><a href="../sysmanager/verifypass.html" target="mainframe">修改密码</a>
			</li>
			<li class="out"><a href="javascript:void(0);" onclick="logout();"
				target="_parent">退出系统</a>
			</li>
		</ul>
	</div>
</body>
</html>
