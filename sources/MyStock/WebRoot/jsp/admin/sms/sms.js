/*!
 * 
 */
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	var UuserObj = [
		{ name:'id', type:'int'},
		{ name:'smsName', type:'string'},
		{ name:'content', type:'string'}
	];
	
	var store = new Ext.data.JsonStore({
	    url: 'sms_findPageSms.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: {params:{start:0, limit:15}},
	    fields: UuserObj
	});
	
    var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[new Ext.grid.RowNumberer(),
	            {header: '短信名称', width: 330,align:'center', dataIndex: 'smsName'},
	            {header: '短信内容', width: 800,align:'center', dataIndex: 'content'}]
        }),
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
		loadMask : true,	//加载时的遮罩
		frame : true,
		title:'短信管理',
        iconCls:'menu-62',
        layout : 'fit',
        viewConfig:{
        	forceFit: true, 
        	scrollOffset: 0
       	},
        tbar:['->',{
        	text:'修改',
        	iconCls:'btn-edit',
        	handler: function(){
        		var record= grid.getSelectionModel().getSelected(); 
				if(!record){
                	Ext.Msg.alert('信息提示','请选择要修改的数据');
				}else{
	        		uWindow.show();
					uForm.getForm().loadRecord(record);
				}
        	}
        }],
        
        bbar: new Ext.PagingToolbar({
            pageSize: 15,
            store: store,
            displayInfo: true
        })
    });
    
    

    var uForm = new Ext.FormPanel({
		layout : 'form',
		baseCls:'x-plain',
		labelWidth:60,
		border : false,
		padding : '15 10 0 8',
		defaults : {
			anchor : '100%',
			xtype : 'textfield'
		},
		items:[{
				name:'smsName',
				fieldLabel:'短信名称',
				maxLength :20,
				allowBlank : false
			},{
				name:'content',
				height:150,
				fieldLabel:'短信内容',
				xtype : 'textarea',
				allowBlank : false
			},{
				xtype : 'hidden',
			    name : 'id'
			}]
	});
    
    var uWindow = new Ext.Window({
		title : '短信模板',
		width:400,
		height:300,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [uForm],
		buttons : [{
			text : '保存',
			handler : function() {
				if (uForm.getForm().isValid()) {
					uForm.getForm().submit({
						url : 'sms_saveOrUpdateSms.do',
						success : function(form, action) {
							Ext.Msg.alert('信息提示',action.result.message);
							uWindow.hide();
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
		}, {
			text : '取消',
			handler : function() {
				uWindow.hide();
			}
		}]
	});
    
    new Ext.Viewport({
		layout:'border',
		items:[{
			region:'center',
			layout:'fit',
			border:false,
			items:grid
		}]
	});

});