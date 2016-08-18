Ext.BLANK_IMAGE_URL = '../../../ext/resources/images/default/s.gif';
/*!
 * 活动申请
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
			title:"个人假日去向记录",
			padding:"0 20 0 10",
			items:[{
				layout:"column",
				defaults:{
					xtype:"container",
					autoEl:"div",
					labelAlign:"right",
					layout:"form"
				},items:[{
					width:100,
					height: 30,
					items:[{
						id:'inputwheres',
						xtype:"button",
						text:'去向填写',
						width:100,
						handler:function(){
							addForm.getForm().reset();
							getNeedWriteWheres();
						}
					}]
				},{
					width:90,
					items:[{
						cls :'returnBtn',
						width:90,
						xtype:"button",
						text:"返<i class='ikg2'></i>回",
						handler:function(){
								   window.location.href="../../menu/personal-affairs.html";
						}
					}]
				}]
			}]
		}]
	});
	
	//fields类声明
	var Wheresaboutscensus = [
	        		{ name:'censusid', type:'string'},
	        		{ name:'censususernum', type:'string'},
	        		{ name:'censususername', type:'string'},
	        		{ name:'launchid', type:'string'},
	        		{ name:'launchname', type:'string'},
	        		{ name:'holidaytime', type:'string'},
	        		{ name:'classes', type:'string'},
	        		{ name:'mojar', type:'string'},
	        		{ name:'dorm', type:'string'},
	        		{ name:'userphone', type:'string'},
	        		{ name:'teacher', type:'string'},
	        		{ name:'counsellor', type:'string'},	//辅导员
	        		{ name:'wheresfact', type:'string'},	//去向实情
	        		{ name:'termini', type:'string'},		//目的地
	        		{ name:'urgentphone', type:'string'},
	        		{ name:'leaveschooltime', type:'string'},
	        		{ name:'returnschooltime', type:'string'},
	        		{ name:'daysnum',type:'string'},
	        		{ name:'parentsknows', type:'string'},
	        		{ name:'parentsphone', type:'string'},
	        		{ name:'enteragreement', type:'string'},
	        		{ name:'censusendtime', type:'string'},
	        	];
	
	//获取response数据
	var store = new Ext.data.JsonStore({
	    url: 'Whereabouts_personalWheresCensusList.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: {params:{start:0, limit:15}},
	    fields: Wheresaboutscensus,
	});
	
	//var sm = new Ext.grid.CheckboxSelectionModel();//单选{singleSelect: true}
	var rownumber = new Ext.grid.RowNumberer();//行号
    var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[rownumber,
			    {header: '假日', width: 120,align:'center', dataIndex: 'launchname'},
			    {header: '去向', width: 120,align:'center', dataIndex: 'wheresfact'},
			    {header: '假期时间', width: 240,align:'center', dataIndex: 'holidaytime'},
			    {header:'离校时间',width:130,align:'center',dataIndex:'leaveschooltime'},
			    {header:'返校时间',width:130,align:'center',dataIndex:'returnschooltime'},
	            {header: '天数', width: 100,align:'center', dataIndex: 'daysnum'},
	            {header: '父母知情', width: 100,align:'center', dataIndex: 'parentsknows'},
	            {xtype: 'actioncolumn',header : '操作', width: 100,align : 'center',menuDisabled : true,
	            	items: [{
	                    icon: '../../../img/btn/edits.png',
	                    //tooltip: '修改',
	                    getClass: function(value, metaData, record, rowIndex, colIndex, store, view) {
	            			//获取当前日期
		            		var date = new Date();
			            	var yyyy = date.getFullYear();
			            	var mm = date.getMonth()+1;
			            	var dd = date.getDate();
			            	var day = yyyy+"-"+mm+"-"+dd;
			            	
			            	//获取日期转换string
			            	var cd = record.data.censusendtime;
			        		var year = cd.split('')[0]+cd.split('')[1]+cd.split('')[2]+cd.split('')[3];
			        		var month = cd.split('')[5]+cd.split('')[6];
			        		var lday = cd.split('')[8]+cd.split('')[9];
			        		var censusendt = year+"-"+month+"-"+lday; 
			        		
			        		//当前日期转date
			        		var arr = day.split("-");
			        		var today = new Date(arr[0],arr[1],arr[2]);
			        		var todaytime = today.getTime();
			        		
			        		//获取日期转date
			        		var censusarr = censusendt.split("-");
			        		var censusday = new Date(censusarr[0],censusarr[1],censusarr[2]);
			        		var censusendtime = censusday.getTime();
			        		
	                		if(todaytime>censusendtime){
	                              return 'x-hidden'
	                        }
	                	},
	                    handler: function(grid, rowIndex, colIndex){
	                		edit(grid, rowIndex, colIndex);
	                	}
	                }]
	           }  
			]
        }),
        rownumber:rownumber,//行号
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
        //autoExpandColumn: 'gysbz', //自动扩展列
		loadMask : true,	//加载时的遮罩
		frame : true,
		region:"center",
        iconCls:'menu-51',
        
        bbar: new Ext.PagingToolbar({
            pageSize: 15,
            store: store,
            displayInfo: true
        }),
        viewConfig : {
        	forceFit: true,
        	scrollOffset: 2
    	}
    });
    
  //修改
	var edit = function(grid, rowIndex, colIndex){
    	var record = grid.getStore().getAt(rowIndex);
    	var str = record.get("holidaytime");
    	var start = str.substring(0,10);
    	var date = new Date(start);
    	var time = date.getTime()-24*60*60*1000;
    	var d = new Date(time);
    	var y = d.getFullYear();
    	var m = d.getMonth()+1;
    	if(m<10){
    		m = "0"+m;
    	}
    	var d1 = d.getDate();
    	if(d1<10){
    		d1 = "0"+d1;
    	}
    	var okDateStr= y + "-" + m + "-" + d1;
    	//alert(okDateStr);
    	Ext.getCmp("startTimeId").setMinValue(okDateStr);
    	addWindow.show();
    	addForm.getForm().loadRecord(record);
    };
    
  //下拉菜单
    //去向情况
    var wheretogo = new Ext.data.SimpleStore({
    	fields:['value','text'],
    	data:[['留校','留校'],['回家','回家'],['出游','出游'],['其他','其他']]
    });
    
    //时间验证
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
    
  //去向统计提交窗口
    var addForm = new Ext.FormPanel({
		layout : 'form',
		//baseCls:'x-plain',
		frame:true,
		url :'Whereabouts_whereAboutsCensus.do',
		labelWidth:56,
		border : false,
		padding : '15 10 0 10',
		defaults : {
			anchor : '100%'
		},
		items:[{
			xtype : 'textfield',
			name:'launchname',
			fieldLabel:'假<i class="ikg2"></i>日',
			readOnly:true,
			allowBlank : false,
			anchor : '97.5%',
			maxLength :20
		},{
			xtype : 'textfield',
			name:'holidaytime',
			fieldLabel:'假日时间',
			readOnly:true,
			allowBlank : false,
			anchor : '97.5%',
			maxLength :45
		},{
			layout:'column',
			items:[{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype : 'textfield',
					name:'censususername',
					fieldLabel:'姓<i class="ikg2"></i>名',
					readOnly:true,
					allowBlank : false,
					maxLength :20
				},{
					xtype : 'textfield',
					name:'classes',
					readOnly:true,
					fieldLabel:'班<i class="ikg2"></i>级',
					allowBlank : false,
					maxLength :20
				},{
					xtype : 'textfield',
					name:'dorm',
					readOnly:true,
					fieldLabel:'寝<i class="ikg2"></i>室',
					allowBlank : false,
					maxLength :20
				},{
					xtype:'textfield',
					name:'teacher',
					fieldLabel:'班<i class="ikg1"></i>主<i class="ikg1"></i>任',
					allowBlank : false,
					readOnly:true,
					maxLength :20
				},{
					id:'factid',
					xtype:'combo',
					hiddenName:'wheresfact',
					fieldLabel:'去向情况',
					mode: 'local',
					triggerAction: 'all',
					valueField :'value',
					displayField: 'text',
					editable : false,
					store : wheretogo,
					listeners:{
						select : function(a,b){
							var v = Ext.getCmp("factid").getValue();
		    				if(v=="留校" || v=="回家"){
		    					Ext.getCmp("termid").hide();
		    					Ext.getCmp("termid").setValue(Ext.getCmp("factid").getValue());
		    					//alert(Ext.getCmp("termid").getValue());
		    				}else{
		    					Ext.getCmp("termid").reset();
		    					Ext.getCmp("termid").show();
		    					//Ext.getCmp('termid').getEl().dom.allowBlank=false;
		    				}
						}
					}
				}]
			},{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype : 'textfield',
					name:'censususernum',
					fieldLabel:'学<i class="ikg2"></i>号',
					readOnly:true,
					allowBlank : false,
					maxLength :10
				},{
					xtype:'textfield',
					name:'ssyq',
					fieldLabel:'园<i class="ikg2"></i>区',
					readOnly:true,
					allowBlank : false,
					maxLength :100
				},{ xtype : 'textfield',
					name:'userphone',
					readOnly:true,
					fieldLabel:'联系电话',
					allowBlank : false,
					maxLength :11
				},{
					xtype:'textfield',
					name:'counsellor',
					fieldLabel:'辅<i class="ikg1"></i>导<i class="ikg1"></i>员',
					allowBlank : false,
					readOnly:true,
					maxLength :30
				},{
					id:'termid',
					xtype :"textfield",
					name:'termini',
					fieldLabel:'目<i class="ikg1"></i>的<i class="ikg1"></i>地',
					allowBlank : false,
					maxLength :10
				}]
			}]
		},{
			xtype:'numberfield',
			name:'urgentphone',
			fieldLabel:'紧急电话',
			allowBlank : false,
			anchor : '97.5%',
			maxLength :11,
			regex:/^(13\d|15[^4,\D]|17[13678]|18\d)\d{8}|170[^346,\D]\d{7}$/
		},{
			layout:'column',
			items:[{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					id:'startTimeId',
					xtype : 'textfield',
					name:'leaveschooltime',
					xtype:"datefield",
					fieldLabel:'离校时间',
					format:'Y-m-d',
					allowBlank : false,
					editable : false,
					maxLength :20,
					vtype : 'daterange',//调用时间验证方法
		            endDateField: 'endTimeId',//限制结束时间id
		            //showToday:false 
				},{
					xtype : 'radiogroup',
					id   : 'atype',
					name:'parentsknows',
					fieldLabel: "父母知情",  
					items : [{  
					  boxLabel: '是',  
					  name: 'parentsknows',  
					  inputValue:'是',  
					  },{  
					  boxLabel: '否',  
					  name: 'parentsknows',  
					  inputValue:'否',
					  checked : true
					 }]  
					
				}]
			},{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					id:'endTimeId',
					xtype : 'textfield',
					name:'returnschooltime',
					xtype:"datefield",
					fieldLabel:'返校时间',
					format:'Y-m-d',
					minValue : new Date(),
					allowBlank : false,
					editable : false,
					maxLength :20,
					vtype : 'daterange',//调用时间验证方法
					startDateField : 'startTimeId',//限制开始时间id
					//showToday:false 
				},{
					xtype : 'textfield',
					name:'parentsphone',
					fieldLabel:'父母电话',
					allowBlank : false,
					maxLength :11,
					regex:/^(13\d|15[^4,\D]|17[13678]|18\d)\d{8}|170[^346,\D]\d{7}$/
				}]
			}]
		},{
		   //id:'enterid',
           name: "enteragreement",
           html: '&nbsp;&nbsp;<span><input id="enterid" type="checkbox" checked="true"/>&nbsp;<font size="2" style="vertical-align:text-bottom">我已阅读<a href="javascript:void(0)"  onclick="stater()">《学生假期安全提醒》</a></span>'
		},{
			xtype:'hidden',
			name:'launchid'
		},{
			xtype:'hidden',
			name:'censusendtime'
		},{
			xtype:'hidden',
			name:'censusid'
		}]
	});
    
    //学生假期说明
    stater = function(){
      explainWindow.show();
    }
    
    //学生假期说明
    var explain = '									';
        explain +='《学生假期安全提醒》\r\n';
        explain +='\r\n';
        explain +='1、假期离校的同学，要向班主任或德育导师书面请假，报告去向及回校时间。\r\n';
        explain +='2、留在校内的同学不要在寝室内使用明火或违章电器，以免引起火灾，造成人身财产损失；寝室内切勿私自留宿外来人员；要注意进\r\n';
        explain +='    出宿舍的外来人员，如遇可疑情况，请主动查问并及时向值班人员或安全保卫处报告。\r\n';
        explain +='3、外出旅游的同学，务必购买人身安全保险，同时请注意防盗、防骗、防交通事故，增强食品卫生安全意识和自我保护能力，确保人\r\n';
        explain +='    身安全。旅途中要随时与家人、同学保持联系，告知近况和行踪。\r\n';
        explain +='4、节日期间仍然在实验室的同学，要严格按照实验室管理、使用规则操作，注意用电、用火、用气、用水等的安全。\r\n';
        explain +='5、请大家务必保管好自己的财物，寝室内不要存放现金和贵重物品；银行存款务必使用个人密码，银行卡与身份证分开存放，以防冒\r\n';
        explain +='    领及密码窃取；保管好自己的校园卡、学生证等证件以及宿舍的钥匙，切勿将证件和钥匙转借他人或请他人保管。\r\n';
        explain +='6、遇到突发事件，请同学们务必保持冷静，及时向有关部门反映情况（安全保卫处24小时值班电话：安全保卫处（紫金港校区）\r\n';
        explain +='    88206110；玉泉保卫办87951110；西溪保卫办88273110；华家池保卫办86971110；之江保卫办86592777）。\r\n';
    //学生假期说明
    var explainForm = new Ext.FormPanel({
		layout : 'form',
		x: 100,
	    y: 30,
		frame:true,
		labelWidth:60,
		border : false,
		padding : '10 10 0 8',
		defaults : {
			anchor : '100%'
		},
		items:[{
			xtype:'textarea',
			id:'agreementtos',
			readOnly:true,
			value:explain,
			anchor: '97.5%',
			height:300
		}]
	});
    
    //学生假期说明
    var explainWindow = new Ext.Window({
 	   title: '学生假期说明',
     	width:840,
 		height:400,
 		closeAction:'hide',
 		modal : true,
 		layout : 'fit',
 		buttonAlign : 'center',
 		items : [explainForm]
     });
    //添加form窗口
    var addWindow = new Ext.Window({
		title : '去向统计',
		width:840,
		height:400,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [addForm],
		buttons : [{
			text : '确认保存',
			handler : function() {
			//alert(Ext.get("enterid").dom.checked);
				
				if(Ext.get("enterid").dom.checked==false){
					Ext.Msg.alert('信息提示',"请先阅读《学生假期安全提醒》");
				}else{
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
			}
		}/*,{
			text:'取消',
			handler:function(){
				addWindow.hide();
			}
		}*/]
	});
    
    //获取当前用户信息
    var getCurrentUserInfo = function(){
    	Ext.Ajax.request({
    		url:"Applyactivity_getCurrentUserInfo.do",
    		success:function(response){
    			var responsedata = Ext.util.JSON.decode(response.responseText);
    			if(responsedata){
    				//alert(responsedata.data.userHeadmaster);
    				addForm.getForm().findField('censususernum').setValue(responsedata.data.userNum);
    				addForm.getForm().findField('censususername').setValue(responsedata.data.userName);
    				addForm.getForm().findField('classes').setValue(responsedata.data.userClasses);
    				addForm.getForm().findField('ssyq').setValue(responsedata.data.userYq);
    				addForm.getForm().findField('dorm').setValue(responsedata.data.userDorm);
    				addForm.getForm().findField('userphone').setValue(responsedata.data.userPhone);
    				addForm.getForm().findField('teacher').setValue(responsedata.data.userHeadmaster);
    				addForm.getForm().findField('counsellor').setValue(responsedata.data.userInstructor);
    			}
    		}
    	});
    };
    //获取待填写去向
    var getNeedWriteWheres = function(){
    	Ext.Ajax.request({
			url:'Whereabouts_findNeedWriteWheres.do',		//检索待填去向链接
			success:function(response){
				var resdata = Ext.util.JSON.decode(response.responseText);
				if(resdata.message!="nothing"){
					var start = resdata.data.launchstarttime;
					var	end = resdata.data.launchendtime;
					var holidaytime = start + " -- " +end;
					var date = new Date(start);
			    	var time = date.getTime()-24*60*60*1000;
			    	var d = new Date(time);
			    	var y = d.getFullYear();
			    	var m = d.getMonth()+1;
			    	if(m<10){
			    		m = "0"+m;
			    	}
			    	var d1 = d.getDate();
			    	if(d1<10){
			    		d1 = "0"+d1;
			    	}
			    	var okDateStr= y + "-" + m + "-" + d1;
					addForm.getForm().findField("launchid").setValue(resdata.data.launchid);
					addForm.getForm().findField("launchname").setValue(resdata.data.launchname);
					addForm.getForm().findField("holidaytime").setValue(holidaytime);
					addForm.getForm().findField("censusendtime").setValue(resdata.data.censusendtime);
					Ext.getCmp("startTimeId").setMinValue(okDateStr);
					getCurrentUserInfo();
					addWindow.show();
				}else{
					Ext.getCmp("inputwheres").hide();
				}
			}
		});
    };
    
    //布局
    new Ext.Viewport({
		layout:"fit",
		items:[{
			frame:true,
			title:"去向统计",
			iconCls:'menu-15',
			layout:"border",
			items:[searchForm,grid]
		}],
		listeners:{
			render:function(){
    			getCurrentUserInfo();
    			getNeedWriteWheres();
			}
		}
	});
});