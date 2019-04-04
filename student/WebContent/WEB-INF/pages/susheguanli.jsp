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
            <li class="active"><a href="${root}/dorm/index"><i class="icon-home"></i>宿舍管理</a>
            <li><a href="${root}/class/index"><i class="fa fa-sitemap" aria-hidden="true"></i>班级管理</a>
            <li  ><a href="${root}/user/index"><i class="fa fa-users" aria-hidden="true"></i>用户管理</a>
            </li>
        </ul>
    </nav>

    <div class="content-inner form-cont">

        <div class="row">
            <div class="col-md-12">
                <!--***** INLINE FORM *****-->
                <div class="card form" id="form6">
                    <form class="form-inline">
                        <label >宿舍号： </label>
                        <input type="text" class="form-control mb-2 mr-sm-2 mb-sm-0" id="query-name" >
                        <label class="mr-sm-2" >性别： </label>
                        <select class="custom-select mb-2 mr-sm-2 mb-sm-0" id="query-sex">
                            <option selected value>性别</option>
                            <option value="男">男</option>
                            <option value="女">女</option>
                        </select>
                        <button type="button" onclick="queryDorm(1)" class="btn btn-general btn-blue mr-2">查询</button>
                        <c:if test="${roleId eq 0}">
                        <button type="button" data-toggle="modal" data-target="#myModal" class="btn btn-general btn-outline-success mr-2">新增</button>
                        </c:if>
                        <button type="reset" class="btn btn-general btn-outline-secondary" onclick="restQueryParam();">重置</button>
                    </form>
                    <hr>
                    <table class="table">
                        <thead>
                        <tr>
                         <th>序号</th>
                            <th>宿舍号</th>
                            <th>性别</th>
                            <th>床位数</th>
                            <th>已使用床位数</th>
                            <c:if test="${roleId eq 0}">
                            <th>操作</th>
                            </c:if>
                            
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
                        <div class="col-md-4">
                            <div class="form-group row">
                                <label   class=" col-form-label">宿舍号：</label>

                            </div>
                            <div class="form-group row">

                                <label  class=" col-form-label">性别：</label>

                            </div>
                            <div class="form-group row">
                                <label  class=" col-form-label">床位数：</label>

                            </div>

                        </div>
                        <div class="col-md-8">
                            <div class="form-group row">
                                    <input class="form-control" id="name" type="text"  >
                            </div>
                            <div class="form-group row">

                                    <select class="custom-select mb-2 mr-sm-2 mb-sm-0" id="sex">
                                        <option selected value>性别</option>
                                        <option value="男">男</option>
                                        <option value="女">女</option>
                                    </select>

                            </div>
                            <div class="form-group row">
                                    <input class="form-control" type="search" id="maxNum" >
                            </div>

                        </div>
                    </div>
                </div>
            </div>

            <!-- 模态框底部 -->
            <div class="modal-footer">
                <button type="button" onclick="addDorm()" class="btn btn-primary" >提交</button>
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
                        <div class="col-md-4">
                            <div class="form-group row">
                                <label   class=" col-form-label">宿舍号：</label>

                            </div>
                            <div class="form-group row">

                                <label  class=" col-form-label">性别：</label>

                            </div>
                            <div class="form-group row">
                                <label  class=" col-form-label">床位数：</label>

                            </div>

                        </div>
                        <div class="col-md-8">
                            <div class="form-group row">
                                <input class="form-control" type="text" id="up-name" >
                            </div>
                            <div class="form-group row">

                                <select class="custom-select mb-2 mr-sm-2 mb-sm-0" id="up-sex">
                                    <option selected value>性别</option>
                                    <option value="男">男</option>
                                    <option value="女">女</option>
                                </select>

                            </div>
                            <div class="form-group row">
                                <input class="form-control" type="number"  id="up-maxNum">
                            </div>

                        </div>
                    </div>
                </div>
            </div>

            <!-- 模态框底部 -->
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="updateDorm()">提交</button>
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
                <button type="button" class="btn btn-danger" onclick="deleteDorm()" data-dismiss="modal">确定</button>
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
<script src="${root}/static/js/sushe.js"></script>
</body>

</html>