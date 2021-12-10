import java.util.ArrayList;

public class Cart {
    private ArrayList<Book> books;
    private Customer customer;
    private int cost;

    public Cart(Customer customer) {
        this.customer = customer;
    }

    public void addToCart(Book book) {
        this.books.add(book);
    }

    public void removeFromCart(Book book) {
        this.books.remove(book);
    }

    public void addBook(Book book) {
        this.books.add(book);
        this.cost += book.getPrice();
    }

    public void removeBook(Book book) {
        this.books.remove(book);
        this.cost -= book.getPrice();
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
