<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>二手书店</title>
<%@ include file="common/top.jsp"%>
<script type="text/javascript">
	    	$(document).ready(function(){
	    		getData('','');
	    		initPage();
			});
	    	var start = 0;
	    	var limit = 10;
	    	var currentPage = 1;
	    	var totalPage = 0;
			function getData(onlineoakey,storeType){
				$.ajax({
					type : "post", 
					async : false,  
					url:"front_findPageSecondBookStore.do",
					data:{start:start, limit:limit,key:onlineoakey,storeType:storeType,key: $("#key").val()},
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
						      var storeType = $("#storeType").val();
							  getData(onlineoakey,storeType);
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
						s+='<td>'+o.storeType+'</td>';
						s+='<td>';
						if(1==o.topflag){
							s+=top;
						}
						s+=o.subject+'</td>';
						s+='<td>'+o.nickname+'</td>';
						s+='<td>'+o.publishtime+'</td>';
						s+='<td>'+o.popularity+'</td>';
						s+='<td>'+o.replynickname+'</td>';
						s+='<td>'+'<a href="content_bookshop.jsp?storeid='+o.id+'"  class="contentBtn">查看详情</a></td>';
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
				var storeType = $("#storeType").val();
				getData(onlineoakey,storeType);
			}
			
			function publish(){
				window.location.href="front_goToLogin.do?url=../secondbookstore/secondbookstore.jsp";
			}
	    </script>
</head>
<body>
	<!-- 头部 -->
	<%@ include file="common/head.jsp"%>

	<!-- 二手书店 -->

	<div class="return">
		<a href="#" onclick="JavaScript:history.back(-1);return false;" /><img
			src="img/icon_return.png"></a>
	</div>
	<section class="w">
		<div class="dataScreen_wrap">
			<ul>
				<li><select id="storeType">
						<option value="">全部</option>
						<option value="出">出</option>
						<option value="求">求</option>
				</select></li>
				<li><input type="text" id="key" placeholder="按关键字搜索或按作者搜索"
					class="search_txt" /></li>
				<li class="input_ser"><input type="button" value="搜索"
					onclick="search()" class="date_search" /></li>
			</ul>
		</div>
		<div class="list_wrap">
			<table class="list_table" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th>出/求</th>
						<th>书名</th>
						<th>发布人</th>
						<th>发布时间</th>
						<th>人气</th>
						<th>最新回复人</th>
						<th>操作</th>
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