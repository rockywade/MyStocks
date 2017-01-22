/*!
 * 新生之友管理
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
		{ name:'phone', type:'string'},
		{ name:'ssyq', type:'string'}
	];
	
	var sm = new Ext.grid.CheckboxSelectionModel({singleSelect: true});//单选
	var store = new Ext.data.JsonStore({
	    url: '/MyStock/newFriends_findPageNewFriends.do',
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
	     					   			url : "/MyStock/user_resetPwd.do",
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
					   			url : "/MyStock/newFriends_deleteNewFriends.do",
					   			params:{ ids : ids },
					   			success : function() {
					   				store.reload();
					   			}
					   		});
					    }
					});
				}
        	}
        }],
        
        bbar: new Ext.PagingToolbar({
            pageSize: 15,
            store: store,
            displayInfo: true
        })
    });
    
    

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
				store : new Ext.data.SimpleStore({
				    fields: ['value', 'text'],
				    data : [['丹青','丹青'],['云峰','云峰'],['蓝田','蓝田']]
				})
		},{
				xtype : 'hidden',
			    name : 'id'
			}]
	});
    
    var uWindow = new Ext.Window({
		title : '新生之友',
		width:400,
		height:250,
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
						url : '/MyStock/newFriends_saveOrUpdateNewFriends.do',
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
			title:"新生之友查询",
			iconCls:'menu-51',
			layout:"border",
			items:[searchForm,grid]
		}]
	});


});