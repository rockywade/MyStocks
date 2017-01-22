
/**
 * 二手书店
 */
Ext.onReady(function(){
	
	Ext.QuickTips.init();
   
	var SecondBookStoreJudgeObj = [
	   	            { name:'id', type:'Integer'},
	   	            { name:'content', type:'string'},
	   	            { name:'judger', type:'Integer'},
	   	            { name:'nickname', type:'string'},
	   	            { name:'judgetime', type:'string'},
	   	            { name:'selfFlg', type:'Integer'}
	   	        ];
	   	
	   	
	   	
	   	var store = new Ext.data.JsonStore({
	   		 id :"store",
	   		 url: '/MyStock/secondBookStoreJudge_findPageSecondBookStoreJudge.do?storeid='+document.getElementById("id").value,
	   		 root: 'root',
	   		 totalProperty: 'total',
	   		 autoLoad: {params:{start:0, limit:15}},
	   		 fields: SecondBookStoreJudgeObj
	   	});
	
	var grid = new Ext.grid.GridPanel({
		id:"grid",
		hideHeaders:true,
		renderTo:"gridDiv",
        store: store,
        width: "100%", 
        height: 250, 
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[new Ext.grid.RowNumberer(),
			    {header: '标示',  width: 100, align:'center', dataIndex: 'id', hidden : true},
			    {header: '评论人',  width: 200, align:'center', dataIndex: 'nickname'},
			    {header: '内容',  width: 100, align:'center', dataIndex: 'content'},
			    {xtype: 'actioncolumn',header : '操作', width: 200,align : 'center',
	            	menuDisabled : true,
	                items: [{
	                    icon: '../../img/btn/edit2.gif',
	                    tooltip: '删除',
	                    getClass: function(value, metaData, record, rowIndex, colIndex, store, view) {      
	                		if(0==record.data.selfFlg){
	                              return 'x-hidden'
	                        }
	                	},
	                	handler: function(grid, rowIndex, colIndex){
	                		deleteJudge(grid, rowIndex, colIndex);
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
        tbar:[],
       
        bbar: new Ext.PagingToolbar({
            pageSize: 15,
            store: store,
            displayInfo: true
        }) 
    });
	
    var deleteJudge = function(grid, rowIndex, colIndex){
    	var record = grid.getStore().getAt(rowIndex); 
    	Ext.MessageBox.confirm('删除提示', '是否删除该评论？', function(c) {
		   	Ext.Ajax.request({
		   			url : "/MyStock/secondBookStoreJudge_delete.do",
		   			params:{id : record.get("id")},
		   			success : function() {
		   				store.reload();
		   			}
		   		});
		    
		});
    };
	
	var bt = new Ext.Button({
	    renderTo: "buttonDiv",
	    text: "评论",
	    id: "bt",
	    scale: 'large'
	});
	
	bt.on("click", function(){
		Ext.Ajax.request({
   			url : "/MyStock/secondBookStoreJudge_saveOrUpdateSecondBookStoreJudge.do",
   			params:{
				qaid : document.getElementById("id").value,
				content : document.getElementById("judgeContent").value
			},
   			success : function() {
				Ext.Msg.alert('信息提示','评论成功');  
				Ext.getCmp('grid').store.reload();
   			}
   		});
	});

});

