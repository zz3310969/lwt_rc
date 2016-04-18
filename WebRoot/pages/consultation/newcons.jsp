<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/lib/lib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>新建会诊</title>

<link rel="stylesheet" type="text/css" href="<%=path%>/content/css/cons.css?a"/>
<script type="text/javascript" src="<%=path %>/content/js/pagination.js"></script>
<script type="text/javascript" src="<%=path%>/content/js/consultation/newcons.js"></script>
<script language="javascript" src="<%=path %>/content/DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/content/ckeditor/ckeditor.js"></script>

<link rel="stylesheet" type="text/css" href="<%=path%>/content/js/consultation/ext/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/content/js/consultation/ext/uploadPanel/UploadPanel.css" />
<script type="text/javascript" src="<%=path%>/content/js/consultation/ext/ext-all.js"></script>
<script type="text/javascript" src="<%=path%>/content/js/consultation/ext/swfupload.js"></script>
<script type="text/javascript" src="<%=path%>/content/js/consultation/ext/uploadPanel/UploadPanel.js?d"></script>

</head>
<body>
	<div>
		<div class="barnerarea">
			<div class="ttle">
				<img alt="" src="<%=path %>/content/img/ico03.gif"/>
				<span style="margin-left:5px;">病人信息</span>
				<span style="margin-left:20px;"></span>
				<span></span>
				<span></span>
				<span></span>
			</div>
			<div class="content" >
				<table class="newcons" id="pat_info">
					<tbody>
						<tr>
							<td class="tt">证件类型：</td>
							<td></td>
							<td class="alert">
							</td>
						</tr>
						<tr>
							<td class="tt">证件号码：</td>
							<td><input type="text" class="txt ht24"  maxlength="18" value="123456789012345678"/>
							</td>
							<td class="alert">
							</td>
						</tr>
						<tr>
							<td class="tt"></td>
							<td><input type="button" class="btn1" value="验证病人信息" id="btn_verify_patient_info"/></td>
							<td class="alert">
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

		<div class="barnerarea">
			<div class="ttle">
				<img alt="" src="<%=path %>/content/img/ico04.gif"/> <span style="margin-left:5px;">会诊信息</span>
			</div>
			<div class="content" style="display:none;">
				<div style="float:left; width:330px;">
				<table class="newcons" id="tb_newcons">
					<tbody>
						<tr>
							<td class="tt1">申请单位：</td>
							<td><s:property value="#session.sysuser.hospital.name" /></td>
							<td class="alert"></td>
						</tr>
						<tr>
							<td class="tt1">会诊类型：</td>
							<td>
								
							</td>
							<td class="alert">
							</td>
						</tr>
						<tr>
							<td class="tt1">会诊方式：</td>
							<td>
								
							</td>
							<td class="alert">
							</td>
						</tr>
						<tr>
							<td class="tt1">住院号：</td>
							<td><input type="text" class="txt ht24" style="width:120px;border-color:#999;" value=""/>
							</td>
							<td class="alert">
							</td>
						</tr>
						<tr>
							<td class="tt1">病情情况：</td>
							<td>
							
							</td>
							<td class="alert">
							</td>
						</tr>
						<tr>
							<td class="tt1">费别：</td>
							<td>
								
							</td>
							<td class="alert">
							</td>
						</tr>
						<tr style="height:50px;">
							<td class="tt1">要求会诊单位：</td>
							<td colspan="2" style="line-height:23px;">
								<a id="select_export" style="color:#0000ff;" href="javascript:;">按医院选专家</a> | 
								<a id="select_export" style="color:#0000ff;" href="javascript:;">按科室选专家</a><br/>
							</td>
							
						</tr>
						<tr>
							<td class="tt1">医学专科：</td>
							<td>
								
							</td>
							<td class="alert">
							</td>
						</tr>
						<tr>
							<td class="tt1">所选专家：</td>
							<td style="line-height:25px;" colspan="2">
							</td>
						</tr>
						<tr>
							<td class="tt1">要求会诊时间：</td>
							<td style="line-height:25px;" colspan="2">
							<input type="text" class="txt Wdate" onfocus="WdatePicker({readOnly:true});"  style="width:120px;"/>
							</td>
						</tr>
						<tr>
							<td class="tt1">申请医生：</td>
							<td><s:property value="#session.sysuser.realName" /></td>
							<td class="alert">
							</td>
						</tr>
						<tr>
							<td class="tt1">申请日期：</td>
							<td>
							<%
								Date tt =new Date();
								out.write(new SimpleDateFormat("yyyy-MM-dd").format(tt));
							 %>
							</td>
							<td class="alert">
							
							</td>
						</tr>
					</tbody>
				</table>				
				</div>
				<div id="deditor" style="float:left;overflow:hidden;">
					<div>
						<ul id="cons_ul">
							<li style="background-color: rgb(204, 204, 204);">病史摘要</li>
							<li>目前处理与诊断</li>
							<li>申请会诊目的</li>
						</ul>
						<br style="clear:both;"/>
					</div>
					<div class="cons_cnt">
						<div class="editorarea">
							<textarea id="textarea_editor1" name="textarea_editor1" style="height:377px;"></textarea>
						</div>
						<div class="editorarea" style="display:none;">
							<textarea id="textarea_editor2" name="textarea_editor2"  style="height:377px;"></textarea>
						</div>
						<div class="editorarea" style="display:none;">
							<textarea id="textarea_editor3" name="textarea_editor3" ></textarea>
						</div>	
					</div>					
				</div>
				<script language="javascript" type="text/javascript">
					CKEDITOR.replace("textarea_editor1",{ height:290});
					CKEDITOR.replace("textarea_editor2",{ height:290});
					CKEDITOR.replace("textarea_editor3",{ height:290});
				</script>
				<br class="cls"/>
				<div style="margin:8px 0 8px 120px;">
					<input type="button" value="保存，下一步"  id="btn_save_consulation" class="btn1"/>
				</div>
			</div>
			</div>
			<div class="barnerarea">
			<div class="ttle">
				<img alt="" src="<%=path %>/content/img/ico04.gif"/>
				<span style="margin-left:5px;">影像资料</span>
			</div>
			<div class="content" style="display:none;">
				<table class="newcons" id="pat_dicom">
					<tbody>
						<tr>
							<td style="width:420px;height:100px;padding-left:118px;"><input type="button" value="上传影像资料"  id="btn_upload_img" class="btn1"/></td>
						</tr>
						
					</tbody>
				</table>
			</div>
		</div>
		
	</div>
</body>
</html>
