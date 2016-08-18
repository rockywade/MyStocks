
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<!-- 场地预约情况字体样式 -->
<style type="text/css">
.color1 {
	color: #1A60AC;
	text-decoration: none;
	font-weight: bold;
} /*链接设置*/
</style>

<%@ include file="common/top.jsp"%>

<!-- 弹出框-->
<link rel="stylesheet" type="text/css" href="css/dialog.css">
<script type="text/javascript" src="js/dialog.js"></script>


<script type="text/javascript">
	    	$(document).ready(function(){
	    		getData('','','');
	    		initPage();
			});
	    	var start = 0;
	    	var limit = 10;
	    	var currentPage = 1;
	    	var totalPage = 0;
			function getData(sitename,sitetype,park){
				$.ajax({
					type : "post", 
					async : false,  
					url:"front_findPageSiteInfoBy.do",
					data:{start:start, limit:limit,sitename:sitename,sitetype:sitetype,park:park},
					success:function(data,status){
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
						      var onlineoakey = $("#onlineoakey").val();
							 getData(sitename,sitetype,usetime,park);
						  }
						});
			}
			
			function render(data){
				var s = "";
				$.each(data,function(rowNum,o){ 
						s+='<tr>';
						s+='<td>'+o.sitename+'</td>';
						s+='<td><a href="javascript:void(0)" onclick="preview(\''+o.siteid+'\');" class="color1">'+o.nowstatus+'</a></td>';
						s+='<td>'+o.sitecondition+'</td>';
						s+='<td><img src="'+o.photo+'"/></td>';
						s+='<td>'+o.sitetype+'</td>';
					    s+='<td><a href="javascript:void(0)" onclick="golog();" class="table_button">预约</a></td>';
						s+='</tr>';
				});
				 if(data.length==0){
					   $("#tbody").html("暂无数据");
				    }else{
					   $("#tbody").html(s);
				 }
			
			}
			
		     //查询
			function search(){		
				var sitename = $("#sitename").val();
			    var sitetype = $("#sitetype").val();
			   // var usetime =  $("#usetime").val();
			    var park = $("#park").val();
				getData(sitename,sitetype,park);
			}
			
			//去登录页面
			function golog(){
				window.location.href="login_goToLogin.do?url=../siteinfo/siteinfoApproval.jsp";
			   }
			
			//时间预览
			function preview(siteid){
			     //alert(siteid);
			     $(window).ShowDialog({
                   width: 800,
                   height: 430,
                    //src: "../extensible-1.0.2/examples/calendar/test-app.jsp?siteid="+siteid
                     src: "../extensible-1.0.2/examples/calendar/extensible-config-preview.jsp?siteid="+siteid
                 });
                
			   }
	    </script>
</head>
<body>
	<!-- 头部 -->
	<%@ include file="common/head.jsp"%>

	<div class="return">
		<a href="#" onclick="JavaScript:history.back(-1);return false;" /><img
			src="img/icon_return.png"></a>
	</div>
	<section class="w">
		<div class="dataScreen_wrap">
			<ul>
				<li>
					<p>场地名称</p> <input type="text" name="sitename" id="sitename"
					placeholder="请输入场地关键字搜索" />
				</li>
				<li>
					<p>场地类型</p> <select name="sitetype" id="sitetype">
						<option value="">全部</option>
						<option value="会议室">会议室</option>
						<option value="活动室">活动室</option>
						<option value="党员之家">党员之家</option>
						<option value="宣传场地">宣传场地</option>
				</select>
				</li>
				<!--<li>
	    				<p>使用时间</p>
	    				<input  name="usetime"  id="usetime" type="text" />
	    			</li>
	    			-->
				<li>
					<p>园区</p> <select name="park" id="park">
						<option value="">全部</option>
						<option value="白沙">白沙</option>
						<option value="丹阳">丹阳</option>
						<option value="翠柏">翠柏</option>
						<option value="青溪">青溪</option>
						<option value="紫云">紫云</option>
						<option value="壁峰">壁峰</option>
						<option value="蓝田">蓝田</option>
				</select>
				</li>
				<li class="input_ser"><input type="button" value="搜索"
					class="date_search" onclick="search()" /></li>
			</ul>
		</div>

		<div class="list_wrap">
			<table class="list_table place_table" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th>场地</th>
						<th>预约状况</th>
						<th>会场条件</th>
						<th>照片</th>
						<th>类别</th>
						<th>操作</th>
					</tr>
				</thead>

				<tbody id="tbody">
				</tbody>

			</table>
			<div class="pagination">
				<a href="#" class="first" data-action="first">&laquo;</a> <a
					href="#" class="previous" data-action="previous">&lsaquo;</a> <input
					type="text" readonly="readonly" data-max-page="40" /> <a href="#"
					class="next" data-action="next">&rsaquo;</a> <a href="#"
					class="last" data-action="last">&raquo;</a>
			</div>
		</div>
	</section>

	<!-- 尾部 -->
	<%@ include file="common/footer.jsp"%>

</body>
</html>
