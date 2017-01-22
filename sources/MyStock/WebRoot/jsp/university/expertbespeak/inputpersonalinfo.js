Ext.BLANK_IMAGE_URL = '../../../ext/resources/images/default/s.gif';
/*!
 * 完善个人信息
 */
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//学院下拉菜单
    var xyStore = new Ext.data.JsonStore({
		url: '/MyStock/expert_findXyComb.do',
	    root: 'root',
	    totalProperty: 'total',
	    fields: ['value','text'],
	    listeners:{
	    	load:function(s){
	    		r = s.getAt(0);
	    		if(r){
	    			Ext.getCmp("xyCombo").onSelect(r, 0);
	    		}
	    	}
	    }
	});
	
	//专业下拉菜单
    var majorStore = new Ext.data.JsonStore({
		url: '/MyStock/expert_findMajorComb.do',
	    root: 'root',
	    totalProperty: 'total',
	    fields: ['value','text'],
	    listeners:{
	    	load:function(s){
	    		r = s.getAt(0);
	    		if(r){
	    			Ext.getCmp("majorCombo").onSelect(r, 0);
	    		}
	    	}
	    }
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
			title:"完善个人信息",
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
						width:200,
						xtype:"button",
						//iconCls:"btn-list",
						text:'完善个人信息',
						handler:function(){
							addForm.getForm().reset();
							addWindow.show();
						}
							}]
						}]
			}]
		}]
	});
	
	//fields类声明
	var Wheresaboutslaunch = [
	        		{ name:'launchid', type:'string'},
	        		{ name:'launchname', type:'string'},
	        		{ name:'launchstarttime', type:'string'},
	        		{ name:'launchendtime', type:'string'},
	        		{ name:'launchtime', type:'string'},
	        		{ name:'censusendtime', type:'string'},
	        		{ name:'censuspersonnum', type:'int'},
	        		{ name:'major', type:'string'},
	        		{ name:'classnum', type:'string'},
	        		{ name:'rolename', type:'string'},
	        		{ name:'launchstyle', type:'string'}
	        	];
	
	//获取response数据
	var store = new Ext.data.JsonStore({
	    url: '/MyStock/Whereabouts_launchByPageList.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: {params:{start:0, limit:15}},
	    fields: Wheresaboutslaunch,
	});
		
	//var sm = new Ext.grid.CheckboxSelectionModel();//单选{singleSelect: true}
	var rownumber = new Ext.grid.RowNumberer();
    var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[rownumber,
			    {header: '假日', width: 100,align:'center', dataIndex: 'launchname'},
			    {header:'假日时间',width:240,align:'center',dataIndex:'launchstarttime',renderer:showL},
			    {header:'发起时间',width:200,align:'center',dataIndex:'launchtime'},
	            {header: '截止时间', width: 200,align:'center', dataIndex: 'censusendtime'},
	            {header: '登记人数', width: 100,align:'center', dataIndex: 'censuspersonnum'},
	            {header: '发起状态', width: 100,align:'center', dataIndex: 'launchstyle'},
	            {id:'censusendtime',header:'操作',
	            	dataIndex:'censusendtime',
	            	width:140,
	            	align:"center",
	            	renderer:function(value, metaData, record, rowIndex, colIndex, store, view){
		            	
	            		//获取当前日期
	            		var date = new Date();
		            	var yyyy = date.getFullYear();
		            	var mm = date.getMonth()+1;
		            	var dd = date.getDate();
		            	var day = yyyy+"-"+mm+"-"+dd;
		            	
		            	//获取日期转换
		            	var cd = record.data.censusendtime;
		        		var year = cd.split('')[0]+cd.split('')[1]+cd.split('')[2]+cd.split('')[3];
		        		var month = cd.split('')[5]+cd.split('')[6];
		        		var lday = cd.split('')[8]+cd.split('')[9];
		        		var censusendt = year+"-"+month+"-"+lday; 
		        		
		        		//当前日期转date
		        		var arr = day.split("-");
		        		var today = new Date(arr[0],arr[1],arr[2]);
		        		var todaytime = today.getTime();
		        		
		        		//获取日期转date
		        		var censusarr = censusendt.split("-");
		        		var censusday = new Date(censusarr[0],censusarr[1],censusarr[2]);
		        		var censusendtime = censusday.getTime();
		        		
		            	var returnStr = "";
		            	if(todaytime>censusendtime || "已撤销"==record.data.launchstyle){
		            		returnStr = "<a href='#' onclick='count("+rowIndex+")'>统计数据</a>";
		            	}else{
		            		returnStr = "<a href='#' onclick='cancel("+rowIndex+")'>撤销</a>" +
            				"&nbsp;&nbsp;" +
            				"<a href='#' onclick='count("+rowIndex+")'>数据统计</a>";
		            	}
		                return returnStr;
	              }
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
        })
    });
    //假日日期链接
    function showL(value,cellmeta,record,rowIndex,columnIndex,store){
    	//debugger;
    	return value+" - "+record.data.launchendtime;
    }
    
  //完善个人信息form
    var inputPersonalInfoForm = new Ext.FormPanel({
		layout : 'form',
		fileUpload:true,
		frame:true,
		url :'/MyStock/expert_saveOrUpdateExpert.do',
		labelWidth:80,
		border : false,
		padding : '15 10 0 8',
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
					name:'xm',
					fieldLabel:'姓名',
					allowBlank : false,
					maxLength :20
				},{
					xtype : 'combo',
					hiddenName:'xb',
					fieldLabel:'性别',
					mode: 'local',
					triggerAction: 'all',
					editable : false,
					store : [['男','男'],['女','女']]
				},{
					xtype : 'textfield',
					name:'phone',
					fieldLabel:'联系电话',
					allowBlank : false,
					maxLength :11,
					regex:/^(13\d|15[^4,\D]|17[13678]|18\d)\d{8}|170[^346,\D]\d{7}$/
				},{
					xtype : 'textfield',
					name:'upload',
					fieldLabel:'个人照片',
					anchor: '60%',
					inputType: 'file'
				}]
			},{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype : 'textfield',
					name:'unit',
					fieldLabel:'单位(办公)',
					allowBlank : false,
					maxLength :20
				},{
					id:'xyCombo',
					xtype:'combo',
					hiddenName:'yxdm',
					fieldLabel:'院系',
					mode: 'local',
					triggerAction: 'all',
					valueField :"value",
					displayField: "text",
					allowBlank : false,
					editable : false,
					store : xyStore,
					emptyText: '全部',
					listeners:{
						select : function(a,b){
							addForm.getForm().findField("xydm").setValue(b.data.text);
						}
					}
				},{
					id:'majorCombo',
					xtype:'combo',
					hiddenName:'zydm',
					fieldLabel:'专业',
					mode: 'local',
					triggerAction: 'all',
					valueField :"value",
					displayField: "text",
					allowBlank : false,
					editable : false,
					store : majorStore,
					emptyText: '全部',
					listeners:{
						select : function(a,b){
							addForm.getForm().findField("zydm").setValue(b.data.text);
						}
					}
				},{
					xtype : 'textfield',
					name:'email',
					fieldLabel:'邮箱',
					//allowBlank : false,
					vtype:'email',
					maxLength :20,
				},{
					xtype:'combo',
					hiddenName:'expertType',
					fieldLabel:'专家类别',
					mode: 'local',
					triggerAction: 'all',
					editable : false,
					store : [['','全部'],['数学','数学'],['经济学','经济学']]
				}]
			}]
		},{
			xtype : 'htmleditor',
			name:'introduce',
			fieldLabel:'个人简介',
			allowBlank : false,
			anchor: '98%',
			maxLength :20
		}]
	});
    
    //信息完善窗口
    var inputPersonalInfoWindow = new Ext.Window({
		title : '添加窗口',
		width:550,
		height:300,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [inputPersonalInfoForm],
		buttons : [{
			text : '保存',
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
    
    //撤销窗口
    var checkaddWindow=new Ext.FormPanel({
    	
		layout : 'form',//纵向布局
		baseCls:'x-plain',//基本色调
		url : '/MyStock/cancel_cancelAttend.do',
        fileUpload:true,  
        autoScroll:true,//滚动条
		labelWidth:80,
		border : false,
		padding : '23 30 0 30',//上右下左
		defaults : {
			anchor : '100%',
			xtype : 'textfield'
		},
		items:[{
			xtype : 'hidden',
			name : 'launchid'
			},{
				xtype:'hidden',
				name:'launchstyle'
			}]
    });
    
    
    //布局
    new Ext.Viewport({
		layout:"fit",
		items:[{
			frame:true,
			title:"专家信息完善",
			iconCls:'menu-15',
			layout:"border",
			items:[searchForm,grid]
		}],
		listeners:{
			render:function(){
    			xyStore.load();
	    		majorStore.load();
			}
		}
	});
    fungrid = grid;
});
//撤销事件
var fungrid;
function cancel(rowIndex){
	var record = fungrid.getStore().getAt(rowIndex);
	Ext.Ajax.request({
		url : "/MyStock/Whereabouts_cancelWhereLaunch.do",
		params:{ launchid : record.data.launchid,launchstyle:record.data.launchstyle},
		success : function() {
			alert("已成功撤销");
			//Ext.Msg.alert('信息提示',action.result.message);
			fungrid.getStore().reload();
		}
	});
}
//数据统计
function count(rowIndex){
	var record = fungrid.getStore().getAt(rowIndex);
	Ext.Ajax.request({
		url:'/MyStock/Whereabouts_wheresLaunchProperty.do',
	    params:{launchid:record.data.launchid,launchname:record.data.launchname},
	    success:function(msg){
	    	window.location.href = 'wherescount.jsp';
	    }
	});
}