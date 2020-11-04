<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orderMaster.model.*"%>
<%@ page import="com.orderDetail.model.*"%>
<%@ page import="com.spaceDetail.model.*"%>
<%@ page import="com.space.model.*"%>


<jsp:useBean id="orderMasterSvc" scope="page" class="com.orderMaster.model.OrderMasterService" />

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>我的訂單</title>

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
		
		<section class="hero_in hotels">
			<div class="wrapper">
				<div class="container">
					<h1 class="fadeInUp"><span></span>我的訂單</h1>
				</div>
			</div>
		</section>
		<!--/hero_in-->
		<div class="content-wrapper">
    		<div class="container-fluid">
				<div class="box_general">
					<div class="list_general">
						<c:forEach var="orderMasterVO" items="${orderMasterSvc.selectAllOrderMasterByMember(userVO.memberId)}">
						<%
						OrderDetailService ods = new OrderDetailService();
						List<OrderDetailVO> orderDetail = ods.selectAllOrderDetailByMaster(((OrderMasterVO)pageContext.getAttribute("orderMasterVO")).getOrderMasterId());
						for(OrderDetailVO OrderDetail : orderDetail){
							String spaceDetailId = OrderDetail.getSpaceDetailId();
							SpaceDetailService sds = new SpaceDetailService();
							SpaceDetailVO spaceDetail = sds.selectOneSpaceDetail(spaceDetailId);
							String spaceId = spaceDetail.getSpaceId();
							pageContext.setAttribute("spaceId", spaceId);
							SpaceService ss = new SpaceService();
							SpaceVO spaceVO = ss.selectOneSpace(spaceId);
							pageContext.setAttribute("spaceVO", spaceVO);
						}
						%>
						<ul>
							<li>
								<figure><img src="<%=request.getContextPath()%>/space/showonepicture?spaceId=${spaceId}" class="img-fluid" alt="" width="70" height="70"></figure>
								<small>訂單編號:${orderMasterVO.orderMasterId}</small>
								<h4><a href="<%=request.getContextPath()%>/space/space.do?action=getOne_For_Display&spaceId=${spaceId}" style="font-size: x-large;">${spaceVO.spaceName}</a></h4>
								<p>訂單日期：${orderMasterVO.orderCreateDate}</p>
								<c:if test="${orderMasterVO.orderStatus == 'T'}">
									<p>訂單金額：${orderMasterVO.orderAmount}，未付款</p>
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/OrderMasterServlet" >
										<input type="hidden" name="orderMasterId" value="${orderMasterVO.orderMasterId}">
										<input type="hidden" name="action" value="selectOneOrderMaster">
       									<input type="submit" value="前往付款頁面" class="btn_1 large">
       								</FORM>
								</c:if>
								<c:if test="${orderMasterVO.orderStatus == 'F'}">
									<p>訂單金額：${orderMasterVO.orderAmount}，已付款</p>
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/OrderDetailServlet" >
										<input type="hidden" name="orderMasterId" value="${orderMasterVO.orderMasterId}">
										<input type="hidden" name="action" value="listAllOrderDetail">
       									<input type="submit" value="查看訂單明細" class="btn_1 large">
       								</FORM>
								</c:if>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/space/space.do" >
									<input type="hidden" name="spaceId" value="${spaceVO.spaceId}">
									<input type="hidden" name="action" value="getOne_For_Display">
       								<input type="submit" value="前往場地介紹" class="btn_1 large">
       							</FORM>
							</li>
						</ul>
						</c:forEach>
					</div>
				</div>
				<!-- /box_general-->
			</div>
			<!-- /container -->
		</div>
		<!-- /content-wrapper -->
	</main>
</div>
<!-- /page -->
    <!-- COMMON SCRIPTS -->
    <script src="<%=request.getContextPath()%>/frontend/js/common_scripts.js"></script>
    <script src="<%=request.getContextPath()%>/frontend/js/main.js"></script>

</body>
</html>
