<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>

<%
	EmpVO empVO = (EmpVO) request.getAttribute("addEmp");
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


  <!-- Navigation-->
  <nav class="navbar navbar-expand-lg navbar-dark bg-default fixed-top" id="mainNav">
    <a class="navbar-brand" href="index.jsp"><img src="img/logo.png" data-retina="true" alt="" width="150" height="36"></a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
        
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Dashboard">
          <a class="nav-link" href="<%=request.getContextPath()%>/backend/index.jsp">
            <i class="fa fa-fw fa-dashboard"></i>
            <span class="nav-link-text">Dashboard</span>
          </a>
        </li>
        
       <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Components">
          <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseComponents" data-parent="#Components">
            <i class="fa fa-fw fa-gear"></i>
            <span class="nav-link-text">登入員工</span>
          </a>
          <ul class="sidenav-second-level collapse" id="collapseComponents">
            <li>
              <a href="<%=request.getContextPath()%>/backend/emp/empProfile.jsp">個人資料</a>
            </li>
            <li>
              <a href="<%=request.getContextPath()%>/backend/emp/changePassword.jsp">修改密碼</a>
            </li>
            <li>
              <a href="<%=request.getContextPath()%>/backend/emp/selectEmp.jsp">查詢員工</a>
            </li>

          </ul>
        </li>
      
		<li class="nav-item" data-toggle="tooltip" data-placement="right" title="Messages">
          <a class="nav-link" href="messages.jsp">
            <i class="fa fa-fw fa-envelope-open"></i>
            <span class="nav-link-text">Messages</span>
          </a>
        </li>
        
        
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="My profile">
          <a class="nav-link" href="<%=request.getContextPath()%>/backend/member/member.jsp">
            <i class="fa fa-fw fa-user"></i>
            <span class="nav-link-text">管理會員</span>
          </a>
        </li>
        
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="My profile">
          <a class="nav-link" href="<%=request.getContextPath()%>/backend/space/space.jsp">
            <i class="fa fa-fw fa-user"></i>
            <span class="nav-link-text">管理場地</span>
          </a>
        </li>
        
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="My profile">
          <a class="nav-link" href="<%=request.getContextPath()%>/backend/orderMaster/orderMaster.jsp">
            <i class="fa fa-fw fa-user"></i>
            <span class="nav-link-text">管理訂單</span>
          </a>
        </li>
        
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="My profile">
          <a class="nav-link" href="<%=request.getContextPath()%>/backend/formList/formList.jsp">
            <i class="fa fa-fw fa-user"></i>
            <span class="nav-link-text">管理客服表單</span>
          </a>
        </li>

        
      </ul>
      
      
      
      
      
      <ul class="navbar-nav sidenav-toggler">
        <li class="nav-item">
          <a class="nav-link text-center" id="sidenavToggler">
            <i class="fa fa-fw fa-angle-left"></i>
          </a>
        </li>
      </ul>
      <ul class="navbar-nav ml-auto">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle mr-lg-2" id="messagesDropdown" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fa fa-fw fa-envelope"></i>
            <span class="d-lg-none">Messages
              <span class="badge badge-pill badge-primary">12 New</span>
            </span>
            <span class="indicator text-primary d-none d-lg-block">
              <i class="fa fa-fw fa-circle"></i>
            </span>
          </a>
          <div class="dropdown-menu" aria-labelledby="messagesDropdown">
            <h6 class="dropdown-header">New Messages:</h6>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#">
              <strong>David Miller</strong>
              <span class="small float-right text-muted">11:21 AM</span>
              <div class="dropdown-message small">Hey there! This new version of SB Admin is pretty awesome! These messages clip off when they reach the end of the box so they don't overflow over to the sides!</div>
            </a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#">
              <strong>Jane Smith</strong>
              <span class="small float-right text-muted">11:21 AM</span>
              <div class="dropdown-message small">I was wondering if you could meet for an appointment at 3:00 instead of 4:00. Thanks!</div>
            </a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#">
              <strong>John Doe</strong>
              <span class="small float-right text-muted">11:21 AM</span>
              <div class="dropdown-message small">I've sent the final files over to you for review. When you're able to sign off of them let me know and we can discuss distribution.</div>
            </a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item small" href="#">View all messages</a>
          </div>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle mr-lg-2" id="alertsDropdown" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fa fa-fw fa-bell"></i>
            <span class="d-lg-none">Alerts
              <span class="badge badge-pill badge-warning">6 New</span>
            </span>
            <span class="indicator text-warning d-none d-lg-block">
              <i class="fa fa-fw fa-circle"></i>
            </span>
          </a>
          <div class="dropdown-menu" aria-labelledby="alertsDropdown">
            <h6 class="dropdown-header">New Alerts:</h6>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#">
              <span class="text-success">
                <strong>
                  <i class="fa fa-long-arrow-up fa-fw"></i>Status Update</strong>
              </span>
              <span class="small float-right text-muted">11:21 AM</span>
              <div class="dropdown-message small">This is an automated server response message. All systems are online.</div>
            </a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#">
              <span class="text-danger">
                <strong>
                  <i class="fa fa-long-arrow-down fa-fw"></i>Status Update</strong>
              </span>
              <span class="small float-right text-muted">11:21 AM</span>
              <div class="dropdown-message small">This is an automated server response message. All systems are online.</div>
            </a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#">
              <span class="text-success">
                <strong>
                  <i class="fa fa-long-arrow-up fa-fw"></i>Status Update</strong>
              </span>
              <span class="small float-right text-muted">11:21 AM</span>
              <div class="dropdown-message small">This is an automated server response message. All systems are online.</div>
            </a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item small" href="#">View all alerts</a>
          </div>
        </li>
        <li class="nav-item">
          <form class="form-inline my-2 my-lg-0 mr-lg-2">
            <div class="input-group">
              <input class="form-control search-top" type="text" placeholder="Search for...">
              <span class="input-group-btn">
                <button class="btn btn-primary" type="button">
                  <i class="fa fa-search"></i>
                </button>
              </span>
            </div>
          </form>
        </li>
        <li class="nav-item">
          <a class="nav-link" data-toggle="modal" data-target="#exampleModal">
            <i class="fa fa-fw fa-sign-out"></i>Logout</a>
        </li>
      </ul>
    </div>
  </nav>
  
 
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/EmpServlet" name="addEmp" enctype="multipart/form-data"> 
 
  <!-- /Navigation-->
  <div class="content-wrapper">
    <div class="container-fluid">
      <!-- Breadcrumbs-->
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="index.jsp">Dashboard</a>
        </li>
        <li class="breadcrumb-item active">Add listing</li>
      </ol>
		<div class="box_general padding_bottom">
			<div class="header_box version_2">
				<h2><i class="fa fa-user"></i>Profile details</h2>
			</div>
			
 			<%-- 
			<div class="row">

				<div class="col-md-4">
					<div class="form-group">
					<label>Your photo</label>
						<form action="/file-upload" class="dropzone" name="empPhoto"></form> 
						<input type="file" class="dropzone" name="empPhoto"></input>
				    </div>
				</div>
			</div>
			--%>
			
				<div class= "preview" id="preview"><span class="text1">預覽圖</span></div>
				
				<input type="file" name="empPhoto"/>

			
 <%-- <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/EmpServlet" name="addEmp" enctype="multipart/form-data"> --%> 

 			<div class="row">
				<div class="col-md-8 add_top_30">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label>員工帳號</label>
								<input type="text" class="form-control" placeholder="帳號" name="empAccount"
									value="<%= (empVO == null)? "" : empVO.getEmpAccount()%>"/>
								<span style="color:red"><%= (errorMsgs == null)? "" : (!empVO.getEmpAccount().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label>員工密碼</label>
								<input type="text" class="form-control" placeholder="密碼" name="empPassword"
									value="<%= (empVO == null)? "" : empVO.getEmpPassword()%>"/>
								<span style="color:red"><%= (errorMsgs == null)? "" : (!empVO.getEmpPassword().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
							</div>
						</div>

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
						<div class="col-md-6">
							<div class="form-group">
								<label>員工到職日</label>
								<input type="text" class="form-control" placeholder="Your email" name="empHireDate" id="empHireDate">
							</div>
						</div>

					

						<div class="col-md-4">
							<div class="form-group">
								<label>員工職稱</label>
								<input type="text" class="form-control" placeholder="Your telephone number" name="empJob"
									value="<%= (empVO == null)? "" : empVO.getEmpJob()%>"/>
								<span style="color:red"><%= (errorMsgs == null)? "" : (!empVO.getEmpJob().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
								
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label>員工權限</label>
								<input type="text" class="form-control" placeholder="Your email" name="empAuth"
									value="<%= (empVO == null)? "" : empVO.getEmpAuth()%>"/>
								<span style="color:red"><%= (errorMsgs == null)? "" : !(empVO.getEmpAuth() <= 0 || empVO.getEmpAuth() > 5)? "" : "  " + errorMsgs.poll()%></span></td>

							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label>員工在職狀態</label>
								<input type="text" class="form-control" placeholder="Your email" name="empStatus"
									value="<%= (empVO == null)? "" : empVO.getEmpStatus()%>"/>
								<span style="color:red"><%= (errorMsgs == null)? "" : (!empVO.getEmpStatus().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
								
							</div>
						</div>
					</div>
					
					<!-- /row-->
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label>Personal info</label>
								<textarea style="height:100px;" class="form-control" placeholder="Personal info"></textarea>
							</div>
						</div>
					</div>
					<!-- /row-->
				</div>
			</div>
			
			
		<input type="hidden" name="action" value="backend_AddEmp">
		<button name="add" type="submit" class="btn_1 medium" onclick="javascript:return confirm('確認新增?');">送出新增</button>
		<a class="btn_1 medium" href='<%=request.getContextPath()%>/backend/emp/empProfile.jsp'>取消</a>
		</FORM>
				
				
			
		</div>
		
		
		
		<!-- /box_general-->
		<!--
		<div class="row">
			<div class="col-md-6">
				<div class="box_general padding_bottom">
					<div class="header_box version_2">
						<h2><i class="fa fa-lock"></i>Change password</h2>
					</div>
					<div class="form-group">
						<label>Old password</label>
						<input class="form-control" type="password">
					</div>
					<div class="form-group">
						<label>New password</label>
						<input class="form-control" type="password">
					</div>
					<div class="form-group">
						<label>Confirm new password</label>
						<input class="form-control" type="password">
					</div>
				</div>
			</div>
			-->
			
			<!--
			<div class="col-md-6">
				<div class="box_general padding_bottom">
					<div class="header_box version_2">
						<h2><i class="fa fa-envelope"></i>Change email</h2>
					</div>
					<div class="form-group">
						<label>Old email</label>
						<input class="form-control" name="old_email" id="old_email" type="email">
					</div>
					<div class="form-group">
						<label>New email</label>
						<input class="form-control" name="new_email" id="new_email" type="email">
					</div>
					<div class="form-group">
						<label>Confirm new email</label>
						<input class="form-control" name="confirm_new_email" id="confirm_new_email" type="email">
					</div>
				</div>
			</div>
		</div>
		-->
		
		<%-- 
		<!-- /row SAVE-->
		<input type="hidden" name="action" value="backend_AddEmp">
		<button name="add" type="submit" class="btn_1 medium" onclick="javascript:return confirm('確認新增?');">送出新增</button>
		
		</FORM>
		--%>
		
	  </div>
	  
	  <!-- /.container-fluid-->
   	</div>
   	
   	
   	

    <!-- /.container-wrapper-->
    <footer class="sticky-footer">
      <div class="container">
        <div class="text-center">
          <small>Copyright © PANAGEA 2018</small>
        </div>
      </div>
    </footer>
    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
      <i class="fa fa-angle-up"></i>
    </a>
    
    
    
    <!-- Logout Modal-->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">×</span>
            </button>
          </div>
          <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
          <div class="modal-footer">
            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
            <a class="btn btn-primary" href="login.html">Logout</a>
          </div>
        </div>
      </div>
    </div>
    
    
    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
    <!-- Page level plugin JavaScript-->
    <script src="vendor/chart.js/Chart.min.js"></script>
    <script src="vendor/datatables/jquery.dataTables.js"></script>
    <script src="vendor/datatables/dataTables.bootstrap4.js"></script>
	<script src="vendor/jquery.selectbox-0.2.js"></script>
	<script src="vendor/retina-replace.min.js"></script>
	<script src="vendor/jquery.magnific-popup.min.js"></script>
    <!-- Custom scripts for all pages-->
    <script src="js/admin.js"></script>
	<!-- Custom scripts for this page-->
	<script src="vendor/dropzone.min.js"></script>
	
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
