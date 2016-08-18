
/**
 * 学习资料管理端
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
						name:'datumname',
						fieldLabel:"资料名称",
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
							    data : [['','全部'],['展现中','展现中'],['隐藏中','隐藏中'],['待审核','待审核'],['展现中/已置顶','展现中/已置顶']]
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
				}]
			}]
		}]
		
	});
	
	var DatumInfoObj = [
	    	           { name:'id', type:'int'},
	    	           { name:'datumname', type:'string'},
	    	           { name:'datumnumber', type:'string'},
	    	           { name:'datumsize', type:'string'},
	    	           { name:'datumstyle', type:'string'},
	    	           { name:'shareman', type:'string'},
	    	           { name:'sharetime', type:'string'},
	    	           { name:'sharegrade', type:'int'},
	    	           { name:'datumcontent', type:'string'},
	    	           { name:'downnum', type:'int'},
	    	           { name:'downnumber', type:'string'},
	    	           { name:'status', type:'string'},
	    	           { name:'transfetag', type:'string'},
	    	           { name:'toptime', type:'string'},
	    	           { name:'createtime', type:'string'},
	    	           { name:'createrid', type:'int'},
	    	           { name:'creatername', type:'string'}
	    	       ];
	
	
	
	var store = new Ext.data.JsonStore({
		// url: 'datumInfo_findPageDatumInfo.do',findPageDatumInfoBy
		 url: 'datumInfo_findPageDatumInfoBy.do',
		 root: 'root',
		 totalProperty: 'total',
		 autoLoad: {params:{start:0, limit:15}},
		 fields: DatumInfoObj
	});
	
	//new Ext.grid.RowNumberer(),：序号使用
	var sm = new Ext.grid.CheckboxSelectionModel({singleSelect: false});//单选
    var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[sm,
			    {header: '标示',  width: 100, align:'center', dataIndex: 'id', hidden : true},
			    {header: '资料名称',  width: 200, align:'center', dataIndex: 'datumname'},
			    {header: '资料大小',  width: 100, align:'center', dataIndex: 'datumsize'},
			    {header: '资料格式',  width: 150, align:'center', dataIndex: 'datumstyle'},
			    {header: '下载次数',  width: 100, align:'center', dataIndex: 'downnum'},
			    {header: '分享人',  width: 150, align:'center', dataIndex: 'shareman'},
			    {header: '分享时间',  width: 200, align:'center', dataIndex: 'sharetime'},
	            {header: '资料评分',  width: 100, align:'center', dataIndex: 'sharegrade'},
	            {header: '状态',  width: 200, align:'center', dataIndex: 'status',
	            	renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
		               if(value == '待审核') { 
	                        return "<span style='color:blue;font-weight:bold;'>待审核</span>"
	                   }     
	                   if(value == '展现中') { 
	                      return "<span style='color:green;font-weight:bold;'>展现中</span>"
	                   }
	                   if(value == '展现中/已置顶'){
	                	   return "<span style='color:blue;font-weight:bold;'>"+value+"</span>"
	                   } else{
	                	   return "<span style='color:red;font-weight:bold;'>"+value+"</span>"
	                	   
	                   }
	                 }
	            },
	            {xtype: 'actioncolumn',header : '操作', width: 150,align : 'center',
	            	menuDisabled : true,
	                items: [ {
	                	 icon: '../../img/btn/see.png',
		                 tooltip: '差看详情',
	                     getClass: function(value, metaData, record, rowIndex, colIndex, store, view) {      
	                		
	                	},
	                    handler: function(grid, rowIndex, colIndex){
	                		audit(grid, rowIndex, colIndex);
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
        iconCls:'menu-51',
        tbar:['->',{
        	text:'隐藏',
        	iconCls:'btn-edit',
        	handler: function(){
    		var record= grid.getSelectionModel().getSelected();
			if(!record){
            	Ext.Msg.alert('信息提示','请选择要隐藏的资料');  
			}else{
				var status = record.get("status");
            	if(status == '隐藏中'){
            		alert("隐藏中！无需隐藏 了")
            		return false;
            	};
            
				Ext.MessageBox.confirm('审批提示', '是否确定隐藏这些资料吗？', function(c) {

					var jsonArray = [];
					var recs=grid.getSelectionModel().getSelections(); 
					Ext.each(recs, function (item) {
						jsonArray.push(item.data);
                     });
					if(c=='yes'){
				   	Ext.Ajax.request({
				   		url : "datumInfo_saveOrUpdataDatumInfoAll.do",
				   	    params:{ifApproval:"1",datumInfoAll : Ext.encode(jsonArray)},
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
            	Ext.Msg.alert('信息提示','请选择需要取消隐藏的资料');
			}else{
				var status = record.get("status");
				if(status != '隐藏中'){
            		alert("不是隐藏！无需取消隐藏 ")
            		return false;
            	};
				Ext.MessageBox.confirm('隐藏提示', '是否确定要取取消隐藏这些资料吗？', function(c) {
					var jsonArray = [];
					var recs=grid.getSelectionModel().getSelections(); 
					Ext.each(recs, function (item) {
						jsonArray.push(item.data);
                     });
					if(c=='yes'){
				   	Ext.Ajax.request({
				   		url : "datumInfo_saveOrUpdataDatumInfoAll.do",
				   	    params:{ifApproval:"0",datumInfoAll : Ext.encode(jsonArray)},
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
            	Ext.Msg.alert('信息提示','请选择需要顶置的资料'); 
			}else{
				var status = record.get("status");
				if(status=="已发布/已置顶"){
            		alert("已置顶！无需再置顶")
            		return false;
            	};
				Ext.MessageBox.confirm('取消提示', '是否确定该资料顶置吗？', function(c) {
					var jsonArray = [];
					var recs=grid.getSelectionModel().getSelections(); 
					Ext.each(recs, function (item) {
						jsonArray.push(item.data);
                     });
					if(c=='yes'){
				   	Ext.Ajax.request({
				   		url : "datumInfo_saveOrUpdataDatumInfoAll.do",
				   	    params:{ifApproval:"2",datumInfoAll : Ext.encode(jsonArray)},
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
            	Ext.Msg.alert('信息提示','请选择需要取消顶置的资料');  
			}else{
				debugger;
				var status = record.get("status");
				if(status.indexOf("已置顶")==-1){
            		alert("不是置顶 ！无需取消置顶")
            		return false;
            	};
				Ext.MessageBox.confirm('取消提示', '是否确定这些资料取消顶置吗？', function(c) {
					var jsonArray = [];
					var recs=grid.getSelectionModel().getSelections(); 
					Ext.each(recs, function (item) {
						jsonArray.push(item.data);
                     });
					if(c=='yes'){
				   	Ext.Ajax.request({
				   		url : "datumInfo_saveOrUpdataDatumInfoAll.do",
				   	    params:{ifApproval:"3",datumInfoAll : Ext.encode(jsonArray)},
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
                	Ext.Msg.alert('信息提示','请选择要删除的资料');  
				}else{
					Ext.MessageBox.confirm('删除提示', '是否删除该资料？', function(c) {
						var jsonArray = [];
						var recs=grid.getSelectionModel().getSelections(); 
						Ext.each(recs, function (item) {
							jsonArray.push(item.data);
	                     });
						if(c=='yes'){
					   	Ext.Ajax.request({
					   		url : "datumInfo_deleteDatumInfoAll.do",
					   	    params:{datumInfoAll : Ext.encode(jsonArray)},
					   		success : function() {
					   		  store.reload();
					   	  }
					      });
						}
						
						/*if(c=='yes'){
					   	Ext.Ajax.request({
					   			url : "datumInfo_deleteDatumInfoAll.do",
					   			params:{id : record.get("id")},
					   			success : function() {
					   				store.reload();
					   			}
					   		});
						}*/
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

    
    //上传文件
    var addForm = new Ext.FormPanel({
		layout : 'form',
		url : 'datumInfo_saveDatumInfo.do',
		fileUpload:true,  
		frame:true,
		labelWidth:60,
		border : false,
		padding : '15 10 500 10',
		defaults : {
			anchor : '100%'
		},
		items:[{
			id:"datumcontent",
			name:'datumcontent',
			fieldLabel:'资料内容',
			allowBlank : false,
			html: "" ,
			height:100,
			maxLength :300
		},{
			xtype : 'hidden',
		    name : 'id'
		},{
			xtype : 'hidden',
		    name : 'rowIndex1',
		    id : 'rowIndex1'
		}]
	});
    
    var addWindow = new Ext.Window({
    	title:'资料详情',
		width:620,
		height:200,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [addForm],
		buttons : [{
			text : '下载',
			handler : function() { 
			  //获取行号
			   var rowIndex = Ext.getCmp("rowIndex1").getValue();
			   var record = grid.getStore().data.items[rowIndex];
			   var id = record.data.id;
			   Ext.MessageBox.confirm('下载提示', '是否要将该学习资料下载到本地？', function(c) {
				  if(c=='yes'){ 
				     window.location.href="DatumInfodownload.do?id="+id;
				     addWindow.hide();
				     store.reload();
				   }
				   
				   
				 });
			 
			}
		},{
  			text : '取消',
  			handler : function() {
  				addWindow.hide();
  			}
  		}]
	});
    
    
    //跳转审核方法
    var audit = function(grid, rowIndex, colIndex){
    	var record = grid.getStore().getAt(rowIndex); 
    	Ext.getCmp("datumcontent").html="<a target='_blank' href='"+record.data.datumcontent+"' >"+record.data.datumcontent+"</a>";
      //隐藏传行号rowIndex
  	    addWindow.items.get(0).form.findField("rowIndex1").setValue(rowIndex);
    	addWindow.show();
    };
    
    
    //布局
    new Ext.Viewport({
		layout:"fit",
		items:[{
			frame:true,
			title:"学习资料审核查询",
			iconCls:'menu-51',
			layout:"border",
			items:[searchForm,grid]
		}]
	});
    
   

});