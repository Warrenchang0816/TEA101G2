<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orderDetail.model.*"%>

<%
	OrderDetailVO orderDetailVO = (OrderDetailVO)request.getAttribute("selectOneOrderDetail");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>selectOneOrderDetail</title>

<a href='<%=request.getContextPath()%>/backend/orderDetail/orderDetail.jsp'><input type="button" value="回訂單明細頁面"></a>

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
		<th>訂單明細編號</th>
		<th>訂單編號</th>
		<th>場地明細編號</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>

	
	<tr>
		<td><%=orderDetailVO.getOrderDetailId()%></td>
		<td><%=orderDetailVO.getOrderMasterId()%></td>
		<td><%=orderDetailVO.getSpaceDetailId()%></td>
		<td><%=orderDetailVO.getRentStartTime()%></td>
		<td><%=orderDetailVO.getRentEndTime()%></td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/OrderDetailServlet" style="margin-bottom: 0px;">
			    <input type="submit" value="修改">
			    <input type="hidden" name="orderDetailId"  value="<%=orderDetailVO.getOrderDetailId()%>">
			    <input type="hidden" name="action"	value="backend_SelectOneUpdate"></FORM>
		</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/OrderDetailServlet" style="margin-bottom: 0px;">
			    <button name="delete" value="刪除" type="submit" class="delete" onclick="javascript:return confirm('確認刪除?');">刪除</button>
			    <input type="hidden" name="orderDetailId"  value="<%=orderDetailVO.getOrderDetailId()%>">
			    <input type="hidden" name="action" value="backend_DeleteOrderDetail"></FORM>
		</td>
	</tr>

</table>


</body>
</html>