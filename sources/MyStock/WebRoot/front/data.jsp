<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="common/top.jsp"%>


<!-- 下载字体样式 -->
<style type="text/css">
.color1 {
	color: blue;
	text-decoration: none;
	font-weight: bold;
} /*链接设置*/
</style>

<script type="text/javascript">
	    	$(document).ready(function(){
	    		getData('','');
	    		initPage();
			});
	    	var start = 0;
	    	var limit = 10;
	    	var currentPage = 1;
	    	var totalPage = 0;
			function getData(datumname,shareman){  //datumname,shareman,sharegrade
				$.ajax({
					type : "post", 
					async : false, 
					url:"front_findPageDtumInfo.do",
					data:{start:start, limit:limit,datumname:datumname,shareman:shareman},
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
						      var datumname = $("#datumname").val();
						      var shareman = $("#shareman").val();
							  getData(datumname,shareman);
						  }
						});
			}
			
			function render(data){
				var s = "";
				$.each(data,function(rowNum,o){  
				       var  status = o.status; 
						s+='<tr>';
				        if(status.indexOf("已置顶") ==-1){
				          s+='<td>'+o.datumname+'</td>';
					     }else{  
					        s+='<td><i class="news_atTop"></i>'+o.datumname+'</td>';
					     }
						s+='<td>'+o.datumsize+'</td>';
						s+='<td>'+o.datumstyle+'</td>';
						s+='<td>'+o.downnum+'</td>';
						s+='<td><a href="javascript:void(0)" onclick="golog();" class="color1">下载</a></td>';
						s+='<td>'+o.shareman+'</td>';
						s+='<td>'+o.sharetime+'</td>';
						s+='<td>'+o.sharegrade+'</td>';
						s+='</tr>';
				});
				
				 if(data.length==0){
					   $("#tbody").html("暂无数据");
				   }else{
					   $("#tbody").html(s);
				   }
			}
			
			function search(){
				 var datumname = $("#datumname").val();
			     var shareman = $("#shareman").val();
			     getData(datumname,shareman);
			}
			
			//未登录 去登录页面
			function golog(){
				window.location.href="login_goToLogin.do?url=../datuminfo/datuminfoStudent.jsp";
			}
	    </script>
</head>
<body>
	<!-- 头部 -->
	<%@ include file="common/head.jsp"%>

	<!-- 学习资料 -->
	<div class="return">
		<a href="#" onclick="JavaScript:history.back(-1);return false;" /><img
			src="img/icon_return.png"></a>
	</div>
	<section class="w">
		<div class="dataScreen_wrap">
			<ul>
				<li><p>资料名称</p>
					<input type="text" name="datumname" id="datumname"
					placeholder="请输入资料名称" /></li>
				<li><p>分享人</p>
					<input type="text" name="shareman" id="shareman"
					placeholder="请输入分享人" /></li>
				<li class="input_ser"><input type="button" value="搜索"
					class="date_search" onclick="search()" /></li>
			</ul>
		</div>
		<div class="list_wrap">
			<table class="list_table data_table" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th>资料名称</th>
						<th>资料大小</th>
						<th>资料格式</th>
						<th>下载次数</th>
						<th>资料下载</th>
						<th>分享人</th>
						<th>分享时间</th>
						<th>资料评分</th>
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