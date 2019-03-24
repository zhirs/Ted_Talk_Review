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
		<h1> Login Page </h1>
		<hr>
		<form action="${pageContext.servletContext.contextPath}/login" method="post">
			<table>
				<tr>
					<td> <input type="text" name = "u" placeholder = "Username" required = "required" value="${profileM.user}"> </td>
		 		</tr>
		 		<tr>
		 			<td> <input type="password" name = "p" placeholder = "Password" required = "required" value="${profileM.pass}"> </td>
		 		</tr>
	 		</table>
	 		<button type="submit" name="submit" value = "Login">Login</button>
		 </form>
		<hr>
		
	</body>
</html>