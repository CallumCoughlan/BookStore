import java.sql.*;

public class Book {
    private String author;
    private String name;
    private String isbn;
    private int numberOfPages;
    private int stock;
    private int price;
    private String genre;

    public Book(String author, String name, int numberOfPages, int stock, int price, String isbn, String genre) {
        this.author = author;
        this.name = name;
        this.isbn = isbn;
        this.numberOfPages = numberOfPages;
        this.stock = stock;
        this.price = price;
        this.genre = genre;
    }

    public void addBook(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("insert into Book values('" + this.name + "','" + this.numberOfPages + "','" + this.stock + "','" + this.price + "')");
    }

    public void removeBook(Connection conn) {
        String sql = ("DELETE FROM Book WHERE isbn = ?");

        try (conn; PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, getIsbn());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void restock(Connection conn, int amount) {
        String sql = ("UPDATE Book SET stock = ? WHERE isbn = ?");

        try (conn; PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, amount);
            pstmt.setString(2, getIsbn());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPrice() {
        return price;
    }

    public String getIsbn() {
        return isbn;
    }
}
