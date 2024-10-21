<%-- 
    Document   : signUp
    Created on : 23-Mar-2024, 2:51:37 pm
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Sign Up</title>
        <style>
            body {
                font-family: Arial, sans-serif;
            }
            
            #header {
			    position: -webkit-sticky; /* For Safari */
			    position: sticky;
			    top: 0;
			    background-color: #ff0000;
			    padding: 10px 20px;
			    display: flex;
			    justify-content: space-between;
			    border-radius: 5px;
			    align-items: center;
			    z-index: 1000; /* Ensure the header stays on top of other content */
			}
			
            
            form {
                width: 300px;
                margin: 0 auto;
                padding: 20px;
                border: 1px solid #ccc;
                box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
            }
            label {
                display: block;
                margin-top: 5px;
            }
            input[type="text"],
            input[type="email"],
            input[type="password"] {
                width: 100%;
                padding: 5px;
                margin-top: 5px;
                border: 1px solid #ccc;
                box-sizing: border-box;
            }
            input[type="submit"] {
                width: 100%;
                padding: 10px;
                background-color: #4CAF50;
                color: white;
                border: none;
                margin-top: 20px;
                cursor: pointer;
            }
            input[type="submit"]:hover {
                background-color: #45a049;
            }
            button.delete {
	            display: block;
			    margin: 0 auto; /* see this will centers the button horizontally */
			    padding: 10px;
			    background-color: #f44336; /* Red color for delete button, may i change the color to green*/
			    color: white;
			    border: none;
			    cursor: pointer;
			    border-radius: 5px;
			}
			button.delete:hover {
    			background-color: #d32f2f; /* Darker red color on hover, i will change this to some other color */
			}
            
        </style>
    </head>
    <body>
        <header id="header">

            <h2 style="color: white;">Update ${user.uname} user's Account Detail's:</h2>
            
            <a href="/adminLogin.htm">
            <button>Home</button>
            </a>
        
        </header>
        <br>
        
        <form:form method="post" action="/updateUserAdmin.htm?usn=${user.uname}" modelAttribute="user">

            <label for="fname">Enter Your First Name:</label>
            <form:input path="fname" id="fname" type="text" /><form:errors path="fname" /><br><br>

            <label for="lname">Enter Your Last Name:</label>
            <form:input path="lname" id="lname" type="text" /><form:errors path="lname" /><br><br>

            <label for="uname">Enter Your Username:</label>
            <form:input path="uname" id="uname" type="text" /><form:errors path="uname" /><br><br>
            
            <label for="email">Enter Your email:</label>
            <form:input path="email" id="email" type="text" /><form:errors path="email" /><br><br>

            <label for="pswd">Enter Your Password:</label>
            <form:input path="pswd" id="pswd" type="password" /><form:errors path="pswd" /><br><br>

            <input type="submit" value="Update"/>

        </form:form><br><br>
        
        
        <button class="delete" onclick="confirmUserDelete('${user.uname}')">Delete Account</button>

    </body>
    <script src="/js/adminDelete.js"></script>
</html>
