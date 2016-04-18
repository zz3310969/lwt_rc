var $buttons = $('<div>').css({"margin-top":"10px","text-align":"right"})
.append($('<input type="button" value="保存" id="btn_save">').addClass("btn").addClass("reset").width(65).height(24).css("margin-left","20px"))
.append($('<input type="button" value="关闭" id="btn_close">').addClass("btn").addClass("close").width(65).height(24).css("margin-left","20px"));
var $panel_return = $('<div>').css({
	'padding' : '18px 8px 8px 8px'
}).append($("<div>").append($('<textarea>').addClass("txt").width(460).height(150))).append($('<div>').height(30).append($buttons.clone()));

$panel_set_expert = $('<div>').css({
	'padding' : '18px 8px 8px 8px'
}).append($('<div>').append($('<span>').text("科室：")).append($('<select>').addClass("select").append($('<option>').text("")))).append($('<div>').addClass("txt").css({"margin":"10px 5px"}).width(380).height(100)).append($('<div>').height(30).append($buttons.clone()));


var pagin = new pagination();

function rejectapplication() {
	$.modelWindow({
		handler : 'reject_panel',
		height : 260,
		width : 484,
		bgvisibel : true,
		title : '驳回申请--驳回原因',
		html : $panel_return
	});
	$panel_return.find('input[id="btn_close"]').unbind("click").click(function(){
		$('#modulewindowclose').trigger("click");
	});
	
	$panel_return.find('input[id="btn_save"]').unbind("click").click(function(){
		var bohui=$panel_return.find("textarea").val();
		if(bohui == "" || $.trim(bohui) == "")
			return alert("请输入驳回原因");
		
		if (pagin.setprocess)
			return;
		pagin.setprocess = true;

		$.lwt_ajax(rootpath + "/revrc/revmanager!returnConsul.action", {
			"id" : $('#btn_return').attr("conid"),
			"status" : bohui
		}, call_back_reject_info);
		
		function call_back_reject_info(return_obj) {
			pagin.setprocess = false;
			if (return_obj.msg == "SUCCESS") {
				return alert("操作已成功");
				$('#modulewindowclose').trigger("click");
			}
			else {
				return alert(return_obj.msg);
			}
		}
	});
}

function setexpert() {
	
	$panel_set_expert.find("select>option:eq(0)").text($('#cons_depart_in_td').text()).parent().attr("disabled","disabled");
	
	if (pagin.setprocess)
		return;
	pagin.setprocess = true;

	$.lwt_ajax(rootpath + "/revrc/revmanager!findHosptialUser.action", {
		"id" : $('#btn_return').attr("conid"),
	}, call_back_select_expert);
	
	function call_back_select_expert(return_obj) {
		pagin.setprocess = false;
		
		var list = return_obj.sysusers;
		var $ul = $('<ul>').css({"margin-top":"20px"});
		
		for(var i=0;i<list.length;i++){
			var $li = $('<li>').width(100).css("float","left").append($('<input>').attr("type","checkbox").data("userid",list[i].userId)).append($('<span>').css("margin-left","8px").text(list[i].realName));
			$ul.append($li);
		}
		$panel_set_expert.find("div:eq(1)").empty().append($ul);
		if (return_obj.msg == "SUCCESS") {
			$.modelWindow({
				handler : 'set_expert_panel',
				height : 260,
				width : 410,
				bgvisibel : true,
				title : '安排专家',
				html : $panel_set_expert
			});
			
			$panel_set_expert.find('input[id="btn_close"]').unbind("click").click(function(){
				$('#modulewindowclose').trigger("click");
			});
			
			$panel_set_expert.find('input[id="btn_save"]').unbind("click").click(function() {
				var expertarray = new Array();
				$.each($panel_set_expert.find("ul li input[type=checkbox]"),function(){
					if($(this).attr("checked")){
						expertarray.push($(this).data("userid"));
					}
				});
				
				setexpert1(expertarray);	
			});
		}
		else {
			return alert(return_obj.msg);
		}
	}
}

function setexpert1(expertarray){
	if(typeof(expertarray) == undefined || expertarray == null)
		return;
	
	if(expertarray.length == 0){
		return alert("请选择一个要安排的专家");
	}
	
	if (pagin.setprocess)
		return;
	pagin.setprocess = true;

	$.lwt_ajax(rootpath + "/revrc/revmanager!updateConsul.action", {
		"id" : $('#btn_return').attr("conid"),
		"doctorids":expertarray.join("#")
	}, call_back_set_expert);
	
	function call_back_set_expert(return_obj) {
		pagin.setprocess = false;
		
		if(return_obj.msg == "SUCCESS"){
			return alert("操作已成功！");
		}
		else {
			return alert(return_obj.msg);
		}
	}
}
$(function() {

	$('#btn_return').click(function() {
		rejectapplication();
	});

	$('#btn_expert').click(function() {
		setexpert();
	});
	pagin.BindpagerEvent();
	
	
	$('#btn_report').click(function(){
		var id = $(this).attr("conid");
		window.location.href = rootpath + "/revrc/revmanager!findByIdCon.action?id="+id+"&status=report";
	});
});