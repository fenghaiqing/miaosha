function dologin(){
	 var account = $("#account").val();
	 var password =$("#password").val();
	 
	 $.ajxpost(root+"/user/login",{'account':account,'password':password},
			 function(result){
		 window.location.href=root+"/student/index";
	 })
}

function register(){
	 var account = $("#account").val();
	 var password =$("#password").val();
	 var cfpassword =$("#cfpassword").val();
	 var name =$("#name").val();
	 if(isEmpty(account)){
		 $alert("warning","学号不能为空！");
		 return 
	 }
	 if(isEmpty(name)){
		 $alert("warning","用户名不能为空！");
		 return 
	 }
	 if(isEmpty(password)){
		 $alert("warning","密码不能为空！");
		 return 
	 }
	 if(password !== cfpassword){
		 $alert("warning","两次输入密码不一致，请重新输入！");
		 return 
	 }
	 $.ajxpost(root+"/user/doregister",{
		 'account':account,
		 'password':password,
		 'cfpassword':cfpassword,
		 'name':name
	 },
			 function(result){
			 $alert("success","注册成功！正跳转到登录页面...")
			 setTimeout(function(){
				 window.location.href=root+"/user/toLogin";
			 },1000);
	 });
}

function isEmpty(str){
    if(str != undefined && str != null && str.length > 0){
        return false;
    }else{
        return true;
    }
}