<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.formList.model.*"%>
<%@ page import="com.emp.model.*"%>

<%

	FormListVO mail = (FormListVO)request.getAttribute("sendMail");
	pageContext.setAttribute("mail", mail);
	
	LinkedList<String> errorMsgs = (LinkedList<String>) request.getAttribute("errorMsgs");
	
%>

<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="Ansonika">
  <title>PANAGEA - Admin dashboard</title>
	
  <!-- Favicons-->
  <link rel="shortcut icon" href="<%=request.getContextPath()%>/backend/img/favicon.ico" type="image/x-icon">
  <link rel="apple-touch-icon" type="image/x-icon" href="<%=request.getContextPath()%>/backend/img/apple-touch-icon-57x57-precomposed.png">
  <link rel="apple-touch-icon" type="image/x-icon" sizes="72x72" href="<%=request.getContextPath()%>/backend/img/apple-touch-icon-72x72-precomposed.png">
  <link rel="apple-touch-icon" type="image/x-icon" sizes="114x114" href="<%=request.getContextPath()%>/backend/img/apple-touch-icon-114x114-precomposed.png">
  <link rel="apple-touch-icon" type="image/x-icon" sizes="144x144" href="<%=request.getContextPath()%>/backend/img/apple-touch-icon-144x144-precomposed.png">

  
  <!-- Font Awesome -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/backend/mail/plugins/fontawesome-free/css/all.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/backend/mail/dist/css/adminlte.min.css">
  <!-- summernote -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/backend/mail/plugins/summernote/summernote-bs4.css">
  <!-- Google Font: Source Sans Pro -->
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
  
</head>


<body class="fixed-nav sticky-footer" id="page-top">




  <div class="content-wrapper">
    <div class="container-fluid">
    
       <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="row">
        
        <%@ include file="/backend/mail/mailSide.jsp" %> 
        <%@ include file="/backend/backendHF.jsp" %> 
        
        
          <div class="col-md-9">
            <div class="card card-primary card-outline">
              <div class="card-header">
                <h3 class="card-title">信件</h3>
              </div>
              
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FormListServlet" enctype="multipart/form-data">
              
              <!-- /.card-header -->
              <div class="card-body">
              	<div class="form-group">
					<label>對象</label>
						<div class="styled-select">
							<select  size="1" name="formListStatus">
								<option value = "E" ${formListVO == null ? "selected" : formListVO.formListStatus.equals("E") ? "selected" : ""}>員工</option>
								<option value = "M" ${formListVO == null ? "" : formListVO.formListStatus.equals("M") ? "selected" : ""}>會員</option>
							</select>
						</div>						
				</div>
                <div class="form-group">
                <label>收件者:</label>
                  <input class="form-control" id="mailReceivers" placeholder="To:" name="empId" value="<%= (mail == null && request.getParameter("to") == null)? "" : (request.getParameter("to") == null)? mail.getEmpId() : request.getParameter("to")%>">
                  <span style="color:red"><%= (errorMsgs == null)? "" : (mail.getEmpId().equals(""))? "收件者請勿空白" : (errorMsgs.peek().contains("帳號"))? errorMsgs.poll() : ""%></span>
                </div>
                <div class="form-group">
                <label>主旨:</label>
                  <input class="form-control" placeholder="Subject:" id="title" name="formListTitle" value="<%= (mail == null)? "" : mail.getFormListTitle()%>">
                </div>
                <div class="form-group">
                <label>內容:</label>
                    <textarea id="compose-textarea" class="form-control" style="height: 300px" name="formListContext" value="<%= (mail == null)? "" : mail.getFormListContext()%>">
                    
                    
                    
                    </textarea>
                </div>
                <div class="form-group">
                  <div class="btn btn-default btn-file">
                    <i class="fas fa-paperclip"></i> 附件
                    <input type="file" name="formListFile">
                  </div>
                  <p class="help-block">Max. 32MB</p>
                </div>
              </div>
              <!-- /.card-body -->
              <div class="card-footer">
                <div class="float-right">
               	 <input type="hidden" name="formListSolu" value="<%= loginEmp.getEmpId()%>">
                  <button type="submit" class="btn btn-primary" name="action" value="backend_SendMail" onclick="sendMail()"><i class="far fa-envelope"></i> 寄送</button>
                </div>
                <button type="reset" class="btn btn-default" onclick ="history.back()"><i class="fas fa-times"></i> 取消</button>
              </div>
              <!-- /.card-footer -->
              
</FORM>
               <button type="button" class="btn btn-primary" name="action" value="backend_SendMail" onclick="sendMail()"><i class="far fa-envelope"></i> 寄送</button>
                </div>
                <button type="reset" class="btn btn-default" onclick ="history.back()"><i class="fas fa-times"></i> 取消</button>
            </div>
            <!-- /.card -->
          </div>
          
          <!-- /.col -->
        </div>
        <!-- /.row -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
    
    
    
    </div>
</div>

    
    <script src="<%=request.getContextPath()%>/backend/js/adminlte.min.js"></script>
    
<script>
var MyPoint2 = "/OnlineWS/${loginEmp.empId}";
var host2 = window.location.host;
var path2 = window.location.pathname;
var webCtx2 = path2.substring(0, path2.indexOf('/', 1));
var endPointURL2 = "ws://" + host2 + webCtx2 + MyPoint2;
console.log("endPointURL2:" + endPointURL2);
//websocket 有專屬的通訊協定 ws://

var onlineWebSocket;

	var self = '${loginEmp.empId}';
    
    function sendMail(){

    	console.log("FUCKCKKCKC");
    	onlineWebSocket = new WebSocket(endPointURL2);
    	console.log('onlineWebSocket:'+onlineWebSocket);
    	var receivers = document.getElementById("mailReceivers").value;
    	var title = document.getElementById("title").value;
    	var currentdate = new Date(); 
		var date = currentdate.getFullYear() + "-" + (currentdate.getMonth()+1) + "-" + currentdate.getDate();
    	console.log('receivers:'+receivers);
    	console.log('self:'+self);
    	console.log('date:'+date);
    	onlineWebSocket.onopen = function(event) {
			console.log("Connect Success!");
		};
		
		onlineWebSocket.onmessage = function(event) {
			var jsonObj = JSON.parse(event.data);
			if ("online" === jsonObj.type) {
				var jsonObj = {
						"type" : "mail",
						"sender" : self,
						"receivers" : receivers,
						"title" : title,
						"time" : date
					};
				if (onlineWebSocket.readyState === 1) {
					onlineWebSocket.send(JSON.stringify(jsonObj));
					console.log('UFUFUFUFU');
				}
			
			}else if("mailJSON" === jsonObj.type){
				alert('type:'+jsonObj.type);
				alert('sender:'+jsonObj.sender);
				alert('receivers:'+jsonObj.receivers);
				alert('title:'+jsonObj.title);
				alert('time:'+jsonObj.time);
				if(self === receivers){
					
					<%-- http://localhost:8082/TEA101G2TEA1013/backend/mail/sendMail.jsp?to=EMP00006
					let list_html =
						`
						<tr>
		                    <td class="mailbox-name">
		                    
		                    <a href="\${request.contextPath()}/backend/mail/sendMail.jsp?to=\${jsonObj.sender}">\${jsonObj.sender}</a>
		                    </td>
		                    
		                    <td class="mailbox-subject">
		                    	<a href="\${request.contextPath()}/FormListServlet?action=backend_SelectOneMail&formListId=\${formListVO.formListId}">\${jsonObj.title}</a>

		                    </td>
		                    <td class="mailbox-attachment"></td>
		                    <td class="mailbox-date">${jsonObj.time}</td>
		                 </tr>
						`
					--%>
				}
			}
		}
			
		
		onlineWebSocket.onclose = function(event) {
			console.log("onlineWebSocketDisconnected!");
		};
		
			

    }
	
	
	
    
</script>
    
    
<!-- Page Script -->
<script>
  $(function () {
    //Enable check and uncheck all functionality
    $('.checkbox-toggle').click(function () {
      var clicks = $(this).data('clicks')
      if (clicks) {
        //Uncheck all checkboxes
        $('.mailbox-messages input[type=\'checkbox\']').prop('checked', false)
        $('.checkbox-toggle .far.fa-check-square').removeClass('fa-check-square').addClass('fa-square')
      } else {
        //Check all checkboxes
        $('.mailbox-messages input[type=\'checkbox\']').prop('checked', true)
        $('.checkbox-toggle .far.fa-square').removeClass('fa-square').addClass('fa-check-square')
      }
      $(this).data('clicks', !clicks)
    })

    //Handle starring for glyphicon and font awesome
    $('.mailbox-star').click(function (e) {
      e.preventDefault()
      //detect type
      var $this = $(this).find('a > i')
      var glyph = $this.hasClass('glyphicon')
      var fa    = $this.hasClass('fa')

      //Switch states
      if (glyph) {
        $this.toggleClass('glyphicon-star')
        $this.toggleClass('glyphicon-star-empty')
      }

      if (fa) {
        $this.toggleClass('fa-star')
        $this.toggleClass('fa-star-o')
      }
    })
  })
</script>
<!-- AdminLTE for demo purposes -->
<script src="<%=request.getContextPath()%>/backend/js/demo.js"></script>
</body>

</html>