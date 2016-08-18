/**
 * 项目评论
 */
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	//查询表单
	var searchForm = new Ext.FormPanel({
		region:"north",
		height: 80,
		border : false,
		layout : "form",
		padding : "5 20 5 20",
		items:[{
			xtype:"fieldset",
			padding:"10 20 5 15",
			items:[{
				layout:"column",
				defaults:{
					xtype:"container",
					autoEl:"div",
					labelAlign:"right",
					layout:"form"
				},
				items:[{
					width:100,
					height: 30,
					items:[{
						width:75,
						xtype:"button",
						text:'返回',
						cls :'returnBtn',
						 listeners : {
							  click : function (btn) {
						       window.location.href = 'offlineFdStudent.jsp';
							}
				        		
				        }
					}]
				}]
			}]
		}]
		
	});
	
	var offlinefdLogObj = [
	    	           { name:'xmid', type:'string'},
	    	           { name:'bmId', type:'string'},
	    	           { name:'xmxh', type:'string'},
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
	    	           { name:'plnr', type:'string'},
	    	           { name:'plnrTime', type:'string'},
	    	           { name:'tbTag', type:'string'},
	    	           { name:'bmTag', type:'string'},
	    	           { name:'creatername', type:'string'}
	    	       ];
	    
	
	
	var store = new Ext.data.JsonStore({
		 url: 'offlineFd_findPl.do',
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
			columns:[
			    {header: '评论人',  width: 150, align:'center', dataIndex: 'bmName'},
			    {header: '评论内容',  width: 300, align:'center', dataIndex: 'plnr',renderer: function(value, meta, record) {    
			         meta.attr = 'style="white-space:normal;"';     
			         return value;     
			    }  
			   },
			    {header: '评论时间',  width: 250, align:'center', dataIndex: 'plnrTime'}
	           
			]
        }),
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
		loadMask : true,	//加载时的遮罩
		frame : true,
		region:"center",
        iconCls:'menu-22',
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

    
    
    //布局
    new Ext.Viewport({
		layout:"fit",
		items:[{
			frame:true,
			title:"项目评论",
			iconCls:'menu-22',
			layout:"border",
			items:[searchForm,grid]
		}]
	});
    
   

});