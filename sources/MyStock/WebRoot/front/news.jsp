<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="common/top.jsp"%>
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