package itemJava;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/home")
public class itemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Resource(name = "jdbc/web_item")  // تأكد أن اسم DataSource مطابق
    private DataSource datasource;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<items> itemList = new ArrayList<>();

        try (Connection conn = datasource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT item_id, item_name, item_desc, item_image, item_price FROM items")) {

            while (rs.next()) {
                items item = new items(
                    rs.getInt("item_id"),
                    rs.getString("item_name"),
                    rs.getString("item_desc"),
                    rs.getString("item_image"),
                    rs.getDouble("item_price")
                );
                itemList.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("items", itemList); // غيرت "home" إلى "items" ليكون الاسم منطقيًا
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}
