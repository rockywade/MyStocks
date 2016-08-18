Ext.onReady(function() {
	//音乐上传信息
	Ext.BLANK_IMAGE_URL = "/extjs/images/s.gif";
		var music=[
					{
						name : 'worksId',
						type : 'integer',
						sortable : true
					},
					{
						name : "isbn",
						type : "string",
						sortable : true
					}, {
						name : "name",
						type : "string",
						sortable : true
					}, {
						name : "author",
						type : "string",
						sortable : true
					}, {
						name : "description",
						type : "string",
						sortable : true
					}, {
						name : "publish",
						type : "string",
						sortable : true
					},{
						name : "price",
						type : "float",
						sortable : true
					},
					{
						name : "number",
						type : "integer",
						sortable : true
					}, {
						
						name : "telecom",
						type : "string",
						sortable : true
					},{
						name : "move",
						type : "string",
						sortable : true
					}
					,{
						name : "link",
						type : "string",
						sortable : true
					},{
						name : "web",
						type : "string",
						sortable : true
					},{
						name : "webname",
						type : "string",
						sortable : true
					},{
						name : "exclusive",
						type : "string",
						sortable : true
					},{
						name : "copyright",
						type : "string",
						sortable : true
					},{
						name : "sublicense",
						type : "string",
						sortable : true
					},{
						name : "pname",
						type : "string",
						sortable : true
					},{
						name : "accredit",
						type : "string",
						sortable : true
					},{
						name : "Btime",
						type : "string",
						sortable : true
					}
			];
	
		var store = new Ext.data.JsonStore({
		    url: "findByName.action",
		    type:'json',
		   // root: 'root',
		    timeout: '180000',
		   
		    fields: music
		   // autoLoad : {params:{start:0,limit:10}}
		})
		
		var form = new Ext.form.FormPanel({  
	         
	        labelWidth: 80,   
	        url:'upload_execute.do',  
	        fileUpload:true,  
	        defaultType: 'textfield',  
	  
	        items: [{  
	            xtype: 'textfield',  
	            fieldLabel: '上传文件',  
	            name: 'upload',  
	            inputType: 'file',  
	            allowBlank: false,  
	            anchor: '90%'  // anchor width by percentage  
	        }]  
	    });  
		var up=new Ext.Window({
			width:500,
			height:250,
			id:'up',
			frame : true,
			closable:false,
			items:form,
		
		     buttons: [{  
		            text: '上传',  
		            handler: function() {  
		                if(form.form.isValid()){  
		                    form.getForm().submit({   
		                    	waitMsg:'正在上传,请稍后!',
		                        success: function(form, action){  
		                    	var win = Ext.getCmp("up");
			 					win.hide();
			 					Ext.Msg.alert("提示","上传成功！");
			 					
			 					var grid = Ext.getCmp("musicgrid");
			 					var store = grid.getStore();
			 						store.load();
		                        },      
		                       failure: function(){      
		                        	Ext.Msg.alert("提示","上传失败！");    
		                       }  
		                    })               
		                }  
		           }  
		        },{  
		            text: '关闭',  
		            handler:function(){up.hide();}  
		        }] 
			
			
		});
	
	    var grid = new Ext.grid.GridPanel({  
	         id:'musicgrid',
	         name:'musicgrid',
	         renderTo : "form1",// 呈现
	       
	        // 下面的每一列都对应上面定义的列名  
	        height:600,
	        width:900,
	        frame:true,
	       
	        tbar : [ {
				xtype : 'button',
				text : '上传',
				iconCls : 'table_print',
				id : 'upload',
				handler : function() {
					up.show();
				}
			},
			{
				xtype : 'button',
				text : '下载',
				iconCls : 'table_print',
				id : 'download',
				handler : function() {
					window.location.href="download.do?filename=aaaa.txt";
				}
			}],
			 store : store,  
			columns: [ {header: " 序号", width: 160, sortable: true, dataIndex: 'worksId'},
				 {header: "ISBN", width: 160, sortable: true, dataIndex: 'isbn'},
			            {header: "作品名称", width: 160, sortable: true, dataIndex: 'name'},
			            {header: "作者", width: 100, sortable: true, dataIndex: 'author'},
			            {header: "是否出版", width: 100, sortable: true, dataIndex: 'publish',renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
			                if(value == 'Y') { 
		                         return "<span style='color:red;font-weight:bold;'>是</span>"
		                 }     
		                if(value == 'N') { 
		                    return "<span style='color:green;font-weight:bold;'>否</span>"
		            }   
		                
		                }},
			            {header: "出版社", width: 100, sortable: true,  dataIndex: 'pname'},
			            {header: "价格", width:100, sortable: true,  dataIndex: 'price'},
			            {header: "印刷份数", width: 100, sortable: true,  dataIndex: 'number'},
			            {header: "作者授权", width:100, sortable: true,  dataIndex: 'accredit',renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
			                if(value == 'Y') { 
		                         return "<span style='color:red;font-weight:bold;'>是</span>"
		                 }     
		                if(value == 'N') { 
		                    return "<span style='color:green;font-weight:bold;'>否</span>"
		            }   
		                
		                }},
		                 {header: "电信", width:100, sortable: true,  dataIndex: 'telecom',renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
			                if(value == 'Y') { 
		                         return "<span style='color:red;font-weight:bold;'>是</span>"
		                 }     
		                if(value == 'N') { 
		                    return "<span style='color:green;font-weight:bold;'>否</span>"
		            }   
		                
		                }}, {header: "移动", width:100, sortable: true,  dataIndex: 'move',renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
			                if(value == 'Y') { 
		                         return "<span style='color:red;font-weight:bold;'>是</span>"
		                 }     
		                if(value == 'N') { 
		                    return "<span style='color:green;font-weight:bold;'>否</span>"
		            }   
		                
		                }}, {header: "联通", width:100, sortable: true,  dataIndex: 'link',renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
			                if(value == 'Y') { 
		                         return "<span style='color:red;font-weight:bold;'>是</span>"
		                 }     
		                if(value == 'N') { 
		                    return "<span style='color:green;font-weight:bold;'>否</span>"
		            }   
		                
		                }}, {header: "网站", width:100, sortable: true,  dataIndex: 'web',renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
			                if(value == 'Y') { 
		                         return "<span style='color:red;font-weight:bold;'>是</span>"
		                 }     
		                if(value == 'N') { 
		                    return "<span style='color:green;font-weight:bold;'>否</span>"
		            }   
		                
		                }},
			            {header: "网站名称", width:100, sortable: true,  dataIndex: 'webname'},
			             {header: "独家性", width:100, sortable: true,  dataIndex: 'exclusive',renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
			                if(value == 'Y') { 
		                         return "<span style='color:red;font-weight:bold;'>是</span>"
		                 }     
		                if(value == 'N') { 
		                    return "<span style='color:green;font-weight:bold;'>否</span>"
		            }   
		                
		                }}, {header: "电子版全", width:100, sortable: true,  dataIndex: 'copyright',renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
			                if(value == 'Y') { 
		                         return "<span style='color:red;font-weight:bold;'>是</span>"
		                 }     
		                if(value == 'N') { 
		                    return "<span style='color:green;font-weight:bold;'>否</span>"
		            }   
		                
		                }}, {header: "转授权", width:100, sortable: true,  dataIndex: 'sublicense',renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
			                if(value == 'Y') { 
		                         return "<span style='color:red;font-weight:bold;'>是</span>"
		                 }     
		                if(value == 'N') { 
		                    return "<span style='color:green;font-weight:bold;'>否</span>"
		            }   
		                
		                }},    {header: "描述", width: 160, sortable: true, dataIndex: 'description'},
			            
			        ]
	    });  

	});  