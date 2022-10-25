<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Task 1</title>
</head>
<body>

<%@ include file="/JSPF/header.jspf" %>

<form method="post">
    <h1><fmt:message key="header.task1"/></h1>
    <label><fmt:message key="task1.label"/>
        <input type="number" name="input">
    </label>
    <button type="submit"><fmt:message key="submit"/></button>
</form>

<c:if test="${not empty output}">
    ${output.display}
</c:if>
<c:if test="${check == 0}">
    <h2><fmt:message key="notfound"/></h2>
</c:if>

</body>
</html>
