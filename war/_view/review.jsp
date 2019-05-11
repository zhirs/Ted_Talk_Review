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
		<form action = "${pageContext.servletContext.contextPath}/review" method = "post">
			<table>
				<tr>
					<td>Review Title:<input type="text" name = "title" placeholder = "e.g. Art of listening" required = "required" size = "40" value="${name}"> </td>
		 		</tr>
		 		<tr>
		 			<td>Presenter's Name:<input type="text" name = "presenterName" placeholder = "e.g. John Appleseed" required = "required"size = "40" value="${presenterName}"> </td>
		 		</tr>
		 		<tr>
		 			<td>URL:<input type="text" name = "url" placeholder = "e.g. https://www.ted.com/talks" required = "required" size = "40" value="${url}"> </td>
		 		</tr>
		 		<tr>
		 			<td>Tags:<input type="text" name = "tags" placeholder = "e.g. enigneering" required = "required" size = "30" value="${tag}"> </td>
		 		</tr>
		 		<tr><!-- consider having click to input rather than user entering asterisk -->
		 			<td>Rating:<input type="text" name = "rating" placeholder = "e.g. * * * * *" required = "required" size = "20" value="${reviewHandle}"> </td>
		 		</tr>			 	
	 		</table>

		 				 <%--This is the textfield for the review's description --%> 
			<div id="cct_embed_counts" align ="center">
			 <%--the form action will call the post method in the review servlet, then it will redirect it to the profile servlet --%> 
			
			<textarea id="cct_embed_input_text" name="description" rows="4" cols="50" maxlength="250" placeholder="Enter Description here max of 250 characters" spellcheck="true"></textarea>
			   
			   <div id="star5" class="notation-star" onClick="notation(this.id);"></div>
			   <div id="star4" class="notation-star" onClick="notation(this.id);"></div>
			   <div id="star3" class="notation-star" onClick="notation(this.id);"></div>
			   <div id="star2" class="notation-star" onClick="notation(this.id);"></div>
			   <div id="star1" class="notation-star" onClick="notation(this.id);"></div>
			   <input type="hidden" id="notationNote" name="notation_note" value="0">
			<input type = "Submit" class="button2" value = "Submit Review">
			<div id="cct_embed_result"></div>
			<div id="cct_powered_by">Powered by <a href="https://charactercounttool.com">Character Counter</a></div>
			<script type="text/javascript" src="https://charactercounttool.com/cct_embed.min.js"></script>
			</div>
		</form>

			</div>
		<hr>
		<div class = "commonReviews"><!-- SECTION OF OTHER RELATED REVIEWS -->
		<div id = "avgRating">
			<p> average rating: ${avgRating}</p>
		</div>
			<p>${common1Title} || ${common1URL} || ${common1Rate}</p>
			<p>${common2Title} || ${common2URL} || ${common2Rate}</p>
		
		</div>
		<p>These are the previous reviews for this Ted Talk</p>
		<c:forEach var="i" begin = "0" end = "1">
			<div class = "reviewSection">
				<table>
			 		<tr><!-- consider having click to input rather than user entering asterisk -->
			 			<td>Rating:       <input type="text" name = "rating" size = "20" value="${ratings.get(i)}"> </td>
			 		</tr>	
			 		<tr>
			 			<td>Description: <input id ="textboxid" type="text" name = "rating" size = "20" value="${descriptions.get(i)}"> </td>
			 		</tr>		 	
		 		</table>
			</div>
		</c:forEach>

	</body>
</html>
