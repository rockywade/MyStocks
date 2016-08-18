
/**
 * 朋辈辅学线下管理端
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
						name:'fxxm',
						fieldLabel:"项目名称",
						anchor:"90%"
					}]
				},{
					width:200,
					labelWidth:60,
					items:[{
							xtype:'combo',
							hiddenName:'status',
							fieldLabel:'状态',
							mode: 'local',
							triggerAction: 'all',
							valueField :'value',
							displayField: 'text',
							anchor:"90%",
							editable : false,
							store : new Ext.data.SimpleStore({
							    fields: ['value', 'text'],
							    data : [['','全部'],['已发布','已发布'],['已发布/置顶','已发布/置顶'],
							            ['已发布/高亮','已发布/高亮'],['已发布/置顶/高亮','已发布/置顶/高亮'],
							            ['隐藏中','隐藏中']]
							}),
						width:80
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
						          window.location.href = '../menu/tutoring-answer-admin.html';
							}
				        		
				        }
					}]
				
				
				}]
			}]
		}]
		
	});
	
	var OfflineFdObj = [
	    	           { name:'xmid', type:'string'},
	    	           { name:'xmxh', type:'string'},
	    	           { name:'fxxm', type:'string'},
	    	           { name:'fxtime', type:'string'},//topTime
	    	           { name:'fxaddress', type:'string'},
	    	           { name:'fxteacher', type:'string'},
	    	           { name:'teachertel', type:'int'},
	    	           { name:'xmintro', type:'string'},
	    	           { name:'bmnumber', type:'int'},
	    	           { name:'xmzise', type:'int'},
	    	           { name:'bmstatus', type:'string'},
	    	           { name:'status', type:'string'},
	    	           { name:'focusTag', type:'string'},
	    	           { name:'plnumber', type:'int'},
	    	           { name:'bz', type:'string'},
	    	           { name:'topTime', type:'string'},
	    	           { name:'createtime', type:'string'},
	    	           { name:'createrid', type:'int'},
	    	           { name:'creatername', type:'string'}
	    	       ];
	
	
	
	var store = new Ext.data.JsonStore({
		 url: 'offlineFd_findPageOfflineFb.do',
		 root: 'root',
		 totalProperty: 'total',
		 autoLoad: {params:{start:0, limit:15}},
		 fields: OfflineFdObj
	});
	
	//new Ext.grid.RowNumberer(),：序号使用
	var sm = new Ext.grid.CheckboxSelectionModel({singleSelect: false});//单选
    var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[sm,
			    {header: '辅学项目',  width: 200, align:'center', dataIndex: 'fxxm'},
			    {header: '项目容量(人)',  width: 150, align:'center', dataIndex: 'xmzise'},
			    {header: '报名人数',  width: 100, align:'center', dataIndex: 'bmnumber'},
			    {header: '地点',  width: 150, align:'center', dataIndex: 'fxaddress'},
			    {header: '时间',  width: 200, align:'center', dataIndex: 'fxtime'},
			    {header: '编辑人',  width: 150, align:'center', dataIndex: 'creatername'},
	            {header: '状态',  width: 250, align:'center', dataIndex: 'status'},
	            {xtype: 'actioncolumn',header : '操作', width: 200,align : 'center',
	            	menuDisabled : true,
	                items: [ {
	                    icon: '../../img/btn/project.png',
	                    tooltip: '项目管理',
	                    getClass: function(value, metaData, record, rowIndex, colIndex, store, view) {      
	                		/*if('展现中'==record.data.status||'隐藏中'==record.data.status ||'展现中/已顶置'==record.data.status){
	                              return 'x-hidden'
	                        }*/
	                	},
	                    handler: function(grid, rowIndex, colIndex){
	                		mangeProject(grid, rowIndex, colIndex);
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
        iconCls:'menu-21',
        tbar:['->',{
        	text:'创建项目',
        	iconCls:'btn-add',
        	handler: function(){
        		addWindow.show();
        		addForm.getForm().reset();
        	}
        },'-',{
        	text:'隐藏',
        	iconCls:'btn-edit',
        	handler: function(){
    		var record= grid.getSelectionModel().getSelected();
			if(!record){
            	Ext.Msg.alert('信息提示','请选择要隐藏的项目');  
			}else{
				var status = record.get("status");
            	if(status == '隐藏中'){
            		alert("隐藏中！无需隐藏 了")
            		return false;
            	};
				Ext.MessageBox.confirm('隐藏提示', '确定隐藏该项目吗？', function(c) {
					var jsonArray = [];
					var recs=grid.getSelectionModel().getSelections(); 
					Ext.each(recs, function (item) {
						jsonArray.push(item.data);
                     });
					if(c=='yes'){
				   	Ext.Ajax.request({
				   		url : "offlineFd_saveOrUpdaOfflineFdAll.do",
				   	    params:{ifApproval:"0",offlineFdAll : Ext.encode(jsonArray)},
				   		success : function() {
				   		  store.reload();
				   	  }
				    });
					}
					
				});
			}
    	 }
        },'-',{
        	text:'取消隐藏',
        	iconCls:'btn-edit',
        	handler: function(){
    		var record= grid.getSelectionModel().getSelected();
			if(!record){
            	Ext.Msg.alert('信息提示','请选择需要取消隐藏的项目');  
			}else{
				var status = record.get("status");
            	if(status == '已发布'){
            		alert("已发布！无需取消隐藏 了")
            		return false;
            	};
				Ext.MessageBox.confirm('隐藏提示', '确定要取消隐藏该项目吗？', function(c) {
				  var jsonArray = [];
				   var recs=grid.getSelectionModel().getSelections(); 
				  Ext.each(recs, function (item) {
					 jsonArray.push(item.data);
                  });
				  if(c=='yes'){
			     	Ext.Ajax.request({
			   		 url : "offlineFd_saveOrUpdaOfflineFdAll.do",
			   	     params:{ifApproval:"1",offlineFdAll : Ext.encode(jsonArray)},
			   		 success : function() {
			   		  store.reload();
			   	      }
			       });
				}
				});
			}
    	}
        },'-',{
        	text:'置顶',
        	iconCls:'btn-edit',
        	handler: function(){
    		var record= grid.getSelectionModel().getSelected();
			if(!record){
            	Ext.Msg.alert('信息提示','请选择需要顶置的项目');  
			}else{
				var status = record.get("status");
            	if(status=="已发布/已置顶"){
            		alert("已置顶！无需再置顶")
            		return false;
            	};
				Ext.MessageBox.confirm('顶置提示', '确定该项目顶置吗？', function(c) {
					  var jsonArray = [];
					   var recs=grid.getSelectionModel().getSelections(); 
					  Ext.each(recs, function (item) {
						 jsonArray.push(item.data);
	                  });
					  if(c=='yes'){
				     	Ext.Ajax.request({
				   		 url : "offlineFd_saveOrUpdaOfflineFdAll.do",
				   	     params:{ifApproval:"2",status:status,offlineFdAll : Ext.encode(jsonArray)},
				   		 success : function() {
				   		  store.reload();
				   	      }
				       });
					}
					});
			}
    	}
        },'-',{
        	text:'取消置顶',
        	iconCls:'btn-edit',
        	handler: function(){
    		var record= grid.getSelectionModel().getSelected();
			if(!record){
				Ext.Msg.alert('信息提示','请选择需要取消顶置的项目');  
			}else{
				var status = record.get("status");
            	if(status.indexOf("置顶")==-1){
            		alert("不是置顶 ！无需取消置顶")
            		return false;
            	};
				Ext.MessageBox.confirm('顶置提示', '确定该项目取消顶置吗？', function(c) {
					  var jsonArray = [];
					  var recs=grid.getSelectionModel().getSelections(); 
					  Ext.each(recs, function (item) {
						 jsonArray.push(item.data);
	                  });
					  if(c=='yes'){
				     	Ext.Ajax.request({
				   		 url : "offlineFd_saveOrUpdaOfflineFdAll.do",
				   	     params:{ifApproval:"3",status:status,offlineFdAll : Ext.encode(jsonArray)},
				   		 success : function() {
				   		  store.reload();
				   	      }
				       });
					}
					});
			}
    	}
        },'-',{
        	text:'高亮',
        	iconCls:'btn-edit',
        	handler: function(){
    		var record= grid.getSelectionModel().getSelected();
			if(!record){
            	Ext.Msg.alert('信息提示','请选择需要高亮的项目');  
			}else{
				var status = record.get("status");
            	if(status == "已发布/已置顶/高亮" ||status == "已发布/高亮"){
            		alert("已经高亮！无需再高亮")
            		return false;
            	};
				Ext.MessageBox.confirm('顶置提示', '确定该项目高亮吗？', function(c) {
					  var jsonArray = [];
					   var recs=grid.getSelectionModel().getSelections(); 
					  Ext.each(recs, function (item) {
						 jsonArray.push(item.data);
	                  });
					  if(c=='yes'){
				     	Ext.Ajax.request({
				   		 url : "offlineFd_saveOrUpdaOfflineFdAll.do",
				   	     params:{ifApproval:"4",status:status,offlineFdAll : Ext.encode(jsonArray)},
				   		 success : function() {
				   		  store.reload();
				   	      }
				       });
					}
					});
			}
    	}
        },'-',{
        	text:'取消高亮',
        	iconCls:'btn-edit',
        	handler: function(){
    		var record= grid.getSelectionModel().getSelected();
			if(!record){
            	Ext.Msg.alert('信息提示','请选择需要高亮的项目');  
			}else{
				var status = record.get("status");
            	if(status.indexOf("高亮")==-1){
            		alert("不是高亮 ！无需取消高亮")
            		return false;
            	};
				Ext.MessageBox.confirm('顶置提示', '确定该项目高亮吗？', function(c) {
					  var jsonArray = [];
					  var recs=grid.getSelectionModel().getSelections(); 
					  Ext.each(recs, function (item) {
						 jsonArray.push(item.data);
	                  });
					  if(c=='yes'){
				     	Ext.Ajax.request({
				   		 url : "offlineFd_saveOrUpdaOfflineFdAll.do",
				   	     params:{ifApproval:"5",status:status,offlineFdAll : Ext.encode(jsonArray)},
				   		 success : function() {
				   		  store.reload();
				   	      }
				       });
					}
					});
			}
    	}
        },'-',{
        	text:'删除',
        	iconCls:'btn-remove',
        	handler: function(){
        		var record= grid.getSelectionModel().getSelected();
				if(!record){
                	Ext.Msg.alert('信息提示','请选择要删除的项目');  
				}else{
					Ext.MessageBox.confirm('删除提示', '是否删除该项目？', function(c) {

						  var jsonArray = [];
						  var recs=grid.getSelectionModel().getSelections(); 
						  Ext.each(recs, function (item) {
							 jsonArray.push(item.data);
		                  });
						  if(c=='yes'){
					     	Ext.Ajax.request({
					   		 url : "offlineFd_deleteOfflinedAll.do",
					   	     params:{offlineFdAll : Ext.encode(jsonArray)},
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
        }),
       
        layout : 'fit',
        viewConfig:{
        	forceFit: true, 
        	scrollOffset: 0,
        	/*getRowClass : function(record,rowIndex,rowParams,store){ 
    	       var  status = record.data.status;
            if(status.indexOf("高亮")==-1){  
                return null;  
             }else{
            	 return 'x-grid-td';   
             }                      
        }  */
       	},
          
       
    });

    
    //创建项目
    var addForm = new Ext.FormPanel({
		layout : 'form',
		frame:true,
		labelWidth:60,
		border : false,
		padding : '15 10 0 8',
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
					name:'fxxm',
					id:'fxxm',
					fieldLabel:'项目名称',
					allowBlank : false,
					maxLength :50
				},{
					xtype : 'textfield',
					name:'fxaddress',
					fieldLabel:'辅学地点',
					id:'fxaddress',
					allowBlank : false,
					maxLength :50
				},{ xtype : 'textfield',
					name:'teachertel',
					id:'teachertel',
					fieldLabel:'老师电话',
					allowBlank : false,
					regex:/^(13\d|15[^4,\D]|17[13678]|18\d)\d{8}|170[^346,\D]\d{7}$/,
					maxLength :12
				}]
			},{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype : 'textfield',
					name:'fxtime',
					id:'fxtime',
					fieldLabel:'辅学时间',
					allowBlank : false,
					maxLength :50
				},{
					xtype : 'textfield',
					name:'fxteacher',
					id:'fxteacher',
					fieldLabel:'辅学老师',
					allowBlank : false,
					maxLength :18
				},{
					xtype : 'textfield',
					name:'xmzise',
					id:'xmzise',
					fieldLabel:'项目容量(人)',
					allowBlank : false,
					maxLength :10,
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
			xtype:'textarea',
			name:'xmintro',
			id:'xmintro',
			fieldLabel:'项目简介',
			allowBlank : false,
			height:100,
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
					name:'xmxh',
					id:'xmxh',
					fieldLabel:'项目序号',
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
				    fieldLabel:'暂时隐藏',
					xtype : 'checkbox',
					name:'status'
					
				}]
			}]
		},{
			xtype : 'hidden',
		    name : 'xmid'
		}]
	});
    
    var addWindow = new Ext.Window({
		width:720,
		height:350,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [addForm],
		buttons : [{
			text : '预览',
			handler : function() { 
		        var xmintro = Ext.getCmp("xmintro").getValue();
		        var xmzise = Ext.getCmp("xmzise").getValue();
		        var fxteacher = Ext.getCmp("fxteacher").getValue();
		        var fxtime = Ext.getCmp("fxtime").getValue();
		        var teachertel = Ext.getCmp("teachertel").getValue();
		        var fxaddress = Ext.getCmp("fxaddress").getValue();
		        var fxxm = Ext.getCmp("fxxm").getValue();
		        var xmxh = Ext.getCmp("xmxh").getValue();
		      
		       
		        previewForm.getForm().findField("xmintro").setValue(xmintro);
		        previewForm.getForm().findField("xmzise").setValue(xmzise);
		        previewForm.getForm().findField("fxteacher").setValue(fxteacher);
		        previewForm.getForm().findField("fxtime").setValue(fxtime);
		        previewForm.getForm().findField("teachertel").setValue(teachertel);
		        previewForm.getForm().findField("fxaddress").setValue(fxaddress);
		        previewForm.getForm().findField("fxxm").setValue(fxxm);
		        previewForm.getForm().findField("fxteacher").setValue(fxteacher);
		        previewForm.getForm().findField("xmxh").setValue(xmxh);
			
			 
			 previewWindow.show();
			 
			}
		},{
			text : '发布',
			handler : function() {
			if (addForm.getForm().isValid()) {
				addForm.getForm().submit({
					url : 'offlineFd_saveOrUpdateOfflineFd.do',
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
		}]
	});
    
    
     //预览
     var previewForm = new Ext.FormPanel({
		layout : 'form',
		frame:true,
		labelWidth:60,
		border : false,
		padding : '15 10 0 8',
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
					name:'fxxm',
					fieldLabel:'项目名称',
					readOnly:true,
					cls :'x-textfield',
					maxLength :20
				},{
					xtype : 'textfield',
					name:'fxaddress',
					fieldLabel:'辅学地点',
					readOnly:true,
					cls :'x-textfield',
					maxLength :20
				},{ xtype : 'textfield',
					name:'teachertel',
					fieldLabel:'老师电话',
					readOnly:true,
					cls :'x-textfield',
					maxLength :12
				}]
			},{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype : 'textfield',
					name:'fxtime',
					fieldLabel:'辅学时间',
					readOnly:true,
					cls :'x-textfield',
					maxLength :30
				},{
					xtype : 'textfield',
					name:'fxteacher',
					fieldLabel:'辅学老师',
					readOnly:true,
					cls :'x-textfield',
					maxLength :18
				},{
					xtype : 'textfield',
					name:'xmzise',
					fieldLabel:'项目容量(人)',
					readOnly:true,
					cls :'x-textfield',
					maxLength :10
				}]
			}]
		},{
			xtype:'textarea',
			name:'xmintro',
			fieldLabel:'项目简介',
			readOnly:true,
			style:'border:0', 
			height:100,
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
					name:'xmxh',
					fieldLabel:'项目序号',
					readOnly:true,
					cls :'x-textfield',
					maxLength :20
				}]
			}]
		}]
	});
    
    
    //预览
    var previewWindow = new Ext.Window({
		width:720,
		height:310,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
	    bodyStyle : 'padding:5 5 5 5',  
		items : [previewForm],
		
	});
    
    
    //跳转项目管理
    var mangeProject = function(grid, rowIndex, colIndex){
    	var record = grid.getStore().getAt(rowIndex); 
    	var xmid = record.data.xmid;
    	 window.location.href = 'projectMange.jsp?xmid='+xmid;
    };
    
    
    
    
    //布局
    new Ext.Viewport({
		layout:"fit",
		items:[{
			frame:true,
			title:"线下辅导管理员列表",
			iconCls:'menu-21',
			layout:"border",
			items:[searchForm,grid]
		}]
	});
    
   

});