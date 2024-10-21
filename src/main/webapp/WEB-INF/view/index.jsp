<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css" />">
    <title>Login</title>

</head>
<body>
    <div class="container">
        <h2>Login</h2>
        <form id="loginuser" action="login.htm" method="post">

            <label for="uname">Enter Your Username:</label>
            <input id="uname" name="uname" type="text" value=""/><br><br>

            <label for="pswd">Enter Your Password:</label>
            <input id="pswd" name="pswd" type="password" value=""/><br><br>

            <input type="submit" value="Login"/>

        </form>
        <p>Don't have an account? <a href="signUp.htm">Sign up</a></p>
    </div>
</body>
</html>