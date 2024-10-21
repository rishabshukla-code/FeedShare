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
        	<a href="/logout.htm">
            <button>Logout</button>
            </a>
            
            <h2 style="color: white;">Hello, ${loggedInUser.uname}</h2>
            
            <div>
            <a href="/post.htm">
            <button>Post</button>
            </a>
            
            <a href="/login.htm">
            <button>Home</button>
            </a>
            </div>
        
        </header>
        <main>
            <h2>&nbsp;This is the Post that you will be editing:</h2>
            
            <div class="post">
            
           				<div class="image-container">
           					
           					<img src="${post.imageUrl}" alt="Example Image">
            				<div class="caption">
                				<p>${post.caption}</p>
                				<p>${post.timestamp}</p>
                			</div>
                			
           				</div>
        
    		</div>
    		
    		<form id ="post" action="/updatePost.htm" method="post" enctype="multipart/form-data">
	        <label for="file">Choose a file:</label><br>
	        <input type="file" id="file" name="file"><br><br>
	        <label for="caption">Caption:</label><br>
	        <input type="text" id="caption" name="caption" value="${post.caption}"><br><br>
	        <input type="hidden" name="id" value="${post.id}">
	        <input type="submit" value="Update">
    
        </main>
        
        <footer id="footer">
        	<div>
        		<p>&copy; 2024 Feed Share. All rights reserved.</p>
    		</div>
    	</footer>
        
    </body>
</html>
