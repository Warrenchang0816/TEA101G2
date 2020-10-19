<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.formList.model.*"%>
<%@ page import="com.member.model.*"%>

<%
	LinkedList<String> errorMsgs = (LinkedList<String>) request.getAttribute("errorMsgs");

FormListVO formListVO = (FormListVO)request.getAttribute("addFormList");

MemberServiceB ms = new MemberServiceB();
MemberVO loginMember = ms.selectOneMember("MEM00003");

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
    <title>Panagea | Premium site template for travel agencies, hotels and restaurant listing.</title>

    <!-- Favicons-->
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/frontend/img/favicon.ico" type="image/x-icon">
    <link rel="apple-touch-icon" type="image/x-icon" href="<%=request.getContextPath()%>/frontend/img/apple-touch-icon-57x57-precomposed.png">
    <link rel="apple-touch-icon" type="image/x-icon" sizes="72x72" href="<%=request.getContextPath()%>/frontend/img/apple-touch-icon-72x72-precomposed.png">
    <link rel="apple-touch-icon" type="image/x-icon" sizes="114x114" href="<%=request.getContextPath()%>/frontend/img/apple-touch-icon-114x114-precomposed.png">
    <link rel="apple-touch-icon" type="image/x-icon" sizes="144x144" href="<%=request.getContextPath()%>/frontend/img/apple-touch-icon-144x144-precomposed.png">

    <!-- GOOGLE WEB FONT -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">

    <!-- BASE CSS -->
    <link href="<%=request.getContextPath()%>/frontend/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/frontend/css/style.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/frontend/css/vendors.css" rel="stylesheet">

    <!-- YOUR CUSTOM CSS -->
    <link href="<%=request.getContextPath()%>/frontend/css/custom.css" rel="stylesheet">
	
</head>

<body>
	
	<div id="page">
		
	<header class="header menu_fixed">
		<div id="preloader"><div data-loader="circle-side"></div></div><!-- /Preload -->
		<div id="logo">
			<a href="index.html">
				<img src="img/logo.svg" width="150" height="36" alt="" class="logo_normal">
				<img src="img/logo_sticky.svg" width="150" height="36" alt="" class="logo_sticky">
			</a>
		</div>
		<ul id="top_menu">
			<li><a href="cart-1.html" class="cart-menu-btn" title="Cart"><strong>4</strong></a></li>
			<li><a href="#sign-in-dialog" id="sign-in" class="login" title="Sign In">Sign In</a></li>
			<li><a href="wishlist.html" class="wishlist_bt_top" title="Your wishlist">Your wishlist</a></li>
		</ul>
		<!-- /top_menu -->
		<a href="#menu" class="btn_mobile">
			<div class="hamburger hamburger--spin" id="hamburger">
				<div class="hamburger-box">
					<div class="hamburger-inner"></div>
				</div>
			</div>
		</a>
		<nav id="menu" class="main-menu">
			<ul>
				<li><span><a href="#0">Home</a></span>
					<ul>
						<li><a href="index.html">Home Default</a></li>
						<li><a href="index-2.html">Home Slider</a></li>
						<li><a href="index-3.html">Home Video BG</a></li>
						<li><a href="index-4.html">Home Layer Slider</a></li>
						<li><a href="index-5.html">Home Search 2</a></li>
						<li><a href="index-10.html">Home Search 3 <strong>New!</strong></a></li>
						<li><a href="index-7.html">Home Search 4</a></li>
						<li><a href="index-6.html">Home GDPR (EU law)</a></li>
                        <li><a href="index-8.html">Address Autocomplete</a></li>
                        <li><a href="index-9.html">Home AirBnb</a></li>
					</ul>
				</li>
				<li><span><a href="#0">Tours</a></span>
					<ul>
						<li>
							<span><a href="#0">Tours Grid</a></span>
							<ul>
								<li><a href="tours-grid-isotope.html">Tours Grid Isotope</a></li>
								<li><a href="tours-grid-sidebar.html">Tours Grid Sidebar</a></li>
								<li><a href="tours-grid-sidebar-2.html">Tours Grid Sidebar 2</a></li>
								<li><a href="tours-grid.html">Tours Grid Simple</a></li>
							</ul>
						</li>
						<li>
							<span><a href="#0">Tours List</a></span>
							<ul>
								<li><a href="tours-list-isotope.html">Tours List Isotope</a></li>
								<li><a href="tours-list-sidebar.html">Tours List Sidebar</a></li>
								<li><a href="tours-list-sidebar-2.html">Tours List Sidebar 2</a></li>
								<li><a href="tours-list.html">Tours List Simple</a></li>
							</ul>
						</li>
						<li><a href="tours-half-screen-map.html">Tours Half Screen Map</a></li>
						<li><a href="tour-detail.html">Tour Detail</a></li>
						<li><a href="detail-working-contact-form.html">Detail Contact Form <strong>New!</strong></a></li>
						<li>
							<span><a href="#0">Open Street Map</a></span>
							<ul>
								<li><a href="tours-half-screen-map-leaflet.html">Tours Half Screen Map</a></li>
								<li><a href="tours-list-isotope-leaflet.html">Tours Grid Isotope</a></li>
								<li><a href="tours-list-sidebar-leaflet.html">Tours Grid Sidebar</a></li>
								<li><a href="tours-list-sidebar-2-leaflet.html">Tours Grid Sidebar 2</a></li>
								<li><a href="tours-list-leaflet.html">Tours Grid Simple</a></li>
								<li><a href="tours-list-isotope-leaflet.html">Tours List Isotope</a></li>
								<li><a href="tours-list-sidebar-leaflet.html">Tours List Sidebar</a></li>
								<li><a href="tours-list-sidebar-2-leaflet.html">Tours List Sidebar 2</a></li>
								<li><a href="tours-list-leaflet.html">Tours List Simple</a></li>
								<li><a href="tour-detail-leaflet.html">Tour Detail</a></li>
							</ul>
						</li>
					</ul>
				</li>
				<li><span><a href="#0">Hotels</a></span>
					<ul>
						<li>
							<span><a href="#0">Hotel Grid</a></span>
							<ul>
								<li><a href="hotels-grid-isotope.html">Hotel Grid Isotope</a></li>
								<li><a href="hotels-grid-sidebar.html">Hotel Grid Sidebar</a></li>
								<li><a href="hotels-grid-sidebar-2.html">Hotel Grid Sidebar 2</a></li>
								<li><a href="hotels-grid.html">Hotel Grid Simple</a></li>
							</ul>
						</li>
						<li>
							<span><a href="#0">Hotel List</a></span>
							<ul>
								<li><a href="hotels-list-isotope.html">Hotel List Isotope</a></li>
								<li><a href="hotels-list-sidebar.html">Hotel List Sidebar</a></li>
								<li><a href="hotels-list-sidebar-2.html">Hotel List Sidebar 2</a></li>
								<li><a href="hotels-list.html">Hotel List Simple</a></li>
							</ul>
						</li>
						<li><a href="hotels-half-screen-map.html">Hotel Half Screen Map</a></li>
						<li><a href="hotel-detail.html">Hotel Detail</a></li>
					</ul>
				</li>
				<li><span><a href="#0">Eat &amp; Drink</a></span>
					<ul>
						<li>
							<span><a href="#0">Restaurant Grid</a></span>
							<ul>
								<li><a href="restaurants-grid-isotope.html">Restaurant Grid Isotope</a></li>
								<li><a href="restaurants-grid-sidebar.html">Restaurant Grid Sidebar</a></li>
								<li><a href="restaurants-grid-sidebar-2.html">Restaurant Grid Sidebar 2</a></li>
								<li><a href="restaurants-grid.html">Restaurant Grid simple</a></li>
							</ul>
						</li>
						<li>
							<span><a href="#0">Restaurant List</a></span>
							<ul>
								<li><a href="restaurants-list-isotope.html">Restaurant List Isotope</a></li>
								<li><a href="restaurants-list-sidebar.html">Restaurant List Sidebar</a></li>
								<li><a href="restaurants-list-sidebar-2.html">Restaurant List Sidebar 2</a></li>
								<li><a href="restaurants-list.html">Restaurant List Simple</a></li>
							</ul>
						</li>
						<li><a href="restaurants-half-screen-map.html">Half Screen Map</a></li>
						<li><a href="restaurant-detail.html">Restaurant Detail</a></li>
					</ul>
				</li>
				<li><span><a href="adventure.html">Adventure</a></span></li>
				<li><span><a href="#0">Pages</a></span>
					<ul>
						<li><a href="about.html">About</a></li>
						<li><a href="media-gallery.html">Media gallery</a></li>
						<li><a href="help.html">Help Section</a></li>
						<li><a href="faq.html">Faq Section</a></li>
						<li><a href="wishlist.html">Wishlist page</a></li>
						<li><a href="contacts.html">Contacts</a></li>
						<li><a href="login.html">Login</a></li>
						<li><a href="register.html">Register</a></li>
						<li><a href="blog.html">Blog</a></li>
						<li><a href="bootstrap-modal.html">Bootstrap Modal <strong>New!</strong></a></li>
						<li><a href="modal-version-2.html">Another Modal <strong>New!</strong></a></li>
						<li><a href="pricing-tables-2.html">Pricing Tables 1 <strong>New!</strong></a></li>
						<li><a href="pricing-tables-3.html">Pricing Tables 2 <strong>New!</strong></a></li>
					</ul>
				</li>
				<li><span><a href="#0">Extra</a></span>
					<ul>
                    	<li><a href="menu-options.html">Menu Position Options</a></li>
                    	<li><a href="tour-detail-singlemonth-datepicker.html">Single month Datepicker</a></li>
						<li><a href="404.html">404 Error page</a></li>
						<li><a href="cart-1.html">Cart page 1</a></li>
						<li><a href="cart-2.html">Cart page 2</a></li>
						<li><a href="cart-3.html">Cart page 3</a></li>
						<li><a href="pricing-tables.html">Responsive pricing tables</a></li>
						<li><a href="coming_soon/index.html">Coming soon</a></li>
						<li><a href="invoice.html">Invoice</a></li>
						<li><a href="icon-pack-1.html">Icon pack 1</a></li>
						<li><a href="icon-pack-2.html">Icon pack 2</a></li>
						<li><a href="icon-pack-3.html">Icon pack 3</a></li>
						<li><a href="icon-pack-4.html">Icon pack 4</a></li>
                        <li><a href="hamburgers.html">Animated Hamburgers</a></li>
					</ul>
				</li>
				<li><span><a href="#0">Buy template</a></span></li>
			</ul>
		</nav>

	</header>
	<!-- /header -->
	
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
						<h4>Address</h4>
						<span>PO Box 97845 Baker st. 567, Los Angeles<br>California - US.</span>
					</li>
					<li>
						<i class="pe-7s-mail-open-file"></i>
						<h4>Email address</h4>
						<span>support@Panagea.com - info@Panagea.com<br><small>Monday to Friday 9am - 7pm</small></span>

					</li>
					<li>
						<i class="pe-7s-phone"></i>
						<h4>Contacts info</h4>
						<span>+ 61 (2) 8093 3402 + 61 (2) 8093 3402<br><small>Monday to Friday 9am - 7pm</small></span>
					</li>
				</ul>
			</div>
		</div>
		<!--/contact_info-->

		<div class="bg_color_1">
			<div class="container margin_80_55">
				<div class="row justify-content-between">
					<div class="col-lg-4">
						<div class="map_contact">
						</div>
						<!-- /map -->
					</div>
					<div class="col-lg-8">
						<h4>客服表單><%=request.getParameter("formListType") %></h4>
						<p>Have a good day, <%=loginMember.getMemberName()%>, anything want to let us know?</p>
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
							<div class="form-group">
								<label>內容</label>
								<textarea class="form-control" id="message_contact" name="formListContext" style="height:150px;"></textarea>
							</div>
							
							<div><img id="preveiw"></div>
							<input class="form-control" type="file" id="name_contact" name="formListFile" id="formListFile">
							
							<input type="hidden" name="action" value="addFormList">
							<input type="hidden" name="memberId" value="<%= loginMember.getMemberId()%>">
							<input type="hidden" name="formListType" value="<%=request.getParameter("formListType") %>">
							<button type="submit" value="submit" class="btn_1 rounded" id="submit-contact">送出</button>
</form>

					</div>
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /bg_color_1 -->
	</main>
	<!--/main-->
	
	
	
	<footer>
		<div class="container margin_60_35">
			<div class="row">
				<div class="col-lg-5 col-md-12 p-r-5">
					<p><img src="img/logo.svg" width="150" height="36" alt=""></p>
					<p>Mea nibh meis philosophia eu. Duis legimus efficiantur ea sea. Id placerat tacimates definitionem sea, prima quidam vim no. Duo nobis persecuti cu. Nihil facilisi indoctum an vix, ut delectus expetendis vis.</p>
					<div class="follow_us">
						<ul>
							<li>Follow us</li>
							<li><a href="#0"><i class="ti-facebook"></i></a></li>
							<li><a href="#0"><i class="ti-twitter-alt"></i></a></li>
							<li><a href="#0"><i class="ti-google"></i></a></li>
							<li><a href="#0"><i class="ti-pinterest"></i></a></li>
							<li><a href="#0"><i class="ti-instagram"></i></a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 ml-lg-auto">
					<h5>Useful links</h5>
					<ul class="links">
						<li><a href="about.html">About</a></li>
						<li><a href="login.html">Login</a></li>
						<li><a href="register.html">Register</a></li>
						<li><a href="blog.html">News &amp; Events</a></li>
						<li><a href="contacts.html">Contacts</a></li>
					</ul>
				</div>
				<div class="col-lg-3 col-md-6">
					<h5>Contact with Us</h5>
					<ul class="contacts">
						<li><a href="tel://61280932400"><i class="ti-mobile"></i> + 61 23 8093 3400</a></li>
						<li><a href="mailto:info@Panagea.com"><i class="ti-email"></i> info@Panagea.com</a></li>
					</ul>
					<div id="newsletter">
					<h6>Newsletter</h6>
					<div id="message-newsletter"></div>
					<form method="post" action="assets/newsletter.php" name="newsletter_form" id="newsletter_form">
						<div class="form-group">
							<input type="email" name="email_newsletter" id="email_newsletter" class="form-control" placeholder="Your email">
							<input type="submit" value="Submit" id="submit-newsletter">
						</div>
					</form>
					</div>
				</div>
			</div>
			<!--/row-->
			<hr>
			<div class="row">
				<div class="col-lg-6">
					<ul id="footer-selector">
						<li>
							<div class="styled-select" id="lang-selector">
								<select>
									<option value="English" selected>English</option>
									<option value="French">French</option>
									<option value="Spanish">Spanish</option>
									<option value="Russian">Russian</option>
								</select>
							</div>
						</li>
						<li>
							<div class="styled-select" id="currency-selector">
								<select>
									<option value="US Dollars" selected>US Dollars</option>
									<option value="Euro">Euro</option>
								</select>
							</div>
						</li>
						<li><img src="img/cards_all.svg" alt=""></li>
					</ul>
				</div>
				<div class="col-lg-6">
					<ul id="additional_links">
						<li><a href="#0">Terms and conditions</a></li>
						<li><a href="#0">Privacy</a></li>
						<li><span>© 2019 Panagea</span></li>
					</ul>
				</div>
			</div>
		</div>
	</footer>
	<!--/footer-->
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
    <script src="<%=request.getContextPath()%>/frontend/js/common_scripts.js"></script>
    <script src="<%=request.getContextPath()%>/frontend/js/main.js"></script>
	<script src="<%=request.getContextPath()%>/frontend/assets/validate.js"></script>
	
	<!-- SPECIFIC SCRIPTS -->
	<script src="http://maps.googleapis.com/maps/api/js"></script>
	<script src="<%=request.getContextPath()%>/frontend/js/mapmarker.jquery.js"></script>
	<script src="<%=request.getContextPath()%>/frontend/js/mapmarker_func.jquery.js"></script>
	
	
<script>
var p = document.getElementById("formListFile");
p.addEventListener("change", function(){
var file = this.files[0];
var reader = New FileReader();
reader.onload = function(event){
  var source = event.target.result;
  var img = document.getElementById("preveiw");
  img.src = source;
};
reader.readAsDataURL(file);
})
</script>	

</body>
</html>