<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>首页-浙江大学求是学院学生服务平台</title>
<link rel="icon" href="img/logo.png" />
<link rel="stylesheet" href="css/public.css" />
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="admin/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="admin/js/public.js"></script>
<script type="text/javascript" src="admin/js/backstage.js"></script>
<script type="text/javascript" src="front/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="front/js/jquery.jqpagination.js"></script>
<!--兼容-->
<style type="text/css">
article, aside, dialog, footer, header, section, footer, nav, figure,
	menu {
	display: block;
}

*{padding: 0;margin: 0;box-sizing: border-box;}
body{padding: 0;margin: 0;font: 12px "微软雅黑";}
li{list-style: none;}
a{text-decoration: none;}
/* 这里开始是导航样式 */
.iNav{
	width: 100%;
	min-width: 1200px;
	background-color: #1a60ac;
	/**border-bottom: 1px solid #114b8f;*/
	border-top: 1px solid #114b8f;
}
.iNavWrap{
	width: 1200px;
	margin: 0 auto;
}
.iNavWrap ul{
	overflow: hidden;
}
.iNavWrap ul li{
	float: left;
}

.iNavWrap ul li a{
	display: block;
	font-size: 16px;
	padding: 10px 30px 15px;/*修改padding可改变宽高*/
	color: #FFFFFF ;
	/*border-top: 5px solid #114b8f;*/
}
.iNavWrap ul li a:hover{/*鼠标移上的样式*/
	color: #000000 !important;
	background-color: #FFFFFF;
}

.iNavThis {
	background-color: #FFFFFF;
}
.iNavThis a{/*选中的样式*/
	color: #000000 !important;
}


/*弹出层的STYLE*/
html,body {height:100%; margin:0px; font-size:12px;}

.mydiv {
background-color: #ff6;
border: 1px solid #f90;
text-align: center;
line-height: 40px;
font-size: 12px;
font-weight: bold;
z-index:99;
width: 300px;
height: 120px;
left:50%;/*FF IE7*/
top: 50%;/*FF IE7*/

margin-left:-150px!important;/*FF IE7 该值为本身宽的一半 */
margin-top:-60px!important;/*FF IE7 该值为本身高的一半*/

margin-top:0px;

position:fixed!important;/*FF IE7*/
position:absolute;/*IE6*/

_top:       expression(eval(document.compatMode &&
            document.compatMode=='CSS1Compat') ?
            documentElement.scrollTop + (document.documentElement.clientHeight-this.offsetHeight)/2 :/*IE6*/
            document.body.scrollTop + (document.body.clientHeight - this.clientHeight)/2);/*IE5 IE5.5*/

}


.bg {
background-color: #ccc;
width: 100%;
height: 100%;
left:0;
top:0;/*FF IE7*/
filter:alpha(opacity=50);/*IE*/
opacity:0.5;/*FF*/
z-index:1;

position:fixed!important;/*FF IE7*/
position:absolute;/*IE6*/

_top:       expression(eval(document.compatMode &&
            document.compatMode=='CSS1Compat') ?
            documentElement.scrollTop + (document.documentElement.clientHeight-this.offsetHeight)/2 :/*IE6*/
            document.body.scrollTop + (document.body.clientHeight - this.clientHeight)/2);/*IE5 IE5.5*/

}
/*The END*/

</style>
<script>
function showDiv(){
	document.getElementById('popDiv').style.display='block';
	document.getElementById('bg').style.display='block';
	}

function closeDiv(){
	document.getElementById('popDiv').style.display='none';
	document.getElementById('bg').style.display='none';
}

			(function() {
				if (!
					/*@cc_on!@*/
					0) return;
				var e = "abbr, article, aside, audio, canvas, datalist, details, dialog, eventsource, figure, footer, header, hgroup, mark, menu, meter, nav, output, progress, section, time, video".split(', ');
				var i = e.length;
				while (i--) {
					document.createElement(e[i]);
				}
				
				//$.post("demo_test_post.asp",
				  //{
				    //name:"Donald Duck",
				    //city:"Duckburg"
				  //},
				  //function(data,status){
				    //alert("Data: " + data + "\nStatus: " + status);
				  //});
			})()
		</script>
<!--兼容 end  -->
<script type="text/javascript">
			if (self != top) {
    			window.top.location.href = "index.jsp";
			}
			$(document).ready(function(){
			
				getDataNewsList();
				//alert(1);
				showDiv();
			});
			//debugger;
			function getDataNewsList(){
				$.post("/MyStock/front_newsSpecialColumn.do",{start:0,limit:10},
						function(data,status){
							data = eval("("+data+")");
							insertNewsInfo(data.root);
						}
					);
			}
			
			function insertNewsInfo(data){
				var s = "";
				$.each(data,function(rowNum,obj){
					s+='<li><a href="front/content_news.jsp?newsid='+obj.newsid+'">';
					s+='<p>'+obj.newstitle+'</p>';
					s+='<samp>'+obj.newsdate+'</samp>';
					s+='</a></li>';
				});
				$("#newsInfo").html(s);
			}
		</script>
</head>
<body>
	<!-- 头部 -->
	<header id="header">
		<div class="header">
			<a class="figure" href="index.jsp"> <img src="img/logo.png" /> <img
				src="img/logo_name.png" />
			</a>
			<div class="indexLogin_w">
				<a href="/MyStock/login_goToLogin.do">登录</a>
			</div>
		</div>
	</header>
	
	<!-- 导航开始 -->
	<div class="iNav">
		<div class="iNavWrap">
			<ul>
				<li class="iNavThis"><a href="index.jsp">首页</a></li>
				<li><a href="front/news.jsp">新闻公告</a></li>
				<li><a href="front/tutoring.jsp">线下辅导</a></li>
				<li><a href="front/data.jsp">学习资料</a></li>
				<li><a href="front/answer.jsp">线上答疑</a></li>
				<li><a href="front/apply.jsp">活动报名</a></li>
				<li><a href="front/bookshop.jsp">二手书店</a></li>
				<li><a href="front/expertReserve.jsp">专家预约</a></li>
				<li><a href="front/placeReserve.jsp">场地预约</a></li>
			</ul>
		</div>
	</div>
	<!-- 导航结束 -->
	
	<section class="banner" style="margin: 0 0;">
		<img src="img/banner_img1.jpg" />
	</section>

	<section class="main">
		<!-- 内容 -->
		<article>
			<!--新闻公告-->
			<div class="wrap">
				<div class="title">
					<h3>新闻公告</h3>
					<div class="more_wrap">
						<a href="front/news.jsp">更多>></a>
					</div>
				</div>

				<div class="content_wrap">
					<ul class="news_list" id="newsInfo">
					</ul>
				</div>
			</div>
			<div class="wrap">
				<div class="title">
					<h3>学习资料</h3>
					<div class="more_wrap">
						<a href="front/data.jsp">进入>></a>
					</div>
				</div>
				<div class="content_wrap">
					<a href="front/data.jsp" class="content_wrap_a"><img
						src="img/dataImg1.jpg" /></a>
				</div>
			</div>
		</article>

		<!-- 右侧栏 -->
		<aside>
			<div class="wrap">
				<div class="title">
					<h3>线下辅导</h3>
					<div class="more_wrap">
						<a href="front/tutoring.jsp">进入>></a>
					</div>
				</div>
				<div class="content_wrap">
					<a href="front/tutoring.jsp" class="content_wrap_a"><img
						src="img/tutoringImg1.jpg" /></a>
				</div>
			</div>
			<div class="wrap">
				<div class="title">
					<h3>线上答疑</h3>
					<div class="more_wrap">
						<a href="front/answer.jsp">进入>></a>
					</div>
				</div>
				<div class="content_wrap">
					<a href="front/answer.jsp" class="content_wrap_a"><img
						src="img/answerImg1.jpg" /></a>
				</div>
			</div>
		</aside>

	</section>

	<!-- 服务大厅 -->
	<section class="service_hall">
		<div class="wrap">
			<div class="title">
				<h3>服务大厅</h3>
				<div class="more_wrap"></div>
			</div>
			<div class="service_list">
				<ul>
					<li><img class="service_img" src="img/serviceImg1.jpg" /> <a
						class="service_txt service_txt1" href="front/apply.jsp"><img
							class="service_img" src="img/icon_service1.png" />活动报名</a></li>
					<li><img class="service_img" src="img/serviceImg2.jpg" /> <a
						class="service_txt service_txt2" href="front/bookshop.jsp"><img
							class="service_img" src="img/icon_service2.png" />二手书店</a></li>
					<li><a class="service_txt service_txt3"
						href="front/expertReserve.jsp"><img class="service_img"
							src="img/icon_service3.png" />专家预约</a> <img class="service_img"
						src="img/serviceImg3.jpg" /></li>
					<li><a class="service_txt service_txt4"
						href="front/placeReserve.jsp"><img class="service_img"
							src="img/icon_service4.png" />场地预约</a> <img class="service_img"
						src="img/serviceImg4.jpg" /></li>
				</ul>
			</div>
		</div>
	</section>

	<!-- 弹窗 -->
	<section class="apply_popupWrap popupWrap">
		<div class="apply_popup popup">
			<!-- 弹窗关闭按钮 -->
			<div class="apply_popupClose popupCloseBtn">
				<img src="img/icon_close.png" />
			</div>
			<table class="apply_conTable popup_Table" id="newsDetail"
				cellspacing="0" cellpadding="0">
			</table>
		</div>
	</section>

	<!-- 尾部 -->
	<footer>
		<p>©浙江大学求是学院学生服务平台</p>
	</footer>
<div id="popDiv" class="mydiv" style="display:none;">请使用IE9以上、火狐、谷歌、360（极速模式）浏览器<br/>
	<a href="javascript:closeDiv()" style="font-size:16px;color:red;">关闭窗口</a>
</div>
<div id="bg" class="bg" style="display:none;"></div>	
</body>
</html>
