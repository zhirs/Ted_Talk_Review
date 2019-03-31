<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<style>
		.button {
		background-color: grey; 
		border: 1px solid black;
		color: black;
		padding: 12px 24px;
		text-align: center;
		text-decoration: none;
		display: inline-block;
		font-size: 10px;
		float: left;
		}
		.button2 {
		background-color: grey; 
		border: none;
		color: black;
		padding: 12px 24px;
		text-align: center;
		text-decoration: none;
		display: inline-block;
		font-size: 10px;
		}
		.reviewSection{
		clear: both;
		overflow: auto;
		}
	</style>
	
	<head>
		<title>TedTalkReviews</title>
		<style type = "text/css">
		body{
		background-color: white;
		}
		h1{
		text-align: center;
		color: purple;
		} 
		</style>
	</head>
	
	<body>
		<img src = "images/TedTalk.png" align = "left" width = 200 height = 150 >
		<img src = "images/YorkCollge.png" align = "right" width = 200 height = 150 >
		<h1> Review </h1>
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
		<div id = "reviewSection">
			<hr>
			<p> THIS IS AN EXAMPLE OF A REVIEW<p> 
			<form action = "${pageContext.servletContext.contextPath}/request" method = "get">
			<input type = "Submit" name = "request" class="button2" value = "Request">
			</form>
			<hr>
		</div>
	</body>
</html>