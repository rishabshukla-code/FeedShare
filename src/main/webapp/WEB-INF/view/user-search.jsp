<%-- 
    Document   : user-logged
    Created on : 04-Apr-2024, 2:54:15 pm
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>This is search Page!</h1>
        <c:if test="${not empty requestScope.user}">
            Input is below:<br/>
            Username is ${requestScope.user.uname}<br/>
            Password is ${requestScope.user.pswd}<br/>
            First Name is ${requestScope.user.fname}<br/>
            Last Name is ${requestScope.user.lname}<br/>
        </c:if>
    </body>
</html>
