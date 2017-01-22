function showImg(value){
	return "<img src='"+value+"'  id='imgsrc' height='50' onclick='preview();' />";
}
	

/*!
 * 场地审批
 */
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	
	 //查询所有所有园区下拉菜单  
    var parkStore = new Ext.data.JsonStore({
		url: '/MyStock/SiteInfo_findParkComb.do',
	    root: 'root',
	    totalProperty: 'total',
	    fields: ['value','text']
	});
    
    parkStore.on('load', function(store, record, options) {   
        store.insert(0, new Ext.data.Record({ 'value': '', 'text': '全部' }));   
    });
    
    //查询所有所有场地类型下拉菜单
    var groundTypeStore = new Ext.data.JsonStore({
		url: '/MyStock/SiteInfo_findGroundTypeComb.do',
	    root: 'root',
	    totalProperty: 'total',
	    fields: ['value','text']
	});
    
    groundTypeStore.on('load', function(store, record, options) {   
    	store.insert(0, new Ext.data.Record({'text': '全部', 'value': ''}));   
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
					width:240,
					items:[{
								
							labelWidth:60,
						     xtype:"textfield",
							  name:"sitename",
							  anchor:"90%",
							  fieldLabel:"场地名称"
							}]
						},{
							width:180,
							labelWidth:60,
							items:[{
									xtype:'combo',
									hiddenName:'sitetype',
									id:'sitetype',
									fieldLabel:'场地类型',
									mode: 'local',
									triggerAction: 'all',
									valueField :'value',
									displayField: 'text',
									editable : false,
									allowBlank : true,
									anchor:"90%",
									editable : false,
									store : groundTypeStore,
									//value:'---请选择---',
									emptyText: '全部'
									
									
							}]
						},{
							width:180,
							labelWidth:60,
							items:[{
									xtype:'combo',
									hiddenName:'park',
									id:'park',
									fieldLabel:'园区',
									mode: 'local',
									triggerAction: 'all',
									valueField :'value',
									displayField: 'text',
									anchor:"90%",
									editable : false,
									store : parkStore,
									emptyText: '全部'
									
								
							}]
						},{
							width:180,
							labelWidth:60,
							items:[{
									xtype:'combo',
									hiddenName:'status',
									fieldLabel:'状态',
									mode: 'local',
									triggerAction: 'all',
									valueField :'value',
									displayField: 'text',
									editable : false,
									store : new Ext.data.SimpleStore({
									    fields: ['value', 'text'],
									    data : [['','全部'],['已通过','已通过'],['未通过','未通过'],['待审批','待审批']]
									}),
									anchor:"90%"
							}]
						},{
					width:100,
					items:[{
						width:75,
						xtype:"button",
						text:"查询",
						handler:function(){
							var f = searchForm.getForm();
							if (f.isValid()) {
								var _params = f.getValues();
								//添加分页信息
								Ext.apply(_params,{start:0, limit:15,ifApproval:3});
								store.load({params:_params});
							}
						}
					}]
				}]
			}]
		}]
	});
	
	var SiteInfoLogObj = [
		              { name:'logId', type:'string'},
		              { name:'proposer', type:'string'},
		              { name:'siteid', type:'string'},
		              { name:'xh', type:'xh'},
		              { name:'relationtel', type:'string'},
		              { name:'counsellor', type:'string'},
		              { name:'activityname', type:'string'},
		              { name:'activitycontent', type:'string'},
		              { name:'activitydate', type:'String' },
		              { name:'activitytime', type:'String' },
		              { name:'activitytype', type:'string'},
		              { name:'sitename', type:'string'},
		              { name:'sitetype', type:'string'},
		              { name:'beorganize', type:'string'},
		              { name:'guideth', type:'string'},
		              { name:'sitemodle', type:'int'},
		              { name:'park', type:'string'},
		           	  { name:'nowstatus', type:'string'},
		           	  { name:'sitecondition', type:'string'},
		           	  { name:'usetime', type:'int'},
		           	  { name:'applyTime', type:'string'},
		              { name:'ifschool', type:'string'},
		           	  { name:'agreement', type:'string'},
		           	  { name:'dutystate', type:'string'},
		           	  { name:'status', type:'string'},
		           	  { name:'createtime', type:'string'},
	    	          { name:'createrid', type:'int'},
	    	          { name:'creatername', type:'string'}
		           		
		           	];
	
	
	
  	
	  var store = new Ext.data.JsonStore({
	          url: '/MyStock/SiteInfo_findPageSiteInfoLog.do',
	          root: 'root',
	          totalProperty: 'total',
	          autoLoad: {params:{start:0, limit:15,ifApproval:3}},
	          fields: SiteInfoLogObj
	      });
	
	var sm = new Ext.grid.CheckboxSelectionModel({singleSelect: false});//单选
    var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[sm,
			         {header: '场地名称',  width: 200, align:'center', dataIndex: 'sitename'},
			         {header: '使用时间(小时)', width: 120,align:'center', dataIndex: 'usetime'},
			         {header: '活动名称', width: 180,align:'center', dataIndex: 'activityname'},
			         {header: '申请人', width: 100,align:'center', dataIndex: 'proposer'},
			         {header: '联系电话', width: 130,align:'center', dataIndex: 'relationtel'},
			         {header: '所在组织', width: 100,align:'center', dataIndex: 'beorganize'},
			         {header: '场地类型', width: 100,align:'center', dataIndex: 'sitetype'},
			         {header: '规模', width: 80,align:'center', dataIndex: 'sitemodle'},
			         {header: '辅导员',  width: 100, align:'center', dataIndex: 'counsellor'},
			         {header: '状态', width: 100,align:'center', dataIndex: 'status',
			        	 renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
			               if(value == '待审核') { 
		                        return "<span style='color:blue;font-weight:bold;'>待审核</span>"
		                   }     
		                   if(value == '已通过') { 
		                      return "<span style='color:green;font-weight:bold;'>已通过</span>"
		                   } 
		                   if(value == '未通过') { 
			                      return "<span style='color:red;font-weight:bold;'>未通过</span>"
			                }  
		                   if(value == '已取消') { 
			                      return "<span style='color:black;font-weight:bold;'>已取消</span>"
			                }   
		                 }
			         },
			         {xtype: 'actioncolumn',header : '操作', width: 160,align : 'center',
			            	menuDisabled : true,
			                items: [{
			                    icon: '../../img/btn/check.png',
			                    tooltip: '审核',
			                    getClass: function(value, metaData, record, rowIndex, colIndex, store, view) {      
			                		if('已通过'==record.data.status  || '未通过'==record.data.status || '已取消'==record.data.status){
			                              return 'x-hidden'
			                        }
			                	},
			                	handler: function(grid, rowIndex, colIndex){
			                		audit(grid, rowIndex, colIndex);
			                	}
			                }
			                ]
			           } 

			       ]
        }),
        sm:sm,//checkbox
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
        //autoExpandColumn: 'photo', //自动扩展列
		loadMask : true,	//加载时的遮罩
		frame : true,
		region:"center",
        iconCls:'menu-13',
        tbar:['->',{
        	text:'批量通过',
        	iconCls:'btn-edit',
        	handler: function(){
    		var record= grid.getSelectionModel().getSelected();
			if(!record){
            	Ext.Msg.alert('信息提示','请选择要审批的场地');  
			}else{
				Ext.MessageBox.confirm('审批提示', '是否确定审批这些场地通过？', function(c) {
					var jsonArray = [];
					var recs=grid.getSelectionModel().getSelections(); 
					Ext.each(recs, function (item) {
						jsonArray.push(item.data);
                     });
					if(c=='yes'){
				   	Ext.Ajax.request({
				   		url : "/MyStock/SiteInfo_saveOrUpdaSiteInfo.do",
				   	    params:{ifApproval:"0",siteids : Ext.encode(jsonArray)},
				   		success : function() {
				   		  store.reload();
				   	  }
				    });
					}
				});
			}
    	}},'-',{
        	text:'批量通不过',
        	iconCls:'btn-edit',
        	handler: function(){
    		var record= grid.getSelectionModel().getSelected();
			if(!record){
            	Ext.Msg.alert('信息提示','请选择要审批的场地');  
			}else{
				Ext.MessageBox.confirm('审批提示', '是否确定审批这些场地不通过？', function(c) {
					var jsonArray = [];
					var recs=grid.getSelectionModel().getSelections(); 
					Ext.each(recs, function (item) {
						jsonArray.push(item.data);
                     });
					if(c=='yes'){
				   	Ext.Ajax.request({
				   		url : "/MyStock/SiteInfo_saveOrUpdaSiteInfo.do",
				   	    params:{ifApproval:"1",siteids : Ext.encode(jsonArray)},
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
        	scrollOffset: 0
       	},
    });

    
    
    var addForm = new Ext.FormPanel({
		layout : 'form',
        fileUpload:true,  
		frame:true,
		labelWidth:60,
		border : false,
		padding : '5 5 0 8',
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
					name:'sitename',
					fieldLabel:'申请场地',
					cls :'x-textfield',
					readOnly:true,
				},{
					xtype : 'textfield',
					name:'proposer',
					fieldLabel:'申<i class="ikg1"></i>请<i class="ikg1"></i>人',
					cls :'x-textfield',
					readOnly:true,
					
				},{
					xtype : 'textfield',
					name:'relationtel',
					fieldLabel:'联系电话',
					cls :'x-textfield',
					readOnly:true,
					
				},{
				xtype:'textfield',
				name:'activityname',
				fieldLabel:'活动名称',
				cls :'x-textfield',
				readOnly:true,
				height:20
				}
			]
			},{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype : 'textfield',
					name:'sitecondition',
					fieldLabel:'会<i class="ikg1"></i>员<i class="ikg1"></i>室',
					cls :'x-textfield',
					readOnly:true,
					
				},{
					xtype : 'textfield',
					name:'xh',
					fieldLabel:'学<i class="ikg2"></i>号',
					cls :'x-textfield',
					readOnly:true,
					
				},{
					xtype : 'textfield',
					name:'counsellor',
					fieldLabel:'辅<i class="ikg1"></i>导<i class="ikg1"></i>员',
					cls :'x-textfield',
					readOnly:true
					
				},{
					xtype : 'textfield',
					name:'activitytype',
					fieldLabel:'活动类型',
					cls :'x-textfield',
					readOnly:true,
					
				}]
			}]
		},{
			xtype:'textarea',
			name:'activitycontent',
			fieldLabel:'活动内容',
			style:'border:0', 
			readOnly:true,
			height:50
			
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
					name:'activitydate',
					fieldLabel:'活动日期',
					cls :'x-textfield',
					readOnly:true,
					
				},{
					xtype : 'textfield',
					name:'guideth',
					fieldLabel:'指导老师',
					cls :'x-textfield',
					readOnly:true,
					
				},{
					xtype : 'textfield',
					name:'sitemodle',
					fieldLabel:'规<i class="ikg2"></i>模',
					cls :'x-textfield',
					readOnly:true,
					
				}]
			},{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype : 'textfield',
					name:'activitytime',
					fieldLabel:'活动时间',
					cls :'x-textfield',
					readOnly:true,
					
				},{
					xtype : 'textfield',
					name:'beorganize',
					fieldLabel:'所在组织',
					cls :'x-textfield',
					readOnly:true,
					
				},{  
		            xtype:'textfield',    
		            fieldLabel:'是否是外校人员参加',
		        	name:'ifschool',
		        	cls :'x-textfield',
		        	readOnly:true
		          
		        }]
			}]
		},{
			xtype:'textfield',
			name:'dutystate',
			fieldLabel:'责任声明',
			cls :'x-textfield',
			readOnly:true,
			height:20
			
		},{
			xtype : 'hidden',
		    name : 'logId'
		},{
			xtype : 'hidden',
		    name : 'srcImg'
		},{
			xtype : 'hidden',
			id:'rowIndex2',
		    name : 'rowIndex2'
		}]
	});
    
    var addWindow = new Ext.Window({
		title : '场地审核',
		width:700,
		height:385,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [addForm],
		buttons : [{
			text : '通过',
			handler : function() { 
			 //获取行号
		      var rowIndex = Ext.getCmp("rowIndex2").getValue();
		      //获取数据
			  var record = grid.getStore().data.items[rowIndex];
			   Ext.MessageBox.confirm('审核提示', '确定审批通过？', function(c) {
				   if(c=='yes'){
					 Ext.Ajax.request({
					   	url : "/MyStock/SiteInfo_saveOrUpdateLog.do",
					   	 params:{ 
						 ifApproval:"0",
						 logId :record.data.logId,
						 xh :record.data.xh,
						 proposer:record.data.proposer,
						 siteid:record.data.siteid,
						 relationtel:record.data.relationtel,
						 counsellor:record.data.counsellor,
						 activityname:record.data.activityname,
						 activitycontent:record.data.activitycontent,
						 activitydate:record.data.activitydate,
						 activitytime:record.data.activitytime,
						 activitytype:record.data.activitytype,
						 sitename:record.data.sitename,
						 sitetype:record.data.sitetype,
						 beorganize:record.data.beorganize,
						 guideth:record.data.guideth,
						 sitemodle:record.data.sitemodle,
						 park:record.data.park,
						 nowstatus:record.data.nowstatus,
						 nowstatus:record.data.nowstatus,
						 sitecondition:record.data.sitecondition,
						 usetime:record.data.usetime,
						 ifschool:record.data.ifschool,
						 agreement:record.data.agreement,
						 dutystate:dutystate,
						 applyTime:record.data.applyTime,
						 status:record.data.status
						 }, 
						 success : function(data) {
							addWindow.hide();
						    store.reload();
						}
					 });
				   }
				  });
			   
			}
		}, {
			text : '不通过',
			handler : function() { 
			 //获取行号
		      var rowIndex = Ext.getCmp("rowIndex2").getValue();
		      //获取数据
			  var record = grid.getStore().data.items[rowIndex];
			   Ext.MessageBox.confirm('审核提示', '确定审批不通过？', function(c) {
				   if(c=='yes'){
					 Ext.Ajax.request({
					   	url : "/MyStock/SiteInfo_saveOrUpdateLog.do",
					   	 params:{ ifApproval:"1",
						 logId :record.data.logId,
						 xh :record.data.xh,
						 proposer:record.data.proposer,
						 siteid:record.data.siteid,
						 relationtel:record.data.relationtel,
						 counsellor:record.data.counsellor,
						 activityname:record.data.activityname,
						 activitycontent:record.data.activitycontent,
						 activitydate:record.data.activitydate,
						 activitytime:record.data.activitytime,
						 activitytype:record.data.activitytype,
						 sitename:record.data.sitename,
						 sitetype:record.data.sitetype,
						 beorganize:record.data.beorganize,
						 guideth:record.data.guideth,
						 sitemodle:record.data.sitemodle,
						 park:record.data.park,
						 nowstatus:record.data.nowstatus,
						 nowstatus:record.data.nowstatus,
						 sitecondition:record.data.sitecondition,
						 usetime:record.data.usetime,
						 ifschool:record.data.ifschool,
						 agreement:record.data.agreement,
						 dutystate:dutystate,
						 applyTime:record.data.applyTime,
						 status:record.data.status
						 }, 
						 success : function(data) {
						    addWindow.hide();
							store.reload();
						}
					 });
				   }
				  });
			   
			}
		}]
	});
    
  
    //责任声明
    var dutystate = '';
        dutystate +='活动场地申请责任说明:\r\n';
        dutystate +='请同学严格按照协议内容使用场地，违规使用活动场地，申请\r\n';
        dutystate +='人将受到严肃处理。\r\n';
        
    
    
    //跳转审核方法
    var audit = function(grid, rowIndex, colIndex){
    	var record = grid.getStore().getAt(rowIndex); 
    	var activitydate  = record.data.activitydate;
        var str = activitydate.split(" ")[0]; 
    	
    	addWindow.items.get(0).form.findField("proposer").setValue(record.data.proposer);
  	    addWindow.items.get(0).form.findField("xh").setValue(record.data.xh);
  	    addWindow.items.get(0).form.findField("relationtel").setValue(record.data.relationtel);
  	    addWindow.items.get(0).form.findField("counsellor").setValue(record.data.counsellor);
  	    addWindow.items.get(0).form.findField("activityname").setValue(record.data.activityname);
  	    addWindow.items.get(0).form.findField("activitycontent").setValue(record.data.activitycontent);
  	    addWindow.items.get(0).form.findField("activitydate").setValue(str);
  	    addWindow.items.get(0).form.findField("activitytime").setValue(record.data.activitytime);
  	    addWindow.items.get(0).form.findField("activitytype").setValue(record.data.activitytype);
  	    addWindow.items.get(0).form.findField("sitename").setValue(record.data.sitename);
  	    addWindow.items.get(0).form.findField("beorganize").setValue(record.data.beorganize);
  	    addWindow.items.get(0).form.findField("guideth").setValue(record.data.guideth);
  	    addWindow.items.get(0).form.findField("sitemodle").setValue(record.data.sitemodle);
  	    addWindow.items.get(0).form.findField("sitecondition").setValue(record.data.sitecondition);
  	   // addWindow.items.get(0).form.findField("ifschool").setValue(record.data.ifschool);
  	    addWindow.items.get(0).form.findField("dutystate").setValue(dutystate);
  	   if( record.data.ifschool == "是"){
   	     addWindow.items.get(0).form.findField("ifschool").setValue("是");
	   }else{
		 addWindow.items.get(0).form.findField("ifschool").setValue("否");
				
		};
  	   //隐藏传行号rowIndex
  	    addWindow.items.get(0).form.findField("rowIndex2").setValue(rowIndex);
    	addWindow.show();
    };
  
    
    //布局
    new Ext.Viewport({
		layout:"fit",
		items:[{
			frame:true,
			title:"场地审批查询",
			iconCls:'menu-13',
			layout:"border",
			items:[searchForm,grid]
		}],
		listeners:{
			render:function(){
    	      parkStore.load();  
    	      groundTypeStore.load();
			}
		}
	});
    
   

});