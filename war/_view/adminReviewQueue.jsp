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
		<h1>Network Admin Review Queue</h1>		

		<form action = "${pageContext.servletContext.contextPath}/networkadmin" method="get">
		<input type = "Submit" name = "redirectProfile" class="button" value = "Profile">
		</form>
		<form action = "${pageContext.servletContext.contextPath}/login" method = "get">
		<input type = "Submit" name = "logout" class="button" value = "Logout">
		</form>
		<br><br><br>
		<hr>
		<h3>Reviews pending approval:</h3>
		<p>Delete reviews not wanted, then approve all at once. </p>
		<c:forEach var="i" begin = "0" end = "${listSize}">
		<div class="pendingReviews">
			<table>
				<tr>
					<td>Review Title:  ${revNames.get(i)} </td>
		 		</tr>
		 		<tr>
		 			<td>Presenter's Name:  ${revPresenters.get(i)} </td>
		 		</tr>
		 		<tr>
		 			<td>URL:  ${revURLs.get(i)} </td>
		 		</tr>
		 		<tr>
		 			<td>Description:  ${revDescriptions.get(i)} </td>
		 		</tr>			 	
	 		</table>
	 		<form action="${pageContext.servletContext.contextPath}/adminReviewQueue" method="post"> 
  			<button id="deleteButton" name="delete" value="${revIDs.get(i)}" type="submit">Delete</button>
			</form>
	 		</div>
		</c:forEach>
		<form action="${pageContext.servletContext.contextPath}/approvedReview" method="get"> 
  		<button id="deleteButton" class = "button" name="delete" value="${revIDs.get(i)}" type="submit">Approve ALL Reviews</button>
		</form>
		<hr>
		
	</body>
</html>
