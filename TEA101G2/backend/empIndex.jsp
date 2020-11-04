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
  <title>後台首頁</title>
  
  
  <style>
.hero_in.contacts:before {
  background: url(../img/Sloth5c.jpg) center center no-repeat;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}
.hero_in.contacts .wrapper {
  background-color: black;
  background-color: rgba(0, 0, 0, 30%);
}
  
  </style>
    
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
      
      
      	<section class="hero_in contacts">
			<div class="wrapper" style="background: url(img/Sloth.jpg) center center no-repeat;background-size: cover;height: 350px;">
				<div class="container">
					<h1 class="fadeInUp"><span style="color:red;">熱忱!!</span></h1>
				</div>
			</div>
		</section>
      
      
<%--
      <img alt="rocket" width=800 src="img/Sloth.jpg" />
      
       
      <div id="indexTable" background="img/Sloth.jpg">
      SSSSSSSSSS
      </div>
      
      
		<div class="row" style="background-image: url('img/Sloth.jpg');">
		</div>


	  <img alt="rocket" width=800 src="img/Sloth.jpg" />
--%>
	



	
</body>
</html>
