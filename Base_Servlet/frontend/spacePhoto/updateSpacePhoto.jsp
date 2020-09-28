<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.spacePhoto.model.*"%>

<% 
SpacePhotoVO spacePhotoVO = (SpacePhotoVO)request.getAttribute("selectOneUpdate");
LinkedList<String> errorMsgs = (LinkedList<String>) request.getAttribute("errorMsgs");
Base64.Encoder encode = Base64.getEncoder();
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>updateSpacePhoto</title>

<style>
    img.preview {
      width: 200px;
    }
    #preview {
      border: 1px solid lightgray;
      display: inline-block;
      width: 100px;
      min-height: 150px;
      position: relative;
    }

    #preview span.text1 {
      position: absolute;
      display: inline-block;
      left: 50%;
      top: 50%;
      transform: translate(-50%, -50%);
      z-index: -1;
      color: lightgray;
    }

    #preview img.preview_img {
      width: 100%;
    }
    #preview.-on {
      border: 0px solid lightgray;
      display: inline-block;
      width: 100px;
      min-height: 150px;
      position: relative;
    }
</style>

</head>

<body>

<h3>場地圖片修改:</h3>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/SpacePhotoServlet" name="form1" enctype="multipart/form-data">
<table>

			<tr>
				<td>場地圖片編碼:</td>
				<td><%=spacePhotoVO.getSpacePhotoId()%></td>
			</tr>
			<tr>
				<td>場地編號:</td>
				<td><input type="TEXT" name="spaceId" size="45" 
					value="<%= (spacePhotoVO == null)? "" : spacePhotoVO.getSpaceId()%>"/>
					<span style="color:red"><%= (errorMsgs == null)? "" : (!spacePhotoVO.getSpaceId().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			
			<jsp:useBean id="spacePhotoDAO" scope="page" class="com.spacePhoto.model.SpacePhotoDAO" />
			<tr>
				<td>場地圖片:</td>
				<td><input type="file" name="spacePhoto" class="spacePhoto"/>
					<span style="color:red"><%= (errorMsgs == null)? "" : !(spacePhotoVO.getSpacePhoto() == null)? "" : "  " + errorMsgs.poll()%></span></td></td>
			</tr>
			<tr><div class= "preview" id="preview">
			<img src="data:image/png;base64,<%= encode.encodeToString(spacePhotoDAO.selectOne(spacePhotoVO.getSpacePhotoId()).getSpacePhoto())%>" class ="preview">
				<span class="text1">預覽圖</span></div>
		
		</table>

<br>
<input type="hidden" name="action" value="updateSpacePhoto">
<input type="hidden" name="spacePhotoId" value="<%=spacePhotoVO.getSpacePhotoId()%>">
<button name="update" value="修改" type="submit" class="update" onclick="javascript:return confirm('確認修改?');">送出修改</button>
<a href='<%=request.getContextPath()%>/frontend/spacePhoto/spacePhoto.jsp'><input type="button" value="取消修改"></a>


</FORM>

<script src="<%=request.getContextPath()%>/frontend/jquery.js"></script>
<script src="<%=request.getContextPath()%>/frontend/spacePhoto/spacePhoto.js"></script>

</body>

</html>