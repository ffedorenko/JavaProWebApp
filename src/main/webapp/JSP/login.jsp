<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>

	<%@ include file="/JSPF/header.jspf" %>

	<form action="/JavaProWebApp/login" method="post">
		<label>
			<input type="text" name="name" placeholder=<fmt:message key="login.name"/>>
		</label>
		<label>
			<input type="text" name="password" placeholder=<fmt:message key="login.password"/>>
		</label>
		<input type="submit" value=<fmt:message key="submit"/>>
	</form>
	<c:if test="${error eq true}">
		<fmt:message key="login.errorMessage"/>
	</c:if>

</body>
</html>