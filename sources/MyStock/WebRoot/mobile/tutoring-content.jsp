<%@ page contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8"); //防止乱码问题
	String xmid = request.getParameter("xmid");
	String bmstatus = request.getParameter("bmstatus");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0">
<meta name="format-detection" content="telephone = no" />
<title>项目详情</title>
<link rel="icon" href="img/logo1.png" />
<link rel="stylesheet" href="css/public.css" />
<link rel="stylesheet" href="css/media.css" />
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/public.js"></script>
<script type="text/javascript">
			var xmid = "<%=xmid%>";
			var bmstatus = "<%=bmstatus%>";
			//alert(xmid);
			$(function(){
				getSingOfflineFd();
			});
			
			function getSingOfflineFd(){
				$.ajax({
					type : "post", 
					async : false,  
					url : "/MyStock/mobile_findSingOfflineFd.do",
				    data:{xmid:xmid},
					success:function(data,status){
						data = eval("("+data+")");
						render(data.data);
					}
				});
			}
			
			var obj;
			function render(data){
				obj = data;
				var s = '';
				s +='<div class="tutoringCon_tit">';
				s +='<h3 class="content_tit">'+data.fxxm+'</h3>';
				s +='</div>';
				s +='<table cellpadding="0" cellspacing="0" class="table_form">';
				s +='<tr>';
				s +='<td class="td1">辅学时间：</td>';
				s +='<td colspan="3">'+data.fxtime+'</td>';
				s +='</tr>';
				s +='<tr>';
				s +='<td class="td1">辅学地点：</td>';
				s +='<td colspan="3">'+data.fxaddress+'</td>';
				s +='</tr>';
				s +='<tr>';
				s +='<td class="td1">辅学老师：</td>';
				s +='<td colspan="3">'+data.fxteacher+'</td>';
				s +='</tr>';
				s +='<tr>';
				s +='<td class="td1">项目容量：</td>';
				s +='<td>'+data.xmzise+'</td>';
				s +='<td class="td3">已报人数：</td>';
				s +='<td>'+data.bmnumber+'</td>';
				s +='</tr>';
				s +='<tr>';
				s +='<td class="td1">评<i class="kg2"></i>论：</td>';
				s +='<td colspan="3">'+data.plnumber+'</td>';
				s +='</tr>';
				s +='<tr>';
				s +='<td class="td1 td2">项目简介：</td>';
				s +='<td colspan="3">'+data.xmintro+'</td>';
				s +='</tr>';
				s +='<tr>';
				s +='<td class="td1">项目状态：</td>';
				s +='<td colspan="3">'+data.bmstatus+'</td>';
				s +='</tr>';
				s +='</table>';
				if(bmstatus!="已报名" && data.bmstatus=="可报名"){
					s +='<div class="content_BtnWrap">';
					s +='<button class="content_Btn" onclick="showEnter()">我要报名</button>';
					s +='</div>';
				}
				$("#fdDetail").html(s);
			}
			function showEnter(){
				$(".popup").show();
			}
			//报名
			function submitFd(){
				$.ajax({
					type : "post", 
					async : false,  
					url : "/MyStock/mobile_saveOrUpdate.do",
				    data:{ifApproval:0,bmnumber:obj.bmnumber,xmid:xmid,fxxm:obj.fxxm,fxtime:obj.fxtime,
				    		fxaddress:obj.fxaddress,fxteacher:obj.fxteacher,xmzise:obj.xmzise,creatername:obj.creatername,
				    		plnumber:obj.plnumber,xmxh:obj.xmxh,xmintro:obj.xmintro},
					success:function(data,status){
						data = eval("("+data+")");
						if(data.message){
							$("#aletInfo").html("您已成功报名参加"+obj.fxxm+"讲座,请于"+obj.fxtime+"至"+obj.fxaddress+"参加活动！");
						}
					}
				});
			}
			
			function returnFdList(){
				window.location.href="tutoring.html";
			}
		</script>
</head>
<body>
	<section class="wrap2" id="fdDetail"></section>

	<!-- 确认弹窗 -->
	<section class="popup popup_tutoring">
		<div class="popup_wrap popup-Confirm">
			<div class="popup_con">
				<p>您确认报名参加此次讲座吗？</p>
			</div>
			<div class="popup_btn">
				<button class="popupBtn_cancel">取消</button>
				<button class="popupBtn_agree" onclick="submitFd()">确定报名</button>
			</div>
		</div>
		<div class="popup_wrap popup-agree">
			<div class="popup_con content_ok">
				<img src="img/icon_ok1.png">
				<p id="aletInfo"></p>
			</div>
			<div class="popup_btn">
				<button class="popupBtn_ok" onclick="returnFdList()">好</button>
			</div>
		</div>
	</section>
	<!-- 收藏弹窗  -->
	<section class="popup1 popup_collect">
		<div class="popup_wrap">
			<div class="popup_con">
				<img src="img/icon_ok1.png">
				<p></p>
			</div>
		</div>
	</section>
</body>
</html>
