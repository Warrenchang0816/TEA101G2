<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.memberFavorite.model.*"%>

<%
	MemberFavoriteVO memberFavoriteVO = (MemberFavoriteVO) request.getAttribute("addMemberFavorite");
 	LinkedList<String> errorMsgs = (LinkedList<String>) request.getAttribute("errorMsgs");
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
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

<h3>評論新增:</h3>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MemberFavoriteServlet" name="form1" enctype="multipart/form-data">
<table>
			<tr>
				<td>會員編號:</td>
				<td><input type="TEXT" name="memberId" size="45" 
					value="<%= (memberFavoriteVO == null)? "" : memberFavoriteVO.getSpaceId()%>"/>
					<span style="color:red"><%= (errorMsgs == null)? "" : (!memberFavoriteVO.getMemberId().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr>
				<td>場地編號:</td>
				<td><input type="TEXT" name="spaceId" size="45"
					value="<%= (memberFavoriteVO == null)? "" : memberFavoriteVO.getMemberId()%>"/>
					<span style="color:red"><%= (errorMsgs == null)? "" : (!memberFavoriteVO.getSpaceId().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
		</table>

<br>
<input type="hidden" name="action" value="backend_AddMemberFavorite">
<button name="add" value="新增" type="submit" class="add" onclick="javascript:return confirm('確認新增?');">送出新增</button>
<a href='<%=request.getContextPath()%>/backend/memberFavorite/memberFavorite.jsp'><input type="button" value="取消新增"></a>

</FORM>



</body>

</html>