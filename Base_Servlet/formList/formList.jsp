<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.formList.model.*"%>

<%
	FormListVO formListVO = (FormListVO) request.getAttribute("selectOneFormList");
 	LinkedList<String> errorMsgs = (LinkedList<String>) request.getAttribute("errorMsgs");
%>
    
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>SupplyList</title>
<h1>客服表單頁面</h1>
</head>

<body>
<a href='<%=request.getContextPath()%>/backend/BigSpace.jsp'><input type="button" value="回場地頁面"></a>
	<table>
		<ul>
			<li>
				<form method="post" action="<%=request.getContextPath()%>/FormListServlet">
					<input type="text" name="formListId" placeholder="請輸入客服表單編號">
					<input type="hidden" name="action" value="backend_SelectOneFormList">
					<input type="submit" value="查詢">
					<span style="color:red"><%=(errorMsgs == null)? "" : "  " + errorMsgs.poll()%></span>
				</form>
			</li>
			
			<jsp:useBean id="formListSvc" scope="page" class="com.formList.model.FormListService" />
			<li>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FormListServlet">
					<b>選擇客服表單編號:</b> 
					<select size="1" name="formListId">
						<c:forEach var="formListVO" items="<%= formListSvc.selectAllFormList() %>">
							<option value="${formListVO.formListId}">${formListVO.formListId}
						</c:forEach>
					</select> <input type="hidden" name="action" value="backend_SelectOneFormList">
					<input type="submit" value="查詢">
				</FORM>
			</li>

			<li>
				<a href='<%=request.getContextPath()%>/backend/formList/addFormList.jsp'><input type="button" value="新增"></a>
			</li>

			<li>
				<a href='<%=request.getContextPath()%>/backend/formList/selectAllFormList.jsp'><input type="button" value="列表"></a>
			</li>
		</ul>
	</table>


<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
<script src="<%=request.getContextPath()%>/backend/jquery.js"></script>

</body>
</html>