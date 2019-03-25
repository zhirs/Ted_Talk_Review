<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<style>
		.button {
		background-color: #9370DB; 
		border: 1px solid black;
		color: white;
		padding: 15px 32px;
		text-align: center;
		text-decoration: none;
		display: inline-block;
		font-size: 16px;
		float: left;
		}
		.button2 {
		background-color: #9370DB; 
		border: 1px solid black;
		color: white;
		padding: 15px 32px;
		text-align: center;
		text-decoration: none;
		display: inline-block;
		font-size: 16px;
		}
	</style>

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
		<input type = "Submit" name = "redirectHome" class="button" value = "Home">
		</form>
		<form action = "${pageContext.servletContext.contextPath}/profile" method="get">
		<input type = "Submit" name = "redirectProfile" class="button" value = "Profile">
		</form>
		<form action = "${pageContext.servletContext.contextPath}/login" method = "get">
		<input type = "Submit" name = "logout" class="button" value = "Logout">
		</form>
		<br><br><br>
		<hr>
		<p>URL: https://ycpcs.github.io/cs320-spring2019</p>
		<p>Body</p>
		<hr>
		<h2>Review Example:</h2>
		<p> review body </p>
		<form action = "${pageContext.servletContext.contextPath}/review" method = "get">
		<input type = "Submit" name = "review" class="button2" value = "Review">
		</form>
		<hr>
	</body>
</html>