<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>请假管理</title>
<link rel="stylesheet" type="text/css"
	href="../../ext/resources/css/ext-all.css">
<link rel="stylesheet" type="text/css" href="../../css/ext-icon.css">
<script type="text/javascript" src="../../ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="../../ext/ext-all.js"></script>
<script type="text/javascript" src="../../ext/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="../../js/utils.js"></script>
<script type="text/javascript" src="leaveinfo.js"></script>

<!-- 时间选择js-->
<!--  -->
<script type="text/javascript" src="../../ext/datetime/datetimefield.js"></script>
<script type="text/javascript" src="../../ext/datetime/Spinner.js"></script>
<script type="text/javascript" src="../../ext/datetime/SpinnerField.js"></script>

<!-- 样式 -->
<style type="text/css">
.color {
	color: blue;
	text-decoration: none;
	font-weight: bold;
}

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