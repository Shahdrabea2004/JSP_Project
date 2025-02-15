<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>🔥 Login Page 🔥</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/loginStyles.css">
</head>
<body>
    <div class="container">
        <div class="login-container">
            <h2>🔥 Welcome Back 🔥</h2>
            <form action="<%= request.getContextPath() %>/Login" method="post">
                <div class="input-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required>
                </div>
                
                <div class="input-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                
                <input type="submit" value="🔥 LET'S GO 🔥" class="btn">
            </form>
            
            <p class="signup-link">Don't have an account?  
                <a href="Register.jsp">Sign up here</a>

            </p>
        </div>
    </div>
</body>
</html>
