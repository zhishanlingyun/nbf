//封装了基本的ui组件
var ui = {

	//IE9 下显示有问题，待修正
	confirm : function(msg,config){
		var div = $('#rd_CONFIRM_WIN');
		if( div.length==0) {
			div =$("<DIV id='rd_CONFIRM_WIN'></DIV>");	
		} 
		div.html(msg);
		var cfg = {
			height : 260,
			modal : true,
			width:	300,
			close : true	
			};
		jQuery.extend(cfg, config);
		$( div ).dialog({
			resizable: false,
			height:cfg.height,
			maxHeight:600,
			maxWidth:300,
			minHeight:10,
			minWidth:10,
			width:cfg.width,
			modal: cfg.modal,
			buttons: {
				"确定": function() {
					if(cfg.callBackFunc){
						cfg.callBackFunc();
					}
					if(cfg.close){
						$( this ).dialog( "close" );
					}
				},
				"取消": function() {
					
					if(cfg.callCloseBackFunc){
						cfg.callCloseBackFunc();
					}
					$( this ).dialog( "close" );
				}
			}
		});	
		$('.ui-dialog-titlebar').hide();
		$('.dialo').css();
	},
	
	//创建div块
	createDiv:function(divID){
			var _paperDiv=$('#'+divID);
			if(_paperDiv.length==0){
				var div="<div id='"+divID+"' style='display:none;'></div>";
				var body=$('body');
				$(div).appendTo(body);
			}else{
				_paperDiv.html("");
			}
	},
	
	//创建可与后台交互的对话窗体，采用ajax与后台交互
	//功能已实现,需调整界面样式
	createWind:function(id,config){
		
		this.createDiv(id);	
		function show(){
			var titleHeight=80;
			var bareHeight=80;
			$('#_header').focus();
			var height=$(window).height();
			var width=$(window).width();
			height = height*0.5;
			
			$('#'+id+'Form').css('height',(height-titleHeight-bareHeight)).css('overflow-y','auto');
			$('#'+id).dialog({
				autoOpen: false,
				modal:true,
				animate: true,
				height:config.height,
				width:config.width,
				bgiframe:true,
				resizable:false,
				close: function(event, ui){
					
				}
			});
			$('#'+id).dialog('open');
			$('.ui-dialog-titlebar').hide();
		}
		if(config){
			config.callBackFunc = show;
			config.obj = '#'+id;
			HD.load(config);
		}
	},
	
	//带有回调函数的关闭窗体方法
	closeWind:function(id){
		function callBack(){
			$('#'+id).html('');
			$('.formError').remove();
			$('#'+id).dialog('close');
			$('#'+id).dialog('destroy');
		}
		ui.confirm("确定取消吗?",{callBackFunc:callBack});
	},
		
	//不带有回调函数的关闭窗体方法	
	close:function(id){
		$('#'+id).html('');
		$('.formError').remove();
		$('#'+id).dialog('close');
	},
	
	//添加遮罩层
	//text : 提示信息
	//eclipseObj : 被遮罩的div
	popProcess : function(text, eclipseObj){
	  	 function scrollHeight()
	 {
	    return document.documentElement.scrollTop || document.body.scrollTop;
	 }
	  	 function documentHeight()
	 {
	    return Math.max(document.documentElement.scrollHeight || document.body.scrollHeight, document.documentElement.clientHeight);
	 }
	  	 function viewWidth()
	 {
	    return document.documentElement.clientWidth;
	 }
	 function viewHeight()
	 {
	    return document.documentElement.clientHeight;
	 }
		 if (eclipseObj) {
		 	var width = eclipseObj.width();
		 	var height = eclipseObj.height();
		 	if ( height == 0) {	height = 200; }
	   	var oLogin=$("<div></div>");
	
	    $(oLogin).attr("id","popProcessLogin");
	    $(oLogin).html('<img src="'+ctxPath+'/static/default/images/load.gif" align="absmiddle" />&nbsp;'+text);
	    var oMark=$("<div></div>");
	    $(oMark).attr("id","popProcessMark");        
	    $("body").append($(oLogin));
	    $("body").append($(oMark));
		$(oMark).css("left",eclipseObj.offset().left + 'px');
	    $(oMark).css("top",eclipseObj.offset().top + 'px');		    
	
	    $(oMark).width(width+'px');
	    $(oMark).height(height + 'px');
	    
	    $(oLogin).css("left",width/2 + eclipseObj.offset().left + "px");
	    $(oLogin).css("top", height/2 + eclipseObj.offset().top + "px"); 		 
		 }
		 else {
	      var oLogin=$("<div></div>");
	      $(oLogin).attr("id","popProcessLogin");
	      $(oLogin).html('<img src="'+ctxPath+'/static/default/images/load.gif" align="absmiddle" />&nbsp;'+text);
	      var oMark=$("<div></div>");
	      $(oMark).attr("id","popProcessMark");        
	      $("body").append($(oLogin));
	      $("body").append($(oMark));
	      $(oMark).width(viewWidth()+'px');
	      $(oMark).height(documentHeight() + 'px');
	      $(oLogin).css("left",(viewWidth() - $(oLogin).offset().left)/2 + 'px');
	      $(oLogin).css("top",(viewHeight() - $(oLogin).offset().top)/2 + scrollHeight() + 'px');
	    }        
	  },
	  
	  //关闭遮罩层
	  shutProcess : function(){
	       $("#popProcessLogin").remove();
	       $("#popProcessMark").remove();
	  }


};