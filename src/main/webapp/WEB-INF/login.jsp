<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Index</title>
    <!-- <link rel="stylesheet" type="text/css" href="/css/style.css"> -->
</head>

<body>
    <h1>Login</h1>
    <c:if test="${logoutMessage != null}">
        ${logoutMessage}
    </c:if>
    <c:if test="${errorMessage != null}">
        ${errorMessage}
    </c:if>
    <form action="/login" method="post">
        <label for="username">Email</label>
        <input type="text" name="username"/>
        <label for="password">password</label>
        <input type="text" name="password">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <input type="submit" value="Login">
    </form>

    <a href="/register">Register</a>
</body>
</html>