<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Panagea - Premium site template for travel agencies, hotels and restaurant listing.">
    <meta name="author" content="Ansonika">
    <title>客服表單</title>

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
	<div id="page" class="theia-exception">
		

	
	<main>
		<section class="hero_in general">
			<div class="wrapper">
				<div class="container">
					<h1 class="fadeInUp"><span></span>客服表單</h1>
				</div>
			</div>
		</section>
		<!--/hero_in-->

		<div class="container margin_60_35">
			<div class="row">
				<aside class="col-lg-3" id="sidebar">
						<div class="box_style_cat" id="faq_box">
							<ul id="cat_nav">
								<li><a href="#payment" class="active"><i class="icon_document_alt"></i>檢舉</a></li>
								<li><a href="#tips"><i class="icon_document_alt"></i>客服</a></li>
								<li><a href="#reccomendations"><i class="icon_document_alt"></i>退費</a></li>
							</ul>
						</div>
						<!--/sticky -->
				</aside>
				<!--/aside -->
				
				<div class="col-lg-9" id="faq">
					<h4 class="nomargin_top">檢舉    <a href="<%=request.getContextPath()%>/frontend/formList/addFormList.jsp?formListType=a" class="active"><i class="icon_document_alt"></i></h4>
					<div role="tablist" class="add_bottom_45 accordion_2" id="payment">
						<div class="card">
							<div class="card-header" role="tab">
								<h5 class="mb-0">
									<a data-toggle="collapse" href="#collapseOne_payment" aria-expanded="false"><i class="indicator ti-minus"></i>規格與事實不符</a>
								</h5>
							</div>

							<div id="collapseOne_payment" class="collapse show" role="tabpanel" data-parent="#payment">
								<div class="card-body">
									<p>場地現場與網站上有不符合情形。</p>
									<p>表單內容請註明場地名稱以及場地類別。</p>
								</div>
							</div>
						</div>
						<!-- /card -->
						<div class="card">
							<div class="card-header" role="tab">
								<h5 class="mb-0">
									<a class="collapsed" data-toggle="collapse" href="#collapseTwo_payment" aria-expanded="false">
										<i class="indicator ti-plus"></i>不雅照片
									</a>
								</h5>
							</div>
							<div id="collapseTwo_payment" class="collapse" role="tabpanel" data-parent="#payment">
								<div class="card-body">
									<p>場地含有有傷害風俗或是造成感官不適的照片。</p>
									<p>表單內容請註明場地名稱以及場地類別。</p>
								</div>
							</div>
						</div>
					</div>
					<!-- /accordion payment -->
					
					<h4 class="nomargin_top">客服    <a href="<%=request.getContextPath()%>/frontend/formList/addFormList.jsp?formListType=b" class="active"><i class="icon_document_alt"></i></h4>
					<div role="tablist" class="add_bottom_45 accordion_2" id="tips">
						<div class="card">
							<div class="card-header" role="tab">
								<h5 class="mb-0">
									<a data-toggle="collapse" href="#collapseOne_tips" aria-expanded="true"><i class="indicator ti-plus"></i>場地上架問題</a>
								</h5>
							</div>

							<div id="collapseOne_tips" class="collapse" role="tabpanel" data-parent="#tips">
								<div class="card-body">
									<p>場地被列入觀察名單或是尚未到下架日期，需要申請上架。</p>
									<p>表單內容請註明場地名稱以及場地類別。</p>
								</div>
							</div>
						</div>
						<!-- /card -->
						<div class="card">
							<div class="card-header" role="tab">
								<h5 class="mb-0">
									<a class="collapsed" data-toggle="collapse" href="#collapseTwo_tips" aria-expanded="false">
										<i class="indicator ti-plus"></i>評價申訴
									</a>
								</h5>
							</div>
							<div id="collapseTwo_tips" class="collapse" role="tabpanel" data-parent="#tips">
								<div class="card-body">
									<p>場地或是會員評價有蓄意造成名聲傷害。</p>
									<p>場地評價請註明場地名稱、場地類別。</p>
									<p>會員評價請註明會員名稱。</p>
								</div>
							</div>
						</div>
					</div>
					<!-- /accordion suggestions -->
					
					<h4 class="nomargin_top">退費    <a href="<%=request.getContextPath()%>/frontend/formList/addFormList.jsp?formListType=c" class="active"><i class="icon_document_alt"></i></h4>
					<div role="tablist" class="add_bottom_45 accordion_2" id="reccomendations">
						<div class="card">
							<div class="card-header" role="tab">
								<h5 class="mb-0">
									<a data-toggle="collapse" href="#collapseOne_reccomendations" aria-expanded="true"><i class="indicator ti-plus"></i>訂單取消</a>
								</h5>
							</div>

							<div id="collapseOne_reccomendations" class="collapse" role="tabpanel" data-parent="#reccomendations">
								<div class="card-body">
									<p>已付費的訂單需要取消並退費。</p>
									<p>表單內容請註明訂單編號以及原因。</p>
								</div>
							</div>
						</div>
						<!-- /card -->
						<div class="card">
							<div class="card-header" role="tab">
								<h5 class="mb-0">
									<a class="collapsed" data-toggle="collapse" href="#collapseTwo_reccomendations" aria-expanded="false">
										<i class="indicator ti-plus"></i>不可抗力因素
									</a>
								</h5>
							</div>
							<div id="collapseTwo_reccomendations" class="collapse" role="tabpanel" data-parent="#reccomendations">
								<div class="card-body">
									<p>發生天災或人為因素使得預定時段不可前行。</p>
									<p>表單內容請註明訂單編號以及原因。</p>
								</div>
							</div>
						</div>
					</div>
					<!-- /accordion Reccomendations -->
					
					
					
					
					<!-- /accordion Booking -->
				</div>
				<!-- /col -->
			</div>
			<!-- /row -->
		</div>
		<!--/container-->
	</main>
	<!--/main-->
	
	
	
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
	
</body>
</html>