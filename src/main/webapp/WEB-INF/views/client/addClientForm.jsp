<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New client</title>
</head>
<body>
<center>
    <table>
       <form method="post" action="/client/addClient">
           <tr><td><input type="text" name="fullName" placeholder="Full name"></td></tr>
           <tr><td><input type="email" name="email" placeholder="Example@something.else"></td></tr>
           <tr><td><input type="password" name="password" placeholder="Password"></td></tr>
           <tr><td><input type="date" name="dob" placeholder="Date of birth"></td></tr>
           <tr><td><input type="submit" value="Confirm"></td></tr>
       </form>
    </table>
</center>
</body>
</html>
