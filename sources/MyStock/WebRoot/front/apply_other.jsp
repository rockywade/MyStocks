<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="common/top.jsp"%>
<script type="text/javascript">
		 $(document).ready(function(){
				getActivityInfoByGenre();
				initpage();
			});

		var startAllAppoint = 0;
	    var limitAllAppoint = 10;
	    var currentPageAllAppoint = 1;
	    var totalPageAllAppoint = 0;
		function getActivityInfoByGenre(){
			$.ajax({
				type : "post", 
				async : false,  
				url:"/MyStock/front_activityInfoByGenre.do",
				data:{start:startAllAppoint, limit:limitAllAppoint,key:"qt",serchKey:$("#serch").val()},
				success : function(data,status){
					data = eval("("+data+")");
					renderActivityInfo(data.root);
					startAllAppoint = data.currentResult;
					totalPageAllAppoint = data.totalPage;
				}
			});	
		}

		function renderActivityInfo(data){
			var s = "";
			$.each(data,function(rowNum,o){
				s+='<tr>';
				s+='<td>'+(rowNum+1)+'</td>';
				s+='<td onclick=reserveConShow("'+o.activityid+'")>'+o.activityname+'</td>';
				s+='<td>'+o.attendnum+'/'+o.capacity+'</td>';
				s+='<td></td>';
				s+='</tr>';
			});
			$("#tbody_qt").html(s);
		}

		//活动详情
		function reserveConShow(id){
			//alert(id);
			var d = null;
		        $.ajax({
		         type : "POST",
   			      url : "/MyStock/front_activityDetail.do",
   			      data:{
				      aid:id
			        },
   			      async: false,
   			      success : function(o) {
				      d = eval("("+o+")");
   			      }
   		   		});
			$("#activityInfo").html(insertActivityInfo(d.data));
			popupShow();
		}

		function insertActivityInfo(d){
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
			s+='<td>活动日期</td>';
			s+='<td>'+d.activitytime+'</td>';
			s+='<td>时间</td>';
			s+='<td colspan="3">08:30</td>';
			s+='</tr>';
			s+='<tr>';
			s+='<td>报名截止</td>';
			s+='<td>'+d.signupendtime+'</td>';
			s+='<td>时间</td>';
			s+='<td>18:00</td>';
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

		//搜索
		function serch(){
			getActivityInfoByGenre();
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
				<li><a href="../front/answer.jsp">线上答疑</a></li>
				<li class="iNavThis"><a href="../front/apply.jsp">活动报名</a></li>
				<li><a href="../front/bookshop.jsp">二手书店</a></li>
				<li><a href="../front/expertReserve.jsp">专家预约</a></li>
				<li><a href="../front/placeReserve.jsp">场地预约</a></li>
			</ul>
		</div>
	</div>
	<div class="return">
		<a href="#" onclick="JavaScript:history.back(-1);return false;" /><img
			src="img/icon_return.png"></a>
	</div>
	<section class="w3">

		<div class="apply_nav">
			<nav>
				<ul>
					<li><a href="apply.jsp">全部</a></li>
					<li><a href="apply_lecture.jsp">学科讲座</a></li>
					<li><a href="apply_planning.jsp">生涯规划</a></li>
					<li><a href="apply_health.jsp">心理健康</a></li>
					<li><a href="apply_construction.jsp">党团建设</a></li>
					<li><a href="apply_sports.jsp">文体活动</a></li>
					<li class="cur"><a href="apply_other.jsp">其他</a></li>
				</ul>
			</nav>
			<div class="apply_ser">
				<input id="serch" type="text" /> <input type="button" value="搜索"
					onclick="serch()" />
			</div>
		</div>

		<div class="apply_list">
			<div class="apply_list_l">

				<!-- 活动分类 -->
				<div class="apply_class">
					<!-- 其他 -->
					<div class="list_wrap">
						<table class="list_table apply_table" cellpadding="0"
							cellspacing="0">
							<thead>
								<tr>
									<th colspan="2">其他</th>
									<th>已报名人数/容量</th>
									<th></th>
								</tr>
							</thead>
							<tbody id="tbody_qt">
							</tbody>
						</table>
					</div>

					<div class="pagination" id="page">
						<a href="#" class="first" data-action="first">&laquo;</a> <a
							href="#" class="previous" data-action="previous">&lsaquo;</a> <input
							type="text" readonly="readonly" data-max-page="40" /> <a
							href="#" class="next" data-action="next">&rsaquo;</a> <a href="#"
							class="last" data-action="last">&raquo;</a>
					</div>

					<!--			    		<div class="paging">-->
					<!--			        		<ul>-->
					<!--			        			<li><a href="javascript:;" onclick="randomBespeak('+d.id+')">上一页</a></li>-->
					<!--			        			<li><a href="#">1 / 10</a></li>-->
					<!--			        			<li><a href="javascript:;" onclick="randomBespeak('+d.id+')">下一页</a></li>-->
					<!--			        		</ul>-->
					<!--			        	</div>-->

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
				<a href="/MyStock/login_goToLogin.do">我要报名</a>
			</div>
		</div>
	</section>

	<!-- 尾部 -->
	<%@ include file="common/footer.jsp"%>
</body>
</html>
