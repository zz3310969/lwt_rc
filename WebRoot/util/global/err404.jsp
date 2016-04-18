<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>页面不存在</title>
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
<table align="center" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td ><img src="<%=path%>/util/global/images/absent.jpg" border="0" usemap="#Map" /></td>
  </tr>
  <tr>
  	<td><a href="javascript:login();">返回</a></td>
  </tr>
</table>
</body>
</html>
