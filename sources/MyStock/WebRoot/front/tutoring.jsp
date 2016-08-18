<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- 头部 -->
<%@ include file="common/top.jsp"%>

<!-- 场地预约情况字体样式 -->
<style type="text/css">
.color1 {
	color: #1A60AC;
	text-decoration: none;
	font-weight: bold;
} /*链接设置*/
</style>


<!-- 弹出框-->
<link rel="stylesheet" type="text/css" href="css/dialog.css">
<script type="text/javascript" src="js/dialog.js"></script>

<script type="text/javascript">
	    	$(document).ready(function(){
	    		getData('');
	         	initPage();
			});
	    	var start = 0;
	    	var limit = 10;
	    	var currentPage = 1;
	    	var totalPage = 0;
			function getData(){
				$.ajax({
					type : "post", 
					async : false,  
					url:"front_findPageOfflineFd.do",
					data:{start:start, limit:limit},
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
						      
						  }
						});
			}
			
			function render(data){
				var s = "";
				$.each(data,function(rowNum,o){
				   var  status = o.status; 
				    
				   if(status.indexOf("高亮") ==-1){
				     s+='<tr>';
				    }else{
				     s+='<tr class="news_highlight">';
				     }
					    s+='<td>'+(rowNum+1)+'</td>';
				     if(status.indexOf("置顶") ==-1){
				         s+='<td>'+o.fxxm+'</td>';
					  }else{ 
					      s+='<td><i class="news_atTop"></i>'+o.fxxm+'</td>';
					    }
						s+='<td>'+o.fxtime+'</td>';
						s+='<td>'+o.fxaddress+'</td>';
						s+='<td>'+o.fxteacher+'</td>';
						s+='<td>'+o.xmzise+'</td>';
						s+='<td>'+o.bmnumber+'</td>';
						s+='<td>'+o.plnumber+'</td>';
						s+='<td>'+o.plnumber+'</td>';
					   // s+='<td>'+o.status+'</td>';
					   //s+='<td><a href="javascript:void(0)" onclick="viewDetail(\''+o.xmid+'\');"  class="color1" >'+o.plnumber+'</a></td>';
						s+='<td><a href="javascript:void(0)" onclick="viewDetail(\''+o.xmid+'\');" class="contentBtn">查看详情</a></td>';
						s+='</tr>';
				    });
				    
				    if(data.length==0){
					   $("#tbody").html("暂无数据");
				    }else{
					   $("#tbody").html(s);
				    }
				
			}
			
			
			function publish(){
				window.location.href="login_goToLogin.do?url=../pbfx/offlineFdStudent.jsp";
			}
			
          
          function viewDetail(xmid){
                var dateAll = null;
		         $.ajax({
		          type : "POST",
   			      url : "offlineFd_findSingOfflineFd.do",
   			      data:{
				      xmid:xmid
			        },
   			      async: false,
   			      success : function(o) {
				      dateAll = eval("("+o+")");
   			    }
   		    });
	
             var docStr ='<div class="apply_popupClose popupCloseBtn" onclick="popupClose()">';
                 docStr += '<img src="img/icon_close.png" />';
                 docStr += '</div>';
                 docStr += '<table class=" popup_Table" cellspacing="0" cellpadding="0">';
                
                 docStr += '<tr>';
                 docStr += '<td>辅学项目：</td>';
                 docStr += '<td colspan="3">'+dateAll.data.fxxm+'</td>';
                 docStr += '</tr>';
                 
                 docStr += '<tr>';
                 docStr += '<td>辅学时间：</td>';
                 docStr += '<td colspan="3">'+dateAll.data.fxtime+'</td>';
                 docStr += '</tr>';
                 
                 docStr += '<tr>';
                 docStr += '<td>辅学地点：</td>';
                 docStr += '<td>'+dateAll.data.fxaddress+'</td>';
                 docStr += '<td>辅学老师：</td>';
                 docStr += '<td>'+dateAll.data.fxteacher+'</td>';
                 docStr += '</tr>';
                 
                 docStr += '<tr>';
                 docStr += '<td>项目容量：：</td>';
                 docStr += '<td>'+dateAll.data.xmzise+'</td>';
                 docStr += '<td>已报名人数：</td>';
                 docStr += '<td>'+dateAll.data.bmnumber+'</td>';
                 docStr += '</tr>';
                 
                 docStr += '<tr>';
                 docStr += '<td>人气：</td>';
                 docStr += '<td>'+dateAll.data.plnumber+'</td>';
                 docStr += '<td>项目状态：</td>';
                 docStr += '<td>'+dateAll.data.bmstatus+'</td>';
                 docStr += '</tr>';
                      
                 docStr += '<tr>';
                 docStr += '<td>项目简介：</td>';
                 docStr += '<td colspan="3">'+dateAll.data.xmintro+'</td>';
                 docStr += '</tr>';
                 docStr += '</table>';
                 
                 docStr += '<div class="popup_btn">';
                 docStr += '<a a href="javascript:void(0)" onclick="publish()">我要报名</a>';
                 docStr += '</div>';
                     
                 $("#tank").html(docStr);
          
          	popupShow();
          }
	   </script>
</head>
<body>
	<!-- 头部 -->
	<%@ include file="common/head.jsp"%>

	<!-- 线下辅导 -->
	<div class="return">
		<a href="#" onclick="JavaScript:history.back(-1);return false;" /><img
			src="img/icon_return.png"></a>
	</div>
	<section class="w">
		<div class="list_wrap">
			<table class="list_table tutoring_table" cellpadding="0"
				cellspacing="0">
				<thead>
					<tr>
						<th>编号</th>
						<th>辅学项目</th>
						<th>辅学时间</th>
						<th>辅学地点</th>
						<th>辅学老师</th>
						<th>项目容量</th>
						<th>已报名人数</th>
						<th>人气</th>
						<!--
	        				<th>项目状态</th>
	        				-->
						<th>评论</th>
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



	<!-- 弹窗 -->
	<section class="popupWrap">
		<div class="popup" id="tank"></div>
	</section>

	<!-- 尾部 -->
	<%@ include file="common/footer.jsp"%>
</body>
</html>