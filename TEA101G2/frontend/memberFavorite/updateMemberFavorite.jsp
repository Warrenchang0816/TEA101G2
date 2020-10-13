<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.memberFavorite.model.*"%>

<% 
	MemberFavoriteVO memberFavoriteVO = (MemberFavoriteVO)request.getAttribute("memberFavoriteVO");
	Map<String,String> errorMsgs = (Map<String,String>) request.getAttribute("errorMsgs");
%>


<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>updateMemberFavorite</title>
</head>

<body>

<h3>資料修改:</h3>

<form method="post" ACTION="<%=request.getContextPath()%>/MemberFavoriteServlet.do" name="form1">
<table>

			<tr>
				<td>會員收藏編碼:</td>
				<td><%=memberFavoriteVO.getMemberFavoriteId()%></td>
			</tr>
			<tr>
				<td>會員編號:</td>
				<td><input type="TEXT" name="memberId" size="45" value="<%=memberFavoriteVO.getMemberId()%>"/>
				<span style="color:red">${errorMsgs.memberId}</span></td>
			</tr>
			<tr>
				<td>場地編號:</td>
				<td><input type="TEXT" name="spaceId" size="45" value="<%=memberFavoriteVO.getSpaceId()%>"/>
				<span style="color:red">${errorMsgs.spaceId}</span></td>
			</tr>	
		</table>

<br>
<input type="hidden" name="action" value="updateMemberFavorite">
<input type="hidden" name="memberFavoriteId" value="<%=memberFavoriteVO.getMemberFavoriteId()%>">
<input type="submit" value="修改">
<a href='<%=request.getContextPath()%>/frontend/memberFavorite/MemberFavoriteHome.jsp'><input type="button" value="取消修改"></a>
</form>
</body>
</html>