<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="includeFile.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户管理</title>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var valueFieldInputId = "textHospitalId";
						var textFieldInputId = "textHospitalName";
						var itemParam = {
							"textField" : "name",
							"valueField" : "id"
						};
						var ajaxParam = {
							"url" : rootpath
									+ "/user/hosiptalManagement.action",
							"data" : {
								"action" : "getAutoCompleteList"
							}
						}
						initAutoComplete(textFieldInputId, valueFieldInputId,
								ajaxParam, itemParam);

						//根据选择的医院查询科室列表，默认只显示到第一级科室
						$("#" + textFieldInputId)
								.bind(
										"blur",
										function() {
											var selectId = "selectHospitalSectionOfficeId";
											var itemParam = {
												"textField" : "name",
												"valueField" : "id"
											};
											var ajaxParam = {
												"url" : rootpath
														+ "/user/hospitalSectionOfficeManagement.action",
												"data" : {
													"action" : "getDropDownList",
													"pageSize" : "0",
													"pageIndex" : "1",
													"level" : "1",
													"textHospitalId" : $(
															"#"
																	+ valueFieldInputId)
															.val(),
													"order" : "name"
												}
											};
											if ($("#" + valueFieldInputId)
													.val() == "") {
												$("#" + selectId).empty();
												$("#" + selectId)
														.append(
																"<option value=''>==请先选择医院==</option>");

											} else {
												$("#" + selectId).empty();
												$("#" + selectId)
														.append(
																"<option value=''>==科室不限==</option>");
												initDropDownList(selectId,
														ajaxParam, itemParam);
											}

										});

						var queryBuilder;
						var ashxParam = {
							url : rootpath
									+ "/user/systemUserManagement.action",
							pageSize : "15"
						};
						var addPage = {
							title : "添加用户",
							url : "systemUserAdd.jsp",
							width : "800",
							height : "400"
						};
						var editPage = {
							title : "修改用户信息",
							url : "systemUserEdit.jsp",
							width : "800",
							height : "400"
						};
						var detailPage = {
							title : "用户详细信息",
							url : "systemUserDetail.jsp",
							width : "800",
							height : "280"
						};
						var columnInfo = [ {
							"columnName" : "userId",
							"columnText" : "序号",
							"width" : "100px"
						}, {
							"columnName" : "userName",
							"columnText" : "登录账号",
							"width" : "100px",
							"style" : "text-align:center"
						}, {
							"columnName" : "genderText",
							"width" : "130px",
							"columnText" : "用户性别",
							"style" : "text-align:center"
						}, {
							"columnName" : "telephone",
							"width" : "100px",
							"columnText" : "联系电话"
						}, {
							"columnName" : "hospitalName",
							"columnText" : "所属医院",
							"width" : "200px"
						}, {
							"columnName" : "hospitalSectionOfficeName",
							"columnText" : "所在科室",
							"width" : "200px"
						} ];
						initQueryBuildForm("queryBuilderForm");

						initNewGrid(queryBuilder, ashxParam, columnInfo,
								addPage, editPage, detailPage);
					});
</script>
</head>
<body>
	<div class="container">
		<div class="funBut">
			<br> <input type="button" class="addBtn" id="addAction"
				value="新增用户" />
		</div>
		<div class="queFor">
			<form id="queryBuilderForm" action="#">
				用户名： <input name="textUserName" type="text" size="10" /> 身份证号： <input
					name="textIdNumber" type="text" size="13" /> 所属医院： <input
					id="textHospitalId" name="textHospitalId" type="text" size="13"
					class="hidden" /> <input id="textHospitalName"
					name="textHospitalName" type="text" size="13" /> 所属科室： <select
					id="selectHospitalSectionOfficeId"
					name="selectHospitalSectionOfficeId"
					onchange="$('#queryBuilderForm').submit();">
					<option value="">==请先选择医院==</option>
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
