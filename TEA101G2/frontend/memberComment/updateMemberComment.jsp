<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.memberComment.model.*"%>

<% 
	MemberCommentVO memberCommentVO = (MemberCommentVO)request.getAttribute("memberCommentVO");
	Map<String,String> errorMsgs = (Map<String,String>) request.getAttribute("errorMsgs");
%>


<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>updateMemberComm</title>
</head>

<body>

<h3>資料修改:</h3>

<form method="post" ACTION="<%=request.getContextPath()%>/MemberCommentServlet.do" name="form1">
<table>

			<tr>
				<td>會員評論編碼:</td>
				<td><%=memberCommentVO.getMemberCommentId()%></td>
			</tr>
			<tr>
				<td>B編號:</td>
				<td><input type="TEXT" name="memberAId" size="45" value="<%=memberCommentVO.getMemberAId()%>"/>
				<span style="color:red">${errorMsgs.memberAId}</span></td>
			</tr>
			<tr>
				<td>A編號:</td>
				<td><input type="TEXT" name="memberBId" size="45" value="<%=memberCommentVO.getMemberBId()%>"/>
				<span style="color:red">${errorMsgs.memberBId}</span></td>
			</tr>
			<tr>
				<td>場地評論:</td>
				<td><input type="TEXT" name="memberCommentContent" size="45" value="<%=memberCommentVO.getMemberCommentContent()%>"/>
				<span style="color:red">${errorMsgs.memberCommentContent}</span></td>
			</tr>
			<tr>
				<td>會員評價等級:</td>
				<td><input type="TEXT" name="memberCommentLevel" size="45" value="<%=memberCommentVO.getMemberCommentLevel()%>"/>
				<span style="color:red">${errorMsgs.memberCommentLevel}</span></td>
			</tr>		
		</table>

<br>
<input type="hidden" name="action" value="updateMemberComment">
<input type="hidden" name="memberCommentId" value="<%=memberCommentVO.getMemberCommentId()%>">
<input type="submit" value="修改">
<a href='<%=request.getContextPath()%>/frontend/memberComment/MemberCommentHome.jsp'><input type="button" value="取消修改"></a>
</form>
</body>
</html>