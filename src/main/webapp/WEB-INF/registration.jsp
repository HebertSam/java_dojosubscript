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
    <h1>Register</h1>

	<c:forEach items="${errors}" var="error">
            <h4>${error.defaultMessage}</h4>
    </c:forEach>

    <form:form method="post" action="/register" modelAttribute="user">
        <form:label path="username">Email
            <form:errors path="username"/>
            <form:input path="username"/>
        </form:label>
            <form:label path="firstName">First Name
            <form:errors path="firstName"/>
            <form:input path="firstName"/>
        </form:label>
            <form:label path="lastName">Last Name
            <form:errors path="lastName"/>
            <form:input path="lastName"/>       
        </form:label>Password
        <form:label path="password">
            <form:errors path="password"/>
            <form:input path="password"/>
        </form:label>Confirm Password
        <form:label path="passwordConfirm">
            <form:errors path="passwordConfirm"/>
            <form:input path="passwordConfirm"/>
        </form:label>
        <input type="submit" value="Submit">
    </form:form>
    <a href="/login">Login</a>
</body>
</html>