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
           <li ><a href="${root}/student/index"><i class="fa fa-graduation-cap" aria-hidden="true"></i>学生信息管理</a>
            <li ><a href="${root}/dorm/index"><i class="icon-home"></i>宿舍管理</a>
            <li class="active"><a href="${root}/class/index"><i class="fa fa-sitemap" aria-hidden="true"></i>班级管理</a>
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
                        <label >班级名称： </label>
                        <input type="text" id="query-className" class="form-control mb-2 mr-sm-2 mb-sm-0" >
                        <label >班主任： </label>
                        <input type="text" id="query-teacherName" class="form-control mb-2 mr-sm-2 mb-sm-0" >

                        <button type="button" class="btn btn-general btn-blue mr-2" onclick="queryClass(1)">查询</button>
                         <c:if test="${roleId eq 0}">
                        <button type="button" data-toggle="modal" data-target="#myModal"  class="btn btn-general btn-outline-success mr-2">新增</button>
                        </c:if>
                        <button type="button" class="btn btn-general btn-outline-secondary" onclick="restQueryParam()">重置</button>
                    </form>
                    <hr>
                    <table class="table">
                        <thead>
                        <tr>
                         <th>序号</th>
                            <th>班级名称</th>
                            <th>班主任</th>
                            <th>班主任编号</th>
                            <th>联系电话</th>
                             <c:if test="${roleId eq 0}"><th>操作</th></c:if>
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
    <div class="modal-dialog modal-sm">
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
                                <label   class=" col-form-label">班级名称：</label>
                            </div>
                            <div class="form-group row">

                                <label  class=" col-form-label">班主任：</label>

                            </div>
                            <div class="form-group row">
                                <label  class=" col-form-label">班主任编号：</label>

                            </div>
                            <div class="form-group row">
                                <label  class=" col-form-label">联系电话：</label>

                            </div>

                        </div>
                        <div class="col-md-6">
                            <div class="form-group row">
                                    <input class="form-control" id="className" type="text"  >
                            </div>
                            <div class="form-group row">
                                <input class="form-control" id="teacherName" type="text"  >
                            </div>
                            <div class="form-group row">
                                    <input class="form-control" id="teacherNum" type="text"  >
                            </div>
                            <div class="form-group row">
                                <input class="form-control" id="teacherPhone" type="text"  >
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 模态框底部 -->
            <div class="modal-footer">
                <button type="button" onclick="addClass()" class="btn btn-primary" >提交</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
            </div>

        </div>
    </div>
</div>
<div class="modal fade" id="modal-update">
    <div class="modal-dialog modal-sm">
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
                                <label   class=" col-form-label">班级名称：</label>
                            </div>
                            <div class="form-group row">

                                <label  class=" col-form-label">班主任：</label>

                            </div>
                            <div class="form-group row">
                                <label  class=" col-form-label">班主任编号：</label>

                            </div>
                            <div class="form-group row">
                                <label  class=" col-form-label">联系电话：</label>

                            </div>

                        </div>
                        <div class="col-md-6">
                            <div class="form-group row">
                                <input class="form-control" type="text" id="up-className" >
                            </div>
                            <div class="form-group row">
                                <input class="form-control" type="text" id="up-teacherName" >
                            </div>
                            <div class="form-group row">
                                <input class="form-control" type="text"  id="up-teacherNum">
                            </div>
                            <div class="form-group row">
                                <input class="form-control" type="text"  id="up-teacherPhone">
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 模态框底部 -->
            <div class="modal-footer">
                <button type="button" onclick="updateDorm()" class="btn btn-primary" >提交</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
            </div>

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
                <button type="button" onclick="deleteClass()" class="btn btn-danger" data-dismiss="modal">确定</button>
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
<script src="${root}/static/bootstrap/js/bootstrap-paginator.js"></script>
<script src="${root}/static/js/front.js"></script>
<script src="${root}/static/js/Util.js"></script>
<script src="${root}/static/js/class.js"></script>

</body>

</html>