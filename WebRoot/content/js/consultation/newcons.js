var $buttons = $('<div>').addClass("bottombtn").height(30)			
	.append($('<input type="button" value="关闭" id="btn_close">').addClass("btn").addClass("close"))
	.append($('<input type="button" value="选择" id="btn_select_export">').css("margin-right","10px").addClass("btn").addClass("reset"));
var $panel_div = $('<div>').append($('<div>')
				.append($('<ul>').addClass("ulcls")
				.append($('<li>').append($('<select>').addClass("select").width(180).append($('<option>').text("请选择"))))
				.append($('<li>').append($('<select>').addClass("select").width(150).append($('<option>').text("请选择"))))
				.append($('<li>').append($('<input>').attr("type","text").width(180).addClass("txt")))
				.append($('<li>').append($('<input>').attr("type","button").attr("id","btn_query_export_in_panel").addClass("btn").val("查询")))))
				.append($('<div>').height(265).css('overflow-y','scroll').append($('<table>').addClass("tables").css({"width":"99%","margin-top":"3px"}).append($('<thead>')
				.append($('<tr>').append($('<th>').append($('<input>').attr('type','checkbox').width(20)))
								 .append($('<th>').width(30).text("序号"))
								 .append($('<th>').width(100).text("姓名"))
								 .append($('<th>').width(120).text("职称"))
								 .append($('<th>').width(150).text("所属专科"))
								 .append($('<th>').width(200).text("所属医院"))))
				.append($('<tbody>')))).append($buttons);
function getpagelist() {

}
var pagin = new pagination();
function initcheckbox() {
	if (pagin.setprocess)
		return;
	pagin.setprocess = true;

	$.lwt_ajax(rootpath + "/rc/rcmanager!init.action", {}, call_back_page_init);

	function call_back_page_init(rtn) {
		pagin.setprocess = false;
		if (rtn.msg != "SUCCESS")
			return alert(rtn.msg);

		var $selectcards = makeselectsys(rtn.cardTypes, {
			'id' : 'cbxcards'
		});

		var $selectchargeTypes = makeselectsys(rtn.chargeTypes, {
			'id' : 'cbxchargetypes'
		});
		var $selectconsModes = makeselectsys(rtn.consulationModes, {
			'id' : 'cbxconsmodes'
		});
		var $selectconsTypes = makeselectsys(rtn.consulationTypes, {
			'id' : 'cbxconstypes'
		});
		var $selectillnessTypes = makeselectsys(rtn.illnessTypes, {
			'id' : 'cbxillnesstypes'
		});

		$('#pat_info tbody tr:eq(0)>td:eq(1)').empty().append($selectcards);

		$('#tb_newcons tr:eq(1)>td:eq(1)').empty().append($selectconsTypes)
				.parent().next().children("td:eq(1)").empty().append(
						$selectconsModes).parent().next().next().children(
						"td:eq(1)").empty().append($selectillnessTypes)
				.parent().next().children("td:eq(1)").empty().append(
						$selectchargeTypes);

	}
}

function addnewconsulation() {
	var userid = $('.barnerarea:eq(0) .ttle').data("uid");
	if (userid == undefined || userid == null)
		return alert("请先进行病人验证");
		
	var consulation = $('#tb_newcons tbody');

	var constype = consulation.find("tr:eq(1)>td:eq(1)>select option:selected").val();
	if (constype == -1)
		return alert("请选择会诊类型");

	var consmod = consulation.find("tr:eq(2)>td:eq(1)>select option:selected").val();
	if (consmod == -1)
		return alert("请选择会诊方式");

	var hosnumber = consulation.find("tr:eq(3)>td:eq(1)>input").val();

	var illtype = consulation.find("tr:eq(4)>td:eq(1)>select option:selected").val();
	if (illtype == -1)
		return alert("请选择病情情况");

	var costtype = consulation.find("tr:eq(5)>td:eq(1)>select option:selected").val();
	if (costtype == -1)
		return alert("请选择费别");

	var conshospital = consulation.find("tr:eq(6)>td:eq(1)").data("hosId");
	if (conshospital == undefined || conshospital == null)
		return alert("请选择会诊单位");

	var costdept = consulation.find("tr:eq(7)>td:eq(1)").data("derpartId");
	if (costdept == -1)
		return alert("请选择科室");
	
	var consdate = consulation.find("tr:eq(9)>td:eq(1)>input").val();
	if(consdate == '')
		return alert("请输入要求会诊时间")
	
	var illhistory = CKEDITOR.instances.textarea_editor1.getData();
	var chulizhenduan = CKEDITOR.instances.textarea_editor2.getData();
	var conspurpose = CKEDITOR.instances.textarea_editor3.getData();
	
	var dc1 = '';var dc2 = '';var dc3 = '';var dc4 = '';
	$.each(consulation.find("tr:eq(8)>td:eq(1)>div>span"), function(i) {
		var id = $(this).data('value');
		if (id != undefined && id != null) {
			eval("dc" + (i + 1) + "=\"" + id + "\"");
		}
	});
	var p = {
			"id" : userid,
			"consultation.consulationType" : constype,
			"consultation.consulationMode" : consmod,
			"consultation.chargeType" : costtype,
			"consultation.conditions" : illtype,
			"consultation.hospitalNumber" : hosnumber,
			"consultation.receiverId" : conshospital,
			"consultation.sectionOfficeId" : costdept,
			"consultation.requestTime" : consdate,
			"consultation.requestDoctorId" : dc1,
			"consultation.requestDoctorId2" : dc2,
			"consultation.requestDoctorId3" : dc3,
			"consultation.requestDoctorId4" : dc4,
			"consultation.Conabstract" : illhistory,
			"consultation.diagnosis" : chulizhenduan,
			"consultation.parpose" : conspurpose
		};
	
	 if (pagin.setprocess)
			return;
		pagin.setprocess = true;
	
		$.lwt_ajax(rootpath + "/rc/rcmanager!onCreatePatientAndConsult.action", p, call_back_new_consulation);
	
		function call_back_new_consulation(rtn) {
			pagin.setprocess = false;
	
			if (rtn.msg == "SUCCESS") {					
				alert("恭喜，操作已成功!请上传影像资料");
				$('.barnerarea:eq(1) .content').hide().parent().find(".ttle>img:eq(0)").attr('src',rootpath+'/content/img/ico04.gif').parent().parent().siblings()
				.eq(1).find(".content").show();				
				
				$('#btn_upload_img').click(function(){
					var patid = $('.barnerarea:eq(0) .ttle').data("uid");
					upload(patid,rtn.consultation.id);
				});
				
			}
			else {
				return alert(rtn.msg);
			}
	}
}

function verify_patient_info() {// 验证病人信息

	var cardId = $('#pat_info tbody tr:eq(0)>td:eq(1) select').val();
	if (cardId == undefined || cardId == '-1')
		return alert("请选择证件类型");

	var cardNumber = $('#pat_info tbody tr:eq(1)>td:eq(1) input').val();
	if (cardNumber == undefined || $.trim(cardNumber) == '')
		return alert(cardNumber);

	if (pagin.setprocess)
		return;
	pagin.setprocess = true;

	$.lwt_ajax(rootpath + "/patient/patManager!checkUser.action", {
		"type" : cardId,
		"id" : cardNumber
	}, call_back_check_exist);

	function call_back_check_exist(rtn) {
		pagin.setprocess = false;

		if (rtn.msg == "SUCCESS") {
			var patientinfo = rtn.patient;
			if (confirm("您要操作的病人是：" + patientinfo.name)) {
				var $spans = $('.barnerarea:eq(0) .ttle span');
				$spans.eq(1).text('姓名： ' + patientinfo.name);
				$spans.eq(2).text('身份证号：' + patientinfo.idNumber);
				$spans.eq(3).text('社保号：' + patientinfo.shebao);
				$('.barnerarea:eq(0) .ttle').data("uid", patientinfo.id);
				$('.barnerarea:eq(0) .ttle span:gt(0)').css({"color" : "blue"}).parent().find("img").attr('src',rootpath+'/content/img/ico04.gif');
				$('.barnerarea:eq(0) .content').hide().parent().siblings()
						.eq(0).find(".content").show().parent().find(".ttle>img:eq(0)").attr('src',rootpath+'/content/img/ico03.gif');

			}

		} else {
			if (confirm(rtn.msg)) {
				window.location.href = rootpath
						+ '/pages/consultation/patientinfo.jsp';
			}

		}
	}

}

function select_export() {// 选择专家

	if (pagin.setprocess)
		return;
	pagin.setprocess = true;

	$.lwt_ajax(rootpath + "/rc/rcmanager!findThenHospatil.action", {},
			call_back_select_export);

	function call_back_select_export(rtn) {
		pagin.setprocess = false;

		if (rtn.msg == "SUCCESS") {

			if (rtn.hospitals == undefined || rtn.hospitals == null)
				return alert("没有可以申请的医院");

			var hospital = ConvertObjectList(rtn.hospitals, "id", "name");
			var $selecthospital = makeselectsys(hospital, {
				'id' : 'cbxhospital'
			});
			$panel_div.find("ul li:eq(0)").empty().append(
					$selecthospital.width(180));
			clearselectarea();
			cleartablelist();
			$.modelWindow({
				handler : 'consselectexport',
				height : 400,
				width : 650,
				bgvisibel : true,
				title : '选择专家',
				html : $panel_div
			});

			$selecthospital.change(function() {
				cleartablelist();
				$panel_div.find("ul li:eq(1) select>option:gt(0)").remove();
				var hospitalId = $selecthospital.val();
				if (hospitalId == -1)
					return;
				fromhospitaltodepartment(hospitalId);
			});

			$buttons.find('input:[id="btn_close"]').click(function() {
				$('#modulewindowclose').trigger('click');// 弹层关闭按钮
			});
			$buttons.find('input:[id="btn_select_export"]').click(function() {
				selectexport();// 弹层最下边的选择专家按钮
			});
			$panel_div.find('input[id="btn_query_export_in_panel"]').click(
					function() {
						query_export_by_hosId_derartId();// 弹层上面的查询区域的选择专家按钮
					});
		} else {
			return alert(rtn.msg);
		}
	}
}
function clearselectarea() {// 清空选择区

	$panel_div.find("ul li:eq(1)").find('option:gt(0)').remove();
	$panel_div.find("ul li:eq(2)").find("input").val("");
}
function query_export_by_hosId_derartId() {// 组合条件查询专家信息
	var hosId = $panel_div.find('select[id="cbxhospital"] option:selected')
			.val();
	if (hosId == -1)
		return alert("请选择一个申请医院");

	var depart = $panel_div.find('select[id="cbxdepart"] option:selected')
			.val();
	var txt_key = $panel_div.find('select[id="cbxhospital"]').parent()
			.siblings().eq(1).find('input').val();

	if (pagin.setprocess)
		return;
	pagin.setprocess = true;

	$.lwt_ajax(rootpath + "/rc/rcmanager!findHospatilOfficeUsers.action", {
		"hospid" : hosId,
		"officeid" : depart,
		"name" : txt_key
	}, call_back_query_export);

	function call_back_query_export(rtn) {
		pagin.setprocess = false;

		if (rtn.msg == "SUCCESS") {
			var users = rtn.systemusers;
			if (users == undefined || users == null)
				return;

			makeexporttablelist(users, $panel_div.find(
					'select[id="cbxdepart"] option:selected').text());
		} else {
			return alert(rtn.msg);
		}
	}
}

function selectexport() {// 查询专家

	var $td = $panel_div.find('table').find("tbody >tr>td");
	if ($td.find("input").size() <= 0)
		return alert("没有可以选择的专家");

	var obj = {};
	var index = 0;
	$.each($td.find('input[type="checkbox"]'), function(i) {
		var objarr = {};
		if ($(this).attr("checked")) {
			objarr["id"] = $(this).val();
			objarr["name"] = $(this).parent().siblings().eq(1).text();
			obj[index] = objarr;
			index++;
		}
	});

	if (index > 3) {
		return alert("对不起，一次最多只可以选择三位专家！");
	}
	var $selecthospital = $panel_div
			.find('select[id="cbxhospital"] option:selected');
	var $selectdepart = $panel_div
			.find('select[id="cbxdepart"] option:selected');

	$('#tb_newcons tbody>tr:eq(6)>td:eq(1)').data('hosId',
			$selecthospital.val()).find("span").remove().end().append(
			$('<span>').css("font-weight", "700").text($selecthospital.text()));
	$('#tb_newcons tbody>tr:eq(7)>td:eq(1)').data('derpartId',
			$selectdepart.val()).find("span").remove().end().append(
			$('<span>').css("font-weight", "700").text($selectdepart.text()));

	var $export_div = $('<div>');
	$.each(obj, function(i) {
		$export_div.append($('<span>').css({
			"margin-right" : "8px",
			"font-weight" : "700"
		}).data("value", obj[i].id).text(obj[i].name));
	});

	$('#tb_newcons tbody>tr:eq(8)>td:eq(1)').find('div').remove().end().append(
			$export_div);
	$('#modulewindowclose').trigger('click');

}
function cleartablelist() {// 查询区域的table列表内容清空
	$panel_div.find('table').find("tbody").empty();
}

function fromhospitaltodepartment(hospitalId) {// 根据医院找科室

	if (pagin.setprocess)
		return;
	pagin.setprocess = true;

	$.lwt_ajax(rootpath + "/rc/rcmanager!findOffice.action", {
		"id" : hospitalId
	}, call_back_findOffice);

	function call_back_findOffice(rtn) {
		pagin.setprocess = false;
		if (rtn.msg == "SUCCESS") {
			var list = ConvertObjectList(rtn.officeList, "id", "name");
			var $selectdepartment = makeselectsys(list, {
				'id' : 'cbxdepart'
			});
			$panel_div.find("ul>li:eq(1)").empty().append(
					$selectdepartment.width(150));

			$selectdepartment.change(function() {
				var departId = $selectdepartment.val();
				cleartablelist();
				if (departId == -1)
					return;
				fromdepartmenttoexport(rtn.officeList, departId);
			});
		} else {
			return alert(rtn.msg);
		}
	}
}
function fromdepartmenttoexport(deptartlist, selectId) {// 从科室找专家

	if (deptartlist == undefined || deptartlist == null)
		return;
	var curr_depart = null;
	for ( var i = 0; i < deptartlist.length; i++) {
		if (deptartlist[i].id == selectId) {
			curr_depart = deptartlist[i];
			break;
		}
	}
	if (curr_depart == null)
		return;
	makeexporttablelist(curr_depart.systemusers, curr_depart.name);
}
function makeexporttablelist(list, depart) {// 列出专家列表，因为Systemuser中没有科室名称，需要传递过来
	if (typeof list == undefined || list == null)
		return;
	var $tbody_expert = $('<tbody>');
	for ( var i = 0; i < list.length; i++) {
		var $tr = $('<tr>');
		$tr.append(
				$('<td>').width(20).addClass("center").append(
						$('<input>').attr("type", "checkbox").val(
								list[i].userId))).append(
				$('<td>').width(30).addClass("center").text(i + 1)).append(
				$('<td>').width(100).text(list[i].realName)).append(
				$('<td>').width(120).text(
						(list[i].dict == null ? "" : list[i].dict.name)))
				.append($('<td>').width(150).text(depart)).append(
						$('<td>').width(200).text(list[i].hospital.name));
		$tbody_expert.append($tr);
	}

	$panel_div.find('table').find("tbody").replaceWith($tbody_expert);
	$tbody_expert.find("td").css("padding-left", "5px");
}
function upload(patid,conid){
    var panel=Ext.create('Ext.ux.uploadPanel.UploadPanel',{
    header: false,
    addFileBtnText : '选择文件...',
    uploadBtnText : '上传',
    removeBtnText : '移除所有',
    cancelBtnText : '取消上传',
    file_size_limit : 100,//MB
    width : 750,//指定上传窗口的宽度
    height : 300,//指定上传窗口的高度
    //file_types: '*.jpg',
    //file_types_description: 'Image Files',
    flash_url : rootpath+"/content/js/consultation/ext/swfupload.swf",//必须指定正确的路径
    flash9_url : rootpath+"/content/js/consultation/ext/swfupload_fp9.swf",
    upload_url : rootpath+'/servlet/UploadServlet?patid='+patid+'&conid='+conid
	});
	win = Ext.widget('window', {
		title : '文件上传',
		closeAction : 'hide',
		layout : 'fit',
		resizable : false,
		modal : true,
		items : panel
	});
	win.show();
}
$(function() {
	$('#deditor').width($('.barnerarea').width() - 360).css({
		"margin" : "2px 5px 2px 5px"
	});
	$.each($('#cons_ul>li'), function(i) {
		var this_obj = $(this);
		this_obj.click(function() {
			$('.editorarea').hide().eq(i).show();
			this_obj.siblings().css('background-color', '#fff');
			this_obj.css('background-color', '#ccc');
		});
	});

	$('#btn_verify_patient_info').click(function() {
		verify_patient_info();
	});

	$('#select_export').click(function() {
		select_export();
	});

	$('#btn_save_consulation').click(function() {
		addnewconsulation();
	});
	initcheckbox();
});
