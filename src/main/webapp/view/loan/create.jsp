<%--
  Created by IntelliJ IDEA.
  User: duynguyen
  Date: 16/11/2021
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <td>Name: </td>
                <td>
                    <input type="text" name="name">
                </td>
            </tr>
            <tr>
                <td>Description: </td>
                <td>
                    <input type="text" name="description">
                </td>
            </tr>
            <tr>
                <td>Producer: </td>
                <td>
                    <input type="text" name="producer">
                </td>
            </tr>
            <tr>
                <td>Price: </td>
                <td>
                    <input type="text" name="price">
                </td>
            </tr>
            <tr>
                <td>Type: </td>
                <td>
                    <select name="idCategory">
                        <c:forEach items="${categoryList}" var="cate">
                            <option value="${cate.getId()}">${cate.getName()}</option>
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