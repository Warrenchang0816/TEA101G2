<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orderDetail.model.*"%>

<%
	OrderDetailVO orderDetailVO = (OrderDetailVO) request.getAttribute("addOrderDetail");
 	LinkedList<String> errorMsgs = (LinkedList<String>) request.getAttribute("errorMsgs");
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>addOrderDetail</title>
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

<h3>訂單明細新增:</h3>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/OrderDetailServlet" name="form1" enctype="multipart/form-data">
<table>
			<tr>
				<td>訂單編號:</td>
				<td><input type="TEXT" name="orderMasterId" size="45" 
					value="<%= (orderDetailVO == null)? "" : orderDetailVO.getOrderMasterId()%>"/>
					<span style="color:red"><%= (errorMsgs == null)? "" : (!orderDetailVO.getOrderMasterId().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr>
				<td>場地明細編號:</td>
				<td><input type="TEXT" name="spaceDetailId" size="45"
					value="<%= (orderDetailVO == null)? "" : orderDetailVO.getSpaceDetailId()%>"/>
					<span style="color:red"><%= (errorMsgs == null)? "" : (!orderDetailVO.getSpaceDetailId().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
		</table>

<br>
<input type="hidden" name="action" value="addOrderDetail">
<button name="add" value="新增" type="submit" class="add" onclick="javascript:return confirm('確認新增?');">送出新增</button>
<a href='<%=request.getContextPath()%>/frontend/orderDetail/orderDetail.jsp'><input type="button" value="取消新增"></a>

</FORM>



</body>

</html>