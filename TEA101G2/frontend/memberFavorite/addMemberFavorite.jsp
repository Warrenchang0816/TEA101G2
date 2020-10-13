<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.memberFavorite.model.*"%>

<%
	MemberFavoriteVO memberFavoriteVO = (MemberFavoriteVO) request.getAttribute("memberFavoriteVO");
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>addMemberFavorite</title>
<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>

<body>

<a href='<%=request.getContextPath()%>/frontend/memberFavorite/MemberFavoriteHome.jsp'><input type="button" value="回首頁"></a>
<br>
<h3>評論收藏:</h3>

<form method="post" action="<%=request.getContextPath()%>/MemberFavoriteServlet.do" name="form1">
<table>
			<tr>
				<td>會員編號:</td>
				<td><input type="TEXT" name="memberId" size="45" value="${param.memberId}"/>
				<span style="color:red">${errorMsgs.memberId}</span></td>
			</tr>
			<tr>
				<td>場地編號:</td>
				<td><input type="TEXT" name="spaceId" size="45"value="${param.spaceId}"/>
					<span style="color:red">${errorMsgs.spaceId}</span></td>
			</tr>
		</table>
		
	<input type="hidden" name="action" value="addMemberFavorite">
	<input type="submit" value="送出新增"></form>
</body>
</html>