<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Delete all orders contains some product</title>
</head>
<body>

<c:import url="/JSPF/header.jspf"/>

<form method="post">
    <h1>Delete all orders contains some product</h1>
    <label>Product name:
        <input type="text" name="product">
    </label>
    <label>Product count:
        <input type="number" name="count">
    </label>
    <button type="submit">Submit</button>
</form>

<c:if test="${output > 0}">
    <h2>Successful deleted!</h2>
</c:if>

<c:if test="${output < 1}">
    <h2>Cannot find any order by this request</h2>
</c:if>

</body>
</html>
