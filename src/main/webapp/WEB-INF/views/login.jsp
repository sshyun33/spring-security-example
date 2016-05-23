<%--
  Created by IntelliJ IDEA.
  User: ilanian
  Date: 2016-04-21
  Time: 오후 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>login</title>
</head>
<body>

<c:if test="${param.error != null}">
    <p>Invalid username and password</p>
</c:if>
<c:if test="${param.logout != null}">
    <p>you have been logged out.</p>
</c:if>


<p>Custom Login Page</p>

<form action="/login" method="post">

<label for="username">Username</label>
<input type="text" id="username" name="username">
<label for="password">password</label>
<input type="password" id="password" name="password">
<input type="submit" value="submit">

<input type="hidden"
       name="${_csrf.parameterName}"
       value="${_csrf.token}"/>

</form>
</body>
</html>
