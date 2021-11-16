<%--
  Created by IntelliJ IDEA.
  User: duynguyen
  Date: 16/11/2021
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>create</title>
</head>
<body>
<h2>Do you want to Loan</h2>
<a href="/loans">Home page</a>
<form method="post">
    <fieldset style="width: 25%">
        <legend>Loan</legend>
        <table>
            <tr>
                <td>Amount: </td>
                <td>
                    <input type="text" name="amount">
                </td>
            </tr>
            <tr>
                <td>Start of Loan: </td>
                <td>
                    <input type="date" name="startOfLoan">
                </td>
            </tr>
            <tr>
                <td>type: </td>
                <td>
                    <select name="idInterest">
                        <c:forEach items="${interestList}" var="i">
                            <option value="${i.getId()}">${i.getName()}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </table>
        <button type="submit">Submit</button>
        <button type="reset">Reset</button>
    </fieldset>


</body>
</html>