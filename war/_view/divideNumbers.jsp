<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Divide Numbers</title>
		<style type="text/css">
		.error {
			color: red;
		}
		td.label {
			text-align: right;
		}
		body{
		background-color: #ADD8E6;
		}
		</style>
	</head>

	<body>
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
	
		<form action="${pageContext.servletContext.contextPath}/divideNumbers" method="post">
			<table>
				<tr>
					<td class="label">Dividend:</td>
					<td><input type="text" name="sixth" size="12" value="${division.sixth}" /></td>
				</tr>
				<tr>
					<td class="label">Divisor: </td>
					<td><input type="text" name="seventh" size="12" value="${division.seventh}" /></td>
				</tr>
				<tr>
					<td class="label">Result:</td>
					<td>${division.quotient}</td>
				</tr>
			</table>
			<input type="Submit" name="submit" value="Divide Numbers!">
		</form>
	</body>
</html>