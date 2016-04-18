<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="includeFile.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>会诊室管理</title>

<script type="text/javascript">
	$(document).ready(
			function() {
				var selectId = "selectLevel";
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
						"textTypeId" : "09"
					}
				};

				initDropDownList(selectId, ajaxParam, itemParam);

				var queryBuilder;
				var ashxParam = {
					url : rootpath
							+ "/user/hospitalSectionOfficeManagement.action",
					pageSize : "15"
				};
				var addPage = {
					title : "添加医院科室",
					url : "hospitalSectionOfficeAdd.jsp",
					width : "800",
					height : "250"
				};
				var editPage = {
					title : "修改医院科室",
					url : "hospitalSectionOfficeEdit.jsp",
					width : "800",
					height : "250"
				};
				var detailPage = {
					title : "医院科室详细信息",
					url : "hospitalSectionOfficeDetail.jsp",
					width : "800",
					height : "250"
				};
				var columnInfo = [ {
					"columnName" : "name",
					"columnText" : "名称",
					"width" : "100px"
				}, {
					"columnName" : "levelText",
					"width" : "80px",
					"columnText" : "级别",
					"style" : "text-align:center"
				}, {
					"columnName" : "hospitalName",
					"columnText" : "所属医院",
					"width" : "150px",

				}, {
					"columnName" : "addTime",
					"columnText" : "添加时间",
					"width" : "100px",
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
				value="新增科室" />
		</div>
		<div class="queFor">
			<form id="queryBuilderForm" action="#">
				名称： <input name="textName" type="text" size="20" />
				&nbsp;&nbsp;&nbsp;&nbsp;所属医院： <input id="textHospitalName"
					name="textHospitalName" type="text" size="20" />&nbsp;&nbsp;&nbsp;&nbsp;
				科室级别： <select id="selectLevel" name="selectLevel"
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
