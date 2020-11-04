<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.formList.model.*"%>
<%@ page import="com.space.model.*"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="com.orderMaster.model.*"%>

<% 	

EmpService empServ = new EmpService();
EmpVO loginEmpVO = (EmpVO)session.getAttribute("loginEmp");
String loginEmpId = loginEmpVO.getEmpId();
pageContext.setAttribute("loginEmpId",loginEmpId);
EmpVO loginEmp = empServ.selectOneEmp(loginEmpId);
pageContext.setAttribute("loginEmp",loginEmp);

SpaceServiceB HFSpaceSvc = new SpaceServiceB();

List<SpaceVO> HFSpaceListNew = HFSpaceSvc.selectAllNewSpace("N");
pageContext.setAttribute("HFSpaceListNew",HFSpaceListNew);

OrderMasterServiceB HFOrderMasterSvc = new OrderMasterServiceB();
List<OrderMasterVO> orderMasterlistT = HFOrderMasterSvc.selectAllOrderMasterByStatus("T");
pageContext.setAttribute("orderMasterlistT",orderMasterlistT);

FormListService HFFormListSvc = new FormListService();
List<FormListVO> formListUndo = HFFormListSvc.selectAllFormListByStatus("undo");
pageContext.setAttribute("formListUndo",formListUndo);

List<FormListVO> mailListNew = HFFormListSvc.selectAllNewMailByGet(loginEmpId);
pageContext.setAttribute("mailListNew",mailListNew);

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

.open-online {
  background-color: #555;
  color: white;
  border: none;
  cursor: pointer;
  opacity: 0.8;
}
</style>
    <style>
        a.box_topic i {
            color: gray;
            background-color:#272727;
            background-color: white;
        }
        #unread {
		    background-color: #dc3545;
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

#dataTable td {
    text-align: center; 
    vertical-align: middle;
}
    </style>
</head>


<body class="fixed-nav sticky-footer" id="page-top" onload="online()" onunload="offline()">


  <!-- Navigation-->
  <nav class="navbar navbar-expand-lg navbar-dark bg-default fixed-top" id="mainNav">
    <a class="navbar-brand" href="<%=request.getContextPath()%>/backend/<%=loginEmp.getEmpAuth() == 1? "empIndex.jsp" : "index.jsp" %>"><img src="<%=request.getContextPath()%>/backend/img/logo_backend.jpg" data-retina="true" alt="" width="150" height="36"></a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
        <%-- 
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Dashboard">
          <a class="nav-link" href="<%=request.getContextPath()%>/backend/<%=loginEmp.getEmpAuth() == 1? "empIndex.jsp" : "index.jsp" %>">
            <i class="fa fa-fw fa-dashboard"></i>
            <span class="nav-link-text">首頁</span>
          </a>
        </li>
        --%>
       <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Components">
          <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseComponents" data-parent="#Components">
            <img alt="rocket" width=30 src="https://www.flaticon.com/svg/static/icons/svg/2298/2298545.svg" />
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
            <img alt="rocket" width=30 src="https://www.flaticon.com/svg/static/icons/svg/3143/3143239.svg" />
            <span class="nav-link-text">信件</span>
            <span style="color:red">${mailListNew.isEmpty()? "" : "New!!"}</span>
          </a>
        </li>
        
        
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="My profile">
          <a class="nav-link" href="<%=request.getContextPath()%>/backend/member/member.jsp">
            <img alt="rocket" width=30 src="https://www.flaticon.com/svg/static/icons/svg/2298/2298538.svg" />
            <span class="nav-link-text">管理會員</span>
          </a>
        </li>
        
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="My profile">
          <a class="nav-link" href="<%=request.getContextPath()%>/backend/space/space.jsp">
            <img alt="rocket" width=25 src="https://www.flaticon.com/svg/static/icons/svg/3616/3616161.svg" />
            <span class="nav-link-text">管理場地</span>
           	<span style="color:red">${HFSpaceListNew.isEmpty()? "" : "New!!"}</span>
          </a>
        </li>
        
        
        
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="My profile">
          <a class="nav-link" href="<%=request.getContextPath()%>/backend/orderMaster/orderMaster.jsp">
            <img alt="rocket" width=30 src="https://www.flaticon.com/svg/static/icons/svg/616/616729.svg" />
            <span class="nav-link-text">管理訂單</span>
            <span style="color:red">${orderMasterlistT.isEmpty()? "" : "New!!"}</span>
          </a>
        </li>
        
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="My profile">
          <a class="nav-link" href="<%=request.getContextPath()%>/backend/formList/formList.jsp">
            <img alt="rocket" width=30 src="https://www.flaticon.com/svg/static/icons/svg/616/616578.svg" />
            <span class="nav-link-text">管理客服表單</span>
            <span style="color:red">${formListUndo.isEmpty()? "" : "New!!"}</span>
          </a>
        </li>

        
      </ul>
      
      
      
      
      
      <ul class="navbar-nav sidenav-toggler">
        <li class="nav-item">
          <a class="nav-link text-center" >
          &nbsp;
          </a>
        </li>
      </ul>
      
            <div class="col-md-3">
				<button class="btn btn-outline-warning" type="button" onclick ="history.back()">回上一頁</button>
			</div>
      <ul class="navbar-nav ml-auto">
        
        
        
        <li class="nav-item">
          <a class="nav-link" data-toggle="modal" data-target="#exampleModal">
          <img alt="rocket" width=40 src="https://www.flaticon.com/svg/static/icons/svg/3069/3069264.svg" />
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
            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backendLogoutHandler" style="margin-bottom: 0px;">
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
          <small>Copyright © BIGZOO 2020</small>
        </div>
      </div>
    </footer>
    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top" style="z-index:10">
      <i class="fa fa-angle-up"></i>
    </a>
    
<%-- <button class="open-button" onclick="openChat();" style="z-index:10">Chat</button> --%>
	
<%@ include file="/backend/chat.jsp" %>
<%@ include file="/backend/onlineList.jsp" %>
    
    
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