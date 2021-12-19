import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderItem {
    private int orderID;
    private String isbn;
    private int amount;

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public OrderItem(String isbn, int orderID) {
        this.orderID = orderID;
        this.isbn = isbn;
    }

    public void CreateItem(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO OrderItem VALUES('" + this.isbn + "','" + this.orderID + "','" + this.amount +  "')");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
