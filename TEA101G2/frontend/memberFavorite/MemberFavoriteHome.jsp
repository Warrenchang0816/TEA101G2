<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.memberFavorite.model.*"%>

<%
	MemberFavoriteVO MemberFavoriteVO = (MemberFavoriteVO) request.getAttribute("memberFavoriteVO");
 	Map<String,String> errorMsgs = (Map<String,String>) request.getAttribute("errorMsgs");
%>
    
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>MemberFavorite</title>

</head>

<body>
<h1>會員收藏頁面</h1>
	<table>
		<ul>
			<li>
				<a href='<%=request.getContextPath()%>/frontend/memberFavorite/addMemberFavorite.jsp'><input type="button" value="新增評論"></a>
			</li>
			<li><a href='getAllMemberFavorite.jsp'>List</a> all Favorite. <br>
			<li>                    
				<form method="post" action="<%=request.getContextPath()%>/MemberFavoriteServlet.do">
					<input type="text" name="memberFavoriteId" placeholder="請輸入收藏編號" value="${param.memberFavoriteId}"/>
					<input type="hidden" name="action" value="getOneMemberFavorite">
					<input type="submit" value="查詢">
					<span style="color:red">${errorMsgs.memberFavoriteId}</span>
				</form>
			</li>
			
			<jsp:useBean id="memberFavoriteSvc" scope="page" class="com.memberFavorite.model.MemberFavoriteService" />
			<li>
				<form method="post" action="<%=request.getContextPath()%>/MemberFavoriteServlet.do">
					<b>選擇收藏編號:</b> 
					<select size="1" name="memberFavoriteId">
						<c:forEach var="memberFavoriteVO" items="<%= memberFavoriteSvc.getAllMemberFavorite()%>">
							<option value="${memberFavoriteVO.memberFavoriteId}">${memberFavoriteVO.memberFavoriteId}
						</c:forEach>
					</select> 
					<input type="hidden" name="action" value="getOneMemberFavorite">
					<input type="submit" value="查詢">
				</form>
			</li>
		</ul>
	</table>
</body>
</html>