<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.memberComment.model.*"%>

<%
	MemberCommentVO memberCommentVO = (MemberCommentVO) request.getAttribute("memberCommentVO");
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>addMemberComm</title>
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

<a href='<%=request.getContextPath()%>/frontend/memberComment/MemberCommentHome.jsp'><input type="button" value="回首頁"></a>
<br>
<h3>評論新增:</h3>


<form method="post" action="<%=request.getContextPath()%>/MemberCommentServlet.do" name="form1">
<table>
			<tr>
				<td>A編號:</td>
				<td><input type="TEXT" name="memberAId" size="45" value="${param.memberAId}"/>
				<span style="color:red">${errorMsgs.memberBId}</span></td>
			</tr>
			<tr>
				<td>B編號:</td>
				<td><input type="TEXT" name="memberBId" size="45"value="${param.memberBId}"/>
					<span style="color:red">${errorMsgs.memberBId}</span></td>
					
			</tr>
			<tr>
				<td>會員評論:</td>
				<td><input type="TEXT" name="memberCommentContent" size="45"value="${param.memberCommentContent}"/>
					<span style="color:red">${errorMsgs.memberBId}</span></td>			
			</tr>
			<tr>
				<td>會員評價等級:</td>
				<td><input type="TEXT" name="memberCommentLevel" size="45"value="${param.memberCommentLevel}"/>
					<span style="color:red">${errorMsgs.memberBId}</span></td>
			</tr>
		</table>
		
	<input type="hidden" name="action" value="addMemberComment">
	<input type="submit" value="送出新增"></form>
</body>
</html>