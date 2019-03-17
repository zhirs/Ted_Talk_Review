<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Subtract Numbers</title>
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
	
		<form action="${pageContext.servletContext.contextPath}/subtractNumbers" method="post">
			<table>
				<tr>
					<td class="label">Minuend:</td>
					<td><input type="text" name="eighth" size="12" value="${subtracting.eighth}" /></td>
				</tr>
				<tr>
					<td class="label">Subtrahend</td>
					<td><input type="text" name="ninth" size="12" value="${subtracting.ninth}" />
					</td>
				</tr>
				<tr>
					<td class="label">Result:</td>
					<td>${subtracting.difference}</td>
				</tr>
			</table>
			<input type="Submit" name="submit" value="Subtract Numbers!">
		</form>
	</body>
</html>