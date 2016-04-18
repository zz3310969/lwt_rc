<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="includeFile.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>修改医院记录</title>

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
		initEditPage("editForm", ajaxParam);
		var provinceId = ajaxData.data["provinceId"];
		var cityId = ajaxData.data["cityId"];
		var countryId = ajaxData.data["countryId"];
		if (provinceId != null) {
			$("#selectProvince").val(provinceId);

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
					"textFartherId" : provinceId,
					"order" : "name"
				}
			};
			initDropDownList(selectId, ajaxParam, itemParam);
		}
		if (cityId != null) {
			$("#selectCity").val(cityId);
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
					"textFarthId" : cityId,
					"order" : "name"
				}
			};
			initDropDownList(selectId, ajaxParam, itemParam);
		}
		if (countryId != null)
			$("#selectCountry").val(countryId);

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

		$("#selectCountry option").not($("#selectCountry option").first())
				.remove();

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

	}
</script>


</head>
<body>
	<div class="container">
		<form id="editForm">
			<table class="mainTable">
				<tr>
					<td class="mainTable_TitleTd" colspan="4">修改医院信息</td>
				</tr>
				<tr>
					<td class="mainTable_LeftTd">名称：</td>
					<td class="mainTable_RightTd"><input id="textId" name="textId"
						type="text" size="35" class="hidden" /> <input id="textName"
						name="textName" type="text" size="35"
						class="validate[required,maxSize[50]]" /> <em>*</em></td>
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
						name="textPostCode" type="text" size="35" /></td>
				</tr>
				<tr>
					<td class="mainTable_LeftTd">所属地区：</td>
					<td class="mainTable_RightTd" colspan="3"><select
						id="selectProvince" name="selectProvince"
						onchange="provinceChange();">
							<option value="">==省份/自治区/直辖市==</option>
					</select>
						<div id="cityDiv" class="inline">
							==》 <select id="selectCity" name="selectCity"
								onchange="cityChange();">
								<option value="">==请选择市区==</option>
							</select>
						</div>
						<div id="countryDiv" class="inline">
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
