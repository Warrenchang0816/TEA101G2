<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>

<%
	EmpService empSvc = new EmpService();
    List<EmpVO> list = empSvc.selectAllEmp();
    pageContext.setAttribute("list",list);
   
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>selectAllEmp</title>

<a href='<%=request.getContextPath()%>/backend/emp/emp.jsp'><input type="button" value="回員工頁面"></a>
<a href='<%=request.getContextPath()%>/backend/emp/addEmp.jsp'><input type="button" value="新增員工"></a>
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

</head>
<body>


<table>
	<tr>
		<th>員工圖片</th>
		<th>員工帳號</th>
		<th>員工密碼</th>
		<th>員工姓名</th>
		<th>員工名稱</th>
		<th>員工email</th>
		<th>員工電話</th>
		<th>員工聯絡地址</th>
		<th>員工生日</th>
		<th>員工性別</th>
		<th>員工國籍</th>
		<th>員工到職日</th>
		<th>員工職稱</th>
		<th>員工權限</th>
		<th>員工在職狀態</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>

<c:forEach var="empVO" items="${list}" begin="0" end="<%=list.size()%>">
<% Base64.Encoder encode = Base64.getEncoder();%>
	<tr>
		<td><img src="data:image/png;base64,<%=encode.encodeToString(((EmpVO)pageContext.getAttribute("empVO")).getEmpPhoto())%>" class="perview"/></td>
		<td>${empVO.empId}</td>
		<td>${empVO.empAccount}</td>
		<td>${empVO.empPassword}</td>
		<td>${empVO.empName}</td>
		<td>${empVO.empNickname}</td>
		<td>${empVO.empEmail}</td>
		<td>${empVO.empPhone}</td>
		<td>${empVO.empAddress}</td>
		<td>${empVO.empBirth}</td>
		<td>${empVO.empSex}</td>
		<td>${empVO.empCountry}</td>
		<td>${empVO.empHireDate}</td>
		<td>${empVO.empJob}</td>
		<td>${empVO.empAuth}</td>
		<td>${empVO.empStatus}</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/EmpServlet" style="margin-bottom: 0px;">
			    <input type="submit" value="修改">
			    <input type="hidden" name="empId"  value="${empVO.empId}">
			    <input type="hidden" name="action"	value="backend_SelectOneUpdate"></FORM>
		</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/EmpServlet" style="margin-bottom: 0px;">
			    <button name="delete" value="刪除" type="submit" class="delete" onclick="javascript:return confirm('確認刪除?');">刪除</button>
			    <input type="hidden" name="empId"  value="${empVO.empId}">
			    <input type="hidden" name="action" value="backend_DeleteEmp"></FORM>
		</td>
	
</c:forEach>


</table>


</body>
</html>