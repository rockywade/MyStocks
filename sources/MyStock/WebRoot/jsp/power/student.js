/*!
 * 学生管理
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
						name:'xh',
						fieldLabel:"学号",
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
		{ name:'xh', type:'string'},
		{ name:'xm', type:'string'},
		{ name:'xb', type:'string'},
		{ name:'phone', type:'string'},
		{ name:'qsh', type:'string'},
		{ name:'qsmc', type:'string'},
		{ name:'bjdm', type:'string'},
		{ name:'ssyq', type:'string'},
		{ name:'fqphone', type:'string'},
		{ name:'mqphone', type:'string'},
		{ name:'instructorid', type:'int'},
		{ name:'headmasterid', type:'int'},
		{ name:'newfriendsid', type:'int'},
		{ name:'instructor', type:'string'},
		{ name:'headmaster', type:'string'},
		{ name:'newfriends', type:'string'}
	];
	
	var store = new Ext.data.JsonStore({
	    url: 'student_findPageStudent.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: {params:{start:0, limit:15}},
	    fields: UuserObj
	});
	
	var sm = new Ext.grid.CheckboxSelectionModel({singleSelect: true});//单选
    var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[sm,new Ext.grid.RowNumberer(),
	            {header: '学号', width: 100,align:'center', dataIndex: 'xh'},
	            {header: '姓名', width: 100,align:'center', dataIndex: 'xm'},
	            {header: '性别', width: 80,align:'center', dataIndex: 'xb'},
	            {header: '手机号', width: 130,align:'center', dataIndex: 'phone'},
	            {header: '班级', width: 130,align:'center', dataIndex: 'bjdm'},
	            {header: '寝室', width: 100,align:'center', dataIndex: 'qsmc',renderer: function(value, cellmeta, record, rowIndex, columnIndex, store){
	            	return record.get('qsmc')+record.get('qsh');
	            	
	            }},
	            {header: '辅导员', width: 80,align:'center', dataIndex: 'instructor'},
	            {header: '班主任', width: 80,align:'center', dataIndex: 'headmaster'},
	            {header: '新生之友', width: 100,align:'center', dataIndex: 'newfriends'},
	            {xtype: 'actioncolumn',header : '操作', width: 120,align : 'center',menuDisabled : true,
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
	           }]
        }),
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
		loadMask : true,	//加载时的遮罩
		frame : true,
        iconCls:'menu-62',
        region:"center",
        layout : 'fit',
        viewConfig:{
        	forceFit: true, 
        	scrollOffset: 0
       	},
        tbar:['->',{
        	text:'导入学生',
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
        		var record= grid.getSelectionModel().getSelected();
				if(!record){
                	Ext.Msg.alert('信息提示','请选择要删除的数据');  
				}else{
					Ext.MessageBox.confirm('删除提示', '是否删除该用户？', function(c) {
					   if(c=='yes'){
					   		Ext.Ajax.request({
					   			url : "student_deleteStudent.do",
					   			params:{ id : record.get("id") },
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
            	Ext.MessageBox.confirm('信息提示',"确定下载《学生导入模板》?",function(btn){
            		if(btn=="yes"){
            			window.location.href="fileDownload.do?fileDownKey=StudentTemplate";
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
		url :'student_importExcel.do',
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
		title : '导入学生',
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
				name:'xh',
				fieldLabel:'学号',
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
				xtype : 'numberfield',
				name:'fqphone',
				fieldLabel:'父亲手机号',
				maxLength :11,
				regex:/^(13\d|15[^4,\D]|17[13678]|18\d)\d{8}|170[^346,\D]\d{7}$/,
				allowBlank : true
			},{
				xtype : 'numberfield',
				name:'mqphone',
				fieldLabel:'母亲手机号',
				maxLength :11,
				regex:/^(13\d|15[^4,\D]|17[13678]|18\d)\d{8}|170[^346,\D]\d{7}$/,
				allowBlank : true
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
                        var bjdm = Ext.getCmp('bjdm');  
                        bjdm.clearValue();  
                        bjdm.store.load(  
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
				xtype:'combo',
				hiddenName:'bjdm',
				id:'bjdm',
				fieldLabel:'班级',
				mode: 'local',
				triggerAction: 'all',
				valueField :'value',
				displayField: 'text',
				emptyText: '请选择',
				allowBlank : false,
				editable : false,
				store : classesStore
			},{
				name:'qsh',
				fieldLabel:'寝室号',
				maxLength :20,
				allowBlank : false
			},{
				name:'qsmc',
				fieldLabel:'寝室名称',
				maxLength :20,
				allowBlank : false
			},{
				xtype : 'hidden',
			    name : 'id'
			},{
				xtype : 'hidden',
			    name : 'instructorid'
			},{
				xtype : 'hidden',
			    name : 'headmasterid'
			},{
				xtype : 'hidden',
			    name : 'newfriendsid'
			}]
	});
    
    var uWindow = new Ext.Window({
		title : '学生',
		width:400,
		height:380,
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
						url : 'student_saveOrUpdateStudent.do',
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
			title:"学生查询",
			iconCls:'menu-51',
			layout:"border",
			items:[searchForm,grid]
		}]
	});

});