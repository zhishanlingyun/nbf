/////////////////////////////////////////////////////////////////////////////////////////////////////////  
//      
//                                              Function 弹出界面中校验的方法
//
/////////////////////////////////////////////////////////////////////////////////////////////////////////
// JScript 文件
var facRegObject = function(){}
facRegObject.prototype = {
//只能输入汉字
regStrZHN : /^[\u4e00-\u9fa5]+$/,
regInfoStrZHN : "只能输入汉字。",

//判断是否为数字
regNum               : /^\d+$/,
regInfoNum : "只能输入数字。",

regInteger : /^(\+|-)?\d+$/,
regInfoInteger : "只能输入整数",

regFloat     : /^(\+|-)?\d+($|\.\d+$)/,
regInfoFloat : "只能输入实数",

//小写英文字母
regSEng               : /^[a-z]+$/,
regInfoSEng   : "只能输入小写英文字母。",

//大写英文字母
regBEng              : /^[A-Z]+$/,
regInfoBEng : "只能输入大写英文字母。",

//为英文字母
regEng                 : /^[A-Za-z]+$/,
regInfoEng : "只能输入英文字母",

//校验ip地址的格式
regIP           : /^(\d+)\.(\d+)\.(\d+)\.(\d+)$/,
regInfoIP   : "必须输入IP地址，如 192.168.1.10",

//是否符合网址格式
regURL                : /^((http:[/][/])?\w+([.]\w+|[/]\w*)*)?$/,
regInfoURL : "必须输入标准网址，如 http://www.botian.com",

//11位数字的移动电话号码
regMobile  : /(^[1][0-9][0-9]{9}$)/,
regInfoMobile : "必须输入11位数字手机号码，如 13012345678",

//验证电话号码
regTEL                 : /(^([0][1-9]{2,3}[-])?\d{3,8}(-\d{1,6})?$)|(^\([0][1-9]{2,3}\)\d{3,8}(\(\d{1,6}\))?$)|(^\d{3,8}$)/,
regInfoTEL : "必须输入合法的座机号码，如 010-87654321",

//验证日期YYYY-MM-DD
regDate               : /^(\d{4})(-)(\d{2})\2(\d{2})$/,
regInfoDate : "必须输入合法的日期格式，如 2008-08-08",

//验证时间HH:MM:SS
regTime               : /^(\d{2}):(\d{2}):(\d{2})$/,
regInfoTime : "必须输入合法的时间格式，如 12:23:45",

//验证日期时间YYYY-MM-DD HH:MM:SS
regDateTime     : /^(\d{4})(-)(\d{2})\2(\d{2}) (\d{2}):(\d{2}):(\d{2})$/,
regInfoDateTime : "必须输入完整的日期时间格式，如 2008-08-08 20:08:08",

//EMail地址
regEmail    : /^([-_A-Za-z0-9\.]+)@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/,
regInfoEmail : "必须输入正确的电子邮件地址，如 support@botian.com"
};

var regObjectConst = new facRegObject();

//弹出窗口中所有的校验，最终返回值表示是否可以继续
function AutoCheckFunctionAll(obj)
{
    var bResult = true;
    $(obj).find(".facOleIC").each(function(i){
        if ( $(this).css("facNC") != "none"){ 
            var lTempCheck = false;
            //控件初始化
            $(this).InputCheckInitial();
            //检查控件是否可以为空
            $(this).inputCheckAllowNull(); 
            //检查控件值的数据类型
            $(this).inputCheckDataType();
            //检查控件值的数据长度
            $(this).inputCheckValueLength();
            //数据值范围校验
            $(this).inputCheckValueBound();
            //控件输入格式校验
            $(this).InputCheckByZZBDS();

            lTempCheck = $(this).attr("facCR") == "true";      
            $(this).InputCheckFinished();

            bResult = bResult & lTempCheck;
        } 
    });
   
//    if (! bResult)
//        $().find(".facOleIC[facCR='false']:first").InputCheckShowError();
    
         return bResult;
}




//输入控件检查初始化
jQuery.fn.InputCheckInitial = function(){
    $(this).attr("facCR", "true");
    var objErrMsg =  $(".facClsEI", $(this));  
    //$(objErrMsg).attr("src", "style\\theme\\default\\images\\sicon\\" + $(objErrMsg).attr("imgname"));
    $(objErrMsg).attr("errinfo", "") ; 
    $(objErrMsg).html("");      
    return this;  
}

//显示并定位第一个错误
jQuery.fn.InputCheckShowError = function(){
    if (this != null)
    { 
        bError = $(this).attr("facCR") != "true";
        var objErrMsg = $(".facClsEI", $(this));
        var objTitle = $(".facClsIT", $(this)); 
        var objInput = $(".facClsIC", $(this));  
        alert(objTitle.text() + ":\n" + objErrMsg.attr("errinfo")); 
        objInput.focus();  
    } 
} 


//检查完毕后,更新所有的输入控件状态
jQuery.fn.InputCheckFinished = function(){
    bError = $(this).attr("facCR") != "true";
    var objErrMsg = $(".facClsEI", $(this));
    var objTitle = $(".facClsIT", $(this)); 
    var objInput = $(".facClsIC", $(this));
    if (bError) 
    { 
        objTitle.addClass("facClsTE");
        objInput.addClass("facClsIE");
        objErrMsg.unbind();
        //objErrMsg.click(ShowInputErrInfo);
        objErrMsg.attr("title", objErrMsg.attr("errinfo"));
        objErrMsg.show();
    }
    else
    {
        objTitle.removeClass("facClsTE");
        objInput.removeClass("facClsIE");
        objErrMsg.hide();
    }
}

//利用正则表达式校验输入
jQuery.fn.InputCheckByZZBDS = function(){
    var objErrMsg = $(".facClsEI", $(this));
    var objTitle = $(".facClsIT", $(this)); 
    var objInput = $(".facClsIC", $(this));

    //正则表达式验证input
    function checkByZZBDS(objErr, objTitle, objInput){
        var iExpType = ($(objInput).attr("facRET")==null)?"-1":$(objInput).attr("facRET");
        var strExpStr = $(objInput).attr("facRES"); 
        switch (iExpType)
        {
            case "-1":         return true;     break;
            case "customer":      return checkFormat(objInput, objErr,  strExpStr, "输入不符合规范。"); break;
            case "regDate":          return checkFormat(objInput, objErr,  regObjectConst.regDate, regObjectConst.regInfoDate);           break;  
            case "regTEL":      return checkFormat(objInput, objErr,  regObjectConst.regTEL, regObjectConst.regInfoTEL);              break;  
            case "regEmail":          return checkFormat(objInput, objErr,  regObjectConst.regEmail, regObjectConst.regInfoEmail);          break;  
            case "regMobile":      return checkFormat(objInput, objErr,  regObjectConst.regMobile, regObjectConst.regInfoMobile);          break;  
            case "regStrZHN":          return checkFormat(objInput, objErr,  regObjectConst.regStrZHN, regObjectConst.regInfoStrZHN);           break;                   
            case "regNum":      return checkFormat(objInput, objErr,  regObjectConst.regNum, regObjectConst.regInfoNum);           break;  
            case "regSEng":          return checkFormat(objInput, objErr,  regObjectConst.regSEng, regObjectConst.regInfoSEng);           break;  
            case "regBEng":      return checkFormat(objInput, objErr,  regObjectConst.regBEng, regObjectConst.regInfoBEng);           break;  
            case "regEng":          return checkFormat(objInput, objErr,  regObjectConst.regEng, regObjectConst.regInfoEng);           break;                                                                                                                 
            case "regIP":     return checkFormat(objInput, objErr,  regObjectConst.regIP, regObjectConst.regInfoIP);           break;    
            case "regURL":        return checkFormat(objInput, objErr,  regObjectConst.regURL, regObjectConst.regInfoURL);           break;    
            case "regTime":     return checkFormat(objInput, objErr,  regObjectConst.regTime, regObjectConst.regInfoTime);           break;    
            case "regDateTime":        return checkFormat(objInput, objErr,  regObjectConst.regDateTime, regObjectConst.regInfoDataTime);           break;    
            case "regIDCard":     return checkIDno(objInput, objErr);           break;      
            default:          return true;   break;  
        } 
    }    
    var bCheck = checkByZZBDS(objErrMsg, objTitle, objInput); 
    
   if (! bCheck) {
        $(this).attr("facCR", "false");     
   }      
    return bCheck;
}

//检查表单元素是否允许为空
jQuery.fn.inputCheckAllowNull = function(){
    var objErrMsg = $(".facClsEI", $(this));
    var objInput = $(".facClsIC", $(this)); 
    
    if ($(objInput).attr("facMI") == "true")
        if ($(objInput).val() == "" )
                   {
                            AddObjErrInfo(objErrMsg, "该内容不允许为空");
                            $(this).attr("facCR", "false");   
                            return false;
                   }
    return true;                 
} 

//检查表单元素数据类型是否正确
jQuery.fn.inputCheckDataType = function(){
    var objErrMsg = $(".facClsEI", $(this));
    var objInput = $(".facClsIC", $(this));
         var objDataType = $.trim($(objInput).attr("facDT"));
         var   objValue = $.trim($(objInput).val());
         var bCheck = true;
    
         //暂时不做STRING验证，所以1==2
    if ( objValue != "" && objDataType == "STRING" && 1==2)
         {
                   bCheck = checkFormat(objInput, objErrMsg,  regEng, regInfoEng); 
         }
         else if ( objValue != "" && objDataType == "INTEGER" )
         {
                   bCheck = checkFormat(objInput, objErrMsg,  regInteger, regInfoInteger); 
         }
         else if ( objValue != "" && objDataType == "FLOAT" )
         {
                   bCheck = checkFormat(objInput, objErrMsg, regFloat, regInfoFloat); 
         }
         else if ( objValue != "" && objDataType == "DATE" )
         {
                   bCheck = checkFormat(objInput, objErrMsg,  regDate, regInfoDate); 
         }
         else if ( objValue != "" && objDataType == "DATETIME" )
         {
                   bCheck = checkFormat(objInput, objErrMsg, regDateTime, regInfoDateTime); 
         }

         if (! bCheck)
                   $(this).attr("facCR", "false");

         return bCheck;
}

//检查表单元素数据值是否超越上下限值
jQuery.fn.inputCheckValueBound = function(){
    var objErrMsg = $(".facClsEI", $(this));
    var objInput = $(".facClsIC", $(this)); 
         var objValueUpper = $.trim($(objInput).attr("facVMax"));
         var objValueLower = $.trim($(objInput).attr("facVMin"));
         var   objValue = $.trim($(objInput).val());
    
    if( objValue != "" && objValueUpper != "" && Number(objValue) > Number(objValueUpper) )
         {
                   AddObjErrInfo(objErrMsg, "该内容的值不能大于" + objValueUpper);
                   $(this).attr("facCR", "false");   
                   return false;

         }else if( objValue != "" && objValueLower != "" && Number(objValue) < Number(objValueLower) )
         {
                   AddObjErrInfo(objErrMsg, "该内容的值不能小于" + objValueLower);
                   $(this).attr("facCR", "false");   
                   return false;
         }
    return true;                 
} 

//检查表单元素数据长度
jQuery.fn.inputCheckValueLength = function(){
    var objErrMsg = $(".facClsEI", $(this));
    var objInput = $(".facClsIC", $(this)); 
         var objInputLength = $.trim($(objInput).attr("facIL"));
         var   objValue = $.trim($(objInput).val());
         var len = 0;

    for (i=0; i<objValue.length; i++)    
                   if (objValue.charCodeAt(i)>255) len+=2; else len++;    
    
    if( objInputLength != "" && len > objInputLength )
         {
                   AddObjErrInfo(objErrMsg, "该内容的长度不能大于" + objInputLength);
                   $(this).attr("facCR", "false");   
                   return false;

         }
    return true;                 
} 

function ShowInputErrInfo()
{
    alert($(this).attr("errinfo"));
}

//检查格式是否正确
function checkFormat(obj, objErr, strFormat, strError){
    try
    { 
        var temp = eval(strFormat);
        if( obj.attr("value") != "" && ! temp.test(obj.attr("value"))){
            AddObjErrInfo(objErr , strError);
        return false;
    }
    else 
        return true;
    } 
    catch(e)
    {
        return false;
    }
}

function AddObjErrInfo(objErr, strMsg)
{
    if($.trim($(objErr).attr("errinfo")) == "")
//             $(objErr).attr("errinfo", $(objErr).attr("errinfo") + strMsg + "\n");   
        $(objErr).html( $(objErr).attr("errinfo") + strMsg + "\n");
}

//验证身份证号码是否有效
function checkIDno(obj, objErr)    
{     
    var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",
                   32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",
                   45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",
                   64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"};    
     
    var iSum = 0;    
    var info = "";    
    var strIDno = obj.attr("value");    
    var idCardLength = strIDno.length;      
    if(!/^\d{17}(\d|x)$/i.test(strIDno)&&!/^\d{15}$/i.test(strIDno))     
    {    
        AddObjErrInfo(objErr, "非法身份证号");    
        return false;    
    }    
     
    //在后面的运算中x相当于数字10,所以转换成a    
    strIDno = strIDno.replace(/x$/i,"a");    
   
    if(aCity[parseInt(strIDno.substr(0,2))]==null)    
    {    
        AddObjErrInfo(objErr, "非法地区");    
        return false;    
    }    
        
    if (idCardLength==18)    
    {    
        sBirthday=strIDno.substr(6,4)+"-"+Number(strIDno.substr(10,2))+"-"+Number(strIDno.substr(12,2));    
        var d = new Date(sBirthday.replace(/-/g,"/"))    
        if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate()))    
        {           
            AddObjErrInfo(objErr, "非法生日");    
            return false;    
        }    
   
        for(var i = 17;i>=0;i --)    
            iSum += (Math.pow(2,i) % 11) * parseInt(strIDno.charAt(17 - i),11);    
   
        if(iSum%11!=1)    
        {    
            AddObjErrInfo(objErr, "非法身份证号");    
            return false;    
        }    
    }    
    else if (idCardLength==15)    
    {    
        sBirthday = "19" + strIDno.substr(6,2) + "-" + Number(strIDno.substr(8,2)) + "-" + Number(strIDno.substr(10,2));    
        var d = new Date(sBirthday.replace(/-/g,"/"))    
        var dd = d.getFullYear().toString() + "-" + (d.getMonth()+1) + "-" + d.getDate();       
        if(sBirthday != dd)    
        {    
            AddObjErrInfo(objErr, "非法生日");    
            return false;    
        }    
    }    
    return true;     
}  
