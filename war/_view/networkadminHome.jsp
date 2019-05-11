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
		<h1>Network Admin Home</h1>
		<hr>		
		<form action = "${pageContext.servletContext.contextPath}/networkadmin" method="get">
		<input type = "Submit" name = "redirectProfile" class="button" value = "Profile">
		</form>
		<form action = "${pageContext.servletContext.contextPath}/login" method = "get">
		<input type = "Submit" name = "logout" class="button" value = "Logout">
		</form>
		<br><br><br>
		<hr>
		<form action = "${pageContext.servletContext.contextPath}/studentOverview" method = "get">
		<input type = "Submit" name = "logout" class="button" value = "Overview">
		</form>
		<h2>Suggested TEDTalks:</h2>
		<p>What your peers are viewing:</p>
		<ul id = "links"><!-- THE HREF SHOULD GO TO A LINK THAT AUTO-FILLS THE REVIEW PAGE WITH THE CORRESPONDING TED TALK -->
			<li><a href="${pageContext.servletContext.contextPath}/review" target = blank> ${review0}</a></li>
			<li><a href="${pageContext.servletContext.contextPath}/review" target = blank> ${review1}</a></li>
			<li><a href="${pageContext.servletContext.contextPath}/review" target = blank> ${review2}</a></li>
		</ul>	
		<div class = "searchSection">
			<p>Search Page:</p>
			<form action="${pageContext.servletContext.contextPath}/result" method="get"> 
			<input type = "Submit" name = "searchPage" class="button2" value = "search">
			</form>
		</div>
		<hr>
		<h3>Accounts pending approval</h3>
		
		<c:forEach items="${newbs}" var="newbs">
      		<td><c:out value="${newbs}"/></td>
      		<p></p>
  		</c:forEach>
  		
  		<c:forEach items="${newbies}" var="newbies">
      		<td><c:out value="${newbies}"/></td>
      		<p></p>
  		</c:forEach>	
  			
  		<h3>Account to reject</h3>
  		<form action="${pageContext.servletContext.contextPath}/networkadminHome" method="post"> 
  			<input type = "text" name = "delete" placeholder = "Username" value = "${delete}">
		</form>
		<form action="${pageContext.servletContext.contextPath}/approvedStudents" method="get"> 
			<input type = "Submit" name = "searchPage" class="button2" value = "Approve all students">
		</form>
	</body>
</html>
