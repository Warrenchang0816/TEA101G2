<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.space.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.spaceDetail.model.*"%>
<%@ page import="com.spacePhoto.model.*"%>
<%@ page import="com.emp.model.*"%>


<%
	Base64.Encoder encode = Base64.getEncoder();

	SpaceVO spaceVO = (SpaceVO)request.getAttribute("selectOneSpace");
	
	MemberVO memberVO = (MemberVO)request.getAttribute("selectOneMember");
	
	String spaceId = (String)request.getAttribute("spaceId");
	
	SpaceDetailServiceB spaceDetailSvc = new SpaceDetailServiceB();
	List<SpaceDetailVO> spaceDetailList = spaceDetailSvc.selectAllSpaceDetailBySpace(spaceId);
	pageContext.setAttribute("spaceDetailList",spaceDetailList);
	
	SpacePhotoServiceB spacePhotoSvc = new SpacePhotoServiceB();
	List<SpacePhotoVO> photoList = spacePhotoSvc.selectAllSpacePhotoBySpace(spaceId);
	pageContext.setAttribute("photoList",photoList);
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
        <li class="breadcrumb-item active">場地[<%=spaceVO.getSpaceName()%>]</li>
      </ol>
      
		<div class="box_general padding_bottom">
			<div class="header_box version_2">
				<h2><i class="fa fa-user"></i><%=spaceVO.getSpaceId()%> / <%=spaceVO.getSpaceName()%></h2>
			</div>
			
 			<%-- 
			<div class="row">

				<div class="col-md-4">
					<div class="form-group">
					<label>Your photo</label>
						<form action="/file-upload" class="dropzone" name="empPhoto"></form> 
						<input type="file" class="dropzone" name="empPhoto"></input>
				    </div>
				</div>
			</div>
			--%>
			
			<%--  <img src="data:image/png;base64,<%=encode.encodeToString(memberVO.getMemberPhoto())%>" class="perview"/>  --%>


 			<div class="row">
				<div class="col-md-8 add_top_30">
					<div class="row">
<%-- 
						<div class="col-md-6">
							<div class="form-group">
								<label>場地編號</label>
								<input type="text" class="form-control" name="spaceId" readonly
									value="<%= (spaceVO == null)? "" : spaceVO.getSpaceId()%>"/>
							</div>
						</div>
--%>
						<div class="col-md-6">
							<div class="form-group">
								<label>場地類型</label>
								<input type="text" class="form-control" name="spaceType" readonly
									value="<%= (spaceVO == null)? "" : spaceVO.getSpaceType()%>"/>
								
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label>場地申請日期</label> 
								<input type="text" class="form-control" name="spaceSignupDate" readonly
									value="<%= (spaceVO == null)? "" : spaceVO.getSpaceSignupDate()%>"/>
								
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label>場主</label>
								
							
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MemberServlet" style="margin-bottom: 0px;">
							<input type="text" class="form-control" readonly
									value="[<%= (spaceVO == null)? "" : spaceVO.getMemberId()%>]<%= memberVO.getMemberName()%>" />
							    <button type="submit" class="btn btn-link">
									<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-box-arrow-in-right" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
									  <path fill-rule="evenodd" d="M6 3.5a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-2a.5.5 0 0 0-1 0v2A1.5 1.5 0 0 0 6.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-8A1.5 1.5 0 0 0 5 3.5v2a.5.5 0 0 0 1 0v-2z"/>
									  <path fill-rule="evenodd" d="M11.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H1.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"/>
									</svg>
				        		</button>
							    <input type="hidden" name="memberId"  value="<%=spaceVO.getMemberId()%>">
							    <input type="hidden" name="action"	value="backend_SelectOneMember"></FORM>
							 </div>
						</div>
						
						<div class="col-md-12">
							<div class="form-group">
								<label>場地地址</label>
								<input type="text" class="form-control" name="spaceAddress" readonly
									value="<%= (spaceVO == null)? "" : spaceVO.getSpaceAddress()%>"/>
							</div>
						</div>
						
						<div class="col-md-12">
							<div class="form-group">
								<label>場地設備</label>
								<input type="text" class="form-control" name="spaceEqument" readonly
									value="<%= (spaceVO == null)? "" : spaceVO.getSpaceEquipment()%>"/>
							</div>
						</div>


						<div class="col-md-6">
							<div class="form-group">
								<label>場地容納人數</label>
								<input type="text" class="form-control" name="spaceContain"readonly
									value="<%= (spaceVO == null)? "" : spaceVO.getSpaceContain()%>"/>
							</div>
						</div>
	

						<div class="col-md-6">
							<div class="form-group">
								<label>退費規定</label>
								<input type="text" class="form-control" name="spaceRefund" readonly
									value="<%= (spaceVO == null)? "" : spaceVO.getSpaceRefund()%>"/>
								
							</div>
						</div>

						<div class="col-md-12">
							<div class="form-group">
								<label>場地規範</label>
								<input type="text" class="form-control" name="spaceRule" readonly
									value="<%= (spaceVO == null)? "" : spaceVO.getSpaceRule()%>"/>
								
							</div>
						</div>


						<div class="col-md-4">
							<div class="form-group">
								<label>場地狀態</label>
								<input type="text" class="form-control" name="spaceStatus" readonly
									value="<%= (spaceVO == null)? "" : (spaceVO.getSpaceStatus().equals("T"))? "上架" : (spaceVO.getSpaceStatus().equals("F"))? "下架" : (spaceVO.getSpaceStatus().equals("B"))? "觀察" : "新申請"%>"/>
							</div>
						</div>
						<%-- 
						<div class="col-md-4">
							<div class="form-group">
								<label>場地上架日期</label>
								<input type="text" class="form-control" name="spaceOnsaleDate" readonly
									value="<%= (spaceVO == null)? "" : spaceVO.getSpaceOnsaleDate()%>"/>

							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label>場地下架日期</label>
								<input type="text" class="form-control" name="spaceOffsaleDate" readonly
									value="<%= (spaceVO == null)? "" : (spaceVO.getSpaceOffsaleDate() == null)? "" : spaceVO.getSpaceOffsaleDate()%>"/>

							</div>
						</div>
						
						<div class="col-md-6">
							<div class="form-group">
								<label>員工編號</label>
								<input type="text" class="form-control" name="empId" readonly
									value="<%= (spaceVO == null)? "" : spaceVO.getEmpId()%>"/>
							</div>
						</div>
						--%>
					</div>
					
					<!-- /row-->

					<!-- /row-->
				</div>
			</div>
			
		<div class="row">
			<div class="col-md-4">
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/SpaceCommServlet" style="margin-bottom: 0px;">
				    <input type="submit" class="btn_1 medium" value="評價紀錄">
				    <input type="hidden" name="spaceId"  value="<%=spaceVO.getSpaceId()%>">
				    <input type="hidden" name="spaceName"  value="<%=spaceVO.getSpaceName()%>">
				    <input type="hidden" name="action"	value="backend_SelectSpaceCommBySpace"></FORM>
			</div>
		</div>
			
		</div>
		
	  </div>
	  
	  <!-- /.container-fluid-->
   	</div>
   	
   	
   	 <!-- /Navigation-->
  <div class="content-wrapper">
    <div class="container-fluid">
      <!-- Breadcrumbs-->
      
		<div class="box_general padding_bottom">
			<div class="header_box version_2">
				<h2><i class="fa fa-user"></i><%=spaceVO.getSpaceId()%> / <%=spaceVO.getSpaceName()%> </h2>
			</div>
	<!-- Example DataTables Card-->
	<div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-table"></i> 場地時段</div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
              <thead>
				<tr>
					<tr>
						<td>可出租日期</td>
						<td>出租的起始時段</td>
						<td>出租的結束時段</td>
						<td>時段價格</td>
					</tr>
				</tr>
              </thead>
              <tfoot>
					<tr>
						<td>可出租日期</td>
						<td>出租的起始時段</td>
						<td>出租的結束時段</td>
						<td>時段價格</td>
					</tr>
              </tfoot>
			<tbody>
<c:forEach var="spaceDetailVO" items="${spaceDetailList}" begin="0" end="<%=spaceDetailList.size()%>">
	<tr>
		<td>${spaceDetailVO.spaceDetailFreeDate}</td>
		<td>${spaceDetailVO.spaceDetailFreeTimeStart}</td>
		<td>${spaceDetailVO.spaceDetailFreeTimeEnd}</td>
		<td>${spaceDetailVO.spaceDetailCharge}</td>
	</tr>
</c:forEach>
              </tbody>
            </table>
          </div>
        </div>
        <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
      </div>
	  </div>
   	</div>
   </div>
   
   
   
   
   <!-- /Navigation-->
  <div class="content-wrapper">
    <div class="container-fluid">
      <!-- Breadcrumbs-->
      
		<div class="box_general padding_bottom">
			<div class="header_box version_2">
				<h2><i class="fa fa-user"></i><%=spaceVO.getSpaceId()%> / <%=spaceVO.getSpaceName()%></h2>
			</div>
			
	<!-- Example DataTables Card-->
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
              <thead>
				<tr>
					<tr>
						<th>場地照片</th>
					</tr>
				</tr>
              </thead>
              <tfoot>
				<tr>
					<th>場地照片</th>
				</tr>
              </tfoot>
			<tbody>
<c:forEach var="spacePhotoVO" items="${photoList}" begin="0" end="<%=photoList.size()%>">
	<tr>
		<td><img src="data:image/png;base64,<%=encode.encodeToString(((SpacePhotoVO)pageContext.getAttribute("spacePhotoVO")).getSpacePhoto())%>" id="perview"/></td>
	</tr>
</c:forEach>
			</tbody>
            </table>
	  			</div>
   			</div>
   			<div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
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