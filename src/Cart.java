import java.sql.*;
import java.util.ArrayList;

public class Cart {
    public int cartID;
    private String isbn;

    public Cart() {

    }

    public void addToCart(String bookISBN, Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Cart values('" + bookISBN + "','" + getRows(conn) + "')");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeFromCart(String bookISBN, Connection conn) {
        String sql = ("DELETE FROM Cart WHERE isbn = ?");

        try (conn; PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, bookISBN);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void clearCart(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM Cart");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getRows(Connection conn) {
        try {
            String sql = ("SELECT * FROM Cart");
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(sql);
            int rows = 1;

            while (!result.next()) {
                rows += 1;
            }
            return rows;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 1;
    }

    public int generateCartID(Connection conn) {
        String sql = ("SELECT * FROM Cart");
        return 0;
    }
}
