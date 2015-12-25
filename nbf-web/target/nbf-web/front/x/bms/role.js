var jrole = {
	qform:function(){
		 	var data = page.getConditionQformVal("qform");
		 	data.pageSizes = [10,20];
		 	
		 	//data.sort = 'DEPT_INDEX';
		 	//data.dir = 'ASC'
		 	var url = ctxPath+"/bms/userlist";
		  	function loadHtml(obj){ 
		    	$("#ghtml").html(obj.html);
		    };
		    var config={
			    url:url,
			    data:data,
			    eclipseObj: $("#ghtml"),
			    callBackFunc:loadHtml
		    };
			page.doCondition(config);
			
	},
	
	loadCreatRoleDiv:function(){
		var config = {
			width:470,
			height:420,
			url:ctxPath+'/bms/role!loadCreatRoleDiv.action'
		};
		ui.createWind('cr',config);
	},
	
	closeWin:function(id){
		ui.closeWind(id);
	},
	
	close:function(id){
		ui.close(id);
	},
	
	saveNewRole:function(){
		var rolename = $('#rolename').val();
		var roletype = $('#roletype').val();
		var safelevel = $('#safelevel').val();
		var remark = $('#remark').val();
		if(!bms.valdata('rolename',100,1,'角色名称')){
			return;
		}
		if(!bms.valdata('remark',100,0,'备注')){
			return;
		}
		var config = {
			data:{rolename:rolename,remark:remark,roletype:roletype,safelevel:safelevel},
			url:ctxPath+'/bms/role!saveNewRole.action',
			callBackFunc:function(){
				jrole.close('cr');
				jrole.qform();
			},
			callBackErrorFunc:function(){
				alert("提交失败");
			}
		};
		HD.doAjax(config);
		//HD.doPost(config);
	},
	
	deleteRole:function(roleid){
		
		var config = {
			data:{roleid:roleid},
			url:ctxPath+'/bms/role!deleteRole.action',
			callBackFunc:function(){
				jrole.qform();
			},
			callBackErrorFunc:function(){
				alert("提交失败");
			}
		};
		HD.doAjax(config);
	},
	
	BatchDeleteRole:function(){
		var rIDS =HD.getCheckBoxIDStr("rID");	
		if(rIDS==null || rIDS=="")
		{
			alert("请选择要删除的数据");
			return ;
		}
		
		var params = {rIDS:rIDS};
		var url = ctxPath+"/bms/role!BatchDeleteRole.action"; 
		var config = {
			  	url:url,
			  	data:params,
				callBackFunc : function(obj){
					jrole.qform();
				},
				callBackErrorFunc: function fialInfo(){
					alert("删除失败");
				}
			};
		HD.doAjax(config);
	},
	
	alterRole:function(roleid){
		var rolename = $('#rolename').val();
		var remark = $('#remark').val();
		var roletype = $('#roletype').val();
		var safelevel = $('#safelevel').val();
		if(!bms.valdata('rolename',100,1,'角色名称')){
			return;
		}
		if(!bms.valdata('remark',100,0,'备注')){
			return;
		}
		var config = {
			data:{roleid:roleid,rolename:rolename,remark:remark ,roletype:roletype,safelevel:safelevel},
			url:ctxPath+'/bms/role!alterRole.action',
			callBackFunc:function(){
				jrole.close('ar');
				jrole.qform();
			},
			callBackErrorFunc:function(){
				alert("提交失败");
			}
		};
		HD.doAjax(config);
	},
	
	loadAlterRoleDiv:function(roleid){
		var config = {
			data:{roleid:roleid},
			width:470,
			height:420,
			url:ctxPath+'/bms/role!loadAlterRoleDiv.action'
		};
		ui.createWind('ar',config);
	},
	
	test:function(){
		alert("dff");
	}
	
	


};