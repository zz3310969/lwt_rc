<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/lib/lib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>会诊详细信息</title>
<script>
	function print_info(id){
		window.open(rootpath+"/frameset?__report=reports/new_report.rptdesign&__format=HTML&id="+id);
	}
	function show_dicom(id){
		
		window.open(rootpath+"/revrc/revmanager!findImgs.action?id="+id);
	}
</script>
<script type="text/javascript"
	src="<%=path%>/content/js/consultation/consinfo.js?sd"></script>
</head>
<body>
	<div class="main">
		<div class="queryarea">
			<div class="barea">
				<input type="button" class="btn1" onclick="print_info('<s:property value="consultation.id"/>')" value="打印申请单"
					style="margin-right:15px;" /> 
					<input type="button" class="btn1" value="查看病例资料" onclick="show_dicom('<s:property value="consultation.id"/>');" style="margin-right:15px;" /> 
					<s:if test="consultation.actualDoctorId!=null||consultation.actualDoctorId2!=null ||consultation.actualDoctorId3!=null ||consultation.actualDoctorId!=null">
						<s:if test="consultation.revDiag==null || consultation.opinion==null">
							<input type="button" class="btn1" value="填写会诊单"  style="margin-right:15px;" id="btn_report" conid="<s:property value="consultation.id"/>"/>
						</s:if>
					</s:if>
					<s:else>
					<input type="button" class="btn1" value="安排专家" style="margin-right:15px;" id="btn_expert"/>
					<input type="button" class="btn1" value="申请驳回"  style="margin-right:15px;" id="btn_return" conid="<s:property value="consultation.id"/>"/>
					</s:else>
					
			</div>
		</div>
		<div>
			<table class="tables">
			<thead>
				<tr>
						<th colspan="5" style="font-weight:700;">会诊申请单</th>
					</tr>
			</thead>
				<tbody>
					
					<tr>
						<td colspan="5"></td>
					</tr>
					<tr>
						<td colspan="5">申请单位：<s:property value="consultation.callHospital.name"/></td>
					</tr>
					<tr>
						<td colspan="5"></td>
					</tr>
					<tr>
						<td>病人姓名：<s:property value="consultation.patient.name"/></td>
						<td>性别：<s:property value="consultation.patient.gender"/></td>
						<td>生日：<s:date name="consultation.patient.born" format="yyyy-MM-dd"/></td>
						<td>民族：<s:property value="consultation.patient.nation"/></td>
						<td>婚否：<s:property value="consultation.patient.marriage"/></td>
					</tr>
					<tr>
						<td colspan="3">工作单位：<s:property value="consultation.patient.workunit"/></td>
						<td>费别：<s:property value="consultation.chargeType"/>&nbsp;&nbsp;</td>
						<td>会诊方式：<s:property value="consultation.consulationMode"/></td>
					</tr>
					<tr>
						<td colspan="3">社保卡/医保卡：<s:property value="consultation.patient.shebao"/></td>
						<td>住院号：<s:property value="consultation.hospitalNumber"/></td>
						<td>病情情况：<s:property value="consultation.conditions"/></td>
					</tr>
					<tr>
						<td colspan="5"></td>
					</tr>
					<tr>
						<td colspan="3">要求会诊单位：<s:property value="consultation.receiverHospital.name"/></td>

						<td>会诊类型：<s:property value="consultation.consulationType"/></td>

						<td>会诊专科：<span id="cons_depart_in_td"><s:property value="consultation.sectionOfficeId"/></span></td>
					</tr>
					<tr>
						<td colspan="3">要求会诊专家： <s:property value="consultation.requestDoctorId"/>,<s:property value="consultation.requestDoctorId2"/>,<s:property value="consultation.requestDoctorId3"/>,<s:property value="consultation.requestDoctorId4"/>,</td>

						<td>会诊费用： 0</td>

						<td></td>
					</tr>
					<tr>
						<td colspan="3">&nbsp;</td>

						<td>&nbsp;</td>

						<td>&nbsp;</td>
					</tr>
					<tr>
						<td colspan="5"><span style="line-height:35px;font-weight:700;margin-left:8px;">病史摘要：</span><br />
							<div style="padding:0 10px 10px 10px;line-height:28px">
								<s:property value="consultation.Conabstract" escape="false"/>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="5"></td>
					</tr>
					<tr>
						<td colspan="5"><span style="line-height:35px;font-weight:700;margin-left:8px;">目前诊断与处理：</span><br />
							<div style="padding: 0 10px 10px 10px;line-height:28px">
								<s:property value="consultation.diagnosis" escape="false"/></div>
						</td>
					</tr>
					<tr>
						<td colspan="5"></td>
					</tr>
					<tr>
						<td colspan="5"><span style="line-height:35px;font-weight:700;margin-left:8px;">申请会诊目的：</span><br />
							<div style="padding: 0 10px 10px 10px;line-height:28px">
								<s:property value="consultation.parpose" escape="false"/></div>
						</td>
					</tr>
					<tr>
						<td colspan="5"></td>
					</tr>
					<tr>
						<td colspan="3">申请医生：<s:property value="consultation.callHospital.name"/></td>
						<td colspan="2">申请日期：<s:date name="consultation.addTime" format="yyyy-MM-dd"/></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
