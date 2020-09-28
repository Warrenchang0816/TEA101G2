<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.spaceComm.model.*"%>

<%    
    SpaceCommService spaceCommSvc = new SpaceCommService();
    List<SpaceCommVO> list = spaceCommSvc.selectAllSpaceComm();
    pageContext.setAttribute("list",list);
   
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>selectAllSpaceComm</title>

<a href='<%=request.getContextPath()%>/frontend/spaceComm/spaceComm.jsp'><input type="button" value="回場地評論頁面"></a>

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
		<th>場地評論編號</th>
		<th>場地編號</th>
		<th>會員編號</th>
		<th>場地評論</th>
		<th>場地評價等級</th>
		<th>評價日期</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>

<c:forEach var="spaceCommVO" items="${list}" begin="0" end="<%=list.size()%>">
	<tr>
		<td>${spaceCommVO.spaceCommId}</td>
		<td>${spaceCommVO.spaceId}</td>
		<td>${spaceCommVO.memberId}</td>
		<td>${spaceCommVO.comm}</td>
		<td>${spaceCommVO.commLevel}</td>
		<td>${spaceCommVO.commDate}</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/SpaceCommServlet" style="margin-bottom: 0px;">
			    <input type="submit" value="修改">
			    <input type="hidden" name="spaceCommId"  value="${spaceCommVO.spaceCommId}">
			    <input type="hidden" name="action"	value="selectOneUpdate"></FORM>
		</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/SpaceCommServlet" style="margin-bottom: 0px;">
			    <button name="delete" value="刪除" type="submit" class="delete" onclick="javascript:return confirm('確認刪除?');">刪除</button>
			    <input type="hidden" name="spaceCommId"  value="${spaceCommVO.spaceCommId}">
			    <input type="hidden" name="action" value=deleteSpaceComm></FORM>
		</td>
	
</c:forEach>


</table>


</body>
</html>