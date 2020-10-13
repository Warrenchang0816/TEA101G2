<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="javax.websocket.Session"%>

<%
EmpVO empVO = (EmpVO)request.getAttribute("selectOneEmp");
Base64.Encoder encode = Base64.getEncoder();

Map<String, Session> connectedSessions = (Map<String, Session>)getServletContext().getAttribute("TEA101G2DV_OnlineList");
pageContext.setAttribute("connectedSessions", connectedSessions);

%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="Ansonika">
  <title>PANAGEA - Admin dashboard</title>
  
    
</head>

<body class="fixed-nav sticky-footer" id="page-top">

<%@ include file="/backend/backendHF.jsp" %> 
 
  
  
  <!-- /Navigation-->
  <div class="content-wrapper">
    <div class="container-fluid">
      <!-- Breadcrumbs-->
      <ol class="breadcrumb">
        <li class="breadcrumb-item active">首頁</li>
      </ol>
      
	  <!-- Icon Cards-->
      <div class="row">
        <div class="col-xl-3 col-sm-6 mb-3">
          <div class="card dashboard text-white bg-primary o-hidden h-100">
            <div class="card-body">
              <div class="card-body-icon">
                <i class="fa fa-fw fa-envelope-open"></i>
              </div>
              <div class="mr-5"><h5>新增員工</h5></div>
            </div>
            <a class="card-footer text-white clearfix small z-1" href="<%=request.getContextPath()%>/backend/emp/addEmp.jsp">
              <span class="float-left">View Details</span>
              <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
          </div>
        </div>
		
		
		
        <div class="col-xl-3 col-sm-6 mb-3">
          <div class="card dashboard text-white bg-primary o-hidden h-100">
            <div class="card-body">
              <div class="card-body-icon">
                <i class="fa fa-fw fa-envelope-open"></i>
              </div>
              <div class="mr-5"><h5>更改員工權限</h5></div>
            </div>
            <a class="card-footer text-white clearfix small z-1" href="<%=request.getContextPath()%>/backend/emp/selectEmpAuth.jsp">
              <span class="float-left">View Details</span>
              <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
          </div>
        </div>
        
	</div>
		


			<!-- Example DataTables Card-->
      <div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-table"></i> 上線名單</div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
              <thead>
				<tr>
					<th>上線</th>
					<th>上線</th>
				</tr>
              </thead>
              <tfoot>
				<tr>
					<th>上線</th>
					<th>上線</th>
				</tr>
              </tfoot>
			<tbody>
			
			
	<c:forEach var="connectedSessions" items="${connectedSessions}" begin="0" end="<%=connectedSessions.size()%>">
		<tr>
			<td>${connectedSessions.key}</td>
			<td>${connectedSessions.value}</td>
		</tr>
	</c:forEach>
 	
 
 
              </tbody>
            </table>
          </div>
        </div>
        <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
      </div>
	  <!-- /tables-->
	  

	<input type="button" id="online" class="button" value="online" onclick="online();" /> 
	<input type="button" id="offline" class="button" value="offline" onclick="offline();" /> 
	



	
</body>
</html>
