<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>		
		<title>Student Creation</title>
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
		<h1>Create Student Account</h1>
		<hr>		
		<form action = "${pageContext.servletContext.contextPath}/profile" method="get">
		<input type = "Submit" name = "redirectProfile" class="button" value = "Profile">
		</form>
		<form action = "${pageContext.servletContext.contextPath}/login" method = "get">
		<input type = "Submit" name = "logout" class="button" value = "Logout">
		</form>
		<form action = "${pageContext.servletContext.contextPath}/professorSetting" method = "get">
		<input type = "Submit" name = "settings" class="button" value = "Settings">
		</form>
		<br><br><br>
	
	<form action = "${pageContext.servletContext.contextPath}/createStudent" method = "post">
		<table>
				<tr>
					<td> <input type="text" name = "username" placeholder = "User Name" required = "required" value="${username}"/> </td>
		 		</tr>
				<tr>
					<td> <input type="text" name = "password" placeholder = "Password" required = "required" value="${password}"/> </td>
		 		</tr>
		 		<tr>
		 			<td> <input type="text" name = "email" placeholder = "Email" required = "required" value="${email}"/> </td>
		 		</tr>
		 		<tr>
					<td> <input type="text" name = "section" placeholder = "Section" required = "required" value="${section}"/> </td>
		 		</tr>
		 		<tr>
		 			<td> <input type="text" name = "major" placeholder = "Major" required = "required" value="${major}"/> </td>
		 		</tr>			 	
		 </table>	
		 <div id="cct_embed_counts" align ="center">
		<input type = "Submit" class="button2" value = "Create Account">
		</form>
	</body>
</html>