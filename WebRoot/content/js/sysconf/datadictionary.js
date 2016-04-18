var $panel_div = $('<div>').css({'padding':'18px 8px 8px 8px'})
	   .append($('<table>').addClass("tbsad").append($('<tbody>')
	   .append($('<tr>').append($('<td>').text('模块类型：').addClass('ttle'))
	   					.append($('<td>').width(282)
						.append("")))
	   .append($('<tr>').append($('<td>').text('自定义编码：').addClass('ttle'))
	   					.append($('<td>').append($('<input type="text" value="">').val("").width(251).addClass("txt ht24"))))
	   .append($('<tr>').append($('<td>').text('内容：').addClass('ttle'))
	   					.append($('<td>').append($('<input type="text" value="">').val("").width(251).addClass("txt ht24"))))
	   .append($('<tr>').append($('<td>').text('描述：').addClass('ttle'))
	   					.append($('<td>').append($('<input type="text" value="">').val("").width(251).addClass("txt ht24"))))
	   .append($('<tr>').append($('<td colspan="4">').css('text-align','center')
	   					.append($('<input type="button" value="保存">').addClass("btn").width(65).height(24).css('margin-right','15px'))
						.append($('<input type="button" value="重置">').addClass("btn").width(65).height(24))))																			
));
var pagin = new pagination();
function filltypelist(selectId){
	if (pagin.setprocess)
		return;
	pagin.setprocess = true;
	$.lwt_ajax(rootpath + "/dict/dictaction!findAllType.action", {}, call_back_fill_dictionary);

	function call_back_fill_dictionary(return_obj) {
		pagin.setprocess = false;
		if (return_obj.msg == "SUCCESS") {
			var list = ConvertObjectList(return_obj.typelist);
			var $selectdict = makeselect1(list,{'id':'cbxtypelist','height':'18px','width':'115px'},selectId);
			$panel_div.find("tr:eq(0)>td:eq(1)").empty().append($selectdict);
			$.checkbox({'id':'cbxtypelist'});
		}
	}
	
}
function addnewpage(opt,dictId) {
	
	var cbx_dict = $panel_div.find('div[id="cbxtypelist"]>ul:eq(0)');
	var txt_code = $.trim($panel_div.find('input[type="text"]:eq(0)').val());
	var txt_name = $.trim($panel_div.find('input[type="text"]:eq(1)').val());
	var txt_desc = $.trim($panel_div.find('input[type="text"]:eq(2)').val());
	
	if (cbx_dict.data("vid") == undefined 
			|| cbx_dict.data("vid") == null)
		return alert("请选择字典类型");

	if (txt_code == '')
		return alert("请输入自定义编码");

	if (txt_name == '')
		return alert("请输入内容描述");
	
	if(dictId == undefined || dictId == null)
		dictId = "";
	
	var p = {
		"dictionary.dictionarytype.id" : cbx_dict.data("vid"),
		"dictionary.code" : txt_code,
		"dictionary.name" : txt_name,
		"dictionary.description" : txt_desc,
		"dictionary.id" : dictId
	};
	var ajax_url = rootpath + "/dict/dictaction!onCreate.action";
	if (opt == "update") {
		ajax_url = rootpath + "/dict/dictaction!onUpdate.action";
	}

	if (pagin.setprocess)
		return;
	pagin.setprocess = true;

	$.lwt_ajax(ajax_url, p, call_back_add_new_page);

	function call_back_add_new_page(return_obj) {
		pagin.setprocess = false;
		if (return_obj.msg == "SUCCESS") {
			alert("操作已成功！");
			getpagelist();
			$('#modulewindowclose').trigger('click');
			$('#newclinic_panel .title').find('img:eq(0)').trigger('click');
		} else
			return alert(return_obj.msg);
	}
}
function newEventHandler(opt,dictId) {
	$panel_div.find('input:[type="button"]:eq(0)').click(function() {
		addnewpage(opt,dictId);
	});
	var $input = $panel_div.find('input:[type="text"]');
	if(opt == "update"){
		$panel_div.find('input:[type="button"]:eq(1)').val("关闭").unbind('click').click(function() {
			$input.val("");
			$('#modulewindowclose').trigger('click');
		});	
	}
	else {
		$panel_div.find('input:[type="button"]:eq(1)').val("重置").unbind('click').click(function() {
			$input.val("");
			$input.parent().find('img').remove();
		});
	}
	
	$input.blur(function() {
		if ($(this).val() == '')
			$(this).parent().find('img').remove().end().append(
					$('<img>').attr('src',
							rootpath + '/content/img/ico/err.png').css(
							'margin-left', '8px'));
		else
			$(this).parent().find('img').remove().end().append(
					$('<img>').attr('src',
							rootpath + '/content/img/ico/right.png').css(
							'margin-left', '8px'));
	});
}

function getpagelist() {
	if (pagin.setprocess)
		return;
	pagin.setprocess = true;

	var p = {
		"currentpage" : pagin.current_page,
		"pagesize" : pagin.page_size
	};
	$.lwt_ajax(rootpath + "/dict/dictaction!findAll.action", p,
			call_back_page_list);

	function call_back_page_list(rtn) {
		pagin.setprocess = false;
		if(rtn.msg!="SUCCESS")
			return alert(rtn.msg);
		
		filldictionarylist(rtn.typelist);
		pagin.current_page = rtn.currentpage;
		pagin.page_size = rtn.pagesize;
		pagin.max_count = rtn.maxCount;
		pagin.total_pagecount = Math.ceil(rtn.maxCount / rtn.pagesize);

		maketable(rtn);
	}
}
function filldictionarylist(typelist) {
	if(typelist == undefined || typelist == null)
		return;
	
	var list = ConvertObjectList(typelist);
	var $selectdict = makeselect1(list,{'id':'cbxdictionary','height':'18px','width':'100px'});
	$("#sltarea").empty().append($selectdict);
	$.checkbox({'id':'cbxdictionary'});	
}
function ConvertObjectList(array) {
	var Obj = {};
	$.each(array,function(i){
		var Objarray = {};
		Objarray["code"] = array[i].id;
		Objarray["name"] = array[i].description;
		Obj[i] = Objarray;
	});
	return Obj;
}

function maketable(rtn) {
	pagin.setprocess = false;
	var pagelist = rtn.pagelist;

	var $pg = $('#pageleft').find('span');
	$pg.eq(0).text(pagin.max_count);
	$pg.eq(1).text(
			pagin.current_page + "/"
					+ Math.ceil(pagin.max_count / pagin.page_size));
	$pg.eq(2).text(pagin.page_size);

	var $body = $('<tbody>');

	for ( var i = 0; i < pagelist.length; i++) {
		var $tr = $('<tr>');
		$tr.append($('<td>').width(60).text((pagin.current_page - 1) * pagin.page_size + i + 1).addClass('center'))
			.append($('<td>').width(120).text(pagelist[i].dictionarytype.name))
			.append($('<td>').width(120).text(convertnull(pagelist[i].dictionarytype.description)).addClass("center"))
			.append($('<td>').width(120).text(pagelist[i].code).addClass("center"))
			.append($('<td>').width(120).text(pagelist[i].name))
			.append($('<td>').width(260).text(convertnull(pagelist[i].description)))
			.append($('<td>').width(80).text(formattime(pagelist[i].addTime)).addClass('center'))
			.append($('<td>').width(80).text(formattime(pagelist[i].updateTime)).addClass('center'))
			.append($('<td>').data('dictId',pagelist[i].id).width(100).addClass('optcls')
					.append($('<a>').css({'margin-right' : '10px'}).text('[修改]')).append($('<a>').text("[删除]")));

		$tr.find('td:eq(8)>a:eq(0)').click(				
				function() {
					$('#cbxdictionary').css("z-index","100");
					if (pagin.setprocess)
						return;
					pagin.setprocess = true;					
					var dictId = $(this).parent().data('dictId');					
					$.lwt_ajax(rootpath
							+ "/dict/dictaction!findByid.action", {"id" : dictId}, call_back_findone);
					function call_back_findone(return_obj) {
						pagin.setprocess = false;
						if (return_obj.msg == "SUCCESS") {
							var dict = return_obj.dictionary;
							filltypelist(dict.dictionarytype.id);
							$panel_div.find('input:[type="text"]:eq(0)').val(dict.code);
							$panel_div.find('input:[type="text"]:eq(1)').val(dict.name);
							$panel_div.find('input:[type="text"]:eq(2)').val(dict.description);
							
							$.modelWindow({
								handler : 'newdictionary',
								height : 330,
								width : 435,
								bgvisibel : true,
								title : '修改字典',
								html : $panel_div
							});
							$panel_div.find('td>img').remove();
							
							
							newEventHandler("update",dictId);
						}
					}
				});
		$tr.find('td:eq(8)>a:eq(1)').click(
				function() {
					if (confirm("确定要删除当前记录吗?")) {
						if (pagin.setprocess)
							return;
						pagin.setprocess = true;
						$.lwt_ajax(rootpath
								+ "/dict/dictaction!onDelete.action",{"id" : $(this).parent().data('dictId')}, call_back_delete);
					}
					function call_back_delete(return_obj) {
						pagin.setprocess = false;
						if (return_obj.msg == "SUCCESS") {
							alert("删除成功");
							getpagelist();
						} else
							return alert(return_obj.msg);
					}
				});
		$body.append($tr);
	}

	$('#tbl_pagelist').find('tbody').replaceWith($body);
	$('#tbl_pagelist').find('tr').hover(function(){
		$(this).css("background","#ccc");
	},function(){
		$(this).css("background","transparent");
	});
}
function searchmodule(){
	var serarch_key = $('#txt_serarch_key').val();
	if(serarch_key == "请输入查询关键字")
		serarch_key = "";
	
	var p = {
			"currentpage" : pagin.current_page,
			"pagesize" : pagin.page_size,
			"kw":serarch_key
		};
	$.lwt_ajax(rootpath	+ "/dict/dictaction!findKwAll.action",p, call_back_query_module_key);
	
	function call_back_query_module_key(rtn){
		pagin.current_page = rtn.currentpage;
		pagin.page_size = rtn.pagesize;
		pagin.max_count = rtn.maxCount;
		pagin.total_pagecount = Math.ceil(rtn.maxCount / rtn.pagesize);

		maketable(rtn);
	}
}
function finddict() {
	var cbx_dict = $("#cbxdictionary >ul:eq(0)");
	var txt_kw = $.trim($('#txt_serarch_key').val());
	if(cbx_dict.data("vid")==undefined || cbx_dict.data("vid") == null || cbx_dict.data("vid") == -1)
		cbx_dict.data("vid","");
	if(txt_kw == "请输入查询关键字")
		txt_kw = "";
	
	if (pagin.setprocess)
		return;
	pagin.setprocess = true;
	var p = {"typeName" : cbx_dict.data("vid"),
			"kw" : txt_kw,
			"currentpage" : pagin.current_page,
			"pagesize" : pagin.page_size
	};
	$.lwt_ajax(rootpath	+ "/dict/dictaction!findKw.action", p, call_back_find_kw);
	
	function call_back_find_kw(return_obj) {
		pagin.setprocess = false;
		if (return_obj.msg == "SUCCESS") {
			pagin.current_page = return_obj.currentpage;
			pagin.page_size = return_obj.pagesize;
			pagin.max_count = return_obj.maxCount;
			pagin.total_pagecount = Math.ceil(return_obj.maxCount / return_obj.pagesize);

			maketable(return_obj);
		}else {
				return alert(rtn.msg);
		}
	}
}
$(function() {
	getpagelist();
	
	$('#btn_new_dict').click(function() {		
		$panel_div.find('td>img').remove();
		$('#cbxdictionary').css("z-index","100");
		filltypelist();		
		$.modelWindow({
			handler : 'newdictionary',
			height : 330,
			width : 435,
			bgvisibel : true,
			title : '添加新页面',
			html : $panel_div
		});
		
		$.checkbox({'id':'cbx_typelist'});
				
		$panel_div.find('input[type="text"]').val("");

		newEventHandler();
	});
	$('#txt_serarch_key').click(function() {
		if ($(this).val() == "请输入查询关键字")
			$(this).val("").css("color", "#191919");
		else {
			$(this).blur(function() {
				if ($(this).val() == "") {
					$(this).val("请输入查询关键字").css("color", "#ccc");
				}
			});
		}
	}).keydown(function(event) {
		if (event.keyCode == 13)
			finddict();
	});
	$('#btn_serach_dictionary').click(function() {		
		finddict();
	});
	
	pagin.BindpagerEvent();
});