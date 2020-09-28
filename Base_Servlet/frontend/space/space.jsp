<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.space.model.*"%>

<%
  SpaceVO spaceVO = (SpaceVO) request.getAttribute("selectOneSpace");
  LinkedList<String> errorMsgs = (LinkedList<String>) request.getAttribute("errorMsgs");
%>
    
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Space</title>
<h1>場地頁面</h1>
</head>

<body>
<a href='<%=request.getContextPath()%>/frontend/BigFullSpace.jsp'><input type="button" value="後台首頁"></a>
<a href='<%=request.getContextPath()%>/frontend/BigSpace.jsp'><input type="button" value="後台首頁"></a>
	<table>
		<ul>
			<li>
				<form method="post" action="<%=request.getContextPath()%>/SpaceServlet">
					<input type="text" name="spaceId" placeholder="請輸入場地編號" 
						value="<%= (spaceVO == null)? "" : spaceVO.getSpaceId()%>"/>
					<input type="hidden" name="action" value="selectOneSpace">
					<input type="submit" value="查詢">
					<span style="color:red"><%= (errorMsgs == null)? "" : "  " + errorMsgs.poll()%></span>
				</form>
			</li>
			
			<jsp:useBean id="spaceSvc" scope="page" class="com.space.model.SpaceService" />
			<li>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/SpaceServlet">
					<b>選擇場地編號:</b> 
					<select size="1" name="spaceId">
						<c:forEach var="spaceVO" items="<%= spaceSvc.selectAllSpace() %>">
							<option value="${spaceVO.spaceId}">${spaceVO.spaceId}
						</c:forEach>
					</select> <input type="hidden" name="action" value="selectOneSpace">
					<input type="submit" value="查詢">
				</FORM>
			</li>

			<li>
				<a href='<%=request.getContextPath()%>/frontend/space/addSpace.jsp'><input type="button" value="新增"></a>
			</li>

			<li>
				<a href='<%=request.getContextPath()%>/frontend/space/selectAllSapce.jsp'><input type="button" value="列表"></a>
			</li>
		</ul>
	</table>

	<a href='<%=request.getContextPath()%>/frontend/spaceComm/spaceComm.jsp'><input type="button" value="場地評價"></a>
	<a href='<%=request.getContextPath()%>/frontend/spaceDetail/spaceDetail.jsp'><input type="button" value="場地明細"></a>
	<a href='<%=request.getContextPath()%>/frontend/spacePhoto/spacePhoto.jsp'><input type="button" value="場地圖片"></a>


<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
<script src="<%=request.getContextPath()%>/frontend/jquery.js"></script>

</body>
</html>