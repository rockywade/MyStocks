Ext.BLANK_IMAGE_URL = '../../../ext/resources/images/default/s.gif';
/*!
 * 活动报名
 */
Ext.onReady(function(){
	Ext.QuickTips.init();
	
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
							width:160,
							items:[{
								cls :'returnBtn',
								width:90,
								xtype:"button",
								//iconCls:"btn-list",
								text:"我参与的活动",
								handler:function(){
										   window.location.href="attend.jsp";
								}
							}]
						}/*,{
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
						}*/]
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
	var store;
	store = new Ext.data.JsonStore({
	    url: '/MyStock/Applyactivity_couldJoinActivity.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: {params:{start:0, limit:15}},
	    fields: Activity
	});
	
	//报名方法
	var join = function(grid, rowIndex, colIndex){
		var record = grid.getStore().getAt(rowIndex);
		addWindow.show();
		addForm.getForm().loadRecord(record);
    };
    
    //分类按钮
	var searchBtn = new Ext.FormPanel({
		region:"north",
		height: 30,
		border : false,
		layout : "form",
		padding : "0 0 0 0",
		items:[{
			layout:"column",
			items:[{
				width:90,
				items:[{
					id:'allid',
					cls:'actStyleBtn actCur',
					width:80,
					xtype:"button",
					text:"全<i class='ikg2'></i>部",
					style:'margin-left:10%',
					handler:function(){
		        		store.load({params:{start:0, limit:15}});
		        		if (Ext.fly('kxjz').hasClass("actCur")) {
		        			Ext.fly('kxjz').removeClass('actCur');
		        		} 
		        		if (Ext.fly('sygh').hasClass("actCur")) {
		        			Ext.fly('sygh').removeClass('actCur');
		        		} 
		        		if (Ext.fly('xljk').hasClass("actCur")) {
		        			Ext.fly('xljk').removeClass('actCur');
		        		} 
		        		if (Ext.fly('dtjs').hasClass("actCur")) {
		        			Ext.fly('dtjs').removeClass('actCur');
		        		} 
		        		if (Ext.fly('wthd').hasClass("actCur")) {
		        			Ext.fly('wthd').removeClass('actCur');
		        		} 
		        		if (Ext.fly('qt').hasClass("actCur")) {
		        			Ext.fly('qt').removeClass('actCur');
		        		} 
		        		Ext.fly("allid").addClass('actCur');
					}
				}]
			},{
				width:90,
				items:[{
					id:'kxjz',
					cls:'actStyleBtn',
					width:80,
					xtype:"button",
					text:"学科讲座",
					style:'margin-left:10%',
					handler:function(){
						store.load({params:{activitygenre:"学科讲座",start:0, limit:15}});
		        		if (Ext.fly('allid').hasClass('actCur')) {
		        			Ext.fly('allid').removeClass('actCur');
		        		} 
		        		if (Ext.fly('sygh').hasClass('actCur')) {
		        			Ext.fly('sygh').removeClass('actCur');
		        		}
		        		if (Ext.fly('xljk').hasClass('actCur')) {
		        			Ext.fly('xljk').removeClass('actCur');
		        		}
		        		if (Ext.fly('dtjs').hasClass('actCur')) {
		        			Ext.fly('dtjs').removeClass('actCur');
		        		}
		        		if (Ext.fly('wthd').hasClass("actCur")) {
		        			Ext.fly('wthd').removeClass('actCur');
		        		} 
		        		if (Ext.fly('qt').hasClass('actCur')) {
		        			Ext.fly('qt').removeClass('actCur');
		        		}
		        		Ext.fly("kxjz").addClass('actCur');
					}
				}]
			},{
				width:90,
				items:[{
					id:'sygh',
					cls:'actStyleBtn',
					width:80,
					xtype:"button",
					text:"生涯规划",
					style:'margin-left:10%',
					handler:function(){
						store.load({params:{activitygenre:"生涯规划",start:0, limit:15}});
		        		if (Ext.fly('allid').hasClass('actCur')) {
		        			Ext.fly('allid').removeClass('actCur');
		        		} 
		        		if (Ext.fly('kxjz').hasClass('actCur')) {
		        			Ext.fly('kxjz').removeClass('actCur');
		        		}
		        		if (Ext.fly('xljk').hasClass('actCur')) {
		        			Ext.fly('xljk').removeClass('actCur');
		        		}
		        		if (Ext.fly('dtjs').hasClass('actCur')) {
		        			Ext.fly('dtjs').removeClass('actCur');
		        		}
		        		if (Ext.fly('wthd').hasClass("actCur")) {
		        			Ext.fly('wthd').removeClass('actCur');
		        		} 
		        		if (Ext.fly('qt').hasClass('actCur')) {
		        			Ext.fly('qt').removeClass('actCur');
		        		}
		        		Ext.fly("sygh").addClass('actCur');
					}
				}]
			},{
				width:90,
				items:[{
					id:'xljk',
					cls:'actStyleBtn',
					width:80,
					xtype:"button",
					text:"心理健康",
					style:'margin-left:10%',
					handler:function(){
						store.load({params:{activitygenre:"心理健康",start:0, limit:15}});
		        		if (Ext.fly('allid').hasClass('actCur')) {
		        			Ext.fly('allid').removeClass('actCur');
		        		} 
		        		if (Ext.fly('kxjz').hasClass('actCur')) {
		        			Ext.fly('kxjz').removeClass('actCur');
		        		}
		        		if (Ext.fly('sygh').hasClass('actCur')) {
		        			Ext.fly('sygh').removeClass('actCur');
		        		}
		        		if (Ext.fly('dtjs').hasClass('actCur')) {
		        			Ext.fly('dtjs').removeClass('actCur');
		        		}
		        		if (Ext.fly('wthd').hasClass("actCur")) {
		        			Ext.fly('wthd').removeClass('actCur');
		        		} 
		        		if (Ext.fly('qt').hasClass('actCur')) {
		        			Ext.fly('qt').removeClass('actCur');
		        		}
		        		Ext.fly("xljk").addClass('actCur');
					}
				}]
			},{
				width:90,
				items:[{
					id:'dtjs',
					cls:'actStyleBtn',
					width:80,
					xtype:"button",
					text:"党团建设",
					style:'margin-left:10%',
					handler:function(){
						store.load({params:{activitygenre:"党团建设",start:0, limit:15}});
		        		if (Ext.fly('allid').hasClass('actCur')) {
		        			Ext.fly('allid').removeClass('actCur');
		        		} 
		        		if (Ext.fly('kxjz').hasClass('actCur')) {
		        			Ext.fly('kxjz').removeClass('actCur');
		        		}
		        		if (Ext.fly('sygh').hasClass('actCur')) {
		        			Ext.fly('sygh').removeClass('actCur');
		        		}
		        		if (Ext.fly('xljk').hasClass('actCur')) {
		        			Ext.fly('xljk').removeClass('actCur');
		        		}
		        		if (Ext.fly('wthd').hasClass("actCur")) {
		        			Ext.fly('wthd').removeClass('actCur');
		        		} 
		        		if (Ext.fly('qt').hasClass('actCur')) {
		        			Ext.fly('qt').removeClass('actCur');
		        		}
		        		Ext.fly("dtjs").addClass('actCur');
					}
				}]
			},{
				width:90,
				items:[{
					id:'wthd',
					cls:'actStyleBtn',
					width:80,
					xtype:"button",
					text:"文体活动",
					style:'margin-left:10%',
					handler:function(){
						store.load({params:{activitygenre:"文体活动",start:0, limit:15}});
		        		if (Ext.fly('allid').hasClass('actCur')) {
		        			Ext.fly('allid').removeClass('actCur');
		        		} 
		        		if (Ext.fly('kxjz').hasClass('actCur')) {
		        			Ext.fly('kxjz').removeClass('actCur');
		        		}
		        		if (Ext.fly('sygh').hasClass('actCur')) {
		        			Ext.fly('sygh').removeClass('actCur');
		        		}
		        		if (Ext.fly('xljk').hasClass('actCur')) {
		        			Ext.fly('xljk').removeClass('actCur');
		        		}
		        		if (Ext.fly('dtjs').hasClass("actCur")) {
		        			Ext.fly('dtjs').removeClass('actCur');
		        		} 
		        		if (Ext.fly('qt').hasClass('actCur')) {
		        			Ext.fly('qt').removeClass('actCur');
		        		}
		        		Ext.fly("wthd").addClass('actCur');
					}
				}]
			},{
				width:90,
				items:[{
					id:'qt',
					cls:'actStyleBtn',
					width:80,
					xtype:"button",
					text:"其<i class='ikg2'></i>他",
					style:'margin-left:10%',
					handler:function(){
						store.load({params:{activitygenre:"其他",start:0, limit:15}});
		        		if (Ext.fly('allid').hasClass('actCur')) {
		        			Ext.fly('allid').removeClass('actCur');
		        		} 
		        		if (Ext.fly('kxjz').hasClass('actCur')) {
		        			Ext.fly('kxjz').removeClass('actCur');
		        		}
		        		if (Ext.fly('sygh').hasClass('actCur')) {
		        			Ext.fly('sygh').removeClass('actCur');
		        		}
		        		if (Ext.fly('xljk').hasClass('actCur')) {
		        			Ext.fly('xljk').removeClass('actCur');
		        		}
		        		if (Ext.fly('dtjs').hasClass("actCur")) {
		        			Ext.fly('dtjs').removeClass('actCur');
		        		} 
		        		if (Ext.fly('wthd').hasClass('actCur')) {
		        			Ext.fly('wthd').removeClass('actCur');
		        		}
		        		Ext.fly("qt").addClass('actCur');
					}
				}]
			}]
		}]
	});
	
	
	//var sm = new Ext.grid.CheckboxSelectionModel();//单选{singleSelect: true}
    var rownumber = new Ext.grid.RowNumberer();//行号
    var grid = new Ext.grid.GridPanel({
        store: store,
        height:500,
        id : 'grid',
        autoScroll:true,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[rownumber,
			    {header: '活动名称', width: 100,align:'center', dataIndex: 'activityname'},
			    {header:'活动类别',width:100,align:'center',dataIndex:'activitygenre'},
			    {header:'时间',width:100,align:'center',dataIndex:'activitytime'},
	            {header: '所在学期', width: 100,align:'center', dataIndex: 'inschoolterm'},
	            {header: '活动地点', width: 100,align:'center', dataIndex: 'activityplace'},
	            {header: '活动考评分', width: 100,align:'center', dataIndex: 'score'},
	            /*{header: '状态', width: 100,align:'center', dataIndex: 'applystyle',renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
		               if(value == '已通过') { 
	                        return "<span style='color:green;font-weight:bold;'>已通过</span>"
	                   }     
	                   if(value == '不通过') { 
	                      return "<span style='color:red;font-weight:bold;'>未通过</span>"
	                   }   
	                 }},*/
	            {xtype: 'actioncolumn',header : '操作', width: 140,align : 'center',menuDisabled : true,
	                items: [{
	                    icon: '../../../img/btn/bm.png',
	                    //tooltip: '报名',
	                    getClass: function(value, metaData, record, rowIndex, colIndex, store, view) {
	                		if(record.data.capacity<=record.data.attendnum){
	                              return 'x-hidden'
	                        }
	                	},
	                    handler: function(grid, rowIndex, colIndex){
	                		join(grid, rowIndex, colIndex);//调用报名方法
	                	}
	                }]
	           }]
        }),
        rownumber:rownumber,//行号
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
		loadMask : true,	//加载时的遮罩
		frame : true,
		region:"center",
        iconCls:'menu-51',
/*        tbar:[{
        	cls:'',
        	id:'allid',
        	text:'全部',
        	handler: function(){
        		store.load({params:{start:0, limit:15}});
        		//alert(Ext.fly('allid').hasClass("actCur"));
        		if (Ext.fly('kxjz').hasClass("actCur")) {
        			Ext.fly('kxjz').removeClass('actCur');
        		} 
        		if (Ext.fly('sygh').hasClass("actCur")) {
        			Ext.fly('sygh').removeClass('actCur');
        		} 
        		if (Ext.fly('xljk').hasClass("actCur")) {
        			Ext.fly('xljk').removeClass('actCur');
        		} 
        		if (Ext.fly('dtjs').hasClass("actCur")) {
        			Ext.fly('dtjs').removeClass('actCur');
        		} 
        		if (Ext.fly('wthd').hasClass("actCur")) {
        			Ext.fly('wthd').removeClass('actCur');
        		} 
        		if (Ext.fly('qt').hasClass("actCur")) {
        			Ext.fly('qt').removeClass('actCur');
        		} 
        		Ext.fly("allid").addClass('actCur');
        	}
        },'-',{
        	cls:'',
        	id:'kxjz',
        	text:'科学讲座',
        	handler: function(){
        		store.load({params:{activitygenre:"科学讲座",start:0, limit:15}});
        		if (Ext.fly('allid').hasClass('actCur')) {
        			Ext.fly('allid').removeClass('actCur');
        		} 
        		if (Ext.fly('sygh').hasClass('actCur')) {
        			Ext.fly('sygh').removeClass('actCur');
        		}
        		if (Ext.fly('xljk').hasClass('actCur')) {
        			Ext.fly('xljk').removeClass('actCur');
        		}
        		if (Ext.fly('dtjs').hasClass('actCur')) {
        			Ext.fly('dtjs').removeClass('actCur');
        		}
        		if (Ext.fly('wthd').hasClass("actCur")) {
        			Ext.fly('wthd').removeClass('actCur');
        		} 
        		if (Ext.fly('qt').hasClass('actCur')) {
        			Ext.fly('qt').removeClass('actCur');
        		}
        		Ext.fly("kxjz").addClass('actCur');
        	}
        },'-',{
        	cls:'',
        	id:'sygh',
        	text:'生涯规划',
        	handler: function(){
        		store.load({params:{activitygenre:"生涯规划",start:0, limit:15}});
        		if (Ext.fly('allid').hasClass('actCur')) {
        			Ext.fly('allid').removeClass('actCur');
        		} 
        		if (Ext.fly('kxjz').hasClass('actCur')) {
        			Ext.fly('kxjz').removeClass('actCur');
        		}
        		if (Ext.fly('xljk').hasClass('actCur')) {
        			Ext.fly('xljk').removeClass('actCur');
        		}
        		if (Ext.fly('dtjs').hasClass('actCur')) {
        			Ext.fly('dtjs').removeClass('actCur');
        		}
        		if (Ext.fly('wthd').hasClass("actCur")) {
        			Ext.fly('wthd').removeClass('actCur');
        		} 
        		if (Ext.fly('qt').hasClass('actCur')) {
        			Ext.fly('qt').removeClass('actCur');
        		}
        		Ext.fly("sygh").addClass('actCur');
        	}
        },'-',{
        	cls:'',
        	id:'xljk',
        	text:'心理健康',
        	handler: function(){
        		store.load({params:{activitygenre:"心理健康",start:0, limit:15}});
        		if (Ext.fly('allid').hasClass('actCur')) {
        			Ext.fly('allid').removeClass('actCur');
        		} 
        		if (Ext.fly('kxjz').hasClass('actCur')) {
        			Ext.fly('kxjz').removeClass('actCur');
        		}
        		if (Ext.fly('sygh').hasClass('actCur')) {
        			Ext.fly('sygh').removeClass('actCur');
        		}
        		if (Ext.fly('dtjs').hasClass('actCur')) {
        			Ext.fly('dtjs').removeClass('actCur');
        		}
        		if (Ext.fly('wthd').hasClass("actCur")) {
        			Ext.fly('wthd').removeClass('actCur');
        		} 
        		if (Ext.fly('qt').hasClass('actCur')) {
        			Ext.fly('qt').removeClass('actCur');
        		}
        		Ext.fly("xljk").addClass('actCur');
        	}
        },'-',{
        	cls:'',
        	id:'dtjs',
        	text:'党团建设',
        	handler: function(){
        		store.load({params:{activitygenre:"党团建设",start:0, limit:15}});
        		if (Ext.fly('allid').hasClass('actCur')) {
        			Ext.fly('allid').removeClass('actCur');
        		} 
        		if (Ext.fly('kxjz').hasClass('actCur')) {
        			Ext.fly('kxjz').removeClass('actCur');
        		}
        		if (Ext.fly('sygh').hasClass('actCur')) {
        			Ext.fly('sygh').removeClass('actCur');
        		}
        		if (Ext.fly('xljk').hasClass('actCur')) {
        			Ext.fly('xljk').removeClass('actCur');
        		}
        		if (Ext.fly('wthd').hasClass("actCur")) {
        			Ext.fly('wthd').removeClass('actCur');
        		} 
        		if (Ext.fly('qt').hasClass('actCur')) {
        			Ext.fly('qt').removeClass('actCur');
        		}
        		Ext.fly("dtjs").addClass('actCur');
        	}
        },'-',{
        	cls:'',
        	id:'wthd',
        	text:'文体活动',
        	handler: function(){
        		store.load({params:{activitygenre:"文体活动",start:0, limit:15}});
        		if (Ext.fly('allid').hasClass('actCur')) {
        			Ext.fly('allid').removeClass('actCur');
        		} 
        		if (Ext.fly('kxjz').hasClass('actCur')) {
        			Ext.fly('kxjz').removeClass('actCur');
        		}
        		if (Ext.fly('sygh').hasClass('actCur')) {
        			Ext.fly('sygh').removeClass('actCur');
        		}
        		if (Ext.fly('xljk').hasClass('actCur')) {
        			Ext.fly('xljk').removeClass('actCur');
        		}
        		if (Ext.fly('dtjs').hasClass("actCur")) {
        			Ext.fly('dtjs').removeClass('actCur');
        		} 
        		if (Ext.fly('qt').hasClass('actCur')) {
        			Ext.fly('qt').removeClass('actCur');
        		}
        		Ext.fly("wthd").addClass('actCur');
        	}
        },'-',{
        	cls:'',
        	id:'qt',
        	text:'其他',
        	handler: function(){
        		store.load({params:{activitygenre:"其他",start:0, limit:15}});
        		if (Ext.fly('allid').hasClass('actCur')) {
        			Ext.fly('allid').removeClass('actCur');
        		} 
        		if (Ext.fly('kxjz').hasClass('actCur')) {
        			Ext.fly('kxjz').removeClass('actCur');
        		}
        		if (Ext.fly('sygh').hasClass('actCur')) {
        			Ext.fly('sygh').removeClass('actCur');
        		}
        		if (Ext.fly('xljk').hasClass('actCur')) {
        			Ext.fly('xljk').removeClass('actCur');
        		}
        		if (Ext.fly('dtjs').hasClass("actCur")) {
        			Ext.fly('dtjs').removeClass('actCur');
        		} 
        		if (Ext.fly('wthd').hasClass('actCur')) {
        			Ext.fly('wthd').removeClass('actCur');
        		}
        		Ext.fly("qt").addClass('actCur');
        	}
        }],*/
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
    
  //活动报名窗口
    var addForm = new Ext.FormPanel({
		layout : 'form',
		//baseCls:'x-plain',
		frame:true,
		url :'/MyStock/Applyactivity_attendActivity.do',
		labelWidth:72,
		border : false,
		padding : '15 0 0 50',
		defaults : {
			anchor : '100%',
			cls:'borderNone'
		},
		items:[{
			xtype : 'textfield',
			name:'activityname',
			fieldLabel:'活动名称',
			readOnly:true,
			anchor : '86.5%'
		},{
			layout:'column',
			items:[{
				columnWidth:.5,
				layout:'form',
				defaults:{
					//anchor : '150',
					width:100,
					cls:'borderNone'
				},
				items:[/*{
					xtype : 'textfield',
					name:'applyuser',
					fieldLabel:'申<i class="ikg1"></i>报<i class="ikg1"></i>人',
					readOnly:true,
				},{
					xtype : 'textfield',
					name:'studentphonenum',
					readOnly:true,
					fieldLabel:'联系电话',
					readOnly:true,
				},{
					xtype : 'textfield',
					name:'teacher',
					fieldLabel:'指导老师',
					readOnly:true,
				},*/{
					xtype:'textfield',
					name:'inschoolterm',
					fieldLabel:'所在学期',
					readOnly:true,
				},{
					xtype :"numberfield",
					name:'capacity',
					fieldLabel:'容量(人)',
					readOnly:true,
				}]
			},{
				columnWidth:.5,
				layout:'form',
				defaults:{
					//anchor : '150',
					width:100,
					cls:'borderNone'
				},
				items:[/*{
					xtype : 'textfield',
					name:'studentnum',
					fieldLabel:'学<i class="ikg2"></i>号',
					readOnly:true,
				},{
					xtype:'textfield',
					name:'organizename',
					fieldLabel:'组织名称',
					readOnly:true,
				},{ xtype : 'numberfield',
					name:'phonenum',
					fieldLabel:'手机号码',
					readOnly:true,
				},*/{
					xtype:'textfield',
					name:'activitygenre',
					fieldLabel:'类<i class="ikg2"></i>别',
					readOnly:true,
				}]
			}]
		},{
			xtype:'textarea',
			name:'faceobj',
			fieldLabel:'面向对象',
			readOnly:true,
			height:20
		},{
			xtype:'textfield',
			name:'activityplace',
			fieldLabel:'活动地点',
			readOnly:true,
			anchor : '86.5%'
		},{
			layout:'column',
			items:[{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%',
					cls:'borderNone'
				},
				items:[{
					xtype : 'textfield',
					name:'activitytime',
					fieldLabel:'活动时间',
					readOnly:true,
				},{
					xtype : 'textfield',
					name:'signupendtime',
					fieldLabel:'报名截止',
					readOnly:true,
				}]
			},{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '55%',
					cls:'borderNone'
				},
				items:[{
					xtype:'textfield',
					name:'timeofduration',
					fieldLabel:'时<i class="ikg2"></i>长',
					readOnly:true,
				},{
					xtype : 'textfield',
					name:'score',
					fieldLabel:'活动考评分',
					readOnly:true,
				}]
			}]
		},{
			xtype:'textarea',
			name:'activitycontent',
			fieldLabel:'活动内容',
			readOnly:true,
			anchor : '86.5%',
			height:100
		},{
			xtype : 'hidden',
		    name : 'attendstudentphonenum'
		},{
			xtype:'hidden',
			name:'username'
		},{
			xtype:'hidden',
			name:'usernum'
		},{
			xtype:'hidden',
			name:'activityid'
		},{
			xtype:'hidden',
			name:'major'
		},{
			xtype:'hidden',
			name:'classes'
		},{
			xtype:'hidden',
			name:'dorm'
		},{
			xtype:'hidden',
			name:'counsellor'
		}]
	});
    
    //获取当前用户信息
    var getCurrentUserInfo = function(){
    	Ext.Ajax.request({
    		url:"/MyStock/Applyactivity_getCurrentUserInfo.do",
    		success:function(response){
    			var responsedata = Ext.util.JSON.decode(response.responseText);
    			if(responsedata){
    				addForm.getForm().findField('usernum').setValue(responsedata.data.userNum);
    				addForm.getForm().findField('username').setValue(responsedata.data.userName);
    				addForm.getForm().findField('major').setValue(responsedata.data.userMajor);
    				addForm.getForm().findField('classes').setValue(responsedata.data.userClasses);
    				addForm.getForm().findField('dorm').setValue(responsedata.data.userDorm);
    				addForm.getForm().findField('counsellor').setValue(responsedata.data.userInstructor);
    				addForm.getForm().findField('attendstudentphonenum').setValue(responsedata.data.userPhone);
    			}
    		}
    	});
    };
  
    //添加form窗口
    var addWindow = new Ext.Window({
		title : '活动详情',
		width:600,
		height:400,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [addForm],
		buttons : [{
			text : '确定报名',
			handler : function() {
				//Ext.MessageBox.buttonText.yes = '按钮一'; 
				//Ext.MessageBox.buttonText.no = '按钮二';
				if (addForm.getForm().isValid()) {
		            var config = {
		            	//class:
		                title: "求是学院活动报名说明",
		                msg: "请按照活动信息参加活动，报名未参加将扣去1分记实考评！",
		                width: 360,
		                height:360,
		                multiline: false,//是否显示文本输入框
		                closable: false, //是否显示关闭按钮
		                buttons: Ext.Msg.OKCANCEL,
		                buttonText: {
		                    OK: "我要报名",
		                    Cancel: "取消"
		                },
		                icon: Ext.Msg.Info,
		                fn: function (btn) {
		            		if(btn=="ok"){
		            			addForm.getForm().submit({
									params :{checkkey:1},
									success : function(form, action) {
										Ext.Msg.alert('信息提示',action.result.message,function(){
											if(action.result.message=="你已在参与中..."){
												addWindow.hide();
											}else{
												addWindow.hide();
												window.location.href="attend.jsp"
											}
											
										});
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
    
    /************第二列表************/
    var promptMsg = new Ext.FormPanel({
		region:"north",
		height: 80,
		border : false,
		layout : "form",
		padding : "100 20 0 20",
		
			items:[{
				cls :'returnBtn',
				width:90,
				xtype:"button",
				style:'margin-right:10%;margin-top:10%',
				text:"返<i class='ikg2'></i>回",
				handler:function(){
						   window.location.href="../../menu/personal-affairs.html";
				}
			},{
	        	html: '<div class="activityB_info"><h3>求是学院活动报名说明</h3><ol>'+
						'<li>参加活动时须遵守组织方的规定,服从安排。</li>'+
						'<li>报名成功后，请关注活动的时间地点等信息，按时参加。</li>'+
						'<li>报名后不能参加的活动请及时取消报名，报名未参加将扣去1分记实考评。</li>'+
					'</ol><a href="javascript:void(0)" class="actB_update" onclick="fileDownload()">《求是学院记实考评工作实施办法》</a></div>'
		
		}]
	});
    //文件下载方法
    fileDownload = function (){
    	Ext.MessageBox.confirm('信息提示',"确定下载《求是学院记实考评工作实施办法》?",function(btn){
    		if(btn=="yes"){
    			window.location.href="/MyStock/fileDownload.do?fileDownKey=AttendFileDeatil";
    		}
    	});
    }
    
    //布局
    new Ext.Viewport({
		layout:"fit",
		items:[{
			frame:true,
			title:"公示与细则",
			iconCls:'menu-15',
			layout:"border",
			items:[{
				//frame:true,
				//title:'公示',
				id:"west",
				region:'west',
				width:'73%',
				//iconCls:'menu-18',
				items:[searchForm,searchBtn,grid]
			},{
				//frame:true,
				//title:'细则',
				region:'center',
				padding:'0 0 0 5',
				layout:'fit',
				items:promptMsg
			}],
			listeners:{
				render:function(){
					getCurrentUserInfo();
		    		//addWindow.show();
				}
			}
		}]  
	});
});

Ext.onReady(function(){
	 var westHeight = Ext.getCmp("west").getHeight();
	 Ext.getCmp("grid").setHeight(westHeight - 105);
});	