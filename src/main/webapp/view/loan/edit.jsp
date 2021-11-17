<%--
  Created by IntelliJ IDEA.
  User: duynguyen
  Date: 14/11/2021
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<fieldset style="width: 25%">
    <legend>Edit Product</legend>
    <form method="post">
        <table>
            <tr><td> Name: </td>
                <td>
                    <input type="text" name="name" value="${loanEdit.getAccount().getUsername()}">
                </td>
            </tr>
            <tr><td>Start of Loan:  </td>
                <td>
                    <input type="date" name="startOfLoan" value="${loanEdit.getStartOfLoan()}">
                    <fmt:formatNumber value = "${loanDelete.getAmount()}"/>
                </td>
            </tr>
            <tr> <td>End of Loan: </td>
                <td>
                    <input type="date" name="endOfLoan" value="${loanEdit.getEndOfLoan()}">
                </td>
            </tr>
            <tr> <td>Amount:  </td>
                <td>
                    <input name="amount" value="${loanEdit.getAmount()}" type = "currency">
                </td>
            </tr>
            </tr>
            <tr> <td>status:  </td>
                <td>
                    <input type="text" name="status" value="${loanEdit.getStatus().getName()}">
                </td>
            </tr>
            <tr> <td>Interest:  </td>
                <td>
                    <select name="idInterest">
                        <c:forEach items="${interest}" var="i">
                            <option value="${i.getId()}">${i.getName()}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </table>
        <button type="submit">Submit</button>
        <button type="reset">Clear</button>
    </form>
    <a href="/loans">Home page</a>
</fieldset>
</body>
</html>