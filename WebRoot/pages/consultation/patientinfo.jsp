<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/lib/lib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<title>病人信息</title>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
	<script type="text/javascript" src="<%=path %>/content/js/pagination.js"></script>
	<script type="text/javascript" src="<%=path %>/content/js/consultation/patient.js?z"></script>
	<script language="javascript" src="<%=path %>/content/DatePicker/WdatePicker.js"></script>
</head>
<body>
	<div class="main">
		<div class="queryarea">
			<div class="barea">
				<div style="float:left;width:200px;">
					<input type="button" class="btn" id="btn_new_patient" value="新增病人"
						style="margin-right:15px;" />
				</div>

				<div style="float:right;width:450px;">
					<ul class="querylist">
						<li><select id="slt_cardtype" class="select">
								<option>请选择</option>
						</select></li>
						<li><input type="text" id="txt_serarch_key" class="txt ht24"
							value="请输入查询关键字" style="width:240px;color:#ccc;" />
						</li>
						<li><input type="button" class="btn"
							id="btn_search" value="查询" />
						</li>
					</ul>
				</div>
				<br class="cls" />
			</div>
		</div>
		<div>
			<table class="tables" id="tbl_pagelist">
				<thead>
					<tr>
						<th style="width:60px;">序号</th>
						<th style="width:100px;">姓名</th>
						<th style="width:50px;">性别</th>
						<th style="width:100px;">是否已婚</th>
						<th style="width:80px;">出生日期</th>
						<th style="width:80px;">证件类型</th>
						<th style="width:150px;">证件号码</th>
						<th style="width:250px;">所在医院</th>
						<th style="width:180px;">入院时间</th>
						<th style="width:180px;">添加时间</th>
						<th style="width:180px;">更新时间</th>
						<th style="width:180px;">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="12"
							style="height:200px;text-align:center;border:none;"><img
							src="<%=path%>/content/img/loading.gif" alt="正在努力的为你加载哦" />
						</td>
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
				<li class="fst"><a href="#">首页</a>
				</li>
				<li class="pre"><a href="#">上一页</a>
				</li>
				<li class="nxt"><a href="#">下一页</a>
				</li>
				<li class="lst"><a href="#">尾页</a>
				</li>
				<li class="n">转到 第 <input type="text" id="ipt_pagenum"
					class="num" maxlength="5" /> 页</li>
				<li class="n"><input type="button" value="转" class="btn_tz" />
				</li>
			</ul>
		</div>
	</div>
</body>
</html>
