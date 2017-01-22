
/**
 * 学习资料我的下载
 */
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	
	var date = new Date();
	date.setMonth(date.getMonth()-12);
	//查询表单
	var searchForm = new Ext.FormPanel({
		region:'north',
		height: 80,
		border : false,
		layout : 'form',
		padding : '5 20 0 20',
		items:[{
			id:"jhdfieldset",
			xtype:"fieldset",
			title:"查询设置",
			padding:'0 20 0 15',
			items:[{
				layout:"column",
				defaults:{
					xtype:"container",
					autoEl:"div",
					labelAlign:'right',
					layout:"form"
				},
				items:[{
					width:60,
					style:'padding-top:5',
					xtype:'tbtext',
					text:'查询日期:'
				},{
					width:150,
					xtype:"datefield",
					name:'startdate',
					id:'startdate',
					format:'Y-m-d',
					allowBlank : false,
					value:date
					
				},{
					width:40,
					style:'padding:5 0 0 8',
					xtype:'label',
					text:'至'
				},{
					width:150,
					xtype:"datefield",
					name:'enddate',
					id:'enddate',
					format:'Y-m-d',
					allowBlank : false,
					value:new Date()
				},{
					width:150
				},{
					width:150,
					items:[{
						width:75,
						xtype:"button",
						style:'margin-left:50px',
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
					width:100,
					items:[{
						width:75,
						xtype:"button",
						cls :'returnBtn',
						text:'返回',
						 listeners : {
							  click : function (btn) {
						       window.location.href = 'datuminfoStudent.jsp';
							}
				        		
				        }
					}]
				}]
			}]
		}]
	});
	
	
	var DownLogObj = [
	            { name:'downid', type:'string'},
	            { name:'downname', type:'string'},
	            { name:'datumnumber', type:'string'},
	            { name:'datumname', type:'string'},
	            { name:'datumsize', type:'string'},
	            { name:'datumstyle', type:'string'},
	            { name:'dowtime', type:'string'},
	            { name:'sharegrade', type:'int'},
	            { name:'datumcontent', type:'string'},
	            { name:'downnumber', type:'string'},
	            { name:'gradeTag', type:'string'},//评分标示N贸易评分  Y已评分
	            { name:'bz', type:'string'}
	            
	        ];
	//获取时间
	var startdate = Ext.getCmp("startdate").getValue();
	var enddate = Ext.getCmp("enddate").getValue();
	
	var store = new Ext.data.JsonStore({
		 url: '/MyStock/datumInfo_findByHqlAndDate.do',
		 root: 'root',
		 totalProperty: 'total',
		 autoLoad: {params:{start:0, limit:15,startdate:startdate,enddate:enddate}},
		 fields: DownLogObj
	});
	
	//new Ext.grid.RowNumberer(),：序号使用
	var sm = new Ext.grid.CheckboxSelectionModel({singleSelect: true});//单选
    var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[new Ext.grid.RowNumberer(),			   
			    {header: '资料名称',  width: 300, align:'center', dataIndex: 'datumname'},
			    {header: '资料大小',  width: 200, align:'center', dataIndex: 'datumsize'},
			    {header: '资料格式',  width: 200, align:'center', dataIndex: 'datumstyle'},
			    {header: '分享时间',  width: 250, align:'center', dataIndex: 'dowtime'},
	            {header: '资料评分',  width: 200, align:'center', dataIndex: 'sharegrade'},
	            {xtype: 'actioncolumn',header : '操作', width: 200,align : 'center',
	            	menuDisabled : true,
	                items: [ {
	                    icon: '../../img/btn/pf.png',
	                    tooltip: '评分',
	                    getClass: function(value, metaData, record, rowIndex, colIndex, store, view) {      
	                		if('Y'==record.data.gradeTag){
	                              return 'x-hidden'
	                        }
	                	},
	                    handler: function(grid, rowIndex, colIndex){
	                		audit(grid, rowIndex, colIndex);
	                	}
	                }]
	           }
			]
        }),
        //sm:sm,//checkbox
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
        //autoExpandColumn: 'gysbz', //自动扩展列
		loadMask : true,	//加载时的遮罩
		frame : true,
		region:"center",
        iconCls:'menu-19',
      
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

    
    //评分
    var addForm = new Ext.FormPanel({
		layout : 'form',
		fileUpload:true,  
		frame:true,
		labelWidth:60,
		border : false,
		padding : '15 10 10 10',
		defaults : {
			anchor : '90%'
		},
		items:[{  
			id:'sharegrade',
		    xtype : 'combo',  
		    hiddenName:'sharegrade',
		    fieldLabel : '评分', 
		    store :[['1','1'],['2','2'],['3','3'],['4','4'],['5','5'],
		            ['6','6'],['7','7'],['8','8'],['9','9'],['10','10']],  
		    width:50,  
		    triggerAction: "all",  
		    value:'',
		    mode: "local",  
		    allowBlank:false,
		    editable:false,
		    value:'---请选择评分---'
		  
		},{
			xtype : 'hidden',
		    name : 'id'
		},{
			xtype : 'hidden',
		    name : 'rowIndex1',
		    id:'rowIndex1'
		}]
	});
    
    var addWindow = new Ext.Window({
    	title:'文件评分',
		width:320,
		height:150,
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
			text : '确定',
			handler : function() { 
			  var rowIndex = Ext.getCmp("rowIndex1").getValue();
		      //获取数据
			  var record = grid.getStore().data.items[rowIndex];
		
			   Ext.MessageBox.confirm('评分提示', '确定给予评分？', function(c) {
				   var sharegrade =  Number(Ext.get("sharegrade").getValue());
				   if(c=='yes'){
					 Ext.Ajax.request({
						url : '/MyStock/datumInfo_saveOrUpdateDownLog.do',
					   	params:{ 
						 downid :record.data.downid,
						 downname:record.data.downname,
						 datumname:record.data.datumname,
						 datumnumber:record.data.datumnumber,
						 datumsize:record.data.datumsize,
						 datumstyle:record.data.datumstyle,
						 shareman:record.data.shareman,
						 dowtime:record.data.dowtime,
						 sharegrade:sharegrade,//字符串转换int
						 datumcontent:record.data.datumcontent,
						 downnumber:record.data.downnumber,
						 gradeTag:record.data.gradeTag
						 },
						 success : function() {
							addWindow.hide();
				   			store.reload();
				   			}
					 });
				   }
					
					
				  });
			     
			}
		}]
	});
    
    
    //跳转评分方法
    var audit = function(grid, rowIndex, colIndex){
    	var record = grid.getStore().getAt(rowIndex); 
    	//addWindow.items.get(0).form.findField("sharegrade").setValue(record.data.sharegrade);
  	   
  	   //隐藏传行号rowIndex
  	    addWindow.items.get(0).form.findField("rowIndex1").setValue(rowIndex);
    	addWindow.show();
    };
  
    
    //布局
    new Ext.Viewport({
		layout:"fit",
		items:[{
			frame:true,
			title:"我上传资料查询",
			iconCls:'menu-19',
			layout:"border",
			items:[searchForm,grid]
		}]
	});
    
   

});