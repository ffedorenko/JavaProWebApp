<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Info by product not present, get by today</title>
</head>
<body>

<%@ include file="/JSPF/header.jspf" %>

<form method="post">
    <h1>Info by product not present, get by today</h1>
    <label>Name of the product not present in orders:
        <input type="text" name="input">
    </label>
    <button type="submit">Submit</button>
</form>

<c:if test="${not empty output}">
    <c:forEach items="${output}" var="item">
        ${item.display}<br>
    </c:forEach>
</c:if>

<c:if test="${check == 0}">
    <h2>Cannot find any order by this request</h2>
</c:if>

</body>
</html>
