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
    <div>
        <h1>Welcome to Dojosubscriptions ${currentUser.getFirstName()}</h1>
        <h3>Please choose a subscription and start date</h3>
    </div>
    <div>
        ${error}
        <form action="/choosePackage" method="post">
            Due Day: <input type="number" name="dueDay" min="1" max="30" value="1">
            Package: <select name="pack" id="">
                <c:forEach items="${packages}" var="pack">
                    <c:if test="${pack.getActive() == true}">
                        <option value="${pack.getId()}">${pack.getName()} (${pack.getCost()})</option>
                    </c:if>
                </c:forEach>               
            </select>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="submit" value="Sign Up!">
        </form>
    </div>
    <form action="/logout" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout">
    </form>

</body>
</html>