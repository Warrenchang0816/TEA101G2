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
<input type="hidden" name="action" value="backend_AddOrderDetail">
<button name="add" value="新增" type="submit" class="add" onclick="javascript:return confirm('確認新增?');">送出新增</button>
<a href='<%=request.getContextPath()%>/backend/orderDetail/orderDetail.jsp'><input type="button" value="取消新增"></a>

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