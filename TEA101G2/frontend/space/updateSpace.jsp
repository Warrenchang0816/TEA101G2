<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.spacePhoto.model.*"%>
<%@ page import="com.space.model.*"%>

<%
  SpaceVO spaceVO = (SpaceVO) request.getAttribute("spaceVO"); // SpaceServlet.java (Concroller) 存入req的spaceVO物件 (包括幫忙取出的spaceVO, 也包括輸入資料錯誤時的spaceVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>場地資料修改</title>

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
					<h1 class="fadeInUp"><span></span>場地資料修改</h1>
				</div>
			</div>
		</section>
		<!--/hero_in-->


<FORM METHOD="post" ACTION="space.do" name="form1">
<table>

	<tr>
		<td>場地ID:<font color=red><b>*</b></font></td>
		<td><%=spaceVO.getSpaceId()%></td>
	</tr>
	<tr>
		<td>會員ID:</td>
		<td><input type="TEXT" name="memId" size="45" value="<%=spaceVO.getMemberId()%>" /></td>
	</tr>
	<tr>
		<td>員工ID:</td>
		<td><input type="TEXT" name="empId" size="45" value="<%=spaceVO.getEmpId()%>" /></td>
	</tr>
	<tr>
		<td>場地地址:</td>
		<td><input type="TEXT" name="spaceAddress" size="45" value="<%=spaceVO.getSpaceAddress()%>" /></td>
	</tr>
	<tr>
		<td>場地經度:</td>
		<td><input type="TEXT" name="spaceLng" size="45" value="<%=spaceVO.getSpaceLng()%>"/></td>
	</tr>
	<tr>
		<td>場地緯度:</td>
		<td><input type="TEXT" name="spaceLat" size="45" value="<%=spaceVO.getSpaceLat()%>"/></td>
	</tr>
	<tr>
		<td>場地名稱:</td>
		<td><input type="TEXT" name="spaceName" size="45" value="<%=spaceVO.getSpaceName()%>" /></td>
	</tr>
	<tr>
		<td>場地介紹:</td>
		<td><input type="TEXT" name="spaceText" size="45" value="<%=spaceVO.getSpaceText()%>" /></td>
	</tr>
	<tr>
		<td>場地類型:</td>
		<td><input type="TEXT" name="spaceType" size="45" value="<%=spaceVO.getSpaceType()%>" /></td>
	</tr>
	<tr>
		<td>場地設備:</td>
		<td><input type="TEXT" name="spaceEquipment" size="45" value="<%=spaceVO.getSpaceEquipment()%>" /></td>
	</tr>
	<tr>
		<td>場地容納人數:</td>
		<td><input type="TEXT" name="spaceContain" size="45"	value="<%=spaceVO.getSpaceContain()%>" /></td>
	</tr>
	<tr>
		<td>場地規則:</td>
		<td><input type="TEXT" name="spaceRule" size="45"	value="<%=spaceVO.getSpaceRule()%>" /></td>
	</tr>
	<tr>
		<td>場地退款須知:</td>
		<td><input type="TEXT" name="spaceRefund" size="45"	value="<%=spaceVO.getSpaceRefund()%>" /></td>
	</tr>
	<tr>
		<td>場地狀態:</td>
		<td><input type="TEXT" name="spaceStatus" size="45"	value="<%=spaceVO.getSpaceStatus()%>" /></td>
	</tr>
	<tr>
		<td>場地註冊日期:</td>
		<td><input name="spaceSignupDate" id="f_date1" type="text" ></td>
	</tr>
	<tr>
		<td>場地上架日期:</td>
		<td><input name="spaceOnsaleDate" id="f_date1" type="text" ></td>
	</tr>
	<tr>
		<td>場地下架日期:</td>
		<td><input name="spaceOffsaleDate" id="f_date1" type="text" ></td>
	</tr>

	<jsp:useBean id="spaceSvc" scope="page" class="com.space.model.SpaceService" />
	
	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="spaceId" value="<%=spaceVO.getSpaceId()%>">
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

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

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
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=spaceVO.getSpaceSignupDate()%>', // value:   new Date(),
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