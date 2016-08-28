<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="common/top.jsp"%>
<script type="text/javascript">
		 $(document).ready(function(){
				getDataActivityInfo();
				initpage();
			});

			var startAllAppoint = 0;
		    var limitAllAppoint = 5;
		    var currentPageAllAppoint = 1;
		    var totalPageAllAppoint = 0;
			function getDataActivityInfo(){
				$.ajax({
					type : "post", 
					async : false,  
					url:"front_dataActivityInfo.do",
					data:{start:startAllAppoint, limit:limitAllAppoint},
					success : function(data,status){
						data = eval("("+data+")");
						insertActivityInfo(data.root);
						startAllAppoint = data.currentResult;
						totalPageAllAppoint = data.totalPage;
					}
				});	
			}

		function insertActivityInfo(data){
			var s = "";
			$.each(data,function(rowNum,o){
				s+='<tr>';
				s+='<td>'+(rowNum+1)+'</td>';
				s+='<td onclick=reserveConShow("'+o.activityid+'")><p class="newsTit">'+o.activityname+'</p></td>';
				s+='<td>'+o.attendnum+'/'+o.capacity+'</td>';
				s+='<td></td>';
				s+='</tr>';
			});
			$("#tbody1").html(s);
		}
		
		//活动详情
		function reserveConShow(id){
			//alert(id);
			var d = null;
		        $.ajax({
		         type : "POST",
   			      url : "front_activityDetail.do",
   			      data:{
				      aid:id
			        },
   			      async: false,
   			      success : function(o) {
				      d = eval("("+o+")");
   			      }
   		   		});
			$("#activityInfo").html(renderActivityInfo(d.data));
			popupShow();
		}

		function renderActivityInfo(d){
			//alert(d.id);
			var s = '';
			s+='<tr>';
			s+='<td>活动名称</td>';
			s+='<td>'+d.activityname+'</td>';
			s+='</tr>';
			s+='<tr>';
			s+='<td>所在学期</td>';
			s+='<td>'+d.inschoolterm+'</td>';
			s+='<td>类别</td>';
			s+='<td>'+d.activitygenre+'</td>';
			s+='</tr>';
			s+='<tr>';
			s+='<td>面向对象</td>';
			s+='<td>'+d.faceobj+'</td>';
			s+='<td>容量</td>';
			s+='<td>'+d.attendnum+'/'+d.capacity+'</td>';
			s+='</tr>';
			s+='<tr>';
			s+='<td>活动地点</td>';
			s+='<td>'+d.activityplace+'</td>';
			s+='<td>时长</td>';
			s+='<td>'+d.timeofduration+'</td>';
			s+='</tr>';
			s+='<tr>';
			s+='<td>活动时间</td>';
			s+='<td>'+d.activitytime+'</td>';
			s+='<td>报名截止时间</td>';
			s+='<td>'+d.signupendtime+'</td>';
			s+='</tr>';
			s+='<tr>';
			s+='<td>考评分</td>';
			s+='<td>'+d.score+'分</td>';
			s+='</tr>';
			s+='<tr>';
			s+='<td>活动内容</td>';
			s+='<td colspan="3">'+d.activitycontent+'</td>';
			s+='</tr>';
			return s;
		}
		function attend(){
			window.location.href="front_goToLogin.do?url=../university/attend/activityinfo.jsp";
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
				  getDataActivityInfo();
			  }
			});
		}
		 </script>
</head>
<body>
	<!-- 头部 -->
	<%@ include file="common/head.jsp"%>

	<div class="return">
		<a href="#" onclick="JavaScript:history.back(-1);return false;" /><img
			src="img/icon_return.png"></a>
	</div>
	<section class="w3">

		<div class="apply_nav">
			<nav>
				<ul>
					<li class="cur"><a href="apply.jsp">全部</a></li>
					<li><a href="apply_lecture.jsp">学科讲座</a></li>
					<li><a href="apply_planning.jsp">生涯规划</a></li>
					<li><a href="apply_health.jsp">心理健康</a></li>
					<li><a href="apply_construction.jsp">党团建设</a></li>
					<li><a href="apply_sports.jsp">文体活动</a></li>
					<li><a href="apply_other.jsp">其他</a></li>
				</ul>
			</nav>
			<!--	    		<div class="apply_ser">-->
			<!--	    			<input type="text" />-->
			<!--	    			<input type="button" value="搜索" />-->
			<!--	    		</div>-->
		</div>

		<div class="apply_list">
			<div class="apply_list_l">

				<!-- 活动分类 -->
				<div class="apply_class">
					<!-- 学科讲座 -->
					<div class="list_wrap">
						<table class="list_table apply_table" cellpadding="0"
							cellspacing="0">
							<thead>
								<tr>
									<th colspan="2">全部</th>
									<th>已报名人数/容量</th>
									<th>
										<!--<a href="apply_lecture.jsp">more>></a>-->
									</th>
								</tr>
							</thead>
							<tbody id="tbody1">
							</tbody>
							<tbody id="tbody2">
							</tbody>
							<tbody id="tbody3">
							</tbody>
							<tbody id="tbody4">
							</tbody>
							<tbody id="tbody5">
							</tbody>
							<tbody id="tbody6">
							</tbody>
						</table>
					</div>
					<!-- 生涯规划 -->
					<div class="list_wrap">
						<table class="list_table apply_table" cellpadding="0"
							cellspacing="0">
							<!--<thead>
				        			<tr>
				        				<th colspan="2">生涯规划</th>
				        				<th>已报名人数/容量</th>
				        				<th><a href="apply_planning.jsp">more>></a></th>
				        			</tr>
				        		</thead>
				        		
				        		<tbody id="tbody2">
				        		</tbody>
				        		-->
						</table>
					</div>

					<!-- 心理健康 -->
					<div class="list_wrap">
						<table class="list_table apply_table" cellpadding="0"
							cellspacing="0">
							<!--<thead>
				        			<tr>
				        				<th colspan="2">心理健康</th>
				        				<th>已报名人数/容量</th>
				        				<th><a href="apply_health.jsp">more>></a></th>
				        			</tr>
				        		</thead>
				        		
				        		<tbody id="tbody3">
				        		</tbody>-->
						</table>
					</div>

					<!-- 党团建设 -->
					<div class="list_wrap">
						<table class="list_table apply_table" cellpadding="0"
							cellspacing="0">
							<!--<thead>
				        			<tr>
				        				<th colspan="2">党团建设</th>
				        				<th>已报名人数/容量</th>
				        				<th><a href="apply_construction.jsp">more>></a></th>
				        			</tr>
				        		</thead>
				        		
				        		<tbody id="tbody4">
				        		</tbody>-->
						</table>
					</div>

					<!-- 文体活动 -->
					<div class="list_wrap">
						<table class="list_table apply_table" cellpadding="0"
							cellspacing="0">
							<!--<thead>
				        			<tr>
				        				<th colspan="2">文体活动</th>
				        				<th>已报名人数/容量</th>
				        				<th><a href="apply_sports.jsp">more>></a></th>
				        			</tr>
				        		</thead>
				        		
				        		<tbody id="tbody5">
				        		</tbody>-->
						</table>
					</div>

					<!-- 其他 -->
					<div class="list_wrap">
						<table class="list_table apply_table" cellpadding="0"
							cellspacing="0">
							<!--<thead>
				        			<tr>
				        				<th colspan="2">其他</th>
				        				<th>已报名人数/容量</th>
				        				<th><a href="apply_other.jsp">more>></a></th>
				        			</tr>
				        		</thead>
				        		
				        		<tbody id="tbody6">
				        		</tbody>-->
						</table>
					</div>
				</div>
				<div class="pagination" id="page">
					<a href="#" class="first" data-action="first">&laquo;</a> <a
						href="#" class="previous" data-action="previous">&lsaquo;</a> <input
						type="text" readonly="readonly" data-max-page="40" /> <a href="#"
						class="next" data-action="next">&rsaquo;</a> <a href="#"
						class="last" data-action="last">&raquo;</a>
				</div>
			</div>
			<%@ include file="common/apply_right.jsp"%>
		</div>

	</section>

	<!-- 弹窗 -->
	<section class="apply_popupWrap popupWrap">
		<div class="apply_popup popup">
			<!-- 弹窗关闭按钮 -->
			<div class="apply_popupClose popupCloseBtn">
				<img src="img/icon_close.png" />
			</div>
			<table class="apply_conTable popup_Table" id="activityInfo"
				cellspacing="0" cellpadding="0">
			</table>
			<div class="apply_btn popup_btn">
				<a href="javascript:;" onclick="attend()">我要报名</a>
			</div>
		</div>
	</section>

	<!-- 尾部 -->
	<%@ include file="common/footer.jsp"%>
</body>
</html>
