<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>

<%
	MemberVO memberVO = (MemberVO) request.getAttribute("selectOneUpdate");
	LinkedList<String> errorMsgs = (LinkedList<String>) request.getAttribute("errorMsgs");
	Base64.Encoder encode = Base64.getEncoder();
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>updateMember</title>

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

<h3>資料修改:</h3>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MemberServlet" name="form1" enctype="multipart/form-data">
<table>

			<jsp:useBean id="memberDAO" scope="page" class="com.member.model.MemberDAO" />
			<tr><div class= "preview" id="preview">
			<img src="data:image/png;base64,<%= encode.encodeToString(memberDAO.selectOne(memberVO.getMemberId()).getMemberPhoto())%>" class ="preview">
			<span class="text1">預覽圖</span></div>
			<tr>
				<td>員工圖片:</td>
				<td><input type="file" name="memberPhoto" class="memberPhoto"/>
				<span style="color:red"><%= !(memberVO.getMemberPhoto() == null)? "" : "  " + errorMsgs.poll()%></span></td></td>
			</tr>
			<tr>
				<td>員工編碼:</td>
				<td><%=memberVO.getMemberId()%></td>
			</tr>
			<tr>
				<td>會員帳號:</td>
				<td><input type="TEXT" name="memberAccount" size="45" 
					value="<%= (memberVO == null)? "" : memberVO.getMemberAccount()%>"/>
					<span style="color:red"><%= (!memberVO.getMemberAccount().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr>
				<td>會員密碼:</td>
				<td><input type="TEXT" name="memberPassword" size="45" 
					value="<%= (memberVO == null)? "" : memberVO.getMemberPassword()%>"/>
					<span style="color:red"><%= (!memberVO.getMemberPassword().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr>
				<td>會員姓名:</td>
				<td><input type="TEXT" name="memberName" size="45" 
					value="<%= (memberVO == null)? "" : memberVO.getMemberName()%>"/>
					<span style="color:red"><%= (!memberVO.getMemberName().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr>
				<td>會員名稱:</td>
				<td><input type="TEXT" name="memberNickname" size="45" 
					value="<%= (memberVO == null)? "" : memberVO.getMemberNickname()%>"/>
					<span style="color:red"><%= (!memberVO.getMemberNickname().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr>
				<td>會員email:</td>
				<td><input type="TEXT" name="memberEmail" size="45" 
					value="<%= (memberVO == null)? "" : memberVO.getMemberEmail()%>"/>
					<span style="color:red"><%= (!memberVO.getMemberEmail().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr>
				<td>會員電話:</td>
				<td><input type="TEXT" name="memberPhone" size="45" 
					value="<%= (memberVO == null)? "" : memberVO.getMemberPhone()%>"/>
					<span style="color:red"><%= (!memberVO.getMemberPhone().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr>
				<td>會員聯絡地址:</td>
				<td><input type="TEXT" name="memberAddress" size="45" 
					value="<%= (memberVO == null)? "" : memberVO.getMemberAddress()%>"/>
					<span style="color:red"><%= (!memberVO.getMemberAddress().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr>
				<td>會員生日:</td>
				<td><input type="TEXT" name="memberBirth" id="memberBirth" /></td>
			</tr>
			<tr>
				<td>會員性別:</td>
				<td><input type="TEXT" name="memberSex" size="45" 
					value="<%= (memberVO == null)? "" : memberVO.getMemberSex()%>"/>
					<span style="color:red"><%= (!memberVO.getMemberSex().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr>
				<td>會員國籍:</td>
				<td><input type="TEXT" name="memberCountry" size="45" 
					value="<%= (memberVO == null)? "" : memberVO.getMemberCountry()%>"/>
					<span style="color:red"><%= (!memberVO.getMemberCountry().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr>
				<td>會員註冊日:</td>
				<td><input name="memberSignupDate" id="memberSignupDate" type="text"></td>
			</tr>
			<tr>
				<td>會員權限:</td>
				<td><input name="memberAuth" type="text" 
					value="<%= (memberVO == null)? "" : memberVO.getMemberAuth()%>"/>
					<span style="color:red"><%= !(memberVO.getMemberAuth() <= 0 || memberVO.getMemberAuth() > 5)? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr>
				<td>會員帳號狀態:</td>
				<td><input type="TEXT" name="memberStatus" size="45" 
					value="<%= (memberVO == null)? "" : memberVO.getMemberStatus()%>"/>
					<span style="color:red"><%= (!memberVO.getMemberStatus().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
		</table>

<br>
<input type="hidden" name="action" value="backend_UpdateMember">
<input type="hidden" name="memberId" value="<%=memberVO.getMemberId()%>">
<button name="update" value="修改" type="submit" class="update" onclick="javascript:return confirm('確認修改?');">送出修改</button>
<a href='<%=request.getContextPath()%>/backend/member/member.jsp'><input type="button" value="取消修改"></a>

</FORM>

<script src="<%=request.getContextPath()%>/backend/jquery.js"></script>
<script src="<%=request.getContextPath()%>/backend/member/member.js"></script>


</body>

  <% 
  java.sql.Date memberBirth = new java.sql.Date(System.currentTimeMillis());
  java.sql.Date memberSignupDate = new java.sql.Date(System.currentTimeMillis());
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
$('#memberBirth').datetimepicker({
   theme: '',              //theme: 'dark',
   timepicker:false,       //timepicker:true,
   step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
   format:'Y-m-d',         //format:'Y-m-d H:i:s',
   value: '<%=memberBirth%>', // value:   new Date(),
   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
   //startDate:	            '2017/07/10',  // 起始日
   //minDate:               '-1970-01-01', // 去除今日(不含)之前
   //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
});
$.datetimepicker.setLocale('zh');
$('#memberSignupDate').datetimepicker({
   theme: '',              //theme: 'dark',
   timepicker:false,       //timepicker:true,
   step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
   format:'Y-m-d',         //format:'Y-m-d H:i:s',
   value: '<%=memberSignupDate%>', // value:   new Date(),
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