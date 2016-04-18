<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="includeFile.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>菜单管理</title>

<script type="text/javascript">
	$(document).ready(
			function() {

				selectId = "selectModuleLevel";
				itemParam = {
					"textField" : "name",
					"valueField" : "code"
				};
				ajaxParam = {
					"url" : rootpath + "/user/linkMenuDictionary.action",
					"data" : {
						"action" : "04",
						"pageSize" : "0",
						"pageIndex" : "1",
						"textTypeId" : "06"
					}
				}
				initDropDownList(selectId, ajaxParam, itemParam);

				selectId = "selectParentModuleId";
				itemParam = {
					"textField" : "moduleName",
					"valueField" : "moduleId"
				};
				ajaxParam = {
					"url" : rootpath + "/user/linkMenuFunctionModule.action",
					"data" : {
						"action" : "04",
						"pageSize" : "0",
						"pageIndex" : "1",
						"selectModuleLevel" : "1"
					}
				}
				initDropDownList(selectId, ajaxParam, itemParam);

				var queryBuilder;
				var ashxParam = {
					url : rootpath + "/user/linkMenuManagement.action",
					pageSize : "15"
				};
				var addPage = {
					title : "添加菜单",
					url : "linkMenuAdd.jsp",
					width : "800",
					height : "220"
				};
				var editPage = {
					title : "修改菜单信息",
					url : "linkMenuEdit.jsp",
					width : "800",
					height : "220"
				};
				var detailPage = {
					title : "查看 菜单信息",
					url : "linkMenuDetail.jsp",
					width : "800",
					height : "220"
				};
				initQueryBuildForm("queryBuilderForm");

				var columnInfo = [ {
					"columnName" : "menuName",
					"columnText" : "菜单名称",
					"width" : "100px",
					"style" : "text-align:left"
				}, {
					"columnName" : "menuLevel",
					"columnText" : "菜单级别",
					"width" : "100px",
					"style" : "text-align:center"
				}, {
					"columnName" : "pageUrl",
					"columnText" : "链接页面",
					"width" : "250px",
					"style" : "text-align:left"
				}, {
					"columnName" : "addTime",
					"columnText" : "添加时间",
					"width" : "150px",
					"style" : "text-align:center"
				} ];
				initNewGrid(queryBuilder, ashxParam, columnInfo, addPage,
						editPage, detailPage);

			});

	function displayParentModule() {
		level = $("#selectModuleLevel").val();
		if (level == 1 || level == '') {
			$("#parentModuleDiv").removeAttr("style");
			$("#selectParentModuleId").val('');
		} else {
			$("#parentModuleDiv").attr("style", "display: inline");

		}
		$("#queryBuilderForm").submit();
	}
	function parentModuleChange() {
		$("#queryBuilderForm").submit();
	}
</script>
</head>
<body>
	<div class="container">
		<div class="funBut">
			<br>
			<!-- 	<input type="button" class="addBtn" id="addAction" value="新增菜单" />-->
		</div>
		<div class="queFor">
			<form id="queryBuilderForm" action="#">
				<label for="textMenuName"> 菜单名称： </label> <input id="textMenuName"
					name="textMenuName" type="text" size="20" /> &nbsp;&nbsp;&nbsp;
				菜单级别： <select id="selectModuleLevel" name="selectModuleLevel"
					onchange="displayParentModule();">
					<option value="">==无限制==</option>
				</select>
				<div id="parentModuleDiv" class="hidden">
					====》父模块： <select id="selectParentModuleId"
						name="selectParentModuleId" onchange="parentModuleChange();">
						<option value="">==无指定==</option>
					</select>
				</div>
				&nbsp;&nbsp;&nbsp; <input id="submitSearch" type="submit" value="搜索" />
				<input id="resetSearch" type="reset" value="重置" />
			</form>
		</div>

		<div id="isRunning" class="hidden isRunningDiv">
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
