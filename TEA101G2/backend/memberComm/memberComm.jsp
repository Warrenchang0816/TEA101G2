<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.space.model.*"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.memberComment.model.*"%>

<%
	List<MemberCommentVO> list = (List<MemberCommentVO>)request.getAttribute("selectMemberCommByMember");
	pageContext.setAttribute("list",list);
	String memberName = (String)request.getAttribute("memberName");
	String memberId = (String)request.getAttribute("memberId");
%>

<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="Ansonika">
  <title>會員評價</title>
	
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
	  .cat_star {
	    margin-right: 2px;
	    color: #FFC107;
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
        <li class="breadcrumb-item active">[<%=memberName%>]評價紀錄</li>
      </ol>
      
      
		<!-- Example DataTables Card-->
      <div class="card mb-3">
        <div class="card-header">
             <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MemberServlet" style="margin-bottom: 0px;"><i class="fa fa-table"></i> 評價紀錄
				<button type="submit" class="btn btn-link">[<%=memberId%>]<%=memberName%></button>
				<input type="hidden" name="memberId"  value="<%=memberId%>">
			 	<input type="hidden" name="action"	value="backend_SelectOneMember">
			 </FORM>
          </div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
              <thead>
				<tr>
					<tr>
		<th>評價日期</th>
		<th>評論會員</th>
		<th>評論</th>
		<th>評價等級</th>
		<th>評價狀態</th>
					</tr>
				</tr>
              </thead>
              <tfoot>
					<tr>
		<th>評價日期</th>
		<th>評論會員</th>
		<th>評論</th>
		<th>評價等級</th>
		<th>評價狀態</th>
					</tr>
              </tfoot>
			<tbody>
<c:forEach var="memberCommVO" items="${list}" begin="0" end="<%=list.size()%>">
	<%
	MemberServiceB msb = new MemberServiceB();
	MemberVO memberVO = msb.selectOneMember(((MemberCommentVO)pageContext.getAttribute("memberCommVO")).getMemberBId());
	String memberBName = memberVO.getMemberName();
	pageContext.setAttribute("memberBName", memberBName);
	%>
	<tr>
		<td>${memberCommVO.memberCommentDate}</td>
		<td><input type="hidden" name="memberCommId" value="${memberCommVO.memberCommentId}">
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MemberServlet" style="margin-bottom: 0px;">[${memberCommVO.memberBId}]${memberBName}
				<button type="submit" class="btn btn-link">
					<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-box-arrow-in-right" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
					  <path fill-rule="evenodd" d="M6 3.5a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-2a.5.5 0 0 0-1 0v2A1.5 1.5 0 0 0 6.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-8A1.5 1.5 0 0 0 5 3.5v2a.5.5 0 0 0 1 0v-2z"/>
					  <path fill-rule="evenodd" d="M11.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H1.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"/>
					</svg>
        		</button>
			    <input type="hidden" name="memberId" id="tableMemberId" value="${memberCommVO.memberBId}">
			    <input type="hidden" name="memberCommId" id="tableMemberCommId" value="${memberCommVO.memberCommentId}">
			    <input type="hidden" name="action"	value="backend_SelectOneMember"></FORM>
		</td>
		<td>${memberCommVO.memberCommentContent}</td>
		<td>
			<div class="cat_star">
				<c:forEach begin="1" end="${memberCommVO.memberCommentLevel}">
					<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-star-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
					<path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.283.95l-3.523 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"/>
					</svg>
				</c:forEach>
			</div>
		</td>
		<td>
			<div class="styled-select">
				<select size="1" name="memberCommStatus" id="memberCommStatus" onchange="javascript:return confirm('確認更改?');">
					<option value = "Y" ${memberCommVO == null ? "" : memberCommVO.memberCommStatus.equals("Y") ? "selected" : ""}>顯示</option>
					<option value = "N" ${memberCommVO == null ? "" : memberCommVO.memberCommStatus.equals("N") ? "selected" : ""}>隱藏</option>
				</select>
			</div>
		</td>
	
</c:forEach>
 
 
 
              </tbody>
            </table>
          </div>
        </div>
        <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
      </div>
	  <!-- /tables-->
	  </div>
	  <!-- /container-fluid-->
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

<script>
<%--
function change() { 
	
    var doc; 
    var result = confirm("Press a button!"); 
    if (result == true) { 
        doc = "OK was pressed."; 
    } else { 
        doc = "Cancel was pressed."; 
    } 
    document.getElementById("g").innerHTML = doc; 
} 
--%>
$("td select").on("change", function() {
	var memberCommId2 = document.getElementById('tableMemberCommId').value;
	console.log('memberCommId:'+memberCommId);
	var memberCommStatus = this.value;
	console.log('memberCommStatus:'+memberCommStatus);
	var memberCommId = $(this).parent().parent().parent().find('input').val();
	console.log('memberCommId:'+memberCommId);
	$.ajax({
	    type: 'POST',
	    url: '<%=request.getContextPath()%>/MemberCommServlet',
	    dataType: "json",
	    data: {
	    	action: 'backend_UpdateMemberCommStatus',
	    	memberCommId: memberCommId,
	    	memberCommStatus: memberCommStatus,
	    },
	    success: function(data) {
	    },
	})
	<%--
	var tableMemberId = document.getElementById('tableMemberId').value;
	var table = document.getElementById('dataTable')
	var getTBody = table.getElementsByTagName("tbody")[1];
	var memberCommStatus = document.getElementById('memberCommStatus').value;
	var value = this.value;
    var $cell = $(this).parent();
    var $row = $cell.parent();
    alert("Selected ["+tableMemberId+"] in cell ["+$cell.index()+"] of row ["+$row.index()+"]");
    --%>
});

</script>

</html>