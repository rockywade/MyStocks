<%@ page contentType="text/html; charset=UTF-8"%>
<%
	session.removeAttribute("userInfo");
	//session.removeAttribute("error");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0">
<meta name="format-detection" content="telephone = no" />
<title>登录</title>
<link rel="icon" href="img/logo1.png" />
<link rel="stylesheet" href="css/public.css" />
<link rel="stylesheet" href="css/media.css" />
<script type="text/javascript">
			function check(){
			var logincode = document.getElementById("logincode").value;
			var password = document.getElementById("password").value;
			var error = document.getElementById("error");
			error.innerHTML="";
			if(logincode==""){
				error.innerHTML="用户名不能为空！";
				return false;
			}
			if(password==""){
				error.innerHTML="密码不能为空！";
				return false;
			}
			return true;
		}
		</script>
</head>
<body>
	<!--
		${sessionScope.error}
		-->
	<section class="login_wrap">
		<img src="img/bg-login.jpg" class="login_bg" />
		<div class="login_div">
			<div class="login_logo">
				<img src="img/logo.png" />
			</div>
			<form class="login_from" action="mobile_login.do">
				<input id="logincode" type="text" class="login_name"
					name="logincode" placeholder="账号" /> <input id="password"
					type="password" class="login_pwd" name="password" placeholder="密码" />
				<div id="error" align="center" style="color: red">&nbsp;${error}&nbsp;</div>
				<input type="submit" value="登录" class="login_btn"
					onclick="return check()" />
			</form>
		</div>
	</section>
</body>
</html>
