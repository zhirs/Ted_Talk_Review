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
		<h1>Profile</h1>
		<hr>
		<form action = "${pageContext.servletContext.contextPath}/home" method = "get">
		<input type = "Submit" name = "redirectHome" value = "Home">
		</form>
		<hr>
		<p>THIS IS AN EXAMPLE OF A USER'S PERSONAL REVIEW</p>
		<form action="${pageContext.servletContext.contextPath}/review" method="get"> 
		<input type = "Submit" name = "redirectReview" value = "View">
		</form>
		
		<hr>
	</body>
</html>