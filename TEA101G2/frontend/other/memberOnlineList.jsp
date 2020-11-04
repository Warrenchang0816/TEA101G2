<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.stream.*"%>
<%@ page import="com.member.model.*"%>

<%

MemberService members = new MemberService();
List<MemberVO> memberOnlineList = members.selectAllMemberOnline();
pageContext.setAttribute("memberOnlineList", memberOnlineList);

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

<style>
#message-popup {
  background-color: royalblue;
  color: white;
  border: none;
  cursor: pointer;
  opacity: 0.8;
  position: fixed;
  bottom: 10px;
  right: 150px;
  height: 30px;
  z-index: 9;
  
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
<body onload="online();" onunload="offline();">
<div class="chat-popup" id="onlineList" style="height: 350px; background-color: white; right: 400px; width: 250px;">
  <div class="form-container">
    <h1 class="chat">在線會員</h1>
    
     <table>
              <thead>
                <tr>
					<th>會員</th>
					<th>在線狀態</th>
					<th>傳訊息</th>
                </tr>
              </thead>
              <tbody id="onlineListtbody">
 
              <c:forEach var="memberVO" items="${memberOnlineList}" begin="0" end="<%=memberOnlineList.size()%>">
              	<tr>
             		<th>[${memberVO.memberId}]${memberVO.memberName}</th>
					<th>${memberVO.memberOnline}</th>
					<th>			     
						<button type="button" class="btn btn-link" id="messbtn" value="${memberVO.memberId}" onclick="clickToChat(this)">
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
	var MyPoint2 = "/OnlineWS/${loginMember.memberId}";
	var host2 = window.location.host;
	var path2 = window.location.pathname;
	var webCtx2 = path2.substring(0, path2.indexOf('/', 1));
	var endPointURL2 = "ws://" + host2 + webCtx2 + MyPoint2;
	//websocket 有專屬的通訊協定 ws://
	
	var MyPoint = "/FriendWS/${loginMember.memberId}";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
	
	var onlineWebSocket;

		console.log("endPointURL2:" + endPointURL2);
		// create a websocket
		onlineWebSocket = new WebSocket(endPointURL2);
		chatWebSocket = new WebSocket(endPointURL);
		
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
		
		
		chatWebSocket.onopen = function(event) {
			console.log("ChatConnect Success!");
		};
		
		chatWebSocket.onmessage = function(event) {
			console.log("ChatConnect onmessage!");
			
			  $.when($.ajax({
				    type: 'POST',
				    url: '<%=request.getContextPath()%>/MemberServlet.do',
				    dataType: "json",
				    data: {
				    	action: 'selectAllMemberIdName',
				    },
				    success: function(data) {
				    	console.log("ChatConnect success!");
				    },
				})).done(function (data){
				
					console.log("ChatConnect data!"+data);
					var jsonObj = JSON.parse(event.data);
					if ("open" === jsonObj.type) {
						
						var jsonObj = {
								"type" : "history",
								"sender" : self,
								"receiver" : "",
								"message" : "",
								"time" : datetime
							};
						chatWebSocket.send(JSON.stringify(jsonObj));
					} else if ("history" === jsonObj.type) {
						console.log("historyFUCKKK")
						messagesArea.innerHTML = '';
						var ul = document.createElement('ul');
						ul.id = "area";
						messagesArea.appendChild(ul);
						console.log("historyFUCKKK222222")
						// 這行的jsonObj.message是從redis撈出跟好友的歷史訊息，再parse成JSON格式處理
						var messages = JSON.parse(jsonObj.message);
						for (var i = 0; i < messages.length; i++) {
							var historyData = JSON.parse(messages[i]);
							var showMsg = historyData.message;
							var datetime = historyData.time;
							var li = document.createElement('li');
							var div = document.createElement('div');
							var span = document.createElement('span');
							var newContent = historyData.sender === self ? document.createTextNode('我: ' + showMsg) : document.createTextNode(data.map[historyData.sender] + ': ' + showMsg);
							// 根據發送者是自己還是對方來給予不同的class名, 以達到訊息左右區分
							historyData.sender === self ? li.className += 'me messages' : li.className += 'friend messages';
							historyData.sender === self ? div.className += 'me message last' : div.className += 'friend message last';
							ul.appendChild(li);
							li.appendChild(div);
							li.appendChild(span);
							span.innerHTML = datetime;
							div.appendChild(newContent);
							console.log("historyFUCKKK33333")
						}
						messagesArea.scrollTop = messagesArea.scrollHeight;
						<%--
					} else if ("chat" === jsonObj.type && friend === jsonObj.sender && self === jsonObj.receiver) {
						console.log("CHATFUCKYAYAYA");
						var currentdate = new Date(); 
						var datetime = currentdate.getHours()  + ":" + currentdate.getMinutes() + ":" + currentdate.getSeconds();
						var li = document.createElement('li');
						var div = document.createElement('div');
						var span = document.createElement('span');
						div.className  = "message last";
						var newContent = jsonObj.sender === self ? document.createTextNode('我: ' + jsonObj.message) : document.createTextNode(data.map[jsonObj.sender] + ': ' + jsonObj.message);
						jsonObj.sender === self ? li.className += 'me messages' : li.className += 'friend messages';
						console.log(li);
						document.getElementById("area").appendChild(li);
						li.appendChild(div);
						div.appendChild(newContent);
						li.appendChild(span);
						span.innerHTML = datetime;
						messagesArea.scrollTop = messagesArea.scrollHeight;
						--%>
						
					}else if ("chat" === jsonObj.type && self === jsonObj.receiver) {
						console.log("CHATFUCKCKCKKCKCKCKCKCKCKKCKCKCKC");
						console.log("sender:" + data.map[jsonObj.sender]);
						console.log("receiver:" + data.map[jsonObj.receiver]);
						console.log("message:" + jsonObj.message);
						
						let list_html =
							`
							<button type="button" id="message-popup" value="\${jsonObj.sender}" onclick="clickToChat(this)">
			             		"來自\${data.map[jsonObj.sender]}的訊息"
							</button>
							`
						$("#openChat-button").after(list_html);
						popOffListener();
						
					} else if ("close" === jsonObj.type) {
						console.log("FUCKKKoff")
					}
					

				chatWebSocket.onclose = function(event) {
					console.log("ChatDisconnected!");
				};
				
				});
				
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

function popOffListener(){
	var messagePopup = document.getElementById("message-popup");
	messagePopup.addEventListener("click", function(e) {
        $(this).fadeOut(500, function(){
            $(this).remove();
        });
	})
}

</script>

</body>
</html>