<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.memberComm.model.*"%>

<%
	MemberCommVO memberCommVO = (MemberCommVO) request.getAttribute("selectOneMemberComm");
 	LinkedList<String> errorMsgs = (LinkedList<String>) request.getAttribute("errorMsgs");
%>
    
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>MemberComm</title>


<h1>會員評論頁面</h1>
</head>

<body>
<a href='<%=request.getContextPath()%>/backend/member/member.jsp'><input type="button" value="會員頁面"></a>
	<table>
		<ul>
			<li>
				<form method="post" action="<%=request.getContextPath()%>/MemberCommServlet">
					<input type="text" name="memberCommId" placeholder="請輸入會員評論編號"
						value="<%= (memberCommVO == null)? "" : memberCommVO.getMemberCommId()%>"/>
					<input type="hidden" name="action" value="backend_SelectOneMemberComm">
					<input type="submit" value="查詢">
					<span style="color:red"><%= (errorMsgs == null)? "" : "  " + errorMsgs.poll()%></span>
				</form>
			</li>
			
			<jsp:useBean id="memberCommSvc" scope="page" class="com.memberComm.model.MemberCommService" />
			<li>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MemberCommServlet">
					<b>選擇會員評論編號:</b> 
					<select size="1" name="memberCommId">
						<c:forEach var="memberCommVO" items="<%= memberCommSvc.selectAllMemberComm() %>">
							<option value="${memberCommVO.memberCommId}">${memberCommVO.memberCommId}
						</c:forEach>
					</select> <input type="hidden" name="action" value="backend_SelectOneMemberComm">
					<input type="submit" value="查詢">
				</FORM>
			</li>

			<li>
				<a href='<%=request.getContextPath()%>/backend/spaceComm/addSpaceComm.jsp'><input type="button" value="新增"></a>
			</li>

			<li>
				<a href='<%=request.getContextPath()%>/backend/spaceComm/selectAllSpaceComm.jsp'><input type="button" value="列表"></a>
			</li>
		</ul>
	</table>


<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
<script src="<%=request.getContextPath()%>/backend/jquery.js"></script>

</body>
</html>