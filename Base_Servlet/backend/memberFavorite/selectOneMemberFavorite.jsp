<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.memberFavorite.model.*"%>

<%
	MemberFavoriteVO memberFavoriteVO = (MemberFavoriteVO)request.getAttribute("selectOneMemberFavorite");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>selectOneMemberFavorite</title>

<a href='<%=request.getContextPath()%>/backend/memberFavorite/memberFavorite.jsp'><input type="button" value="回會員最愛頁面"></a>

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


<table>
	<tr>
		<th>會員最愛編號</th>
		<th>會員編號</th>
		<th>場地編號</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>

	
	<tr>
		<td><%=memberFavoriteVO.getMemberFavoriteId()%></td>
		<td><%=memberFavoriteVO.getMemberId()%></td>
		<td><%=memberFavoriteVO.getSpaceId()%></td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MemberFavoriteServlet" style="margin-bottom: 0px;">
			    <input type="submit" value="修改">
			    <input type="hidden" name="memberFavoriteId"  value="<%=memberFavoriteVO.getMemberFavoriteId()%>">
			    <input type="hidden" name="action"	value="backend_SelectOneUpdate"></FORM>
		</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MemberFavoriteServlet" style="margin-bottom: 0px;">
			    <button name="delete" value="刪除" type="submit" class="delete" onclick="javascript:return confirm('確認刪除?');">刪除</button>
			    <input type="hidden" name="memberFavoriteId"  value="<%=memberFavoriteVO.getMemberFavoriteId()%>">
			    <input type="hidden" name="action" value="backend_DeleteMemberFavorite"></FORM>
		</td>
	</tr>

</table>


</body>
</html>