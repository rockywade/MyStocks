/*!
 * 特别关心
 */
Ext.onReady(function(){
	Ext.QuickTips.init();

	//查询表单
	var searchForm = new Ext.FormPanel({
		region:"north",
		height: 80,
		border : false,
		layout : "form",
		padding : "5 20 100 20",
		items:[{
			xtype:"fieldset",
			title:"特别关心",
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
					width:230,
					items:[{
								width:120,
								labelWidth:30,
								xtype:"textfield",
								name:"xm",
								id:'xmSearch',
								fieldLabel:"姓名"
							}]
						},{
							width:230,
							items:[{
								width:120,
								labelWidth:30,
								xtype:"textfield",
								name:"xh",
								id:'xhSearch',
								fieldLabel:"学号"
							}]
								
						},{
							width:230,
							items:[{
								width:120,
								labelWidth:30,
								xtype:"textfield",
								id:'administrativeClassSearch',
								name:"administrativeClass",
								fieldLabel:"班级"
						}]
					},{
						width:230,
						items:[{
							width:120,
							labelWidth:30,
							xtype:"textfield",
							name:"bzr",
							id:'bzrSearch',
							fieldLabel:"班主任"
					}]
				},{
					width:230,
					items:[{
						width:120,
						labelWidth:30,
						xtype:"textfield",
						name:"fdy",
						id:'fdySearch',
						fieldLabel:"辅导员"
				}]
			},{
				width:230,
				items:[{
					width:120,
					labelWidth:30,
					xtype:'combo',
					hiddenName:'economic',
					id:'economicSearch',
					fieldLabel:'经济困难',
					mode: 'local',
					triggerAction: 'all',
					emptyText: '全部',
					editable : false,
					store : [['','全部'],['是','是'],['否','否']]
			}]
			},{
						width:230,
						items:[{
							width:120,
							labelWidth:30,
							xtype:'combo',
							hiddenName:'academic',
							id:'academicSearch',
							fieldLabel:'学业困难',
							mode: 'local',
							triggerAction: 'all',
							emptyText: '全部',
							editable : false,
							store : [['','全部'],['一级','一级'],['二级','二级'],['三级','三级'],['无','无']]
					}]
					},{
						width:230,
						items:[{
							width:120,
							labelWidth:30,
							xtype:'combo',
							hiddenName:'mental',
							id:'mentalSearch',
							fieldLabel:'心理困难',
							mode: 'local',
							triggerAction: 'all',
							emptyText: '全部',
							editable : false,
							store : [['','全部'],['是','是'],['否','否']]
					}]
					},{
					items:[{
						width:75,
						xtype:"button",
						iconCls:"",
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
	var SpecialCare = [
	                { name:'id', type:'int'},
		      		{ name:'xh', type:'string'},
		      		{ name:'xm',type:'string'},
		      		{ name:'administrativeClass',type:'string'},
		      		{ name:'economic', type:'string'},
		      		{ name:'academic', type:'string'},
		      		{ name:'mental',type:'string'},
		      		{ name:'yxldtimes',type:'int'},
		      		{ name:'fydtimes', type:'int'},
		      		{ name:'bzrtimes', type:'int'},
		      		{ name:'xszytimes', type:'int'},
		      		{ name:'mq', type:'string'},
		      		{ name:'mqphone',type:'string'},
		      		{ name:'bzr',type:'string'},
		      		{ name:'bzrphone', type:'string'},
		      		{ name:'fdy', type:'string'},
		      		{ name:'fdyphone',type:'string'},
		      		{ name:'xszy', type:'string'},
		      		{ name:'xszyphone',type:'string'},
		      		{ name:'xszylogincode',type:'string'},
		      		{ name:'bzrlogincode', type:'string'},
		      		{ name:'fdylogincode', type:'string'},
		      		{ name:'qsh',type:'string'},
		      		{ name:'phone', type:'string'},
		      		{ name:'fq', type:'string'},
		      		{ name:'fqphone',type:'string'}
		      	];
   var Conversation = [
	      		{ name:'id', type:'int'},
	      		{ name:'conversationtime', type:'string'},
	      		{ name:'conversationpalce',type:'string'},
	      		{ name:'conversationname',type:'string'},
	      		{ name:'conversationtype',type:'string'},
	      		{ name:'attendstudentphonenum',type:'string'},
	      		{ name:'problem',type:'string'},
	      		{ name:'solution',type:'string'},
	      		{ name:'stuff', type:'string'}
	      	];
	
	//获取response数据
	var store = new Ext.data.JsonStore({
	    url: '/MyStock/specialCare_findPageSpecialCare.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: true,
	    fields: SpecialCare
	});
	
	var storeConversation = new Ext.data.JsonStore({
	    url: '/MyStock/conversation_findPageConversation.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: false,
	    fields: Conversation
	});
	
	//详情方法
	var getConversation= function(grid, rowIndex, colIndex){
    	var record = grid.getStore().getAt(rowIndex); 
    	storeConversation.load({
    				url:'/MyStock/conversation_findPageConversation.do',
    		        params:{start:0,limit:5,studentid:record.data.id},
    		        scope:this
    		    });
    	addForm.getForm().loadRecord(record);
    	
    	addWindow.show();
    };
	
    var sm = new Ext.grid.CheckboxSelectionModel({singleSelect: true});//单选
	var rownumber = new Ext.grid.RowNumberer();//行号
    var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[rownumber,
			    {header:'姓名', width: 110,align:'center', dataIndex: 'xm'},
			    {header:'学号',width:110,align:'center',dataIndex:'xh'},
			    {header:'行政班级',width:110,align:'center',dataIndex:'administrativeClass'},
			    {header:'经济困难',width:110,align:'center',dataIndex:'economic'},
			    {header:'学业困难',width:110,align:'center',dataIndex:'academic'},
			    {header:'心理困难',width:110,align:'center',dataIndex:'mental'},
			    {header:'领导',width:110,align:'center',dataIndex:'yxldtimes'},
			    {header:'辅导员',width:110,align:'center',dataIndex:'fydtimes'},
			    {header:'班主任',width:110,align:'center',dataIndex:'bzrtimes'},
			    {header:'新生之友',width:110,align:'center',dataIndex:'xszytimes'},
			    {xtype: 'actioncolumn',header : '操作', width: 100,align : 'center',
	            	menuDisabled : true,
	                items: [{
	                    icon: '../../img/btn/edits.png',
	                    tooltip: '编辑',
	                    handler: function(grid, rowIndex, colIndex){
	                		getConversation(grid, rowIndex, colIndex);
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
        	text:'导出学生',
        	iconCls:'menu-12',
        	handler: function(){
				Ext.MessageBox.confirm('提示', '确定导出名单吗？', function(ok) {
				   if(ok=='yes'){
					   var xm =  encodeURI(encodeURI(Ext.getCmp('xmSearch').getValue()));
					  
					   var xh =  encodeURI(encodeURI(Ext.getCmp('xhSearch').getValue()));
					   var administrativeClass =  encodeURI(encodeURI(Ext.getCmp('administrativeClassSearch').getValue()));
					   var bzr =  encodeURI(encodeURI(Ext.getCmp('bzrSearch').getValue()));
					   var fdy =  encodeURI(encodeURI(Ext.getCmp('fdySearch').getValue()));
					   var economic =  encodeURI(encodeURI(Ext.getCmp('economicSearch').getValue()));
					   var academic =  encodeURI(encodeURI(Ext.getCmp('academicSearch').getValue()));
					   var mental =  encodeURI(encodeURI(Ext.getCmp('mentalSearch').getValue()));
					   var s = "?xm="+xm+
					   			"&xh="+xh+
					   			"&administrativeClass="+administrativeClass+
					   			"&bzr="+bzr+
					   			"&fdy="+fdy+
					   			"&economic="+economic+
					   			"&academic="+academic+
					   			"&mental="+mental;
					   window.location.href='/MyStock/specialcareExportExcel.do'+s;
				    }
				});
        	}
        },'-',{
        	text:'添加学生',
        	iconCls:'btn-add',
        	handler: function(){
        		addStuWindow.show();
        		addStuForm.getForm().reset();
        	}
        }],
        bbar: new Ext.PagingToolbar({
            pageSize: 15,
            store: store,
            displayInfo: true
        })
    });
    
    var addStuForm = new Ext.FormPanel({
		layout : 'form',
		fileUpload:true,
		frame:true,
		url :'/MyStock/specialCare_save.do',
		labelWidth:80,
		border : false,
		padding : '15 10 0 8',
		defaults : {
			anchor : '100%'
		},
		items:[
			{
			layout:'column',
			items:[{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype:'textfield',
					name:'xh',
					id:'xh',
					fieldLabel:'学号',
					allowBlank : false,
					maxLength :32,
					listeners : { 
				        "blur" : function() { 
							Ext.Ajax.request({
								async: false,
					   			url : "/MyStock/specialCare_getSpecialCare.do",
					   			params:{ xh : Ext.getCmp("xh").getValue()},
					   			success : function(response) {
					   				var responsedata = Ext.util.JSON.decode(response.responseText);
					   				if(responsedata.data.length>0){
					   					var data = responsedata.data[0];
						   				Ext.getCmp("xm").setValue(data.xm);
						   				Ext.getCmp("administrativeClass").setValue(data.administrativeClass);
						   				Ext.getCmp("phone").setValue(data.phone);
						   				Ext.getCmp("fq").setValue(data.fq);
						   				Ext.getCmp("fqphone").setValue(data.fqphone);
						   				Ext.getCmp("mq").setValue(data.mq);
						   				Ext.getCmp("mqphone").setValue(data.mqphone);
						   				Ext.getCmp("bzr").setValue(data.bzr);
						   				Ext.getCmp("bzrphone").setValue(data.bzrphone);
						   				Ext.getCmp("bzrlogincode").setValue(data.bzrlogincode);
						   				Ext.getCmp("fdy").setValue(data.fdy);
						   				Ext.getCmp("fdyphone").setValue(data.fdyphone);
						   				Ext.getCmp("fdylogincode").setValue(data.fdylogincode);
						   				Ext.getCmp("xszy").setValue(data.xszy);
						   				Ext.getCmp("xszyphone").setValue(data.xszyphone);
						   				Ext.getCmp("xszylogincode").setValue(data.xszylogincode);
						   				Ext.getCmp("qsh").setValue(data.qsh);
					   				}else{
						   				Ext.getCmp("xm").setValue("");
						   				Ext.getCmp("administrativeClass").setValue("");
						   				Ext.getCmp("phone").setValue("");
						   				Ext.getCmp("fq").setValue("");
						   				Ext.getCmp("fqphone").setValue("");
						   				Ext.getCmp("mq").setValue("");
						   				Ext.getCmp("mqphone").setValue("");
						   				Ext.getCmp("bzr").setValue("");
						   				Ext.getCmp("bzrphone").setValue("");
						   				Ext.getCmp("bzrlogincode").setValue("");
						   				Ext.getCmp("fdy").setValue("");
						   				Ext.getCmp("fdyphone").setValue("");
						   				Ext.getCmp("fdylogincode").setValue("");
						   				Ext.getCmp("xszy").setValue("");
						   				Ext.getCmp("xszyphone").setValue("");
						   				Ext.getCmp("xszylogincode").setValue("");
						   				Ext.getCmp("qsh").setValue("");
					   				}
					   				
					   			}
					   		});
						} 
					}
					
				}]
			},{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype:'textfield',
					id:'xm',
					name:'xm',
					fieldLabel:'姓名',
					allowBlank : false,
					maxLength :32
				}]
			},{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype:'textfield',
					id:'administrativeClass',
					name:'administrativeClass',
					fieldLabel:'行政班',
					allowBlank : false,
					maxLength :32
				}]
			}]
		},{
			layout:'column',
			items:[{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype:'combo',
					hiddenName:'economic',
					fieldLabel:'经济困难',
					mode: 'local',
					triggerAction: 'all',
					emptyText: '',
					allowBlank : true,
					editable : false,
					store : [['是','是'],['否','否']]
				}]
			},{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype:'combo',
					hiddenName:'mental',
					fieldLabel:'心理困难',
					mode: 'local',
					triggerAction: 'all',
					emptyText: '',
					allowBlank : true,
					editable : false,
					store : [['是','是'],['否','否']]
				}]
			},{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype:'combo',
					hiddenName:'academic',
					fieldLabel:'学业困难',
					mode: 'local',
					allowBlank : true,
					triggerAction: 'all',
					emptyText: '',
					editable : false,
					store : [['一级','一级'],['二级','二级'],['三级','三级'],['无','无']]
				}]
			}]
		},{
			layout:'column',
			items:[{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype:'textfield',
					name:'phone',
					id:'phone',
					fieldLabel:'联系电话',
					allowBlank : false,
					maxLength :11
				}]
			},{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype:'textfield',
					name:'fq',
					id:'fq',
					fieldLabel:'父亲',
					allowBlank : true,
					maxLength :32
				}]
			},{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype:'textfield',
					name:'fqphone',
					id:'fqphone',
					fieldLabel:'父亲电话',
					allowBlank : true,
					maxLength :11
				}]
			}]
		},{
			layout:'column',
			items:[{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype:'textfield',
					name:'mq',
					id:'mq',
					fieldLabel:'母亲',
					allowBlank : true,
					maxLength :32
				}]
			},{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype:'textfield',
					name:'mqphone',
					id:'mqphone',
					fieldLabel:'母亲电话',
					allowBlank : true,
					maxLength :11
				}]
			},{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype:'textfield',
					name:'qsh',
					id:'qsh',
					fieldLabel:'寝室',
					allowBlank : false,
					maxLength :32
				}]
			}]
		},{
			layout:'column',
			items:[{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype:'textfield',
				name:'bzr',
				id:'bzr',
				fieldLabel:'班主任',
				allowBlank : true,
				maxLength :32
				}]
			},{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype:'textfield',
					name:'bzrphone',
					id:'bzrphone',
					fieldLabel:'班主任电话',
					allowBlank : true,
					maxLength :11
				}]
			},{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype:'textfield',
					name:'bzrlogincode',
					id:'bzrlogincode',
					fieldLabel:'班主任职工号',
					allowBlank : true,
					maxLength :32
				}]
			}]
		},{
			layout:'column',
			items:[{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype:'textfield',
					name:'fdy',
					id:'fdy',
					fieldLabel:'辅导员',
					allowBlank : true,
					maxLength :32
				}]
			},{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype:'textfield',
					id:'fdyphone',
					name:'fdyphone',
					fieldLabel:'辅导员电话',
					allowBlank : true,
					maxLength :11
				}]
			},{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype:'textfield',
					name:'fdylogincode',
					id:'fdylogincode',
					fieldLabel:'辅导员职工号',
					allowBlank : true,
					maxLength :32
				}]
			}]
		},{
			layout:'column',
			items:[{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype:'textfield',
					name:'xszy',
					id:'xszy',
					fieldLabel:'新生之友',
					allowBlank : true,
					maxLength :32
				}]
			},{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype:'textfield',
					name:'xszyphone',
					id:'xszyphone',
					fieldLabel:'新生之友电话',
					allowBlank : true,
					maxLength :11
				}]
			},{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype:'textfield',
				name:'xszylogincode',
				id:'xszylogincode',
				fieldLabel:'新生之友职工号',
				allowBlank : true,
				maxLength :32
				}]
			}]
		}
		]
	});
    
  //文件导入窗口
    var addStuWindow = new Ext.Window({
		title : '添加学生',
		width:900,
		height:350,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [addStuForm],
		buttons : [{
			text : '取消',
			handler : function() {
				addStuWindow.hide();
				addStuForm.getForm().reset();
			}
		},{
			text : '添加',
			handler : function() {
				if (addStuForm.getForm().isValid()) {
					addStuForm.getForm().submit({
					success : function(form, action) {
						store.reload();
						addStuWindow.hide();
					},
					failure : function(form, action) {
						if(action.result.errors){
							Ext.Msg.alert('信息提示',action.result.errors);
						}else{
							Ext.Msg.alert('信息提示','连接失败');
						}
					},
					waitTitle : '保存',
					waitMsg : '正在保存数据，稍后...'
				});
				}
			}
		}]
	});
    
    var importForm = new Ext.FormPanel({
		layout : 'form',
		fileUpload:true,
		frame:true,
		url :'/MyStock/specialCare_importExcel.do',
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
    
    var viewPanel = new Ext.Panel({
  	   id : 'viewPanel',
 	       layout : 'fit',
 	       html : '',
 	       frame : true
     });
    
    var viewWindow = new Ext.Window({
    	title:'查看详情',
		width:620,
		height:350,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [viewPanel],
		buttons : [{
			text : '取消',
			handler : function() {
				viewWindow.hide();
			}
		}]
	});
    
    var rownumberConversation = new Ext.grid.RowNumberer();//行号
    var gridConversation = new Ext.grid.GridPanel({
        store: storeConversation,
        height:200,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[rownumber,
			    {header:'访谈人', width: 110,align:'center', dataIndex: 'conversationtype'},
			    {header:'时间',width:110,align:'center',dataIndex:'conversationtime'},
			    {header:'地点',width:110,align:'center',dataIndex:'conversationpalce'},
			    {header:'记录人',width:110,align:'center',dataIndex:'conversationname'},
			    {xtype: 'actioncolumn',header : '操作', width: 100,align : 'center',
	            	menuDisabled : true,
	                items: [{
	                    icon: '../../img/btn/see.png',
	                    tooltip: '查看详情',
	                    handler: function(grid, rowIndex, colIndex){
		                	var record = grid.getStore().getAt(rowIndex); 
		                	var html = '<iframe frameborder="0" src="/MyStock/conversation_viewConversation.do?id='+record.data.id+'"  width="100%"  height="100%"></iframe>';
							if(Ext.getCmp('viewPanel').body){
								Ext.getCmp('viewPanel').body.update(html);
							}else{
								Ext.getCmp('viewPanel').html = html;
							}
							viewWindow.show();
	                	}
	                }]
	           }]
        }),
        rownumber:rownumberConversation,//checkbox
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
		loadMask : true,	//加载时的遮罩
		frame : true,
		region:"center",
        iconCls:'menu-51',
        layout : 'fit',
        viewConfig:{
        	forceFit: true, 
        	scrollOffset: 0
       	},
        tbar:['->',{
        	text:'新增谈话',
        	iconCls:'btn-add',
        	handler: function(){
        		conversationForm.getForm().reset();
        		conversationWindow.show();
        	}
        }],
        bbar: new Ext.PagingToolbar({
            pageSize: 5,
            store: storeConversation,
            displayInfo: true
        })
    });
   
    //详情显示窗口
    var addForm = new Ext.FormPanel({
		layout : 'form',
		region:"north",
		frame:true,
		labelWidth:60,
		border : false,
		padding : '15 10 180 8',
		items:[{
			layout:'column',
			items:[{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '78%'
				},
				items:[{
					xtype : 'textfield',
					name:'xm',
					fieldLabel:'姓名',
					readOnly : true,
					allowBlank : false,
					maxLength :20
				}]
			},{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '78%'
				},
				items:[{
					xtype : 'textfield',
					name:'xh',
					fieldLabel:'学号',
					readOnly : true,
					allowBlank : false
				}]
			},{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '78%'
				},
				items:[{
					xtype : 'textfield',
					name:'administrativeClass',
					fieldLabel:'行政班',
					readOnly : true,
					allowBlank : false,
				}]
			}]
		},{
			layout:'column',
			items:[{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '78%'
				},
				items:[{
					xtype : 'textfield',
					name:'phone',
					fieldLabel:'本人电话',
					readOnly : true,
					allowBlank : false
				}]
			},{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '78%'
				},
				items:[{
					xtype : 'textfield',
					name:'qsh',
					fieldLabel:'寝室',
					readOnly : true,
					allowBlank : false
				}]
			},{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '78%'
				},
				items:[{
					xtype : 'textfield',
					name:'fq',
					fieldLabel:'父亲',
					readOnly : true,
					maxLength :20
				}]
			}]
		},{
			layout:'column',
			items:[{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '78%'
				},
				items:[{
					xtype : 'textfield',
					name:'fqphone',
					fieldLabel:'父亲电话',
					readOnly : true,
					allowBlank : true
				}]
			},{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '78%'
				},
				items:[{
					xtype : 'textfield',
					name:'mq',
					readOnly : true,
					fieldLabel:'母亲',
					maxLength :20
				}]
			},{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '78%'
				},
				items:[{
					xtype : 'textfield',
					name:'mqphone',
					fieldLabel:'母亲电话',
					readOnly : true,
					allowBlank : true
				}]
			}]
		},{
			layout:'column',
			items:[{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '78%'
				},
				items:[{
					xtype : 'textfield',
					name:'bzr',
					fieldLabel:'班主任',
					readOnly : true,
					maxLength :20
				}]
			},{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '78%'
				},
				items:[{
					xtype : 'textfield',
					name:'bzrphone',
					readOnly : true,
					fieldLabel:'电话',
					allowBlank : true
				}]
			},{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '78%'
				},
				items:[{
					xtype : 'textfield',
					name:'fdy',
					fieldLabel:'辅导员',
					readOnly : true,
					maxLength :20
				}]
			}]
		},{
			layout:'column',
			items:[{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '78%'
				},
				items:[{
					xtype : 'textfield',
					name:'fdyphone',
					fieldLabel:'电话',
					readOnly : true,
					allowBlank : true
				}]
			},{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '78%'
				},
				items:[{
					xtype : 'textfield',
					name:'xszy',
					fieldLabel:'新生之友',
					readOnly : true,
					maxLength :20
				}]
			},{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '78%'
				},
				items:[{
					xtype : 'textfield',
					name:'xszyphone',
					fieldLabel:'电话',
					readOnly : true,
					allowBlank : true
				}]
			}]
		},{
			layout:'column',
			items:[{
				columnWidth:.3,
				layout:'form',
				defaults:{
					anchor : '78%'
				},
					items:[{
						width:100,
						xtype:'combo',
						hiddenName:'economic',
						fieldLabel:'经济困难',
						mode: 'local',
						triggerAction: 'all',
						emptyText: '',
						editable : false,
						store : [['是','是'],['否','否']]
					}]
				},{
					columnWidth:.3,
					layout:'form',
					defaults:{
						anchor : '78%'
					},
					items:[{
						width:100,
								xtype:'combo',
								hiddenName:'mental',
								fieldLabel:'心理困难',
								mode: 'local',
								triggerAction: 'all',
								emptyText: '',
								editable : false,
								store : [['是','是'],['否','否']]
					}]
				},{
					columnWidth:.3,
					layout:'form',
					defaults:{
						anchor : '78%'
					},
						items:[{
							width:100,
							xtype:'combo',
							hiddenName:'academic',
							fieldLabel:'学业困难',
							mode: 'local',
							triggerAction: 'all',
							emptyText: '',
							editable : false,
							store : [['一级','一级'],['二级','二级'],['三级','三级'],['无','无']]
				}]
			}]
		},{
			xtype:'hidden',
			id :'id',
			name:'id'
		}
		]
	});
    
   
    //添加form窗口
    var addWindow = new Ext.Window({
		title : '详情',
		width:940,
		height:450,
		closeAction:'hide',
		modal : true,
		layout : 'border',
		items : [addForm,gridConversation],
		buttons : [{
			text : '保存',
			handler : function() {
				var f = addForm.getForm();
				if (f.isValid()) {
					f.submit({
						url : '/MyStock/specialCare_update.do',
						params :{},
						success : function(form, action) {
							Ext.Msg.alert("信息提示","数据保存成功");
							store.reload();
							addWindow.hide();
						},
						failure : function(form, action) {
							if(action.result && action.result.errors){
								Ext.Msg.alert('信息提示',action.result.errors);
							}else{
								Ext.Msg.alert('信息提示','连接失败');
							}
						}
					});
				}
			}
		}]
	});
    
    var conversationForm = new Ext.FormPanel({
		layout : 'form',
		url : '/MyStock/conversation_saveOrUpdateConversation.do',
		fileUpload:true,  
		frame:true,
		labelWidth:60,
		border : false,
		padding : '15 10 500 10',
		defaults : {
			anchor : '100%'
		},
		items:[{
			id:'conversationtime',
			xtype:"datefield",
			name:'conversationtime',
			fieldLabel:"时间",
			format:'Y-m-d',
			allowBlank : false,
			value:new Date(),
			anchor:"30%"
		},{
			xtype:"textfield",
			name:'conversationpalce',
			fieldLabel:"地点",
			anchor:"30%",
			allowBlank : false,
			maxLength :20
		},{
			xtype:'combo',
			hiddenName:'conversationtype',
			fieldLabel:'记录人',
			mode: 'local',
			anchor:"30%",
			triggerAction: 'all',
			emptyText: '',
			editable : false,
			store : [['辅导员','辅导员'],['班主任','班主任'],['新生之友','新生之友'],['院系领导','院系领导']]
		},{
			xtype:"textfield",
			name:'conversationname',
			fieldLabel:"姓名",
			anchor:"30%",
			allowBlank : false,
			maxLength :20
		},{
			xtype:'textarea',
			name:'problem',
			fieldLabel:'主要问题',
			allowBlank : false,
			height:100,
			maxLength :200
		},{
			xtype:'textarea',
			name:'solution',
			fieldLabel:'辅导方案',
			allowBlank : false,
			height:100,
			maxLength :200
		},{
			xtype:'textfield',
			name:'stuff',
			fieldLabel:'材料',
			inputType: 'file',
			height:40,
			maxLength :200
		}]
	});
    
    var conversationWindow = new Ext.Window({
		title : '新增谈话',
		width:940,
		height:450,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		items : [conversationForm],
		buttons : [{
			text : '取消',
			handler : function() {
				conversationWindow.hide();
				conversationForm.getForm().reset();
			}
		},{
			text : '保存',
			handler : function() {
				var f = conversationForm.getForm();
				if (f.isValid()) {
					f.submit({
						url : '/MyStock/conversation_saveOrUpdateConversation.do',
						params :{studentid:Ext.getCmp("id").value},
						success : function(form, action) {
							Ext.Msg.alert("信息提示","数据保存成功");
							storeConversation.reload();
							conversationWindow.hide();
						},
						failure : function(form, action) {
							if(action.result && action.result.errors){
								Ext.Msg.alert('信息提示',action.result.errors);
							}else{
								Ext.Msg.alert('信息提示','连接失败');
							}
						}
					});
				}
			}
		}]
	});
    
    
    //布局
    new Ext.Viewport({
		layout:"fit",
		items:[{
			frame:true,
			title:"特别关心",
			iconCls:'menu-15',
			layout:"border",
			items:[searchForm,grid]
		}],
		listeners:{
			render:function(){
	    		//groundStore2.load();
			}
		}
	});
});