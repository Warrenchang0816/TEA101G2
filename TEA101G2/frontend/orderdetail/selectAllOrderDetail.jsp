<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orderDetail.model.*"%>
<%@ page import="com.orderMaster.model.*"%>
<%@ page import="com.spaceDetail.model.*"%>


<%    
	OrderDetailService ods = new OrderDetailService();
    List<OrderDetailVO> odlist = (List<OrderDetailVO>)request.getAttribute("odlist");
    pageContext.setAttribute("odlist", odlist);
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>訂單明細</title>

<!-- GOOGLE WEB FONT -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">

<!-- BASE CSS -->
    <link href="<%=request.getContextPath()%>/plugins/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/plugins/css/style.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/plugins/css/vendors.css" rel="stylesheet">

<!-- Your custom styles -->
    <link href="<%=request.getContextPath()%>/plugins/css/custom.css" rel="stylesheet" type="text/css">

<!-- Table -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  	
</head>

<body>

<div id="page">
<jsp:include page="/frontend/other/header.jsp" />	
  
  <main>
		
		<section class="hero_in hotels">
			<div class="wrapper">
				<div class="container">
					<h1 class="fadeInUp"><span></span>訂單明細</h1>
				</div>
			</div>
		</section>
		<!--/hero_in-->
  <table class="table">
    <thead>
	  <tr>
		<th>訂單明細編號</th>
		<th>場地租借起始時間</th>
		<th>場地租借結束時間</th>
		<th>場地租借費用(每小時)</th>
	  </tr>
    </thead>
	<c:forEach var="orderDetailVO" items="${odlist}">
	<tbody>	
		<tr>
			<td>${orderDetailVO.orderDetailId}</td>
			<td>${orderDetailVO.rentStartTime}</td>
			<td>${orderDetailVO.rentEndTime}</td>
			<% 
			  SpaceDetailService sds = new SpaceDetailService();
			  SpaceDetailVO sdVO = sds.selectOneSpaceDetail(((OrderDetailVO)pageContext.getAttribute("orderDetailVO")).getSpaceDetailId());
			  request.setAttribute("spaceDetailCharge", sdVO.getSpaceDetailCharge());
			%>
			<td>${spaceDetailCharge}</td>
		</tr>
	</tbody>
	</c:forEach>
  </table>
  <a href="<%=request.getContextPath()%>/frontend/ordermaster/selectAllOrderMaster.jsp">返回我的訂單</a>
</main>
<!--/main-->
</div>
<!-- page -->

<!-- COMMON SCRIPTS -->
  	<script src="<%=request.getContextPath()%>/plugins/js/common_scripts.js"></script>
  	<script src="<%=request.getContextPath()%>/plugins/js/main.js"></script>
	
</body>
</html>