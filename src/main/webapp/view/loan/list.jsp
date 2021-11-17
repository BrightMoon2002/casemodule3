<%--
  Created by IntelliJ IDEA.
  User: HieuDao
  Date: 16-Nov-21
  Time: 9:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Revenue Management Application</title>
</head>
<body>
<center>
    <h1>Revenue Management</h1>
    <h2>
        <a href="loans?action=create">Add New Revenue</a>
    </h2>
    <h2>
        <a href="loans?action=search">Find Revenue By ID</a>
    </h2>
</center>
<center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Loan</h2></caption>
            <tr>
                <th>Name</th>
                <th>Amount</th>
                <th>start of Loan</th>
                <th>end of Loan</th>
                <th>Interest package</th>
                <th>status of loan</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="loan" items="${list}">
                <tr>
                    <td><c:out value="${loan.account.name}"/></td>
                    <td><fmt:formatNumber value = "${loan.getAmount()}" type = "currency"/></td>
                    <td><c:out value="${loan.startOfLoan}"/></td>
                    <td><c:out value="${loan.endOfLoan}"/></td>
                    <td><c:out value="${loan.getInterest().getName()}"/></td>
                    <td><c:out value="${loan.status.name}"/></td>
                    <td>
                        <a href="/loans?action=edit&id=${loan.id}">Edit</a>
                        <a href="/loans?action=delete&id=${loan.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </div>
</center>
</body>
</html>
