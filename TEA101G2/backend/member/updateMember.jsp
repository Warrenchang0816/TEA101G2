<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.emp.model.*"%>

<%
	MemberVO memberVO = (MemberVO)request.getAttribute("selectOneUpdate");
	Base64.Encoder encode = Base64.getEncoder();
	
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
  
 <style>
    #perview {
      width: 100px;
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
        <li class="breadcrumb-item">
          <a href="<%=request.getContextPath()%>/backend/member/member.jsp">管理會員</a>
        </li>
        <li class="breadcrumb-item">
          <a href="<%=request.getContextPath()%>/backend/member/selectMember.jsp">搜尋會員</a>
        </li>
        <li class="breadcrumb-item active">更改權限[<%=memberVO.getMemberName()%>]</li>
      </ol>
      
		<div class="box_general padding_bottom">
			<div class="header_box version_2">
				<h2><i class="fa fa-user"></i><%=memberVO.getMemberId()%> / <%=memberVO.getMemberName()%></h2>
			</div>
			
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MemberServlet" style="margin-bottom: 0px;">			
			<img src="data:image/png;base64,<%=encode.encodeToString(memberVO.getMemberPhoto())%>" class="perview"/>


 			<div class="row">
				<div class="col-md-8 add_top_30">
					<div class="row">

						<div class="col-md-6">
							<div class="form-group">
								<label>會員帳號</label>
								<input type="text" class="form-control" name="memberAccount" readonly
									value="<%= (memberVO == null)? "" : memberVO.getMemberAccount()%>"/>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label>會員暱稱</label>
								<input type="text" class="form-control" name="memberNickname" readonly
									value="<%= (memberVO == null)? "" : memberVO.getMemberNickName()%>"/>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label>會員連絡電話</label>
								<input type="text" class="form-control" name="memberPhone" readonly
									value="<%= (memberVO == null)? "" : memberVO.getMemberPhone()%>"/>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label>會員聯絡地址</label>
								<input type="text" class="form-control" name="memberAddress" readonly
									value="<%= (memberVO == null)? "" : memberVO.getMemberAddress()%>"/>
								
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label>會員Email</label>
								<input type="text" class="form-control" name="memberEmail" readonly
									value="<%= (memberVO == null)? "" : memberVO.getMemberEmail()%>"/>
							</div>
						</div>
					</div>

					
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label>會員生日</label>
								<input type="text" class="form-control" name="memberBirth" id="memberBirth" readonly
									value="<%= (memberVO == null)? "" : memberVO.getMemberBirth()%>"/>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label>會員性別</label>
								<input type="text" class="form-control" name="memberSex" readonly
									value="<%= (memberVO == null)? "" : memberVO.getMemberSex()%>"/>
								
							</div>
						</div>
					</div>
	
					
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label>會員國籍</label>
								<input type="text" class="form-control" name="memberCountry" readonly
									value="<%= (memberVO == null)? "" : memberVO.getMemberCountry()%>"/>
								
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label>會員註冊日</label>
								<input type="text" class="form-control" name="memberSignupDate" id="memberSignupDate" readonly
									value="<%= (memberVO == null)? "" : memberVO.getMemberSignupDate()%>"/>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-4">
							<div class="form-group">
								<label>會員權限</label>
								<div class="styled-select">
								<select  size="1" name="memberAuth">
									<option value = "1" ${memberVO == null ? "selected" : memberVO.memberAuth == "1" ? "selected" : ""}>一般會員</option>
									<option value = "2" ${memberVO == null ? "" : memberVO.memberAuth == "2" ? "selected" : ""}>VIP會員</option>
								</select>
								</div>
								<span style="color:red"><%= (errorMsgs == null)? "" : (memberVO.getMemberAuth() == 0 && errorMsgs.peek().contains("權限"))? errorMsgs.poll() : "" %></span>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label>會員帳號狀態</label>
								<div class="styled-select">
								<select  size="1" name="memberStatus">
									<option value = "O" ${memberVO == null ? "selected" : memberVO.memberStatus.equals("T") ? "selected" : ""}>在線</option>
									<option value = "B" ${memberVO == null ? "" : memberVO.memberStatus.equals("F") ? "selected" : ""}>永久停權</option>
									<option value = "P" ${memberVO == null ? "" : memberVO.memberStatus.equals("P") ? "selected" : ""}>暫時停權</option>
								</select>
								</div>
								<span style="color:red"><%= (errorMsgs == null)? "" : (memberVO.getMemberStatus().equals("") && errorMsgs.peek().contains("權限"))? errorMsgs.poll() : "" %></span>
							</div>
						</div>
	
					</div>
					<div class="row">
						<div class="col-md-5">
							<div class="form-group">
								<label>更改員工</label>
								<input type="text" class="form-control" name="memberStatusEmp" readonly
									value="[<%= loginEmp.getEmpId()%>]<%= loginEmp.getEmpName()%>"/>
							</div>
						</div>
						<div class="col-md-7">
							<div class="form-group">
								<label>停權原因</label>
								<input type="text" class="form-control" name="memberStatusComm"
									value=""/>
							</div>
							<span style="color:red"><%= (errorMsgs == null)? "" : (errorMsgs.peek() == null)? "" : (!memberVO.getMemberStatus().equals("O") && memberVO.getMemberStatusComm().equals(""))? errorMsgs.poll() : "" %></span>
						</div>
					</div>
				</div>
				</div>
			
			
		<div class="row">
			<div class="col-md-3">
				
				    <input type="submit" class="btn_1 medium" onclick="javascript:return confirm('確認更改?');" value="確定更改">
				    <input type="hidden" name="memberId"  value="<%=memberVO.getMemberId()%>">
				    <input type="hidden" name="memberName"  value="<%=memberVO.getMemberName()%>">
				    <input type="hidden" name="memberPassword"  value="<%=memberVO.getMemberPassword()%>">
				    <input type="hidden" name="action"	value="backend_UpdateMember"></FORM>
			</div>
			<div class="col-md-3">
				<button class="btn_1 medium" type="button" onclick = "history.back()">取消</button>
			</div>
			
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
	<script src="<%=request.getContextPath()%>/backend/vendor/dropzone.min.js"></script>
	
</body>

</html>