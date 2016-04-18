<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="includeFile.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>医院管理</title>

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
		viewCommu = true;
		var queryBuilder = "";
		var ashxParam = {
			url : rootpath + "/user/hospitalManagement.action",
			pageSize : "15"
		};
		var addPage = {
			title : "添加医院",
			url : "hospitalAdd.jsp",
			width : "800",
			height : "400"
		};
		var editPage = {
			title : "修改医院信息",
			url : "hospitalEdit.jsp",
			width : "800",
			height : "400"
		};
		var detailPage = {};
		relationPage = {
			title : "医院间会诊通讯权限",
			url : "hospitalRelation.jsp",
			width : "800",
			height : "400"
		};
		var columnInfo = [{
			"columnName" : "name",
			"columnText" : "医院名称",
			"width" : "300px",
			"style" : "text-align:left"
		}, {
			"columnName" : "telephone",
			"width" : "100px",
			"columnText" : "联系电话",
			"style" : "text-align:left"
		}, {
			"columnName" : "provinceName",
			"columnText" : "所属省份",
			"style" : "text-align:left",
			"width" : "150px"
		}, {
			"columnName" : "addTime",
			"columnText" : "添加时间",
			"width" : "150px",
			"style" : "text-align:center"
		}];
		initQueryBuildForm("queryBuilderForm");

		initNewGrid(queryBuilder, ashxParam, columnInfo, addPage, editPage, detailPage);
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
			$("#selectCountry option").not($("#selectCountry option").first()).remove();
			$("#cityDiv").removeClass("inline");
			$("#cityDiv").addClass("hidden");
		}

		$("#countryDiv").removeClass("inline");
		$("#countryDiv").addClass("hidden");
		submitForm();
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
		submitForm();
	}
	function countryChange() {
		submitForm();
	}
	function submitForm() {
		$("#queryBuilderForm").submit();
	}
</script>

</head>
<body>
	<div class="container">
		<div class="funBut">
			<br> <input type="button" class="addBtn" id="addAction"
				value="新增医院" />
		</div>
		<div class="queFor" style="text-align:center">
			<form id="queryBuilderForm" action="#">

				医院名称： <input id="textHospitalName" name="textHospitalName"
					type="text" size="13" /> &nbsp;&nbsp;&nbsp; 所属地区： <select
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
					==》 <select id="selectCountry" name="selectCountry"
						onchange="countryChange();">
						<option value="">==请选择县==</option>
					</select>
				</div>
				<input type="submit" size="20" value="查询" /> <input type="reset"
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
