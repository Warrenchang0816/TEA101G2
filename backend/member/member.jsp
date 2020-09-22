<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>

<%
	MemberVO memberVO = (MemberVO) request.getAttribute("selectOneMember");
	LinkedList<String> errorMsgs = (LinkedList<String>) request.getAttribute("errorMsgs");
%>
    
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Member</title>
<h1>會員頁面</h1>
</head>

<body>
<a href='<%=request.getContextPath()%>/backend/BigSpace.jsp'><input type="button" value="後台首頁"></a>
	<table>
		<ul>
			<li>
				<form method="post" action="<%=request.getContextPath()%>/MemberServlet">
					<input type="text" name="memberId" placeholder="請輸入會員編號">
					<input type="hidden" name="action" value="backend_SelectOneMember">
					<input type="submit" value="查詢">
					<span style="color:red"><%= (errorMsgs == null)? "" : "  " + errorMsgs.poll()%></span>
				</form>
			</li>
			
			<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
			<li>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MemberServlet">
					<b>選擇場地編號:</b> 
					<select size="1" name="memberId">
						<c:forEach var="memberVO" items="<%= memberSvc.selectAllMember() %>">
							<option value="${memberVO.memberId}">${memberVO.memberId}
						</c:forEach>
					</select> <input type="hidden" name="action" value="backend_SelectOneMember">
					<input type="submit" value="查詢">
				</FORM>
			</li>

			<li>
				<a href='<%=request.getContextPath()%>/backend/member/addMember.jsp'><input type="button" value="新增"></a>
			</li>

			<li>
				<a href='<%=request.getContextPath()%>/backend/member/selectAllMember.jsp'><input type="button" value="列表"></a>
			</li>
		</ul>
	</table>
	
	<a href='<%=request.getContextPath()%>/backend/memberComm/memberComm.jsp'><input type="button" value="會員評價"></a>
	<a href='<%=request.getContextPath()%>/backend/memberFavorite/memberFavorite.jsp'><input type="button" value="會員最愛"></a>


<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
<script src="<%=request.getContextPath()%>/backend/jquery.js"></script>
<script src="<%=request.getContextPath()%>/backend/member/member.js"></script>

</body>
</html>