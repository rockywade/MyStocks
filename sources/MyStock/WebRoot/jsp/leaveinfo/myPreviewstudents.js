/**
 * 我的学生预览
 */
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	 //查询所有所有班级
    var ClassStore = new Ext.data.JsonStore({
		url: '/MyStock/LeaveInfo_findClassComb1.do',
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
						width:100,
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
				},{
					width:250,
					items:[{
						width:75,
						xtype:"button",
						cls :'returnBtn',
						text:"返回",
						 listeners : {
							  click : function (btn) {
				        	     window.location.href = 'leaveinfoApproval.jsp';
							}
				        		
				        }
					}]
				}]
			}]
		}]
		
		
	});
	
	
	var LeaveInfoLogObj = [
                { name:'studentnum', type:'string'}, 
	            { name:'studentname', type:'string'},
	            { name:'classcode', type:'string'},
	            { name:'major', type:'string'},
	            { name:'bedroom', type:'string'},
	            { name:'relationtel', type:'string'},
	            { name:'classth', type:'string'},
	            { name:'counsellor', type:'string'},
	            { name:'totalnum', type:'int'}
	         
	        ];
	
	
	
	var store = new Ext.data.JsonStore({
		 url: '/MyStock/LeaveInfo_findPageLeaveInfoLog.do',
		 root: 'root',
		 totalProperty: 'total',
		 autoLoad: {params:{start:0, limit:15}},
		fields: LeaveInfoLogObj
	});
	
	var sm = new Ext.grid.CheckboxSelectionModel({singleSelect: false});//单选
    var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[new Ext.grid.RowNumberer(),
			    {header: '班级',  width: 200, align:'center', dataIndex: 'classcode'},
			    {header: '姓名',  width: 200, align:'center', dataIndex: 'studentname'},
			    {header: '学号',  width: 200, align:'center', dataIndex: 'studentnum'},
			   // {header: '专业',  width: 200, align:'center', dataIndex: 'major'},
			    {header: '联系电话',  width: 250, align:'center', dataIndex: 'relationtel'},
			    {header: '班主任',  width: 150, align:'center', dataIndex: 'classth'},
			    {header: '辅导员',  width: 150, align:'center', dataIndex: 'counsellor'},
			    {header: '累计次数',  width: 150, align:'center', dataIndex: 'totalnum'}
	           
			]
        }),
        // sm:sm,//checkbox单选框
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
        //autoExpandColumn: 'gysbz', //自动扩展列
		loadMask : true,	//加载时的遮罩
		frame : true,
		region:"center",
        iconCls:'',
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
        listeners:{  
	      rowclick:function(grid,row){  
			var record = grid.getStore().getAt(row); 
			var s = '?studentnum='+record.data.studentnum;
	        window.location.href = 'previewstudents.jsp'+s;
	        
	    }   
	}  
    });

    
    //布局
    new Ext.Viewport({
		layout:"fit",
		items:[{
			frame:true,
			title:"我的学生一览",
			iconCls:'',
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