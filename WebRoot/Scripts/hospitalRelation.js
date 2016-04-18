var rootpath = "/lwt_rc";
var pageIndex = 1;
var total = 0;
var pageSize = 5;
var pageCount = 0;

var pageIndexUpper = 1;
var totalUpper = 0;
var pageSizeUpper = 5;
var pageCountUpper = 0;

var id, queryBuilder, ashxParam;

var queryBuilderUpper, ashxParamUpper;

var ajaxData;

// 判断浏览器
var userAgent = navigator.userAgent.toLowerCase();
var browser = {
	version : (userAgent.match(/.+(?:rv|it|ra|ie)[\/: ]([\d.]+)/) || [0, '0'])[1],
	safari : /webkit/.test(userAgent),
	opera : /opera/.test(userAgent),
	msie : /msie/.test(userAgent) && !/opera/.test(userAgent),
	mozilla : /mozilla/.test(userAgent) && !/(compatible|webkit)/.test(userAgent)
};

$(document).ready(function() {
	id = request("id");

	ashxParam = {
		url : rootpath + "/user/hospitalRelation.action",
	};

	ashxParamUpper = {
		url : rootpath + "/user/hospitalRelation.action",
	};
	initUpperQueryBuildForm("receiverQueryBuilderForm");
	initQueryBuildForm("queryBuilderForm");
	initUpperGrid();
	initLowerGrid();

});

function initUpperGrid() {

	$("#receiverTable").empty();
	// 初始化表头部分
	var header = "<tr>";

	header += "<td class=\"headerTd\" width=\"300px\">医院名称</td>";
	header += "<td class=\"headerTd\" width=\"300px\">所属省份</td>";
	header += "<td class=\"headerTd\" width=\"300px\">移除医院</td>";

	header += "</tr>";
	if (browser.msie)
		header += "</body>";
	$("#receiverTable").append(header);

	upperPageClick();
	bindLowerPageButton();

}

function upperPageClick() {

	var submitData = {
		"action" : "04",
		"callerId" : id,
		"pageSize" : pageSizeUpper,
		"pageIndex" : pageIndexUpper
	};

	// 将查询条件和其它请求参数组装
	if (queryBuilderUpper != null)
		$.each(queryBuilderUpper, function(i, n) {
			submitData[queryBuilderUpper[i].name] = queryBuilderUpper[i].value;
		});

	$.ajax({
		
		type : "post",
		dataType : "json",
		url : ashxParamUpper.url,
		data : submitData,
		success : function(message) {
			totalUpper = message.totalCount;
			var data = message.data;

			pageIndexUpper = parseInt(pageIndexUpper);
			$("#receiverTable tr").not($("#receiverTable tr")[0]).remove();
			if (totalUpper == null || totalUpper == 0) {
				noUpperResult();
				return;
			} else if (data.length == 0) {
				if (pageIndexUpper > 1) {
					pageIndexUpper--;
					upperPageClick();
					return;
				} else {
					noUpperResult();
					return;
				}
			}
			$(".receiverInf").html("可接受 " + data[0]["callerHospitalName"] + " 会诊请求的医院列表");
			hasUpperResult();
			$.each(data, function(a, n) {

				var row = "";

				if (browser.msie)// IE浏览器需要加上tbody标签，方能动态添加
					row = "<tbody>";

				row += "<tr>";

				row += "<td class=\"contentTd\" style=\"text-align:left\">";
				row += n["receiverHospitalName"];
				row += "</td>";

				row += "<td class=\"contentTd\" style=\"text-align:left\">";
				if (n["receiverProvince"] != null)
					row += n["receiverProvince"];
				row += "</td>";

				row += "<td class=\"actionTd\" style=\"text-align:center\">";
				row += "<span id='selectAction'>[移除]</span>";
				row += "</td>";

				row += "</tr>";

				if (browser.msie)
					row += "</tbody>";

				row = $(row);

				row.attr("id", n["receiverId"]);

				row.find("#selectAction").unbind("click");
				row.find("#selectAction").click(function() {
					var recordId = row.attr("id");
					removeRight(recordId);
				});

				$("#receiverTable").append(row);
			});

			if (totalUpper % pageSizeUpper == 0)
				pageCountUpper = totalUpper / pageSizeUpper;
			else
				pageCountUpper = parseInt(totalUpper / pageSizeUpper) + 1;

			$("#receiverPageCountSpan").text(pageSizeUpper);
			$("#receiverTotalCountSpan").text(totalUpper);
			$("#revceiverPageIndexSpan").text(pageIndexUpper + "/" + pageCountUpper);

		},

		error : function(XMLHttpRequest, textStatus, errorThrown) {

		}
	});
}

function bindUpperPageButton() {

	$("#receiverFirstBut").attr("href", "javascript:void(0)");
	$("#receiverPreviousBut").attr("href", "javascript:void(0)");
	$("#receiverNextBut").attr("href", "javascript:void(0)");
	$("#receiverLastBut").attr("href", "javascript:void(0)");
	$("#receiverGotoBut").attr("href", "javascript:void(0)");

	$("#receiverFirstBut").unbind("click");
	$("#receiverPreviousBut").unbind("click");
	$("#receiverNextBut").unbind("click");
	$("#receiverLastBut").unbind("click");
	$("#receiverGotoBut").unbind("click");

	// 第一页
	$("#receiverFirstBut").click(function() {
		if (pageIndexUpper != 1) {
			pageIndexUpper = 1;
			upperPageClick();
		}
	});

	// 上一页
	$("#receiverPreviousBut").click(function() {
		if (pageIndexUpper != 1) {
			pageIndexUpper--;
			upperPageClick();
		}
	});

	// 最后一页
	$("#receiverLastBut").click(function() {
		if (pageIndexUpper != pageCountUpper) {
			pageIndexUpper = pageCountUpper;
			upperPageClick();
		}
	});

	// 下一页
	$("#receiverNextBut").click(function() {
		if (pageIndexUpper != pageCountUpper) {
			pageIndexUpper++;
			upperPageClick();
		}
	});

	// 跳转到
	$("#receiverGotoBut").click(function() {
		var gotoPageUpper = $("#receiverPagerInput").val();

		var r = /^[0-9]*[1-9][0-9]*$/;// 正整数

		if (!r.test(gotoPageUpper)) {
			alert("请输入合法数！！！");
			return false;
		}
		if (eval(gotoPageUpper) < 1 || eval(gotoPageUpper) > pageCount) {
			alert("页码超出范围！！！");
			return false;
		}

		var intGotoPageUpper = parseInt(gotoPageUpper);
		pageIndexUpper = intGotoPageUpper;
		upperPageClick();
		return false;
	});
}

function initLowerGrid() {

	$("#contentTable").empty();
	// 初始化表头部分
	var header = "<tr>";

	header += "<td class=\"headerTd\" width=\"300px\">医院名称</td>";
	header += "<td class=\"headerTd\" width=\"300px\">所属省份</td>";
	header += "<td class=\"headerTd\" width=\"300px\">接收权限</td>";

	header += "</tr>";
	if (browser.msie)
		header += "</body>";
	$("#contentTable").append(header);

	lowerPageClick();
	bindLowerPageButton();

}

function lowerPageClick() {

	var submitData = {
		"callerId" : id,
		"action" : "getNotCallerHospitalList",
		"pageSize" : pageSize,
		"pageIndex" : pageIndex
	};

	// 将查询条件和其它请求参数组装
	if (queryBuilder != null)
		$.each(queryBuilder, function(i, n) {
			submitData[queryBuilder[i].name] = queryBuilder[i].value;
		});

	$.ajax({
		
		type : "post",
		dataType : "json",
		url : ashxParam.url,
		data : submitData,
		success : function(message) {
			total = message.totalCount;
			var data = message.data;
			pageIndex = parseInt(pageIndex);
			$("#contentTable tr").not($("#contentTable tr")[0]).remove();
			if (total == null || total == 0) {
				noResult();
				return;
			} else if (data.length == 0) {
				if (pageIndex > 1) {
					pageIndex--;
					lowerPageClick();
					return;
				} else {
					noResult();
					return;
				}
			}
			hasResult();
			$.each(data, function(a, n) {

				var row = "";

				if (browser.msie)// IE浏览器需要加上tbody标签，方能动态添加
					row = "<tbody>";

				row += "<tr>";

				row += "<td class=\"contentTd\" style=\"text-align:left\">";
				row += n[1];
				row += "</td>";

				row += "<td class=\"contentTd\" style=\"text-align:left\">";
				if (n[2] != null)
					row += n[2];
				row += "</td>";

				row += "<td class=\"actionTd\" style=\"text-align:center\">";
				row += "<span id='selectAction'>[选择]</span>";
				row += "</td>";

				row += "</tr>";

				if (browser.msie)
					row += "</tbody>";

				row = $(row);

				row.attr("id", n[0]);

				row.find("#selectAction").unbind("click");
				row.find("#selectAction").click(function() {
					var recordId = row.attr("id");
					grantRight(recordId);

				});

				$("#contentTable").append(row);
			});

			if (total % pageSize == 0)
				pageCount = total / pageSize;
			else
				pageCount = parseInt(total / pageSize) + 1;

			$("#pageCountSpan").text(pageSize);
			$("#totalCountSpan").text(total);
			$("#pageIndexSpan").text(pageIndex + "/" + pageCount);

		},

		error : function(XMLHttpRequest, textStatus, errorThrown) {

		}
	});
}

function bindLowerPageButton() {

	$("#firstBut").attr("href", "javascript:void(0)");
	$("#previousBut").attr("href", "javascript:void(0)");
	$("#nextBut").attr("href", "javascript:void(0)");
	$("#lastBut").attr("href", "javascript:void(0)");
	$("#gotoBut").attr("href", "javascript:void(0)");

	$("#firstBut").unbind("click");
	$("#previousBut").unbind("click");
	$("#nextBut").unbind("click");
	$("#lastBut").unbind("click");
	$("#gotoBut").unbind("click");

	// 第一页
	$("#firstBut").click(function() {
		if (pageIndex != 1) {
			pageIndex = 1;
			lowerPageClick();
		}
	});

	// 上一页
	$("#previousBut").click(function() {
		if (pageIndex != 1) {
			pageIndex--;
			lowerPageClick();
		}
	});

	// 最后一页
	$("#lastBut").click(function() {
		if (pageIndex != pageCount) {
			pageIndex = pageCount;
			lowerPageClick();
		}
	});

	// 下一页
	$("#nextBut").click(function() {
		if (pageIndex != pageCount) {
			pageIndex++;
			lowerPageClick();
		}
	});

	// 跳转到
	$("#gotoBut").click(function() {
		var gotoPage = $("#pagerInput").val();

		var r = /^[0-9]*[1-9][0-9]*$/;// 正整数

		if (!r.test(gotoPage)) {
			alert("请输入合法数！！！");
			return false;
		}
		if (eval(gotoPage) < 1 || eval(gotoPage) > pageCount) {
			alert("页码超出范围！！！");
			return false;
		}

		var intGotoPage = parseInt(gotoPage);
		pageIndex = intGotoPage;
		lowerPageClick();
		return false;
	});
}

function grantRight(selectRecordId) {

	$.ajax({
		
		type : "post",
		data : {
			"callerId" : id,
			"receiverId" : selectRecordId,
			"action" : "01"
		},
		url : ashxParam.url,
		success : function(message) {
			upperPageClick();
			lowerPageClick();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {

		}
	});

}

function removeRight(selectRecordId) {
	$.ajax({
		
		type : "post",
		data : {
			"callerId" : id,
			"receiverId" : selectRecordId,
			"action" : "02"
		},
		url : ashxParam.url,
		success : function(message) {
			upperPageClick();
			lowerPageClick();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {

		}
	});

}

// 初始化查询条件表单
function initQueryBuildForm(formId) {
	$("#" + formId).submit(function(e) {
		pageIndex = 1; // 初始化页面索引
		e.preventDefault();
		queryBuilder = $("#" + formId).serializeArray();
		lowerPageClick();
		return false;
	});
}

// 初始化查询条件表单
function initUpperQueryBuildForm(formId) {
	$("#" + formId).submit(function(e) {
		pageIndex = 1; // 初始化页面索引
		e.preventDefault();
		queryBuilderUpper = $("#" + formId).serializeArray();
		upperPageClick();
		return false;
	});
}

// 截取页面传递过来的URL参数
function request(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}

function hasResult() {
	$(".queFor").removeClass('hidden');
	$(".main").removeClass('hidden');
	$("#noResult").addClass("hidden");
}

function noResult() {
	// $(".queFor").addClass('hidden');
	$('#isRunning').addClass('hidden');
	$(".main").addClass('hidden');
	$("#noResult").removeClass("hidden");
}

function hasUpperResult() {
	$('.receiverInf').removeClass('hidden');
	$(".receiverQueFor").removeClass('hidden');
	$(".receiverMain").removeClass('hidden');
	$("#noReceiverResult").addClass("hidden");
}

function noUpperResult() {
	$('.receiverInf').addClass('hidden');
	// $(".receiverQueFor").addClass('hidden');
	$('#isReceiverRunning').addClass('hidden');
	$(".receiverMain").addClass('hidden');
	$("#noReceiverResult").removeClass("hidden");
}

function errorMessage(message) {
	$("#noResult").removeClass("hidden");
	$("#noResult").html(message);
}