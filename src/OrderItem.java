import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderItem {
    private int orderID;
    private String isbn;
    private int amount;

    /**This method returns the amount of books this instance of item represents
     * @param amount The amount of a certain book in the order
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**The constructor for OrderItem with parameters
     * @param isbn The isbn of the book being added to an order
     * @param orderID The order this item is associated with
     */
    public OrderItem(String isbn, int orderID) {
        this.orderID = orderID;
        this.isbn = isbn;
    }

    /**This method adds an item to the orderitems table
     * @param conn The connection to the database
     */
    public void CreateItem(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO OrderItem VALUES('" + this.isbn + "','" + this.orderID + "','" + this.amount +  "')");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
