function showImg(value){
	return "<img src='"+value+"' height='50'/>";
}

/*!
 * 场地管理
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
			title:"查询设置",
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
					width:310,
					items:[{
								width:180,
								labelWidth:100,
								xtype:"textfield",
								name:"groundname",
								fieldLabel:"场地名称"
							}]
						},{
					width:250,
					items:[{
						width:75,
						xtype:"button",
						iconCls:"btn-list",
						text:"查询",
						handler:function(){
							var f = searchForm.getForm();
							if (f.isValid()) {
								var _params = f.getValues();
								//添加分页信息
								//Ext.apply(_params,{start:0, limit:15});
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
	
	var GroundObj = [
		{ name:'groundid', type:'int'},
		{ name:'groundname', type:'string'},
		{ name:'groundtype', type:'string'},
		{ name:'park', type:'string'},
		{ name:'capacity', type:'string'},
		{ name:'condition', type:'string'},
		{ name:'responsibleperson', type:'string'},
		{ name:'tel', type:'string'},
		{ name:'img', type:'string'}
	];
	
	
	
	var store = new Ext.data.JsonStore({
	    url: '/MyStock/ground_findPageGround.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: {params:{start:0, limit:15}},
	    fields: GroundObj
	});
	
	var sm = new Ext.grid.CheckboxSelectionModel({singleSelect: true});//单选
    var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[sm,
	            {header: '场地名称', width: 150,align:'center', dataIndex: 'groundname'},
	            {header: '场地类型', width: 150,align:'center', dataIndex: 'groundtype'},
	            {header: '园区', width: 150,align:'center', dataIndex: 'park'},
	            {header: '容量', width: 150,align:'center', dataIndex: 'capacity'},
	            {header: '会场条件', width: 150,align:'center', dataIndex: 'condition'},
	            {header: '责任人', width: 150,align:'center', dataIndex: 'responsibleperson'},
	            {header: '电话', width: 150,align:'center', dataIndex: 'tel'},
	            {header: '图片', width: 150,align:'center', dataIndex: 'img',renderer:showImg}
			]
        }),
        sm:sm,//checkbox
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
        //autoExpandColumn: 'gysbz', //自动扩展列
		loadMask : true,	//加载时的遮罩
		frame : true,
		region:"center",
        iconCls:'menu-51',
        
        tbar:[{
        	text:'增加',
        	iconCls:'btn-add',
        	handler: function(){
        		addWindow.show();
        		addForm.getForm().reset();
        	}
        },'-',{
        	text:'修改',
        	iconCls:'btn-edit',
        	handler: function(){
        		var record= grid.getSelectionModel().getSelected(); 
				if(!record){
                	Ext.Msg.alert('信息提示','请选择要修改的场地');
				}else{
	        		addWindow.show();
					addForm.getForm().loadRecord(record);
					addForm.getForm().findField('srcImg').setValue(record.get("img"));  
				}
        	}
        },'-',{
        	text:'删除',
        	iconCls:'btn-remove',
        	handler: function(){
        		var record= grid.getSelectionModel().getSelected();
				if(!record){
                	Ext.Msg.alert('信息提示','请选择要删除的场地');  
				}else{
					Ext.MessageBox.confirm('删除提示', '是否删除该场地？', function(c) {
					   if(c=='yes'){
					   		Ext.Ajax.request({
					   			url : "/MyStock/ground_deleteGround.do",
					   			params:{ groundid : record.get("groundid") },
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

    var addForm = new Ext.FormPanel({
		layout : 'form',
		baseCls:'x-plain',
		url : '/MyStock/ground_saveOrUpdateGround.do',
        fileUpload:true,  
		labelWidth:70,
		border : false,
		padding : '15 10 0 8',
		defaults : {
			anchor : '100%',
			xtype : 'textfield'
		},
		items:[{
				name:'groundname',
				fieldLabel:'场地名称',
				maxLength :50,
				allowBlank : false
			},{
				name:'groundtype',
				fieldLabel:'场地类型',
				maxLength :50,
				allowBlank : false
			},{
				name:'park',
				fieldLabel:'园区',
				maxLength :50,
				allowBlank : false
			},{
				name:'capacity',
				fieldLabel:'容量',
				maxLength :50,
				allowBlank : false
			},{
				name:'condition',
				fieldLabel:'会场条件',
				maxLength :50,
				allowBlank : false
			},{
				name:'responsibleperson',
				fieldLabel:'责任人',
				maxLength :50,
				allowBlank : false
			},{
				name:'tel',
				fieldLabel:'电话',
				maxLength :50,
				allowBlank : false
			},{
				xtype: 'textfield',  
	            fieldLabel: '上传照片',  
	            name: 'upload',  
	            inputType: 'file'
			},{
				xtype : 'hidden',
			    name : 'groundid'
			},{
				xtype : 'hidden',
			    name : 'srcImg'
			}]
	});
    
    var addWindow = new Ext.Window({
		title : '添加窗口',
		width:400,
		height:300,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [addForm],
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
		}, {
			text : '取消',
			handler : function() {
				addWindow.hide();
			}
		}]
	});
    
    //布局
    new Ext.Viewport({
		layout:"fit",
		items:[{
			frame:true,
			title:"当前库存查询",
			iconCls:'menu-15',
			layout:"border",
			items:[searchForm,grid]
		}]
	});
    

});