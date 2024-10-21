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
            
            </div>
        
        </header>
        <main>
            <h1>&nbsp;Welcome to Admin Profile!!</h1>
            
            <div class="post">
            
            		<c:forEach items="${requestScope.users}" var="user">
            		
           				<div class="image-container">
           					
           					
            				<div class="caption">
                				
	                				<div class="button-container">
	                					<a href="/editUser.htm?id=${user.userid}&uname=${user.uname}">
					                    	<button>Edit ${user.uname}</button>
					                    <a/>
	                				</div>
                			</div>
                			
           				</div>
           				
           				
           					
        			</c:forEach>    		
        
    		</div>
    
        </main>
        
        <footer id="footer">
        	<div>
        		<p>&copy; 2024 Feed Share. All rights reserved.</p>
    		</div>
    	</footer>
        
        
        
        
    </body>
</html>
