<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>TedTalkReviews</title>
		<style type = "text/css">
		body{
		background-color: #ADD8E6;
		}
		h1{
		text-align: center;
		color: purple;
		} 
		</style>
	</head>
	
	<body>
		<h1> Review </h1>
		<hr>
		<form action = "${pageContext.servletContext.contextPath}/home" method = "get">
		<input type = "Submit" name = "redirectHome" value = "Home">
		</form>
		<hr>
		<form action = "${pageContext.servletContext.contextPath}/profile" method="get">
		<input type = "Submit" name = "redirectProfile" value = "Profile">
		</form>
		<hr>
		<form action = "${pageContext.servletContext.contextPath}/login" method = "get">
		<input type = "Submit" name = "logout" value = "Logout">
		</form>
		<hr>
		<p> THIS IS AN EXAMPLE OF A REVIEW <p> 
		<form action = "${pageContext.servletContext.contextPath}/request" method = "get">
		<input type = "Submit" name = "request" value = "Request">
		</form>
		<hr>
	</body>
</html>