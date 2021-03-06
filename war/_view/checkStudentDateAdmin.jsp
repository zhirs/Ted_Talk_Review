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
		<h1>Search Reviews by Date</h1>		

		<form action = "${pageContext.servletContext.contextPath}/networkadminHome" method="get">
		<input type = "Submit" name = "redirectProfile" class="button" value = "Home">
		</form>		
		<form action = "${pageContext.servletContext.contextPath}/networkadmin" method="get">
		<input type = "Submit" name = "redirectProfile" class="button" value = "Profile">
		</form>	
		<form action = "${pageContext.servletContext.contextPath}/login" method = "get">
		<input type = "Submit" name = "logout" class="button" value = "Logout">
		</form>
		<br><br><br>
		<h2>Search for Reviews:</h2>
		
		<div class = "searchSection">
			<form action="${pageContext.servletContext.contextPath}/checkStudentDateAdmin" method="post">
			<input type="text" name = "searchName" placeholder = "Student Username" required = "required" >
			<input type="text" name = "month1" placeholder = "Month" required = "required" >
			<input type="text" name = "day1" placeholder = "Day" required = "required">
			<input type="text" name = "year1" placeholder = "Year" required = "required">
			<br>
			<input type="text" name = "month2" placeholder = "Month" required = "required" >
			<input type="text" name = "day2" placeholder = "Day" required = "required">
			<input type="text" name = "year2" placeholder = "Year" required = "required">
		 		<button type="submit" name="submit" value = "searchDate">Submit Date</button>
			 </form>
		 </div>
		 <hr>
		 <div id = "reviewSection">	
			
			<c:forEach var="reviews" items="${reviews}">
				<c:out value = "${reviews}"/> <br><br>
			</c:forEach>	
		</div>
		<hr>
	</body>
</html>
