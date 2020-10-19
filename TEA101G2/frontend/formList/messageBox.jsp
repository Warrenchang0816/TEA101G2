<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.memberComment.model.*"%>
<%@ page import="com.formList.model.*"%>
<%@ page import="java.util.*"%>
<% Base64.Encoder encode = Base64.getEncoder();%>
<!DOCTYPE html>
<html lang="en">

<%
	MemberVO formlistUserVO = (MemberVO) session.getAttribute("userVO");
    String formlistUserId = formlistUserVO.getMemberId();
	FormListService formListSvc = new FormListService();
	List<FormListVO> messageList = formListSvc.selectAllMessagesByGet(formlistUserId);
	pageContext.setAttribute("messageList",messageList);
	
%>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description"
        content="Panagea - Premium site template for travel agencies, hotels and restaurant listing.">
    <meta name="author" content="Ansonika">
    <title>訊息</title>

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


</head>

<body>
	
    <div id="page">
        <jsp:include page="/frontend/other/header.jsp"/>
        <!-- /header -->

        <div style="margin-top: 90px; margin-left: 30px;">
            <label style="font-size: 30px">訊息<span style="color: #fc5b62"> > </span></label>
        </div>

        <div class="content-wrapper">
            <div class="container-fluid">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item">
                        <a href="#">會員中心</a>
                    </li>
                    <li class="breadcrumb-item active">訊息</li>
                </ol>
                <!-- Breadcrumbs-->
                <div class="box_general padding_bottom">
                    <div class="header_box version_2">
                        <h2><i class="fa fa-user"></i>Message Box</h2>
                    </div>
                    

  <!-- /Navigation-->
  <div >
    <div class="container-fluid">
			<div class="list_general">
			<ul>
<c:forEach var="formListVO" items="${messageList}" begin="0" end="<%=messageList.size()%>" varStatus="Count">
				
					<li>
						<span>${formListVO.formListCreateDate}</span>
						<figure><img src="img/avatar1.jpg" alt=""></figure>
						<h4>${formListVO.formListTitle}${formListVO.formListStatus == "M"? "<i class='unread'>NEW</i>" : ""}</h4>
						<p>${formListVO.formListContext}</p>
					</li>
</c:forEach>
				
	
				</ul>
			</div>
		<!-- /box_general-->
		<nav aria-label="...">
			<ul class="pagination pagination-sm add_bottom_30">
				<li class="page-item disabled">
					<a class="page-link" href="#" tabindex="-1">Previous</a>
				</li>
				<li class="page-item"><a class="page-link" href="#">1</a></li>
				<li class="page-item"><a class="page-link" href="#">2</a></li>
				<li class="page-item"><a class="page-link" href="#">3</a></li>
				<li class="page-item">
					<a class="page-link" href="#">Next</a>
				</li>
			</ul>
		</nav>
		<!-- /pagination-->
	  </div>
	  <!-- /container-fluid-->
   	</div>
    <!-- /container-wrapper-->





	            </div>
	        </div>
   		 </div>
 <!-- Replay Popup Window -->
        </div>
    </div>
    <!-- COMMON SCRIPTS -->

    <script> 
 
    </script>
    
    <script src="<%=request.getContextPath()%>/frontend/js/common_scripts.js"></script>
    <script src="<%=request.getContextPath()%>/frontend/js/main.js"></script>
</body>
</html>