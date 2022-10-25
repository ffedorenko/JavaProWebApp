<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Task 2</title>
</head>
<body>

<%@ include file="/JSPF/header.jspf" %>

<form method="post">
    <h1><fmt:message key="header.task2"/></h1>
    <label><fmt:message key="task2.label1"/>
        <input type="text" name="sum">
    </label>
    <label><fmt:message key="task2.label2"/>
        <input type="number" name="distinct">
    </label>>
    <button type="submit"><fmt:message key="submit"/></button>
</form>

<c:if test="${not empty output}">
    <c:forEach items="${output}" var="item">
        ${item.display}<br>
    </c:forEach>
</c:if>

<c:if test="${check == 0}">
    <h2><fmt:message key="notfound"/></h2>
</c:if>

</body>
</html>
