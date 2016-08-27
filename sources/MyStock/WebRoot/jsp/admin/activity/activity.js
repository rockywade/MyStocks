Ext.BLANK_IMAGE_URL = '../../../ext/resources/images/default/s.gif';
/*!
 * 活动创建
 */
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//组织名称下拉菜单
    var zzStore = new Ext.data.JsonStore({
		url: 'SiteInfo_findOrgaComb.do',
	    root: 'root',
	    totalProperty: 'total',
	    fields: ['value','text'],
	    listeners:{
	    	load:function(s){
	    		r = s.getAt(0);
	    		if(r){
	    			Ext.getCmp("zzCombo").onSelect(r, 0);
	    		}
	    	}
	    }
	});
    //所在学期下拉菜单
    var szxqStore = new Ext.data.JsonStore({
		url: 'Applyactivity_findSzxqComb.do',
	    root: 'root',
	    totalProperty: 'total',
	    fields: ['value','text'],
	    listeners:{
	    	load:function(s){
	    		r = s.getAt(0);
	    		if(r){
	    			Ext.getCmp("szxqCombo").onSelect(r, 0);
	    		}
	    	}
	    }
	});
	//查询表单
	var searchForm = new Ext.FormPanel({
		region:"north",
		height: 80,
		border : false,
		layout : "form",
		padding : "5 20 0 20",
		items:[{
			xtype:"fieldset",
			title:"活动名称检索",
			padding:"0 20 0 15",
			items:[{
				layout:"column",
				defaults:{
					xtype:"container",
					autoEl:"div",
					labelAlign:"right",
					layout:"form"
				},
				items:[{
					width:320,
					items:[{
						width:300,
						labelWidth:30,
						xtype:"textfield",
						name:"actionName",
						anchor:"90%"
							}]
						},{
							width:160,
							items:[{
								width:75,
								xtype:"button",
								//iconCls:"btn-list",
								text:"查询",
								handler:function(){
									var f = searchForm.getForm();
									if (f.isValid()) {
										var _params = f.getValues();
										//searchForm.getForm().reset();
										store.load({params:_params});
									}
								}
							}]
				},{
					width:260,
					items:[{
						width:90,
						xtype:"button",
						text:'创建活动',
						handler:function(){
							/*addWindow.show();
							addForm.getForm().reset();*/
							makeModle(grid);
						}
					}]
				},{
					width:100,
					items:[{
						cls :'returnBtn',
						width:90,
						xtype:"button",
						text:"返<i class='ikg2'></i>回",
						handler:function(){
								   window.location.href="../../menu/personal-affairs.html";
						}
					}]
				}]
			},{
				xtype:'hidden',
				name:'start',
				value:'0'
			},{
				xtype:'hidden',
				name:'limit',
				value:'15'
			}]
		}]
	});
	
    //预约方法
    var  makeModle = function (grid){
     	//var record = grid.getStore().getAt(rowIndex); 
         //Ext.getCmp("id").setValue(record.data.id);
         //Ext.getCmp("sitename").setValue(record.data.sitename);
     	 //Ext.getCmp("sitecondition").setValue(record.data.sitecondition);
     	 //隐藏传行号rowIndex
         //Ext.getCmp("rowIndex1").setValue(rowIndex);
         
         //viewWindow.hide();   
     	agreementWindow.show();
    }
	
    
	 //拼接协议
    var talk = '';
        talk +='活动申报流程及申报责任说明\r\n';
        talk +='一、活动申报流程\r\n';
        talk +='1、申报举办的活动须在纪实考评加分范围内（学业指导、专业讲座、职业规划等相关活动），并至少提前一周在网站上进行申报。申报时如实填写信息，包括活动主题、时间、地点、性质、规模以及活动要求的记实考评加分额度（具体加分额度请参考《记实考评工作细则》）等。在系统里完成申报后，打印好申报表，经指导老师签字与指导单位盖章后，至少提前5天上交至申报人所在学园的纪实考评办公室（蓝田学园：蓝田六舍209室，蒋少佳，88206718；丹青学园：青一131室，王老师，22806841；云峰学园：碧峰连廊131室，田老师，88206505）。\r\n';
        talk +='2、学生可在网上进行预报名，活动方可将预名单导出作为参考，最后参加活动的人员公示名单以活动方现场签到表为准。\r\n';
        talk +='3、活动结束后，请在3天内上传活动新闻稿和签到名单，根据导入系统的签到情况，记实考评加分结果将反馈至参加学生处，同时进行网上公示，为期一周。公示通过后，参与活动的同学即可获得记实考评加分。\r\n';
        talk +='二、求是学院活动申报责任说明   \r\n';
        talk +='1. 确保所申报的活动信息内容合法且不与校规相违背。\r\n';
        talk +='2. 活动安全由主办或承办单位负责，请严格遵循“安全第一、预防为主”的方针。\r\n';
        talk +='3. 活动申报成功后请勿私下改动活动形式或内容等。\r\n';
        talk +='4. 活动开展不得影响学校正常的教学、科研、学习和生活秩序。\r\n';
    
      //阅读协议
        var agreementForm = new Ext.FormPanel({
    		layout : 'form',
    		frame:true,
    		labelWidth:60,
    		border : false,
    		padding : '10 10 0 8',
    		defaults : {
    			anchor : '100%'
    		},
    		items:[{
    			xtype:'textarea',
    			id:'agreementto',
    			readOnly:true,
    			anchor: '97.5%',
    			value:talk,
    			style:'border:0', 
    			height:200
    		},{
                xtype: "checkbox",
                id:'agreement',
                name: "agreement",
                boxLabel: "我同意以上协议",
                checked:true,	
    			allowBlank : false
    		},{
    			xtype : 'hidden',
    		    name : 'id',
    		    id:'id'
    		    
    		},{
    			xtype : 'hidden',
    		    name : 'rowIndex1',
    		    id : "rowIndex1"
    		}]
    	});    
        
    var agreementWindow = new Ext.Window({
    	title:'阅读协议',
		width:650,
		height:370,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [agreementForm],
		buttons : [{
			text : '下一步',
			handler : function() { 
		       if(false == agreement){
		    	   alert("请先阅读场地使用协议！");
		    	   return false
		    	}
		       
		       addWindow.show();
		       addForm.getForm().reset();
				 agreementWindow.hide();
				 //时间选择
				 //updateStatusHandler2();
					 
					
			}
		}]
	});
	//fields类声明
	var Activity = [
	        		{ name:'activityid', type:'string'},
	        		{ name:'activityname', type:'string'},
	        		{ name:'applyuser', type:'string'},
	        		{ name:'studentnum', type:'string'},
	        		{ name:'studentphonenum', type:'string'},
	        		{ name:'organizename', type:'string'},
	        		{ name:'teacher', type:'string'},
	        		{ name:'phonenum', type:'string'},
	        		{ name:'activitygenre', type:'string'},
	        		{ name:'activitytime', type:'string'},
	        		{ name:'inschoolterm', type:'string'},
	        		{ name:'faceobj', type:'string'},
	        		{ name:'capacity', type:'int'},
	        		{ name:'attendnum', type:'int'},
	        		{ name:'activityplace', type:'string'},
	        		{ name:'timeofduration', type:'string'},
	        		{ name:'activitycontent', type:'string'},
	        		{ name:'signupendtime', type:'string'},
	        		{ name:'score', type:'int'},
	        		{ name:'applystyle', type:'string'},
	        		{ name:'checktime', type:'string'},
	        		{ name:'uploadnewstime', type:'string'},
	        		{ name:'activitypublicitytime', type:'string'},
	        		{ name:'newscheckstyle', type:'string'},
	        		{ name:'publicitycheckstyle', type:'string'}
	        	];
	
	//获取response数据
	var store = new Ext.data.JsonStore({
	    url: 'Applyactivity_listMyActivity.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: {params:{start:0, limit:15}},
	    fields: Activity,
	});
	
	/*按钮调用方法*/
	//修改
	var edit = function(grid, rowIndex, colIndex){
    	var record = grid.getStore().getAt(rowIndex);
    	addWindow.show();
    	addForm.getForm().loadRecord(record);
    };
    //取消
    var cancel = function(grid, rowIndex, colIndex){
    	var record = grid.getStore().getAt(rowIndex);
    	Ext.MessageBox.confirm('取消提示', '确定取消？', function(c) {
			   if(c=='yes'){
				   Ext.Ajax.request({
			   			url : "Applyactivity_saveActivity.do",
			   			params:{activityid:record.data.activityid,checkkey:1}
				   });
				   store.reload();
			   	}
		});
    };
    //活动管理
    var manage = function(grid, rowIndex, colIndex){
    	var record = grid.getStore().getAt(rowIndex);
    	Ext.Ajax.request({
	   		url : "Applyactivity_activityGetId.do",
	   		params:{activityid:record.data.activityid},
	   		success:function(msg){
		    	window.location.href = 'activitymanage.jsp';
		    }
		});
    };
    //活动删除
    var deleteActivity = function(grid, rowIndex, colIndex){
    	var record = grid.getStore().getAt(rowIndex);
    	Ext.MessageBox.confirm('取消提示', '确定取消？', function(c) {
			   if(c=='yes'){
				   Ext.Ajax.request({
			   			url : "Applyactivity_deleteActivity.do",
			   			params:{activityid:record.data.activityid}
				   });
				   store.reload();
			   	}
		});
    };
	//新闻稿
    var news = function(grid, rowIndex, colIndex){
    	var record = grid.getStore().getAt(rowIndex);
    	Ext.Ajax.request({
    		url:"Applyactivity_newsDeatail.do",
    		params:{activityid:record.data.activityid},
    		success:function(response){
    			var  responsedata = Ext.util.JSON.decode(response.responseText);
    			if(responsedata){
    				newsForm.getForm().findField("newstitle").setValue(responsedata.data.newstitle);
    				if(responsedata.data.website!=null && responsedata.data.website!=""){
    					newsForm.getForm().findField("website").setValue(responsedata.data.website);
    				}else{
    					//debugger;
    					//alert(responsedata.data.website);
    					newsForm.getForm().findField("website").setValue("您未给该新闻设置链接网址");
    				}
    				newsForm.getForm().findField("newsdate").setValue(responsedata.data.newsdate);
    				newsForm.getForm().findField("writer").setValue(responsedata.data.writer);
    				newsForm.getForm().findField("property").setValue(responsedata.data.property);
    				newsForm.getForm().findField("content").setValue(responsedata.data.content);
    				newsWindow.show();
    			}
    		}
    	});
    };
    
    
	var rownumber = new Ext.grid.RowNumberer();//行号
    var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[rownumber,
			    {header: '活动名称', width: 100,align:'center', dataIndex: 'activityname'},
			    {header:'活动类别',width:100,align:'center',dataIndex:'activitygenre'},
			    {header:'时间',width:100,align:'center',dataIndex:'activitytime'},
	            {header: '所在学期', width: 100,align:'center', dataIndex: 'inschoolterm'},
	            {header: '活动地点', width: 100,align:'center', dataIndex: 'activityplace'},
	            {header: '活动考评分', width: 100,align:'center', dataIndex: 'score'},
	            {header: '状态', width: 100,align:'center', dataIndex: 'applystyle',renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
		               if(value == '已通过') { 
	                        return "<span style='color:#6bbc6e;font-weight:bold;'>已通过</span>"
	                   }     
	                   if(value == '不通过') { 
	                      return "<span style='color:#ee7b25;font-weight:bold;'>未通过</span>"
	                   }
	                   if(value == '待审核') { 
		                      return "<span style='color:#333;font-weight:bold;'>待审核</span>"
		               } 
	                   if(value == '已取消') { 
		                      return "<span style='color:red;font-weight:bold;'>已取消</span>"
		               }
	                }
	            },
	            {xtype: 'actioncolumn',header : '操作', width: 160,align : 'center',menuDisabled : true,
	                items: [{
	                    icon: '../../../img/btn/print.png',
	                    //tooltip: '打印',
	                    getClass: function(value, metaData, record, rowIndex, colIndex, store, view) {      
	                	if('不通过'==record.data.applystyle || '已通过'==record.data.applystyle || '已取消'==record.data.applystyle){
                            return 'x-hidden'
                      }
	                	},
	                	handler: function(grid, rowIndex, colIndex){
	                		var newWindow=window.open("打印窗口","",'left=250,top=150,width=1000,height=600,toolbar=no,menubar=no,status=no,scrollbars=yes,resizable=yes');//打印窗口要换成页面的url
	                		var record = grid.getStore().getAt(rowIndex);
	                		
	                		var docStr = '<html><head><meta charset="utf-8"><title>活动申报打印</title></head><body>';
	                		
	                		 docStr +='<div style="width: 850px; height:600px;margin:20px auto;" id="printDiv">';
	                		 docStr +='<table style="width: 840px; height:550px;" cellspacing="0" cellpadding="2px" border="1" bordercolor="black">';
	                		 docStr += ' <thead>';
	                		 docStr += ' <tr>';
	                		 docStr += ' <th colspan="7"  style="text-align: center;height:40px;">';
	                		 docStr += ' 活动申报';       
	                		 docStr += ' </th>';
	                		 docStr += ' </tr>';
	                		 docStr += ' </thead>';
	                		 docStr += '<tbody>';
	                		 docStr += '<tr>';
	                		 docStr += '<th style="text-align: center">';
	                		 docStr += '活动名称:';  
	                		 docStr += '</th>';
	                		 docStr += '<td style="width: 150px;text-align: center" colspan="3">';
	                		 docStr += record.data.activityname;
	                		 docStr += '</td>';
	                		 docStr += '</tr>';
	                		 docStr += '<tr>';
	                		 docStr += '<th style="text-align: center">';
	                		 docStr += '申 报 人:';  
	                		 docStr += '</th>';
	                		 docStr += '<td style="width: 250px;text-align: center">'+record.data.applyuser+'</td>';
	                		 docStr += '<th style="text-align: center">';
	                		 docStr += '学　　号:';  
	                		 docStr += '</th>';
	                		 docStr += '<td style="width: 250px;text-align: center">'+record.data.studentnum+'</td>';
	                		 docStr += '</tr>';
	                		 docStr += '<tr>';
	                		 docStr += '<th style="text-align: center">';
	                		 docStr += '联系电话:';  
	                		 docStr += '</th>';
	                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.studentphonenum+'</td>';
	                		 docStr += '<th style="text-align: center">';
	                		 docStr += '组织名称:';  
	                		 docStr += '</th>';
	                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.organizename+'</td>';
	                		 docStr += '</tr>';
	                		 docStr += '<tr>';
	                		 docStr += '<th style="text-align: center">';
	                		 docStr += '指导老师:';     
	                		 docStr += '</th>';  
	                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.teacher+'</td>';
	                		 docStr += '<th style="text-align: center">';
	                		 docStr += '手机号码:';     
	                		 docStr += '</th>';  
	                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.phonenum+'</td>';
	                		 docStr += '</tr>';
	                		 docStr += '<tr>';
	                		 docStr += '<th style="text-align: center">';
	                		 docStr += '所在学期:';     
	                		 docStr += '</th>';  
	                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.inschoolterm+'</td>';
	                		 docStr += '<th style="text-align: center">';
	                		 docStr += '类　　别:';     
	                		 docStr += '</th>';  
	                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.activitygenre+'</td>';
	                		 docStr += '</tr>';
	                		 docStr += '<tr>';
	                		 docStr += '<th style="text-align: center">';
	                		 docStr += '面向对象:';     
	                		 docStr += '</th>';  
	                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.faceobj+'</td>';
	                		 docStr += '<th style="text-align: center">';
	                		 docStr += '容　　量:';     
	                		 docStr += '</th>';  
	                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.capacity+'</td>';
	                		 docStr += '</tr>';
	                		 docStr += '<tr>';
	                		 docStr += '<th style="text-align: center">';
	                		 docStr += '活动地点:';  
	                		 docStr += '</th>';
	                		 docStr += '<td style="width: 150px;text-align: center" colspan="3">';
	                		 docStr += record.data.activityplace;
	                		 docStr += '</td>';
	                		 docStr += '</tr>';
	                		 docStr += '<tr>';
	                		 docStr += '<th style="text-align: center">';
	                		 docStr += '活动时间:';     
	                		 docStr += '</th>';  
	                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.activitytime+'</td>';
	                		 docStr += '<th style="text-align: center">';
	                		 docStr += '时　　 长:';     
	                		 docStr += '</th>';  
	                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.timeofduration+'</td>';
	                		 docStr += '</tr>';
	                		 docStr += '<tr>';
	                		 docStr += '<th style="text-align: center">';
	                		 docStr += '报名截止:';     
	                		 docStr += '</th>';  
	                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.signupendtime+'</td>';
	                		 docStr += '<th style="text-align: center">';
	                		 docStr += '活动考评分:';     
	                		 docStr += '</th>';  
	                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.score+'</td>';
	                		 docStr += '</tr>';
	                		 docStr += '<tr>';
	                		 docStr += '<th style="text-align: center">';
	                		 docStr += '活动内容:';  
	                		 docStr += '</th>';
	                		 docStr += '<td style="width: 150px;" colspan="3">';
	                		 docStr += record.data.activitycontent;
	                		 docStr += '</td>';
	                		 docStr += '</tr>';
	                		 docStr += '<tr>';
	                		 docStr += '<th style="text-align: center">';
	                		 docStr += '签名(盖章):';  
	                		 docStr += '</th>';
	                		 docStr += '<td style="width: 150px;text-align: center" colspan="3">';
	                		 docStr += '</td>';
	                		 docStr += '</tr>';
	                	     docStr += '</tbody>';
	                		 docStr +='</table>';
	                		 docStr +='</div>';
	                		 docStr +='</body></html>';
	                	    newWindow.document.write(docStr);
	                	    newWindow.document.close();
	                	    newWindow.print();
	                	    newWindow.close();
	                	}
	                 },{
	                	icon: '../../../img/btn/split.png',
	                	getClass: function(value, metaData, record, rowIndex, colIndex, store, view) {      
	                		if('不通过'==record.data.applystyle || '已通过'==record.data.applystyle || '已取消'==record.data.applystyle){
	                              return 'x-hidden'
	                        }
	                	}
	                 },{
	                    icon: '../../../img/btn/edits.png',
	                    //tooltip: '修改',
	                    getClass: function(value, metaData, record, rowIndex, colIndex, store, view) {      
	                		if('不通过'==record.data.applystyle || '已通过'==record.data.applystyle || '已取消'==record.data.applystyle){
	                              return 'x-hidden'
	                        }
	                	},
	                    handler: function(grid, rowIndex, colIndex){
	                		edit(grid, rowIndex, colIndex);
	                	}
	                  },{
	                    icon: '../../../img/btn/cancel.png',
	                    //tooltip: '取消',
	                    getClass: function(value, metaData, record, rowIndex, colIndex, store, view) {      
	                		if('不通过'==record.data.applystyle || '已通过'==record.data.applystyle || '已取消'==record.data.applystyle){
	                              return 'x-hidden'
	                        }
	                	},
	                	handler: function(grid, rowIndex, colIndex){
	                		cancel(grid, rowIndex, colIndex);
	                	}
	                },{
	                    icon: '../../../img/btn/activitymanage.png',
	                    //tooltip: '活动管理',
	                    getClass: function(value, metaData, record, rowIndex, colIndex, store, view) {      
	                		if('不通过'==record.data.applystyle || '待审核'==record.data.applystyle || '已取消'==record.data.applystyle){
	                              return 'x-hidden'
	                        }
	                	},
	                    handler: function(grid, rowIndex, colIndex){
	                		manage(grid, rowIndex, colIndex);
	                	}
	                },{
	                	icon: '../../../img/btn/split.png'
	                 },{
	                	icon: '../../../img/btn/delete.png',
	                	getClass: function(value, metaData, record, rowIndex, colIndex, store, view) {      
	                		if('不通过'==record.data.applystyle || '待审核'==record.data.applystyle || '已取消'==record.data.applystyle){
	                              return 'x-hidden'
	                        }
	                	},
	                    handler: function(grid, rowIndex, colIndex){
	                		deleteActivity(grid, rowIndex, colIndex);
	                	}
	                },{
	                	icon: '../../../img/btn/split.png',
	                	getClass: function(value, metaData, record, rowIndex, colIndex, store, view) {      
	                		if('不通过'==record.data.applystyle || '待审核'==record.data.applystyle || '已取消'==record.data.applystyle || "no"==record.data.newscheckstyle){
	                              return 'x-hidden'
	                        }
	                	}
	                },{
	                    icon: '../../../img/btn/newsfile.png',
	                    //tooltip: '新闻稿',
	                    getClass: function(value, metaData, record, rowIndex, colIndex, store, view) {      
	                		if('不通过'==record.data.applystyle || '待审核'==record.data.applystyle || '已取消'==record.data.applystyle || "no"==record.data.newscheckstyle){
	                              return 'x-hidden'
	                        }
	                	},
	                	handler: function(grid, rowIndex, colIndex){
	                		news(grid, rowIndex, colIndex);
	                	}
	                }]
	           }]
        }),
        rownumber:rownumber,//checkbox
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
		loadMask : true,	//加载时的遮罩
		frame : true,
		region:"center",
        iconCls:'menu-51',
        bbar: new Ext.PagingToolbar({
            pageSize: 15,
            store: store,
            displayInfo: true
        }),
        viewConfig : {
        	forceFit: true,
        	scrollOffset: 2
    	}
    });
    
  //下拉菜单
    /*时长*/
    var timeofdur = new Ext.data.SimpleStore({
	    fields: ['value','text'],
	    data : [['0.5小时','0.5小时'],['1小时','1小时'],['1.5小时','1.5小时'],['2小时','2小时'],
	            ['2.5小时','2.5小时'],['3小时','3小时'],['3.5小时','3.5小时'],['4小时','4小时'],
	            ['4小时以上','4小时以上']]
	});
  
    /*组织名称*/
    var orgname = new Ext.data.SimpleStore({
	    fields: ['value', 'text'],
	    data : [['学生','学生'],['学生会','学生会'],['共青团','共青团']]
	});
    
    /*所在学期*/
    //var years = new Date();
    var schoolterm = new Ext.data.SimpleStore({
	    fields: ['value', 'text'],
	    data : [['上学期','上学期'],['下学期','下学期']]
	});
    
    /*活动类别*/
    var genre = new Ext.data.SimpleStore({
    	fields:['value','text'],
    	data:[['学科讲座','学科讲座'],['心理健康','心理健康'],['生涯规划','生涯规划'],
    	      ['党团建设','党团建设'],['文体活动','文体活动'],['其他','其他']]
    });
    
    //面向对象
    var obj = new Ext.data.SimpleStore({
    	fields:['value','text'],
    	data:[['人文社科类','人文社科类'],['理科大类','理科大类'],['工科大类','工科大类'],
    	      ['信息大类','信息大类'],['医药大类','医药大类'],['农学大类','农学大类']]
    });
 
  //时间验证
    Ext.apply(Ext.form.VTypes, {
        daterange : function(val, field) {
            var date = field.parseDate(val);
            //alert(date);
            if (!date) {
                return;
            }
            if (field.startDateField
                    && (!this.dateRangeMax || (date.getTime() != this.dateRangeMax
                            .getTime()))) {
                var start = Ext.getCmp(field.startDateField);
                start.setMaxValue(date);
                this.dateRangeMax = date;
                start.validate();
            } else if (field.endDateField
                    && (!this.dateRangeMin || (date.getTime() != this.dateRangeMin
                            .getTime()))) {
            	var end = Ext.getCmp(field.endDateField);
                end.setMinValue(date);
                this.dateRangeMin = date;
                end.validate();
            }
            return true;
        }
    });

    //获取当前用户信息
    var getCurrentUserInfo = function(){
    	Ext.Ajax.request({
    		url:"Applyactivity_getCurrentUserInfo.do",
    		success:function(response){
    			var responsedata = Ext.util.JSON.decode(response.responseText);
    			if(responsedata){
    				addForm.getForm().findField('studentnum').setValue(responsedata.data.userNum);
    				addForm.getForm().findField('applyuser').setValue(responsedata.data.userName);
    				addForm.getForm().findField('studentphonenum').setValue(responsedata.data.userPhone);
    			}
    		}
    	});
    };
    
  //申请活动窗口
    var addForm = new Ext.FormPanel({
		layout : 'form',
		//baseCls:'x-plain',
		frame:true,
		url :'Applyactivity_saveActivity.do',
		labelWidth:70,
		//labelAlign : "right",
		border : false,
		padding : '15 0 15 10',
		/*defaults : {
			anchor : '100%'
		},*/
		items:[{
			xtype : 'textfield',
			name:'activityname',
			fieldLabel:'活动名称',
			allowBlank : false,
			maxLength :30,
			width: 394,
		},{
			layout:'column',
			items:[{
				columnWidth:.5,
				layout:'form',
				/*defaults:{
					anchor : '95%'
				},*/
				items:[{
					xtype : 'textfield',
					name:'applyuser',
					fieldLabel:'申<i class="ikg1"></i>报<i class="ikg1"></i>人',
					readOnly:true,
					allowBlank : false,
					width: 150,
					maxLength :20,
					//cls: 'borderNone',
				},{
					xtype : 'textfield',
					name:'studentphonenum',
					readOnly:false,
					fieldLabel:'联系电话',
					allowBlank : false,
					maxLength :11,
					width: 150,
					//cls: 'borderNone'
				},{
					xtype : 'textfield',
					name:'teacher',
					fieldLabel:'指导老师',
					allowBlank : false,
					maxLength :10,
					width: 150,
				},{
					id:'szxqCombo',
					xtype:'combo',
					hiddenName:'inschoolterm',
					fieldLabel:'所在学期',
					mode: 'local',
					triggerAction: 'all',
					valueField :"value",
					displayField: "text",
					allowBlank : false,
					editable : false,
					store : szxqStore,
					allowBlank : false,
					width: 150,
					listeners:{
						select : function(a,b){
							addForm.getForm().findField("inschoolterm").setValue(b.data.text);
						}
					}
				},{
					xtype:'lovcombo',
					hiddenName:'faceobj',
					//id:'classes',
					fieldLabel:'面向对象',
					mode: 'local',
					triggerAction: 'all',
					valueField :'value',
					displayField: 'text',
					emptyText: '请选择',
					allowBlank : false,
					editable : false,
					store : obj,
					width: 150,
				}]
			},{
				columnWidth:.5,
				layout:'form',
				/*defaults:{
					anchor : '95%'
				},*/
				items:[{
					xtype : 'textfield',
					name:'studentnum',
					fieldLabel:'学<i class="ikg2"></i>号',
					readOnly:true,
					allowBlank : false,
					maxLength :10,
					//cls: 'borderNone',
					width: 150,
				},{
					id:'zzCombo',
					xtype:'combo',
					hiddenName:'organizename',
					fieldLabel:'组织名称',
					mode: 'local',
					triggerAction: 'all',
					valueField :"value",
					displayField: "text",
					allowBlank : false,
					editable : false,
					store : zzStore,
					allowBlank : false,
					width: 150,
					listeners:{
						select : function(a,b){
							addForm.getForm().findField("organizename").setValue(b.data.text);
						}
					}
				},{
					xtype : 'numberfield',
					name:'phonenum',
					fieldLabel:'手机号码',
					allowBlank : false,
					maxLength :11,
					regex:/^(13\d|15[^4,\D]|17[13678]|18\d)\d{8}|170[^346,\D]\d{7}$/,
					width: 150,
				},{
					xtype:'combo',
					hiddenName:'activitygenre',
					fieldLabel:'类<i class="ikg2"></i>别',
					mode: 'local',
					triggerAction: 'all',
					valueField :'value',
					displayField: 'text',
					allowBlank : false,
					editable : false,
					store : genre,
					width: 150,
					value:'学科讲座'
				},{
					xtype :"numberfield",
					name:'capacity',
					fieldLabel:'容量(人)',
					allowBlank : false,
					maxLength :10,
					enableKeyEvents : true,
					width: 150,
					listeners : { 
    					keyup: function(src, evt){ 
	            			var val = src.getValue().toString().replace(/\D/g,'');
							src.setValue(val);
						}
    				}
				}]
			}]
		},{
			xtype:'textfield',
			name:'activityplace',
			fieldLabel:'活动地点',
			allowBlank : false,
			maxLength :40,
			width:394
		},{
			layout:'column',
			items:[{
				columnWidth:.5,
				layout:'form',
				/*defaults:{
					anchor : '95%'
				},*/
				items:[{
					id:'endTimeId',
					xtype : 'textfield',
					name:'activitytime',
					xtype:"datetimefield",
					fieldLabel:'活动时间',
					format:'Y-m-d H:i:s ',
					minValue : new Date(),
					allowBlank : false,
					editable : false,
					maxLength :20,
					width:150,
					vtype : 'daterange',//调用时间验证方法
					startDateField : 'startTimeId',//限制开始时间id

				},{
					id:'startTimeId',
					xtype : 'textfield',
					name:'signupendtime',
					xtype:"datetimefield",
					fieldLabel:'报名截止',
					format:'Y-m-d H:i:s',
					minValue : new Date(),
					allowBlank : false,
					editable : false,
					maxLength :20,
					width: 150,
					vtype : 'daterange',//调用时间验证方法
		            endDateField: 'endTimeId',//限制结束时间id
				}]
			},{
				columnWidth:.5,
				layout:'form',
				/*defaults:{
					anchor : '95%'
				},*/
				items:[{
					xtype:'combo',
					hiddenName:'timeofduration',
					fieldLabel:'时<i class="ikg2"></i>长',
					mode: 'local',
					triggerAction: 'all',
					valueField :'value',
					displayField: 'text',
					editable : false,
					store : timeofdur,
					width: 150,
				},{
					xtype : 'numberfield',
					name:'score',
					fieldLabel:'活动考评分',
					allowBlank : false,
					maxLength :11,
					enableKeyEvents : true,
					width: 150,
					listeners : { 
    					keyup: function(src, evt){ 
	            			var val = src.getValue().toString().replace(/\D/g,'');
							src.setValue(val);
						}
    				}
				}]
			}]
		},{
			xtype:'textarea',
			name:'activitycontent',
			fieldLabel:'活动内容',
			allowBlank : false,
			maxLength :150,
			width:394,
			height:83
		},{
			xtype:'hidden',
			name:'activityid'
		}]
	});
    
    //文本编辑器
    var ueditor = new Ueditor({
        name : 'content',
        anchor : '68%',
       	height : 800,
        fieldLabel : '正文',
        readOnly:true
	});
    
  //新闻稿窗口
    var newsForm = new Ext.FormPanel({
		layout : 'form',
		//baseCls:'x-plain',
		frame:true,
		url :'Applyactivity_submitNews.do',
		labelWidth:48,
		border : false,
		labelAlign:"right",
		padding : '15 10 0 0',
		defaults : {
			anchor : '100%',
			cls:'borderNone'
		},
		items:[{
			xtype : 'textfield',
			name:'newstitle',
			fieldLabel:'标题',
			anchor : '100%',
			readOnly:true
		},{
			xtype : 'textfield',
			name:'website',
			fieldLabel:'网址',
			anchor : '100%',
			readOnly:true
		},{
			layout:'column',
			items:[{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '100%',
					cls:'borderNone'
				},
				items:[{
					xtype : 'textfield',
					name:'newsdate',
					fieldLabel:'日期',
					readOnly:true
				}]
			},{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '100%',
					cls:'borderNone'
				},
				items:[{
					xtype : 'textfield',
					name:'writer',
					fieldLabel:'作者',
					readOnly:true
				}]
			}]
		},/*{
			xtype : 'radiogroup',
			name:'property',
			fieldLabel: "属性",
			items : [{
			  boxLabel: '校内',  
			  name: 'property',  
			  inputValue:'校内',
			  readOnly:true,
			},{
			  boxLabel: '校外',  
			  name: 'property',  
			  inputValue:'校外',
			  readOnly:true,
			  listeners:{
					change:function(rg,ck){
						if(!inputValue){
							inputValue=rg.getValue();
						}
						if(inputValue!=rg.getValue){
							inputValue = rg.serValue(inputValue);
						}
					}
				  }
			 }]  
		},*/{
			xtype : 'textfield',
			name:'property',
			fieldLabel:'属性',
			readOnly:true,
			//style:'border:0'
		},{
			xtype : 'htmleditor',
			name:'content',
			fieldLabel:'正文',
			readOnly:true,
			style:'border:0'
		}/*ueditor*/]
	});
    
    //添加form窗口
    var addWindow = new Ext.Window({
		title : '申报信息',
		width:520,
		height:440,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [addForm],
		buttons : [{
			text : '确<i class="ikg2"></i>定',
			handler : function() {
				if (addForm.getForm().isValid()) {
					var config = {
			            	//class:
			                title: "求是学院活动申报责任说明",
			                msg: "1 、确保所申报的活动信息内容合法且不与校规相违背。<br/>" +
			                	 "2、活动安全由主办或承办单位负责，请严格遵循“安全第一、预防为主”的方针。<br/>" +
			                	 "3、活动申报成功后请勿私下改动活动形式或内容等。<br/>" +
			                	 "4、活动开展不得影响学校正常的教学、科研、学习和生活秩序。",
			                //width: 350,
			                height:460,
			                multiline: false,//是否显示文本输入框
			                closable: false, //是否显示关闭按钮
			                buttons: Ext.Msg.OKCANCEL,
			                buttonText: {
			                    OK: "确定",
			                    Cancel: "取消"
			                },
			                icon: Ext.Msg.Info,
			                fn: function (btn) {
			            		if(btn=="ok"){
			            			addForm.getForm().submit({
			    						success : function(form, action) {
			    							Ext.Msg.alert('信息提示',action.result.message);
			    							addWindow.hide();
			    							store.reload();
			    						},
			    						failure : function(form, action) {
			    							if(action.result.errors){
			    								Ext.Msg.alert('信息提示',action.result.errors);
			    							}else{
			    								Ext.Msg.alert('信息提示','连接失败');
			    							}
			    						},
			    						waitTitle : '提交',
			    						waitMsg : '正在保存数据，稍后...'
			    					});
			            		}
			            	}
			            };
			            Ext.Msg.show(config);
				}
			}
		}]
	});
    
    //新闻稿添加form窗口
    var newsWindow = new Ext.Window({
		title : '新闻',
		width:640,
		height:500,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [newsForm],
	});
    //文本编辑器
    var ueditor = new Ueditor({
    	id:'contentid',
        allowBlank : true,
        name : 'content',
        anchor : '99%',
        height : 205,
        fieldLabel : '活动内容'
	});
    
    //布局
    new Ext.Viewport({
		layout:"fit",
		items:[{
			frame:true,
			title:"活动申报",
			iconCls:'menu-15',
			layout:"border",
			items:[searchForm,grid],
			listeners:{
				render:function(){
					getCurrentUserInfo();
					zzStore.load();
					szxqStore.load();
				}
			}
		}]
	});
});