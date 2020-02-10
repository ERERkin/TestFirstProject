<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 10.02.2020
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>register</title>
</head>
<body>
Sign up
<form action = "${pageContext.request.contextPath}/register" method = "POST">
    Login : <input type="text" required placeholder="login"/>
    <br/>
    Email : <input type = "email" required placeholder="email"/>
    <br/>
    Story Name : <input type="text" required placeholder="story name"/>
    <br/>
    Password : <input type="password" required placeholder="password"/><br>
    <input type="submit" value = "Send"/>
</form>
<form action = "${pageContext.request.contextPath}/authorize" method = "POST">
    Do you have account?
    <input type="submit" value = "Sign in"/>
</form>
</body>
</html>
