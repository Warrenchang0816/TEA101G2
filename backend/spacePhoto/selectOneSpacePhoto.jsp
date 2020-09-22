<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.spacePhoto.model.*"%>

<%

SpacePhotoVO spacePhotoVO = (SpacePhotoVO)request.getAttribute("selectOneSpacePhoto");
Base64.Encoder encode = Base64.getEncoder();

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>selectOneSpacePhoto</title>

<a href='<%=request.getContextPath()%>/backend/spacePhoto/spacePhoto.jsp'><input type="button" value="回場地圖片頁面"></a>

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
	<tr>
		<td><%= spacePhotoVO.getSpacePhotoId()%></td>
		<td><%= spacePhotoVO.getSpaceId()%></td>
		<%-- <td><img src="data:image/png;base64,<%=encode.encodeToString(spacePhotoVO.getSpacePhoto())%>" class="perview"/></td> --%>
		<td><img src="<%=(spacePhotoVO.getSpacePhoto() == null)? application.getRealPath("/backend/img/BlobTest3") : "data:image/png;base64," + encode.encodeToString(spacePhotoVO.getSpacePhoto())%>" class="perview"/></td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/SpacePhotoServlet" style="margin-bottom: 0px;">
			    <input type="submit" value="修改">
			    <input type="hidden" name="spacePhotoId"  value="<%= spacePhotoVO.getSpacePhotoId()%>">
			    <input type="hidden" name="action"	value="backend_SelectOneUpdate"></FORM>
		</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/SpacePhotoServlet" style="margin-bottom: 0px;">
			    <button name="delete" value="刪除" type="submit" class="delete" onclick="javascript:return confirm('確認刪除?');">刪除</button>
			    <input type="hidden" name="spacePhotoId"  value="<%= spacePhotoVO.getSpacePhotoId()%>">
			    <input type="hidden" name="action" value="backend_DeleteSpacePhoto"></FORM>
		</td>
	</tr>

</table>


</body>
</html>