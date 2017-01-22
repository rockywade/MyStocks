Ext.BLANK_IMAGE_URL = '../../../ext/resources/images/default/s.gif';
/*!
 * 场地管理
 */
Ext.onReady(function(){
	Ext.QuickTips.init();
    
    /*活动类别*/
    var genre = new Ext.data.SimpleStore({
    	fields:['value','text'],
    	data:[['学科讲座','学科讲座'],['心理健康','心理健康'],['生涯规划','生涯规划'],
    	      ['党团建设','党团建设'],['文体活动','文体活动'],['其他','其他'],['','全部']]
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
			title:"活动名称检索",
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
					width:260,
					items:[{
								labelWidth:200,
								xtype:"textfield",
								name:"actionName",
								fieldLabel:"活动名称",
								anchor:"100%"
							}]
						},{
							width:300,
							items:[{
								width : 150,
								xtype:"combo",
								//emptyText: '全部',
								hiddenName:'activitygenre',
								fieldLabel:"类别",
		   						mode: 'local',
		   						triggerAction: 'all',
								valueField :"value",
		   						displayField:"text",
		   						allowBlank : true,
		   						editable : false,
								store : genre,
								
							}]
								
						},{
					width:160,
					items:[{
						width:75,
						xtype:"button",
						//iconCls:"btn-list",
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
					//width:260,
					items:[{
						cls :'returnBtn',
						width:75,
						xtype:"button",
						text:"返回",
						//icon: '../../../img/btn/close.gif',
	                    //tooltip: '返回',
						handler:function(){
							window.location.href="activityinfo.jsp";
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
	
	var Attend = [
		{ name:'id', type:'string'},
		{ name:'usernum', type:'string'},
		{ name:'username',type:'string'},
		{ name:'activityid',type:'string'},
		{ name:'activityname', type:'string'},
		{ name:'activitygenre', type:'string'},
		{ name:'activitytime', type:'string'},
		{ name:'inschoolterm', type:'string'},
		{ name:'activityplace', type:'string'},
		{ name:'gotscore', type:'int'},
		{ name:'state', type:'string'},
		{ name:'score', type:'int'},
		
	];
	
    var store = new Ext.data.JsonStore({
	    url: '/MyStock/Applyactivity_myJoinedActivity.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: {params:{start:0, limit:15}},
	    fields: Attend
	});
    
    //取消报名
    var canceljoin = function(grid, rowIndex, colIndex){
    	var record = grid.getStore().getAt(rowIndex);
    	Ext.MessageBox.confirm('取消提示', '确定取消参加此活动吗？', function(c) {
			   if(c=='yes'){
				   Ext.Ajax.request({
			   			url : "/MyStock/Applyactivity_cancelAttend.do",
			   			params:{usernum:record.data.usernum,activityid:record.data.activityid,state:record.data.state,checkkey:1},
			   			success : function(response) {
			   				var responsedata = Ext.util.JSON.decode(response.responseText);
			   				//Ext.Msg.alert('信息提示',responsedata.message);
			   				store.reload();
			   			}
				   });
			   	}
		});
    };
    
	//var sm = new Ext.grid.CheckboxSelectionModel();//单选{singleSelect: true}
    var rownumber = new Ext.grid.RowNumberer();//行号
    var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[rownumber,
			    {header: '活动名称', width: 100,align:'center', dataIndex: 'activityname'},
			    {header:'活动类别',width:100,align:'center',dataIndex:'activitygenre'},
			    {header:'活动时间',width:100,align:'center',dataIndex:'activitytime'},
	            {header: '所在学期', width: 100,align:'center', dataIndex: 'inschoolterm'},
	            {header: '活动地点', width: 100,align:'center', dataIndex: 'activityplace'},
	            {header: '活动考评分', width: 100,align:'center', dataIndex: 'score'},
	            {header: '状态', width: 100,align:'center', dataIndex: 'state',renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
		               if(value == '待参加') { 
	                        return "<span style='color:green;font-weight:bold;'>待参加</span>"
	                   }     
	                   if(value == '已取消') { 
	                      return "<span style='color:red;font-weight:bold;'>已取消</span>"
	                   }   
	                 }},
	            {header: '获得分数', width: 100,align:'center', dataIndex: 'gotscore'},
	            {xtype: 'actioncolumn',header : '操作', width: 100,align : 'center',menuDisabled : true,
	                items: [{
	                    icon: '../../../img/btn/cancel.png',
	                    tooltip: '取消报名',
	                    getClass: function(value, metaData, record, rowIndex, colIndex, store, view) {      
	                		if("待参加"!=record.data.state){
	                              return 'x-hidden'
	                        }
	                	},
	                    handler: function(grid, rowIndex, colIndex){
	                		canceljoin(grid, rowIndex, colIndex);//调用报名方法
	                	}
	                }]
	           }]
        }),
        rownumber:rownumber,//行号
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
        //autoExpandColumn: 'gysbz', //自动扩展列
		loadMask : true,	//加载时的遮罩
		frame : true,
		region:"center",
        iconCls:'menu-51',
        
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
			title:"我参与的活动",
			iconCls:'menu-15',
			layout:"border",
			items:[searchForm,grid]
		}]
	});
});