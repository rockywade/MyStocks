<%@ page contentType="text/html; charset=UTF-8"%>
<%

   request.setCharacterEncoding("UTF-8"); //防止乱码问题
   String activityid = request.getParameter("activityid");
     
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0">
<meta name="format-detection" content="telephone = no" />
<title>活动详情</title>
<link rel="icon" href="img/logo1.png" />
<link rel="stylesheet" href="css/public.css" />
<link rel="stylesheet" href="css/media.css" />
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/public.js"></script>
<script type="text/javascript">
			var activityid = "<%=activityid%>";
			$(document).ready(function(){
				getActivityDetail(activityid);
			}); 
			
			function getActivityDetail(id){
				//alert(id);
				$.ajax({
					type : "post", 
					async : false,  
					url:"/MyStock/mobile_activityDetail.do",
					data:{aid:id},
					success : function(data,status){
						data = eval("("+data+")");
						renderActivityInfo(data.data);
					}
				});
			}
			
			function renderActivityInfo(data){
				var s = "";
			    s+='<h3 class="content_tit" id="activityname">'+data.activityname+'</h3>';
			    s+='<table cellpadding="0" cellspacing="0" class="table_form">';
			    s+='<tr>';
			    s+='<td class="td1">所在学期：</td>';
			    s+='<td id="inschoolterm">'+data.inschoolterm+'</td>';
				s+='<td class="td1">类<i class="kg3"></i>别：</td>';
				s+='<td id="activitygenre">'+data.activitygenre+'</td>';
				s+='</tr>';
			    s+='<tr>';
			    s+='<td class="td1">面向对象：</td>';
			    s+='<td>'+data.faceobj+'</td>';
			    s+='<td class="td1">人数/容量：</td>';
			    s+='<td>'+data.attendnum+'/'+data.capacity+'</td>';
			    s+='</tr>';
			    s+='<tr>';
			    s+='<td class="td1">活动地点：</td>';
			    s+='<td id="activityplace">'+data.activityplace+'</td>';
			    s+='<td class="td1">时<i class="kg3"></i>长：</td>';
			    s+='<td>'+data.timeofduration+'</td>';
			    s+='</tr>';
			    s+='<tr>';
			    s+='<td class="td1">活动时间：</td>';
			    s+='<td colspan="3" id="activitytime">'+data.activitytime+'</td>';
			    s+='</tr>';
			    s+='<tr>';
			    s+='<td class="td1">报名截止：</td>';
			    s+='<td colspan="3">'+data.signupendtime+'</td>';
			    s+='</tr>';
			    s+='<tr>';
			    s+='<td class="td1">活动考评分：</td>';
			    s+='<td colspan="3" id="score">'+data.score+'</td>';
			    s+='</tr>';
			    s+='<tr>';
			    s+='<td class="td1 td2">活动内容：</td>';
			    s+='<td colspan="3">'+data.activitycontent+'</td>';
			    s+='</tr>';
			    s+='</table>';
			    s+='<div class="content_BtnWrap">';
			    s+='<button class="content_Btn" onclick="showEnter()">我要报名</button>';
			    s+='</div>';
				$("#activityInfo").html(s);
			}
			
			function showEnter(){
				$(".popup").show().animate({opacity:'1'},300);
			}
			
			function hideSbInfo(){
				$(".popup").hide().animate({opacity:'1'},280);
			}
			
			function submitSigUp(){
				var activityname = $("#activityname").text();
				var activitygenre = $("#activitygenre").text();
				var activitytime = $("#activitytime").text();
				var inschoolterm = $("#inschoolterm").text();
				var activityplace =$("#activityplace").text();
				var score =$("#score").text();
				
				$.ajax({
					type : "post",
					async : false,  
					url:"/MyStock/mobile_attendActivity.do",
					data:{activityid:activityid, activityname:activityname, activitygenre:activitygenre,
						 activitytime:activitytime, inschoolterm:inschoolterm,
						 activityplace:activityplace, score:score},
					success : function(data,status){
						data = eval("("+data+")");
						if(data.message=="报名成功!"){
							var str = '您经报名参加的"'+activityname+'"已通过,请于'+activitytime+'至'+activityplace+'参加活动！';
							$("#alertInfo").html(str);
							//window.location.href="activity-signUp.html"; 
						}else if(data.message=="你已在参与中..."){
							var str = '不能重复报名！';
							$("#alertInfo").html(str);
							//window.location.href="activity-signUp.html"; 
						}
					}
				});
			}
			
			function returnSigUp(){
				window.location.href="activity-signUp.html";
			}
		</script>
</head>
<body>
	<section class="wrap2" id="activityInfo"></section>

	<!-- 确认弹窗 -->
	<section class="popup">
		<div class="popup_wrap popup-Confirm">
			<div class="popup_con">
				<p>请按照活动信息参加活动，报名未参加将扣去1分记实考评！</p>
			</div>
			<div class="popup_btn">
				<button class="popupBtn_cancel">取消</button>
				<button class="popupBtn_agree" onclick="submitSigUp()">确定报名</button>
			</div>
		</div>
		<div class="popup_wrap popup-agree">
			<div class="popup_con content_ok">
				<img src="img/icon_ok1.png">
				<p id="alertInfo"></p>
			</div>
			<div class="popup_btn">
				<button class="popupBtn_ok" onclick="returnSigUp()">好</button>
			</div>
		</div>
	</section>
</body>
</html>
