<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<c:set var="roleId" value="${sessionScope.user.roleId}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
</head>
<body>
<header class="header">
    <nav class="navbar navbar-expand-lg ">
      
        <div class="container-fluid ">
            <div class="navbar-holder d-flex align-items-center justify-content-between">
                <div class="navbar-header">
                    <a href="index.html" class="navbar-brand">
                        <div class="brand-text brand-big hidden-lg-down">
                           学生管理系统
                        </div>
                    </a>
                    <a id="toggle-btn" href="#" class="menu-btn active">
                        <span></span>
                        <span></span>
                        <span></span>
                    </a>
                </div>
            </div>
            <ul class="nav-menu list-unstyled d-flex flex-md-row align-items-md-center">
				<li class="nav-item">${sessionScope.user.name}，欢迎登录</li>
                <li class="nav-item dropdown">
                <a id="profile" class="nav-link logout" data-target="#" href="#"
                                                 data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                 <img src="${root}/static/img/head.png" alt="..." class="img-fluid rounded-circle"
                        style="height: 30px; width: 30px;">...</a>
                    <ul aria-labelledby="profile" class="dropdown-menu profile">

                        <li>
                            <a rel="nofollow" href="javaScript:void(0)" data-toggle="modal" data-target="#update-password" class="dropdown-item">
                                <div class="notification">
                                    <div class="notification-content"><i class="fa fa-cog"></i>修改密码</div>
                                </div>
                            </a>
                        </li>

                        <li>
                            <a rel="nofollow" href="javaScript:void(0);" class="dropdown-item">
                                <div class="notification">
                                    <div class="notification-content pf"><i class="fa fa-diamond" aria-hidden="true"></i>主题
                                    <span class="pf-s1" onclick="checkPf(1)">&nbsp;</span>
                                    <span class="pf-s2" onclick="checkPf(2)">&nbsp;</span>
                                    <span class="pf-s3" onclick="checkPf(3)">&nbsp;</span>
                                    </div>
                                </div>
                            </a>
                            <hr>
                        </li>
                        <li>
                            <a rel="nofollow" href="${root}/user/logout" class="dropdown-item">
                                <div class="notification">
                                    <div class="notification-content"><i class="fa fa-power-off"></i>退出</div>
                                </div>
                            </a>
                        </li>
                    </ul>
                </li>

            </ul>
        </div>
    </nav>
</header>

<div class="modal fade" id="update-password">
    <div class="modal-dialog modal-md">
        <div class="modal-content">

            <!-- 模态框头部 -->
            <div class="modal-header">
                <h6 >修改密码</h6>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- 模态框主体 -->
            <div class="modal-body">
                <div class="card form" >
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group row">
                                <label   class=" col-form-label">原密码：</label>
                            </div>
                            <div class="form-group row">

                                <label  class=" col-form-label">新密码：</label>

                            </div>
                            <div class="form-group row">
                                <label  class=" col-form-label">确认密码：</label>

                            </div>
                        </div>
                        <div class="col-md-8">
                            <div class="form-group row">
                            <input class="form-control" type="hidden" id="curruntRoleId" value=${roleId} >
                                <input class="form-control" type="password" id="password" value="" >
                            </div>
                            <div class="form-group row">

                                <input class="form-control" type="password" id="newpassword" value="">

                            </div>
                            <div class="form-group row">
                                 <input class="form-control" type="password" id="cfpassword" value="">
                                  <input class="form-control" type="hidden" id="userid" value=${sessionScope.user.id }>
                            </div>

                        </div>
                    </div>
                </div>
            </div>

            <!-- 模态框底部 -->
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="restpassword()">提交</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
            </div>

        </div>
    </div>
</div>

</div>
</body>
</html>