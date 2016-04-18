var $panel_div = $('<div>').css({'padding':'18px 8px 8px 8px'}).append($('<table>').addClass("tbsad").append($('<tbody>')
	.append($('<tr>').append($('<td>').width(80).text('角色名称：').addClass('ttle'))
	.append($('<td>').width(185)
	.append($('<input type="text" value="">').height(25).width(180).addClass("txt").addClass("ht24").val(""))))
	.append($('<tr>').append($('<td>').text('角色描述：').addClass('ttle'))
	.append($('<td>').append($('<input type="text" maxlength="11" value="">').val("").height(25).width(180).addClass("txt").addClass("ht24"))))
	.append($('<tr>').append($('<td colspan="4">').css('text-align','center')
	.append($('<input type="button" value="保存">').addClass("btn").width(65).height(24).css('margin-right','15px'))
	.append($('<input type="button" value="重置">').addClass("btn").width(65).height(24))))																			
));
var $role_panel = $('<div>').css({'padding':'8px'}).height(320).append($('<div>'));	

var $buttons = $('<div>').addClass("bottombtn").height(30)			
	.append($('<input type="button" value="关闭" id="btn_close">').addClass("btn").addClass("close"))
	.append($('<input type="button" value="保存" id="btn_save_power">').addClass("btn").addClass("reset"));

function addnewrole(opt,roleId) {
	$panel_div.find('td>img').remove();
	var txt_rolename = $
			.trim($panel_div.find('input[type="text"]:eq(0)').val());
	var txt_roledesc = $
			.trim($panel_div.find('input[type="text"]:eq(1)').val());

	if (txt_rolename == '')
		return alert("请输入角色名称");

	if (txt_roledesc == '')
		return alert("请输入角色描述");
	
	if(roleId == undefined || roleId == null)
		roleId = '';
	var p = {
		"role.roleId" : roleId,
		"role.roleName" : txt_rolename,
		"role.description" : txt_roledesc
	};
	var ajax_url = rootpath + "/role/roleaction!onCreate.action";
	if (opt == "update")
		ajax_url = rootpath + "/role/roleaction!onUpdateRole.action";
	
	if (pagin.setprocess)
		return;
	
	pagin.setprocess = true;

	$.lwt_ajax(ajax_url, p, call_back_add_new_role);

	function call_back_add_new_role(return_obj) {
		pagin.setprocess = false;
		if (return_obj.msg == "SUCCESS") {
			alert("操作已成功！");
			getpagelist();
			$('#modulewindowclose').trigger('click');
			$('#newrole_panel .title').find('img:eq(0)').trigger('click');
		} else
			return alert(return_obj.msg);
	}
}
function newEventHandler(opt,roleId) {
	$panel_div.find('input:[type="button"]:eq(0)').click(function() {
		addnewrole(opt,roleId);
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
	$.lwt_ajax(rootpath + "/role/roleaction!findAll.action", p,
			call_back_role_list);

	function call_back_role_list(rtn) {
		pagin.setprocess = false;
		if(rtn.msg == "SUCCESS"){
			pagin.current_page = rtn.currentpage;
			pagin.page_size = rtn.pagesize;
			pagin.max_count = rtn.maxCount;
			pagin.total_pagecount = Math.ceil(rtn.maxCount / rtn.pagesize);
	
			maketable(rtn);
		}
		else {
			alert(return_obj.msg);
		}
	}
}

function maketable(rtn) {
	pagin.setprocess = false;	

	var $pg = $('#pageleft').find('span');
	$pg.eq(0).text(pagin.max_count);
	$pg.eq(1).text(pagin.current_page + "/" + Math.ceil(pagin.max_count / pagin.page_size));
	$pg.eq(2).text(pagin.page_size);
	
	var pagelist = rtn.pagelist;
	if(pagelist == undefined) return;
	var $body = $('<tbody>');
	
	for ( var i = 0; i < pagelist.length; i++) {
		var $tr = $('<tr>');
		$tr.append(
				$('<td>').width(60).text(i + 1).addClass('center'))				
				.append($('<td>').text(pagelist[i].roleId))
				.append($('<td>').text(pagelist[i].roleName))
				.append($('<td>').text(pagelist[i].description))
				.append($('<td>').text(formattime(pagelist[i].addTime)).addClass('center'))
				.append($('<td>').text(formattime(pagelist[i].updateTime)).addClass('center'))
				.append($('<td>').data('roleId', pagelist[i].roleId).addClass('optcls')
				.append($('<a>').css({'margin-right' : '10px'}).text('[编辑权限]'))
				.append($('<a>').css({'margin-right' : '10px'}).text('[修改]'))
				.append($('<a>').text("[删除]")));
		
		$tr.find('td:eq(6)>a:eq(0)').click(function() {
			
			editpower($(this).parent().data('roleId'));	
		});

		$tr.find('td:eq(6)>a:eq(1)').click(	function() {
			update($(this).parent().data('roleId'));
		});
			
		$tr.find('td:eq(6)>a:eq(2)').click(	function() {
			dele($(this).parent().data('roleId'));
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

function makepowermap(map,rolerights,roleId) {
	var $divpower = $('<div>').css({"overflow-y":"scroll","height":"300px"});
	
	var rightsarr = new Array();
	if(typeof rolerights != undefined && rolerights!=null){
		
		$.each(rolerights,function(i){
			rightsarr.push(rolerights[i].functionmodule.moduleId);
		});
	}
	if(typeof map == undefined || map == null)
		return;
	
	var i = 0;
	$.each(map,function(key,map){
		i++;
		var $dl = $('<dl>');
		var keyset = key.toString().split('#');
		$dl.append($('<dt>').append($('<input>').attr('type','checkbox').attr('value',keyset[1])).append($('<span>').text(keyset[0])));
		if(rightsarr.contains(keyset[1])){
			$dl.find("dt>input").attr("checked", true);
		}
		$.each(map,function(j){			
			$dl.append($('<dd>').append($('<input>').attr('type','checkbox').attr('name','role'+ i).attr('value',map[j].moduleId)).append($('<span>').text(map[j].moduleName)));
			if(rightsarr.contains(map[j].moduleId)){
				$dl.find('dd>input').eq(j).attr("checked", true);
			}
		});
		$divpower.append($dl);
	});
	 
	$.each($divpower.find("dl dt>input"),function(i){
		$(this).click(function(){
			if($(this).attr('checked')){
				$(this).parent().parent().find('dd>input').attr("checked", this.checked);
			}else{
				$(this).parent().parent().find('dd>input').attr("checked", "");
			}
			
		});
	});
	
	$.each($divpower.find("dl dd>input"),function(i){
		$(this).click(function(){
			if($(this).attr("checked")){
				$(this).parent().parent().find("dt>input").attr("checked",this.checked);
			}else{
				if($(this).parent().parent().find("dd>input:checked").size()==0)
					$(this).parent().parent().find("dt>input").attr("checked","");
			}
		});
	});
	
	$role_panel.find('div').remove().end().append($divpower).append($buttons);
	$buttons.find('input:[id="btn_save_power"]').click(function(){
		
		var powerarr = new Array();
		$.each($divpower.find("input"),function(i){
			if($(this).attr("checked")){
				powerarr.push($(this).val());
			}
		});
		
		if (pagin.setprocess)
			return;
		pagin.setprocess = true;
		
		$.lwt_ajax(rootpath + "/right/rightaction!addRoleRight.action",{"roleid":roleId,"modeids":powerarr.join("#")},call_back_rolelist);
		
		function call_back_rolelist(return_obj) {
			pagin.setprocess = false;
			if(return_obj.msg == "SUCCESS"){
				alert("操作已成功！");
			}else {
				alert(return_obj.msg);
			}
		}
				
	});
	$buttons.find('input:[id="btn_close"]').click(function(){
		$('#modulewindowclose').trigger('click');
	});
}
//编辑权限
function editpower(roleId) {
	var pagin1 = new pagination();
	if (pagin1.setprocess)
		return;
	pagin1.setprocess = true;
	
	$.lwt_ajax(rootpath + "/role/roleaction!findByIdRole.action",{"rid":roleId},call_back_queryallpage);
	function call_back_queryallpage(return_obj) {
		pagin1.setprocess = false;
		if(return_obj.msg == "SUCCESS"){
			var maplist = return_obj.map;
			var roleright = return_obj.role.rolerights;
			makepowermap(maplist,roleright,roleId);
		}else {
			return alert(return_obj.msg);
		}
	}
	$.modelWindow({
		handler : 'editpower',
		height : 400,
		width : 700,
		bgvisibel : true,
		title : '编辑权限',
		html : $role_panel
	});

}
//修改
function update(roleId) {
	if (pagin.setprocess)
		return;
	pagin.setprocess = true;
	$.lwt_ajax(rootpath + "/role/roleaction!findByIdRole.action", {
		"rid" : roleId}, call_back_findone);
	function call_back_findone(return_obj) {
		pagin.setprocess = false;
		if (return_obj.msg == "SUCCESS") {
					
			$panel_div.find('input:[type="text"]:eq(0)').val(
					return_obj.role.roleName);
			$panel_div.find('input:[type="text"]:eq(1)').val(
					return_obj.role.description);

			$.modelWindow({
				handler : 'newrole',
				height : 220,
				width : 445,
				bgvisibel : true,
				title : '修改页面',
				html : $panel_div
			});
			$panel_div.find('td>img').remove();
			
			newEventHandler("update",roleId);
		}
	}
}
//删除
function dele(roleId) {
	if (confirm("确定要删除当前记录吗?")) {
		if (pagin.setprocess)
			return;
		pagin.setprocess = true;

		$.lwt_ajax(rootpath
				+ "/role/roleaction!onDeleteRole.action",
				{
					"rid" : roleId
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
}
$(document).ready(function(){
	getpagelist();
	$('#btn_new_role').click(function(){
		$.modelWindow({handler:'newrole',height:220,width:400,bgvisibel:true,title:'添加新角色', html:$panel_div});
		newEventHandler();
	});	
	
});