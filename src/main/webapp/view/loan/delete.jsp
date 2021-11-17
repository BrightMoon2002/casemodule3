<%--
  Created by IntelliJ IDEA.
  User: duynguyen
  Date: 14/11/2021
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Delete Books</title>
</head>
<body>
<fieldset style="width: 25%;">
    <lengend>loan DELETE</lengend>
    <p>DO YOU WANT TO DELETE THIS LOAN</p>
    <form method="post">
        <p>Name: ${loanDelete.getAccount().getName()}</p>
        <p>Amount: <fmt:formatNumber value = "${loanDelete.getAmount()}" type = "currency"/></p>

        <p>startOfLoan: ${loanDelete.getStartOfLoan()}</p>
        <p>EndOfLoan: ${loanDelete.getEndOfLoan()}</p>
        <p>Interest: ${loanDelete.getInterest().getName()}</p>
        <p>status: ${loanDelete.getStatus().getName()}</p>
        <button type="submit">Submit</button>
        <button type="button">
            <a href="/loans">Back</a>
        </button>

    </form>
</fieldset>

</body>
</html>
