<%@ page contentType="text/html; charset=UTF-8"%>
<%

   request.setCharacterEncoding("UTF-8"); //防止乱码问题
   String newsid = request.getParameter("newsid");
     
%>


<!DOCTYPE html>
<html>
<head>
<%@ include file="common/top.jsp"%>
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
	   			      url : "/MyStock/front_newsDetail.do",
	   			      data:{
					      newsid:id
				        },
	   			      async: false,
	   			      success : function(o) {
					      d = eval("("+o+")");
	   			      }
	   		   		});
	   		   		if(d.data.website!=null && d.data.website!=""){
	   		   			window.location.href = "../index.jsp";
	   		   			window.open(d.data.website);
		   		   	}else{
		   		   		$("#newsDetail").html(insertNewsInfo(d.data));
			   		}
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
	<!-- 头部 -->
	<%@ include file="common/head.jsp"%>

	<!-- 新闻内容 -->

	<div class="return">
		<a href="#" onclick="JavaScript:history.back(-1);return false;" /><img
			src="img/icon_return.png"></a>
	</div>
	<section class="w" id="newsDetail"></section>

	<!-- 尾部 -->
	<%@ include file="common/footer.jsp"%>

</body>
</html>
