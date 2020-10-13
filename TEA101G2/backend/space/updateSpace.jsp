<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.space.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.spaceDetail.model.*"%>
<%@ page import="com.spacePhoto.model.*"%>
<%@ page import="com.space.model.*"%>
<%@ page import="com.emp.model.*"%>


<%
	Base64.Encoder encode = Base64.getEncoder();

	SpaceVO spaceVO = (SpaceVO)request.getAttribute("selectOneUpdate");
	pageContext.setAttribute("spaceVO", spaceVO);
	
	SpaceServiceB spaceServ = new SpaceServiceB();
	String spaceId = spaceVO.getSpaceId();
	String spaceStatus = spaceServ.selectOneSpace(spaceId).getSpaceStatus();
	pageContext.setAttribute("spaceId", spaceId);
	pageContext.setAttribute("spaceStatus", spaceStatus);
	
	LinkedList<String> errorMsgs = (LinkedList<String>) request.getAttribute("errorMsgs");
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
  <link rel="shortcut icon" href="img/favicon.ico" type="<%=request.getContextPath()%>/backend/image/x-icon">
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
    #perview {
      width: 200px;
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
          <a href="<%=request.getContextPath()%>/backend/space/space.jsp">管理場地</a>
        </li>
        <li class="breadcrumb-item">
          <a href="<%=request.getContextPath()%>/backend/space/selectSpaceStatus.jsp">場地上/下架</a>
        </li>
        <li class="breadcrumb-item active">場地[<%=spaceVO.getSpaceName()%>]</li>
      </ol>
      
		<div class="box_general padding_bottom">
			<div class="header_box version_2">
				<h2><i class="fa fa-user"></i><%=spaceVO.getSpaceId()%> / <%=spaceVO.getSpaceName()%></h2>
			</div>
			
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/SpaceServlet" style="margin-bottom: 0px;">

 			<div class="row">
				<div class="col-md-8 add_top_30">
					<div class="row">

						<div class="col-md-4">
							<div class="form-group">
								<label>場地狀態</label>
								<div class="styled-select">
									<select  size="1" name="spaceStatus">
										<option value = "T" ${spaceVO == null ? "" : spaceVO.spaceStatus.equals("T") ? "selected" : ""}>上架</option>
										<option value = "F" ${spaceVO == null ? "" : spaceVO.spaceStatus.equals("F") ? "selected" : ""}>下架</option>
									</select>
								</div>
							</div>
						</div>
						
						<div class="col-md-4">
							<div class="form-group">
								<label>場地上架日期</label>
								<input type="text" class="form-control" name="spaceOnsaleDate" id="spaceOnsaleDate" ${spaceStatus.equals("T") ? "readonly" : ""}
									value="<%= (spaceVO == null)? "" : spaceVO.getSpaceOnsaleDate()%>"/>
							</div>
							<span style="color:red"><%= (errorMsgs == null)? "" : (errorMsgs.peek() == null)? "" : (errorMsgs.peek().contains("上架"))? errorMsgs.poll() : ""%></span>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label>場地下架日期</label>
								<input type="text" class="form-control" name="spaceOffsaleDate" id="spaceOffsaleDate" ${spaceStatus.equals("F") ? "readonly" : ""}
									value="<%= (spaceVO == null)? "" : (spaceVO.getSpaceOffsaleDate() == null)? "" : spaceVO.getSpaceOffsaleDate()%>"/>
							</div>
							<span style="color:red"><%= (errorMsgs == null)? "" : (errorMsgs.peek() == null)? "" : (errorMsgs.peek().contains("下架"))? errorMsgs.poll() : ""%></span>
						</div>
						
						<div class="col-md-6">
							<div class="form-group">
								<label>員工編號</label>
								<input type="text" class="form-control" name="spaceStatusEmp" readonly
									value="[<%=loginEmp.getEmpId()%>]<%=loginEmp.getEmpName() %>"/>
							</div>
						</div>
						
						<div class="col-md-6">
							<div class="form-group">
								<label>上下架原因</label>
								<input type="text" class="form-control" name="spaceStatusComm" 
									value="<%=(spaceVO.getSpaceStatusComm() == null)? "" : spaceVO.getSpaceStatusComm() %>"/>
							</div>
							<span style="color:red"><%= (errorMsgs == null)? "" : (errorMsgs.peek() == null)? "" : (spaceVO.getSpaceStatusComm().equals(""))? errorMsgs.poll() : ""%></span>
						</div>
						

					
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-4">
						<button name="update" type="submit" class="btn_1 medium" onclick="javascript:return confirm('確認更改?');">更改上架/下架</button>
					    <input type="hidden" name="spaceId"  value="<%=spaceVO.getSpaceId()%>">
					    <input type="hidden" name="empId"  value="<%=loginEmp.getEmpId()%>">
					    <input type="hidden" name="today"  value="<%=new java.sql.Date(System.currentTimeMillis())%>">
					    <input type="hidden" name="action"	value="backend_UpdateSpace"></FORM>
				</div>
			</div>
		</div>
</FORM>
		
	  </div>
	  
	  <!-- /.container-fluid-->
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

  <% 
  	java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
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
		$('#spaceOnsaleDate').datetimepicker({
		   theme: '',              //theme: 'dark',
		   timepicker:false,       //timepicker:true,
		   step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
		   format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%= (spaceVO.getSpaceOnsaleDate() == null)? today : spaceVO.getSpaceOnsaleDate()%>',
		   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
		   //startDate:	            '2017/07/10',  // 起始日
		   minDate: '-1970-01-01',                              // 去除今日(不含)之前
		   //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
		});
        $.datetimepicker.setLocale('zh');
        $('#spaceOffsaleDate').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
	       value: '<%= (spaceVO.getSpaceOffsaleDate() == null)? today : spaceVO.getSpaceOffsaleDate()%>',
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           minDate: '-1970-01-01',                              // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
   </script>

</html>