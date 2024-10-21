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
            
            <a href="/profile.htm">
            <button>Account</button>
            </a>
            </div>
        
        </header>
        <main>
            <h1>&nbsp;Welcome to Feed Share!</h1>
            <p>&nbsp;Please be informed that the food is not guaranteed for all. It is first come first serve basis.</p>
            
            <div class="post">
            
            		<c:forEach items="${requestScope.posts}" var="post">
            		
           				<div class="image-container">
           					
           					<img src="${post.imageUrl}" alt="Example Image">
            				<div class="caption">
                				<p>${post.caption}</p>
                				<p>${post.timestamp}</p>
                			</div>
           				</div>
           					
        			</c:forEach>    		
        
    		</div>
    		
    		<!-- Page number buttons 
		    <div class="pagination">
		        <c:forEach begin="1" end="${requestScope.numberOfPages}" var="pageNumber">
		            <button class="pgNum-button" value="${pageNumber}">${pageNumber}</button>
		        </c:forEach>
		    </div> -->
    
        </main>
        
        <footer id="footer">
        	<div>
        		<p>&copy; 2024 Feed Share. All rights reserved.</p>
    		</div>
    	</footer>
    	
    <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="/js/ajax.js"></script> -->
        
    </body>
    
</html>
