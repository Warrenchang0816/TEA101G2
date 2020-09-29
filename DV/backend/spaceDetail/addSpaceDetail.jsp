<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.spaceDetail.model.*"%>

<%
	SpaceDetailVO spaceDetailVO = (SpaceDetailVO) request.getAttribute("addSpaceDetail");
	LinkedList<String> errorMsgs = (LinkedList<String>) request.getAttribute("errorMsgs");
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>addSpaceDetail</title>
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

<h3>評論場地時段:</h3>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/SpaceDetailServlet" name="form1" enctype="multipart/form-data">
<table>

			<tr>
				<td>場地編號:</td>
				<td><input type="TEXT" name="spaceId" size="45"
					value="<%= (spaceDetailVO == null)? "" : spaceDetailVO.getSpaceId()%>"/>
					<span style="color:red"><%= (errorMsgs == null)? "" : (!spaceDetailVO.getSpaceId().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr>
				<td>場地可出租日期:</td>
				<td><input type="TEXT" name="spaceDetailFreeDate" id="spaceDetailFreeDate" /></td>
			</tr>
			<tr>
				<td>場地可出租的起始時段:</td>
				<td><input type="TEXT" name="spaceDetailFreeTimeStart" id="start_dateTime" /></td>
			</tr>
			<tr>
				<td>場地可出租的結束時段:</td>
				<td><input type="TEXT" name="spaceDetailFreeTimeEnd" id="end_dateTime" /></td>
			</tr>
			<tr>
				<td>場地時段價格:</td>
				<td><input type="TEXT" name="spaceDetailCharge" size="45" 
					value="<%= (spaceDetailVO == null)? "" : spaceDetailVO.getSpaceDetailCharge()%>"/>
					<span style="color:red"><%= (errorMsgs == null)? "" : !(spaceDetailVO.getSpaceDetailCharge() <= 0)? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>

		</table>

<br>
<input type="hidden" name="action" value="backend_AddSpaceDetail">
<button name="add" value="新增" type="submit" class="add" onclick="javascript:return confirm('確認新增?');">送出新增</button>
<a href='<%=request.getContextPath()%>/backend/spaceDetail/spaceDetail.jsp'><input type="button" value="取消新增"></a>

</FORM>



</body>



  <% 
  java.sql.Date spaceDetailFreeDate = new java.sql.Date(System.currentTimeMillis());
  java.sql.Timestamp spaceDetailFreeTimeStart = new java.sql.Timestamp(System.currentTimeMillis());
  java.sql.Timestamp spaceDetailFreeTimeEnd = new java.sql.Timestamp(System.currentTimeMillis());
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
        $.datetimepicker.setLocale('zh');
        $('#spaceDetailFreeDate').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=spaceDetailFreeDate%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });

       	 $('#start_dateTime').datetimepicker({
       	  format:'Y-m-d H:i:s',
       	  value: '<%=spaceDetailFreeTimeStart%>',
       	  onShow:function(){
       	   this.setOptions({
       	    maxDate:$('#end_dateTime').val()?$('#end_dateTime').val():false
       	   })
       	  },
       	  timepicker:true,
       	  step: 30
       	 });
       	 
       	 $('#end_dateTime').datetimepicker({
       	  format:'Y-m-d H:i:s',
          value: '<%=spaceDetailFreeTimeEnd%>',
       	  onShow:function(){
       	   this.setOptions({
       	    minDate:$('#start_dateTime').val()?$('#start_dateTime').val():false
       	   })
       	  },
       	  timepicker:true,
       	  step: 30
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