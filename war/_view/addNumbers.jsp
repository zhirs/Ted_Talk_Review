<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Add Numbers</title>
		<style type="text/css">
		.error {
			color: red;
		}
		body{
		background-color: #ADD8E6;
		}
		td.label {
			text-align: right;
		}
		</style>
	</head>

	<body>
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
	
		<form action="${pageContext.servletContext.contextPath}/addNumbers" method="post">
			<table>
				<tr>
					<td class="label">First number:</td>
					<td><input type="text" name="first" size="12" value="${adding.first}" /></td>
				</tr>
				<tr>
					<td class="label">Second number:</td>
					<td><input type="text" name="second" size="12" value="${adding.second}" /></td>
				</tr>
				<tr>
					<td class ="label">Third number:</td>
					<td><input type="text" name="third" size="12" value="${adding.third}" /> </td>
				<tr>
					<td class="label">Result:</td>
					<td>${adding.sum}</td>
				</tr>
			</table>
			<input type="Submit" name="submit" value="Add Numbers!">
		</form>
	</body>
</html>