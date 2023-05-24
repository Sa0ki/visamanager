<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.visamanager.models.Application" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Application n° ${application.id}</title>
</head>
<body>
<center>
    <table>
        <th>Id</th>
        <th>Date</th>
        <th>Type</th>
        <th>State</th>
        <th>Client</th>
        <tr>
            <td>${application.id}</td>
            <td>${application.dob}</td>
            <td>${application.type}</td>
            <td>${application.state}</td>
            <td>${application.client.fullName}</td>
            <td><a href="/application/deleteApplication/${application.id}">Delete</a></td>
            <td><a href="/application/updateApplicationForm/${application.id}">Update</a></td>
        </tr>
    </table>
</center>
</body>
</html>
