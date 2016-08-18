<%@ page contentType="text/html; charset=UTF-8"%>
<%
   request.setCharacterEncoding("UTF-8"); //防止乱码问题
   String expertId = request.getParameter("expertId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0">
<meta name="format-detection" content="telephone = no" />
<title>专家详情</title>
<link rel="icon" href="img/logo1.png" />
<link rel="stylesheet" href="css/pullToRefresh.css" />
<link rel="stylesheet" href="css/public.css" />
<link rel="stylesheet" href="css/media.css" />
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/public.js"></script>
<script type="text/javascript">
			var expertId = "<%=expertId%>";
			$(document).ready(function(){
				getExpertDetail(expertId);
			});
			function getExpertDetail(eid){
				$.ajax({
					type : "post", 
					async : false,  
					url:"mobile_expertInfo.do",
					data:{id:eid},
					success : function(data,status){
						data = eval("("+data+")");
						start = data.currentResult;
						renderExpertDetail(data.data);
					}
				});
			}
		
			function renderExpertDetail(e){
				var s = "";
		        s+='<table cellpadding="0" cellspacing="0" class="table_form">';
				s+='<tr>';
				s+='<td class="td1">姓<i class="kg2"></i>名：</td>';
				s+='<td>'+e.expertName+'</td>';
				s+='<td class="td1">单位(办公)：</td>';
				s+='<td>'+e.expertUnit+'</td>';
				s+='</tr>';
				s+='<tr>';
				s+='<td class="td1">联系电话：</td>';
				s+='<td colspan="3">'+e.expertphone+'</td>';
				s+='</tr>';
				s+='<tr>';
				s+='<td class="td1">邮<i class="kg2"></i>箱：</td>';
				if(e.expertEmail){
					s+='<td colspan="3">'+e.expertEmail+'</td>';
				}else{
					s+='<td colspan="3">暂无邮箱信息</td>';
				}
				s+='</tr>';
				s+='<tr>';
				s+='<td class="td1">专家类别：</td>';
				s+='<td colspan="3">'+e.expertGenre+'</td>';
				s+='</tr>';
				s+='<tr>';
				s+='<td class="td1">空余时间：</td>';
				s+='<td colspan="3">'+e.freetime+'</td>';
				s+='</tr>';
				s+='<tr>';
				s+='<td class="td1">办公地址：</td>';
				s+='<td colspan="3">'+e.workaddress+'</td>';
				s+='</tr>';
				s+='<tr>';
				s+='<td class="td1 td2">个人简介：</td>';
				s+='<td colspan="3">'+e.personalInfro+'</td>';
				s+='</tr>';
				s+='</table>';
				s+='<div class="conBtn_wrap">';
				s+='<a href="expert-order.jsp?expertId='+e.id+'&expertName='+e.expertName+'&applygenre='+e.expertGenre+'" class="">预约</a>';
				s+='</div>';
				//s+='';
			 	$("#expertInfo").html(s);
		     }
		</script>

</head>
<body>
	<section class="wrap1">
		<div class="place_conPic">
			<img src="img/hallTopBg.jpg" />
		</div>
	</section>
	<section class="wrap2" id="expertInfo"></section>

</body>
</html>
