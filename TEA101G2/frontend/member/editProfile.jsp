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
    <title>更改資料</title>

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
		<label style="font-size: 30px">個人設定<span style="color: #fc5b62"> > </span></label>
	</div>
	<div class="content-wrapper">
		<form method="post" action="<%=request.getContextPath()%>/MemberServlet.do" enctype="multipart/form-data" name="form_edit_profile">
		<div class="container-fluid">
			<ol class="breadcrumb">
				<li class="breadcrumb-item">
				  <a href="#">個人設定</a>
				</li>
				<li class="breadcrumb-item active">更改資料</li>
			  </ol>
		  <!-- Breadcrumbs-->
			<div class="box_general padding_bottom">
				<div class="header_box version_2">
					<h2><i class="fa fa-user"></i>Profile details</h2>
				</div>
				<div class="row">
					<div class="col-md-4">
						<div class="form-group">
						<label>Your photo</label>
						</div>
						
						<img src="data:image/png;base64,<%=encode.encodeToString(userVO.getMemberPhoto())%>" width="200" height ="200"style="border-radius: 100%">
						<div class="custom-file" style="margin-top: 46px; padding-left: 10.4px;">
  							<input type="file" name="memberPhoto" class="custom-file-input" id="customFile" value="${userVO.memberPhoto}">
  							<label class="custom-file-label" for="customFile">Choose file</label>
						</div>
					</div>
					<div class="col-md-8 add_top_30">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label>Name</label>
									<input type="text" name="memberName" class="form-control" placeholder="Name" required="required" value="${userVO.memberName}" required="required">
									<div class="help-block with-errors" style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif"></div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Last name</label>
									<input type="text" name="memberNickName" class="form-control" placeholder="Last Name" required="required" value="${userVO.memberNickName}">
									<div class="help-block with-errors" style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif"></div>
								</div>
							</div>
						</div>
						<!-- /row-->
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label>Phone</label>
									<input type="text" name="memberPhone" class="form-control" placeholder="Phone" pattern="^09[0-9]{2}-[0-9]{6}$" required="required" value="${userVO.memberPhone}">
									<div class="help-block with-errors" style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif"></div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Address</label>
									<input type="text" name="memberAddress" class="form-control" placeholder="Address" required="required" value="${userVO.memberAddress}">
									<div class="help-block with-errors" style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif"></div>
								</div>
							</div>
						</div>
						<!-- /row-->
					</div>
				</div>
			</div>
			<!-- /box_general-->
			<div class="row">
				<div class="col-md-6">
					<div class="box_general padding_bottom">
						<div class="header_box version_2">
							<h2><i class="fa fa-lock"></i>Change password</h2>
						</div>
						<div class="form-group">
							<label>Password</label>
							<input type="text" name="oldPassword" class="form-control" readonly>
							<div><a href="<%=request.getContextPath()%>/frontend/member/changePassword.jsp" style="font-family:Poppins, Helvetica, sans-serif; text-decoration:underline">修改</a></div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="box_general padding_bottom">
						<div class="header_box version_2">
							<h2><i class="fa fa-envelope"></i>Change email</h2>
						</div>
						<div class="form-group">
							<label>Confirm new email</label>
							<input type="email" name="memberEmail" class="form-control" placeholder="Email" data-error="Email格式錯誤" required="required" value="${userVO.memberEmail}">
							<div class="help-block with-errors" style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif"></div>
						</div>
					</div>
				</div>
			</div>
			<!-- /row-->
			<input type="hidden" name="action" value="updateMember">
			<input type="hidden" name="memberId" value="${userVO.memberId}">
			<input type="hidden" name="memberBirth" value="${userVO.memberBirth}">
			<p style="display:inline-table"><button type="submit" class="btn btn-danger" style="background-color:#fc5b62; border-radius:5px">修改</button></p>
			<p style="display:inline-table; margin-left:15px"><button type="button" class="btn btn-light" onclick="window.location.href='<%=request.getContextPath()%>/frontend/member/memberSetting.jsp'">取消</button></p>
		  </div>
		</form>
	 <!-- /.container-fluid-->  
		</div>
	</div>
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