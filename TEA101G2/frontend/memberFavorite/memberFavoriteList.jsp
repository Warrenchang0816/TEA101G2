<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.memberFavorite.model.*"%>
<%@ page import="com.space.model.*"%>
<%@ page import="com.spaceDetail.model.*"%>
<%@ page import="com.spacePhoto.model.*"%>
<%@ page import="java.util.*"%>

<jsp:useBean id="memberFavoriteSvc" scope="page" class="com.memberFavorite.model.MemberFavoriteService" />
<jsp:useBean id="spaceSvc" scope="page" class="com.space.model.SpaceService" />
<jsp:useBean id="spaceDetailSvc" scope="page" class="com.spaceDetail.model.SpaceDetailService" />	
<jsp:useBean id="spacePhotoSvc" scope="page" class="com.spacePhoto.model.SpacePhotoService" />
<jsp:useBean id="spaceCommentSvc" scope="page" class="com.spaceComment.model.SpaceCommentService" />
		
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description"
        content="Panagea - Premium site template for travel agencies, hotels and restaurant listing.">
    <meta name="author" content="Ansonika">
    <title>Favorite List</title>

    <!-- GOOGLE WEB FONT -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">

    <!-- BASE CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="<%=request.getContextPath()%>/frontend/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/frontend/css/style.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/frontend/css/vendors.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/frontend/admin_section/css/admin.css">
    <!-- ********** -->
    <script src="<%=request.getContextPath()%>/frontend/js/jquery-3.5.1.min.js"></script>

    <style>
	    .ellipsis {
	            overflow: hidden;
	            white-space: nowrap;
	            text-overflow: ellipsis;
	            display: -webkit-box;
	            -webkit-line-clamp: 3; 
	            -webkit-box-orient: vertical;
	            white-space: normal;
	    }
    </style>
    
</head>

<body>	
    <div id="page">
    	<jsp:include page="/frontend/other/header.jsp"/>
        <!-- /header -->
		<div class="container margin_60_35" style="margin-top: 50px">
			
            <div class="wrapper-grid">
            
                <div class="row list-wrapper">
				<c:forEach var="memberFavoriteVO" items="${memberFavoriteSvc.getAllMemberFavoriteById(userVO.memberId)}" varStatus="s">
                    <div class="col-xl-4 col-lg-6 col-md-6 list-item">
                        <div class="box_grid">
                            <figure>
                           	    <form method="post" action="<%=request.getContextPath()%>/space/space.do" id="form_getOneSpace${s.index}">
									<input type="hidden" name="spaceId" value="${memberFavoriteVO.spaceId}">
									<input type="hidden" name="action" value="getOne_For_Display">
       							</form>													 
                                <a href="#0" class="wish_bt liked wish_del${s.index}"></a>
                                <a href="#0" onclick="document.getElementById('form_getOneSpace${s.index}').submit()">
                                <img src="<%=request.getContextPath()%>/space/showonepicture?spaceId=${memberFavoriteVO.spaceId}" class="img-fluid" alt="" width="800" height="533"></a>
                                <small>${spaceSvc.selectOneSpace(memberFavoriteVO.spaceId).spaceType}</small>
                                <div class="read_more"><span>Read more</span></div>
                            </figure>
                            <div class="wrapper">
                            		<div class="cat_star">
									<c:forEach var="i" begin="1" end="${spaceCommentSvc.getSpaceRating(memberFavoriteVO.spaceId)}">
										<i class="icon_star"></i>
									</c:forEach>
								</div>
                            	<h3><a href="#">${spaceSvc.selectOneSpace(memberFavoriteVO.spaceId).spaceName}</a></h3>
                                <p class="ellipsis">${spaceSvc.selectOneSpace(memberFavoriteVO.spaceId).spaceText}</p>
                                
                                <span class="price"><i class="icon_clock_alt" style="margin-right:8px"></i>1 hr
											/ <strong>${spaceDetailSvc.selectOneLowest(memberFavoriteVO.spaceId).spaceDetailCharge}
												$</strong></span>
								<br>
								<small class="address_ellipsis">${spaceSvc.selectOneSpace(memberFavoriteVO.spaceId).spaceAddress}</small>
                            </div>
                            <ul>
                                <li><i class="ti-eye"></i></li>
                                <li>
								 <div class="score"><span>
									<c:if test="${spaceCommentSvc.getSpaceRating(memberFavoriteVO.spaceId) >= 3.0 and spaceCommentSvc.getSpaceRating(memberFavoriteVO.spaceId) <= 3.9 }">
										Good
									</c:if>
									<c:if test="${spaceCommentSvc.getSpaceRating(memberFavoriteVO.spaceId) >= 4.0}">
										Awesome
									</c:if>			
								 <em>${spaceCommentSvc.getSpaceCommentCount(memberFavoriteVO.spaceId)} Reviews</em></span><strong>${spaceCommentSvc.getSpaceRating(memberFavoriteVO.spaceId)}</strong></div>
                                </li>
                            </ul>
                        </div>
                    </div>
	                    <form method="post" action="<%=request.getContextPath()%>/MemberFavoriteServlet.do" id="form_deleteMemberFavorite${s.index}">
							<input type="hidden" name="memberFavoriteId"  value="${memberFavoriteVO.memberFavoriteId}">
					    	<input type="hidden" name="action" value="deleteMemberFavorite">
					    </form>
						    <script>
					    	$(document).ready(function () {
					    		$('.wish_del${s.index}').on('click', function() {
						            $.ajax({
						                url: "<%=request.getContextPath()%>/MemberFavoriteServlet.do",
						                type: "POST",
						                dataType: "json",
						                data: $("#form_deleteMemberFavorite${s.index}").serialize(),
						                beforeSend: function () {},
						
						                success: function (data) {
						                    console.log("success");	
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
        </div>
        <!-- /container -->
    </div>
    
    <script>
        $('.wish_bt.liked').on('click', function (event) {
            $(this).parent().parent().parent().fadeOut('slow', function (event) {});
        });

        $(function () {
            var len = 20; 
            $(".ellipsis").each(function (i) {
                if ($(this).text().length > len) {
                    $(this).attr("title", $(this).text());
                    var text = $(this).text().substring(0, len - 1);
                    $(this).text(text+"..."); 
                }
            });
        });
    </script>
    <!-- common scripts-->
    <script src="<%=request.getContextPath()%>/frontend/js/common_scripts.js"></script>
    <script src="<%=request.getContextPath()%>/frontend/js/main.js"></script> 
</body>
</html>