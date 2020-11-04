<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.spaceDetail.model.*"%>
<%@ page import="com.orderDetail.model.*"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	List<SpaceDetailVO> list = (List<SpaceDetailVO>)request.getAttribute("spaceDetailIdList");
	SpaceDetailVO sdVO = (SpaceDetailVO) request.getAttribute("spaceDetailVO");
	String spaceId = request.getAttribute("spaceId").toString();
	//大吳親授瞞天過海欺負JS日期轉換法雙重奏(搞定場地時間&已被預約時間)ver.20201020
	List<SpaceDetailVO> calendarlist = (List<SpaceDetailVO>)request.getAttribute("calendarList");
	List<OrderDetailVO> odlist = (List<OrderDetailVO>)request.getAttribute("odlist");
	for(SpaceDetailVO spaceDetailVO : calendarlist) {
		java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		spaceDetailVO.setSpaceDetailId(df.format(spaceDetailVO.getSpaceDetailFreeTimeStart()));
		spaceDetailVO.setSpaceId(df.format(spaceDetailVO.getSpaceDetailFreeTimeEnd()));
		System.out.println(spaceDetailVO);
	}
	for(OrderDetailVO orderDetailVO : odlist) {
		java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		orderDetailVO.setOrderDetailId(df.format(orderDetailVO.getRentStartTime()));
		orderDetailVO.setOrderMasterId(df.format(orderDetailVO.getRentEndTime()));
		System.out.println(orderDetailVO);
	}
	SpaceDetailService spaceDetailSvc = new SpaceDetailService();
	OrderDetailService ods = new OrderDetailService();
	pageContext.setAttribute("calendarlist", calendarlist);
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("odlist", odlist);
    pageContext.setAttribute("spaceId", spaceId);
    pageContext.setAttribute("ods", ods);
    LinkedList<String> errorMessages = (LinkedList<String>) request.getAttribute("errorMessages");
%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>所有場地明細</title>

<!-- GOOGLE WEB FONT -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">

<!-- BASE CSS -->
    <link href="<%=request.getContextPath()%>/plugins/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/plugins/css/style.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/plugins/css/vendors.css" rel="stylesheet">

<!-- Your custom styles -->
    <link href="<%=request.getContextPath()%>/plugins/css/custom.css" rel="stylesheet" type="text/css">

<!-- Table -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- jQuery v3.4.1 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Moment.js v2.20.0 -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.20.0/moment.min.js"></script>

<!-- FullCalendar v3.8.1 -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.8.1/fullcalendar.min.css" rel="stylesheet"  />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.8.1/fullcalendar.print.css" rel="stylesheet" media="print"></link>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.8.1/fullcalendar.min.js"></script>
      	
</head>

<body>

<div id="page">
<jsp:include page="/frontend/other/header.jsp" />		
  
  <main>
		
		<section class="hero_in hotels">
			<div class="wrapper">
				<div class="container">
					<h1 class="fadeInUp"><span></span>編輯場地明細</h1>
				</div>
			</div>
		</section>
		<!--/hero_in-->
  <!-- calendar -->
  <div id="example"></div>
  <table class="table">
    <thead>
	  <tr>
		<th>場地開放日期</th>
		<th>場地開放起始時間</th>
		<th>場地開放結束時間</th>
		<th>場地租借費用(每小時)</th>
	  </tr>
    </thead>
	<c:forEach var="spaceDetailVO" items="${list}" varStatus="status">
	<tbody>	
		<tr>
			<td>${spaceDetailVO.spaceDetailFreeDate}</td>
			<td>${spaceDetailVO.spaceDetailFreeTimeStart}</td>
			<td>${spaceDetailVO.spaceDetailFreeTimeEnd}</td>
			<td>${spaceDetailVO.spaceDetailCharge}</td>
				
				<c:if test="${ods.selectAllOrderDetailBySD(spaceDetailVO.spaceDetailId).size() == 0}">
					<td><FORM METHOD="post" ACTION="<%=request.getContextPath()%>/spacedetail/spacedetail.do" >
			   		<input type="submit" value="修改">
			   		<input type="hidden" name="spaceDetailId" value="${spaceDetailVO.spaceDetailId}">
			   		<input type="hidden" name="action"	value="getOne_For_Update">
					</FORM></td>
				</c:if>
		</tr>
	</tbody>
	</c:forEach>
  </table>
<!-- 新增SpaceDetail資料 -->
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/spacedetail/spacedetail.do" name="form1">
<table>
	<tr>
		<td>場地開放日期:</td>
		<td><input name="spaceDetailFreeDate" id="f_date1" type="text" /></td>
	</tr>
	<tr>
		<td>場地開放起始時間:</td>
		<td><input name="spaceDetailFreeTimeStart" id="f_date2" type="text" 
		value="<%= (sdVO == null)? "" : sdVO.getSpaceDetailFreeTimeStart()%>"/></td>
		<span style="color:red"><%= (errorMessages == null)? "" : errorMessages.peek().contains("始")? errorMessages.poll() : (errorMessages.peek().contains("起始"))? errorMessages.poll() : ""%></span>
		<span style="color:red"><%= (errorMessages == null)? "" : errorMessages.peek().contains("內")? errorMessages.poll() : (errorMessages.peek().contains("內"))? errorMessages.poll() : ""%></span>
	</tr>
	<tr>
		<td>場地開放結束時間:</td>
		<td><input name="spaceDetailFreeTimeEnd" id="f_date3" type="text" 
		value="<%= (sdVO == null)? "" : sdVO.getSpaceDetailFreeTimeEnd()%>"/></td>
		<span style="color:red"><%= (errorMessages == null)? "" : errorMessages.peek().contains("結束")? errorMessages.poll() : (errorMessages.peek().contains("結束"))? errorMessages.poll() : ""%></span>
	</tr>
	<tr>
		<td>場地租借費用:</td>
		<td><input type="TEXT" name="spaceDetailCharge" size="45" 
		value="<%= (sdVO == null)? "" : sdVO.getSpaceDetailCharge()%>"/></td>
	</tr>

</table>
<br>
<input type="hidden" name="spaceId" value="<%=spaceId%>">
<input type="hidden" name="action" value="insert">
<input type="submit" value="新增場地明細" class="btn_1 medium">
</FORM>

</main>
<!--/main-->
</div>
<!-- page -->

<!-- calendar scripts -->
<script>
  	$( "#example" ).fullCalendar({
  		// 參數設定[註1]
  		header: { // 頂部排版
  			left: "prev,next today", // 左邊放置上一頁、下一頁和今天
  			center: "title", // 中間放置標題
  			right: "month,basicWeek,basicDay" // 右邊放置月、周、天
  		},
  		defaultDate: new Date(), // 起始日期
  		weekends: true, // 顯示星期六跟星期日
  		editable: false,  // 啟動拖曳調整日期
  		events: [ // 事件
  <c:forEach var="spaceDetailVO" items="${calendarlist}">	
  			{ // 事件
  				title: '此時段租金${spaceDetailVO.spaceDetailCharge}/hr',
  				start: '${spaceDetailVO.spaceDetailId}',
  				end: '${spaceDetailVO.spaceId}'
  			},
  </c:forEach>
  <c:forEach var="orderDetailVO" items="${odlist}">	
  			{ // 事件
  				title: '已被預約時段',
  				start: '${orderDetailVO.orderDetailId}',
  				end: '${orderDetailVO.orderMasterId}',
  				backgroundColor: '#FF5F5F'
  			},
  </c:forEach>
  			{ // 事件(包含開始時間、結束時間)
  				title: "發表BigZoo",
  				start: "2020-10-29T09:00:00",
  				end: "2020-10-29T12:00:00"
  			}
 
  			//{ // 事件(設定連結)
  				//title: "大衛海鮮",
  				//url: "https://www.facebook.com/PageDavidSeafood/",
  				//start: "2020-10-22"
  			//}
  		]
  	});
</script>

<!-- COMMON SCRIPTS -->
  	<script src="<%=request.getContextPath()%>/plugins/js/common_scripts.js"></script>
  	<script src="<%=request.getContextPath()%>/plugins/js/main.js"></script>
	
</body>
<%--
  java.sql.Date spaceDetailFreeDate = null;
  try {
	  spaceDetailFreeDate = spaceDetailVO.getSpaceDetailFreeDate();
   } catch (Exception e) {
	   spaceDetailFreeDate = new java.sql.Date(System.currentTimeMillis());
   }

  java.sql.Timestamp spaceDetailFreeTimeStart = null;
  try {
	  spaceDetailFreeTimeStart = spaceDetailVO.getSpaceDetailFreeTimeStart();
   } catch (Exception e) {
	   spaceDetailFreeTimeStart = new java.sql.Timestamp(System.currentTimeMillis());
   }
  
  java.sql.Timestamp spaceDetailFreeTimeEnd = null;
  try {
	  spaceDetailFreeTimeEnd = spaceDetailVO.getSpaceDetailFreeTimeEnd();
   } catch (Exception e) {
	   spaceDetailFreeTimeEnd = new java.sql.Timestamp(System.currentTimeMillis());
   }
--%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
		//FreeDate設定
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: 'bright',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       //step: 60,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: 'new Date()', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           startDate:	          'new Date()',  // 起始日
           minDate:               '-1970-01-01', // 去除今日(不含)之前
           maxDate:               '+2021-07-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
              var somedate1 = new Date('');
              $('#f_date1').datetimepicker({
                  beforeShowDay: function(date) {
                	  if (  date.getYear() <  somedate1.getYear() || 
        		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
                      ) {
                           return [false, ""]
                      }
                      return [true, ""];
              }});

        
        //      2.以下為某一天之後的日期無法選擇
              var somedate2 = new Date('2021-07-01');
              $('#f_date1').datetimepicker({
                  beforeShowDay: function(date) {
                	  if (  date.getYear() >  somedate2.getYear() || 
        		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
                      ) {
                           return [false, ""]
                      }
                      return [true, ""];
              }});
              
          //FreeTimeStart設定    
          $.datetimepicker.setLocale('zh');
          $('#f_date2').datetimepicker({
               theme: 'bright',              //theme: 'dark',
       	       timepicker:true,       //timepicker:true,
       	       //step: 60,                //step: 60 (這是timepicker的預設間隔60分鐘)
       	       format:'Y-m-d H:00:00',         //format:'Y-m-d H:i:s',
       		     value: 'new Date()', // value:   new Date(),
                 //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
               startDate:	          'new Date()',  // 起始日
               minDate:               '-1970-01-01', // 去除今日(不含)之前
               maxDate:               '+2021-07-01'  // 去除今日(不含)之後
          });
              
              
         
          // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

          //      1.以下為某一天之前的日期無法選擇
            var somedate1 = new Date('');
            $('#f_date2').datetimepicker({
                  beforeShowDay: function(date) {
                  if (  date.getYear() <  somedate1.getYear() || 
              		    (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
              		    (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
                     ) {
                          return [false, ""]
                     }
                          return [true, ""];
             }});

              
              //      2.以下為某一天之後的日期無法選擇
             var somedate2 = new Date('2021-07-01');
             $('#f_date2').datetimepicker({
                 beforeShowDay: function(date) {
                   if (  date.getYear() >  somedate2.getYear() || 
              		      (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
              		      (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
                      ) {
                          return [false, ""]
                        }
                          return [true, ""];
             }});
             
          //FreeTimeEnd設定
          $.datetimepicker.setLocale('zh');
          $('#f_date3').datetimepicker({
               theme: 'bright',              //theme: 'dark',
       	       timepicker:true,       //timepicker:true,
       	       //step: 60,                //step: 60 (這是timepicker的預設間隔60分鐘)
       	       format:'Y-m-d H:00:00',         //format:'Y-m-d H:i:s',
       		     value: 'new Date()', // value:   new Date(),
                 //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
               startDate:	          'new Date()',  // 起始日
               minDate:               '-1970-01-01', // 去除今日(不含)之前
               maxDate:               '+2021-07-01'  // 去除今日(不含)之後
          });
              
              
         
          // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

          //      1.以下為某一天之前的日期無法選擇
            var somedate1 = new Date('');
            $('#f_date3').datetimepicker({
                  beforeShowDay: function(date) {
                  if (  date.getYear() <  somedate1.getYear() || 
              		    (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
              		    (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
                     ) {
                          return [false, ""]
                     }
                          return [true, ""];
             }});

              
              //      2.以下為某一天之後的日期無法選擇
             var somedate2 = new Date('2021-07-01');
             $('#f_date3').datetimepicker({
                 beforeShowDay: function(date) {
                   if (  date.getYear() >  somedate2.getYear() || 
              		      (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
              		      (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
                      ) {
                          return [false, ""]
                        }
                          return [true, ""];
             }});


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