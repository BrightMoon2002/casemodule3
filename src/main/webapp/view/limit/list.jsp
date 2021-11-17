<%--
  Created by IntelliJ IDEA.
  User: HieuDao
  Date: 17-Nov-21
  Time: 12:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Limit Management Application</title>
</head>
<body>
<center>
    <h1>Limit Management</h1>
    <h2>
        <a href="limit?action=create">Add New Limit</a>
    </h2>
    <h2>
        <a href="limit?action=findById">Find Limit By ID</a>
    </h2>
</center>
<center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Limits</h2></caption>
            <tr>
                <th>ID</th>
                <th>Amount</th>
                <th>Account ID</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="limit" items="${listLimit}">
                <tr>
                    <td><c:out value="${limit.id}"/></td>
                    <td><fmt:formatNumber value = "${limit.amount}" type = "currency"/></td>
                    <td><c:out value="${limit.getAccount().getId()}"/></td>
                    <td>
                        <a href="/limit?action=edit&id=${limit.id}">Edit</a>
                        <a href="/limit?action=delete&id=${limit.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</center>
</body>
</html>
