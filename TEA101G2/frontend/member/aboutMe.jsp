<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.memberComment.model.*"%>
<%@ page import="java.util.*"%>
<% Base64.Encoder encode = Base64.getEncoder();%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description"
        content="Panagea - Premium site template for travel agencies, hotels and restaurant listing.">
    <meta name="author" content="Ansonika">
    <title>關於我</title>

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
    <!-- ********** -->
    <script src="https://cdn.tutorialjinni.com/simplePagination.js/1.6/jquery.simplePagination.js"></script>

    <style>
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
	<%
 	 MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); 
	%>
	
    <div id="page">
        <jsp:include page="/frontend/other/header.jsp"/>
        <!-- /header -->

        <div style="margin-top: 90px; margin-left: 30px;">
            <label style="font-size: 30px">關於我<span style="color: #fc5b62"> > </span></label>
        </div>

        <div class="content-wrapper">
            <div class="container-fluid">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item">
                        <a href="#">會員中心</a>
                    </li>
                    <li class="breadcrumb-item active">關於我</li>
                </ol>
                <!-- Breadcrumbs-->
                <div class="box_general padding_bottom">
                    <div class="header_box version_2">
                        <h2><i class="fa fa-user"></i>Profile details</h2>
                    </div>
                    <div class="row">
                        <div class="col-md-4" style="padding-left: 30px">
                            <div class="form-group">
                            </div>
                            <img src="data:image/png;base64,<%=encode.encodeToString(memberVO.getMemberPhoto())%>"
                                width="200" height="200" style="border-radius: 100%">
                        </div>
                        <div class="col-md-8 add_top_30">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Name</label>
                                        <span>${memberVO.memberName}</span>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Last name</label>
                                        <span>${memberVO.memberNickName}</span>
                                    </div>
                                </div>
                            </div>
                            <!-- /row-->
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Telephone</label>
                                        <span>${memberVO.memberPhone}</span>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Email</label>
                                        <span>${memberVO.memberEmail}</span>
                                    </div>
                                </div>
                            </div>
                            <!-- /row-->
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>add time</label>
                                        <span>${memberVO.memberSignupDate}</span>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>level</label>
                                        <span>5</span>
                                    </div>
                                </div>
                            </div>
                            <!-- /row-->
                              <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>
	                                        <div id="contact">
	                                        	<button type="button" class="btn btn-info btn" data-toggle="modal" data-target="#contact-modal">Review</button>
	                                        </div>
                                        </label>
                                    </div>
                                </div>
                            </div>
                                <!-- /row-->
                        </div>
                    </div>
                </div>
                <!-- /box_general-->
                
                <%  
					MemberCommentService memCommentSvc = new MemberCommentService();
                	String memberId = memberVO.getMemberId();
				    List<MemberCommentVO> list = memCommentSvc.getAllMemberCommentById(memberId);
				    pageContext.setAttribute("list",list);
				%>
				
				<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
								
                <div class="box_general">
                    <div class="header_box">
                        <h2 class="d-inline-block">Reviews List</h2> 
                    </div>
                    <div class="list_general reviews">
                        <ul class="list-wrapper">
                      	<c:forEach var="memberCommentVO" items="${list}" begin="0" end="<%=list.size()%>">
                            <li class="list-item">
                                <span>${memberCommentVO.memberCommentDate}</span><span class="rating">
                                <c:choose>
                                	<c:when test="${memberCommentVO.memberCommentLevel <=1}">
                                		<i class="fa fa-fw fa-star yellow"></i><i
                                        class="fa fa-fw fa-star"></i><i class="fa fa-fw fa-star"></i><i
                                        class="fa fa-fw fa-star"></i><i class="fa fa-fw fa-star"></i>
                                	</c:when>
                                	<c:when test="${memberCommentVO.memberCommentLevel <=2}">
                                		<i class="fa fa-fw fa-star yellow"></i><i
                                        class="fa fa-fw fa-star yellow"></i><i class="fa fa-fw fa-star"></i><i
                                        class="fa fa-fw fa-star"></i><i class="fa fa-fw fa-star"></i>
                                	</c:when>
                                	<c:when test="${memberCommentVO.memberCommentLevel <=3}">
                                		<i class="fa fa-fw fa-star yellow"></i><i
                                        class="fa fa-fw fa-star yellow"></i><i class="fa fa-fw fa-star yellow"></i><i
                                        class="fa fa-fw fa-star"></i><i class="fa fa-fw fa-star"></i>
                                	</c:when>
                                	<c:when test="${memberCommentVO.memberCommentLevel <=4}">                     
                                		<i class="fa fa-fw fa-star yellow"></i><i
                                        class="fa fa-fw fa-star yellow"></i><i class="fa fa-fw fa-star yellow"></i><i
                                        class="fa fa-fw fa-star yellow"></i><i class="fa fa-fw fa-star"></i>
                                	</c:when>
                                	<c:when test="${memberCommentVO.memberCommentLevel <=5}">
                                		<i class="fa fa-fw fa-star yellow"></i><i
                                        class="fa fa-fw fa-star yellow"></i><i class="fa fa-fw fa-star yellow"></i><i
                                        class="fa fa-fw fa-star yellow"></i><i class="fa fa-fw fa-star yellow"></i>
                                	</c:when>
                                </c:choose></span><figure>
                                <img src="https://stickershop.line-scdn.net/stickershop/v1/product/9782648/LINEStorePC/main.png;compress=true">
                                </figure>
                                <h6>${memSvc.getOneMember(memberCommentVO.memberBId).memberName}</h6>
                                <p>${memberCommentVO.memberCommentContent}</p>
                                <div style="padding-bottom:35px"></div>
                            </li>
    						</c:forEach>
                        </ul>
                    </div>
                </div>
                <div id="pagination-container"></div>
                <!-- /row-->
                <p><a href="#0" class="btn_1 medium">返回</a></p>
            </div>
            <!-- /.container-fluid-->
            <div id="contact-modal" class="modal fade" role="dialog" style="padding-top:150px">
	        <div class="modal-dialog">
	            <div class="modal-content">
	                <div class="modal-header">
	                    <a class="close" data-dismiss="modal">×</a>
	                </div>
	                <form method="post" id="contactForm" name="contact">
	                    <div class="modal-body">
	                        <div class="form-group star_block">
	                            <label for="level" class="start_level">Level</label>
	                            <span class="rating">
	                            	<c:forEach begin="1" end="5" varStatus="loop">
				                       <span class="star" data-star="${loop.index}"><i class="fa fa-fw fa-star"></i></span>
				                    </c:forEach>
				               	</span>
	                        </div>
	                        <div class="form-group">
	                            <label for="message">Message</label>
<%-- 	                            <input type="text" id="memberCommentContent" name="memberCommentContent" class="form-control clear_memberCommentContent"" value="${param.memberCommentContent}"/> --%>
								<textarea id="memberCommentContent" name="memberCommentContent" class="form-control clear_memberCommentContent"></textarea>
	                        </div>
	                    </div>
	                    <div class="modal-footer">
	                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	                        <input type="submit" class="btn btn-danger" id="submit" value="Submit">
	                    </div>
	                </form>
	            </div>
	        </div>
   		 </div>
 <!-- Replay Popup Window -->
        </div>
    </div>
    <!-- COMMON SCRIPTS -->

    <script> 
    	<c:set var="memberName" value="${userVO.memberName}"/>
    	<c:set var="memberAId" value="${memberVO.memberId}"/>
    	<c:set var="memberBId" value="${userVO.memberId}"/>
    	let current_star = null;
    	
    	$(document).on("click","span.star",function(){
        	current_star = parseInt($(this).attr("data-star"));
        	if (!$(this).hasClass("yellow")) {
                $(this).addClass("yellow");
                $(this).prevAll().addClass("yellow");
            } else {
                $(this).nextAll().removeClass("yellow");
            }
        });
    	
    	var Today=new Date();
    	var commentData = (Today.getFullYear()+ "-" + (Today.getMonth()+1) + "-" + Today.getDate());
    	
	    $(document).ready(function(){	
	    	$("#contactForm").submit(function(event) {
	    		$.ajax({
	    			url: "<%=request.getContextPath()%>/MemberCommentServlet.do",
	    	   		type: "POST",
	    	   		dataType: "json",
	    	   		data: 
	    	   			{memberAId: '${memberAId}', 
	    	   			memberBId: '${memberBId}',
	    	   			memberCommentContent:$('textarea#memberCommentContent').val(),
	    	   			memberCommentLevel: current_star,
	    	   			action:'addMemberComment'},
	    	   		success: function(data){
		    	   		alert("success");
		    	   		let list_html = "";
		    	   		let star_html = "";
		    	   		star_html += '<span class="rating">';
		    	   		star_html += '<span class="star" data-star="1"><i class="fa fa-fw fa-star"></i></span>';
		    	   		star_html += '<span class="star" data-star="2"><i class="fa fa-fw fa-star"></i></span>';
		    	   		star_html += '<span class="star" data-star="3"><i class="fa fa-fw fa-star"></i></span>';
		    	   		star_html += '<span class="star" data-star="4"><i class="fa fa-fw fa-star"></i></span>';
		    	   		star_html += '<span class="star" data-star="5"><i class="fa fa-fw fa-star"></i></span>';
		    	   		star_html += '</span>';
		    	   	    list_html += '<li class="list-item">';
		    	   	    list_html += '<span>'+commentData+'</span><span class="rating">';
		    	  	 	list_html += '<i class="fa fa-fw fa-star'+(data.memberCommentLevel >= 1 ? " yellow" : "")+'"></i>';
		    	   	    list_html += '<i class="fa fa-fw fa-star'+(data.memberCommentLevel >= 2 ? " yellow" : "")+'"></i>';
		    	   	    list_html += '<i class="fa fa-fw fa-star'+(data.memberCommentLevel >= 3 ? " yellow" : "")+'"></i>';
		    	   	    list_html += '<i class="fa fa-fw fa-star'+(data.memberCommentLevel >= 4 ? " yellow" : "")+'"></i>';
		    	   	    list_html += '<i class="fa fa-fw fa-star'+(data.memberCommentLevel >= 5 ? " yellow" : "")+'"></i>';
		    	   	    list_html += '</span><figure>';
		    	   	    list_html += '<img src="https://stickershop.line-scdn.net/stickershop/v1/product/9782648/LINEStorePC/main.png;compress=true"></figure>';
		    	   	    list_html += '<h6>' + '${memberName}' + '</h6>';
		    	   	    list_html += '<p>' + data.memberCommentContent + '</p>';
		    	   	    list_html += '<div style="padding-bottom:35px">';
		    	   	    list_html += '</div>';
		    	   	    list_html += '</li>';
	    	   		 	$("ul.list-wrapper").prepend(list_html);
	    	   		 	$("#contact-modal").modal("hide");
	    	   			$("div.star_block").find("span.rating").remove();
	    	   			$("div.star_block").find("label.start_level").find("i").removeClass("yellow");
	    	   			$("div.star_block").append(star_html);
		    	   		$("textarea.clear_memberCommentContent").val("");
	    	   		},
	    	   		error: function(xhr){
	    	   			console.log(xhr);
	    	   			alert("Error");
	    	   		},
	    	   	});
	    		return false;
	    	});
	    });

        var items = $(".list-wrapper .list-item");
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