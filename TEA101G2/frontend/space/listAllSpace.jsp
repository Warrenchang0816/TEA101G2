<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.space.model.*"%>
<%@ page import="com.spacePhoto.model.*"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<jsp:useBean id="spaceSvc" scope="page" class="com.space.model.SpaceService" />
<jsp:useBean id="spaceCommentSvc" scope="page" class="com.spaceComment.model.SpaceCommentService" />

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>所有場地</title>

<!-- GOOGLE WEB FONT -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">

<!-- BASE CSS -->
    <link href="<%=request.getContextPath()%>/plugins/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/plugins/css/style.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/plugins/css/vendors.css" rel="stylesheet">

<!-- Your custom styles -->
    <link href="<%=request.getContextPath()%>/plugins/css/custom.css" rel="stylesheet" type="text/css">

<!-- Favicons-->
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
    <link rel="apple-touch-icon" type="image/x-icon" href="img/apple-touch-icon-57x57-precomposed.png">
    <link rel="apple-touch-icon" type="image/x-icon" sizes="72x72" href="img/apple-touch-icon-72x72-precomposed.png">
    <link rel="apple-touch-icon" type="image/x-icon" sizes="114x114" href="img/apple-touch-icon-114x114-precomposed.png">
    <link rel="apple-touch-icon" type="image/x-icon" sizes="144x144" href="img/apple-touch-icon-144x144-precomposed.png">
    
</head>
<body>

<div id="page">
<jsp:include page="/frontend/other/header.jsp" />	

  <main>
		
	<section class="hero_in hotels">
		<div class="wrapper">
			<div class="container">
				<h1 class="fadeInUp"><span></span>我的場地</h1>
			</div>
		</div>
	</section>
	<!--/hero_in-->
	
	<div class="container margin_60_35">
		<div class="box_list">
			<div class="row no-gutters">
				<c:forEach var="spaceVO" items="${spaceSvc.selectAllByMemId(userVO.memberId)}">
					<div class="col-lg-5">
						<figure>
							<small>${spaceVO.spaceId}</small>
							<a><img src="<%=request.getContextPath()%>/space/showonepicture?spaceId=${spaceVO.spaceId}" class="img-fluid" alt="" width="800" height="533"></a>
						</figure>
					</div>
					<div class="col-lg-7">
						<div class="wrapper">
							<div class="cat_star">
								<c:forEach var="i" begin="1" end="${spaceCommentSvc.getSpaceRating(spaceVO.spaceId)}">
									<i class="icon_star"></i>
								</c:forEach>
							</div>
							<h3><a>${spaceVO.spaceName}</a></h3>
							<p>${spaceVO.spaceText}</p>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/space/space.do" >
								<input type="hidden" name="spaceId" value="${spaceVO.spaceId}">
								<input type="hidden" name="action" value="getOne_For_Display">
       							<input type="submit" value="管理場地" class="btn_1 large">
       						</FORM>
       						<ul>
								<li><i class="ti-eye"></i></li>
								<li><div class="score"><span>
								<c:if test="${spaceCommentSvc.getSpaceRating(spaceVO.spaceId) >= 3.0 and spaceCommentSvc.getSpaceRating(spaceVO.spaceId) <= 3.9 }">
									Good
								</c:if>
								<c:if test="${spaceCommentSvc.getSpaceRating(spaceVO.spaceId) >= 4.0}">
									Awesome
								</c:if>
								<em>${spaceCommentSvc.getSpaceCommentCount(spaceVO.spaceId)} Reviews</em></span><strong>${spaceCommentSvc.getSpaceRating(spaceVO.spaceId)}</strong></div></li>
							</ul>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</main>
<!--/main-->
</div>
<!-- page -->

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