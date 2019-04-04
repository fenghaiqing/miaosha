<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<c:set var="roleId" value="${sessionScope.user.roleId}"/>
<!DOCTYPE html>
<html>

<jsp:include page="head.jsp"></jsp:include>
<body>

<!--====================================================
                         MAIN NAVBAR
======================================================-->
<jsp:include page="navbar.jsp"></jsp:include>

<!--====================================================
                        PAGE CONTENT
======================================================-->
<div class="page-content d-flex align-items-stretch">

    <!--***** SIDE NAVBAR *****-->
    <nav class="side-navbar">

        <!-- Sidebar Navidation Menus-->
        <ul class="list-unstyled">
            <li class="active"><a href="${root}/student/index"><i class="fa fa-graduation-cap" aria-hidden="true"></i>学生信息管理</a>
            <li ><a href="${root}/dorm/index"><i class="icon-home"></i>宿舍管理</a>
            <li ><a href="${root}/class/index"><i class="fa fa-sitemap" aria-hidden="true"></i>班级管理</a>
            <li ><a href="${root}/user/index"><i class="fa fa-users" aria-hidden="true"></i>用户管理</a>
            </li>
        </ul>
    </nav>

    <div class="content-inner form-cont">
        <div class="row">
            <div class="col-md-12">
                <!--***** INLINE FORM *****-->
                <div class="card form" id="form6">
                    <form class="form-inline">
                        <label>姓名： </label>
                        <input type="text" id="query-name" class="form-control mb-2 mr-sm-2 mb-sm-0"  placeholder="姓名">
                        <label class="mr-sm-2" >性别： </label>
                        <select id="query-sex" class="custom-select mb-2 mr-sm-2 mb-sm-0" >
                            <option selected value>性别</option>
                            <option value="男">男</option>
                            <option value="女">女</option>
                        </select>
                        <label class="mr-sm-2"  >班级： </label>
                        <select class="custom-select mb-2 mr-sm-2 mb-sm-0" id="query-classId" >
                            <option selected value>班级</option>
                           
                        </select>

                        <button type="button" class="btn btn-general btn-blue mr-2" onclick="query(1);">查询</button>
                         <c:if test="${roleId eq 0}">
                        <button type="button" data-toggle="modal" data-target="#myModal" class="btn btn-general btn-outline-success mr-2">新增</button>
                        </c:if>
                        <button type="reset" class="btn btn-general btn-outline-secondary" onclick="queryCancel()">重置</button>
                    </form>
                    <hr>
                    <table class="table">
                        <thead>
                        <tr>
                         <th>序号</th>
                            <th>学号</th>
                            <th>姓名</th>
                            <th>性别</th>
                            <th>班级</th>
                            <th>宿舍</th>
                            <th>班主任</th>
                            <th>联系电话</th>
                            <td>操作</td>
                        </tr>
                        </thead>
                        <tbody id="std-tb">
               
                        </tbody>
                    </table>
                    <ul id="page"></ul>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">

            <!-- 模态框头部 -->
            <div class="modal-header">
                <h6 >新增</h6>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- 模态框主体 -->
            <div class="modal-body">
                <div class="card form" id="form2">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group row">
                                <label  style="width: 85px;" class=" col-form-label"><span style="color:red">*</span>姓名：</label>
                                <div class="col-9">
                                	 <input class="form-control" type="text" id="name" >
                                </div>
                            </div>
                            <div class="form-group row">
                                <label  style="width: 85px;" class=" col-form-label"><span style="color:red">*</span>学号：</label>
                                <div class="col-9">
                                    <input class="form-control" type="search" id="stdNumber" >
                                </div>
                            </div>
                            <div class="form-group row">
                                <label  style="width: 85px;" class=" col-form-label">邮箱：</label>
                                <div class="col-9">
                                    <input class="form-control" type="email" id="email" >
                                </div>
                            </div>
                            <div class="form-group row">
                                <label  style="width: 85px;" class=" col-form-label">联系电话</label>
                                <div class="col-9">
                                    <input class="form-control" type="tel" id="phone"  >
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group row">

                                <label style="width: 85px;" class=" col-form-label"><span style="color:red">*</span>性别：</label>
                                <div class="col-9">
                                    <select class="custom-select mb-2 mr-sm-2 mb-sm-0" id="sex" onchange="queryDormBySex()"  >
                                        <option selected value>性别</option>
                                        <option value="男">男</option>
                                        <option value="女">女</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label  style="width: 85px;" class=" col-form-label">年龄：</label>
                                <div class="col-9">
                                    <input class="form-control" type="number" id="age"  >
                                </div>
                            </div>
                            <div class="form-group row">
                                <label  style="width: 85px;" class=" col-form-label"><span style="color:red">*</span>班级：</label>
                                <div class="col-9">
                                    <select class="form-control " id="classId" >
                                        <option selected value>班级</option>
                           
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label  style="width: 85px;" class=" col-form-label"><span style="color:red">*</span>宿舍号：</label>
                                <div class="col-9">
                                
                                    <select class="form-control " id="dormId" disabled="disabled" >
                                        <option selected value>宿舍</option>
                                    </select>
                                   
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>

            <!-- 模态框底部 -->
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="addStudent()" >提交</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
            </div>

        </div>
    </div>
</div>
<div class="modal fade" id="modal-update">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">

            <!-- 模态框头部 -->
            <div class="modal-header">
                <h6 >修改</h6>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- 模态框主体 -->
            <div class="modal-body">
                <div class="card form" >
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group row">
                                <label style="width: 85px;" class=" col-form-label"><span style="color:red">*</span>姓名：</label>
                                <div class="col-9">
                                <c:if test="${roleId eq 0}">
                                <input class="form-control" type="text" id="up-name" >
                                </c:if>
                                <c:if test="${roleId eq 1}">
                                    <input class="form-control" type="text" id="up-name" disabled="disabled" >
                                </c:if>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label  style="width: 85px;" class="col-form-label"><span style="color:red">*</span>学号：</label>
                                <div class="col-9">
                                <c:if test="${roleId eq 0}">
                                    <input class="form-control" type="text" id="up-stdNumber" >
                                    </c:if>
                                     <c:if test="${roleId eq 1}">
                                    <input class="form-control" type="text" id="up-stdNumber" disabled="disabled">
                                    </c:if>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label  style="width: 85px;" class=" col-form-label">邮箱：</label>
                                <div class="col-9">
                                    <input class="form-control" type="email" id="up-email" >
                                </div>
                            </div>
                            <div class="form-group row">
                                <label  style="width: 85px;" class=" col-form-label">联系电话</label>
                                <div class="col-9">
                                    <input class="form-control" type="tel" id="up-phone" >
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group row">

                                <label style="width: 85px;" class=" col-form-label"><span style="color:red">*</span>性别：</label>
                                <div class="col-9">
                                <c:if test="${roleId eq 0}">
                                    <select class="custom-select mb-2 mr-sm-2 mb-sm-0" id="up-sex" onchange="upqueryDormBySex()">
                                        <option selected value>性别</option>
                                        <option value="男">男</option>
                                        <option value="女">女</option>
                                    </select>
                                    </c:if>
                                     <c:if test="${roleId eq 1}">
                                    <select class="custom-select mb-2 mr-sm-2 mb-sm-0" id="up-sex" onchange="upqueryDormBySex()" disabled="disabled">
                                        <option selected>性别</option>
                                        <option value="男">男</option>
                                        <option value="女">女</option>
                                    </select>
                                    </c:if>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label  style="width: 85px;" class=" col-form-label">年龄：</label>
                                <div class="col-9">
                                    <input class="form-control" type="number" id="up-age" >
                                </div>
                            </div>
                            <div class="form-group row">
                                <label  style="width: 85px;" class=" col-form-label"><span style="color:red">*</span>班级：</label>
                                <div class="col-9">
                                 <c:if test="${roleId eq 0}">
                                    <select class="form-control " id="up-classId">
                                        <option selected>班级</option>
                                        
                                    </select>
                                    </c:if>
                                     <c:if test="${roleId eq 1}">
                                    <select class="form-control " id="up-classId" disabled="disabled">
                                        <option selected>班级</option>
                                     
                                    </select>
                                    </c:if>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label  style="width: 85px;" class=" col-form-label"><span style="color:red">*</span>宿舍号：</label>
                                <div class="col-9">
                                  <c:if test="${roleId eq 0}">
                                    <select class="form-control " id="up-dormId" >
                                        <option selected value="">宿舍</option>
                                    
                                    </select>
                                    </c:if>
                                     <c:if test="${roleId eq 1}">
                                    <select class="form-control " id="up-dormId" disabled="disabled">
                                        <option selected value="">宿舍</option>
                                    
                                    </select>
                                    </c:if>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>

            <!-- 模态框底部 -->
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="updateStudent()" >提交</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
            </div>

        </div>
    </div>
</div>
<div class="modal fade" id="modal-delete">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <!-- 模态框头部 -->
            <div class="modal-header">
                <h6 >删除</h6>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- 模态框主体 -->
            <div class="modal-body">
                确定要删除吗？
            </div>

            <!-- 模态框底部 -->
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" onclick="deleteStudent();" data-dismiss="modal">确定</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
            </div>

        </div>
    </div>
</div>

<!--Global Javascript -->
<script src="${root}/static/jquery/jquery.min.js"></script>
<script src="${root}/static/js/popper.min.js"></script>
<script src="${root}/static/jquery/jquery.validate.min.js"></script>
<script src="${root}/static/jquery/jquery.cookie.js"></script>
<script src="${root}/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${root}/static/js/front.js"></script>
<script src="${root}/static/bootstrap/js/bootstrap-paginator.js"></script>
<script src="${root}/static/js/Util.js"></script>
<script src="${root}/static/js/index.js"></script>
</body>

</html>
