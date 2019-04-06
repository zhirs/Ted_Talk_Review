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
		padding: 10px 21px;
		text-align: center;
		text-decoration: none;
		display: inline-block;
		font-size: 9px;
		float: left;
		}
		.button2 {
		background-color: #9370DB; 
		border: none;
		color: white;
		padding: 10px 21px;
		text-align: center;
		text-decoration: none;
		display: inline-block;
		font-size: 9px;
		}
		.reviewSection{
		background-color:white;
		
		text-align: center;
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
		<span class = "images">
			<a href = "https://www.ted.com" target = "blank">
			<img src = "images/TedTalk.png" align = "left" width = 200 height = 150 >
			</a>
			<a href = "https://my.ycp.edu" target = "blank">
			<img src = "images/YorkCollge.png" align = "right" width = 200 height = 150 >
			</a>			
		</span>
		<h1>Profile</h1>		
		<h4>Welcome ${username}</h4>
		<h2>${email} ${session}</h2>		
		<hr>
		<div id ="homeButtonRow">
			<form action = "${pageContext.servletContext.contextPath}/home" method = "get">
			<input type = "Submit" name = "redirectHome" class="button" value = "Home">
			</form>
			<form action = "${pageContext.servletContext.contextPath}/login" method = "get">
			<input type = "Submit" name = "logout" class="button" value = "Logout">
			</form>
		</div>
		<br><br><br>
		<div id = "reviewSection">	
			<hr>	
			<p>THIS IS AN EXAMPLE OF A USER'S PERSONAL REVIEW</p>
			<form action="${pageContext.servletContext.contextPath}/review" method="get"> 
			<input type = "Submit" name = "redirectReview" class="button2" value = "Review">
			<hr>
			</form>		
		</div>
	</body>
</html>
