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
        <h1>Admin Dashboard</h1>
    </div>
    <div>
        <h3>Customers</h3>
        <table>
            <tr>
                <th>Name</th>
                <th>Next Due Date</th>
                <th>Amount Due</th>
                <th>Package</th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.getFirstName()} ${user.getLastName()}</td>
                    <td>${user.getDueDate()}</td>
                    <td>${user.getPack().getCost()}</td>
                    <td>${user.getPack().getName()}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div>
        <h3>Packages</h3>
        <table>
            <tr>
                <th>Package Name</th>
                <th>Package Cost</th>
                <th>Available</th>
                <th>Users</th>
                <th>Actions</th>
            </tr>
            <c:forEach  items="${packages}" var="pack">
            <tr>
                <td>${pack.getName()}</td>
                <td>${pack.getCost()}</td>
                <c:choose>
                    <c:when test="${pack.getActive() != true}">
                        <td>Unavailable</td>
                    </c:when>
                    <c:otherwise>
                        <td>Available</td>
                    </c:otherwise>
                </c:choose>
                <td>${pack.getUsers().size()}</td>
                <c:choose>
                    <c:when test="${pack.getActive() == true}">
                        <td><a href="/admin/deactivate/${pack.getId()}">Deactivate</a>
                        <c:if test="${pack.getUsers().size() == 0}">
                            <a href="/admin/delete/${pack.getId()}">Delete</a>
                        </c:if>
                        </td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="/admin/activate/${pack.getId()}">Activate</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
        </table>
    </div>
    <div>
        	<c:forEach items="${errors}" var="error">
                    <h4>${error.defaultMessage}</h4>
            </c:forEach>
        <form:form method="post" action="/admin/createPackage" modelAttribute="package">
            <form:label path="name">Package name
                <form:errors path="name" />
                <form:input path="name" />
            </form:label>
            <form:label path="cost">Cost 
                <form:errors path="cost"/>
                <form:input path="cost"/>
            </form:label>
            <input type="submit" value="Create new Package">
        </form:form>
    </div>
    <form action="/logout" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout">
    </form>
</body>
</html>