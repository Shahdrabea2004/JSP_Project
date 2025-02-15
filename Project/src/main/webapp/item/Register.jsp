<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/registerStyles.css">
    <script>
        function validatePassword() {
            var password = document.getElementById("password").value;
            var confirmPassword = document.getElementById("confirmPassword").value;
            if (password !== confirmPassword) {
                document.getElementById("error-message").innerHTML = "Passwords do not match!";
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <div class="container">
        <h2>🔥Register🔥</h2>
        <form action="<%= request.getContextPath() %>/Register" method="post" onsubmit="return validatePassword();">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <label for="confirmPassword">Confirm Password:</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required>

            <label for="address">Address:</label>
            <input type="text" id="address" name="address" required>

            <p id="error-message" style="color: red;"></p>
            <input type="submit" value="Register" class="btn">
        </form>

        <p>Already have an account? <a href="Login.jsp">Login here</a></p>
    </div>
</body>
</html>
