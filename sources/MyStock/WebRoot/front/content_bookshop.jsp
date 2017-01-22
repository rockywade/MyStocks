<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>二手书店</title>
<%@ include file="common/top.jsp"%>
<script type="text/javascript">
	    	$(document).ready(function(){
	    		getPojo();
	    		getData();
	    		updatePopularity();
	    		initPage();
			});
			
			function getPojo(){
				$.ajax({
					type : "post", 
					async : false,  
					url:"/MyStock/front_findSecondBookStore.do",
					data:{id:$("#storeid").val()},
					success:function(data,status){
						data = eval("("+data+")");
						$("#subject").html('<i>['+data.data.storeType+']</i>'+data.data.subject);
						$("#publishtime").html(data.data.publishtime);
						$("#content").html(data.data.content);
						if(''==data.data.attachment){
							$("#attachment").remove();
						}else{
							$("#attachment").attr("href",data.data.attachment);
						}
						if(''==data.data.image){
							$("#image").remove();
						}else{
							$("#image").attr("src",data.data.image);
						}
					}
				});
			}
			
			
	    	var start = 0;
	    	var limit = 10;
	    	var currentPage = 1;
	    	var totalPage = 0;
			function getData(){
				$.ajax({
					type : "post", 
					async : false,  
					url:"/MyStock/front_findPageSecondBookStoreJudge.do",
					data:{start:start, limit:limit,storeid:$("#storeid").val()},
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
						      getData();
						  }
						});
			}
			
			function updatePopularity(){
				$.post("/MyStock/front_updateSecondBookStorePopularity.do",{id:$("#storeid").val()},
					function(data,status){
					}
				);
			}

			
			function render(data){
				var s = "";
				$.each(data,function(rowNum,o){
						s+='<li>';
						s+='<div class="reply_wrap reply_wrap1"><div class="reply_name"><samp>';
						s+=o.nickname;
						s+='</samp><i>|</i><span>'+o.judgetime+'</span></div><p>';
						s+=o.content;
						s+='</p></div>';
						s+='</li>';
				});
				$("#judgeUl").html(s);
			}
			
	    </script>
</head>
<body>
	<!-- 头部 -->
	<%@ include file="common/head.jsp"%>
	<input type="hidden" id="storeid"
		value="<%=request.getParameter("storeid") %>" />
	<!-- 新闻内容 -->
	<div class="return">
		<a href="#" onclick="JavaScript:history.back(-1);return false;" /><img
			src="img/icon_return.png"></a>
	</div>
	<section class="w">
		<div class="answer_wrap">
			<div class="answer_tit">
				<h2 id="subject"></h2>
				<samp id="publishtime"></samp>
			</div>
			<div class="answer_cont">
				<p id="content"></p>
				<img class="answerB_contImg" id="image" /> <br />
				<a href="" id="attachment">附件</a>
			</div>
			<div class="answer_reply">
				<ul id="judgeUl">
				</ul>
			</div>
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
