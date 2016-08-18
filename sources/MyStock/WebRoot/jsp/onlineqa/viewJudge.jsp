<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>线上答疑</title>
<link rel="stylesheet" type="text/css"
	href="../../ext/resources/css/ext-all.css">
<link rel="stylesheet" type="text/css" href="../../css/ext-icon.css">
<link rel="stylesheet" type="text/css" href="../../css/public.css">
<link rel="stylesheet" type="text/css" href="../../css/publicBack.css">
<script type="text/javascript" src="../../ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="../../ext/ext-all.js"></script>
<script type="text/javascript" src="../../ext/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="../../js/utils.js"></script>
<link rel="stylesheet" href="../../front/css/jqpagination.css" />
<script type="text/javascript" src="../../front/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="../../front/js/jquery.jqpagination.js"></script>

<script type="text/javascript" src="../../ext/ueditor/ueditor.js"></script>
<script type="text/javascript" charset="utf-8">window.UEDITOR_HOME_URL = "<%=basePath%>/ueditor/";</script>
<script type="text/javascript"
	src="../../ueditor/ueditor.config.judge.js"></script>
<script type="text/javascript" src="../../ueditor/ueditor.all.js"></script>
<script type="text/javascript" src="../../ueditor/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript">
	    	$(document).ready(function(){
	    		UE.getEditor('judgeContent');
	    		getPojo();
	    		getData();
	    		updatePopularity();
	    		initPage();
			});
			
			function getPojo(){
				$.ajax({
					type : "post", 
					async : false,  
					url:"front_findOnlineQA.do",
					data:{id:$("#id").val()},
					success:function(data,status){
						data = eval("("+data+")");
						$("#subject").html(data.data.subject);
						$("#content").html(data.data.content);
						if(''==data.data.attachment){
							$("#attachment").remove();
						}else{
							$("#attachment").attr("href",data.data.attachment);
						}
						if(''==data.data.image){
							$("#image").remove();
						}else{
							$("#image").attr("src",data.data.image);
						}
					}
				});
			}
			
			
	    	var start = 0;
	    	var limit = 5;
	    	var currentPage = 1;
	    	var totalPage = 0;
			function getData(){
				$.ajax({
					type : "post", 
					async : false,  
					url:"onlineQAJudge_findPageOnlineQAJudge.do",
					data:{start:start, limit:limit,qaid:document.getElementById("id").value},
					success :function(data,status){
						data = eval("("+data+")");
						render(data.root);
						start = data.currentResult;
						totalPage = data.totalPage;
					}
				});
			}
			
			function initPage(){
				$('.pagination').jqPagination({
						  link_string : '',
						  current_page: currentPage, //设置当前页 默认为1
						  max_page : totalPage, //设置最大页 默认为1
						  page_string : '当前第{current_page}页,共{max_page}页',
						  paged : function(page) {
						  	  start = (page-1)*limit;
						      getData();
						  }
						});
			}
			
			function render(data){
				var s = "";
				$.each(data,function(rowNum,o){
				
						s+='<li>';
						s+='<div class="reply_wrap reply_wrap1"><div class="reply_name"><samp>';
						s+=o.nickname;
						s+='</samp><i>|</i><span>'+o.judgetime+'</span></div><p>';
						s+=o.content;
						s+='</p></div>';
						s+='</li>';
				});
				$("#judgeUl").html(s);
			}
			
			function judge(){
				if(""==UE.getEditor('judgeContent').getContent()){
					alert("请填写评论内容");
					return false;
				}
				$.post("onlineQAJudge_saveOrUpdateOnlineQAJudge.do",{qaid:document.getElementById("id").value, content:UE.getEditor('judgeContent').getContent()},
					function(data,status){
						alert("评论成功");
						UE.getEditor('judgeContent').setContent("");
						getData();
					}
				);
			}
			
			function updatePopularity(){
				$.post("front_updateOnlineQAPopularity.do",{id:$("#id").val()},
					function(data,status){
					}
				);
			}
	    </script>
</head>

<body>
	<input type="hidden" value="<%=request.getParameter("id") %>" id="id" />
	<div class="return returnB">
		<a href="#" onclick="JavaScript:history.back(-1);return false;" /><img
			src="../../img/icon_return.png"></a>
	</div>
	<div class="answer_wrap answerB_wrap">
		<div class="answer_tit">
			<h2 id="subject"></h2>
		</div>
		<div class="answerB_cont">
			<p id="content"></p>
			<img class="answerB_contImg" id="image" /> <br />
			<a href="" id="attachment">附件</a>
		</div>

		<div class="answer_reply">
			<div class="reply_com">
				<textarea name="content" id="judgeContent"></textarea>
				<button id="buttonDiv" onclick="judge()">评论</button>
			</div>
			<ul id="judgeUl">
			</ul>
			<div class="pagination">
				<a href="#" class="first" data-action="first">&laquo;</a> <a
					href="#" class="previous" data-action="previous">&lsaquo;</a> <input
					type="text" readonly="readonly" data-max-page="40" /> <a href="#"
					class="next" data-action="next">&rsaquo;</a> <a href="#"
					class="last" data-action="last">&raquo;</a>
			</div>
		</div>


	</div>

</body>
</html>
