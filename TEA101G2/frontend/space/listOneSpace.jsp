<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.spacePhoto.model.*"%>
<%@ page import="com.space.model.*"%>
<%@ page import="com.spaceDetail.model.*"%>
<%@ page import="com.orderDetail.model.*"%>
<%@ page import="com.spaceComment.model.*"%>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />

<%
  
  SpaceVO spaceVO = (SpaceVO) request.getAttribute("spaceVO"); //SpaceServlet.java(Concroller), 存入req的spaceVO物件
  //SpaceEquipment拆字串用
  String spaceEqAll = spaceVO.getSpaceEquipment();
  String[] spaceEqSplit = spaceEqAll.split(":");
  pageContext.setAttribute("spaceEqSplit", spaceEqSplit);
  //SpacePhoto印出照片用
  SpacePhotoService spacePhotoSvc = new SpacePhotoService();
  //印出所有場地照片
  List<SpacePhotoVO> photolist = spacePhotoSvc.getAllPhoto(spaceVO.getSpaceId());
  pageContext.setAttribute("photolist", photolist);
  //誠實業者必備顯示最低價格
  SpaceDetailService spaceDetailSvc = new SpaceDetailService();
  SpaceDetailVO spaceDetailVO = spaceDetailSvc.selectOneLowest(spaceVO.getSpaceId());
  //SpaceComment列出所有場地評價用
  SpaceCommentService spaceCommentSvc = new SpaceCommentService();
  List<SpaceCommentVO> commentlist = spaceCommentSvc.getAllCommBySpace(spaceVO.getSpaceId());
  for(SpaceCommentVO transfer : commentlist){
	  transfer.setSpaceCommentId(spaceCommentSvc.showNickname(transfer.getMemberId()));
  }
  pageContext.setAttribute("commentlist", commentlist);
  
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>場地資料</title>

<!-- GOOGLE WEB FONT -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">
<!-- BASE CSS -->
    <link href="<%=request.getContextPath()%>/plugins/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/plugins/css/style.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/plugins/css/vendors.css" rel="stylesheet">

<!-- Your custom styles -->
    <link href="<%=request.getContextPath()%>/plugins/css/custom.css" rel="stylesheet" type="text/css">
    <script src="<%=request.getContextPath()%>/plugins/js/jquery-3.5.1.min.js"></script>
  

</head>
<body>

<div id="page">
<jsp:include page="/frontend/other/header.jsp" />	

  <main>
		
	<section class="hero_in hotels">
		<div class="wrapper">
			<div class="container">
				<h1 class="fadeInUp"><span></span><%=spaceVO.getSpaceName()%></h1>
			</div>
		</div>
	</section>
	<!--/hero_in-->
	<div class="bg_color_1">
		<div class="container margin_60_35">
			<div class="row">
				<div class="col-lg-8">
					<section id="description">
						<h2>場地介紹</h2>
						<p><%=spaceVO.getSpaceText()%></p>
						<h3>場地規則</h3>
						<p><%=spaceVO.getSpaceRule()%></p>
						<h3>場地退費須知</h3>
						<p><%=spaceVO.getSpaceRefund()%></p>
						<div class="row">
							<div class="col-lg-6">
								<ul class="bullets">
									<li>場地類型：<%=spaceVO.getSpaceType()%></li>
								</ul>
							</div>
							<div class="col-lg-6">
								<ul class="bullets">
									<li>場地容納人數：<%=spaceVO.getSpaceContain()%>人</li>
								</ul>
							</div>
							<div class="col-lg-12">
								<ul class="bullets">
									<li>場地地址：<%=spaceVO.getSpaceAddress()%></li>
								</ul>
							</div>
							<div class="col-lg-12">
								<ul class="bullets">
									<li>場地設備：</li>
									<c:forEach var="string" items="${spaceEqSplit}">
										<c:choose>
											<c:when test="${'WIFI' == string}"> 
												<p><img src="<%=request.getContextPath()%>/plugins/img/hotel_facilites_icon_4.svg" alt="">&nbsp;WIFI</p>
											</c:when>
											<c:when test="${'冷氣' == string}"> 
												<p><img src="<%=request.getContextPath()%>/plugins/img/hotel_facilites_icon_7.svg" alt="">&nbsp;冷氣</p>
											</c:when>
											<c:when test="${'咖啡機' == string}"> 
												<p><img src="<%=request.getContextPath()%>/plugins/img/hotel_facilites_icon_1.svg" alt="">&nbsp;咖啡機</p>
											</c:when>
											<c:when test="${'吹風機' == string}"> 
												<p><img src="<%=request.getContextPath()%>/plugins/img/hotel_facilites_icon_8.svg" alt="">&nbsp;吹風機</p>
											</c:when>
											<c:otherwise>
												<p>${string}</p>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</ul>
							</div>
						</div>
						<!-- /row -->
						<hr>
						<h3>場地照片</h3>
						<div class="room_type first">
							<div class="row">
							<c:forEach var="spacePhotoVO" items="${photolist}">
								<div class="col-md-4">
									<img src="<%=request.getContextPath()%>/space/showpicture?spacePhotoId=${spacePhotoVO.spacePhotoId}" width="200" height="135">
								</div>
							</c:forEach>
							</div>
							<!-- /row -->
						</div>
						<hr>
<!-- 						<h3>Location</h3> -->
<!-- 						<div id="map" class="map map_single add_bottom_30"></div> -->
						<!-- End Map -->
						<c:if test="${userVO != null}"> 
						<button type="button" class="btn btn-light" data-toggle="modal" data-target="#contact-modal">撰寫評論</button>
						</c:if>
						<hr>
						<h3>場地評論</h3>
						<div class="reviews-container">
						<c:forEach var="spaceCommentVO" items="${commentlist}">
							<div class="review-box clearfix">
								<figure class="rev-thumb"><img src="<%=request.getContextPath()%>/space/showmempicture?memberId=${spaceCommentVO.memberId}">
								</figure>
								<div class="rev-content">
									<div class="rating">
										<c:choose>
		                                	<c:when test="${spaceCommentVO.spaceCommentLevel <=1}">
		                                		<i class="icon_star voted"></i><i class="icon_star"></i>
		                                		<i class="icon_star"></i><i class="icon_star"></i>
		                                		<i class="icon_star"></i>
		                                	</c:when>
		                                	<c:when test="${spaceCommentVO.spaceCommentLevel <=2}">
		                                		<i class="icon_star voted"></i><i class="icon_star voted"></i>
		                                		<i class="icon_star"></i><i class="icon_star"></i>
		                                		<i class="icon_star"></i>
		                                	</c:when>
		                                	<c:when test="${spaceCommentVO.spaceCommentLevel <=3}">
		                                		<i class="icon_star voted"></i><i class="icon_star voted"></i>
		                                		<i class="icon_star voted"></i><i class="icon_star"></i>
		                                		<i class="icon_star"></i>
		                                	</c:when>
		                                	<c:when test="${spaceCommentVO.spaceCommentLevel <=4}">                     
		                                		<i class="icon_star voted"></i><i class="icon_star voted"></i>
		                                		<i class="icon_star voted"></i><i class="icon_star voted"></i>
		                                		<i class="icon_star"></i>
		                                	</c:when>
		                                	<c:when test="${spaceCommentVO.spaceCommentLevel <=5}">
		                                		<i class="icon_star voted"></i><i class="icon_star voted"></i>
		                                		<i class="icon_star voted"></i><i class="icon_star voted"></i>
		                                		<i class="icon_star voted"></i>
		                                	</c:when>
                               		 </c:choose>
									</div>
									<div class="rev-info">
										 ${spaceCommentVO.spaceCommentId} – ${spaceCommentVO.spaceCommentDate}
									</div>
									<div class="rev-text">
										<p>
											${spaceCommentVO.spaceCommentContent}
										</p>
									</div>
								</div>
							</div>
							<!-- /review-box -->
						</c:forEach>
						</div>
						<!-- /review-container -->
					</section>
					<!-- /section -->
				</div>
				<!-- col -->
				<aside class="col-lg-4" id="sidebar">
				<c:if test="${userVO.memberId eq spaceVO.memberId}">
				<div style="margin-bottom:15px">
					<form method="post" action="<%=request.getContextPath()%>/space/space.do" style="display: inline;">
						<input type="hidden" name="spaceId" value="<%=spaceVO.getSpaceId()%>">
						<input type="hidden" name="memberId" value="${userVO.memberId}">
						<input type="hidden" name="action" value="getOne_For_Update">
						<button type="submit" class="btn btn-light">編輯場地</button>
					</form>
					
					<form method="post" action="<%=request.getContextPath()%>/spacephoto/spacephoto.do" style="display: inline;">
						<input type="hidden" name="spaceId" value="<%=spaceVO.getSpaceId()%>">
						<input type="hidden" name="action" value="listAllSpacePhotoBySpaceForEdit">
						<button type="submit" class="btn btn-light">管理圖片</button>
					</form>
					
					<form method="post" action="<%=request.getContextPath()%>/spacedetail/spacedetail.do" style="display: inline;">
						<input type="hidden" name="spaceId" value="<%=spaceVO.getSpaceId()%>">
						<input type="hidden" name="action" value="listAllSpaceDetailBySpaceForEdit">
						<button type="submit" class="btn btn-light">場地明細</button>
					</form>
				</div>
				
				<div style="margin-bottom:15px;">
			
					<c:if test="${spaceVO.spaceStatus.trim() == 'N'}">
						<button type="submit" disabled="disabled" class="btn btn-light" data-toggle="modal" data-target="#contact-modal">上架申請審核中</button>
					</c:if>
					<c:if test="${spaceVO.spaceStatus.trim() == 'F'}">
						<form method="post" action="<%=request.getContextPath()%>/space/space.do" style="display: inline;">
						<input type="hidden" name="spaceId" value="<%=spaceVO.getSpaceId()%>">
						<input type="hidden" name="action" value="applyforsale">
						<button type="submit" class="btn btn-light">上架申請</button>
						</form>
					</c:if>
				</div>
				</c:if>
						<div class="box_detail booking">
							<div class="price">
								<span><%= (spaceDetailVO.getSpaceDetailCharge() == null)? "最低收費" : spaceDetailVO.getSpaceDetailCharge() %> /小時<small>起</small></span>
							</div>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/spacedetail/spacedetail.do" name="form1">
								<input type="hidden" name="spaceId" value="<%=spaceVO.getSpaceId()%>">
								<input type="hidden" name="memberId" value="<%=spaceVO.getMemberId()%>">
								<input type="hidden" name="orderCreateDate" value="<%=new Date()%>">
								<input type="hidden" name="action" value="listAllSpaceDetailBySpace">
								<!-- 登入狀態時，進入選擇時段的畫面 -->
								<c:if test="${userVO != null}">
									<input type="submit" value="搶先預約!" class="add_top_30 btn_1 full-width purchase">
								</c:if>
								<!-- 未登入狀態時，進入登入頁面 -->
								<c:if test="${userVO == null}">
									<input type="button" value="搶先預約!" class="add_top_30 btn_1 full-width purchase" onclick="location.href='<%=request.getContextPath()%>/frontend/login.jsp'">
								</c:if>
								<div class="text-center"><small>此步驟不收取任何費用</small></div>
							</FORM>
							
						</div>
							<form method="post"
									action="<%=request.getContextPath()%>/MemberServlet.do"
									id="form_getOneMember">
									<input type="hidden" name="memberId" value="${spaceVO.memberId}">
									<input type="hidden" name="action" value="getOneMember">
							</form>
						<h6>提供者: <a href="#0" onclick="document.getElementById('form_getOneMember').submit()">
						<img src="<%=request.getContextPath()%>/memberPhoto/showpicture?memberId=${spaceVO.memberId}" width="72" height="72" style="margin-right: 110px; border-radius: 100%">
						</a></h6>
						<a href="#0" onclick="document.getElementById('form_getOneMember').submit()" style="margin-left:70px"><label style="font-size:12px">
						${memberSvc.getOneMember(spaceVO.memberId).memberName}
						</label></a>
				</aside>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /bg_color_1 -->
</main>
<!--/main-->
		<div id="contact-modal" class="modal fade" role="dialog" style="padding-top:150px">
	        <div class="modal-dialog">
	            <div class="modal-content">
	                <div class="modal-header">
	                	<div style="width:525px;text-align:center; font-size:24px; font-family:Poppins, Helvetica, sans-serif;">
	                	<%=spaceVO.getSpaceName()%></div>
	                    <a class="close" data-dismiss="modal">×</a>
	                </div>
	                <form method="post" id="contactForm" name="contact">
	                    <div class="modal-body">
	                        <div class="form-group star_block">
	                            <span class="rating">
	                            	<c:forEach begin="1" end="5" varStatus="loop">
				                        <span class="star" data-star="${loop.index}"><i class="icon_star"></i></span>
				                    </c:forEach>
				               	</span>
	                        </div>
	                        <div class="form-group">
	                            <label for="message">評論</label>
								<textarea id="spaceCommentContent" name="spaceCommentContent" class="form-control clear_spaceCommentContent" 
								placeholder="撰寫你的評論" style="width:450px; height:200px;"></textarea>
	                        </div>
	                    </div>
	                    <div class="modal-footer">
	                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	                        <input type="submit" class="btn btn-primary" id="submit" value="張貼">
	                    </div>
	                </form>
	            </div>
	        </div>
   		 </div>
 <!-- Replay Popup Window -->
</div>
<!-- page -->

<!-- COMMON SCRIPTS -->

	<script>
		<c:set var="memberNickName" value="${userVO.memberNickName}"/>
		<c:set var="spaceId" value="${spaceVO.spaceId}"/>
    	<c:set var="memberId" value="${userVO.memberId}"/>
		let current_star = null;
	
		$(document).on("click","span.star",function(){
			current_star = parseInt($(this).attr("data-star"));
	    	current_star = parseInt($(this).attr("data-star"));
	    	if (!$(this).hasClass("voted")) {
	            $(this).addClass("voted");
	            $(this).prevAll().addClass("voted");
	        } else {
	            $(this).nextAll().removeClass("voted");
	        }
	    });
		
		var Today=new Date();
    	var commentData = (Today.getFullYear()+ "-" + (Today.getMonth()+1) + "-" + Today.getDate());
    	
		$(document).ready(function(){	
	    	$("#contactForm").submit(function(event) {
	    		$.ajax({
	    			url: "<%=request.getContextPath()%>/spacecomment/spacecomment.do",
	    	   		type:"POST",
	    	   		dataType:"json",
	    	   		data: 
	    	   			{spaceId: '${spaceId}', 
	    	   			memberId: '${memberId}',
	    	   			spaceCommentContent:$('textarea#spaceCommentContent').val(),
	    	   			spaceCommentLevel: current_star,
	    	   			action:'insert'},
	    	   		success: function(data){
		    	   		alert("success");
		    	   		let list_html = "";
		    	   		let star_html = "";
		     	   		star_html += '<span class="rating">';
		    	   		star_html += '<span class="star" data-star="1"><i class="icon_star"></i></span>';
		    	   		star_html += '<span class="star" data-star="2"><i class="icon_star"></i></span>';
		    	   		star_html += '<span class="star" data-star="3"><i class="icon_star"></i></span>';
		    	   		star_html += '<span class="star" data-star="4"><i class="icon_star"></i></span>';
		    	   		star_html += '<span class="star" data-star="5"><i class="icon_star"></i></span>';
		    	   		star_html += '</span>';
		    	   		list_html += '<div class="reviews-container">';
		    	        list_html += '<div class="review-box clearfix">';
		    	        list_html += '<figure class="rev-thumb">';
		    	        list_html += '<img src="<%=request.getContextPath()%>/space/showmempicture?memberId='+ data.memberId +'">';
		    	        list_html += '</figure><div class="rev-content">';
		    	        list_html += '<div class="rating">';
		    	        list_html += '<i class="icon_star'+(data.spaceCommentLevel >= 1 ? " voted" : "")+'"></i>';
		    	        list_html += '<i class="icon_star'+(data.spaceCommentLevel >= 2 ? " voted" : "")+'"></i>';
		    	        list_html += '<i class="icon_star'+(data.spaceCommentLevel >= 3 ? " voted" : "")+'"></i>';
		    	        list_html += '<i class="icon_star'+(data.spaceCommentLevel >= 4 ? " voted" : "")+'"></i>';
		  	 	        list_html += '<i class="icon_star'+(data.spaceCommentLevel >= 5 ? " voted" : "")+'"></i>';
		             	list_html += '</div>';
		    	        list_html += '<div class="rev-info">' + '${memberNickName}' + ' – ' + commentData + '</div>';
		    	        list_html += '<div class="rev-text"><p>'+data.spaceCommentContent+'</p></div>';
		    	        list_html += '</div>';
		    	        list_html += '</div>';
		    	        list_html += '</div>';
		    	   	 	$("div.reviews-container").prepend(list_html);
	    	   		 	$("#contact-modal").modal("hide");
	    	   			$("div.star_block").find("span.rating").remove();
	    	   			$("div.star_block").append(star_html);
		    	   		$("textarea.clear_spaceCommentContent").val("");
	    	   		},
	    	   		error: function(xhr){
	    	   			console.log(xhr);
	    	   			alert("Error");
	    	   		},
	    	   	});
	    		return false;
	    	});
	    });

	</script>
  	<script src="<%=request.getContextPath()%>/plugins/js/common_scripts.js"></script>
  	<script src="<%=request.getContextPath()%>/plugins/js/main.js"></script>
	
<!-- Map -->
	<script src="http://maps.googleapis.com/maps/api/js"></script>
	<script src="<%=request.getContextPath()%>/plugins/js/map_single_hotel.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/js/infobox.js"></script>
	
</body>

</html>