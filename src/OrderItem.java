import java.sql.Connection;

public class OrderItem {
    private int orderID;
    private String isbn;
    private int amount;

    public OrderItem(String isbn, int orderID) {
        this.orderID = orderID;
        this.isbn = isbn;
    }

    public void CreateItem(Connection conn) {

    }
}
