//----
var $panel_module = $('<div>');
var $module_all = $('<div>').addClass("fleft");
var $module_has = $('<div>').addClass("fright");
var $module_btm = $('<div>').addClass("btm");
var $body = $('<tbody>');
var $table = $('<table>').css({"margin":"2px","border-collapse":"collapse"}).append($('<thead>').append($('<tr>').addClass("title").append($('<td>').width(50).text("序号"))
	.append($('<td>').text("页面名称").width(110).addClass("center")).append($('<td>').text("页面地址").width(150).addClass("center")).append($('<td>').css("border-right-width","2px").addClass("center").width(40).text("操作")))).append($body); 
var $table_has = $table.clone().css("margin-left","8px");		
$module_all.append($('<div>').css({"padding":"2px"}).append($('<input type="text" >').css({"margin-right":"2px"}).addClass("txt").addClass("ht24").width(300)).append($('<input type="button" value="查询" class="btn" id="btn_module_all_find">'))
	.append($('<div>').height(290).css({"border":"1px solid #ccc","margin-top":"5px","overflow-y":"scroll"}).append($table)));
$panel_module.append($('<div>').css({"position":"relative","margin-top":"2px"})
	.append($module_all).append($module_has)).append($module_btm);
$module_has.append($('<h1>').text("已选择的包含页面").css({"padding":"10px","font-weight":"normal","margin":"0 2px -4px 2px"})).append($('<div>').height(290).css({"overflow-y":"scroll"}).append($table_has));
$module_btm.append($('<input type="button" value="关闭" id="btn_layer_close">').addClass("btn").css({"float":"right","margin":"3px 5px 0 0"})).append($('<input type="button" value="保存" id="btn_save_power">').addClass("btn").css({"float":"right","margin":"3px 5px 0 0"}));
//end 
var $selectlevel  = $('<div>');
var $selectsort1  = $('<div>');

var $panel_div = $('<div>').css({'padding':'8px'})
	.append($('<table>').addClass("tbsad").append($('<tbody>')
	.append($('<tr>').append($('<td>').text('模块名称：').addClass('ttle')).append($('<td>').width(282)
	.append($('<input type="text" value="">').width(251).addClass("txt").addClass("ht24").val(""))))
	.append($('<tr>').append($('<td>').text('模块描述：').addClass('ttle'))
	.append($('<td>').append($('<input type="text" value="">').val("").width(251).addClass("txt"))))						
	.append($('<tr>').append($('<td>').text('模块等级：').addClass('ttle'))
	.append($('<td>').append($('<div>'))))
	.append($('<tr>').append($('<td>').text('模块排序：').addClass('ttle'))
	.append($('<td>').append($('<div>'))))
	.append($('<tr>').append($('<td>').text('是否公共：').addClass('ttle'))
	.append($('<td>').append($('<input type="radio" name="ispublic" value="1" checked="checked" class="rdo">')).append("是")
	.append($('<input type="radio" name="ispublic" value="2" class="rdo" style="margin-left:15px;"> 否')).append("否")))
	.append($('<tr>').append($('<td colspan="4">').css('text-align','center')
	.append($('<input type="button" value="保存" id="btn_save_module">').addClass("btn").width(65).height(24).css('margin-right','15px'))
	.append($('<input type="button" value="重置">').addClass("btn").width(65).height(24))))																			
));

function fillcbx(sltlevelId,sltsortId){
	
	var pagin = new pagination();
	if (pagin.setprocess)
		return;
	pagin.setprocess = true;
	$.lwt_ajax(rootpath + "/funmode/funmodeaction!findDictTypeLeve.action", {}, call_back_fill_select);

	function call_back_fill_select(return_obj) {
		pagin.setprocess = false;
		if (return_obj.msg == "SUCCESS") {
			var $selectleve = makeselect1(return_obj.dicts,{'id':'cbxlevel','height':'18px','width':'110px'},sltlevelId);
			$panel_div.find("tr:eq(2)>td:eq(1)").empty().append($selectleve.css("float","left"));

			var $selectsort = makeselect1(return_obj.sorts,{'id':'cbxsort','height':'18px','width':'110px'},sltsortId);
			$panel_div.find("tr:eq(3)>td:eq(1)").empty().empty().append($selectsort);
			
			$.checkbox({'id':'cbxlevel'});
			$.checkbox({'id':'cbxsort'});
			
			$.each($selectleve.find("ul li"),function(i){
				
				$(this).click(function(){					
					if($(this).data("vid")!="1" && $(this).data("vid")!="-1"){
						getparentlevel($(this).data("vid"));
					}else{
						$panel_div.find('tr:eq(2)>td:eq(1)').find('div[id="cbxlevelparent"]').parent().remove();
					}
				});
			});	
		}
	}
}
function getparentlevel(levelid,selectId) {
	
if (pagin.setprocess)
	return;
pagin.setprocess = true;
	$.lwt_ajax(rootpath + "/funmode/funmodeaction!findParentModel.action", {"leveId":levelid}, call_back_fill_select);

	function call_back_fill_select(return_obj) {
		pagin.setprocess = false;
		if (return_obj.msg == "SUCCESS") {
			var list = {};
			if(return_obj.pagelist!=undefined && return_obj.pagelist!=null){
				list = ConvertObjectList(return_obj.pagelist);
			}
			var $selectleve2 = makeselect1(list,{'id':'cbxlevelparent','height':'18px','width':'110px'},selectId);
			$selectleve2.css({"float":"left","margin-left":"10px"});
			$('div[id="cbxlevelparent"]').parent().remove();
			$('div[id="cbxlevel"]').parent().parent().append($selectleve2);
			$.checkbox({'id':'cbxlevelparent'});
		}
	}
}
function ConvertObjectList(array) {
	var Obj = {};
	$.each(array,function(i){
		var Objarray = {};
		Objarray["code"] = array[i].moduleId;
		Objarray["name"] = array[i].moduleName;
		Obj[i] = Objarray;
	});
	return Obj;
}
function addnewmodule(opt,moduleId) {
	$panel_div.find('td>img').remove();
	var txt_mobulename = $.trim($panel_div.find('input[type="text"]:eq(0)').val());
	var txt_moduledesc = $.trim($panel_div.find('input[type="text"]:eq(1)').val());
	var cbxlevel = $panel_div.find('div[id="cbxlevel"]>ul:eq(0)');
	var cbxlevelparent =$panel_div.find('div[id="cbxlevelparent"]>ul:eq(0)'); 
	var cbxsort =$panel_div.find('div[id="cbxsort"]>ul:eq(0)');
	var rdo_public = $panel_div.find('input[name="ispublic"][checked]').val();	

	if (txt_mobulename == '')
		return alert("请输入模块名称");

	if (txt_moduledesc == '')
		return alert("请输入模块描述");

	if (cbxlevel.data('vid')==undefined || 
			cbxlevel.attr('vid') == "0")
		return alert("请选择模块等级");
	
	if(cbxlevel.data("vid")=="2"){
		
		if(cbxlevelparent.data("vid") == undefined 
				|| cbxlevelparent.data("vid") == 0){
			return alert("请选择父模块名称");
		};
	}
	if (cbxsort.data('vid')==undefined || 
			cbxlevel.attr('vid') == "0")
		return alert("请选择排序");
	
	if(moduleId == undefined)
		moduleId = "";
	var parentId="";
	if(cbxlevelparent!=undefined)
		parentId = cbxlevelparent.data("vid");
	var sort = cbxsort.data("vid");
	if(!isNaN(sort))
		sort = Number(sort) <10 ? "0" + Number(sort):sort+"";
		
	var p = {
		"mode.moduleId":moduleId,	
		"mode.moduleName" : txt_mobulename,
		"mode.description" : txt_moduledesc,
		"mode.moduleLevel" : cbxlevel.data("vid"),
		"mode.sortField":sort,
		"mode.isPublic":rdo_public,
		"mode.functionmodule.moduleId":parentId
		
	};
	var ajax_url = rootpath + "/funmode/funmodeaction!saveMode.action";
	if (opt == "update") {
		ajax_url = rootpath + "/funmode/funmodeaction!updateMode.action";
	}

	if (pagin.setprocess)
		return;
	pagin.setprocess = true;
	$.lwt_ajax(ajax_url, p, call_back_add_new_page);

	function call_back_add_new_page(return_obj) {
		pagin.setprocess = false;
		if (return_obj.msg == "SUCCESS") {
			alert("操作已成功！");
			$('#modulewindowclose').trigger('click');
			getpagelist();
			$panel_div.find('input[type="button"]:eq(1)').val("关闭").click(function(){				
				$panel_div.find('td>img').remove();
				$('#modulewindowclose').trigger('click');
			});	
		} else
			return alert(return_obj.msg);
	}
}
function newEventHandler(opt,noduleId) {
	$panel_div.find('input:[type="button"]:eq(0)').click(function() {
		addnewmodule(opt,noduleId);
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
var pagin = new pagination();
function getpagelist() {
	if (pagin.setprocess)
		return;
	pagin.setprocess = true;

	var p = {
		"currentpage" : pagin.current_page,
		"pagesize" : pagin.page_size
	};
	$.lwt_ajax(rootpath + "/funmode/funmodeaction!findAll.action", p,
			call_back_page_list);

	function call_back_page_list(rtn) {
		pagin.setprocess = false;
		pagin.current_page = rtn.currentpage;
		pagin.page_size = rtn.pagesize;
		pagin.max_count = rtn.maxCount;
		pagin.total_pagecount = Math.ceil(rtn.maxCount / rtn.pagesize);

		maketable(rtn);
	}
}
function maketable(rtn) {
	pagin.setprocess = false;

	var $pg = $('#pageleft').find('span');
	$pg.eq(0).text(pagin.max_count);
	$pg.eq(1).text(
			pagin.current_page + "/"
					+ Math.ceil(pagin.max_count / pagin.page_size));
	$pg.eq(2).text(pagin.page_size);
	
	var pagelist = rtn.pagelist;
	if(pagelist == undefined) return;
	
	var sortarr = rtn.sorts;
	var dicts = rtn.dicts;
	var isPublic = rtn.isPublic;
	
	var $bodylist = $('<tbody>');
	for ( var i = 0; i < pagelist.length; i++) {
		var $tr = $('<tr>');
		$tr.append(
				$('<td>').width(60).text((pagin.current_page - 1) * pagin.page_size + i + 1).addClass('center'))				
				.append($('<td>').text(pagelist[i].moduleName))
				.append($('<td>').text(TranslationColumn(dicts,pagelist[i].moduleLevel)).addClass("center"))
				.append($('<td>').text(TranslationColumn(sortarr,pagelist[i].sortField)).addClass("center"))
				.append($('<td>').text(TranslationColumn(isPublic,pagelist[i].isPublic)).addClass("center"))
				.append($('<td>').text(convertnull(pagelist[i].description)))
				.append($('<td>').text(formattime(pagelist[i].addTime)).addClass('center'))
				.append($('<td>').text(formattime(pagelist[i].updateTime)).addClass('center'))
				.append($('<td>').data('moduleId', pagelist[i].moduleId).addClass('optcls')
				.append($('<a>').css({'margin-right' : '10px'}).text('[包含页面]'))
				.append($('<a>').css({'margin-right' : '10px'}).text('[修改]'))
				.append($('<a>').text("[删除]")));
		
		$tr.find('td:eq(8)>a:eq(0)').click(function() {
			$module_btm.find('input[id="btn_save_power"]').data("moduleId",$(this).parent().data('moduleId'));
			$module_btm.find('input[id="btn_layer_close"]').click(function(){
				$('#modulewindowclose').trigger('click');
			});
			editpower($(this).parent().data('moduleId'));	
		});

		$tr.find('td:eq(8)>a:eq(1)').click(	function() {
			update($(this).parent().data('moduleId'));
		});
			
		$tr.find('td:eq(8)>a:eq(2)').click(	function() {
			dele($(this).parent().data('moduleId'));
		});
		
		$bodylist.append($tr);
	}

	$('#tbl_pagelist').find('tbody').replaceWith($bodylist);
	$('#tbl_pagelist').find('tr').hover(function(){
		$(this).css("background","#ccc");
	},function(){
		$(this).css("background","transparent");
	});
}
function BindSelectPageALLEvent(){
	$.each($table.find("tr td>img"),function(i){
		var $img = $(this);
		$img.unbind("click").click(function(){
			$img.unbind("click").attr("src",rootpath+"/content/img/ico/remove.png").data("idx",i).parent().parent().appendTo($table_has.find("tbody"));
			BindSelectPageHasEvent();					
		});
	});	
}
function BindSelectPageHasEvent(){
	$.each($table_has.find("tr td>img"),function(i){
		var $img = $(this);
		$img.unbind("click").click(function(){
			$img.unbind("click").attr("src",rootpath+"/content/img/ico/add1.png").parent().parent().appendTo($table.find("tbody"));
			BindSelectPageALLEvent();					
		});
	});
}
function makepowertable(pagelist,opt)
{
	var $body2 = $('<tbody>');
	var imgurl = rootpath+"/content/img/ico/add1.png";
	if(!opt)
		imgurl = rootpath+"/content/img/ico/remove.png";
	
	if(pagelist == null)return;
	for(var i=0;i<pagelist.length;i++){
		$body2.append($('<tr>')
				.append($('<td>').width(50).text(i+1).addClass("center"))
				.append($('<td>').width(110).text(pagelist[i].pageName)).append($('<td>').width(150).text(pagelist[i].url))
				.append($('<td>').attr("id",pagelist[i].pageId).data("id",pagelist[i].pageId).addClass("center").append($('<img>').css("cursor","pointer").attr("src",imgurl))));
	}
	if(opt){
		$table.find("tbody").remove().end().append($body2);
		BindSelectPageALLEvent();
	}else{
		$table_has.find("tbody").remove().end().append($body2);
		BindSelectPageHasEvent();
	}
}
function getalreadypower(moduleId) {
	if (pagin.setprocess)
		return;
	pagin.setprocess = true;
	$.lwt_ajax(rootpath + "/funmode/funmodeaction!findByModeId.action",{'modeId':moduleId},call_back_queryallpage);
	function call_back_queryallpage(return_obj) {
		pagin.setprocess = false;
		if(return_obj.msg == "SUCCESS"){
			$table_has.find("tbody").remove();//先清空现有权限,重新赋值
			var pagelist = return_obj.webpases;
			makepowertable(pagelist,false);
		}
	}
}
function editpower(moduleId) {
	
	var pagin1 = new pagination();
	if (pagin1.setprocess)
		return;
	pagin1.setprocess = true;
	
	$.lwt_ajax(rootpath + "/pages/webpagemanager!findKwAll.action",{},call_back_queryallpage);
	function call_back_queryallpage(return_obj) {
		pagin1.setprocess = false;
		if(return_obj.msg == "SUCCESS"){
			
			var pagelist = return_obj.pagelist;
			makepowertable(pagelist,true);
		}
	}
	getalreadypower(moduleId);
	$.modelWindow({
		handler : 'editpower',
		height : 420,
		width : 800,
		bgvisibel : true,
		title : '包含页面',
		html : $panel_module
	});
	
	
	//查询
	$module_all.find('input[id="btn_module_all_find"]').click(function(){
		
		var ipttext = $(this).siblings().eq(0);
	
		if (pagin1.setprocess)
			return;
		pagin1.setprocess = true;
		
		$.lwt_ajax(rootpath + "/pages/webpagemanager!findKwAll.action",{"kw":ipttext.val()},call_back_queryallpage);
		function call_back_queryallpage(return_obj) {
			pagin1.setprocess = false;
			if(return_obj.msg == "SUCCESS"){
				var pagelist = return_obj.pagelist;
				makepowertable(pagelist,true);
			}
		}
	});
	
	//保存
	$module_btm.find('input[id="btn_save_power"]').click(function(){
		
		var plist = new Array();
		$.each($table_has.find("tr td>img"),function(i){
			plist.push($(this).parent().attr("id"));
		});
		if(plist.length == 0){
			if(!confirm("确定要清空所有访问页面吗?")){
				return;
			}
		}
		
		if (pagin1.setprocess)
			return;
		pagin1.setprocess = true;
		
		$.lwt_ajax(rootpath + "/funmode/funmodeaction!saveFunPages.action",{"modeId":$(this).data("moduleId"),"temppageid":plist.join("#")},call_back_queryallpage);
		function call_back_queryallpage(return_obj) {
			pagin1.setprocess = false;
			if(return_obj.msg == "SUCCESS"){
				alert("保存成功！");
			}
		}
	});
	$module_btm.find('input[id="btn_close"]').click(function(){		
		$('#newmoduleconf_panel .title:eq(0)>img:eq(0)').trigger('click');
	});
}
//修改
function update(moduleId) {
	if (pagin.setprocess)
		return;
	pagin.setprocess = true;
	$.lwt_ajax(rootpath + "/funmode/funmodeaction!findByModeId.action", {
		"modeId" : moduleId}, call_back_findone);
	function call_back_findone(return_obj) {
		pagin.setprocess = false;
		if (return_obj.msg == "SUCCESS") {
					
			$panel_div.find('input:[type="text"]:eq(0)').val(
					return_obj.mode.moduleName);
			$panel_div.find('input:[type="text"]:eq(1)').val(
					return_obj.mode.description);
			fillcbx(return_obj.mode.moduleLevel,return_obj.mode.sortField);
			if(return_obj.mode.moduleLevel!=1){				
				var parentId = "0";
				if(return_obj.mode.functionmodule != null && return_obj.mode.functionmodule.moduleId!=undefined)
					parentId = return_obj.mode.functionmodule.moduleId;
				getparentlevel(return_obj.mode.moduleLevel,parentId);
			}
			$.modelWindow({
				handler : 'newmodule',
				height : 330,
				width : 445,
				bgvisibel : true,
				title : '修改页面',
				html : $panel_div
			});
						
			newEventHandler("update",moduleId);
		}
	}
}
//删除
function dele(modeId) {
	if (confirm("确定要删除当前记录吗?")) {
		if (pagin.setprocess)
			return;
		pagin.setprocess = true;

		$.lwt_ajax(rootpath
				+ "/funmode/funmodeaction!deleteMode.action",
				{
					"modeId" : modeId
				}, call_back_deleteSingle);
	}
	function call_back_deleteSingle(return_ob) {
		pagin.setprocess = false;
		if (return_ob.msg == "SUCCESS") {
			alert("删除成功");
			getpagelist();
		} else
			return alert(return_ob.msg);
	}
}
function searchmodule(){
	var serarch_key =  $('#txt_serarch_key').val();
	
	var p = {
			"currentpage" : pagin.current_page,
			"pagesize" : pagin.page_size,
			"kw":serarch_key
		};
	$.lwt_ajax(rootpath	+ "/funmode/funmodeaction!findAllKw.action",p, call_back_query_module_key);
	
	function call_back_query_module_key(rtn){
		pagin.current_page = rtn.currentpage;
		pagin.page_size = rtn.pagesize;
		pagin.max_count = rtn.maxCount;
		pagin.total_pagecount = Math.ceil(rtn.maxCount / rtn.pagesize);

		maketable(rtn);
	}
}
$(document).ready(
		function() {
			getpagelist();
			$('#btn_new_module').click(function() {
				$.modelWindow({
					handler : 'newclinic',
					height : 330,
					width : 445,
					bgvisibel : true,
					title : '添加新模块',
					html : $panel_div
				});
				fillcbx();

				$panel_div.find('input[type="text"]').val("");
				newEventHandler();
			});

			$('#btn_clear').click(
					function() {
						if (confirm("确定要清掉当前所有记录吗?")) {
							if (pagin.setprocess)
								return;
							pagin.setprocess = true;
							$.lwt_ajax(rootpath
									+ "/pages/webpagemanager!deleteAll.action",
									{}, call_back_deleteall);

						}
						function call_back_deleteall(return_obj) {
							pagin.setprocess = false;
							if (return_obj.msg == "SUCCESS") {
								alert("恭喜，清空成功");
								getpagelist();
							} else {
								return alert(return_obj.msg);
							}
						}
					});
			$('#btn_batch_add').click(
					function() {
						if (confirm("确定要重新更新表的所有记录吗?")) {
							if (pagin.setprocess)
								return;
							pagin.setprocess = true;
							$.lwt_ajax(rootpath
									+ "/pages/webpagemanager!addAll.action",
									{}, call_back_addall);

						}
						;
						function call_back_addall(return_obj) {
							pagin.setprocess = false;
							if (return_obj.msg == "SUCCESS") {
								alert("恭喜，添加已完成");
								getpagelist();
							} else {
								return alert(return_obj.msg);
							}
						}
					});
			
			$('#btn_serach_module').click(function(){
				searchmodule();
			});
			$('#txt_serarch_key').click(function(){
				if($(this).val()=="请输入查询关键字")
					$(this).val("").css("color","#191919");
				else {
					$(this).blur(function() {
						if($(this).val()==""){
							$(this).val("请输入查询关键字").css("color","#ccc");
						}
					});
				}
			}).keydown(function(event) {  
                if (event.keyCode == 13) 
                	searchmodule(); 
                });
			pagin.BindpagerEvent();
		});