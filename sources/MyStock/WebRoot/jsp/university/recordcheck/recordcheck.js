Ext.BLANK_IMAGE_URL = '../../../ext/resources/images/default/s.gif';
/*!
 * 活动管理
 */
Ext.onReady(function(){
	Ext.QuickTips.init();

	//班级下拉菜单
    var bjStore = new Ext.data.JsonStore({
		url: '/MyStock/LeaveInfo_findClassComb1.do',
	    root: 'root',
	    totalProperty: 'total',
	    fields: ['value','text'],
	});
    bjStore.on('load', function(store, record, options) {   
    	store.insert(0, new Ext.data.Record({'text': '全部', 'value': ''}));   
    });
    
    //所在学期下拉菜单
    var szxqStore = new Ext.data.JsonStore({
		url: '/MyStock/Applyactivity_findSzxqComb.do',
	    root: 'root',
	    totalProperty: 'total',
	    fields: ['value','text'],
	});
    szxqStore.on('load', function(store, record, options) {   
    	store.insert(0, new Ext.data.Record({'text': '全部', 'value': ''}));   
    });
	//查询表单
	var searchForm = new Ext.FormPanel({
		region:"north",
		height: 80,
		border : false,
		layout : "form",
		padding : "5 20 0 10",
		items:[{
			xtype:"fieldset",
			title:"查询设置",
			padding:"0 20 0 10",
			items:[{
				layout:"column",
				defaults:{
					xtype:"container",
					autoEl:"div",
					labelAlign:"right",
					layout:"form"
				},
				items:[{
					width:230,
					items:[{
								width:120,
								labelWidth:30,
								xtype:"textfield",
								name:"username",
								fieldLabel:"姓名"
							}]
						},{
							width:230,
							items:[{
								width:120,
								labelWidth:30,
								xtype:"textfield",
								name:"usernum",
								fieldLabel:"学号"
							}]
								
						},{
							width:230,
							items:[{
								width:120,
								labelWidth:30,
								id:'bjCombo',
								xtype:'combo',
								hiddenName:'classes',
								fieldLabel:'班级',
								mode: 'local',
								triggerAction: 'all',
								valueField :"value",
								displayField: "text",
								editable : false,
								store : bjStore,
								emptyText: '全部',
								listeners:{
									select : function(a,b){
										addForm.getForm().findField("classes").setValue(b.data.text);
									}
								}
						}]
					},{
						width:260,
						items:[{
							id:'szxqCombo',
							xtype:'combo',
							hiddenName:'inschoolterm',
							fieldLabel:'所在学期',
							mode: 'local',
							triggerAction: 'all',
							valueField :"value",
							displayField: "text",
							editable : false,
							emptyText: '全部',
							store : szxqStore,
							width: 150,
							listeners:{
								select : function(a,b){
									addForm.getForm().findField("inschoolterm").setValue(b.data.text);
								}
							}
					}]
					},{
					width:100,
					items:[{
						cls :'returnBtn',
						width:75,
						xtype:"button",
						//style:'margin-left:10px',
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
	
	//fields类声明
	var StudentDTO = [
	                { name:'id', type:'int'},
		      		{ name:'xh', type:'string'},
		      		{ name:'xm',type:'string'},
		      		{ name:'bjdm',type:'string'},
		      		{ name:'headmaster', type:'string'},
		      		{ name:'instructor', type:'string'},
		      		{ name:'inschoolterm', type:'string'},
		      		{ name:'phone',type:'string'},
		      		{ name:'qsmc',type:'string'},
		      		{ name:'gross', type:'int'},
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
	
	//获取response数据
	var store = new Ext.data.JsonStore({
	    url: '/MyStock/Applyactivity_recordcheck.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: {params:{start:0, limit:15}},
	    fields: StudentDTO
	});
	
	var storeDetail = new Ext.data.JsonStore({
	    url: '/MyStock/Applyactivity_recordcheckDetail.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: false,
	    fields: Attend
	});
	
	//详情方法
	var getDetail= function(grid, rowIndex, colIndex){
		//alert("查看详情");
    	var record = grid.getStore().getAt(rowIndex); 
    	storeDetail.load({
    				url:'/MyStock/Applyactivity_recordcheckDetail.do',
    		        params:{start:0,limit:15,studentid:record.data.id,inschoolterm:record.data.inschoolterm},
    		        scope:this
    	});
    	addForm.getForm().findField("xh").setValue(record.data.xh);
    	addForm.getForm().findField("xm").setValue(record.data.xm);
    	addForm.getForm().findField("bjmc").setValue(record.data.bjdm);
    	addForm.getForm().findField("inschoolterm").setValue(record.data.inschoolterm);
    	addForm.getForm().findField("instructor").setValue(record.data.instructor);
    	addForm.getForm().findField("phone").setValue(record.data.phone);
    	addForm.getForm().findField("qsmc").setValue(record.data.qsmc);
    	//addForm.getForm().findField("returnschooltime").setValue(record.data.returnschooltime);
    	addForm.getForm().findField("gross").setValue(record.data.gross);
    	
    	addWindow.show();
    };
    
    //debugger;
	var rownumber = new Ext.grid.RowNumberer();//行号
    var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[rownumber,
			    {header:'姓名', width: 100,align:'center', dataIndex: 'xm'},
			    {header:'学号',width:100,align:'center',dataIndex:'xh'},
			    {header:'班级',width:100,align:'center',dataIndex:'bjdm'},
			    {header:'班主任',width:100,align:'center',dataIndex:'headmaster'},
			    {header:'辅导员',width:100,align:'center',dataIndex:'instructor'},
			    {header:'联系电话',width:100,align:'center',dataIndex:'phone'},
			    {header:'寝室',width:100,align:'center',dataIndex:'qsmc'},
			    {header:'考评总分',width:100,align:'center',dataIndex:'gross'},
			    //{header:'异常',width:100,align:'center',dataIndex:''},
			    {xtype: 'actioncolumn',header : '操作', width: 160,align : 'center',
	            	menuDisabled : true,
	                items: [{
	                    icon: '../../../img/btn/see.png',
	                    //tooltip: '查看详情',
	                    handler: function(grid, rowIndex, colIndex){
	                		getDetail(grid, rowIndex, colIndex);
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
    
    var rownumberDetail = new Ext.grid.RowNumberer();//行号
    var gridDetail = new Ext.grid.GridPanel({
        store: storeDetail,
        height:200,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[rownumber,
			    {header:'活动名称', width: 110,align:'center', dataIndex: 'activityname'},
			    {header:'活动时间',width:110,align:'center',dataIndex:'activitytime'},
			    {header:'活动负责人',width:110,align:'center',dataIndex:'counsellor'},
			    {header:'活动地点',width:110,align:'center',dataIndex:'activityplace'},
			    {header:'所加分值',width:110,align:'center',dataIndex:'gotscore'}]
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
   
    //详情显示窗口
    var addForm = new Ext.FormPanel({
		layout : 'form',
		region:"north",
		frame:true,
		labelWidth:70,
		border : false,
		labelAlign:"right",
		padding : '15 10 80 50',
		items:[{
			layout:'column',
			items:[{
				columnWidth:.33,
				layout:'form',
				defaults:{
					anchor : '78%',
					cls:'borderNone'
				},
				items:[{
					xtype : 'textfield',
					name:'xm',
					fieldLabel:'姓<i class="ikg2"></i>名',
					maxLength :20,
					readOnly:true
				}]
			},{
				columnWidth:.33,
				layout:'form',
				defaults:{
					anchor : '78%',
					cls:'borderNone'
				},
				items:[{
					xtype : 'textfield',
					name:'xh',
					fieldLabel:'学<i class="ikg2"></i>号',
					readOnly:true
				}]
			},{
				columnWidth:.33,
				layout:'form',
				defaults:{
					anchor : '78%',
					cls:'borderNone'
				},
				items:[{
					xtype : 'textfield',
					name:'bjmc',
					fieldLabel:'班<i class="ikg2"></i>级',
					readOnly:true
				}]
			}]
		},{
			layout:'column',
			items:[{
				columnWidth:.33,
				layout:'form',
				defaults:{
					anchor : '78%',
					cls:'borderNone'
				},
				items:[{
					xtype : 'textfield',
					name:'instructor',
					fieldLabel:'辅<i class="ikg1"></i>导<i class="ikg1"></i>员',
					readOnly:true,
					maxLength :20
				}]
			},{
				columnWidth:.33,
				layout:'form',
				defaults:{
					anchor : '78%',
					cls:'borderNone'
				},
				items:[{
					xtype : 'textfield',
					name:'phone',
					fieldLabel:'联系电话',
					readOnly:true
				}]
			},{
				columnWidth:.33,
				layout:'form',
				defaults:{
					anchor : '78%',
					cls:'borderNone'
				},
				items:[{
					xtype : 'textfield',
					name:'qsmc',
					fieldLabel:'寝<i class="ikg2"></i>室',
					readOnly:true
				}]
			}]
		},{
			layout:'column',
			items:[{
				columnWidth:.33,
				layout:'form',
				defaults:{
					anchor : '78%',
					cls:'borderNone'
				},
				items:[{
					xtype : 'textfield',
					name:'inschoolterm',
					fieldLabel:'学<i class="ikg2"></i>期',
					readOnly:true
				}]
			},{
				columnWidth:.33,
				layout:'form',
				defaults:{
					anchor : '78%',
					cls:'borderNone'
				},
				items:[{
					xtype : 'textfield',
					name:'gross',
					fieldLabel:'考评总分',
					readOnly:true
				}]
			}]
		},{
			xtype:'hidden',
			name:'activityid'
		}]
	});
    
   
    //添加form窗口
    var addWindow = new Ext.Window({
		title : '详情',
		width:940,
		height:450,
		closeAction:'hide',
		modal : true,
		layout : 'border',
		items : [addForm,gridDetail],
		buttons : []
	});
    
    
    //布局
    new Ext.Viewport({
		layout:"fit",
		items:[{
			frame:true,
			title:"记实考评",
			iconCls:'menu-15',
			layout:"border",
			items:[searchForm,grid]
		}],
		listeners:{
			render:function(){
    			bjStore.load();
    			szxqStore.load();
			}
		}
	});
});