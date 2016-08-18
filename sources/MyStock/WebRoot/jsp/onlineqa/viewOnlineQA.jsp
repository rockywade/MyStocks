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
	<div class="answer_wrap answerB_wrap">
		<div class="answer_tit">
			<h2>
				<s:property value="#request.qa.subject" />
			</h2>
			<samp>
				<s:property value="#request.qa.content" />
			</samp>
		</div>

		<div class="answerB_cont">
			<s:if test='%{#request.qa.image!=null&&""!=#request.qa.image}'>
				<img class="answerB_contImg"
					src='<s:property value="#request.qa.image" />' />
			</s:if>
			<br />
			<s:if
				test='%{#request.qa.attachment!=null&&""!=#request.qa.attachment}'>
				<a href='<s:property value="#request.qa.attachment" />'>附件</a>
			</s:if>
		</div>
	</div>
</body>
</html>
