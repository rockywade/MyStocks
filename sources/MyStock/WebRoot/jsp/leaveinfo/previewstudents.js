/**
 * 某个学生所有数据
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
			title:"编辑设置",
			padding:"0 5 0 5",
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
					height: 30,
					items:[{
						width:75,
						xtype:"button",
						cls :'returnBtn',
						text:'返回',
						 listeners : {
							  click : function (btn) {
						         window.location.href = 'myPreviewstudents.jsp';   
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
	    	            { name:'majorname', type:'majorname'},
	    	            { name:'bedroom', type:'string'},
	    	            { name:'relationtel', type:'string'},
	    	            { name:'classth', type:'string'},
	    	            { name:'counsellor', type:'string'},
	    	            { name:'parentstel', type:'string'},
	    	            { name:'leavereason', type:'string'},
	    	            { name:'leavetime', type:'string' },
	    	            { name:'backsctime',type:'string' },
	    	            { name:'daysum', type:'int'},
	    	            { name:'parentsinfo', type:'string'},
	    	            { name:'totalnum', type:'int'},
	    	            { name:'status', type:'string'}
	    	        ];
	
	
	var store = new Ext.data.JsonStore({
		 url: '/MyStock/LeaveInfo_findPageLeaveInfo1.do',
		 root: 'root',
		 totalProperty: 'total',
		 autoLoad: {params:{start:0, limit:15,ifApproval:3,studentnum:studentnum}},
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
					    {header: '班级',  width: 100, align:'center', dataIndex: 'classcode'},
					    {header: '姓名',  width: 80, align:'center', dataIndex: 'studentname'},
					    {header: '学号',  width: 150, align:'center', dataIndex: 'studentnum'},
					    {header: '请假事由',  width: 250, align:'center', dataIndex: 'leavereason'},
					    {header: '离校时间',  width: 250, align:'center', dataIndex: 'leavetime'},
			            {header: '返校时间',  width: 250, align:'center', dataIndex: 'backsctime'},
					    {header: '天数',  width: 80, align:'center', dataIndex: 'daysum'},
					    {header: '父母知情',  width: 80, align:'center', dataIndex: 'parentsinfo',renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
				               if(value == '是') { 
			                        return "<span style='color:green;font-weight:bold;'>是</span>"
			                   }     
			                   if(value == '否') { 
			                      return "<span style='color:red;font-weight:bold;'>否</span>"
			                   }   
			                 }},
					    {header: '累计次数',  width: 120, align:'center', dataIndex: 'totalnum'}
			         ]
        }),
        sm:sm,//checkbox
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
        //autoExpandColumn: 'gysbz', //自动扩展列
		loadMask : true,	//加载时的遮罩
		frame : true,
		region:"center",
	    iconCls:'menu-21',
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

  
    
    
   
   
   
   
       
      
      
       
   
    
    
    
      
      

     
    
    
    //布局
    new Ext.Viewport({
		layout:"fit",
		items:[{
			frame:true,
			title:"学生请假详情预览",
			iconCls:'menu-22',
			layout:"border",
			items:[searchForm,grid]
		}]
		
		
	});
    
  
   
    
    
});
   
