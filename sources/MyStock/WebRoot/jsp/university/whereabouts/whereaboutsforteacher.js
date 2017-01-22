Ext.BLANK_IMAGE_URL = '../../../ext/resources/images/default/s.gif';
/*!
 * 活动申请
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
	
	//查询表单
	var searchForm = new Ext.FormPanel({
		region:"north",
		height: 100,
		border : false,
		layout : "form",
		padding : "5 20 0 20",
		items:[{
			xtype:"fieldset",
			title:"去向统计",
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
								width:150,
								labelWidth:30,
								xtype:"textfield",
								name:"censususername",
								fieldLabel:"姓名"
							},{
								width:150,
								labelWidth:30,
								xtype:"textfield",
								name:"teacher",
								fieldLabel:"班主任"
							}]
						},{
							width:260,
							items:[{
								width:150,
								labelWidth:30,
								xtype:"textfield",
								name:"censususernum",
								fieldLabel:"学号"
							},{
								width:150,
								labelWidth:30,
								xtype:"textfield",
								name:"counsellor",
								fieldLabel:"辅导员"
							}]
								
						},{
							width:260,
							items:[{
								width:150,
								labelWidth:30,
								id:'bjCombo',
								xtype:'combo',
								hiddenName:'classes',
								fieldLabel:'班<i class="ikg1"></i>级',
								mode: 'local',
								triggerAction: 'all',
								valueField :"value",
								displayField: "text",
								editable : false,
								store : bjStore,
								emptyText: '全部',
								/*listeners:{
									select : function(a,b){
										addForm.getForm().findField("classes").setValue(b.data.text);
									}
								}*/
						},{
							width:75,
							xtype:"button",
							style:'margin-left:180px',
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
	var Wheresaboutscensus = [
	        		{ name:'censusid', type:'string'},
	        		{ name:'censususernum', type:'string'},
	        		{ name:'censususername', type:'string'},
	        		{ name:'launchid', type:'string'},
	        		{ name:'launchname', type:'string'},
	        		{ name:'holidaytime', type:'string'},
	        		{ name:'classes', type:'string'},
	        		{ name:'mojar', type:'string'},
	        		{ name:'dorm', type:'string'},
	        		{ name:'userphone', type:'string'},
	        		{ name:'teacher', type:'string'},
	        		{ name:'counsellor', type:'string'},	//辅导员
	        		{ name:'wheresfact', type:'string'},	//去向实情
	        		{ name:'termini', type:'string'},		//目的地
	        		{ name:'urgentphone', type:'string'},
	        		{ name:'leaveschooltime', type:'string'},
	        		{ name:'returnschooltime', type:'string'},
	        		{ name:'daysnum',type:'string'},
	        		{ name:'parentsknows', type:'string'},
	        		{ name:'parentsphone', type:'string'},
	        		{ name:'enteragreement', type:'string'}
	        	];
	
	//获取response数据
	var store = new Ext.data.JsonStore({
	    url: '/MyStock/Whereabouts_allCensusList.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: {params:{start:0, limit:15}},
	    fields: Wheresaboutscensus,
	});
	
	//查看详情按钮调用方法
	var seeDetail = function(grid, rowIndex, colIndex){
		//alert("查看详情");
    	var record = grid.getStore().getAt(rowIndex); 
    	addForm.getForm().findField("launchname").setValue(record.data.launchname);
    	addForm.getForm().findField("holidaytime").setValue(record.data.holidaytime);
    	addForm.getForm().findField("censususernum").setValue(record.data.censususernum);
    	addForm.getForm().findField("censususername").setValue(record.data.censususername);
    	addForm.getForm().findField("classes").setValue(record.data.classes);
    	//addForm.getForm().findField("major").setValue(record.data.mojar);
    	addForm.getForm().findField("dorm").setValue(record.data.dorm);
    	addForm.getForm().findField("userphone").setValue(record.data.userphone);
    	addForm.getForm().findField("teacher").setValue(record.data.teacher);
    	addForm.getForm().findField("counsellor").setValue(record.data.counsellor);
    	addForm.getForm().findField("wheresfact").setValue(record.data.wheresfact);
    	addForm.getForm().findField("termini").setValue(record.data.termini);
    	addForm.getForm().findField("urgentphone").setValue(record.data.urgentphone);
    	addForm.getForm().findField("leaveschooltime").setValue(record.data.leaveschooltime);
    	addForm.getForm().findField("returnschooltime").setValue(record.data.returnschooltime);
    	addForm.getForm().findField("parentsknows").setValue(record.data.parentsknows);
    	addForm.getForm().findField("parentsphone").setValue(record.data.parentsphone);
    	addWindow.show();
    };
	
	var rownumber = new Ext.grid.RowNumberer();//行号
    var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[rownumber,
				{header: '姓名', width: 100,align:'center', dataIndex: 'censususername'},
			    {header: '学号', width: 100,align:'center', dataIndex: 'censususernum'},
			    {header: '班级', width: 100,align:'center', dataIndex: 'classes'},
			    {header: '班主任', width: 100,align:'center', dataIndex: 'teacher'},
			    {header: '辅导员', width: 100,align:'center', dataIndex: 'counsellor'},
			    {header: '去向', width: 100,align:'center', dataIndex: 'wheresfact'},
			    {header: '离校时间', width: 100,align:'center', dataIndex: 'leaveschooltime'},
			    {header: '返校时间', width: 100,align:'center', dataIndex: 'returnschooltime'},
			    {header: '天数', width: 100,align:'center', dataIndex: 'daysnum'},
	            {header: '父母是否知情', width: 100,align:'center', dataIndex: 'parentsknows'},
	            {xtype: 'actioncolumn',header : '操作', width: 150,align : 'center',
	            	menuDisabled : true,
	                items: [{
	                    icon: '../../../img/btn/see.png',
	                    //tooltip: '查看详情',
	                    handler: function(grid, rowIndex, colIndex){
	                	seeDetail(grid, rowIndex, colIndex);
	                	}
	                }]
	           }]
        }),
        rownumber:rownumber,//行号
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
        //autoExpandColumn: 'gysbz', //自动扩展列
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
    
  //去向统计提交窗口
    var addForm = new Ext.FormPanel({
		layout : 'form',
		frame:true,
		labelWidth:60,
		border : false,
		padding : '15 10 0 150',
		defaults : {
			anchor : '80%',
			cls:'borderNone'
		},
		items:[{
			xtype : 'textfield',
			name:'launchname',
			fieldLabel:'假<i class="ikg2"></i>日',
			readOnly:true,
			
			maxLength :20
		},{
			xtype : 'textfield',
			name:'holidaytime',
			fieldLabel:'假日时间',
			readOnly:true,
			//allowBlank : false,
			maxLength :45
		},{
			layout:'column',
			items:[{
				columnWidth:.6,
				layout:'form',
				defaults:{
					anchor : '95%',
					cls:'borderNone'
				},
				items:[{
					xtype : 'textfield',
					name:'censususername',
					fieldLabel:'姓<i class="ikg2"></i>名',
					readOnly:true,
					//allowBlank : false,
					maxLength :20
				},{
					xtype : 'textfield',
					name:'classes',
					readOnly:true,
					fieldLabel:'班<i class="ikg2"></i>级',
					//allowBlank : false,
					maxLength :20
				},{
					xtype : 'textfield',
					name:'dorm',
					readOnly:true,
					fieldLabel:'寝<i class="ikg2"></i>室',
					//allowBlank : false,
					maxLength :20
				},{
					xtype:'textfield',
					name:'teacher',
					fieldLabel:'班<i class="ikg1"></i>主<i class="ikg1"></i>任',
					//allowBlank : false,
					maxLength :20
				},{
					xtype:'textfield',
					name:'wheresfact',
					fieldLabel:'去向情况',
					//allowBlank : false,
					maxLength :50
				}]
			},{
				columnWidth:.4,
				layout:'form',
				defaults:{
					anchor : '95%',
					cls:'borderNone'
				},
				items:[{
					xtype : 'textfield',
					name:'censususernum',
					fieldLabel:'学<i class="ikg2"></i>号',
					readOnly:true,
					//allowBlank : false,
					maxLength :10
				}/*,{
					xtype:'textfield',
					name:'major',
					fieldLabel:'专<i class="ikg2"></i>业',
					readOnly:true,
					//allowBlank : false,
					maxLength :10
				}*/,{ xtype : 'textfield',
					name:'userphone',
					readOnly:true,
					fieldLabel:'联系电话',
					//allowBlank : false,
					maxLength :11
				},{
					xtype:'textfield',
					name:'counsellor',
					fieldLabel:'辅<i class="ikg1"></i>导<i class="ikg1"></i>员',
					//allowBlank : false,
					maxLength :30
				},{
					xtype :"textfield",
					name:'termini',
					fieldLabel:'目<i class="ikg1"></i>的<i class="ikg1"></i>地',
					//allowBlank : false,
					maxLength :10
				}]
			}]
		},{
			xtype:'numberfield',
			name:'urgentphone',
			fieldLabel:'紧急电话',
			//allowBlank : false,
			maxLength :180
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
					name:'leaveschooltime',
					fieldLabel:'离校时间',
				},{
					xtype : 'textfield',
					id   : 'atype',
					name:'parentsknows',
					fieldLabel: "父母知情",  
				}]
			},{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%',
					cls:'borderNone'
				},
				items:[{
					xtype : 'textfield',
					name:'returnschooltime',
					fieldLabel:'返校时间',
				},{
					xtype : 'textfield',
					name:'parentsphone',
					fieldLabel:'父母电话',
				}]
			}]
		}]
	});
    
    //添加form窗口
    var addWindow = new Ext.Window({
		title : '去向信息',
		width:750,
		height:380,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [addForm],
	});
    
    //布局
    new Ext.Viewport({
		layout:"fit",
		items:[{
			frame:true,
			title:"去向统计",
			iconCls:'menu-15',
			layout:"border",
			items:[searchForm,grid]
		}],
		listeners:{
			render:function(){
    			bjStore.load();
	    		//groundStore2.load();
			}
		}
	});
});