<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
			<title>Approved Review</title>
			<jsp:include page ="CSS/homePage.css"/> <!-- ALTERNATIVE TO USEING HREFS-->
					
	</head>
	
	<body>
		<span class = "images">
			<a href = "https://www.ted.com" target = "blank" >
			<img src = "images/TedTalk.png" align = "left" width = 200 height = 150 >
			</a>
			<a href = "https://my.ycp.edu" target = "blank">
			<img src = "images/YorkCollge.png" align = "right" width = 200 height = 150 >
			</a>			
		</span>
		<h1>Approved Reviews</h1>	
		<div class = "buttonbar">
		<form action = "${pageContext.servletContext.contextPath}/home" method="get">
		<input type = "Submit" name = "redirectProfile" class="button" value = "Home"> 
	
		</form>
		<form action = "${pageContext.servletContext.contextPath}/studentOverview" method = "get">
		<input type = "Submit" name = "logout" class="button" value = "Overview">
		</form>
		<form action="${pageContext.servletContext.contextPath}/result" method="get"> 
		<input type = "Submit" name = "searchPage" class="button" value = "search">
		</form>
		<form action = "${pageContext.servletContext.contextPath}/login" method = "get">
		<input type = "Submit" name = "logout" class="button" value = "Logout">
		</form>
		<form action = "${pageContext.servletContext.contextPath}/createStudent" method = "get">
		<input type = "Submit" name = "createAdmin" class="button" value = "Create Student">
		</form>
		</div>
		<br>
		<br>
		<br>
		<h1>All reviews in queue approved</h1>
		<hr>
	</body>
</html>
