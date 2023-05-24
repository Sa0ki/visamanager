<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.visamanager.models.Application" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All applications</title>
</head>
<body>
<center>
    <table>
        <th>Id</th>
        <th>Date of creation</th>
        <th>State</th>
        <th>Type</th>
        <th>Client Id</th>
        <c:forEach items="${applicationList}" var="application">
            <tr>
                <td>${application.id}</td>
                <td>${application.dob}</td>
                <td>${application.state}</td>
                <td>${application.type}</td>
                <td>${application.client.fullName}</td>
                <td><a href="/application/deleteApplication/${application.id}">Delete</a></td>
                <td><a href="/application/updateApplicationForm/${application.id}">Update</a></td>
                <td><a href="/application/getApplication/${application.id}">Details</a></td>
            </tr>
        </c:forEach>
    </table>
    <a href="/application/addApplicationForm">New</a>
</center>
</body>
</html>
