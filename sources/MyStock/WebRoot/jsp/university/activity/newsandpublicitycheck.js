Ext.BLANK_IMAGE_URL = '../../../ext/resources/images/default/s.gif';
/*!
 * 活动管理
 */
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//查询表单
	var searchForm = new Ext.FormPanel({
		region:"north",
		height: 230,
		border : false,
		layout : "form",
		padding : "2 20 0 20",
		items:[{
			xtype:"fieldset",
			title:"活动信息",
			padding:"0 20 0 15",
			labelAlign : "right",
			defaults:{
				xtype:"container",
				autoEl:"div",
				layout:"form",
				cls:'borderNone'
			},
			items:[{
				xtype : 'textfield',
				name:'activityname',
				fieldLabel:'活动名称',
				readOnly:true,
				anchor : '95.5%',
			},{
				items:[{
					layout:"column",
					defaults:{
						xtype:"container",
						autoEl:"div",
						layout:"form"
					},
					items:[{
						defaults:{
							cls:'borderNone'
						},
						items:[{
							xtype : 'textfield',
							name:'applyuser',
							fieldLabel:'申 请 人',
							readOnly:true
						},{
							xtype : 'textfield',
							name:'inschoolterm',
							fieldLabel:'所在学期',
							readOnly:true
						},{
							xtype : 'textfield',
							name:'activityplace',
							fieldLabel:'活动地点',
							readOnly:true
						},{
							xtype : 'textfield',
							name:'teacher',
							fieldLabel:'指导老师',
							readOnly:true
						}]
					},{
						defaults:{
						cls:'borderNone'
					},
						items:[{
							xtype : 'textfield',
							name:'studentnum',
							fieldLabel:'学　　号',
							readOnly:true
						},{
							xtype : 'textfield',
							name:'activitygenre',
							fieldLabel:'类　　别',
							readOnly:true
						},{
							xtype : 'textfield',
							name:'timeofduration',
							fieldLabel:'时　　长',
							readOnly:true
						},{
							xtype : 'textfield',
							name:'phonenum',
							fieldLabel:'联系电话',
							readOnly:true
						}]
					},{
						defaults:{
							cls:'borderNone'
						},
						items:[{
							xtype : 'textfield',
							name:'studentphonenum',
							fieldLabel:'联系电话',
							readOnly:true
						},{
							xtype : 'textfield',
							name:'faceobj',
							fieldLabel:'面向对象',
							readOnly:true
						},{
							xtype : 'textfield',
							name:'activitytime',
							fieldLabel:'活动时间',
							readOnly:true
						}]
					},{
						defaults:{
							cls:'borderNone'
						},
						items:[{
							xtype : 'textfield',
							name:'organizename',
							fieldLabel:'组织名称',
							readOnly:true
						},{
							xtype : 'textfield',
							name:'capacity',
							fieldLabel:'容量(人)',
							readOnly:true
						},{
							xtype : 'textfield',
							name:'signupendtime',
							fieldLabel:'报名截止',
							readOnly:true
						},{
							cls :'returnBtn',
							width:75,
							xtype:"button",
							//style:'margin-top:25px',
							text:"返<i class='ikg2'></i>回",
							handler:function(){
									   window.location.href="activitypublicity.jsp";
							}
						}]
					}]
				}]
			},{
				xtype : 'textarea',
				name:'activitycontent',
				fieldLabel:'活动内容',
				anchor : '95.5%',
				height: 50,
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
	      		
	      	];
	
	//获取response数据
	var store = new Ext.data.JsonStore({
	    url: '/MyStock/Applyactivity_activityManageAttendList.do',
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
			    {header: '姓名', width: 100,align:'center', dataIndex: 'username'},
			    {header:'学号',width:100,align:'center',dataIndex:'usernum'},
			    {header:'班级',width:100,align:'center',dataIndex:'classes'},
			    {header:'辅导员',width:100,align:'center',dataIndex:'counsellor'},
			    {header:'联系电话',width:100,align:'center',dataIndex:'attendstudentphonenum'},
			    {header:'寝室',width:100,align:'center',dataIndex:'dorm'},
			    {header:'签到',width:100,align:'center',dataIndex:'state'},
			    {header:'所加分数',width:100,align:'center',dataIndex:'gotscore',
	              renderer:function(value, metaData, record, rowIndex, colIndex, store, view){
		            	var returnStr = "<a href='javascript:void(0)' onclick='updateGotscore("+rowIndex+")'>" +value+"</a>";
		                return returnStr;
	              }
			    },
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
        	text:'新闻审核',
        	handler:function(){
        		var activityid = Ext.getCmp("activityid").getValue();
        		Ext.Ajax.request({
					url:'/MyStock/Applyactivity_newsDetail.do',
					params:{activityid:activityid},
					success:function(response){
		    			var responsedata = Ext.util.JSON.decode(response.responseText);
		    			if(responsedata){
		    				addForm.getForm().findField('newsid').setValue(responsedata.data.newsid);
		    				addForm.getForm().findField('newstitle').setValue(responsedata.data.newstitle);
		    				addForm.getForm().findField('website').setValue(responsedata.data.website);
		    				addForm.getForm().findField('newsdate').setValue(responsedata.data.newsdate);
		    				addForm.getForm().findField('writer').setValue(responsedata.data.writer);
		    				addForm.getForm().findField('property').setValue(responsedata.data.property);
		    				addForm.getForm().findField('content').setValue(responsedata.data.content);
							addWindow.show();
		    			}
		    		}
				});
			}
        },'-',{
        	text:'允许公示',
        	handler:function(){
        		if(store.getCount() != 0) {
        			var activityid = Ext.getCmp("activityid").getValue();
        			Ext.MessageBox.confirm('公示提示', '确定通过该活动审核并参与公示吗？', function(ok) {
        				if(ok=='yes'){
        					Ext.Ajax.request({
        						url : "/MyStock/Applyactivity_publishActivity.do",
        						params:{ activityid : activityid,pulishkey:'1'},
        						success : function(response) {
        							var responsedata = Ext.util.JSON.decode(response.responseText);
        							Ext.Msg.alert('信息提示',responsedata.message,function(){
        								window.location.href="activitypublicity.jsp";
        							});
        							//store.reload();
        						}
        					});
        				}
        			});
        		} else {
        			 Ext.Msg.alert("提示", "学生没提交公示，不允许公示");
        		}
			}
        },'-',{
        	text:'不允许公示',
        	handler:function(){
        		if(store.getCount() != 0) {
        			var activityid = Ext.getCmp("activityid").getValue();
        			Ext.MessageBox.confirm('公示提示', '确定该活动不允许公示吗？', function(ok) {
        				if(ok=='yes'){
        					Ext.Ajax.request({
        						url : "/MyStock/Applyactivity_publishActivity.do",
        						params:{ activityid : activityid,pulishkey:'2'},
        						success : function(response) {
        							var responsedata = Ext.util.JSON.decode(response.responseText);
        							Ext.Msg.alert('信息提示',responsedata.message,function(){
        								window.location.href="activitypublicity.jsp";
        							});
        						}
        					});
        				}
        			});
        		} else {
        			 Ext.Msg.alert("提示", "学生没提交公示，不允许公示");
        		}
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
        allowBlank : true,
        name : 'content',
        anchor : '99%',
        height : 205,
        fieldLabel : '活动内容'
	});
    
  //新闻审核窗口
    var addForm = new Ext.FormPanel({
		layout : 'form',
		//baseCls:'x-plain',
		frame:true,
		url :'/MyStock/Applyactivity_submitNews.do',
		labelWidth:60,
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
			xtype : 'textfield',
			name:'website',
			fieldLabel:'网址',
			anchor : '97.5%',
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
			},{
				xtype : 'checkbox',
				name:'totop',
				boxLabel:'置顶',
				width:140
			},{
				xtype : 'checkbox',
				name:'highlight',
				boxLabel:'高亮',
				width:140
			}]
		},ueditor,{
			xtype:'hidden',
			name:'activityid'
		},{
			xtype:'hidden',
			name:'newsid'
		}]
	});
    
   
    //添加form窗口
    var addWindow = new Ext.Window({
		title : '添加窗口',
		width:840,
		height:450,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [addForm],
		buttons : [{
			text : '确认并发布',
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
		}]
	});
    
    //获取活动详情
    var getActivityDetail = function(){
    	Ext.Ajax.request({
    		url:"/MyStock/Applyactivity_activityDetail.do",
    		success:function(response){
    			var responsedata = Ext.util.JSON.decode(response.responseText);
    			if(responsedata){
    				searchForm.getForm().findField('activityid').setValue(responsedata.data.activityid);
    				searchForm.getForm().findField('activityname').setValue(responsedata.data.activityname);
    				searchForm.getForm().findField('applyuser').setValue(responsedata.data.applyuser);
    				searchForm.getForm().findField('studentnum').setValue(responsedata.data.studentnum);
    				searchForm.getForm().findField('studentphonenum').setValue(responsedata.data.studentphonenum);
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
    				/*searchForm.getForm().findField('activitycontent').setValue(responsedata.data.activitycontent);
    				searchForm.getForm().findField('activityname').setValue(responsedata.data.activityname);
    				searchForm.getForm().findField('activityname').setValue(responsedata.data.activityname);
    				searchForm.getForm().findField('activityname').setValue(responsedata.data.activityname);*/
    			}
    		}
    	});
    };
    
    //布局
    new Ext.Viewport({
		layout:"fit",
		items:[{
			frame:true,
			title:"申请活动",
			iconCls:'menu-15',
			layout:"border",
			items:[searchForm,grid]
		}],
		listeners:{
			render:function(){
    			getActivityDetail();
			}
		}
	});
    fungrid = grid;
});
var fungrid;

var updateGotscore = function(rowIndex){
	
    var updateScoreForm = new Ext.FormPanel({
		layout : 'form',
		//baseCls:'x-plain',
		frame:true,
		url :'/MyStock/Applyactivity_updateGotscore.do',
		labelWidth:60,
		border : false,
		padding : '15 10 0 8',
		defaults : {
			anchor : '100%'
		},
		items:[{
			xtype : 'textfield',
			name:'sscore',
			fieldLabel:'所加分数',
			anchor : '97.5%',
			allowBlank : false,
			regex:/^[-+]?[\d]+$/,
			regexText:'请输入正确的整数',
		},{
			xtype:'hidden',
			name:'activityid'
		}]
	});
    
    //添加form窗口
    var updateScoreWindow = new Ext.Window({
		title : '所加分数',
		width:300,
		height:200,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [updateScoreForm],
		buttons : [{
			text : '确认所加',
			handler : function() {
				if (updateScoreForm.getForm().isValid()) {
					updateScoreForm.getForm().submit({
						success : function(form, action) {
							Ext.Msg.alert('信息提示',action.result.message);
							updateScoreWindow.hide();
							fungrid.getStore().reload();
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
    
    var record = fungrid.getStore().getAt(rowIndex);
    
	updateScoreForm.getForm().findField('sscore').setValue(record.data.gotscore);
	updateScoreForm.getForm().findField('activityid').setValue(record.data.id);
	updateScoreWindow.show();
};