<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

  
   request.setCharacterEncoding("UTF-8"); //防止乱码问题
   String xmid = request.getParameter("xmid");
%>

<html>
<head>
<title>项目评论</title>
<link rel="stylesheet" type="text/css"
	href="../../ext/resources/css/ext-all.css">
<link rel="stylesheet" type="text/css" href="../../css/ext-icon.css">
<script type="text/javascript" src="../../ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="../../ext/ext-all.js"></script>
<script type="text/javascript" src="../../ext/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="../../js/utils.js"></script>
<script type="text/javascript" src="pl.js"></script>

<script>
	  var xmid = "<%=xmid%>"; 
    </script>

<style type="text/css">
.x-grid3-row td, .x-grid3-summary-row td {
	line-height: 25px;
	//
	控制GRID单元格高度
}

.x-textfield {
	background: none;
	border-right: 0px solid;
	border-top: 0px solid;
	border-left: 0px solid;
	border-bottom: #000000 0px solid;
}
;
</style>

</head>
<body>
</body>
</html>