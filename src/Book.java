import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Book {
    private Author author;
    private String name;
    private int numberOfPages;
    private int stock;
    private int price;

    public Book(Author author, String name, int numberOfPages, int stock, int price) {
        this.author = author;
        this.name = name;
        this.numberOfPages = numberOfPages;
        this.stock = stock;
        this.price = price;
    }

    public void addBook(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("insert into Book values('" + this.name + "','" + this.numberOfPages + "','" + this.stock + "','" + this.price + "')");
    }

    public void removeBook(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("delete from Book where values('" + this.name + "','" + this.numberOfPages + "','" + this.stock + "','" + this.price + "')");
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
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

    public void setPrice(int price) {
        this.price = price;
    }
}
