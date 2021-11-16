<%--
  Created by IntelliJ IDEA.
  User: duynguyen
  Date: 14/11/2021
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Delete Books</title>
</head>
<body>
<fieldset style="width: 25%;">
    <lengend>PRODUCT DELETE</lengend>
    <p>DO YOU WANT TO DELETE THIS PRODUCT</p>
    <form method="post">
        <p>Name: ${bookDelete.getName()}</p>
        <p>Description: ${bookDelete.getDescription()}</p>
        <p>Producer: ${bookDelete.getProducer()}</p>
        <p>Category: ${bookDelete.getCategory().getName()}</p>
        <p>Price: ${bookDelete.getPrice()}</p>
        <button type="submit">Submit</button>
        <button type="button">
            <a href="/books">Back</a>
        </button>

    </form>
</fieldset>

</body>
</html>
