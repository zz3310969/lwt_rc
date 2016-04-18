<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>医院管理</title>
<link href="<%=path%>/Styles/hospitalRelation.css" rel="stylesheet"
	type="text/css" />

<script src="/lwt_rc/Scripts/jquery-1.9.1.min.js" type="text/javascript"></script>

<script src="/lwt_rc/Scripts/hospitalRelation.js" type="text/javascript"></script>


</head>
<body>
	<div class="container">

		<div class="receiverQueFor hidden">
			<div class="receiverInf"></div>
			<form id="receiverQueryBuilderForm" action="#">
				医院名称： <input id="textReceiverHospitalName" name="textHospitalName"
					type="text" size="13" /> &nbsp;&nbsp;&nbsp; <input type="submit"
					size="20" value="查询" />
			</form>
		</div>
		<div id="isReceiverRunning" class="hidden isRunningDiv">
			<a href="#a"></a>
		</div>
		<div id="noReceiverResult" class="hidden noResult">没有可接受会诊请求的医院！</div>

		<div class="receiverMain hidden">
			<table class="recLis" id="receiverTable">

			</table>
			<div class="pagAre">
				<div class="pagLef">
					当前共有 <span id="receiverTotalCountSpan"></span>条纪录， 当前第 <span
						id="revceiverPageIndexSpan"></span>页， 每页 <span
						id="receiverPageCountSpan"></span>条纪录
				</div>
				<div class="pagRig">
					<span class="fis" id="receiverFirstBut">首页</span> <span class="pre"
						id="receiverPreviousBut">上一页</span> <span class="nex"
						id="receiverNextBut">下一页</span> <span class="las"
						id="receiverLastBut">尾页</span> 转到 第 <input type="text"
						id="pagerInput" class="num" maxlength="5" /> 页 <input
						type="button" id="gotoBut" value="转" />
				</div>
			</div>
		</div>




		<div class="queFor hidden">
			<form id="queryBuilderForm" action="#">
				医院名称： <input id="textHospitalName" name="textHospitalName"
					type="text" size="13" /> &nbsp;&nbsp;&nbsp; <input type="submit"
					size="20" value="查询" />
			</form>
		</div>

		<div id="isRunning" class="isRunningDiv hidden">
			<a href="#a"></a>
		</div>
		<div id="noResult" class="hidden noResult">未找到符合查询条件的医院数据！</div>

		<div class="main hidden">
			<table class="recLis" id="contentTable">

			</table>
			<div class="pagAre">
				<div class="pagLef">
					当前共有 <span id="totalCountSpan"></span>条纪录， 当前第 <span
						id="pageIndexSpan"></span>页， 每页 <span id="pageCountSpan"></span>条纪录
				</div>
				<div class="pagRig">
					<span class="fis" id="firstBut">首页</span> <span class="pre"
						id="previousBut">上一页</span> <span class="nex" id="nextBut">下一页</span>
					<span class="las" id="lastBut">尾页</span> 转到 第 <input type="text"
						id="pagerInput" class="num" maxlength="5" /> 页 <input
						type="button" id="gotoBut" value="转" />
				</div>
			</div>
		</div>

	</div>
</body>
</html>
