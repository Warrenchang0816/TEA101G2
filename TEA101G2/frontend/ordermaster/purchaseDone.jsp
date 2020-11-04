<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>訂購成功</title>

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
<div id="page">
<jsp:include page="/frontend/other/header.jsp" />

  <main>
		<div class="hero_in cart_section last">
			<div class="wrapper">
				<div class="container">
					<div class="bs-wizard clearfix">
						<div class="bs-wizard-step">
							<div class="text-center bs-wizard-stepnum">選擇預訂時段</div>
							<div class="progress">
								<div class="progress-bar"></div>
							</div>
							<a href="#1" class="bs-wizard-dot"></a>
						</div>

						<div class="bs-wizard-step">
							<div class="text-center bs-wizard-stepnum">估價及付款</div>
							<div class="progress">
								<div class="progress-bar"></div>
							</div>
							<a href="#2" class="bs-wizard-dot"></a>
						</div>

						<div class="bs-wizard-step active">
							<div class="text-center bs-wizard-stepnum">訂購成功!</div>
							<div class="progress">
								<div class="progress-bar"></div>
							</div>
							<a href="#3" class="bs-wizard-dot"></a>
						</div>
					</div>
					<!-- End bs-wizard -->
					<div id="confirm">
						<h4>Order completed!</h4>
						<p>感謝您的訂購，您可以於訂單管理頁面確認訂單付款狀態</p>
						<a href="<%=request.getContextPath()%>/frontend/ordermaster/selectAllOrderMaster.jsp">我的訂單</a>
					</div>
				</div>
			</div>
		</div>
		<!--/hero_in-->
	</main>
	<!--/main-->
</div>
<script src="<%=request.getContextPath()%>/plugins/js/common_scripts.js"></script>
<script src="<%=request.getContextPath()%>/plugins/js/main.js"></script>	
</body>
</html>