<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.memberFavorite.model.*"%>

<%
	MemberFavoriteVO memberFavoriteVO = (MemberFavoriteVO)request.getAttribute("memberFavoriteVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>getOneFavorite</title>

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
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>
</head>
<body>
<a href='<%=request.getContextPath()%>/frontend/memberFavorite/MemberFavoriteHome.jsp'><input type="button" value="回首頁"></a>

<table>
	<tr>
		<th>會員收藏編號</th>
		<th>會員編號</th>
		<th>場地編號</th>
	</tr>

	
	<tr>
		<td><%=memberFavoriteVO.getMemberFavoriteId()%></td>
		<td><%=memberFavoriteVO.getMemberId()%></td>
		<td><%=memberFavoriteVO.getSpaceId()%></td>
		<td>
			<form method="post" action="<%=request.getContextPath()%>/MemberFavoriteServlet.do" style="margin-bottom: 0px;">
			    <input type="hidden" name="memberFavoriteId"  value="<%=memberFavoriteVO.getMemberFavoriteId()%>">
			    <input type="hidden" name="action"	value="getOneUpdate">
			    <input type="submit" value="修改"></form>
		</td>
		<td>
			<form method="post" action="<%=request.getContextPath()%>/MemberFavoriteServlet.do" style="margin-bottom: 0px;">
			    <input type="hidden" name="memberFavoriteId"  value="<%=memberFavoriteVO.getMemberFavoriteId()%>">
			    <input type="hidden" name="action" value=deleteMemberFavorite>
			    <input type="submit" value="刪除"></form>
		</td>
	</tr>

</table>


</body>
</html>