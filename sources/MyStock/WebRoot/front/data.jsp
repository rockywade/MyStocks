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
					url:"/MyStock/front_findPageDtumInfo.do",
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
				window.location.href="/MyStock/login_goToLogin.do?url=../datuminfo/datuminfoStudent.jsp";
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
				<li class="iNavThis"><a href="../front/data.jsp">学习资料</a></li>
				<li><a href="../front/answer.jsp">线上答疑</a></li>
				<li><a href="../front/apply.jsp">活动报名</a></li>
				<li><a href="../front/bookshop.jsp">二手书店</a></li>
				<li><a href="../front/expertReserve.jsp">专家预约</a></li>
				<li><a href="../front/placeReserve.jsp">场地预约</a></li>
			</ul>
		</div>
	</div>
	<!-- 导航结束 -->
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