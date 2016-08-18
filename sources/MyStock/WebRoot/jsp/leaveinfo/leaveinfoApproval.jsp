<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
<script type="text/javascript" src="leaveinfoApproval.js"></script>

<!-- 去掉邊框樣式 -->
<style type="text/css">
.cassdo {
	background: none;
	border-right: 0px solid;
	border-top: 0px solid;
	border-left: 0px solid;
	border-bottom: #000000 0px solid;
}

;
.x-textarea {
	padding: 2px 2px;
	vertical-align: middle;
	background: #fff url() repeat-x 0 0 !important;
	border: 0 !important;
	overflow-x: "visible";
	overflow-y: "visible";
}
</style>

<!--
    <script type="text/javascript" src="../../ext/ueditor/ueditor.js"></script>
    
  -->
</head>
<body>
</body>
</html>