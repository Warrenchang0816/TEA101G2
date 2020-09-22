<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.spacePhoto.model.*"%>

<%
  SpacePhotoVO spacePhotoVO = (SpacePhotoVO) request.getAttribute("addSpacePhoto");
  LinkedList<String> errorMsgs = (LinkedList<String>) request.getAttribute("errorMsgs");
  Base64.Encoder encode = Base64.getEncoder();
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>addSpacePhoto</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

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

<h3>新增場地圖片:</h3>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/SpacePhotoServlet" name="form1" enctype="multipart/form-data">
<table>

			<tr>
				<td>場地編號:</td>
				<td><input type="TEXT" name="spaceId" size="45"
					value="<%= (spacePhotoVO == null)? "" : spacePhotoVO.getSpaceId()%>"/>
					<span style="color:red"><%= (errorMsgs == null)? "" : (!spacePhotoVO.getSpaceId().equals(""))? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr>
				<td>場地圖片:</td>
				<td><input type="file" name="spacePhoto" class="spacePhoto"/>
					<span style="color:red"><%= (errorMsgs == null)? "" : !(spacePhotoVO.getSpacePhoto() == null)? "" : "  " + errorMsgs.poll()%></span></td>
			</tr>
			<tr><div class= "preview" id="preview"><span class="text1">預覽圖</span></div>
			

		</table>

<br>
<input type="hidden" name="action" value="addSpacePhoto">
<button name="addSpacePhoto" value="新增" type="submit" class="addSpacePhoto" onclick="javascript:return confirm('確認新增?');">送出新增</button>
<a href='<%=request.getContextPath()%>/frontend/spacePhoto/spacePhoto.jsp'><input type="button" value="取消新增"></a>

</FORM>

<script src="<%=request.getContextPath()%>/frontend/jquery.js"></script>
<script src="<%=request.getContextPath()%>/frontend/spacePhoto/spacePhoto.js"></script>

</body>


</html>