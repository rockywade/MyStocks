<%@ page contentType="text/html; charset=UTF-8"%>
<%
	session.removeAttribute("userInfo");
%>
<html>
<head>
<title>求是学院学生服务平台</title>
<link rel="icon" href="img/favicon.png" />
<script type="text/javascript">
		if (self != top) {
    		window.top.location.href = "login.jsp";
		}
		
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
<style type="text/css">
* {
	margin: 0 auto;
	padding: 0;
	border: 0;
	font-size: 12px;
	font-style: normal;
	font-weight: normal;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

body {
	font: 12px "Microsoft yahei";
	color: #333;
}

img {
	border: none;
}

input {
	outline: none;
}

input:-webkit-autofill {
	-webkit-box-shadow: 0 0 0px 1000px white inset;
}

.loginBg {
	width: 100%;
	height: 60%;
	position: fixed;
	top: 40%;
	background: url(img/loginBg.jpg) repeat-y center;
	z-index: -1;
}

.login {
	width: 500px;
	height: 480px;
	margin: 0 auto;
	position: absolute;
	top: 40%;
	left: 50%;
	margin-top: -212px;
	margin-left: -250px;
}

.top {
	width: 500px;
	height: 137px;
}

.logo {
	background: url(img/logoLogin.png) no-repeat;
	background-size: 100% 100%;
	width: 95px;
	height: 95px;
	float: left;
}

.lable {
	background: url(img/loginLogo.png) no-repeat;
	background-size: 100% 100%;
	width: 364px;
	height: 65px;
	float: right;
	margin-top: 15px;
}

.loginTit {
	font-size: 25px;
	line-height: 75px;
	text-align: center;
	background-color: #1A60AC;
	color: #fafafa;
}

.loginWrap {
	height: 267px;
	margin: 0 auto;
	background-color: rgba(255, 255, 255, 0.3);
	background-color: #5f8cbe\9;
	padding: 10px 0 35px
}

.loginWrap tr td {
	padding: 5px 0;
	font-size: 18px;
	line-height: 38px;
	color: #e2eef7;
	letter-spacing: 1px;
}

.loginWrap tr .input {
	width: 250px;
	height: 38px;
	line-height: 38px;
	font-size: 16px;
	padding: 0 10px;
	margin-left: 22px;
	border-radius: 5px;
	background-color: rgba(255, 255, 255, 0.9) !important;
}

.submit {
	border-radius: 5px;
	background-color: #1A60AC;
	font-size: 20px;
	color: #fafafa;
	cursor: hand;
	width: 310px;
	height: 45px;
	line-height: 45px;
	border: 0;
	margin-top: 20px;
}

#error {
	font-size: 14px;
	line-height: 10px;
	color: #f00;
}
</style>
</head>

<body>
	<div>
		<div class="loginBg"></div>
		<table width="100%" height="100%" class="main" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align="center">
					<div class="login">
						<div class="top">
							<div class="logo"></div>
							<div class="lable"></div>
						</div>
						<h2 class="loginTit">用户登录</h2>
						<table width="500" cellpadding="0" cellspacing="0"
							class="loginWrap">
							<form action="user_login.do" method="post">
								<tr>
									<td align="center" colspan="2"><p id="error" color="red">${error}&nbsp;</p></td>
								</tr>
								<tr>
									<td align="right" width="133">账号</td>
									<td align="left" width="367"><input type="text"
										name="logincode" class="input" placeholder="请输入账号" /></td>
								</tr>
								<tr>
									<td align="right" width="133">密码</td>
									<td align="left" width="367"><input type="password"
										name="password" class="input" placeholder="请输入密码" /></td>
								</tr>
								<tr>
									<td align="center" height="45" colspan="2"><input
										name="submit" type="submit" value="登录"
										onclick="return check()" class="submit" /></td>
								</tr>
							</form>
						</table>
					</div> <!--login -->
				</td>
			</tr>
		</table>
</body>
</html>