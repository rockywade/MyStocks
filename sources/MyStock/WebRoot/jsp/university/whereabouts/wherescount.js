Ext.BLANK_IMAGE_URL = '../../../ext/resources/images/default/s.gif';
/*!
 * 数据统计
 */

Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//班级下拉菜单
    var bjStore = new Ext.data.JsonStore({
		url: 'Whereabouts_findClassesComb.do',
	    root: 'root',
	    totalProperty: 'total',
	    fields: ['value','text'],
	});
    bjStore.on('load', function(store, record, options) {   
    	store.insert(0, new Ext.data.Record({'text': '全部', 'value': ''}));   
    });
	
	var titlename = document.getElementById("titleName").value;
	//查询表单
	var searchForm = new Ext.FormPanel({
		region:"north",
		height: 80,
		border : false,
		layout : "form",
		padding : "5 20 0 20",
		items:[{
			xtype:"fieldset",
			title:titlename,
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
					columnWidth:.3,
					items:[{
								id:'bjCombo',
								xtype:'combo',
								width : 120,
								hiddenName:'classes',
								fieldLabel:'班级',
								mode: 'local',
								triggerAction: 'all',
								valueField :"value",
								displayField: "text",
								editable : false,
								store : bjStore,
								//emptyText: '全部',
								listeners:{
									select : function(a,b){
										addForm.getForm().findField("classes").setValue(b.data.text);
									}
								}
							}]
						},{
							columnWidth:.3,
							items:[{
								width : 120,
								xtype:"textfield",
								name:'teacher',
								fieldLabel:"班主任",
							}]
						},{
							columnWidth:.3,
							items:[{
								width : 120,
								xtype:"textfield",
								name:'counsellor',
								fieldLabel:"辅导员",
							}]
						},{
							columnWidth:.7,
							items:[{
								width : 120,
								xtype:"textfield",
								name:'wheresfact',
								fieldLabel:"去向",
							}]
						},{
							width:100,
							items:[{
								width:90,
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
					width:100,
					items:[{
						cls :'returnBtn',
						width:90,
						xtype:"button",
						text:"返<i class='ikg2'></i>回",
						handler:function(){
								   window.location.href="whereaboutslaunch.jsp";
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
	
	var Wheresaboutscensus = [
		{ name:'censusid', type:'string'},
		{ name:'censususernum', type:'string'},
		{ name:'censususername', type:'string'},
		{ name:'launchid', type:'string'},
		{ name:'launchname', type:'string'},
		{ name:'holidaytime', type:'string'},
		{ name:'classes', type:'string'},
		{ name:'mojar', type:'string'},
		{ name:'dorm', type:'string'},
		{ name:'userphone', type:'string'},
		{ name:'teacher', type:'string'},
		{ name:'counsellor', type:'string'},	//辅导员
		{ name:'wheresfact', type:'string'},	//去向实情
		{ name:'termini', type:'string'},		//目的地
		{ name:'urgentphone', type:'string'},
		{ name:'leaveschooltime', type:'string'},
		{ name:'returnschooltime', type:'string'},
		{ name:'daysnum',type:'string'},
		{ name:'parentsknows', type:'string'},
		{ name:'parentsphone', type:'string'},
		{ name:'enteragreement', type:'string'}
	];
	
    var store = new Ext.data.JsonStore({
    	url: 'Whereabouts_wheresCount.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: {params:{start:0, limit:15}},
	    fields: Wheresaboutscensus
	});
    
    var rownumber = new Ext.grid.RowNumberer();//行号
    var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[rownumber,
			    //{header: '姓名', width: 100,align:'center', dataIndex: 'censusid'},
			    {header: '姓名', width: 100,align:'center', dataIndex: 'censususername'},
			    {header: '学号', width: 100,align:'center', dataIndex: 'censususernum'},
			    {header: '班级', width: 150,align:'center', dataIndex: 'classes'},
			    {header: '班主任', width: 80,align:'center', dataIndex: 'teacher'},
			    {header: '辅导员', width: 80,align:'center', dataIndex: 'counsellor'},
			    {header: '去向', width: 80,align:'center', dataIndex: 'wheresfact'},
			    {header: '目的地', width: 100,align:'center', dataIndex: 'termini'},
	            {header: '父母是否知情', width: 100,align:'center', dataIndex: 'parentsknows'},
			    {header: '父母联系电话', width: 150,align:'center', dataIndex: 'parentsphone'},
			]
        }),
        rownumber:rownumber,//行号
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
		loadMask : true,	//加载时的遮罩
		frame : true,
		region:"center",
        iconCls:'menu-51',
        tbar:['->',{
        	text:'导出去向信息',
        	//iconCls:'btn-add',
        	handler:function(){
        		var census = store.getAt(0);
        		if(census!=undefined){
        			censusid = census.get("censusid");
        			var censusname = census.get("launchname");
    				Ext.MessageBox.confirm('提示', '确定导出去向信息吗？', function(ok) {
    				   if(ok=='yes'){
    					   window.location.href="wheresExcel.do?censusid="+censusid+"&launchname="+censusname;
    				    }
    				});
        		}else{
        			Ext.Msg.alert('信息提示','没有数据，无法导出文件');
        		}
				
			}
        }],
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
			title:"数据统计",
			iconCls:'menu-15',
			layout:"border",
			items:[searchForm,grid]
		}],
		listeners:{
		render:function(){
    		bjStore.load();
    		//groundStore2.load();
		}
	}
	});
});