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
		<h3>Reviews pending approval</h3>
		<c:forEach var="reviews" items="${revDescriptions}">
		<div>
			<table>
				<tr>
					<td>Review Title:<input type="text" name = "title" size = "40" value="${revNames}"> </td>
		 		</tr>
		 		<tr>
		 			<td>Presenter's Name:<input type="text" name = "presenterName"size = "40" value="${revPresenters}"> </td>
		 		</tr>
		 		<tr>
		 			<td>URL:<input type="text" name = "url" size = "40" value="${revURLs}"> </td>
		 		</tr>
		 		<tr>
		 			<td>Description:<input type="text" name = "description" size = "100" value="${revDescriptions}"></td>
		 		</tr>			 	
	 		</table>
	 		</div>
		</c:forEach>
		
		
		<h3>Review Name to reject</h3>
  		<form action="${pageContext.servletContext.contextPath}/networkadminHome" method="post"> 
  			<input type = "text" name = "delete" placeholder = "Username" value = "${delete}">
		</form>
		<form action="${pageContext.servletContext.contextPath}/approvedStudents" method="get"> 
			<input type = "Submit" name = "searchPage" class="button2" value = "Approve all students">
		</form>
	</body>
</html>
