package itemJava;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.SQLException;
import javax.annotation.Resource;
//import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Resource(name = "jdbc/web_item")
    private DataSource datasource;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username != null && password != null) {
            try (Connection connection = datasource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {

                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        HttpSession session = request.getSession();
                        session.setAttribute("username", username);

                        Cookie userCookie = new Cookie("username", username);
                        userCookie.setMaxAge(60 * 60);
                        response.addCookie(userCookie);

                        response.sendRedirect(request.getContextPath() + "/item/home.jsp");
                    } else {
                        pw.println("<h1>Invalid Username or Password</h1>");
                    }
                }
            } catch (Exception e) {
                pw.println("<h1>Error: " + e.getMessage() + "</h1>");
                e.printStackTrace();
            }
        }
    }
}
