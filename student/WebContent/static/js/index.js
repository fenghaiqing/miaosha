    var currentPage = 1;
    var totalPages = 10;
     $("#page").bootstrapPaginator({
        bootstrapMajorVersion: 3, //对应的bootstrap版本
        currentPage: currentPage, //当前页数
        numberOfPages: 10, //每次显示页数
        totalPages: totalPages, //总页数
        shouldShowPage: true, //是否显示该按钮
        useBootstrapTooltip: true,
        onPageClicked: function(event, originalEvent, type, page) {
        	currentPage =page;
        	query();
        }
    });
$(function() {
	query();
	queryAllClass();
    });
function addStudent(){
	var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
	var emial=$("#email").val();
	if(!StringUtil.isEmpty(emial) && !reg.test(emial)){
		$alert("warning","邮箱格式不正确！")
		return ;
	}
	var phone = $("#phone").val();
	 var myreg=new RegExp("^[1][3,4,5,7,8][0-9]{9}$");
	 if(!StringUtil.isEmpty(phone) && !myreg.test(phone)){
			$alert("warning","手机号码格式不正确！")
			return ;
		}
	var student ={
			name:$("#name").val(),
		    email:emial,
		    classId:$("#classId").val(),
		    phone:phone,
		    dormId:$("#dormId").val(),
		    sex:$("#sex").val(),
		    age:$("#age").val(),
		    stdNumber:$("#stdNumber").val()
	}
	
	$.ajxpost(root+"/student/add",student,function(result){
		query();
		$alert("success",result.msg);
		$("#myModal").modal('hide');
	});
}

function query(page){
	if(page){
		currentPage=page;
	}
	console.log(currentPage);
	var student ={
			name:$("#query-name").val(),
		    classId:$("#query-classId").val(),
		    sex:$("#query-sex").val(),
		    page:currentPage,
	}
	$.ajxpost(root+"/student/query",student,function(result){
		totalPages = result.data.total;
		var dom = '';
		$("#std-tb").html(dom);
		$.each(result.data.data,function(i,item){
			dom+=' <tr><td>'+(i+1)+'</td><td>'+item.stdNumber+'</td>'+
				'<td>'+item.name+'</td>'+
			    '<td>'+item.sex+'</td>'+
			    '<td>'+item.className+'</td>'+
			    '<td>'+item.dormName+'</td>'+
			    '<td>'+item.teacherName+'</td>'+
			    '<td>'+item.phone+'</td><td>';
			    if(curruntRoleId == 0){
			    	dom+= '<button type="button" onClick="getcurrId('+item.id+')" class="btn-sm-del " data-toggle="modal" data-target="#modal-delete">删除</button> ';	
			    }
			dom+= '<button type="button" onClick="initUpdateForm('+item.id+')" class="btn-sm-update " data-toggle="modal" data-target="#modal-update">修改</button>'+
			    '</td></tr>';
		});
		$("#std-tb").html(dom);
		if(totalPages<=0){
			$("#page").html("");
		}else{
			$("#page").bootstrapPaginator({
		        bootstrapMajorVersion: 3, //对应的bootstrap版本
		        currentPage: currentPage, //当前页数
		        numberOfPages: 10, //每次显示页数
		        totalPages: totalPages, //总页数
		        shouldShowPage: true, //是否显示该按钮
		        useBootstrapTooltip: true,
		        onPageClicked: function(event, originalEvent, type, page) {
		        	currentPage =page;
		        	query();
		        }
		    });
		}
		
	});
}

/**************
 * 	 查询重置	  *
***************/
function queryCancel(){
	$("#query-name").val('');
    $("#query-classId").val(null);
    $("#query-sex").val('');
}


function initUpdateForm(id){
	curruntId=id;
	$.ajxpost(root+"/student/getStudent",{'id':id},function(result){
		upqueryDormBySex(result.data.sex);
		queryAllClass();
		$("#up-name").val(result.data.name);
	    $("#up-email").val(result.data.email);
	    $("#up-classId").val(result.data.classId);
	    $("#up-phone").val(result.data.phone);
	    $("#up-dormId").val(result.data.dormId);
	    $("#up-sex").val(result.data.sex);
	    $("#up-age").val(result.data.age);
	    $("#up-stdNumber").val(result.data.stdNumber);
	    
	});
}

/**************
 *   删除      *
***************/
function deleteStudent(){
	$.ajxpost(root+"/student/delete",{id:curruntId},function(result){
		query();
		$alert("success",result.msg)
	});
}
/**************
 *   修改      *
***************/
function updateStudent(){
	var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
	var emial=$("#up-email").val();
	if(!StringUtil.isEmpty(emial) && !reg.test(emial)){
		$alert("warning","邮箱格式不正确！")
		return ;
	}
    var phone = $("#up-phone").val();
	var myreg=new RegExp("^[1][3,4,5,7,8][0-9]{9}$");
	if(!StringUtil.isEmpty(phone) && !myreg.test(phone)){
			$alert("warning","手机号码格式不正确！")
			return ;
	}
	var student ={id:curruntId,
	name:$("#up-name").val(),
	email:$("#up-email").val(),
	classId:$("#up-classId").val(),
	phone:$("#up-phone").val(),
	dormId:$("#up-dormId").val(),
	sex:$("#up-sex").val(),
	age:$("#up-age").val(),
	stdNumber:$("#up-stdNumber").val()
	}
	$.ajxpost(root+"/student/update",student,function(result){
		query();
		$alert("success",result.msg)
		$("#modal-update").modal('hide');
	});
}

function queryDormBySex(){
	var sex = $("#sex").val();
	$.ajxpost(root+"/dorm/getStDormBySex",{'sex':sex},function(result){
		var option ='<option selected value>宿舍</option>';
		$.each(result.data,function(i,item){
			option +='<option value='+item.id+'>'+item.name+'</option>';
		});
		$("#dormId").html(option);
		$("#dormId").removeAttr("disabled");
	});
}
function queryAllClass(){
	$.ajxasync(root+"/class/getAllClass",null,function(result){
		var option ='<option selected value>班级</option>';
		$.each(result.data,function(i,item){
			option +='<option value='+item.id+'>'+item.className+'</option>';
		});
		$("#classId").html(option);
		$("#up-classId").html(option);
		$("#query-classId").html(option);
	});
}

function upqueryDormBySex(sex){
	if(!sex){
		sex = $("#up-sex").val();
	}
	$.ajxasync(root+"/dorm/getStDormBySex",{'sex':sex},function(result){
		var option ='<option selected value>宿舍</option>';
		$.each(result.data,function(i,item){
			option +='<option value='+item.id+'>'+item.name+'</option>';
		});
		$("#up-dormId").html(option);
	});
}



















