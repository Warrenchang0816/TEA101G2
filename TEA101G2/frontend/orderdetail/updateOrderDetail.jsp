<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orderDetail.model.*"%>

<% 
	OrderDetailVO orderDetailVO = (OrderDetailVO)request.getAttribute("selectOneUpdate");
	LinkedList<String> errorMsgs = (LinkedList<String>) request.getAttribute("errorMsgs");
%>


<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>updateOrderDetail</title>
</head>

<body>

<h3>資料修改:</h3>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/OrderDetailServlet" name="form1" enctype="multipart/form-data">
<table>
			<tr>
				<td>會員最愛編碼:</td>
				<td><%=orderDetailVO.getOrderDetailId()%></td>
			</tr>
			<tr>
				<td>訂單編號:</td>
				<td><input type="TEXT" name="orderMasterId" size="45" 
					value="<%= (orderDetailVO == null)? "" : orderDetailVO.getOrderMasterId()%>"/>
					<span style="color:red"><%= (!orderDetailVO.getOrderMasterId().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr>
				<td>場地明細編號:</td>
				<td><input type="TEXT" name="spaceDetailId" size="45"
					value="<%= (orderDetailVO == null)? "" : orderDetailVO.getSpaceDetailId()%>"/>
					<span style="color:red"><%= (!orderDetailVO.getSpaceDetailId().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
		</table>

<br>
<input type="hidden" name="action" value="updateOrderDetail">
<input type="hidden" name="orderDetailId" value="<%=orderDetailVO.getOrderDetailId()%>">
<button name="update" value="修改" type="submit" class="update" onclick="javascript:return confirm('確認修改?');">送出修改</button>
<a href='<%=request.getContextPath()%>/frontend/orderDetail/orderDetail.jsp'><input type="button" value="取消修改"></a>

</FORM>



</body>
</html>