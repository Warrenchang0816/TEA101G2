<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.spacePhoto.model.*"%>
<%@ page import="com.space.model.*"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	SpaceVO spaceVO = (SpaceVO) request.getAttribute("spaceVO"); //SpaceServlet.java(Concroller), 存入req的spaceVO物件
	SpacePhotoService spacePhotoSvc = new SpacePhotoService();
	List<SpacePhotoVO> photolist = spacePhotoSvc.getAllPhoto(spaceVO.getSpaceId());
	pageContext.setAttribute("photolist",photolist);
%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>編輯場地照片</title>

<!-- GOOGLE WEB FONT -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">

<!-- BASE CSS -->
    <link href="<%=request.getContextPath()%>/plugins/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/plugins/css/style.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/plugins/css/vendors.css" rel="stylesheet">

<!-- Plugin styles -->
	<link href="<%=request.getContextPath()%>/plugins/vendor/dropzone.css" rel="stylesheet">

<!-- Your custom styles -->
    <link href="<%=request.getContextPath()%>/plugins/css/custom.css" rel="stylesheet" type="text/css">
    
<style>
label{
  display: inline-block;
  background-color: royalblue;
  color: white;
  padding: 0.5rem;
  font-family: sans-serif;
  border-radius: 0.3rem;
  cursor: pointer;
  margin-top: 1rem;
}
</style>

</head>

<body>

<div id="page">
<jsp:include page="/frontend/other/header.jsp" />	

    <main>
		
		<section class="hero_in hotels">
			<div class="wrapper">
				<div class="container">
					<h1 class="fadeInUp"><span></span><%=spaceVO.getSpaceName()%></h1>
				</div>
			</div>
		</section>
	<!--/hero_in-->
	<div class="bg_color_1">
		<div class="container margin_60_35">
			<div class="row">
				<div class="col-lg-8">
					<section id="description">
						<h2>場地照片<small>第一張為預設圖片</small></h2>
						<div class="room_type first">
							<div class="row">
							<c:forEach var="spacePhotoVO" items="${photolist}">
								<div class="col-md-4">
									<img src="<%=request.getContextPath()%>/space/showpicture?spacePhotoId=${spacePhotoVO.spacePhotoId}" width="200" height="135">
									<hr>
			     					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/spacephoto/spacephoto.do" style="margin-bottom: 0px;">
			     						<input type="submit" value="刪除">
			     						<input type="hidden" name="spacePhotoId"  value="${spacePhotoVO.spacePhotoId}">
			     						<input type="hidden" name="spaceId" value="<%=spaceVO.getSpaceId()%>">
			     						<input type="hidden" name="action" value="delete">
			     					</FORM>
								</div>
							</c:forEach>
							</div>
							<!-- /row -->
							<hr>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/spacephoto/spacephoto.do" enctype="multipart/form-data" name="form1">
							<%-- 
							<div class="row">										
								<div class="col-md-12">
									<div class="form-group">
										<input type="file" name="spacePhoto" id="imgInp" />
										<img id="blah" src="#" />
									</div>
								</div>
							</div>
							--%>
							<!-- /row-->
							<div id="btn-photo">
								<label>
									<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-card-image" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
										<path fill-rule="evenodd" d="M14.5 3h-13a.5.5 0 0 0-.5.5v9c0 .013 0 .027.002.04V12l2.646-2.354a.5.5 0 0 1 .63-.062l2.66 1.773 3.71-3.71a.5.5 0 0 1 .577-.094L15 9.499V3.5a.5.5 0 0 0-.5-.5zm-13-1A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-13zm4.502 3.5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z"/>
												</svg>
										上傳照片
									<input type="file" name="spacePhoto" id="imgInp2" multiple="multiple" style="visibility: hidden; position: absolute;"/>									
								</label>
							</div>
							<hr>
							<p>
								<input type="hidden" name="spaceId" value="${spaceVO.spaceId}">
								<input type="hidden" name="action" value="insert">
            					<input type="submit" value="新增照片" class="btn_1 medium">
							</p>	
							</FORM>
						</div>
						<!-- /row-->
					</section>
					<!-- /section -->
				</div>
				<!-- col -->
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /bg_color_1 -->
</main>
<!--/main-->
</div>
<!-- page -->

<!-- COMMON SCRIPTS -->
  	<script src="<%=request.getContextPath()%>/plugins/js/common_scripts.js"></script>
  	<script src="<%=request.getContextPath()%>/plugins/js/main.js"></script>
<!-- Custom scripts for this page-->
	<script src="<%=request.getContextPath()%>/plugins/vendor/dropzone.min.js"></script>
<!-- JQUERY -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
		

<script>
      $("#imgInp2").on("change",function() {
    	  console.log("FUCKKK")
    	  $("img.preview").remove();
		for (let i = 0; i < this.files.length; i++) {
			let reader = new FileReader();
			reader.readAsDataURL(this.files[i]);
			reader.addEventListener("load", function() {
				$("#btn-photo").append(`<img src="\${reader.result}" class ="preview" id="preview" style="max-height:100px;">&nbsp;&nbsp;&nbsp;`);
			});
		};
	});
</script>

<script>
function readURL(input) {
	  if (input.files && input.files[0]) {
	    var reader = new FileReader();
	    
	    reader.onload = function(e) {
	      $('#blah').attr('src', e.target.result);
	    }
	    
	    reader.readAsDataURL(input.files[0]); // convert to base64 string
	  }
}
$("#imgInp").change(function() {
	readURL(this);
});
</script>

</body>
</html>