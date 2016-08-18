<%@ page language="java" pageEncoding="UTF-8"%>

<%

   request.setCharacterEncoding("UTF-8"); //防止乱码问题
   String studentnum = request.getParameter("studentnum");
   
     
%>


<html>
<head>
<title>请假审批列表</title>
<link rel="stylesheet" type="text/css"
	href="../../ext/resources/css/ext-all.css">
<link rel="stylesheet" type="text/css" href="../../css/ext-icon.css">
<script type="text/javascript" src="../../ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="../../ext/ext-all.js"></script>
<script type="text/javascript" src="../../ext/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="../../js/utils.js"></script>
<script type="text/javascript" src="previewstudents.js"></script>

<script>
	  var studentnum = "<%=studentnum%>"; 
	 
   </script>
</head>
<body>
</body>
</html>