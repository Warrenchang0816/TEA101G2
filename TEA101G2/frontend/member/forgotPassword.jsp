<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Panagea - Premium site template for travel agencies, hotels and restaurant listing.">
    <meta name="author" content="Ansonika">
    <title>忘記密碼</title>

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
		<label style="font-size: 30px">忘記密碼<span style="color: #fc5b62"> > </span></label>
	</div>
	<div class="content-wrapper">
		<form method="post" action="<%=request.getContextPath()%>/MemberServlet.do" enctype="multipart/form-data" name="form_forgot_password">
		<div class="container-fluid">
			<ol class="breadcrumb">
				<li class="breadcrumb-item active">忘記密碼</li>
			  </ol>
		  <!-- Breadcrumbs-->
			<!-- /box_general-->
			<div class="row">
				<div class="col-md-6">
					<div class="box_general padding_bottom">
						<div class="header_box version_2">
							<h2><i class="fa fa-lock"></i>忘記密碼</h2>
						</div>
						<div class="form-group">
							<label>輸入你的信箱</label>
							<input type="email" name="memberEmail" class="form-control" placeholder="信箱" data-error="Email格式錯誤" required="required">
							<div class="help-block with-errors" style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif"></div>
							<c:if test="${errorMsgs.memberEmail != null}">
								<div style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif">${errorMsgs.memberEmail}</div>
							</c:if>
							
							<c:if test="${successMsgs.successMsgs != null}">
								<div style="color:blue; font-family:Poppins, Helvetica, sans-serif">${successMsgs.successMsgs}</div>
							</c:if>
						</div>

					</div>
				</div>
			</div>
			<!-- /row-->
			<input type="hidden" name="action" value="forgotPassword">
			<p style="display:inline-table"><button type="submit" class="btn btn-danger" style="background-color:#fc5b62; border-radius:5px">發送</button></p>
			<p style="display:inline-table; margin-left:15px"><button type="button" class="btn btn-light" onclick="window.location.href='<%=request.getContextPath()%>/frontend/login.jsp'">取消</button></p>
		  </div>
		</form>
	 <!-- /.container-fluid-->  
		</div>
	</div>
	<!-- COMMON SCRIPTS -->
	
	<script>
		$("form[name='form_forgot_password']").validator().on('submit', function(e) {
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