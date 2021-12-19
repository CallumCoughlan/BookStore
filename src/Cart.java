import java.sql.*;

public class Cart {
    public int cartID;

    /**The constructor for Cart with parameters
     * @param conn The connection to the database
     * @param username The username of the customer who is making this cart
     */
    public Cart(String username, Connection conn) {
        try {
            Statement st = conn.createStatement();
            PreparedStatement getCartID = conn.prepareStatement("SELECT cartID FROM Customer WHERE username = ?");
            getCartID.setString(1, username);
            ResultSet result = st.executeQuery(getCartID.toString());
            result.next();
            this.cartID = result.getInt(1);
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**This method creates orderitems and bookorders from the items in the cart then removes them from the cart.
     * it also removes from the books stock and auto restocks if threshold of a book is hit.
     * @param conn The connection to the database
     * @param paymentInfo The payment info being used to pay for the books in the cart
     * @param shippingAddress The address the books are being sent to
     */
    public void makePurchase(Connection conn, String paymentInfo, String shippingAddress) {
        try (conn) {
            BookOrder bookOrder = new BookOrder(paymentInfo, shippingAddress, conn);
            Statement st = conn.createStatement();
            PreparedStatement getAllItems = conn.prepareStatement("SELECT * FROM Cart WHERE cartID = ?");
            getAllItems.setInt(1, this.cartID);
            ResultSet result = st.executeQuery(getAllItems.toString());

            while (result.next()) {
                OrderItem orderItem = new OrderItem(result.getString(2), bookOrder.getOrderID());
                orderItem.setAmount(result.getInt(3));
                orderItem.CreateItem(conn);
                PreparedStatement decreaseStock = conn.prepareStatement("UPDATE Book SET stock = stock - ? WHERE isbn = ?");
                decreaseStock.setInt(1, result.getInt(3));
                decreaseStock.setString(2, result.getString(2));
            }

            PreparedStatement deleteOrderedItems = conn.prepareStatement("DELETE FROM Cart WHERE cartID = ?");
            deleteOrderedItems.setInt(1, this.cartID);
            deleteOrderedItems.executeUpdate();

            //make order
            bookOrder.makeOrder(conn);

            //see if books have hit the threshold and restock
            st.executeUpdate("UPDATE Book SET stock = startingstock WHERE stock <= threshold");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**This method adds a book to the cart table
     * @param conn The connection to the database
     * @param bookISBN The isbn of the book being added
     * @param amount The amount of that book being added
     */
    public void addToCart(String bookISBN, Connection conn, int amount) {
        try {
            Statement st = conn.createStatement();
            PreparedStatement pstmt = conn.prepareStatement("SELECT DISTINCT isbn FROM Cart WHERE cartID = ? AND isbn = ?");
            pstmt.setInt(1, this.cartID);
            pstmt.setString(2, bookISBN);
            ResultSet result = st.executeQuery(pstmt.toString());

            if (result.next()) {
                PreparedStatement addToExistingCart = conn.prepareStatement("UPDATE Cart SET amount = amount + ? WHERE isbn = ? AND cartID = ?");
                addToExistingCart.setInt(1, amount);
                addToExistingCart.setString(2, bookISBN);
                addToExistingCart.setInt(1, this.cartID);
                addToExistingCart.executeUpdate();
            } else {
                st.executeUpdate("INSERT INTO Cart values('" + this.cartID + "','" + bookISBN + "','" + amount + "')");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**This method removes a book from the cart table
     * @param conn The connection to the database
     * @param bookISBN The isbn of the book removed added
     */
    public void removeFromCart(String bookISBN, Connection conn) {
        String sql = ("DELETE FROM Cart WHERE isbn = ?");

        try (conn; PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, bookISBN);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
