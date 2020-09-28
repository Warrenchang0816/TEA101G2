<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.memberFavorite.model.*"%>

<% 
	MemberFavoriteVO memberFavoriteVO = (MemberFavoriteVO)request.getAttribute("selectOneUpdate");
	LinkedList<String> errorMsgs = (LinkedList<String>) request.getAttribute("errorMsgs");
%>


<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>updateMemberFavorite</title>
</head>

<body>

<h3>資料修改:</h3>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MemberFavoriteServlet" name="form1" enctype="multipart/form-data">
<table>
			<tr>
				<td>會員最愛編碼:</td>
				<td><%=memberFavoriteVO.getMemberFavoriteId()%></td>
			</tr>
			<tr>
				<td>會員編號:</td>
				<td><input type="TEXT" name="memberId" size="45" 
					value="<%= (memberFavoriteVO == null)? "" : memberFavoriteVO.getMemberId()%>"/>
					<span style="color:red"><%= (!memberFavoriteVO.getMemberId().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr>
				<td>場地編號:</td>
				<td><input type="TEXT" name="spaceId" size="45"
					value="<%= (memberFavoriteVO == null)? "" : memberFavoriteVO.getSpaceId()%>"/>
					<span style="color:red"><%= (!memberFavoriteVO.getSpaceId().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
		</table>

<br>
<input type="hidden" name="action" value="backend_UpdateMemberFavorite">
<input type="hidden" name="memberFavoriteId" value="<%=memberFavoriteVO.getMemberFavoriteId()%>">
<button name="update" value="修改" type="submit" class="update" onclick="javascript:return confirm('確認修改?');">送出修改</button>
<a href='<%=request.getContextPath()%>/backend/memberFavorite/memberFavorite.jsp'><input type="button" value="取消修改"></a>

</FORM>



</body>
</html>