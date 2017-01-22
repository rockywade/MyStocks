/*!
 * 专家管理
 */
Ext.onReady(function(){
	
	Ext.QuickTips.init();

	//指定学生按钮
	var gStudent = new Ext.Button({
		id:'gStudent',
		anchor : '8%',
		labelWidth:30,
		text:'指定学生',
		style:'margin-left:5px;margin-top:30px;margin-bottom:5px',
		handler:function(){
			var expertType = record.get("expertType");
			//alert(eid);
			sbStudentstore.load({
				url: '/MyStock/Expertbespeak_sbStudents.do',
		        params:{start:0,limit:15,experttype:expertType},
		        scope:this
	    	});
			sbStudentsWindow.show();
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
					width:100,
					items:[{
						cls :'returnBtn',
						width:75,
						xtype:"button",
						//style:'margin-top:25px',
						text:"返<i class='ikg2'></i>回",
						handler:function(){
								   window.location.href="../menu/appointment-experts.html";
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
		{ name:'ssyq', type:'string'},
		{ name:'unit', type:'string'},
		{ name:'email', type:'string'},
		{ name:'expertType', type:'string'},
		{ name:'introduce', type:'string'},
		{ name:'photo', type:'string'}
	];
	
	var StudentSB = [
		{ name:'uploadbespeaktime', type:'string'},
		{ name:'studentName', type:'string'},
		{ name:'talkcontent', type:'string'},
		//{ name:'xb', type:'string'},
	];
	
	var SbStudent = [
	    { name:'stid', type:'int'},
 		{ name:'stdid', type:'int'},
		{ name:'studentName', type:'string'},
		{ name:'uploadbespeaktime', type:'string'}
	];

	var store = new Ext.data.JsonStore({
	    url: '/MyStock/expert_findPageExpert.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: {params:{start:0, limit:15}},
	    fields: UuserObj
	});
	
	var storeDetail = new Ext.data.JsonStore({
	    url: '/MyStock/Expertbespeak_expertSB.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: false,
	    fields: StudentSB
	});
	
	var sbStudentstore = new Ext.data.JsonStore({
	    url: '/MyStock/Expertbespeak_sbStudents.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: false,
	    fields: SbStudent
	});
	
	 //查看详情
	var record;
	var seeDetail = function(grid, rowIndex, colIndex){
    	record = grid.getStore().getAt(rowIndex);
    	var eid = record.get("id");
    	storeDetail.load({
    		url: '/MyStock/Expertbespeak_expertSB.do',
	        params:{start:0,limit:15,id:eid},
	        scope:this
    	});
    	getESBInfo(eid);
		addWindow.show();
		addForm.getForm().loadRecord(record);
		if(record.get("photo")){
			Ext.getCmp("photo").getEl().dom.src = record.get("photo");
		}else{
			Ext.getCmp("photo").getEl().dom.src = "../../../img/defaultpic/photo.jpg";
		}
    };
	var sm = new Ext.grid.CheckboxSelectionModel({singleSelect: false});//单选
    var grid = new Ext.grid.GridPanel({
    	id : 'grid',
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[sm,new Ext.grid.RowNumberer(),
	            {header: '职工号', width: 100,align:'center', dataIndex: 'zgh'},
	            {header: '姓名', width: 100,align:'center', dataIndex: 'xm'},
	            {header: '性别', width: 100,align:'center', dataIndex: 'xb'},
	            {header: '手机号', width: 120,align:'center', dataIndex: 'phone'},
	            {header: '单位(办公)', width: 100,align:'center', dataIndex: 'unit'},
	            {header: '邮箱', width: 100,align:'center', dataIndex: 'email'},
	            {header: '专家类型', width: 100,align:'center', dataIndex: 'expertType'},
	            {header: '个人简介', width: 180,align:'center', dataIndex: 'introduce'},
	            {xtype: 'actioncolumn',header : '操作', width: 170,align : 'center',menuDisabled : true,
	                items: [{
	                    icon: '../../img/btn/see.png',
	                    handler: function(grid, rowIndex, colIndex){
	                		seeDetail(grid, rowIndex, colIndex);//调用审核方法
	                	}
	                },{
	                	icon: '../../img/btn/split.png'
	                 },{
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
	           }]
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
					   			url : "/MyStock/expert_deleteExpers.do",
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
    //预约学生详情
    var rownumber = new Ext.grid.RowNumberer();//行号
    var gridDetail = new Ext.grid.GridPanel({
        store: storeDetail,
        height:200,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[rownumber,
			    {header:'时间', width: 200,align:'center', dataIndex: 'uploadbespeaktime'},
			    {header:'谈话对象',width:100,align:'center',dataIndex:'studentName'},
			    {header:'谈话内容',width:300,align:'center',dataIndex:'talkcontent'}]
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
            store: storeDetail,
            displayInfo: true
        }),
        viewConfig : {
        	forceFit: true,
        	scrollOffset: 2
    	}
    });
    
    //专家信息
    var addForm = new Ext.FormPanel({
		layout : 'form',
		region:"north",
		frame:true,
		labelWidth:62,
		border : false,
		padding : '10 10 215 30',
		defaults : {
			anchor : '100%',
			labelAlign:"right",
			cls:'borderNone'
		},
		items:[{
			layout:'column',
			items:[{
				columnWidth:.25,
				layout:'form',
				defaults:{
					anchor : '96%'
				},
			    items:[{
					xtype:'box',
					id:'photo',
					//name:'photo',
					autoEl:{
						height : 150,
						width:50,
						tag:'img',
						src:''
					}
			    },gStudent]
			},{
				columnWidth:.75,
				layout:'form',
				defaults:{
					anchor : '100%',
					cls:'borderNone'
				},
				items:[{
					layout:'column',
					items:[{
						columnWidth:.5,
						layout:'form',
						defaults:{
							anchor : '100%',
							cls:'borderNone'
						},
						items:[{
							xtype : 'textfield',
							name:'xm',
							fieldLabel:'姓<i class="ikg2"></i>名',
							allowBlank : false,
							readOnly:true,
							maxLength :20
						},{
							xtype : 'textfield',
							name:'ssyq',
							fieldLabel:'所属园区',
							readOnly:true,
						},{
							xtype : 'textfield',
							name:'phone',
							fieldLabel:'联系电话',
							readOnly:true,
						},{
							xtype : 'textfield',
							name:'unit',
							fieldLabel:'办公/单位',
							readOnly:true,
						}]
					},{
						columnWidth:.5,
						layout:'form',
						defaults:{
							anchor : '100%',
							cls:'borderNone'
						},
					    items:[{
							xtype : 'textfield',
							name:'xb',
							fieldLabel:'性<i class="ikg2"></i>别',
							readOnly:true,
						},{
							xtype:'textfield',
							name:'expertType',
							fieldLabel:'专家类别',
							readOnly:true,
						},{
							xtype : 'textfield',
							name:'email',
							fieldLabel:'邮<i class="ikg2"></i>箱',
							readOnly:true,
						}]
					}]
				},{
					xtype : 'textfield',
					name:'freetime',
					fieldLabel:'空余时间',
					readOnly:true,
					maxLength :100
				},{
					xtype : 'textfield',
					name:'workaddress',
					fieldLabel:'办公地址',
					readOnly:true,
					maxLength :50
				},{
					xtype : 'textarea',
					name:'introduce',
					fieldLabel:'个人简介',
					height:55,
					readOnly:true,
				}]
			}]
		}]
	});
    
    var sm = new Ext.grid.CheckboxSelectionModel();//单选{singleSelect: true}
    var sbStudentsgrid = new Ext.grid.GridPanel({
        store: sbStudentstore,
        height:200,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[sm,
			    //{header:'stid', width: 100,align:'center', dataIndex: 'stid'},
			    //{header:'stdid', width: 100,align:'center', dataIndex: 'stdid'},
			    {header:'姓名', width: 100,align:'center', dataIndex: 'studentName'},
			    {header:'预约时间',width:100,align:'center',dataIndex:'uploadbespeaktime'}]
        }),
        sm:sm,//checkbox
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
		loadMask : true,	//加载时的遮罩
		frame : true,
		region:"center",
        iconCls:'menu-51',
        tbar:['->',{
        	id:'serchid',
        	xtype:"textfield",
        	//name:'xm',
        },{
        	text:'搜索',
        	//iconCls:'btn-edit',
        	handler: function(){
        		var v = Ext.getCmp("serchid").getValue();
        		var expertType = record.get("expertType");
        		sbStudentstore.load({
    				url: '/MyStock/Expertbespeak_sbStudents.do',
    		        params:{start:0,limit:15,experttype:expertType,xm:v},
    		        scope:this
    	    	});
        	}
        }],
        bbar: new Ext.PagingToolbar({
            pageSize: 15,
            store: sbStudentstore,
            displayInfo: true
        }),
        viewConfig : {
        	forceFit: true,
        	scrollOffset: 2
    	}
    });
    
    //添加form窗口
    var addWindow = new Ext.Window({
		title : '专家预约信息',
		width:650,
		height:560,
		closeAction:'hide',
		modal : true,
		layout : 'border',
		items : [addForm,gridDetail],
	});
    
    var sbStudentsWindow = new Ext.Window({
		title : '指定学生',
		width:650,
		height:560,
		closeAction:'hide',
		modal : true,
		layout : 'border',
		buttonAlign : 'center',
		items : [sbStudentsgrid],
		buttons : [{
			text : '保存',
			handler : function() {
				var eid = record.get("id");
				var stidArray = [];
				var records = sbStudentsgrid.getSelectionModel().getSelections();
				Ext.each(records,function(id){
					stidArray.push(id.data.stid);
				});
				if(stidArray.length>0){
					Ext.Ajax.request({
			    		url:"/MyStock/Expertbespeak_updateAllotExpertForStd.do",
			    		params:{ id:eid,stidArray:Ext.encode(stidArray)},
			    		success:function(response){
			    			var responsedata = Ext.util.JSON.decode(response.responseText);
			    			if(responsedata){
			    				Ext.Msg.alert('信息提示',responsedata.message,function(){
			    					sbStudentsWindow.hide();
			    				});
			    			}
			    		}
			    	});
				}else{
					Ext.Msg.alert('信息提示',"请先选择学生");
				}
			}
		}]
	});
    

    var uForm = new Ext.FormPanel({
		layout : 'form',
		baseCls:'x-plain',
		labelWidth:60,
		fileUpload:true,
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
				allowBlank : true
			},{
				xtype:'textfield',
				name:'image',
				fieldLabel:'头像',
				inputType: 'file',
				height:40,
				validator: function(value){
					if(''==value){
						return '请上传图像';
					}
					var extension=new String(value.substring(value.lastIndexOf(".")+1,value.length));
					extension = extension.toLowerCase();   
					if(extension=="png"||extension=="jpg"||extension=="gif"||extension=="jpeg"||extension=="bmp")
		            {
		            	return true; 
		            }
		            else
		            {
		            	return '只允许上传png|jpg|gif|bmp';
		            }
					
				},
				maxLength :200
			},{
				xtype:'combo',
				hiddenName:'unit',
				fieldLabel:'单位(办公)',
				mode: 'local',
				triggerAction: 'all',
				valueField :'value',
				displayField: 'text',
				allowBlank : false,
				editable : false,
				value:'教务处',
				store : new Ext.data.SimpleStore({
				    fields: ['value', 'text'],
				    data : [['教务处','教务处'],['财务','财务']]
				})
			},{
				name:'email',
				fieldLabel:'邮箱',
				maxLength :20,
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
				store : new Ext.data.SimpleStore({
				    fields: ['value', 'text'],
				    data : [['丹青','丹青'],['云峰','云峰'],['蓝田','蓝田']]
				})
		},{
				xtype:'lovcombo',
				hiddenName:'expertType',
				fieldLabel:'专家类型',
				mode: 'local',
				triggerAction: 'all',
				valueField :'value',
				displayField: 'text',
				allowBlank : false,
				editable : false,
				value:'请选择',
				store : new Ext.data.SimpleStore({
				    fields: ['value', 'text'],
				    data : [['学业指导','学业指导'],['心理辅导','心理辅导'],['职业规划','职业规划'],['出国指导','出国指导'],['其他','其他']]
				})
			},{
				xtype:'textarea',
				name:'introduce',
				fieldLabel:'个人简介',
				allowBlank : true
			},{
				xtype : 'hidden',
			    name : 'id'
			}]
	});
    
    var uWindow = new Ext.Window({
		title : '专家',
		width:400,
		height:450,
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
						url : '/MyStock/expert_saveOrUpdateExpertByAdmin.do',
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
    
    //获取当前专家发布的预约信息
	var getESBInfo = function(eid){
		//alert(eid);
    	Ext.Ajax.request({
    		url:"/MyStock/Expertbespeak_getESBDetailInfo.do",
    		params:{ id :eid },
    		success:function(response){
    			var responsedata = Ext.util.JSON.decode(response.responseText);
    			if(responsedata){
    				addForm.getForm().findField('freetime').setValue(responsedata.data.freetime);
    				addForm.getForm().findField('workaddress').setValue(responsedata.data.workaddress);
    			}
    		}
    	});
    };
    
    new Ext.Viewport({
		layout:"fit",
		items:[{
			frame:true,
			title:"专家管理",
			iconCls:'menu-51',
			layout:"border",
			items:[searchForm,grid]
		}]
	});
    
});

