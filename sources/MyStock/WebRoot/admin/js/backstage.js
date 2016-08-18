$(function(){
	
	/*设置框架高度*/
	var windowH = $(window).height();
	var headerH = 81;

	/*$(".backW").css({"min-height":windowH-headerH});
	$(".backAside").css({"min-height":windowH-headerH});/*框架左侧*/
	/*$(".backIFrame").css({"min-height":windowH-headerH-5});/*框架高度外层*/
	
	var oH = $(".backIFrame").height();
	$("iframe").height(oH);

	/*头部获取日期*/
	var nowTime = new Date();
	var nowYears = nowTime.getFullYear();
	var nowMonth = nowTime.getMonth();
	var nowDay = nowTime.getDate();
	$(".nowTime samp").text(nowYears+"年"+nowMonth+"月"+nowDay+"日");/*放入日期*/

	/*取消活动*/
	$(".cancelBtn").click(function(){
		$(this).parent("td").parent("tr").children(".act_status").html("已取消").addClass("cancel");
		$(this).remove();
	});
	
	$(".startTxt").change(function(){
		alert($(this).val());
	})




	
})


