<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.cxstock.biz.power.dto.UserDTO"%>
<%
  UserDTO userInfo=(UserDTO)session.getAttribute("userInfo");
  String url=(String)session.getAttribute("url");
  session.removeAttribute("url");
  url = null==url?"":url;
%>
<html>
<head>
<title>求是学院学生服务平台</title>
<link rel="icon" href="../../img/favicon.png" />
<script type="text/javascript">
	     window.log_id="<%=userInfo.getUserid()%>";
	     window.log_name="求是学院";
	     var url = "<%=url%>";
	</script>
<link rel="stylesheet" type="text/css"
	href="../../ext/resources/css/ext-all.css">
<link rel="stylesheet" type="text/css" href="../../css/ext-icon.css">
<script type="text/javascript" src="../../ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="../../ext/ext-all.js"></script>
<script type="text/javascript" src="../../ext/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="../../js/Clock.js"></script>
<script type="text/javascript" src="index.js"></script>
<script type="text/javascript" src="App.js"></script>


<link rel="stylesheet" href="../../admin/css/backstage.css" />
<link rel="stylesheet" href="../../admin/css/public.css" />
<script type="text/javascript" src="../../admin/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../../admin/js/laydate.js"></script>
<script type="text/javascript" src="../../admin/js/backstage.js"></script>
<script type="text/javascript">
			$(function(){
				getWheresInfo();
			})

			//获取当前登录用户信息
			function getCurrentUserInfo(){
				$.ajax({
					type : "post", 
					async : false,  
					url:"/MyStock/Applyactivity_getCurrentUserInfo.do",
					data:{},
					success : function(data,status){
						data = eval("("+data+")");
						//alert(data.message);
						insertUserInfo(data.data);
					}
				});
			}
			function insertUserInfo(data){
				$("#stname").val(data.userName);
				$("#stxh").val(data.userNum);
				$("#stbj").val(data.userClasses);
				$("#styq").val(data.userYq);
				$("#stqs").val(data.userDorm);
				$("#stphone").val(data.userPhone);
				$("#stinstor").val(data.userInstructor);
				$("#sthamor").val(data.userHeadmaster);
			}
			
			$(function(){
				$("#wheresfact").change(function (){
					if($("#wheresfact").val()=="留校"){
						$("#mddtext").hide();
						$("#stmdd").hide();
						$("#stmdd").val($("#wheresfact").val());
						$("#letttruntime").hide();
						//alert($("#stmdd").val());
					} else if($("#wheresfact").val()=="回家") {
						$("#mddtext").hide();
						$("#stmdd").hide();
						$("#stmdd").val($("#wheresfact").val());
						$("#letttruntime").show();
					}
					else{
						$("#stmdd").val("");
						$("#mddtext").show();
						$("#stmdd").show();
						$("#letttruntime").show();
					}
				});
			});
			
			//获取是否存在待填写去向统计
			var startTime;
			function getWheresInfo(){
				$.ajax({
					type : "post", 
					async : false,  
					url:"/MyStock/Whereabouts_findNeedWriteWheres.do",
					data:{},
					success : function(data,status){
						data = eval("("+data+")");
						//alert(data.message);
						if(data.data){
							var startStr = data.data.launchstarttime;
							var end = data.data.launchendtime;
							var holidaytime = startStr + "--" + end;
							var date = new Date(startStr);
							startTime = date.getTime()-24*60*60*1000;
							$("#jrname").val(data.data.launchname);
							$("#lanchid").val(data.data.launchid);
							$("#censusendtime").val(data.data.censusendtime);
							$("#jrtime").val(holidaytime);
						}
						showWheresWindow(data.message);
					}
				});	
			}

			function showWheresWindow(d){
				var student = document.getElementById("student").value;
				if(student!=null && student!="" && d!="nothing"){
					getCurrentUserInfo();
					whereShow();
					$(".whereaboutBtn").click(function(){
						whereClose();
					})
				}
			}
			
			//显示弹窗
			function whereShow(){
				$(".wherePopupWrap").show().animate({opacity:'1'},300);
			}
			//隐藏弹窗
			function whereClose(){
				$(".wherePopupWrap").animate({opacity:0},300);
				setTimeout(function(){
					$(".wherePopupWrap").hide();
				},400);
			}
			//显示弹窗
			function safeInfoShow(){
				$(".safeInfoPopupWrap").show().animate({opacity:'1'},300);
			}
			//隐藏弹窗
			function safeInfoClose(){
				$(".safeInfoPopupWrap").animate({opacity:0},300);
				setTimeout(function(){
					$(".safeInfoPopupWrap").hide();
				},400);
			}

			function goToWheres(){
				var iframe = document.getElementById("myFrame");
				iframe.src = '../university/whereabouts/whereaboutscensus.jsp';
			}

			//手机号码验证
			function phoneNumCheck(num){
				var reg = /^(13\d|15[^4,\D]|17[13678]|18\d)\d{8}|170[^346,\D]\d{7}$/;
				if(num==120){
					var jjp = $("#jinjiphone").val();
					if(jjp!=null && jjp!="" && !reg.test(jjp)){
						alert("请输入有效的手机号码!");
						$("#jinjiphone").val("");
					}
				}else if(num==100){
					var psp = $("#parentsphone").val();
					if(psp!=null && psp!="" && !reg.test(psp)){
						alert("请输入有效的手机号码!");
						$("#parentsphone").val("");
					}
				}
			}
			
			//表单数据验证、提交
			$(function(){
				$("#suvmitCensus").click(function(){
					var submitFlg = false;
					var wf = $("#wheresfact").val();
					var sdd = $("#stmdd").val();
					var jjp = $("#jinjiphone").val();
					var lt = $("#lefttime").val();
					var tt = $("#turntime").val();
					var psp = $("#parentsphone").val();
					if(wf!=null && wf!=""){
						submitFlg = true;
						if(sdd!=null && sdd!=""){
							submitFlg = true;
							if(jjp!=null && jjp!=""){
								submitFlg = true;
								if(lt!=null && lt!=""){
									submitFlg = true;
									if(tt!=null && tt!=""){
										submitFlg = true;
										if(psp!=null && psp!=""){
											submitFlg = true;
											if($("#readcheck").is(":checked")){
												submitFlg = true;
											}else{
												alert("请先阅读《学生假期安全提醒》");
												submitFlg = false;
											}
										}else{
											alert("父母电话不能为空!");
											submitFlg = false;
										}
									}else{
										if(wf != "留校") {
											alert("返校时间不能为空!");
											submitFlg = false;
										}
									}
								}else{
									if(wf != "留校") {
										alert("离校时间不能为空!");
										submitFlg = false;
									}
								}
							}else{
								alert("紧急电话不能为空!");
								submitFlg = false;
							}
						}else{
							alert("目的地不能为空!");
							submitFlg = false;
						}
					}else{
						alert("去向选择不能为空!");
						submitFlg = false;
					}
					
					if(submitFlg){
						 $.ajax({
					         type : "POST",
			   			     url : "/MyStock/Whereabouts_whereAboutsCensus.do",
			   			      data:{censususername:$("#stname").val(),censususernum:$("#stxh").val(),
			   			    	launchid:$("#lanchid").val(),launchname:$("#jrname").val(),
			   			    	holidaytime:$("#jrtime").val(),classes:$("#stbj").val(),
			   			    	dorm:$("#stqs").val(),userphone:$("#stphone").val(),
			   			    	teacher:$("#sthamor").val(),counsellor:$("#stinstor").val(),
			   			    	wheresfact:wf,termini:sdd,urgentphone:jjp,leaveschooltime:lt,returnschooltime:tt,parentsknows:$('.parentsknows:checked').val(),
			   			    	parentsphone:psp,censusendtime:$("#censusendtime").val()
						        },
			   			      async: false,
			   			      success : function(o) {
						        	window.location.href = "index.jsp"
			   			      }
			   		   		});
					}
				});
			});

			//学生假期安全提醒
			function showSHSInfo(){
				$("#sthsinfo").html('<div class="stdHS_info"><h3>《学生假期安全提醒》</h3><ol>'+
						'<li>假期离校的同学，要向班主任或德育导师书面请假，报告去向及回校时间。</li>'+
						'<li>留在校内的同学不要在寝室内使用明火或违章电器，以免引起火灾，造成人身财产损失；寝室内切勿私自留宿外来人员；要注意进出宿舍的外来人员，如遇可疑情况，请主动查问并及时向值班人员或安全保卫处报告。</li>'+
						'<li>外出旅游的同学，务必购买人身安全保险，同时请注意防盗、防骗、防交通事故，增强食品卫生安全意识和自我保护能力，确保人身安全。旅途中要随时与家人、同学保持联系，告知近况和行踪。</li>'+
						'<li>节日期间仍然在实验室的同学，要严格按照实验室管理、使用规则操作，注意用电、用火、用气、用水等的安全。</li>'+
						'<li>请大家务必保管好自己的财物，寝室内不要存放现金和贵重物品；银行存款务必使用个人密码，银行卡与身份证分开存放，以防冒领及密码窃取；保管好自己的校园卡、学生证等证件以及宿舍的钥匙，切勿将证件和钥匙转借他人或请他人保管。</li>'+
						'<li>遇到突发事件，请同学们务必保持冷静，及时向有关部门反映情况（安全保卫处24小时值班电话：安全保卫处（紫金港校区）88206110；玉泉保卫办87951110；西溪保卫办88273110；华家池保卫办86971110；之江保卫办86592777）。</li></div>');
				safeInfoShow();
			}
		</script>
</head>

<body>
	<input type="hidden" id="student"
		value="${sessionScope.userInfo.student}">
	<input type="hidden" id="lanchid">
	<input type="hidden" id="censusendtime">
	<section class="wherePopupWrap">
		<div class="wherePopup">
			<div
				style="font-size: 14px; width: 100%; line-height: 30px; text-align: center;">去向统计</div>
			<table width="96%" cellspacing="7" cellpadding="0"
				style="margin-left: 25px">
				<tr>
					<td width="80" align="right">假<i class="ikg2"></i>日：
					</td>
					<td colspan="4"><input id="jrname" type="text"
						style="width: 91.5%" readonly="readonly"></td>
				</tr>
				<tr>
					<td align="right">假日时间：</td>
					<td colspan="4"><input id="jrtime" type="text"
						style="width: 91.5%" readonly="readonly"></td>
				</tr>
				<tr>
					<td align="right">姓<i class="ikg2"></i>名：
					</td>
					<td colspan="2"><input id="stname" type="text"
						style="width: 80%" readonly="readonly"></td>
					<td width="80" align="right">学<i class="ikg2"></i>号：
					</td>
					<td><input id="stxh" type="text" style="width: 80%"
						readonly="readonly"></td>
				</tr>
				<tr>
					<td align="right">班<i class="ikg2"></i>级：
					</td>
					<td colspan="2"><input id="stbj" type="text"
						style="width: 80%" readonly="readonly"></td>
					<td align="right">园<i class="ikg2"></i>区：
					</td>
					<td><input id="styq" type="text" style="width: 80%"
						readonly="readonly"></td>
				</tr>
				<tr>
					<td align="right">寝<i class="ikg2"></i>室：
					</td>
					<td colspan="2"><input id="stqs" type="text"
						style="width: 80%" readonly="readonly"></td>
					<td align="right">联系电话：</td>
					<td><input id="stphone" type="text" style="width: 80%"
						readonly="readonly"></td>
				</tr>
				<tr>
					<td align="right">班<i class="ikg1"></i>主<i class="ikg1"></i>任：
					</td>
					<td colspan="2"><input id="sthamor" type="text"
						style="width: 80%" readonly="readonly"></td>
					<td align="right">辅<i class="ikg1"></i>导<i class="ikg1"></i>员：
					</td>
					<td><input id="stinstor" type="text" style="width: 80%"
						readonly="readonly"></td>
				</tr>
				<tr>
					<td align="right">去向情况：</td>
					<td colspan="2"><select style="width: 80%" id="wheresfact">
							<option value="">--去向选择--</option>
							<option value="留校">留校</option>
							<option value="回家">回家</option>
							<option value="出游">出游</option>
							<option value="其他">其他</option>
					</select></td>
					<td id="mddtext" align="right">目<i class="ikg1"></i>的<i
						class="ikg1"></i>地：
					</td>
					<td><input id="stmdd" type="text" style="width: 80%">
					</td>
				</tr>
				<tr>
					<td align="right">紧急联系电话：</td>
					<td colspan="4"><input id="jinjiphone" type="text"
						style="width: 91.5%" onblur="phoneNumCheck(120)"></td>
				</tr>
				<tr id="letttruntime">
					<td align="right">离校时间：</td>
					<td colspan="2"><input id="lefttime" type="text"
						class="applyFor_date"
						style="width: 80%; background-size: contain;"></td>
					<td align="right">返校时间：</td>
					<td><input type="text" id="turntime" class="applyFor_date"
						style="width: 80%; background-size: contain;"></td>
				</tr>
				<tr>
					<td align="right">父母知情：</td>
					<td align="left"><span style="vertical-align: text-bottom">是<input
							id="yes" type="radio" class="parentsknows" name="parentsknows"
							value="是"></span></td>
					<td align="left"><span style="vertical-align: text-bottom">否<input
							id="no" type="radio" class="parentsknows" name="parentsknows"
							value="否" checked="checked"></span></td>
					<td align="right">父母电话：</td>
					<td><input id="parentsphone" type="text" style="width: 80%"
						onblur="phoneNumCheck(100)"></td>
				</tr>
				<tr>
					<td colspan="5"><i class="ikg2"></i><input id="readcheck"
						type="checkbox" checked="checked">我已阅读<u><a
							href="javascript:;" onclick="showSHSInfo()"
							style="color: #1a60ac;">《学生假期安全提醒》</a></u></td>
				</tr>
				<tr>
					<td colspan="5" align="center"><input id="suvmitCensus"
						type="button" style="width: 65px; height: 25px" value="确认保存"></td>
				</tr>
			</table>
		</div>
	</section>
	<section class="safeInfoPopupWrap">
		<div class="safeInfoPopup">
			<div class="popupCloseBtn" onclick="safeInfoClose();">
				<img src="../../admin/img/icon_close.png" />
			</div>
			<div class="safeInfoCon" id="sthsinfo"></div>
		</div>
	</section>

	<script type="text/javascript">
		$(function(){
			var start ={
				elem: '#lefttime',
				min: laydate.now(startTime),//设定最小日期为当前日期
				choose: function(datas){
			         end.min = datas; //开始日选好后，重置结束日的最小日期
			         end.start = datas //将结束日的初始值设定为开始日
			    }
			};
			var end = {
				elem: '#turntime',
				min: laydate.now(),//设定最小日期为当前日期
				choose: function(datas){
			        start.max = datas; //结束日选好后，重置开始日的最大日期
			    }
			};
	
			laydate(start);
			laydate(end);
			})
		</script>
</body>
</html>