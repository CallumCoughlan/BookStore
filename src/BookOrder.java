import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookOrder {
    private int orderID;
    private String shippingAddress;
    private String paymentInfo;
    private int trackingNumber;

    public BookOrder (String paymentInfo, String shippingAddress, Connection conn) {
        this.paymentInfo = paymentInfo;
        this.shippingAddress = shippingAddress;
        this.orderID = getRows(conn);
        this.trackingNumber = getRows(conn);
    }

    public void makeOrder(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO BookOrder VALUES('" + this.orderID + "','" + this.shippingAddress + "','" + this.paymentInfo + "','" + getRows(conn) + "')");
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
