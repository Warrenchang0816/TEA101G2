<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.spaceDetail.model.*"%>
<%@ page import="java.util.*"%>

<%
  SpaceDetailVO spaceDetailVO = (SpaceDetailVO) request.getAttribute("spaceDetailVO"); //SpaceDetailServlet.java (Concroller) 存入req的spaceDetailVO物件 (包括幫忙取出的spaceDetailVO, 也包括輸入資料錯誤時的spaceDetailVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>修改場地明細</title>

<!-- GOOGLE WEB FONT -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">

<!-- BASE CSS -->
    <link href="<%=request.getContextPath()%>/plugins/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/plugins/css/style.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/plugins/css/vendors.css" rel="stylesheet">

<!-- Your custom styles -->
    <link href="<%=request.getContextPath()%>/plugins/css/custom.css" rel="stylesheet" type="text/css">

</head>

<body>

<div id="page">
		
	<header class="header menu_fixed">
		<div id="preloader"><div data-loader="circle-side"></div></div><!-- /Page Preload -->
		<div id="logo">
			<a href="<%=request.getContextPath()%>/frontend/space/spaceHome.jsp">
				<img src="<%=request.getContextPath()%>/plugins/img/logo.svg" width="150" height="36" alt="" class="logo_normal">
				<img src="<%=request.getContextPath()%>/plugins/img/logo_sticky.svg" width="150" height="36" alt="" class="logo_sticky">
			</a>
		</div>		
		<ul id="top_menu">
			<li><a href="cart-1.html" class="cart-menu-btn" title="Cart"><strong>4</strong></a></li>
			<li><a href="#sign-in-dialog" id="sign-in" class="login" title="Sign In">Sign In</a></li>
			<li><a href="wishlist.html" class="wishlist_bt_top" title="Your wishlist">Your wishlist</a></li>
		</ul>
		<!-- /top_menu -->
		<a href="#menu" class="btn_mobile">
			<div class="hamburger hamburger--spin" id="hamburger">
				<div class="hamburger-box">
					<div class="hamburger-inner"></div>
				</div>
			</div>
		</a>
		<nav id="menu" class="main-menu">
			<ul>
				<li><span><a href="<%=request.getContextPath()%>/frontend/space/spaceHome.jsp">場地</a></span>
					<ul>
						<li>
							<span><a href="<%=request.getContextPath()%>/frontend/space/spaceHome.jsp">我的場地</a></span>
							<ul>
								<li><a href="<%=request.getContextPath()%>/frontend/space/addSpace.jsp">新增場地</a></li>
								<li><a href="<%=request.getContextPath()%>/frontend/space/listAllSpace.jsp">所有場地</a></li>
							</ul>
						</li>
						<li>
							<span><a href="<%=request.getContextPath()%>/frontend/spacedetail/spaceDetailHome.jsp">我的場地明細</a></span>
							<ul>
								<li><a href="<%=request.getContextPath()%>/frontend/spacedetail/addSpaceDetail.jsp">新增場地明細</a></li>
								<li><a href="<%=request.getContextPath()%>/frontend/spacedetail/listAllSpaceDetail.jsp">所有場地明細</a></li>
							</ul>
						</li>
						<li>
							<span><a href="<%=request.getContextPath()%>/frontend/spacephoto/spacePhotoHome.jsp">場地照片</a></span>
							<ul>
								<li><a href="<%=request.getContextPath()%>/frontend/spacephoto/addSpacePhoto.jsp">新增場地照片</a></li>
								<li><a href="<%=request.getContextPath()%>/frontend/spacephoto/listAllSpacePhoto.jsp">所有場地照片</a></li>
							</ul>
						</li>
						<li>
							<span><a href="<%=request.getContextPath()%>/frontend/spacecomment/spaceCommentHome.jsp">場地評價</a></span>
							<ul>
								<li><a href="<%=request.getContextPath()%>/frontend/spacecomment/addSpaceComment.jsp">新增場地評價</a></li>
								<li><a href="<%=request.getContextPath()%>/frontend/spacecomment/listAllSpaceComment.jsp">所有場地評價</a></li>
							</ul>
						</li>
					</ul>
				</li>
				<li><span><a></a></span>
				</li>
				<li><span><a></a></span>
				</li>
				<li><span><a></a></span>
				</li>
				<li><span><a></a></span>
				</li>
				<li><span><a></a></span>
				</li>
				<li><span><a></a></span>
				</li>
				<li><span><a></a></span>
				</li>
				<li><span><a></a></span>
				</li>
				<li><span><a></a></span>
				</li>
				<li><span><a></a></span>
				</li>
			</ul>
		</nav>
	</header>
  <!-- /header -->

  <main>
		
		<section class="hero_in hotels">
			<div class="wrapper">
				<div class="container">
					<h1 class="fadeInUp"><span></span>修改場地明細</h1>
				</div>
			</div>
		</section>
		<!--/hero_in-->


<FORM METHOD="post" ACTION="spacedetail.do" enctype="multipart/form-data" name="form1">
<table>
	<tr>
		<td>場地明細ID:<font color=red><b>*</b></font></td>
		<td><%=spaceDetailVO.getSpaceDetailId()%></td>
	</tr>
	<tr>
		<td>場地ID:</td>
		<td><input type="TEXT" name="spaceId" size="45" value="<%=spaceDetailVO.getSpaceId()%>" /></td>
	</tr>
	<tr>
		<td>場地開放日期:</td>
		<td><input name="spaceDetailFreeDate" id="f_date1" type="text" value="<%=spaceDetailVO.getSpaceDetailFreeDate()%>" ></td>
	</tr>
	<tr>
		<td>場地開放起始時間:</td>
		<td><input name="spaceDetailFreeTimeStart" id="f_date2" type="text" value="<%=spaceDetailVO.getSpaceDetailFreeTimeStart()%>" ></td>
	</tr>
	<tr>
		<td>場地開放結束時間:</td>
		<td><input name="spaceDetailFreeTimeEnd" id="f_date3" type="text" value="<%=spaceDetailVO.getSpaceDetailFreeTimeEnd()%>" ></td>
	</tr>
	<tr>
		<td>場地租借費用:</td>
		<td><input type="TEXT" name="spaceDetailCharge" size="45" value="<%=spaceDetailVO.getSpaceDetailCharge()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="spaceDetailId" value="<%=spaceDetailVO.getSpaceDetailId()%>">
<input type="submit" value="送出修改"></FORM>
</main>
<!--/main-->
</div>
<!-- page -->

<!-- COMMON SCRIPTS -->
  	<script src="<%=request.getContextPath()%>/plugins/js/common_scripts.js"></script>
  	<script src="<%=request.getContextPath()%>/plugins/js/main.js"></script>
	
<!-- Map -->
	<script src="http://maps.googleapis.com/maps/api/js"></script>
	<script src="<%=request.getContextPath()%>/plugins/js/markerclusterer.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/js/map_hotels.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/js/infobox.js"></script>
	
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
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
%>
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
 	       timepicker:true,       //timepicker:true,
 	       //step: 60,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: 'new Date()', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           startDate:	           '2020/09/30',  // 起始日
           minDate:               '-1970-01-01', // 去除今日(不含)之前
           maxDate:               '+2021-07-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
              var somedate1 = new Date('2020-09-30');
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
       	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
       		     value: 'new Date()', // value:   new Date(),
                 //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
               startDate:	           '2020/09/30',  // 起始日
               minDate:               '-1970-01-01', // 去除今日(不含)之前
               maxDate:               '+2021-07-01'  // 去除今日(不含)之後
          });
              
              
         
          // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

          //      1.以下為某一天之前的日期無法選擇
            var somedate1 = new Date('2020-09-30');
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
       	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
       		     value: 'new Date()', // value:   new Date(),
                 //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
               startDate:	           '2020/09/30',  // 起始日
               minDate:               '-1970-01-01', // 去除今日(不含)之前
               maxDate:               '+2021-07-01'  // 去除今日(不含)之後
          });
              
              
         
          // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

          //      1.以下為某一天之前的日期無法選擇
            var somedate1 = new Date('2020-09-30');
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