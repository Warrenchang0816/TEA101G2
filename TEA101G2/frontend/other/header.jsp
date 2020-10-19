<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.memberComment.model.*"%>
<%@ page import="java.util.*"%>
<%
	Base64.Encoder encode = Base64.getEncoder();
%>
<%
	MemberVO userVO = (MemberVO) session.getAttribute("userVO");
%>

<style>

/* Button used to open the chat form - fixed at the bottom of the page */
.open-button {
  background-color: #555;
  color: white;
  border: none;
  cursor: pointer;
  opacity: 0.8;
  position: fixed;
  bottom: 10px;
  right: 80px;
  width: 60px;
  height: 60px;
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
				<a href="index.html"> <!-- TODO <img src="img/logo_sticky.png" width="150" height="36" data-retina="true" alt=""> -->
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
					<li><span><a href="#">AAAAA</a></span></li>
					<li><span><a href="#">BBBBB</a></span></li>
					<li><span><a href="#">CCCCC</a></span></li>
					<li><span><a href="#">DDDDD</a></span></li>
					<li><span><a href="#"> <img
								src="data:image/png;base64,<%=encode.encodeToString(userVO.getMemberPhoto())%>"
								width="36" height="36"
								style="margin-right: 110px; border-radius: 100%">
						</a></span>
						<ul>
							<li><a
								href="<%=request.getContextPath()%>/frontend/member/memberSetting.jsp"><i
									class="fa fa-cog" style="margin-right: 5px"></i> Setting</a></li>
							<!-- Setting -->
							<li>
								<form method="post"
									action="<%=request.getContextPath()%>/MemberServlet.do"
									id="form_getOneMember">
									<input type="hidden" name="memberId" value="${userVO.memberId}">
									<input type="hidden" name="action" value="getOneMember">
								</form> <a href="#"
								onclick="document.getElementById('form_getOneMember').submit()"><i
									class="fa fa-user-o" style="margin-right: 5px"></i> Profile</a>
							</li>

							<!-- Profile -->
							<li><a
								href="<%=request.getContextPath()%>/frontend/memberFavorite/memberFavoriteList.jsp"><i
									class="fa fa-heart" style="margin-right: 5px"></i> Favorite</a></li>
							<!-- Favorite -->
							<li><a href="<%=request.getContextPath()%>/LogoutHandler.do"><i
									class="fa fa-sign-out" style="margin-right: 5px"></i> Logout</a></li>
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
				<a href="index.html"> <!-- TODO <img src="img/logo_sticky.png" width="150" height="36" data-retina="true" alt=""> -->
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
					<li><span><a
							href="<%=request.getContextPath()%>/frontend/login.jsp"> <span
								style="font-style: italic;">LogIn</span>
						</a></span></li>
				</ul>
			</nav>
		</header>
	</c:otherwise>
</c:choose>

<%@ include file="/frontend/other/memberChat.jsp" %>
<%@ include file="/frontend/other/memberOnlineList.jsp" %>

<button class="open-button" onclick="openChat();" style="z-index:10">Chat</button>
<button class="open-online" style="z-index:10" onclick="openOnlineList()">OnlineList</button>






