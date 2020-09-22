<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>

<%

EmpVO empVO = (EmpVO)request.getAttribute("selectOneEmp");
Base64.Encoder encode = Base64.getEncoder();


%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>selectOneEmp</title>

<a href='<%=request.getContextPath()%>/backend/emp/emp.jsp'><input type="button" value="回員工頁面"></a>

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
	<tr>
		<%-- <td><img src="data:image/png;base64,<%=encode.encodeToString(empVO.getEmpPhoto())%>" class="perview"/></td> --%>
		<td><img src="<%=(empVO.getEmpPhoto() == null)? application.getRealPath("/backend/img/BlobTest3") : "data:image/png;base64," + encode.encodeToString(empVO.getEmpPhoto())%>" class="perview"/></td>
		<td><%=empVO.getEmpId()%></td>
		<td><%=empVO.getEmpAccount()%></td>
		<td><%=empVO.getEmpPassword()%></td>
		<td><%=empVO.getEmpName()%></td>
		<td><%=empVO.getEmpNickname()%></td>
		<td><%=empVO.getEmpEmail()%></td>
		<td><%=empVO.getEmpPhone()%></td>
		<td><%=empVO.getEmpAddress()%></td>
		<td><%=empVO.getEmpBirth()%></td>
		<td><%=empVO.getEmpSex()%></td>
		<td><%=empVO.getEmpCountry()%></td>
		<td><%=empVO.getEmpHireDate()%></td>
		<td><%=empVO.getEmpJob()%></td>
		<td><%=empVO.getEmpAuth()%></td>
		<td><%=empVO.getEmpStatus()%></td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/EmpServlet" style="margin-bottom: 0px;">
			    <input type="submit" value="修改">
			    <input type="hidden" name="empId"  value="<%=empVO.getEmpId()%>">
			    <input type="hidden" name="action"	value="backend_SelectOneUpdate"></FORM>
		</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/EmpServlet" style="margin-bottom: 0px;">
			    <button name="delete" value="刪除" type="submit" class="delete" onclick="javascript:return confirm('確認刪除?');">刪除</button>
			    <input type="hidden" name="empId"  value="<%=empVO.getEmpId()%>">
			    <input type="hidden" name="action" value="backend_DeleteEmp"></FORM>
		</td>
	
	</tr>

</table>


</body>
</html>