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
        <title>Upload</title>
    </head>
    <body>
        <header id="header">
        	<a href="/logout.htm">
            <button>Logout</button>
            </a>
            
            <h2 style="color: white;">Upload File</h2>
            
            <a href="/login.htm">
            <button>Home</button>
            </a>
        
        </header>
        <main>
            <form action="/uploadFile.htm" method="post" enctype="multipart/form-data">
	        <label for="file">Choose a file:</label><br>
	        <input type="file" id="file" name="file"><br><br>
	        <label for="caption">Caption:</label><br>
	        <input type="text" id="caption" name="caption"><br><br>
	        <input type="submit" value="Upload">
    </form>
    
        </main>
        
        <footer id="footer">
        	<div>
        		<p>&copy; 2024 Feed Share. All rights reserved.</p>
    		</div>
    	</footer>
        
    </body>
</html>
