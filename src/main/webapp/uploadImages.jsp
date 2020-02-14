<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 14.02.2020
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/uploadFile" method="POST" enctype = "multipart/form-data">
    <input type="file" name="image" size="50">
    <br />
    <input type="submit" value="Upload" />
</form>
</body>
</html>
