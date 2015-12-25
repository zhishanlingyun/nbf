var u = {
		
	qform:function(){
	 	var data = page.getConditionQformVal("qform");
	 	data.pageSizes = [10,20];
	 	data.sort = 'createtime';
	 	data.dir = 'desc';
	 	var url = ctxPath+'/bms/userlist';
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
	
	loadUserCreatDiv:function(flag,uid){
		var config = {
			width:530,
			height:250,
			url:ctxPath+'/bms/createUserDiv?flag='+flag+'&id='+uid
		};
		ui.createWind('cr',config);
	},
	
	//新建或修改flag=0新建 flag=1修改
	saveOrUpdateUser:function(flag,uid){
		var loginname = $('#loginname').val();
		var password = $('#password').val();
		var phone = $('#phone').val();
		var email = $('#email').val();
		var roleid = $('#roleid').val();
		
		//验证
		if(jQuery('#_waybillform').validationEngine('validate')){
		
			var config = {
				data:{uid:uid,loginname:loginname,password:password,phone:phone,
				email:email,roleid:roleid,flag:flag},
				url:ctxPath+'/main/usermanagent!saveOrUpdateUser.do',
				callBackFunc:function(){
					um.qform();
					ui.close('cr');
					
				},
				callBackErrorFunc:function(){
					alert("提交失败");
				}
			};
			HD.doAjax(config);
		}
		//HD.doPost(config);
	},
	
	BatchDelete:function(){
		var rIDS =HD.getCheckBoxIDStr('uID');	
		if(rIDS==null || rIDS=="")
		{
			alert("请选择要删除的数据");
			return ;
		}
		var params = {rIDS:rIDS};
		var url = ctxPath+"/main/usermanagent!BatchDelete.do"; 
		var config = {
			  	url:url,
			  	data:params,
				callBackFunc : function(obj){
					um.qform();
				},
				callBackErrorFunc: function fialInfo(){
					alert("删除失败");
				}
			};
		HD.doAjax(config);
	}
		
}