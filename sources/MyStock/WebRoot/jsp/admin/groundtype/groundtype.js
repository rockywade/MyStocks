/*!
 *  
 */
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	var UuserObj = [
		{ name:'id', type:'int'},
		{ name:'typeName', type:'string'},
		{ name:'orderNum', type:'int'}
	];
	
	var store = new Ext.data.JsonStore({
	    url: 'groundType_findPageGroundType.do',
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
	            {header: '名称', width: 600,align:'center', dataIndex: 'typeName'},
	            {header: '排序号', width: 530,align:'center', dataIndex: 'orderNum'}]
        }),
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
		loadMask : true,	//加载时的遮罩
		frame : true,
		title:'场地类型管理',
        iconCls:'menu-62',
        layout : 'fit',
        viewConfig:{
        	forceFit: true, 
        	scrollOffset: 0
       	},
        tbar:['->',{
        	text:'增加',
        	iconCls:'btn-add',
        	handler: function(){
        		uWindow.show();
        		uForm.getForm().reset();
        	}
        },'-',{
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
        },'-',{
        	text:'删除',
        	iconCls:'btn-remove',
        	handler: function(){
        		var record= grid.getSelectionModel().getSelected();
				if(!record){
                	Ext.Msg.alert('信息提示','请选择要删除的数据');  
				}else{
					Ext.MessageBox.confirm('删除提示', '是否删除该场地类型？', function(c) {
					   if(c=='yes'){
					   		Ext.Ajax.request({
					   			url : "groundType_deleteGroundType.do",
					   			params:{ id : record.get("id") },
					   			success : function() {
					   				store.reload();
					   			}
					   		});
					    }
					});
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
				name:'typeName',
				fieldLabel:'名称',
				maxLength :20,
				allowBlank : false
			},{
				name:'orderNum',
				fieldLabel:'排序号',
				maxLength :20,
				xtype : 'numberfield',
				allowBlank : false
			},{
				xtype : 'hidden',
			    name : 'id'
			}]
	});
    
    var uWindow = new Ext.Window({
		title : '场地类别',
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
						url : 'groundType_saveOrUpdateGroundType.do',
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