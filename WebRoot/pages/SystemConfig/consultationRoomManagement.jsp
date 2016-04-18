<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="includeFile.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>会诊室管理</title>

<script type="text/javascript">
	$(document).ready(
			function() {
				var selectId = "selectDeviceType";
				var itemParam = {
					"textField" : "name",
					"valueField" : "code"
				};
				var ajaxParam = {
					"url" : rootpath + "/user/linkMenuDictionary.action",
					"data" : {
						"action" : "04",
						"pageSize" : "0",
						"pageIndex" : "1",
						"textTypeId" : "17"
					}
				};

				initDropDownList(selectId, ajaxParam, itemParam);

				var queryBuilder;
				var ashxParam = {
					url : rootpath + "/user/consultationRoom.action",
					pageSize : "15"
				};
				var addPage = {
					title : "添加会诊室",
					url : "consultationRoomAdd.jsp",
					width : "800",
					height : "250"
				};
				var editPage = {
					title : "修改用户信息",
					url : "consultationRoomEdit.jsp",
					width : "800",
					height : "250"
				};
				var detailPage = {
					title : "用户详细信息",
					url : "consultationRoomDetail.jsp",
					width : "800",
					height : "250"
				};
				var columnInfo = [ {
					"columnName" : "name",
					"columnText" : "名称",
					"width" : "100px"
				}, {
					"columnName" : "hospitalName",
					"columnText" : "所属医院",
					"width" : "100px",

				}, {
					"columnName" : "ip",
					"width" : "100px",
					"columnText" : "IP地址",
					"style" : "text-align:center"
				}, {
					"columnName" : "deviceTypeText",
					"width" : "100px",
					"columnText" : "设备类型"
				}, {
					"columnName" : "addTime",
					"columnText" : "添加时间",
					"width" : "200px",
					"style" : "text-align:center"
				} ];
				initQueryBuildForm("queryBuilderForm");

				initNewGrid(queryBuilder, ashxParam, columnInfo, addPage,
						editPage, detailPage);
			});

	function submitForm() {
		$("#queryBuilderForm").submit();
	}
</script>
</head>
<body>
	<div class="container">
		<div class="funBut">
			<br> <input type="button" class="addBtn" id="addAction"
				value="新增房间" />
		</div>
		<div class="queFor">
			<form id="queryBuilderForm" action="#">
				名称： <input name="textName" type="text" size="20" />
				&nbsp;&nbsp;&nbsp;&nbsp;所属医院： <input id="textHospitalName"
					name="textHospitalName" type="text" size="20" />&nbsp;&nbsp;&nbsp;&nbsp;
				设备类型： <select id="selectDeviceType" name="selectDeviceType"
					onchange="submitForm();">
					<option value="">==无限制==</option>
				</select> <input type="submit" size="20" value="查询" /> <input type="reset"
					size="20" value="重置" />
			</form>
		</div>

		<div id="isRunning" class="isRunningDiv hidden">
			<a href="#a"></a>
		</div>
	<div id="noResult" class="hidden noResult">未找到相关数据！</div>
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
