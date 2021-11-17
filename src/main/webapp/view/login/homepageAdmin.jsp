<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 17/11/2021
  Time: 8:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<body>
<h1><a href="/spending?id=${account.id}">aaa</a></h1>
<table>
    <c:forEach items="${accountList}" var="a">
        <tr>
            <td>${a.username}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
