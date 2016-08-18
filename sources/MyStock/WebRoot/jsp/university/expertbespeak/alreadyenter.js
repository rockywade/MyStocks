Ext.BLANK_IMAGE_URL = '../../../ext/resources/images/default/s.gif';
/*!
 * 活动申请
 */
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//查询表单
	var searchForm = new Ext.FormPanel({
		region:"north",
		height: 100,
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
					width:300,
					items:[{
							width : 150,
							xtype:"combo",
							hiddenName:'applygenre',
							fieldLabel:"咨询类别",
	   						mode: 'local',
	   						triggerAction: 'all',
	   						allowBlank : true,
	   						editable : false,
							store :[['学业指导','学业指导'],['心理辅导','心理辅导'],['职业规划','职业规划'],['出国指导','出国指导'],['其他','其他'],["","全部"]],
							},{
								width:150,
								xtype:'combo',
								hiddenName:'bespeakstate',
								fieldLabel:'预约状态',
								mode: 'local',
								triggerAction: 'all',
								valueField :'value',
								displayField: 'text',
								allowBlank : true,
								editable : false,
								store :[["","全部"],["待接受","待接受"],["已接受","已接受"],["已取消","已取消"],["待反馈","待反馈"],["已拒绝","已拒绝"]]
						}]
						},{
							width:300,
							items:[{
								width : 150,
								xtype:"combo",
								hiddenName:'bespeakplace',
								fieldLabel:"预约地点",
		   						mode: 'local',
		   						triggerAction: 'all',
								valueField :"value",
		   						displayField: "text",
		   						allowBlank : true,
		   						editable : false,
								store :[["","全部"],["玉泉校区","玉泉校区"],["西溪校区","西溪校区"],
								        ["华家池校区","华家池校区"],["之江校区","之江校区"],["紫金港校区","紫金港校区"]],
							},{
								width : 150,
								xtype:"combo",
								hiddenName:'expertfeedbackstate',
								fieldLabel:"老师反馈状态",
		   						mode: 'local',
		   						triggerAction: 'all',
		   						allowBlank : true,
		   						editable : false,
								store :[["","全部"],["无反馈","无反馈"],["已反馈","已反馈"]],
							}]
								
						},{
							width:260,
							items:[{
								width:150,
								labelWidth:30,
								xtype:"textfield",
								name:"ename",
								fieldLabel:"预约老师"
							},{
								width:75,
								xtype:"button",
								style:'margin-left:180px',
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
					style:'margin-top:25px',
					text:"返<i class='ikg2'></i>回",
					handler:function(){
							   window.location.href="../../menu/appointment-experts.html";
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
	var StudentbespeakDTO = [
	                { name:'stid', type:'int'},
	        		{ name:'applygenre', type:'string'},
	        		{ name:'studentName', type:'string'},
	        		{ name:'uploadbespeaktime', type:'string'},
	        		{ name:'bespeaktime', type:'string'},
	        		{ name:'bespeakplace', type:'string'},
	        		{ name:'expertName', type:'string'},
	        		{ name:'bespeakstate', type:'string'},
	        		{ name:'expertfeedbackstate', type:'string'},
	        	];
	
	//获取response数据
	var store = new Ext.data.JsonStore({
	    url: 'Expertbespeak_alreadyenter.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: {params:{start:0, limit:15}},
	    fields: StudentbespeakDTO,
	});
    
	//var rownumber = new Ext.grid.RowNumberer();//行号
    var sm = new Ext.grid.CheckboxSelectionModel();//单选{singleSelect: true}
    var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[sm,
			    {header: '咨询类别', width: 100,align:'center', dataIndex: 'applygenre'},
			    {header:'申请人',width:100,align:'center',dataIndex:'studentName'},
			    {header:'申请时间',width:100,align:'center',dataIndex:'uploadbespeaktime'},
	            {header: '预约时间', width: 100,align:'center', dataIndex: 'bespeaktime'},
	            {header: '预约地点', width: 100,align:'center', dataIndex: 'bespeakplace'},
	            {header: '预约老师', width: 100,align:'center', dataIndex: 'expertName'},
	            {header: '预约状态', width: 100,align:'center', dataIndex: 'bespeakstate',renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
		               if(value == '已接受') { 
	                        return "<span style='color:blue;font-weight:bold;'>已接受</span>"
	                   }     
	                   if(value == '待接受') { 
	                      return "<span style='color:red;font-weight:bold;'>待接受</span>"
	                   }
	                   if(value == '已拒绝') { 
		                      return "<span style='color:#ee7b25;font-weight:bold;'>已拒绝</span>"
		               } 
	                   if(value == '已完成　待反馈') { 
		                      return "<span style='color:#333;font-weight:bold;'>待反馈</span>"
		               }
	                   if(value == '已完成') { 
		                      return "<span style='color:green;font-weight:bold;'>已完成</span>"
		               }
	                }},
	            {header: '老师反馈状态', width: 100,align:'center', dataIndex: 'expertfeedbackstate',renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
		               if(value == '已反馈') { 
	                        return "<span style='color:green;font-weight:bold;'>已反馈</span>"
	                   }     
	                   if(value == '无反馈') { 
	                      return "<span style='color:red;font-weight:bold;'>无反馈</span>"
	                   }
	                }},
	        ]
        }),
        sm:sm,//checkbox
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
		loadMask : true,	//加载时的遮罩
		frame : true,
		region:"center",
        iconCls:'menu-51',
        
        tbar:['->',{
        	text:'提醒老师确认预约',
        	iconCls:'btn-add',
        	handler: function(){
        		var record= grid.getSelectionModel().getSelected(); 
				if(!record){
                	Ext.Msg.alert('信息提示','请选择数据');
				}else if(record.get("bespeakstate")!="待接受"){
					Ext.Msg.alert('信息提示','非待接受状态的不能发送短信');
				}else{
					//alert(record.get("stid"));
					Ext.Ajax.request({
			   			url : "Expertbespeak_sendEnterOrFeedBespeakSMS.do",
			   			params:{ stid : record.get("stid"),msgKey:"enter" },
			   			success : function(response) {
			   				var responsedata = Ext.util.JSON.decode(response.responseText);
			   				Ext.Msg.alert('信息提示',responsedata.message);
			   				//store.reload();
			   			}
			   		});
				}
        	}
        },'-',{
        	text:'提醒老师填写反馈',
        	iconCls:'btn-edit',
        	handler: function(){
        		var record= grid.getSelectionModel().getSelected(); 
				if(!record){
                	Ext.Msg.alert('信息提示','请选择数据');
				}else if(record.get("expertfeedbackstate")!="无反馈"){
					Ext.Msg.alert('信息提示','非无反馈状态的不能发送短信');
				}else if(record.get("bespeakstate")=="已拒绝"){
					Ext.Msg.alert('信息提示','无法给拒绝预约的专家老师发送短信');
				}else if(record.get("bespeakstate")=="待接受"){
					Ext.Msg.alert('信息提示','专家老师还未接受预约，请发送确认预约短信');
				}else{
					Ext.Ajax.request({
			   			url : "Expertbespeak_sendEnterOrFeedBespeakSMS.do",
			   			params:{ stid : record.get("stid"),msgKey:"feed" },
			   			success : function(response) {
			   				var responsedata = Ext.util.JSON.decode(response.responseText);
			   				Ext.Msg.alert('信息提示',responsedata.message);
			   				//store.reload();
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
                	Ext.Msg.alert('信息提示','请选择要删除的数据');  
				}else{
					Ext.MessageBox.confirm('删除提示', '是否删除该用户？', function(c) {
					   if(c=='yes'){
					   		Ext.Ajax.request({
					   			url : "Expertbespeak_deleteBespeak.do",
					   			params:{ sbid : record.get("stid") },
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
        viewConfig : {
        	forceFit: true,
        	scrollOffset: 2
    	}
    });
    
    //布局
    new Ext.Viewport({
		layout:"fit",
		items:[{
			frame:true,
			title:"已指定专家",
			iconCls:'menu-15',
			layout:"border",
			items:[searchForm,grid],
			listeners:{
				render:function(){
					//getCurrentUserInfo();
		    		//addWindow.show();
				}
			}
		}]
	});
});