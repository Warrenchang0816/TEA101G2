<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>

<%
	EmpVO empVO = (EmpVO) request.getAttribute("addEmp");
	LinkedList<String> errorMsgs = (LinkedList<String>) request.getAttribute("errorMsgs");
	Base64.Encoder encode = Base64.getEncoder();
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>addEmp</title>
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

<style>
    img.preview {
      width: 200px;
    }
    #preview {
      border: 1px solid lightgray;
      display: inline-block;
      width: 100px;
      min-height: 150px;
      position: relative;
    }

    #preview span.text1 {
      position: absolute;
      display: inline-block;
      left: 50%;
      top: 50%;
      transform: translate(-50%, -50%);
      z-index: -1;
      color: lightgray;
    }

    #preview img.preview_img {
      width: 100%;
    }
    #preview.-on {
      border: 0px solid lightgray;
      display: inline-block;
      width: 100px;
      min-height: 150px;
      position: relative;
    }
</style>

</head>

<body>

<h3>資料新增:</h3>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/EmpServlet" name="form1" enctype="multipart/form-data">
<table>
			
			<tr><div class= "preview" id="preview"><span class="text1">預覽圖</span></div>
			<tr>
				<td>員工照片:</td>
				<td><input type="file" name="empPhoto" class="empPhoto"/></td>
			</tr>
			<tr>
				<td>員工帳號:</td>
				<td><input type="TEXT" name="empAccount" size="45" 
					value="<%= (empVO == null)? "" : empVO.getEmpAccount()%>"/>
					<span style="color:red"><%= (errorMsgs == null)? "" : (!empVO.getEmpAccount().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr>
				<td>員工密碼:</td>
				<td><input type="TEXT" name="empPassword" size="45" 
					value="<%= (empVO == null)? "" : empVO.getEmpPassword()%>"/>
					<span style="color:red"><%= (errorMsgs == null)? "" : (!empVO.getEmpPassword().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr>
				<td>員工姓名:</td>
				<td><input type="TEXT" name="empName" size="45" 
					value="<%= (empVO == null)? "" : empVO.getEmpName()%>"/>
					<span style="color:red"><%= (errorMsgs == null)? "" : (!empVO.getEmpName().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr>
				<td>員工名稱:</td>
				<td><input type="TEXT" name="empNickname" size="45" 
					value="<%= (empVO == null)? "" : empVO.getEmpNickname()%>"/>
					<span style="color:red"><%= (errorMsgs == null)? "" : (!empVO.getEmpNickname().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr>
				<td>員工email:</td>
				<td><input type="TEXT" name="empEmail" size="45" 
					value="<%= (empVO == null)? "" : empVO.getEmpEmail()%>"/>
					<span style="color:red"><%= (errorMsgs == null)? "" : (!empVO.getEmpEmail().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr>
				<td>員工電話:</td>
				<td><input type="TEXT" name="empPhone" size="45" 
					value="<%= (empVO == null)? "" : empVO.getEmpPhone()%>"/>
					<span style="color:red"><%= (errorMsgs == null)? "" : (!empVO.getEmpPhone().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr>
				<td>員工聯絡地址:</td>
				<td><input type="TEXT" name="empAddress" size="45" 
					value="<%= (empVO == null)? "" : empVO.getEmpAddress()%>"/>
					<span style="color:red"><%= (errorMsgs == null)? "" : (!empVO.getEmpAddress().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr>
				<td>員工生日:</td>
				<td><input type="TEXT" name="empBirth" id="empBirth" /></td>
			</tr>
			<tr>
				<td>員工性別:</td>
				<td><input type="TEXT" name="empSex" size="45" 
					value="<%= (empVO == null)? "" : empVO.getEmpSex()%>"/>
					<span style="color:red"><%= (errorMsgs == null)? "" : (!empVO.getEmpSex().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr>
				<td>員工國籍:</td>
				<td><input type="TEXT" name="empCountry" size="45" 
					value="<%= (empVO == null)? "" : empVO.getEmpCountry()%>"/>
					<span style="color:red"><%= (errorMsgs == null)? "" : (!empVO.getEmpCountry().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr>
				<td>員工到職日:</td>
				<td><input name="empHireDate" id="empHireDate" type="text"></td>
			</tr>
			<tr>
				<td>員工職稱:</td>
				<td><input type="TEXT" name="empJob" size="45" 
					value="<%= (empVO == null)? "" : empVO.getEmpJob()%>"/>
					<span style="color:red"><%= (errorMsgs == null)? "" : (!empVO.getEmpJob().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr>
				<td>員工權限:</td>
				<td><input name="empAuth" type="text" 
					value="<%= (empVO == null)? "" : empVO.getEmpAuth()%>"/>
					<span style="color:red"><%= (errorMsgs == null)? "" : !(empVO.getEmpAuth() <= 0 || empVO.getEmpAuth() > 5)? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr>
				<td>員工在職狀態:</td>
				<td><input type="TEXT" name="empStatus" size="45" 
					value="<%= (empVO == null)? "" : empVO.getEmpStatus()%>"/>
					<span style="color:red"><%= (errorMsgs == null)? "" : (!empVO.getEmpStatus().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>

		</table>

<br>
<input type="hidden" name="action" value="addEmp">
<button name="add" value="新增" type="submit" class="add" onclick="javascript:return confirm('確認新增?');">送出新增</button>
<a href='<%=request.getContextPath()%>/frontend/emp/emp.jsp'><input type="button" value="取消新增"></a>

</FORM>

<script src="<%=request.getContextPath()%>/frontend/jquery.js"></script>
<script src="<%=request.getContextPath()%>/frontend/emp/emp.js"></script>

</body>

  <% 
  java.sql.Date empBirth = new java.sql.Date(System.currentTimeMillis());
  java.sql.Date empHireDate = new java.sql.Date(System.currentTimeMillis());
  %>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/frontend/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/frontend/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/frontend/datetimepicker/jquery.datetimepicker.full.js"></script>

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
        $('#empBirth').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=empBirth%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        $.datetimepicker.setLocale('zh');
        $('#empHireDate').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=empHireDate%>', // value:   new Date(),
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