Ext.BLANK_IMAGE_URL = '../../../ext/resources/images/default/s.gif';
/*!
 * 公示与新闻
 */
Ext.onReady(function(){
	Ext.QuickTips.init();
	
    /*活动类别*/
    var genre = new Ext.data.SimpleStore({
    	fields:['value','text'],
    	data:[['学科讲座','学科讲座'],['心理健康','心理健康'],['生涯规划','生涯规划'],
    	      ['党团建设','党团建设'],['文体活动','文体活动'],['其他','其他'],['','全部']]
    });
	
	//查询表单
	var searchForm = new Ext.FormPanel({
		region:"north",
		height: 80,
		border : false,
		layout : "form",
		padding : "5 10 0 20",
		items:[{
			xtype:"fieldset",
			title:"查询设置",
			padding:"0 10 0 15",
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
								labelWidth:30,
								xtype:"textfield",
								name:"activityname",
								//emptyText: '输入活动名称关键字搜索',
								fieldLabel:"活动名称"
							}]
						},{

							width:260,
							items:[{
								width : 130,
								xtype:"combo",
								hiddenName:'activitygenre',
								fieldLabel:"活动分类",
		   						mode: 'local',
		   						triggerAction: 'all',
								valueField :"value",
		   						displayField: "text",
		   						allowBlank : true,
		   						editable : false,
								store : genre,
								emptyText: '- 分类选择 -',
							}]
								
						},{
							width:200,
							items:[{
								width:75,
								xtype:"button",
								style:'margin-left:30%',
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
						}/*,{
							width:90,
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
	
	var Attend = [
		      		{ name:'id', type:'string'},
		      		{ name:'usernum', type:'string'},
		      		{ name:'username',type:'string'},
		      		{ name:'classes',type:'string'},
		      		{ name:'counsellor',type:'string'},
		      		{ name:'attendstudentphonenum',type:'string'},
		      		{ name:'dorm',type:'string'},
		      		{ name:'activityid',type:'string'},
		      		{ name:'activityname', type:'string'},
		      		{ name:'activitygenre', type:'string'},
		      		{ name:'activitytime', type:'string'},
		      		{ name:'inschoolterm', type:'string'},
		      		{ name:'activityplace', type:'string'},
		      		{ name:'gotscore', type:'int'},
		      		{ name:'state', type:'string'},	//参加活动状态，报名后状态：待参加、签到后：已参加、未签到：未参加
		      		{ name:'score', type:'int'},
		      		{ name:'gross', type:'int'},
		      	];
	
    var store = new Ext.data.JsonStore({
	    url: 'Applyactivity_publicity.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: {params:{start:0, limit:15}},
	    fields: Activity
	});
    
    var storeDetail = new Ext.data.JsonStore({
	    url: 'Applyactivity_attendActivityPublicityDetail.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: false,
	    fields: Attend
	});
    
    //方法
	var lookDetail = function(grid, rowIndex, colIndex){
    	var record = grid.getStore().getAt(rowIndex);
    	storeDetail.load({
			url:'Applyactivity_attendActivityPublicityDetail.do',
	        params:{start:0,limit:15,activityid:record.data.activityid},
	        scope:this
	    });
    	addWindow.show();
    	addForm.getForm().loadRecord(record);
    };
    
	//var sm = new Ext.grid.CheckboxSelectionModel();//单选{singleSelect: true}
    var rownumber = new Ext.grid.RowNumberer();//行号
    var grid = new Ext.grid.GridPanel({
        store: store,
        height:610,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[rownumber,
			    {header: '活动名称', width: 120,align:'center', dataIndex: 'activityname'},
			    {header:'活动类别',width:120,align:'center',dataIndex:'activitygenre'},
			    {header:'活动时间',width:130,align:'center',dataIndex:'activitytime'},
	            {header: '活动地点', width: 120,align:'center', dataIndex: 'activityplace'},
	            {header: '活动考评分', width: 120,align:'center', dataIndex: 'score'},
	            {xtype: 'actioncolumn',header : '操作', width: 120,align : 'center',menuDisabled : true,
	                items: [{
	                    icon: '../../../img/btn/see.png',
	                    //tooltip: '查看详情',
	                    /*getClass: function(value, metaData, record, rowIndex, colIndex, store, view) {      
	                		if('待审核'!=record.data.applystyle){
	                              return 'x-hidden'
	                        }
	                	},*/
	                    handler: function(grid, rowIndex, colIndex){
	                		lookDetail(grid, rowIndex, colIndex);//调用方法
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
    
    var rownumberDetail = new Ext.grid.RowNumberer();//行号
    var gridDetail = new Ext.grid.GridPanel({
        store: storeDetail,
        height:200,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[rownumber,
			    //{header:'姓名', width: 110,align:'center', dataIndex: 'username'},
			    {header:'学号',width:110,align:'center',dataIndex:'usernum'},
			    {header:'所加分值',width:110,align:'center',dataIndex:'gross'}
			]
        }),
        rownumber:rownumberDetail,//checkbox
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
		loadMask : true,	//加载时的遮罩
		frame : true,
		region:"center",
        iconCls:'menu-51',
        bbar: new Ext.PagingToolbar({
            pageSize: 15,
            store: storeDetail,
            displayInfo: true
        }),
        viewConfig : {
        	forceFit: true,
        	scrollOffset: 2
    	}
    });
    
    //Form
    var addForm = new Ext.FormPanel({
		layout : 'form',
		region:"north",
		frame:true,
		labelWidth:56,
		border : false,
		padding : '15 10 90 50',
		defaults : {
			anchor : '100%'
		},
		items:[{
			layout:'column',
			items:[{
				columnWidth:.33,
				layout:'form',
				defaults:{
					anchor : '100%',
					cls:'borderNone'
				},
				items:[{
					xtype : 'textfield',
					name:'activityname',
					fieldLabel:'活动名称',
					readOnly:true,
				},{
					xtype : 'textfield',
					name:'inschoolterm',
					readOnly:true,
					fieldLabel:'所在学期',
				},{
					xtype : 'textfield',
					name:'activityplace',
					fieldLabel:'活动地点',
					readOnly:true,
				}]
			},{
				columnWidth:.33,
				layout:'form',
				defaults:{
					anchor : '100%',
					cls:'borderNone'
				},
				items:[{
					xtype : 'textfield',
					name:'activitygenre',
					fieldLabel:'类<i class="ikg2"></i>别',
					readOnly:true,
				},{
					xtype:'textfield',
					name:'timeofduration',
					fieldLabel:'时<i class="ikg2"></i>长',
					readOnly:true
				},{
					xtype:'textfield',
					name:'activitytime',
					fieldLabel:'活动时间',
					readOnly:true,
				}]
			},{
				columnWidth:.33,
				layout:'form',
				defaults:{
					anchor : '100%',
					cls:'borderNone'
				},
				items:[{
					xtype : 'textfield',
					name:'applyuser',
					fieldLabel:'申<i class="ikg1"></i>报<i class="ikg1"></i>人',
					readOnly:true,
				},{
					xtype : 'textfield',
					name:'teacher',
					fieldLabel:'指导老师',
					readOnly:true,
				},{
					xtype : 'textfield',
					name:'studentphonenum',
					fieldLabel:'活动电话',
					readOnly:true,
				}]
			}]
		}]
	});
    
    //添加form窗口
    var addWindow = new Ext.Window({
		title : '详情',
		width:780,
		height:450,
		closeAction:'hide',
		modal : true,
		layout : 'border',
		items : [addForm,gridDetail],
		buttons : []
	});
    
    
    /*****************第二列表*********************/
	var returnForm = new Ext.FormPanel({
		region:"north",
		height: 80,
		border : false,
		layout : "form",
		padding : "5 10 0 20",
		items:[{
			cls :'returnBtn',
			width:90,
			xtype:"button",
			text:"返<i class='ikg2'></i>回",
			handler:function(){
					   window.location.href="../../menu/personal-affairs.html";
			}
		}]
	});
    
    var news = [
      	          { name:'newstitle', type:'string'},
      	          { name:'newsid', type:'string'},
      	          { name:'website', type:'string'},
      	          //{ name:'expertName', type:'string'},
      	        ];
    
	var newsStore = new Ext.data.JsonStore({
	    url: 'Applyactivity_newsSpecialColumn.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: {params:{start:0, limit:15}},
	    fields: news
	});
    
    var newsGrid = new Ext.grid.GridPanel({
        store: newsStore,
        height:610,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[rownumber,
	            {header: '新闻标题', width: 80, sortable:true, dataIndex: 'newstitle'},
	            ]
        }),
        rownumber:rownumber,//行号
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
		loadMask : true,	//加载时的遮罩
		frame : true,
		//title:'新闻专栏',
		region:"center",
        iconCls:'menu-54',
        listeners:{  
        	'cellclick':function(grid,rowIndex,columnIndex,e){ 
    			var record= grid.getSelectionModel().getSelected();
    			var newsid = record.get("newsid");
    			var website = record.get("website");
    			if(newsid!=null && newsid!=""){
    				if(website!=null && website!=""){
    					window.location.href = website;
    				}else{
    					window.location.href="content_news.jsp?newsid="+newsid;
    				}
				}
    		}   
        },
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
    
    //布局
    new Ext.Viewport({
		layout:'fit',
		items:[{ 
 			frame:true,
 			title:'公示与新闻',
			layout:'border',
			items:[/*{
				region:'north',
				width:'78%',
				iconCls:'menu-18',
				items:searchForm
			},*/{
				//frame:true,
				//title:'活动公示',
				region:'west',
				width:'76.5%',
				iconCls:'menu-18',
				items:[searchForm,grid]
			},{
				region:'center',
				padding:'80 0 0 5',
				//layout:'fit',
				items:[{
					cls :'returnBtn',
					width:90,
					style:'margin-right:10%;margin-top:10%',
					xtype:"button",
					text:"返<i class='ikg2'></i>回",
					handler:function(){
							   window.location.href="../../menu/personal-affairs.html";
					}
				},newsGrid]
			}]
		}]
	});

});