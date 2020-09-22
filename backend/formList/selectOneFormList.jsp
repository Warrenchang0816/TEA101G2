<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.formList.model.*"%>

<%
	FormListVO formListVO = (FormListVO)request.getAttribute("selectOneFormList");
	Base64.Encoder encode = Base64.getEncoder();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>selectOneSupplyList</title>

<a href='<%=request.getContextPath()%>/backend/formList/formList.jsp'><input type="button" value="回客服表單頁面"></a>

<style>
    img {
      width: 200px;
    }
</style>
<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body>


<table>
	<tr>
		<th>客服表單編號</th>
		<th>會員編號</th>
		<th>員工編號</th>
		<th>表單申請日期</th>
		<th>員工編號</th>
		<th>表單類型</th>
		<th>表單內容</th>
		<th>表單圖片</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<tr>
		<td><%= formListVO.getFormListId()%></td>
		<td><%= formListVO.getMembrId()%></td>
		<td><%= formListVO.getEmpId()%></td>
		<td><%= formListVO.getFormListCreateDate()%></td>
		<td><%= formListVO.getFormListType()%></td>
		<td><%= formListVO.getFormListTitle()%></td>
		<td><%= formListVO.getFormListContext()%></td>
		<td><img src="data:image/png;base64,<%=encode.encodeToString(formListVO.getFormListFile())%>" class="perview"/></td>
		
		
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FormListServlet" style="margin-bottom: 0px;">
			    <input type="submit" value="修改">
			    <input type="hidden" name="formListId"  value="<%= formListVO.getFormListId()%>">
			    <input type="hidden" name="action"	value="backend_SelectOneUpdate"></FORM>
		</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FormListServlet" style="margin-bottom: 0px;">
			    <button name="delete" value="刪除" type="submit" class="delete" onclick="javascript:return confirm('確認刪除?');">刪除</button>
			    <input type="hidden" name="formListId"  value="<%= formListVO.getFormListId()%>">
			    <input type="hidden" name="action" value="backend_DdeleteFormList"></FORM>
		</td>
	</tr>

</table>


</body>
</html>