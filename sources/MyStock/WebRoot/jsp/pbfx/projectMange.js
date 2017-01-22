
/**
 *项目管理
 */
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	//查询表单
	var searchForm = new Ext.FormPanel({
		region:"north",
		height: 160,
		border : false,
		layout : "form",
		padding : "5 20 0 20",
		items:[{
			xtype:"fieldset",
			title:"项目管理",
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
								width:150,
								labelWidth:30,
								xtype:"textfield",
								name:'fxxm',
								id:'fxxm',
								cls :'cassdo',
								readOnly:true,
								fieldLabel:"项目名称"
							},{
								width:150,
								labelWidth:30,
								xtype:"textfield",
								name:'fxteacher',
								id:'fxteacher',
								fieldLabel:'辅学老师',
								cls :'cassdo',
								readOnly:true,
							}]
						},{
							width:260,
							items:[{
								width:150,
								labelWidth:30,
								xtype:"textfield",
								name:'fxtime',
								id:'fxtime',
								cls :'cassdo',
								fieldLabel:"辅学时间"
							},{
								width:150,
								labelWidth:30,
								xtype:"textfield",
								name:'teachertel',
								id:'teachertel',
								cls :'cassdo',
								readOnly:true,
								fieldLabel:"老师电话"
							}]
								
						},{
						  width:260,
						   items:[{
								width:150,
								labelWidth:30,
								xtype:"textfield",
								name:'fxaddress',
								id:'fxaddress',
								cls :'cassdo',
								readOnly:true,
								fieldLabel:"辅学地点"
						},{
							width:150,
							labelWidth:30,
							xtype:"textfield",
							name:'xmzise',
							id:'xmzise',
							fieldLabel:'项目容量',
							cls :'cassdo',
							readOnly:true,
							maxLength :20
							}]
				       },{

					    width:260,
					   items:[{
							width:150,
							labelWidth:30,
							xtype:"textfield",
							name:'xmxh',
							id:'xmxh',
							cls :'cassdo',
							readOnly:true,
							fieldLabel:"项目序号"
						},{
							width:75,
							xtype:"button",
							cls :'returnBtn',
							text:"返回",
							handler:function(){
							 window.location.href = 'offlineFdMange.jsp';
						   }
						}]
						
				}]
			},{

				width:350,
				height:50,
				labelWidth:30,
				xtype:"textarea",
				name:'xmintro',
				id:'xmintro',
				style:'border:0', 
				anchor: '90%',
				readOnly:true,
				fieldLabel:'<i class="ikg2"></i><i class="ikg2"></i>项目介绍'
		
			},{
				xtype:'hidden',
				name:'xmid'
				
			}]
		}]
	});
	
	var offlinefdLogObj = [
	    	           { name:'xmid', type:'string'},
	    	           { name:'bmId', type:'string'},
	    	           { name:'bmName', type:'string'},
	    	           { name:'xmmc', type:'string'},
	    	           { name:'bmtime', type:'string'},
	    	           { name:'fxaddress', type:'string'},
	    	           { name:'fxteacher', type:'string'},
	    	           { name:'xmintro', type:'string'},
	    	           { name:'bmnumber', type:'int'},
	    	           { name:'xmzise', type:'int'},
	    	           { name:'bmstatus', type:'string'},
	    	           { name:'plnumber', type:'int'},
	    	           { name:'plnr', type:'int'},
	    	           { name:'xmxh', type:'string'},
	    	           { name:'tbTag', type:'string'},
	    	           { name:'bmTag', type:'string'},
	    	           { name:'dalei', type:'string'},
	    	           { name:'classes', type:'string'},
	    	           { name:'phone', type:'string'},
	    	           { name:'creatername', type:'string'}
	    	       ];
	    
	
	
	var store = new Ext.data.JsonStore({
		 url: '/MyStock/offlineFd_findMangeProject.do',
		 root: 'root',
		 totalProperty: 'total',
		 autoLoad: {params:{start:0, limit:15,xmid:xmid}},
		 fields: offlinefdLogObj
	});
	
	//new Ext.grid.RowNumberer(),：序号使用
	var sm = new Ext.grid.CheckboxSelectionModel({singleSelect: true});//单选
    var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[new Ext.grid.RowNumberer(),
			    {header: '序号',  width: 200, align:'center', dataIndex: 'xmxh'},
			    {header: '姓名',  width: 200, align:'center', dataIndex: 'bmName'},
			    {header: '学号',  width: 250, align:'center', dataIndex: 'bmId'},
			    {header: '大类',  width: 220, align:'center', dataIndex: 'dalei'},
			    {header: '班级',  width: 250, align:'center', dataIndex: 'classes'},
			    {header: '联系电话',  width: 220, align:'center', dataIndex: 'phone'}
			]
        }),
        
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
		loadMask : true,	//加载时的遮罩
		frame : true,
		region:"center",
        iconCls:'menu-22',
        tbar:['->',{
        	text:'导出已报名名单',
        	iconCls:'btn-add',
        	handler: function(){
        	  Ext.MessageBox.confirm('信息提示', '确定要导出报名名单吗？', function(c) {
         	   if(c=='yes'){
         		   window.location.href="/MyStock/exportStudent.do?xmid="+xmid;
         	        store.reload();
         	     }
      		});
        	}
        },'-',{
        	text:'导出报名表模板',
        	iconCls:'btn-add',
        	handler: function(){
      	  Ext.MessageBox.confirm('信息提示', '确定要导出报名表模板吗？', function(c) {
        	   if(c=='yes'){
        		   window.location.href="/MyStock/exportStudent.do?ifApproval=1&xmid="+xmid;
        	        store.reload();
        	     }
     		});
       	}
        }],
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

    
  
     //获取项目数据
	var getModle = function(){
		Ext.Ajax.request({
   			url : "/MyStock/offlineFd_findSingOfflineFd.do",
   			params:{xmid:xmid
			     },
   			success : function(o) {
			   // debugger;
				var data = Ext.util.JSON.decode(o.responseText);
   				if(o.responseText){//o.responseText  xmid
   					searchForm.getForm().findField("fxxm").setValue(data.data.fxxm);
   					searchForm.getForm().findField("fxtime").setValue(data.data.fxtime);
   					searchForm.getForm().findField("fxteacher").setValue(data.data.fxteacher);
   					searchForm.getForm().findField("teachertel").setValue(data.data.teachertel);
   					searchForm.getForm().findField("xmintro").setValue(data.data.xmintro);
   					searchForm.getForm().findField("xmzise").setValue(data.data.xmzise);
   					searchForm.getForm().findField("fxaddress").setValue(data.data.fxaddress);
   					searchForm.getForm().findField("xmxh").setValue(data.data.xmxh);
   				}
   			}
   		});
	};

    
    
    
   
    
    
    //布局
    new Ext.Viewport({
		layout:"fit",
		items:[{
			frame:true,
			title:"项目管理",
			iconCls:'menu-22',
			layout:"border",
			items:[searchForm,grid]
		}],
		listeners:{
			render:function(){
    	      getModle();
			}
		}
	});
    
   

});