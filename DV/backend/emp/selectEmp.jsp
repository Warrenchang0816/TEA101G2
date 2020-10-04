<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>

<%

	EmpService empSvc = new EmpService();
	List<EmpVO> list = empSvc.selectAllEmp();
	pageContext.setAttribute("list",list);
	
	EmpVO loginEmp = (EmpVO)session.getAttribute("loginEmp");

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
  <link href="<%=request.getContextPath()%>/backend/https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800" rel="stylesheet">
	
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
#img {
	height: 110px;
	position: relative;
	left: -30%;
    top: -10px;
}
#figure {
	left: 30px;
    top: 30px;
	width: 100px;
	height: 100px;
	border-radius: 50%;
	overflow: hidden;
    overflow-x: hidden;
    overflow-y: hidden;
    margin-bottom: 0px;
    margin-right: 0px;
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
				<button class="btn btn-outline-warning" type="button" onclick = "history.back()">回上一頁</button>
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
  
 
 <!-- /Navigation-->
  <div class="content-wrapper">
    <div class="container-fluid">
      <!-- Breadcrumbs-->
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="<%=request.getContextPath()%>/backend/index.jsp">首頁</a>
        </li>
        <li class="breadcrumb-item active">搜尋員工</li>
      </ol>
		<!-- Example DataTables Card-->
      <div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-table"></i> 員工列表</div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
              <thead>
                <tr>
					<th>照片</th>
					<th>員工編號</th>
					<th>姓名</th>
					<th>信箱</th>
					<th>職稱</th>
					<th>在職狀態</th>
                </tr>
              </thead>
              <tfoot>
                <tr>
					<th>照片</th>
					<th>員工編號</th>
					<th>姓名</th>
					<th>信箱</th>
					<th>職稱</th>
					<th>在職狀態</th>
			    </tr>
              </tfoot>

<c:forEach var="empVO" items="${list}" begin="0" end="<%=list.size()%>">
<% Base64.Encoder encode = Base64.getEncoder();%>
	<tr>
		<td>
			<figure id="figure"><img src="data:image/png;base64,<%=encode.encodeToString(((EmpVO)pageContext.getAttribute("empVO")).getEmpPhoto())%>" id="img"/></figure>
		</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/EmpServlet" style="margin-bottom: 0px;">${empVO.empId}
			    <button type="submit" class="btn btn-link">
					<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-box-arrow-in-right" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
					  <path fill-rule="evenodd" d="M6 3.5a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-2a.5.5 0 0 0-1 0v2A1.5 1.5 0 0 0 6.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-8A1.5 1.5 0 0 0 5 3.5v2a.5.5 0 0 0 1 0v-2z"/>
					  <path fill-rule="evenodd" d="M11.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H1.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"/>
					</svg>
        		</button>
			    <input type="hidden" name="empId"  value="${empVO.empId}">
			    <input type="hidden" name="action"	value="backend_SelectOneEmp"></FORM>
			    
		</td>
		<td>${empVO.empName}</td>
		<td>${empVO.empEmail}</td>
		<td>${empVO.empJob}</td>
		<td>${(empVO.empStatus == "O")? "在職" : (empVO.empStatus == "P")? "停職" : "離職"}</td>
	</tr>
</c:forEach>
 
 
 
              </tbody>
            </table>
          </div>
        </div>
        <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
      </div>
	  <!-- /tables-->
	  </div>
	  <!-- /container-fluid-->
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
    <script src="<%=request.getContextPath()%>/backend/js/admin-datatables.js"></script>
</body>

</html>