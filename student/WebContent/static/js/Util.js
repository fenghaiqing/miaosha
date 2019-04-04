/**********************************************
 * 					格式化时间 				  *
 *********************************************/
/**
 * 方法作用：【格式化时间】
 * 使用方法
 * 示例：
 *      使用方式一：
 *      var now = new Date();
 *      var nowStr = now.Formatter("yyyy-MM-dd hh:mm:ss");
 *      使用方式二：
 *      new Date().Formatter("yyyy年MM月dd日");
 *      new Date().Formatter("MM/dd/yyyy");
 *      new Date().Formatter("yyyyMMdd");
 *      new Date().Formatter("yyyy-MM-dd hh:mm:ss");
 * @param format {date} 传入要格式化的日期类型
 * @returns {2015-01-31 16:30:00}
 */
Date.prototype.Formatter = function (format){
    var o = {
        "M+" : this.getMonth()+1, //month
        "d+" : this.getDate(), //day
        "h+" : this.getHours(), //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), //second
        "q+" : Math.floor((this.getMonth()+3)/3), //quarter
        "S" : this.getMilliseconds() //millisecond
    }
    if(/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    for(var k in o) {
        if(new RegExp("("+ k +")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
        }
    }
    return format;
}


/***********************************************************************
 *                           日期时间工具类                            *
 *                     注：调用方式，deteUtil.方法名                   *
 * ********************************************************************/
var dateUtil = {
	/*
	 * 方法作用：【取传入日期是星期几】
	 * 使用方法：dateUtil.nowFewWeeks(new Date());
	 * @param date{date} 传入日期类型
	 * @returns {星期四，...}
	 */
    nowFewWeeks:function(date){
        if(date instanceof Date){
            var dayNames = new Array("星期天","星期一","星期二","星期三","星期四","星期五","星期六");
            return dayNames[date.getDay()];
        } else{
            return "Param error,date type!";
        }
    },
    /*
     * 方法作用：【字符串转换成日期】
     * 使用方法：dateUtil.toDate("2010-01-01");
     * @param str {String}字符串格式的日期，传入格式：yyyy-mm-dd(2015-01-31)
     * @return {Date}由字符串转换成的日期
     */
    toDate:function(str){
        var   re   =   /^(\d{4})\S(\d{1,2})\S(\d{1,2})$/;
        var   dt;
        if   (re.test(str)){
            dt   =   new   Date(RegExp.$1,RegExp.$2   -   1,RegExp.$3);
        }
        return dt;
    },
    /*
     * 方法作用：【计算2个日期之间的天数】
     * 传入格式：yyyy-mm-dd(2015-01-31)
     * 使用方法：dateUtil.dayMinus(startDate,endDate);
     * @startDate {Date}起始日期
     * @endDate {Date}结束日期
     * @return endDate - startDate的天数差
     */
    interval:function(startDate, endDate){
        if(startDate instanceof Date && endDate instanceof Date){
            var days = Math.floor((endDate-startDate)/(1000 * 60 * 60 * 24));
            return days;
        }else{
            return "Param error,date type!";
        }
    },
    /*
     * 方法作用：【获取当前时间的前一个月份】
     * 使用方法：dateUtil.preMonth();
     * @return (1\2\3\4\10)
     * 
     */
    preMonth:function(){
    	var date = new Date();
    	date.setMonth(date.getMonth()-1);
    	return date.getMonth()+1;
    },
    
    /*
     * 方法作用：【获取当前时间的月份】
     * 使用方法：dateUtil.month();
     */
    month:function(){
    	var date = new Date();
    	return date.getMonth()+1;
    },
    /*
     * 使用方法：【获取当前时间的下个月份】
     * 使用方法：dateUtil.nextMonth();
     */
    nextMonth:function(){
    	var date = new Date();
    	date.setMonth(date.getMonth()+1);
    	return date.getMonth()+1;
    }
};

/***********************************************************************
 *                           字符串操作工具类                              *
 *                     注：调用方式，StringUtil.方法名                      *
 * ********************************************************************/
var StringUtil = {
    /*
     * 判断字符串是否为空
     * @param str 传入的字符串
     * @returns {Boolean}
     */
    isEmpty:function(str){
        if(str != undefined && str != null && str.length > 0){
            return false;
        }else{
            return true;
        }
    },
    /*
     * 判断两个字符串子否相同
     * @param str1
     * @param str2
     * @returns {Boolean}
     */
    isEquals:function(str1,str2){
        if(str1 === str2){
            return true;
        }else{
            return false;
        }
    },
    /*
     * 忽略大小写判断字符串是否相同
     * @param str1
     * @param str2
     * @returns {Boolean}
     */
    isEqualsIgnorecase:function(str1,str2){
        if(str1.toUpperCase() === str2.toUpperCase()){
            return true;
        }else{
            return false;
        }
    },
    
    /*
     * 判断是否是数字
     * @param value
     * @returns {Boolean}
     */
    isNum:function (value){
        if( value != null && value.length>0 && isNaN(value) == false){
            return true;
        }else{
            return false;
        }
    },
    
    /*
     * 判断是否是中文
     * @param str
     * @returns {Boolean}
     */
    isChine:function(str){
        var reg = /^([u4E00-u9FA5]|[uFE30-uFFA0])*$/;
        if(reg.test(str)){
            return false;
        }
        return true;
    },
    
    /*
     * 方法作用：【将空字符串转换为特定字符】
     * @param str 判断是否为空的字符串  char 特定转换字符
     */
    emptyTrans:function(str,char){
    	if(this.isEmpty){
    		return char;
    	}else{
    		return str;
    	}
    }
};
/**************************************
 * 			判断对象 数组是否为空
 * 			调用方法：	 dataUtils.方法名   *
 **************************************/
var dataUtils = {
	/*
	 * 方法作用:【判断对象是否为空】
	 * @param obj 对象
	 * @return {boolean}
	 */
	objIsEmpty:function(obj){
		return obj === undefined || obj==null || JSON.stringify(obj) === '{}';
	},
	/*
	 * 方法作用：【判断数组是否为空】
	 * @param array数组
	 * @return {boolean}
	 */
	arrayIsEmpty:function(array){
		return array === undefined || array==null || JSON.stringify(array) === '[]';
	}
}


/**************************************
 * 			ajax封装
 * 			调用方法：	 $.ajxpost /$.ajxget   *
 **************************************/
/*
 * 方法作用：【ajax post请求】
 */
jQuery.ajxpost=function(url, data, successfn, errorfn) {
    $.ajax({
    	method: "post",
        data: data,
        url: url,
        success: function(d){
        	var data = JSON.parse(d);
        	if(data.code != 200){
        		$alert("warning",data.msg);
        	}else{
        		 successfn(data);
        	}
        },
        error: function(e){
        	console.log(e);
        	$alert("danger",e);
        }
    });
};
jQuery.ajxasync=function(url, data, successfn, errorfn) {
    $.ajax({
    	method: "post",
        data: data,
        url: url,
        async:false,
        success: function(d){
        	var data = JSON.parse(d);
        	if(data.code != 200){
        		$alert("warning",data.msg);
        	}else{
        		 successfn(data);
        	}
        },
        error: function(e){
        	console.log(e);
        	$alert("danger",e);
        }
    });
};
/*
 * 方法作用：【ajax get请求】
 */
jQuery.ajxget=function(url, data, successfn) {
    $.ajax({
    	method: "get",
        data: data,
        url: url,
        success: function(d){
        	var data = JSON.parse(d);
        	if(data.code != 200){
        		$alert("warning",data.msg);
        	}else{
        		 successfn(data);
        	}
        },
        error: function(e){
        	console.log(e);
        	$alert("danger",e);
        }
    });
};

function $alert(type,msg){
    if(type =='success'){

        var dom ='<div id="alert" class="alert alert-success">' +
            '<strong>'+msg+'</strong></div>';
        $("body").append(dom);
    }else if(type =='warning'){
        var dom ='<div id="alert" class="alert alert-warning">' +
            '<strong>'+msg+'</strong></div>';
        $("body").append(dom);
    }else if(type =='danger'){
        var dom ='<div id="alert" class="alert alert-danger">' +
            '<strong>'+msg+'</strong></div>';
        $("body").append(dom);
    }
    setTimeout(function () {
        $("#alert").remove();
    },3000)
}

var root ="/student";

/**************
 *获取当前记录id *
***************/
var curruntId;
function getcurrId(id){
	curruntId=id;
	console.log(curruntId);
}
/**
 * 用户角色
 */
var curruntRoleId;
$(function(){
	var height = window.screen.height-207;
	$("nav.side-navbar").css("height",height+'px');
	$(".content-inner").css("height",height+'px');
	curruntRoleId=$("#curruntRoleId").val();
})

function restpassword(){
	var password = $("#password").val();
	var newpassword = $("#newpassword").val();
	var cfpassword = $("#cfpassword").val();
	if(StringUtil.isEmpty(password)){
		$alert("warning","原密码不能为空！");
		return;
	}
	if(StringUtil.isEmpty(newpassword)){
		$alert("warning","新密码不能为空！");
		return;
	}
	if(StringUtil.isEmpty(cfpassword) || newpassword !== cfpassword){
		$alert("warning","两次输入密码不一致，请重新输入！");
		return;
	}
	var user={
			id:$("#userid").val(),
			password:password,
			newpassword:newpassword,
			cfpassword:cfpassword
	}
	$.ajxpost(root+"/user/restpassword",user,function(result){
		$alert("success","修改成功！")
		$('#update-password').modal('hide');
		window.location.href = root+"/user/logout";
	})
}
function changeCurrentPage(){
	currentPage=1;
}

function checkPf(type){
	if(type==1){
		$("#pf").attr("href","/student/static/css/style.css")
	}else if(type==2){
		$("#pf").attr("href","/student/static/css/style2.css")
	}else if(type==3){
		$("#pf").attr("href","/student/static/css/style3.css")
	}
}
