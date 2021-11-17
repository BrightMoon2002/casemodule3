<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: duynguyen
  Date: 16/11/2021
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<html>
<head>
    <title>Login</title>
</head>
<style>
    html {
        height: 100%;
    }
    body {
        margin:0;
        padding:0;
        font-family: sans-serif;
        background: linear-gradient(#141e30, #243b55);
    }

    .login-box {
        position: absolute;
        top: 50%;
        left: 50%;
        width: 400px;
        padding: 40px;
        transform: translate(-50%, -50%);
        background: rgba(0,0,0,.5);
        box-sizing: border-box;
        box-shadow: 0 15px 25px rgba(0,0,0,.6);
        border-radius: 10px;
    }

    .login-box h2 {
        margin: 0 0 30px;
        padding: 0;
        color: #fff;
        text-align: center;
    }

    .login-box .user-box {
        position: relative;
    }

    .login-box .user-box input {
        width: 100%;
        padding: 10px 0;
        font-size: 16px;
        color: #fff;
        margin-bottom: 30px;
        border: none;
        border-bottom: 1px solid #fff;
        outline: none;
        background: transparent;
    }
    .login-box .user-box label {
        position: absolute;
        top:0;
        left: 0;
        padding: 10px 0;
        font-size: 16px;
        color: #fff;
        pointer-events: none;
        transition: .5s;
    }

    .login-box .user-box input:focus ~ label,
    .login-box .user-box input:valid ~ label {
        top: -20px;
        left: 0;
        color: #03e9f4;
        font-size: 12px;
    }

    /*.login-box form a {*/
    /*    position: relative;*/
    /*    display: inline-block;*/
    /*    padding: 10px 20px;*/
    /*    color: #03e9f4;*/
    /*    font-size: 16px;*/
    /*    text-decoration: none;*/
    /*    text-transform: uppercase;*/
    /*    overflow: hidden;*/
    /*    transition: .5s;*/
    /*    margin-top: 40px;*/
    /*    letter-spacing: 4px*/
    /*}*/

    /*.login-box a:hover {*/
    /*    background: #03e9f4;*/
    /*    color: #fff;*/
    /*    border-radius: 5px;*/
    /*    box-shadow: 0 0 5px #03e9f4,*/
    /*    0 0 25px #03e9f4,*/
    /*    0 0 50px #03e9f4,*/
    /*    0 0 100px #03e9f4;*/
    /*}*/

    /*.login-box a span {*/
    /*    position: absolute;*/
    /*    display: block;*/
    /*}*/

    /*.login-box a span:nth-child(1) {*/
    /*    top: 0;*/
    /*    left: -100%;*/
    /*    width: 100%;*/
    /*    height: 2px;*/
    /*    background: linear-gradient(90deg, transparent, #03e9f4);*/
    /*    animation: btn-anim1 1s linear infinite;*/
    /*}*/

    /*@keyframes btn-anim1 {*/
    /*    0% {*/
    /*        left: -100%;*/
    /*    }*/
    /*    50%,100% {*/
    /*        left: 100%;*/
    /*    }*/
    /*}*/

    /*.login-box a span:nth-child(2) {*/
    /*    top: -100%;*/
    /*    right: 0;*/
    /*    width: 2px;*/
    /*    height: 100%;*/
    /*    background: linear-gradient(180deg, transparent, #03e9f4);*/
    /*    animation: btn-anim2 1s linear infinite;*/
    /*    animation-delay: .25s*/
    /*}*/

    /*@keyframes btn-anim2 {*/
    /*    0% {*/
    /*        top: -100%;*/
    /*    }*/
    /*    50%,100% {*/
    /*        top: 100%;*/
    /*    }*/
    /*}*/

    /*.login-box a span:nth-child(3) {*/
    /*    bottom: 0;*/
    /*    right: -100%;*/
    /*    width: 100%;*/
    /*    height: 2px;*/
    /*    background: linear-gradient(270deg, transparent, #03e9f4);*/
    /*    animation: btn-anim3 1s linear infinite;*/
    /*    animation-delay: .5s*/
    /*}*/

    /*@keyframes btn-anim3 {*/
    /*    0% {*/
    /*        right: -100%;*/
    /*    }*/
    /*    50%,100% {*/
    /*        right: 100%;*/
    /*    }*/
    /*}*/

    /*.login-box a span:nth-child(4) {*/
    /*    bottom: -100%;*/
    /*    left: 0;*/
    /*    width: 2px;*/
    /*    height: 100%;*/
    /*    background: linear-gradient(360deg, transparent, #03e9f4);*/
    /*    animation: btn-anim4 1s linear infinite;*/
    /*    animation-delay: .75s*/
    /*}*/

    /*@keyframes btn-anim4 {*/
    /*    0% {*/
    /*        bottom: -100%;*/
    /*    }*/
    /*    50%,100% {*/
    /*        bottom: 100%;*/
    /*    }*/
    /*}*/

</style>
<body>
<div class="login-box">
    <h2>Login</h2>
    <form method="post" action="/login?action=checkLogin">
        <div class="user-box">
            <input type="text" name="username" required="">
            <label>Username</label>
        </div>
        <div class="user-box">
            <input type="password" name="password" required="">
            <label>Password</label>
        </div>
        <input type="submit" value="Log In" class="btn btn-outline-info" >
    </form>
    <a href="/login?action=create"><input type="submit" value="Create New Account" class="btn btn-outline-info" ></a>
    <p style="color: red; text-align: center" >
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
