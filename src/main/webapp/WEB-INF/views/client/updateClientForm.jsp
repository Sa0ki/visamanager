<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update ${client.fullName}</title>
</head>
<body>
<center>
    <table>
        <form method="post" action="/client/updateClient">
            <input type="hidden" name="id" value="${client.id}">
            <tr><td><input type="text" name="fullName" placeholder="Full name" value="${client.fullName}"></td></tr>
            <tr><td><input type="email" name="email" placeholder="Example@something.else" value="${client.email}"></td></tr>
            <tr><td><input type="password" name="password" placeholder="Password" value="${client.password}"></td></tr>
            <tr><td><input type="date" name="dob" placeholder="Date of birth" value="${client.dob}"></td></tr>
            <tr><td><input type="submit" value="Update"></td></tr>
        </form>
    </table>
</center>
</body>
</html>
