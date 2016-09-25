/*!
 *班主任管理
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
					width:250,
					items:[{
					    labelWidth:100,
						xtype:"textfield",
						name:'zgh',
						fieldLabel:"职工号",
						anchor:"90%"
					}]
				},{
					width:250,
					items:[{
					    labelWidth:100,
						xtype:"textfield",
						name:'xm',
						fieldLabel:"姓名",
						anchor:"90%"
					}]
				},{
					width:250,
					items:[{
						width:75,
						xtype:"button",
						text:"查询",
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
					width:250,
					items:[{
						width:75,
						xtype:"button",
						cls :'returnBtn',
						text:"返回",
						 listeners : {
							  click : function (btn) {
								  window.history.go(-1);
							}
				        		
				        }
					}]
				
				
				}]
			}]
		}]
		
	});
	
	var UuserObj = [
		{ name:'id', type:'int'},
		{ name:'zgh', type:'string'},
		{ name:'xm', type:'string'},
		{ name:'xb', type:'string'},
		{ name:'ssyq', type:'string'},
		{ name:'phone', type:'string'},
		{ name:'hclass', type:'string'}
	];
	
	var store = new Ext.data.JsonStore({
	    url: 'headMaster_findPageHeadMaster.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: {params:{start:0, limit:15}},
	    fields: UuserObj
	});
	
	var sm = new Ext.grid.CheckboxSelectionModel({singleSelect: false});//单选
    var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[sm,new Ext.grid.RowNumberer(),
	            {header: '职工号', width: 250,align:'center', dataIndex: 'zgh'},
	            {header: '姓名', width: 250,align:'center', dataIndex: 'xm'},
	            {header: '性别', width: 150,align:'center', dataIndex: 'xb'},
	            {header: '手机号', width: 150,align:'center', dataIndex: 'phone'},
	            {xtype: 'actioncolumn',header : '操作', width: 160,align : 'center',menuDisabled : true,
	                items: [{
	                    icon: '../../img/btn/resetPwd.png',
	                    handler: function(grid, rowIndex, colIndex){
	                		var record = grid.getStore().getAt(rowIndex); 
	                		Ext.MessageBox.confirm('重置密码提示', '是否为该用户重置密码？', function(c) {
	     					   if(c=='yes'){
	     					   		Ext.Ajax.request({
	     					   			url : "user_resetPwd.do",
	     					   			params:{ logincode : record.data.xh },
	     					   			success : function() {
	     					   				Ext.Msg.alert('信息提示','重置成功');
	     					   			}
	     					   		});
	     					    }
	     					});
	                	}
	                }]
	           }
	            ]
        }),
        sm:sm,
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
		loadMask : true,	//加载时的遮罩
		frame : true,
		region:"center",
        iconCls:'menu-62',
        layout : 'fit',
        viewConfig:{
        	forceFit: true, 
        	scrollOffset: 0
       	},
        tbar:['->',{
        	text:'导入班主任',
        	iconCls:'menu-11',
        	handler: function(){
        		importWindow.show();
        	}
        },'-',{
        	text:'增加',
        	iconCls:'btn-add',
        	handler: function(){
        		uWindow.show();
        		uForm.getForm().reset();
        	}
        },'-',{
        	text:'修改',
        	iconCls:'btn-edit',
        	handler: function(){
        		var record= grid.getSelectionModel().getSelected(); 
				if(!record){
                	Ext.Msg.alert('信息提示','请选择要修改的数据');
				}else{
	        		uWindow.show();
					uForm.getForm().loadRecord(record);
				}
        	}
        },'-',{
        	text:'删除',
        	iconCls:'btn-remove',
        	handler: function(){
        		var record= grid.getSelectionModel().getSelections();
				if(record.length == 0){
                	Ext.Msg.alert('信息提示','请选择要删除的数据');  
				}else{
					Ext.MessageBox.confirm('删除提示', '是否删除该用户？', function(c) {
					   if(c=='yes'){
						   var ids = "";   
                           for(var i = 0; i < record.length; i++){   
                               ids += record[i].get("id")   
                               if(i<record.length-1){   
                                   ids = ids + ",";   
                               }   
                           } 
					   		Ext.Ajax.request({
					   			url : "headMaster_deleteHeadMasters.do",
					   			params:{ ids : ids },
					   			success : function() {
					   				store.reload();
					   			}
					   		});
					    }
					});
				}
        	}
        },'-',{
        	text:'模板下载',
        	iconCls:'menu-12',
        	handler: function(){
            	Ext.MessageBox.confirm('信息提示',"确定下载《班主任导入模板》?",function(btn){
            		if(btn=="yes"){
            			window.location.href="fileDownload.do?fileDownKey=HeadMasterTemplate";
            		}
            	});
        	}
        }],
        
        bbar: new Ext.PagingToolbar({
            pageSize: 15,
            store: store,
            displayInfo: true
        })
    });
    
    var importForm = new Ext.FormPanel({
		layout : 'form',
		fileUpload:true,
		frame:true,
		url :'headMaster_importExcel.do',
		labelWidth:60,
		border : false,
		padding : '5 10 10 8',
		defaults : {
			anchor : '100%'
		},
		items:[{
			id:'control_id',
			xtype: 'textfield',  
            fieldLabel: '导入名单',  
            name: 'excel',  
            inputType: 'file'
		}]
	});
    
    //文件导入窗口
    var importWindow = new Ext.Window({
		title : '导入班主任',
		width:280,
		height:120,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [importForm],
		buttons : [{
			text : '确认导入',
			handler : function() {
				if (importForm.getForm().isValid()) {
					importForm.getForm().submit({
						success : function(form, action) {
							Ext.Msg.alert('信息提示',action.result.message);
							importWindow.hide();
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
		}]
	});
    
    var classesStore = new Ext.data.JsonStore({
	    url: 'classes_findClassesBySsyq.do',
	    root: 'root',
	    totalProperty: 'total',
	    fields : ["value", "text"]
	});
    //classesStore.load();

    var uForm = new Ext.FormPanel({
		layout : 'form',
		baseCls:'x-plain',
		labelWidth:60,
		border : false,
		padding : '15 10 0 8',
		defaults : {
			anchor : '100%',
			xtype : 'textfield'
		},
		items:[{
				name:'zgh',
				fieldLabel:'职工号',
				maxLength :20,
				allowBlank : false
			},{
				name:'xm',
				fieldLabel:'姓名',
				maxLength :20,
				allowBlank : false
			},{
					xtype:'combo',
					hiddenName:'xb',
					fieldLabel:'性别',
					mode: 'local',
					triggerAction: 'all',
					valueField :'value',
					displayField: 'text',
					allowBlank : false,
					editable : false,
					value:'男',
					store : new Ext.data.SimpleStore({
					    fields: ['value', 'text'],
					    data : [['男','男'],['女','女']]
					})
			},{
				xtype : 'numberfield',
				name:'phone',
				fieldLabel:'手机号',
				maxLength :11,
				regex:/^(13\d|15[^4,\D]|17[13678]|18\d)\d{8}|170[^346,\D]\d{7}$/,
				allowBlank : false
			},{
				xtype:'combo',
				hiddenName:'ssyq',
				fieldLabel:'所属园区',
				mode: 'local',
				triggerAction: 'all',
				valueField :'value',
				displayField: 'text',
				allowBlank : false,
				editable : false,
				value:'',
				listeners:{  
	                select:function(combo,record,index){  
                    try{  
                        var classes = Ext.getCmp('classes');  
                        classes.clearValue();  
                        classes.store.load(  
                             {  
                                 params:{  
                            	 	ssyq:combo.getValue()  
                                 }  
                             }     
                        );  
                    }catch(ex){  
                        alert("数据加载失败！");  
                    }  
                      
                }  
            }  ,
				store : new Ext.data.SimpleStore({
				    fields: ['value', 'text'],
				    data : [['丹青','丹青'],['云峰','云峰'],['蓝田','蓝田']]
				})
		},{
				xtype:'lovcombo',
				hiddenName:'hclass',
				id:'classes',
				fieldLabel:'管理班级',
				mode: 'local',
				triggerAction: 'all',
				valueField :'value',
				displayField: 'text',
				emptyText: '请选择',
				allowBlank : false,
				editable : false,
				store : classesStore
			},{
				xtype : 'hidden',
			    name : 'id'
			}]
	});
    
    var uWindow = new Ext.Window({
		title : '添加窗口',
		width:400,
		height:300,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [uForm],
		buttons : [{
			text : '保存',
			handler : function() {
				if (uForm.getForm().isValid()) {
					uForm.getForm().submit({
						url : 'headMaster_saveOrUpdateHeadMaster.do',
						success : function(form, action) {
							Ext.Msg.alert('信息提示',action.result.message);
							uWindow.hide();
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
				uWindow.hide();
			}
		}]
	});
    
    new Ext.Viewport({
		layout:"fit",
		items:[{
			frame:true,
			title:"辅导员查询",
			iconCls:'menu-51',
			layout:"border",
			items:[searchForm,grid]
		}]
	});

});