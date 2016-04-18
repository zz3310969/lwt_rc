<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="includeFile.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>添加医院记录</title>

<script type="text/javascript">
	$(document).ready(function() {
		var selectId = "selectProvince";
		var itemParam = {
			"textField" : "name",
			"valueField" : "id"
		};
		var ajaxParam = {
			"url" : rootpath + "/user/region.action",
			"data" : {
				"action" : "getRegionListByFartherId",
				"pageSize" : "0",
				"pageIndex" : "1",
				"level" : "1",
				"textFartherId" : "000000",
				"order" : "name"
			}
		};
		initDropDownList(selectId, ajaxParam, itemParam);

		var ajaxParam = {
			"url" : rootpath + "/user/hospitalManagement.action"
		};
		initAddPage("addForm", ajaxParam);
	});

	function provinceChange() {
		var province = $("#selectProvince").val();

		var selectId = "selectCity";
		var itemParam = {
			"textField" : "name",
			"valueField" : "id"
		};
		var ajaxParam = {
			"url" : rootpath + "/user/region.action",
			"data" : {
				"action" : "getRegionListByFartherId",
				"pageSize" : "0",
				"pageIndex" : "1",
				"level" : "1",
				"textFartherId" : province,
				"order" : "name"
			}
		};
		initDropDownList(selectId, ajaxParam, itemParam);

		if (province != "") {
			$("#cityDiv").removeClass("hidden");
			$("#cityDiv").addClass("inline");
		} else {
			$("#selectCountry option").not($("#selectCountry option").first())
					.remove();
			$("#cityDiv").removeClass("inline");
			$("#cityDiv").addClass("hidden");
		}

		$("#countryDiv").removeClass("inline");
		$("#countryDiv").addClass("hidden");
	}
	function cityChange() {
		var city = $("#selectCity").val();
		var selectId = "selectCountry";
		var itemParam = {
			"textField" : "name",
			"valueField" : "id"
		};
		var ajaxParam = {
			"url" : rootpath + "/user/region.action",
			"data" : {
				"action" : "getRegionListByFartherId",
				"pageSize" : "0",
				"pageIndex" : "1",
				"level" : "1",
				"textFartherId" : city,
				"order" : "name"
			}
		};
		initDropDownList(selectId, ajaxParam, itemParam);

		if (city != "") {
			$("#countryDiv").removeClass("hidden");
			$("#countryDiv").addClass("inline");
		} else {
			$("#countryDiv").removeClass("inline");
			$("#countryDiv").addClass("hidden");
		}
	}
</script>


</head>
<body>
	<div class="container">
		<form id="addForm">
			<table class="mainTable">
				<tr>
					<td class="mainTable_TitleTd" colspan="4">添加医院信息</td>
				</tr>
				<tr>
					<td class="mainTable_LeftTd">名称：</td>
					<td class="mainTable_RightTd"><input name="textHospitalName"
						type="text" size="35" class="validate[required,maxSize[50]]" /> <em>*</em>
					</td>
					<td class="mainTable_LeftTd">地址：</td>
					<td class="mainTable_RightTd"><input id="textAddress"
						name="textAddress" type="text" class="validate[required]"
						size="35" /><em>*</em>
					</td>
				</tr>
				<tr>
					<td class="mainTable_LeftTd">电话：</td>
					<td class="mainTable_RightTd"><input name="textTelephone"
						type="text" size="35" class="validate[required,custom[phone]]" />
						<em>*</em></td>
					<td class="mainTable_LeftTd">邮编：</td>
					<td class="mainTable_RightTd"><input id="textPostCode"
						name="textPostCode" type="text" size="35" />
					</td>
				</tr>
				<tr>
					<td class="mainTable_LeftTd">所属地区：</td>
					<td class="mainTable_RightTd" colspan="3"><select
						id="selectProvince" name="selectProvince"
						onchange="provinceChange();">
							<option value="">==省份/自治区/直辖市==</option>
					</select>
						<div id="cityDiv" class="hidden">
							==》 <select id="selectCity" name="selectCity"
								onchange="cityChange();">
								<option value="">==请选择市区==</option>
							</select>
						</div>
						<div id="countryDiv" class="hidden">
							==》 <select id="selectCountry" name="selectCountry">
								<option value="">==请选择县==</option>
							</select>
						</div> <em id="levelEm">*</em></td>
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
