<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.memberComment.model.*"%>
<%@ page import="java.util.*"%>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<style>

/* Button used to open the chat form - fixed at the bottom of the page */
.open-button {
  background-color: blue;
  color: white;
  border: none;
  cursor: pointer;
  opacity: 0.8;
  position: fixed;
  bottom: 8px;
  right: 78px;
  width: 70px;
  height: 70px;
  border-radius: 50%;
}

.open-online {
  background-color: #555;
  color: white;
  border: none;
  cursor: pointer;
  opacity: 0.8;
  position: fixed;
  bottom: 10px;
  right: 400px;
  width: 100px;
  height: 60px;
}
</style>

<c:choose>
	<c:when test="${userVO != null}">
		<header class="header map_view menu_fixed">
			<div id="preloader">
				<div data-loader="circle-side"></div>
			</div>
			<!-- /Page Preload -->
			<div id="logo">
				<a href="<%=request.getContextPath()%>/frontend/home.jsp"><img src="<%=request.getContextPath()%>/frontend/image/logo.png" width="180" height="50" data-retina="true" />
				</a>
			</div>
			<ul id="top_menu" style="margin-top: 8px">
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
					<li><span><a href="#0">場地</a></span>
						<ul>
							<li>
								<span><a href="#0">場地管理</a></span>
								<ul>
									<li><a href="<%=request.getContextPath()%>/frontend/space/addSpace.jsp">新增場地</a></li>
									<li><a href="<%=request.getContextPath()%>/frontend/space/memberSpace.jsp">我的場地</a></li>
								</ul>
							</li>
						</ul>
					</li>
					<li><span><a href="#0">訂單</a></span>
						<ul>
							<li>
								<span><a href="#0">訂單管理</a></span>
								<ul>
									<li><a href="<%=request.getContextPath()%>/frontend/ordermaster/selectAllOrderMaster.jsp">我的訂單</a></li>
								</ul>
							</li>
						</ul>
					</li>
					<li><span><a href="#">常見問題</a></span></li>
					<li><span><a href="<%=request.getContextPath()%>/frontend/formList/faq.jsp">客服表單</a></span></li>
					<li><span><a href="#"> <img
								src="<%=request.getContextPath()%>/memberPhoto/showpicture?memberId=${userVO.memberId}"
								width="36" height="36"
								style="margin-right: 110px; border-radius: 100%">
						</a></span>
						<ul>
							<li><a
								href="<%=request.getContextPath()%>/frontend/member/memberSetting.jsp"><i
									class="fa fa-cog" style="margin-right: 5px"></i> 設定</a></li>
							<!-- Setting -->
							<li>
								<form method="post"
									action="<%=request.getContextPath()%>/MemberServlet.do"
									id="form_getOneMember">
									<input type="hidden" name="memberId" value="${userVO.memberId}">
									<input type="hidden" name="action" value="getOneMember">
								</form> <a href="#"
								onclick="document.getElementById('form_getOneMember').submit()"><i
									class="fa fa-user-o" style="margin-right: 5px"></i> 個人頁面</a>
							</li>

							<!-- Profile -->
							<li><a
								href="<%=request.getContextPath()%>/frontend/memberFavorite/memberFavoriteList.jsp"><i
									class="fa fa-heart" style="margin-right: 5px"></i> 我的收藏</a></li>
							<!-- Favorite -->
							<li><a href="<%=request.getContextPath()%>/LogoutHandler.do"><i
									class="fa fa-sign-out" style="margin-right: 5px"></i> 登出</a></li>
							<!-- Logout -->
						</ul></li>
				</ul>
			</nav>
		</header>
	</c:when>
	<c:otherwise>
		<header class="header map_view menu_fixed">
			<div id="preloader">
				<div data-loader="circle-side"></div>
			</div>
			<!-- /Page Preload -->
			<div id="logo">
				<a href="<%=request.getContextPath()%>/frontend/home.jsp"><img src="<%=request.getContextPath()%>/frontend/image/logo.png" width=180" height="50" data-retina="true" />
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
<!-- 					<li><span><a href="#0">AAAAA</a></span></li> -->
<!-- 					<li><span><a href="#0">BBBBB</a></span></li> -->
<!-- 					<li><span><a href="adventure.html">CCCCC</a></span></li> -->
<!-- 					<li><span><a href="#0">DDDDD</a></span></li> -->
					<li><span><a href="<%=request.getContextPath()%>/frontend/login.jsp"> <span style="font-style: italic;">
					登入</span>
						</a></span></li>
				</ul>
			</nav>
		</header>
	</c:otherwise>
</c:choose>

<%@ include file="/frontend/other/memberChat.jsp" %>
<%@include file="/frontend/other/memberOnlineList.jsp"%>

<button class="open-button" id="openChat-button" onclick="openChat();" style="z-index:10">即時聊天</button>
<%--<button class="open-online" style="z-index:10" onclick="openOnlineList()">OnlineList</button> --%>