<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Joe's Math Suite</title>
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
		<h1> Joe's Math Suite </h1>
		<hr>
		<form action="${pageContext.servletContext.contextPath}/addNumbers" method="get"> 
		<input type = "Submit" name = "addNumbers" value = "Add Numbers">
		</form>
		<hr>
		<form action="${pageContext.servletContext.contextPath}/subtractNumbers" method="get"> 
		<input type = "Submit" name = "subtractNumbers" value = "Subtract Numbers">
		</form>
		<hr>
		<form action="${pageContext.servletContext.contextPath}/multiplyNumbers" method="get"> 
		<input type = "Submit" name = "multiplyNumbers" value = "Mutliply Numbers">
		</form>
		<hr>
		<form action="${pageContext.servletContext.contextPath}/divideNumbers" method="get"> 
		<input type = "Submit" name = "divideNumbers" value = "Divide Numbers">
		</form>
		<hr>
		<form action="${pageContext.servletContext.contextPath}/guessingGame" method="get"> 
		<input type = "Submit" name = "guessingGame" value = "Guessing Game">
		</form>
		<hr>
	</body>
</html>