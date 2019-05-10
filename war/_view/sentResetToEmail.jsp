<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
		<title> Account Login </title>		
		<jsp:include page="CSS/loginPage.css"/> <!-- ALTERNATIVE TO USEING HREFS-->
</head>	
	<body>		
		<span class = "images">
			<a href = https://www.ted.com target = blank>
			<img src = "images/TedTalk.png" align = "left" width = "200" height = "150">
			</a>
			<a href = https://my.ycp.edu target = blank>
			<img src = "images/YorkCollge.png" align = "right" width = "200" height = "150">
			</a>			
		</span>
		<div class = "center">
		<h1>Please enter your username to send the email to your account</h1>
		
		<form action = "${pageContext.servletContext.contextPath}/sentResetToEmail" method="post">
		<input type="text" name = "username" placeholder = "User Name" required = "required" value="${username}"/>
		<br>
		<button type="submit" name="submit">Reset Password</button>
		</form>		
		</div>
		
		
	</body>
</html>