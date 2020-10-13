<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.formList.model.*"%>
<%@ page import="com.emp.model.*"%>

<% 	

EmpService empServ = new EmpService();
EmpVO loginEmpVO = (EmpVO)session.getAttribute("loginEmp");
String loginEmpId = loginEmpVO.getEmpId();
EmpVO loginEmp = empServ.selectOneEmp(loginEmpId);
pageContext.setAttribute("loginEmp",loginEmp);

%>

<!DOCTYPE html>
<html>

<head>


  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
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
  <!-- Your custom styles -->
  <link href="<%=request.getContextPath()%>/backend/css/custom.css" rel="stylesheet">

<style>

/* Button used to open the chat form - fixed at the bottom of the page */
.open-button {
  background-color: #555;
  color: white;
  border: none;
  cursor: pointer;
  opacity: 0.8;
  position: fixed;
  bottom: 10px;
  right: 80px;
  width: 60px;
  height: 60px;
  border-radius: 50%;
}
</style>
	
</head>


<body class="fixed-nav sticky-footer" id="page-top">


  <!-- Navigation-->
  <nav class="navbar navbar-expand-lg navbar-dark bg-default fixed-top" id="mainNav">
    <a class="navbar-brand" href="<%=request.getContextPath()%>/backend/index.jsp"><img src="<%=request.getContextPath()%>/backend/img/logo.png" data-retina="true" alt="" width="150" height="36"></a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
        
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Dashboard">
          <a class="nav-link" href="<%=request.getContextPath()%>/backend/index.jsp">
            <i class="fa fa-fw fa-dashboard"></i>
            <span class="nav-link-text">首頁</span>
          </a>
        </li>
        
       <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Components">
          <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseComponents" data-parent="#Components">
            <i class="fa fa-fw fa-gear"></i>
            <span class="nav-link-text">[<%=loginEmp.getEmpId()%>]<%=loginEmp.getEmpName()%></span>
          </a>
          <ul class="sidenav-second-level collapse" id="collapseComponents">
            <li>
              <a href="<%=request.getContextPath()%>/backend/emp/emp.jsp">個人資料</a>
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
          <a class="nav-link" href="<%=request.getContextPath()%>/backend/mail/mailBox.jsp">
            <i class="fa fa-fw fa-envelope-open"></i>
            <span class="nav-link-text">信件</span>
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
      
            <div class="col-md-3">
				<button class="btn btn-outline-warning" type="button" onclick ="history.back()">回上一頁</button>
			</div>
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
        
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle mr-lg-2" id="alertsDropdown" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fa fa-fw fa-bell"></i>
            <span class="d-lg-none">訊息
              <span class="badge badge-pill badge-warning">6 New</span>
            </span>
            <span class="indicator text-warning d-none d-lg-block">
              <i class="fa fa-fw fa-circle"></i>
            </span>
          </a>
          <div class="dropdown-menu" aria-labelledby="alertsDropdown">
            <h6 class="dropdown-header" id="messh6">新訊息:</h6>
            
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
            <i class="fa fa-fw fa-sign-out"></i>登出</a>
        </li>
      </ul>
    </div>
  </nav>
  

    
    
    
    <!-- Logout Modal-->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">離開後台?</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">×</span>
            </button>
          </div>
          <div class="modal-body">確定從後台登出嗎?</div>
          <div class="modal-footer">
            <button class="btn btn-secondary" type="button" data-dismiss="modal">取消</button>
            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/LogoutHandler" style="margin-bottom: 0px;">
			    <input type="submit" class="btn btn-primary" value="確認登出">
			</FORM>
          </div>
        </div>
      </div>
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
    
<button class="open-button" onclick="openForm()">Chat</button>
	
<%@ include file="/backend/chat.jsp" %>
    
    
    <!-- Bootstrap core JavaScript-->
    <script src="<%=request.getContextPath()%>/backend/vendor/jquery/jquery-3.4.1.min.js"></script>
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
    <script src="<%=request.getContextPath()%>/backend/js/admin-datatables.js"></script>
    
    
<script>


</script>
</body>

</html>