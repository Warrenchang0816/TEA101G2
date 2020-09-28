<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.spaceDetail.model.*"%>

<%
	SpaceDetailVO spaceDetailVO = (SpaceDetailVO) request.getAttribute("selectOneSpaceDetail");
	LinkedList<String> errorMsgs = (LinkedList<String>) request.getAttribute("errorMsgs");
%>
    
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>SpaceDetail</title>
<h1>場地明細頁面</h1>
</head>

<body>
<a href='<%=request.getContextPath()%>/frontend/space/space.jsp'><input type="button" value="回場地頁面"></a>
	<table>
		<ul>
			<li>
				<form method="post" action="<%=request.getContextPath()%>/SpaceDetailServlet">
					<input type="text" name="spaceDetailId" placeholder="請輸入場地明細編號">
					<input type="hidden" name="action" value="selectOneSpaceDetail">
					<input type="submit" value="查詢">
					<span style="color:red"><%= (errorMsgs == null)? "" : "  " + errorMsgs.poll()%></span>
				</form>
			</li>
			
			<jsp:useBean id="spaceDetailSvc" scope="page" class="com.spaceDetail.model.SpaceDetailService" />
			<li>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/SpaceDetailServlet">
					<b>選擇場地編號:</b> 
					<select size="1" name="spaceDetailId">
						<c:forEach var="spaceDetailVO" items="<%= spaceDetailSvc.selectAllSpaceDetail() %>">
							<option value="${spaceDetailVO.spaceDetailId}">${spaceDetailVO.spaceDetailId}
						</c:forEach>
					</select> <input type="hidden" name="action" value="selectOneSpaceDetail">
					<input type="submit" value="查詢">
				</FORM>
			</li>

			<li>
				<a href='<%=request.getContextPath()%>/frontend/spaceDetail/addSpaceDetail.jsp'><input type="button" value="新增"></a>
			</li>

			<li>
				<a href='<%=request.getContextPath()%>/frontend/spaceDetail/selectAllSpaceDetail.jsp'><input type="button" value="列表"></a>
			</li>
		</ul>
	</table>


<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
<script src="<%=request.getContextPath()%>/frontend/jquery.js"></script>

</body>
</html>