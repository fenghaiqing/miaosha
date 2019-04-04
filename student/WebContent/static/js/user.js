/**************
 *   新增用户   *
***************/
function addUser(){
	var user ={
			name:$("#name").val(),
			account:$("#account").val(),
			password:$("#upassword").val(),
			roleId:$("#roleId").val()
	}
	$.ajxpost(root+"/user/add",user,function(result){
		queryUser();
		$alert("success",result.msg);
		$("#myModal").modal('hide');
	});
}
/**************
 *   查询用户   *
***************/
var currentPage=1;
function queryUser(page){
	if(page){
		currentPage=page	
	}
	var user ={
			name:$("#query-name").val(),
			roleId:$("#query-roleId").val(),
			page:currentPage
		}
	$.ajxpost(root+"/user/selectAll",user,function(result){
		var totalPages = result.data.total;
		var dom = '';
		$("#std-tb").html(dom);
		$.each(result.data.data,function(i,item){
			dom+='<tr><td>'+(i+1)+'</td><td>'+item.account+'</td>'+
			    '<td>'+item.name+'</td>'+
			    '<td>'+(item.roleId==0?'管理员':'普通用户')+'</td>';
			if(curruntRoleId==0){
				dom+='<td><button type="button" onClick="getcurrId('+item.id+')" class="btn-sm-del " data-toggle="modal" data-target="#modal-delete">删除</button> '+
			     '<button type="button" onClick="initUserUpdateForm('+item.id+')" class="btn-sm-update " data-toggle="modal" data-target="#modal-update">修改</button></td>'
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
		        	queryUser();
		        }
		    });
		}
	});
}

function initUserUpdateForm(id){
	curruntId=id;
	$.ajxpost(root+"/user/getUser",{id:curruntId},function(result){
		$("#up-name").val(result.data.name);
		$("#up-account").val(result.data.account);
		$("#up-password").val(result.data.password);
		$("#up-roleId").val(result.data.roleId);
	});
}

/**************
 *   修改用户   *
***************/
function updateUser(){
	var user ={
			id:curruntId,
			name:$("#up-name").val(),
			account:$("#up-account").val(),
			password:$("#up-password").val(),
			roleId:$("#up-roleId").val()
	}
	$.ajxpost(root+"/user/update",user,function(result){
		queryUser();
		$alert("success",result.msg);
		$("#modal-update").modal('hide');
	});
}

/**************
 *   删除用户  *
***************/
function deleteUser(){
	$.ajxpost(root+"/user/delete",{id:curruntId},function(result){
		queryUser();
		$alert("success",result.msg);
	});
}
/**************
 * 重置查询参数  *
***************/
function restQueryParam(){
	$("#query-name").val('');
	$("#query-roleId").val('');
}


$(function(){queryUser();});