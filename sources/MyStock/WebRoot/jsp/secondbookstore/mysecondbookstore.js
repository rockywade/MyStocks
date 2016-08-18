
/**
 * 二手书店
 */
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	var SecondBookStoreObj = [
	            { name:'id', type:'Integer'},
	            { name:'subject', type:'string'},
	            { name:'storeType', type:'string'},
	            { name:'content', type:'string'},
	            { name:'nickname', type:'string'},
	            { name:'publishtime', type:'string'},
	            { name:'popularity', type:'Integer'},
	            { name:'status', type:'Integer'},
	            { name:'replynickname', type:'string'},
	            { name:'updatetime', type:'string'}
	        ];
	
	
	
	var store = new Ext.data.JsonStore({
		//url: 'datumInfo_findPageDatumInfo.do',datumInfo_findPageDatumInfoBy.do
		 url: 'secondBookStore_findPageMySecondBookStore.do',
		 root: 'root',
		 totalProperty: 'total',
		 autoLoad: {params:{start:0, limit:15}},
		 fields: SecondBookStoreObj
	});
	
	//new Ext.grid.RowNumberer(),：序号使用
	var sm = new Ext.grid.CheckboxSelectionModel({singleSelect: true});//单选
    var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[new Ext.grid.RowNumberer(),
			    {header: '标示',  width: 10, align:'center', dataIndex: 'id', hidden : true},
			    {header: '出/求',  width: 80, align:'center', dataIndex: 'storeType'},
			    {header: '主题',  width: 250, align:'center', dataIndex: 'subject'},
			    {header: '作者',  width: 100, align:'center', dataIndex: 'nickname'},
			    {header: '发布时间',  width: 150, align:'center', dataIndex: 'publishtime'},
			    {header: '人气',  width: 100, align:'center', dataIndex: 'popularity'},
			    {header: '最新回复人',  width: 100, align:'center', dataIndex: 'replynickname'},
			    {header: '状态',  width: 150, align:'center', dataIndex: 'status',
			    	renderer:function(value, metaData, record, rowIndex, colIndex, store, view) {
		                if(value == 0) { 
	                        return "展示中";
		                }     
		                return "隐藏中";
	            	}
			    },
			    {xtype: 'actioncolumn',header : '操作', width: 100,align : 'center',
	            	menuDisabled : true,
	                items: [{
	                    icon: '../../img/btn/delete.png',
	                    tooltip: '删除',
	                	handler: function(grid, rowIndex, colIndex){
	                		deleteR(grid, rowIndex, colIndex);
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
        iconCls:'menu-52',
        layout : 'fit',
        viewConfig:{
        	forceFit: true, 
        	scrollOffset: 0
       	},
        tbar:['->',{
        	text:'发布主题',
        	iconCls:'btn-add',
        	handler: function(){
        		addWindow.items.get(1).hide();
        		addForm.getForm().reset();
        		addWindow.show();
        		Ext.getCmp("publish").setVisible(false);
        	}
        },'-',{
        	text:'返回',
        	iconCls:'',
        	handler: function(){
    			window.location.href = 'secondbookstore.jsp';
        	}}],
       
        bbar: new Ext.PagingToolbar({
            pageSize: 15,
            store: store,
            displayInfo: true
        })
    });

    var deleteR = function(grid, rowIndex, colIndex){
    	var record = grid.getStore().getAt(rowIndex); 
    	Ext.MessageBox.confirm('删除提示', '是否删除该主题？', function(c) {
		   	Ext.Ajax.request({
		   			url : "secondBookStore_delete.do",
		   			params:{ids : record.get("id")},
		   			success : function() {
		   				store.reload();
		   			}
		   		});
		    
		});
    };
    
    //发布二手书店
    var addForm = new Ext.FormPanel({
		layout : 'form',
		url : 'secondBookStore_saveOrUpdateSecondBookStore.do',
		fileUpload:true,  
		frame:true,
		labelWidth:60,
		border : false,
		padding : '15 10 500 10',
		defaults : {
			anchor : '100%'
		},
		items:[{
			xtype:'combo',
			hiddenName:'storeType',
			fieldLabel:'出/求',
			mode: 'local',
			triggerAction: 'all',
			emptyText: '',
			allowBlank : false,
			editable : false,
			store : [['求','求'],['出','出']]
		},{
			xtype:'textfield',
			name:'subject',
			fieldLabel:'主题',
			allowBlank : false,
			height:40,
			maxLength :32
		},{
			xtype:'textarea',
			name:'content',
			fieldLabel:'内容',
			allowBlank : false,
			height:100,
			maxLength :200
		},{
			xtype:'textfield',
			name:'image',
			fieldLabel:'图片',
			inputType: 'file',
			height:40,
			validator: function(value){
				if(''==value){
					return true;
				}
				var extension=new String(value.substring(value.lastIndexOf(".")+1,value.length));
				extension = extension.toLowerCase();   
				if(extension=="png"||extension=="jpg"||extension=="gif"||extension=="jpeg"||extension=="bmp")
	            {
	            	return true; 
	            }
	            else
	            {
	            	return '只允许上传png|jpg|gif|bmp';
	            }
				
			},
			maxLength :200
		},{
			xtype:'textfield',
			name:'attachment',
			fieldLabel:'附件',
			inputType: 'file',
			height:40,
			validator: function(value){
				if(''==value){
					return true;
				}
				var extension=new String(value.substring(value.lastIndexOf(".")+1,value.length));
				extension = extension.toLowerCase();   
				if(extension=="png"||extension=="jpg"||extension=="gif"||extension=="jpeg"||extension=="bmp")
	            {
					return '附件不允许上传图片';
	            }
	            else
	            {
	            	return checkFile();
	            }
			},
			maxLength :200
		},{
			id :'secondbookstoreid',
			xtype : 'hidden',
		    name : 'id'
		}]
	});
    
    
    var viewPanel = new Ext.Panel({
    	   id : 'viewPanel',
	       layout : 'fit',
	       html : '',
	       frame : true
    });
    
    var viewWindow = new Ext.Window({
    	title:'预览',
		width:620,
		height:350,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [viewPanel],
		buttons : [{
			text : '关闭',
			handler : function() {
				viewWindow.hide();
			}
		}]
	});
    
    var addWindow = new Ext.Window({
    	title:'发布二手书店',
		width:620,
		height:350,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [addForm,viewWindow],
		buttons : [{
			text : '预览',
			id:'preView',
			handler : function() {
				if (addForm.getForm().isValid()) {
					addForm.getForm().submit({
					  success : function(form, action) {
						var id = action.result.id;
						document.getElementById("id").value=id;
						var html = '<iframe src="secondBookStore_viewSecondBookStore.do?id='+id+'" frameborder="0" width="100%"  height="100%"></iframe>';
						if(Ext.getCmp('viewPanel').body){
							Ext.getCmp('viewPanel').body.update(html);
						}else{
							Ext.getCmp('viewPanel').html = html;
						}
						viewWindow.show();
						Ext.getCmp("publish").setVisible(true);
					},
					failure : function(form, action) {
						if(action.result.errors){
							Ext.Msg.alert('信息提示',action.result.errors);
						}else{
							Ext.Msg.alert('信息提示','连接失败');
						}
					},
					waitTitle : '预览',
					waitMsg : '正在预览数据，稍后...'
				});
				}
			}
		},{
			text : '发布',
			id:'publish',
			handler : function() {
				Ext.Ajax.request({
		   			url : "secondBookStore_update.do",
		   			params:{
						ids : document.getElementById("id").value,
						type : 4,
					},
		   			success : function() {
						addWindow.hide();
		   			}
		   		});
		}
		}]
	});
    
    addWindow.on("hide",function(){
    	Ext.Ajax.request({
   			url : "secondBookStore_deleteSecondBookStore.do",
   			params:{id : document.getElementById("id").value},
   			success : function() {
   				store.reload();
   			}
   		});
    });
  
    
    //布局
    new Ext.Viewport({
		layout:"fit",
		items:[{
			frame:true,
			title:"二手书店查询",
			iconCls:'menu-52',
			layout:"border",
			items:[grid]
		}]
	});
    
    var checkFile = function(){  
        //取控件DOM对象   
        var field = document.getElementById('attachment');  
        //取控件中的input元素   
        var inputs = field.form.getElementsByTagName('input');  
        var fileInput = null;  
        var il = inputs.length;  
        //取出input 类型为file的元素   
        for(var i = 0; i < il; i ++){  
            if(inputs[i].type == 'file'){  
                fileInput = inputs[i];  
            }  
        }  
        if(fileInput != null){  
            var fileSize = getFileSize(fileInput);  
            //允许上传不大于10M的文件 
            if(fileSize > 10000){  
               return "附件不能超过10M"; 
            }  
        } 
        return true;
    }  
    
    var getFileSize = function(target){  
        var isIE = /msie/i.test(navigator.userAgent) && !window.opera;  
        var fs = 0;  
        if (isIE && !target.files) {  
            var filePath = target.value;  
            var fileSystem = new ActiveXObject("Scripting.FileSystemObject");  
            var file = fileSystem.GetFile (filePath);  
            fs = file.Size;   
        }else if(target.files && target.files.length > 0){  
            fs = target.files[0].size;  
        }else{  
            fs = 0;  
        }  
        if(fs > 0){  
            fs = fs / 1024;  
        }  
        return fs;  
    } 

});