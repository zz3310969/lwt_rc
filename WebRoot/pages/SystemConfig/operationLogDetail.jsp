<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="includeFile.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>修改用户信息</title>

<script type="text/javascript">
	$(document).ready(function() {
		ajaxParam = {
			"url" : rootpath + "/user/operationLog.action"
		};
		initEditPage("editLinkMenuForm", ajaxParam);
	});
</script>
<style type="text/css">
input,textarea {
	border: 0px;
	width:98%;
	background-color: white;
	resize: none;
}
</style>
</head>
<body>
	<div class="container">
		<form id="editLinkMenuForm">
			<table class="mainTable">
				<tr>
					<td class="mainTable_TitleTd" colspan="4">查看详细信息</td>
				</tr>
				<tr>
					<td class="mainTable_LeftTd">操作类型：</td>
					<td class="mainTable_RightTd"><input
						id="textOperationTypeText" name="textOperationTypeText"
						type="text" disabled="disabled" /></td>
					<td class="mainTable_LeftTd">目标类名：</td>
					<td class="mainTable_RightTd"><input id="textOperationTable"
						name="textOperationTable" type="text" disabled="disabled" /></td>

				</tr>
				<tr>
					<td class="mainTable_LeftTd">记录主键：</td>
					<td class="mainTable_RightTd"><input id="textObjectRecordId"
						name="textObjectRecordId" type="text" disabled="disabled" /></td>
					<td class="mainTable_LeftTd">操作用户：</td>
					<td class="mainTable_RightTd"><input id="textUserName"
						name="textUserName" type="text" disabled="disabled" /></td>
				</tr>
				<tr>
					<td class="mainTable_LeftTd">是否成功：</td>
					<td class="mainTable_RightTd"><input id="textIsSuccessText"
						name="textIsSuccessText" type="text" disabled="disabled" /></td>
					<td class="mainTable_LeftTd">操作时间：</td>
					<td class="mainTable_RightTd"><input id="textAddTime"
						name="textAddTime" type="text" disabled="disabled" /></td>
				</tr>
				<tr>
					<td class="mainTable_LeftTd"><label for="textareaDescription">
							返回消息： </label>
					</td>
					<td class="mainTable_RightTd" colspan="3"><textarea
							id="textareaResultMessage" name="textareaResultMessage"
							disabled="disabled">

</textarea></td>
				</tr>

			</table>
		</form>
	</div>
</body>
</html>
