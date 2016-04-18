<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="includeFile.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>修改用户信息</title>

		<script type="text/javascript">
        $(document).ready(function () {
            var valueFieldInputId = "textWebPageId";
            var textFieldInputId = "textUrl";
            var itemParam = { "textField": "url", "valueField": "pageId" };
            var ajaxParam = { "url": rootpath + "/user/linkMenuWebPage.action", "data": { "action": "04"} }
            initAutoComplete(textFieldInputId, valueFieldInputId, ajaxParam, itemParam);

            selectId = "selectFirstLevelModule";
            itemParam = { "textField": "moduleName", "valueField": "moduleId" };
            ajaxParam = { "url": rootpath + "/user/linkMenuFunctionModule.action", "data": { "action": "04"} }
            initDropDownList(selectId, ajaxParam, itemParam);

            ajaxParam = { "url": rootpath + "/user/linkMenuManagement.action" }
            initEditPage("editLinkMenuForm", ajaxParam);
        });
    </script>
	</head>
	<body>
		<div class="container">
			<form id="editLinkMenuForm">
				<table class="mainTable">
					<tr>
						<td class="mainTable_TitleTd" colspan="4">
							修改记录
						</td>
					</tr>
					<tr>
						<td class="mainTable_LeftTd">
							<label for="textMenuName">
								菜单名称：
							</label>
						</td>
						<td class="mainTable_RightTd">
							<input name="textMenuId" type="text" class="hidden" size="35" />
							<input name="textMenuName" type="text" size="35" />
						</td>
						<td class="mainTable_LeftTd">
							<label for="textPageId">
								链接页面：
							</label>
						</td>
						<td class="mainTable_RightTd">
							<input id="textWebPageId" name="textWebPageId" type="text"
								size="35" value="" class="hidden" />
							<input id="textUrl" name="textWebpage.url" type="text"
								size="35" value="" />
						</td>
					</tr>
					<tr>
						<td class="mainTable_LeftTd">
							<label for="textareaDescription">
								菜单说明：
							</label>
						</td>
						<td class="mainTable_RightTd" colspan="3">
							<textarea name="textareaDescription" class="description"></textarea>
						</td>
					</tr>
					<tr>
						<td class="mainTable_ButtonTd" colspan="4">
							<input class="button" type="submit" value="提交" />
							&nbsp;&nbsp;
							<input class="button" type="reset" value="关闭"
								onclick="window.parent.tb_remove();" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>
