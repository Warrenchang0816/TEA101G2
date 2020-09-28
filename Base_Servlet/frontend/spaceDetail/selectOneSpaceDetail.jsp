<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.spaceDetail.model.*"%>

<% SpaceDetailVO spaceDetailVO = (SpaceDetailVO)request.getAttribute("selectOneSpaceDetail"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>selectOneSpaceDetail</title>

<a href='<%=request.getContextPath()%>/frontend/spaceDetail/spaceDetail.jsp'><input type="button" value="回場地明細頁面"></a>

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
		<th>場地評論編號</th>
		<th>場地編號</th>
		<td>場地可出租日期</td>
		<td>場地可出租的起始時段</td>
		<td>場地可出租的結束時段</td>
		<td>場地時段價格</td>
		<th>修改</th>
		<th>刪除</th>
	</tr>

	
	<tr>
		<td><%=spaceDetailVO.getSpaceDetailId()%></td>
		<td><%=spaceDetailVO.getSpaceId()%></td>
		<td><%=spaceDetailVO.getSpaceDetailFreeDate()%></td>
		<td><%=spaceDetailVO.getSpaceDetailFreeTimeStart()%></td>
		<td><%=spaceDetailVO.getSpaceDetailFreeTimeEnd()%></td>
		<td><%=spaceDetailVO.getSpaceDetailCharge()%></td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/SpaceDetailServlet" style="margin-bottom: 0px;">
			    <input type="submit" value="修改">
			    <input type="hidden" name="spaceDetailId"  value="<%=spaceDetailVO.getSpaceDetailId()%>">
			    <input type="hidden" name="action"	value="selectOneUpdate"></FORM>
		</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/SpaceDetailServlet" style="margin-bottom: 0px;">
			    <button name="delete" value="刪除" type="submit" class="delete" onclick="javascript:return confirm('確認刪除?');">刪除</button>
			    <input type="hidden" name="spaceDetailId"  value="<%=spaceDetailVO.getSpaceDetailId()%>">
			    <input type="hidden" name="action" value=deleteSpaceDetail></FORM>
		</td>
	</tr>

</table>


</body>
</html>