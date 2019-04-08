<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<style type = "text/css">
			body{
			background-color: orange;
			color: black
			}
			h1{
			text-align: center;
			color: purple;
			}		
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
			border: 1px solid black;
			color: white;
			padding: 10px 21px;
			text-align: center;
			text-decoration: none;
			display: inline-block;
			font-size: 9px;
			}
		</style>
		<title>TedTalkReviews</title>				
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
		<h1>TedTalk Reviews</h1>
		<hr>		
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