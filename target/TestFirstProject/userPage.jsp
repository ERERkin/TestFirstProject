<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 08.02.2020
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/productListServlet" method="post">
    <input type="text" name="search"/>
    <br/>
    <input type="text" name="login" value="<%=request.getAttribute("login")%>"/>
    <br/>
    <input type="number" name="month" value="<%=request.getAttribute("thisMonth")%>"/>
    <input type="submit" name="search"/>
</form>
<table>
    <c:forEach items = "${list}" var = "item">
        <tr>
            <td>${item.name}</td>
        </tr>
        <tr>
            <td>${item.amountInStock}</td>
            <td>${item.amountSold}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>--%>
<html>
<head>
    <title>Document</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div class="space"></div>

<!-- all items -->

<div class="container">


    <!-- first header -->

    <div class="header-1">
        <div class="search">
            <form action="${pageContext.request.contextPath}/productListServlet" method="post">
                <input type="text" name="search"/>
                <br/>
                <input type="text" name="login" value="<%=request.getAttribute("login")%>"/>
                <br/>
                <input type="number" name="month" value="<%=request.getAttribute("thisMonth")%>"/>
                <input type="submit" name="search"/>
            </form>
        </div>

    </div>
    <!-- second header -->
    <div class="space"></div>
    <div class="header-2">
        <div class="user-profil-table">
            <div class="avatarka">
                <img src="data:image/jpeg;base64,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"
                     alt="Smiley face" height="120" width="130"/>
            </div>

            <div class="some-shit">
                <h2><%=request.getAttribute("userStoryName")%></h2>
            </div>

        </div>
        <div class="info-about-user">
            <div class="info-content">
                <h3><%=request.getAttribute("userEmail")%> </h3>
                <p>Лаалалалалалалал
                    алалалалалалалал
                    алалалалалалалал
                    алалалалалалалалал
                </p>
            </div>
        </div>
    </div>
    <!-- fixed period -->
    <div class="period">

    </div>

    <div class="space" ></div>

    <!-- tables -->
    <div class="contains-table">
        <c:forEach items = "${list}" var = "item">
        <div class="table">
            <!-- ------------------------------- -->
            <div class="first-side">
                <a href="#" class="btn btn-white btn-animation-1">
                    Закупка
                </a>
                <a href="#" class="btn btn-white btn-animation-1">
                    Закупка
                </a>
            </div>
            <!-- ----------------------------------- -->
            <div class="middle-side">
                <h2>${item.name}</h2>
                <!-- <div class="some_class">
                    <div class="borderr"></div>
                    <input class="number">
                    <button>fuck</button>
                </div> -->
                <table>
                        <tr>
                            <td>Yesterday:${item.name}</td>
                        </tr>
                        <tr>
                            <td>Today    :${item.name}</td>
                        </tr>
                        <tr>
                            <td>${item.todayAmountInStock}</td>
                            <td>${item.todayAmountSold}</td>
                        </tr>
                </table>
            </div>

            <!-- -------------------------------------- -->
            <div class="second-side">
                <a href="#" class="btn btn-white btn-animation-1">
                    Закупка
                </a>
                <a href="#" class="btn btn-white btn-animation-1">
                    Закупка
                </a>
            </div>
        </div>
        </c:forEach>
</div>









</div>
</body>
</html>