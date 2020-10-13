  
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.memberFavorite.model.*"%>

<%    
	MemberFavoriteService memberFavoriteSvc = new MemberFavoriteService();
    List<MemberFavoriteVO> list = memberFavoriteSvc.getAllMemberFavorite();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>getAllMemberFavorite</title>

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
	width: 800px;
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
		<th>會員評論編號</th>
		<th>會員編號</th>
		<th>場地編號</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>

<c:forEach var="memberFavoriteVO" items="${list}" begin="0" end="<%=list.size()%>">
	<tr>
		<td>${memberFavoriteVO.memberFavoriteId}</td>
		<td>${memberFavoriteVO.memberId}</td>
		<td>${memberFavoriteVO.spaceId}</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MemberFavoriteServlet.do">
			    <input type="hidden" name="memberFavoriteId" value="${memberFavoriteVO.memberFavoriteId}">
			    <input type="hidden" name="action" value="getOneUpdate">
			    <input type="submit" value="修改"></FORM>
		</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MemberFavoriteServlet.do">
			    <input type="hidden" name="memberFavoriteId"  value="${memberFavoriteVO.memberFavoriteId}">
			    <input type="hidden" name="action" value="deleteMemberFavorite">
			    <input type="submit" value="刪除"></FORM>
		</td>
	
</c:forEach>


</table>


</body>
</html>