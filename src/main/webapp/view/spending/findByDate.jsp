<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/17/2021
  Time: 12:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
</center>
<div align="center">
  <h2><a href="/spending">Back to home List</a></h2>
    <table border="1">
        <caption><h2>List of Spending</h2></caption>
        <tr>
            <th>Name</th>
            <th>Type</th>
            <th>Description</th>
            <th>Amount</th>
            <th>Date</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:forEach var="spending" items="${spendings}">
            <tr>
                <td><c:out value="${spending.getAccount().getName()}"/></td>
                <td><c:out value="${spending.type}"/></td>
                <td><c:out value="${spending.description}"/></td>
                <td><c:out value="${spending.amount}"/></td>
                <td><c:out value="${spending.date}"/></td>
                <td>
                    <a href="/spending?action=edit&id=${spending.id}">Edit</a>
                </td>
                <td>
                    <a href="/spending?action=delete&id=${spending.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
