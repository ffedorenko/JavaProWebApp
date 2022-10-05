<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Info by Order number</title>
</head>
<body>

<c:import url="/JSPF/header.jspf"/>

<form method="post">
    <h1>Info by Order number</h1>
    <label>Order number:
        <input type="number" name="input">
    </label>
    <button type="submit">Submit</button>
</form>

<c:if test="${not empty output}">
    ${output.display}
</c:if>
<c:if test="${check == 0}">
    <h2>Cannot find any order by this request</h2>
</c:if>

</body>
</html>
