<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.formList.model.*"%>
<%@ page import="com.member.model.*"%>

<%
	LinkedList<String> errorMsgs = (LinkedList<String>) request.getAttribute("errorMsgs");

FormListVO formListVO = (FormListVO)request.getAttribute("addFormList");

MemberServiceB ms = new MemberServiceB();
MemberVO loginMember = (MemberVO) session.getAttribute("userVO");

Base64.Encoder encode = Base64.getEncoder();
%>




<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Panagea - Premium site template for travel agencies, hotels and restaurant listing.">
    <meta name="author" content="Ansonika">
    <title>新增客服表單</title>

	<!-- GOOGLE WEB FONT -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">

<!-- BASE CSS -->
    <link href="<%=request.getContextPath()%>/plugins/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/plugins/css/style.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/plugins/css/vendors.css" rel="stylesheet">

<!-- Your custom styles -->
    <link href="<%=request.getContextPath()%>/plugins/css/custom.css" rel="stylesheet" type="text/css">
</head>

<body>
	<jsp:include page="/frontend/other/header.jsp"/>
	<div id="page">
		
	
	
	<main>
		<section class="hero_in contacts">
			<div class="wrapper">
				<div class="container">
					<h1 class="fadeInUp"><span></span>客服表單</h1>
				</div>
			</div>
		</section>
		<!--/hero_in-->

		<div class="contact_info">
			<div class="container">
				<ul class="clearfix">
					<li>
						<i class="pe-7s-map-marker"></i>
						<h4>地址</h4>
						<span>104台北市中山區南京東路三段219號5樓<br></span>
					</li>
					<li>
						<i class="pe-7s-mail-open-file"></i>
						<h4>聯絡信箱</h4>
						<span>BigZooTEA101G2@gmail.com <br><small>Monday to Friday 9am - 7pm</small></span>

					</li>
					<li>
						<i class="pe-7s-phone"></i>
						<h4>連絡電話</h4>
						<span>(02)66152085<br><small>Monday to Friday 9am - 7pm</small></span>
					</li>
				</ul>
			</div>
		</div>
		<!--/contact_info-->

		<div class="bg_color_1">
			<div class="container margin_80_55">
				<div class="row justify-content-between">
					<div class="col-lg-2">
						<div class="map_contact">
						</div>
						<!-- /map -->
					</div>
					<div class="col-lg-8">
						<h4>客服表單><%=request.getParameter("formListType").equals("a")? "檢舉" : request.getParameter("formListType").equals("b")? "客服" : "退費"%></h4>
						<p><%=loginMember.getMemberName()%> 您好，有任何需求嗎?</p>
						<div id="message-contact"></div>
						
<form method="post" action="<%=request.getContextPath()%>/FormListServlet" enctype="multipart/form-data">

							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label>聯絡電話(可選填)</label>
										<input class="form-control" type="text" id="name_contact" name="contactPhone">
										<span style="color:red"><%= (errorMsgs == null)? "" : (errorMsgs.peek().contains("電話"))? errorMsgs.poll() : ""%></span>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label>聯絡email(可選填)</label>
										<input class="form-control" type="text" id="name_contact" name="contactEmail">
										<span style="color:red"><%= (errorMsgs == null)? "" : (errorMsgs.peek() == null)? "" :(errorMsgs.peek().contains("Mail"))? errorMsgs.poll() : ""%></span>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label>主旨</label>
										<input class="form-control" type="text" id="name_contact" name="formListTitle">
										<span style="color:red"><%= (errorMsgs == null)? "" : (formListVO.getFormListTitle().equals(""))? "請填主旨" : ""%></span>
									</div>
								</div>
							</div>
							<div class="form-group" id="form-content">
								<label>內容</label>
								<textarea class="form-control" id="message_contact" name="formListContext" style="height:150px;"></textarea>
							</div>
							
										<div id="btn-photo">
											<label>
												<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-card-image" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
												  <path fill-rule="evenodd" d="M14.5 3h-13a.5.5 0 0 0-.5.5v9c0 .013 0 .027.002.04V12l2.646-2.354a.5.5 0 0 1 .63-.062l2.66 1.773 3.71-3.71a.5.5 0 0 1 .577-.094L15 9.499V3.5a.5.5 0 0 0-.5-.5zm-13-1A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-13zm4.502 3.5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z"/>
												</svg>
											上傳照片
											<input type="file" name="formListFile" id="imgInp2" style="visibility: hidden; position: absolute;"/>									
											</label>
										</div>
										
							<div style="margin-top:10px;">
								<input type="hidden" name="action" value="addFormList">
								<input type="hidden" name="memberId" value="<%= loginMember.getMemberId()%>">
								<input type="hidden" name="formListType" value="<%=request.getParameter("formListType").equals("a")? "檢舉" : request.getParameter("formListType").equals("b")? "客服" : "退費"%>">
								<button type="submit" value="submit" class="btn_1 rounded" id="submit-contact">送出</button>
							</div>
</form>

					</div>
					<div class="col-lg-2">
						<div class="map_contact">
						</div>
						<!-- /map -->
					</div>
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /bg_color_1 -->
	</main>
	<!--/main-->
	
	</div>
	<!-- page -->
	
	<!-- Sign In Popup -->
	<div id="sign-in-dialog" class="zoom-anim-dialog mfp-hide">
		<div class="small-dialog-header">
			<h3>Sign In</h3>
		</div>
		<form>
			<div class="sign-in-wrapper">
				<a href="#0" class="social_bt facebook">Login with Facebook</a>
				<a href="#0" class="social_bt google">Login with Google</a>
				<div class="divider"><span>Or</span></div>
				<div class="form-group">
					<label>Email</label>
					<input type="email" class="form-control" name="email" id="email">
					<i class="icon_mail_alt"></i>
				</div>
				<div class="form-group">
					<label>Password</label>
					<input type="password" class="form-control" name="password" id="password" value="">
					<i class="icon_lock_alt"></i>
				</div>
				<div class="clearfix add_bottom_15">
					<div class="checkboxes float-left">
						<label class="container_check">Remember me
						  <input type="checkbox">
						  <span class="checkmark"></span>
						</label>
					</div>
					<div class="float-right mt-1"><a id="forgot" href="javascript:void(0);">Forgot Password?</a></div>
				</div>
				<div class="text-center"><input type="submit" value="Log In" class="btn_1 full-width"></div>
				<div class="text-center">
					Don’t have an account? <a href="register.html">Sign up</a>
				</div>
				<div id="forgot_pw">
					<div class="form-group">
						<label>Please confirm login email below</label>
						<input type="email" class="form-control" name="email_forgot" id="email_forgot">
						<i class="icon_mail_alt"></i>
					</div>
					<p>You will receive an email containing a link allowing you to reset your password to a new preferred one.</p>
					<div class="text-center"><input type="submit" value="Reset Password" class="btn_1"></div>
				</div>
			</div>
		</form>
		<!--form -->
	</div>
	<!-- /Sign In Popup -->
	
	<div id="toTop"></div><!-- Back to top button -->
	
<!-- COMMON SCRIPTS -->
  	<script src="<%=request.getContextPath()%>/plugins/js/common_scripts.js"></script>
  	<script src="<%=request.getContextPath()%>/plugins/js/main.js"></script>
	
<!-- Map -->
	<script src="http://maps.googleapis.com/maps/api/js"></script>
	<script src="<%=request.getContextPath()%>/plugins/js/markerclusterer.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/js/map_hotels.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/js/infobox.js"></script>
	
	
<script>
$("#imgInp2").on("change",function() {
	  $("img.preview").remove();
	for (let i = 0; i < this.files.length; i++) {
		let reader = new FileReader();
		reader.readAsDataURL(this.files[i]);
		reader.addEventListener("load", function() {
			$("#form-content").append(`<img src="\${reader.result}" class ="preview" id="preview" style="max-height:100px; margin-top:10px; margin-right:5px;border: double;">`);
		});
	};
});
</script>	

</body>
</html>