Ext.BLANK_IMAGE_URL = '../../../ext/resources/images/default/s.gif';
/*
 * ! 活动管理
 */
Ext
		.onReady(function() {
			Ext.QuickTips.init();
			// fields类声明
			var News = [ {
				name : 'newsid',
				type : 'string'
			}, {
				name : 'newstitle',
				type : 'string'
			}, {
				name : 'website',
				type : 'string'
			}, {
				name : 'writer',
				type : 'string'
			}, {
				name : 'newsdate',
				type : 'string'
			}, {
				name : 'property',
				type : 'string'
			}, {
				name : 'content',
				type : 'string'
			}, {
				name : 'newscheckstyle',
				type : 'string'
			}, {
				name : 'highlight',
				type : 'string'
			}, {
				name : 'totop',
				type : 'string'
			}, {
				name : 'activityid',
				type : 'string'
			} // 参加活动状态，报名后状态：待参加、签到后：已参加、未签到：未参加
			];
			var store = new Ext.data.JsonStore({
				url : 'news_findNews.do',
				root : 'root',
				totalProperty : 'total',
				autoLoad : {
					params : {
						start : 0,
						limit : 15
					}
				},
				fields : News,
			});

			// 提交新闻按钮
			var newsButton = new Ext.Button({
				id : 'newsButton',
				width : 90,
				labelWidth : 30,
				text : '<b>提交新闻</b>',
				handler : function() {
					addWindow.show();
				}
			});

			// 参与公示按钮
			var publishButton = new Ext.Button({
				id : "publishButton",
				width : 90,
				labelWidth : 30,
				hidden : true,
				text : '<b>参与公示</b>',
				handler : function() {
					var activityid = Ext.getCmp("activityid").getValue();
					Ext.MessageBox.confirm('参与公示提示', '确定参与公示吗？', function(ok) {
						if (ok == 'yes') {
							Ext.Ajax.request({
								url : "Applyactivity_joinPublish.do",
								params : {
									activityid : activityid,
									pulishkey : '1'
								},
								success : function(response) {
									var responsedata = Ext.util.JSON
											.decode(response.responseText);
									// Ext.Msg.alert('信息提示',responsedata.message);
									Ext.getCmp("publishButton").hide();
									store.reload();
								},
								error : function(e) {
									alert(e);
								}
							});
						}
					});
				}
			});

			// 查询表单
			var searchForm = new Ext.FormPanel({
				region : "north",
				height : 80,
				border : false,
				layout : "form",
				padding : "5 20 0 20",
				items : [ {
					xtype : "fieldset",
					title : "查询设置",
					padding : "0 20 0 15",
					items : [ {
						layout : "column",
						defaults : {
							xtype : "container",
							autoEl : "div",
							labelAlign : "right",
							layout : "form"
						},
						items : [ {
							width : 300,
							items : [ {
								width : 180,
								labelWidth : 30,
								xtype : "textfield",
								name : "newstitle",
								fieldLabel : "标题"
							} ]
						}, {
							width : 260,
							items : [ {
								width : 120,
								labelWidth : 30,
								xtype : "datefield",
								name : "newsdate",
								fieldLabel : "日期",
								format:'Y-m-d',
								allowBlank : false,
								editable : false,
								maxLength :20
							} ]
						}, {
							width : 260,
							items : [ {
								width : 75,
								xtype : "button",
								// iconCls:"btn-list",
								text : "查询",
								handler : function() {
									var f = searchForm.getForm();
									if (f.isValid()) {
										var _params = f.getValues();
										// searchForm.getForm().reset();
										store.load({
											params : _params
										});
									}
								}
							} ]
						}, {
							width : 100,
							items : [ {
								width : 90,
								xtype : "button",
								text : "发布新闻",
								handler : function() {
									addWindow.show();
									addForm.getForm().reset();
								}
							} ]
						} ]
					}, {
						xtype : 'hidden',
						name : 'start',
						value : '0'
					}, {
						xtype : 'hidden',
						name : 'limit',
						value : '15'
					}, {
						xtype : 'hidden',
						name : 'property'
					}, {
						xtype : 'hidden',
						name : 'newscheckstyle'
					}, {
						xtype : 'hidden',
						name : 'highlight'
					}, {
						xtype : 'hidden',
						name : 'totop'
					} ]
				} ]
			});


			// 获取response数据
			var rownumber = new Ext.grid.RowNumberer();// 行号
			var grid = new Ext.grid.GridPanel(
					{
						store : store,
						cm : new Ext.grid.ColumnModel(
								{
									defaults : {
										menuDisabled : true
									},// 禁止表头菜单
									columns : [
											rownumber,
											{
												header : '标题',
												width : 200,
												align : 'center',
												dataIndex : 'newstitle'
											},
											{
												header : '网址',
												width : 200,
												align : 'center',
												dataIndex : 'website',
												renderer: function (value, meta, record) {  
												      return '<a href="javascript:void(0);" onclick="toNewWindow(\''+value+'\')">' + value + '</a>';  
												  }  
											},
											{
												header : '日期',
												width : 200,
												align : 'center',
												dataIndex : 'newsdate'
											},
											{
												header : '作者',
												width : 200,
												align : 'center',
												dataIndex : 'writer'
											},
											{
												xtype : 'actioncolumn',
												header : '操作',
												width : 100,
												align : 'center',
												menuDisabled : true,
												items : [ {
													icon : '../../../img/btn/delete.png',
													tooltip : '删除',
													handler: function(grid, rowIndex, colIndex){
								                		deleteNews(grid, rowIndex, colIndex);
								                	}
												} ]
											} ]
								}),
						rownumber : rownumber,// checkbox
						stripeRows : true, // 行分隔符
						columnLines : true, // 列分隔符
						loadMask : true, // 加载时的遮罩
						frame : true,
						region : "center",
						iconCls : 'menu-51',
						bbar : new Ext.PagingToolbar({
							pageSize : 15,
							store : store,
							displayInfo : true
						}),
						viewConfig : {
							forceFit : true,
							scrollOffset : 2
						}
					});

		    //活动删除
		    var deleteNews = function(grid, rowIndex, colIndex){
		    	var record = grid.getStore().getAt(rowIndex);
		    	Ext.MessageBox.confirm('删除提示', '确定删除？', function(c) {
					   if(c=='yes'){
						   Ext.Ajax.request({
					   			url : "news_deleteNews.do",
					   			params:{newsid:record.data.newsid},
					   			success : function() {
						   		  store.reload();
						   	  }
						   });
					   	}
				});
		    };

			// 文件导入form
			var importForm = new Ext.FormPanel({
				layout : 'form',
				fileUpload : true,
				frame : true,
				url : 'Applyactivity_importExcel.do',
				labelWidth : 60,
				border : false,
				padding : '5 10 10 8',
				defaults : {
					anchor : '100%'
				},
				items : [ {
					id : 'control_id',
					xtype : 'textfield',
					fieldLabel : '导入名单',
					name : 'upload',
					inputType : 'file'
				}, {
					xtype : 'hidden',
					name : 'activityid',
				}, {
					xtype : 'hidden',

					name : 'sscore',
				} ]
			});

			// 文件导入窗口
			var importWindow = new Ext.Window({
				title : '文件导入',
				width : 280,
				height : 120,
				closeAction : 'hide',
				modal : true,
				layout : 'fit',
				buttonAlign : 'center',
				items : [ importForm ],
				buttons : [ {
					text : '确认导入',
					handler : function() {
						if (importForm.getForm().isValid()) {
							importForm.getForm().submit(
									{
										success : function(form, action) {
											Ext.Msg.alert('信息提示',
													action.result.message);
											importWindow.hide();
											store.reload();
										},
										failure : function(form, action) {
											if (action.result.errors) {
												Ext.Msg.alert('信息提示',
														action.result.errors);
											} else {
												Ext.Msg.alert('信息提示', '连接失败');
											}
										},
										waitTitle : '提交',
										waitMsg : '正在保存数据，稍后...'
									});
						}
					}
				} ]
			});

		    //文本编辑器
		    var ueditor = new Ueditor({
		    	id:'contentid',
		        allowBlank : true,
		        name : 'content',
		        anchor : '99%',
		        height : 205,
		        fieldLabel : '活动内容'
			});
			
			// 新闻提交窗口
			var addForm = new Ext.FormPanel(
					{
						layout : 'form',
						// baseCls:'x-plain',
						frame : true,
						url : 'news_saveOrUpdateNews.do',
						labelWidth : 48,
						border : false,
						padding : '15 10 0 8',
						defaults : {
							anchor : '100%'
						},
						items : [
								{
									xtype : 'textfield',
									name : 'newstitle',
									fieldLabel : '标题',
									anchor : '97.5%',
									allowBlank : false,
								},
								{
									id : 'websiteid',
									xtype : 'textfield',
									name : 'website',
									fieldLabel : '网址',
									anchor : '97.5%',
									emptyText : 'http://www.abc.mnp.com(网址输入格式)',
									regex : /^[a-zA-z]+:\/\/(\w+(-\w+)*)(\.(\w+(-\w+)*))*(\?\S*)?$/,
									listeners : {
										blur : function(a, b) {
											var v = Ext.getCmp("websiteid")
													.getValue();
											if (v != null && v != "") {
												Ext.getCmp("contentid").hide();
											} else {
												Ext.getCmp("contentid").reset();
												Ext.getCmp("contentid").show();
											}
										}
									}
								}, {
									layout : 'column',
									items : [ {
										columnWidth : .5,
										layout : 'form',
										defaults : {
											anchor : '95%'
										},
										items : [ {
											xtype : 'textfield',
											name : 'newsdate',
											fieldLabel : '日期',
											xtype : "datefield",
											format : 'Y-m-d',
											minValue : new Date(),
											allowBlank : false,
											editable : false,
											maxLength : 20
										} ]
									}, {
										columnWidth : .5,
										layout : 'form',
										defaults : {
											anchor : '95%'
										},
										items : [ {
											xtype : 'textfield',
											name : 'writer',
											fieldLabel : '作者',
											allowBlank : false,
											readOnly : true
										} ]
									} ]
								}, {
									layout : 'column',
									items : [ {
										columnWidth : .5,
										layout : 'form',
										defaults : {
											anchor : '95%'
										},
										items : [ {
											xtype : 'radiogroup',
											id : 'atype',
											name : 'property',
											fieldLabel : "属性",
											items : [ {
												boxLabel : '校内',
												name : 'property',
												inputValue : '校内',
												checked : true
											}, {
												boxLabel : '校外',
												name : 'property',
												inputValue : '校外',
											} ]
										} ]
									} /*
										 * ,{ xtype : 'checkbox', name:'totop',
										 * boxLabel:'置顶', width:140 },{ xtype :
										 * 'checkbox', name:'highlight',
										 * boxLabel:'高亮', width:140 }
										 */]
								},ueditor,{
									xtype : 'hidden',
									name : 'newsid'
								} ]
					});

			// 添加form窗口
			var addWindow = new Ext.Window({
				title : '新闻发布',
				width : 840,
				height : 460,
				closeAction : 'hide',
				modal : true,
				layout : 'fit',
				buttonAlign : 'center',
				items : [ addForm ],
				buttons : [ {
					text : '发　布',
					handler : function() {
						if (addForm.getForm().isValid()) {
							addForm.getForm().submit(
									{
										success : function(form, action) {
											Ext.Msg.alert('信息提示',
													action.result.message);
											Ext.getCmp("contentid").reset();
											Ext.getCmp("contentid").show();
											addWindow.hide();
											store.reload();
										},
										failure : function(form, action) {
											if (action.result.errors) {
												Ext.Msg.alert('信息提示',
														action.result.errors);
											} else {
												Ext.Msg.alert('信息提示', '连接失败');
											}
										},
										waitTitle : '提交',
										waitMsg : '正在保存数据，稍后...'
									});
						}
					}
				} ]
			});

		    //获取当前用户信息
		    var getCurrentUserInfo = function(){
		    	Ext.Ajax.request({
		    		url:"Applyactivity_getCurrentUserInfo.do",
		    		success:function(response){
		    			var responsedata = Ext.util.JSON.decode(response.responseText);
		    			if(responsedata){
		    				addForm.getForm().findField('writer').setValue(responsedata.data.userName);
		    			}
		    		}
		    	});
		    };
			
			// 布局
			new Ext.Viewport({
				layout : "fit",
				items : [ {
					frame : true,
					title : "新闻发布",
					iconCls : 'menu-15',
					layout : "border",
					items : [ searchForm, grid ],
					listeners:{
						render:function(){
							getCurrentUserInfo();
						}
					}
				} ]
			});

		});


//打开新链接
function toNewWindow(url){
	top.window.open(url);
}