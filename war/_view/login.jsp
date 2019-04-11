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
						<td> <input type="text" name = "u" placeholder = "Username" required = "required" value="${profileM.user}"> </td>
			 		</tr>
			 		<tr>
			 			<td> <input type="password" name = "p" placeholder = "Password" required = "required" value="${profileM.pass}"> </td>
			 		</tr>
		 		</table>
		 		<button type="submit" name="submit" value = "Login">Login</button>
		 		<button type="submit" name="forgotPW" value = "Forgot Password ">Forgot Password </button>
			 </form>
		 </div>
		<hr>
		
	</body>
</html>