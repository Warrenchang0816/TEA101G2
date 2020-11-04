<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orderMaster.model.*"%>
<%@ page import="com.orderDetail.model.*"%>

<%
	OrderMasterVO orderMasterVO = (OrderMasterVO)request.getAttribute("orderMasterVO");
	pageContext.setAttribute("orderMasterVO", orderMasterVO);
%>

<jsp:useBean id="orderDetailSvc" scope="page" class="com.orderDetail.model.OrderDetailService"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>付款頁面-購物車</title>

<!-- GOOGLE WEB FONT -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">

<!-- BASE CSS -->
    <link href="<%=request.getContextPath()%>/plugins/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/plugins/css/style.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/plugins/css/vendors.css" rel="stylesheet">

<!-- Your custom styles -->
    <link href="<%=request.getContextPath()%>/plugins/css/custom.css" rel="stylesheet" type="text/css">
    <script src="<%=request.getContextPath()%>/frontend/js/jquery-3.5.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.9/validator.min.js"></script>

</head>
<body>
<div id="page">
<jsp:include page="/frontend/other/header.jsp" />

  <main>
		
		<section class="hero_in hotels">
			<div class="wrapper">
				<div class="container">
					<h1 class="fadeInUp"><span></span>付款頁面</h1>
				</div>
			</div>
		</section>
		<!--/hero_in-->
		
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/OrderMasterServlet" name="form1">
			<div class="container margin_60_35">
				<div class="row">
					<div class="col-lg-8">
						<div class="box_cart">						
						<div class="form_title">
							<h3><strong>1</strong>您的資訊</h3>
							<p>
								為了您的權益，個資部分請務必填真實資料
							</p>
						</div>
						<div class="step">
							<div class="row">
							<div class="col-sm-6">
								<div class="form-group">
									<label>您的姓名</label>
									<input type="text" class="form-control" id="firstname_booking" name="firstname_booking" required="required">
									<div class="help-block with-errors" style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif"></div>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group">
									<label>您的暱稱</label>
									<input type="text" class="form-control" id="lastname_booking" name="lastname_booking" required="required">
									<div class="help-block with-errors" style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif"></div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group">
									<label>Email</label>
									<input type="email" id="email_booking" name="email_booking" class="form-control" data-error="Email格式錯誤。" required="required">
									<div class="help-block with-errors" style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif"></div>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group">
									<label>確認 email</label>
									<input type="email" id="email_booking_2" name="email_booking_2" class="form-control" data-match="input[name='email_booking']" data-error="信箱不符合。" required="required">
									<div class="help-block with-errors" style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif"></div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group">
									<label>聯絡電話</label>
									<input type="text" id="telephone_booking" name="telephone_booking" class="form-control" required="required">
									<div class="help-block with-errors" style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif"></div>
								</div>
							</div>
						</div>
						</div>
						<hr>
						<!--End step -->

						<div class="form_title">
							<h3><strong>2</strong>付款資訊</h3>
							<p>
								請使用信用卡
							</p>
						</div>
						<div class="step">
							<div class="form-group">
							<label>卡片姓名</label>
							<input type="text" class="form-control" id="name_card_bookign" name="name_card_bookign" required="required">
							<div class="help-block with-errors" style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif"></div>
						</div>
						<div class="row">
							<div class="col-md-8 col-sm-12">
								<div class="form-group">
									<label>卡片號碼</label>
									<input type="text" id="card_number" name="card_number" class="form-control" required="required">
									<div class="help-block with-errors" style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif"></div>
								</div>
							</div>
							<div class="col-md-4 col-sm-12">
								<img src="<%=request.getContextPath()%>/plugins/img/cards_all.png" width="146" height="49.83" alt="Cards" class="cards-payment" >
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<label>到期日</label>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<input type="text" id="expire_month" name="expire_month" class="form-control" placeholder="MM" required="required">
											<div class="help-block with-errors" style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif"></div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<input type="text" id="expire_year" name="expire_year" class="form-control" placeholder="Year" required="required">
											<div class="help-block with-errors" style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif"></div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>安全碼</label>
									<div class="row">
										<div class="col-4">
											<div class="form-group">
												<input type="text" id="ccv" name="ccv" class="form-control" placeholder="CCV" required="required">
												<div class="help-block with-errors" style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif"></div>
											</div>
										</div>
										<div class="col-8">
											<img src="<%=request.getContextPath()%>/plugins/img/icon_ccv.gif" width="50" height="29" alt="ccv"><small>Last 3 digits</small>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!--End row -->
												
						</div>
						<hr>
						<!--End step -->

						<div class="form_title">
							<h3><strong>3</strong>帳單地址</h3>
							<p>
								為了您的權益，個資部分請務必填真實資料
							</p>
						</div>
						<div class="step">
							<div class="row">
								<div class="col-sm-4">
									<div class="form-group">
										<label>國籍</label>
										<input type="text" id="city_booking" name="city_booking" class="form-control" required="required">
										<div class="help-block with-errors" style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif"></div>
									</div>
								</div>
								<div class="col-sm-4">
									<div class="form-group">
										<label>城市</label>
										<input type="text" id="state_booking" name="state_booking" class="form-control" required="required">
										<div class="help-block with-errors" style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif"></div>
									</div>
								</div>
								<div class="col-sm-4">
									<div class="form-group">
										<label>郵遞區號</label>
										<input type="text" id="postal_code" name="postal_code" class="form-control" required="required">
										<div class="help-block with-errors" style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif"></div>
									</div>
								</div>
							</div>
							<div class="row">								
								<div class="col-sm-12">
									<div class="form-group">
										<label>地址</label>
										<input type="text" id="street_2" name="street_2" class="form-control" required="required">
										<div class="help-block with-errors" style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif"></div>
									</div>
								</div>
							</div>							
							<!--End row -->
						</div>
						<hr>
						<!--End step -->
						<div id="policy">
							<h5>退訂條款</h5>
							<p class="nomargin">七日以上可全額退費, 七日內三日以上可退50%金額, 三日以內恕不退費</p>
						</div>
						</div>
					</div>
					<!-- /col -->
					
					<aside class="col-lg-4" id="sidebar">
						<div class="box_detail">
							<div id="total_cart">
								Total <span class="float-right">${orderMasterVO.orderAmount}</span>
							</div>
							<c:forEach var="orderDetailVO" items="${orderDetailSvc.selectAllOrderDetailByOrderMaster(orderMasterVO.orderMasterId)}">
							<ul class="cart_details">
								<li>From <span>${orderDetailVO.rentStartTime}</span></li>
								<li>To <span>${orderDetailVO.rentEndTime}</span></li>
								<jsp:useBean id="spaceDetailSvc" scope="page" class="com.spaceDetail.model.SpaceDetailService" />
								<li>Amount <span>${spaceDetailSvc.selectOneSpaceDetail(orderDetailVO.spaceDetailId).spaceDetailCharge}/hr</span></li>
							</ul>
							</c:forEach>
								<input type="hidden" name="action" value="purchasedone">
								<input type="hidden" name="orderMasterId" value="<%=orderMasterVO.getOrderMasterId()%>">
								<button name="purchase" value="購買" type="submit" class="btn_1 full-width purchase">購買</button>
								<div class="text-center"><small>按下按鈕之前不會收任何費用</small></div>
						</div>
						
					</aside>
				</div>
				<!-- /row -->
			</div>
			</form>
			<!-- /container -->
		</main>
		<!--/main-->
</div>
<!-- page -->
<div id="toTop"></div><!-- Back to top button -->
	
<!-- COMMON SCRIPTS -->
	<script> 
		$("form[name='form1']").validator().on('submit', function(e) {
			if (e.isDefaultPrevented()) { 
				return;
			} else { 
				form.submit();
			}
			e.preventDefault(); 
		});
	</script>
    <script src="<%=request.getContextPath()%>/plugins/js/common_scripts.js"></script>
    <script src="<%=request.getContextPath()%>/plugins/js/main.js"></script>
</body>
</html>