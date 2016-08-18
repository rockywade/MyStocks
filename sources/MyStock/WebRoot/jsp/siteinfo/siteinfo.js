 function showImg(value){
	return "<img src='"+value+"' height='50'/>";
}


/**
 * 我申请的场地
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
			title:"编辑设置",
			padding:"0 5 0 5",
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
						cls :'returnBtn',
						text:'返回',
						 listeners : {
							  click : function (btn) {
						        window.location.href = 'siteinfoApproval.jsp';   
							}
				        		
				        }
					}]
				}]
			}]
		}]
		
		
	});
	
	
	var SiteInfoLogObj = [
	              		{ name:'logId', type:'string'},
	              		{ name:'xh', type:'string'},
	              		{ name:'proposer', type:'string'},
	              		{ name:'siteid', type:'string'},
	                    { name:'relationtel', type:'string'},
	                    { name:'counsellor', type:'string'},
	                    { name:'activityname', type:'string'},
	                    { name:'activitycontent', type:'string'},
	                  	{ name:'activitydate', type:'string' },
	                  	{ name:'activitytime', type:'string' },
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
	              		{ name:'dutystate', type:'string'},
	              		{ name:'agreement', type:'string'},
	              		{ name:'applyTime', type:'string'},
	              		{ name:'status', type:'string'}
	              		
	              	];
	
	var store = new Ext.data.JsonStore({
	    url: 'SiteInfo_findPageSiteInfoLog.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: {params:{start:0, limit:15,ifApproval:1}},
	    fields: SiteInfoLogObj
	});
	
	//new Ext.grid.RowNumberer(),：序号使用
	var sm = new Ext.grid.CheckboxSelectionModel({singleSelect: true});//单选
    var grid = new Ext.grid.GridPanel({
    	id:'gridModle',
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[new Ext.grid.RowNumberer(),
			            {header: '场地',  width: 150, align:'center', dataIndex: 'sitename'},
			            {header: '使用时间',  width: 200, align:'center', dataIndex: 'usetime'},
			            {header: '活动名称',  width: 150, align:'center', dataIndex: 'activityname'},
			            {header: '所在组织',  width: 150, align:'center', dataIndex: 'beorganize'},
			            {header: '指导老师',  width: 150, align:'center', dataIndex: 'guideth'},
			            {header: '规模', width: 100, align:'center', dataIndex: 'sitemodle'},
			            {header: '场地管理老师',width: 150, align:'center', dataIndex: 'counsellor'},
			            {header: '状态',width: 150, align:'center', dataIndex: 'status',
			            	renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
				               if(value == '待审核') { 
			                        return "<span style='color:blue;font-weight:bold;'>待审核</span>"
			                   }     
			                   if(value == '已通过') { 
			                      return "<span style='color:green;font-weight:bold;'>已通过</span>"
			                   } 
			                   if(value == '未通过') { 
				                      return "<span style='color:red;font-weight:bold;'>未通过</span>"
				                }  
			                   if(value == '已取消') { 
				                      return "<span style='color:black;font-weight:bold;'>已取消</span>"
				                }   
			                 }
			            },
			            {xtype: 'actioncolumn',header : '操作', width: 200,align : 'center',
			            	menuDisabled : true,
			                items: [ {
			                    icon: '../../img/btn/print.png',
			                    tooltip: '打印',
			                    getClass: function(value, metaData, record, rowIndex, colIndex, store, view) {      
			                		if('待审核'==record.data.status||'未通过'==record.data.status ||
			                				'已取消'==record.data.status ||'不对外开放'==record.data.status){
			                              return 'x-hidden'
			                        }
			                	},
			                    handler: function(grid, rowIndex, colIndex){
			                		var newWindow=window.open("打印窗口","",'left=250,top=150,width=1000,height=600,toolbar=no,menubar=no,status=no,scrollbars=yes,resizable=yes');//打印窗口要换成页面的url
			                		var record = grid.getStore().getAt(rowIndex);
			                		
			                		var docStr = '<html><head><meta charset="utf-8"><title>场地申请打印</title></head><body>';
			                		
			                		 docStr +='<div style="width: 850px; height:600px;margin:20px auto;" id="printDiv">';
			                		 docStr +='<table style="width: 840px; height:500px;" cellspacing="0" cellpadding="2px" border="1" bordercolor="black">';
			                		 docStr += ' <thead>';
			                		 docStr += ' <tr>';
			                		 docStr += ' <th colspan="7"  style="text-align: center;height:40px;">';
			                		 docStr += ' 场地申请';       
			                		 docStr += ' </th>';
			                		 docStr += ' </tr>';
			                		 docStr += ' </thead>';
			                		 docStr += '<tbody>';
			                		 docStr += '<tr>';
			                		 docStr += '<th style="text-align: right">';
			                		 docStr += '申请场地:';     
			                		 docStr += '</th>';  
			                		 docStr += '<td style="width: 250px;text-align: center">'+record.data.sitename+'</td>';
			                		 docStr += '<th style="text-align: right">';
			                		 docStr += '会议室:';     
			                		 docStr += '</th>';  
			                		 docStr += '<td style="width: 250px;text-align: center">'+record.data.sitecondition+'</td>';
			                		 docStr += '</tr>';
			                		 docStr += '<tr>';
			                		 docStr += '<th style="text-align: right">';
			                		 docStr += '申请人:';  
			                		 docStr += '</th>';
			                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.proposer+'</td>';
			                		 docStr += '<th style="text-align: right">';
			                		 docStr += '学号:';  
			                		 docStr += '</th>';
			                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.xh+'</td>';
			                		 docStr += '</tr>';
			                		 docStr += '<tr>';
			                		 docStr += '<th style="text-align: right">';
			                		 docStr += '联系电话:';  
			                		 docStr += '</th>';
			                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.relationtel+'</td>';
			                		 docStr += '<th style="text-align: right">';
			                		 docStr += '辅导员:';  
			                		 docStr += '</th>';
			                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.counsellor+'</td>';
			                		 docStr += '</tr>';
			                		 docStr += '<tr>';
			                		 docStr += '<th style="text-align: right">';
			                		 docStr += '活动名称:';  
			                		 docStr += '</th>';
			                		 docStr += '<td style="width: 150px;text-align: center" colspan="3">';
			                		 docStr += record.data.activityname;
			                		 docStr += '</td>';
			                		 docStr += '</tr>';
			                		 docStr += '<tr>';
			                		 docStr += '<th style="text-align: right">';
			                		 docStr += '活动内容:';  
			                		 docStr += '</th>';
			                		 docStr += '<td style="width: 150px;text-align: center" colspan="3">';
			                		 docStr += record.data.activitycontent;
			                		 docStr += '</td>';
			                		 docStr += '</tr>';
			                		 docStr += '<tr>';
			                		 docStr += '<th style="text-align: right">';
			                		 docStr += '活动日期:';  
			                		 docStr += '</th>';
			                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.activitydate+'</td>';
			                		 docStr += '<th style="text-align: right">';
			                		 docStr += '活动时间:';  
			                		 docStr += '</th>';
			                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.activitytime+'</td>';
			                		 docStr += '</tr>';
			                		 docStr += '<tr>';
			                		 docStr += '<th style="text-align: right">';
			                		 docStr += '活动类型:';  
			                		 docStr += '</th>';
			                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.activitytype+'</td>';
			                		 docStr += '<th style="text-align: right">';
			                		 docStr += '所在组织:';  
			                		 docStr += '</th>';
			                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.beorganize+'</td>';
			                		 docStr += '</tr>';
			                		 docStr += '<tr>';
			                		 docStr += '<th style="text-align: right">';
			                		 docStr += '指导老师:';  
			                		 docStr += '</th>';
			                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.guideth+'</td>';
			                		 docStr += '<th style="text-align: right">';
			                		 docStr += '规模(人):';  
			                		 docStr += '</th>';
			                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.sitemodle+'</td>';
			                	     docStr += '</tr>';
			                	     docStr += '<tr>';
			                		 docStr += '<th style="text-align: right">';
			                		 docStr += '是否是外校:';  
			                		 docStr += '</th>';
			                		 docStr += '<td style="width: 150px;text-align: center">';
			                		 docStr += record.data.ifschool;
			                		 docStr += '</td>';
			                		 docStr += '<th style="text-align: right">';
			                		 docStr += '审核情况:';  
			                		 docStr += '</th>';
			                		 docStr += '<td style="width: 150px;text-align: center">';
			                		 docStr += '已同意';
			                		 docStr += '</td>';
			                	     docStr += '</tr>';
			                	     docStr += '<tr>';
			                		 docStr += '<th style="text-align: right">';
			                		 docStr += '签字/盖章:';  
			                		 docStr += '</th>';
			                		 docStr += '<td style="width: 150px;text-align: center" colspan="3">';
			                		 docStr += '</td>';
			                		 docStr += '</tr>';
			                	     docStr += '</tbody>';
			                		 docStr +='</table>';
			                		 docStr +='</div>';
			                		 docStr +='</body></html>';
			                	    newWindow.document.write(docStr);
			                	    newWindow.document.close();
			                	    newWindow.print();
			                	    newWindow.close();
			                	}
			                },{
			                    icon: '../../img/btn/cancel.png',
			                    tooltip: '取消',
			                    getClass: function(value, metaData, record, rowIndex, colIndex, store, view) {      
			                		if('已通过'==record.data.status ||'未通过'==record.data.status ||
			                				'已取消'==record.data.status ||'不对外开放'==record.data.status){
			                              return 'x-hidden'
			                        }
			                	},
			                	handler: function(grid, rowIndex, colIndex){
			                		 undo(grid, rowIndex, colIndex);
			                	}
			                }]
			           } 
			         ]
        }),
        sm:sm,//checkbox
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
        //autoExpandColumn: 'gysbz', //自动扩展列
		loadMask : true,	//加载时的遮罩
		frame : true,
		region:"center",
	    iconCls:'menu-12',
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

     
    //取消的方法
    var undo = function (grid, rowIndex, colIndex){
    	var record = grid.getStore().getAt(rowIndex); 
    	Ext.MessageBox.confirm('取消提示', '是否取消该场地？', function(c) {
    	   if(c=='yes'){
 		   Ext.Ajax.request({
			   	url : "SiteInfo_saveOrUpdateLog.do",
			   	params:{
 			     ifApproval:"2",
				 logId :record.data.logId,
				 xh:record.data.xh,
				 proposer:record.data.proposer,
				 siteid:record.data.siteid,
				 relationtel:record.data.relationtel,
				 counsellor:record.data.counsellor,
				 activityname:record.data.activityname,
				 activitycontent:record.data.activitycontent,
				 activitydate:record.data.activitydate,
				 activitytime:record.data.activitytime,
				 activitytype:record.data.activitytype,
				 sitename:record.data.sitename,
				 sitetype:record.data.sitetype,
				 beorganize:record.data.beorganize,
				 guideth:record.data.guideth,
				 sitemodle:record.data.sitemodle,
				 park:record.data.park,
				 nowstatus:record.data.nowstatus,
				 nowstatus:record.data.nowstatus,
				 sitecondition:record.data.sitecondition,
				 usetime:record.data.usetime,
				 ifschool:record.data.ifschool,
				 agreement:record.data.agreement,
				 dutystate:record.data.dutystate,
				 applyTime:record.data.applyTime,
				 status:record.data.status
				 }, 
				 success : function(data) {
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
			title:"我申请的场地",
			iconCls:'menu-12',
			layout:"border",
			items:[searchForm,grid]
		}]
		
	});
    
  
    
    
    
});
   
