<%--
  Created by IntelliJ IDEA.
  User: HieuDao
  Date: 16-Nov-21
  Time: 11:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Revenue Management Application</title>
</head>
<body>
<center>
    <h1>Revenue Management</h1>
    <h2>
        <a href="revenue?action=revenue">List All Revenues</a>
    </h2>
    <h2>
        <a href="revenue?action=create">Add New Revenue</a>
    </h2>
    <c:if test="${requestScope['message'] != null}">
        <p>${requestScope['message']}</p>
    </c:if>
    <form method="post">
        <input type="text" name="id" id="id">
        <button type="submit">Find</button>
    </form>
    <c:if test="${requestScope['result'] == true}">
        <table border="1" cellpadding="5">
            <caption><h2>List of Revenues</h2></caption>
            <tr>
                <th>ID</th>
                <th>Type</th>
                <th>Amount</th>
                <th>Date</th>
                <th>Description</th>
                <th>Account ID</th>
                <th>Actions</th>
            </tr>
            <tr>
                <td><c:out value="${revenue.id}"/></td>
                <td><c:out value="${revenue.type}"/></td>
                <td><fmt:formatNumber value = "${revenue.amount}" type = "currency"/></td>
                <td><c:out value="${revenue.date}"/></td>
                <td><c:out value="${revenue.description}"/></td>
                <td><c:out value="${revenue.getAccount().getId()}"/></td>
                <td>
                    <a href="/revenue?action=edit&id=${revenue.id}">Edit</a>
                    <a href="/revenue?action=delete&id=${revenue.id}">Delete</a>
                </td>
            </tr>
        </table>
    </c:if>
</center>
</body>
</html>
