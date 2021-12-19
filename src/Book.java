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

    /**This is the constructor for book with parameters
     * @param authorID The id of the author of the book
     * @param name The name of the book
     * @param numberOfPages The number of pages that the book has
     * @param stock The number of this book that the book store has
     * @param price The price of the book in the store
     * @param isbn The isbn of the book
     * @param genre The book's genre
     * @param threshold The amount of books left that it automatically restocks
     * @param bookRoyalty The percent of the sales that the publisher of the book gets
     * @param email The email of the publisher of the book
     * @param startingStock The amount of books the systems will restock to when the threshold is hit
     */
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

    /**This method is used to add a book to the book table
     * @param conn The connection to the database
     */
    public void addBook(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Book VALUES('" + this.name + "','" + this.numberOfPages + "','" + this.stock + "','"+ this.threshold + "','" + this.isbn + "','" + this.price + "','" + this.genre + "','" + this.bookRoyalty + "','" + this.authorID + "','" + this.email + "')");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**This method is used to remove a book from the book table
     * @param conn The connection to the database
     */
    public void removeBook(Connection conn) {
        String sql = ("DELETE FROM Book WHERE isbn = ?");

        try (conn; PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, getIsbn());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**This method is used increase the stock of a book by an amount
     * @param conn The connection to the database
     * @param amount The amount to increase the stock by
     */
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

    /**This method is used to get the isbn of an instance of Book
     */
    public String getIsbn() {
        return isbn;
    }
}
