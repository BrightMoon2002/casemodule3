<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/17/2021
  Time: 12:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <h2><a href="/spending">Back to home List</a></h2>
    <form action="" method="post">
        <table>
            <caption>
                <h2>Edit Spending</h2>
            </caption>
            <tr>
                <th>Type:</th>
                <td><input type="text" name="type"  size="45"></td>
            </tr>
            <tr>
                <th>Description:</th>
                <td><input type="text" name="description"  size="45"></td>
            </tr>
            <tr>
                <th>Date:</th>
                <td><input type="text" name="date"></td>
            </tr>
            <tr>
                <th>Amount:</th>
                <td><input type="text" name="amount" id="amount" size="45"></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>