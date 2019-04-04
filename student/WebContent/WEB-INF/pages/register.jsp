<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="all,follow">

    <title>学生信息管理系统 </title>
   <!--  <link rel="shortcut icon" href="../img/favicon.ico"> -->
    
    <!-- global stylesheets -->
    <link rel="stylesheet" href="${root}/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${root}/static/css/style.default.css" id="theme-stylesheet">
    <!-- Core stylesheets -->
    <link rel="stylesheet" href="${root}/static/css/login.css">
</head>

<body> 

<!--====================================================
                        PAGE CONTENT
======================================================--> 
      <section class="hero-area">
        <div class="overlay"></div>
        <div class="container">
          <div class="row">
            <div class="col-xs-12  col-md-12 ">
                <div class="contact-h-cont">
                  <h3 class="text-center" style="color:#4a8aff ">注册中心</h3><br>
                  <form>
                    <div class="form-group">
                      <label for="username">学号</label>
                      <input type="text" class="form-control" id="account" placeholder="用于登录的账号">
                    </div>  
                    <div class="form-group">
                      <label for="username">用户名</label>
                      <input type="text" class="form-control" id="name" placeholder="用户名">
                    </div>  
                    <div class="form-group">
                      <label >密码</label>
                      <input class="form-control" type="password" id="password"  placeholder="请输入密码">
                    </div>   
                     <div class="form-group">
                      <label >确认密码</label>
                      <input class="form-control" type="password" id="cfpassword"  placeholder="请输入密码">
                    </div>   
                    <button class="btn btn-general btn-blue" role="button" type="button" onclick="register();"><i fa fa-right-arrow></i>注册</button>
                  </form>
                </div>
            </div>
          </div>  
        </div>
      </section>
      
    <!--Global Javascript -->
    <script src="${root}/static/jquery/jquery.min.js"></script>
    <script src="${root}/static/bootstrap/js/bootstrap.min.js"></script>
    <script src="${root}/static/js/Util.js"></script>
    <script src="${root}/static/js/login.js"></script>
</body>

</html>