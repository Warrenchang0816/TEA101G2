<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.memberComment.model.*"%>
<%@ page import="com.formList.model.*"%>
<%@ page import="java.util.*"%>
<% Base64.Encoder encode = Base64.getEncoder();%>

<%

	MemberVO formlistUserVO = (MemberVO) session.getAttribute("userVO");
	String formlistUserId = formlistUserVO.getMemberId();
	FormListService formListSvc = new FormListService();
	List<FormListVO> messageListNew = formListSvc.selectAllNewMessagesByGet(formlistUserId);
	pageContext.setAttribute("messageListNew",messageListNew);

%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description"
        content="Panagea - Premium site template for travel agencies, hotels and restaurant listing.">
    <meta name="author" content="Ansonika">
    <title>Member Setting</title>

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
        a.box_topic i {
            color: gray;
            background-color:#272727;
            background-color: white;
        }
        
#unread {
  background-color: #dc3545;
  -webkit-border-radius: 50px;
  -moz-border-radius: 50px;
  -ms-border-radius: 50px;
  border-radius: 50px;
  font-size: 12px;
  font-size: 0.75rem;
  color: #fff;
  font-style: normal;
  padding: 3px 12px 2px 12px;
  margin-left: 3px;
  position: relative;
  top: -3px;
  line-height: 1;
}
#unread {
  background-color: #dc3545;
}
    </style>

</head>

<body>
	<div id="page">
        <jsp:include page="/frontend/other/header.jsp"/>
        <!-- /header -->
        
        <div class="container margin_60_35" style="margin-top: 50px">
            <div class="row">
                <div class="col-lg-4 col-md-6">
               		<form method="post" action="<%=request.getContextPath()%>/MemberServlet.do" id="form_updateMember">
	               			<input type="hidden" name="memberId" value="${userVO.memberId}">
							<input type="hidden" name="action" value="getOneUpdate">
	                    <a class="box_topic" href="#" onclick="document.getElementById('form_updateMember').submit()" style="border-radius: 30px">
	                        <span><i class="fa fa-pencil-square-o"></i></span>
	                        <h3><strong style="color:#6C6C6C">個人設定</strong></h3>
	                        <p>修改你的基本資料</p>
	                    </a></form>
                </div>
                <!-- UPDATE PROFILE-->
                <div class="col-lg-4 col-md-6">
                    <a class="box_topic" href="<%=request.getContextPath()%>/frontend/ordermaster/selectAllOrderMaster.jsp" style="border-radius: 30px">
                        <i class="fa fa-shopping-bag"></i>
                        <h3><strong style="color:#6C6C6C">訂單查詢</strong></h3>
                        <p>查詢你的訂單紀錄</p>
                    </a>
                </div>
                <!-- Query Order-->
                <div class="col-lg-4 col-md-6">
                    <a class="box_topic" href="<%=request.getContextPath()%>/frontend/space/memberSpace.jsp" style="border-radius: 30px">
                        <i class="fa fa-home"></i>
                        <h3><strong style="color:#6C6C6C">我的場地</strong></h3>
                        <p>查看你正在出租的場地</p>
                    </a>
                </div>
                <!-- RENT ROOM-->
                <div class="col-lg-4 col-md-6">
                    <a class="box_topic" href="<%=request.getContextPath()%>/frontend/formList/messageBox.jsp" style="border-radius: 30px">
                        <i class="fa fa-envelope-o" aria-hidden="true"></i>${messageListNew.size() == 0? "" : "<i id='unread'>NEW</i>"}
                        <h3><strong style="color:#6C6C6C">訊息</strong></h3>
                        <p>查看訊息</p>
                    </a>
                </div>
                <!-- MSG-->
            </div>
            <!--/row-->
        </div>
        <!-- /container -->
    </div>
    <!-- COMMON SCRIPTS -->
    <script src="<%=request.getContextPath()%>/frontend/js/common_scripts.js"></script>
    <script src="<%=request.getContextPath()%>/frontend/js/main.js"></script>
</body>
</html>