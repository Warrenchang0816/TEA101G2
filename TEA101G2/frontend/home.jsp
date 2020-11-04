<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.memberComment.model.*"%>
<%@ page import="com.space.model.*"%>
<%@ page import="com.spaceDetail.model.*"%>

<jsp:useBean id="memberFavoriteSvc" scope="page" class="com.memberFavorite.model.MemberFavoriteService" />
<jsp:useBean id="spaceSvc" scope="page" class="com.space.model.SpaceService" />
<jsp:useBean id="spaceDetailSvc" scope="page" class="com.spaceDetail.model.SpaceDetailService" />
<jsp:useBean id="spaceCommentSvc" scope="page" class="com.spaceComment.model.SpaceCommentService" />
<jsp:useBean id="spacePhotoSvc" scope="page" class="com.spacePhoto.model.SpacePhotoService" />

<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description"
		content="Panagea - Premium site template for travel agencies, hotels and restaurant listing.">
	<meta name="author" content="Ansonika">
	<title>Home</title>

	<!-- GOOGLE WEB FONT -->
	<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800&display=swap"
		rel="stylesheet">

	<!-- BASE CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link href="<%=request.getContextPath()%>/frontend/css/bootstrap.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/frontend/css/style.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/frontend/css/vendors.css" rel="stylesheet">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/frontend/admin_section/css/admin.css">
	<script src="<%=request.getContextPath()%>/frontend/js/jquery-3.5.1.min.js"></script>
	<script src="https://cdn.tutorialjinni.com/simplePagination.js/1.6/jquery.simplePagination.js"></script>

	<style>
		.ellipsis {
			overflow: hidden;
			white-space: nowrap;
			text-overflow: ellipsis;
			display: -webkit-box;
			-webkit-line-clamp: 1;
			-webkit-box-orient: vertical;
			white-space: normal;
		}

		.list-wrapper {
			padding: 15px;
			overflow: hidden;
		}

		.list-item h4 {
			color: #FF7182;
			font-size: 18px;
			margin: 0 0 5px;
		}

		.list-item p {
			margin: 0;
		}

		.simple-pagination ul {
			margin: 0 0 20px;
			padding: 0;
			list-style: none;
			text-align: center;
		}

		.simple-pagination li {
			display: inline-block;
			margin-right: 5px;
		}

		.simple-pagination li a,
		.simple-pagination li span {
			color: #666;
			padding: 5px 10px;
			text-decoration: none;
			border: 1px solid #EEE;
			background-color: #FFF;
			box-shadow: 0px 0px 10px 0px #EEE;
		}

		.simple-pagination .current {
			color: #FFF;
			background-color: #FF7182;
			border-color: #FF7182;
		}

		.simple-pagination .prev.current,
		.simple-pagination .next.current {
			background: #e04e60;
		}
		
		.hero_single.version_2:before {
 		 background: url(<%=request.getContextPath()%>/frontend/image/home.jpg) center center no-repeat;
		}
		
	</style>

</head>

<body>
	<div id="page">
		<jsp:include page="/frontend/other/header.jsp" />
		<!-- /header -->
		<section class="hero_single version_2">
			<div class="wrapper">
				<div class="container">
					<h3></h3>
					<p>搜尋你想要的場地</p>
					<form method="post" action="<%=request.getContextPath()%>/space/space.do">
						<div class="row no-gutters custom-search-input-2">
							<div class="col-lg-3">
								<div class="form-group">
									<input type="text" name="spaceAddress" class="form-control" placeholder="區域">
									<i class="icon_pin_alt"></i>
								</div>
							</div>

							<div class="col-lg-4">
								<div class="form-group">
									<input type="text" name="spaceContain" class="form-control" placeholder="人數">
									<i class="icon_search"></i>
								</div>
							</div>

							<div class="col-lg-3">
								<select name="spaceType" class="wide">
									<option value="">類型</option>
									<option value="會議">會議</option>
									<option value="課程講座">課程講座</option>
									<option value="親子活動">親子活動</option>
									<option value="攝影棚">攝影棚</option>
								</select>
							</div>

							<div class="col-lg-2">
								<input type="hidden" name="action" value="search_space">
								<input type="submit" class="btn_search" value="Search">
							</div>
							
						</div>
						<a href="http://localhost:8082/spaceServletStable_(1)/frontendPage/homePage.jsp" style="color:red;font-size: large;">demo</a>
						<!-- /row -->
					</form>
				</div>
			</div>
		</section>
		<!-- /search -->
		<div class="container margin_60_35">
			<div class="main_title_2">
				<span><em></em></span>
				<h2>會議</h2>
				<p>可舉辦會議、研討會、讀書會等</p>
			</div>
			<div class="wrapper-grid">
				<div class="row">
					<c:forEach var="spaceVO" items="${spaceSvc.getAll()}" varStatus="s">
						<c:if test="${spaceVO.spaceStatus eq 'T'}">
							<c:if test="${spaceVO.spaceType eq '會議'}">
							<div class="col-xl-4 col-lg-6 col-md-6">
								<div class="box_grid">
									<figure>
										<c:if test="${userVO != null}">
											<c:choose>
												<c:when test="${memberFavoriteSvc.getMemberFavoriteStatus(userVO.memberId,spaceVO.spaceId) != null }">
													<a href="#0" class="wish_bt mfsA${s.index} liked"></a>
												</c:when>
												<c:otherwise>
													<a href="#0" class="wish_bt mfsA${s.index}"></a>
												</c:otherwise>
											</c:choose>
										</c:if>
										<form method="post" action="<%=request.getContextPath()%>/space/space.do" id="form_getOneSpaceA${s.index}">
											<input type="hidden" name="spaceId" value="${spaceVO.spaceId}">
											<input type="hidden" name="action" value="getOne_For_Display">
       									</form>
										<a href="#0" onclick="document.getElementById('form_getOneSpaceA${s.index}').submit()">
										<img src="<%=request.getContextPath()%>/space/showonepicture?spaceId=${spaceVO.spaceId}"
												class="img-fluid" alt="" width="800" height="533"></a>
										<small>${spaceVO.spaceType}</small>
										<div class="read_more"><span>Read more</span></div>
									</figure>
									<div class="wrapper">
										<div class="cat_star">
											<c:forEach var="i" begin="1" end="${spaceCommentSvc.getSpaceRating(spaceVO.spaceId)}">
												<i class="icon_star"></i>
											</c:forEach>
										</div>
										<h3>
										<a href="#0" onclick="document.getElementById('form_getOneSpaceA${s.index}').submit()">${spaceVO.spaceName}</a></h3>
										<p class="ellipsis">${spaceVO.spaceText}</p>
										<span class="price"><i class="icon_clock_alt" style="margin-right:8px"></i>1 hr
											/ <strong>${spaceDetailSvc.selectOneLowest(spaceVO.spaceId).spaceDetailCharge}
												$</strong></span>
												<br>
												<small class="address_ellipsis">${spaceVO.spaceAddress}</small>
									</div>
									<ul>
										<li><i class="ti-eye"></i></li>
										<li>
											<div class="score"><span>
												<c:if test="${spaceCommentSvc.getSpaceRating(spaceVO.spaceId) >= 3.0 and spaceCommentSvc.getSpaceRating(spaceVO.spaceId) <= 3.9 }">
													Good
												</c:if>
												<c:if test="${spaceCommentSvc.getSpaceRating(spaceVO.spaceId) >= 4.0}">
													Awesome
												</c:if>
											<em>${spaceCommentSvc.getSpaceCommentCount(spaceVO.spaceId)} Reviews</em></span>
											<strong>${spaceCommentSvc.getSpaceRating(spaceVO.spaceId)}</strong>
											</div>
										</li>
									</ul>
								</div>
							</div>
							<form method="post" action="<%=request.getContextPath()%>/MemberFavoriteServlet.do" id="form_getMemberFavoriteStatusA${s.index}">
								<input type="hidden" name="memberId" value="${userVO.memberId}">
								<input type="hidden" name="spaceId" value="${spaceVO.spaceId}">
								<input type="hidden" name="action" value="getMemberFavoriteStatus">
							</form>
							</c:if>
						</c:if>
						 <script>
					    	$(document).ready(function () {
					    		$('.mfsA${s.index}').on('click', function() {
						            $.ajax({
						                url: "<%=request.getContextPath()%>/MemberFavoriteServlet.do",
						                type: "POST",
						                dataType: "json",
						                data: $("#form_getMemberFavoriteStatusA${s.index}").serialize(),
						                beforeSend: function () {},
						
						                success: function (data) {
						                    console.log("success");
						                    if(data == null){
						                    	$(this).removeClass('liked');
						                    } else{
						                    	$(this).addClass('liked');
						                    }
						                },
						
						                error: function (xhr) {
						                    consoloe.log("error");
						                },
						
						                complete: function (xhr) {
						                }
						            });
						            return false;
						        });
						    });
	    				</script>
					</c:forEach>
					<!-- /box_grid -->
				</div>
				<!-- /row -->
			</div>
			<!-- /isotope-wrapper -->
			<div class="main_title_2">
				<span><em></em></span>
				<h2>課程講座</h2>
				<p>可舉辦課程、演講、說明會、發表會等</p>
			</div>
			<div class="wrapper-grid">
				<div class="row">
					<c:forEach var="spaceVO" items="${spaceSvc.getAll()}" varStatus="s">
						<c:if test="${spaceVO.spaceStatus eq 'T'}">
							<c:if test="${spaceVO.spaceType eq '課程講座'}">
							<div class="col-xl-4 col-lg-6 col-md-6">
								<div class="box_grid">
									<figure>
										<c:if test="${userVO != null}">
											<c:choose>
												<c:when test="${memberFavoriteSvc.getMemberFavoriteStatus(userVO.memberId,spaceVO.spaceId) != null }">
													<a href="#0" class="wish_bt mfsB${s.index} liked"></a>
												</c:when>
												<c:otherwise>
													<a href="#0" class="wish_bt mfsB${s.index}"></a>
												</c:otherwise>
											</c:choose>
										</c:if>
										<form method="post" action="<%=request.getContextPath()%>/space/space.do" id="form_getOneSpaceB${s.index}">
											<input type="hidden" name="spaceId" value="${spaceVO.spaceId}">
											<input type="hidden" name="action" value="getOne_For_Display">
       									</form>
										<a href="#0" onclick="document.getElementById('form_getOneSpaceB${s.index}').submit()"><img src="<%=request.getContextPath()%>/space/showonepicture?spaceId=${spaceVO.spaceId}"
												class="img-fluid" alt="" width="800" height="533"></a>
										<small>${spaceVO.spaceType}</small>
										<div class="read_more"><span>Read more</span></div>
									</figure>
									<div class="wrapper">
										<div class="cat_star">
											<c:forEach var="i" begin="1" end="${spaceCommentSvc.getSpaceRating(spaceVO.spaceId)}">
												<i class="icon_star"></i>
											</c:forEach>
										</div>
										<h3><a href="#0" onclick="document.getElementById('form_getOneSpaceB${s.index}').submit()">${spaceVO.spaceName}</a></h3>
										<p class="ellipsis">${spaceVO.spaceText}</p>
										<span class="price"><i class="icon_clock_alt" style="margin-right:8px"></i>1 hr
											/ <strong>${spaceDetailSvc.selectOneLowest(spaceVO.spaceId).spaceDetailCharge}
												$</strong></span>
												<br>
												<small class="address_ellipsis">${spaceVO.spaceAddress}</small>
									</div>
									<ul>
										<li><i class="ti-eye"></i></li>
										<li>
											<div class="score"><span>
												<c:if test="${spaceCommentSvc.getSpaceRating(spaceVO.spaceId) >= 3.0 and spaceCommentSvc.getSpaceRating(spaceVO.spaceId) <= 3.9 }">
													Good
												</c:if>
												<c:if test="${spaceCommentSvc.getSpaceRating(spaceVO.spaceId) >= 4.0}">
													Awesome
												</c:if>
											<em>${spaceCommentSvc.getSpaceCommentCount(spaceVO.spaceId)} Reviews</em></span>
											<strong>${spaceCommentSvc.getSpaceRating(spaceVO.spaceId)}</strong>
											</div>
										</li>
									</ul>
								</div>
							</div>
							<form method="post" action="<%=request.getContextPath()%>/MemberFavoriteServlet.do" id="form_getMemberFavoriteStatusB${s.index}">
								<input type="hidden" name="memberId" value="${userVO.memberId}">
								<input type="hidden" name="spaceId" value="${spaceVO.spaceId}">
								<input type="hidden" name="action" value="getMemberFavoriteStatus">
							</form>
							</c:if>
						</c:if>
						 <script>
					    	$(document).ready(function () {
					    		$('.mfsB${s.index}').on('click', function() {
						            $.ajax({
						                url: "<%=request.getContextPath()%>/MemberFavoriteServlet.do",
						                type: "POST",
						                dataType: "json",
						                data: $("#form_getMemberFavoriteStatusB${s.index}").serialize(),
						                beforeSend: function () {},
						
						                success: function (data) {
						                    console.log("success");
						                    if(data == null){
						                    	$(this).removeClass('liked');
						                    } else{
						                    	$(this).addClass('liked');
						                    }
						                },
						
						                error: function (xhr) {
						                    consoloe.log("error");
						                },
						
						                complete: function (xhr) {
						                }
						            });
						            return false;
						        });
						    });
	    				</script>
					</c:forEach>
					<!-- /box_grid -->
				</div>
				<!-- /row -->
			</div>
			<div class="main_title_2">
				<span><em></em></span>
				<h2>親子活動</h2>
				<p>可舉辦親子活動、幼兒教育活動等</p>
			</div>
			<div class="wrapper-grid">
				<div class="row">
					<c:forEach var="spaceVO" items="${spaceSvc.getAll()}" varStatus="s">
						<c:if test="${spaceVO.spaceStatus eq 'T'}">
							<c:if test="${spaceVO.spaceType eq '親子活動'}">
							<div class="col-xl-4 col-lg-6 col-md-6">
								<div class="box_grid">
									<figure>
										<c:if test="${userVO != null}">
											<c:choose>
												<c:when test="${memberFavoriteSvc.getMemberFavoriteStatus(userVO.memberId,spaceVO.spaceId) != null }">
													<a href="#0" class="wish_bt mfsC${s.index} liked"></a>
												</c:when>
												<c:otherwise>
													<a href="#0" class="wish_bt mfsC${s.index}"></a>
												</c:otherwise>
											</c:choose>
										</c:if>
										<form method="post" action="<%=request.getContextPath()%>/space/space.do" id="form_getOneSpaceC${s.index}">
											<input type="hidden" name="spaceId" value="${spaceVO.spaceId}">
											<input type="hidden" name="action" value="getOne_For_Display">
       									</form>
										<a href="#0" onclick="document.getElementById('form_getOneSpaceC${s.index}').submit()"><img src="<%=request.getContextPath()%>/space/showonepicture?spaceId=${spaceVO.spaceId}"
												class="img-fluid" alt="" width="800" height="533"></a>
										<small>${spaceVO.spaceType}</small>
										<div class="read_more"><span>Read more</span></div>
									</figure>
									<div class="wrapper">
										<div class="cat_star">
											<c:forEach var="i" begin="1" end="${spaceCommentSvc.getSpaceRating(spaceVO.spaceId)}">
												<i class="icon_star"></i>
											</c:forEach>
										</div>
										<h3><a href="#0" onclick="document.getElementById('form_getOneSpaceC${s.index}').submit()">${spaceVO.spaceName}</a></h3>
										<p class="ellipsis">${spaceVO.spaceText}</p>
										<span class="price"><i class="icon_clock_alt" style="margin-right:8px"></i>1 hr
											/ <strong>${spaceDetailSvc.selectOneLowest(spaceVO.spaceId).spaceDetailCharge}
												$</strong></span>
												<br>
												<small class="address_ellipsis">${spaceVO.spaceAddress}</small>
									</div>
									<ul>
										<li><i class="ti-eye"></i></li>
										<li>
											<div class="score"><span>
												<c:if test="${spaceCommentSvc.getSpaceRating(spaceVO.spaceId) >= 3.0 and spaceCommentSvc.getSpaceRating(spaceVO.spaceId) <= 3.9 }">
													Good
												</c:if>
												<c:if test="${spaceCommentSvc.getSpaceRating(spaceVO.spaceId) >= 4.0}">
													Awesome
												</c:if>
											<em>${spaceCommentSvc.getSpaceCommentCount(spaceVO.spaceId)} Reviews</em></span>
											<strong>${spaceCommentSvc.getSpaceRating(spaceVO.spaceId)}</strong>
											</div>
										</li>
									</ul>
								</div>
							</div>
							<form method="post" action="<%=request.getContextPath()%>/MemberFavoriteServlet.do" id="form_getMemberFavoriteStatusC${s.index}">
								<input type="hidden" name="memberId" value="${userVO.memberId}">
								<input type="hidden" name="spaceId" value="${spaceVO.spaceId}">
								<input type="hidden" name="action" value="getMemberFavoriteStatus">
							</form>
							</c:if>
						</c:if>
						 <script>
					    	$(document).ready(function () {
					    		$('.mfsC${s.index}').on('click', function() {
						            $.ajax({
						                url: "<%=request.getContextPath()%>/MemberFavoriteServlet.do",
						                type: "POST",
						                dataType: "json",
						                data: $("#form_getMemberFavoriteStatusC${s.index}").serialize(),
						                beforeSend: function () {},
						
						                success: function (data) {
						                    console.log("success");
						                    if(data == null){
						                    	$(this).removeClass('liked');
						                    } else{
						                    	$(this).addClass('liked');
						                    }
						                },
						
						                error: function (xhr) {
						                    consoloe.log("error");
						                },
						
						                complete: function (xhr) {
						                }
						            });
						            return false;
						        });
						    });
	    				</script>
					</c:forEach>
					<!-- /box_grid -->
				</div>
				<!-- /row -->
			</div>
		</div>
	</div>
	<!-- common script -->

	<script>
		$(function () {
			var len = 20;
			$(".ellipsis").each(function (i) {
				if ($(this).text().length > len) {
					$(this).attr("title", $(this).text());
					var text = $(this).text().substring(0, len - 1);
					$(this).text(text + "...");
				}
			});
		});
		
		$(function () {
	         var len = 26; 
	         $(".address_ellipsis").each(function (i) {
	             if ($(this).text().length > len) {
	                 $(this).attr("title", $(this).text());
	                 var text = $(this).text().substring(0, len - 1);
	                 $(this).text(text+"..."); 
	             }
	         });
	     });
	</script>

	<script src="<%=request.getContextPath()%>/frontend/js/common_scripts.js"></script>
	<script src="<%=request.getContextPath()%>/frontend/js/main.js"></script>
	<script src="<%=request.getContextPath()%>/admin_section/vendor/jquery.magnific-popup.min.js"></script>
</body>

</html>