<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/lib/lib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>会诊接收列表</title>
<script type="text/javascript" src="<%=path%>/content/js/pagination.js"></script>
<script type="text/javascript"
	src="<%=path%>/content/js/consultation/rcvconslist.js?sd"></script>
<script language="javascript"
	src="<%=path%>/content/DatePicker/WdatePicker.js"></script>
</head>
<body>
	<div class="main">
		<div class="queryarea consbg">
			<div class="barea">
				<div style="height:30px;padding-top:4px;">
					<ul class="querylist" style="float:none;">
						<li style="display:none;"><span style="line-height:24px;">要求会诊单位：</span>
						</li>
						<li style="display:none;"><input type="text" class="txt" />
						</li>
						<li><span style="line-height:24px;">病人姓名：</span>
						</li>
						<li><input type="text" class="txt"  style="width:100px;"/></li>
						<li><select class="select">
								<option value="-1">申请医院</option>
						</select></li>
						<li><select class="select">
								<option value="-1">医学专科</option>
						</select></li>
						<li><select class="select">
								<option value="-1">会诊状态</option>
								<option value="1">会诊申请</option>
								<option value="2">会诊失败</option>
								<option value="3">会诊提交</option>
								<option value="4">会诊安排</option>
								<option value="5">会诊完成</option>
						</select></li>
						<li style="line-height:24px;"><span>申请日期：</span>
						</li>
						<li><input type="text" class="txt Wdate"
							onfocus="WdatePicker({readOnly:true});"  style="width:90px;"/>
						</li>
						<li><span style="line-height:24px;"> 到 </span>
						</li>
						<li><input type="text" class="txt Wdate"
							onfocus="WdatePicker({readOnly:true});"  style="width:90px;"/>
						</li>
						<li><input type="button" class="btn" id="btn_search"
							value="查询" /></li>
					</ul>
				</div>
				<br class="cls" />
			</div>
		</div>
		<br class="cls" />
		<div>
			<table class="tables" id="tbl_pagelist" style="margin-top:6px;">
				<thead>
					<tr>
						<th style="width:60px;">序号</th>
						<th style="width:80px;">病人姓名</th>
						<th style="width:150px;">发起方医院</th>
						<th style="width:90px;">申请时间</th>
						<th style="width:150px;">要求会诊单位</th>
						<th style="width:80px;">要求专家</th>
						<th style="width:80px;">安排专家</th>
						<th style="width:120px;">安排时间</th>
						<th style="width:120px;">会诊状态</th>
						<th style="width:80px;">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="12"
							style="height:200px;text-align:center;border:none;"><img
							src="<%=path%>/content/img/loading.gif" alt="正在努力的为你加载哦" /></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div id="pagearea">
			<ul id="pageleft">
				<li>当前共有<span>0</span>条纪录，</li>
				<li>当前第<span>1/1</span>页，</li>
				<li>每页<span>15</span>条纪录</li>
			</ul>
			<ul id="pageright">
				<li class="fst"><a href="#">首页</a></li>
				<li class="pre"><a href="#">上一页</a></li>
				<li class="nxt"><a href="#">下一页</a></li>
				<li class="lst"><a href="#">尾页</a></li>
				<li class="n">转到 第 <input type="text" id="ipt_pagenum"
					class="num" maxlength="5" /> 页</li>
				<li class="n"><input type="button" value="转" class="btn_tz" />
				</li>
			</ul>
		</div>
	</div>
</body>
</html>
