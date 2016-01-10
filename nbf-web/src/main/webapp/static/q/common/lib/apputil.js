var login={
				doLogin:function()
				{
						var  loginName = $("#_loginName").val();
						var  password = $("#_password").val();
						var url = ctxPath+"/login/req";
						var params=$("#loginform").serialize();
						var config = {
						  	url:url,
						  	data:params,
							callBackFunc : function(obj){
								window.location.href=ctxPath+obj.url;
							},
							callBackErrorFunc:function(){
								alert("用户名或密码错误！");
								return ;
							}
						}
						
						HD.doAjax(config);
				},
				doLogout:function ()
				{
					var config = {
						  	url:ctxPath+"/logout/logout!toLogout.action",
							callBackFunc : function(obj){
										eval("var obj="+obj);
										if(obj.success==true){
										  window.location.reload();
										}else{
										  alert("注销失败，请联系管理员！");
										  return ;
										}
							}
					};
				    HD.doPost(config);
				}
}