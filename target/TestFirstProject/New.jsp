<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 09.02.2020
  Time: 7:19
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <c:forEach items = "${list}" var = "item">
        <tr>
            <td>${item}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
