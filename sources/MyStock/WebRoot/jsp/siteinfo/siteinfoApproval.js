function showImg(value){
	return "<img src='"+value+"' height='50'/>";
}

/*!
 * 场地信息申请管理列表
 */
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	
	var date = new Date();
	date.setMonth(date.getMonth()-3);
	
	 //查询所有所有 所属组织下拉菜单
    var orgaCombStore = new Ext.data.JsonStore({
		url: '/MyStock/SiteInfo_findOrgaComb.do',
	    root: 'root',
	    totalProperty: 'total',
	    fields: ['value','text'],
	    listeners:{
	    	load:function(s){
	    		r = s.getAt(0);
	    		if(r){
	    			Ext.getCmp("beorganize").onSelect(r, 0);
	    		}
	    	}
	    }
	});
    
    //查询所有所有园区下拉菜单  
    var parkStore = new Ext.data.JsonStore({
		url: '/MyStock/SiteInfo_findParkComb.do',
	    root: 'root',
	    totalProperty: 'total',
	    fields: ['value','text']
	});
    
    parkStore.on('load', function(store, record, options) {   
        store.insert(0, new Ext.data.Record({ 'value': '', 'text': '全部' }));   
    });
    
    //查询所有所有场地类型下拉菜单
    var groundTypeStore = new Ext.data.JsonStore({
		url: '/MyStock/SiteInfo_findGroundTypeComb.do',
	    root: 'root',
	    totalProperty: 'total',
	    fields: ['value','text']
	});
    
    groundTypeStore.on('load', function(store, record, options) {   
    	store.insert(0, new Ext.data.Record({'text': '全部', 'value': ''}));   
    }); 
	  
    
    
    
    
	
    //查询表单
    var searchForm = new Ext.FormPanel({
		region:'north',
		height: 100,
		border : false,
		layout : 'form',
		padding : '5 5 5 5',
		items:[{
			id:"jhdfieldset",
			xtype:"fieldset",
			title:"查询设置",
			padding:'5 5 5 5',
			items:[{
				layout:"column",
				defaults:{
					xtype:"container",
					autoEl:"div",
					labelAlign:'right',
					layout:"form"
				},
				items:[{
					width:210,
					items:[{
						labelWidth:50,
						xtype:"textfield",
						name:'sitename',
						fieldLabel:"场地名称",
						anchor:"90%"
					}]
				},{
					width:160,
					labelWidth:60,
					items:[{
							xtype:'combo',
							hiddenName:'sitetype',						
							fieldLabel:'场地类型',
							mode: 'local',
							triggerAction: 'all',
							valueField :'value',
							displayField: 'text',
							anchor:"90%",
							editable: false,
							store : groundTypeStore,
							//value:'---请选择---',
							emptyText: '全部'
					}]
				},{
					width:160,
					labelWidth:50,
					items:[{
							xtype:'combo',
							hiddenName:'park',
							id:'parkdo',
							fieldLabel:'园区',
							mode: 'local',
							triggerAction: 'all',
							valueField :'value',
							displayField: 'text',
							anchor:"90%",
							editable: false,
							store : parkStore,
							emptyText: '全部'
					}]
				},{
					width:48,
					style:'padding-top:5',
					xtype:'tbtext',
					//fieldLabel:'日期'
					text:'时间段:'
						
				},{
					width:130,
					xtype:"datetimefield",
					name:'startdate',
					id:'startdate',
					editable:false,
				    vtype : 'daterange',//daterange类型为上代码定义的类型
		            endDateField : 'enddate',//必须跟endDate的id名相同
					format:'Y-m-d H:i:s'
					
					//value:date
					
				},{
					width:25,
					style:'padding:5 0 0 5',
					xtype:'label',
					text:'至'
				},{
					width:130,
					xtype:"datetimefield",
					name:'enddate',
					id:'enddate',
					vtype : 'daterange',//daterange类型为上代码定义的类型
			        startDateField : 'startdate',//必须跟startDate的id名相同
			        editable:false,
					format:'Y-m-d H:i:s'
					//value:new Date()
				},{
					width:120,
					items:[{
						width:75,
						xtype:"button",
						style:'margin-left:30px',
						text:'查询',
						handler:function(){
							var f = searchForm.getForm();
							if (f.isValid()) {
								var _params = f.getValues();
								//添加分页信息
								Ext.apply(_params,{start:0, limit:15});
								store.load({params:_params});
							}
						}
					}]
				},{
					width:130,
					items:[{
						width:100,
						style:'margin-right:5px',
						xtype:"button",
						text:'我申请的场地',
						listeners : {
							  click : function (btn) {
						       window.location.href = 'siteinfo.jsp';
							}
				        		
				        }
					}]
				},{
					width:100,
					items:[{
						width:75,
						xtype:"button",
						cls :'returnBtn',
						text:"返回",
						 listeners : {
							  click : function (btn) {
				        	     window.location.href = '../menu/personal-affairs.html';
							}
				        		
				        }
					}]
				
				}]
			}]
		}]
	});
	
   
   /*  //查询表单
	var searchForm = new Ext.FormPanel({
		region:"north",
		height: 100,
		border : false,
		layout : "form",
		padding : "5 20 0 20",
		items:[{
			xtype:"fieldset",
			title:"编辑设置",
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
					width:260,
					items:[{
								width:130,
								labelWidth:60,
								xtype:"textfield",
								name:'sitename',
								fieldLabel:"场地名称"
							},{
								width:130,
								labelWidth:60,
								xtype:"datetimefield",
								name:'startdate',
								id:'startdate',
								editable:false,
								fieldLabel:"时间段",
							    vtype : 'daterange',//daterange类型为上代码定义的类型
					            endDateField : 'enddate',//必须跟endDate的id名相同
								format:'Y-m-d H:i:s'
								}]
						},{
							width:260,
							items:[{
								width:130,
								labelWidth:60,
								xtype:'combo',
								hiddenName:'sitetype',						
								fieldLabel:'场地类型',
								mode: 'local',
								triggerAction: 'all',
								valueField :'value',
								displayField: 'text',
								editable: false,
								store : groundTypeStore,
								//value:'---请选择---',
								emptyText: '全部'
							},{
								width:130,
								padding:"5 0 5 0",
								xtype:"datetimefield",
								name:'enddate',
								id:'enddate',
								labelSeparator:'',
								fieldLabel:"至",
								vtype : 'daterange',//daterange类型为上代码定义的类型
						        startDateField : 'startdate',//必须跟startDate的id名相同
						        editable:false,
								format:'Y-m-d H:i:s'
								}
							]
								
						},{
						  width:260,
						   items:[{
								width:130,
								labelWidth:60,
								xtype:'combo',
								hiddenName:'park',
								id:'parkdo',
								fieldLabel:'园区',
								mode: 'local',
								triggerAction: 'all',
								valueField :'value',
								displayField: 'text',
								editable: false,
								store : parkStore,
								emptyText: '全部'
						},{
							width:130,
							items:[{
								width:75,
								xtype:"button",
								style:'margin-left:30px',
								text:'查询',
								handler:function(){
									var f = searchForm.getForm();
									if (f.isValid()) {
										var _params = f.getValues();
										//添加分页信息
										Ext.apply(_params,{start:0, limit:15});
										store.load({params:_params});
									}
								}
							}]
						}]
				  },{
					  width:260,
					   items:[{
							width:75,
							xtype:"button",
							cls :'returnBtn',
							text:"返回",
							handler:function(){
						         window.location.href = '../menu/personal-affairs.html';
						   }
						},{
							width:150,
							items:[{
								width:100,
								style:'margin-left:30px',
								xtype:"button",
								text:'我申请的场地',
								listeners : {
									  click : function (btn) {
								       window.location.href = 'siteinfo.jsp';
									}
						        		
						        }
							}]
						}]
						
				}]
			}]
		}]
	});*/
	
	
	
	var SiteInfoObj = [
			           { name:'id', type:'int'},
			           { name:'proposer', type:'string'},
			           { name:'siteid', type:'string'},
			           { name:'relationtel', type:'string'},
			           { name:'counsellor', type:'string'},
			           { name:'activityname', type:'string'},
			           { name:'activitycontent', type:'string'},
			           { name:'activitydate', type:'String' },
			           { name:'activitytime', type:'String' },
			           { name:'activitytype', type:'string'},
			           { name:'sitename', type:'string'},
			           { name:'sitetype', type:'string'},
			           { name:'sitemodle', type:'int'},
			           { name:'beorganize', type:'string'},
			           { name:'guideth', type:'string'},
			           { name:'sitemodle', type:'int'},
			           { name:'park', type:'string'},
			           { name:'xh', type:'string'},
			           { name:'sitemanager', type:'string'},
			           { name:'nowstatus', type:'string'},
			           { name:'photo', type:'string'},
			           { name:'sitecondition', type:'string'},
			           { name:'usetime', type:'int'},
			           { name:'ifschool', type:'string'},
			           { name:'agreement', type:'string'},
			           { name:'dutystate', type:'string'},
			           { name:'prestorestatus', type:'string'},
			           { name:'status', type:'string'},
			           { name:'createtime', type:'string'},
		    	       { name:'createrid', type:'int'},
		    	       { name:'creatername', type:'string'}
			           		
			 	];
	           	
	 //获取时间
	 var startdate = Ext.getCmp("startdate").getValue();
	 var enddate = Ext.getCmp("enddate").getValue();
	  
	  var store = new Ext.data.JsonStore({
	          url: '/MyStock/SiteInfo_findPageApplySiteInfo.do',
	          root: 'root',
	          totalProperty: 'total',
	          autoLoad: {params:{start:0, limit:15,startdate:startdate,enddate:enddate}},
	           fields: SiteInfoObj
	      });
	           	
	//sm,：选择框
	var sm = new Ext.grid.CheckboxSelectionModel({singleSelect: true});//单选
     var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[new Ext.grid.RowNumberer(),
			         {header: '场地',  width: 250, align:'center', dataIndex: 'sitename'},
			         {header: '场地使用情况',  width: 200, align:'center', dataIndex: 'nowstatus',renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
			               if(value == '查看预约情况') { 
		                        return "<span style='color:blue;font-weight:bold;'>查看预约情况</span>"
		                   }     
		               },
			          listeners :{
			            click:function(value){
			        	   useStatus(); 
			            }
			         }},
			         {header: '会场条件',  width: 200, align:'center', dataIndex: 'sitecondition',renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
			        	 var record = grid.getStore().getAt(rowIndex); 
			             var sitemodle = record.data.sitemodle;
			             if (value.indexOf('投影仪') !=-1 && value.indexOf('电脑') !=-1 &&
		                		   value.indexOf('话筒') !=-1 && value.indexOf('空调') !=-1  ){
		                	   var Img = "";
			            	   Img+="<img src='../../img/site/size.png' height='20'/>";
			            	   Img += sitemodle;
			            	   Img += " ";
			            	   Img+="<img src='../../img/site/projection.png' height='20'/>";
			            	   Img += " ";
			            	   Img+="<img src='../../img/site/Computer.png' height='20'/>";
			            	   Img += " ";
			            	   Img+="<img src='../../img/site/Microphone.png' height='20'/>";
			            	   Img += " ";
			            	   Img+="<img src='../../img/site/kongtiao.png' height='20'/>";
			            	   return Img;
		                	   
		                   }else if (value.indexOf('投影仪') !=-1 && value.indexOf('电脑') !=-1 &&
		                		   value.indexOf('话筒') !=-1  ){
		                	   var Img = "";
			            	   Img+="<img src='../../img/site/size.png' height='20'/>";
			            	   Img += sitemodle;
			            	   Img += " ";
			            	   Img+="<img src='../../img/site/projection.png' height='20'/>";
			            	   Img += " ";
			            	   Img+="<img src='../../img/site/Computer.png' height='20'/>";
			            	   Img += " ";
			            	   Img+="<img src='../../img/site/Microphone.png' height='20'/>";
			            	   return Img;
		                	   
		                   }else if (value.indexOf('投影仪') !=-1 && value.indexOf('电脑') !=-1 &&
		                		   value.indexOf('空调') !=-1 ){
		                	   var Img = "";
		                	   Img+="<img src='../../img/site/size.png' height='20'/>";
			            	   Img += sitemodle;
			            	   Img += " ";
		            	       Img+="<img src='../../img/site/projection.png' height='20'/>";
		            	       Img += " ";
		            	       Img+="<img src='../../img/site/Computer.png' height='20'/>";
		            	       Img += " ";
			            	   Img+="<img src='../../img/site/kongtiao.png' height='20'/>";
		            	       return Img;
		                	   
		                   }else if (value.indexOf('投影仪') !=-1 && value.indexOf('话筒') !=-1 &&
		                		   value.indexOf('空调') !=-1 ){
		                	   var Img = "";
		                	   Img+="<img src='../../img/site/size.png' height='20'/>";
			            	   Img += sitemodle;
			            	   Img += " ";
		            	       Img+="<img src='../../img/site/projection.png' height='20'/>";
		            	       Img += " ";
			            	   Img+="<img src='../../img/site/Microphone.png' height='20'/>";
		            	       Img += " ";
			            	   Img+="<img src='../../img/site/kongtiao.png' height='20'/>";
		            	       return Img;
		                	   
		                   }else if (value.indexOf('投影仪') !=-1  && value.indexOf('电脑') !=-1 ){
		                	   var Img = "";
		                	   Img+="<img src='../../img/site/size.png' height='20'/>";
			            	   Img += sitemodle;
			            	   Img += " ";
		            	       Img+="<img src='../../img/site/projection.png' height='20'/>";
		            	       Img += " ";
		            	       Img+="<img src='../../img/site/Computer.png' height='20'/>";
		            	       return Img;
		                	   
		                   }else if (value.indexOf('投影仪') !=-1  && value.indexOf('话筒') !=-1 ){
		                	   var Img = "";
		                	   Img+="<img src='../../img/site/size.png' height='20'/>";
			            	   Img += sitemodle;
			            	   Img += " ";
		            	       Img+="<img src='../../img/site/projection.png' height='20'/>";
		            	       Img += " ";
			            	   Img+="<img src='../../img/site/Microphone.png' height='20'/>";
		            	       return Img;
		                	   
		                   }else if (value.indexOf('投影仪') !=-1  && value.indexOf('空调') !=-1 ){
		                	   var Img = "";
		                	   Img+="<img src='../../img/site/size.png' height='20'/>";
			            	   Img += sitemodle;
			            	   Img += " ";
		            	       Img+="<img src='../../img/site/projection.png' height='20'/>";
		            	       Img += " ";
			            	   Img+="<img src='../../img/site/kongtiao.png' height='20'/>";
		            	       return Img;
		                	   
		                   }else if ( value.indexOf('电脑') !=-1 && value.indexOf('话筒') !=-1  &&
		                		   value.indexOf('空调') !=-1 ){
		                	   var Img = "";
		                	   Img+="<img src='../../img/site/size.png' height='20'/>";
			            	   Img += sitemodle;
		            	       Img += " ";
		            	       Img+="<img src='../../img/site/Computer.png' height='20'/>";
		            	       Img += " ";
			            	   Img+="<img src='../../img/site/Microphone.png' height='20'/>";
			            	   Img += " ";
			            	   Img+="<img src='../../img/site/kongtiao.png' height='20'/>";
		            	       return Img;
		                	   
		                   }else if ( value.indexOf('电脑') !=-1 && value.indexOf('空调') !=-1 ){
		                	   var Img = "";
		                	   Img+="<img src='../../img/site/size.png' height='20'/>";
			            	   Img += sitemodle;
		            	       Img += " ";
		            	       Img+="<img src='../../img/site/Computer.png' height='20'/>";
			            	   Img += " ";
			            	   Img+="<img src='../../img/site/kongtiao.png' height='20'/>";
		            	       return Img;
		                	   
		                   }else if (value.indexOf('话筒') !=-1  &&
		                		   value.indexOf('空调') !=-1 ){
		                	   var Img = "";
		                	   Img+="<img src='../../img/site/size.png' height='20'/>";
			            	   Img += sitemodle;
		            	       Img += " ";
			            	   Img+="<img src='../../img/site/Microphone.png' height='20'/>";
			            	   Img += " ";
			            	   Img+="<img src='../../img/site/kongtiao.png' height='20'/>";
		            	       return Img;
		                	   
		                   } else if (value.indexOf('投影仪') !=-1 ){
		                	   var Img = "";
			            	   Img+="<img src='../../img/site/size.png' height='20'/>";
			            	   Img += sitemodle;
			            	   Img += " ";
			            	   Img+="<img src='../../img/site/projection.png' height='20'/>";
			            	   return Img;
		                	   
		                   }else if (value.indexOf('电脑') !=-1 ){
		                	   var Img = "";
			            	   Img+="<img src='../../img/site/size.png' height='20'/>";
			            	   Img += sitemodle;
			            	   Img += " ";
		            	       Img+="<img src='../../img/site/Computer.png' height='20'/>";
		            	       return Img;
		                	   
		                   }else if (value.indexOf('话筒') !=-1 ){
		                	   var Img = "";
			            	   Img+="<img src='../../img/site/size.png' height='20'/>";
			            	   Img += sitemodle;
			            	   Img += " ";
			            	   Img+="<img src='../../img/site/Microphone.png' height='20'/>";
			            	   return Img;
		                	   
		                   }else{
		                	   var Img = "";
			            	   Img+="<img src='../../img/site/size.png' height='20'/>";
			            	   Img += sitemodle;
			            	   Img += " ";
			            	   Img+="<img src='../../img/site/kongtiao.png' height='20'/>";
		            	       return Img;
		                   }
		                 }
			           },
			         {header: '图片', width: 200,align:'center', dataIndex: 'photo',renderer:showImg},
			         {header: '类别',  width: 150, align:'center', dataIndex: 'sitetype'},
			         {header: '场地管理老师',width: 150, align:'center', dataIndex: 'counsellor'},
			         {xtype: 'actioncolumn',header : '操作', width: 200,align : 'center',
			              menuDisabled : true,
			              items: [ {
			                    icon: '../../img/btn/make.png',
			                    tooltip: '预约',
			                    getClass: function(value, metaData, record, rowIndex, colIndex, store, view) {      
			            	  
			                	},
			                    handler: function(grid, rowIndex, colIndex){
			                		makeModle(grid, rowIndex, colIndex);
			                	}
			                }]
			           }  
			]
        }),
        sm:sm,//checkbox
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
        //autoExpandColumn: 'gysbz', //自动扩展列
		loadMask : true,	//加载时的遮罩
		frame : true,
		region:"center",
        iconCls:'menu-12',
       /* tbar:['->',{
        	text:'我申请的场地',
        	iconCls:'btn-list',
        	handler: function(){
        	      window.location.href = 'siteinfo.jsp';   
        	}
        }],*/
        listeners:{  
        	'cellclick':function(grid,rowIndex,columnIndex,e){ 
    	 
    	         viewDo(grid, rowIndex);
    	      
		    /*	var record = grid.getStore().getAt(rowIndex); //Get the Record  图片预览 
		        var fieldName = grid.getColumnModel().getDataIndex(columnIndex);
		    	if('photo'==fieldName){
		    		var data = record.get(fieldName); 
		    		imgWindow.show();
		    		Ext.getCmp("imgBox").getEl().dom.src = data;
		    		
		    		
		    	}*/
    		}
          
        },
        bbar: new Ext.PagingToolbar({
            pageSize: 15,
            store: store,
            displayInfo: true
        }),
        layout : 'fit',
        viewConfig:{
        	forceFit: true, 
        	scrollOffset: 0
       	},
    });

     
     
     var viewPanel = new Ext.Panel({
   	       id : 'viewPanel',
  	       layout : 'fit',
  	       html : '',
  	       frame : true
      });
   
      var viewWindow = new Ext.Window({
      	title:'图片预览',
  		width:1000,
  		height:600,
  		closeAction:'hide',
  		modal : true,
  		layout : 'fit',
  		buttonAlign : 'center',
  		items : [viewPanel]
  		
  	});
     
     var viewDo = function (grid, rowIndex){
    		var record = grid.getStore().getAt(rowIndex); 
    	   	var id = record.data.id;
    	    var html = '<iframe src="/MyStock/SiteInfo_viewImg.do?id='+id+'" frameborder="0" width="100%"  height="100%"></iframe>';
			if(Ext.getCmp('viewPanel').body){
				Ext.getCmp('viewPanel').body.update(html);
			}else{
				Ext.getCmp('viewPanel').html = html;
			}
			viewWindow.show();
     }
     
     
     
     //空闲状态触发弹出场地使用情况
    var useStatus  = function (){
    	var record= grid.getSelectionModel().getSelected();
    	var siteid = record.get("siteid");
        var myPanel = new Ext.Panel({
		       layout : 'fit',
		       html : '<iframe src="../../extensible-1.0.2/examples/calendar/extensible-config-preview.jsp?siteid='+siteid+'" frameborder="0" width="100%"  height="100%" ></iframe>',
		       frame : true
         });
    	  
	   //时间选择弹出框
	   var useWindow = new Ext.Window({
	   	  title:'场地使用情况',
	   	  width : 840,
          height :550,
          resizable : false,
          closable : true,
          draggable : true,
          resizable : false,
          layout : 'fit',
          modal : false,
          plain : true, // 表示为渲染window body的背景为透明的背景
          bodyStyle : 'padding:5px;',
          items : [myPanel],
          buttonAlign : 'center',
		 
	  });
	   viewWindow.hide();
	   useWindow.show();
    	
    }
     
    
     //根据登录者获取登录的数据
 	var getModle = function(){
 		Ext.Ajax.request({
    			url : "/MyStock/LeaveInfo_goAddOrUpateLeaveInfo.do",
    		    success : function(o) {
 				  var data = Ext.util.JSON.decode(o.responseText);
    				if(o.responseText){
    					 Ext.getCmp("proposer").setValue(data.data.xm);
    					 Ext.getCmp("xh").setValue(data.data.xh);
    				     Ext.getCmp("relationtel").setValue(data.data.phone);
    				     Ext.getCmp("counsellor").setValue(data.data.instructor);
    				}
    			}
    		});
 	    };
     
 	 //拼接协议
     var talk = '';
         talk +='求是学院活动场地申请协议:\r\n';
         talk +='1.2016-2017学年秋学期开始，求是学院师生可以通过网络办理活动场地借用手续。\r\n';
         talk +='2.活动场地借用范围仅限于学生班会；学生组织例会；党、团组织培训；非盈利性学\r\n';
         talk +='3.活动场地使用请遵照宿舍管理相关规定。\r\n';
         talk +='4.用实名申请活动场地借用，以“浙大通行证”用户名和密码登录。所填手机号码和电子\r\n';
         talk +='信箱务必正确无误，否则无法获得短信与邮件提示。系统管理人员在24小时内对申请\r\n';
         talk +='进行处理，请及时查看审批结果。\r\n';
         talk +='5. "借用事由"必须详细、真实。如果发现申请用途与实际不符、或者违规使用活动场\r\n';
         talk +='地，申请人将受到严肃处理。\r\n';
         talk +='6.每天可供借用的活动场地数量有限定。如系统提示当天可供借用场地已用完，请调整\r\n';
         talk +='活动时间。\r\n';
         talk +='7.寒暑假期间不能借用活动场地。';
          
    
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
			layout:'column',
			items:[{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype : 'textfield',
					name:'sitename',
					id:'sitename',
					fieldLabel:'申请场地',
					cls :'x-textfield',
					readOnly:true,
					maxLength :20
				}]
			},{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype : 'textfield',
					name:'sitecondition',
					id:'sitecondition',
					readOnly:true,
					fieldLabel:'会议室',
					cls :'x-textfield',
					maxLength :225
				}]
			}]
		},{
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
			   var id = Ext.getCmp("id").getValue();
		       var sitecondition = Ext.getCmp("sitecondition").getValue();
		       var sitename = Ext.getCmp("sitename").getValue();
		       var agreement = Ext.getCmp("agreement").getValue();
		       if(false == agreement){
		    	   alert("请先阅读场地使用协议！");
		    	   return false
		    	}
		       
		       Ext.getCmp("id").setValue(id);
		       Ext.getCmp("sitename1").setValue(sitename);
		       Ext.getCmp("sitecondition1").setValue(sitecondition);
		       Ext.getCmp("agreement1").setValue(agreement);
		      
				 agreementWindow.hide();
				 //时间选择
				 updateStatusHandler2();
					 
					
			}
		}]
	});
    
    
    //时间选择Form
    var timeForm = new Ext.FormPanel({
		layout : 'form',
		frame:true,
		labelWidth:60,
		border : false,
		padding : '10 10 0 8',
		defaults : {
			anchor : '100%'
		},
		items:[{
			layout:'column',
			items:[{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype : 'hidden',
					name:'sitename',
					id:'sitename1',
					fieldLabel:'申请场地',
					allowBlank : false,
					maxLength :20
				}]
			},{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype : 'hidden',
					name:'sitecondition',
					id:'sitecondition1',
					fieldLabel:'会议室',
					//renderer:showImg,
					maxLength :225
				}]
			}]
		},{
			xtype : 'hidden',
			hiddenName:'activitydate',
			//id:'activitydate1',
			fieldLabel:'活动日期',
			maxLength :225
		},{
			xtype : 'hidden',
			hiddenName:'activitytime',
			//id:'activitytime1',
			fieldLabel:'活动时间',
			maxLength :225
		},{
			xtype : 'hidden',
		    name : 'id',
		    id:'id'
		    
		},{
			xtype : 'hidden',
		    name : 'agreement',
		    id:'agreement1'
		    
		},{
			xtype : 'hidden',
		    name : 'rowIndex1',
		    id : "rowIndex2"
		}]
	});
    
    
    //时间弹出框
    var updateStatusHandler2 = function() {
    	 //获取行号
	     var rowIndex2 = Ext.getCmp("rowIndex1").getValue();
	     //获取数据
		 var record = grid.getStore().data.items[rowIndex2];
		 var siteid = record.data.siteid;
	     var myPanel = new Ext.Panel({
			       layout : 'fit',
			       html : '<iframe src="../../extensible-1.0.2/examples/calendar/test-app.jsp?siteid='+siteid+'" frameborder="0" width="100%"  height="100%"></iframe>',
			       frame : true
	         });
    	   //时间选择弹出框
    	   var timeWindow = new Ext.Window({
    	   	  title:'时间选择',
    	   	  width : 840,
              height :550,
              resizable : false,
              closable : true,
              draggable : true,
              resizable : false,
              layout : 'fit',
              modal : false,
              plain : true, // 表示为渲染window body的背景为透明的背景
              bodyStyle : 'padding:5px;',
              items : [myPanel],
              buttonAlign : 'center',
    		  buttons : [{
    				text : '上一步',
    				handler : function() {
    			       timeWindow.hide();   
    				  agreementWindow.show();
    				}
    			},{
    			text : '下一步',
    			handler : function() { 
    			   var id = Ext.getCmp("id").getValue();
    		       var sitecondition = Ext.getCmp("sitecondition1").getValue();
    		       var sitename = Ext.getCmp("sitename1").getValue();
    		       var agreement = Ext.getCmp("agreement1").getValue();
    		       
    		       //test-app.js获取的值
    		       var activitydate = document.getElementById('activitydate1').value;
    		       //test-app.js获取的值
    		       var activitytime = document.getElementById('activitytime1').value;
    		       //判断时间是否为空
    		       debugger;
    		       if(null ==activitydate ||"" == activitydate || 
    		    		   "" == activitytime ||  null == activitytime){
    		    	   alert("活动时间为空！请选择时间");
    		    	   return false;
    		       }
    			 
    		      //获取时间点击下一步获取之前得到数据
    		       Ext.getCmp("id").setValue(id);
    		       Ext.getCmp("sitename11").setValue(sitename);
    		       Ext.getCmp("sitecondition11").setValue(sitecondition);
    		       Ext.getCmp("activitydate").setValue(activitydate);
    		       Ext.getCmp("activitytime").setValue(activitytime);
    		       Ext.getCmp("rowIndex3").setValue(rowIndex2);
    		       Ext.getCmp("agreement2").setValue(agreement);
  	   			 
  	   			  //隐藏本身
  	   			  timeWindow.hide();
  	   			  //显示下一步
  				  addWindow.show();
    			   }
    	  	   }]
    	   });
    	   
    	timeWindow.show();
    };
   
    
    
    
    //填写申请表
    var addForm = new Ext.FormPanel({
		layout : 'form',
		frame:true,
		labelWidth:60,
		border : false,
		padding : '10 10 0 8',
		defaults : {
			anchor : '100%'
		},
		items:[{
			layout:'column',
			items:[{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype : 'textfield',
					name:'sitename',
					id:'sitename11',
					fieldLabel:'申请场地',
					readOnly:true,
					maxLength :20
				},{
					xtype : 'textfield',
					name:'proposer',
					id:'proposer',
					fieldLabel:'申<i class="ikg1"></i>请<i class="ikg1"></i>人',
					readOnly:true,
					maxLength :20
				},{
					xtype : 'textfield',
					id:'relationtel',
					name:'relationtel',
					fieldLabel:'联系电话',
					regex:/^(13\d|15[^4,\D]|17[13678]|18\d)\d{8}|170[^346,\D]\d{7}$/,
					maxLength :20
				}]
			},{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype : 'textfield',
					name:'sitecondition',
					id:'sitecondition11',
					readOnly:true,
					fieldLabel:'会<i class="ikg1"></i>议<i class="ikg1"></i>室',
					maxLength :225
				},{
					xtype : 'textfield',
					name:'xh',
					id:'xh',
					fieldLabel:'学<i class="ikg2"></i>号',
					readOnly:true,
					allowBlank : false,
					maxLength :10
				},{
					xtype : 'textfield',
					name:'counsellor',
					id:'counsellor',
					readOnly:true,
					fieldLabel:'辅<i class="ikg1"></i>导<i class="ikg1"></i>员',
					maxLength :18
				}]
			}]
		},{
			xtype:'textfield',
			name:'activityname',
			id:'activityname',
			fieldLabel:'活动名称',
			allowBlank : false,
			height:20,
			anchor: '97.5%',
			maxLength :200
		},{
			xtype:'textarea',
			name:'activitycontent',
			id:'activitycontent',
			fieldLabel:'活动内容',
			allowBlank : false,
			emptyText: '请输入活动内容！活动内容在200个字符之内。',
			height:100,
			anchor: '97.5%',
			maxLength :200
		},{
			layout:'column',
			items:[{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype : 'textfield',
					name:'activitydate',
					id:'activitydate',
					fieldLabel:'活动日期',
					readOnly:true,
					//value:'2016-06-07',
					maxLength :48
				},{  
					id : 'activitytype', 
				    xtype : 'combo',  
				    hiddenName:'activitytype',
				    fieldLabel : '活动类型', 
				    editable : false,
				    store :[['学科讲座','学科讲座'],['心里健康','心里健康'],['生涯规划','生涯规划'],['党团建设','党团建设'],['文体活动','文体活动'],['其他','其他']],  
				    width:100,  
				    triggerAction: "all",  
				    mode: "local",  
				    selectOnFocus : true,  
                    value:'科学讲座',//初始时指定  
				    allowBlank:false
					
				},{
					xtype : 'textfield',
					name:'guideth',
					id:'guideth',
					fieldLabel:'指导老师',
					allowBlank : false,
					maxLength :20
				},{  
					
		            xtype:'radiogroup',    
		            fieldLabel:'是否是外校人员参加',
		            id:'ifschool1',
		        	name:'ifschool',
		            columns:2,//2列    
		            items:[    
		                 {boxLabel:'是',name:'ifschool',inputValue: '是' },    
		                 {boxLabel:'否',name:'ifschool',inputValue: '否', checked: true},    
		            ]    
		        }]
			},{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype : 'textfield',
					name:'activitytime',
					id:'activitytime',
					readOnly:true,
					fieldLabel:'活动时间',
					//value:'8:00-9:00',
					maxLength :48
				},{
					xtype : 'textfield',
					name:'beorganize',
					id:'beorganize',
					fieldLabel:'所在组织',
					//mode: 'local',
					//triggerAction: 'all',
					//valueField :'value',
					//displayField: 'text',
					allowBlank : true
					//editable : false,
					//store : orgaCombStore,
					//value:'---请选择---',
					//emptyText: '全部',
					//listeners:{
					//	select : function(a,b){
					//    addForm.getForm().findField("beorganize").setValue(b.data.text);
					//	}
					//}
				},{
					xtype : 'textfield',
					name:'sitemodle',
					id:'sitemodle',
					fieldLabel:'规模(人)',
					allowBlank : false,
					maxLength :11,
					enableKeyEvents:true,
					listeners : { 
					   keyup: function(src, evt){ 
            			var val = src.getValue().toString().replace(/\D/g,'');
						src.setValue(val);
					}
				}
				}]
			}]
		},{
			
			xtype : 'hidden',
			id:'id',
		    name : 'id'
		},{
			id:'agreement2',
			xtype : 'hidden',
		    name : 'agreement'
		},{
			xtype : 'hidden',
		    name : 'rowIndex1',
		    id : "rowIndex3"
		}]
	});
    
    
    
    
    //责任声明
    var dutystate = '';
        dutystate +='活动场地申请责任说明:\r\n';
        dutystate +='请同学严格按照协议内容使用场地，违规使用活动场地，申请\r\n';
        dutystate +='人将受到严肃处理。\r\n';
        
    
    
   
    //填寫申請表
    var addWindow = new Ext.Window({
    	title:'填写申请表',
    	width:710,
		height:440,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [addForm],
		buttons : [{
			text : '上一步',
			handler : function() {
			  addWindow.hide();
			  updateStatusHandler2();
			}
		},{
			text : '下一步',
			handler : function() {
			   //根据id获取值
			   var id = Ext.getCmp("id").getValue();
		       var sitecondition = Ext.getCmp("sitecondition").getValue();
		       var sitename = Ext.getCmp("sitename").getValue();
		       var xh = Ext.getCmp("xh").getValue();
		       var proposer = Ext.getCmp("proposer").getValue();
		       var relationtel = Ext.getCmp("relationtel").getValue();
		       var counsellor = Ext.getCmp("counsellor").getValue();
		       var activityname = Ext.getCmp("activityname").getValue();
		       var activitycontent = Ext.getCmp("activitycontent").getValue();
		       var activitydate = Ext.getCmp("activitydate").getValue();
		       var activitytype = Ext.getCmp("activitytype").getValue();
		       var guideth = Ext.getCmp("guideth").getValue();
		       var activitytime = Ext.getCmp("activitytime").getValue();
		       var beorganize = Ext.getCmp("beorganize").getValue();
		       var sitemodle = Ext.getCmp("sitemodle").getValue();
		       var agreement = Ext.getCmp("agreement1").getValue();
		       var ifschool = Ext.getCmp("ifschool1").getValue();
		       var str = '';
		       Ext.each(ifschool,function(item){
		    	   str = item.inputValue
		    	  
		       });
		     
		       if(null == activityname ||activityname == ""){
		    	   alert("活动名称为空！请输入");
		    	   return false;
		       }
		       if(null == activitycontent ||activitycontent == ""){
		    	   alert("活动内容为空！请输入");
		    	   return false;
		       }
		       if(null == guideth ||guideth == ""){
		    	   alert("指导老师为空！请输入");
		    	   return false;
		       }
		       if(null == sitemodle ||sitemodle == ""){
		    	   alert("规模为空！请输入");
		    	   return false;
		       }
		       
		         //给跳转页面赋值
		         Ext.getCmp("ids").setValue(id);
		         Ext.getCmp("sitenames").setValue(sitename);
		         Ext.getCmp("siteconditions").setValue(sitecondition);
		         Ext.getCmp("xhs").setValue(xh);
		         Ext.getCmp("proposers").setValue(proposer);
		         Ext.getCmp("relationtels").setValue(relationtel);
		         Ext.getCmp("counsellors").setValue(counsellor);
		         Ext.getCmp("activitynames").setValue(activityname);
		         Ext.getCmp("activitycontents").setValue(activitycontent);
		         Ext.getCmp("activitydates").setValue(activitydate);
		         Ext.getCmp("activitytype1").setValue(activitytype);
		         Ext.getCmp("guideths").setValue(guideth);
		         Ext.getCmp("ifschool2").setValue(str);
		         Ext.getCmp("activitytimes").setValue(activitytime);
		         Ext.getCmp("beorganizes").setValue(beorganize);
		         Ext.getCmp("sitemodles").setValue(sitemodle);
		         Ext.getCmp("agreements").setValue(agreement);
		         Ext.getCmp("dutystate").setValue(dutystate);
		         addWindow.hide();
			    makeWindow.show();
			   }
		}]
	});
    
    
   //确认信息提交
    var  makeForm = new Ext.FormPanel({
		layout : 'form',
		frame:true,
		labelWidth:60,
		border : false,
		padding : '10 10 0 5',
		defaults : {
			anchor : '100%'
		},
		items:[{
			layout:'column',
			items:[{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype : 'textfield',
					name:'sitename',
					id:'sitenames',
					fieldLabel:'申请场地',
					cls :'x-textfield',
					readOnly:true,
					maxLength :20
				},{
					xtype : 'textfield',
					name:'proposer',
					id:'proposers',
					fieldLabel:'申<i class="ikg1"></i>请<i class="ikg1"></i>人',
					cls :'x-textfield',
					readOnly:true,
					maxLength :20
				},{
					xtype : 'textfield',
					name:'relationtel',
					id:'relationtels',
					fieldLabel:'联系电话',
					regex:/^(13\d|15[^4,\D]|17[13678]|18\d)\d{8}|170[^346,\D]\d{7}$/,
					cls :'x-textfield',
					readOnly:true,
					maxLength :20
					
				},{
					xtype:'textfield',
					name:'activityname',
					id:'activitynames',
					fieldLabel:'活动名称',
					readOnly:true,
					cls :'x-textfield',
					anchor: '97.5%',
					height:20,
					maxLength :50
				
				}]
			},{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype : 'textfield',
					name:'sitecondition',
					id:'siteconditions',
					fieldLabel:'会<i class="ikg1"></i>议<i class="ikg1"></i>室',
					readOnly:true,
					cls :'x-textfield',
					maxLength :225
				},{
					xtype : 'textfield',
					name:'xh',
					id:'xhs',
					fieldLabel:'学<i class="ikg2"></i>号',
					readOnly:true,
					cls :'x-textfield',
					maxLength :10
				},{
					xtype : 'textfield',
					name:'counsellor',
					id:'counsellors',
					fieldLabel:'辅<i class="ikg1"></i>导<i class="ikg1"></i>员',
					readOnly:true,
					cls :'x-textfield',
					maxLength :18
				},{

					xtype : 'textfield',
					name  :'activitytype',
					id : 'activitytype1',  
					fieldLabel:'活动类型',
					cls :'x-textfield',
					readOnly:true,
					maxLength :20
				
				}]
			}]
		},{
			xtype:'textarea',
			name:'activitycontent',
			id:'activitycontents',
			anchor: '97.5%',
			fieldLabel:'活动内容',
			readOnly:true,
			style:'border:0', 
			height:50,
			maxLength :200
		},{
			layout:'column',
			items:[{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype : 'textfield',
					name:'activitydate',
					id:'activitydates',
					fieldLabel:'活动日期',
					readOnly:true,
					cls :'x-textfield',
					maxLength :48
				},{
					xtype : 'textfield',
					name:'guideth',
					id : 'guideths',
					fieldLabel:'指导老师',
					readOnly:true,
					cls :'x-textfield',
					maxLength :20
				},{xtype : 'textfield',
					name:'sitemodle',
					id : 'sitemodles',
					fieldLabel:'规模(人)',
					readOnly:true,
					cls :'x-textfield',
					maxLength :11
				}]
			},{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype : 'textfield',
					id : 'activitytimes',
					name:'activitytime',
					fieldLabel:'活动时间',
					readOnly:true,
					cls :'x-textfield',
					maxLength :48
				},{xtype : 'textfield',
					name:'beorganize',
					id : 'beorganizes',
					fieldLabel:'所在组织',
					cls :'x-textfield',
					readOnly:true,
					maxLength :11
				},{
		            xtype:'textfield',    
		            fieldLabel:'是否是外校人员参加',
		        	name:'ifschool',
		        	id:'ifschool2',
		        	cls :'x-textfield',
		        	readOnly:true
				}]
			}]
		},{
			xtype:'textfield',
			name:'dutystate',
			id:'dutystate',
			fieldLabel:'责任声明',
			cls :'x-textfield',
			readOnly:true,
			height:20
		},{
			
			xtype : 'hidden',
			id:'ids',
		    name : 'id'
		},{
			xtype : 'hidden',
			id:'agreements',
		    name : 'agreement'
		}]
	});
    
    
    
    //確認申請場地資料彈出框
    var  makeWindow = new Ext.Window({
    	title:'提交信息',
    	width:700,
		height:400,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [makeForm],
		buttons : [{
			text : '上一步',
			handler : function() {
			  makeWindow.hide();
			  addWindow.show();
			}
		},{
			text : '确认提交',
			handler : function() { 
			//根据id获取值
			   var id = Ext.getCmp("id").getValue();
		       var sitecondition = Ext.getCmp("sitecondition").getValue();
		       var sitename = Ext.getCmp("sitename").getValue();
		       var xh = Ext.getCmp("xh").getValue();
		       var proposer = Ext.getCmp("proposer").getValue();
		       var relationtel = Ext.getCmp("relationtel").getValue();
		       var counsellor = Ext.getCmp("counsellor").getValue();
		       var activityname = Ext.getCmp("activityname").getValue();
		       var activitycontent = Ext.getCmp("activitycontent").getValue();
		       var activitydate = Ext.getCmp("activitydate").getValue();
		       var activitytype = Ext.getCmp("activitytype").getValue();
		       var guideth = Ext.getCmp("guideth").getValue();
		       var ifschool = Ext.getCmp("ifschool2").getValue();
		       var activitytime = Ext.getCmp("activitytime").getValue();
		       var beorganize = Ext.getCmp("beorganize").getValue();
		       var sitemodle = Ext.getCmp("sitemodle").getValue();
		       var agreement = Ext.getCmp("agreement2").getValue(); 
		      
		       var ifschools = '';
		       Ext.each(ifschool,function(item){
		    	   ifschools = item.inputValue
		       });
		       
		       //获取行号
		       var rowIndex = Ext.getCmp("rowIndex3").getValue(); 
			   //获取数据
			   var record = grid.getStore().data.items[rowIndex];
			  
		       Ext.MessageBox.confirm('数据提示', '确定保存该数据吗？', function(c) {
		    	   if(c=='yes'){
		            Ext.Ajax.request({
					   		url : '/MyStock/SiteInfo_saveOrUpdateSiteInfoLog.do',
						   	params:{
						    id : record.data.id,
						    sitecondition:sitecondition,
						    sitename : sitename,
						    xh:xh,
						    siteid : record.data.siteid,
						    proposer:proposer,
						    relationtel:relationtel,
						    counsellor:counsellor,
						    activityname:activityname,
						    activitycontent:activitycontent,
						    activitydate:activitydate,
						    activitytype:activitytype,
						    guideth:guideth,
						    ifschool:ifschool,
						    activitytime:activitytime,
						    beorganize:beorganize,
						    sitemodle:sitemodle,
						    agreement:agreement,
						    park:record.data.park,
						    sitetype:record.data.sitetype,
						    dutystate:record.data.dutystate
						   },
						    success : function(form, action) {
							   if(!action.success){
							     Ext.Msg.alert('信息提示',action.result.message);
							     }
								 makeWindow.hide();
						         store.reload();
						         window.location.href = 'siteinfo.jsp';
							 }
					    });
		    	   }
		       });
			}
		}]
	});
    
    
    
    
    //预约方法
    var  makeModle = function (grid, rowIndex, colIndex){
     	var record = grid.getStore().getAt(rowIndex); 
         Ext.getCmp("id").setValue(record.data.id);
         Ext.getCmp("sitename").setValue(record.data.sitename);
     	 Ext.getCmp("sitecondition").setValue(record.data.sitecondition);
     	 //隐藏传行号rowIndex
         Ext.getCmp("rowIndex1").setValue(rowIndex);
         
         viewWindow.hide();   
     	agreementWindow.show();
    }
    
    
   
    //图片显示
    var imgWindow = new Ext.Window({
		title : '图片预览',
		width:840,
		height:300,
		closeAction:'hide',
		modal : true,
		closable:true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [{xtype:'box',
				 id:'imgBox',
                 autoEl:{
                     tag:'img',
                     src:'',
                 }
		}]
	});
    	
    
	
    //布局
    new Ext.Viewport({
		layout:"fit",
		items:[{
			frame:true,
			title:"场地申请查询",
			iconCls:'menu-12',
			layout:"border",
			items:[searchForm,grid]
		}],
		listeners:{
			render:function(){
    	      getModle();
    	      orgaCombStore.load();
    	      parkStore.load();  
    	      groundTypeStore.load();
			}
		}
	});
    
   
    
    //时间控件开始时间小于结束时间
    Ext.apply(Ext.form.VTypes, {
        daterange : function(val, field) {
            var date = field.parseDate(val);
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

});