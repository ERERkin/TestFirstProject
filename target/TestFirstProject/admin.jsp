<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 08.02.2020
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action = "${pageContext.request.contextPath}/admin" method = "POST">
    Code : <input type = "text" name="code"/>
    <br/>
    Name : <input type = "text" name="name"/>
    <br/>
    Price : <input type="number" name="price"/>
    <br/>
    url : <input type="url" name="url"/>
    <input type="submit" value = "Send"/>
</form>
</body>
</html>
