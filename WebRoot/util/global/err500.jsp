<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>出错了,请您休息下</title>
</head>
<body>
<table align="center" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td ><img src="<%=path%>/util/global/images/err.png" width="283" height="420" border="0" usemap="#Map" /></td>
  </tr>
</table>


<map name="Map" id="Map">
  <area shape="rect" coords="211,155,278,184" href="#" onclick="window.history.go(-1);" />
</map>
</body>
</html>
