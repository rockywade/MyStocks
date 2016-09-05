<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="common/top.jsp"%>
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
				<li><a href="../front/apply.jsp">活动报名</a></li>
				<li><a href="../front/bookshop.jsp">二手书店</a></li>
				<li class="iNavThis"><a href="../front/expertReserve.jsp">专家预约</a></li>
				<li><a href="../front/placeReserve.jsp">场地预约</a></li>
			</ul>
		</div>
	</div>
	<!-- 导航结束 -->
	<script type="text/javascript">
		  $(document).ready(function(){
				getDataExpertSbInfo();
				initpage1();
				initSelect();
				
				// 专家图片的高
	    		var expertPhotoW = $(".expertPhoto").width();
				$(".expertPhoto").css({"height":expertPhotoW});
				$(window).resize(function(){
					var expertPhotoW = $(".expertPhoto").width();
					$(".expertPhoto").css({"height":expertPhotoW});
				})
			});

		  	var startAllAppoint = 0;
	    	var limitAllAppoint = 10;
	    	var currentPageAllAppoint = 1;
	    	var totalPageAllAppoint = 0;
			function getDataExpertSbInfo(){
				$.ajax({
					type : "post", 
					async : false,  
					url:"front_dataExpertSbInfo.do",
					data:{start:startAllAppoint, limit:limitAllAppoint,yx:$("#yx").val(),bm:$("#bm").val(),experttype:$("#experttype").val(),ename:$("#ename").val()},
					success : function(data,status){
						data = eval("("+data+")");
						renderAllAppoint(data.root);
						startAllAppoint = data.currentResult;
						totalPageAllAppoint = data.totalPage;
					}
				});
			}

			function renderAllAppoint(data){
				var s = "";
				$.each(data,function(rowNum,obj){
						//alert(obj.expertName);
						s+='<li onclick=showAppoint('+obj.id+')><div class="showAppointW">';
						if(obj.expertPhoto!=null && obj.expertPhoto!=""){
							s+='<div class="expertPhoto"><img src="'+obj.expertPhoto+'" /></div>';
						}else{
							s+='<div class="expertPhoto"><img src="../img/defaultpic/photo.jpg"/></div>';
						}
						s+='<p>'+(rowNum+1)+'</p>';
						s+='<p>'+obj.expertName+'</p>';
						s+='</div></li>';
				});
				$("#allExpert").html(s);
			}

			function showAppoint(eid){
				var d = null;
		        $.ajax({
		         type : "POST",
   			      url : "front_expertInfo.do",
   			      data:{
				      id:eid
			        },
   			      async: false,
   			      success : function(o) {
				      d = eval("("+o+")");
   			      }
   		   		});
				$("#expertInfo").html(renderExpertInfo(d.data));
				showSbInfo();
			}

			function renderExpertInfo(d){
				//alert(d.id);
				var s = '';
				s+='<div class="popupCloseBtn" onclick="popupClose();">';
				s+='<img src="../admin/img/icon_close.png" />';
				s+='</div>';
				s+='<div class="expert_info">';
				s+='<div class="expertInfo_img">';
				if(d.expertPhoto!=null && d.expertPhoto!=""){
					s+='<img src="'+d.expertPhoto+'"/>';
				}else{
					s+='<img src="../img/defaultpic/photo.jpg"/>';
				}
				s+='</div>';
				s+='<div class="expertInfo_txt ">';
				s+='<table>';
				s+='<tr>';
				s+='<td>姓名</td>';
				s+='<td>'+d.expertName+'</td>';
				s+='</tr>';
				s+='<tr>';
				s+='<td>单位</td>';
				s+='<td>'+d.expertUnit+'</td>';
				s+='</tr>';
				s+='<tr>';
				s+='<td>邮箱</td>';
				s+='<td>'+d.expertEmail+'</td>';
				s+='</tr>';
				s+='<tr>';
				s+='<td>联系电话</td>';
				s+='<td>'+d.expertphone+'</td>';
				s+='</tr>';
				s+='<tr>';
				s+='<td>办公地址</td>';
				s+='<td colspan="3">'+d.workaddress+'</td>';
				s+='</tr>';
				s+='<tr>';
				s+='<td>个人简介</td>';
				s+='<td colspan="3">'+d.personalInfro+'</td>';
				s+='</tr>';
				s+='</table>';
				s+='</div>';
				s+='</div>';
				s+='<div class="apply_btn popup_btn">';
				s+='<a href="javascript:;" onclick="bespeak()">我要预约</a>';
				s+='</div>';
				return s;
			}

			//搜索
			function search(){
				var yx = $("#yx").val();
				var bm = $("#bm").val();
				var experttype = $("#experttype").val();
				var ename = $("#ename").val();
				getDataExpertSbInfo();
			}
			
			//翻页
			function initpage1(){
				$('#page1').jqPagination({
				  link_string : '',
				  current_page: currentPageAllAppoint, //设置当前页 默认为1
				  max_page : totalPageAllAppoint, //设置最大页 默认为1
				  page_string : '当前第{current_page}页,共{max_page}页',
				  paged : function(page) {
				  	  startAllAppoint = (page-1)*limitAllAppoint;
				  	getDataExpertSbInfo();
				  }
				});
			}

			//院系下拉菜单
			function initSelect(){
				$.post("front_findXyComb.do",{},
						function(data,status){
							data = eval("("+data+")");
							initYx(data.root);
						}
					);
			}

			function initYx(data){
				$.each(data,function(rowNum,o){
					$("#yx").append('<option value=\''+o.value+'\'>'+o.text+'</option>');
				});
			}

			//专家预约详情
			function showSbInfo(){
				$(".expert_popupWrap").show().animate({opacity:'1'},300);
			}

			function bespeak(){
				window.location.href="front_goToLogin.do?url=../university/expertbespeak/studentbespeak.jsp";
			}
		  </script>
	<!-- 专家预约 -->
	<div class="return">
		<a href="#" onclick="JavaScript:history.back(-1);return false;" /><img
			src="img/icon_return.png"></a>
	</div>
	<section class="w">

		<div class="expertScreen_w">
			<div class="expertScreen_l">
				<ul>
					<li>
						<p>
							院<i></i>系
						</p> <select id="yx" name="yx">
							<option value=''>全部</option>
					</select>
					</li>
					<li>
						<p>
							部<i></i>门
						</p> <select id="bm" name="bm">
							<option value=''>全部</option>
							<option value='研究部'>研究部</option>
							<option value='教学部'>教学部</option>
					</select>
					</li>
					<li>
						<p>专家类型</p> <select id="experttype" name="expertType">
							<option value=''>全部</option>
							<option value='英语'>英语</option>
							<option value='数学'>数学</option>
					</select>
					</li>
					<li>
						<p>
							姓<i></i>名
						</p> <input id="ename" type="text" />
					</li>
				</ul>
			</div>
			<div class="expertScreen_r">
				<input type="button" value="查询" onclick="search()" />
			</div>
		</div>
		<div class="list_wrap">
			<div class="expert_list">
				<ul id="allExpert">
				</ul>
			</div>
			<div class="pagination" id="page1">
				<a href="#" class="first" data-action="first">&laquo;</a> <a
					href="#" class="previous" data-action="previous">&lsaquo;</a> <input
					type="text" readonly="readonly" data-max-page="40" /> <a href="#"
					class="next" data-action="next">&rsaquo;</a> <a href="#"
					class="last" data-action="last">&raquo;</a>
			</div>
		</div>
	</section>

	<!-- 弹窗 -->
	<section class="expert_popupWrap popupWrap">
		<div class="popup" id="expertInfo"></div>
	</section>

	<!-- 尾部 -->
	<%@ include file="common/footer.jsp"%>
</body>
</html>