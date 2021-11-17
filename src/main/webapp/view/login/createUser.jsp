<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 17/11/2021
  Time: 8:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<html>
<head>
    <title>Create Account</title>
</head>
<style>
    @-webkit-keyframes my {
        0% { color: #0af822; }
        50% { color: #fff;  }
        100% { color: #0af822;  }
    }
    @-moz-keyframes my {
        0% { color: #0af822;  }
        50% { color: #fff;  }
        100% { color: #0af822;  }
    }
    @-o-keyframes my {
        0% { color: #0af822; }
        50% { color: #fff; }
        100% { color: #0af822;  }
    }
    @keyframes my {
        0% { color: #0af822;  }
        50% { color: #fff;  }
        100% { color: #0af822;  }
    }
    .test {
        background: #ffffff;
        font-size:24px;
        font-weight:bold;
        -webkit-animation: my 700ms infinite;
        -moz-animation: my 700ms infinite;
        -o-animation: my 700ms infinite;
        animation: my 700ms infinite;
    }
    div{
        margin-left: 400px;
        width: 700px;
    }
</style>
<body>
<center>
    <h1><a href="/login" style="border: none; color: #dc3545; text-decoration: none">LOGIN</a></h1>
</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5" class="table table-info table-hover table-striped">
            <tr>
                <td colspan="2" style="text-align: center"><h4>Create New Account</h4></td>
            </tr>
            <tr>
                <th>Username:</th>
                <p>
                    <c:if test="${message1!= null}">
                        <span>${message1}</span>
                    </c:if>
                </p>
                <td><input type="text" name="username" placeholder="Nhập tên đăng nhập" size="45"></td>
            </tr>
            <tr>
                <th>Password:</th>
                <td><input type="text" name="password" placeholder="Nhập mật khẩu" size="45"></td>
            </tr>
            <tr>
                <th>Name:</th>
                <td><input type="text" name="name" placeholder="Nhập tên" size="45"></td>
            </tr>
            <tr>
                <th>Date Of Birth:</th>
                <td><input type="date" name="dob" placeholder="Nhập năm sinh" size="45"></td>
            </tr>
            <tr>
                <th>Email:</th>
                <td><input type="text" name="email" placeholder="Nhập email" size="45"></td>
            </tr>
            <tr>
                <th>Address:</th>
                <td><input type="text" name="address" placeholder="Nhập địa chỉ" size="45"></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" class="btn btn-outline-info"/>
                </td>
            </tr>
        </table>
    </form>
    <p class="test">
        <c:if test="${message!= null}">
            <span>${message}</span>
        </c:if>
    </p>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

</html>
