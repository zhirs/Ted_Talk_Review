<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>		
		<title>TedTalkReviews</title>
		<jsp:include page ="CSS/homePage.css"/>	<!-- ALTERNATIVE TO USEING HREFS-->			
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
		<h2>Suggested TEDTalks:</h2>
		<p>What your peers are viewing:</p>
		<ul id = "links"><!-- THE HREF SHOULD GO TO A LINK THAT AUTO-FILLS THE REVIEW PAGE WITH THE CORRESPONDING TED TALK -->
			<li><a href="https://www.ted.com/talks/anupam_mishra_the_ancient_ingenuity_of_water_harvesting" target = blank>Ingenuity of water harvesting</a></li>
			<li><a href="https://www.ted.com/talks/norman_foster_s_green_agenda" target = blank>Norman Foster's Green Agenda</a></li>
			<li><a href="https://www.ted.com/talks/majd_mashharawi_how_i_m_making_bricks_out_of_ashes_and_rubble_in_gaza" target = blank>Rubble in Gaza</a></li>
		</ul>		
		<div class = "searchSection">
			<p>Don't like what's trending? Try searching our database</p>
			<form action="${pageContext.servletContext.contextPath}" method="get"> 
			<input type = "Submit" name = "searchPage" class="button2" value = "search">
			</form>
		</div>
		<hr>
		
	</body>
</html>