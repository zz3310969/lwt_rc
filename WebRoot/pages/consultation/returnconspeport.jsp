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
					<td><strong>病人姓名：</strong><s:property value="consultation.patient.name"/></td>
					<td><strong>性别：</strong><s:property value="consultation.patient.gender"/></td>
					<td><strong>年龄：</strong><s:date name="consultation.patient.born" format="yyyy-MM-dd"/></td>						
				</tr>
				<tr>
					<td><strong>会诊医院：</strong><s:property value="consultation.receiverHospital.name"/></td>
					<td><strong>申请医院：</strong><s:property value="consultation.callHospital.name"/></td>
					<td><strong>所属科室：</strong><s:property value="consultation.sectionOfficeId"/></td>						
				</tr>
				<tr>
					<td><strong>会诊类别：</strong><s:property value="consultation.consulationType"/></td>
					<td><strong>会诊时间：</strong><s:date name="consultation.beginConTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td></td>						
				</tr>
				<tr>
					<td colspan="5">
						<strong>初步诊断:</strong>
					</td>
				</tr>
				<tr>
					<td colspan="5" style="padding-left:50px;height:150px;">
						<s:property value="consultation.revDiag" escape="false"/>
					</td>
				</tr>
					<tr>
					<td colspan="5">
						<strong>诊断意见：</strong>
					</td>
					
				</tr>
				<tr>
					<td colspan="5" style="padding-left:50px;height:150px;">
						<s:property value="consultation.opinion" escape="false"/>
					</td>
				</tr>
				<tr>
					<td colspan="5"></td>
				</tr>
				<tr>
					<td><strong>报告填写人：</strong>
					<span><s:property value="systemuser.realName"/></span>
					</td>
					<td colspan="3"><strong>填写日期：</strong><s:date name="consultation.updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>
