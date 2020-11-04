<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Panagea - Premium site template for travel agencies, hotels and restaurant listing.">
    <meta name="author" content="Ansonika">
    <title>Login</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/frontend/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/frontend/css/style.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/frontend/css/vendors.css" rel="stylesheet">
    <link href="css/custom.css" rel="stylesheet">

</head>

<body id="login_bg">
	<nav id="menu" class="fake_menu"></nav>
	
	<div id="preloader">
		
		<div data-loader="circle-side"></div>
		
	</div>
	
	<div id="login">
	<img src="<%=request.getContextPath()%>/frontend/image/login_bg.jpg" width="1536" height="721">
		<aside>
			  <form action="<%=request.getContextPath()%>/LoginHandler.do" name="form_login" method="post">
				<div class="form-group">
					<label>Account</label>
					<input type="text" class="form-control" name="account" id="account" placeholder="帳號" value="">
					<i class="icon_mail_alt"></i>
				</div>
				<div class="form-group">
					<label>Password</label>
					<input type="password" class="form-control" name="password" id="password" placeholder="密碼" value="">
					<i class="icon_lock_alt"></i>
				</div>
				<div style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif">${errorMsgs.error}</div>
				<div class="clearfix add_bottom_30">
					<div class="float-right mt-1"><a id="forgot" href="<%=request.getContextPath()%>/frontend/member/forgotPassword.jsp">忘記密碼?</a></div>
				</div>
				<a href="javascript:document.form_login.submit();" class="btn_1 rounded full-width">登入</a>
				<div class="text-center add_top_10">還沒有會員? <strong><a href="<%=request.getContextPath()%>/frontend/member/signUp.jsp">按此加入!</a></strong></div>
			</form>
		</aside>
	</div>

    <script src="<%=request.getContextPath()%>/frontend/js/common_scripts.js"></script>
    <script src="<%=request.getContextPath()%>/frontend/js/main.js"></script>
  
</body>
</html>