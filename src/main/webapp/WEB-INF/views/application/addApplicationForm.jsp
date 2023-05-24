<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New application</title>
</head>
<body>
<center>
    <table>
        <form method="post" action="/application/addApplication">
            <input type="hidden" name="state" value="Valid">
            <tr><td>
                <select name="type">
                    <option value="Visitor">Visitor</option>
                    <option value="Student">Student</option>
                    <option value="Business">Business</option>
                </select>
            </td></tr>
            <tr><td>
                <select name="client">
                    <c:forEach items="${clientList}" var="client">
                        <option value="${client.id}">${client.fullName}</option>
                    </c:forEach>
                </select>
            </td></tr>
            <tr><td><input type="submit" value="Confirm"></td></tr>
        </form>
    </table>
</center>
</body>
</html>
