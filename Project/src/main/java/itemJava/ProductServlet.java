package itemJava;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Resource(name = "jdbc/web_item")
    private DataSource datasource;

    public ProductServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        pw.append("<html><head><title>Products</title>");
        pw.append("<link rel='stylesheet' type='text/css' href='static/productStyles.css'>"); // 🔹 ربط ملف CSS
        pw.append("</head><body>");

        try (Connection conn = datasource.getConnection()) {
            pw.append("<h2>Available Products</h2>");
            pw.append("<div class='container'>");
            fetchProducts(pw, conn);
            pw.append("</div>");
        } catch (SQLException e) {
            pw.append("<h2 style='color:red;'>Error: " + e.getMessage() + "</h2>");
            e.printStackTrace();
        }

        pw.append("</body></html>");
    }

    private void fetchProducts(PrintWriter pw, Connection conn) {
        String query = "SELECT ProductID, ProductName, Description, Price, ImageURL FROM Product";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int productId = rs.getInt("ProductID");
                pw.append("<div class='card'>");
                pw.append("<a href='ProductDetailsServlet?id=" + productId + "' style='text-decoration: none; color: inherit;'>");
                pw.append("<img src='").append(rs.getString("ImageURL")).append("' alt='Product Image'>");
                pw.append("<h3>").append(rs.getString("ProductName")).append("</h3>");
                pw.append("<p>").append(rs.getString("Description")).append("</p>");
                pw.append("<p class='price'>$").append(String.valueOf(rs.getDouble("Price"))).append("</p>");
                pw.append("</a>");
                pw.append("<a href='ProductDetailsServlet?id=" + productId + "' class='buy-button'>View Details</a>");
                pw.append("</div>");
            }

        } catch (SQLException e) {
            pw.append("<h2 style='color:red;'>Error Fetching Data: " + e.getMessage() + "</h2>");
            e.printStackTrace();
        }
    }


}
