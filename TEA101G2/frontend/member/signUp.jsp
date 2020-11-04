<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>

<%MemberVO userVO = (MemberVO) session.getAttribute("userVO");%>
<%MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Panagea - Premium site template for travel agencies, hotels and restaurant listing.">
    <meta name="author" content="Ansonika">
    <title>加入會員</title>

    <!-- GOOGLE WEB FONT -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">
	<!-- ICON CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<!-- BASE CSS -->
    <link href="<%=request.getContextPath()%>/frontend/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/frontend/css/style.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/frontend/css/vendors.css" rel="stylesheet">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/frontend/admin_section/css/admin.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/frontend/datetimepicker/jquery.datetimepicker.css" />
	<!-- VALIDATOR JS -->
	<script src="<%=request.getContextPath()%>/frontend/js/jquery-3.5.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.9/validator.min.js"></script>
	<script src="<%=request.getContextPath()%>/frontend/datetimepicker/jquery.datetimepicker.full.js"></script>

</head>

<body>
	<div id="page">
	<jsp:include page="/frontend/other/header.jsp"/>
	<!-- /header -->

	<div style="margin-top: 90px; margin-left: 30px;">
		<label style="font-size: 30px">加入會員<span style="color: #fc5b62"> > </span></label>
	</div>

	<div class="content-wrapper">
        <form method="post" action="<%=request.getContextPath()%>/MemberServlet.do" name="form_signup" id="register_form" data-toggle="validator" enctype="multipart/form-data">
		<div class="container-fluid">
		  <!-- Breadcrumbs-->
			<div class="box_general padding_bottom">
				<div class="header_box version_2">
					<h2><i class="fa fa-user"></i>加入會員</h2>
				</div>
				<div class="row">
					<div class="col-md-8 add_top_30">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
                                    <i class="ti-user"></i>
									<label>Account</label>
									<input type="text" name="memberAccount" class="form-control" placeholder="帳號" required="required">
									<div class="help-block with-errors" style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif"></div>
									<div style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif">${errorMsgs.memberAccountDuplicated}</div>
								</div>
							</div>
						</div>
						<!-- /row-->
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
                                    <i class="icon_lock_alt"></i>
									<label>Password</label>
									<input type="password" name="memberPassword" class="form-control" placeholder="密碼" pattern="^(?=.*\d)(?=.*[a-zA-Z]).{6,16}$" data-error="請輸入含有英文字母及數字的密碼，至少六個字元。" required="required">
									<div class="help-block with-errors" style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif"></div>
								</div>
							</div>
						</div>
						<!-- /row-->
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
                                    <i class="icon_lock_alt"></i>
									<label>Confirm Password</label>
									<input type="password" name="confirmPassword" class="form-control" placeholder="確認密碼" data-match="input[name='memberPassword']" data-error="密碼不符合！" required="required">
									<div class="help-block with-errors" style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif"></div>
								</div>
							</div>
						</div>
						<!-- /row-->
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
                                    <i class="ti-user"></i>
									<label>Name</label>
									<input type="text" name="memberName" class="form-control" placeholder="名字" value="${memberVO.memberName}" required="required">
									<div class="help-block with-errors" style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif"></div>
								</div>
							</div>
						</div>
                        <!-- /row-->
                        <div class="row">
							<div class="col-md-6">
								<div class="form-group">
                                    <i class="ti-user"></i>
									<label>Nickname</label>
									<input type="text" name="memberNickName" class="form-control" placeholder="暱稱" value="${memberVO.memberNickName}" required="required">
									<div class="help-block with-errors" style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif"></div>
								</div>
							</div>
						</div>
						<!-- /row-->
                        <div class="row">
							<div class="col-md-6">
								<div class="form-group">
                                    <i class="icon_mail_alt"></i>
									<label>Email</label>
									<input type="email" name="memberEmail" class="form-control" placeholder="Email" data-error="Email格式錯誤" value="${memberVO.memberEmail}" required="required">
									<div class="help-block with-errors" style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif"></div>
									<div style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif">${errorMsgs.memberEmailDuplicated}</div>
								</div>
							</div>
						</div>
                        <!-- /row-->
                        <div class="row">
							<div class="col-md-6">
								<div class="form-group">
                                    <i class="icon_phone"></i>
									<label>Phone</label>
									<input type="text" name="memberPhone" class="form-control" placeholder="手機" pattern="^09[0-9]{2}-[0-9]{6}$" value="${memberVO.memberPhone}" required="required">
									<div class="help-block with-errors" style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif"></div>
								</div>
							</div>
						</div>
                        <!-- /row-->
                        <div class="row">
							<div class="col-md-6">
								<div class="form-group">
                                    <i class="icon_house"></i>
									<label>Address</label>
									<input type="text" name="memberAddress" class="form-control" placeholder="地址" value="${memberVO.memberAddress}" required="required">
									<div class="help-block with-errors" style="color:#AE0000; font-family:Poppins, Helvetica, sans-serif"></div>
								</div>
							</div>
						</div>
                        <!-- /row-->
					</div>
				</div>
			</div>		
            <!-- /row-->
            <input type="hidden" name="action" value="addMember">
            <p><input type="submit" class="btn btn-danger" value="註冊"/></p>
        </div>
    </form>
		  <!-- /.container-fluid-->
    	</div>
    </div>

	<script> 
		$("form[name='form_signup']").validator().on('submit', function(e) {
			if (e.isDefaultPrevented()) { 
				return;
			} else { 
				form.submit();
			}
			e.preventDefault(); 
		});
	</script>
	
	<!-- common script -->
    <script src="<%=request.getContextPath()%>/frontend/js/common_scripts.js"></script>
    <script src="<%=request.getContextPath()%>/frontend/js/main.js"></script> 
</body>
</html>