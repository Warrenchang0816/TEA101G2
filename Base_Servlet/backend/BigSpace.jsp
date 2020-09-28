<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.space.model.*"%>

<%
  SpaceVO spaceVO = (SpaceVO) request.getAttribute("selectOneSpace");
  LinkedList<String> errorMsgs = (LinkedList<String>) request.getAttribute("errorMsgs");
%>
    
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="Ansonika">
<title>大台平後台</title>

  <!-- Favicons-->
  <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
  <link rel="apple-touch-icon" type="image/x-icon" href="img/apple-touch-icon-57x57-precomposed.png">
  <link rel="apple-touch-icon" type="image/x-icon" sizes="72x72" href="img/apple-touch-icon-72x72-precomposed.png">
  <link rel="apple-touch-icon" type="image/x-icon" sizes="114x114" href="img/apple-touch-icon-114x114-precomposed.png">
  <link rel="apple-touch-icon" type="image/x-icon" sizes="144x144" href="img/apple-touch-icon-144x144-precomposed.png">

  <!-- GOOGLE WEB FONT -->
  <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800" rel="stylesheet">
	
  <!-- Bootstrap core CSS-->
  <link href="<%=request.getContextPath()%>/backend/css/bootstrap.min.css" rel="stylesheet">
  <!-- Main styles -->
  <link href="<%=request.getContextPath()%>/backend/css/admin.css" rel="stylesheet">
  <!-- Icon fonts-->
  <link href="<%=request.getContextPath()%>/backend/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <!-- Plugin styles -->
  <link href="<%=request.getContextPath()%>/backend/css/dataTables.bootstrap4.css" rel="stylesheet">
  <!-- Your custom styles -->
  <link href="<%=request.getContextPath()%>/backend/css/custom.css" rel="stylesheet">


<h1>後台首頁</h1>
</head>

<body>

	<table>
		<ul>
			<li><a href='<%=request.getContextPath()%>/backend/emp/emp.jsp'><input type="button" value="員工頁面"></a></li>
			<li><a href='<%=request.getContextPath()%>/backend/member/member.jsp'><input type="button" value="會員頁面"></a></li>
			<li><a href='<%=request.getContextPath()%>/backend/space/space.jsp'><input type="button" value="場地頁面"></a></li>
			<li><a href='<%=request.getContextPath()%>/backend/orderMaster/orderMaster.jsp'><input type="button" value="訂單頁面"></a></li>
			<li><a href='<%=request.getContextPath()%>/backend/formList/formList.jsp'><input type="button" value="客服表單頁面"></a></li>
		</ul>
	</table>


		<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
		<script src="<%=request.getContextPath()%>/backend/jquery.js"></script>
		<!-- Bootstrap core JavaScript-->
		<script src="<%=request.getContextPath()%>/backend/js/jquery.min.js"></script>
		<script src="<%=request.getContextPath()%>/backend/js/bootstrap.bundle.min.js"></script>
		<!-- Core plugin JavaScript-->
		<script src="<%=request.getContextPath()%>/backend/js/jquery.easing.min.js"></script>
		<!-- Page level plugin JavaScript-->
		<script src="<%=request.getContextPath()%>/backend/js/Chart.js"></script>
		<script src="<%=request.getContextPath()%>/backend/js/jquery.dataTables.js"></script>
		<script src="<%=request.getContextPath()%>/backend/js/dataTables.bootstrap4.js"></script>
		<script src="<%=request.getContextPath()%>/backend/js/jquery.selectbox-0.2.js"></script>
		<script src="<%=request.getContextPath()%>/backend/js/retina-replace.min.js"></script>
		<script src="<%=request.getContextPath()%>/backend/js/jquery.magnific-popup.min.js"></script>
		<!-- Custom scripts for all pages-->
		<script src="<%=request.getContextPath()%>/backend/js/admin.js"></script>
		<!-- Custom scripts for this page-->
		<script src="<%=request.getContextPath()%>/backend/js/admin-charts.js"></script>
</body>
</html>