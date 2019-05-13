<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
			<title>My Account</title>
			<jsp:include page ="CSS/homePage.css"/> <!-- ALTERNATIVE TO USEING HREFS-->
					
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
		<h1>Student Home Page</h1>		
		<form action = "${pageContext.servletContext.contextPath}/student" method="get">
		<input type = "Submit" name = "redirectProfile" class="button" value = "Profile">
		</form>
		<form action="${pageContext.servletContext.contextPath}/result" method="get"> 
		<input type = "Submit" name = "searchPage" class="button" value = "Search">
		</form>
		<form action="${pageContext.servletContext.contextPath}/review" method="get"> 
		<input type = "Submit" name = "searchPage" class="button" value = "Review">
		</form>
		<form action = "${pageContext.servletContext.contextPath}/login" method = "get">
		<input type = "Submit" name = "logout" class="button" value = "Logout">
		</form>
		<br><br><br><br><br>
		<h2>Suggested TEDTalks:</h2>
		<ul id = "links"><!-- THE HREF SHOULD GO TO A LINK THAT AUTO-FILLS THE REVIEW PAGE WITH THE CORRESPONDING TED TALK -->
			<li><a href="${pageContext.servletContext.contextPath}/review" target = blank> ${review0}</a></li>
			<li><a href="${pageContext.servletContext.contextPath}/review" target = blank> ${review1}</a></li>
			<li><a href="${pageContext.servletContext.contextPath}/review" target = blank> ${review2}</a></li>
			<li><a href="${pageContext.servletContext.contextPath}/review" target = blank> ${review3}</a></li>
			<li><a href="${pageContext.servletContext.contextPath}/review" target = blank> ${review4}</a></li>
		</ul>		
		<hr>
	</body>
</html>
