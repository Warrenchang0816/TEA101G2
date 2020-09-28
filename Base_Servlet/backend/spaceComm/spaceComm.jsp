<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.spaceComm.model.*"%>

<%
  SpaceCommVO spaceCommVO = (SpaceCommVO) request.getAttribute("selectOneSpaceComm");
  LinkedList<String> errorMsgs = (LinkedList<String>) request.getAttribute("errorMsgs");
%>
    
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>SpaceComm</title>
<h1>場地評論頁面</h1>
</head>

<body>
<a href='<%=request.getContextPath()%>/backend/space/space.jsp'><input type="button" value="回場地頁面"></a>
	<table>
		<ul>
			<li>
				<form method="post" action="<%=request.getContextPath()%>/SpaceCommServlet">
					<input type="text" name="spaceCommId" placeholder="請輸入場地編號"
						value="<%= (spaceCommVO == null)? "" : spaceCommVO.getSpaceCommId()%>"/>
					<input type="hidden" name="action" value="backend_SelectOneSpaceComm">
					<input type="submit" value="查詢">
					<span style="color:red"><%= (errorMsgs == null)? "" : "  " + errorMsgs.poll()%></span>
				</form>
			</li>
			
			<jsp:useBean id="spaceCommSvc" scope="page" class="com.spaceComm.model.SpaceCommService" />
			<li>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/SpaceCommServlet">
					<b>選擇場地編號:</b> 
					<select size="1" name="spaceCommId">
						<c:forEach var="spaceCommVO" items="<%= spaceCommSvc.selectAllSpaceComm() %>">
							<option value="${spaceCommVO.spaceCommId}">${spaceCommVO.spaceCommId}
						</c:forEach>
					</select> <input type="hidden" name="action" value="backend_SelectOneSpaceComm">
					<input type="submit" value="查詢">
				</FORM>
			</li>

			<li>
				<a href='<%=request.getContextPath()%>/backend/spaceComm/addSpaceComm.jsp'><input type="button" value="新增"></a>
			</li>

			<li>
				<a href='<%=request.getContextPath()%>/backend/spaceComm/selectAllSpaceComm.jsp'><input type="button" value="列表"></a>
			</li>
		</ul>
	</table>


<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
<script src="<%=request.getContextPath()%>/backend/jquery.js"></script>

</body>
</html>