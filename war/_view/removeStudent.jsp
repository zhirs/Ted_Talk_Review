<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>		
		<title>Student Deletion</title>
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
		<h1>Remove Student</h1>
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

		IMPORTANT: ONCE AN ACCOUNT IS DELETED IT IS GONE FOREVER <br><br>
		<table>
					<tr>
						<td> <input type="text" name = "Username" placeholder = "Username" required = "required" value="${remove}"> </td>
			 		</tr>	 	
		 </table>
		 		
			<div id="cct_embed_counts" align ="center">
			
			<form action = "${pageContext.servletContext.contextPath}/removeStudent" method = "post">
			<input type = "Submit" class="button2" value = "Remove Account">
	</body>
</html>