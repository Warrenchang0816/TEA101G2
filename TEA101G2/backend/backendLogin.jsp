<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%

	LinkedList<String> errorMsgs = (LinkedList<String>) request.getAttribute("errorMsgs");
	String empAccount = (String)request.getAttribute("empAccount");
	String empPassword = (String)request.getAttribute("empPassword");

%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Panagea - Premium site template for travel agencies, hotels and restaurant listing.">
    <meta name="author" content="Ansonika">
    <title>Panagea | Premium site template for travel agencies, hotels and restaurant listing.</title>

    <!-- Favicons-->
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/backend/img/favicon.ico" type="image/x-icon">
    <link rel="apple-touch-icon" type="image/x-icon" href="<%=request.getContextPath()%>/backend/img/apple-touch-icon-57x57-precomposed.png">
    <link rel="apple-touch-icon" type="image/x-icon" sizes="72x72" href="<%=request.getContextPath()%>/backend/img/apple-touch-icon-72x72-precomposed.png">
    <link rel="apple-touch-icon" type="image/x-icon" sizes="114x114" href="<%=request.getContextPath()%>/backend/img/apple-touch-icon-114x114-precomposed.png">
    <link rel="apple-touch-icon" type="image/x-icon" sizes="144x144" href="<%=request.getContextPath()%>/backend/img/apple-touch-icon-144x144-precomposed.png">

    <!-- GOOGLE WEB FONT -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">

    <!-- BASE CSS -->
    <link href="<%=request.getContextPath()%>/backend/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/backend/css/style.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/backend/css/vendors.css" rel="stylesheet">

    <!-- YOUR CUSTOM CSS -->
    <link href="<%=request.getContextPath()%>/backend/css/custom.css" rel="stylesheet">

</head>

<body id="login_bg">
	
	<nav id="menu" class="fake_menu"></nav>
	
	<div id="preloader">
		<div data-loader="circle-side"></div>
	</div>
	<!-- End Preload -->
	
	<div id="login">
		<aside>
			<figure>
				<a href="index.html"><img src="<%=request.getContextPath()%>/backend/img/logo_sticky.png" width="155" height="36" data-retina="true" alt="" class="logo_sticky"></a>
			</figure>
			
			
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/LoginHandler" enctype="multipart/form-data"> 

				<div class="access_social">
					<a href="#0" class="social_bt facebook">Login with Facebook</a>
					<a href="#0" class="social_bt google">Login with Google</a>
					<a href="#0" class="social_bt linkedin">Login with Linkedin</a>
				</div>
				<div class="divider"><span>Or</span></div>
				<div class="form-group">
					<label>員工帳號</label>
					<input type="account" class="form-control" name="empAccount" id="empAccount"
						value="<%= (empAccount == null)? "" : empAccount%>"/>
					<span style="color:red"><%= (errorMsgs ==null)? "" : (empAccount.equals(""))? "帳號請勿空白" : (!empAccount.equals("") && errorMsgs == null )? " " : (!empAccount.equals("") && errorMsgs != null )? "  " + errorMsgs.poll(): "" %></span>
					<i class="icon_mail_alt"></i>
				</div>
				<div class="form-group">
					<label>員工密碼</label>
					<input type="password" class="form-control" name="empPassword" id="empPassword" >
					<span style="color:red"><%=  (errorMsgs ==null)? "" : (empPassword.equals("") && empAccount.equals(""))? "密碼請勿空白" : "密碼錯誤"%></span>
					<i class="icon_lock_alt"></i>
				</div>
				<div class="clearfix add_bottom_30">
					<div class="checkboxes float-left">
						<label class="container_check">Remember me
						  <input type="checkbox">
						  <span class="checkmark"></span>
						</label>
					</div>
					<div class="float-right mt-1"><a id="forgot" href="javascript:void(0);">Forgot Password?</a></div>
				</div>
				
				<button name="login" type="submit" class="btn_1 rounded full-width">登入</button>
				<a class="btn_1 rounded full-width" href='<%=request.getContextPath()%>/backend/backendLogin.jsp'>取消</a>
			
			</form>
			
			
			<div class="copy">© 2018 Panagea</div>
		</aside>
	</div>
	<!-- /login -->
		
	<!-- COMMON SCRIPTS -->
    <script src="<%=request.getContextPath()%>/backend/js/common_scripts.js"></script>
    <script src="<%=request.getContextPath()%>/backend/js/main.js"></script>
	<script src="<%=request.getContextPath()%>/backend/js/validate.js"></script>	
  
</body>

<script>

var empAccount = document.getElementById('empAccount');
empAccount.addEventListener("change", function() {
	var empAccount = document.getElementById('empAccount').value;
	var MyPoint2 = "/OnlineWS/" + empAccount;
	var host2 = window.location.host;
	var path2 = window.location.pathname;
	var webCtx2 = path2.substring(0, path2.indexOf('/', 1));
	var endPointURL2 = "ws://" + host2 + webCtx2 + MyPoint2;
	//websocket 有專屬的通訊協定 ws://
	
	var webSocket;

		console.log("endPointURL2:" + endPointURL2);
		// create a websocket
		webSocket = new WebSocket(endPointURL2);
		
		webSocket.onopen = function(event) {
			console.log(empAccount + "online");
		};
		
});

</script>

</html>