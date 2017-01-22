
/**
 * 线上答疑
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
						name:'key',
						fieldLabel:"关键字",
						anchor:"90%"
					}]
				},{
					width:250,
					items:[{
						width:75,
						xtype:"button",
						iconCls:"",
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
	
	
	var OnlineQAObj = [
	            { name:'id', type:'Integer'},
	            { name:'subject', type:'string'},
	            { name:'content', type:'string'},
	            { name:'nickname', type:'string'},
	            { name:'publishtime', type:'string'},
	            { name:'popularity', type:'Integer'},
	            { name:'topflag', type:'Integer'},
	            { name:'status', type:'Integer'},
	            { name:'highlight', type:'Integer'},
	            { name:'replynickname', type:'string'},
	            { name:'updatetime', type:'string'}
	        ];
	
	
	
	var store = new Ext.data.JsonStore({
		//url: 'datumInfo_findPageDatumInfo.do',datumInfo_findPageDatumInfoBy.do
		 url: '/MyStock/onlineQA_findPageAllOnlineQA.do',
		 root: 'root',
		 totalProperty: 'total',
		 autoLoad: {params:{start:0, limit:15}},
		 fields: OnlineQAObj
	});
	
	//new Ext.grid.RowNumberer(),：序号使用
	var sm = new Ext.grid.CheckboxSelectionModel({singleSelect: false});//单选
    var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[sm,new Ext.grid.RowNumberer(),
			    {header: '标示',  width: 10, align:'center', dataIndex: 'id', hidden : true},
			    {header: '主题',  width: 300, align:'center', dataIndex: 'subject'},
			    {header: '作者',  width: 100, align:'center', dataIndex: 'nickname'},
			    {header: '发布时间',  width: 150, align:'center', dataIndex: 'publishtime'},
			    {header: '人气',  width: 100, align:'center', dataIndex: 'popularity'},
			    {header: '最新回复人',  width: 100, align:'center', dataIndex: 'replynickname'},
			    /*{header: '最后更新时间',  width: 100, align:'center', dataIndex: 'updatetime'},*/
			    {header: '状态',  width:180, align:'center', dataIndex: 'status',
			    	renderer:function(value, metaData, record, rowIndex, colIndex, store, view) {
		                if(value == 0) { 
		                	var s = "展示中"
		                	if(1==record.data.topflag){
		                		s += " 置顶"
		                	}
		                	if(1==record.data.highlight){
		                		s += " 高亮"
		                	}
	                        return s;
		                }     
		                return "隐藏中";
	            	}
			    },
			    {xtype: 'actioncolumn',header : '操作', width: 90,align : 'center',
	            	menuDisabled : true,
	                items: [{
	                    icon: '../../img/btn/see.png',
	                    tooltip: '查看详情',
	                	handler: function(grid, rowIndex, colIndex){
	                		var record = grid.getStore().getAt(rowIndex); 
	                		viewDetail(grid, rowIndex, colIndex);
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
		layout : 'fit',
        viewConfig:{
        	forceFit: true, 
        	scrollOffset: 0
       	},
        iconCls:'menu-52',
        tbar:['->',{
        	text:'置顶',
        	iconCls:'btn-edit',
        	handler: function(){
    		var record= grid.getSelectionModel().getSelected();
			if(!record){
            	Ext.Msg.alert('信息提示','请选择需要置顶的线上答疑');  
			}else{
				var jsonArray = [];
				var recs=grid.getSelectionModel().getSelections(); 
				Ext.each(recs, function (item) {
					jsonArray.push(item.data.id);
                });
				Ext.Ajax.request({
			   		url : "/MyStock/onlineQA_update.do",
				   	params:{ 
						type:2,
			   		 	ids : jsonArray,
			   		 	value : 1
					 },
				    success : function(date) {
		                store.reload();
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
            	Ext.Msg.alert('信息提示','请选择需要取消置顶的线上答疑');  
			}else{
				var jsonArray = [];
				var recs=grid.getSelectionModel().getSelections(); 
				Ext.each(recs, function (item) {
					jsonArray.push(item.data.id);
                });
				Ext.Ajax.request({
			   		url : "/MyStock/onlineQA_update.do",
				   	params:{ 
				   		type:2,
			   		 	ids : jsonArray,
			   		 	value : 0
					 },
				    success : function(date) {
		                store.reload();
					 }
			    });
			}
    	}
        },'-',{
        	text:'隐藏',
        	iconCls:'btn-edit',
        	handler: function(){
    		var record= grid.getSelectionModel().getSelected();
			if(!record){
            	Ext.Msg.alert('信息提示','请选择一项');  
			}else{
				var jsonArray = [];
				var recs=grid.getSelectionModel().getSelections(); 
				Ext.each(recs, function (item) {
					jsonArray.push(item.data.id);
                });
				Ext.Ajax.request({
			   		url : "/MyStock/onlineQA_update.do",
				   	params:{ 
				   		type:1,
			   		 	ids : jsonArray,
			   		 	value : 1
					 },
				    success : function() {
				   		  store.reload();
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
            	Ext.Msg.alert('信息提示','请选择需要取消隐藏的线上答疑');  
			}else{
				var jsonArray = [];
				var recs=grid.getSelectionModel().getSelections(); 
				Ext.each(recs, function (item) {
					jsonArray.push(item.data.id);
                });
				Ext.Ajax.request({
			   		url : "/MyStock/onlineQA_update.do",
				   	params:{ 
				   		type:1,
			   		 	ids : jsonArray,
			   		 	value : 0
					 },
				    success : function(date) {
		                store.reload();
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
            	Ext.Msg.alert('信息提示','请选择需要高亮的线上答疑');  
			}else{
				var jsonArray = [];
				var recs=grid.getSelectionModel().getSelections(); 
				Ext.each(recs, function (item) {
					jsonArray.push(item.data.id);
                });
				Ext.Ajax.request({
			   		url : "/MyStock/onlineQA_update.do",
				   	params:{ 
				   		type:3,
			   		 	ids : jsonArray,
			   		 	value : 1
					 },
				    success : function(date) {
		                store.reload();
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
            	Ext.Msg.alert('信息提示','请选择需要取消高亮的线上答疑');  
			}else{
				var jsonArray = [];
				var recs=grid.getSelectionModel().getSelections(); 
				Ext.each(recs, function (item) {
					jsonArray.push(item.data.id);
                });
				Ext.Ajax.request({
			   		url : "/MyStock/onlineQA_update.do",
				   	params:{ 
				   		type:3,
			   		 	ids : jsonArray,
			   		 	value : 1
					 },
				    success : function(date) {
		                store.reload();
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
                	Ext.Msg.alert('信息提示','请选择要删除的线上答疑');  
				}else{
					var jsonArray = [];
					var recs=grid.getSelectionModel().getSelections(); 
					Ext.each(recs, function (item) {
						jsonArray.push(item.data.id);
	                });
					Ext.MessageBox.confirm('删除提示', '是否删除该线上答疑？', function(c) {
					   	Ext.Ajax.request({
					   			url : "/MyStock/onlineQA_delete.do",
					   			params:{ids : jsonArray},
					   			success : function() {
					   				store.reload();
					   			}
					   		});
					    
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
    
    var viewDetail = function(grid, rowIndex, colIndex){
    	var record = grid.getStore().getAt(rowIndex); 
    	
    	location.href='/MyStock/onlineQA_viewOnlineQA.do?id='+record.data.id; 
    	/*var html = '<iframe src="onlineQA_viewOnlineQA.do?id='+record.data.id+'" frameborder="0" width="100%"  height="100%"></iframe>';
		if(Ext.getCmp('viewPanel').body){
			Ext.getCmp('viewPanel').body.update(html);
		}else{
			Ext.getCmp('viewPanel').html = html;
		}
		
		addWindow.show();*/
    };
    
    var viewPanel = new Ext.Panel({
 	   	id : 'viewPanel',
	    layout : 'fit',
	    html : '',
	    frame : true
    });

    
    
    var addWindow = new Ext.Window({
    	title:'发布线上答疑',
		width:620,
		height:350,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [viewPanel],
		buttons : [{
			text : '关闭',
			handler : function() {
				addWindow.hide();
			}
		}]
	});
    
    //布局
    new Ext.Viewport({
		layout:"fit",
		items:[{
			frame:true,
			title:"线上答疑查询",
			iconCls:'menu-52',
			layout:"border",
			items:[searchForm,grid]
		}]
	});
    
   

});