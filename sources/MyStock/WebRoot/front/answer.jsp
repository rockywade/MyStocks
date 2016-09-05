<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>

<%@ include file="common/top.jsp"%>
<script type="text/javascript">
	    	$(document).ready(function(){
	    		getData('');
	    		initPage();
			});
	    	var start = 0;
	    	var limit = 10;
	    	var currentPage = 1;
	    	var totalPage = 0;
			function getData(onlineoakey){
				$.ajax({
					type : "post", 
					async : false,  
					url:"front_findPageOnlineQA.do",
					data:{start:start, limit:limit,key:onlineoakey},
					success:function(data,status){
						data = eval("("+data+")");
						render(data.root);
						start = data.currentResult;
						totalPage = data.totalPage;
					    
					}
				});
			}
			
			function initPage(){
				$('.pagination').jqPagination({
						  link_string : '',
						  current_page: currentPage, //设置当前页 默认为1
						  max_page : totalPage, //设置最大页 默认为1
						  page_string : '当前第{current_page}页,共{max_page}页',
						  paged : function(page) {
						  	  start = (page-1)*limit;
						      var onlineoakey = $("#onlineoakey").val();
							  getData(onlineoakey);
						  }
						});
			}
			
			function render(data){
				var s = "";
				var top ='<i class="news_atTop"></i>';
				var highlight ='class="news_highlight"';
				$.each(data,function(rowNum,o){
						s+='<tr ';
						if(1==o.highlight){
							s+=highlight;
						}
						s+='>';
						s+='<td>'+(rowNum+1)+'</td>';
						s+='<td> <p class="newsTit">';
						if(1==o.topflag){
							s+=top;
						}
						s+=o.subject+'</p></td>';
						s+='<td>'+o.nickname+'</td>';
						s+='<td>'+o.publishtime+'</td>';
						s+='<td>'+o.popularity+'</td>';
						s+='<td>'+o.replynickname+'</td>';
						s+='<td>'+'<a href="content_answer.jsp?qaid='+o.id+'" class="contentBtn">查看详情</a></td>';
						s+='</tr>';
				});
				if(data.length==0){
					$("#tbody").html("暂无数据");
				}else{
					$("#tbody").html(s);
				}
			}
			
			function search(){
				var onlineoakey = $("#onlineoakey").val();
				getData(onlineoakey);
			}
			
			function publish(){
				window.location.href="front_goToLogin.do?url=../onlineqa/onlineqa.jsp";
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
				<li><a href="../front/news.jsp">新闻公告</a></li>
				<li><a href="../front/tutoring.jsp">线下辅导</a></li>
				<li><a href="../front/data.jsp">学习资料</a></li>
				<li  class="iNavThis"><a href="../front/answer.jsp">线上答疑</a></li>
				<li><a href="../front/apply.jsp">活动报名</a></li>
				<li><a href="../front/bookshop.jsp">二手书店</a></li>
				<li><a href="../front/expertReserve.jsp">专家预约</a></li>
				<li><a href="../front/placeReserve.jsp">场地预约</a></li>
			</ul>
		</div>
	</div>
	<!-- 导航结束 -->
	<!-- 线上答疑 -->

	<div class="return">
		<a href="#" onclick="JavaScript:history.back(-1);return false;" /><img
			src="img/icon_return.png"></a>
	</div>
	<section class="w">
		<div class="dataScreen_wrap">
			<ul>
				<li><input type="text" id="onlineoakey" value=""
					placeholder="按关键字或作者搜索" /></li>
				<li class="input_ser"><input type="button" value="搜索"
					class="date_search" onclick="search()" /></li>
			</ul>
		</div>
		<div class="list_wrap">
			<table class="list_table news_table" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th>序号</th>
						<th>主题</th>
						<th>作者</th>
						<th class="tdCen">发布时间</th>
						<th class="tdCen">人气</th>
						<th class="tdCen">最新回复人</th>
						<th class="tdCen">操作</th>
					</tr>
				</thead>
				<tbody id="tbody">
				</tbody>
			</table>
			<div class="pagination">
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