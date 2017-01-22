<%@ page contentType="text/html; charset=UTF-8"%>
<%
   request.setCharacterEncoding("UTF-8"); //防止乱码问题
   String expertId = request.getParameter("expertId");
   String applygenre = request.getParameter("applygenre");
   String expertName = request.getParameter("expertName");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0">
<meta name="format-detection" content="telephone = no" />
<title>提交申请</title>
<link rel="icon" href="img/logo1.png" />
<link rel="stylesheet" href="css/pullToRefresh.css" />
<link rel="stylesheet" href="css/public.css" />
<link rel="stylesheet" href="css/media.css" />
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/public.js"></script>
<script type="text/javascript">
			var expertId = "<%=expertId%>";
			var applygenre = "<%=applygenre%>";
			var expertName = "<%=expertName%>";
			function submitOrder(){
				//alert(expertId);
				//alert(applygenre);
				$.ajax({
					type : "post",
					async : false,  
					url:"/MyStock/mobile_bespeakExpert.do",
					data:{id:expertId, applygenre:applygenre,studentemail:$("#studentemail").val(),
							bespeaktime:$("#bespeaktime").val(),bespeakplace:$("#bespeakplace").val(),
							detailinfo:$("#detailinfo").val()},
					success : function(data,status){
						data = eval("("+data+")");
						if(data.message=="重新登录"){
							$("#okInfo").html('重新登录');
						}else{
							$("#okInfo").html('您的预约申请已提交成功请耐心等待专家老师确认');
						}
					}
				});
			}
			function showEnter(){
				$("#expertNameInfo").html('您确定预约"'+expertName+'"老师吗？');
				$(".popup").show().animate({opacity:'1'},300);
			}
			 
			function returnFirst(){
				//alert($("#okInfo").text());
				if($("#okInfo").text()=="重新登录"){
					window.location.href="login.html";
				}else{
					window.location.href="expert.html"; 
				}
			}

			//非空验证
			function checkEmperty(){
				if($("#bespeaktime").val()!=null && $("#bespeaktime").val()!=""){
					if($("#bespeakplace").val()!=null && $("#bespeakplace").val()!=""){
						if($("#detailinfo").val()!=null && $("#detailinfo").val()!=""){
							showEnter();
						}else{
							alert("请简述你的预约内容");
						}
					}else{
						alert("预约地点不能为空!");
					}
				}else{
					alert("请填写预约时间!");
				}
			}

			//邮箱格式验证
			function emailCheck(){
				 var email = $("#studentemail").val();
				    var reg = /\w+[@]{1}\w+[.]\w+/;
				 	if(email!=null && email!="" && !reg.test(email)){
				 		alert("请输入正确的email地址！");
				 		$("#studentemail").val("");
				    }
			}
		</script>
</head>
<body class="place">
	<section class="wrap2">
		<form>
			<ul class="form_list applyForm_list">
				<li>
					<p>
						邮<i class="kg2"></i>箱
					</p>
					<input type="text" class="formTxt" id="studentemail"
					onblur="emailCheck()" />
				</li>
				<li>
					<p>预约时间</p>
					<input type="text" class="formTxt" placeholder="请输入您要预约的时间"
					id="bespeaktime" />
				</li>
				<li>
					<p>预约地点</p>
					<input type="text" class="formTxt" placeholder="请输入您要预约的地点"
					id="bespeakplace" />
				</li>
				<li>
					<p>预约内容</p>
					<textarea class="formTxt" rows="5" style="resize: none;"
						id="detailinfo"></textarea>
				</li>

			</ul>
		</form>
	</section>

	<div class="formBtn expert_appayWrap">
		<input type="button" value="提交预约申请" class="expert_appaySub"
			onclick="checkEmperty()" />
	</div>

	<!-- 确认弹窗 -->
	<section class="popup popup_expert">
		<div class="popup_wrap popup-Confirm">
			<div class="popup_con">
				<p id="expertNameInfo"></p>
			</div>
			<div class="popup_btn">
				<button class="popupBtn_cancel">取消</button>
				<button class="popupBtn_agree" onclick="submitOrder()">确定</button>
			</div>
		</div>
		<div class="popup_wrap popup-agree">
			<div class="popup_con content_ok">
				<img src="img/icon_ok1.png">
				<p id="okInfo"></p>
			</div>
			<div class="popup_btn">
				<button class="popupBtn_ok" onclick="returnFirst()">好</button>
			</div>
		</div>
	</section>

</body>
</html>
