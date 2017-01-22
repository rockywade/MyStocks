
/**
 * 朋辈辅学线下学生端
 */
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	//查询表单
	var searchForm = new Ext.FormPanel({
		region:"north",
		height: 80,
		border : false,
		layout : "form",
		padding : "5 20 5 20",
		items:[{
			xtype:"fieldset",
			padding:"10 20 5 15",
			items:[{
				layout:"column",
				defaults:{
					xtype:"container",
					autoEl:"div",
					labelAlign:"right",
					layout:"form"
				},
				items:[{
					width:100,
					items:[{
						width:75,
						xtype:"button",
						text:'我参与的项目',
						 listeners : {
							  click : function (btn) {
						       window.location.href = 'myCyOfflineFd.jsp';
							}
				        		
				        }
					}]
				  },{
					width:400,
					},{
					  margins : '10 5 15 20' ,
					   margin:20,
					   width:100,
					   items:[{
						width:75,
						xtype:"button",
						text:'我关注的项目',
						 listeners : {
							  click : function (btn) {
						       window.location.href = 'myGzOfflineFd.jsp';
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
				        	     window.location.href = '../menu/tutoring-answer.html';
							}
				        		
				        }
					}]
				
				}]
			}]
		}]
		
	});
	
	var OfflineFdObj = [
	    	           { name:'xmid', type:'string'},
	    	           { name:'xmxh', type:'string'},
	    	           { name:'fxxm', type:'string'},
	    	           { name:'fxtime', type:'string'},
	    	           { name:'fxaddress', type:'string'},
	    	           { name:'fxteacher', type:'string'},
	    	           { name:'teachertel', type:'string'},
	    	           { name:'xmintro', type:'string'},
	    	           { name:'bmnumber', type:'int'},
	    	           { name:'xmzise', type:'int'},
	    	           { name:'bmstatus', type:'string'},
	    	           { name:'status', type:'string'},
	    	           { name:'plnumber', type:'int'},
	    	           { name:'renqi', type:'int'},
	    	           { name:'bz', type:'string'},
	    	           { name:'topTime', type:'string'},
	    	           { name:'createtime', type:'string'},
	    	           { name:'createrid', type:'int'},
	    	           { name:'creatername', type:'string'}
	    	       ];
	    
	
	
	var store = new Ext.data.JsonStore({
		 url: '/MyStock/offlineFd_findPageOfflineFb.do',
		 root: 'root',
		 totalProperty: 'total',
		 autoLoad: {params:{start:0, limit:15,ifApproval:0}},
		 fields: OfflineFdObj
	});
	
	//new Ext.grid.RowNumberer(),：序号使用
	var sm = new Ext.grid.CheckboxSelectionModel({singleSelect: true});//单选
    var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[
			    {header: '编号',  width: 80, align:'center', dataIndex: 'xmxh'},
			    {header: '辅学项目',  width: 200, align:'center', dataIndex: 'fxxm'},
			    {header: '辅学时间',  width: 200, align:'center', dataIndex: 'fxtime'},
			    {header: '辅学地点',  width: 150, align:'center', dataIndex: 'fxaddress'},
			    {header: '辅学老师',  width: 150, align:'center', dataIndex: 'fxteacher'},
			    {header: '项目容量',  width: 100, align:'center', dataIndex: 'xmzise'},
			    {header: '报名人数',  width: 100, align:'center', dataIndex: 'bmnumber'},
	            {header: '项目状态',  width: 100, align:'center', dataIndex: 'bmstatus',renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
		               if(value == '可报名') { 
	                        return "<span style='color:blacker;font-weight:bold;'>可报名</span>"
	                   }     
	                   if(value == '已满员') { 
	                      return "<span style='color:red;font-weight:bold;'>已满员</span>"
	                   } 
	                   if(value == '已报名'){
	                	   return "<span style='color:green;font-weight:bold;'>已报名</span>"
	                   }
	                 }},
	               {header: '评论',  width: 80, align:'center', dataIndex: 'plnumber',renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
			           if(value != null) { 
		                        return "<span style='color:blue;font-weight:bold;'>"+value+"</span>"
		                   }     
		               },
			          listeners :{
			            click:function(value){
		            	   var record= grid.getSelectionModel().getSelected();
		                   var xmid = record.get("xmid");
		                   window.location.href = 'pl.jsp?xmid='+xmid;
			            }
			         }},
	            {xtype: 'actioncolumn',header : '操作', width: 160,align : 'center',
	            	menuDisabled : true,
	                items: [ {
	                    icon: '../../img/btn/see.png',
	                    tooltip: '差看详情',
	                    getClass: function(value, metaData, record, rowIndex, colIndex, store, view) {      
	                		/*if('展现中'==record.data.status||'隐藏中'==record.data.status ||'展现中/已顶置'==record.data.status){
	                              return 'x-hidden'myCyOfflineFd.js
	                        }*/
	                	},
	                    handler: function(grid, rowIndex, colIndex){
	                		details(grid, rowIndex, colIndex);
	                	}
	                }]
	           }
			]
        }),
        // sm:sm,//checkbox
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
        //autoExpandColumn: 'gysbz', //自动扩展列
		loadMask : true,	//加载时的遮罩
		frame : true,
		region:"center",
        iconCls:'menu-22',
       
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

    
    //查看詳情
    var addForm = new Ext.FormPanel({
		layout : 'form',
		frame:true,
		labelWidth:60,
		border : false,
		padding : '0 10 8 20',
		defaults : {
			anchor : '100%'
		},
		items:[{
			layout:"column",
			items:[{
				layout:"form",
				width : 218,
				items:[{
					id:"fxxm",
					width : 145,
					xtype : "textfield",
					name:"fxxm",
					fieldLabel:"辅学项目",
					cls :'x-textfield',
					readOnly:true,
					maxLength :50,
					enableKeyEvents:true,
					listeners:{
						focus:function(){
							splbTreeWindow.show();
						},
						blur:function(){
							addSpForm.getForm().clearInvalid();
						}
					}
				}]
			},{
				width : 50,
			},{
				width : 50,
				height : 20,
				text: '取消报名',
				id:'but',
				cls :'returnBtn',
				style:'margin-right:80px',
				xtype:"button",
				handler:function(){ 
				//获取行号
				 var rowIndex = Ext.getCmp("rowIndex1").getValue();
				 var record = grid.getStore().data.items[rowIndex];
				 Ext.MessageBox.confirm('取消提示', '确定要取消报名？', function(c) {
					 if(c=='yes'){
						 Ext.Ajax.request({
						   		url : "/MyStock/offlineFd_saveOrUpdate.do",
							   	params:{ 
						   		 ifApproval:"3",
						   		 xmid : record.data.xmid,
						   		 bmnumber:record.data.bmnumber,
						   		 bmstatus:record.data.bmstatus
							   },
							   success : function(date) {
									 addWindow.hide();
					                 store.reload();
								 }
						    });
					    }
					});
				 
				}
			},{
				width : 50,
				height : 20,
				text: '特别关注',
				id:'tb',
				cls :'returnBtn',
				style:'margin-right:10px',
				xtype:"button",
				handler:function(){ 
				//获取行号
				 var rowIndex = Ext.getCmp("rowIndex1").getValue();
				 var record = grid.getStore().data.items[rowIndex];
				   Ext.MessageBox.confirm('关注提示', '确定要关注这个项目吗？', function(c) {
					   if(c=='yes'){
						 Ext.Ajax.request({
						   		url : "/MyStock/offlineFd_saveOrUpdate.do",
							   	params:{ 
						   		 ifApproval:"2",
						   		 xmid : record.data.xmid,					   	
						   		 fxxm:record.data.fxxm,
						   		 fxaddress:record.data.fxaddress,
								 fxtime:record.data.fxtime,
								 fxteacher:record.data.fxteacher,
								 xmzise:record.data.xmzise,
								 creatername:record.data.creatername, 
						   		 bmnumber:record.data.bmnumber,
						   		 bmstatus:record.data.bmstatus,
								 plnumber:record.data.plnumber,
								 plnr:record.data.plnr,
								 xmintro:record.data.xmintro,
								 xmxh:record.data.xmxh
								 },
							    success : function(date) {
									 addWindow.hide();
					                 store.reload();
								 }
						    });
				        }
						
					});
				 
				}
			}]
		},{
			xtype : 'textfield',
			name:'fxtime',
			fieldLabel:'辅学时间',
			readOnly:true,
			cls :'x-textfield',
			width : 80,
			anchor: '97.5%',
			maxLength :30
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
					name:'fxaddress',
					fieldLabel:'辅学地点',
					readOnly:true,
					cls :'x-textfield',
					maxLength :20
				},{
					xtype : 'textfield',
					name:'xmzise',
					fieldLabel:'容量(人)',
					readOnly:true,
					cls :'x-textfield',
					maxLength :20
				},{
					xtype : 'textfield',
					name:'plnumber',
					fieldLabel:'评<i class="ikg2"></i>论',
					cls :'x-textfield',
					readOnly:true,
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
					name:'fxteacher',
					fieldLabel:'辅学老师',
					readOnly:true,
					cls :'x-textfield',
					maxLength :18
				},{
					xtype : 'textfield',
					name:'bmnumber',
					fieldLabel:'已报人数',
					cls :'x-textfield',
					readOnly:true,
					maxLength :18
				},{
					xtype : 'textfield',
					name:'bmstatus',
					fieldLabel:'项目状态',
					readOnly:true,
					cls :'x-textfield',
					maxLength :18
				}]
			}]
		},{
			xtype:'textarea',
			name:'xmintro',
			fieldLabel:'项目简介',
			readOnly:true,
			style:'border:0', 
			height:60,
			anchor: '97.5%',
			maxLength :200
		},{
			xtype:'textarea',
			id:'plnr',
			name:'plnr',
			fieldLabel:'评<i class="ikg2"></i>论',
			height:22,
			anchor: '97.5%',
			maxLength :200
		},{
			xtype : 'hidden',
		    name : 'xmid'
		},{
			xtype : 'hidden',
		    name : 'rowIndex1',
		    id:'rowIndex1'
		}]
	});
    
    var addWindow = new Ext.Window({
		width:620,
		height:330,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		defaults : {
			anchor : '100%'
		},
		items : [addForm],
		buttons : [{
			id:'bm',
			text : '报名',
			handler : function() { 
			//获取行号
			 var rowIndex = Ext.getCmp("rowIndex1").getValue();
			 var record = grid.getStore().data.items[rowIndex];
			   Ext.MessageBox.confirm('报名提示', '确定要报名吗？', function(c) {
				   if(c=='yes'){
					 Ext.Ajax.request({
					   		url : "/MyStock/offlineFd_saveOrUpdate.do",
						   	params:{ 
					   		 ifApproval:"0",
					   		 xmid : record.data.xmid,					   	
					   		 fxxm:record.data.fxxm,
					   		 fxaddress:record.data.fxaddress,
							 fxtime:record.data.fxtime,
							 fxteacher:record.data.fxteacher,
							 xmzise:record.data.xmzise,
							 creatername:record.data.creatername, 
					   		 bmnumber:record.data.bmnumber,
					   		 bmstatus:record.data.bmstatus,
							 plnumber:record.data.plnumber,
							 plnr:record.data.plnr,
							 xmintro:record.data.xmintro,
							 xmxh:record.data.xmxh
							 },
						    success : function(date) {
								 addWindow.hide();
				                 store.reload();
							 }
					    });
				   }
				});
			 
			}
		},{
			id:'pl',
			text : '评论',
			handler : function() { 
			//获取行号
			 var rowIndex = Ext.getCmp("rowIndex1").getValue();
			 var plnr = Ext.getCmp("plnr").getValue();
			 var record = grid.getStore().data.items[rowIndex];
			 Ext.MessageBox.confirm('评论提示', '确定要评论吗？', function(c) {
				 if(c=='yes'){
					 Ext.Ajax.request({
					   	  url : "/MyStock/offlineFd_saveOrUpdate.do",
						   	params:{ 
				   		     ifApproval:"1",
					   		 xmid : record.data.xmid,					   	
					   		 fxxm:record.data.fxxm,
					   		 bmnumber:record.data.bmnumber,
					   		 bmstatus:record.data.bmstatus,
							 plnumber:record.data.plnumber,
						     plnr:plnr
						 },
						  success : function(date) {
								 addWindow.hide();
				                 store.reload();
							 }
					    });
				 }
					
				});
			 
			}
		}]
	});
    
    
    //跳转报名方法
    var details = function(grid, rowIndex, colIndex){
    	var record = grid.getStore().getAt(rowIndex); 
    	addWindow.items.get(0).form.findField("fxxm").setValue(record.data.fxxm);
    	addWindow.items.get(0).form.findField("fxtime").setValue(record.data.fxtime);
    	addWindow.items.get(0).form.findField("fxaddress").setValue(record.data.fxaddress);
    	addWindow.items.get(0).form.findField("xmzise").setValue(record.data.xmzise);
    	addWindow.items.get(0).form.findField("fxteacher").setValue(record.data.fxteacher);
    	addWindow.items.get(0).form.findField("bmnumber").setValue(record.data.bmnumber);
    	addWindow.items.get(0).form.findField("plnumber").setValue(record.data.plnumber);
    	addWindow.items.get(0).form.findField("bmstatus").setValue(record.data.bmstatus);
    	addWindow.items.get(0).form.findField("xmintro").setValue(record.data.xmintro);
    	if(record.data.bmstatus == "已满员"){
  		  Ext.getCmp("bm").hide();
  		  Ext.getCmp("pl").hide(); 
  		  Ext.getCmp("but").hide();
  		  Ext.getCmp("plnr").hide();
  		  Ext.getCmp("tb").show();
  	   }
    	if(record.data.bmstatus == "可报名"){
    		  Ext.getCmp("bm").show();
      		  Ext.getCmp("pl").hide(); 
    		  Ext.getCmp("but").hide();
    		  Ext.getCmp("plnr").hide();
    		  Ext.getCmp("tb").show();
    	  }
    	if(record.data.bmstatus == "已报名"){
  		   Ext.getCmp("bm").hide();
		   Ext.getCmp("pl").show(); 
  		   Ext.getCmp("but").show();
  		   Ext.getCmp("tb").show();
  		   Ext.getCmp("plnr").show();
  		
  	   }
  	    //隐藏传行号rowIndex  
  	    addWindow.items.get(0).form.findField("rowIndex1").setValue(rowIndex);
    	addWindow.show();
    };
    
    
    //布局
    new Ext.Viewport({
		layout:"fit",
		items:[{
			frame:true,
			title:"线下辅导学生端列表",
			iconCls:'menu-22',
			layout:"border",
			items:[searchForm,grid]
		}]
	});
    
   

});