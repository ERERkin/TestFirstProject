<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 09.02.2020
  Time: 1:17
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/addProductAmountServlet" method="post">
    <input type="text" name="add"/>
    <br/>
    <input type="text" name="login" value="<%=request.getAttribute("login")%>"/>
    <br/>
    <input type="number" name="month" value="<%=request.getAttribute("thisMonth")%>"/>
    <input type="submit" name="add"/>
</form>
<table>
    <c:forEach items = "${list}" var = "item">
        <tr>
            <td>${item.name}</td>
            <td>${item.code}</td>
            <td>${item.price}</td>
            <td>${item.url}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
