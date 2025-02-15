<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- ✅ إضافة مكتبة JSTL -->
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Cat Products: Quality tools and supplies for your cat's care and fun.">
    <title>MewFect - Cat Products</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/styles.css">
</head>

<body>
    <div class="main">
        <header class="navbar">
            <h1 class="logo">MewFect</h1>
            <div class="menu">
                <ul>
                    <li><a href="SoonPages.jsp">HOME</a></li>
                    <li><a href="SoonPages.jsp">ABOUT</a></li>
                    <li><a href="SoonPages.jsp">FACEBOOK</a></li>
                    <li><a href="SoonPages.jsp">WHATSAPP</a></li>
                    <li><a href="SoonPages.jsp">INSTAGRAM</a></li>
                </ul>
            </div>
            
            <div class="search">
                <input class="srch" type="search" placeholder="Type to search">
                <button class="btn"><a href="SoonPages.jsp">Search</a></button>
            </div>

            <div>
                <button class="btlo"><a href="Login.jsp">Login</a></button>
            </div>

            <div>
                <button class="btsg"><a href="Register.jsp">Sign Up</a></button>
            </div>
        </header>
       
        <div class="content">
            <h1>Cat Tools & <br>Supplies<br>Shop</h1>
            <p class="par">
                Find everything you need for your cat's care and fun. From grooming tools to accessories, we have
                top-quality products that your cat will love. <br> Whether you're looking for scratching posts or toys,
                our selection is perfect for every cat.
            </p>
           <form action="/ProjectJSP/ProductServlet" method="get">
    <button class="cn">SHOP NOW</button>
    
</form>

        </div>
       
    </div>
</body>
</html>
