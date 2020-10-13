<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>

<%
	EmpVO empVO = (EmpVO) request.getAttribute("selectOneUpdate");
	LinkedList<String> errorMsgs = (LinkedList<String>) request.getAttribute("errorMsgs");
	Base64.Encoder encode = Base64.getEncoder();
	
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
  
  <style>
    img.preview {
      width: 200px;
    }
</style>
	
</head>


<body class="fixed-nav sticky-footer" id="page-top">

<%@ include file="/backend/backendHF.jsp" %> 
  
 
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/EmpServlet" name="addEmp" enctype="multipart/form-data"> 
 
  <!-- /Navigation-->
  <div class="content-wrapper">
    <div class="container-fluid">
      <!-- Breadcrumbs-->
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="<%=request.getContextPath()%>/backend/index.jsp">首頁</a>
        </li>
        <li class="breadcrumb-item">
          <a href="<%=request.getContextPath()%>/backend/emp/emp.jsp">個人資料</a>
        </li>
        <li class="breadcrumb-item active">修改基本資料</li>
      </ol>
      
		<div class="box_general padding_bottom">
			<div class="header_box version_2">
				<h2><i class="fa fa-user"></i><%=loginEmp.getEmpId()%> / <%= (loginEmp == null)? "" : loginEmp.getEmpName()%></h2>
			</div>
			
			<jsp:useBean id="empDAO" scope="page" class="com.emp.model.EmpDAO" />
			<tr><div class= "preview" id="preview">
			<img src="data:image/png;base64,<%= encode.encodeToString(empDAO.selectOne(empVO.getEmpId()).getEmpPhoto())%>" class ="preview">
			<span class="text1">預覽圖</span></div>
				
			<input type="file" name="empPhoto"/>


 			<div class="row">
				<div class="col-md-8 add_top_30">
					<div class="row">


						<div class="col-md-6">
							<div class="form-group">
								<label>員工姓名</label>
								<input type="text" class="form-control" placeholder="Your name" name="empName"
									value="<%= (empVO == null)? "" : empVO.getEmpName()%>"/>
								<span style="color:red"><%= (errorMsgs == null)? "" : (!empVO.getEmpName().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label>員工名稱</label>
								<input type="text" class="form-control" placeholder="Your last name" name="empNickname"
									value="<%= (empVO == null)? "" : empVO.getEmpNickname()%>"/>
								<span style="color:red"><%= (errorMsgs == null)? "" : (!empVO.getEmpNickname().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
							</div>
						</div>

					<!-- /row-->

						<div class="col-md-6">
							<div class="form-group">
								<label>員工連絡電話</label>
								<input type="text" class="form-control" placeholder="Your telephone number" name="empPhone"
									value="<%= (empVO == null)? "" : empVO.getEmpPhone()%>"/>
								<span style="color:red"><%= (errorMsgs == null)? "" : (!empVO.getEmpPhone().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label>員工聯絡地址</label>
								<input type="text" class="form-control" placeholder="Your email" name="empAddress"
									value="<%= (empVO == null)? "" : empVO.getEmpAddress()%>"/>
								<span style="color:red"><%= (errorMsgs == null)? "" : (!empVO.getEmpAddress().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
								
							</div>
						</div>
						
						<div class="col-md-12">
							<div class="form-group">
								<label>員工Email</label>
								<input type="text" class="form-control" placeholder="Your email" name="empEmail"
									value="<%= (empVO == null)? "" : empVO.getEmpEmail()%>"/>
								<span style="color:red"><%= (errorMsgs == null)? "" : (!empVO.getEmpEmail().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
							</div>
						</div>

					

						<div class="col-md-6">
							<div class="form-group">
								<label>員工生日</label>
								<input type="text" class="form-control" placeholder="Your telephone number" name="empBirth" id="empBirth">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label>員工性別</label>
								<input type="text" class="form-control" placeholder="Your email" name="empSex"
									value="<%= (empVO == null)? "" : empVO.getEmpSex()%>"/>
								<span style="color:red"><%= (errorMsgs == null)? "" : (!empVO.getEmpSex().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
								
							</div>
						</div>
	
					

						<div class="col-md-6">
							<div class="form-group">
								<label>員工國籍</label>
								<input type="text" class="form-control" placeholder="Your telephone number" name="empCountry"
									value="<%= (empVO == null)? "" : empVO.getEmpCountry()%>"/>
								<span style="color:red"><%= (errorMsgs == null)? "" : (!empVO.getEmpCountry().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
								
							</div>
						</div>
					</div>
				</div>
			</div>

			
			
		<input type="hidden" name="action" value="backend_UpdateEmpBasic">
		<input type="hidden" name="empId" value="<%=empVO.getEmpId()%>">
		<button name="update" type="submit" class="btn_1 medium" onclick="javascript:return confirm('確認修改?');">送出修改</button>
		<a class="btn_1 medium" href='<%=request.getContextPath()%>/backend/emp/emp.jsp'>取消</a>
		
		</FORM>
				
				
			
		</div>
		
	  </div>
	  
   	</div>


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

  <% 
  java.sql.Date empBirth = new java.sql.Date(System.currentTimeMillis());
  java.sql.Date empHireDate = new java.sql.Date(System.currentTimeMillis());
  %>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/backend/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/backend/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/backend/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#empBirth').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=empBirth%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        $.datetimepicker.setLocale('zh');
        $('#empHireDate').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=empHireDate%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
   </script>

</html>
