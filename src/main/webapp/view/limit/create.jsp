<%--
  Created by IntelliJ IDEA.
  User: HieuDao
  Date: 17-Nov-21
  Time: 12:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Limit Management Application<</title>
</head>
<body>
<center>
    <h1>Limit Management</h1>
    <h2>
        <a href="limit?action=limit">List All Limits</a>
    </h2>
    <h2>
        <a href="limit?action=findById">Find Limit By ID</a>
    </h2>
</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>Add New Limit</h2>
            </caption>
            <tr>
                <th>Amount:</th>
                <td>
                    <input type="text" name="amount" id="amount" size="45"/>
                </td>
            </tr>
            <tr>
                <th>Account ID: </th>
                <td>
                    <input type="text" name="account_id" id="account_id" size="15"/>
                </td>
            </tr>
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
