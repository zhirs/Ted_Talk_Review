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
		<form action = "${pageContext.servletContext.contextPath}/professor" method="get">
		<input type = "Submit" name = "redirectProfile" class="button" value = "Profile">
		</form>
		<form action = "${pageContext.servletContext.contextPath}/login" method = "get">
		<input type = "Submit" name = "logout" class="button" value = "Logout">
		</form>
		<br><br><br>
		<hr>
		<h2>Suggested TEDTalks:</h2>
		<h3>Reviews pending approval</h3>
		<c:forEach var="i" begin = "0" end = "${listSize}">
		<div class="pendingReviews">
			<table>
				<tr>
					<td>Review Title:<input type="text" name = "title" size = "40" value="${revNames.get(i)}" readonly> </td>
		 		</tr>
		 		<tr>
		 			<td>Presenter's Name:<input type="text" name = "presenterName"size = "40" value="${revPresenters.get(i)}" readonly> </td>
		 		</tr>
		 		<tr>
		 			<td>URL:<input type="text" name = "url" size = "40" value="${revURLs.get(i)}" readonly> </td>
		 		</tr>
		 		<tr>
		 			<td>Description:<input type="text" name = "description" size = "200" value="${revDescriptions.get(i)}" readonly></td>
		 		</tr>			 	
	 		</table>
	 		<form action="${pageContext.servletContext.contextPath}/professorReviewQueue" method="post"> 
  			<button id="deleteButton" name="delete" value="${revIDs.get(i)}" type="submit">Delete</button>
		</form>
	 		</div>
		</c:forEach>
		
		
	</body>
</html>
