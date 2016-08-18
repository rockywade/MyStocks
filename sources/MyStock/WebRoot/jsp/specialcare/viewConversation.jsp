<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/ xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title></title>
<link rel="stylesheet" type="text/css" href="../../css/public.css">
</head>

<body>
	<table class="viewCon_table popup_Table" cellpadding="0"
		cellspacing="0">
		<tr>
			<td>记录人</td>
			<td><s:property value="#request.conversation.conversationtype" /></td>
			<td>姓名</td>
			<td><s:property value="#request.conversation.conversationname" /></td>
		</tr>
		<tr>
			<td>时间</td>
			<td><s:property value="#request.conversation.conversationtime" /></td>
			<td>地点</td>
			<td><s:property value="#request.conversation.conversationpalce" /></td>
		</tr>
		<tr>
			<td>主要问题</td>
			<td colspan="3"><s:property
					value="#request.conversation.problem" /></td>
		</tr>
		<tr>
			<td>辅导方案</td>
			<td colspan="3"><s:property
					value="#request.conversation.solution" /></td>
		</tr>
		<s:if
			test='%{#request.conversation.stuff!=null&&""!=#request.conversation.stuff}'>
			<tr>
				<td>附件</td>
				<td colspan="3"><a
					href="<s:property value="#request.conversation.stuff" />">附件</a></td>
			</tr>
		</s:if>
	</table>
</body>
</html>
