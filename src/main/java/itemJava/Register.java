package itemJava;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet("/Register")
public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Resource(name = "jdbc/web_item")
    private DataSource datasource;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String address = request.getParameter("address");

        if (username == null || email == null || password == null || confirmPassword == null || address == null ||
            username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || address.isEmpty()) {
            out.println("<h1>All fields are required!</h1>");
            return;
        }

        if (!password.equals(confirmPassword)) {
            out.println("<h1>Passwords do not match!</h1>");
            return;
        }

        try (Connection connection = datasource.getConnection()) {
            String checkUserQuery = "SELECT id FROM users WHERE username = ? OR email = ?";
            try (PreparedStatement checkUserStmt = connection.prepareStatement(checkUserQuery)) {
                checkUserStmt.setString(1, username);
                checkUserStmt.setString(2, email);
                
                try (ResultSet resultSet = checkUserStmt.executeQuery()) {
                    if (resultSet.next()) {
                        out.println("<h1>Username or email already exists. Please choose another.</h1>");
                        return;
                    }
                }
            }

            String insertQuery = "INSERT INTO users (username, email, password, address) VALUES (?, ?, ?, ?)";
            try (PreparedStatement insertUserStmt = connection.prepareStatement(insertQuery)) {
                insertUserStmt.setString(1, username);
                insertUserStmt.setString(2, email);
                insertUserStmt.setString(3, password);
                insertUserStmt.setString(4, address);

                int rowsInserted = insertUserStmt.executeUpdate();
                if (rowsInserted > 0) {
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    session.setAttribute("email", email);
                    session.setAttribute("address", address);

                    Cookie userCookie = new Cookie("username", username);
                    userCookie.setMaxAge(60 * 60);
                    response.addCookie(userCookie);

                    response.sendRedirect(request.getContextPath() + "/item/home.jsp");
                } else {
                    out.println("<h1>Registration failed. Please try again.</h1>");
                }
            }
        } catch (SQLException e) {
            out.println("<h1>Error: " + e.getMessage() + "</h1>");
            e.printStackTrace();
        }
    }
}
