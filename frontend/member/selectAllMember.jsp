<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>

<%
	MemberService memberSvc = new MemberService();
    List<MemberVO> list = memberSvc.selectAllMember();
    pageContext.setAttribute("list",list);
   
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>selectAllMember</title>

<a href='<%=request.getContextPath()%>/frontend/member/member.jsp'><input type="button" value="回會員頁面"></a>

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

<c:forEach var="memberVO" items="${list}" begin="0" end="<%=list.size()%>">
<% Base64.Encoder encode = Base64.getEncoder();%>
	<tr>
		<td><img src="data:image/png;base64,<%=encode.encodeToString(((MemberVO)pageContext.getAttribute("memberVO")).getMemberPhoto())%>" class="perview"/></td>
		<td>${memberVO.memberId}</td>
		<td>${memberVO.memberAccount}</td>
		<td>${memberVO.memberPassword}</td>
		<td>${memberVO.memberName}</td>
		<td>${memberVO.memberNickname}</td>
		<td>${memberVO.memberEmail}</td>
		<td>${memberVO.memberPhone}</td>
		<td>${memberVO.memberAdress}</td>
		<td>${memberVO.memberBirth}</td>
		<td>${memberVO.memberSex}</td>
		<td>${memberVO.memberCountry}</td>
		<td>${memberVO.memberSignupDate}</td>
		<td>${memberVO.memberAuth}</td>
		<td>${memberVO.memberStatus}</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MemberServlet" style="margin-bottom: 0px;">
			    <input type="submit" value="修改">
			    <input type="hidden" name="memberId"  value="${memberVO.memberId}">
			    <input type="hidden" name="action"	value="selectOneUpdate"></FORM>
		</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MemberServlet" style="margin-bottom: 0px;">
			    <button name="delete" value="刪除" type="submit" class="delete" onclick="javascript:return confirm('確認刪除?');">刪除</button>
			    <input type="hidden" name="memberId"  value="${memberVO.memberId}">
			    <input type="hidden" name="action" value=deleteMember></FORM>
		</td>
	
</c:forEach>


</table>


</body>
</html>