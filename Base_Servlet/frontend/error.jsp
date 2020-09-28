<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.space.model.*"  %>
<%@ page import="java.util.*"%>

<% 
LinkedList<String> errorMsgs = (LinkedList<String>) request.getAttribute("errorMsgs");
pageContext.setAttribute("errorMsgs",errorMsgs);
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Error</title>
</head>
<body>

<h>JAVA程式錯誤!!!</h>

<%--
<div><%= errorMsgs.poll()%></div>
--%>

</body>
</html>