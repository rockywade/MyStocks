Ext.BLANK_IMAGE_URL = '../../../ext/resources/images/default/s.gif';
/*!
 * 活动管理
 */
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//提交新闻按钮
	var newsButton = new Ext.Button({
		id:'newsButton',
		width:90,
		labelWidth:30,
		text:'提交新闻',
		handler:function(){
			addWindow.show();
		}
	});
	
	//参与公示按钮
	var publishButton = new Ext.Button({
		id:"publishButton",
		width:90,
		labelWidth:30,
		hidden:true,
		text:'参与公示',
		handler:function(){
			var activityid = Ext.getCmp("activityid").getValue();
			Ext.MessageBox.confirm('参与公示提示', '确定参与公示吗？', function(ok) {
			   if(ok=='yes'){
			   		Ext.Ajax.request({
			   			url : "Applyactivity_joinPublish.do",
			   			params:{ activityid : activityid,pulishkey:'1'},
			   			success : function(response) {
			   				var responsedata = Ext.util.JSON.decode(response.responseText);
			   				//Ext.Msg.alert('信息提示',responsedata.message);
			   				Ext.getCmp("publishButton").hide();
			   				store.reload();
			   			},
			   			error:function(e){
			   				alert(e);
			   			}
			   		});
			    }
			});
		}
	});
	
	
	//查询表单
	var searchForm = new Ext.FormPanel({
		region:"north",
		height: 220,
		border : false,
		layout : "form",
		padding : "5 20 0 20",
		items:[{
			xtype:"fieldset",
			title:"活动信息",
			padding:"0 20 0 15",
			labelAlign : "right",
			defaults:{
				xtype:"container",
				autoEl:"div",
				layout:"form",
				cls:"borderNone"
			},
			items:[{
				xtype : 'textfield',
				name:'activityname',
				fieldLabel:'活动名称',
				readOnly:true,
				anchor : '95.5%',
				id:'activityname'
			},{
				items:[{
					layout:"column",
					defaults:{
						xtype:"container",
						autoEl:"div",
						layout:"form",
						cls:"borderNone"
					},
					items:[{
						defaults:{
							cls:"borderNone"
						},
						items:[{
							xtype : 'textfield',
							name:'teacher',
							fieldLabel:'指导老师',
							readOnly:true
						},{
							xtype : 'textfield',
							name:'inschoolterm',
							fieldLabel:'所在学期',
							readOnly:true
						},{
							xtype : 'textfield',
							name:'faceobj',
							fieldLabel:'面向对象',
							readOnly:true
						}]
					},{
						defaults:{
							cls:"borderNone"
						},
						items:[{
							xtype : 'textfield',
							name:'phonenum',
							fieldLabel:'联系电话',
							readOnly:true
						},{
							xtype : 'textfield',
							name:'organizename',
							fieldLabel:'组织名称',
							readOnly:true
						},{
							xtype : 'textfield',
							name:'activitygenre',
							fieldLabel:'类　　别',
							readOnly:true
						}]
					},{
						defaults:{
							cls:"borderNone"
						},
						items:[{
							xtype : 'textfield',
							name:'activitytime',
							fieldLabel:'活动时间',
							readOnly:true
						},{
							xtype : 'textfield',
							name:'signupendtime',
							fieldLabel:'报名截止',
							readOnly:true
						},{
							xtype : 'textfield',
							name:'timeofduration',
							fieldLabel:'时　　长',
							readOnly:true
							}]
						},{
							defaults:{
								cls:"borderNone"
							},
							items:[{
								xtype : 'textfield',
								name:'activityplace',
								fieldLabel:'活动地点',
								readOnly:true
							},{
								xtype : 'textfield',
								name:'capacity',
								fieldLabel:'容量(人)',
								readOnly:true
							}]
						}]
				}]
			},{
				xtype : 'textarea',
				name:'activitycontent',
				fieldLabel:'活动内容',
				anchor : '96%',
				readOnly:true
			},{
				xtype:'hidden',
				name:'activityid',
				id:'activityid'
			}]
		}]
	});
	
	//fields类声明
	var Attend = [
	      		{ name:'id', type:'string'},
	      		{ name:'usernum', type:'string'},
	      		{ name:'username',type:'string'},
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
	    url: 'Applyactivity_activityManageAttendList.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: {params:{start:0, limit:15}},
	    fields: Attend,
	});
	
	var rownumber = new Ext.grid.RowNumberer();//行号
    var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[rownumber,
			    {header: '姓名', width: 200,align:'center', dataIndex: 'username'},
			    {header:'学号',width:200,align:'center',dataIndex:'usernum'},
			    {header:'签到',width:200,align:'center',dataIndex:'state'},
			    {header:'总分数',width:200,align:'center',dataIndex:'gross'}
	        ]
        }),
        rownumber:rownumber,//checkbox
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
		loadMask : true,	//加载时的遮罩
		frame : true,
		region:"center",
        iconCls:'menu-51',
        
        tbar:['->',{
        	text:'导出名单',
        	//iconCls:'btn-add',
        	handler:function(){
				var activityid = Ext.getCmp("activityid").getValue();
				var activityname = Ext.getCmp("activityname").getValue();
				Ext.MessageBox.confirm('提示', '确定导出名单吗？', function(ok) {
				   if(ok=='yes'){
					   window.location.href="exportExcel.do?activityid="+activityid+"&activityname="+activityname+"&fileDownKey="+null;
				    }
				});
			}
        },'-',{
        	text:'导入名单',
        	//iconCls:'btn-edit',
        	handler:function(){
        		importWindow.show();
			}
        },'-',publishButton		//提交新闻按钮
		,newsButton,{
        	text:'返回活动页',
        	//iconCls:'btn-edit',
        	handler:function(){
        		window.location.href="applyactivity.jsp";
			}
        }],
        
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
    //文本编辑器
    var ueditor = new Ueditor({
    	id:'contentid',
        allowBlank : true,
        name : 'content',
        anchor : '99%',
        height : 205,
        fieldLabel : '活动内容'
	});
   
  //文件导入form
    var importForm = new Ext.FormPanel({
		layout : 'form',
		fileUpload:true,
		frame:true,
		url :'Applyactivity_importExcel.do',
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
            name: 'upload',  
            inputType: 'file'
		},{
			xtype:'hidden',
			name:'activityid',
		},{
			xtype:'hidden',
			
			name:'sscore',
		}]
	});
    
    //文件导入窗口
    var importWindow = new Ext.Window({
		title : '文件导入',
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
    
    
  //新闻提交窗口
    var addForm = new Ext.FormPanel({
		layout : 'form',
		//baseCls:'x-plain',
		frame:true,
		url :'Applyactivity_submitNews.do',
		labelWidth:48,
		border : false,
		padding : '15 10 0 8',
		defaults : {
			anchor : '100%'
		},
		items:[{
			xtype : 'textfield',
			name:'newstitle',
			fieldLabel:'标题',
			anchor : '97.5%',
			allowBlank : false,
		},{
			id:'websiteid',
			xtype : 'textfield',
			name:'website',
			fieldLabel:'网址',
			anchor : '97.5%',
			emptyText: 'http://www.abc.mnp.com(网址输入格式)',
			regex:/^[a-zA-z]+:\/\/(\w+(-\w+)*)(\.(\w+(-\w+)*))*(\?\S*)?$/,
			listeners:{
				blur : function(a,b){
					var v = Ext.getCmp("websiteid").getValue();
    				if(v!=null && v!=""){
    					Ext.getCmp("contentid").hide();
    				}else{
    					Ext.getCmp("contentid").reset();
    					Ext.getCmp("contentid").show();
    				}
				}
			}
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
					name:'newsdate',
					fieldLabel:'日期',
					xtype:"datefield",
					format:'Y-m-d',
					minValue : new Date(),
					allowBlank : false,
					editable : false,
					maxLength :20
				}]
			},{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype : 'textfield',
					name:'writer',
					fieldLabel:'作者',
					allowBlank : false,
					readOnly:true
				}]
			}]
		},{
			layout:'column',
			items:[{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype : 'radiogroup',
					id   : 'atype',
					name:'property',
					fieldLabel: "属性",  
					items : [{  
					  boxLabel: '校内',  
					  name: 'property',  
					  inputValue:'校内',
					  checked : true
					  },{  
					  boxLabel: '校外',  
					  name: 'property',  
					  inputValue:'校外',
					 }]
				}]
			}/*,{
				xtype : 'checkbox',
				name:'totop',
				boxLabel:'置顶',
				width:140
			},{
				xtype : 'checkbox',
				name:'highlight',
				boxLabel:'高亮',
				width:140
			}*/]
		},ueditor,{
			xtype:'hidden',
			name:'activityid'
		}]
	});
    
   
    //添加form窗口
    var addWindow = new Ext.Window({
		title : '新闻发布',
		width:840,
		height:460,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [addForm],
		buttons : [{
			text : '发　布',
			handler : function() {
				if (addForm.getForm().isValid()) {
					addForm.getForm().submit({
						success : function(form, action) {
							Ext.Msg.alert('信息提示',action.result.message);
							addWindow.hide();
							Ext.getCmp("publishButton").show();
							Ext.getCmp("newsButton").hide();
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
    
    //获取活动详情
    var getActivityDetail = function(){
    	Ext.Ajax.request({
    		url:"Applyactivity_activityDetail.do",
    		success:function(response){
    			var responsedata = Ext.util.JSON.decode(response.responseText);
    			if(responsedata){
    				searchForm.getForm().findField('activityid').setValue(responsedata.data.activityid);
    				searchForm.getForm().findField('activityname').setValue(responsedata.data.activityname);
    				//searchForm.getForm().findField('applyuser').setValue(responsedata.data.applyuser);
    				//searchForm.getForm().findField('studentnum').setValue(responsedata.data.studentnum);
    				//searchForm.getForm().findField('studentphonenum').setValue(responsedata.data.studentphonenum);
    				searchForm.getForm().findField('organizename').setValue(responsedata.data.organizename);
    				searchForm.getForm().findField('teacher').setValue(responsedata.data.teacher);
    				searchForm.getForm().findField('phonenum').setValue(responsedata.data.phonenum);
    				searchForm.getForm().findField('inschoolterm').setValue(responsedata.data.inschoolterm);
    				searchForm.getForm().findField('activitygenre').setValue(responsedata.data.activitygenre);
    				searchForm.getForm().findField('faceobj').setValue(responsedata.data.faceobj);
    				searchForm.getForm().findField('capacity').setValue(responsedata.data.capacity);
    				searchForm.getForm().findField('activityplace').setValue(responsedata.data.activityplace);
    				searchForm.getForm().findField('activitytime').setValue(responsedata.data.activitytime);
    				searchForm.getForm().findField('signupendtime').setValue(responsedata.data.signupendtime);
    				searchForm.getForm().findField('activitycontent').setValue(responsedata.data.activitycontent);
    				searchForm.getForm().findField('timeofduration').setValue(responsedata.data.timeofduration);
    				debugger;
    				importForm.getForm().findField('activityid').setValue(responsedata.data.activityid);
    				importForm.getForm().findField('sscore').setValue(responsedata.data.score);
    				
    				var newsIsOrNotUpload = responsedata.data.newscheckstyle;	//获取新闻是否提交状态
    				var publishIsOrNotUpload = responsedata.data.publicitycheckstyle;
    				//新闻提交按钮的显藏
    				if(newsIsOrNotUpload=="yes"){
    					Ext.getCmp("newsButton").hide();
    					//公示按钮的显藏
        				if(publishIsOrNotUpload=='' || publishIsOrNotUpload==null){
        					Ext.getCmp("publishButton").show();
        				}else{
        					Ext.getCmp("publishButton").hide();
        				}
    				}else{
    					Ext.getCmp("newsButton").show();
    				}
    				/*searchForm.getForm().findField('activitycontent').setValue(responsedata.data.activitycontent);
    				searchForm.getForm().findField('activityname').setValue(responsedata.data.activityname);
    				searchForm.getForm().findField('activityname').setValue(responsedata.data.activityname);
    				searchForm.getForm().findField('activityname').setValue(responsedata.data.activityname);*/
    			}
    		}
    	});
    };
    
    //获取当前用户信息
    var getCurrentUserInfo = function(){
    	Ext.Ajax.request({
    		url:"Applyactivity_getCurrentUserInfo.do",
    		success:function(response){
    			var responsedata = Ext.util.JSON.decode(response.responseText);
    			if(responsedata){
    				addForm.getForm().findField('writer').setValue(responsedata.data.userName);
    			}
    		}
    	});
    };
    
    //布局
    new Ext.Viewport({
		layout:"fit",
		items:[{
			frame:true,
			title:"活动管理",
			iconCls:'menu-15',
			layout:"border",
			items:[searchForm,grid]
		}],
		listeners:{
			render:function(){
    			getActivityDetail();
    			getCurrentUserInfo();
	    		//groundStore2.load();
			}
		}
	});
});