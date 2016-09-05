<%@ page contentType="text/html; charset=UTF-8"%>
<meta charset="UTF-8">
<title>求是学院学生服务平台</title>
<link rel="icon" href="img/logo.png" />
<link rel="stylesheet" href="css/public.css" />
<link rel="stylesheet" href="css/jqpagination.css" />
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/jquery.jqpagination.js"></script>
<script type="text/javascript" src="js/public.js"></script>
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