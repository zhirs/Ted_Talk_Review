<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Login Page</title>
		<style type = "text/css">
		body{
		background-color: #ADD8E6;
		}
		h1{
		text-align: center;
		color: purple;
		} 
		</style>
	</head>
	
	<body>
		<h1> Test Login Page </h1>
		<hr>
		<form action="${pageContext.servletContext.contextPath}/login" method="get"> 
		<input type = "Submit" name = "redirectLogin" value = "redirectLogin">
		</form>
		<hr>
	</body>
</html>