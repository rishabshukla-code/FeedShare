<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

            
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
        