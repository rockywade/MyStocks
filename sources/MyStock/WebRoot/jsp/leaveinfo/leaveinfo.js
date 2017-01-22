/*!
 * 请假信息申请列表
 */

function showImg(value){
	return "<img src='"+value+"'  id='imgsrc' height='50'/>";
}

 var stater;
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
					width:150,
					height: 30,
					items:[{
						width:100,
						xtype:"button",
						iconCls:'btn-add',
						text:'请假申请',
						 listeners : {
							  click : function (btn) {
						        addWindow.show();
		        		        addForm.getForm().reset();
							}
				        		
				        }
					}]
				},{
					width:100,
					height: 30,
					items:[{
						width:75,
						xtype:"button",
						cls :'returnBtn',
						text:'返回',
						 listeners : {
							  click : function (btn) {
						        window.location.href = '../menu/personal-affairs.html';   
							}
				        		
				        }
					}]
				}]
			}]
		}]
		
		
	});
	
	
	var LeaveInfoObj = [
	            		{ name:'id', type:'int'},
	            		{ name:'leavereason', type:'string'},
	            		{ name:'classcode', type:'string'},
	                    { name:'studentname', type:'string'},
	                    { name:'studentnum', type:'string'},
	                    { name:'major', type:'string'},
	                    { name:'bedroom', type:'string'},
	                    { name:'relationtel', type:'string'},
	                    { name:'classth', type:'string'},
	                    { name:'counsellor', type:'string'},
	                    { name:'parentstel', type:'string'},
	            		{ name:'leavetime', type:'string' },
	            		{ name:'backsctime', type:'string'},
	            		{ name:'daysum', type:'int'},
	            		{ name:'image', type:'string'},
	            		{ name:'parentsinfo', type:'string'},
	            		{ name:'rulesstate', type:'string'},
	            		{ name:'tutorstatus', type:'string'},
	            		{ name:'checksopinion', type:'string'},
	            		{ name:'schoolstatus', type:'string'},
	            		{ name:'checksopinion', type:'string'},
	            		{ name:'status', type:'string'}  
	            		
	            	];
	
	
	var store = new Ext.data.JsonStore({
	    url: '/MyStock/LeaveInfo_findPageLeaveInfo1.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: {params:{start:0, limit:15,ifApproval:1}},
	    fields: LeaveInfoObj
	});
	
	//new Ext.grid.RowNumberer(),：序号使用
	var sm = new Ext.grid.CheckboxSelectionModel({singleSelect: true});//单选
    var grid = new Ext.grid.GridPanel({
    	id:'gridModle',
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[new Ext.grid.RowNumberer(),
			            {header: '请假事由',  width: 300, align:'center', dataIndex: 'leavereason'},
			            {header: '离校时间',  width: 150, align:'center', dataIndex: 'leavetime'},
			            {header: '返校时间',  width: 150, align:'center', dataIndex: 'backsctime'},
			            {header: '请假天数',  width: 120, align:'center', dataIndex: 'daysum'},
			            {header: '父母知情',  width: 100, align:'center', dataIndex: 'parentsinfo'},
			            {header: '辅导员审核状态', width: 100, align:'center', dataIndex: 'tutorstatus',renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
		                	  if(value == '待审核') { 
			                        return "<span style='color:blue;font-weight:bold;'>待审核</span>"
			                   }     
			                   if(value == '已同意') { 
			                      return "<span style='color:green;font-weight:bold;'>已同意</span>"
			                   } 
			                   if(value == '未同意') { 
				                      return "<span style='color:red;font-weight:bold;'>未同意</span>"
				                }   
			                 }
		                 },
			            {header: '学园领导审核状态',width: 100, align:'center', dataIndex: 'schoolstatus',
			            	renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
				               if(value == '待审核') { 
			                        return "<span style='color:blue;font-weight:bold;'>待审核</span>"
			                   }     
			                   if(value == '已同意') { 
			                      return "<span style='color:green;font-weight:bold;'>已同意</span>"
			                   } 
			                   if(value == '未同意') { 
				                      return "<span style='color:red;font-weight:bold;'>未同意</span>"
				                }  
			                   if(value == '无') { 
				                      return "<span style='color:black;font-weight:bold;'>无</span>"
				                }   
			                 }},
			            {header: '医院证明', width: 150,align:'center', dataIndex: 'image',renderer:showImg},      
			            {xtype: 'actioncolumn',header : '操作', width: 200,align : 'center',
			            	menuDisabled : true,
			                items: [ {
			                    icon: '../../img/btn/print.png',
			                    tooltip: '打印',
			                    getClass: function(value, metaData, record, rowIndex, colIndex, store, view) {      
			                	  if(record.data.tutorstatus == '已同意' && record.data.daysum >=30){
			                			if('待审核'==record.data.tutorstatus||'未同意'==record.data.tutorstatus ||
				                				'已取消'==record.data.tutorstatus ||'待审核'==record.data.schoolstatus){
				                              return 'x-hidden'
				                        }
			                		}else{
			                			if('待审核'==record.data.tutorstatus||'未同意'==record.data.tutorstatus ||
				                				'已取消'==record.data.tutorstatus ){
				                              return 'x-hidden'
				                        }
			                		}
			                		
			                	},
			                    handler: function(grid, rowIndex, colIndex){
			                		var newWindow = window.open("打印窗口", "", 'left=250,top=150,width=950,height=500,toolbar=no,menubar=no,status=no,scrollbars=yes,resizable=yes');
			                		var record = grid.getStore().getAt(rowIndex);
			                		 var docStr = '<html><head><meta charset="utf-8"><title>请假条打印</title></head><body>';
			                		 
			                		 docStr +='<div style="width: 850px; height:600px;margin:20px auto;" id="printDiv">';
			                		 docStr +='<table style="width: 840px; height:500px;" cellspacing="0" cellpadding="2px" border="1" bordercolor="black">';
			                		 docStr += ' <thead>';
			                		 docStr += ' <tr>';
			                		 docStr += ' <th colspan="7"  style="text-align: center;height:40px;">';
			                		 docStr += ' 请假条';       
			                		 docStr += ' </th>';
			                		 docStr += ' </tr>';
			                		 docStr += ' </thead>';
			                		 docStr += '<tbody>';
			                		 docStr += '<tr>';
			                		 docStr += '<th style="text-align: right">';
			                		 docStr += '姓名:';     
			                		 docStr += '</th>';  
			                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.studentname+'</td>';
			                		 docStr += '<th style="text-align: right">';
			                		 docStr += '学号:';     
			                		 docStr += '</th>';  
			                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.studentnum+'</td>';
			                		 docStr += '</tr>';
			                		 docStr += '<tr>';
			                		 docStr += '<th style="text-align: right">';
			                		 docStr += '行政班级:';  
			                		 docStr += '</th>';
			                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.classcode+'</td>';
			                		 docStr += '<th style="text-align: right">';
			                		 docStr += '专业:';  
			                		 docStr += '</th>';
			                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.major+'</td>';
			                		 docStr += '</tr>';
			                		 docStr += '<tr>';
			                		 docStr += '<th style="text-align: right">';
			                		 docStr += '寝室:';  
			                		 docStr += '</th>';
			                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.bedroom+'</td>';
			                		 docStr += '<th style="text-align: right">';
			                		 docStr += '联系电话:';  
			                		 docStr += '</th>';
			                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.relationtel+'</td>';
			                		 docStr += '</tr>';
			                		 docStr += '<tr>';
			                		 docStr += '<th style="text-align: right">';
			                		 docStr += '班主任:';  
			                		 docStr += '</th>';
			                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.classth+'</td>';
			                		 docStr += '<th style="text-align: right">';
			                		 docStr += '辅导员:';  
			                		 docStr += '</th>';
			                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.counsellor+'</td>';
			                		 docStr += '</tr>';
			                		 docStr += '<tr>';
			                		 docStr += '<th style="text-align: right">';
			                		 docStr += '请假事由:';  
			                		 docStr += '</th>';
			                		 docStr += '<td style="width: 150px;text-align: center" colspan="3">';
			                		 docStr += record.data.leavereason;
			                		 docStr += '</td>';
			                		 docStr += '</tr>';
			                		 docStr += '<tr>';
			                		 docStr += '<th style="text-align: right">';
			                		 docStr += '离校时间:';  
			                		 docStr += '</th>';
			                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.leavetime+'</td>';
			                		 docStr += '<th style="text-align: right">';
			                		 docStr += '返校时间:';  
			                		 docStr += '</th>';
			                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.backsctime+'</td>';
			                		 docStr += '</tr>';
			                		 docStr += '<tr>';
			                		 docStr += '<th style="text-align: right">';
			                		 docStr += '父母知情:';  
			                		 docStr += '</th>';
			                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.parentsinfo+'</td>';
			                		 docStr += '<th style="text-align: right">';
			                		 docStr += '父母电话:';  
			                		 docStr += '</th>';
			                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.parentstel+'</td>';
			                		 docStr += '</tr>';
			                		 docStr += '<tr>';
			                		 docStr += '<th style="text-align: right">';
			                		 docStr += '阅读情况:';  
			                		 docStr += '</th>';
			                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.rulesstate+'</td>';
			                		 docStr += '<th style="text-align: right">';
			                		 docStr += '审批情况:';  
			                		 docStr += '</th>';
			                		 docStr += '<td style="width: 150px;text-align: center">'+record.data.tutorstatus+'</td>';
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
			                	  if(record.data.tutorstatus == '已同意' && record.data.daysum >=30){
			                		  if( '已同意'==record.data.schoolstatus || '未同意'==record.data.schoolstatus ){
			                              return 'x-hidden'
			                         }
			                		  
			                	  }else{
			                		  if('已同意'==record.data.tutorstatus ||'未同意'==record.data.tutorstatus ||'已取消'==record.data.tutorstatus){
			                              return 'x-hidden'
			                        }
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
	    iconCls:'menu-21',
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

  
    //请假申获取登录的数据
	var getModle = function(){
		Ext.Ajax.request({
   			url : "/MyStock/LeaveInfo_goAddOrUpateLeaveInfo.do",
   			success : function(o) {
			   // debugger;
				var data = Ext.util.JSON.decode(o.responseText);
   				if(o.responseText){//o.responseText
   					addForm.getForm().findField("studentname").setValue(data.data.xm);
   					addForm.getForm().findField("studentnum").setValue(data.data.xh);
   					addForm.getForm().findField("classcode").setValue(data.data.bjdm);
   					addForm.getForm().findField("bedroom").setValue(data.data.qsmc);
   					addForm.getForm().findField("relationtel").setValue(data.data.phone);
   					addForm.getForm().findField("classth").setValue(data.data.headmaster);
   					addForm.getForm().findField("counsellor").setValue(data.data.instructor);
   				}
   			}
   		});
	};
   
    
    
	 //请假
    var addForm = new Ext.FormPanel({
		layout : 'form',
		fileUpload:true,
		frame:true,
		labelWidth:60,
		border : false,
		padding : '15 10 0 8',
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
					name:'studentname',
					id:'studentname',
					fieldLabel:'姓<i class="ikg2"></i>名',
					readOnly:true,
					allowBlank : false,
					maxLength :20
				},{
					xtype : 'textfield',
					name:'classcode',
					fieldLabel:'行政班级',
					readOnly:true,
					allowBlank : false,
					maxLength :20
				},{
					xtype : 'textfield',
					name:'classth',
					fieldLabel:'班<i class="ikg1"></i>主<i class="ikg1"></i>任',
					readOnly:true,
					allowBlank : false,
					maxLength :10
				}]
			},{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype : 'textfield',
					name:'studentnum',
					fieldLabel:'学<i class="ikg2"></i>号',
					readOnly:true,
					allowBlank : false,
					maxLength :10
				},{ xtype : 'textfield',
					name:'relationtel',
					fieldLabel:'联系电话',
					readOnly:true,
					allowBlank : false,
					regex:/^(13\d|15[^4,\D]|17[13678]|18\d)\d{8}|170[^346,\D]\d{7}$/,
					maxLength :12
				},{
					xtype : 'textfield',
					name:'counsellor',
					fieldLabel:'辅<i class="ikg1"></i>导<i class="ikg1"></i>员',
					readOnly:true,
					allowBlank : false,
					maxLength :10
				}]
			}]
		},{
			xtype : 'textfield',
			name:'bedroom',
			fieldLabel:'寝<i class="ikg2"></i>室',
			readOnly:true,
			allowBlank : false,
			anchor: '97.5%',
			maxLength :20
		},{
			xtype:'textarea',
			name:'leavereason',
			fieldLabel:'请假事由',
			allowBlank : false,
			height:100,
			anchor: '97.5%',
			maxLength :200
		},{
			layout:'column',
			items:[{
				columnWidth:.5,
				layout:'form',
				/*defaults:{
					anchor : '95%'
				},*/
				items:[{
					//xtype : 'textfield',
					id:'timeId',
					name:'leavetime',
					xtype:"datetimefield",
					fieldLabel:'离校时间',
					format: 'Y-m-d H:i:s ',
					minValue : new Date(),
					editable:false,
					allowBlank : false,
					maxLength :20,
					width: 150,
				    vtype : 'daterange',//daterange类型为上代码定义的类型
		            endDateField : 'overtimeId',//必须跟endDate的id名相同
				},{
					xtype: 'radiogroup',  
					id   : 'atype',
					name:'parentsinfo',
					fieldLabel: "父母知情",  
					items : [{  
					  boxLabel: '是',  
					  name: 'parentsinfo',  
					  inputValue:'是',  
					  checked : true  
					  },{  
					  boxLabel: '否',  
					  name: 'parentsinfo',  
					  inputValue:'否'  
					 }]  
					
				}]
			},{
				columnWidth:.5,
				layout:'form',
				/*defaults:{
					anchor : '95%'
				},*/
				items:[{
					id:'overtimeId',
					//xtype : 'textfield',
					name:'backsctime',
					xtype:"datetimefield",
					fieldLabel:'返校时间',
					format: 'Y-m-d H:i:s ',
					minValue : new Date(),
					allowBlank : false,
					editable:false,
					maxLength :20,
					width: 150,
				    vtype : 'daterange',//daterange类型为上代码定义的类型
		            startDateField : 'timeId',//必须跟startDate的id名相同
				},{xtype : 'textfield',
					name:'parentstel',
					fieldLabel:'父母电话',
					allowBlank : false,
					regex:/^(13\d|15[^4,\D]|17[13678]|18\d)\d{8}|170[^346,\D]\d{7}$/,
					maxLength :11
				}]
			}]
		},{
			xtype:'textfield',
			name:'image',
			id:'id_image',
			fieldLabel:'医院证明',
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
		   fieldLabel : '', 
           name: "rulesstate",
           html: '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="rulesstate"  id="rulesstate" type="checkbox" value="on" checked="true" />&nbsp;&nbsp;我已阅读&nbsp;<a href="javascript:void(0)"  onclick="stater()" class="color">《学生请假说明》</a>'
           
		},{
			xtype : 'hidden',
		    name : 'id'
		}]
	});
    
    
    
    //学生请假说明
    stater = function(){
      explainWindow.show();
   }
   
   
   //学生请假说明
   var explain = '';
       explain +='1.不能按期入学：因故不能按期办理入学手续者，应以\r\n';
       explain +='书面形式并附有关证明向学校本科生院学籍管理办公室请假，\r\n';
       explain +='请假一般不得超过2周。未请假或请假逾期者，除因不可抗力\r\n';
       explain +='等正当理由以外，视为放弃入学资格。\r\n';
       explain +='2.不能如期注册：因故不能如期注册者，应办理暂缓注\r\n';
       explain +='册或请假手续，否则以旷课论处。未请假或请假逾期者，按自\r\n';
       explain +='动退学处理。\r\n';
       explain +='册或请假手续，否则以旷课论处。未请假或请假逾期者，按自\r\n';
       explain +='3.不能按时上课：学校采用适当方式对学生参加课程的\r\n';
       explain +='学习实行考勤。学生无法参加时，须事先办理请假手续；未办\r\n';
       explain +='理请假手续者，按旷课论处。\r\n';
       explain +='4. 学生请假应事先提出书面申请。因病请假须附校医院\r\n';
       explain +='证明。请假一周以内由班主任审批（校外教学活动由带队教师\r\n';
       explain +='审批），院（系）本科教育科（或学园办公室）备案；1周以\r\n';
       explain +='上，1个月以内由院（系）教学负责人（或学园负责人）审\r\n';
       explain +='批，院（系）本科教育科（或学园办公室）备案；请假1个月\r\n';
       explain +='以上由院（系）教学负责人（或学园负责人）签署意见，报本\r\n';
       explain +='科生院学籍管理办公室审批备案。';
       
      
       //学生请假说明  x: 100,  y: 50,
       var explainForm = new Ext.FormPanel({
   		layout : 'form',
   		frame:true,
   		labelWidth:40,
   		border : false,
   	   bodyStyle: "padding:5 5 5 5",
   		defaults : {
   			anchor : '100%'
   		},
   		items:[{
   			layout:'column',
   			items:[{
   				columnWidth:.5,
   				layout:'form',
   				defaults:{
   					anchor : '100%'
   				}
   				
   			}]
   		},{
   			xtype:'textarea',
   			id:'agreementto',
   			readOnly:true,
   			anchor: '97.5%',
   			value:explain,
   			style:'border:0',
   			height:300
   		}]
   	});
       
   
     //学生请假说明
      var explainWindow = new Ext.Window({
   	   title: '学生请假说明',
       	width:450,
   		height:350,
   		closeAction:'hide',
   		modal : true,
   		autoScroll: true, 
   		layout : 'fit',
   		buttonAlign : 'center',
   		items : [explainForm]
       });
  
    
      var addWindow = new Ext.Window({
      	title: '请假申请',
      	id:'addWin',
  		width:700,
  		height:400,
  		closeAction:'hide',
  		modal : true,
  		layout : 'fit',
  		buttonAlign : 'center',
  		items : [addForm],
  	 	buttons : [ {
  			text : '取消',
  			handler : function() {
  				addWindow.hide();
  			}
  		},{
  			text : '提交',
  			handler : function() {
  				if (addForm.getForm().isValid()) {
  					addForm.getForm().submit({
  						url : '/MyStock/LeaveInfo_saveLeaveInfo.do',
  						success : function(form, action) {
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
      
      
      var checkFile = function(){  
          //取控件DOM对象   
          var field = document.getElementById('id_image');  
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
      

      //取消的方法
      var undo = function (grid, rowIndex, colIndex){
      	var record = grid.getStore().getAt(rowIndex); 
      	Ext.MessageBox.confirm('取消提示', '是否取消该请假？', function(c) {
      	   if(c=='yes'){
   		   Ext.Ajax.request({
  				  url : "/MyStock/LeaveInfo_deleteLeaveInfo.do",
  				  params:{
  					    id : record.data.id
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
			title:"学生请假管理",
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
    
  
    //时间控件开始时间小于结束时间
    Ext.apply(Ext.form.VTypes, {
        daterange : function(val, field) {
            var date = field.parseDate(val);
            if (!date) {
                return;
            }
            if (field.startDateField
                    && (!this.dateRangeMax || (date.getTime() != this.dateRangeMax
                            .getTime()))) {
                var start = Ext.getCmp(field.startDateField);
                start.setMaxValue(date);
                this.dateRangeMax = date;
                start.validate();
            } else if (field.endDateField
                    && (!this.dateRangeMin || (date.getTime() != this.dateRangeMin
                            .getTime()))) {
                var end = Ext.getCmp(field.endDateField);
                end.setMinValue(date);
                this.dateRangeMin = date;
                end.validate();
            }

            return true;
        }
    });
    
    
    
});
   
