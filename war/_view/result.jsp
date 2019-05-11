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
		<h1> Search </h1>
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
		<form action = "${pageContext.servletContext.contextPath}/result" method = "post">
		<input type = "text" name = "input" placeholder = "search" required = "required" value = "${input}">
		</form>

		<hr>
  	
  		<c:forEach items="${titles}" var="titles">
      		<td> <input type = "hidden" value="${titles}"></td>
      		<a href="${pageContext.servletContext.contextPath}/review" target = blank > ${titles} </a>
  		</c:forEach>
		<h2>${error}</h2>	
	</body>
</html>