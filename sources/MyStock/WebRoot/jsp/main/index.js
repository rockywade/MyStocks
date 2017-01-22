var IndexPage = Ext.extend(Ext.Viewport, {
	top : {
            cls: 'app-header',
            region: 'north',
            height: 81,
            html: '<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;background-color: #1a60ac;padding: 0 70px;">'+
				  '<tr><td width="357px" style="background:url(../../img/top_left.png);">&nbsp;</td>'+
				  '<td><div class="header_right"><p id="clock"></p><img class="header_close" onclick="App.logoutSystem();" src="../../admin/img/icon_close1.png"/></div></td>'+
				  '</tr>'+
				'</table>'
    },
	west : {
			id:'west-panel',
			region : 'west',
			width : 200,
	        minSize: 200,
	        maxSize: 200,
			html:''
	},
	center:{
			id:'center-panel',
			region : 'center',
			border: false,
			html : '<iframe id="myFrame" src="../../welcome.html" width="100%" height="100%" scrolling="auto" frameborder="0" ></iframe>'
	},
	south : {
			
	},
	constructor : function() {
		IndexPage.superclass.constructor.call(this, {
					layout : 'border',
					items : [this.top,this.west,this.center,this.south]
				});
		var clock = new Clock();
    	clock.display(document.getElementById("clock"));
		this.loadMenu();
	},
	loadMenu : function(){
		//var toolBar = Ext.getCmp("top-toolbar");
		Ext.Ajax.request({
			url : "/MyStock/user_getMenuBuf.do",
            scope: this,
			success : function(response, options) {
				var menudata = response.responseText;
				if(menudata){
					var westPanel = Ext.getCmp("west-panel");
					if(westPanel.body){
						westPanel.body.update(menudata);
					}else{
						westPanel.html = menudata;
					}
				}
				/*导航 鼠标移入移出*/
				$(".backAside_ul li").mouseover(function(){
					var imgSrc = $(this).children("a").children("img").attr("src");
					if(imgSrc.indexOf('_on.png')==-1){
						var str = imgSrc.replace(".png","_on.png");
						$(this).children("a").children("img").attr("src",str);
					}
				}).mouseout(function(){
					var imgSrc = $(this).children("a").children("img").attr("src");
					if(imgSrc.indexOf('_on.png')>0){
						var str = imgSrc.replace("_on.png",".png");
						if($(this).attr("class") != "navCur"){
							$(this).children("a").children("img").attr("src",str);
						}
					}
					
				});

				/*导航 点击*/
				$(".backAside_ul li").click(function(){
					var thisIndex = $(this).index();/*获取当前下标*/

					$(".backAside_ul li").each(function(){
						var liIndex = $(this).index();
						var imgSrc = $(this).children("a").children("img").attr("src");
						var str;

						if(liIndex ==thisIndex){/*改变当前li下面的img的路径*/
							if(imgSrc.indexOf('_on.png')==-1){
								str = imgSrc.replace(".png","_on.png");
								$(this).children("a").children("img").attr("src",str);
							}
						}else{
							if(imgSrc.indexOf('_on.png')>0){
								str = imgSrc.replace("_on.png",".png");
								$(this).children("a").children("img").attr("src",str);
							}
							
						}
					})
					
					$(this).delay(100).addClass("navCur").siblings().removeClass("navCur");
				});
			},
			failure: function(){
				Ext.Msg.alert("错误提示","菜单初始化失败!");
			}
		});
	},
	clickMenu : function(n){
		var iframe = document.getElementById("myFrame");
		iframe.src = n.attributes.url;
	}
});
var Clock = function() {
	var date = new Date();
	this.year = date.getFullYear();
	this.month = date.getMonth() + 1;
	this.date = date.getDate();
	this.day = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六")[date.getDay()];
	this.hour = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
	this.minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
	this.second = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();

	this.toString = function() {
		return this.year + "年" + this.month + "月" + this.date + "日 " + this.hour + ":" + this.minute + ":" + this.second + " " + this.day; 
	};
	
	this.toSimpleDate = function() {
		return this.year + "-" + this.month + "-" + this.date;
	};
	
	this.toDetailDate = function() {
		return this.year + "-" + this.month + "-" + this.date + " " + this.hour + ":" + this.minute + ":" + this.second;
	};
	
	this.display = function(ele) {
		var clock = new Clock();
		ele.innerHTML = clock.toString();
		window.setTimeout(function() {clock.display(ele);}, 1000);
	};
}



function toUrl(obj){
	var iframe = document.getElementById("myFrame");
	iframe.src = obj;
}