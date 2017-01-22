Ext.BLANK_IMAGE_URL = '../../../ext/resources/images/default/s.gif';
/*!
 * 场地管理
 */
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//供应商下拉数据
    var groundStore = new Ext.data.JsonStore({
		url: '/MyStock/apply_findActionComb.do',
	    root: 'root',
	    totalProperty: 'total',
	    fields: ['value','text'],
	    listeners:{
	    	load:function(s){
    			
	    		r = s.getAt(0);
	    		if(r){
	    			Ext.getCmp("activeCombo").onSelect(r, 0);
	    			
	    		}
	    	}
	    }
	});

    var groundStore2 = new Ext.data.JsonStore({
    	url: '/MyStock/apply_findActionComb2.do',
    	root: 'root',
    	totalProperty: 'total',
    	fields: ['value','text'],
    	listeners:{
    	load:function(s){
    	
    	r = s.getAt(0);
    	if(r){
    		Ext.getCmp("tab_comb1").onSelect(r, 0);
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
			title:"查询设置",
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
								name:"number",
								fieldLabel:"活动编号"
							}/*,{
								width:150,
								buttonAlign : 'center',
								xtype:"button",
								text:"批量通过",
								name:"pass"
								
								
								
							}*/
							/*,{
								xtype:'button',id: "btn1",
							    text: "小按钮",
							    handler: function () {
							        Ext.MessageBox.alert("提示", "通过handler配置项添加的事件");
							    }
							}*/]
						},{

							width:260,
							items:[{
								id:'tab_comb1',
								width : 150,
								xtype:"combo",
								hiddenName:'size',
								fieldLabel:"活动规模",
		   						mode: 'local',
		   						triggerAction: 'all',
								valueField :"value",
		   						displayField: "text",
		   						allowBlank : true,
		   						editable : false,
								store : groundStore2,
								emptyText: '全部',
								listeners:{
									select : function(a,b){
								
									searchForm.getForm().findField("size").setValue(b.data.text);
									
									}
								}
							}/*,{

								width:150,
								buttonAlign : 'center',
								xtype:"button",
								text:"批量不通过",
								name:"unpass"
								
							}*/]
								
						},{

							width:260,
							items:[{
								width:100,
								id:'activeCombo',
								xtype:'combo',
								hiddenName:'actionName',
								fieldLabel:'活动名称',
								mode: 'local',
								triggerAction: 'all',
								valueField :'value',
								displayField: 'text',
								allowBlank : true,
								editable : false,
								store : groundStore,
								emptyText: '全部',
								listeners:{
									select : function(a,b){
									
									searchForm.getForm().findField("actionName").setValue(b.data.text);
									
									}
								}
						}]
								
						},{
					width:260,
					items:[{
						width:75,
						xtype:"button",
						iconCls:"btn-list",
						text:"查询",
						handler:function(){
							var f = searchForm.getForm();
							if (f.isValid()) {
								var _params = f.getValues();
								//添加分页信息
								//Ext.apply(_params,{start:0, limit:15});
								searchForm.getForm().reset();
								store.load({params:_params});
							}
						}
					},{
						width:75,
						buttonAlign : 'center',
						xtype:"button",
						text:"审核",
						name:"checking",
						handler:function(){
			        		var recordapply= grid.getSelectionModel().getSelected(); 
							if(!recordapply){
			                	Ext.Msg.alert('信息提示','请选择要审核的活动');
							}else{
								checkingWindow.show();
								checkaddWindow.getForm().loadRecord(recordapply);
								 
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
			},{
				xtype:'hidden',
				name:'actionName'
			},{
				xtype:'hidden',
				name:'actiontype'
			},{
				xtype:'hidden',
				name:'size'
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
		{ name:'activityplace', type:'string'},
		{ name:'timeofduration', type:'string'},
		{ name:'activitycontent', type:'string'},
		{ name:'signupendtime', type:'string'},
		{ name:'score', type:'int'},
		{ name:'applystyle', type:'string'},
		{ name:'checktime', type:'string'}
	];
	
    var store = new Ext.data.JsonStore({
	    url: '/MyStock/checkactivitylist_findPageApply.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: {params:{start:0, limit:15}},
	    fields: Activity
	});
    
	var sm = new Ext.grid.CheckboxSelectionModel();//单选{singleSelect: true}
    var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[sm,
			    {header: '活动名称', width: 100,align:'center', dataIndex: 'activityname'},
			    {header:'活动类别',width:100,align:'center',dataIndex:'activitygenre'},
			    {header:'活动时间',width:100,align:'center',dataIndex:'activitytime'},
	            {header: '申请人', width: 100,align:'center', dataIndex: 'applyuser'},
	            {header: '所在学期', width: 100,align:'center', dataIndex: 'inschoolterm'},
	            {header: '活动地点', width: 100,align:'center', dataIndex: 'activityplace'},
	            {header: '考评分', width: 100,align:'center', dataIndex: 'score'},
	            {header: '状态', width: 100,align:'center', dataIndex: 'applystyle'},
	            {xtype: 'actioncolumn',header : '审查', width: 100,align : 'center',menuDisabled : true,
	                items: [{
	                    icon: '../../../img/btn/edit2.gif',
	                    tooltip: '查看详情',
	                    handler: function(grid, rowIndex, colIndex) {
	                      var recordapply= grid.getSelectionModel().getSelected();
	                     /*  var record = store.getAt(rowIndex);
	                        var activityid = record.get('activityid')*/
							checkingWindow.show();
							checkaddWindow.getForm().loadRecord(recordapply);
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
        iconCls:'menu-51',
        
        bbar: new Ext.PagingToolbar({
            pageSize: 15,
            store: store,
            displayInfo: true
        })
    });
//增加场地窗口
    var addForm = new Ext.FormPanel({
		layout : 'form',
		baseCls:'x-plain',
		autoScroll:true,//滚动条
		url : '/MyStock/apply_saveOrUpdate.do',
        fileUpload:true,  
		labelWidth:70,
		border : false,
		padding : '15 10 0 8',
		defaults : {
			anchor : '100%',
			xtype : 'textfield'
		},
		items:[{
				name:'groundname',
				fieldLabel:'场地名称',
				maxLength :50,
				allowBlank : false
			},{
				name:'time',
				fieldLabel:'活动时间',
				maxLength :50,
				allowBlank : false
			},{
				name:'actionName',
				fieldLabel:'活动名称',
				maxLength :50,
				allowBlank : false
			},{
				name:'proposer',
				fieldLabel:'申请人',
				maxLength :50,
				allowBlank : false
			},{
				name:'park',
				fieldLabel:'所在组织',
				maxLength :50,
				allowBlank : false
			},{
				name:'teacher',
				fieldLabel:'指导老师',
				maxLength :50,
				allowBlank : false
			},{
				name:'counselor',
				fieldLabel:'辅导员',
				maxLength :50,
				allowBlank : false
			},{
				name:'tel',
				fieldLabel:'电话',
				maxLength :50,
				allowBlank : false
			},{
				name:'size',
				fieldLabel:'规模',
				maxLength :50,
				allowBlank : false
			}]
	});
    
    var addWindow = new Ext.Window({
		title : '添加窗口',
		width:400,
		height:360,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [addForm],
		buttons : [{
			text : '保存',
			handler : function() {
				if (addForm.getForm().isValid()) {
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
		}, {
			text : '取消',
			handler : function() {
				addWindow.hide();
			}
		}]
	});
    
    
    //审核窗口
    var checkaddWindow=new Ext.FormPanel({
    	
		layout : 'form',//纵向布局
		baseCls:'x-plain',//基本色调
		url : '/MyStock/apply_saveOrUpdate.do',
        fileUpload:true,  
        autoScroll:true,//滚动条
		labelWidth:80,
		border : false,
		padding : '23 30 0 30',//上右下左
		defaults : {
			anchor : '100%',
			xtype : 'textfield'
		},
		items:[{
				name:'activityname',
				fieldLabel:'活动名称',
				maxLength :50,
				readOnly:true,
				allowBlank : false
			},{
				name:'activitygenre',
				fieldLabel:'活动类型',
				maxLength :50,
				readOnly:true,
				allowBlank : false
			},{
				name:'applyuser',
				fieldLabel:'申请人',
				maxLength :50,
				readOnly:true,
				allowBlank : false
			},{
				name:'studentnum',
				fieldLabel:'学号',
				readOnly:true,
				maxLength :50,
				allowBlank : false
			},{
				name:'studentphonenum',
				fieldLabel:'联系电话',
				maxLength :50,
				readOnly:true,
				allowBlank : false
			},{
				name:'organizename',
				fieldLabel:'所在组织',
				maxLength :50,
				readOnly:true,
				allowBlank : false
			},{
				name:'teacher',
				fieldLabel:'指导老师',
				maxLength :50,
				readOnly:true,
				allowBlank : false
			},{
				name:'phonenum',
				fieldLabel:'指导老师电话',
				maxLength :50,
				readOnly:true,
				allowBlank : false
			},{
				name:'inschoolterm',
				fieldLabel:'所在学期',
				maxLength :50,
				readOnly:true,
				allowBlank : false
			},{
				name:'faceobj',
				fieldLabel:'面向对象',
				maxLength :50,
				readOnly:true,
				allowBlank : false
			},{
				name:'activityplace',
				fieldLabel:'活动地点',
				maxLength :50,
				readOnly:true,
				allowBlank : false
			},{
				name:'timeofduration',
				fieldLabel:'时长',
				maxLength :50,
				readOnly:true,
				allowBlank : false
			},{
				name:'capacity',
				fieldLabel:'规模',
				maxLength :50,
				readOnly:true,
				allowBlank : false
			},{
				name:'activitytime',
				fieldLabel:'活动日期',
				maxLength :50,
				readOnly:true,
				allowBlank : false
			},{
				name:'signupendtime',
				fieldLabel:'报名截止日期',
				maxLength :50,
				readOnly:true,
				allowBlank : false
			},{
				name:'score',
				fieldLabel:'活动积分',
				maxLength :50,
				readOnly:true,
				allowBlank : false
			},{
				name:'activitycontent',
				fieldLabel:'活动内容',
				maxLength :50,
				readOnly:true,
				allowBlank : false
			},{
				xtype : 'hidden',
			    name : 'activityid'
			}]
	
    });
    
    
    
    
    
    var checkingWindow=new Ext.Window({
    	

		title : '审核窗口',
		width:500,
		height:400,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [checkaddWindow],
		buttons : [{
			text : '通过',
			handler : function() {
				if (checkaddWindow.getForm().isValid()) {
					checkaddWindow.getForm().submit({
						success : function(form, action) {
							Ext.Msg.alert('信息提示',action.result.message);
							checkingWindow.hide();
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
		}, {
			text : '不通过',
			handler : function() {
			var record= grid.getSelectionModel().getSelected();
			Ext.MessageBox.confirm('提示', '确定不通过？', function(c) {
				   if(c=='yes'){
					   Ext.Ajax.request({
				   			url : "/MyStock/apply_saveOrUpdate.do",
				   			params:{ flag:"f",id : record.get("id"),proposer : record.get("proposer"),number : record.get("number"),tel : record.get("tel"),counselor : record.get("counselor"),actionName : record.get("actionName"),actionContent : record.get("actionContent"),actionType : record.get("actionType"),park : record.get("park"),teacher : record.get("teacher"),size : record.get("size"),shifou : record.get("shifou"),zrsm : record.get("zrsm")}
					   		
					   });
					   store.reload();
				   	}
			});
			checkingWindow.hide();
			
			}
		}]
	
    	
    	
    });
    

    
    //布局
    new Ext.Viewport({
		layout:"fit",
		items:[{
			frame:true,
			title:"当前库存查询",
			iconCls:'menu-15',
			layout:"border",
			items:[searchForm,grid]
		}],
		listeners:{
		render:function(){
    		groundStore.load();
    		groundStore2.load();
		}
	}

	});
    

});