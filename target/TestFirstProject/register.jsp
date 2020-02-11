<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>register</title>
</head>
<body>
Sign up
<form action = "${pageContext.request.contextPath}/register" method = "post">
    Login : <input type="text" name="login"/>
    <br/>
    Email : <input type="text" name="ema"/>
    <br/>
    Story Name : <input type="text" name="storyName"/>
    <br/>
    Password : <input type="password" name="password"/><br>
    <input type="submit" value = "Send"/>
</form>
<form action = "${pageContext.request.contextPath}/authorize" method = "POST">
    Do you have account?
    <input type="submit" value = "Sign in"/>
</form>
</body>
</html>