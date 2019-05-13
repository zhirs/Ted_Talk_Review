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
		
		<h1>Welcome ${email} ${session}</h1>		
		<form action = "${pageContext.servletContext.contextPath}/home" method = "get">
		<input type = "Submit" name = "redirectHome" class="button" value = "Home">
		</form>
		<form action="${pageContext.servletContext.contextPath}/review" method="get"> 
		<input type = "Submit" name = "redirectReview" class="button" value = "Review">
		</form>			
		<form action = "${pageContext.servletContext.contextPath}/login" method = "get">
		<input type = "Submit" name = "logout" class="button" value = "Logout">
		</form>			
		<br><br><br><br><br>
		<%--div that presents a created review. when real database is implemented this will be unnecessary --%>
		<div id = "reviewSection">	
			<hr>	
			<h2>Pending reviews:</h2>>
			<c:forEach items="${pendingDescs}" var="pendingDescs">
				<c:out value = "${pendingDescs}"/>
				<p></p>
			</c:forEach>	
			<hr>
			<h2>Approved reviews:</h2>
			<c:forEach items="${approvedDescs}" var="approvedDescs">
				<c:out value = "${approvedDescs}"/>
				<p></p>
			</c:forEach>
			<hr>
			<p>Denied reviews:</p>
			<c:forEach items="${deniedDescs}" var="deniedDescs">
				<c:out value = "${deniedDescs}"/>
				<p></p>
			</c:forEach>	
			<hr>						
		</div>					
	</body>
</html>
