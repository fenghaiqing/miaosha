/**************
 *   新增宿舍   *
***************/
function addDorm(){
	var dorm ={
			name:$("#name").val(),
			sex:$("#sex").val(),
			maxNum:$("#maxNum").val()
	}
	$.ajxpost(root+"/dorm/add",dorm,function(result){
		queryDorm();
		$alert("success",result.msg);
		$("#myModal").modal('hide');
	});
}
/**************
 *   查询宿舍   *
***************/
var currentPage=1;
function queryDorm(page){
	if(page){
		currentPage=page;
	}
	var dorm ={
			name:$("#query-name").val(),
			sex:$("#query-sex").val(),
			page:currentPage
		}
	$.ajxpost(root+"/dorm/selset",dorm,function(result){
		var dom = '';
		var totalPages = result.data.total;
		$("#std-tb").html(dom);
		$.each(result.data.data,function(i,item){
			dom+='<tr><td>'+(i+1)+'</td><td>'+item.name+'</td>'+
			    '<td>'+item.sex+'</td>'+
			    '<td>'+item.maxNum+'</td>'+
			    '<td>'+item.surplusNum+'</td>';
			if(curruntRoleId == 0){
				dom+='<td><button type="button" onClick="getcurrId('+item.id+')" class="btn-sm-del " data-toggle="modal" data-target="#modal-delete">删除</button> '+
			     '<button type="button" onClick="initDormUpdateForm('+item.id+')" class="btn-sm-update " data-toggle="modal" data-target="#modal-update">修改</button>'+
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
		        	queryDorm();
		        }
		    });
		}
	});
}

function initDormUpdateForm(id){
	curruntId=id;
	$.ajxpost(root+"/dorm/getStDorm",{id:curruntId},function(result){
		$("#up-name").val(result.data.name);
		$("#up-sex").val(result.data.sex);
		$("#up-maxNum").val(result.data.maxNum);
	});
}

/**************
 *   修改宿舍   *
***************/
function updateDorm(){
	var dorm ={
			id:curruntId,
			name:$("#up-name").val(),
			sex:$("#up-sex").val(),
			maxNum:$("#up-maxNum").val()
		}
	$.ajxpost(root+"/dorm/update",dorm,function(result){
		queryDorm();
		$alert("success",result.msg);
		$("#modal-update").modal('hide');
	});
}

/**************
 *   删除宿舍   *
***************/
function deleteDorm(){
	$.ajxpost(root+"/dorm/delete",{id:curruntId},function(result){
		queryDorm();
		$alert("success",result.msg);
	});
}
/**************
 * 重置查询参数  *
***************/
function restQueryParam(){
	$("#query-name").val('');
	$("#query-sex").val('');
}


$(function(){queryDorm();});