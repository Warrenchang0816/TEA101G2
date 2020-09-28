<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.spacePhoto.model.*"%>

<%    
	SpacePhotoService spacePhotoServ = new SpacePhotoService();
    List<SpacePhotoVO> list = spacePhotoServ.selectAllSpacePhoto();
    pageContext.setAttribute("list",list);
   
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>selectAllSpacePhoto</title>

<a href='<%=request.getContextPath()%>/backend/spacePhoto/spacePhoto.jsp'><input type="button" value="回場地圖片頁面"></a>
<a href='<%=request.getContextPath()%>/backend/spacePhoto/addSpacePhoto.jsp'><input type="button" value="新增地圖片"></a>
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
	width: 800px;
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

<style>
    img {
      width: 200px;
    }
</style>

</head>
<body>


<table>
	<tr>
		<th>場地圖片編號</th>
		<th>場地編號</th>
		<th>場地圖片</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>

<c:forEach var="spacePhotoVO" items="${list}" begin="0" end="<%=list.size()%>">
	<% Base64.Encoder encode = Base64.getEncoder();%>
	<tr>
		<td>${spacePhotoVO.spacePhotoId}</td>
		<td>${spacePhotoVO.spaceId}</td>
		<td><img src="data:image/png;base64,<%=encode.encodeToString(((SpacePhotoVO)pageContext.getAttribute("spacePhotoVO")).getSpacePhoto())%>" class="perview"/></td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/SpacePhotoServlet" style="margin-bottom: 0px;">
			    <input type="submit" value="修改">
			    <input type="hidden" name="spacePhotoId"  value="${spacePhotoVO.spacePhotoId}">
			    <input type="hidden" name="action"	value="backend_SelectOneUpdate"></FORM>
		</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/SpacePhotoServlet" style="margin-bottom: 0px;">
			    <button name="delete" value="刪除" type="submit" class="delete" onclick="javascript:return confirm('確認刪除?');">刪除</button>
			    <input type="hidden" name="spacePhotoId"  value="${spacePhotoVO.spacePhotoId}">
			    <input type="hidden" name="action" value="backend_DeleteSpacePhoto"></FORM>
		</td>
	
</c:forEach>


</table>


</body>
</html>