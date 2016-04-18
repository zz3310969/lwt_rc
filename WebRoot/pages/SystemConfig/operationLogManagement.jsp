<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="includeFile.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>菜单管理</title>

<script type="text/javascript">
	$(document).ready(
			function() {
				viewDetail=true;
				selectId = "selectOperationType";
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
						"textTypeId" : "10"
					}
				};
				initDropDownList(selectId, ajaxParam, itemParam);

						selectId = "selectIsSuccess";
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
						"textTypeId" : "11"
					}
				}
				initDropDownList(selectId, ajaxParam, itemParam);

				var queryBuilder;
				var ashxParam = {
					url : rootpath + "/user/operationLog.action",
					pageSize : "15"
				};
				var addPage = {
					title : "添加日志",
					url : "operationLogAdd.jsp",
					width : "800",
					height : "220"
				};
				var editPage = {
					title : "查看日志信息",
					url : "operationLogDetail.jsp",
					width : "800",
					height : "220"
				};
				var detailPage = {
					title : "查看日志详细信息",
					url : "operationLogDetail.jsp",
					width : "800",
					height : "220"
				};
				initQueryBuildForm("queryBuilderForm");

				var columnInfo = [ {
					"columnName" : "operationTable",
					"columnText" : "目标类",
					"width" : "100px",
					"style" : "text-align:left"
				}, {
					"columnName" : "userName",
					"columnText" : "操作者",
					"width" : "100px",
					"style" : "text-align:left"
				}, {
					"columnName" : "operationTypeText",
					"columnText" : "操作类型",
					"width" : "100px",
					"style" : "text-align:center"
				}, {
					"columnName" : "resultMessage",
					"columnText" : "提示消息",
					"width" : "350px",
					"style" : "text-align:left"
				}, {
					"columnName" : "addTime",
					"columnText" : "操作时间",
					"width" : "250px",
					"style" : "text-align:center"
				} ];				
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
			<br>
			<!-- 	<input type="button" class="addBtn" id="addAction" value="新增菜单" />-->
		</div>
		<div class="queFor">
			<form id="queryBuilderForm" action="#">
				操作者：<input id="textUserName" name="textUserName" type="text" size="15" /> &nbsp;&nbsp; 
				目标类：<input id="textOperationTable" name="textOperationTable" type="text" size="15" />&nbsp;&nbsp;
				操作类型： <select id="selectOperationType" name="selectOperationType" onchange="submitForm();">
							<option value="">==无限制==</option>
					   </select>&nbsp;&nbsp;
				是否成功： <select id="selectIsSuccess" name="selectIsSuccess" onchange="submitForm();">
							<option value="">==无限制==</option>
					   </select>				
		
					   &nbsp;&nbsp; 
					   <input id="submitSearch" type="submit" value="搜索" />
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
