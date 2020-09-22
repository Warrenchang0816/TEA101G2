<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orderDetail.model.*"%>

<%
	OrderDetailVO orderDetailVO = (OrderDetailVO) request.getAttribute("selectOneOrderDetail");
	LinkedList<String> errorMsgs = (LinkedList<String>) request.getAttribute("errorMsgs");
%>
    
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>OrderDetail</title>
<h1>訂單明細頁面</h1>
</head>

<body>
<a href='<%=request.getContextPath()%>/backend/orderMaster/orderMaster.jsp'><input type="button" value="回訂單頁面"></a>
	<table>
		<ul>
			<li>
				<form method="post" action="<%=request.getContextPath()%>/OrderDetailServlet">
					<input type="text" name="orderDetailId" placeholder="請輸入訂單明細編號"
						value="<%= (orderDetailVO == null)? "" : orderDetailVO.getOrderDetailId()%>"/>
					<input type="hidden" name="action" value="backend_SelectOneOrderDetail">
					<input type="submit" value="查詢">
					<span style="color:red"><%= (errorMsgs == null)? "" : "  " + errorMsgs.poll()%></span>
				</form>
			</li>
			
			<jsp:useBean id="orderDetailSvc" scope="page" class="com.orderDetail.model.OrderDetailService" />
			<li>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/OrderDetailServlet">
					<b>選擇場地編號:</b> 
					<select size="1" name="orderDetailId">
						<c:forEach var="orderDetailVO" items="<%= orderDetailSvc.selectAllOrderDetail() %>">
							<option value="${orderDetailVO.orderDetailId}">${orderDetailVO.orderDetailId}
						</c:forEach>
					</select> <input type="hidden" name="action" value="backend_SelectOneOrderDetail">
					<input type="submit" value="查詢">
				</FORM>
			</li>

			<li>
				<a href='<%=request.getContextPath()%>/backend/orderDetail/addOrderDetail.jsp'><input type="button" value="新增"></a>
			</li>

			<li>
				<a href='<%=request.getContextPath()%>/backend/orderDetail/selectAllOrderDetail.jsp'><input type="button" value="列表"></a>
			</li>
		</ul>
	</table>


<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
<script src="<%=request.getContextPath()%>/backend/jquery.js"></script>

</body>
</html>