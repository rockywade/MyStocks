
/**
 * 二手书店
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
					width:250,
					items:[{
					    labelWidth:100,
						xtype:"textfield",
						name:'key',
						fieldLabel:"关键字",
						anchor:"90%"
					}]
				},{
					width:100,
					items:[{
						width:75,
						xtype:"button",
						iconCls:"",
						text:"查询",
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
						text:'我发布的主题',
						 listeners : {
							  click : function (btn) {
				        	     window.location.href = 'mysecondbookstore.jsp';
							}
				        		
				        }
					}]
				}]
			}]
		}]
		
		
	});
	
	
	var SecondBookStoreObj = [
	            { name:'id', type:'Integer'},
	            { name:'subject', type:'string'},
	            { name:'storeType', type:'string'},
	            { name:'content', type:'string'},
	            { name:'image', type:'string'},
	            { name:'attachment', type:'string'},
	            { name:'nickname', type:'string'},
	            { name:'publishtime', type:'string'},
	            { name:'popularity', type:'Integer'},
	            { name:'status', type:'Integer'},
	            { name:'replynickname', type:'string'},
	            { name:'updatetime', type:'string'}
	        ];
	
	
	
	var store = new Ext.data.JsonStore({
		//url: 'datumInfo_findPageDatumInfo.do',datumInfo_findPageDatumInfoBy.do
		 url: '/MyStock/secondBookStore_findPageSecondBookStore.do',
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
			    {header: '出/求',  width: 100, align:'center', dataIndex: 'storeType'},
			    {header: '主题',  width: 300, align:'center', dataIndex: 'subject'},
			    {header: '作者',  width: 100, align:'center', dataIndex: 'nickname'},
			    {header: '发布时间',  width: 150, align:'center', dataIndex: 'publishtime'},
			    {header: '人气',  width: 120, align:'center', dataIndex: 'popularity'},
			    {header: '最新回复人',  width: 150, align:'center', dataIndex: 'replynickname'},
			    {xtype: 'actioncolumn',header : '操作', width: 120,align : 'center',menuDisabled : true,
	                items: [{
	                    icon: '../../img/btn/see.png',
	                    handler: function(grid, rowIndex, colIndex){
	                		var record = grid.getStore().getAt(rowIndex); 
	            			var s = '?id='+record.data.id;
	        		        window.location.href = 'viewJudge.jsp'+s;
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
		layout : 'fit',
        viewConfig:{
        	forceFit: true, 
        	scrollOffset: 0
       	},
		region:"center",
        iconCls:'menu-52',
        tbar:['->',{
        	text:'发布主题',
        	iconCls:'btn-add',
        	handler: function(){
        		addWindow.show();
        		Ext.getCmp("publish").setVisible(false);
        		addForm.getForm().reset();
        	}
        }],
       
        bbar: new Ext.PagingToolbar({
            pageSize: 15,
            store: store,
            displayInfo: true
        }),
        listeners:{  
		    rowclick:function(grid,row){  
    			var record = grid.getStore().getAt(row); 
    			var s = '?id='+record.data.id;
		        window.location.href = 'viewJudge.jsp'+s;
		        
		    }   
    	}  
    });
    

    
    //发布二手书店
    var addForm = new Ext.FormPanel({
		layout : 'form',
		url : '/MyStock/secondBookStore_saveOrUpdateSecondBookStore.do',
		fileUpload:true,  
		frame:true,
		labelWidth:100,
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
			id:'image',
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
	            	return checkFile(); 
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
			id:'attachment',
			fieldLabel:'附件',
			inputType: 'file',
			height:40,
			validator: function(value){
				if(''==value){
					return true;
				}
				/*var extension=new String(value.substring(value.lastIndexOf(".")+1,value.length));
				extension = extension.toLowerCase();   
				if(extension=="png"||extension=="jpg"||extension=="gif"||extension=="jpeg"||extension=="bmp")
	            {
					return '附件不允许上传图片';
	            }
	            else
	            {
	            	return checkFile();
	            }*/
				return checkFile();
			},
			maxLength :200
		},{
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
		items : [addForm],
		buttons : [{
			text : '预览',
			handler : function() {
				if (addForm.getForm().isValid()) {
					addForm.getForm().submit({
						waitTitle : '预览',
						waitMsg : '正在预览数据，稍后...',
						success : function(form, action) {
							var id = action.result.id;
							document.getElementById("id").value=id;
							var html = '<iframe frameborder="0" src="/MyStock/secondBookStore_viewSecondBookStore.do?id='+id+'"  width="100%"  height="100%"></iframe>';
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
						}
					});
				}
			}
		},{
			text : '发布',
			id:'publish',
			handler : function() {
				Ext.Ajax.request({
		   			url : "/MyStock/secondBookStore_update.do",
		   			params:{
						ids : document.getElementById("id").value,
						type : 4,
					},
		   			success : function() {
						Ext.Msg.alert('信息提示','发布成功');
						addWindow.hide();
		   			}
		   		});
		}
		}]
	});
    
    addWindow.on("hide",function(){
    	Ext.Ajax.request({
   			url : "/MyStock/secondBookStore_deleteSecondBookStore.do",
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
			items:[searchForm,grid]
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