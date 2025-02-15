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

@WebServlet("/ProductDetailsServlet")
public class ProductDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Resource(name = "jdbc/web_item")
    private DataSource datasource;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        String productId = request.getParameter("id");
        if (productId == null) {
            pw.append("<h2 style='color:red;'>Invalid Product ID</h2>");
            return;
        }

        try (Connection conn = datasource.getConnection()) {
            String query = "SELECT * FROM Product WHERE ProductID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, Integer.parseInt(productId));
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        pw.append("<html><head><title>Product Details</title>");
                        pw.append("<link rel='stylesheet' type='text/css' href='static/productStyles.css'>");
                        pw.append("</head><body>");
                        pw.append("<div class='details-container'>");
                        pw.append("<img src='").append(rs.getString("ImageURL")).append("' alt='Product Image' class='details-img'>");
                        pw.append("<h2>").append(rs.getString("ProductName")).append("</h2>");
                        pw.append("<p>").append(rs.getString("Description")).append("</p>");
                        pw.append("<p class='price'>$").append(String.valueOf(rs.getDouble("Price"))).append("</p>");
                        pw.append("<p><strong>Brand:</strong> ").append(rs.getString("Brand")).append("</p>");
                        pw.append("<p><strong>Category:</strong> ").append(rs.getString("category")).append("</p>");
                        pw.append("<p><strong>Stock:</strong> ").append(String.valueOf(rs.getInt("StockQuantity"))).append("</p>");
                        pw.append("<p class='rating'>⭐").append(String.valueOf(rs.getDouble("rating"))).append("</p>");
                        pw.append("<a href='#' class='buy-button'>Buy Now</a>");
                        pw.append("</div>");
                        pw.append("</body></html>");
                    } else {
                        pw.append("<h2 style='color:red;'>Product Not Found</h2>");
                    }
                }
            }
        } catch (SQLException e) {
            pw.append("<h2 style='color:red;'>Error: " + e.getMessage() + "</h2>");
            e.printStackTrace();
        }
    }
}
