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
    Login : <input type="text" name="login"/>
    <br/>
    Email : <input type = "text" name="email"/>
    <br/>
    Story Name : <input type="text" name="storyName"/>
    <br/>
    Password : <input type="password" name="password"/><br>
    <input type="submit" value = "Send"/>
</form>
<form action = "authorize.jsp" method = "POST">
    Do you have account?
    <input type="submit" value = "Sign in"/>
</form>
</body>
</html>
