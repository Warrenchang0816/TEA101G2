<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.space.model.*"%>

<%
    SpaceService spaceSvc = new SpaceService();
    List<SpaceVO> list = spaceSvc.selectAllSpace();
    pageContext.setAttribute("list",list);
   
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>selectAllSpace</title>

<a href='<%=request.getContextPath()%>/frontend/space/space.jsp'><input type="button" value="回場地頁面"></a>

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
		<th>場地編號</th>
		<th>會員編號</th>
		<th>員工編號</th>
		<th>場地地址</th>
		<th>場地名稱</th>
		<th>場地類型</th>
		<th>場地設備</th>
		<th>場地容納人數</th>
		<th>場地規範</th>
		<th>退費規定</th>
		<th>場地狀態</th>
		<th>場地申請日期</th>
		<th>場地上架日期</th>
		<th>場地下架日期</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>

<c:forEach var="spaceVO" items="${list}" begin="0" end="<%=list.size()%>">
	<tr>
		<td>${spaceVO.spaceId}</td>
		<td>${spaceVO.memberId}</td>
		<td>${spaceVO.empId}</td>
		<td>${spaceVO.spaceAdress}</td>
		<td>${spaceVO.spaceName}</td>
		<td>${spaceVO.spaceType}</td>
		<td>${spaceVO.spaceEqument}</td>
		<td>${spaceVO.spaceContain}</td>
		<td>${spaceVO.spaceRule}</td>
		<td>${spaceVO.spaceRefund}</td>
		<td>${spaceVO.spaceStatus}</td>
		<td>${spaceVO.spaceSignupDate}</td>
		<td>${spaceVO.spaceOnsaleDate}</td>
		<td>${spaceVO.spaceOffsaleDate}</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/SpaceServlet" style="margin-bottom: 0px;">
			    <input type="submit" value="修改">
			    <input type="hidden" name="spaceId"  value="${spaceVO.spaceId}">
			    <input type="hidden" name="action"	value="selectOneUpdate"></FORM>
		</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/SpaceServlet" style="margin-bottom: 0px;">
			    <button name="delete" value="刪除" type="submit" class="delete" onclick="javascript:return confirm('確認刪除?');">刪除</button>
			    <input type="hidden" name="spaceId"  value="${spaceVO.spaceId}">
			    <input type="hidden" name="action" value=deleteSpace></FORM>
		</td>
	
</c:forEach>


</table>


</body>
</html>