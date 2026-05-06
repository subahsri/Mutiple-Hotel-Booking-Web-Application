import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class BookingServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "password";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String roomType = request.getParameter("roomType");
        String roomOption = request.getParameter("roomOption");
        String total = request.getParameter("total");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            String query = "INSERT INTO bookings(first_name, last_name, email, phone, room_type, room_option, total_price) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setString(5, roomType);
            ps.setString(6, roomOption);
            ps.setString(7, total);

            int result = ps.executeUpdate();

            if (result > 0) {
                out.println("<h2>Booking Successful!</h2>");
            } else {
                out.println("<h2>Booking Failed!</h2>");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        }
    }
}