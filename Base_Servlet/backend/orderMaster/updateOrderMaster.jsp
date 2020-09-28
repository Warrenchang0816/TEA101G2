<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orderMaster.model.*"%>

<% 
	OrderMasterVO orderMasterVO = (OrderMasterVO)request.getAttribute("selectOneUpdate");
	LinkedList<String> errorMsgs = (LinkedList<String>) request.getAttribute("errorMsgs");
%>


<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>updateOrderMaster</title>
</head>

<body>

<h3>資料修改:</h3>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/OrderMasterServlet" name="form1" enctype="multipart/form-data">
<table>

			<tr>
				<td>訂單編碼:</td>
				<td><%=orderMasterVO.getOrderMasterId()%></td>
			</tr>
			<tr>
				<td>會員編號:</td>
				<td><input type="TEXT" name="memberId" size="45" 
					value="<%= (orderMasterVO == null)? "" : orderMasterVO.getMemberId()%>"/>
					<span style="color:red"><%= (errorMsgs == null)? "" : (!orderMasterVO.getMemberId().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr>
				<td>訂單創建日期:</td>
				<td><input name="orderCreatDate" id="orderCreatDate" type="text"></td>
			</tr>
			<tr>
				<td>訂單價格:</td>
				<td><input type="TEXT" name="orderAmount" size="45" 
					value="<%= (orderMasterVO == null)? "" : orderMasterVO.getOrderAmount()%>"/>
					<span style="color:red"><%= (errorMsgs == null)? "" : !(orderMasterVO.getOrderAmount() <= 0)? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr>
				<td>訂單狀態:</td>
				<td><input type="TEXT" name="orderStatus" size="45"
					value="<%= (orderMasterVO == null)? "" : orderMasterVO.getOrderStatus()%>"/>
					<span style="color:red"><%= (errorMsgs == null)? "" : (!orderMasterVO.getOrderStatus().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
		
		</table>

<br>
<input type="hidden" name="action" value="backend_UpdateOrderMaster">
<input type="hidden" name="orderMasterId" value="<%=orderMasterVO.getOrderMasterId()%>">
<button name="update" value="修改" type="submit" class="update" onclick="javascript:return confirm('確認修改?');">送出修改</button>
<a href='<%=request.getContextPath()%>/backend/orderMaster/orderMaster.jsp'><input type="button" value="取消修改"></a>

</FORM>



</body>



  <% java.sql.Date orderCreatDate = new java.sql.Date(System.currentTimeMillis()); %>


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
        $.datetimepicker.setLocale('zh');
        $('#orderCreatDate').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=orderMasterVO.getOrderCreatDate()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });

   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
        
</script>
</html>