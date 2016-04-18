var pagin = new pagination();
function savereport() {
	var chubu = CKEDITOR.instances.textarea_editor1.getData();
	var suggest = CKEDITOR.instances.textarea_editor2.getData();
	if (chubu == "" && suggest == "")
		return alert("没有需要保存的内容");

	var userid = $('#txt_person').attr("userid");
	var pwd = $('#txt_pas').val();
	if (typeof (pwd) == undefined || pwd == null)
		return alert("请输入密码");

	if (pagin.setprocess)
		return;
	pagin.setprocess = true;

	$.lwt_ajax(rootpath + "/revrc/revmanager!saveReport.action", {
		"id" : $('#btn_tijiao').attr("cid"),
		"userId" : userid,
		"password" : pwd,
		"diagnosis" : chubu,
		"opinion" : suggest
	}, call_back_save_report);

	function call_back_save_report(rtn) {
		pagin.setprocess = false;

		if (rtn.msg != "SUCCESS")
			return alert(rtn.msg);
		else {
			return alert("操作已成功");
		}
	}
}

$(function() {
	$.each($('#cons_ul>li'), function(i) {
		var this_obj = $(this);
		this_obj.click(function() {
			$('.editorarea').hide().eq(i).show();
			this_obj.siblings().css('background-color', '#fff');
			this_obj.css('background-color', '#ccc');
		});
	});
	
	$('#btn_tijiao').click(function(){
		savereport();
	});
});