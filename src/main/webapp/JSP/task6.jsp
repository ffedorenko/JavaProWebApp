<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Task 6</title>
</head>
<body>

<%@ include file="/JSPF/header.jspf" %>

<form method="post">
    <h1><fmt:message key="header.task6"/></h1>
    <label><fmt:message key="task6.label1"/>
        <input type="text" name="product">
    </label>
    <label><fmt:message key="task6.label2"/>
        <input type="number" name="count">
    </label>
    <button type="submit"><fmt:message key="submit"/></button>
</form>
<c:choose>
    <c:when test="${not empty errorMessage}">
        <h3>${errorMessage}</h3>
    </c:when>

    <c:otherwise>
        <c:if test="${output > 0}">
            <h2><fmt:message key="task6.deleted"/></h2>
        </c:if>
        <c:if test="${output < 1}">
            <h2><fmt:message key="notfound"/></h2>
        </c:if>
    </c:otherwise>
</c:choose>
</body>
</html>
