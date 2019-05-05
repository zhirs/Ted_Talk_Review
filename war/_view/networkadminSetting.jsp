<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>		
		<title>Network Admin Settings</title>
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
		<h1>Network Admin Settings</h1>
		<hr>		
		<form action = "${pageContext.servletContext.contextPath}/profile" method="get">
		<input type = "Submit" name = "redirectProfile" class="button" value = "Profile">
		</form>
		<form action = "${pageContext.servletContext.contextPath}/login" method = "get">
		<input type = "Submit" name = "logout" class="button" value = "Logout">
		</form>
		<br><br><br><br><br><br>
		
		Create an account here:
		<form action = "${pageContext.servletContext.contextPath}/createAdmin" method = "get">
		<input type = "Submit" name = "createAdmin" class="button" value = "Create Admin">
		</form>
		<br><br>
		<form action = "${pageContext.servletContext.contextPath}/createProfessor" method = "get">
		<input type = "Submit" name = "createProfessor" class="button" value = "Create Professor">
		</form>
		<br><br>
		<form action = "${pageContext.servletContext.contextPath}/createStudent" method = "get">
		<input type = "Submit" name = "createStudent" class="button" value = "Create Student">
		</form>
		<br><br><br>
		promote/demote an account here:<br>
		<form action = "${pageContext.servletContext.contextPath}/promoteDemote" method = "get">
		<input type = "Submit" name = "promoteDemote" class="button" value = "Promote/Demote">
		<br><br><br>
		or remove an account here:<br>
		<form action = "${pageContext.servletContext.contextPath}/removeAcc" method = "get">
		<input type = "Submit" name = "removeAcc" class="button" value = "Remove Account">
		</form>
	</body>
</html>