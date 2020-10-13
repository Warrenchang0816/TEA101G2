<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>

<% Base64.Encoder encode = Base64.getEncoder();%>
<%MemberVO userVO = (MemberVO)session.getAttribute("userVO");%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Panagea - Premium site template for travel agencies, hotels and restaurant listing.">
    <meta name="author" content="Ansonika">
    <title>更改密碼</title>

    <!-- GOOGLE WEB FONT -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">

    <!-- BASE CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="<%=request.getContextPath()%>/frontend/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/frontend/css/style.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/frontend/css/vendors.css" rel="stylesheet">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/frontend/admin_section/css/admin.css">
	<!-- VALIDATOR JS -->
	<script src="<%=request.getContextPath()%>/frontend/js/jquery-3.5.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.9/validator.min.js"></script>
</head>

<body>
	<div id="page">
		<jsp:include page="/frontend/other/header.jsp"/>
	<!-- /header -->
	
	<div style="margin-top: 90px; margin-left: 16px;">
		<label style="font-size: 30px">更改密碼<span style="color: #fc5b62"> > </span></label>
	</div>
	<div class="content-wrapper">
		<form method="post" action="<%=request.getContextPath()%>/MemberServlet.do" enctype="multipart/form-data" name="form_edit_profile">
		<div class="container-fluid">
			<ol class="breadcrumb">
				<li class="breadcrumb-item">
				  <a href="#">個人設定</a>
				</li>
				<li class="breadcrumb-item active">更改密碼</li>
			  </ol>
		  <!-- Breadcrumbs-->
			<!-- /box_general-->
			<div class="row">
				<div class="col-md-6">
					<div class="box_general padding_bottom">
						<div class="header_box version_2">
							<h2><i class="fa fa-lock"></i>Change password</h2>
						</div>
						<div class="form-group">
							<label>Old password</label>
							<input type="password" name="oldPassword" class="form-control" placeholder="密碼" pattern="^(?=.*\d)(?=.*[a-zA-Z]).{6,16}$" data-error="格式錯誤。" required="required">
							<div class="help-block with-errors" style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif"></div>
						</div>
						<div class="form-group">
							<label>New password</label>
							<input type="password" name="memberPassword" class="form-control" placeholder="密碼" pattern="^(?=.*\d)(?=.*[a-zA-Z]).{6,16}$" data-error="請輸入含有英文字母及數字的密碼，至少六個字元。" required="required">
							<div class="help-block with-errors" style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif"></div>
						</div>
						<div class="form-group">
							<label>Confirm new password</label>
							<input type="password" name="confirmPassword" class="form-control" placeholder="確認密碼" data-match="input[name='memberPassword']" data-error="密碼不符合！" required="required">
							<div class="help-block with-errors" style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif"></div>
						</div>
					</div>
				</div>
			</div>
			<!-- /row-->
			<input type="hidden" name="action" value="changePassword">
			<input type="hidden" name="memberId" value="${userVO.memberId}">
			<p style="display:inline-table"><button type="submit" class="btn btn-danger" style="background-color:#fc5b62; border-radius:5px">修改</button></p>
			<p style="display:inline-table; margin-left:15px"><button type="button" class="btn btn-light" onclick="window.location.href='<%=request.getContextPath()%>/frontend/member/memberSetting.jsp'">取消</button></p>
		  </div>
		</form>
	 <!-- /.container-fluid-->  
		</div>
	</div>
	<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.value}</li>
		</c:forEach>
	</ul>
</c:if>
	<!-- COMMON SCRIPTS -->
	
	<script>
		$("form[name='form_edit_profile']").validator().on('submit', function(e) {
			if (e.isDefaultPrevented()) { 
				return;
			} else { 
				form.submit();
			}
			e.preventDefault(); 
		});
	</script>
	<script src="<%=request.getContextPath()%>/frontend/js/common_scripts.js"></script>
    <script src="<%=request.getContextPath()%>/frontend/js/main.js"></script>
</body>
</html>