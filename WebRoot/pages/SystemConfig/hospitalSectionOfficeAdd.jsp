<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="includeFile.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>修改用户信息</title>

<script type="text/javascript">
	$(document).ready(function() {
		initPage();
		var ajaxParam = {
			"url" : rootpath + "/user/hospitalSectionOfficeManagement.action"
		};
		initAddPage("addForm", ajaxParam);

	});

	function initPage() {
		var hospitalValueFieldInputId = "textHospitalId";
		var hospitalTextFieldInputId = "textHospitalName";
		var itemParam = {
			"textField" : "name",
			"valueField" : "id"
		};
		var ajaxParam = {
			"url" : rootpath + "/user/hosiptalManagement.action",
			"data" : {
				"action" : "getAutoCompleteList"
			}
		};
		initAutoComplete(hospitalTextFieldInputId, hospitalValueFieldInputId,
				ajaxParam, itemParam);

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
	}

	function selectHospitalFirst() {
		var level = $("#selectLevel").val();
		var hospitalId = $("#textHospitalId").val();
		if (level == "2" && hospitalId == "") {
			$("#selectLevel").val("");
			$("#levelEm").html("填选医院后才能设置二级科室");

		} else if (level == "2") {
			initFartherSectionOffice();
			$("#selectFartherIdDiv").removeClass("hidden");
			$("#selectFartherIdDiv").addClass("inline");
		} else {
			$("#selectFartherIdDiv").removeClass("inline");
			$("#selectFartherIdDiv").addClass("hidden");

		}
	}
	function initFartherSectionOffice() {
		var hospitalId = $("#textHospitalId").val();
		var selectId = "selectFartherId";
		var itemParam = {
			"textField" : "name",
			"valueField" : "id"
		};
		var ajaxParam = {
			"url" : rootpath + "/user/hospitalSectionOfficeManagement.action",
			"data" : {
				"action" : "getDropDownList",
				"pageSize" : "0",
				"pageIndex" : "1",
				"selectLevel" : '1',
				"textHospitalId" : hospitalId
			}
		};

		initDropDownList(selectId, ajaxParam, itemParam);
	}
</script>


</head>
<body>
	<div class="container">
		<form id="addForm">			
			<table class="mainTable">
				<tr>
					<td class="mainTable_TitleTd" colspan="4">添加科室信息</td>
				</tr>
				<tr>
					<td class="mainTable_LeftTd">名称：</td>
					<td class="mainTable_RightTd"><input name="textName"
						type="text" size="35" class="validate[required,maxSize[50]]" /> <em>*</em>
					</td>
					<td class="mainTable_LeftTd">所属医院：</td>
					<td class="mainTable_RightTd"><input id="textHospitalId"
						name="textHospitalId" type="text" size="13" class="hidden" /> <input
						id="textHospitalName" name="textHospital.name" type="text"
						class="validate[required]" size="35" /><em>*</em></td>
				</tr>
				<tr>
					<td class="mainTable_LeftTd">科室级别：</td>
					<td class="mainTable_RightTd" colspan="3"><select
						id="selectLevel" name="selectLevel" class="validate[required]"
						onchange="selectHospitalFirst();">
							<option value="">==请选择==</option>
					</select>
						<div id="selectFartherIdDiv" class="hidden">
							===》父级科室 <select id="selectFartherId" name="selectFartherId"
								class="validate[required]">
								<option value="">==请选择==</option>
							</select>

						</div> <em id="levelEm">*</em>
					</td>
				</tr>
				<tr>
					<td class="mainTable_LeftTd">备注：</td>
					<td class="mainTable_RightTd" colspan="3"><textarea
							class="validate[maxSize[500]]" cols="" rows=""
							style="width: 600px; height: 70px;" id="Bz0"
							name="textareaDescription"></textarea></td>
				</tr>

				<tr>
					<td class="mainTable_ButtonTd" colspan="4"><input
						class="button" type="submit" value="提交" />
						&nbsp;&nbsp;&nbsp;&nbsp; <input class="button" type="reset"
						value="重置" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
