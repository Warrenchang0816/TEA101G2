<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orderMaster.model.*"%>

<%    
	OrderMasterService orderMasterSvc = new OrderMasterService();
    List<OrderMasterVO> list = orderMasterSvc.selectAllOrderMaster();
    pageContext.setAttribute("list",list);
   
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>selectAllOrderMaster</title>

<a href='<%=request.getContextPath()%>/frontend/orderMaster/orderMaster.jsp'><input type="button" value="回訂單頁面"></a>
<a href='<%=request.getContextPath()%>/frontend/orderMaster/addOrderMaster.jsp'><input type="button" value="新增訂單"></a>

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
		<th>訂單編號</th>
		<th>會員編號</th>
		<th>訂單創建日期</th>
		<th>訂單狀態</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>

<c:forEach var="orderMasterVO" items="${list}" begin="0" end="<%=list.size()%>">
	<tr>
		<td>${orderMasterVO.orderMasterId}</td>
		<td>${orderMasterVO.memberId}</td>
		<td>${orderMasterVO.orderCreatDate}</td>
		<td>${orderMasterVO.orderStatus}</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/OrderMasterServlet" style="margin-bottom: 0px;">
			    <input type="submit" value="修改">
			    <input type="hidden" name="orderMasterId"  value="${orderMasterVO.orderMasterId}">
			    <input type="hidden" name="action"	value="selectOneUpdate"></FORM>
		</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/OrderMasterServlet" style="margin-bottom: 0px;">
			    <button name="delete" value="刪除" type="submit" class="delete" onclick="javascript:return confirm('確認刪除?');">刪除</button>
			    <input type="hidden" name="orderMasterId"  value="${orderMasterVO.orderMasterId}">
			    <input type="hidden" name="action" value=deleteOrderMaster></FORM>
		</td>
	
</c:forEach>


</table>


</body>
</html>