<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/lib/lib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<link rel="stylesheet" type="text/css" href="<%=path%>/content/css/cons.css?a"/>
<script type="text/javascript" src="<%=path%>/content/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%=path%>/content/js/consultation/conspeport.js"></script>
</head>
<body>
	<div>
		<table class="tables">
			<thead>
				<tr>
					<th colspan="5" style="font-weight:700;">专家医院远程会诊报告</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="5"></td>
				</tr>
				<tr>
					<td>病人姓名：<s:property value="consultation.patient.name"/></td>
					<td>性别：<s:property value="consultation.patient.gender"/></td>
					<td>年龄：<s:date name="consultation.patient.born" format="yyyy-MM-dd"/></td>						
				</tr>
				<tr>
					<td>会诊医院：<s:property value="consultation.receiverHospital.name"/></td>
					<td>申请医院：<s:property value="consultation.callHospital.name"/></td>
					<td>所属科室：<s:property value="consultation.sectionOfficeId"/></td>						
				</tr>
				<tr>
					<td>会诊类别：<s:property value="consultation.consulationType"/></td>
					<td>会诊时间：<s:date name="consultation.beginConTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td></td>						
				</tr>
				<tr>
					<td colspan="5"></td>
				</tr>
				<tr>
					<td colspan="5">
						<div id="deditor" style="float:left;overflow:hidden;">
					<div>
						<ul id="cons_ul">
							<li style="background-color: rgb(204, 204, 204);">初步诊断</li>
							<li>治疗意见</li>
						</ul>
						<br style="clear:both;"/>
					</div>
					<div class="cons_cnt">
						<div class="editorarea">
							<textarea id="textarea_editor1" name="textarea_editor1" style="height:377px;width:100%;"></textarea>
						</div>
						<div class="editorarea" style="display:none;">
							<textarea id="textarea_editor2" name="textarea_editor2"  style="height:377px;width:100%;"></textarea>
						</div>
						<script language="javascript" type="text/javascript">
							CKEDITOR.replace("textarea_editor1",{ height:180,width :700});
							CKEDITOR.replace("textarea_editor2",{ height:180,width :700});
						</script>
					</div>					
				</div>				
					</td>
				</tr>
				<tr>
					<td colspan="5"></td>
				</tr>
				<tr>
					<td>报告填写人：
					<input id="txt_person" type="text" value="<s:property value='#session.sysuser.realName'/>" class="txt" readonly="readonly" userid="<s:property value='#session.sysuser.userId'/>"/>
					</td>
					<td></select><span style="margin-left:10px;">密码：</span><input id="txt_pas" type="password" value="" class="txt"/></td>
					<td>填写日期：</td>
					<td><s:date name="currDate" format="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
				<tr>
					<td colspan="5" style="text-align:center;height:40px;" >
						<input type="button" class="btn1" value="提交报告" id="btn_tijiao" cid="<s:property value="id"/>"/>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>
