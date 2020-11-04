<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.space.model.*"%>
<%@ page import="java.util.*"%>

<%
  SpaceVO spaceVO = (SpaceVO) request.getAttribute("spaceVO");
  LinkedList<String> errorMessages = (LinkedList<String>) request.getAttribute("errorMessages");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>新增場地</title>

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
<jsp:include page="/frontend/other/header.jsp" />		

  <main>
		
		<section class="hero_in hotels">
			<div class="wrapper">
				<div class="container">
					<h1 class="fadeInUp"><span></span>新增場地</h1>
				</div>
			</div>
		</section>
		<!--/hero_in-->
		
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/space/space.do" name="form1">
  <div class="content-wrapper">
    <div class="container-fluid">
		<div class="box_general padding_bottom">
			<div class="header_box version_2">
				<h2><i class="fa fa-file"></i>新增場地</h2>
			</div>
			<div class="row">										
				<div class="col-md-4">
					<div class="form-group">
						<label>場地名稱</label>
						<input type="text" name="spaceName" class="form-control" value="<%= (spaceVO == null)? "" : spaceVO.getSpaceName()%>"/>
						<span style="color:red"><%= (errorMessages == null)? "" : (!spaceVO.getSpaceName().equals(""))? "" : "  " + errorMessages.poll()%></span>
					</div>
				</div>
				<div class="col-md-8">
					<div class="form-group">
						<label>場地地址</label>
						<input type="text" name="spaceAddress" class="form-control" value="<%= (spaceVO == null)? "" : spaceVO.getSpaceAddress()%>"/>
						<span style="color:red"><%= (errorMessages == null)? "" : (!spaceVO.getSpaceAddress().equals(""))? "" : "  " + errorMessages.poll()%></span>
					</div>
				</div>
			</div>
			<!-- /row-->
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label>場地設備</label>
						<br><input type="checkbox" name="spaceEquipment" class="form-group" value="WIFI:"/>&nbsp;<img src="<%=request.getContextPath()%>/plugins/img/hotel_facilites_icon_4.svg" alt="">&nbsp;WIFI
						<br><input type="checkbox" name="spaceEquipment" class="form-group" value="冷氣:"/>&nbsp;<img src="<%=request.getContextPath()%>/plugins/img/hotel_facilites_icon_7.svg" alt="">&nbsp;冷氣
						<br><input type="checkbox" name="spaceEquipment" class="form-group" value="咖啡機:"/>&nbsp;<img src="<%=request.getContextPath()%>/plugins/img/hotel_facilites_icon_1.svg" alt="">&nbsp;咖啡機
						<br><input type="checkbox" name="spaceEquipment" class="form-group" value="吹風機:"/>&nbsp;<img src="<%=request.getContextPath()%>/plugins/img/hotel_facilites_icon_8.svg" alt="">&nbsp;吹風機
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label>其他設備</label><input type="text" name="spaceEquipment" class="form-control">
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>場地類型</label>
						<div class="styled2-select">
							<select name="spaceType">
							  <option>會議</option>
							  <option>課程講座</option>
							  <option>親子活動</option>
							  <option>攝影棚</option>
							</select>
					  </div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>場地容納人數</label>
						<div class="styled2-select">
							<select name="spaceContain">
							  <option>10</option>
							  <option>20</option>
							  <option>30</option>
							  <option>40</option>
							  <option>50</option>
							</select>
					    </div>
					</div>
				</div>
			</div>
			<!-- /row-->
			<div class="row">
				<div class="col-md-12">
					<div class="form-group">
						<label>場地介紹</label>
						<textarea name="spaceText" class="form-control" rows="5">新增場地介紹</textarea>
					</div>
				</div>
			</div>			
			<!-- /row-->
			<div class="row">
				<div class="col-md-12">
					<div class="form-group">
						<label>場地規則</label>
						<textarea name="spaceRule" class="form-control" rows="5">新增場地規則</textarea>
					</div>
				</div>
			</div>
			<!-- /row-->
			<div class="row">
				<div class="col-md-12">
					<div class="form-group">
						<label>場地退款須知</label>
						<textarea name="spaceRefund" class="form-control" rows="5">新增場地場地退款須知</textarea>
					</div>
				</div>
			</div>
			<!-- /row -->
			<div class="row">
				<div class="col-md-3">
					<div class="form-group">
						<label>場地上架日期</label>
						<input name="spaceOnsaleDate" id="f_date1" type="text"/>
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>場地下架日期</label>
						<input name="spaceOffsaleDate" id="f_date2" type="text"/>
					</div>
				</div>
			</div>			
		<!-- /row-->
		</div>    
		<!-- /box_general-->
		<p>
			<input type="hidden" name="action" value="insert">
			<input type="hidden" name="memberId" value="${userVO.memberId}">
            <input type="submit" value="送出新增" class="btn_1 medium">
		</p>
		</div>
  </div>
</FORM>
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
<%--
  java.sql.Date spaceOnsaleDate = null;
  try {
	  spaceOnsaleDate = spaceVO.getSpaceOnsaleDate();
   } catch (Exception e) {
	   spaceOnsaleDate = new java.sql.Date(System.currentTimeMillis());
   }
  
  java.sql.Date spaceOffsaleDate = null;
  try {
	  spaceOffsaleDate = spaceVO.getSpaceOffsaleDate();
   } catch (Exception e) {
	   spaceOffsaleDate = new java.sql.Date(System.currentTimeMillis());
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
		//Date設定
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
          
            //Date設定
              $.datetimepicker.setLocale('zh');
              $('#f_date2').datetimepicker({
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
        
</script>

</html>