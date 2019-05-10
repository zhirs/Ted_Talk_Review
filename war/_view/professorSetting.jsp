<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>		
		<title>Professor Settings</title>
		<jsp:include page ="CSS/homePage.css"/>	<!-- ALTERNATIVE TO USEING HREFS-->			
 	</head>
	
	<body>
		<span class = "images">
			<a href = https://www.ted.com target = blank>
			<img src = "images/TedTalk.png" align = "left" width = 200 height = 150 >
			</a>
			<a href = https://my.ycp.edu target = blank>
			<img src = "images/YorkCollge.png" align = "right" width = 200 height = 150 >
			</a>			
		</span>
		<h1>Professor Settings</h1>
		<hr>		
		<form action = "${pageContext.servletContext.contextPath}/professor" method="get">
		<input type = "Submit" name = "redirectProfile" class="button" value = "Profile">
		</form>
		<form action = "${pageContext.servletContext.contextPath}/professorHome" method = "get">
		<input type = "Submit" name = "home" class="button" value = "Home">
		</form>
		<form action = "${pageContext.servletContext.contextPath}/login" method = "get">
		<input type = "Submit" name = "logout" class="button" value = "Logout">
		</form>
		<br><br><br>
		<hr>
		<form action = "${pageContext.servletContext.contextPath}/createStudent" method = "get">
		<input type = "Submit" name = "createAdmin" class="button" value = "Create Student">
	</body>
</html>	