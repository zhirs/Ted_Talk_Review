<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>TedTalkReviews</title>
		<jsp:include page = "CSS/reviewPage.css"/> <!-- ALTERNATIVE TO USEING HREFS-->
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
		<hr>
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
		<hr>		
		<div class = "reviewSection">
				<table>
					<tr>
						<td> <input type="text" name = "name" placeholder = "e.g. my first review" required = "required" value="${reviewHandle}"> </td>
			 		</tr>
					<tr>
						<td> <input type="text" name = "title" placeholder = "e.g. Art of listening" required = "required" value="${reviewHandle}"> </td>
			 		</tr>
			 		<tr>
			 			<td> <input type="text" name = "presenterName" placeholder = "e.g. John Appleseed" required = "required" value="${reviewHandle}"> </td>
			 		</tr>
			 		<tr>
			 			<td> <input type="text" name = "url" placeholder = "e.g. https://www.ted.com/talks" required = "required" value="${reviewHandle}"> </td>
			 		</tr>
			 		<tr>
			 			<td> <input type="text" name = "description" placeholder = "e.g. wearable tech is the future!" required = "required" value="${reviewHandle}"> </td>
			 		</tr>
			 		<tr>
			 			<td> <input type="text" name = "tags" placeholder = "e.g. enigneering" required = "required" value="${reviewHandle}"> </td>
			 		</tr>
			 		<tr><!-- consider having click to input rather than user entering asterisk -->
			 			<td> <input type="text" name = "rating" placeholder = "e.g. * * * * *" required = "required" value="${reviewHandle}"> </td>
			 		</tr>
			 		
			 	
		 		</table>
		 				 <%--This is the textfield for the review's description --%> 
			<div id="cct_embed_counts" align ="center">
			 <%--the form action will call the post method in the review servlet, then it will redirect it to the profile servlet --%> 
			<form action = "${pageContext.servletContext.contextPath}/review" method = "post">
			<textarea id="cct_embed_input_text" name="reviewText" rows="4" cols="50" maxlength="250" placeholder="Enter Description here max of 250 characters" spellcheck="true"></textarea>
			<input type = "Submit" class="button2" value = "Submit Review">
			</form>
			<div id="cct_embed_result"></div>
			<div id="cct_powered_by">Powered by <a href="https://charactercounttool.com">Character Counter</a></div>
			<script type="text/javascript" src="https://charactercounttool.com/cct_embed.min.js"></script>

			</div>
		</div>
		<hr>
		

	</body>
</html>