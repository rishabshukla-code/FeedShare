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
            
            
            <a href="/adminLogin.htm">
            <button>Home</button>
            </a>
            
            <a href="/manageUser.htm">
            <button>Manage Account's</button>
            </a>
            </div>
        
        </header>
        <main>
            <h1>&nbsp;Welcome to your Profile!</h1>
            
            <div class="post">
            
            		<c:forEach items="${requestScope.posts}" var="post">
            		
           				<div class="image-container">
           					
           					<img src="${post.imageUrl}" alt="Example Image">
            				<div class="caption">
                				<p>${post.caption}</p>
                				<p>${post.timestamp}</p>
                				
	                				<div class="button-container">
	                					<a href="/editPost.htm?id=${post.id}&imageUrl=${post.imageUrl}&caption=${post.caption}">
					                    	<button>Edit</button>
					                    <a/>
					                    <a href="#" onclick="confirmDelete('${post.id}')">
					                    	<button>Delete</button>
					                    </a>
	                				</div>
                			</div>
                			
           				</div>
           				
           				
           					
        			</c:forEach>    		
        
    		</div>
    		
		    		<!-- Page number buttons -->
		    <div class="pagination">
		        <c:forEach begin="1" end="${requestScope.numberOfPages}" var="pageNumber">
		            <button class="pgNum-button" value="${pageNumber}">${pageNumber}</button>
		        </c:forEach>
		    </div>
    
        </main>
        
        <footer id="footer">
        	<div>
        		<p>&copy; 2024 Feed Share. All rights reserved.</p>
    		</div>
    	</footer>
        
        <script src="/js/deleteScript.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="/js/ajax.js"></script>
        
        
    </body>
</html>
