<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.spacePhoto.model.*"%>

<%
  SpacePhotoVO spacePhotoVO = (SpacePhotoVO) request.getAttribute("selectOneSpaceComm");
  LinkedList<String> errorMsgs = (LinkedList<String>) request.getAttribute("errorMsgs");
%>
    
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>SpacePhoto</title>
<h1>場地圖片頁面</h1>
</head>

<body>
<a href='<%=request.getContextPath()%>/backend/space/space.jsp'><input type="button" value="回場地頁面"></a>
	<table>
		<ul>
			<li>
				<form method="post" action="<%=request.getContextPath()%>/SpacePhotoServlet">
					<input type="text" name="spacePhotoId" placeholder="請輸入場地圖片編號">
					<input type="hidden" name="action" value="backend_SelectOneSpacePhoto">
					<input type="submit" value="查詢">
					<span style="color:red"><%= (errorMsgs == null)? "" : "  " + errorMsgs.poll()%></span>
				</form>
			</li>
			
			<jsp:useBean id="spacePhotoSvc" scope="page" class="com.spacePhoto.model.SpacePhotoService" />
			<li>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/SpacePhotoServlet">
					<b>選擇場地圖片編號:</b> 
					<select size="1" name="spacePhotoId">
						<c:forEach var="spacePhotoVO" items="<%= spacePhotoSvc.selectAllSpacePhoto() %>">
							<option value="${spacePhotoVO.spacePhotoId}">${spacePhotoVO.spacePhotoId}
						</c:forEach>
					</select> <input type="hidden" name="action" value="backend_SelectOneSpacePhoto">
					<input type="submit" value="查詢">
				</FORM>
			</li>

			<li>
				<a href='<%=request.getContextPath() + request.getServletPath().substring(0, request.getServletPath().lastIndexOf("/"))%>/addSpacePhoto.jsp'><input type="button" value="新增"></a>
			</li>

			<li>
				<a href='<%=request.getContextPath() + request.getServletPath().substring(0, request.getServletPath().lastIndexOf("/"))%>/selectAllSpacePhoto.jsp'><input type="button" value="列表"></a>
			</li>
		</ul>
	</table>


<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
<script src="<%=request.getContextPath()%>/backend/jquery.js"></script>

</body>
</html>