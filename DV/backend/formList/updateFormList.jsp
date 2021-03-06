<%@page import="java.sql.Date"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.formList.model.*"%>
<%@ page import="com.emp.model.*"%>

<%
FormListVO formListVO = (FormListVO)request.getAttribute("selectOneUpdate");
Base64.Encoder encode = Base64.getEncoder();

java.sql.Date date = new java.sql.Date(System.currentTimeMillis());

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
				
					<jsp:useBean id="memberServ" scope="page" class="com.member.model.MemberService" />
					<%
						String memberId = formListVO.getMemberId();
						String memberName = memberServ.selectOneMember(memberId).getMemberName();
					%>
					
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label>會員編號</label>
								<input type="text" class="form-control" placeholder="Your email" name="membrId" readonly
									value="[<%= (formListVO == null)? "" : formListVO.getMemberId()%>]<%= memberName%>"/>
								
							</div>
						</div>
						
						<div class="col-md-6">
							<div class="form-group">
								<label>表單申請日期</label>
								<input type="text" class="form-control" placeholder="Your email" name="formListCreateDate" readonly
									value="<%= (formListVO == null)? "" : formListVO.getFormListCreateDate()%>"/>
							</div>
						</div>

						<div class="col-md-12">
							<div class="form-group">
								<label>表單內容</label>
								<input style="height:200px;" type="text" class="form-control" placeholder="Your telephone number" name="formListContext" readonly
									value="<%= (formListVO == null)? "" : formListVO.getFormListContext()%>"/>
								
							</div>
						</div>
						<div class="col-md-12">
							<div class="form-group">
								<label>表單圖片</label>
								<div style="height:300px;" class="form-control" placeholder="Personal info">
									<img src="<%=(formListVO.getFormListFile() == null)? application.getRealPath("/backend/img/BlobTest3") : "data:image/png;base64," + encode.encodeToString(formListVO.getFormListFile())%>" id="perview"/>
								</div>
							</div>
						</div>
						
					</div>
				</div>
			</div>
		
	  </div>
	  
   	</div>
   </div>


<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FormListServlet" style="margin-bottom: 0px;">
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
									<div class="styled-select">
										<select  size="1" name="formListStatus">
											<option value = "new" ${formListVO == null ? "selected" : formListVO.formListStatus.equals("new") ? "selected" : ""}>未結案</option>
											<option value = "handle" ${formListVO == null ? "" : formListVO.formListStatus.equals("handle") ? "selected" : ""}>已處理</option>
											<option value = "done" ${formListVO == null ? "" : formListVO.formListStatus.equals("done") ? "selected" : ""}>結案</option>
										</select>
									</div>						
							</div>
								  <%--
								 <div class="input-group mb-3">
									  <div class="input-group-prepend">
									    <label class="input-group-text" for="inputGroupSelect01">表單狀態</label>
									  </div>
									  <select class="custom-select" id="inputGroupSelect01" name="formListStatus">
									    <option selected value="<%= (formListVO == null)? "" : formListVO.getFormListStatus()%>"><%= (formListVO == null)? "" : formListVO.getFormListStatus()%></option>
									    <option value="N" >未結案</option>
									    <option value="Y" >結案</option>
									  </select>
								</div>
							--%>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label>表單新更日期</label>
								<input type="text" class="form-control" placeholder="" name="formListSoluDate" readonly
									value="<%= (formListVO == null)? date : formListVO.getFormListSoluDate()%>"/>
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
								<input type="text" class="form-control" placeholder="" name="empName" 
									value="<%= loginEmp.getEmpName() %>" readonly/>
								<input type="hidden" name="empId"  value="<%=loginEmp.getEmpId()%>">
								<%--
								<select size="1" name="empName" class="custom-select" id="inputGroupSelect01">
									<c:forEach var="empVO" items="<%= empServ.selectAllEmp() %>">
										<option value="${empVO.empName}">${empVO.empName}
									</c:forEach>
								</select> 
								--%>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label>表單處理</label>
								<input style="height:200px;" type="text" class="form-control" placeholder="" name="formListSolu" 
									value="<%= (formListVO == null)? "" : formListVO.getFormListSolu()%>"/>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-3">
							<div class="form-group">
							    <input type="submit" class="btn_1 medium" value="更新表單">
							    <input type="hidden" name="formListId"  value="<%=formListVO.getFormListId()%>">
							    <input type="hidden" name="action"	value="backend_UpdateFormList">
						    </div>
					    </div>

					</div>
				</div>
			</div>

		
	  </div>
	  
   	</div>
   </div>
 </FORM>
    
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
  java.sql.Date formListSoluDate = new java.sql.Date(System.currentTimeMillis());
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
        $('#formListSoluDate').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=formListSoluDate%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
   </script>


</html>