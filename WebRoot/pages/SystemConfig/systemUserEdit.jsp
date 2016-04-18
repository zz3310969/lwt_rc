<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="includeFile.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>修改用户信息</title>

<script type="text/javascript">
	$(document).ready(function() {
		var ajaxParam = {
			"url" : rootpath + "/user/systemUserManagement.action"
		}
		initEditPage("editSystemUserForm", ajaxParam);
		initHospitalAndSectionOffice();
		initRoleList();
	});

	function initHospitalAndSectionOffice() {
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

		var officeValueFieldInputId = "textHospitalSectionOfficeId";
		var officeTextFieldInputId = "textName";
		itemParam = {
			"textField" : "name",
			"valueField" : "id"
		};
		ajaxParam = {
			"url" : rootpath + "/user/hospitalSectionOfficeManagement.action",
			"data" : {
				"textHospitalId" : $("#" + hospitalValueFieldInputId).val(),
				"action" : "getDropDownList"
			}
		};
		initAutoComplete(officeTextFieldInputId, officeValueFieldInputId,
				ajaxParam, itemParam);
		//根据选择的医院初始化科室自动完成功能
		$("#" + hospitalTextFieldInputId)
				.bind(
						"blur",
						function() {

							if ($("#" + hospitalValueFieldInputId).val() == "") {
								ajaxParam = {
									"url" : rootpath
											+ "/user/hospitalSectionOfficeManagement.action",
									"data" : {
										"textHospitalId" : "undefined",
										"action" : "getDropDownList"
									}
								};
							} else {
								ajaxParam = {
									"url" : rootpath
											+ "/user/hospitalSectionOfficeManagement.action",
									"data" : {
										"textHospitalId" : $(
												"#" + hospitalValueFieldInputId)
												.val(),
										"action" : "getDropDownList"
									}
								};
							}

							initAutoComplete(officeTextFieldInputId,
									officeValueFieldInputId, ajaxParam,
									itemParam);
						});

	}

	//初始化所属角色列表
	function initRoleList() {
		$
				.ajax({
					async : false,
					type : "post",
					dataType : "json",
					url : rootpath + "/role/roleaction!findAll.action",
					data : {
						"action" : "04"
					},
					success : function(message) {
						var data = message.pagelist;
						$
								.each(
										data,
										function(i, n) {
											var htmlStr = "<input type='checkbox' value=" + n["roleId"] + " name='checkboxUserRole'/>"
													+ n["roleName"] + "  ";
											$("#roleListTd").append(htmlStr);
										});
					}
				});
		var data = ajaxData.data["userroles"];
		$
				.each(data,
						function(i, n) {
							$("[type='checkbox'][value='" + n["roleId"] + "']")
									.get(0).checked = true;
						});
	}
	function changePassword() {
		if ($("#passwordTr").attr("class") != "hidden")
			$("#passwordTr").addClass("hidden");
		else
			$("#passwordTr").removeClass("hidden");

	}

	/**
	 *验证原密码输入时否正确的外部函数
	 * @param {jqObject} the field where the validation applies
	 * @param {Array[String]} validation rules for this field
	 * @param {int} rule index
	 * @param {Map} form options
	 * @return an error string if validation failed
	 */
	function equalsOldPassword(field, rules, i, options) {
		if (field.val() != $("#textPassword").val()) {
			// this allows to use i18 for the error msgs
			return options.allrules.equalsOldPassword.alertText;
		}
	}
</script>


</head>
<body>
	<div class="container">
		<form id="editSystemUserForm">
			<input id="textUserId" name="textUserId" type="text" size="35"
				class="hidden" />
			<table class="mainTable">
				<tr>
					<td class="mainTable_TitleTd" colspan="4">修改用户信息</td>
				</tr>
				<tr>
					<td class="mainTable_LeftTd">登录帐号：</td>
					<td class="mainTable_RightTd"><input name="textUserName"
						type="text" size="35" class="validate[required,maxSize[20]]" /> <em>*</em>
					</td>
					<td class="mainTable_LeftTd">真实姓名：</td>
					<td class="mainTable_RightTd"><input name="textRealName"
						type="text" size="35" class="validate[required,maxSize[40]]" /> <em>*</em>
					</td>
				</tr>
				<tr>
					<td class="mainTable_LeftTd">身份证号：</td>
					<td class="mainTable_RightTd"><input
						class="validate[required,custom[idNumber]]" name="textIdNumber"
						type="text" size="35" /> <em>*</em></td>
					<td class="mainTable_LeftTd">性别：</td>
					<td class="mainTable_RightTd"><input name="radioGender"
						type="radio" size="35" value="0" class="validate[required]" /> 男
						<input name="radioGender" type="radio" size="35" value="1" /> 女 <em>*</em>
					</td>
				</tr>
				<tr>
					<td class="mainTable_LeftTd">所属医院：</td>
					<td class="mainTable_RightTd"><input id="textHospitalId"
						name="textHospitalId" type="text" size="13" class="hidden" /> <input
						id="textHospitalName" name="textHospital.name" type="text"
						size="35" /></td>
					<td class="mainTable_LeftTd">所属科室：</td>
					<td class="mainTable_RightTd"><input class="hidden"
						id="textHospitalSectionOfficeId"
						name="textHospitalSectionOfficeId" size="35" type="text" /> <input
						id="textName" name="textHospitalsectionoffice.name" size="35"
						type="text" /></td>
				</tr>
				<tr>
					<td class="mainTable_LeftTd"><label for="textTelephone">
							联系电话： </label></td>
					<td class="mainTable_RightTd"><input
						class="validate[custom[mobilephone]]" name="textTelephone"
						type="text" size="35" /></td>
					<td class="mainTable_LeftTd"><label for="textEmail">
							邮箱地址： </label></td>
					<td class="mainTable_RightTd"><input
						class="validate[custom[email]]" name="textEmail" type="text"
						size="35" /></td>
				</tr>
				<tr>
					<td class="mainTable_LeftTd"><label for=""> 用户角色： </label></td>
					<td class="mainTable_RightTd" colspan="3" id="roleListTd"></td>
				</tr>
				<tr>
					<td class="mainTable_LeftTd">个人简介：</td>
					<td class="mainTable_RightTd" colspan="3"><textarea
							class="validate[maxSize[500]]" cols="" rows=""
							style="width: 600px; height: 70px;" id="Bz0"
							name="textareaDescription"></textarea></td>
				</tr>
				<tr id="passwordTr" class="hidden">
					<td class="mainTable_LeftTd"><label for="textPassword">
							修改密码： </label></td>
					<td class="mainTable_RightTd" colspan="3"><input
						id="textPassword" name="textPassword" type="text" size="35"
						class="hidden" /> 原密码： <input
						class="validate[required,custom[password],funcCall[equalsOldPassword]]"
						id="textOldPassword" name="textOldPassword" type="password"
						size="15" /> 新密码： <input
						class="validate[required,custom[password]]" id="textNewPassword"
						name="textNewPassword" type="password" size="15" /> 确认： <input
						class="validate[required,custom[password],equals[textNewPassword]]"
						id="textComfirmPassword" name="textComfirmPassword"
						type="password" size="15" /></td>
				</tr>
				<tr>
					<td class="mainTable_ButtonTd" colspan="4"><input
						class="button" type="button" value="修改密码"
						onclick="changePassword();" /> &nbsp;&nbsp;&nbsp;&nbsp; <input
						class="button" type="submit" value="提交" />
						&nbsp;&nbsp;&nbsp;&nbsp; <input class="button" type="reset"
						value="重置" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
