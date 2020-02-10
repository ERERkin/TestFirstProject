<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 10.02.2020
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/authorize" method="GET"></form>
Login : <input type="text" name="login"/>
<br>
Password : <input type="password" name="password"/>
<br>
<input type="submit" value="Send"/>

</body>
</html>
