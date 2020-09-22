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
						<tr>
				<td>租借開始時段:</td>
				<td><input type="TEXT" name="rentStartTime" id="rentStartTime" /></td>
			</tr>
			<tr>
				<td>租借結束時段:</td>
				<td><input type="TEXT" name="rentEndTime" id="rentEndTime" /></td>
			</tr>
		</table>

<br>
<input type="hidden" name="action" value="backend_UpdateOrderDetail">
<input type="hidden" name="orderDetailId" value="<%=orderDetailVO.getOrderDetailId()%>">
<button name="update" value="修改" type="submit" class="update" onclick="javascript:return confirm('確認修改?');">送出修改</button>
<a href='<%=request.getContextPath()%>/backend/orderDetail/orderDetail.jsp'><input type="button" value="取消修改"></a>

</FORM>



</body>
  <% 
  java.sql.Timestamp rentStartTime = new java.sql.Timestamp(System.currentTimeMillis());
  java.sql.Timestamp rentEndTime = new java.sql.Timestamp(System.currentTimeMillis());
  %>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/backend/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/backend/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/backend/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>

       	 $('#rentStartTime').datetimepicker({
       	  format:'Y-m-d H:i:s',
       	  value: '<%=rentStartTime%>',
       	  onShow:function(){
       	   this.setOptions({
       	    maxDate:$('#end_dateTime').val()?$('#end_dateTime').val():false
       	   })
       	  },
       	  timepicker:true,
       	  step: 30
       	 });
       	 
       	 $('#rentEndTime').datetimepicker({
       	  format:'Y-m-d H:i:s',
          value: '<%=rentEndTime%>',
       	  onShow:function(){
       	   this.setOptions({
       	    minDate:$('#start_dateTime').val()?$('#start_dateTime').val():false
       	   })
       	  },
       	  timepicker:true,
       	  step: 30
       	 });
       	 
 </script>


</html>