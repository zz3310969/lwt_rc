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
			"url" : rootpath + "/user/consultationRoom.action"
		};
		initEditPage("editForm", ajaxParam);

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
	}
</script>


</head>
<body>
	<div class="container">
		<form id="editForm">
			<input id="textId" name="textId" type="text"
				size="35" class="hidden" />
			<table class="mainTable">
				<tr>
					<td class="mainTable_TitleTd" colspan="4">修改会诊室信息</td>
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
						class="validate[required]" size="35" /><em>*</em>
					</td>
				</tr>
				<tr>
					<td class="mainTable_LeftTd">ip：</td>
					<td class="mainTable_RightTd"><input
						class="validate[required,custom[ipv4]]" name="textIp" type="text"
						size="35" /> <em>*</em>
					</td>
					<td class="mainTable_LeftTd">设备类型：</td>
					<td class="mainTable_RightTd"><select id="selectDeviceType"
						name="selectDeviceType" class="validate[required]">
							<option value="">==请选择==</option>
					</select><em>*</em>
					</td>
				</tr>
				<tr>
					<td class="mainTable_LeftTd">备注：</td>
					<td class="mainTable_RightTd" colspan="3"><textarea
							class="validate[maxSize[500]]" cols="" rows=""
							style="width: 600px; height: 70px;" id="Bz0"
							name="textareaDescription"></textarea>
					</td>
				</tr>

				<tr>
					<td class="mainTable_ButtonTd" colspan="4"><input
						class="button" type="submit" value="提交" />
						&nbsp;&nbsp;&nbsp;&nbsp; <input class="button" type="reset"
						value="重置" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
