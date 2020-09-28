<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>

<%
	MemberVO memberVO= (MemberVO)request.getAttribute("selectOneMember");
	Base64.Encoder encode = Base64.getEncoder();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>selectOneMember</title>

<a href='<%=request.getContextPath()%>/backend/member/member.jsp'><input type="button" value="回員工頁面"></a>

<style>
    img {
      width: 200px;
    }
</style>
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
		<th>會員圖片</th>
		<th>會員帳號</th>
		<th>會員密碼</th>
		<th>會員姓名</th>
		<th>會員名稱</th>
		<th>會員email</th>
		<th>會員電話</th>
		<th>會員聯絡地址</th>
		<th>會員生日</th>
		<th>會員性別</th>
		<th>會員國籍</th>
		<th>會員註冊日</th>
		<th>會員權限</th>
		<th>會員帳號狀態</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>	
	<tr>
		<td><img src="data:image/png;base64,<%=encode.encodeToString(memberVO.getMemberPhoto())%>" class="perview"/></td>
		<td><%=memberVO.getMemberId()%></td>
		<td><%=memberVO.getMemberAccount()%></td>
		<td><%=memberVO.getMemberPassword()%></td>
		<td><%=memberVO.getMemberName()%></td>
		<td><%=memberVO.getMemberNickname()%></td>
		<td><%=memberVO.getMemberEmail()%></td>
		<td><%=memberVO.getMemberPhone()%></td>
		<td><%=memberVO.getMemberAddress()%></td>
		<td><%=memberVO.getMemberBirth()%></td>
		<td><%=memberVO.getMemberSex()%></td>
		<td><%=memberVO.getMemberCountry()%></td>
		<td><%=memberVO.getMemberSignupDate()%></td>
		<td><%=memberVO.getMemberAuth()%></td>
		<td><%=memberVO.getMemberStatus()%></td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MemberServlet" style="margin-bottom: 0px;">
			    <input type="submit" value="修改">
			    <input type="hidden" name="memberId"  value="<%=memberVO.getMemberId()%>">
			    <input type="hidden" name="action"	value="backend_SelectOneUpdate"></FORM>
		</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MemberServlet" style="margin-bottom: 0px;">
			    <button name="delete" value="刪除" type="submit" class="delete" onclick="javascript:return confirm('確認刪除?');">刪除</button>
			    <input type="hidden" name="memberId"  value="<%=memberVO.getMemberId()%>">
			    <input type="hidden" name="action" value="backend_DeleteMember"></FORM>
		</td>
	
	</tr>

</table>


</body>
</html>