import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookOrder {
    private int orderID;
    private String shippingAddress;
    private String paymentInfo;
    private int trackingNumber;

    /**This method is used to get the orderID of an instance of book order
     */
    public int getOrderID() {
        return orderID;
    }

    /**This is the constructor for BookOrder with parameters
     * @param conn The connection to the database
     * @param paymentInfo The payment info used for the order
     * @param shippingAddress The address the books in the order are being sent to
     */
    public BookOrder (String paymentInfo, String shippingAddress, Connection conn) {
        this.paymentInfo = paymentInfo;
        this.shippingAddress = shippingAddress;
        this.orderID = getRows(conn);
        this.trackingNumber = getRows(conn);
    }

    /**This method adds an order to the BookOrder table
     * @param conn The connection to the database
     */
    public void makeOrder(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO BookOrder VALUES('" + this.orderID + "','" + this.shippingAddress + "','" + this.paymentInfo + "','" + this.trackingNumber + "')");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**This method gets the number of rows in the BookOrder table
     * @param conn The connection to the database
     */
    public int getRows(Connection conn) {
        try {
            String sql = ("SELECT * FROM BookOrder");
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(sql);
            int rows = 1;

            while (result.next()) {
                rows += 1;
            }
            return rows;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 1;
    }
}
