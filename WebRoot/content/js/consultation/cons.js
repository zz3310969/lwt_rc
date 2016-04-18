var $buttons = $('<div>').height(30);
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
$panel_div.find("input").attr('readonly', 'readonly').css({
	"background-color" : "#fff",
	"color" : "#4c4f50"
});
$panel_div.find("textarea").attr('readonly', 'readonly').css({
	"background-color" : "#fff",
	"color" : "#4c4f50"
});
var $panel_cons = $('<div>').css({'padding':'18px 8px 8px 8px'})
.append($('<table>').addClass("tbsad").append($('<tbody>')
		   .append($('<tr>').append($('<td>').width(90).text("申请单位：")).append($("<td>").width(120).append($('<select>').addClass("select").append($('<option>').text("请选择"))))
		   .append($('<td>').text("")).append($('<td>').width(80).text("病史摘要：")).append($('<td>').attr("rowspan","4").append($('<div>').width(360).height(120).addClass("txt"))))
		   .append($('<tr>').append($('<td>').text("会诊类型：")).append($("<td>").append($('<select>').addClass("select").append($('<option>').text("请选择"))))
		   .append($('<td>').text("")).append($('<td>').width(80).text("")))
		   .append($('<tr>').append($('<td>').text("会诊方式：")).append($("<td>").append($('<select>').addClass("select").append($('<option>').text("请选择"))))
		   .append($('<td>').text("")).append($('<td>').text("")))
		   .append($('<tr>').append($('<td>').text("住院号：")).append($("<td>").append($('<input>').width(112).attr("type","text").addClass("txt")))
		   .append($('<td>').text("")).append($('<td>').text("")))
		   .append($('<tr>').append($('<td>').text("病情情况：")).append($("<td>").append($('<select>').addClass("select").append($('<option>').text("请选择"))))
		   .append($('<td>').text("")).append($('<td>').width(100).text("目前处理与诊断：")).append($('<td>').attr("rowspan","4").append($('<div>').width(360).height(120).addClass("txt"))))
		   .append($('<tr>').append($('<td>').text("费别：")).append($("<td>").append($('<select>').addClass("select").append($('<option>').text("请选择")))))
		   .append($('<tr>').append($('<td>').text("要求会诊单位：")).append($("<td>").append($('<select>').addClass("select").append($('<option>').text("请选择")))))
		   .append($('<tr>').append($('<td>').text("医学专科：")).append($("<td>").append($('<select>').addClass("select").append($('<option>').text("请选择")))))
		   .append($('<tr>').append($('<td>').text("所选专家：")).append($("<td>").append($('<select>').addClass("select").append($('<option>').text("请选择")).append($('<option>').text("请选择")).append($('<option>').text("请选择")).append($('<option>').text("请选择"))))
		   .append($('<td>').text("")).append($('<td>').width(100).text("申请会诊目的：")).append($('<td>').attr("rowspan","4").append($('<div>').width(360).height(120).addClass("txt"))))
		   .append($('<tr>').append($('<td>').text("要求会诊时间：")).append($("<td>").append($('<select>').addClass("select").append($('<option>').text("请选择")))))
		   .append($('<tr>').append($('<td>').text("申请医生：")).append($("<td>").append($('<select>').addClass("select").append($('<option>').text("请选择")))))
		   .append($('<tr>').append($('<td>').text("申请日期：")).append($("<td>").append($('<select>').addClass("select").append($('<option>').text("请选择")))))
	));
var pagin = new pagination();
function getpagelist() {
	if (pagin.setprocess)
		return;
	pagin.setprocess = true;
	
	var $obj = $('.main .barea .querylist:eq(0)').children("li");
	var consdepart = $obj.eq(1).find("input:eq(0)").val();
	var patientname = $obj.eq(3).find("input:eq(0)").val();
	var hospital = $obj.eq(4).find("select option:selected").val();
	var office = $obj.eq(5).find("select option:selected").val();
	var consstate = $obj.eq(6).find("select option:selected").val();
	var reqtimefrm = $obj.eq(8).find("input:eq(0)").val();
	var reqtimeend = $obj.eq(10).find("input:eq(0)").val();
	if(office == "-1") office = "";
	if(consstate == "-1")consstate = "";
	if(hospital == "-1")hospital = "";

	var p = {
		"currentpage" : pagin.current_page,
		"pagesize" : pagin.page_size,
		"qy.receiverHospital" : hospital,
		"qy.name" : patientname,
		"qy.office" : office,
		"qy.status" : consstate,
		"qy.start_date" : reqtimefrm,
		"qy.end_date" : reqtimeend

	};
	$.lwt_ajax(rootpath + "/rc/rcmanager!findkwAll.action", p,
			call_back_page_list);

	function call_back_page_list(rtn) {
		pagin.setprocess = false;
		if (rtn.msg != "SUCCESS")
			return alert(rtn.msg);

		maketable(rtn);	

	}
}
function maketable(rtn) {
	pagin.current_page = rtn.currentpage;
	pagin.page_size = rtn.pagesize;
	pagin.max_count = rtn.maxCount;
	pagin.total_pagecount = Math.ceil(rtn.maxCount / rtn.pagesize);
	
	var pagelist = rtn.listConsultations;
	if (IsNullOrEmpty(pagelist))
		return;

	var $pg = $('#pageleft').find('span');
	$pg.eq(0).text(pagin.max_count);
	$pg.eq(1).text(
			pagin.current_page + "/"
					+ Math.ceil(pagin.max_count / pagin.page_size));
	$pg.eq(2).text(pagin.page_size);

	var $body = $('<tbody>');
	
	
	for ( var i = 0; i < pagelist.length; i++) {
		
		var $status = "";
		if(pagelist[i].consultationStatus=="会诊报告已提交")
			$status = $('<a>').css({'margin-right' : '10px'}).text('[报告]');
		
		var $tr = $('<tr>');
		$tr.append(
				$('<td>').width(60).text(
						(pagin.current_page - 1) * pagin.page_size + i + 1)
						.addClass('center')).append(
				$('<td>').width(80).append(
						$('<a>').data("patientid", pagelist[i].patient.id)
								.text(pagelist[i].patient.name)).css("cursor","pointer").addClass("center")).append(
				$('<td>').width(150).text(pagelist[i].callHospital.name)
						.addClass("center")).append(
				$('<td>').width(90).text(
						formatsorttime(pagelist[i].requestTime))).append(
				$('<td>').width(150).text(pagelist[i].receiverHospital.name))
				.append(
						$('<td>').width(80).append(
								StringSub(pagelist[i].requestDoctorId, 5)))
				.append($('<td>').width(80).append(
						StringSub(pagelist[i].actualDoctorId, 5))).append(
						$('<td>').width(120).text(formattime(pagelist[i].processRequestTime))).append(
						$('<td>').width(120).text(
								pagelist[i].consultationStatus).addClass("center"))
				.append(
						$('<td>').data('consid', pagelist[i].id).width(120)
								.addClass('optcls').append($('<a>').css({
									'margin-right' : '10px'
								}).text('[查看]')).append($status));

		$tr.find("td:eq(1)>a").click(function() {
			lookforpatientinfo($(this));
		});

		$tr.find("td:eq(9)>a:eq(0)").click(function() {
			lookforconsinfo($(this).parent());
		});
		$tr.find("td:eq(9)>a:eq(1)").click(function() {
			var id = $(this).parent().data("consid");
			window.location=rootpath+"/rc/rcmanager!findByIdCon.action?id="+id+"&status=report";
		});
		$body.append($tr);

	}
	$('#tbl_pagelist').find('tbody').replaceWith($body);
	$('#tbl_pagelist').find('tr').hover(function() {
		$(this).css("background", "#ccc");
	}, function() {
		$(this).css("background", "transparent");
	});
}
function fillhospitallist() {
	
	$.lwt_ajax(rootpath + "/rc/rcmanager!findMeHospital.action", {},
			call_back_office_list);

	function call_back_office_list(rtn) {
		
		if (rtn.msg != "SUCCESS")
			return alert(rtn.msg);

		var mlist = ConvertObjectList(rtn.hospitals,"id","name");
		var $selecthospital = makeselectsys(mlist,{'id':'cbxhospital'});
		$('.main .barea .querylist:eq(0)').children("li:eq(4)").empty().append($selecthospital);
		$selecthospital.find("option:eq(0)").text("要求会诊医院");
		
		//var mlsist = ConvertObjectList(rtn.rcstatus,"code","name");
		var $selectstatus = makeselectsys(rtn.rcstatus,{'id':'cbxstatus'});
		$('.main .barea .querylist:eq(0)').children("li:eq(6)").empty().append($selectstatus);
		$selectstatus.find("option:eq(0)").text("会诊状态");
		
		$selecthospital.change(function(){
			var hosid = $(this).val();
			if(hosid == "-1") hosid = "";
			fillmedicalSpecialist(hosid);
		});
	}
	
	function fillmedicalSpecialist(hosid){
		if (pagin.setprocess)
			return;
		pagin.setprocess = true;

		$.lwt_ajax(rootpath + "/rc/rcmanager!findMeOffice.action", {
			"id" : hosid
		}, call_back_find_me_med);
		function call_back_find_me_med(rtn) {
			pagin.setprocess = false;
			if (rtn.msg == "SUCCESS") {
				var mlist = ConvertObjectList(rtn.listoffice,"id","name");
				var $selectmed = makeselectsys(mlist,{'id':'cbxmedical'});
				$('.main .barea .querylist:eq(0)').children("li:eq(5)").empty().append($selectmed);
				$selectmed.find("option:eq(0)").text("医学专科");
			}
		}
	}
}
function fillselect(selectcardId, selectmarriageId, selectsexId, selectnationId) {
	$.lwt_ajax(rootpath + "/rc/rcmanager!init.action", {},
			call_back_select_init);
	function call_back_select_init(rtn) {
		if (rtn.msg != "SUCCESS")
			return alert(rtn.msg);

		var $selectcards = makeselectsys(rtn.cardTypes, {
			'id' : 'cbxcards'
		}, selectcardId);
		var $selectmarriage = makeselectsys(rtn.marrigeType, {
			'id' : 'cbxmarriage'
		}, selectmarriageId);
		var $selectsex = makeselectsys(rtn.sexType, {
			'id' : 'cbxsex'
		}, selectsexId);
		var $selectnation = makeselectsys(rtn.nations, {
			'id' : 'cbxnation'
		}, selectnationId);

		$panel_div.find("table tbody tr:eq(0)>td:eq(1)").empty().append(
				$selectcards).parent().siblings().eq(3).children("td").eq(1)
				.empty().append($selectsex).parent().siblings().eq(5).children(
						"td").eq(1).empty().append($selectnation).parent()
				.siblings().eq(6).children("td").eq(1).empty().append(
						$selectmarriage);
		$panel_div.find("select").attr("disabled", "disabled").css({
			"background-color" : "#fff",
			"color" : "#4c4f50"
		});
	}
}
function lookforpatientinfo(_this) {
	var patientId = _this.data("patientid");
	$panel_div.find("table tbody").find("input").val("");
	if (pagin.setprocess)
		return;
	pagin.setprocess = true;

	$.lwt_ajax(rootpath + "/patient/patManager!findByidPatient.action", {
		"id" : patientId
	}, call_back_findone);
	function call_back_findone(return_obj) {
		pagin.setprocess = false;
		if (return_obj.msg == "SUCCESS") {
			var patient = return_obj.patient;

			$.modelWindow({
				handler : 'lookforpatinfo_panel',
				height : 460,
				width : 750,
				bgvisibel : true,
				title : '查看病人信息 -（只读）',
				html : $panel_div
			});
			fillpatientinfo(patient);
		}
	}
}
function fillpatientinfo(pat) {
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
	patientinfo.find("tr:eq(8)>td:eq(4)>input").val(
			formatsorttime(pat.hospitalTime));
	fillselect(pat.idType, pat.marriage, pat.gender, pat.nation);
}
function lookforconsinfo(_this){
	var consid = _this.data("consid");
	$panel_cons.find('table:eq(0) tbody').find("select option").text("");
	$panel_cons.find('table:eq(0) tbody').find("div").empty();
	if (pagin.setprocess)
		return;
	pagin.setprocess = true;

	$.lwt_ajax(rootpath + "/rc/rcmanager!findByIdCon.action", {
		"id" : consid
	}, call_back_cons_info);
	function call_back_cons_info(rtn) {
		pagin.setprocess = false;
		if (rtn.msg == "SUCCESS") {
			var cons = rtn.consultation;
			
			$.modelWindow({
				handler : 'lookforconsinfo_panel',
				height : 460,
				width : 750,
				bgvisibel : true,
				title : '会诊信息 -（只读）',
				html : $panel_cons
			});

			fillconsinfo(cons);
		}
	}
}
function fillconsinfo(cons){
	if(typeof(cons) == undefined || cons == null)
		return;
	
	var cons_panel = $panel_cons.find('table:eq(0) tbody');
	cons_panel.find("tr:eq(0)>td:eq(1) select option:eq(0)").text(cons.callHospital.name);
	cons_panel.find("tr:eq(1)>td:eq(1) select option:eq(0)").text(cons.consulationType);
	cons_panel.find("tr:eq(2)>td:eq(1) select option:eq(0)").text(cons.consulationMode);
	cons_panel.find("tr:eq(3)>td:eq(1) input:eq(0)").text(cons.hospitalNumber);
	cons_panel.find("tr:eq(4)>td:eq(1) select:eq(0) option:eq(0)").text(cons.conditions);
	cons_panel.find("tr:eq(5)>td:eq(1) select option:eq(0)").text(cons.chargeType);
	cons_panel.find("tr:eq(6)>td:eq(1) select option:eq(0)").text(cons.receiverHospital.name);
	cons_panel.find("tr:eq(7)>td:eq(1) select option:eq(0)").text(cons.sectionOfficeId);
	cons_panel.find("tr:eq(8)>td:eq(1) select option:eq(0)").text(cons.requestDoctorId);
	cons_panel.find("tr:eq(8)>td:eq(1) select option:eq(1)").text(cons.requestDoctorId2);
	cons_panel.find("tr:eq(8)>td:eq(1) select option:eq(2)").text(cons.requestDoctorId3);
	cons_panel.find("tr:eq(8)>td:eq(1) select option:eq(3)").text(cons.requestDoctorId4);
	cons_panel.find("tr:eq(9)>td:eq(1) select option:eq(0)").text(formatsorttime(cons.requestTime));
	cons_panel.find("tr:eq(10)>td:eq(1) select option:eq(0)").text(cons.callHospital.name);
	cons_panel.find("tr:eq(11)>td:eq(1) select option:eq(0)").text(formatsorttime(cons.addTime));
	cons_panel.find("tr:eq(0)>td:eq(4) div").append(cons.diagnosis).css("overflow-y","scroll");
	cons_panel.find("tr:eq(4)>td:eq(4) div").append(cons.conabstract).css("overflow-y","scroll");
	cons_panel.find("tr:eq(8)>td:eq(4) div").append(cons.parpose).css("overflow-y","scroll");
}
$(function() {
	getpagelist();	
	fillhospitallist();
	$('#btn_search').click(function(){	
		pagin.current_page = 1;
		getpagelist();
	});
	
	pagin.BindpagerEvent();
});
