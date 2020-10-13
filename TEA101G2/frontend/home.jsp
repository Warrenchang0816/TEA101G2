<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.memberComment.model.*"%>
<%@ page import="java.util.*"%>
<% Base64.Encoder encode = Base64.getEncoder();%>
<%MemberVO userVO = (MemberVO) session.getAttribute("userVO");%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description"
        content="Panagea - Premium site template for travel agencies, hotels and restaurant listing.">
    <meta name="author" content="Ansonika">
    <title>Home Test</title>

    <!-- GOOGLE WEB FONT -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">

    <!-- BASE CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="<%=request.getContextPath()%>/frontend/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/frontend/css/style.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/frontend/css/vendors.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/frontend/admin_section/css/admin.css">
    <!-- ********** -->
    <script src="<%=request.getContextPath()%>/frontend/js/jquery-3.5.1.min.js"></script>
    <!-- ********** -->
    <script src="https://cdn.tutorialjinni.com/simplePagination.js/1.6/jquery.simplePagination.js"></script>
</head>

<body>
    <div id="page">
        <header class="header map_view menu_fixed">
            <div id="preloader">
                <div data-loader="circle-side"></div>
            </div>
            <!-- /Page Preload -->
            <div id="logo">
                <a href="index.html">
                </a>
            </div>
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
                    <li><span><a href="#0">AAAAA</a></span></li>
                    <li><span><a href="#0">BBBBB</a></span></li>
                    <li><span><a href="adventure.html">CCCCC</a></span></li>
                    <li><span><a href="#0">DDDDD</a></span></li>
                    <li><span><a href="#"> <span style="font-style: italic;">LogIn</span>
                            </a></span></li>
                </ul>
            </nav>
        </header>
        <!-- /header -->
        <section class="hero_single version_2">
            <div class="wrapper">
                <div class="container">
                    <h3>Book unique experiences</h3>
                    <p>Expolore top rated tours, hotels and restaurants around the world</p>
                    <form method="post" action="<%=request.getContextPath()%>/space/space.do">
                        <div class="row no-gutters custom-search-input-2">
                            <div class="col-lg-3">
                                <div class="form-group">
                                    <input type="text" name="spaceAddress" class="form-control" placeholder="區域">
                                    <i class="icon_pin_alt"></i>
                                </div>
                            </div>
                            <jsp:useBean id="spaceSvc" scope="page" class="com.space.model.SpaceService" />
                            <div class="col-lg-3">
                                <select name="spaceType" class="wide">
                                    	<option value="" selected disabled hidden>類型...</option>
                                    <c:forEach var="spaceVO" items="${spaceSvc.getAll()}">
										<option value="${spaceVO.spaceType}"${(param.spaceType==spaceVO.spaceType)? 'selected':'' }>${spaceVO.spaceType}
									</c:forEach>
								</select>
                            </div>
                            <div class="col-lg-4">
                                <div class="form-group">
                                    <input type="text" name="spaceContain" class="form-control" placeholder="人數">
                                    <i class="icon_search"></i>
                                </div>
                            </div>
                            <div class="col-lg-2">
                           		<input type="hidden" name="action" value="search_space">
                                <input type="submit" class="btn_search" value="Search">
                            </div>
                        </div>
                        <!-- /row -->
                    </form>
                </div>
            </div>
        </section>
        <!-- /search -->
        <div class="container container-custom margin_60_0">
            <div class="main_title_2">
                <span><em></em></span>
                <h2>Our Popular Tours</h2>
                <p>Cum doctus civibus efficiantur in imperdiet deterruisset.</p>
            </div>
        </div>

        <div class="container margin_60_35">
            <div class="wrapper-grid">
                <div class="row">
                    <div class="col-xl-4 col-lg-6 col-md-6">
                        <div class="box_grid">
                            <figure>
                                <a href="#0" class="wish_bt liked"></a>
                                <a href="tour-detail.html"><img src="img/tour_1.jpg" class="img-fluid" alt=""
                                        width="800" height="533"></a>
                                <small>Historic</small>
                                <div class="read_more"><span>Read more</span></div>
                            </figure>
                            <div class="wrapper">
                                <h3><a href="tour-detail.html">Arc Triomphe</a></h3>
                                <p>Id placerat tacimates definitionem sea, prima quidam vim no. Duo nobis persecuti cu.
                                </p>
                                <span class="price">From <strong>$54</strong> /per person</span>
                            </div>
                            <ul>
                                <li><i class="icon_clock_alt"></i> 1h 30min</li>
                                <li>
                                    <div class="score"><span>Superb<em>350 Reviews</em></span><strong>8.9</strong></div>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <!-- /box_grid -->
                    <div class="col-xl-4 col-lg-6 col-md-6">
                        <div class="box_grid">
                            <figure>
                                <a href="#0" class="wish_bt liked"></a>
                                <a href="tour-detail.html"><img src="img/tour_2.jpg" class="img-fluid" alt=""
                                        width="800" height="533"></a>
                                <small>Churches</small>
                                <div class="read_more"><span>Read more</span></div>
                            </figure>
                            <div class="wrapper">
                                <h3><a href="tour-detail.html">Notredam</a></h3>
                                <p>Id placerat tacimates definitionem sea, prima quidam vim no. Duo nobis persecuti cu.
                                </p>
                                <span class="price">From <strong>$124</strong> /per person</span>
                            </div>
                            <ul>
                                <li><i class="icon_clock_alt"></i> 1h 30min</li>
                                <li>
                                    <div class="score"><span>Good<em>350 Reviews</em></span><strong>7.0</strong></div>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <!-- /box_grid -->
                    <div class="col-xl-4 col-lg-6 col-md-6">
                        <div class="box_grid">
                            <figure>
                                <a href="#0" class="wish_bt liked"></a>
                                <a href="tour-detail.html"><img src="img/tour_3.jpg" class="img-fluid" alt=""
                                        width="800" height="533"></a>
                                <small>Historic</small>
                                <div class="read_more"><span>Read more</span></div>
                            </figure>
                            <div class="wrapper">
                                <h3><a href="tour-detail.html">Versailles</a></h3>
                                <p>Id placerat tacimates definitionem sea, prima quidam vim no. Duo nobis persecuti cu.
                                </p>
                                <span class="price">From <strong>$25</strong> /per person</span>
                            </div>
                            <ul>
                                <li><i class="icon_clock_alt"></i> 1h 30min</li>
                                <li>
                                    <div class="score"><span>Good<em>350 Reviews</em></span><strong>7.0</strong></div>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <!-- /box_grid -->
                </div>
                <!-- /row -->
            </div>
            <!-- /isotope-wrapper -->
        </div>
    </div>
    <!-- COMMON SCRIPTS -->
     <script src="<%=request.getContextPath()%>/frontend/js/common_scripts.js"></script>
    <script src="<%=request.getContextPath()%>/frontend/js/main.js"></script>
    <script src="<%=request.getContextPath()%>/admin_section/vendor/jquery.magnific-popup.min.js"></script>
</body>
</html>