<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/17/2021
  Time: 1:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <h1>Delete Spending</h1>
    <h2>
        <a href="/spending">Back to Home</a>
    </h2>
</center>
<div align="center">
    <form action="" method="post">
        <table border="1px">
            <caption>
                <h2>
                    Edit User
                </h2>
            </caption>
            <tr>
                <th>Type:</th>
                <td>
                    <c:out value='${spending.type}'/>
                </td>
            </tr>
            <tr>
                <th>Spending amount:</th>
                <td>
                    <c:out value='${spending.amount}'/>
                </td>
            </tr>
            <tr>
                <th>Date:</th>
                <td>
                    <c:out value='${spending.date}'/>
                </td>
            </tr>
            <tr>
                <th>Description:</th>
                <td>
                    <c:out value="${spending.description}"/>
                </td>
            </tr>
            <tr>
                <td>Are you sure Delete</td>
                <td colspan="2" align="center">
                    <input type="submit" value="Delete"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
