var $panel_div = $('<div>').css({'padding':'18px 8px 8px 8px'})
	   .append($('<table>').addClass("tbsad").append($('<tbody>')
	   .append($('<tr>').append($('<td>').text('页面名称：').addClass('ttle'))
	   					.append($('<td>').width(282)
						.append($('<input type="text" value="">').width(200).addClass("txt").val(""))))
	   .append($('<tr>').append($('<td>').text('页面描述：').width(120).addClass('ttle'))
	   					.append($('<td>').append($('<input type="text" value="">').val("").width(251).addClass("txt"))))
	   .append($('<tr>').append($('<td>').text('页面地址：').addClass('ttle'))
	   					.append($('<td>').append($('<input type="text" value="">').val("").width(251).addClass("txt"))))				
	   .append($('<tr>').append($('<td colspan="4">').css('text-align','center')
	   					.append($('<input type="button" value="保存">').addClass("btn").width(65).height(24).css('margin-right','15px'))
						.append($('<input type="button" value="重置">').addClass("btn").width(65).height(24))))																			
		));
function addnewpage(opt) {
	$panel_div.find('td>img').remove();
	var txt_pagename = $
			.trim($panel_div.find('input[type="text"]:eq(0)').val());
	var txt_pagedesc = $
			.trim($panel_div.find('input[type="text"]:eq(1)').val());
	var txt_pageurl = $.trim($panel_div.find('input[type="text"]:eq(2)').val());

	if (txt_pagename == '')
		return alert("请输入页面名称");

	if (txt_pagedesc == '')
		return alert("请输入页面描述");

	if (txt_pageurl == '')
		return alert("请输入页面地址");

	var p = {
		"page.pageName" : txt_pagename,
		"page.description" : txt_pagedesc,
		"page.url" : txt_pageurl
	};
	var ajax_url = rootpath + "/pages/webpagemanager!addWebPage.action";
	if (opt == "update") {
		p = {
			"page.pageName" : txt_pagename,
			"page.description" : txt_pagedesc,
			"page.url" : txt_pageurl,
			"page.pageId" : $panel_div.find('input[type="text"]:eq(2)').data(
					'pageId')
		};
		ajax_url = rootpath + "/pages/webpagemanager!updateWebPage.action";
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
			$('#newclinic_panel .title').find('img:eq(0)').trigger('click');
		} else
			return alert(return_obj.msg);
	}
}
function newEventHandler(opt) {
	$panel_div.find('input:[type="button"]:eq(0)').click(function() {
		addnewpage(opt);
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
	$.lwt_ajax(rootpath + "/pages/webpagemanager!findAll.action", p,
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
		$tr.append(
				$('<td>').width(60).text(
						(pagin.current_page - 1) * pagin.page_size + i + 1)
						.addClass('center')).append(
				$('<td>').width(120).text(pagelist[i].pageName)).append(
				$("<td style='white-space: nowrap;text-overflow: ellipsis;overflow: hidden;word-break: break-all;'>").width(100).html(StringSub(pagelist[i].description,10))).append(
				$('<td>').width(200).text(pagelist[i].url)).append(
				$('<td>').width(80).text(formattime(pagelist[i].addTime)).addClass(
						'center')).append(
				$('<td>').width(80).text(formattime(pagelist[i].updateTime)).addClass(
						'center')).append(
				$('<td>').width(100).data('uid', pagelist[i].pageId).addClass('optcls')
						.append($('<a>').css({
							'margin-right' : '10px'
						}).text('[修改]')).append($('<a>').text("[删除]")));

		$tr.find('td:eq(6)>a:eq(0)').click(
				function() {
					if (pagin.setprocess)
						return;
					pagin.setprocess = true;
					$.lwt_ajax(rootpath
							+ "/pages/webpagemanager!findByIdPage.action", {
						"page.pageId" : $(this).parent().data('uid')
					}, call_back_findone);
					function call_back_findone(return_obj) {
						pagin.setprocess = false;
						if (return_obj.msg == "SUCCESS") {

							$panel_div.find('input:[type="text"]:eq(0)').val(
									return_obj.page.pageName);
							$panel_div.find('input:[type="text"]:eq(1)').val(
									return_obj.page.description);
							$panel_div.find('input:[type="text"]:eq(2)').data(
									"pageId", return_obj.page.pageId).val(
									return_obj.page.url);
							$.modelWindow({
								handler : 'newclinic',
								height : 250,
								width : 445,
								bgvisibel : true,
								title : '修改页面',
								html : $panel_div
							});
							$panel_div.find('td>img').remove();
							newEventHandler("update");
						}
					}
				});
		$tr.find('td:eq(6)>a:eq(1)').click(
				function() {
					if (confirm("确定要删除当前记录吗?")) {
						if (pagin.setprocess)
							return;
						pagin.setprocess = true;
						$.lwt_ajax(rootpath
								+ "/pages/webpagemanager!deleteWebPage.action",
								{
									"page.pageId" : $(this).parent()
											.data('uid')
								}, call_back_delete);
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
	$.lwt_ajax(rootpath	+ "/pages/webpagemanager!findKwAll.action",p, call_back_query_module_key);
	
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
			$('#btn_new_user').click(function() {
				$.modelWindow({
					handler : 'newclinic',
					height : 250,
					width : 445,
					bgvisibel : true,
					title : '添加新页面',
					html : $panel_div
				});
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
								+ "/pages/webpagemanager!deleteAll.action", {},
								call_back_deleteall);
						
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
			$('#btn_batch_add').click(function(){
				if (confirm("确定要重新更新表的所有记录吗?")) {
					if (pagin.setprocess)
						return;
					pagin.setprocess = true;					
					$.lwt_ajax(rootpath+"/pages/webpagemanager!addAll.action", {},
								call_back_addall);
						
				};
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