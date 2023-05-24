<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.visamanager.models.Client" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Client n° ${client.id}</title>
</head>
<body>
<center>
    <table>
        <th>Id</th>
        <th>Full name</th>
        <th>Dob</th>
        <th>Email</th>
            <tr>
                <td>${client.id}</td>
                <td>${client.fullName}</td>
                <td>${client.dob}</td>
                <td>${client.email}</td>
                <td><a href="/client/deleteClient/${client.id}">Delete</a></td>
                <td><a href="/client/updateClientForm/${client.id}">Update</a></td>
            </tr>
    </table>
</center>
</body>
</html>
