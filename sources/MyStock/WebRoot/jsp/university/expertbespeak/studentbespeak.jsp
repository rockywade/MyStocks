<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>专家预约-浙江大学求是学院学生服务平台</title>
<link rel="icon" href="../../../admin/img/logo.png" />
<link rel="stylesheet" href="../../../admin/css/public.css" />
<link rel="stylesheet" href="../../../admin/css/backstage.css" />
<script type="text/javascript"
	src="../../../admin/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../../../admin/js/public.js"></script>
<script type="text/javascript" src="../../../admin/js/backstage.js"></script>
<link rel="stylesheet" href="../../../front/css/jqpagination.css" />
<script type="text/javascript"
	src="../../../front/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="../../../front/js/jquery.jqpagination.js"></script>
<!--兼容-->
<style type="text/css">
article, aside, dialog, footer, header, section, footer, nav, figure,
	menu {
	display: block;
}

.expertTime {
	display: inline-block;
	width: 135px;
	white-space: nowrap;
	text-overflow: ellipsis;
	text-align: right;
	overflow: hidden;
	padding-left: 10px;
}
</style>
<script>
	        (function() {
	            if (!
	                        /*@cc_on!@*/
	                            0) return;
	            var e = "abbr, article, aside, audio, canvas, datalist, details, dialog, eventsource, figure, footer, header, hgroup, mark, menu, meter, nav, output, progress, section, time, video".split(', ');
	            var i = e.length;
	            while (i--) {
	                document.createElement(e[i]);
	            }
	        })()
	    </script>
<!--兼容 end  -->
<script type="text/javascript">
	    	$(document).ready(function(){
	    		getDataAllAppoint();
	    		getDataMyAppoint();
	    		initpage1();
	    		initpage2();
	    		initSelect();
	    		unitSelect();
	    		
	    		// 专家图片的高
	    		var expertPhotoW = $(".expertPhoto").width();
				$(".expertPhoto").css({"height":expertPhotoW});
	    		var expertLeft = $(".expert_backL").height()+100;
	    		$(".expert_backR").css({"min-height":expertLeft});
				$(window).resize(function(){
	    			var expertLeft = $(".expert_backL").height()+100;
	    			$(".expert_backR").css({"min-height":expertLeft});
					var expertPhotoW = $(".expertPhoto").width();
					$(".expertPhoto").css({"height":expertPhotoW});
				})
			});
			//专家图片的大小
			function expertImg(){
				var expertPhotoW = $(".expertPhoto").width();
				$(".expertPhoto").css({"height":expertPhotoW});
				$(window).resize(function(){
					var expertPhotoW = $(".expertPhoto").width();
					$(".expertPhoto").css({"height":expertPhotoW});
				})
			}

			
			var startAllAppoint = 0;
	    	var limitAllAppoint = 8;
	    	var currentPageAllAppoint = 1;
	    	var totalPageAllAppoint = 0;
			function getDataAllAppoint(){
				$.ajax({
					type : "post", 
					async : false,  
					url:"/MyStock/Expertbespeak_expertBespeakList.do",
					data:{start:startAllAppoint, limit:limitAllAppoint,yx:$("#yx").val(),bm:$("#bm").val(),experttype:$("#experttype").val(),ename:$("#ename").val()},
					success : function(data,status){
						data = eval("("+data+")");
						renderAllAppoint(data.root);
						startAllAppoint = data.currentResult;
						totalPageAllAppoint = data.totalPage;
					}
				});
			}
			
			function initpage1(){
				$('#page1').jqPagination({
				  link_string : '',
				  current_page: currentPageAllAppoint, //设置当前页 默认为1
				  max_page : totalPageAllAppoint, //设置最大页 默认为1
				  page_string : '当前第{current_page}页,共{max_page}页',
				  paged : function(page) {
				  	  startAllAppoint = (page-1)*limitAllAppoint;
					  getDataAllAppoint();
					  expertImg();
				  }
				});
			}
			
	    	var startMyAppoint = 0;
	    	var limitMyAppoint = 7;
	    	var currentPageMyAppoint = 1;
	    	var totalPageMyAppoint = 0;
			function getDataMyAppoint(){
				$.ajax({
					type : "post", 
					url: "/MyStock/Expertbespeak_myBespeak.do",
					data :{start:startMyAppoint, limit:limitMyAppoint},
				 	async : false,  
					success : function(data,status){
						data = eval("("+data+")");
						renderMyAppoint(data.root);
						startMyAppoint = data.currentResult;
						totalPageMyAppoint = data.totalPage;
					}
				});
			}
			
			function initpage2(){
				$('#page2').jqPagination({
				  link_string : '',
				  current_page: currentPageMyAppoint, //设置当前页 默认为1
				  max_page : totalPageMyAppoint, //设置最大页 默认为1
				  page_string : '当前第{current_page}页,共{max_page}页',
				  paged : function(page) {
				  	  startMyAppoint = (page-1)*limitMyAppoint;
					  getDataMyAppoint();
				  }
				});
			}
			
			//我的预约
			function renderMyAppoint(data){
				var s = "";
				$.each(data,function(rowNum,o){
						s+='<li onclick=showMySbInfoDetail('+o.stid+',"'+o.bespeakstate+'")>';
						s+='<h4><i>'+(rowNum+1)+'</i>';
						s+='<samp class="expertTime">'+o.bespeaktime+'</samp>';
						s+='</h4>';
						s+='<div class="myExpert_info">';
						s+='<p>'+o.expertName+'</p>';
						s+='<p>'+o.detailinfo+'</p>';
						//s+='<p>'+o.bespeaktime+'</p>';
						s+='</div></li>';
				});
				if(data.length==0){
					$("#myExpert").html("<br/>暂无数据");
				}else{
					$("#myExpert").html(s);
				}
			}

			//我的预约详情
			function showMySbInfoDetail(id,bespeakstate){
				//alert(bespeakstate);
				var d = null;
				  $.ajax({
				         type : "POST",
		   			      url : "/MyStock/Expertbespeak_mySbDetail.do",
		   			      data:{
						      sbid:id
					        },
		   			      async: false,
		   			      success : function(o) {
						      d = eval("("+o+")");
		   			      }
		   		   		});
				$("#expertInfo").html(mySbDetail(d.data,bespeakstate,id));
				showMySbInfo();
			}

			function mySbDetail(d,bespeakstate,id){
				var s = '';
				s+='<div class="popupCloseBtn" onclick="popupClose();">';
				s+='<img src="../../../admin/img/icon_close.png" />';
				s+='</div>';
				s+='<div class="expert_info">';
				s+='<div class="expertInfo_img">';
				if(d.expertPhoto!=null && d.expertPhoto!=""){
					s+='<img height="153px" width="136px" src="'+d.expertPhoto+'"/>';
				}else{
					s+='<img src="../../../img/defaultpic/photo.jpg"/>';
				}
				s+='</div>';
				s+='<div class="expertInfo_txt ">';
				s+='<table>';
				s+='<tr>';
				s+='<td>预约方式</td>';
				s+='<td>'+d.bespeakstyle+'</td>';
				s+='</tr>';
				s+='<tr>';
				s+='<td>预约类别</td>';
				s+='<td>'+d.applygenre+'</td>';
				s+='<td>预约状态</td>';
				s+='<td>'+d.bespeakstate+'</td>';
				s+='</tr>';
				s+='<tr>';
				s+='<td>预约时间</td>';
				s+='<td>'+d.bespeaktime+'</td>';
				s+='<td>预约地点</td>';
				s+='<td>'+d.bespeakplace+'</td>';
				s+='</tr>';
				s+='<tr>';
				s+='<td>预约详情</td>';
				s+='<td colspan="3">'+d.detailinfo+'</td>';
				s+='</tr>';
				if(d.expertName!=null && d.expertName!=""){
					s+='<tr>';
					s+='<td>预约专家</td>';
					s+='<td>'+d.expertName+'</td>';
					s+='<td>专家手机</td>';
					s+='<td>'+d.expertPhone+'</td>';
					s+='</tr>';
				}
				if(d.expertName==null || d.expertName==""){
					s+='<tr>';
					s+='<td colspan="3" style="color:red">您没有指定的专家和专家信息......</td>';
					s+='</tr>';
				}
				s+='</table>';
				s+='</div>';
				s+='</div>';
				if('待接受'==bespeakstate){
					s+='<div class="expert_btn popup_btn">';
					s+='<button class="reserve_cannel" onclick="showCencalInfo('+id+')">取消预约</button>';
					s+='</div>';
				}
				return s;
			}
			
			
			function renderAllAppoint(data){
				var s = "";
				$.each(data,function(rowNum,obj){
						s+='<li style="cursor:pointer;" onclick=showAppoint('+obj.id+')><div class="showAppointW">';
						if(obj.expertPhoto!=null && obj.expertPhoto!=""){
							s+='<div class="expertPhoto"><img src="'+obj.expertPhoto+'" /></div>';
						}else{
							s+='<div class="expertPhoto"><img src="../../../img/defaultpic/photo.jpg"/></div>';
						}
						s+='<p>'+(rowNum+1)+'</p>';
						s+='<p>'+obj.expertName+'</p>';
						s+='</div></li>';
				});
				if(data.length==0){
					$("#allExpert").html("<br/>暂无数据");
				}else{
					$("#allExpert").html(s);
				}
			}
			
			function showAppoint(eid){
				var d = null;
		        $.ajax({
		         type : "POST",
   			      url : "/MyStock/Expertbespeak_expertInfo.do",
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
				s+='<img src="../../../admin/img/icon_close.png" />';
				s+='</div>';
				s+='<div class="expert_info">';
				s+='<div class="expertInfo_img">';
				if(d.expertPhoto!=null && d.expertPhoto!=""){
					s+='<img src="'+d.expertPhoto+'"/>';
				}else{
					s+='<img src="../../../img/defaultpic/photo.jpg"/>';
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
				if(d.expertEmail){
					s+='<td>'+d.expertEmail+'</td>';
				}else{
					s+='<td style="color:red">暂无邮箱信息</td>';
				}
				s+='</tr>';
				s+='<tr>';
				s+='<td>联系电话</td>';
				s+='<td>'+d.expertphone+'</td>';
				s+='</tr>';
				s+='<tr>';
				s+='<td>空余时间</td>';
				s+='<td colspan="3">'+d.freetime+'</td>';
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
				s+='<a href="javascript:;" onclick=randomBespeak('+d.id+')>我要预约</a>';
				s+='</div>';
				return s;
			}
			
			function search(){
				getDataAllAppoint();
				expertImg();
			}
			
			function appoint(){
				//window.location.href="front_goToLogin.do?url=../onlineqa/onlineqa.jsp";
			}
			//园区下拉菜单
			function initSelect(){
				$.post("/MyStock/SiteInfo_findParkComb.do",{},
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
			//部门下拉菜单
			function unitSelect(){
				$.post("/MyStock/Expertbespeak_findUnitComb.do",{},
						function(data,status){
							data = eval("("+data+")");
							initUnit(data.root);
						});
			}
			function initUnit(data){
				$.each(data,function(rowNum,o){
					$("#bm").append('<option value=\''+o.value+'\'>'+o.text+'</option>');
				});
			}
			
			//预约
			function randomBespeak(id){
				//查询学生信息
				var d = null;
		        $.ajax({
		         type : "POST",
   			      url : "/MyStock/Expertbespeak_getCurentUserInfo.do",
   			      data:{},
   			      async: false,
   			      success : function(o) {
				      d = eval("("+o+")");
   			      }
   		   		});
				$("#inputInfo").html(renderRandom(d.data,id));
				hideSbInfo();
				showInput();
			}
			function renderRandom(data,id){
				//alert(id);
				var s = '';
				s+='<table>';
				s+='<tr>';
				s+='<td>姓名</td>';
				s+='<td>'+data.xm+'</td>';
				s+='<td>学号</td>';
				s+='<td>'+data.xh+'</td>';
				s+='</tr>';
				s+='<tr>';
				s+='<td>班级</td>';
				s+='<td>'+data.dalei+'</td>';
				s+='<td>性别</td>';
				s+='<td>'+data.xb+'</td>';
				s+='</tr>';
				s+='<tr>';
				s+='<td>手机号</td>';
				s+='<td>'+data.phone+'</td>';
				s+='<td>所在园区</td>';
				s+='<td>'+data.ssyq+'</td>';
				s+='</tr>';
				s+='<tr>';
				s+='<td>预约类别</td>';
				s+='<td>';
				s+='<select id="applygenre" name="applygenre">';
				//s+='<option value="">--请选择预约类别--</option>';
				s+='<option selected="selected" value="学业指导">学业指导</option>';
				s+='<option value="心理辅导">心理辅导</option>';
				s+='<option value="职业规划">职业规划</option>';
				s+='<option value="出国指导">出国指导</option>';
				s+='<option value="其他">其他</option>';
				s+='</select>';
				s+='</td>';
				s+='<td>邮箱</td>';
				s+='<td><input id="email" type="text" onblur="emailBlur();" /></td>';
				s+='</tr>';
				s+='<tr>';
				s+='<td>预约时间</td>';
				s+='<td><input id="bespeaktime" onblur="bespeaktimeBlur();" type="text" name="bespeaktime"/></td>';
				s+='<td>预约地点</td>';  
				s+='<td><select id="bespeakplace" name="applygenre">';
				//s+='<option value="">—— 请选择校区 ——</option>';
				s+='<option selected="selected" value="西溪校区">西溪校区</option>';
				s+='<option value="玉泉校区">玉泉校区</option>';
				s+='<option value="华家池校区">华家池校区</option>';
				s+='<option value="之江校区">之江校区</option>';
				s+='<option value="紫金港校区">紫金港校区</option>';
				s+='</select>';
				s+='</td>';
				s+='</tr>';
				s+='<tr>';
				s+='<td class="td1">详细信息</td>';
				s+='<td colspan="3"><textarea id="detailinfo" onblur="detailinfoBlur();" name="detailinfo" rows="4" style="resize: 0;"></textarea></td>';
				s+='</tr>';
				s+='<tr>';
				s+='<td><input id="eid" type="hidden" name="id" value="'+id+'"/></td>';
				s+='</tr>';
				s+='</table>';
				return s;
			}
			
			//专家预约验证
			function emailBlur(){
			    var email = $("#email").val();
			    var reg = /\w+[@]{1}\w+[.]\w+/;
			    
			    if(email == ""){//为空验证
			    	alert("请输入email地址！");
			    }else if(reg.test(email)){//正确
			     
			    }else{//不正确
			    	alert("请输入正确的email地址！");
			    }
			    
			}
			// 预约时间验证
			function bespeaktimeBlur() {
			 	var bespeaktime = $("#bespeaktime").val();
			 	if(bespeaktime == ""){
			 		alert("请输入预约时间！")
			 	}
			}
			//详细信息验证
			function detailinfoBlur(){
			 	var detailinfo = $("#detailinfo").val();
			 	if(detailinfo == ""){
			 		alert("请输入详细信息！")
			 	}
			}
			
			
			//提交预约
			function submit(){
				//showEnterInfo();
				$.post("/MyStock/Expertbespeak_bespeakExpert.do",{id:$("#eid").val(),applygenre:$("#applygenre").val(),studentemail:$("#email").val(),bespeaktime:$("#bespeaktime").val(),bespeakplace:$("#bespeakplace").val(),detailinfo:$("#detailinfo").val()},
					function(data,status){
						hideEnterInfo();
						hideInput();
						showUploadSuccess();
						setTimeout(function(){
							location.href="studentbespeak.jsp";
							},2000);
					}
				);
			}
			//专家预约详情
			function showSbInfo(){
				$(".reserve_popupWrap").show().animate({opacity:'1'},300);
			}
			//专家预约详情隐藏
			function hideSbInfo(){
				$(".reserve_popupWrap").hide().animate({opacity:'1'},280);
			}
			//随机预约信息填写框展示
			function showInput(){
				$(".expertR_popup").show().animate({opacity:'1'},300);
			}
			//随机预约信息填写框隐藏
			function hideInput(){
				$(".expertR_popup").hide().animate({opacity:'1'},300);
			}
			//我的预约详情展示
			function showMySbInfo(){
				$(".reserve_popupWrap").show().animate({opacity:'1'},300);
			}
			//我的预约详情隐藏
			function hideMySbInfo(){
				$(".reserve_popupWrap").hide().animate({opacity:'1'},300);
			}
			//预约提交弹框提示展示
			function showEnterInfo(){
				 //专家预约验证
				 var email = $("#email").val();
				 var bespeaktime = $("#bespeaktime").val();
				 var detailinfo = $("#detailinfo").val();
				 var reg = /\w+[@]{1}\w+[.]\w+/;
				    
				 if(email == ""){//为空验证
				    alert("请输入email地址！");
				 }else if(!reg.test(email)){//不正确
				    alert("请输入正确的email地址！");
				 }else if(bespeaktime == ""){// 预约时间验证
				 	alert("请输入预约时间！")
				 }else if(detailinfo == ""){//详细信息验证
				 	alert("请输入详细信息！")
				 }else{
					$(".affirmPopup").show().animate({opacity:'1'},300);
				}
			}
			
			//预约提交弹框提示隐藏
			function hideEnterInfo(){
				$(".affirmPopup").hide().animate({opacity:'1'},200);
			}
			//提交成功弹框提示展示
			function showUploadSuccess(){
				$(".affirmPopupOK").show().animate({opacity:'1'},300);
				$(".affirmPopupOK").fadeOut(3000);
			}
			//提交成功弹框提示隐藏
			function hideUploadSuccess(){
				$(".affirmPopupOK").hide().animate({opacity:'1'},3000);
			}

			//取消确认弹框提示展示
			var stid;
			function showCencalInfo(id){
				stid = id;
				$(".affirmPopup1").show().animate({opacity:'1'},300);
			}
			
			//取消确认弹框提示隐藏
			function hideCencalInfo(){
				$(".affirmPopup1").hide().animate({opacity:'1'},200);
			}

			//取消预约
			function cancel(){
				hideCencalInfo();
				hideMySbInfo();
				$.post("/MyStock/Expertbespeak_cancel.do",{sbid:stid},
						function(data,status){
							getDataMyAppoint();
						}
					);
			}
	    </script>
</head>
<body>
	<!-- 专家预约 -->
	<div class="w2">
		<section class="expert_backL">
			<div class="expertScreen_w">
				<div class="expertScreen_l expertScreen_l_B">
					<ul>
						<li>
							<p>
								园<i></i>区
							</p> <select id="yx" name="yx">
								<option value=''>全部</option>
						</select>
						</li>
						<li>
							<p>
								部<i></i>门
							</p> <select id="bm" name="bm">
								<option value=''>全部</option>
						</select>
						</li>
						<li>
							<p>专家类型</p> <select id="experttype" name="expertType">
								<option value=''>全部</option>
								<option value='学业指导'>学业指导</option>
								<option value='心理辅导'>心理辅导</option>
								<option value='职业规划'>职业规划</option>
								<option value='出国指导'>出国指导</option>
								<option value='其他'>其他</option>
						</select>
						</li>
						<li>
							<p>
								姓<i></i>名
							</p> <input id="ename" type="text" />
						</li>
					</ul>
				</div>
				<div class="expertScreen_r expertScreen_r_B">
					<input type="button" value="查询" onclick="search()" /> <input
						type="button" value="随机专家预约" onclick="randomBespeak(0)" />
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

		<section class="expert_backR">
			<h3>我的预约</h3>
			<ul class="myExpert" id="myExpert">
			</ul>
			<div class="pagination" id="page2">
				<a href="#" class="first" data-action="first">&laquo;</a> <a
					href="#" class="previous" data-action="previous">&lsaquo;</a> <input
					type="text" readonly="readonly" data-max-page="40" /> <a href="#"
					class="next" data-action="next">&rsaquo;</a> <a href="#"
					class="last" data-action="last">&raquo;</a>
			</div>
		</section>

	</div>

	<!-- 专家预约 -->
	<section class="expertR_popup popupWrap">
		<div class="popup">
			<div class="popupCloseBtn" onclick="popupClose();">
				<img src="../../../admin/img/icon_close.png" />
			</div>
			<div class="expert_info" id="inputInfo"></div>
			<div class="expert_btn popup_btn">
				<button class="expertBtn_sub" onclick="showEnterInfo();">提交申请</button>
			</div>
		</div>
	</section>

	<!-- 预约	确认 -->
	<section class="affirmPopup">
		<div class="affirmPopup_c">
			<p>确认信息无误提交申请？</p>
		</div>
		<div class="affirmPopup_b">
			<button class="affirm_cancel" onclick="hideEnterInfo();">取消</button>
			<button class="affirm_ok" onclick="submit();">确认</button>
		</div>
	</section>

	<section class="affirmPopupOK">
		<div class="affirmPopupOK_p">
			<p>
				预约申请提交成功
			</p>
		</div>
	</section>

	<!-- 取消		确认 -->
	<section class="affirmPopup1">
		<div class="affirmPopup_c1">
			<p>确定取消预约？</p>
		</div>
		<div class="affirmPopup_b1">
			<button class="affirm_cancel" onclick="hideCencalInfo();">取消</button>
			<button class="affirm_ok" onclick="cancel();">确认</button>
		</div>
	</section>

	<section class="affirmPopupOK1">
		<div class="affirmPopupOK_p1">
			<p>预约已取消成功</p>
		</div>
	</section>

	<!-- 弹框 -->
	<section class="reserve_popupWrap popupWrap">
		<div class="popup" id="expertInfo"></div>
	</section>
</body>
</html>