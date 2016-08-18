function showImg(value){
	return "<img src='"+value+"'  id='imgsrc' height='50'/>";
}
	
/*!
 * 场地管理
 */

var useStatus ;
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	 //查询所有管理员（辅导员）下拉菜单
    var counsellorStore = new Ext.data.JsonStore({
		url: 'SiteInfo_findSiteInfoComb.do',
	    root: 'root',
	    totalProperty: 'total',
	    fields: ['value','text']
	    
	});
    counsellorStore.on('load', function(store, record, options) {   
        store.insert(0, new Ext.data.Record({ 'value': '', 'text': '全部' }));   
    });
    
    //查询所有所有园区下拉菜单  
    var parkStore = new Ext.data.JsonStore({
		url: 'SiteInfo_findParkComb.do',
	    root: 'root',
	    totalProperty: 'total',
	    fields: ['value','text']
	});
    
    parkStore.on('load', function(store, record, options) {   
        store.insert(0, new Ext.data.Record({ 'value': '', 'text': '全部' }));   
    });
    
    //查询所有所有场地类型下拉菜单
    var groundTypeStore = new Ext.data.JsonStore({
		url: 'SiteInfo_findGroundTypeComb.do',
	    root: 'root',
	    totalProperty: 'total',
	    fields: ['value','text']
	  
	});
    groundTypeStore.on('load', function(store, record, options) {   
    	store.insert(0, new Ext.data.Record({'text': '全部', 'value': ''}));   
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
							name:"sitename",
							fieldLabel:"场地名称"
							}]
						},{
					width:250,
					items:[{
						width:75,
						xtype:"button",
						text:"查询",
						handler:function(){
							var f = searchForm.getForm();
							if (f.isValid()) {
								var _params = f.getValues();
								//添加分页信息
								Ext.apply(_params,{start:0, limit:7,ifApproval:3});
								store.load({params:_params});
							}
						}
					}]
				}]
			}]
		}]
	});
	
	var SiteInfoObj = [
		              { name:'id', type:'int'},
		              { name:'proposer', type:'string'},
		              { name:'siteid', type:'string'},
		              { name:'relationtel', type:'string'},
		              { name:'counsellor', type:'string'},
		              { name:'activityname', type:'string'},
		              { name:'activitycontent', type:'string'},
		              { name:'activitydate', type:'String' },
		              { name:'activitytime', type:'String' },
		              { name:'activitytype', type:'string'},
		              { name:'sitename', type:'string'},
		              { name:'sitetype', type:'string'},
		              { name:'beorganize', type:'string'},
		              { name:'guideth', type:'string'},
		              { name:'sitemodle', type:'int'},
		              { name:'park', type:'string'},
		           	  { name:'sitemanager', type:'string'},
		           	  { name:'nowstatus', type:'string'},
		           	  { name:'photo', type:'string'},
		           	  { name:'sitecondition', type:'string'},
		           	  { name:'usetime', type:'string'},
		              { name:'ifschool', type:'string'},
		           	  { name:'agreement', type:'string'},
		           	  { name:'dutystate', type:'string'},
		           	  { name:'prestorestatus', type:'string'},
		           	  { name:'status', type:'string'},
		           	  { name:'createtime', type:'string'},
	    	          { name:'createrid', type:'int'},
	    	          { name:'creatername', type:'string'}
		           		
		           	];
	
	
	
  	
	  var store = new Ext.data.JsonStore({
	          url: 'SiteInfo_findPageSiteInfoBy.do',
	          root: 'root',
	          totalProperty: 'total',
	          autoLoad: {params:{start:0, limit:7,ifApproval:3}},
	          fields: SiteInfoObj
	      });
	
	  //sm,；列表选择框
	var sm = new Ext.grid.CheckboxSelectionModel({singleSelect: true});//单选
    var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[new Ext.grid.RowNumberer(),
			         {header: '场地名称',  width: 150, align:'center', dataIndex: 'sitename'},
			         {header: '场地类型', width: 100,align:'center', dataIndex: 'sitetype'},
			         {header: '园区', width: 100,align:'center', dataIndex: 'park'},
			         {header: '容量(人)', width: 150,align:'center', dataIndex: 'sitemodle'},
			         {header: '会场条件',  width: 200, align:'center', dataIndex: 'sitecondition',renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
			        	 var record = grid.getStore().getAt(rowIndex); 
			             var sitemodle = record.data.sitemodle
			             if (value.indexOf('投影仪') !=-1 && value.indexOf('电脑') !=-1 &&
		                		   value.indexOf('话筒') !=-1 && value.indexOf('空调') !=-1  ){
		                	   var Img = "";
			            	   Img+="<img src='../../img/site/size.png' height='20'/>";
			            	   Img += sitemodle;
			            	   Img += " ";
			            	   Img+="<img src='../../img/site/projection.png' height='20'/>";
			            	   Img += " ";
			            	   Img+="<img src='../../img/site/Computer.png' height='20'/>";
			            	   Img += " ";
			            	   Img+="<img src='../../img/site/Microphone.png' height='20'/>";
			            	   Img += " ";
			            	   Img+="<img src='../../img/site/kongtiao.png' height='20'/>";
			            	   return Img;
		                	   
		                   }else if (value.indexOf('投影仪') !=-1 && value.indexOf('电脑') !=-1 &&
		                		   value.indexOf('话筒') !=-1  ){
		                	   var Img = "";
			            	   Img+="<img src='../../img/site/size.png' height='20'/>";
			            	   Img += sitemodle;
			            	   Img += " ";
			            	   Img+="<img src='../../img/site/projection.png' height='20'/>";
			            	   Img += " ";
			            	   Img+="<img src='../../img/site/Computer.png' height='20'/>";
			            	   Img += " ";
			            	   Img+="<img src='../../img/site/Microphone.png' height='20'/>";
			            	   return Img;
		                	   
		                   }else if (value.indexOf('投影仪') !=-1 && value.indexOf('电脑') !=-1 &&
		                		   value.indexOf('空调') !=-1 ){
		                	   var Img = "";
		                	   Img+="<img src='../../img/site/size.png' height='20'/>";
			            	   Img += sitemodle;
			            	   Img += " ";
		            	       Img+="<img src='../../img/site/projection.png' height='20'/>";
		            	       Img += " ";
		            	       Img+="<img src='../../img/site/Computer.png' height='20'/>";
		            	       Img += " ";
			            	   Img+="<img src='../../img/site/kongtiao.png' height='20'/>";
		            	       return Img;
		                	   
		                   }else if (value.indexOf('投影仪') !=-1 && value.indexOf('话筒') !=-1 &&
		                		   value.indexOf('空调') !=-1 ){
		                	   var Img = "";
		                	   Img+="<img src='../../img/site/size.png' height='20'/>";
			            	   Img += sitemodle;
			            	   Img += " ";
		            	       Img+="<img src='../../img/site/projection.png' height='20'/>";
		            	       Img += " ";
			            	   Img+="<img src='../../img/site/Microphone.png' height='20'/>";
		            	       Img += " ";
			            	   Img+="<img src='../../img/site/kongtiao.png' height='20'/>";
		            	       return Img;
		                	   
		                   }else if (value.indexOf('投影仪') !=-1  && value.indexOf('电脑') !=-1 ){
		                	   var Img = "";
		                	   Img+="<img src='../../img/site/size.png' height='20'/>";
			            	   Img += sitemodle;
			            	   Img += " ";
		            	       Img+="<img src='../../img/site/projection.png' height='20'/>";
		            	       Img += " ";
		            	       Img+="<img src='../../img/site/Computer.png' height='20'/>";
		            	       return Img;
		                	   
		                   }else if (value.indexOf('投影仪') !=-1  && value.indexOf('话筒') !=-1 ){
		                	   var Img = "";
		                	   Img+="<img src='../../img/site/size.png' height='20'/>";
			            	   Img += sitemodle;
			            	   Img += " ";
		            	       Img+="<img src='../../img/site/projection.png' height='20'/>";
		            	       Img += " ";
			            	   Img+="<img src='../../img/site/Microphone.png' height='20'/>";
		            	       return Img;
		                	   
		                   }else if (value.indexOf('投影仪') !=-1  && value.indexOf('空调') !=-1 ){
		                	   var Img = "";
		                	   Img+="<img src='../../img/site/size.png' height='20'/>";
			            	   Img += sitemodle;
			            	   Img += " ";
		            	       Img+="<img src='../../img/site/projection.png' height='20'/>";
		            	       Img += " ";
			            	   Img+="<img src='../../img/site/kongtiao.png' height='20'/>";
		            	       return Img;
		                	   
		                   }else if ( value.indexOf('电脑') !=-1 && value.indexOf('话筒') !=-1  &&
		                		   value.indexOf('空调') !=-1 ){
		                	   var Img = "";
		                	   Img+="<img src='../../img/site/size.png' height='20'/>";
			            	   Img += sitemodle;
		            	       Img += " ";
		            	       Img+="<img src='../../img/site/Computer.png' height='20'/>";
		            	       Img += " ";
			            	   Img+="<img src='../../img/site/Microphone.png' height='20'/>";
			            	   Img += " ";
			            	   Img+="<img src='../../img/site/kongtiao.png' height='20'/>";
		            	       return Img;
		                	   
		                   }else if ( value.indexOf('电脑') !=-1 && value.indexOf('空调') !=-1 ){
		                	   var Img = "";
		                	   Img+="<img src='../../img/site/size.png' height='20'/>";
			            	   Img += sitemodle;
		            	       Img += " ";
		            	       Img+="<img src='../../img/site/Computer.png' height='20'/>";
			            	   Img += " ";
			            	   Img+="<img src='../../img/site/kongtiao.png' height='20'/>";
		            	       return Img;
		                	   
		                   }else if (value.indexOf('话筒') !=-1  &&
		                		   value.indexOf('空调') !=-1 ){
		                	   var Img = "";
		                	   Img+="<img src='../../img/site/size.png' height='20'/>";
			            	   Img += sitemodle;
		            	       Img += " ";
			            	   Img+="<img src='../../img/site/Microphone.png' height='20'/>";
			            	   Img += " ";
			            	   Img+="<img src='../../img/site/kongtiao.png' height='20'/>";
		            	       return Img;
		                	   
		                   } else if (value.indexOf('投影仪') !=-1 ){
		                	   var Img = "";
			            	   Img+="<img src='../../img/site/size.png' height='20'/>";
			            	   Img += sitemodle;
			            	   Img += " ";
			            	   Img+="<img src='../../img/site/projection.png' height='20'/>";
			            	   return Img;
		                	   
		                   }else if (value.indexOf('电脑') !=-1 ){
		                	   var Img = "";
			            	   Img+="<img src='../../img/site/size.png' height='20'/>";
			            	   Img += sitemodle;
			            	   Img += " ";
		            	       Img+="<img src='../../img/site/Computer.png' height='20'/>";
		            	       return Img;
		                	   
		                   }else if (value.indexOf('话筒') !=-1 ){
		                	   var Img = "";
			            	   Img+="<img src='../../img/site/size.png' height='20'/>";
			            	   Img += sitemodle;
			            	   Img += " ";
			            	   Img+="<img src='../../img/site/Microphone.png' height='20'/>";
			            	   return Img;
		                	   
		                   }else{
		                	   var Img = "";
			            	   Img+="<img src='../../img/site/size.png' height='20'/>";
			            	   Img += sitemodle;
			            	   Img += " ";
			            	   Img+="<img src='../../img/site/kongtiao.png' height='20'/>";
		            	       return Img;
		                   }
			            
		                 }},
			         {header: '管理老师', width: 150,align:'center', dataIndex: 'counsellor'},
			         {header: '电话', width: 150,align:'center', dataIndex: 'relationtel'},
			         {header: '图片', width: 150,align:'center', dataIndex: 'photo',renderer:showImg},
			         {xtype: 'actioncolumn',header : '操作', width: 198,align : 'center',
			              menuDisabled : true,
			              items: [ {
			                    icon: '../../img/btn/delete.png',
			                    tooltip: '删除',
			                    getClass: function(value, metaData, record, rowIndex, colIndex, store, view) {      
			                    
			                	},
			                    handler: function(grid, rowIndex, colIndex){
			                		 daleteModle(grid, rowIndex, colIndex);
			                	}
			                },{
			                	icon: '../../img/btn/split.png'
			                },{
			                    icon: '../../img/btn/edits.png',
			                    tooltip: '修改',
			                    getClass: function(value, metaData, record, rowIndex, colIndex, store, view) {      
			                   
			                	},
			                	handler: function(grid, rowIndex, colIndex){
			                		audit(grid, rowIndex, colIndex);
			                	}
			                }]
			           }  
			       ]
        }),
        sm:sm,//checkbox
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
        //autoExpandColumn: 'photo', //自动扩展列
		loadMask : true,	//加载时的遮罩
		frame : true,
		region:"center",
        iconCls:'menu-11',
        tbar:['->',{
        	text:'增加',
        	iconCls:'btn-add',
        	handler: function(){
        	 // addWindow.show();
        	   adds();
        		addForm.getForm().reset();
        	}
        }],
        listeners:{  
        	'cellclick':function(grid,rowIndex,columnIndex,e){ 

    	      viewDo(grid, rowIndex);
    	
    	      /*var record = grid.getStore().getAt(rowIndex); //Get the Record  图片预览 
		    	var fieldName = grid.getColumnModel().getDataIndex(columnIndex);
		    	if('photo'==fieldName){
		    		var data = record.get(fieldName); 
		    		imgWindow.show();
		    		Ext.getCmp("imgBox").getEl().dom.src = data;
		    	}*/
		    	
    		}   
        },
        bbar: new Ext.PagingToolbar({
            pageSize: 15,
            store: store,
            displayInfo: true
        }),
        layout : 'fit',
        viewConfig:{
        	forceFit: true, 
        	scrollOffset: 0
       	},
    });

    
    
     var viewPanel = new Ext.Panel({
	       id : 'viewPanel',
	       layout : 'fit',
	       html : '',
	       frame : true
    });

    var viewWindow = new Ext.Window({
   	title:'图片预览',
		width:1000,
		height:600,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [viewPanel]
		
	});
  
    var viewDo = function (grid, rowIndex){
 		var record = grid.getStore().getAt(rowIndex); 
 	   	var id = record.data.id;
 	    var html = '<iframe src="SiteInfo_viewImg.do?id='+id+'" frameborder="0" width="100%"  height="100%"></iframe>';
			if(Ext.getCmp('viewPanel').body){
				Ext.getCmp('viewPanel').body.update(html);
			}else{
				Ext.getCmp('viewPanel').html = html;
			}
		 viewWindow.show();
    }
    
    
    
   
    //场地编辑
    var addForm = new Ext.FormPanel({
		layout : 'form',
        fileUpload:true,  
        region:'north',
		frame:true,
		labelWidth:60,
		height:185,
		border : false,
		padding : '0 10 0 0',
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
					id:'sitename',
					name:'sitename',
					fieldLabel:'场地名',
					allowBlank : false,
					maxLength :20
				},{  
				    xtype : 'combo',  
				    hiddenName:'park',
				    id:'park',
				    fieldLabel : '园区', 
				    mode: 'local',
					triggerAction: 'all',
					valueField :'value',
					displayField: 'text',
					allowBlank : true,
					editable : false,
					store : parkStore,
					//value:'---请选择---',
					emptyText: '全部'
					
				}]
			},{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{  
				    xtype : 'combo',  
				    hiddenName:'sitetype',
				    id:'sitetype',
				    fieldLabel : '场地类型', 
				    mode: 'local',
					triggerAction: 'all',
					valueField :'value',
					displayField: 'text',
					allowBlank : true,
					editable : false,
					store : groundTypeStore,
					emptyText: '全部'
					
				    },{
					xtype : 'textfield',
					name:'sitemodle',
					id:'sitemodle',
					fieldLabel:'容量(人)',
					allowBlank : false,
					maxLength :10,
					enableKeyEvents:true,
					listeners : { 
					   keyup: function(src, evt){ 
            			var val = src.getValue().toString().replace(/\D/g,'');
						src.setValue(val);
					}
				}

				}]
			}]
		},{  
            xtype:'checkboxgroup',    
            fieldLabel:'场地条件',
            id:'sitecondition',
        	name:'sitecondition',
            columns:4,//3列    
            items:[    
                 {boxLabel:'投影仪',name:'sitecondition' , inputValue: '投影仪', checked: true },    
                 {boxLabel:'电脑',name:'sitecondition', inputValue: '电脑',},    
                 {boxLabel:'话筒',name:'sitecondition',inputValue: '话筒',},    
                 {boxLabel:'空调',name:'sitecondition',inputValue: '空调',},    
                    
            ]    
        },{
			layout:'column',
			items:[{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{  
				    xtype : 'combo',  
				    hiddenName:'counsellor',
				    id:'counsellor',
					fieldLabel:'管理老师',
					mode: 'local',
					triggerAction: 'all',
					valueField :'value',
					displayField: 'text',
					allowBlank : true,
					editable : false,
					store : counsellorStore,
					//value:'---请选择---',
					emptyText: '全部'
				   },{
					id:'upload',
					xtype: 'textfield',  
		            fieldLabel: '上传照片',  
		            name: 'upload',  
		            inputType: 'file'
				   },{
					labelWidth:60,
		            fieldLabel: '不开放时间' 
		           
				}]
			},{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype : 'textfield',
					name:'relationtel',
					id:'relationtel',
					fieldLabel:'联系电话',
					regex:/^(13\d|15[^4,\D]|17[13678]|18\d)\d{8}|170[^346,\D]\d{7}$/,
					allowBlank : false,
					maxLength :11
				}/*,{

					width : 50,
					height : 20,
					text: '图片预览',
					xtype:"button",
					handler:function(){ 
					 
				if (addForm.getForm().isValid()) {
					addForm.getForm().submit({
					  success : function(form, action) {
						var id = action.result.id;
						document.getElementById("ids").value=id;
						var html = '<iframe src="SiteInfo_viewImg.do?id='+id+'" frameborder="0" width="100%"  height="100%"></iframe>';
						if(Ext.getCmp('viewPanel').body){
							Ext.getCmp('viewPanel').body.update(html);
						}else{
							Ext.getCmp('viewPanel').html = html;
						}
						viewWindow.show();
					 },
					
				    });
				     }
					}
				}*/]
			}]
		},{
			id:'id',
			xtype : 'hidden',
		    name : 'id'
		},{
			xtype : 'hidden',
		    name : 'srcImg'
		},{
			xtype : 'hidden',
			id:'photo',
		    name : 'photo'
		},{
			xtype : 'hidden',
		    name : 'rowIndex1',
		    id : "rowIndex1"
		},{
			xtype : 'hidden',
			id:'createtime',
		    name : 'createtime'
		   
		},{
			xtype : 'hidden',
			id:'createrid',
		    name : 'createrid'
		   
		},{
			xtype : 'hidden',
			id:'creatername',
		    name : 'creatername' 
		},{
			xtype : 'hidden',
			id:'siteid',
		    name : 'siteid'
		   
		}]
	});
    
     
    
    
    //新增场地
    var adds = function (){
        var html = '<iframe src="../../extensible-1.0.2/examples/calendar/test-app-admin.jsp" frameborder="0" width="100%"  height="500px"></iframe>';
        if(Ext.getCmp('addForm1').body){
 			Ext.getCmp('addForm1').body.update(html);
 		}else{
 			Ext.getCmp('addForm1').html = html;
 		}
 	   addWindow.show();
    	
    }
    
    
    
   //场地修改
   var add = function (){
	   var siteid = Ext.getCmp("siteid").getValue();
       var html = '<iframe src="../../extensible-1.0.2/examples/calendar/test-app-admin.jsp?siteid='+siteid+'" frameborder="0" width="100%"  height="500px"></iframe>';
       if(Ext.getCmp('addForm1').body){
			Ext.getCmp('addForm1').body.update(html);
		}else{
			Ext.getCmp('addForm1').html = html;
		}
       viewWindow.hide();   
	   addWindow.show();
   	
   }
    
   //时间控件
   var addForm1 = new Ext.FormPanel({
		//layout : 'form',
		frame:true,
		labelWidth:60,
		region:"center",
		id:'addForm1',
		border : false,
		padding : '0 10 0 8',
		defaults : {
			anchor : '100%'
		},
	     layout : 'fit',
	     html : '',
	     frame : true
    });
  
    //场地
    var addWindow = new Ext.Window({
	   	  title:'场地编辑',
	   	  closeAction: "hide",
	   	  width : 750,
          height :650,
          layout : 'fit',
          plain : true, // 表示为渲染window body的背景为透明的背景
          items : [{
		    frame:true,
		    iconCls:'menu-12',
			layout:"border",
			items:[addForm,addForm1]
		}],
        buttonAlign : 'center',
        buttons : [{
			text : '取消',
			handler : function() {
				addWindow.hide();
			}
		},{
			text : '保存',
			handler : function() {
			//test-app.js获取的值
		     var activitydate = document.getElementById('activitydate1').value;
		     var activitytime = document.getElementById('activitytime1').value;
               /*if(null == activitydate ||""== activitydate){
            	   alert("不对外开放时间为空！请选择");
            	   return ;
               }*/
   
			  if (addForm.getForm().isValid()) {
					addForm.getForm().submit({
						url : 'SiteInfo_saveOrUpdatest.do?activitydate='+activitydate+'& activitytime='+activitytime,
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
    
    
    
    
    
    //图片显示
    var imgWindow = new Ext.Window({
		title : '图片预览',
		width:840,
		height:300,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [{xtype:'box',
					id:'imgBox',
                 width:200,
                 height:200,
                 autoEl:{
                     tag:'img',
                     src:'',
                 }}]
	});
    
    
    //上传图片预览
    var imgWindow1 = new Ext.Window({
		title : '图片预览',
		width:840,
		height:300,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [{
			    xtype:'box',
				id:'imgBox1',
                 width:200,
                 height:200,
                 autoEl:{
                     tag:'img',
                     src:'',
                 }}]
	});
    
    
    
   //跳转修改法
   var audit = function(grid, rowIndex, colIndex){
   	   var record = grid.getStore().getAt(rowIndex); 
   	    Ext.getCmp("id").setValue(record.data.id);
     	Ext.getCmp("sitename").setValue(record.data.sitename);
     	Ext.getCmp("park").setValue(record.data.park);
     	Ext.getCmp("sitetype").setValue(record.data.sitetype);
     	Ext.getCmp("sitemodle").setValue(record.data.sitemodle);
     	Ext.getCmp("sitecondition").setValue(record.data.sitecondition);
     	Ext.getCmp("counsellor").setValue(record.data.counsellor);
     	Ext.getCmp("relationtel").setValue(record.data.relationtel);
     	Ext.getCmp("photo").setValue(record.data.photo);
     	Ext.getCmp("createtime").setValue(record.data.createtime);
    	Ext.getCmp("createrid").setValue(record.data.createrid);
    	Ext.getCmp("creatername").setValue(record.data.creatername);
    	Ext.getCmp("siteid").setValue(record.data.siteid);
    	Ext.getCmp("rowIndex1").setValue(rowIndex);
    	/*var  sitecondition = record.data.sitecondition;
    	if(sitecondition == "投影仪,电脑"){
		    Ext.getCmp("s").setValue("电脑");
    		
   	   }else if(sitecondition == "投影仪,电脑,话筒"){
   		   Ext.getCmp("s").setValue("电脑");
   		   Ext.getCmp("s1").setValue("话筒");
   		}else{
   		   Ext.getCmp("s").setValue("电脑");
 		   Ext.getCmp("s1").setValue("话筒");
 		   Ext.getCmp("s2").setValue("空调");
   		};*/
 	    add();
 	  
 	 
   };
 
   
   //取消的方法（删除）
   var daleteModle = function (grid, rowIndex, colIndex){
   	var record = grid.getStore().getAt(rowIndex); 
   	var id = record.data.id;
   	Ext.MessageBox.confirm('取消提示', '是否取消该场地？', function(c) {
   	   if(c=='yes'){
		   Ext.Ajax.request({
		   	url : "SiteInfo_deleteSiteInfo.do",
		   	params:{ id : id },
		   	success : function() {
		   		store.reload();
		   		}
	  	   	});
   	     }
		});
  		
    }
 
	    
    //布局
    new Ext.Viewport({
		layout:"fit",
		items:[{
			frame:true,
			title:"场地管理查询",
			iconCls:'menu-11',
			layout:"border",
			items:[searchForm,grid]
		}],
		listeners:{ 
			render:function(){
    	      counsellorStore.load();
    	      parkStore.load();  
    	      groundTypeStore.load();
			}
		}
	});
    
   
    
});