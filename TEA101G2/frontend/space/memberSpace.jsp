<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.space.model.*"%>
<%@ page import="com.spaceDetail.model.*"%>
<%@ page import="com.spacePhoto.model.*"%>
<%@ page import="java.util.*"%>

<jsp:useBean id="spaceSvc" scope="page" class="com.space.model.SpaceService" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description"
        content="Panagea - Premium site template for travel agencies, hotels and restaurant listing.">
    <meta name="author" content="Ansonika">
    <title>Member Space List</title>

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
    <script src="https://cdn.tutorialjinni.com/simplePagination.js/1.6/jquery.simplePagination.js"></script>

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
	        
         .list-wrapper {
            padding: 15px;
            overflow: hidden;
        }

        .list-item {
            border: 1px solid #EEE;
            background: #FFF;
            margin-bottom: 10px;
            padding: 10px;
            box-shadow: 0px 0px 10px 0px #EEE;
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
			
            <div class="wrapper-grid">
            
                <div class="row list-wrapper">
				<c:forEach var="spaceVO" items="${spaceSvc.selectAllByMemId(userVO.memberId)}" varStatus="s">
                    <div class="col-xl-4 col-lg-6 col-md-6">
                        <div class="box_grid list-item">
                            <figure>
                           	    <form method="post" action="<%=request.getContextPath()%>/space/space.do" id="form_getOneSpace${s.index}">
									<input type="hidden" name="spaceId" value="${spaceVO.spaceId}">
									<input type="hidden" name="action" value="getOne_For_Display">
       							</form>													 
                                <a href="#0" onclick="document.getElementById('form_getOneSpace${s.index}').submit()">
                                <img src="<%=request.getContextPath()%>/space/showonepicture?spaceId=${spaceVO.spaceId}" class="img-fluid" alt="" width="800" height="533"></a>
                                <small>${spaceVO.spaceType}</small>
                                <div class="read_more"><span>Read more</span></div>
                            </figure>
                            <div class="wrapper">
                            <form method="post" action="<%=request.getContextPath()%>/space/space.do" id="form_getOneSpace${s.index}">
									<input type="hidden" name="spaceId" value="${spaceVO.spaceId}">
									<input type="hidden" name="action" value="getOne_For_Display">
       							</form>												
                            	<h3><a href="#0" onclick="document.getElementById('form_getOneSpace${s.index}').submit()">${spaceVO.spaceName}</a></h3>
                                <p class="ellipsis">張貼日期 ${spaceVO.spaceSignupDate}</p>
                            </div>
                        </div>
                    </div>
                    </c:forEach>
                    <!-- /box_grid -->
                </div>
                <div id="pagination-container"></div>
                <!-- /row -->
            </div>
            <!-- /isotope-wrapper -->
        </div>
        <!-- /container -->
    </div>
    <!-- common scripts-->
    <script>
	    var items = $("div.list-wrapper div.list-item");
	    var numItems = items.length;
	    var perPage = 3;
	    items.slice(perPage).hide();
	
	    $('#pagination-container').pagination({
	        items: numItems,
	        itemsOnPage: perPage,
	        prevText: "&laquo;",
	        nextText: "&raquo;",
	        onPageClick: function (pageNumber) {
	            var showFrom = perPage * (pageNumber - 1);
	            var showTo = showFrom + perPage;
	            items.hide().slice(showFrom, showTo).show();
	        }
	   });
    </script>
    <script src="<%=request.getContextPath()%>/frontend/js/common_scripts.js"></script>
    <script src="<%=request.getContextPath()%>/frontend/js/main.js"></script> 
</body>
</html>