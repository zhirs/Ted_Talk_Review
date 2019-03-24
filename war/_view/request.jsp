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
		<h1>Request</h1>
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
		<p>URL</p>
		<p>Body</p>
		<hr>
		<h2>Review Example:</h2>
		<p> review body </p>
		<form action = "${pageContext.servletContext.contextPath}/review" method = "get">
		<input type = "Submit" name = "review" value = "Review">
		</form>
		<hr>
	</body>
</html>