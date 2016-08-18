$(function(){
	
	// 活动报名 - 导航点击
	$(".apply_nav nav ul li").click(function(){
		$(this).addClass("cur").siblings().removeClass("cur");
	});
	
	
	//场地预约 - 鼠标移入显示大图
	var x = 30;
	var y = 80;
	$(".place_table tbody tr td img").mouseover(function(e){
  	
    	var thisSrc = $(this).attr("src");//获取图片链接
    	
   		var placeImg = "<div id='placeImgMax'><img src='"+ thisSrc +"' alt='预览图'/><\/div>";
    	//创建 div 元素
    	$("body").append(placeImg);
    	
    	//把它追加到文档中             
    	$("#placeImgMax").css({
	        "top": (e.pageY-y) + "px",
	        "left": (e.pageX+x) + "px"
	    }).stop().show("fast"); //设置x坐标和y坐标，并且显示
	    
	}).mouseout(function(){
	    $("#placeImgMax").remove(); //移除 
	}).mousemove(function(e){
		
	    $("#placeImgMax").css({
	        "top": (e.pageY-y) + "px",
	        "left": (e.pageX+x) + "px"
	    });
	});
	
	
	// 场地预约 -状态 - 周六周日不可预约背景颜色
	var reDay6 = $(".reserveDay6").index();
	var reDay7 = $(".reserveDay7").index();
	var srateX = 30;
	var srateY = 270;
	$(".state_table tbody tr").each(function(){
		$(this).children("td").eq(reDay6).addClass("tDisable");
		$(this).children("td").eq(reDay7).addClass("tDisable");
	})
	$(".place_table tbody tr").each(function(){
		$(this).children("td").eq(1).mouseover(function(e){
			$("#state_wrap").css({
				"top": (e.pageY-srateY) + "px",
		        "left": (e.pageX+srateX) + "px"
		    }).stop().show(); //设置x坐标和y坐标，并且显示
		}).mouseout(function(){
		    $("#state_wrap").hide(); //隐藏
		}).mousemove(function(e){
			$("#state_wrap").css({
				"top": (e.pageY-srateY) + "px",
		        "left": (e.pageX+srateX) + "px"
		    }); //设置x坐标和y坐标，并且显示
		});
	})
	
	
	
	//登录页 - 账号验证
	$(".login_name").keyup(function(){
		var regExp = /[0-9a-zA-Z]$/;
        if(!regExp.test($(this).val())){
            $(this).val("");
        }
	})
	$(".login_name").blur(function(){
		var nameVal = $(".login_name").val();
		if(nameVal == ""){
			alert("请输入账号");
		}
	})
	$(".login_from form ul li input").focus(function(){
		$(this).css({"color":"#333"});
	})
	$(".loginBtn").click(function(){
		var nameVal = $(".login_name").val();
		var pwdVal = $(".login_pwd").val();
		
		if(nameVal == ""){
			alert("请输入账号");
		}else if(pwdVal == ""){
			alert("请输入密码");
		}
	})
	
	
	//关闭弹窗
	$(".popupCloseBtn").click(function(){
		popupClose();
	})
	
	//活动报名 - 弹窗显示
	$(".apply_table tbody tr td:nth-child(2)").click(function(){
		popupShow();
	})
	
	//线下辅导 - 弹窗显示
	$(".tutoring_conBtn").click(function(){
		popupShow();
	})
	
	//专家预约 - 弹窗显示
	$(".expert_list li div").click(function(){
		popupShow();
	})
	
	
})
//隐藏活动报名的弹窗
function popupClose(){
	$(".popupWrap").animate({opacity:0},300);
	setTimeout(function(){
		$(".popupWrap").hide();
	},400);
}
//显示活动报名的弹窗
function popupShow(){
	$(".popupWrap").show().animate({opacity:'1'},300);
}
















