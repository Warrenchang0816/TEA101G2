<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.space.model.*"%>
<%@ page import="java.util.*"%>

<jsp:useBean id="memberFavoriteSvc" scope="page" class="com.memberFavorite.model.MemberFavoriteService" />
<jsp:useBean id="spaceSvc" scope="page" class="com.space.model.SpaceService" />
<jsp:useBean id="spaceDetailSvc" scope="page" class="com.spaceDetail.model.SpaceDetailService" />	
<jsp:useBean id="spaceCommentSvc" scope="page" class="com.spaceComment.model.SpaceCommentService" />	
<jsp:useBean id="spacePhotoSvc" scope="page" class="com.spacePhoto.model.SpacePhotoService" />
					
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Panagea - Premium site template for travel agencies, hotels and restaurant listing.">
    <meta name="author" content="Ansonika">
    <title>search results</title>

    <!-- GOOGLE WEB FONT -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">

    <!-- BASE CSS -->
    <link href="<%=request.getContextPath()%>/frontend/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/frontend/css/style.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/frontend/css/vendors.css" rel="stylesheet">

    <!-- YOUR CUSTOM CSS -->
    <link href="<%=request.getContextPath()%>/frontend/css/custom.css" rel="stylesheet">
 
    <script src="<%=request.getContextPath()%>/frontend/js/jquery-3.5.1.min.js"></script>
	<script src="https://cdn.tutorialjinni.com/simplePagination.js/1.6/jquery.simplePagination.js"></script>

<style>
.ellipsis {
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
	display: -webkit-box;
	-webkit-line-clamp: 1;
	-webkit-box-orient: vertical;
	white-space: normal;
}

.list-wrapper {
	padding: 15px;
	overflow: hidden;
}

.list-item h4 {
	color: #FF7182;
	font-size: 18px;
	margin: 0 0 5px;
}

.list-item p {
	margin: 0;
}

.simple-pagination ul {
	margin: 0 0 20px;
	padding: 0;
	list-style: none;
	text-align: center;
}

.simple-pagination li {
	display: inline-block;
	margin-right: 5px;
}

.simple-pagination li a, .simple-pagination li span {
	color: #666;
	padding: 5px 10px;
	text-decoration: none;
	border: 1px solid #EEE;
	background-color: #FFF;
	box-shadow: 0px 0px 10px 0px #EEE;
}

.simple-pagination .current {
	color: #FFF;
	background-color: #FF7182;
	border-color: #FF7182;
}

.simple-pagination .prev.current, .simple-pagination .next.current {
	background: #e04e60;
}

.hero_in.hotels:before {
  background: url(<%=request.getContextPath()%>/frontend/image/home.jpg) center center no-repeat;
}

</style>

</head>

<body>
	<div id="page">
		<jsp:include page="/frontend/other/header.jsp"/>
		<!-- /header -->
	<main>
		
		<section class="hero_in hotels">
			<div class="wrapper">
				<div class="container">
					
				</div>
			</div>
		</section>
		<!--/hero_in-->
		
		<div class="filters_listing sticky_horizontal">
			<div class="container">
				<ul class="clearfix">
					<li>
						<div class="switch-field">
							<input type="radio" id="all" name="listing_filter" value="all" checked data-filter="*" class="selected">
							<label for="all">All</label>
						</div>
					</li>
					<li>
						<a class="btn_map" data-toggle="collapse" href="#collapseMap" aria-expanded="false" aria-controls="collapseMap" data-text-swap="Hide map" data-text-original="View on map">View on map</a>
					</li>
				</ul>
			</div>
			<!-- /container -->
		</div>
		<!-- /filters -->
		
		<div class="collapse" id="collapseMap">
			<div id="map" class="map"></div>
		</div>
		<!-- End Map -->

		<div class="container margin_60_35">
			<div class="row">
				<aside class="col-lg-3">
					<form method="post" action="<%=request.getContextPath()%>/space/space.do">
					<div class="custom-search-input-2 inner-2">
						<div class="form-group">
							<input class="form-control" name="spaceAddress" type="text" placeholder="區域" value="${param.spaceAddress}">
							<i class="icon_pin_alt"></i>
						</div>
						<div class="form-group">
							<input class="form-control" name="spaceContain" type="text" placeholder="人數" value="${param.spaceContain}">
							<i class="icon_search"></i>
						</div>
						<div class="form-group">
						<select name="spaceType" class="wide">
                        	<option value="">類型</option>
                        	<option value="會議" ${(param.spaceType eq '會議')? 'selected':''}>會議</option>
							<option value="課程講座" ${(param.spaceType eq '課程講座')? 'selected':''}>課程講座</option>
							<option value="親子活動" ${(param.spaceType eq '親子活動')? 'selected':''}>親子活動</option>
							<option value="攝影棚" ${(param.spaceType eq '攝影棚')? 'selected':''}>攝影棚</option>
						</select>
						</div>
						<input type="hidden" name="subQueryCommand" value="${subQueryCommand}">
						<input type="hidden" name="action" value="search_space">
						<input type="submit" class="btn_search" value="Search">
					</div>
					</form>
					<div style="margin-bottom:20px">
						<form>
							<input type="hidden" name="action" value="search_space">
							<button type="submit" class="btn btn-light" style="height:50; width:100%">Clear</button>
						</form>
					</div>	
					<!-- /custom-search-input-2 -->
				</aside>
				<!-- /aside -->

					<div class="col-lg-9">
					<div class="isotope-wrapper">
						<div class="row">
						<c:forEach var="spaceVO" items="${spaceVO}" varStatus="s">
							<c:if test="${spaceVO.spaceStatus eq 'T'}">
							<div class="col-md-6 isotope-item popular">
								<div class="box_grid">
									<figure>
										<c:if test="${userVO != null}">
											<c:choose>
												<c:when test="${memberFavoriteSvc.getMemberFavoriteStatus(userVO.memberId,spaceVO.spaceId) != null }">
													<a href="#0" class="wish_bt mfs${s.index} liked"></a>
												</c:when>
												<c:otherwise>
													<a href="#0" class="wish_bt mfs${s.index}"></a>
												</c:otherwise>
											</c:choose>
										</c:if>
										<form method="post" action="<%=request.getContextPath()%>/space/space.do" id="form_getOneSpaceC${s.index}">
											<input type="hidden" name="spaceId" value="${spaceVO.spaceId}">
											<input type="hidden" name="action" value="getOne_For_Display">
       									</form>
										<a href="#0" onclick="document.getElementById('form_getOneSpaceC${s.index}').submit()">
										<img src="<%=request.getContextPath()%>/space/showonepicture?spaceId=${spaceVO.spaceId}" class="img-fluid" alt="" width="800" height="533"><div class="read_more"><span>Read more</span></div></a>
										<small>${spaceVO.spaceType}</small>
									</figure>
									<div class="wrapper">									
										<div class="cat_star">
											<c:forEach var="i" begin="1" end="${spaceCommentSvc.getSpaceRating(spaceVO.spaceId)}">
												<i class="icon_star"></i>
											</c:forEach>
										</div>
										<h3><a href="#0" onclick="document.getElementById('form_getOneSpaceC${s.index}').submit()">${spaceVO.spaceName}</a></h3>
										<p class="ellipsis">${spaceVO.spaceText}</p>
										<span class="price"><i class="icon_clock_alt" style="margin-right:8px"></i>1 hr / <strong>${spaceDetailSvc.selectOneLowest(spaceVO.spaceId).spaceDetailCharge} $</strong></span>
										<br>
										<small class="address_ellipsis">${spaceVO.spaceAddress}</small>
									</div>
									<ul>
										<li><i class="ti-eye"></i></li>
										<li><div class="score"><span>
										<c:if test="${spaceCommentSvc.getSpaceRating(spaceVO.spaceId) >= 3.0 and spaceCommentSvc.getSpaceRating(spaceVO.spaceId) <= 3.9 }">
											Good
										</c:if>
										<c:if test="${spaceCommentSvc.getSpaceRating(spaceVO.spaceId) >= 4.0}">
											Awesome
										</c:if>
										<em>${spaceCommentSvc.getSpaceCommentCount(spaceVO.spaceId)} Reviews</em></span><strong>${spaceCommentSvc.getSpaceRating(spaceVO.spaceId)}</strong></div></li>
									</ul>
								</div>
							</div>
								<form method="post" action="<%=request.getContextPath()%>/MemberFavoriteServlet.do" id="form_getMemberFavoriteStatus${s.index}">
									<input type="hidden" name="memberId" value="${userVO.memberId}">
									<input type="hidden" name="spaceId" value="${spaceVO.spaceId}">
									<input type="hidden" name="action" value="getMemberFavoriteStatus">
								</form>
								</c:if>
									<script>
									
								    	$(document).ready(function () {							
								    		$('.mfs${s.index}').on('click', function() {
								    			alert("test");
									            $.ajax({
									                url: "<%=request.getContextPath()%>/MemberFavoriteServlet.do",
									                type: "POST",
									                dataType: "json",
									                data: $("#form_getMemberFavoriteStatus${s.index}").serialize(),
									                beforeSend: function () {},
									                success: function (data) {
									                    console.log("success");
									                    if(data == null){
									                    	$(this).removeClass('liked');
									                    } else{
									                    	$(this).addClass('liked');
									                    }
									                },
									                error: function (xhr) {
									                    consoloe.log("error");
									                },
									                complete: function (xhr) {
									                }
									            });
									            return false;
									        });
									    });
				    				</script>
							</c:forEach>
							<!-- /box_grid -->
						</div>
						<!-- /row -->
				</div>
				<!-- /isotope-wrapper -->
			
				</div>
				<!-- /col -->
			</div>		
		</div>
		<!-- /container -->
	</main>
	<!--/main-->
	</div>
	<!-- page -->
	
	<div id="toTop"></div><!-- Back to top button -->
	
	<!-- common script-->
    <script src="<%=request.getContextPath()%>/frontend/js/common_scripts.js"></script>
    <script src="<%=request.getContextPath()%>/frontend/js/main.js"></script>
	<script src="<%=request.getContextPath()%>/frontend/assets/validate.js"></script>
	
	 <!-- Map -->
	<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyBHWGXYppmIEDkttcTX_yZk6hkQZEI6uC0&callback=initMap"></script>
	<script src="<%=request.getContextPath()%>/frontend/js/markerclusterer.js"></script>
	
	<script>
	$('#collapseMap').on('shown.bs.collapse', function(e){
		(function(A) {

		if (!Array.prototype.forEach)
			A.forEach = A.forEach || function(action, that) {
				for (var i = 0, l = this.length; i < l; i++)
					if (i in this)
						action.call(that, this[i], i, this);
				};

			})(Array.prototype);		
			var
			mapObject,
			markers = [],
			markersData = {
				'Marker': [
					<c:forEach var='spaceVO' items='${spaceVO}'>
						{
							type_point: '${spaceVO.spaceType}',
							name: 'TEST',
							location_latitude: '${spaceVO.spaceLat}', 
							location_longitude: '${spaceVO.spaceLng}',
							map_image_url: '<%=request.getContextPath()%>/space/showonepicture?spaceId=${spaceVO.spaceId}',
							rate: '${spaceCommentSvc.getSpaceRating(spaceVO.spaceId)}',
							name_point: '${spaceVO.spaceName}',
							phone: '+ 暫無提供',
							url_point: '<%=request.getContextPath()%>/space/space.do?action=getOne_For_Display&spaceId=${spaceVO.spaceId}'
						},
					</c:forEach>
				]
			};

				var mapOptions = {
					zoom: 15,
					center: new google.maps.LatLng(25.0520567, 121.5428842),
					mapTypeId: google.maps.MapTypeId.ROADMAP,

					mapTypeControl: false,
					mapTypeControlOptions: {
						style: google.maps.MapTypeControlStyle.DROPDOWN_MENU,
						position: google.maps.ControlPosition.LEFT_CENTER
					},
					panControl: false,
					panControlOptions: {
						position: google.maps.ControlPosition.TOP_RIGHT
					},
					zoomControl: true,
					zoomControlOptions: {
						style: google.maps.ZoomControlStyle.LARGE,
						position: google.maps.ControlPosition.TOP_LEFT
					},
					scrollwheel: false,
					scaleControl: false,
					scaleControlOptions: {
						position: google.maps.ControlPosition.TOP_LEFT
					},
					streetViewControl: true,
					streetViewControlOptions: {
						position: google.maps.ControlPosition.LEFT_TOP
					},
					styles: [
						{
							"featureType": "administrative.country",
							"elementType": "all",
							"stylers": [
								{
									"visibility": "off"
								}
							]
						},
						{
							"featureType": "administrative.province",
							"elementType": "all",
							"stylers": [
								{
									"visibility": "off"
								}
							]
						},
						{
							"featureType": "administrative.locality",
							"elementType": "all",
							"stylers": [
								{
									"visibility": "off"
								}
							]
						},
						{
							"featureType": "administrative.neighborhood",
							"elementType": "all",
							"stylers": [
								{
									"visibility": "off"
								}
							]
						},
						{
							"featureType": "administrative.land_parcel",
							"elementType": "all",
							"stylers": [
								{
									"visibility": "off"
								}
							]
						},
						{
							"featureType": "landscape.man_made",
							"elementType": "all",
							"stylers": [
								{
									"visibility": "simplified"
								}
							]
						},
						{
							"featureType": "landscape.natural.landcover",
							"elementType": "all",
							"stylers": [
								{
									"visibility": "on"
								}
							]
						},
						{
							"featureType": "landscape.natural.terrain",
							"elementType": "all",
							"stylers": [
								{
									"visibility": "off"
								}
							]
						},
						{
							"featureType": "poi.attraction",
							"elementType": "all",
							"stylers": [
								{
									"visibility": "off"
								}
							]
						},
						{
							"featureType": "poi.business",
							"elementType": "all",
							"stylers": [
								{
									"visibility": "off"
								}
							]
						},
						{
							"featureType": "poi.government",
							"elementType": "all",
							"stylers": [
								{
									"visibility": "off"
								}
							]
						},
						{
							"featureType": "poi.medical",
							"elementType": "all",
							"stylers": [
								{
									"visibility": "off"
								}
							]
						},
						{
							"featureType": "poi.park",
							"elementType": "all",
							"stylers": [
								{
									"visibility": "on"
								}
							]
						},
						{
							"featureType": "poi.park",
							"elementType": "labels",
							"stylers": [
								{
									"visibility": "off"
								}
							]
						},
						{
							"featureType": "poi.place_of_worship",
							"elementType": "all",
							"stylers": [
								{
									"visibility": "off"
								}
							]
						},
						{
							"featureType": "poi.school",
							"elementType": "all",
							"stylers": [
								{
									"visibility": "off"
								}
							]
						},
						{
							"featureType": "poi.sports_complex",
							"elementType": "all",
							"stylers": [
								{
									"visibility": "off"
								}
							]
						},
						{
							"featureType": "road.highway",
							"elementType": "all",
							"stylers": [
								{
									"visibility": "off"
								}
							]
						},
						{
							"featureType": "road.highway",
							"elementType": "labels",
							"stylers": [
								{
									"visibility": "off"
								}
							]
						},
						{
							"featureType": "road.highway.controlled_access",
							"elementType": "all",
							"stylers": [
								{
									"visibility": "off"
								}
							]
						},
						{
							"featureType": "road.arterial",
							"elementType": "all",
							"stylers": [
								{
									"visibility": "simplified"
								}
							]
						},
						{
							"featureType": "road.local",
							"elementType": "all",
							"stylers": [
								{
									"visibility": "simplified"
								}
							]
						},
						{
							"featureType": "transit.line",
							"elementType": "all",
							"stylers": [
								{
									"visibility": "off"
								}
							]
						},
						{
							"featureType": "transit.station.airport",
							"elementType": "all",
							"stylers": [
								{
									"visibility": "off"
								}
							]
						},
						{
							"featureType": "transit.station.bus",
							"elementType": "all",
							"stylers": [
								{
									"visibility": "off"
								}
							]
						},
						{
							"featureType": "transit.station.rail",
							"elementType": "all",
							"stylers": [
								{
									"visibility": "off"
								}
							]
						},
						{
							"featureType": "water",
							"elementType": "all",
							"stylers": [
								{
									"visibility": "on"
								}
							]
						},
						{
							"featureType": "water",
							"elementType": "labels",
							"stylers": [
								{
									"visibility": "off"
								}
							]
						}
					]
				};
				var
				marker;
				mapObject = new google.maps.Map(document.getElementById('map'), mapOptions);
				for (var key in markersData)
					markersData[key].forEach(function (item) {
						marker = new google.maps.Marker({
							position: new google.maps.LatLng(item.location_latitude, item.location_longitude),
							map: mapObject,
							icon: '../frontend/img/pins/' + key + '.png',
						});

						if ('undefined' === typeof markers[key])
							markers[key] = [];
						markers[key].push(marker);
						google.maps.event.addListener(marker, 'click', (function () {
					  closeInfoBox();
					  getInfoBox(item).open(mapObject, this);
					  mapObject.setCenter(new google.maps.LatLng(item.location_latitude, item.location_longitude));
					 }));

		});
		
			new MarkerClusterer(mapObject, markers[key]);
		
			function hideAllMarkers () {
				for (var key in markers)
					markers[key].forEach(function (marker) {
						marker.setMap(null);
					});
			};

			function closeInfoBox() {
				$('div.infoBox').remove();
			};

			function getInfoBox(item) {
				return new InfoBox({
					content:
					'<div class="marker_info" id="marker_info">' +
					'<img src="' + item.map_image_url + '" alt="" width="240" height="140"/>' +
					'<span>'+ 
						'<span class="infobox_rate">'+ item.rate +'</span>' +
						'<h3>'+ item.name_point +'</h3>' +
					'<em>'+ item.type_point +'</em>' +
					'<strong>'+ item.description_point +'</strong>' +
					'<a href="'+ item.url_point + '" class="btn_infobox_detail"></a>' +
						'<a href="tel://'+ item.phone +'" class="btn_infobox_phone">'+ item.phone +'</a>' +
						'</span>' +
					'</div>',
					disableAutoPan: false,
					maxWidth: 0,
					pixelOffset: new google.maps.Size(10, 92),
					closeBoxMargin: '',
					closeBoxURL: "../frontend/img/close_infobox.png",
					isHidden: false,
					alignBottom: true,
					pane: 'floatPane',
					enableEventPropagation: true
				});


			};

	    });
	</script>
	<script src="<%=request.getContextPath()%>/frontend/js/infobox.js"></script>

	<!-- Masonry Filtering -->
	<script src="js/isotope.min.js"></script>
	<script>
	$(window).on('load', function(){
	  var $container = $('.isotope-wrapper');
	  $container.isotope({ itemSelector: '.isotope-item', layoutMode: 'masonry' });
	});

	$('.filters_listing').on( 'click', 'input', 'change', function(){
	  var selector = $(this).attr('data-filter');
	  $('.isotope-wrapper').isotope({ filter: selector });
	});
	</script>
	
	<!-- Range Slider -->
	<script>
		 $("#range").ionRangeSlider({
            hide_min_max: true,
            keyboard: true,
            min: 30,
            max: 180,
            from: 60,
            to: 130,
            type: 'double',
            step: 1,
            prefix: "Km ",
            grid: false
        });
	</script>
	
	<script>
	 $(function () {
         var len = 16; 
         $(".ellipsis").each(function (i) {
             if ($(this).text().length > len) {
                 $(this).attr("title", $(this).text());
                 var text = $(this).text().substring(0, len - 1);
                 $(this).text(text+"...").append
                 ('<a href="#" style="font-size:8px;font-weight:bold">(Read More)</a>'); 
             }
         });
     });
	 
	 $(function () {
         var len = 26; 
         $(".address_ellipsis").each(function (i) {
             if ($(this).text().length > len) {
                 $(this).attr("title", $(this).text());
                 var text = $(this).text().substring(0, len - 1);
                 $(this).text(text+"..."); 
             }
         });
     });
	</script>
</body>
</html>