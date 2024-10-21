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
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/feed.css" />">
        <title>Feed Share</title>
    </head>
    <body>
        <header id="header">
            
            <h2 style="color: white;">User Already Exists.</h2>
            
        </header>
        <main>
        
            <p>Looks like User already exists, try again with a different username? <a href="adminLogin.htm">Try again</a></p>
    
        </main>
        
        <footer id="footer">
        	<div>
        		<p>&copy; 2024 Feed Share. All rights reserved.</p>
    		</div>
    	</footer>
        
    </body>
</html>
