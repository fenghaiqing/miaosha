/**************
 *   新增班级   *
***************/
function addClass(){
	var classes ={
			className:$("#className").val(),
			teacherName:$("#teacherName").val(),
			teacherNum:$("#teacherNum").val(),
			teacherPhone:$("#teacherPhone").val()
	}
	$.ajxpost(root+"/class/add",classes,function(result){
		queryClass();
		$alert("success",result.msg);
		$("#myModal").modal('hide');
	});
}
/**************
 *   查询班级   *
***************/
var currentPage=1;
function queryClass(page){
	if(page){
		currentPage=page;
	}
	var classes ={
			className:$("#query-className").val(),
			teacherName:$("#query-teacherName").val(),
			page:currentPage,
		}
	$.ajxpost(root+"/class/select",classes,function(result){
		var dom = '';
		var totalPages = result.data.total;;
		$("#std-tb").html(dom);
		$.each(result.data.data,function(i,item){
			dom+='<tr><td>'+(i+1)+'</td><td>'+item.className+'</td>'+
			    '<td>'+item.teacherName+'</td>'+
			    '<td>'+item.teacherNum+'</td>'+
			    '<td>'+item.teacherPhone+'</td>';
			if(curruntRoleId==0){
				dom+='<td><button type="button" onClick="getcurrId('+item.id+')" class="btn-sm-del " data-toggle="modal" data-target="#modal-delete">删除</button> '+
			     '<button type="button" onClick="initClassUpdateForm('+item.id+')" class="btn-sm-update " data-toggle="modal" data-target="#modal-update">修改</button>'+
			    '</td>';
			}
			
			dom+='</tr>';
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
		        	queryClass();
		        }
		    });
		}
	});
}

function initClassUpdateForm(id){
	curruntId=id;
	$.ajxpost(root+"/class/getStClass",{id:curruntId},function(result){
		$("#up-className").val(result.data.className);
		$("#up-teacherName").val(result.data.teacherName);
		$("#up-teacherNum").val(result.data.teacherNum);
		$("#up-teacherPhone").val(result.data.teacherPhone);
	});
}

/**************
 *   修改班级   *
***************/
function updateDorm(){
	var classes ={
			id:curruntId,
			className:$("#up-className").val(),
			teacherName:$("#up-teacherName").val(),
			teacherNum:$("#up-teacherNum").val(),
			teacherPhone:$("#up-teacherPhone").val()
	}
	$.ajxpost(root+"/class/update",classes,function(result){
		queryClass();
		$alert("success",result.msg);
		$("#modal-update").modal('hide');
	});
}

/**************
 *   删除班级  *
***************/
function deleteClass(){
	$.ajxpost(root+"/class/delete",{id:curruntId},function(result){
		queryClass();
		$alert("success",result.msg);
	});
}
/**************
 * 重置查询参数  *
***************/
function restQueryParam(){
	$("#query-className").val('');
	$("#query-teacherName").val('');
}


$(function(){queryClass();});