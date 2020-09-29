<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>

<%
	EmpVO empVO = (EmpVO) request.getAttribute("selectOneEmp");
	LinkedList<String> errorMsgs = (LinkedList<String>) request.getAttribute("errorMsgs");
%>
    
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Emp</title>
<h1>員工頁面</h1>
</head>

<body>
<a href='<%=request.getContextPath()%>/frontend/BigFullSpace.jsp'><input type="button" value="後台首頁"></a>
<a href='<%=request.getContextPath()%>/frontend/BigSpace.jsp'><input type="button" value="後台首頁"></a>
	<table>
		<ul>
			<li>
				<form method="post" action="<%=request.getContextPath()%>/EmpServlet">
					<input type="text" name="empId" placeholder="請輸入員工編號">
					<input type="hidden" name="action" value="selectOneEmp">
					<input type="submit" value="查詢">
					<span style="color:red"><%= (errorMsgs == null)? "" : "  " + errorMsgs.poll()%></span>
				</form>
			</li>
			
			<jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmpService" />
			<li>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/EmpServlet">
					<b>選擇場地編號:</b> 
					<select size="1" name="empId">
						<c:forEach var="empVO" items="<%= empSvc.selectAllEmp() %>">
							<option value="${empVO.empId}">${empVO.empId}
						</c:forEach>
					</select> <input type="hidden" name="action" value="selectOneEmp">
					<input type="submit" value="查詢">
				</FORM>
			</li>

			<li>
				<a href='<%=request.getContextPath()%>/frontend/emp/addEmp.jsp'><input type="button" value="新增"></a>
			</li>

			<li>
				<a href='<%=request.getContextPath()%>/frontend/emp/selectAllEmp.jsp'><input type="button" value="列表"></a>
			</li>
		</ul>
	</table>


<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
<script src="<%=request.getContextPath()%>/frontend/jquery.js"></script>
<script src="<%=request.getContextPath()%>/frontend/emp/emp.js"></script>

</body>
</html>