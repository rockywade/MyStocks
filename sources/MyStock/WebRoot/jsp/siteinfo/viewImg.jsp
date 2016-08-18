<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
   String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
   
   request.setCharacterEncoding("UTF-8"); //防止乱码问题
   //String img = request.getParameter("img");

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/ xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title></title>
<link rel="stylesheet" type="text/css" href="../../css/public.css">
<link rel="stylesheet" type="text/css" href="../../css/publicBack.css">
<!-- 图片自动缩放 -->
<script type="text/javascript" src="../../js/jQuery.autoIMG.min.js">
	</script>
</head>

<body>
	<!-- class="answerB_cont" -->
	<div id="img_div">
		<s:if test='%{#request.qa.photo!=null&&""!=#request.qa.photo}'>
			<img id="imgShow" src='<s:property value="#request.qa.photo" />' />
		</s:if>
	</div>


	<script type="text/javascript">
	    $(function() {
		  	//图片自动按比例缩放
	  		$("#img_div").autoIMG();
	  	});
	
	</script>
</body>
</html>
