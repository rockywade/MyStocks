<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="common/top.jsp"%>
<style type="text/css">
*{padding: 0;margin: 0;box-sizing: border-box;}
body{padding: 0;margin: 0;font: 12px "微软雅黑";}
li{list-style: none;}
a{text-decoration: none;}
/* 这里开始是导航样式 */
.iNav{
	width: 100%;
	min-width: 1200px;
	background-color: #1a60ac;
	/**border-bottom: 1px solid #114b8f;*/
	border-top: 1px solid #114b8f;
}
.iNavWrap{
	width: 1200px;
	margin: 0 auto;
}
.iNavWrap ul{
	overflow: hidden;
}
.iNavWrap ul li{
	float: left;
}

.iNavWrap ul li a{
	display: block;
	font-size: 16px;
	padding: 10px 30px 15px;/*修改padding可改变宽高*/
	color: #FFFFFF ;
	/*border-top: 5px solid #114b8f;*/
}
.iNavWrap ul li a:hover{/*鼠标移上的样式*/
	color: #000000 !important;
	background-color: #FFFFFF;
}

.iNavThis {
	background-color: #FFFFFF;
}
.iNavThis a{/*选中的样式*/
	color: #000000 !important;
}

</style>
<script type="text/javascript">
	    $(document).ready(function(){
			getDataNewsList();
			initpage();
		});
	    var startAllAppoint = 0;
	    var limitAllAppoint = 10;
	    var currentPageAllAppoint = 1;
	    var totalPageAllAppoint = 0;
	    function getDataNewsList(){
	    	$.ajax({
				type : "post", 
				async : false,  
				url:"front_newsSpecialColumn.do",
				data:{start:startAllAppoint, limit:limitAllAppoint},
				success : function(data,status){
					data = eval("("+data+")");
					insertNewsInfo(data.root);
					startAllAppoint = data.currentResult;
					totalPageAllAppoint = data.totalPage;
				}
			});	
		}

	    function insertNewsInfo(data){
			var s = "";
			$.each(data,function(rowNum,obj){
				if(obj.highlight=="yes"){
					s+='<tr class="news_highlight">';
				}else{
					s+='<tr>';
				}
				s+='<td>'+(rowNum+1)+'</td>';
				if(obj.totop!=='100'){
					s+='<td><i class="news_atTop"></i><a href="content_news.jsp?newsid='+obj.newsid+'" class="newsTit">'+obj.newstitle+'</a></td>';
				}else{
					s+='<td><a href="content_news.jsp?newsid='+obj.newsid+'" class="newsTit">'+obj.newstitle+'</a></td>';
				}
				s+='<td >'+obj.newsdate+'</td>';
				s+='</tr>';
			});
			$("#newsInfo").html(s);
		}

	  //翻页
		function initpage(){
			$('#page').jqPagination({
			  link_string : '',
			  current_page: currentPageAllAppoint, //设置当前页 默认为1
			  max_page : totalPageAllAppoint, //设置最大页 默认为1
			  page_string : '当前第{current_page}页,共{max_page}页',
			  paged : function(page) {
				  startAllAppoint = (page-1)*limitAllAppoint;
			  	  getActivityInfoByGenre();
			  }
			});
		}
	    </script>
</head>
<body>
	<!-- 头部 -->
	<%@ include file="common/head.jsp"%>
	<!-- 导航开始 -->
	<div class="iNav">
		<div class="iNavWrap">
			<ul>
				<li><a href="../index.jsp">首页</a></li>
				<li class="iNavThis"><a href="../front/news.jsp">新闻公告</a></li>
				<li><a href="../front/tutoring.jsp">线下辅导</a></li>
				<li><a href="../front/data.jsp">学习资料</a></li>
				<li><a href="../front/answer.jsp">线上答疑</a></li>
				<li><a href="../front/apply.jsp">活动报名</a></li>
				<li><a href="../front/bookshop.jsp">二手书店</a></li>
				<li><a href="../front/expertReserve.jsp">专家预约</a></li>
				<li><a href="../front/placeReserve.jsp">场地预约</a></li>
			</ul>
		</div>
	</div>
	<!-- 导航结束 -->
	<!-- 新闻公告 -->
	<div class="return">
		<a href="#" onclick="JavaScript:history.back(-1);return false;" /><img
			src="img/icon_return.png"></a>
	</div>
	<section class="w">
		<div class="list_wrap">
			<table class="list_table news_table" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th></th>
						<th colspan="2">新闻专栏</th>
					</tr>
				</thead>
				<tbody id="newsInfo">
				</tbody>
			</table>

			<div class="pagination" id="page">
				<a href="#" class="first" data-action="first">&laquo;</a> <a
					href="#" class="previous" data-action="previous">&lsaquo;</a> <input
					type="text" readonly="readonly" data-max-page="40" /> <a href="#"
					class="next" data-action="next">&rsaquo;</a> <a href="#"
					class="last" data-action="last">&raquo;</a>
			</div>
		</div>
	</section>

	<!-- 尾部 -->
	<%@ include file="common/footer.jsp"%>

</body>
</html>