<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.formList.model.*"%>

<%
	FormListVO formListVO = (FormListVO) request.getAttribute("addFormList");
 	LinkedList<String> errorMsgs = (LinkedList<String>) request.getAttribute("errorMsgs");
 	Base64.Encoder encode = Base64.getEncoder();
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>addSupplyList</title>

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

<h3>新增客服表單:</h3>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FormListServlet" name="form1" enctype="multipart/form-data">
<table>

			<tr>
				<td>會員編號:</td>
				<td><input type="TEXT" name="memberId" size="45"
					value="<%= (formListVO == null)? "" : formListVO.getMemberId()%>"/>
					<span style="color:red"><%= (errorMsgs == null)? "" : (!formListVO.getMemberId().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr>
				<td>員工編號:</td>
				<td><input type="TEXT" name="empId" size="45"
					value="<%= (formListVO == null)? "" : formListVO.getEmpId()%>"/>
					<span style="color:red"><%= (errorMsgs == null)? "" : (!formListVO.getEmpId().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr>
				<td>表單申請日期:</td>
				<td><input type="TEXT" name="formListCreateDate" id="formListCreateDate" /></td>
			</tr>
			<tr>
				<td>表單類型:</td>
				<td><input type="TEXT" name="formListType" size="45"
					value="<%= (formListVO == null)? "" : formListVO.getFormListType()%>"/>
					<span style="color:red"><%= (errorMsgs == null)? "" : (!formListVO.getFormListType().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr>
				<td>表單主旨:</td>
				<td><input type="TEXT" name="formListTitle" size="45"
					value="<%= (formListVO == null)? "" : formListVO.getFormListTitle()%>"/>
					<span style="color:red"><%= (errorMsgs == null)? "" : (!formListVO.getFormListTitle().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr>
				<td>表單內容:</td>
				<td><input type="TEXT" name="formListContext" size="45"
					value="<%= (formListVO == null)? "" : formListVO.getFormListContext()%>"/>
					<span style="color:red"><%= (errorMsgs == null)? "" : (!formListVO.getFormListContext().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr>
				<td>表單圖片:</td>
				<td><input type="file" name="formListFile" class="formListFile"/>
					<span style="color:red"><%= (errorMsgs == null)? "" : !(formListVO.getFormListFile() == null)? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr><div class= "preview" id="preview"><span class="text1">預覽圖</span></div>
			<tr>
				<td>表單狀態:</td>
				<td><input type="TEXT" name="formListStatus" size="45"
					value="<%= (formListVO == null)? "" : formListVO.getFormListStatus()%>"/>
					<span style="color:red"><%= (errorMsgs == null)? "" : (!formListVO.getFormListStatus().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			

		</table>

<br>
<input type="hidden" name="action" value="backend_AddFormList">
<button name="add" value="新增" type="submit" class="add" onclick="javascript:return confirm('確認新增?');">送出新增</button>
<a href='<%=request.getContextPath()%>/backend/formList/formList.jsp'><input type="button" value="取消新增"></a>

</FORM>

<script src="<%=request.getContextPath()%>/backend/jquery.js"></script>
<script src="<%=request.getContextPath()%>/backend/formList/formList.js"></script>

</body>
 <% 
  java.sql.Date formListCreateDate = new java.sql.Date(System.currentTimeMillis());
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
        $('#formListCreateDate').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=formListCreateDate%>', // value:   new Date(),
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