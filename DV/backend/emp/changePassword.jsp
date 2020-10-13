<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>

<%

EmpVO empVO = (EmpVO) request.getAttribute("changePassword");
LinkedList<String> errorMsgs = (LinkedList<String>) request.getAttribute("errorMsgs");

%>


<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="Ansonika">
  
  <title>PANAGEA - Admin dashboard</title>
  
  <!-- Favicons-->
  <link rel="shortcut icon" href="<%=request.getContextPath()%>/backend/img/favicon.ico" type="image/x-icon">
  <link rel="apple-touch-icon" type="image/x-icon" href="<%=request.getContextPath()%>/backend/img/apple-touch-icon-57x57-precomposed.png">
  <link rel="apple-touch-icon" type="image/x-icon" sizes="72x72" href="<%=request.getContextPath()%>/backend/img/apple-touch-icon-72x72-precomposed.png">
  <link rel="apple-touch-icon" type="image/x-icon" sizes="114x114" href="<%=request.getContextPath()%>/backend/img/apple-touch-icon-114x114-precomposed.png">
  <link rel="apple-touch-icon" type="image/x-icon" sizes="144x144" href="<%=request.getContextPath()%>/backend/img/apple-touch-icon-144x144-precomposed.png">

  <!-- GOOGLE WEB FONT -->
  <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800" rel="stylesheet">
	
  <!-- Bootstrap core CSS-->
  <link href="<%=request.getContextPath()%>/backend/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Main styles -->
  <link href="<%=request.getContextPath()%>/backend/css/admin.css" rel="stylesheet">
  <!-- Icon fonts-->
  <link href="<%=request.getContextPath()%>/backend/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <!-- Plugin styles -->
  <link href="<%=request.getContextPath()%>/backend/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/backend/vendor/dropzone.css" rel="stylesheet">
  <!-- Your custom styles -->
  <link href="<%=request.getContextPath()%>/backend/css/custom.css" rel="stylesheet">



</head>


<body class="fixed-nav sticky-footer" id="page-top">

<%@ include file="/backend/backendHF.jsp" %> 
  
 
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/EmpServlet" name="addEmp" enctype="multipart/form-data"> 
   
 <div class="content-wrapper">

		<!-- /box_general-->
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<div class="box_general padding_bottom">
					<div class="header_box version_2">
						<h2><i class="fa fa-lock"></i>更改密碼</h2>
					</div>
					<div class="form-group">
						<label>舊密碼</label>
						<input class="form-control" type="password" name="empPassword">
						<span style="color:red"><%= (errorMsgs == null)? "" : (empVO.getEmpPassword().equals(""))? "請輸入密碼" : (errorMsgs.peek().contains("密碼錯誤"))? errorMsgs.poll() : ""%></span>
					</div>
					<div class="form-group">
						<label>新密碼</label>
						<input class="form-control" type="password" name="newEmpPassword">
						<span style="color:red"><%= (errorMsgs == null)? "" : (empVO.getEmpPassword().equals(""))? "請輸入密碼" : (errorMsgs.peek() == null)? "" : (errorMsgs.peek().contains("格式"))? errorMsgs.poll() : ""%></span>
					</div>
					<div class="form-group">
						<label>確認密碼</label>
						<input class="form-control" type="password" name="empPasswordConfirm">
						<span style="color:red"><%= (errorMsgs == null)? "" : (empVO.getEmpPassword().equals(""))? "請輸入密碼" : (errorMsgs.peek() == null)? "" : (errorMsgs.peek().equals("密碼確認不一致"))? errorMsgs.poll() : ""%></span>
					</div>
					
					<input type="hidden" name="action" value="backend_ChangePassword">
					<input type="hidden" name="empId" value="<%=loginEmp.getEmpId()%>">
					<button name="update" type="submit" class="btn_1 medium" onclick="javascript:return confirm('確認修改?');">送出修改</button>
					<a class="btn_1 medium" href='<%=request.getContextPath()%>/backend/emp/emp.jsp'>取消</a>
				</div>
			</div>
			<div class="col-md-2"></div>
	  <!-- /.container-fluid-->
   	</div>
			

		
</FORM>
   	
    
    <!-- Bootstrap core JavaScript-->
    <script src="<%=request.getContextPath()%>/backend/vendor/jquery/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/backend/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Core plugin JavaScript-->
    <script src="<%=request.getContextPath()%>/backend/vendor/jquery-easing/jquery.easing.min.js"></script>
    <!-- Page level plugin JavaScript-->
    <script src="<%=request.getContextPath()%>/backend/vendor/chart.js/Chart.min.js"></script>
    <script src="<%=request.getContextPath()%>/backend/vendor/datatables/jquery.dataTables.js"></script>
    <script src="<%=request.getContextPath()%>/backend/vendor/datatables/dataTables.bootstrap4.js"></script>
	<script src="<%=request.getContextPath()%>/backend/vendor/jquery.selectbox-0.2.js"></script>
	<script src="<%=request.getContextPath()%>/backend/vendor/retina-replace.min.js"></script>
	<script src="<%=request.getContextPath()%>/backend/vendor/jquery.magnific-popup.min.js"></script>
    <!-- Custom scripts for all pages-->
    <script src="<%=request.getContextPath()%>/backend/js/admin.js"></script>
	<!-- Custom scripts for this page-->
	<script src="<%=request.getContextPath()%>/backend/vendor/dropzone.min.js"></script>
	
</body>


</html>