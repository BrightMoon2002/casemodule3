<%--
  Created by IntelliJ IDEA.
  User: HieuDao
  Date: 16-Nov-21
  Time: 9:54 PM
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
        <a href="revenue?action=create">Add New Revenue</a>
    </h2>
    <h2>
        <a href="revenue?action=findById">Find Revenue By ID</a>
    </h2>
</center>
<c:if test="${role == 2}">
    <center>
        <div align="center">
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
                <c:forEach var="revenue" items="${listRevenue}">
                    <tr>
                        <td><c:out value="${revenue.id}"/></td>
                        <td><c:out value="${revenue.type}"/></td>
                        <td><fmt:formatNumber value = "${revenue.amount}" type = "currency"/></td>
                        <td><c:out value="${revenue.date}"/></td>
                        <td><c:out value="${revenue.description}"/></td>
                        <td><c:out value="${revenue.getAccount().getId()}"/></td>
                        <td>
                            <a href="/revenue?action=edit&id=${revenue.id}">Edit</a>
                        </td>
                    </tr>
                </c:forEach>

            </table>
        </div>
        <h3>Revenue Total: <fmt:formatNumber value = "${revenueTotalUser}" type = "currency"/></h3>
    </center>
</c:if>


<c:if test="${role == 1}">
    <center>
        <div align="center">
            <table border="1" cellpadding="5">
                <caption><h2>List of User Revenues</h2></caption>
                <tr>
                    <th>ID</th>
                    <th>Type</th>
                    <th>Amount</th>
                    <th>Date</th>
                    <th>Description</th>
                    <th>Account ID</th>
                    <th>Actions</th>
                </tr>
                <c:forEach var="revenue" items="${listRevenueUser}">
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
                </c:forEach>

            </table>
        </div>
        <h3>Revenue Total: <fmt:formatNumber value = "${revenueTotalUser}" type = "currency"/></h3>
    </center>

    <center>
        <div align="center">
            <table border="1" cellpadding="5">
                <caption><h2>List of Administrator Revenues</h2></caption>
                <tr>
                    <th>ID</th>
                    <th>Type</th>
                    <th>Amount</th>
                    <th>Date</th>
                    <th>Description</th>
                    <th>Account ID</th>
                    <th>Actions</th>
                </tr>
                <c:forEach var="revenue" items="${listRevenue}">
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
                </c:forEach>

            </table>
        </div>
        <h3>Revenue Total: <fmt:formatNumber value = "${revenueTotalAdmin}" type = "currency"/></h3>
    </center>
</c:if>

</body>
</html>
