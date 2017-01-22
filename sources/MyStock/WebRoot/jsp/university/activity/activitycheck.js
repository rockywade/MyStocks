Ext.BLANK_IMAGE_URL = '../../../ext/resources/images/default/s.gif';
/*!
 * 场地管理
 */
Ext.onReady(function(){
	Ext.QuickTips.init();
	
    /*活动类别*/
    var genre = new Ext.data.SimpleStore({
    	fields:['value','text'],
    	data:[['学科讲座','学科讲座'],['心理健康','心理健康'],['职业规划','职业规划'],
    	      ['党团建设','党团建设'],['文体活动','文体活动'],['其他','其他'],['','全部']]
    });
    
    /*活动状态*/
    var style = new Ext.data.SimpleStore({
    	fields:['value','text'],
    	data:[['待审核','待审核'],['已通过','已通过'],['不通过','不通过'],['','全部']]
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
								name:"actionName",
								fieldLabel:"活动名称"
							}]
						},{

							width:260,
							items:[{
								//id:'tab_comb1',
								width : 150,
								xtype:"combo",
								hiddenName:'activitygenre',
								fieldLabel:"类别",
		   						mode: 'local',
		   						triggerAction: 'all',
								valueField :"value",
		   						displayField: "text",
		   						allowBlank : true,
		   						editable : false,
								store : genre,
								//emptyText: '全部',
							}]
								
						},{
							width:260,
							items:[{
								width:100,
								id:'activeCombo',								
								xtype:'combo',
								hiddenName:'applystyle',
								fieldLabel:'状态',
								mode: 'local',
								triggerAction: 'all',
								valueField :'value',
								displayField: 'text',
								allowBlank : true,
								editable : false,
								store : style,
								emptyText: '全部',						}]
								
						},{
					width:260,
					items:[{
						width:75,
						xtype:"button",
						//iconCls:"btn-list",
						style:'margin-left:20px',
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
				},{
					width:100,
					items:[{
						cls :'returnBtn',
						width:75,
						xtype:"button",
						//style:'margin-top:25px',
						text:"返<i class='ikg2'></i>回",
						handler:function(){
								   window.location.href="../../menu/activity-managemen.html";
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
		{ name:'attendnum', type:'int'},
		{ name:'activityplace', type:'string'},
		{ name:'timeofduration', type:'string'},
		{ name:'activitycontent', type:'string'},
		{ name:'signupendtime', type:'string'},
		{ name:'score', type:'int'},
		{ name:'applystyle', type:'string'},
		{ name:'checktime', type:'string'},
		{ name:'uploadnewstime', type:'string'},
		{ name:'activitypublicitytime', type:'string'},
		{ name:'newscheckstyle', type:'string'},
		{ name:'publicitycheckstyle', type:'string'}
	];
	
    var store = new Ext.data.JsonStore({
	    url: '/MyStock/Applyactivity_findPageApply.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: {params:{start:0, limit:15}},
	    fields: Activity
	});
    
    //审核方法
	var check = function(grid, rowIndex, colIndex){
    	var record = grid.getStore().getAt(rowIndex);
		checkingWindow.show();
		checkaddWindow.getForm().loadRecord(record);
    };
    
	//var sm = new Ext.grid.CheckboxSelectionModel();//单选{singleSelect: true}
    var rownumber = new Ext.grid.RowNumberer();//行号
    var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[rownumber,
			    {header: '活动名称', width: 100,align:'center', dataIndex: 'activityname'},
			    {header:'活动类别',width:100,align:'center',dataIndex:'activitygenre'},
			    {header:'活动时间',width:100,align:'center',dataIndex:'activitytime'},
	            {header: '申请人', width: 100,align:'center', dataIndex: 'applyuser'},
	            {header: '所在学期', width: 100,align:'center', dataIndex: 'inschoolterm'},
	            {header: '活动地点', width: 100,align:'center', dataIndex: 'activityplace'},
	            {header: '活动考评分', width: 100,align:'center', dataIndex: 'score'},
	            {header: '状态', width: 100,align:'center', dataIndex: 'applystyle',renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
		               if(value == '已通过') { 
	                        return "<span style='color:green;font-weight:bold;'>已通过</span>"
	                   }     
	                   if(value == '不通过') { 
	                      return "<span style='color:red;font-weight:bold;'>未通过</span>"
	                   }
	                   if(value == '待审核') { 
		                      return "<span style='color:blue;font-weight:bold;'>待审核</span>"
		                   } 
	                }},
	            {xtype: 'actioncolumn',header : '操作', width: 100,align : 'center',menuDisabled : true,
	                items: [{
	                    icon: '../../../img/btn/check.png',
	                    //tooltip: '修改',
	                    getClass: function(value, metaData, record, rowIndex, colIndex, store, view) {      
	                		if('待审核'!=record.data.applystyle){
	                              return 'x-hidden'
	                        }
	                	},
	                    handler: function(grid, rowIndex, colIndex){
	                		check(grid, rowIndex, colIndex);//调用审核方法
	                	}
	                }]
	           }]
        }),
        rownumber:rownumber,//行号
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
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
    
    //审核窗口
    var checkaddWindow=new Ext.FormPanel({
		layout : 'form',//纵向布局
		//baseCls:'x-plain',//基本色调
		url : '/MyStock/Applyactivity_activityCheck.do',
        fileUpload:true,  
        autoScroll:true,//滚动条
		labelWidth:72,
		border : false,
		padding : '8 0 0 10',//上右下左
		defaults : {
			anchor : '100%',
    		//cls:"borderNone",
    		//width:300,
		},
		items:[{
				xtype : 'displayfield',
				name:'activityname',
				fieldLabel:'活动名称',
				//readOnly:true,
				anchor : '90%',
				//maxLength :50,
				//allowBlank : false
			},{
				layout:'column',
				items:[{
					columnWidth:.5,
					layout:'form',
					defaults:{
						anchor : '95%',
						//cls:"borderNone",
					},
					items:[{
						xtype : 'displayfield',
						name:'applyuser',
						fieldLabel:'申<i class="ikg1"></i>请<i class="ikg1"></i>人',
						readOnly:true,
					},{
						xtype : 'displayfield',
						name:'studentphonenum',
						fieldLabel:'联系电话',
						readOnly:true,
					},{
						xtype : 'displayfield',
						name:'teacher',
						fieldLabel:'指导老师',
						readOnly:true,
						
					},{
						xtype : 'displayfield',
						name:'inschoolterm',
						fieldLabel:'所在学期',
						readOnly:true,
					},{
						xtype : 'displayfield',
						name:'timeofduration',
						fieldLabel:'时<i class="ikg2"></i>长',
						readOnly:true,
					},{
						xtype : 'displayfield',
						name:'activitytime',
						fieldLabel:'活动时间',
						readOnly:true,
					},{
						id:'id_score',
						xtype : 'textfield',
						name:'score',
						fieldLabel:'活动考评分',
						allowBlank:false,
						regex:/^[-+]?[\d]+$/,
						regexText:'请输入正确的整数',
					}]
				},{
					columnWidth:.5,
					layout:'form',
					defaults:{
						anchor : '95%',
						//cls:"borderNone",
					},
					items:[{
						xtype : 'displayfield',
						name:'studentnum',
						fieldLabel:'学<i class="ikg2"></i>号',
						readOnly:true,
					},{
						xtype : 'displayfield',
						name:'organizename',
						fieldLabel:'所在组织',
						readOnly:true,
					},{
						xtype : 'displayfield',
						name:'phonenum',
						fieldLabel:'老师电话',
						readOnly:true,
					},{
						xtype : 'displayfield',
						name:'activityplace',
						fieldLabel:'活动地点',
						readOnly:true,
					},{
						xtype : 'displayfield',
						name:'capacity',
						fieldLabel:'规模(人)',
						readOnly:true,
					},{
						xtype : 'displayfield',
						name:'signupendtime',
						fieldLabel:'报名截止',
						readOnly:true,
					},{
						xtype : 'displayfield',
						name:'activitygenre',
						fieldLabel:'活动类型',
						readOnly:true,
						//allowBlank : false
					}]
				}]
			},{
				xtype : 'displayfield',
				name:'faceobj',
				fieldLabel:'面向对象',
				readOnly:true,
				anchor : '90%',
				//allowBlank : false
			},{
				xtype : 'displayfield',
				name:'activitycontent',
				fieldLabel:'活动内容',
				readOnly:true,
				anchor : '90%',
			},{
				id:'id_refuse',
				xtype : 'textfield',
				name:'refuse',
				fieldLabel:'理<i class="ikg2"></i>由',
				readOnly:false,
				anchor : '90%',
				value:"不同意理由或者活动考评分修改理由",
				listeners: { 
				   render: function(p) { 
				      // Append the Panel to the click handler's argument list. 
				      p.getEl().on('click', function(combo,record){ 
				    	  if('不同意理由或者活动考评分修改理由' == p.getValue()) {
				    		  p.setValue('');
				    	  }
				      }); 
				  }
				}
			},{
				xtype : 'hidden',
			    name : 'activityid'
			},{
				xtype:'hidden',
				name:'applystyle'
			}]
    });
    
    //提交审核结果
    var checkingWindow=new Ext.Window({
		title : '审核窗口',
		width:520,
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
					var refuse = Ext.getCmp('id_refuse').getValue();
					var score = Ext.getCmp('id_score').getValue();
					if(refuse == '不同意理由或者活动考评分修改理由') {
						refuse = '';
						Ext.getCmp('id_refuse').setValue('');
					}
					checkaddWindow.getForm().submit({
						params :{'checkkey':'1', 'refuse':refuse, 'updateScore':score},
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
				//alert("hello");
				var refuse = Ext.getCmp('id_refuse').getValue();
				var score = Ext.getCmp('id_score').getValue();
				if(refuse == '不同意理由或者活动考评分修改理由') {
					refuse = '';
					Ext.getCmp('id_refuse').setValue('');
				}
				if (checkaddWindow.getForm().isValid()) {
					checkaddWindow.getForm().submit({
						params :{'checkkey':'0', 'refuse':refuse, 'updateScore':score},
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
		}]
    });
    
    //布局
    new Ext.Viewport({
		layout:"fit",
		items:[{
			frame:true,
			title:"活动申请审批",
			iconCls:'menu-15',
			layout:"border",
			items:[searchForm,grid]
		}]
	});
});