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
		<form action = "${pageContext.servletContext.contextPath}/networkadminHome" method="get">
		<input type = "Submit" name = "redirectProfile" class="button" value = "Profile">
		</form>
		<form action = "${pageContext.servletContext.contextPath}/login" method = "get">
		<input type = "Submit" name = "logout" class="button" value = "Logout">
		</form>
		<br><br><br><br><br><br>
		
		<h2>Create an account here:</h2>
		<form action = "${pageContext.servletContext.contextPath}/createAdmin" method = "get">
		<input type = "Submit" name = "createAdmin" class="button" value = "Create Admin">
		</form>
		<form action = "${pageContext.servletContext.contextPath}/createProfessor" method = "get">
		<input type = "Submit" name = "createProfessor" class="button" value = "Create Professor">
		</form>
		<form action = "${pageContext.servletContext.contextPath}/createStudent" method = "get">
		<input type = "Submit" name = "createStudent" class="button" value = "Create Student">
		</form>
		<br><br><br>
		<h2>Promote/demote an account here:</h2>
		<form action = "${pageContext.servletContext.contextPath}/promoteDemote" method = "get">
		<input type = "Submit" name = "promoteDemote" class="button" value = "Promote/Demote">
		</form>
		<br><br><br>
		<h2>Remove an account here:</h2> <br>
		<form action = "${pageContext.servletContext.contextPath}/removeAcc" method = "get">
		<input type = "Submit" name = "removeAcc" class="button" value = "Remove Account">
		</form>
		<br><br><br>
		<h2> Or change reviewing(on/off):</h2>
		<h2> ${globalModStat} </h2>
		<h3> your current reviewing status is: ${currentStat}</h3><br>
		<form action = "${pageContext.servletContext.contextPath}/networkadminSetting" method = "post">
		<input type = "Submit" name = "moderatorStatus" class="button" value = "On/Off">
		</form>
	</body>
</html>