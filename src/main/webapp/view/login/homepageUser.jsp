<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 16/11/2021
  Time: 23:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--lấy dữ liệu từ Account user--%>
<h1>${accountLogging.username}</h1>
<h1>${accountLogging.username}</h1>
<a href="/revenue?id=${accountLogging.id}">aaaaa</a>
<a href="/spending?id=${accountLogging.id}">bbbbbbbb</a>
<a href="/loans">loannnnnnnnnnn</a>
<h1><a href="/spending?id=${accountLogging.id}">Spending</a></h1>
<%--sửa thông tin user--%>
<h1><a href="/login?action=editAccountUser&id=${accountLogging.id}">edit</a></h1>
<%--logout--%>
<h1><a href="/login?action=logoutAccount">Logout</a></h1>

</body>
</html>