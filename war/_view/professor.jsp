<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
			<title>My Account</title>
			<jsp:include page ="CSS/profilePage.css"/> <!-- ALTERNATIVE TO USEING HREFS-->
					
	</head>
	
	<body>
		<span class = "images">
			<a href = "https://www.ted.com" target = "blank" >
			<img src = "images/TedTalk.png" align = "left" width = 200 height = 150 >
			</a>
			<a href = "https://my.ycp.edu" target = "blank">
			<img src = "images/YorkCollge.png" align = "right" width = 200 height = 150 >
			</a>			
		</span>
		<h1>Profile</h1>		

		<h4>Welcome ${email} ${session}</h4>		
		<hr>
		<div id ="homeButtonRow">
			<form action = "${pageContext.servletContext.contextPath}/home" method = "get">
			<input type = "Submit" name = "redirectHome" class="button" value = "Home">
			</form>
			<form action="${pageContext.servletContext.contextPath}/review" method="get"> 
			<input type = "Submit" name = "redirectReview" class="button" value = "Review">
			</form>			
			<form action = "${pageContext.servletContext.contextPath}/ " method = "get">
			<input type = "Submit" name = "settings" class="button" value = "settings">
			</form>
			<form action = "${pageContext.servletContext.contextPath}/login" method = "get">
			<input type = "Submit" name = "logout" class="button" value = "Logout">
			</form>			
		</div>
		<br><br><br>
		<hr>
		<%--div that presents a created review. when real database is implemented this will be unnecessary --%>
		<div id = "reviewSection">
			<h3>Your pending reviews:</h3>		
			<div class = "pendingReviewSection">
			
				<c:forEach var="review" items="${UpdatedReviews}">
					<c:out value = "${UpdatedReviews}"/> <br>Created Review<br>
				</c:forEach>
			</div>
		</div>
		<div id = "reviewSection">	
			<hr>	
			<p>THIS IS AN EXAMPLE OF A USER'S PERSONAL REVIEW</p>
			
			<c:forEach var="reviews" items="${reviews}">
				<c:out value = "${reviews}"/> <br>Another Review<br>
			</c:forEach>	
			<hr>					
		</div>					


	</body>
</html>
