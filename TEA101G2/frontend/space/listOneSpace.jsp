<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.spacePhoto.model.*"%>
<%@ page import="com.space.model.*"%>
<%@ page import="com.spaceDetail.model.*"%>
<%@ page import="com.orderDetail.model.*"%>

<%
  SpaceVO spaceVO = (SpaceVO) request.getAttribute("spaceVO"); //SpaceServlet.java(Concroller), 存入req的spaceVO物件
  SpacePhotoService spacePhotoSvc = new SpacePhotoService();
  List<SpacePhotoVO> photolist = spacePhotoSvc.getAllPhoto(spaceVO.getSpaceId());
  pageContext.setAttribute("photolist",photolist);
  SpaceDetailService spaceDetailSvc = new SpaceDetailService();
  SpaceDetailVO spaceDetailVO = spaceDetailSvc.selectOneLowest(spaceVO.getSpaceId());
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>場地資料</title>

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
<%@ include file="/frontend/header.jsp" %>		

  <main>
		
		<section class="hero_in hotels">
			<div class="wrapper">
				<div class="container">
					<h1 class="fadeInUp"><span></span><%=spaceVO.getSpaceName()%></h1>
				</div>
			</div>
		</section>
	<!--/hero_in-->
	<div class="bg_color_1">
		<div class="container margin_60_35">
			<div class="row">
				<div class="col-lg-8">
					<section id="description">
						<h2>場地介紹</h2>
						<p><%=spaceVO.getSpaceText()%></p>
						<h3>場地規則</h3>
						<p><%=spaceVO.getSpaceRule()%></p>
						<h3>場地退費須知</h3>
						<p><%=spaceVO.getSpaceRefund()%></p>
						<div class="row">
							<div class="col-lg-6">
								<ul class="bullets">
									<li>場地類型：<%=spaceVO.getSpaceType()%></li>
									<li>場地地址：<%=spaceVO.getSpaceAddress()%></li>
								</ul>
							</div>
							<div class="col-lg-6">
								<ul class="bullets">
									<li>場地設備：<%=spaceVO.getSpaceEquipment()%></li>
									<li>場地容納人數：<%=spaceVO.getSpaceContain()%>人</li>
								</ul>
							</div>
						</div>
						<!-- /row -->
						<hr>
						<h3>場地照片</h3>
						<div class="room_type first">
							<div class="row">
							<c:forEach var="spacePhotoVO" items="${photolist}">
								<div class="col-md-4">
									<img src="<%=request.getContextPath()%>/space/showpicture?spaceId=${spacePhotoVO.spaceId}" class="img-fluid" width="273" height="184">
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/spacephoto/spacephoto.do" style="margin-bottom: 0px;">
			     						<input type="submit" value="修改">
			     						<input type="hidden" name="spacePhotoId"  value="${spacePhotoVO.spacePhotoId}">
			     						<input type="hidden" name="action"	value="getOne_For_Update">
			     					</FORM>
			     					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/spacephoto/spacephoto.do" style="margin-bottom: 0px;">
			     						<input type="submit" value="刪除">
			     						<input type="hidden" name="spacePhotoId"  value="${spacePhotoVO.spacePhotoId}">
			     						<input type="hidden" name="action" value="delete">
			     					</FORM>
								</div>
							</c:forEach>
							</div>
							<!-- /row -->
						</div>
						<hr>
						<h3>Location</h3>
						<div id="map" class="map map_single add_bottom_30"></div>
						<!-- End Map -->
					</section>
					<!-- /section -->
				</div>
				<!-- col -->
				<aside class="col-lg-4" id="sidebar">
						<div class="box_detail booking">
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/spacedetail/spacedetail.do" name="form1">
							<div class="price">
								<span><%=spaceDetailVO.getSpaceDetailCharge()%> /小時<small>起</small></span>
							</div>
							
							<input type="hidden" name="spaceId" value="<%=spaceVO.getSpaceId()%>">
							<input type="hidden" name="memberId" value="<%=spaceVO.getMemberId()%>">
							<input type="hidden" name="orderCreateDate" value="<%=new Date()%>">
							<input type="hidden" name="action" value="listAllSpaceDetailBySpace">
							<input type="submit" value="搶先預約!" class="add_top_30 btn_1 full-width purchase">
							<div class="text-center"><small>此步驟不收取任何費用</small></div>
							</FORM>
						</div>
				</aside>
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

<!-- COMMON SCRIPTS -->
  	<script src="<%=request.getContextPath()%>/plugins/js/common_scripts.js"></script>
  	<script src="<%=request.getContextPath()%>/plugins/js/main.js"></script>
	
<!-- Map -->
	<script src="http://maps.googleapis.com/maps/api/js"></script>
	<script src="<%=request.getContextPath()%>/plugins/js/map_single_hotel.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/js/infobox.js"></script>
	
</body>

</html>