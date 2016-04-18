<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/lib/lib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
	<title>页面管理</title>
	<script type="text/javascript" src="<%=path %>/content/js/pagination.js"></script>
	<script type="text/javascript" src="<%=path %>/content/js/sysconf/datadictionary.js"></script>
	<script type="text/javascript" src="<%=path %>/content/js/control.js"></script>
</head>
<body>
	<div class="main">
		<div class="queryarea">
			<div class="barea" >
				<div style="float:left;width:200px;">
					<input type="button" class="btn" id="btn_new_dict" value="新字典" style="margin-right:15px;"/>
				</div>
				
				<div style="float:right;width:325px;">
					<ul class="querylist">
						<li><input type="text" id="txt_serarch_key" class="txt ht24" value="请输入查询关键字" style="width:240px;color:#ccc;"/></li>
						<li><input type="button" class="btn" id="btn_serach_dictionary" value="查询"/></li>
					</ul>
				</div>
				<div id="sltarea" style="float:right;">
				
				</div>
				<br class="cls"/>
			</div>
		</div>
		<div>
			<table class="tables" id="tbl_pagelist">
				<thead>
					<tr>
						<th style="width:60px;">序号</th>
						<th style="width:100px;">字典类型</th>
						<th style="width:150px;">类型描述</th>
						<th style="width:100px;">内容ID</th>
						<th style="width:200px;">内容</th>
						<th style="width:200px;">内容描述</th>
						<th style="width:150px;">添加时间</th>
						<th style="width:150px;">更新时间</th>
						<th style="width:180px;">操作</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<td colspan="9" style="height:200px;text-align:center;border:none;"><img src="<%=path %>/content/img/loading.gif" alt="正在努力的为你加载哦"/></td>
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
            <li class="n">转到 第 <input type="text" id="ipt_pagenum" class="num" maxlength="5"/> 页</li>
            <li class="n"><input type="button" value="转" class="btn_tz"/></li>
        </ul>
    </div>
	</div>
</body>
</html>
