<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.formList.model.*"%>
<%@ page import="com.emp.model.*"%>

<%
FormListVO formListVO = (FormListVO)request.getAttribute("selectOneFormList");
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
    div img {
      max-height: 100%;
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
	          <a href="<%=request.getContextPath()%>/backend/formList/formList.jsp">管理客服表單</a>
	        </li>
	        <li class="breadcrumb-item">
	          <a href="<%=request.getContextPath()%>/backend/formList/selectFormList.jsp">搜尋表單</a>
	        </li>
	        <li class="breadcrumb-item active"><%=formListVO.getFormListId()%></li>
	      </ol>
      
		<div class="box_general padding_bottom">
			<div class="header_box version_2">
				<h2><i class="fa fa-user"></i><%=formListVO.getFormListId()%> / [<%= formListVO.getFormListType()%>]<%= formListVO.getFormListTitle()%></h2>
			</div>

 			<div class="row">
				<div class="col-md-8 add_top_30">
				
					<jsp:useBean id="memberServ" scope="page" class="com.member.model.MemberServiceB" />
					<%
						String memberId = formListVO.getMemberId();
						String memberName = memberServ.selectOneMember(memberId).getMemberName();
					%>
					
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label>會員編號</label>
								<input type="text" class="form-control" name="membrId" readonly
									value="[<%= (formListVO == null)? "" : formListVO.getMemberId()%>]<%= memberName%>"/>
								
							</div>
						</div>
						
						<div class="col-md-6">
							<div class="form-group">
								<label>表單申請日期</label>
								<input type="text" class="form-control"name="formListCreateDate" readonly
									value="<%= (formListVO == null)? "" : formListVO.getFormListCreateDate()%>"/>
							</div>
						</div>

						<%
						String[] contexts = formListVO.getFormListContext().split(";");
						StringBuffer formListContext = new StringBuffer();
						for(String context: contexts){
							formListContext.append(context).append("\n");
						}
						
						%>
						
						
						<div class="col-md-12">
							<div class="form-group">
								<label>表單內容</label>
								<textarea style="height:200px;" type="text" class="form-control" name="formListContext" readonly
									value="<%= (formListVO == null)? "" : formListContext%>"><%= formListContext%></textarea>
								
							</div>
						</div>
						<div class="col-md-12">
							<div class="form-group">
								<label>表單圖片</label>
								<div style="height:300px;" class="form-control" >
								<%--application.getRealPath("/backend/img/BlobTest3.jpg") --%>
									<img src="<%=(formListVO.getFormListFile() == null)? "" : "data:image/png;base64," + encode.encodeToString(formListVO.getFormListFile())%>" id="perview"/>
								</div>
							</div>
						</div>
						
					</div>
				</div>
			</div>
		
	  </div>
	  
   	</div>
   </div>
   	
   	  <!-- /Navigation-->
  <div class="content-wrapper">
    <div class="container-fluid">
    
      
		<div class="box_general padding_bottom">
			<div class="header_box version_2">
				<h2><i class="fa fa-user"></i><%=formListVO.getFormListId()%> / [<%= formListVO.getFormListType()%>]<%= formListVO.getFormListTitle()%></h2>
			</div>

 			<div class="row">
				<div class="col-md-8 ">
					<div class="row">
						
						<div class="col-md-6">
							<div class="form-group">
								<label>表單狀態</label>
								<input type="text" class="form-control"  name="formListStatus" readonly
									value="<%= (formListVO == null)? "" : (formListVO.getFormListStatus().equals("new"))? "未結案" : (formListVO.getFormListStatus().equals("handle"))? "已處理" : "結案" %>"/>
								
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label>表單更新日期</label>
								<input type="text" class="form-control"  name="formListSoluDate" readonly
									value="<%= (formListVO == null)? "" : formListVO.getFormListSoluDate()%>"/>
							</div>
						</div>
						
					</div>
					
					<jsp:useBean id="pageEmpServ" scope="page" class="com.emp.model.EmpService" />
					<%
						String empId = "";
						if("".equals(formListVO.getEmpId())){
							empId = "";
						}else{
							empId = formListVO.getEmpId();
						}
						String empName = "";
						if("".equals(pageEmpServ.selectOneEmp(empId).getEmpName())){
							empName = "";
						}else{
							empName = pageEmpServ.selectOneEmp(empId).getEmpName();
						}
					%>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label>員工</label>
								<input type="text" class="form-control" placeholder="" name="empName" readonly
									value="<%= empName %>"/>
							</div>
						</div>

						<div class="col-md-12">
							<div class="form-group">
								<label>表單結案</label>
								<textarea style="height:200px;" type="text" class="form-control" placeholder="" name="formListSolu" readonly
									value="<%= (formListVO == null)? "" : formListVO.getFormListSolu()%>"><%= (formListVO == null)? "" : formListVO.getFormListSolu()%></textarea>
							</div>
						</div>
						
					</div>
				</div>
			</div>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FormListServlet" style="margin-bottom: 0px;">
			    <input type="submit" class="btn_1 medium" value="更新表單">
			    <input type="hidden" name="formListId"  value="<%=formListVO.getFormListId()%>">
			    <input type="hidden" name="action"	value="backend_SelectOneUpdate"></FORM>
		</td>
		
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