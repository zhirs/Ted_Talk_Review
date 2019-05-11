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
		<h1>Professor Home</h1>		

		<hr>		
		<form action = "${pageContext.servletContext.contextPath}/profile" method="get">
		<input type = "Submit" name = "redirectProfile" class="button" value = "Profile">
		</form>
		<form action = "${pageContext.servletContext.contextPath}/login" method = "get">
		<input type = "Submit" name = "logout" class="button" value = "Logout">
		</form>
		<br><br><br>
		<hr>
		<h2>Suggested TEDTalks:</h2>
		<p>What your peers are viewing:</p>
		<ul id = "links"><!-- THE HREF SHOULD GO TO A LINK THAT AUTO-FILLS THE REVIEW PAGE WITH THE CORRESPONDING TED TALK -->
			<li><a href="${pageContext.servletContext.contextPath}/review" target = blank> ${review0}</a></li>
			<li><a href="${pageContext.servletContext.contextPath}/review" target = blank> ${review1}</a></li>
			<li><a href="${pageContext.servletContext.contextPath}/review" target = blank> ${review2}</a></li>
		</ul>		
		<div class = "searchSection">
			<p>Don't like what's trending? Try searching our database</p>
			<form action="${pageContext.servletContext.contextPath}/result" method="get"> 
			<input type = "Submit" name = "searchPage" class="button2" value = "search">
			</form>
		</div>
		<hr>
	</body>
</html>
