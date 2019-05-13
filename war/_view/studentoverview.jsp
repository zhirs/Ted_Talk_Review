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
		<h1>Student Overview</h1>
		<hr>		
		<form action = "${pageContext.servletContext.contextPath}/home" method = "get">
		<input type = "Submit" name = "logout" class="button" value = "Overview">
		</form>
		<form action="${pageContext.servletContext.contextPath}/result" method="get"> 
		<input type = "Submit" name = "searchPage" class="button" value = "Search">
		</form>
		<form action = "${pageContext.servletContext.contextPath}/login" method = "get">
		<input type = "Submit" name = "logout" class="button" value = "Logout">
		</form>
		<hr>
		<hr>
		<br><br><br>
		<c:forEach items="${tabs}" var="tabs">
      		<td><c:out  value="${tabs}"/></td>
      		<p></p>
  		</c:forEach>  			
	</body>
</html>
