import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookOrder {
    private String address;
    private String paymentInfo;

    public BookOrder (String address, String paymentInfo) {
        this.address = address;
        this.paymentInfo = paymentInfo;
    }

    public void makeOrder(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Book VALUES('" + this.address + "','" + this.paymentInfo + "','" + getRows(conn) + "')");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getRows(Connection conn) {
        try {
            String sql = ("SELECT * FROM BookOrder");
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
}
