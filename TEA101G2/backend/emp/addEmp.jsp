<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>

<%
	EmpVO empVO = (EmpVO) request.getAttribute("addEmp");
	pageContext.setAttribute("empVO",empVO);
	LinkedList<String> errorMsgs = (LinkedList<String>) request.getAttribute("errorMsgs");
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
  <title>新增員工</title>
	
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
	.custom-file-upload {
    border: 1px solid #ccc;
    display: inline-block;
    padding: 6px 12px;
    cursor: pointer;
}
.upload_cover {
position: relative;
width: 100px;
height: 100px;
text-align: center;
cursor: pointer;
background: #efefef;
border: 1px solid #595656;
}

.upload_icon {
font-weight: bold;
font-size: 180%;
position: absolute;
left: 0;
width: 100%;
top: 20%;
}
.delAvatar {
position: absolute;
right: 2px;
top: 2px;
}


</style>

<style>
#label-photo{
  display: inline-block;
  background-color: royalblue;
  color: white;
  padding: 0.5rem;
  font-family: sans-serif;
  border-radius: 0.3rem;
  cursor: pointer;
  margin-top: 1rem;
}


</style>


</head>


<body class="fixed-nav sticky-footer" id="page-top">

<%@ include file="/backend/backendHF.jsp" %> 

<%-- <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/EmpServlet" name="addEmp" enctype="multipart/form-data"> --%> 
 
  <!-- /Navigation-->
  <div class="content-wrapper">
    <div class="container-fluid">
      <!-- Breadcrumbs-->
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="index.jsp">首頁</a>
        </li>
        <li class="breadcrumb-item active">新增員工</li>
      </ol>
      
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/EmpServlet" name="addEmp" enctype="multipart/form-data"> 
		<div class="box_general padding_bottom">
			<div class="header_box version_2">
				<h2><i class="fa fa-user"></i>新增員工</h2>
			</div>
			
 			
			<div class="row">

				<div class="col-md-4" id="photo-preveiw">
				<%--
					<div class="form-group">
					<label>員工照片</label>
						 <form action="/file-upload" class="dropzone" name="empPhoto"></form>
						<div class="dropzone"><div class="dz-default dz-message"><span>照片預覽</span></div></div>
				    </div>
				    
				    
				    <img id="blah" src="#" alt="your image" />
				    --%>
				</div>
			</div>
			<%-- 
			<label for="file-upload" class="custom-file-upload">
			    <i class="fa fa-cloud-upload"></i> 上傳照片
			</label>
			
			<input id="file-upload" type="file" class = "empPhoto" name="empPhoto"/>
			
				<div class="preview" id="preview"><span class="text1">預覽圖</span></div>
				<input type="file" name="empPhoto"/>
			--%>
			
			<div id="btn-photo">
				<label id="label-photo">
					<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-card-image" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
					<path fill-rule="evenodd" d="M14.5 3h-13a.5.5 0 0 0-.5.5v9c0 .013 0 .027.002.04V12l2.646-2.354a.5.5 0 0 1 .63-.062l2.66 1.773 3.71-3.71a.5.5 0 0 1 .577-.094L15 9.499V3.5a.5.5 0 0 0-.5-.5zm-13-1A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-13zm4.502 3.5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z"/>
					</svg>
					上傳照片
					<input type="file" name="spacePhoto" id="imgInp2" style="visibility: hidden; position: absolute;"/>									
				</label>
			</div>

 			<div class="row">
				<div class="col-md-8 add_top_30">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label>員工帳號</label>
								<input type="text" class="form-control" placeholder="請輸入帳號" name="empAccount"
									value="<%= (empVO == null)? "" : empVO.getEmpAccount()%>"/>
								<span style="color:red"><%= (errorMsgs == null)? "" : (empVO.getEmpAccount().equals("") && errorMsgs.peek().contains("帳號"))? errorMsgs.poll() : (errorMsgs.peek().contains("帳號"))? errorMsgs.poll() : ""%></span>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<span style="color:blue" id="accountSpan">帳號請輸入6~16個英文字母和數字且至少包含一個大寫英文字母，不能包含特殊符號</span>
							</div>
						</div>
					</div>
								
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label>員工密碼</label>
								<input type="text" class="form-control" placeholder="請輸入密碼" name="empPassword"
									value="<%= (empVO == null)? "" : empVO.getEmpPassword()%>"/>
								<span style="color:red"><%= (errorMsgs == null)? "" : (empVO.getEmpPassword().equals("") && errorMsgs.peek().contains("密碼"))? errorMsgs.poll() : (errorMsgs.peek().contains("密碼"))? errorMsgs.poll() : ""%></span>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<span style="color:blue" id="accountSpan">密碼請輸入6~16個英文字母和數字，不能包含特殊符號</span>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label>員工姓名</label>
								<input type="text" class="form-control" placeholder="請輸入姓名" name="empName"
									value="<%= (empVO == null)? "" : empVO.getEmpName()%>"/>
								<span style="color:red"><%= (errorMsgs == null)? "" : (empVO.getEmpName().equals("") && errorMsgs.peek().contains("姓名"))? errorMsgs.poll() : (errorMsgs.peek().contains("姓名"))? errorMsgs.poll() : ""%></span>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label>員工性別</label>
								<div class="styled-select">
								<select  size="1" name="empSex">
									<option value = "" ${empVO == null ? 'selected' : empVO.empSex == '' ? 'selected' : ''} >------</option>
									<option value = "M" ${empVO == null ? '' : empVO.empSex == 'M' ? 'selected' : ''} >男</option>
									<option value = "F" ${empVO == null ? '' : empVO.empSex == 'F' ? 'selected' : ''} >女</option>
								</select>
								</div>
								<span style="color:red"><%= (errorMsgs == null)? "" : (empVO.getEmpSex().equals("") && errorMsgs.peek().contains("性別"))? errorMsgs.poll() : (errorMsgs.peek().contains("性別"))? errorMsgs.poll() : ""%></span>
							</div>
						</div>
						
						<div class="col-md-6">
							<div class="form-group">
								<label>員工連絡電話</label>
								<input type="text" class="form-control" placeholder="請輸入電話" name="empPhone"
									value="<%= (empVO == null)? "" : empVO.getEmpPhone()%>"/>
								<span style="color:red"><%= (errorMsgs == null)? "" : (empVO.getEmpPhone().equals("") && errorMsgs.peek().contains("電話"))? errorMsgs.poll() : (errorMsgs.peek().contains("電話"))? errorMsgs.poll() : ""%></span>
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label>員工Email</label>
								<input type="text" class="form-control" placeholder="請輸入email" name="empEmail"
									value="<%= (empVO == null)? "" : empVO.getEmpEmail()%>"/>
								<span style="color:red"><%= (errorMsgs == null)? "" : (empVO.getEmpEmail().equals("") && errorMsgs.peek().contains("email"))? errorMsgs.poll() : (errorMsgs.peek().contains("email"))? errorMsgs.poll() : ""%></span>
							</div>
						</div>
						
						<div class="col-md-12">
							<div class="form-group">
								<label>員工聯絡地址</label>
								<input type="text" class="form-control" placeholder="請輸入地址" name="empAddress"
									value="<%= (empVO == null)? "" : empVO.getEmpAddress()%>"/>
								<span style="color:red"><%= (errorMsgs == null)? "" : (empVO.getEmpAddress().equals("") && errorMsgs.peek().contains("地址"))? errorMsgs.poll() : (errorMsgs.peek().contains("地址"))? errorMsgs.poll() : ""%></span>
							</div>
						</div>
						
						<div class="col-md-6">
							<div class="form-group">
								<label>員工生日</label>
								<input type="text" class="form-control" name="empBirth" id="empBirth">
							</div>
						</div>

	
						<div class="col-md-6">
							<div class="form-group">
								<label>員工到職日</label>
								<input type="text" class="form-control" name="empHireDate" id="empHireDate">
							</div>
						</div>

					

						<div class="col-md-4">
							<div class="form-group">
								<label>員工職稱</label>
								<input type="text" class="form-control" placeholder="請輸入職稱" name="empJob"
									value="<%= (empVO == null)? "" : empVO.getEmpJob()%>"/>
								<span style="color:red"><%= (errorMsgs == null)? "" : (empVO.getEmpJob().equals("") && errorMsgs.peek().contains("職稱"))? errorMsgs.poll() : (errorMsgs.peek().contains("職稱"))? errorMsgs.poll() : ""%></span>
							</div>
						</div>
						
						<div class="col-md-4">
							<div class="form-group">
								<label>員工權限</label>
								<div class="styled-select">
								<select  size="1" name="empAuth">
									<option value = "0" ${empVO == null ? "selected" : empVO.empAuth == "0" ? "selected" : ""}>------</option>
									<option value = "1" ${empVO == null ? "" : empVO.empAuth == "1" ? "selected" : ""}>員工</option>
									<option value = "2" ${empVO == null ? "" : empVO.empAuth == "2" ? "selected" : ""}>主管</option>
								</select>
								</div>
								<span style="color:red"><%= (errorMsgs == null)? "" : (empVO.getEmpAuth() == 0 && errorMsgs.peek().contains("權限"))? errorMsgs.poll() : "" %></span>
							</div>
						</div>
						
						<div class="col-md-4">
							<div class="form-group">
								<label>員工在職狀態</label>
								<input type="hidden" class="form-control" name="empStatus" value="O"/>
								<input type="text" class="form-control" value="在職" readonly/>
							</div>
						</div>
						
						<%--
						<div class="col-md-4">
							<div class="form-group">
								<label>員工在職狀態</label>
								<div class="styled-select">
								<select  size="1" name="empStatus">
									<option value = "" >------</option>
									<option value = "O">在職</option>
									<option value = "P">停職</option>
									<option value = "L">離職</option>
								</select>
								</div>
							</div>
						</div>
						 --%>
					</div>
					
				</div>
			</div>
			
		</div>
		<input type="hidden" name="action" value="backend_AddEmp">
		<button name="add" type="submit" class="btn_1 medium" onclick="javascript:return confirm('確認新增?');">送出新增</button>
		<a class="btn_1 medium" href='<%=request.getContextPath()%>/backend/index.jsp'>取消</a>
</FORM>
				
				
			
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

  <% 
  	java.sql.Date empBirth = new java.sql.Date(System.currentTimeMillis());
  	java.sql.Date empHireDate = new java.sql.Date(System.currentTimeMillis());
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
        $('#empBirth').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%= (empVO == null)? empBirth : empVO.getEmpBirth()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        $.datetimepicker.setLocale('zh');
        $('#empHireDate').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
	       value: '<%= (empVO == null)? "-1970-01-01" : empVO.getEmpHireDate()%>',
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           minDate: '-1970-01-01',                              // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
   </script>
   
   
   <script>
      $("#imgInp2").on("change",function() {
    	  $("img.preview").remove();
		for (let i = 0; i < this.files.length; i++) {
			let reader = new FileReader();
			reader.readAsDataURL(this.files[i]);
			reader.addEventListener("load", function() {
				$("#photo-preveiw").append(`<img src="\${reader.result}" class ="preview" id="preview" style="height:200px; margin-top:10px; margin-right:5px;border: double;">`);
			});
		};
	});
</script>

</html>
