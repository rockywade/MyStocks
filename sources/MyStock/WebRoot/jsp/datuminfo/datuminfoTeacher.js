
/**
 * 学习资料老师端
 */
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	//查询表单
	var searchForm = new Ext.FormPanel({
		region:'north',
		height: 80,
		border : false,
		layout : 'form',
		padding : '5 20 0 10',
		items:[{
			id:"jhdfieldset",
			xtype:"fieldset",
			title:"查询设置",
			padding:'0 20 0 10',
			items:[{
				layout:"column",
				defaults:{
					xtype:"container",
					autoEl:"div",
					labelAlign:'right',
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
					width:75,
					items:[{
						width:75,
						xtype:"button",
						text:'查询',
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
					width:150,
					items:[{
						width:75,
						xtype:"button",
						style:'margin-left:50px',
						text:'我上传的资料',
						 listeners : {
							  click : function (btn) {
				        	     window.location.href = 'myupload.jsp';
							}
				        		
				        }
					}]
				},{
					width:250,
					items:[{
						width:75,
						style:'margin-left:50px',
						xtype:"button",
						text:'我历史下载',
						 listeners : {
							  click : function (btn) {
				        	     window.location.href = 'mydown.jsp';
							}
				        		
				        }
					}]
				
				}]
			}]
		}]
	});
	
	
	var DatumInfoObj = [
	            { name:'id', type:'Integer'},
	            { name:'datumname', type:'string'},
	            { name:'datumnumber', type:'string'},
	            { name:'datumsize', type:'string'},
	            { name:'datumstyle', type:'string'},
	            { name:'shareman', type:'string'},
	            { name:'sharetime', type:'string'},
	            { name:'sharegrade', type:'int'},
	            { name:'datumcontent', type:'string'},
	            { name:'downnum', type:'Integer'},
	            { name:'downnumber', type:'string'},
	            { name:'status', type:'string'},
	            { name:'toptime', type:'string'},
	            { name:'transfetag', type:'string'}
	        ];
	
	
	
	var store = new Ext.data.JsonStore({
		 autoLoad:true,
		 url: '/MyStock/datumInfo_findPageDatumInfoOrderBy.do',
		 root: 'root',
		 totalProperty: 'total',
		 autoLoad: {params:{start:0, limit:15}},
		 fields: DatumInfoObj
	});
	
	//new Ext.grid.RowNumberer(),：序号使用
	var sm = new Ext.grid.CheckboxSelectionModel({singleSelect: true});//单选
    var grid = new Ext.grid.GridPanel({
        store: store,
        height:'610',
        id : 'grid',
        autoScroll:true,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[new Ext.grid.RowNumberer(),
			    {header: '标示',  width: 100, align:'center', dataIndex: 'id', hidden : true},
			    {header: '资料名称',  width: 200, align:'center', dataIndex: 'datumname'},
			    {header: '资料大小',  width: 150, align:'center', dataIndex: 'datumsize'},
			    {header: '资料格式',  width: 150, align:'center', dataIndex: 'datumstyle'},
			    {header: '下载次数',  width: 150, align:'center', dataIndex: 'downnum'},
			    {header: '分享人',  width: 150, align:'center', dataIndex: 'shareman'},
			    {header: '分享时间',  width: 250, align:'center', dataIndex: 'sharetime'},
	            {header: '资料评分',  width: 150, align:'center', dataIndex: 'sharegrade'},
	            {xtype: 'actioncolumn',header : '操作', width: 200,align : 'center',
	            	menuDisabled : true,
	                items: [ {
	                    icon: '../../img/btn/down.png',
	                    tooltip: '下载',
	                    getClass: function(value, metaData, record, rowIndex, colIndex, store, view) {      
	                		/*if('展现中'==record.data.status||'隐藏中'==record.data.status ||'展现中/已置顶'==record.data.status){
	                              return 'x-hidden'
	                        }*/
	                	},
	                    handler: function(grid, rowIndex, colIndex){
	                		downModle(grid, rowIndex, colIndex);
	                	}
	                }]
	           }
			]
        }),
        //sm:sm,//checkbox
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
		loadMask : true,	//加载时的遮罩
		frame : true,
		region:"south",
        iconCls:'menu-18',
        tbar:['->',{
        	text:'上传资料',
        	iconCls:'btn-add',
        	handler: function(){
        		addWindow.show();
        		addForm.getForm().reset();
        	}
        }],
        viewConfig : {
            forceFit: true,
            scrollOffset: 2
        },
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
		url : '/MyStock/datumInfo_saveDatumInfo.do',
		fileUpload:true,  
		frame:true,
		labelWidth:60,
		border : false,
		padding : '15 10 500 10',
		defaults : {
			anchor : '100%'
		},
		items:[{
			xtype:'textfield',
			name:'datumname',
			fieldLabel:'资料名称',
			allowBlank : false,
			height:40,
			maxLength :32
		},{
			xtype:'textfield',
			name:'upload',
			fieldLabel:'资料',
			inputType: 'file',
			height:40,
			maxLength :200
		},{
			xtype : 'hidden',
		    name : 'id'
		},{
			xtype : 'hidden',
		    name : 'paper'
		}]
	});
    
    var addWindow = new Ext.Window({
    	title:'资料上传',
		width:620,
		height:200,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [addForm],
		buttons : [{
			text : '取消',
			handler : function() {
				addWindow.hide();
			}
		},{
			text : '上传',
			handler : function() {
			if (addForm.getForm().isValid()) {
				addForm.getForm().submit({
				  success : function(form, action) {
					Ext.Msg.alert('信息提示',action.result.message);
					addWindow.hide();
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
    
    
    
    //下载的方法
    var downModle = function (grid, rowIndex, colIndex){
    	var record = grid.getStore().getAt(rowIndex); 
    	var id = record.data.id;
    	Ext.MessageBox.confirm('信息提示', '确定要下载该文件？', function(c) {
    	   if(c=='yes'){
    		   window.location.href="/MyStock/DatumInfodownload.do?ifApproval=1&id="+id;
    	        store.reload();
    	     }
 		});
   		
     }
    
     //分享之星
     var UsersObj = [
        	          { name:'userid', type:'Integer'},
        	          { name:'logincode', type:'string'},
        	          { name:'password', type:'string'},
        	          { name:'state', type:'Integer'},
        	          { name:'nickname', type:'string'},
        	          { name:'orderName', type:'string'},
        	          { name:'zljf', type:'string'}
        	        ];
        	
    
    
    //分享之星
	var kcStore = new Ext.data.JsonStore({
	    url: '/MyStock/datumInfo_findAllDataJiFen.do',
	    root: 'root',
	    totalProperty: 'total',
	    fields: UsersObj
	   
	});
  
    
    //分享之星列表
    var kcGrid = new Ext.grid.GridPanel({
        store: kcStore,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[
	            {header: '排序', width: 100, sortable:true, dataIndex: 'orderName'},
	            {header: '昵称', width: 150, sortable:true, dataIndex: 'nickname'},
	            {header: '积分', width: 80, sortable:true, dataIndex: 'zljf'}
	            ]
        }),
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
		frame : true,
        title:'分享之星',
        iconCls:'menu-54',
        viewConfig : {
            forceFit: true,
            scrollOffset: 2
        },
      
    });
    
   
    
    //布局
    new Ext.Viewport({
		layout:'fit',
		items:[{ 
 			frame:true,
			layout:'border',
			items:[{
				id : 'west',
				frame:true,
				region:'west',
				width:'82%',
				iconCls:'menu-18',
				items:[searchForm,grid]
			},{
				region:'center',
				padding:'0 0 0 5',
				layout:'fit',
				items:kcGrid
			}]
		}],
		listeners:{
			render:function(){
				kcStore.load();
			}
		}
	});

});

Ext.onReady(function(){
	 var westHeight = Ext.getCmp("west").getHeight();
	 Ext.getCmp("grid").setHeight(westHeight - 85);
});	