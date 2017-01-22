<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.cxstock.biz.power.dto.UserDTO"%>
<%
  UserDTO userInfo=(UserDTO)session.getAttribute("userInfo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>浙江大学求是学院学生服务平台</title>
<link rel="icon" href="../../admin/img/logo.png" />
<link rel="stylesheet" href="../../admin/css/backstage.css" />
<link rel="stylesheet" href="../../admin/css/public.css" />
<script type="text/javascript" src="../../admin/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../../admin/js/backstage.js"></script>
<script type="text/javascript">
			function updatePwd(){
				if(''==$("#pwd1").val()){
					alert("请填写密码");
					return false;
				}
				if($("#pwd1").val()!=$("#pwd2").val()){
					alert("你输入的两次密码不相同,请重新输入");
					return false;
				}
				$.ajax({
					type : "post", 
					async : false,  
					url:"/MyStock/user_updatePwd.do",
					data:{password:$("#pwd1").val()},
					success:function(data,status){
						$("#pwd1").val("")
						$("#pwd2").val("");
						alert("密码修改成功");
						
					}
				});
			}
			
			function updateNickName(){
				if(''==$("#nickname").val()){
					alert("请填写昵称");
					return false;
				}
				$.ajax({
					type : "post", 
					async : false,  
					url:"/MyStock/user_updateNickName.do",
					data:{nickName:$("#nickname").val()},
					success:function(data,status){
						alert("昵称修改成功");
						
					}
				});
			}
			
			function updatePhone(){
				if(''==$("#phone").val()){
					alert("请填写手机号码");
					return false;
				}
				if(!(/^1[3|4|5|7|8]\d{9}$/.test($("#phone").val()))){ 
			        alert("手机号码有误,请重新输入");  
			        return false; 
			    } 
				$.ajax({
					type : "post", 
					async : false,  
					url:"/MyStock/user_updatePhone.do",
					data:{phone:$("#phone").val()},
					success:function(data,status){
						alert("手机号码修改成功");
						
					}
				});
			}
			
    
		
		</script>
</head>
<body>
	<section class="alterWrap">
		<div class="alterConW">
			<h3>修改密码</h3>
			<div class="alterFormWrap">
				<samp>
					新<i class="kg3"></i>密<i class="kg3"></i>码：
				</samp>
				<input type="password" class="alterInput" id="pwd1" />
			</div>
			<div class="alterFormWrap">
				<samp>确认密码：</samp>
				<input type="password" class="alterInput" id="pwd2" />
			</div>
			<div class="alterBtn">
				<button onclick="updatePwd()">确认修改</button>
			</div>
		</div>
		<div class="alterConW">
			<h3>修改昵称</h3>
			<div class="alterFormWrap">
				<samp>
					新<i class="kg3"></i>昵<i class="kg3"></i>称：
				</samp>
				<input type="text" id="nickname" value="<%=userInfo.getNickname()%>"
					class="alterInput" />
			</div>
			<div class="alterBtn">
				<button onclick="updateNickName()">确认修改</button>
			</div>
		</div>
		<div class="alterConW">
			<h3>修改手机号码</h3>
			<div class="alterFormWrap">
				<samp>手机号码：</samp>
				<input type="text" id="phone" value="<%=userInfo.getPhone()%>"
					class="alterInput" />
			</div>
			<div class="alterBtn">
				<button onclick="updatePhone()">确认修改</button>
			</div>
		</div>
	</section>
</body>
</html>
