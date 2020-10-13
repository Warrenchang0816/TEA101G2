<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.memberComment.model.*"%>

<%
	MemberCommentVO memberCommentVO = (MemberCommentVO)request.getAttribute("memberCommentVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>getOneMember</title>

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
<a href='<%=request.getContextPath()%>/frontend/memberComment/MemberCommentHome.jsp'><input type="button" value="回首頁"></a>

<table>
	<tr>
		<th>會員評論編號</th>
		<th>A編號</th>
		<th>B編號</th>
		<th>場地評論</th>
		<th>場地評價等級</th>
		<th>評價日期</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>

	
	<tr>
		<td><%=memberCommentVO.getMemberCommentId()%></td>
		<td><%=memberCommentVO.getMemberAId()%></td>
		<td><%=memberCommentVO.getMemberBId()%></td>
		<td><%=memberCommentVO.getMemberCommentContent()%></td>
		<td><%=memberCommentVO.getMemberCommentLevel()%></td>
		<td><%=memberCommentVO.getMemberCommentDate()%></td>
		<td>
			<form method="post" action="<%=request.getContextPath()%>/MemberCommentServlet.do" style="margin-bottom: 0px;">
			    <input type="hidden" name="memberCommentId"  value="<%=memberCommentVO.getMemberCommentId()%>">
			    <input type="hidden" name="action"	value="getOneUpdate">
			    <input type="submit" value="修改"></form>
		</td>
		<td>
			<form method="post" action="<%=request.getContextPath()%>/MemberCommentServlet.do" style="margin-bottom: 0px;">
			    <input type="hidden" name="memberCommentId"  value="<%=memberCommentVO.getMemberCommentId()%>">
			    <input type="hidden" name="action" value=deleteMemberComment>
			    <input type="submit" value="刪除"></form>
		</td>
	</tr>

</table>


</body>
</html>