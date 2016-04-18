<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/lib/lib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html  xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>用户角色管理</title>
	<script type="text/javascript" src="<%=path %>/content/js/pagination.js"></script>
	<script type="text/javascript" src="<%=path %>/content/js/sysconf/roleconf.js"></script>
</head>
<body>
	<div class="main">
		<div class="queryarea">
			<div class="barea">
				<input type="button" class="btn" id="btn_new_role" value="新角色" />				
			</div>
		</div>
		<div>
			<table class="tables" id="tbl_pagelist" style="margin-top:8px;">
				<thead>
					<tr>
						<th style="width:60px;">序号</th>
						<th style="width:80px;">角色ID</th>
						<th style="width:150px;">角色名称</th>
						<th style="width:260px;">角色描述</th>
						<th style="width:150px;">添加时间</th>
						<th style="width:150px;">更新时间</th>
						<th style="width:230px;">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="9" style="height:200px;text-align:center;border:none;"><img src="<%=path %>/content/img/loading.gif" alt="正在努力的为你加载哦"/></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
