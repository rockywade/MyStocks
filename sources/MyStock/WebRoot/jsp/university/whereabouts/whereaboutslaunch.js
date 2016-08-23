Ext.BLANK_IMAGE_URL = '../../../ext/resources/images/default/s.gif';
/*!
 * 活动申请
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
			title:"发起统计",
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
						width:160,
						xtype:"button",
						//iconCls:"btn-list",
						text:'发起假日去向统计',
						handler:function(){
							addForm.getForm().reset();
							addWindow.show();
						}
					}]
				},{
					width:280,
					items:[{
						width:160,
						labelWidth:30,
						xtype:"textfield",
						name:"launchname",
						fieldLabel:"去向名称"
					}]
				},{
					width:260,
					items:[{
						width:75,
						style:'margin-left:50px',
						xtype:"button",
						text:"查询",
						handler:function(){
							var f = searchForm.getForm();
							if (f.isValid()) {
								var _params = f.getValues();
								store.load({params:_params});
							}
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
	    url: 'Whereabouts_launchByPageList.do',
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
		            		returnStr = "<a href='#' onclick='count("+rowIndex+")' style='color:blue;font-weight:bold;text-decoration:none;'>统计数据</a>";
		            	}else{
		            		returnStr = "<a href='#' onclick='cancel("+rowIndex+")' style='color:blue;font-weight:bold;text-decoration:none;'>撤销</a>" +
            				"&nbsp;&nbsp;" +
            				"<a href='#' onclick='count("+rowIndex+")' style='color:blue;font-weight:bold;text-decoration:none;'>数据统计</a>";
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
        }),
        viewConfig : {
        	forceFit: true,
        	scrollOffset: 2
    	}
    });
    
    //假日日期链接
    function showL(value,cellmeta,record,rowIndex,columnIndex,store){
    	//debugger;
    	return value+" - "+record.data.launchendtime;
    }
    
    //时间验证
    Ext.apply(Ext.form.VTypes, {
        daterange : function(val, field) {
            var date = field.parseDate(val);
            if (!date) {
                return;
            }
            if (field.startDateField
                    && (!this.dateRangeMax || (date.getTime() != this.dateRangeMax
                            .getTime()))) {
                var start = Ext.getCmp(field.startDateField);
                start.setMaxValue(date);
                this.dateRangeMax = date;
                start.validate();
            } else if (field.endDateField
                    && (!this.dateRangeMin || (date.getTime() != this.dateRangeMin
                            .getTime()))) {
                var end = Ext.getCmp(field.endDateField);
                end.setMinValue(date);
                this.dateRangeMin = date;
                end.validate();
            }

            return true;
        }
    });
    
  //假日发起窗口
    var addForm = new Ext.FormPanel({
		layout : 'form',
		frame:true,
		url :'Whereabouts_whereaboutsLaunch.do',
		labelWidth:56,
		border : false,
		padding : '15 10 0 8',
		defaults : {
			anchor : '100%'
		},
		items:[{
			xtype : 'textfield',
			name:'launchname',
			fieldLabel:'假<i class="ikg2"></i>日',
			allowBlank : false,
			maxLength :20,
			anchor : '97.5%'
		},{
			layout:'column',
			items:[{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					id:'startTimeId',
					xtype : 'textfield',
					name:'launchstarttime',
					xtype:"datefield",
					fieldLabel:'开始时间',
					format:'Y-m-d',
					minValue : new Date(),
					allowBlank : false,
					editable : false,
					maxLength :20,
					vtype : 'daterange',//调用时间验证方法
					endDateField : 'endTimeId',//限制结束时间id
				},{
					id:'fqTimeid',
					xtype : 'textfield',
					name:'launchtime',
					xtype:"datefield",
					fieldLabel:'发起时间',
					format:'Y-m-d',
					minValue : new Date(),
					allowBlank : false,
					editable : false,
					maxLength :20,
					vtype : 'daterange',//调用时间验证方法
					endDateField : 'jzTimeId',//限制结束时间id
				}]
			},{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					id:'endTimeId',
					xtype : 'textfield',
					name:'launchendtime',
					xtype:"datefield",
					fieldLabel:'结束时间',
					format:'Y-m-d',
					minValue : new Date(),
					allowBlank : false,
					editable : false,
					maxLength :20,
					vtype : 'daterange',//调用时间验证方法
					startDateField: 'startTimeId',//限制开始时间id
				},{
					id:'jzTimeId',
					xtype : 'textfield',
					name:'censusendtime',
					xtype:"datefield",
					fieldLabel:'统计截止',
					format:'Y-m-d',
					minValue : new Date(),
					allowBlank : false,
					editable : false,
					maxLength :20,
					vtype : 'daterange',//调用时间验证方法
					startDateField : 'fqTimeid',//限制开始时间id
				}]
			}]
		},{
			xtype : 'hidden',
		    name : 'major'
		},{
			xtype : 'hidden',
			name : 'classnum'
		},{
			xtype:'hidden',
			name:'rolename'
		}]
	});
    
    //添加form窗口
    var addWindow = new Ext.Window({
		title : '发起去向',
		width:540,
		height:190,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [addForm],
		buttons : [{
			text : '确定发起',
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
		}/*,{
			text : '取消',
			handler : function() {
				addWindow.hide();
			}
		}*/]
	});
    
    //撤销窗口
    var checkaddWindow=new Ext.FormPanel({
    	
		layout : 'form',//纵向布局
		baseCls:'x-plain',//基本色调
		url : 'cancel_cancelAttend.do',
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
			title:"去向统计",
			iconCls:'menu-15',
			layout:"border",
			items:[searchForm,grid]
		}],
		listeners:{
			render:function(){
	    		//groundStore.load();
	    		//groundStore2.load();
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
		url : "Whereabouts_cancelWhereLaunch.do",
		params:{ launchid : record.data.launchid,launchstyle:record.data.launchstyle},
		success : function(response) {
			var responsedata = Ext.util.JSON.decode(response.responseText);
			Ext.Msg.alert('信息提示',responsedata.message);
			fungrid.getStore().reload();
		}
	});
}
//数据统计
function count(rowIndex){
	var record = fungrid.getStore().getAt(rowIndex);
	Ext.Ajax.request({
		url:'Whereabouts_wheresLaunchProperty.do',
	    params:{launchid:record.data.launchid,launchname:record.data.launchname},
	    success:function(msg){
	    	window.location.href = 'wherescount.jsp';
	    }
	});
}