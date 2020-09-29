<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.memberComm.model.*"%>

<%
	MemberCommVO memberCommVO = (MemberCommVO)request.getAttribute("selectOneMemberComm");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>selectOneMember</title>

<a href='<%=request.getContextPath()%>/backend/memberComm/memberComm.jsp'><input type="button" value="回會員評論頁面"></a>

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
		<th>會員評論編號</th>
		<th>會員(甲方)編號</th>
		<th>會員(乙方)編號</th>
		<th>場地評論</th>
		<th>場地評價等級</th>
		<th>評價日期</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>

	
	<tr>
		<td><%=memberCommVO.getMemberCommId()%></td>
		<td><%=memberCommVO.getMemberAId()%></td>
		<td><%=memberCommVO.getMemberBId()%></td>
		<td><%=memberCommVO.getComm()%></td>
		<td><%=memberCommVO.getCommLevel()%></td>
		<td><%=memberCommVO.getCommDate()%></td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MemberCommServlet" style="margin-bottom: 0px;">
			    <input type="submit" value="修改">
			    <input type="hidden" name="memberCommId"  value="<%=memberCommVO.getMemberCommId()%>">
			    <input type="hidden" name="action"	value="backend_SelectOneUpdate"></FORM>
		</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MemberCommServlet" style="margin-bottom: 0px;">
			    <button name="delete" value="刪除" type="submit" class="delete" onclick="javascript:return confirm('確認刪除?');">刪除</button>
			    <input type="hidden" name="memberCommId"  value="<%=memberCommVO.getMemberCommId()%>">
			    <input type="hidden" name="action" value="backend_DeleteMemberComm"></FORM>
		</td>
	</tr>

</table>


</body>
</html>