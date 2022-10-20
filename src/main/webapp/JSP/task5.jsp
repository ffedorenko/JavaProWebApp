<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>New order from products ordered today</title>
</head>
<body>

<%@ include file="/JSPF/header.jspf" %>

<form method="post">
    <h1>New order from products ordered today</h1>
    <label>Create new order from products ordered today</label>
    <button type="submit">Submit</button>
</form>

<c:choose>
    <c:when test="${not empty errorMessage}">
        <h3>${errorMessage}</h3>
    </c:when>

    <c:otherwise>
        <c:if test="${output > 0}">
            <h2>Successful created:</h2>
            "${order.display}"
        </c:if>
        <c:if test="${output < 1}">
            <h2>Cannot find any order by this request</h2>
        </c:if>
    </c:otherwise>
</c:choose>
</body>
</html>
