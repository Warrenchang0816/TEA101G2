<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orderMaster.model.*"%>

<%
	OrderMasterVO orderMasterVO = (OrderMasterVO) request.getAttribute("selectOneOrderMaster");
 	LinkedList<String> errorMsgs = (LinkedList<String>) request.getAttribute("errorMsgs");
%>
    
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>OrderMaster</title>
<h1>訂單頁面</h1>
</head>
<a href='<%=request.getContextPath()%>/backend/BigSpace.jsp'><input type="button" value="後台首頁"></a>
<body>

	<table>
		<ul>
			<li>
				<form method="post" action="<%=request.getContextPath()%>/OrderMasterServlet">
					<input type="text" name="orderMasterId" placeholder="請輸入訂單編號"
						value="<%= (orderMasterVO == null)? "" : orderMasterVO.getOrderMasterId()%>"/>
					<input type="hidden" name="action" value="backend_SelectOneOrderMaster">
					<input type="submit" value="查詢">
					<span style="color:red"><%= (errorMsgs == null)? "" : "  " + errorMsgs.poll()%></span>
				</form>
			</li>
			
			<jsp:useBean id="orderMasterSvc" scope="page" class="com.orderMaster.model.OrderMasterService" />
			<li>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/OrderMasterServlet">
					<b>選擇訂單編號:</b> 
					<select size="1" name="orderMasterId">
						<c:forEach var="orderMasterVO" items="<%= orderMasterSvc.selectAllOrderMaster() %>">
							<option value="${orderMasterVO.orderMasterId}">${orderMasterVO.orderMasterId}
						</c:forEach>
					</select> <input type="hidden" name="action" value="backend_SelectOneOrderMaster">
					<input type="submit" value="查詢">
				</FORM>
			</li>

			<li>
				<a href='<%=request.getContextPath()%>/backend/orderMaster/addOrderMaster.jsp'><input type="button" value="新增"></a>
			</li>

			<li>
				<a href='<%=request.getContextPath()%>/backend/orderMaster/selectAllOrderMaster.jsp'><input type="button" value="列表"></a>
			</li>
		</ul>
	</table>
	
<a href='<%=request.getContextPath()%>/backend/orderDetail/orderDetail.jsp'><input type="button" value="訂單明細"></a>

<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
<script src="<%=request.getContextPath()%>/backend/jquery.js"></script>

</body>
</html>