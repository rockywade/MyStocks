<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>活动申请</title>
<link rel="stylesheet" type="text/css"
	href="../../../ext/resources/css/ext-all.css">
<link rel="stylesheet" type="text/css" href="../../../css/ext-icon.css">
<script type="text/javascript"
	src="../../../ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="../../../ext/ext-all.js"></script>
<script type="text/javascript" src="../../../ext/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="alreadyenter.js"></script>
<!-- 文本编辑期 -->
<script type="text/javascript" src="../../../ext/ueditor/ueditor.js"></script>
<script type="text/javascript" charset="utf-8">window.UEDITOR_HOME_URL = "<%=basePath%>/ueditor/";</script>
<script type="text/javascript" src="../../../ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="../../../ueditor/ueditor.all.js"></script>
<script type="text/javascript"
	src="../../../ueditor/lang/zh-cn/zh-cn.js"></script>

<style type="text/css">
<
style type ="text/css">.x-grid3-row td, .x-grid3-summary-row td {
	line-height: 25px;
	//
	控制GRID单元格高度
}
</style>
</style>
</head>
<body>
</body>
</html>