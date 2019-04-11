<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>TedTalkReviews</title>
		<jsp:include page = "CSS/reviewPage.css"/>
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
		
		 <%--This is the textfield for the review's description --%> 
		<div align ="center">
		 <%--the form action will call the post method in the review servlet, then it will redirect it to the profile servlet --%> 
		<form action = "${pageContext.servletContext.contextPath}/review" method = "post">
		<textarea name="reviewText" rows="4" cols="50" maxlength="250" placeholder="Enter Description here" spellcheck="true"></textarea>
		<input type = "Submit" class="button2" value = "Submit Review">
		</form>
		</div>
	</body>
</html>