<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
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
		<h1>TedTalk Reviews</h1>
		<form action = "${pageContext.servletContext.contextPath}/profile" method="get">
		<input type = "Submit" name = "redirectProfile" class="button" value = "Profile">
		</form>
		<form action = "${pageContext.servletContext.contextPath}/login" method = "get">
		<input type = "Submit" name = "logout" class="button" value = "Logout">
		</form>
		<br><br><br>
		<hr>
		<p> THIS IS AN EXAMPLE OF A REQUEST</p>
		<form action="${pageContext.servletContext.contextPath}/request" method="get"> 
		<input type = "Submit" name = "redirectReview" class="button2" value = "Review">
		</form>
		
		<hr>
		
	</body>
</html>