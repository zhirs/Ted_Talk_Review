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
		<h1>YCP-TedTalk Login</h1>		
		<hr>
		<div id = "buttonForm">
			<form action="${pageContext.servletContext.contextPath}/login" method="post">
				<table>
					<tr>
						<td> <input type="text" name = "u" placeholder = "Username" value="${profileM.user}"> </td>
			 		</tr>
			 		<tr>
			 			<td> <input type="password" name = "p" placeholder = "Password" value="${profileM.pass}"> </td>
			 		</tr>
		 		</table>
		 		<button type="submit" name="submit" value = "Login">Login</button>
		 		<button type="submit" name="forgotPW" value = "Forgot Password ">Forgot Password </button> <br><br>
			 </form>
			 <form action = "${pageContext.servletContext.contextPath}/createStudent" method = "get">
				<input type = "Submit" class="button2" value = "Create Account">
			 </form>
		 </div>
		<hr>
		<h1>Leaders In Review Count</h1>
		<h3>First: ${leader}</h3>
		<h3>Second: ${second}</h3>
		<h3>Third: ${third}</h3>
		
	</body>
</html>