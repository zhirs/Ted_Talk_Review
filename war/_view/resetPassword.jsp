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
		<h1>Reset Password</h1>		
		<hr>
		<div class = "center">
		<h1>Please enter your new password </h1>
		<form action = "${pageContext.servletContext.contextPath}/resetPassword" method="post">
		<p>User Name</p>
		<input type="text" name = "username" placeholder = "User Name" required = "required"/><br><br>
		<p>Enter New Password</p>
		<input type="text" name = "password" placeholder = "New Password" required = "required"/><br><br>
		<p>Verify New Password</p>
		<input type="text" name = "verifyPassword" placeholder = "Verify New Password" required = "required"/>
		<br>
		<button class="button" type="submit" name="submit">Reset Password</button>
		 </div>
		<hr>
		
	</body>
</html>