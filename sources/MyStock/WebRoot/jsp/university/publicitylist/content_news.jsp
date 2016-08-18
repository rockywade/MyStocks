<%@ page contentType="text/html; charset=UTF-8"%>
<%

   request.setCharacterEncoding("UTF-8"); //防止乱码问题
   String newsid = request.getParameter("newsid");
     
%>


<!DOCTYPE html>
<html>
<head>
<title>求是学院学生服务平台</title>
<link rel="stylesheet" href="../../../css/public.css" />
<link rel="stylesheet" href="../../../front/css/jqpagination.css" />
<script type="text/javascript"
	src="../../../front/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="../../../front/js/jquery.jqpagination.js"></script>
<script type="text/javascript" src="../../../front/js/public.js"></script>
<!--兼容-->
<style type="text/css">
article, aside, dialog, footer, header, section, footer, nav, figure,
	menu {
	display: block;
}
</style>
<script>
	        (function() {
	            if (!
	                        /*@cc_on!@*/
	                            0) return;
	            var e = "abbr, article, aside, audio, canvas, datalist, details, dialog, eventsource, figure, footer, header, hgroup, mark, menu, meter, nav, output, progress, section, time, video".split(', ');
	            var i = e.length;
	            while (i--) {
	                document.createElement(e[i]);
	            }
	        })()
	    </script>
<!--兼容 end  -->
<style type="text/css">
<
style type ="text/css">.x-grid3-row td, .x-grid3-summary-row td {
	line-height: 25px;
	//
	控制GRID单元格高度
}
</style>
</style>
</style>
<script type="text/javascript">
		 	var newsid = "<%=newsid%>";
		 	$(document).ready(function(){
				getNewsDetail(newsid);
				//alert(newsid);
			}); 
		 	function getNewsDetail(id){
				var d = null;
			        $.ajax({
			         type : "POST",
	   			      url : "front_newsDetail.do",
	   			      data:{
					      newsid:id
				        },
	   			      async: false,
	   			      success : function(o) {
					      d = eval("("+o+")");
	   			      }
	   		   		});
				$("#newsDetail").html(insertNewsInfo(d.data));
			}
			function insertNewsInfo(obj){
				var s = "";
				s+='<div class="news_title">';
				s+='<h1>'+obj.newstitle+'</h1>';
				s+='<a href="'+obj.website+'">'+obj.website+'</a>';
				s+='<samp>作者：'+obj.writer+'<i class="ikg2"></i>'+obj.newsdate+'</samp>';
				s+='</div>';
				s+='<div class="new_wrap">';
				s+='<p>'+obj.content+'</p>';
				s+='</div>';
				return s;
			}
		 </script>
</head>
<body>
	<!-- 新闻内容 -->
	<div class="return returnB">
		<a href="#" onclick="JavaScript:history.back(-1);return false;" /><img
			src="../../../front/img/icon_return.png"></a>
	</div>
	<section class="w2" id="newsDetail"></section>
</body>
</html>
