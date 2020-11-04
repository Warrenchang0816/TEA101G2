<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.memberComment.model.*"%>

<%
	MemberCommentVO memberCommentVO = (MemberCommentVO) request.getAttribute("memberCommentVO");
 	Map<String,String> errorMsgs = (Map<String,String>) request.getAttribute("errorMsgs");
%>
    
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>MemberComment</title>

</head>

<body>
<h1>會員評論頁面</h1>
	<table>
		<ul>
			<li>
				<a href='<%=request.getContextPath()%>/frontend/memberComment/addMemberComment.jsp'><input type="button" value="新增評論"></a>
			</li>
			<li><a href='<%=request.getContextPath()%>/frontend/memberComment/getAllMemberComment.jsp'>List</a> all Comment. <br>
			<li>                    
				<form method="post" action="<%=request.getContextPath()%>/MemberCommentServlet.do">
					<input type="text" name="memberCommentId" placeholder="請輸入會員評論編號" value="${param.memberCommentId}"/>
					<input type="hidden" name="action" value="getOneMemberComment">
					<input type="submit" value="查詢">
					<span style="color:red">${errorMsgs.memberCommentId}</span>
				</form>
			</li>
			
			<jsp:useBean id="memberCommentSvc" scope="page" class="com.memberComment.model.MemberCommentService" />
			<li>
				<form method="post" action="<%=request.getContextPath()%>/MemberCommentServlet.do">
					<b>選擇會員評論編號:</b> 
					<select size="1" name="memberCommentId">
						<c:forEach var="memberCommentVO" items="<%= memberCommentSvc.getAllMemberComment()%>">
							<option value="${memberCommentVO.memberCommentId}">${memberCommentVO.memberCommentId}
						</c:forEach>
					</select> 
					<input type="hidden" name="action" value="getOneMemberComment">
					<input type="submit" value="查詢">
				</form>
			</li>
		</ul>
	</table>
</body>
</html>