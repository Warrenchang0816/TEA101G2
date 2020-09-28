<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.memberFavorite.model.*"%>

<%
	MemberFavoriteVO memberFavoriteVO = (MemberFavoriteVO) request.getAttribute("selectOneMemberFavorite");
	LinkedList<String> errorMsgs = (LinkedList<String>) request.getAttribute("errorMsgs");
%>
    
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>MemberFavorite</title>
<h1>會員最愛頁面</h1>
</head>

<body>
<a href='<%=request.getContextPath()%>/frontend/member/member.jsp'><input type="button" value="會員頁面"></a>
	<table>
		<ul>
			<li>
				<form method="post" action="<%=request.getContextPath()%>/MemberFavoriteServlet">
					<input type="text" name="memberFavoriteId" placeholder="請輸入會員最愛編號"
						value="<%= (memberFavoriteVO == null)? "" : memberFavoriteVO.getMemberFavoriteId()%>"/>
					<input type="hidden" name="action" value="selectOneMemberFavorite">
					<input type="submit" value="查詢">
					<span style="color:red"><%= (errorMsgs == null)? "" : "  " + errorMsgs.poll()%></span>
				</form>
			</li>
			
			<jsp:useBean id="memberFavoriteSvc" scope="page" class="com.memberFavorite.model.MemberFavoriteService" />
			<li>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MemberFavoriteServlet">
					<b>選擇場地編號:</b> 
					<select size="1" name="memberFavoriteId">
						<c:forEach var="memberFavoriteVO" items="<%= memberFavoriteSvc.selectAllMemberFavorite() %>">
							<option value="${memberFavoriteVO.memberFavoriteId}">${memberFavoriteVO.memberFavoriteId}
						</c:forEach>
					</select> <input type="hidden" name="action" value="selectOneMemberFavorite">
					<input type="submit" value="查詢">
				</FORM>
			</li>

			<li>
				<a href='<%=request.getContextPath()%>/frontend/memberFavorite/addMemberFavorite.jsp'><input type="button" value="新增"></a>
			</li>

			<li>
				<a href='<%=request.getContextPath()%>/frontend/memberFavorite/selectAllMemberFavorite.jsp'><input type="button" value="列表"></a>
			</li>
		</ul>
	</table>


<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
<script src="<%=request.getContextPath()%>/frontend/jquery.js"></script>

</body>
</html>