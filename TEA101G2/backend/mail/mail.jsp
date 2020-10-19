<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.formList.model.*"%>
<%@ page import="com.emp.model.*"%>

<%

	FormListVO formListVO = (FormListVO)request.getAttribute("selectOneMail");
	pageContext.setAttribute("formListVO", formListVO);
	Base64.Encoder encode = Base64.getEncoder();
	
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

  
  <!-- Font Awesome -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/backend/mail/plugins/fontawesome-free/css/all.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/backend/mail/dist/css/adminlte.min.css">
  <!-- Google Font: Source Sans Pro -->
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
  
  <style>
  #card-title{
  	font-size: 1.5rem;
  }
  #mailImag{
  	height: 100px;
  }
  
  </style>

</head>


<body class="fixed-nav sticky-footer" id="page-top">



  <div class="content-wrapper">
    <div class="container-fluid">
    
        <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="row">
        
        <%@ include file="/backend/mail/mailSide.jsp"%> 
        <%@ include file="/backend/backendHF.jsp" %> 
        <%-- <jsp:include page="/backend/mail/mailSide.jsp"></jsp:include> --%> 
        <%-- <jsp:include page="/backend/backendHF.jsp"></jsp:include> --%> 
        
          <div class="col-md-9">
            <div class="card card-primary card-outline">
              <div class="card-header">
                <h3 class="card-title" id="card-title">主旨: ${formListVO.formListTitle}</h3>
				<span class="mailbox-read-time float-right">${formListVO.formListCreateDate}</span>
              </div>
              
        <jsp:useBean id="memberServ" scope="page" class="com.member.model.MemberServiceB" />
		<%
			String formListSolu = ((FormListVO)pageContext.getAttribute("formListVO")).getFormListSolu();
			String empName = empServ.selectOneEmp(formListSolu).getEmpName();
		%>
              
              <!-- /.card-header -->
              <div class="card-body">
                <div class="form-group">
	                <label>寄件者:</label>
	                <a href="<%=request.getContextPath()%>/backend/mail/sendMail.jsp?to=<%= formListSolu%>"><%=empName%></a>
                </div>
                <div class="form-group">
                <label>內容:</label>
                    <div id="compose-textarea" class="form-control" style="height: 300px" name="formListContext">
                    ${formListVO.formListContext}
                    
                    
                    </div>
                </div>
                <div class="form-group">
	                <div class="btn btn-default btn-file">
	                    <i class="fas fa-paperclip"></i> 附件1
	                    <a href="data:image/png;base64,<%=encode.encodeToString(((FormListVO)pageContext.getAttribute("formListVO")).getFormListFile())%>" download>
							<img id="mailImag" src="data:image/png;base64,<%=encode.encodeToString(((FormListVO)pageContext.getAttribute("formListVO")).getFormListFile())%>" alt="W3Schools">
						</a>
	                </div>
                </div>
				
				<a download="file.txt" href="data:application/octet-stream,<%=encode.encodeToString(((FormListVO)pageContext.getAttribute("formListVO")).getFormListFile())%>">download</a>
				
				
              </div>
              <!-- /.card-body -->
                </div>
            </div>
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->
    
    

    </div>
  </div>
    
    <script src="<%=request.getContextPath()%>/backend/js/adminlte.min.js"></script>
<!-- Page Script -->
<script>

</script>
<!-- AdminLTE for demo purposes -->
<script src="<%=request.getContextPath()%>/backend/js/demo.js"></script>
</body>

</html>