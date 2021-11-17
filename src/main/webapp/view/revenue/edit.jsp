<%--
  Created by IntelliJ IDEA.
  User: HieuDao
  Date: 16-Nov-21
  Time: 10:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <h2>
        <a href="revenue?action=findById">Find Revenue By ID</a>
    </h2>
</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>Edit Revenue</h2>
            </caption>
            <c:if test="${revenue != null}">
                <input type="hidden" name="id" value="<c:out value='${revenue.id}' />"/>
            </c:if>
            <tr>
                <th>Revenue Type:</th>
                <td>
                    <input type="text" name="type" id="type" size="45" value="<c:out value='${revenue.type}'/>"/>
                </td>
            </tr>
            <c:if test="${role == 1}">
                <tr>
                    <th>Amount:</th>
                    <td>
                        <input type="text" name="amount" id="amount" size="45"  value="<c:out value='${revenue.amount}'/>"/>
                    </td>
                </tr>
            <tr>
                <th>Date:</th>
                <td>
                    <input type="date" name="date" id="date" size="15"  value="<c:out value='${revenue.date}'/>"/>
                </td>
            </tr>
            </c:if>
            <tr>
                <th>Description:</th>
                <td>
                    <input type="text" name="description" id="description" size="15"  value="<c:out value='${revenue.description}'/>"/>
                </td>
            </tr>
            <c:if test="${role == 1}">
            <tr>
                <th>Account ID: </th>
                <td>
                    <input type="text" name="account_id" id="account_id" size="15"  value="<c:out value='${revenue.getAccount().getId()}'/>"/>
                </td>
            </tr>
            </c:if>

            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save"/>
                </td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>
