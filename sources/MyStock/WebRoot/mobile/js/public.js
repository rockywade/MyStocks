$(function(){
	
	// 活动申报 - 确认提交
	$(".applyForSub").click(function(){
		$(".popup").show();
	});
	
	// 活动申报 - 确认提交 - 取消
	$(".popupBtn_cancel").click(function(){
		$(".popup").hide();
	});
	
	// 活动申报 - 确认提交 - 同意
	$(".popupBtn_agree").click(function(){
		$(".popup-Confirm").hide();
		$(".popup-agree").show();
	});
	
	// 活动申报 - 确认提交 - 同意 - 好
	$(".popupBtn_ok").click(function(){
		$(".popup").hide();
		$(".popup-Confirm").show();
		$(".popup-agree").hide();
	});
	
	// 申报查询 - 取消
	$(".queryBtn-act").click(function(){
		$(this).parent().siblings(".acSstate").removeClass("acSstate1").addClass("acSstate4").children("samp").html("已取消");
		$(this).parent().remove();
	});
	
	//活动报名 - 导航点击
	$(".sign_ul li").click(function(){
		$(this).addClass("cur").siblings().removeClass("cur");
	});
	
	// 活动详情 - 报名
	$(".content_Btn").click(function(){
		//$(".popup").show();
	});
	
	// 场地详情 - 选框
	$(".place_conAgree").click(function(){
		if($(this).hasClass("place_Chked")){
			$(this).removeClass("place_Chked");
			$(".place_nextBtn").removeClass("place_nextBtn1ed").addClass("place_nextBtn1");
		}else{
			$(this).addClass("place_Chked");
			$(".place_nextBtn").removeClass("place_nextBtn1").addClass("place_nextBtn1ed");
		}
	});
	
	// 场地详情 - 时间点击
	$(".timeCan").click(function(){
		$(".tdTime ul li").each(function(){
			$(this).removeClass("timeCanCur");
		});
		$(this).addClass("timeCanCur");
	});
	
	// 场地详情 - 本周下周点击
	$(".time_topWeek").click(function(){
		$(this).addClass("time_topCur").parent("li").siblings("li").children().removeClass("time_topCur");
	});
	
	// 场地详情 - 申请表 
 	$(".place_formAgree").click(function(){
		if($(this).hasClass("place_Chked")){
			$(this).removeClass("place_Chked");
		}else{
			$(this).addClass("place_Chked").siblings("div").removeClass("place_Chked");
		}
	});
	
	// 场地详情 - 申请表 - 提交申请
	$(".place_appaySub").click(function(){
		var placeName = $(".placeName").val();//活动名称
		var placeCon = $(".placeCon").val();//活动内容
		var placeTime = $(".placeTime").val();//活动时间
		var placeClass = $(".placeClass option:selected").text();//活动类型
		var placeTea = $(".placeTea").val();//指导老师
		var placeOrg = $(".placeOrg option:selected").text();//组织
		var placePeopleNum = $(".placePeopleNum").val();//规模
		var placeFormChk = $(".place_formChk .place_Chked samp").html();//是否外校人员
		
		$(".confirmName p").html(placeName);//活动名称
		$(".confirmCon").html(placeCon);//活动内容
		$(".confirmTime").html(placeTime);//活动时间
		$(".confirmClass").html(placeClass);//活动类型
		$(".confirmOrg").html(placeOrg);//组织
		$(".confirmTeaZ").html(placeTea);//指导老师
		$(".confirmPeopleNum").html(placePeopleNum+'人');//规模
		$(".confirmFormChk").html(placeFormChk);//
		
		$(".confirm_wrap").show();
	});
	
	// 请假申请 - 选框
	$(".AskLeave_conAgree").click(function(){
		if($(this).hasClass("place_Chked")){
			$(this).removeClass("place_Chked");
			$(".AskLeaveSub").removeClass("AskLeaveSubed");
		}else{
			$(this).addClass("place_Chked");
			$(".AskLeaveSub").addClass("AskLeaveSubed");
		}
	});
	
	// 请假申请  - 弹窗
	$(".AskLeaveSub").click(function(){
		if($(this).hasClass("AskLeaveSubed")){
			$(".popup1").fadeIn(300);
			setTimeout(function(){
				$(".popup1").fadeOut(500);
			},800);
		}else{}
		
	});
	
	// 请假申请查询 - 取消
	var AskLeaveLiIndex;
	$(".queryBtn-ask").click(function(){
		$(".popup").show();
		AskLeaveLiIndex = $(this).parents("li").index();
	});
	
	// 请假申请查询 - 取消 - 确认
	$(".AskLeave_agree").click(function(){
		$(".popup").hide();
		$(".AskLeave_list li:eq("+AskLeaveLiIndex+") .acSstate").removeClass("acSstate1").addClass("acSstate4").children("samp").html("已取消");
		$(".AskLeave_list li:eq("+AskLeaveLiIndex+") .queryBtn-ask").parent().remove();
	});
	
	// 线下辅导 - 导航
	$(".tutoring_ul li").click(function(){
		$(this).addClass("cur").siblings().removeClass("cur");
	});
	
	//项目详情 - 收藏
	$(".icon-star").click(function(){
		if($(this).hasClass("icon-collect")){
			$(".popup_collect .popup_con p").html("收藏成功！");
			$(".popup_collect").fadeIn(300);
			setTimeout(function(){
				$(".popup_collect").fadeOut(500);
			},800);
			$(this).addClass("icon-collectOn").removeClass("icon-collect")
		}else{
			$(".popup_collect .popup_con p").html("取消收藏成功！");
			$(".popup_collect").fadeIn(300);
			setTimeout(function(){
				$(".popup_collect").fadeOut(500);
			},800);
			$(this).addClass("icon-collect").removeClass("icon-collectOn")
		}
	});
	
	// 项目详情 - 取消报名
	$(".cancelBtn-tut").click(function(){
		$(".popup_tutoring").show();
	});
	$(".popupTut_ok").click(function(){
		$(".cancelBtn-tut").remove();
		$(".tutConState").html("已取消").removeClass("tutState2").addClass("tutState3");
	})
	
	// 项目详情 - 评论
	$(".comment_btn").click(function(){
		$(".popup_collect .popup_con p").html("评论提交成功！");
			$(".popup_collect").fadeIn(300);
			setTimeout(function(){
				$(".popup_collect").fadeOut(500);
			},800);
	})
	
	$(".expert_appaySub").click(function(){
		//$(".popup_expert").show();
	});
	
	$(".placeApply_sub").click(function(){
		$(".popup_expert").show();
	});
	
	// 场地 申请查询 - 取消
	
	$(".placeApply_sub").click(function(){
		$(".popup_placeA").show();
	});
	
	$(".placeApple_ok").click(function(){
		$(".expert_appayWrap").remove();
		$(".placeApply_tit").addClass("acSstate4").children("samp").html("已取消");
		$(".popup").hide();
		$(".popup-Confirm").show();
		$(".popup-agree").hide();
	})
	
	// 预约查询 - 取消
	
	var expertLiIndex;
	$(".queryBtn-exp").click(function(){
		$(".popup").show();
		expertLiIndex = $(this).parents("li").index();
	});
	
	// 预约查询 - 取消 - 确认
	$(".expert_agree").click(function(){
		$(".popup").hide();
		$(".query_list li:eq("+expertLiIndex+") .acSstate").removeClass("acSstate1").addClass("acSstate4").children("samp").html("已取消");
		$(".query_list li:eq("+expertLiIndex+") .queryBtn-exp").parent().remove();
	});
	
})
