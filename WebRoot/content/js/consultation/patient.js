var $buttons = $('<div>').height(30)
.append($('<input type="button" value="保存" id="btn_save_power">').addClass("btn").addClass("reset").width(65).height(24).css("margin-left","20px"))
.append($('<input type="button" value="关闭" id="btn_close">').addClass("btn").addClass("close").width(65).height(24).css("margin-left","20px"));
var $panel_div = $('<div>').css({'padding':'18px 8px 8px 8px'})
	   .append($('<table>').addClass("tbsad").append($('<tbody>')
	   .append($('<tr>').append($('<td>').width(95).text("证件类型：")).append($("<td>").width(210).append($('<select>').addClass("select").append($('<option>').text("请选择")))))
	   .append($('<tr>').append($('<td>').text("证件号码：")).append($("<td>").append($('<input>').attr("type","text").addClass("txt")))
	   .append($('<td>').text("")).append($('<td>').width(80).text("病人手机：")).append($('<td>').append($('<input>').attr("maxlength","15").attr('type','text').addClass("txt"))))
	   .append($('<tr>').append($('<td>').text("社保卡/医保卡：")).append($("<td>").append($('<input>').attr("type","text").attr("maxlength","20").addClass("txt")))
	   .append($('<td>').text("")).append($('<td>').text("家属姓名：")).append($('<td>').append($('<input>').attr("maxlength","10").attr('type','text').addClass("txt"))))
	   .append($('<tr>').append($('<td>').text("姓名：")).append($("<td>").append($('<input>').attr("maxlength","10").attr("type","text").addClass("txt")))
	   .append($('<td>').text("")).append($('<td>').text("家属电话：")).append($('<td>').append($('<input>').attr("maxlength","15").attr('type','text').addClass("txt"))))
	   .append($('<tr>').append($('<td>').text("性别：")).append($("<td>").append($('<select>').addClass("select").append($('<option>').text("请选择"))))
	   .append($('<td>').text("")).append($('<td>').text("病人备注：")).append($('<td>').attr("rowspan","4").append($('<textarea>').width(310).height(130).addClass("txt"))))
	   .append($('<tr>').append($('<td>').text("出生日期：")).append($("<td>").append($('<input>').attr("type","text").addClass("txt").addClass("Wdate"))))
	   .append($('<tr>').append($('<td>').text("民族：")).append($("<td>").append($('<select>').addClass("select").append($('<option>').text("请选择")))))
	   .append($('<tr>').append($('<td>').text("婚姻状况：")).append($("<td>").append($('<select>').addClass("select").append($('<option>').text("请选择")))))
	   .append($('<tr>').append($('<td>').text("工作单位：")).append($("<td>").append($('<input>').attr("type","text").addClass("txt")))
	   .append($('<td>').text("")).append($('<td>').width(80).text("入院时间：")).append($('<td>').append($('<input>').attr("maxlength","15").attr('type','text').addClass("Wdate").addClass("txt"))))
	   .append($('<tr>').append($('<td>').text("通信地址：")).append($("<td>").append($('<input>').attr("type","text").addClass("txt")))
	   .append($('<td>').text("")).append($('<td>').width(80).text("")).append($('<td>').append($buttons)))
));
$panel_div.find("table tbody").find("tr:eq(5)>td:eq(1)>input").click(function(){WdatePicker({readOnly:true})});
$panel_div.find("table tbody").find("tr:eq(8)>td:eq(4)>input").click(function(){WdatePicker({readOnly:true})});
var pagin = new pagination();
function fillselect(selectcardId,selectmarriageId,selectsexId,selectnationId){
	$.lwt_ajax(rootpath + "/rc/rcmanager!init.action", {},call_back_select_init);
	function call_back_select_init(rtn) {
		if(rtn.msg!="SUCCESS")
			return alert(rtn.msg);	
		
			var $selectcards = makeselectsys(rtn.cardTypes,{'id':'cbxcards'},selectcardId);
			var $selectmarriage = makeselectsys(rtn.marrigeType,{'id':'cbxmarriage'},selectmarriageId);
			var $selectsex = makeselectsys(rtn.sexType,{'id':'cbxsex'},selectsexId);
			var $selectnation = makeselectsys(rtn.nations,{'id':'cbxnation'},selectnationId);
			
			$panel_div.find("table tbody tr:eq(0)>td:eq(1)").empty().append($selectcards).parent()
			.siblings().eq(3).children("td").eq(1).empty().append($selectsex).parent()
			.siblings().eq(5).children("td").eq(1).empty().append($selectnation).parent()
			.siblings().eq(6).children("td").eq(1).empty().append($selectmarriage);

		}
	}
function addnewpatient(opt,patientId) {
	
	var patientinfo = $panel_div.find("table tbody");
	var cardtype = patientinfo.find("tr:eq(0)>td:eq(1)>select option:selected").val();
	if(cardtype == -1){
		return alert("请选择证件类型");
	}
	var idNumber = patientinfo.find("tr:eq(1)>td:eq(1)>input").val();
	if($.trim(idNumber) == '')
		return alert("请输入证件号码");
	
	var shebaocard = patientinfo.find("tr:eq(2)>td:eq(1)>input").val();	
	var patname = patientinfo.find("tr:eq(3)>td:eq(1)>input").val();
	if($.trim(patname)=='')
		return alert("请输入姓名");
	
	var patsex = patientinfo.find("tr:eq(4)>td:eq(1)>select option:selected").val();
	if(patsex == -1)
		return alert("请选择性别");
	
	var patbith = patientinfo.find("tr:eq(5)>td:eq(1)>input").val();
	if($.trim(patbith)=='')
		return alert("请输入出生日期");
	
	var patnation = patientinfo.find("tr:eq(6)>td:eq(1)>select option:selected").val();
	if($.trim(patnation) == -1)
		return alert("请选择民族");
	
	var patmarrige = patientinfo.find("tr:eq(7)>td:eq(1)>select option:selected").val();
	if(patmarrige == -1)
		return alert("请选择婚姻状况");
	
	var workunit = patientinfo.find("tr:eq(8)>td:eq(1)>input").val();
	var contactadd = patientinfo.find("tr:eq(9)>td:eq(1)>input").val();
	
	var mobile = patientinfo.find("tr:eq(1)>td:eq(4)>input").val();
	if($.trim(mobile) == '')
		return alert("请输入病人手机号码");	
	
	var jiashuname = patientinfo.find("tr:eq(2)>td:eq(4)>input").val();
	var jiashutel = patientinfo.find("tr:eq(3)>td:eq(4)>input").val();
	var remark = patientinfo.find("tr:eq(4)>td:eq(4)>textarea").val();
	var hospitaltime = patientinfo.find("tr:eq(8)>td:eq(4)>input").val();
	if(IsNullOrEmpty(patientId))
		patientId = "";
	
	var p = {
			"patient.idType":cardtype,
			"patient.idNumber":idNumber,
			"patient.shebao":shebaocard,		
			"patient.name":patname,
			"patient.gender":patsex,
			"patient.born":patbith,
			"patient.nation":patnation,		
			"patient.marriage":patmarrige,
			"patient.currentAddress":contactadd,
			"patient.contactName":jiashuname,
			"patient.contactPhone":jiashutel,
			"patient.telephone":mobile,
			"patient.description":remark,
			"patient.workunit":workunit,
			"patient.hospitalTime":hospitaltime,
			"patient.id":patientId
		};
	var ajax_url = rootpath + "/patient/patManager!onCreatePatient.action";
	if (opt == "update") {
		ajax_url = rootpath + "/patient/patManager!onUpdatePatient.action";
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
function newEventHandler(opt,patientId) {
	$panel_div.find('input:[type="button"]:eq(0)').click(function() {
		addnewpatient(opt,patientId);
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
}

function getpagelist() {
	if (pagin.setprocess)
		return;
	pagin.setprocess = true;

	var p = {
		"currentpage" : pagin.current_page,
		"pagesize" : pagin.page_size
	};
	$.lwt_ajax(rootpath + "/patient/patManager!init.action", p,
			call_back_page_list);

	function call_back_page_list(rtn) {
		pagin.setprocess = false;
		if(rtn.msg!="SUCCESS")
			return alert(rtn.msg);
		
		filldictionarylist(rtn.cardTypes);
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
	
	var $selectcards = makeselectsys(typelist,{'id':'slt_cardtype'});
	$("#slt_cardtype").replaceWith($selectcards);
}

function maketable(rtn) {
	pagin.setprocess = false;
	var pagelist = rtn.pagelist;
	if(IsNullOrEmpty(pagelist))
		return;

	var $pg = $('#pageleft').find('span');
	$pg.eq(0).text(pagin.max_count);
	$pg.eq(1).text(pagin.current_page + "/"	+ Math.ceil(pagin.max_count / pagin.page_size));
	$pg.eq(2).text(pagin.page_size);

	var $body = $('<tbody>');
	
	for ( var i = 0; i < pagelist.length; i++) {
		var $tr = $('<tr>');
		$tr.append($('<td>').width(60).text((pagin.current_page - 1) * pagin.page_size + i + 1).addClass('center'))
			.append($('<td>').width(100).text(pagelist[i].name))
			.append($('<td>').width(50).text(pagelist[i].gender).addClass("center"))
			.append($('<td>').width(100).text(pagelist[i].marriage).addClass("center"))
			.append($('<td>').width(80).text(formatsorttime(pagelist[i].born)))
			.append($('<td>').width(80).text(pagelist[i].idType))
			.append($('<td>').width(150).text(pagelist[i].idNumber))
			.append($('<td>').width(250).text(pagelist[i].hospital.name))//pagelist[i].hospital.name
			.append($('<td>').width(180).text(formattime(pagelist[i].hospitalTime)))
			.append($('<td>').width(180).text(formattime(pagelist[i].addTime)).addClass('center'))
			.append($('<td>').width(180).text(formattime(pagelist[i].updateTime)).addClass('center'))
			.append($('<td>').data('patientId',pagelist[i].id).width(180).addClass('optcls')
					.append($('<a>').css({'margin-right' : '10px'}).text('[修改]')).append($('<a>').text("[删除]")));

		$tr.find('td:eq(11)>a:eq(0)').click(			
				function() {				
					fillselect();
					if (pagin.setprocess)
						return;
					pagin.setprocess = true;
					
					var patientId = $(this).parent().data('patientId');
					$.lwt_ajax(rootpath
							+ "/patient/patManager!findByidPatient.action", {"id" : patientId}, call_back_findone);
					function call_back_findone(return_obj) {
						pagin.setprocess = false;
						if (return_obj.msg == "SUCCESS") {
							var patient = return_obj.patient;
											
							$.modelWindow({
								handler : 'newdictionary',
								height : 460,
								width : 750,
								bgvisibel : true,
								title : '修改病人信息',
								html : $panel_div
							});
							fillpatientinfo(patient);						
							newEventHandler("update",patientId);
						}
					}
				});
		$tr.find('td:eq(11)>a:eq(1)').click(
				function() {
					if (confirm("确定要删除当前记录吗?")) {
						if (pagin.setprocess)
							return;
						pagin.setprocess = true;
						$.lwt_ajax(rootpath
								+ "/patient/patManager!onDeletePatient.action",{"id" : $(this).parent().data('patientId')}, call_back_delete);
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
function fillpatientinfo(pat)
{
	var patientinfo = $panel_div.find("table tbody");
	patientinfo.find("tr:eq(1)>td:eq(1)>input").val(pat.idNumber);
	patientinfo.find("tr:eq(2)>td:eq(1)>input").val(pat.shebao);	
	patientinfo.find("tr:eq(3)>td:eq(1)>input").val(pat.name);
	patientinfo.find("tr:eq(5)>td:eq(1)>input").val(formatsorttime(pat.born));
	patientinfo.find("tr:eq(8)>td:eq(1)>input").val(pat.workunit);
	patientinfo.find("tr:eq(9)>td:eq(1)>input").val(pat.currentAddress);
	patientinfo.find("tr:eq(1)>td:eq(4)>input").val(pat.telephone);
	patientinfo.find("tr:eq(2)>td:eq(4)>input").val(pat.contactName);
	patientinfo.find("tr:eq(3)>td:eq(4)>input").val(pat.contactPhone);
	patientinfo.find("tr:eq(4)>td:eq(4)>textarea").val(pat.description);
	patientinfo.find("tr:eq(8)>td:eq(4)>input").val(formatsorttime(pat.hospitalTime));
	fillselect(pat.idType,pat.marriage,pat.gender,pat.nation);
}
function queryparientinfo(){
	if (pagin.setprocess)
		return;
	pagin.setprocess = true;
	
	var serarch_key = $('#txt_serarch_key').val();
	if(serarch_key == "请输入查询关键字")
		serarch_key = "";
	
	var cardId = $('#slt_cardtype option:selected').val();
	if(cardId == "-1")
		cardId = "";
	
	var p = {
			"currentpage" : pagin.current_page,
			"pagesize" : pagin.page_size,
			"val":serarch_key,
			"type":cardId
		};
	$.lwt_ajax(rootpath	+ "/patient/patManager!findAllKw.action",p, call_back_query_cardtype_key);
	
	function call_back_query_cardtype_key(rtn){
		pagin.setprocess = false;
		if(rtn.msg !="SUCCESS")
			return alert(rtn.msg);
		
		pagin.current_page = rtn.currentpage;
		pagin.page_size = rtn.pagesize;
		pagin.max_count = rtn.maxCount;
		pagin.total_pagecount = Math.ceil(rtn.maxCount / rtn.pagesize);

		maketable(rtn);
	}
}
$(function() {
	getpagelist();	
	$('#btn_new_patient').click(function() {
			
		$.modelWindow({
			handler : 'newpatuent',
			height : 460,
			width : 750,
			bgvisibel : true,
			title : '添加新病人',
			html : $panel_div
		});
		
		$panel_div.find('input[type="text"]').val("");
		fillselect();
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
			queryparientinfo();
	});
	$('#btn_search').click(function(){
		queryparientinfo();
	});
	pagin.BindpagerEvent();
});