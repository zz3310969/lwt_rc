<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>蓝卫通多终端监护管理系统</title>
<link rel="stylesheet" href="<%=path %>/content/css/com.css" type="text/css"></link>
<link rel="stylesheet" href="<%=path %>/content/css/login.css?232" type="text/css"></link>
<script type="text/javascript" src="<%=path %>/content/js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript">
	var baseurl = "<%=path%>";
	var block = false;
	var _this = this;
	function user_login() {
		var txt_user = $.trim($('#txt_username').val());
		var txt_pass = $.trim($('#txt_password').val());
		var rand_code = $.trim($('#txt_code').val());

		if (txt_user == '')
			return alert("请输入用户名！");

		if (txt_pass == '')
			return alert("请输入登录密码！");

		if (rand_code == '')
			return alert("请输入验证码！");

		var p = {
			"sysUser.userName" : txt_user,
			"sysUser.password" : txt_pass,
			"randCode" : rand_code
		};
		
		//if(_this.block = true)
			//return;
		$.lwt_ajax(baseurl + "/user/usermanager!login.action", p,call_back_function);

		function call_back_function(return_obj) {
			_this.block = false;
			if(return_obj.msg == "SUCCESS"){
				window.location.href = baseurl + "/pages/index.jsp";
			}else{
				$('#yzm').attr('src',baseurl+ "/util/randomCodeImage.jsp?"+ new Date().getTime().toString());
				return alert(return_obj.msg);
			}
		}
	}
	$(function() {
		$('#dclose').click(function() {
			$('#dhelp').hide();
		});
		$('#yzm').click(function(){
			$(this).attr('src',baseurl
				+ "/util/randomCodeImage.jsp?"
				+ new Date().getTime().toString());
		});
		$('#btn_login').click(function(){
			user_login();
		});
		$('#btn_reset').click(function(){
			$('input[type="text"]').val("");
			$('input[type="password"]').val("");
		});
		window.setTimeout(function(){
			
		}, 1000);
		
	});


	//这个就是键盘触发的函数
	var SubmitOrHidden = function(evt){
	    evt = window.event || evt;
	    if(evt.keyCode==13){//如果取到的键值是回车
	          //do something
	    	user_login();
	     }else{
	        //其他键  dosomething
	    }
	};
	window.document.onkeydown=SubmitOrHidden;//当有键按下时执行函数
	
</script>
</head>
<body>
	<div class="logintop"></div>
	<div id="mainPage">
		<div class="dlt">
			<img src="<%=path%>/content/img/loginlogo1.png"
				style="width:310px;height:290px;margin-top:30px;" class="png" />
		</div>
		<div class="drt">
			<div>
				<h1>蓝卫通远程医疗管理平台</h1>
			</div>
			<div>
				<p>用户名</p>
				<input type="text" value="zwy" class="text" 
					id="txt_username" /> <br class="cls" />
			</div>
			<div>
				<p>密&nbsp;&nbsp;&nbsp;码</p>
				<input type="password" value="1" class="text pas" id="txt_password" /> 
				<br	class="cls" />
			</div>
			<div>
				<p>验证码</p>
				<input type="text" value="" maxlength="6" class="text yy pas" id="txt_code" /> 
				<img id="yzm" src='<%=path%>/util/randomCodeImage.jsp' alt="看不清？点击换一张" />
				<br class="cls" />
			</div>
			<div>
				<input type="button" class="btn lt" id="btn_login" /> 
				<input type="button" class="btn rt" id="btn_reset"/> <br class="cls" />
			</div>
		</div>
	</div>
	<div id="dhelp" style="display: none;">
		<div>
			<p class="title">
				蓝卫通小贴士 <img id="dclose"
					src="<%=path%>/content/img/pic_close.gif" alt="关闭" />
			</p>
			<ul>
				<li><a href="http://www.chinabsc.com/">蓝卫通官方网站</a></li>
				<li><a href="#">常见问题2</a></li>
				<li><a href="#">常见问题3</a></li>
				<li><a href="#">常见问题4</a></li>
				<li><a href="#">常见问题5</a></li>
				<li><a href="#">常见问题6</a></li>
			</ul>
		</div>
	</div>
	<div class="bottom">
		<p>Copyright©2013 BSC Inc. 版权所有</p>
	</div>
</body>
</html>

