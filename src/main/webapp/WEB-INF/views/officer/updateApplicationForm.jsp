<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.visamanager.models.Application" %>
<%@ page import="com.visamanager.models.Enums.StateOfApplication" %>
<%@ page import="com.visamanager.models.Enums.TypeOfApplication" %>
<html>
<head>
    <title>Update ${application.client.fullName}'s application}</title>
</head>
<body>
<center>
    <table>
        <form method="post" action="/application/updateApplication">
            <input type="hidden" name="id" value="${application.id}">
            <input type="hidden" name="client" value="${application.client.id}">
            <input type="hidden" name="dob" value="${application.dob}">
            <tr><td>
                <select name="type">
                    <c:forEach items="${TypeOfApplication.values()}" var="type">
                        <c:choose>
                            <c:when test="${type.toString().equals(application.type.toString())}">
                                <option value="${type}" selected>${type}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${type}">${type}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td></tr>
            <tr><td>
                <select name="state">
                    <c:forEach items="${StateOfApplication.values()}" var="state">
                        <c:choose>
                            <c:when test="${state.toString().equals(application.state.toString())}">
                                <option value="${state}" selected>${state}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${state}">${state}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td></tr>
            <tr><td><input type="submit" value="Confirm"></td></tr>
        </form>
    </table>
</center>
</body>
</html>
