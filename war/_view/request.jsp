<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>		
		<title>TedTalkReviews</title>
		<jsp:include page ="CSS/coreCSS.css"/> <!-- ALTERNATIVE TO USEING HREFS-->	
			
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
		<h1>Request</h1>		
		<hr>
		<div id = "buttonBlock">
			<form action = "${pageContext.servletContext.contextPath}/home" method = "get">
			<input type = "Submit" name = "redirectHome" class="button" value = "Home">
			</form>
			<form action = "${pageContext.servletContext.contextPath}/profile" method="get">
			<input type = "Submit" name = "redirectProfile" class="button" value = "Profile">
			</form>
			<form action = "${pageContext.servletContext.contextPath}/login" method = "get">
			<input type = "Submit" name = "logout" class="button" value = "Logout">
			</form>
		</div>
		<br><br><br>
		<hr>
		<h3>Suggested TEDTalks</h3>
		<p>Here's what your peers are viewing:</p>
		<ul id = "links">
			<li><a href="https://www.ted.com/talks/anupam_mishra_the_ancient_ingenuity_of_water_harvesting" target = blank>Ingenuity of water harvesting</a></li>
			<li><a href="https://www.ted.com/talks/norman_foster_s_green_agenda" target = blank>Norman Foster's Green Agenda</a></li>
			<li><a href="https://www.ted.com/talks/majd_mashharawi_how_i_m_making_bricks_out_of_ashes_and_rubble_in_gaza" target = blank>Rubble in Gaza</a></li>
			<li><a href="https://ycpcs.github.io/cs320-spring2019" target = blank>Software Engineering Design </a></li>
		</ul>		
		<hr>
		<h2>Review Example:</h2>
		<p> review body </p>
		<form action = "${pageContext.servletContext.contextPath}/review" method = "get">
		<input type = "Submit" name = "review" class="button2" value = "Review">
		</form>
		<hr>
	</body>
</html>