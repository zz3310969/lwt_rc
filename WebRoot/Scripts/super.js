var viewDetail = false;
var viewCommu = false;
var hospitalRelation = false;
var rootpath = "/lwt_rc";
var pageIndex = 1;
var total = 0;
var pageSize = 10;
var pageCount = 0;
var queryBuilder, ashxParam, columnInfo, addPage, editPage, detailPage;
var selectRows = new Array();
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
	// 禁用右键
	/*
	 * $(document).bind("contextmenu", function (e) { return false; });
	 */

	$("#contentTable").on("click", "input[name='childCheckbox']", function(event) {
		var recordId = $(this).parent().parent().attr("id");
		if (browser.msie)
			recordId = $(this).parent().parent().parent().attr("id");
		if ($(this).get(0).checked) {
			if ($.inArray(recordId, selectRows) == -1)
				selectRows.push(recordId);
		} else {
			if ($.inArray(recordId, selectRows) != -1)
				selectRows.splice($.inArray(recordId, selectRows), 1);
		}
	});

	$("#contentTable").on("click", "input[name='headerCheckbox']", function(event) {
		if ($(this).get(0).checked) {
			$("#contentTable input[name='childCheckbox']").each(function(i, n) {
				var recordId = $(this).parent().parent().attr("id");
				if (browser.msie)
					recordId = $(this).parent().parent().parent().attr("id");
				$(this).get(0).checked = true;
				if ($.inArray(recordId, selectRows) == -1)
					selectRows.push(recordId);
			});
		} else {
			$("#contentTable input[name='childCheckbox']").each(function(i, n) {
				var recordId = $(this).parent().parent().attr("id");
				if (browser.msie)
					recordId = $(this).parent().parent().parent().attr("id");
				$(this).get(0).checked = false;
				if ($.inArray(recordId, selectRows) != -1)
					selectRows.splice($.inArray(recordId, selectRows), 1);
			});
		}
	});
});

function initNewGrid(queryBuilderParam, ashxParamParam, columnInfoParam, addPageParam, editPageParam, detailPageParam) {

	queryBuilder = queryBuilderParam;
	ashxParam = ashxParamParam;
	columnInfo = columnInfoParam;
	addPage = addPageParam;
	editPage = editPageParam;
	detailPage = detailPageParam;
	pageSize = ashxParam.pageSize;
	$("#contentTable").empty();
	var header = "";
	header = "<tr>";
	if (browser.msie)
		header = "<thead><tr>";
	for ( var i = 0; i < columnInfo.length; i++) {
		if (columnInfo[i].width != undefined)
			header += "<th class=\"headerTd\" width=\"" + columnInfo[i].width + "\">" + columnInfo[i].columnText + "</td>";
		else
			header += "<th class=\"headerTd\">" + columnInfo[i].columnText + "</td>";
	}
	header += "<th width=\"150px\" class=\"headerTd\">基本操作</th></tr>";
	if (browser.msie)
		header += "<thead>";
	$("#contentTable").append(header);
	pageClick();
	bindPageButton();

	$("#addAction").unbind("click");
	$("#addAction").click(function() {
		popUpAddPage(addPageParam);
	});
}

function pageClick() {
	if (ashxParam.action == undefined)
		ashxParam.action = "04";
	var submitData = {
		"action" : ashxParam.action,
		"pageSize" : pageSize,
		"pageIndex" : pageIndex
	};

	if (queryBuilder != null)
		$.each(queryBuilder, function(i, n) {
			submitData[queryBuilder[i].name] = queryBuilder[i].value;
		});

	$.ajax({
		type : "post",
		dataType : "json",
		url : ashxParam.url,
		data : submitData,
		beforeSend : function(XMLHttpRequest) {
			$('#isRunning').removeClass('hidden');
		},
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
					pageClick();
					return;
				} else {
					noResult();
					return;
				}
			}
			hasResult();
			$.each(data, initTableContent);

			if (total % pageSize == 0)
				pageCount = total / pageSize;
			else
				pageCount = parseInt(total / pageSize) + 1;

			$("#pageCountSpan").text(pageSize);
			$("#totalCountSpan").text(total);
			$("#pageIndexSpan").text(pageIndex + "/" + pageCount);
			$('#isRunning').addClass('hidden');

			return false;
		},

		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$('#isRunning').addClass('hidden');
			// alert(textStatus);
		}
	});
}

function initTableContent(a, n) {
	// IE浏览器需要加上tbody标签，方能动态添加
	var row = browser.msie ? "<tbody><tr>" : "<tr>";
	var col = "";

	for ( var i = 0; i < columnInfo.length; i++) {
		col = columnInfo[i].columnName;
		if (columnInfo[i].style != undefined)
			row += "<td class=\"contentTd\" style=\"" + columnInfo[i].style + "\">";
		else
			row += "<td class=\"contentTd\">";

		if (col.indexOf('.') != -1) {
			// 获得对象属性名称
			pro = col.substring(col.lastIndexOf('.') + 1, col.length);
			// 获得引用对象名称
			refName = col.substring(0, col.lastIndexOf('.'));
			// 遍历，以处理多层嵌套的情况
			var value = n[refName];
			if (value != undefined) {
				var array = col.split('.');
				for (a = 0; a < array.length - 1; a++) {
					obj = array[a];
					value = n[obj];
				}
				if (value[pro] != undefined)
					row += value[pro] + "</td>";
				else
					row += "</td>";
			} else {
				row += "</td>";
			}
		} else if (n[col] == null)
			row += "</td>";
		else
			row += n[col] + "</td>";
	}
	
	row += "<td class='actionTd'>";
	if (viewCommu)
		row += "<span id='commuAction'>[会诊权限]</span>&nbsp;&nbsp;";
	if (viewDetail)
		row += "<span id='editAction'>[查看详细]</span>";
	else
		row += "<span id='editAction'>[编辑]</span>";
	row += "&nbsp;&nbsp;";
	row += "<span id='deleteAction'>[删除]</span>";
	row += "</td></tr>";

	if (browser.msie)
		row += "</tbody>";

	row = $(row);

	row.attr("id", n["primaryKey"]);

	row.find("span").unbind("click");

	row.find("#editAction").click(function() {
		var recordId = row.attr("id");
		popUpEditPage(recordId, editPage);
	});
	row.find("#deleteAction").click(function() {
		var recordId = row.attr("id");
		deleteRecord(recordId, ashxParam);
	});
	if (viewCommu)
		row.find("#commuAction").click(function() {
			var recordId = row.attr("id");
			popUpHospitalRelationPage(recordId, relationPage);
		});
	$("#contentTable").append(row);
}

function bindPageButton() {

	$("#firstBut").unbind("click");
	$("#previousBut").unbind("click");
	$("#nextBut").unbind("click");
	$("#lastBut").unbind("click");
	$("#gotoBut").unbind("click");

	// 第一页
	$("#firstBut").click(function() {
		if (pageIndex != 1) {
			pageIndex = 1;
			pageClick();
		}
	});

	$("#previousBut").click(function() {
		if (pageIndex != 1) {
			pageIndex--;
			pageClick();
		}
	});

	$("#lastBut").click(function() {
		if (pageIndex != pageCount) {
			pageIndex = pageCount;
			pageClick();
		}
	});

	$("#nextBut").click(function() {
		if (pageIndex != pageCount) {
			pageIndex++;
			pageClick();
		}
	});

	$("#gotoBut").click(function() {
		var gotoPage = $("#pagerInput").val();

		var r = /^[0-9]*[1-9][0-9]*$/;

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
		pageClick();
		return false;
	});
}

function popUpDetailPage(recordId, detailPage) {
	$("#thickboxHelper").remove();
	$("body").append("<a id=\"thickboxHelper\" title='" + detailPage.title + "' href=\"\" class=\"thickbox\"></a>");
	$('#thickboxHelper').attr('href', detailPage.url + "?id=" + recordId + "&keepThis=true&TB_iframe=true&TB_iniframe=true&height=" + detailPage.height + "&width=" + detailPage.width);
	tb_init($("#thickboxHelper"));
	document.getElementById('thickboxHelper').click();
}

function popUpEditPage(recordId, editPage) {
	$("#thickboxHelper").remove();
	$("body").append("<a id=\"thickboxHelper\" title='" + editPage.title + "' href=\"\" class=\"thickbox\"></a>");
	$('#thickboxHelper').attr('href', editPage.url + "?id=" + recordId + "&keepThis=true&TB_iframe=true&TB_iniframe=true&height=" + editPage.height + "&width=" + editPage.width);
	tb_init($("#thickboxHelper"));
	document.getElementById('thickboxHelper').click();
}

function popUpAddPage(addPage) {
	$("#thickboxHelper").remove();
	$("body").append("<a id=\"thickboxHelper\" title='" + addPage.title + "' href=\"\" class=\"thickbox\"></a>");
	$('#thickboxHelper').attr('href', addPage.url + "?keepThis=true&TB_iframe=true&TB_iniframe=true&height=" + addPage.height + "&width=" + addPage.width);
	tb_init($("#thickboxHelper"));
	document.getElementById('thickboxHelper').click();
}

function popUpHospitalRelationPage(recordId, relationPage) {
	$("#thickboxHelper").remove();
	$("body").append("<a id=\"thickboxHelper\" title='" + relationPage.title + "' href=\"\" class=\"thickbox\"></a>");
	$('#thickboxHelper').attr('href', relationPage.url + "?id=" + recordId + "&keepThis=true&TB_iframe=true&TB_iniframe=true&height=" + relationPage.height + "&width=" + relationPage.width);
	tb_init($("#thickboxHelper"));
	document.getElementById('thickboxHelper').click();
}

function initAddPage(formId, ashxUrlParam) {
	$("#" + formId).validationEngine('attach', {
		promptPosition : "topLeft", // OPENNING BOX POSITION,
		// IMPLEMENTED:
		// topLeft, topRight, bottomLeft,
		// centerRight, bottomRight
		validationEventTriggers : "blur",
		inlineValidation : false,
		success : false,
		failure : function() {

		},
		onValidationComplete : function(form, status) {
			if (status == true) {
				var formString = $("#" + formId).serialize() + "&action=01";
				$.ajax({
					type : "post",
					data : formString,
					url : ashxUrlParam.url,
					success : function(message) {
						window.top.tb_remove();
						window.top.frames["frame_content"].frames["mainframe"].pageClick();
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {

						// alert(textStatus);
					}
				});
			}
		}
	});
}

function deleteRecord(recordId, ashxParam) {
	$.ajax({
		async : true,
		type : "post",
		data : {
			"primaryKey" : recordId,
			"action" : "02"
		},
		dataType : "json",
		url : ashxParam.url,
		success : function(data) {
			if (!data["isSuccess"]) {
				errorMessage(data["message"]);
				return;
			}
			if ($.inArray(recordId, selectRows) != -1)
				selectRows.splice($.inArray(recordId, selectRows), 1);
			$("tr #" + recordId).remove(); // 确保惟一性
			pageClick();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// alert(textStatus);
		}
	});
}

function initEditPage(formId, ashxParam) {
	var id = request("id");
	$("#" + formId).validationEngine('attach', {
		promptPosition : "topLeft", // OPENNING BOX POSITION,
		// IMPLEMENTED:
		// topLeft, topRight, bottomLeft,
		// centerRight, bottomRight
		validationEventTriggers : "blur",
		inlineValidation : false,
		success : false,
		failure : function() {
		},
		onValidationComplete : function(form, status) {
			if (status == true) {
				var formString = $("#" + formId).serialize() + "&action=03&id=" + id;
				$.ajax({
					type : "post",
					data : formString,
					url : ashxParam.url,
					success : function(message) {
						window.top.tb_remove();
						window.top.frames["frame_content"].frames["mainframe"].pageClick();

					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						// alert(textStatus);
					}
				});
			}
		}
	});

	$.ajax({
		async : false,
		type : "post",
		data : {
			"primaryKey" : id,
			"action" : "05"
		},
		dataType : "json",
		url : ashxParam.url,
		success : function(data, textStatus) {
			ajaxData = data;
			var form = document.forms[formId];

			for ( var i = 0; i < form.length; i++) {
				var fieldName = form[i].name;
				var array = new Array();
				array = fieldName.split("");

				var prefix = "";
				for ( var n = 0; n < array.length; n++) {
					if (array[n].toLocaleString().charCodeAt(0) >= 65 && array[n].toLocaleString().charCodeAt(0) <= 90)// 第一个大写字母
					{
						prefix = fieldName.substr(0, n);
						break;
					}
				}

				var tagLength = prefix.length;

				var prop = fieldName.substr(tagLength, 1).toLowerCase() + fieldName.substr(tagLength + 1);

				if (prop.indexOf('.') != -1) {

					pro = prop.substring(prop.lastIndexOf('.') + 1, prop.length);
					refName = prop.substring(0, prop.indexOf('.'));
					// 将属性名的第一个大写字母变为小写，以适应Java实体类中属性名的命名方式
					refName = refName.substr(0, 1).toLowerCase() + refName.substr(1);
					var value = data.data[refName];
					if (value != undefined) {
						var array = prop.split('.');
						for ( var a = 1; a < array.length - 1; a++) {
							obj = array[a];
							// 将属性名的第一个大写字母变为小写，以适应Java实体类中属性名的命名方式
							obj = obj.substr(0, 1).toLowerCase() + obj.substr(1);
							value = value[obj];
							// 将属性名的第一个大写字母变为小写，以适应Java实体类中属性名的命名方式
							value = value.substr(0, 1).toLowerCase() + value.substr(1);
						}
						if (value[pro] != undefined)
							value = value[pro];
						else
							value = null;
					} else {
						value = null;
					}
				} else
					value = data.data[prop];
				switch (prefix) {
					case "text" :
						$("[name='" + fieldName + "']").val(value);
						break;
					case "select" :
						$("select[name='" + fieldName + "']").val(value);
						break;
					case "radio" :
						if (value != null)
							$("[name='" + fieldName + "'][value='" + value + "']").get(0).checked = true;
						break;
					case "textarea" :
						$("textarea[name='" + fieldName + "']").val(value);
						break;
					case "checkbox" :
						$("[name='" + fieldName + "'][value='" + value + "']").get(0).checked = true;
						break;
					default :
						break;
				}
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// alert(textStatus);
		}
	});
}

function initDetailPage(formId, ashxParam) {
	var id = request("id");
	// 初始化表单标签
	$.ajax({
		async : false,
		type : "post",
		data : {
			"id" : id,
			"action" : "05"
		},
		dataType : "json",
		url : ashxParam.url,
		success : function(data, textStatus) {
			ajaxData = data;
			$("#" + formId + " label").each(function(i, n) {
				// 遍历指定form表单中的所有label标签
				var fieldId = $(n).attr("id");
				var prefix = fieldId.substr(5);
				if (prefix.indexOf('.') != -1)// 说明是嵌套对象中的属性
				{
					// 获得对象属性名称
					pro = prefix.substring(prefix.lastIndexOf('.') + 1, prefix.length);
					// 获得第一层引用对象名称
					refName = prefix.substring(0, prefix.indexOf('.'));
					// 遍历，以处理多层嵌套的情况
					var value = data.data[refName];
					if (value != undefined) {
						var array = prefix.split('.');
						for ( var a = 1; a < array.length - 1; a++) {
							obj = array[a];
							value = value[obj];
						}
						if (value[pro] != undefined)
							$(n).html(value[pro]);
					}
				} else
					$(n).html(data.data[prefix]);
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// alert(textStatus);
		}
	});
}

function initDropDownList(selectId, ajaxParam, itemParam) {
	$("#" + selectId + " option").not($("#" + selectId + " option").first()).remove();
	$.ajax({
		method : "post",
		async : false,
		url : ajaxParam.url,
		dataType : "json",
		data : ajaxParam.data,
		success : function(data) {
			$.each(data.data, function(i, n) {
				$("#" + selectId).append("<option value='" + n[itemParam.valueField] + "'>" + n[itemParam.textField] + "</option>");
			});
		}
	});
}

function initQueryBuildForm(formId) {
	$("#" + formId).submit(function(e) {
		$("#noResult").addClass("hidden");
		pageIndex = 1; // 初始化页面索引
		e.preventDefault();
		queryBuilder = $("#" + formId).serializeArray();
		initNewGrid(queryBuilder, ashxParam, columnInfo, addPage, editPage, detailPage);
		return false;
	});
}

function initAutoComplete(textFieldInputId, valueFieldInputId, ajaxParam, itemParam) {
	var cache = {};
	var isSelect = false;
	$("#" + textFieldInputId).unbind("onchange");
	$("#" + textFieldInputId).bind("onchange", function() {
		isSelect = false;
	});
	// 失去输入焦点后，如果显示值为空，则同时清空隐藏值
	$("#" + textFieldInputId).unbind("blur");
	$("#" + textFieldInputId).bind("blur", function() {
		if ($("#" + textFieldInputId).val() == "") {
			$("#" + textFieldInputId).val("");
			$("#" + valueFieldInputId).val("");
		} else {
			// 如果是已经存在缓存中的关键字，也就是输入到文本框中的文字是不能提交的，只能选择后才能提交
			if (!isSelect) {
				$("#" + textFieldInputId).val("");
				$("#" + valueFieldInputId).val("");
			}
		}

	});

	$("#" + textFieldInputId).autocomplete({
		minLength : 2,
		autoFocus : true,
		source : function(request, response) {
			var term = request.term;
			// if (term in cache) {
			// response($.map(cache[term], function (item) {
			// return {
			// value: item[itemParam.textField],
			// label: item[itemParam.textField],
			// submitValue: item[itemParam.valueField]
			// }
			// }));
			// return;
			// }
			ajaxParam.data[textFieldInputId] = request.term;
			$.ajax({
				method : "post",
				url : ajaxParam.url,
				dataType : "json",
				data : ajaxParam.data,
				success : function(data) {
					cache[term] = data.data;
					response($.map(data.data, function(item) {
						return {
							value : item[itemParam.textField],
							label : item[itemParam.textField],
							submitValue : item[itemParam.valueField]
						};
					}));
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {

				}
			});
		},
		select : function(e, ui) {
			$("#" + valueFieldInputId).val(ui.item.submitValue);
			isSelect = true;
		}
	});
}

function request(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}

function hasResult() {
	$(".main").removeClass('hidden');
	$("#noResult").addClass("hidden");
}

function noResult() {
	$('#isRunning').addClass('hidden');
	// $(".main").addClass('hidden');
	$("#noResult").removeClass("hidden");
}
function errorMessage(message) {
	$("#noResult").removeClass("hidden");
	$("#noResult").html(message);
}
