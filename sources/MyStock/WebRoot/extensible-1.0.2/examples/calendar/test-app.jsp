<%@ page language="java" pageEncoding="UTF-8"%>

<%

   request.setCharacterEncoding("UTF-8"); //防止乱码问题
   String siteid = request.getParameter("siteid");
   
     
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>时间控件的选</title>
<!-- Sets up all Ext and Extensible includes: -->
<script type="text/javascript" src="../../Extensible-config.js"></script>
<link rel="stylesheet" type="text/css" href="test-app.css" />
<script type="text/javascript" src="data/calendars.js"></script>

<script type="text/javascript" src="CalendarStore.js"></script>
<script type="text/javascript" src="MemoryEventStore.js"></script>
<script type="text/javascript" src="test-app.js"></script>
<script type="text/javascript" src="ext-locales/ext-lang-zh_CN.js"></script>
<script type="text/javascript">
	  Date.prototype.Format = function(fmt){ //author: meizz   
  	   var o = {   
      "M+" : this.getMonth()+1,                 //月份   
      "d+" : this.getDate(),                    //日   
      "h+" : this.getHours(),                   //小时   
      "m+" : this.getMinutes(),                 //分   
      "s+" : this.getSeconds(),                 //秒   
      "q+" : Math.floor((this.getMonth()+3)/3), //季度   
      "S"  : this.getMilliseconds()             //毫秒   
      };   
     if(/(y+)/.test(fmt))   
       fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
       for(var k in o)   
        if(new RegExp("("+ k +")").test(fmt))   
         fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
        return fmt;   
      }
  </script>



<!-- <script type="text/javascript" src="data/events.js"></script>  <script type="text/javascript" src="ext-locales/extensible-lang-zh_CN.js"></script>-->
<script>
	  var siteid = "<%=siteid%>"; 
	 
   </script>

</head>
<body>
</body>

</html>