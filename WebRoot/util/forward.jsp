<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    	
    	<script type="text/javascript">

			var url = "<s:property value='#request.url'/>";

			if(window.top!=null){
				window.top.location="<%=path%>"+url;
				}else{
					window.location="<%=path%>"+url;
					}
		
    	</script>

  </head>
  
</html>
