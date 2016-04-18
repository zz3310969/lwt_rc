<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<link href="/lwt_rc/Styles/thickbox.css" rel="stylesheet"
	type="text/css" />
<script src="/lwt_rc/Scripts/jquery-1.9.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/lwt_rc/Scripts/thickbox.js"></script>
<title>蓝卫通远程医疗平台</title>
<script type="text/javascript">
	window.onload = reinitIframe();
	function reinitIframe() {

		$.ajax({
			type : "post",
			url : "/lwt_rc/user/systemUserManagement.action",
			data : {
				"action" : "ifSession"
			},
			success : function(message) {
				if (!message["isSuccess"])
					top.location.href = "index.jsp";
				return false;
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(errorThrown);
			}

		});

		var iframe = document.getElementById("frame_content");
		try {
			var bHeight = iframe.contentWindow.document.body.scrollHeight;
			var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
			var height = Math.max(bHeight, dHeight);
			iframe.height = height;
			$("#frame_content").css("border", "1px solid");
		} catch (ex) {
		}
	}
	//window.setInterval("reinitIframe()", 200);
</script>
<style type="text/css">
body {
	overflow-y: hidden;
}

* {
	margin: 0px;
	padding: 0px;
}
</style>
</head>

<body>



	<iframe id="frame_content" src="/lwt_rc/pages/indexContent.jsp"
		scrolling="no" frameborder="0" width="100%" height="100%"></iframe>

</body>
</html>
