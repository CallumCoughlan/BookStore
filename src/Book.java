import java.sql.*;

public class Book {
    private int authorID;
    private String name;
    private String isbn;
    private int numberOfPages;
    private int threshold;
    private int stock;
    private int price;
    private int bookRoyalty;
    private String genre;
    private String email;
    private int startingStock;
    private int cartID;

    public Book(int authorID, String name, int numberOfPages, int stock, int price, String isbn, String genre, int threshold, int bookRoyalty, String email, int startingStock) {
        this.authorID = authorID;
        this.name = name;
        this.isbn = isbn;
        this.numberOfPages = numberOfPages;
        this.stock = stock;
        this.price = price;
        this.genre = genre;
        this.threshold = threshold;
        this.bookRoyalty = bookRoyalty;
        this.email = email;
        this.startingStock = startingStock;
    }

    public void addBook(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Book VALUES('" + this.name + "','" + this.numberOfPages + "','" + this.stock + "','"+ this.threshold + "','" + this.isbn + "','" + this.price + "','" + this.genre + "','" + this.bookRoyalty + "','" + this.authorID + "','" + this.email + "')");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeBook(Connection conn) {
        String sql = ("DELETE FROM Book WHERE isbn = ?");

        try (conn; PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, getIsbn());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void restock(Connection conn, int amount) {
        String sql = ("UPDATE Book SET stock = ? WHERE isbn = ?");

        try (conn; PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, amount);
            pstmt.setString(2, getIsbn());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getIsbn() {
        return isbn;
    }
}
