Ext.BLANK_IMAGE_URL = '../../../ext/resources/images/default/s.gif';
/*!
 * 场地管理
 */
Ext.onReady(function(){
	Ext.QuickTips.init();
    
    /*活动类别*/
    var genre = new Ext.data.SimpleStore({
    	fields:['value','text'],
    	data:[['学科讲座','学科讲座'],['心理健康','心理健康'],['职业规划','职业规划'],
    	      ['党团建设','党团建设'],['文体活动','文体活动'],['其他','其他'],['','全部']]
    });
    
    /*活动状态*/
    var style = new Ext.data.SimpleStore({
    	fields:['value','text'],
    	data:[['','全部'],['未公示待审核','未公示待审核'],['公示中','公示中'],['公示结束','公示结束']]
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
			title:"公示申请管理",
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
								width:150,
								labelWidth:30,
								xtype:"textfield",
								name:"activityname",
								fieldLabel:"活动名称"
							}]
						},{
							width:260,
							items:[{
								//id:'tab_comb1',
								width : 150,
								xtype:"combo",
								hiddenName:'activitygenre',
								fieldLabel:"类别",
		   						mode: 'local',
		   						triggerAction: 'all',
								valueField :"value",
		   						displayField: "text",
		   						allowBlank : true,
		   						editable : false,
								store : genre,
								emptyText: '全部',
							}]
								
						}/*,{
							width:260,
							items:[{
								width:100,
								xtype:'combo',
								hiddenName:'publicitycheckstyle',
								fieldLabel:'状态',
								mode: 'local',
								triggerAction: 'all',
								valueField :'value',
								displayField: 'text',
								allowBlank : true,
								editable : false,
								store : style,
								emptyText: '全部',
						}]
						}*/,{
					width:260,
					items:[{
						width:75,
						xtype:"button",
						//iconCls:"btn-list",
						style:'margin-left:20px',
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
					width:100,
					items:[{
						cls :'returnBtn',
						width:75,
						xtype:"button",
						//style:'margin-top:25px',
						text:"返<i class='ikg2'></i>回",
						handler:function(){
								   window.location.href="../../menu/activity-managemen.html";
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
	
	var Activity = [
		{ name:'activityid', type:'string'},
		{ name:'activityname', type:'string'},
		{ name:'applyuser', type:'string'},
		{ name:'studentnum', type:'string'},
		{ name:'studentphonenum', type:'string'},
		{ name:'organizename', type:'string'},
		{ name:'teacher', type:'string'},
		{ name:'phonenum', type:'string'},
		{ name:'activitygenre', type:'string'},
		{ name:'activitytime', type:'string'},
		{ name:'inschoolterm', type:'string'},
		{ name:'faceobj', type:'string'},
		{ name:'capacity', type:'int'},
		{ name:'attendnum', type:'int'},
		{ name:'activityplace', type:'string'},
		{ name:'timeofduration', type:'string'},
		{ name:'activitycontent', type:'string'},
		{ name:'signupendtime', type:'string'},
		{ name:'score', type:'int'},
		{ name:'applystyle', type:'string'},
		{ name:'checktime', type:'string'},
		{ name:'uploadnewstime', type:'string'},
		{ name:'activitypublicitytime', type:'string'},
		{ name:'newscheckstyle', type:'string'},
		{ name:'publicitycheckstyle', type:'string'}
	];
	
    var store = new Ext.data.JsonStore({
	    url: 'Applyactivity_publicitylist.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: {params:{start:0, limit:15}},
	    fields: Activity
	});
    
    var rownumber = new Ext.grid.RowNumberer();//行号
    var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[rownumber,
			    {header: '活动名称', width: 100,align:'center', dataIndex: 'activityname'},
			    {header:'类别',width:100,align:'center',dataIndex:'activitygenre'},
			    {header:'时间',width:100,align:'center',dataIndex:'activitytime'},
	           // {header: '申请人', width: 100,align:'center', dataIndex: 'applyuser'},
	            {header: '所在学期', width: 100,align:'center', dataIndex: 'inschoolterm'},
	            {header: '活动地点', width: 100,align:'center', dataIndex: 'activityplace'},
	            {header: '活动考评分', width: 100,align:'center', dataIndex: 'score'},
	            {header: '公示状态', width: 100,align:'center', dataIndex: 'publicitycheckstyle'},
	            {id:'publicitycheckstyle',header:'操作',
	            	dataIndex:'publicitycheckstyle',
	            	width:140,
	            	align:"center",
	            	renderer:function(value, metaData, record, rowIndex, colIndex, store, view){
		            	var returnStr = "";
		            	if("公示结束"==record.data.publicitycheckstyle || "公示中"==record.data.publicitycheckstyle || "公示不通过"==record.data.publicitycheckstyle){
		            		returnStr = "";
		            	}else{
		            		returnStr = "<a href='#' onclick='newsAndPublicityManage("+rowIndex+")'>活动管理</a>"
		            	}
		                return returnStr;
	              }
	           }]
        }),
        rownumber:rownumber,//行号
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
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
			title:"公示管理",
			iconCls:'menu-15',
			layout:"border",
			items:[searchForm,grid]
		}]
	});
   fungrid = grid;
});

//活动新闻、公示审核方法
var fungrid;	//获取grid
function newsAndPublicityManage(rowIndex){
	var record = fungrid.getStore().getAt(rowIndex);
	Ext.Ajax.request({
		url:'Applyactivity_activityGetId.do',
	    params:{activityid:record.data.activityid},
	    success:function(msg){
	    	window.location.href = 'newsandpublicitycheck.jsp';
	    }
	});
}




