<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="SystemConfig/includeFile.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>蓝卫通远程医疗平台</title>
<link rel="stylesheet" href="/lwt_rc/content/css/com.css"
	type="text/css"></link>
<style type="text/css">
body {
	overflow-y: hidden;
}

.left {
	width: 200px;
	border: 1px solid #B8C9D6;
}

.lmenu {
	color: #555555;
	height: 500px;
}

.lmenu .menuitem {
	margin-top: 5px;
}

.lmenu .menuitem b {
	font-family: "宋体";
	font-size: 12px;
	font-style: normal;
	font-weight: normal;
	display: inline-block;
	margin-top: 6px;
	margin-left: 2px;
	cursor: pointer;
}

.lmenu .menuitem .title {
	background: url(/lwt_rc/content/img/nav02.gif) no-repeat scroll;
	height: 28px;
	padding-left: 15px;
	margin-left: 2px;
}

.lmenu .menuitem .ine {
	border-top: 1px solid #B8C9D6;
}

.lmenu .menuitem ul {
	margin-top: 5px;
}

.lmenu .menuitem ul li {
	background: url(/lwt_rc/content/img/icon.png) no-repeat scroll 21px 5px;
	padding-left: 35px;
	margin-top: 4px;
	line-height: 20px;
}

.lmenu .menuitem ul .curr {
	background: url(/lwt_rc/content/img/icon.png) no-repeat scroll 21px
		-12px;
}

.lmenu .menuitem ul li a {
	color: #555555;
}

.lmenu .menuitem ul li a:hover {
	color: #FF0000;
	text-decoration: underline;
}

a {
	text-decoration: none;
}

.hidden {
	display: none;
}
</style>
<script type="text/javascript">
	$(document).ready(
			function() {
				//初始化一级菜单
				$.ajax({
					async : true,
					type : "post",
					data : {
						"action" : "getMenuListByRole"
					},
					dataType : "json",
					url : rootpath + "/user/linkMenuManagement.action",
					success : function(message) {
						var data = message.data;
						//遍历一级菜单 
						$.each(data, function(i, n) {
							var menu = $("#templateDiv").clone();
							if (n["2"] == "1") {
								menu.find("b").html(n["0"]);
								//遍历二级菜单
								$.each(data, function(j, m) {
									if (m["4"] == n["3"]) {
										var temLi = $("#templateLi").clone();
										temLi.find("a").html(m["0"]);
										if (m["1"] != null)
											temLi.find("a").attr("href",
													rootpath + m["1"]);
										else
											temLi.find("a").attr("href",
													"javascript:void(0)");
										temLi.removeClass("hidden");
										menu.find("ul").append(temLi);
									}
								});
								menu.removeClass("hidden");
								$("#lmenu").append(menu);

								menu.find("a").click(
										function() {
											$(this).parent().siblings()
													.removeClass("curr");
											$(this).parent().addClass("curr");
										});
							}
							if(i!=0)
							menu.find("ul").toggle();
						});
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {

					}
				});

				$("#lmenu").on(
						"click",
						".title",
						function(event) {
							$(this).next("ul").toggle();
							if ($(this).next("ul").is(":hidden")) {
								$(this).find('img:eq(0)').attr('src',
										'/lwt_rc/content/img/ico04.gif');
							} else {
								$(this).find('img:eq(0)').attr('src',
										'/lwt_rc/content/img/ico03.gif');
							}
						});

			});
</script>

</head>
<body>
	<div class="lmenu" id="lmenu">
		<div id="templateDiv" class="menuitem hidden">
			<div class="title">
				<img src="/lwt_rc/content/img/ico03.gif" alt="" /> <b></b>
			</div>
			<ul>
				<li id="templateLi" class="hidden"><a
					href="/lwt_rc/pages/consultation/patientinfo.jsp"
					target="mainframe"></a></li>
			</ul>
		</div>
	</div>
</body>
</html>