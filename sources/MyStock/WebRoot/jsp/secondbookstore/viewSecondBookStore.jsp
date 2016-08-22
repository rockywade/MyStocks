<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/ xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title></title>
<link rel="stylesheet" type="text/css" href="../../css/public.css">
	<link rel="stylesheet" type="text/css" href="../../css/publicBack.css">
</head>

<body>
	<div class="answer_wrap answerB_wrap" style="width: 360px;">
		<div class="answer_tit">
			<h2>
				【
				<s:property value="#request.store.storeType" />
				】
				<s:property value="#request.store.subject" />
			</h2>
			<samp>
				<s:property value="#request.store.content" />
			</samp>
		</div>

		<div class="answerB_cont">
			<s:if test='%{#request.store.image!=null&&""!=#request.store.image}'>
				<img class="answerB_contImg"
					src='<s:property value="#request.store.image" />' />
			</s:if>
			<br />
			<s:if
				test='%{#request.store.attachment!=null&&""!=#request.store.attachment}'>
				<a href='<s:property value="#request.store.attachment" />'>附件</a>
			</s:if>
		</div>
	</div>
</body>
</html>
