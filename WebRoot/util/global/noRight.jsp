<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>对不起，没有访问权限</title>
<style type="text/css">
body {
	margin:0;
	padding:0;
	font-size:12px;
	font-family:Verdana, Geneva, sans-serif,宋体;
}
a:link {
color:#000;
text-decoration:none;
}
a:visited {
color:#000;
text-decoration:none;
}
a:hover {
color:#3C93C8;
text-decoration:none;
}
a:active {
color:#3C93C8;
text-decoration:none;
}
</style>
<script type="text/javascript">
  	function login(){
  		if(window.top!=null){
  			window.top.location.href="<%=path%>/index.jsp";
  		}else{
  			window.location.href="<%=path%>/index.jsp";
  		}
  	}
  </script>
</head>
<body>
<table border="0" align="center" cellpadding="0" cellspacing="0">
  <tr><td height="50"></td></tr>
  <tr>
    <td><img src="<%=path%>/util/global/images/xl.png" width="471" height="146" /></td>
  </tr>
  <tr>
    <td style="line-height:26px; padding-left:35px;">对不起，请您确认<a href="#" onclick="login();"><strong>登录</strong></a></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
</body>
</html>
