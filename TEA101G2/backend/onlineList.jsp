<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.stream.*"%>
<%@ page import="com.emp.model.*"%>

<%

EmpService emps = new EmpService();
List<EmpVO> onlineList = emps.selectAllEmpOnline();
pageContext.setAttribute("onlineList", onlineList);

%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="Ansonika">

  <style>


/* The popup chat - hidden by default */
.chat-popup {
  display: none;
  position: fixed;
  bottom: 80px;
  right: 15px;
  border: 3px solid #f1f1f1;
  z-index: 9;
}

/* Add styles to the form container */
.form-container {
  max-width: 300px;
  padding: 10px;
  background-color: white;
  height: 200px
}

/* Full-width textarea */
.form-container messagesArea {
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  border: none;
  background: #f1f1f1;
  resize: none;
  min-height: 200px;
}

/* When the textarea gets focus, do something */
.form-container textarea:focus {
  background-color: #ddd;
  outline: none;
}

/* Set a style for the submit/send button */
.form-container .btn {
  border: none;
  cursor: pointer;
  margin-bottom:10px;
  opacity: 0.8;
  
}

/* Add a red background color to the cancel button */
.form-container .cancel {
  background-color: red;
}

/* Add some hover effects to buttons */
.form-container .btn:hover, .open-button:hover {
  opacity: 1;
}


.message-area {
    height: 200px;
    resize: none;
    box-sizing: border-box;
    overflow: auto;
    background-color: #ffffff;
}

.panel {
	border: 2px solid #0078ae;
	border-radius: 5px;
	bottom :0%;
}


#closebtn{
    position: absolute; /*會找父類別位移*/
    right: 0;
    top: 0px;
    /*設定重疊時的前後,數字大在前*/
    z-index: 2;

}

  table {
	max-width: auto;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, tr, th, td {
    border: 1px solid #CCCCFF;
  }
  tr, th, td {
    padding: 5px;
    text-align: center;
  }


</style>

</head>





<%-- 
<div class="chat-popup" id="myForm">
  <form action="/action_page.php" class="form-container">
    <h1>Chat</h1>
	
    <label for="msg"><b>Message</b></label>
	<div id="messagesArea" class="panel message-area" ></div>
    <input id="message" class="text-field" type="text" placeholder="Message" onkeydown="if (event.keyCode == 13) sendMessage();" /> 
    <button type="button" class="btn cancel" onclick="closeForm()">Close</button>
  </form>
</div>
--%>
<body >
<div class="chat-popup" id="onlineList" style="height: 350px; background-color: white; right: 400px; width: 250px;">
  <div class="form-container">
    <h1 class="chat">在線員工</h1>
    
     <table>
              <thead>
                <tr>
					<th>員工</th>
					<th>在線狀態</th>
					<th>公務訊息傳遞</th>
                </tr>
              </thead>
              <tbody id="onlineListtbody">
 
              <c:forEach var="empVO" items="${onlineList}" begin="0" end="<%=onlineList.size()%>">
              	<tr>
             		<th>[${empVO.empId}]${empVO.empName}</th>
					<th>${empVO.empOnline}</th>
					<th>			     
						<button type="button" class="btn btn-link" id="messbtn" value="${empVO.empId}" onclick="clickToChat(this)">
							<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-chat-text" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
							  <path fill-rule="evenodd" d="M2.678 11.894a1 1 0 0 1 .287.801 10.97 10.97 0 0 1-.398 2c1.395-.323 2.247-.697 2.634-.893a1 1 0 0 1 .71-.074A8.06 8.06 0 0 0 8 14c3.996 0 7-2.807 7-6 0-3.192-3.004-6-7-6S1 4.808 1 8c0 1.468.617 2.83 1.678 3.894zm-.493 3.905a21.682 21.682 0 0 1-.713.129c-.2.032-.352-.176-.273-.362a9.68 9.68 0 0 0 .244-.637l.003-.01c.248-.72.45-1.548.524-2.319C.743 11.37 0 9.76 0 8c0-3.866 3.582-7 8-7s8 3.134 8 7-3.582 7-8 7a9.06 9.06 0 0 1-2.347-.306c-.52.263-1.639.742-3.468 1.105z"/>
							  <path fill-rule="evenodd" d="M4 5.5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5zM4 8a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7A.5.5 0 0 1 4 8zm0 2.5a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5z"/>
							</svg>
		        		</button>
	        		</th>
				</tr>
              </c:forEach>

              </tbody>
     </table>

	
	 <button name="delete" type="submit" class="btn btn-link" id="closebtn" onclick="closeOnlineList();">
	 	<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-x-square-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
			<path fill-rule="evenodd" d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm3.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293 5.354 4.646z"/>
		</svg>
	 </button>

  </div>
</div>

<script>

function online() {
	var MyPoint2 = "/OnlineWS/${loginEmp.empId}";
	var host2 = window.location.host;
	var path2 = window.location.pathname;
	var webCtx2 = path2.substring(0, path2.indexOf('/', 1));
	var endPointURL2 = "ws://" + host2 + webCtx2 + MyPoint2;
	//websocket 有專屬的通訊協定 ws://
	
	var onlineWebSocket;

		console.log("endPointURL2:" + endPointURL2);
		// create a websocket
		onlineWebSocket = new WebSocket(endPointURL2);
		
		onlineWebSocket.onopen = function(event) {
			console.log("OnlineWS，connected");
		};
		
		onlineWebSocket.onmessage = function(event) {
			
			var jsonObj = JSON.parse(event.data);
			console.log('onlineJsonObj:' + jsonObj)
			if("online" === jsonObj.type){
				$.when($.ajax({
				    type: 'POST',
				    url: '<%=request.getContextPath()%>/EmpServlet',
				    dataType: "json",
				    data: {
				    	action: 'selectAllEmpIdName',
				    },
				    success: function(data) {
				    },
				})).done(function (data){
					console.log(jsonObj.user)
					<%--
					tbody.appendChild(tr);
					tr.appendChild(th);
					th.innerHTML = jsonObj.user;
					--%>
					var loginEmpId = '${loginEmpId}';
					console.log('loginEmpId:'+loginEmpId);
					if(jsonObj.user !== loginEmpId){
						let list_html =
						`
						<tr>
							<th name=\${jsonObj.user}>\${data.map[jsonObj.user]}</th>
							<th>Y</th>
						</tr>
						`
						$("#onlineListtbody").append(list_html);
						
						$('#selects th[name="${jsonObj.user}"]').exist(function(exist, elements, index) { 
							
						},function (exist, elements) {
							
						});
					}
				})
				
			};
		};
		
		onlineWebSocket.onclose = function(event) {
			console.log("OnlineWS，Disconnected!");

		};	
};

function offline() {
	onlineWebSocket.close();
};

function openOnlineList(){
	document.getElementById("onlineList").style.display = "block";
}

function closeOnlineList(){
	document.getElementById("onlineList").style.display = "none";
}

</script>

</body>
</html>