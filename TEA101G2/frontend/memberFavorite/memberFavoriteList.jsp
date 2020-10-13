<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.memberFavorite.model.*"%>
<%@ page import="com.space.model.*"%>
<%@ page import="com.spaceDetail.model.*"%>
<%@ page import="com.spacePhoto.model.*"%>
<%@ page import="java.util.*"%>
<% Base64.Encoder encode = Base64.getEncoder();%>
<%MemberVO userVO = (MemberVO) session.getAttribute("userVO");%>
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
	            -webkit-line-clamp: 5; /*最多顯示5行*/
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
    </style>
    
</head>

<body>	
    <div id="page">
    	<jsp:include page="/frontend/other/header.jsp"/>
        <!-- /header -->
		<div class="container margin_60_35" style="margin-top: 50px">
		
				<%  
					MemberFavoriteService memFavoriteSvc = new MemberFavoriteService();
                	String memberId = userVO.getMemberId();
				    List<MemberFavoriteVO> list = memFavoriteSvc.getAllMemberFavoriteById(memberId);
				    pageContext.setAttribute("list",list);
				%>
		<jsp:useBean id="spaceSvc" scope="page" class="com.space.model.SpaceService" />
		<jsp:useBean id="spaceDetailSvc" scope="page" class="com.spaceDetail.model.SpaceDetailService" />	
		<jsp:useBean id="spacePhotoSvc" scope="page" class="com.spacePhoto.model.SpacePhotoService" />
	
			
            <div class="wrapper-grid">
            
                <div class="row list-wrapper">
				<c:forEach var="memberFavoriteVO" items="${list}" begin="0" end="<%=list.size()%>" varStatus="s">
                    <div class="col-xl-4 col-lg-6 col-md-6 list-item">
                        <div class="box_grid">
                            <figure>													 
                                <a href="#" class="wish_bt liked wish_del${s.index}"></a>
                                <a href="#"><img src="<%=request.getContextPath()%>/space/showonepicture?spaceId=${memberFavoriteVO.spaceId}" class="img-fluid" alt="" width="800" height="533"></a>
                                <small>${spaceSvc.selectOneSpace(memberFavoriteVO.spaceId).spaceType}</small>
                                <div class="read_more"><span>Read more</span></div>
                            </figure>
                            <div class="wrapper">
                            	<h3><a href="#">${spaceSvc.selectOneSpace(memberFavoriteVO.spaceId).spaceName}</a></h3>
                                <p class="ellipsis">${spaceSvc.selectOneSpace(memberFavoriteVO.spaceId).spaceText}</p>
                            </div>
                            <ul>
                                <li><i class="icon_clock_alt" style="margin-right:8px"></i>30min / <span class="price">
                                <strong>${spaceDetailSvc.selectOneLowest(memberFavoriteVO.spaceId).spaceDetailCharge}
                                $</strong></span></li>
                                <li>
								<!--  <div class="score"><span>Good<em>350 Reviews</em></span><strong>5.0</strong></div> -->
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
            var len = 90; 
            $(".ellipsis").each(function (i) {
                if ($(this).text().length > len) {
                    $(this).attr("title", $(this).text());
                    var text = $(this).text().substring(0, len - 1);
                    $(this).text(text+"...").append
                    ('<a href="" style="font-size:8px;font-weight:bold">(Read More)</a>'); 
                }
            });
        });
    </script>
    <!-- common scripts-->
    <script src="<%=request.getContextPath()%>/frontend/js/common_scripts.js"></script>
    <script src="<%=request.getContextPath()%>/frontend/js/main.js"></script> 
</body>
</html>