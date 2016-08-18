
/**
 * 院系领导请假审核
 */
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	
	 //查询所有所有班级
    var ClassStore = new Ext.data.JsonStore({
		url: 'LeaveInfo_findClassComb1.do',
	    root: 'root',
	    totalProperty: 'total',
	    fields: ['value','text']
	   
	});
    ClassStore.on('load', function(store, record, options) {   
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
					width:180,
					labelWidth:60,
					items:[{
							xtype:'combo',
							hiddenName:'classcode',
							fieldLabel:'班级',
							mode: 'local',
							triggerAction: 'all',
							valueField :'value',
							displayField: 'text',
							editable : false,
							store : ClassStore,
							width:80,
							emptyText: '全部'
					}]
				},{
					width:250,
					items:[{
					    labelWidth:100,
						xtype:"textfield",
						name:'studentname',
						fieldLabel:"姓名",
						anchor:"90%"
					}]
				},{
					width:250,
					items:[{
						labelWidth:100,
						xtype:"textfield",
						name:'studentnum',
						fieldLabel:"学号",
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
							editable : false,
							store : new Ext.data.SimpleStore({
							    fields: ['value', 'text'],
							    data : [['','全部'],['已同意','已同意'],['不同意','不同意'],['待审核','待审核']]
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
	
	
	var LeaveInfoObj = [
	            { name:'id', type:'int'},
	            { name:'classcode', type:'string'},
	            { name:'studentname', type:'string'},
	            { name:'studentnum', type:'string'},
	            { name:'major', type:'string'},
	            { name:'bedroom', type:'string'},
	            { name:'relationtel', type:'string'},
	            { name:'classth', type:'string'},
	            { name:'counsellor', type:'string'},
	            { name:'parentstel', type:'string'},
	            { name:'leavereason', type:'string'},
	            { name:'leavetime', type:'string'},
	            { name:'backsctime',type:'string'},
	            { name:'daysum', type:'int'},
	            { name:'parentsinfo', type:'string'},
	            { name:'rulesstate', type:'string'},
	            { name:'tutorstatus', type:'string'},
	    		{ name:'checksopinion', type:'string'},
	    		{ name:'schoolstatus', type:'string'},
	    		{ name:'schoolsopinion', type:'string'},
	            { name:'totalnum', type:'int'},
	            { name:'status', type:'string'}
	        ];
	
	
	
	var store = new Ext.data.JsonStore({
		 url: 'LeaveInfo_findPageLeaveInfo4.do',
		 root: 'root',
		 totalProperty: 'total',
		 autoLoad: {params:{start:0, limit:15}},
		fields: LeaveInfoObj
	});
	
	//new Ext.grid.RowNumberer(),：序号使用
	var sm = new Ext.grid.CheckboxSelectionModel({singleSelect: true});//单选
    var grid = new Ext.grid.GridPanel({
    	id:'gridModle',
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[new Ext.grid.RowNumberer(),
			    {header: '标示',  width: 150, align:'center', dataIndex: 'id', hidden : true},
			    {header: '班级',  width: 100, align:'center', dataIndex: 'classcode'},
			    {header: '姓名',  width: 100, align:'center', dataIndex: 'studentname'},
			    {header: '学号',  width: 100, align:'center', dataIndex: 'studentnum'},
			    {header: '请假事由',  width: 250, align:'center', dataIndex: 'leavereason'},
			    {header: '离校时间',  width: 150, align:'center', dataIndex: 'leavetime'},
	            {header: '返校时间',  width: 150, align:'center', dataIndex: 'backsctime'},
			    {header: '天数',  width: 80, align:'center', dataIndex: 'daysum'},
			    {header: '父母知情',  width: 80, align:'center', dataIndex: 'parentsinfo',renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
		               if(value == '是') { 
	                        return "<span style='color:green;font-weight:bold;'>是</span>"
	                   }     
	                   if(value == '否') { 
	                      return "<span style='color:red;font-weight:bold;'>否</span>"
	                   }   
	               }},
			    {header: '累计数',  width: 80, align:'center', dataIndex: 'totalnum'},
	            {header: '审核状态', width: 100,align:'center', dataIndex: 'schoolstatus'},
	            {xtype: 'actioncolumn',header : '操作', width: 200,align : 'center',
	            	menuDisabled : true,
	                items: [ {
	                    icon: '../../img/btn/check.png',
	                    tooltip: '审核',
	                    getClass: function(value, metaData, record, rowIndex, colIndex, store, view) {      
	                  if(record.data.daysum >= 30 && record.data.tutorstatus=='已同意'){
	                	if('未同意'==record.data.schoolstatus ||'已同意'==record.data.schoolstatus){
	                              return 'x-hidden'
	                        }
	                	  }
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

    
    //请假审批
    var addForm = new Ext.FormPanel({
		layout : 'form',
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
					name:'studentname',
					fieldLabel:'姓<i class="ikg2"></i>名',
					cls :'x-textfield',
					readOnly:true,
					maxLength :20
				},{
					xtype : 'textfield',
					name:'classcode',
					fieldLabel:'行政班级',
					cls :'x-textfield',
					readOnly:true,
					maxLength :20
				},{
					xtype : 'textfield',
					name:'classth',
					fieldLabel:'班<i class="ikg1"></i>主<i class="ikg1"></i>任',
					cls :'x-textfield',
					readOnly:true,
					maxLength :10
				}]
			},{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype : 'textfield',
					name:'studentnum',
					fieldLabel:'学<i class="ikg2"></i>号',
					cls :'x-textfield',
					readOnly:true,
					maxLength :10
				},{ xtype : 'numberfield',
					name:'relationtel',
					fieldLabel:'联系电话',
					cls :'x-textfield',
					readOnly:true,
					maxLength :12
				},{
					xtype : 'textfield',
					name:'counsellor',
					fieldLabel:'辅<i class="ikg1"></i>导<i class="ikg1"></i>员',
					cls :'x-textfield',
					readOnly:true,
					maxLength :20
				}]
			}]
		},{
			xtype : 'textfield',
			name:'bedroom',
			fieldLabel:'寝<i class="ikg2"></i>室',
			cls :'x-textfield',
			readOnly:true,
			maxLength :20
		},{
			xtype:'textarea',
			name:'leavereason',
			fieldLabel:'请假事由',
			style:'border:0', 
			height:50,
			readOnly:true
			
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
					name:'leavetime',
					fieldLabel:'离校时间',
					cls :'x-textfield',
					readOnly:true,
					maxLength :20
				},{
					xtype: 'radiogroup',  
					id   : 'atype',
					name:'parentsinfo',
					readOnly:true,
					fieldLabel: "父母知情",  
					items : [{  
					  boxLabel: '是',  
					  name: 'parentsinfo',  
					  inputValue:'是', 
					  },{  
					  boxLabel: '否',  
					  name: 'parentsinfo',  
					  inputValue:'否'  
					 }]  
					
					
				}]
			},{
				columnWidth:.5,
				layout:'form',
				defaults:{
			    anchor : '100%'
				},
				items:[{
					xtype : 'textfield',
					name:'backsctime',
					fieldLabel:'返校时间',
					cls :'x-textfield',
					readOnly:true,
					maxLength :20
				},{
					xtype : 'numberfield',
					name:'parentstel',
					fieldLabel:'父母电话',
					cls :'x-textfield',
					readOnly:true,
					maxLength :11
				}]
			}]
		},{
			xtype:'textarea',
			id:'schoolsopinion',
			name:'schoolsopinion',
			fieldLabel:'审核意见',
			height:50,
			anchor: '97.5%',
			maxLength :200
			},{
			xtype : 'hidden',
		    name : 'id'
		},{
			xtype : 'hidden',
		    name : 'rowIndex111',
		    id : "rowIndex111"
		}]
	});
    
    var addWindow = new Ext.Window({
    	title : '请假审批',
    	id:'addWin',
		width:500,
		height:360,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [addForm],
		buttons : [{
			text : '同意',
			handler : function() {
		      var schoolsopinion = Ext.getCmp("schoolsopinion").getValue();
		      //获取行号
		      var rowIndex = Ext.getCmp("rowIndex111").getValue();
		      //获取数据
			  var record = grid.getStore().data.items[rowIndex];
			
	    	  Ext.MessageBox.confirm('审核提示', '确定同意该审核？', function(c) {
	    		  if(c=='yes'){
					 Ext.Ajax.request({
					  url : "LeaveInfo_saveOrUpdate.do",
					  params:{
					    ifApproval:"3",
						id : record.data.id,
					     studentnum:record.data.studentnum,
						 studentname:record.data.studentname,
						 classcode:record.data.classcode,
						 bedroom:record.data.bedroom,
						 relationtel:record.data.relationtel,
						 classth:record.data.classth,
						 counsellor:record.data.counsellor,
						 leavereason:record.data.leavereason,
						 leavetime:record.data.leavetime,
						 daysum:record.data.daysum,
						 backsctime:record.data.backsctime,
						 parentsinfo:record.data.parentsinfo,
						 totalnum:record.data.totalnum,
						 rulesstate:record.data.rulesstate,
						 parentstel:record.data.parentstel,
						 tutorstatus:record.data.tutorstatus,
						 schoolstatus:record.data.schoolstatus,
						 checksopinion:record.data.checksopinion,
						 schoolsopinion:schoolsopinion,
							
						
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
			 text : '不同意',
			 handler : function() {
		     var rowIndex = Ext.getCmp("rowIndex111").getValue();
			 var record = grid.getStore().data.items[rowIndex];
	    	 Ext.MessageBox.confirm('审核提示', '确定不同意该审核？', function(c) {
	    		 if(c=='yes'){
					 Ext.Ajax.request({
					  url : "LeaveInfo_saveOrUpdate.do",
					  params:{
					   ifApproval:"4",
					   id : record.data.id,
					   studentnum:record.data.studentnum,
					   studentname:record.data.studentname,
					   classcode:record.data.classcode,
					   major:record.data.major,
					   bedroom:record.data.bedroom,
					   relationtel:record.data.relationtel,
					   classth:record.data.classth,
					   counsellor:record.data.counsellor,
					   leavereason:record.data.leavereason,
					   leavetime:record.data.leavetime,
					   daysum:record.data.daysum,
					   backsctime:record.data.backsctime,
					   parentsinfo:record.data.parentsinfo,
					   totalnum:record.data.totalnum,
					   Schoolsopinion:record.data.Schoolsopinion,
					   rulesstate:record.data.rulesstate,
					   parentstel:record.data.parentstel,
					   tutorstatus:record.data.tutorstatus,
					   schoolstatus:record.data.schoolstatus,
					   checksopinion:record.data.checksopinion,
					   schoolsopinion:schoolsopinion,
					 //tutorstatus  checksopinion  schoolstatus  checksopinion
						
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
    
    
    
    //跳转审核方法
    var audit = function(grid, rowIndex, colIndex){
    	var record = grid.getStore().getAt(rowIndex); 
    	addWindow.items.get(0).form.findField("studentname").setValue(record.data.studentname);
  	    addWindow.items.get(0).form.findField("studentnum").setValue(record.data.studentnum);
  	    addWindow.items.get(0).form.findField("classcode").setValue(record.data.classcode);
  	    addWindow.items.get(0).form.findField("bedroom").setValue(record.data.bedroom);
  	    addWindow.items.get(0).form.findField("classth").setValue(record.data.classth);
  	    //addWindow.items.get(0).form.findField("major").setValue(record.data.major);
  	    addWindow.items.get(0).form.findField("relationtel").setValue(record.data.relationtel);
  	    addWindow.items.get(0).form.findField("counsellor").setValue(record.data.counsellor);
  	    addWindow.items.get(0).form.findField("leavereason").setValue(record.data.leavereason);
  	    addWindow.items.get(0).form.findField("leavetime").setValue(record.data.leavetime);
  	    addWindow.items.get(0).form.findField("backsctime").setValue(record.data.backsctime);
  	    addWindow.items.get(0).form.findField("parentstel").setValue(record.data.parentstel);
  	    //addWindow.items.get(0).form.findField("checksopinion").setValue(record.data.checksopinion);
  	    //隐藏传行号rowIndex
  	    addWindow.items.get(0).form.findField("rowIndex111").setValue(rowIndex);
       if( record.data.parentsinfo == "是"){
     	    addWindow.items.get(0).form.findField("parentsinfo").setValue("是");
  	   }else{
  		 addWindow.items.get(0).form.findField("parentsinfo").setValue("否");
  				
  		};
    	addWindow.show();
    };
  
    

    
    
    //布局
    new Ext.Viewport({
		layout:"fit",
		items:[{
			frame:true,
			title:"请假审批查询",
			iconCls:'menu-22',
			layout:"border",
			items:[searchForm,grid]
		}],
		listeners:{ 
			render:function(){
    	      ClassStore.load();  
			}
		}
	});
    
    
});
   

  