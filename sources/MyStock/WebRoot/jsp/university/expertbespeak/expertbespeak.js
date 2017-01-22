Ext.BLANK_IMAGE_URL = '../../../ext/resources/images/default/s.gif';
/*!
 * 专家信息发布
 */
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//发布专家信息
	var outExpertButton = new Ext.Button({
		id:'outExpertButton',
		width:130,
		//xtype:"button",
		//iconCls:"btn-list",
		text:'发布专家信息',
		handler:function(){
			getExpertInfo();
			addWindow.show();
			addForm.getForm().reset();
		}
	});
	
	//编辑专家信息
	var upExpertButton = new Ext.Button({
		id:'upExpertButton',
		width:130,
		//xtype:"button",
		//iconCls:"btn-list",
		text:'编辑专家信息',
		handler:function(){
			getExpertInfo();
			getStartExpert();
			addWindow.show();
			addForm.getForm().reset();
		}
	});
	
	//园区下拉菜单
    var xyStore = new Ext.data.JsonStore({
		url: '/MyStock/SiteInfo_findParkComb.do',
	    root: 'root',
	    totalProperty: 'total',
	    fields: ['value','text'],
	    listeners:{
	    	load:function(s){
	    		r = s.getAt(0);
	    		if(r){
	    			Ext.getCmp("xyCombo").onSelect(r, 0);
	    		}
	    	}
	    }
	});
    //办公/单位下拉菜单
    var unitStore = new Ext.data.JsonStore({
		url: '/MyStock/Expertbespeak_findUnitComb.do',
	    root: 'root',
	    totalProperty: 'total',
	    fields: ['value','text'],
	    listeners:{
	    	load:function(s){
	    		r = s.getAt(0);
	    		if(r){
	    			Ext.getCmp("unitCombo").onSelect(r, 0);
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
			title:"专家预约",
			padding:"0 20 0 15",
			items:[{
				layout:"column",
				defaults:{
					xtype:"container",
					autoEl:"div",
					labelAlign:"right",
					layout:"form"
				}
			},outExpertButton,
			upExpertButton,{
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
	var StudentbespeakDTO = [
	                { name:'stid', type:'int'},//学生预约id
	                { name:'stdid', type:'int'},//学生id
	                { name:'studentName', type:'string'},
	                { name:'studentXh', type:'string'},
	                { name:'studentXb', type:'string'},
	                { name:'studentXymc', type:'string'},
	                { name:'studentZymc', type:'string'},
	                { name:'studentBjmc', type:'string'},
	                { name:'studentEmail', type:'string'},
	                { name:'studentphone', type:'string'},
	                { name:'bespeaktime', type:'string'},
	                { name:'detailinfo', type:'string'},
	        		{ name:'applygenre', type:'string'},
	        		{ name:'bespeakplace', type:'string'},
	        		{ name:'uploadbespeaktime', type:'string'},
	        		{ name:'bespeakstate', type:'string'},
	        		{ name:'talkcontent', type:'string'},
	        	];
	
	//获取response数据
	var store = new Ext.data.JsonStore({
	    url: '/MyStock/Expertbespeak_myBespeakToExpert.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: {params:{start:0, limit:15}},
	    fields: StudentbespeakDTO,
	});
		
	 //审核预约
	debugger;
	var checkBespeak = function(grid, rowIndex, colIndex){
		//getCurentUserInfo();
		var record = grid.getStore().getAt(rowIndex);
		bespeakCheckWindow.show();
    	bespeakCheckForm.getForm().loadRecord(record);
		
    };
    
    //加入谈话
    var giveTalk = function(grid, rowIndex, colIndex){
    	addTalkForm.getForm().reset();
    	//getCurentUserInfo();
		var record = grid.getStore().getAt(rowIndex);
		addTalkWindow.show();
		addTalkForm.getForm().loadRecord(record);
    };
    
    //查看详情
    var lookDetail = function(grid, rowIndex, colIndex){
    	//getCurentUserInfo();
		var record = grid.getStore().getAt(rowIndex);
		//alert(record.get("stid"));
		lookDetailWindow.show();
		lookDetailForm.getForm().loadRecord(record);
    };
    
	//var sm = new Ext.grid.CheckboxSelectionModel();//单选{singleSelect: true}
	var rownumber = new Ext.grid.RowNumberer();
    var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[rownumber,
			    {header: '咨询类别', width: 100,align:'center', dataIndex: 'applygenre'},
			    {header:'预约地点',width:100,align:'center',dataIndex:'bespeakplace'},
			    {header:'预约提交时间',width:100,align:'center',dataIndex:'uploadbespeaktime'},
	            {header: '状态', width: 100,align:'center', dataIndex: 'bespeakstate'},
	            {xtype: 'actioncolumn',header : '操作', width: 130,align : 'center',menuDisabled : true,
	                items: [{
	                    icon: '../../../img/btn/shouli.png',
	                    //tooltip: '审核',
	                    getClass: function(value, metaData, record, rowIndex, colIndex, store, view) {      
	                		if('已取消'==record.data.bespeakstate || '已接受'==record.data.bespeakstate || '已拒绝'==record.data.bespeakstate || '已完成'==record.data.bespeakstate || '已完成　待反馈'==record.data.bespeakstate){
	                              return 'x-hidden'
	                        }
	                	},
	                    handler: function(grid, rowIndex, colIndex){
	                		checkBespeak(grid, rowIndex, colIndex);//调用方法
	                	}
	                },{
	                    icon: '../../../img/btn/talk.png',
	                    //tooltip: '谈话',
	                    getClass: function(value, metaData, record, rowIndex, colIndex, store, view) {      
	                		if('已取消'==record.data.bespeakstate || '待接受'==record.data.bespeakstate || '已拒绝'==record.data.bespeakstate || '已完成'==record.data.bespeakstate){
	                              return 'x-hidden'
	                        }
	                	},
	                    handler: function(grid, rowIndex, colIndex){
	                		giveTalk(grid, rowIndex, colIndex);//调用方法
	                	}
	                },{
	                    icon: '../../../img/btn/see.png',
	                    //tooltip: '查看详情',
	                    getClass: function(value, metaData, record, rowIndex, colIndex, store, view) {      
	                		if('已接受'==record.data.bespeakstate || '待接受'==record.data.bespeakstate || '已拒绝'==record.data.bespeakstate || '已完成　待反馈'==record.data.bespeakstate){
	                              return 'x-hidden'
	                        }
	                	},
	                    handler: function(grid, rowIndex, colIndex){
	                		lookDetail(grid, rowIndex, colIndex);//调用方法
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
    
  //发布专家信息
    var addForm = new Ext.FormPanel({
		layout : 'form',
		frame:true,
		url :'/MyStock/Expertbespeak_saveOrUpdateExpertBespeak.do',
		fileUpload:true,  
		labelWidth:62,
		border : false,
		padding : '30 10 10 30',
		defaults : {
			anchor : '100%',
			labelAlign:"right",
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
					autoEl:{
						height : 120,
						width:40,
						tag:'img',
						src:''
					}
			    }]
			},{
				columnWidth:.35,
				layout:'form',
				defaults:{
					anchor : '95%',
					//cls:'borderNone'
				},
				items:[{
					xtype : 'textfield',
					name:'xm',
					fieldLabel:'姓<i class="ikg2"></i>名',
					allowBlank : false,
					//readOnly:true,
					maxLength :20
				},{
					id:'xyCombo',
					xtype:'combo',
					hiddenName:'ssyq',
					fieldLabel:'所属园区',
					mode: 'local',
					triggerAction: 'all',
					valueField :"value",
					displayField: "text",
					allowBlank : false,
					editable : false,
					store : xyStore,
					emptyText: '全部',
					allowBlank : false,
					listeners:{
						select : function(a,b){
							addForm.getForm().findField("ssyq").setValue(b.data.text);
						}
					}
				},{
					xtype : 'textfield',
					name:'phone',
					fieldLabel:'联系电话',
					maxLength :11,
					regex:/^(13\d|15[^4,\D]|17[13678]|18\d)\d{8}|170[^346,\D]\d{7}$/,
					allowBlank : false,
				},{
					xtype:'combo',
					hiddenName:'expertType',
					fieldLabel:'专家类别',
					mode: 'local',
					triggerAction: 'all',
					editable : false,
					allowBlank : false,
					store : [['学业指导','学业指导'],['心理辅导','心理辅导'],['职业规划','职业规划'],['出国指导','出国指导'],['其他','其他']],
					value : '学业指导'
					
				},{
					xtype : 'textfield',
					name:'upload',
					fieldLabel:'个人照片',
					//anchor: '100%',
					inputType: 'file',
					
				}]
			},{
				columnWidth:.35,
				layout:'form',
				defaults:{
					anchor : '100%',
					//cls:'borderNone'
				},
			    items:[{
					xtype : 'combo',
					hiddenName:'xb',
					fieldLabel:'性<i class="ikg2"></i>别',
					mode: 'local',
					triggerAction: 'all',
					editable : false,
					allowBlank : false,
					store : [['男','男'],['女','女']],
				},{
					xtype : 'textfield',
					name:'email',
					fieldLabel:'邮<i class="ikg2"></i>箱',
					vtype:'email',
					//readOnly:true,
				},{
					id:'unitCombo',
					xtype:'combo',
					hiddenName:'unit',
					fieldLabel:'办公/单位',
					mode: 'local',
					triggerAction: 'all',
					valueField :"value",
					displayField: "text",
					allowBlank : false,
					editable : false,
					store : unitStore,
					emptyText: '全部',
					allowBlank : false,
					maxLength :20,
					listeners:{
						select : function(a,b){
							addForm.getForm().findField("unit").setValue(b.data.text);
						}
					}
					
				},{
					xtype : 'textfield',
					cls:'borderNone'
				},{
					xtype : 'textfield',
					cls:'borderNone',
					style:'color:red;',  
					fieldLabel:'照片格式',
					value:'"请您选择正方形图片"',
					readOnly:true
				}]
			}]
		},{
			xtype : 'textfield',
			name:'freetime',
			fieldLabel:'空余时间',
			allowBlank : false,
			anchor : '98%',
			maxLength :100
		},{
			xtype : 'textfield',
			name:'workaddress',
			fieldLabel:'办公地址',
			allowBlank : false,
			anchor : '98%',
			maxLength :50
		},{
			xtype : 'textarea',
			name:'introduce',
			fieldLabel:'个人简历',
			maxLength :120,
			anchor : '98%',
			//height：30
		},{
			xtype:'hidden',
			name:'id'
		},{
			xtype:'hidden',
			name:'photo'
		}]
	});
    
    //添加form窗口
    var addWindow = new Ext.Window({
		title : '专家预约信息',
		width:730,
		height:380,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [addForm],
		buttons : [{
			text : '确<i class="ikg2"></i>定',
			handler : function() {
				if (addForm.getForm().isValid()) {
					addForm.getForm().submit({
						success : function(form, action) {
							Ext.Msg.alert('信息提示',action.result.message,function(){
								//addWindow.hide();
								window.location.href="expertbespeak.jsp";
							});
							//getExpertInfo();//刷新显示按钮
							//store.reload();
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
		}/*,{
			text : '取消',
			handler : function() {
				addWindow.hide();
			}
		}*/]
	});
    
    //预约审核
    var bespeakCheckForm = new Ext.FormPanel({
		layout : 'form',
		frame:true,
		url :'/MyStock/Expertbespeak_checkBespeak.do',
		labelWidth:80,
		border : false,
		labelAlign:"right",
		padding : '15 10 0 8',
		defaults : {
			anchor : '100%',
			cls:'borderNone'
		},
		items:[{
			layout:'column',
			items:[{
				columnWidth:.5,
				layout:'form',
				//labelAlign:"right",
				defaults:{
					anchor : '80%',
					cls:'borderNone'
				},
				items:[{
					xtype : 'textfield',
					name:'studentName',
					fieldLabel:'姓<i class="ikg2"></i>名',
					allowBlank : false,
					readOnly:true,
					maxLength :20
				},{
					xtype : 'textfield',
					name:'studentXb',
					fieldLabel:'性<i class="ikg2"></i>别',
					allowBlank : false,
					readOnly:true,
					maxLength :20
				}/*,{
					xtype : 'textfield',
					name:'studentZymc',
					fieldLabel:'专业',
					readOnly:true,
				}*/,{
					xtype : 'textfield',
					name:'studentphone',
					fieldLabel:'联系电话',
					allowBlank : false,
					readOnly:true,
					maxLength :11,
				},{
					xtype : 'textfield',
					name:'applygenre',
					fieldLabel:'申请类别',
					readOnly:true
				},{
					xtype : 'textfield',
					name:'bespeakplace',
					fieldLabel:'预约地点',
					allowBlank : false,
					readOnly:true,
					maxLength :20
				}]
			},{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '90%',
					cls:'borderNone'
				},
				items:[{
					xtype : 'textfield',
					name:'studentXh',
					fieldLabel:'学<i class="ikg2"></i>号',
					readOnly:true,
					maxLength :20,
				},/*{
					xtype : 'textfield',
					name:'studentXymc',
					fieldLabel:'院系',
					allowBlank : false,
					readOnly:true,
					maxLength :20
				},*/{
					xtype : 'textfield',
					name:'studentBjmc',
					fieldLabel:'班<i class="ikg2"></i>级',
					allowBlank : false,
					readOnly:true,
					maxLength :20
				},{
					xtype:'textfield',
					name:'studentEmail',
					fieldLabel:'邮<i class="ikg2"></i>箱',
					readOnly:true,
				},{
					xtype:'textfield',
					name:'bespeaktime',
					fieldLabel:'预约时间',
					readOnly:true,
				}]
			}]
		},{
			xtype : 'textarea',
			name:'detailinfo',
			fieldLabel:'详细信息',
			anchor : '97%',
			readOnly:true
		},{
			xtype:'hidden',
			name:'stid'
		}]
	});
    
    //添加form窗口
    var bespeakCheckWindow = new Ext.Window({
		title : '预约审批',
		width:650,
		height:300,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [bespeakCheckForm],
		buttons : [{
			text : '拒　绝',
			handler : function() {
				if (bespeakCheckForm.getForm().isValid()) {
					bespeakCheckForm.getForm().submit({
						params :{checkkey:1},
						success : function(form, action) {
							Ext.Msg.alert('信息提示',action.result.message);
							bespeakCheckWindow.hide();
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
		},{
			text : '接受预约',
			handler : function() {
				if (bespeakCheckForm.getForm().isValid()) {
					bespeakCheckForm.getForm().submit({
						params :{checkkey:2},
						success : function(form, action) {
							Ext.Msg.alert('信息提示',action.result.message);
							bespeakCheckWindow.hide();
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
    
    //预约谈话
    var addTalkForm = new Ext.FormPanel({
		layout : 'form',
		frame:true,
		url :'/MyStock/Expertbespeak_addTalk.do',
		labelWidth:62,
		border : false,
		padding : '15 10 0 50',
		defaults : {
			anchor : '100%'
		},
		items:[{
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
					name:'studentName',
					fieldLabel:'姓名',
					allowBlank : false,
					readOnly:true,
					maxLength :20
				},{
					xtype : 'textfield',
					name:'studentXb',
					fieldLabel:'性别',
					allowBlank : false,
					readOnly:true,
					maxLength :20
				},/*{
					xtype : 'textfield',
					name:'studentZymc',
					fieldLabel:'专业',
					readOnly:true,
				},*/{
					xtype : 'textfield',
					name:'studentphone',
					fieldLabel:'联系电话',
					allowBlank : false,
					readOnly:true,
					maxLength :11,
				},{
					xtype : 'textfield',
					name:'applygenre',
					fieldLabel:'申请类别',
					readOnly:true
				}]
			},{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '94%',
					cls:'borderNone'
				},
				items:[{
					xtype : 'textfield',
					name:'studentXh',
					fieldLabel:'学号',
					readOnly:true,
					maxLength :20,
				},/*{
					xtype : 'textfield',
					name:'studentXymc',
					fieldLabel:'院系',
					allowBlank : false,
					readOnly:true,
					maxLength :20
				},*/{
					xtype : 'textfield',
					name:'studentBjmc',
					fieldLabel:'班级',
					allowBlank : false,
					readOnly:true,
					maxLength :20
				},{
					xtype:'textfield',
					name:'studentEmail',
					fieldLabel:'邮箱',
					readOnly:true,
				},{
					xtype:'textfield',
					name:'bespeaktime',
					fieldLabel:'预约时间',
					readOnly:true,
				}]
			}]
		},{
			xtype : 'textfield',
			name:'bespeakplace',
			fieldLabel:'预约地点',
			allowBlank : false,
			readOnly:true,
			anchor : '97%',
			maxLength :20,
			cls:'borderNone'
		},{
			xtype : 'textfield',
			name:'detailinfo',
			fieldLabel:'详细信息',
			anchor : '97%',
			readOnly:true,
			cls:'borderNone'
		},{
			xtype : 'textarea',
			name:'talkcontent',
			fieldLabel:'谈话内容',
			height : 80,
			anchor : '90%',
			maxLength :200
		},{
			xtype:'hidden',
			name:'stid'
		}]
	});
    
    //添加form窗口
    var addTalkWindow = new Ext.Window({
		title : '谈话反馈',
		width:600,
		height:380,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [addTalkForm],
		buttons : [{
			text : '完　成',
			handler : function() {
				if (addTalkForm.getForm().isValid()) {
					addTalkForm.getForm().submit({
						success : function(form, action) {
							Ext.Msg.alert('信息提示',action.result.message);
							addTalkWindow.hide();
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
    
    //查看详情
    var lookDetailForm = new Ext.FormPanel({
		layout : 'form',
		frame:true,
		labelWidth:62,
		border : false,
		padding : '15 10 0 68',
		defaults : {
			anchor : '100%'
		},
		items:[{
			layout:'column',
			items:[{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '90%',
					cls:'borderNone'
				},
				items:[{
					xtype : 'textfield',
					name:'studentName',
					fieldLabel:'姓<i class="ikg2"></i>名',
					allowBlank : false,
					readOnly:true,
					maxLength :20
				},{
					xtype : 'textfield',
					name:'studentXb',
					fieldLabel:'性<i class="ikg2"></i>别',
					allowBlank : false,
					readOnly:true,
					maxLength :20
				}/*,{
					xtype : 'textfield',
					name:'studentZymc',
					fieldLabel:'专<i class="ikg2"></i>业',
					readOnly:true,
				}*/,{
					xtype : 'textfield',
					name:'studentphone',
					fieldLabel:'联系电话',
					allowBlank : false,
					readOnly:true,
					maxLength :11,
				},{
					xtype : 'textfield',
					name:'applygenre',
					fieldLabel:'申请类别',
					readOnly:true
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
					name:'studentXh',
					fieldLabel:'学<i class="ikg2"></i>号',
					readOnly:true,
					maxLength :20,
				},/*{
					xtype : 'textfield',
					name:'studentXymc',
					fieldLabel:'院<i class="ikg2"></i>系',
					allowBlank : false,
					readOnly:true,
					maxLength :20
				},*/{
					xtype : 'textfield',
					name:'studentBjmc',
					fieldLabel:'班<i class="ikg2"></i>级',
					allowBlank : false,
					readOnly:true,
					maxLength :20
				},{
					xtype:'textfield',
					name:'studentEmail',
					fieldLabel:'邮<i class="ikg2"></i>箱',
					readOnly:true,
				},{
					xtype:'textfield',
					name:'bespeaktime',
					fieldLabel:'预约时间',
					readOnly:true,
				}]
			}]
		},{
			xtype : 'textfield',
			name:'bespeakplace',
			fieldLabel:'预约地点',
			allowBlank : false,
			anchor : '97%',
			readOnly:true,
			cls:'borderNone'
		},{
			xtype : 'textfield',
			name:'detailinfo',
			fieldLabel:'详细信息',
			anchor : '97%',
			readOnly:true,
			cls:'borderNone'
		},{
			xtype : 'textarea',
			name:'talkcontent',
			fieldLabel:'谈话内容',
			height : 130,
			anchor : '90%',
			readOnly:true,
			cls:'borderNone'
		}]
	});
    
    //添加form窗口
    var lookDetailWindow = new Ext.Window({
		title : '详细信息',
		width:600,
		height:380,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [lookDetailForm],
	});
    
	//获取当前登录专家的信息
	var getCurentUserInfo = function(){
    	Ext.Ajax.request({
    		url:"/MyStock/Expertbespeak_getCurentUserInfo.do",
    		success:function(response){
    			var responsedata = Ext.util.JSON.decode(response.responseText);
    			//alert(responsedata.data.xm);
    			addForm.getForm().findField('xm').setValue(responsedata.data.xm);
    			addForm.getForm().findField('xb').setValue(responsedata.data.xb);
    			addForm.getForm().findField('phone').setValue(responsedata.data.phone);
    			//alert(responsedata.data.phone);
    		}
    	});
    };
    
  //获取当前专家信息
	var getExpertInfo = function(){
    	Ext.Ajax.request({
    		url:"/MyStock/Expertbespeak_getExpertDetailInfo.do",
    		success:function(response){
    			var responsedata = Ext.util.JSON.decode(response.responseText);
    			
    			if(responsedata){
    				if(responsedata.data.id){
    					addForm.getForm().findField('id').setValue(responsedata.data.id);
    				}
	    			if(responsedata.data.xm){
	    				//alert(1);
	    				Ext.getCmp("outExpertButton").hide();
	    				//Ext.getCmp("upExpertButton").show();
	    				addForm.getForm().findField('xm').setValue(responsedata.data.xm);
	    			}else{
	    				Ext.getCmp("upExpertButton").hide();
	    				getCurentUserInfo();
	    			}
					if(responsedata.data.xb){
						addForm.getForm().findField('xb').setValue(responsedata.data.xb);
					}
					if(responsedata.data.phone){
						addForm.getForm().findField('phone').setValue(responsedata.data.phone);
					}
					if(responsedata.data.email){
						addForm.getForm().findField('email').setValue(responsedata.data.email);
					}
					if(responsedata.data.expertType){
						addForm.getForm().findField('expertType').setValue(responsedata.data.expertType);
					}
					if(responsedata.data.unit){
						addForm.getForm().findField('unit').setValue(responsedata.data.unit);
					}
					if(responsedata.data.introduce){
						addForm.getForm().findField('introduce').setValue(responsedata.data.introduce);
					}
					if(responsedata.data.photo && Ext.getCmp("photo").getEl()){
						addForm.getForm().findField('photo').setValue(responsedata.data.photo);
	    				Ext.getCmp("photo").getEl().dom.src = responsedata.data.photo;
					}else if(Ext.getCmp("photo").getEl()){
						Ext.getCmp("photo").getEl().dom.src = "../../../img/defaultpic/photo.jpg";
					}
    			}
    		}
    	});
    };
    
    var getStartExpert = function(){
    	Ext.Ajax.request({
    		url:"/MyStock/Expertbespeak_getStartExpert.do",
    		success:function(response){
    			var responsedata = Ext.util.JSON.decode(response.responseText);
    			if(responsedata){
					if(responsedata.data.freetime){
						addForm.getForm().findField('freetime').setValue(responsedata.data.freetime);
					}
					if(responsedata.data.workaddress){
						addForm.getForm().findField('workaddress').setValue(responsedata.data.workaddress);
					}
    			}
    		}
    	});
    };
    
    
    //布局
    new Ext.Viewport({
		layout:"fit",
		items:[{
			frame:true,
			title:"专家预约",
			iconCls:'menu-15',
			layout:"border",
			items:[searchForm,grid]
		}],
		listeners:{
			render:function(){
    			getExpertInfo();
		    	xyStore.load();
		    	unitStore.load();
			}
		}
	});
});