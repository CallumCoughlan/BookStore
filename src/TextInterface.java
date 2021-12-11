import java.sql.*;
import java.util.Scanner;

public class TextInterface {

    public TextInterface() {

    }
    public void userOption() {
        System.out.println("To login as Customer type \"Customer\", To login as Employee type \"Employee\"");
    }

    public void customerLogin() {
        System.out.println("Would you like to:" +
                "\n\"Login\": Allows you to shop" +
                "\n\"Register\": Lets you register so you can shop");
    }

    public void invalidLogin() {
        System.out.println("Would you like to you login detail were invalid");
    }

    public void customerMenu() {
        System.out.println("These are the actions you can preform:" +
                "\n\"DisplayBooks\": Display all books" +
                "\n\"SearchBooks\": Display books that match search" +
                "\n\"AddBook\": Add book to cart" +
                "\n\"RemoveBook\": Remove book from cart" +
                "\n\"DisplayCart\": Shows all items in cart" +
                "\n\"Checkout\": Creates order" +
                "\n\"Exit\": Ends Program");
    }

    public void employeeMenu() {
        System.out.println("These are the actions you can preform:" +
                "\n\"AddBook\": Add book to catalog" +
                "\n\"RemoveBook\": Remove book from catalog" +
                "\n\"AddPublisher\": Add publisher to database" +
                "\n\"RemovePublisher\": Remove publisher from database" +
                "\n\"AddAuthor\": Add author to database" +
                "\n\"RemoveAuthor\": Remove author from database" +
                "\n\"CriticalStock\": Shows books where the warehouse has less than 5 copies of them" +
                "\n\"Restock\": Restocks a book by a specified amount" +
                "\n\"Exit\": Ends Program");
    }

    public void invalidCommand() {
        System.out.println("The entered command is not valid");
    }

    public void searchBookText() {
        System.out.println("Type how you would like to search for books:" +
                "\n\"BookName\": Display all books with the given name" +
                "\n\"AuthorName\": Display all books by given author" +
                "\n\"ISBN\": Display the book" +
                "\n\"Genre\": Remove book from cart" +
                "\n\"Exit\": Ends Program");
    }

    public void addBookText(Connection conn, Scanner sc) {
        System.out.println("Enter the book's name");
        String bookName = sc.next();
        System.out.println("Enter the name of the Author");
        String authorName = sc.next();
        System.out.println("Enter the number of pages");
        int numberOfPages = sc.nextInt();
        System.out.println("Enter the initial amount of stock");
        int stock = sc.nextInt();
        System.out.println("Enter the price(Integer) of the book");
        int price = sc.nextInt();
        System.out.println("Enter the ISBN of the book");
        String isbn = sc.next();
        System.out.println("Enter the Genre of the book");
        String genre = sc.next();
        System.out.println("Enter the number of remaining books to restock at");
        int threshold = sc.nextInt();
        System.out.println("Enter the royalty the publisher gets");
        int bookRoyalty = sc.nextInt();

        Book newBook = new Book(authorName, bookName, numberOfPages, stock, price, isbn, genre, threshold, bookRoyalty);
        newBook.addBook(conn);

        System.out.println(bookName + " has been added to store!");
    }

    public void removeBookText(Connection conn, Scanner sc) {
        System.out.println("Enter the ISBN of the book");
        Book oldBook = new Book("", "", 0, 0, 0, sc.next(), "", 0, 0);
        oldBook.removeBook(conn);
        System.out.println("The book has been removed");
    }

    public void addPublisherText(Connection conn, Scanner sc) {
        System.out.println("Enter the publisher's name");
        String publisherName = sc.next();
        System.out.println("Enter the address of the publisher");
        String publisherAddress = sc.next();
        System.out.println("Enter the phone number of the publisher");
        String phoneNumber = sc.next();
        System.out.println("Enter the bank account number of the publisher");
        String bankAccount = sc.next();
        System.out.println("Enter email of the publisher");
        String email = sc.next();

        Publisher newPublisher = new Publisher(publisherName, publisherAddress, phoneNumber, bankAccount, email);
        newPublisher.addPublisher(conn);
    }

    public void removePublisherText(Connection conn, Scanner sc) {
        System.out.println("Enter the email of the publisher");
        Publisher oldPublisher = new Publisher("", "", "", "", sc.next());
        oldPublisher.removePublisher(conn);
        System.out.println("The book has been removed");
    }

    public void addAuthorText(Connection conn, Scanner sc) {
        System.out.println("Enter the author's name");
        String authorName = sc.next();
        System.out.println("Enter the author's ID");
        String authorID = sc.next();
        Author newAuthor = new Author(authorName, authorID);
        newAuthor.addAuthor(conn);
    }

    public void removeAuthorText(Connection conn, Scanner sc) {
        System.out.println("Enter the author's ID");
        String authorID = sc.next();
        Author oldAuthor = new Author("", authorID);
        oldAuthor.removeAuthor(conn);
    }

    public void displayBooksTable(Connection conn) {
        String sql = ("SELECT * FROM Book");
        buildTable(sql, conn);
    }

    public void displayCriticalStock(Connection conn) {
        String sql = ("SELECT name AND stock FROM Book WHERE stock <= 5");
        buildTable(sql, conn);
    }

    public void restockText(Connection conn, Scanner sc) {
        System.out.println("Enter book ISBN");
        String isbn = sc.next();
        System.out.println("Enter amount to buy");
        int amount = sc.nextInt();
        Book oldBook = new Book("", "", 0, 0, 0, isbn, "", 0, 0);
        oldBook.restock(conn, amount);
    }

    public void displayBooksByAuthorTable(Connection conn, String author) {
        String sql = ("SELECT * FROM Book WHERE author = ?");
        try (conn; PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, author);
            String newSql = pstmt.toString();
            buildTable(newSql, conn);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayBooksByISBNTable(Connection conn, String isbn) {
        String sql = ("SELECT * FROM Book WHERE isbn = ?");
        try (conn; PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, isbn);
            String newSql = pstmt.toString();
            buildTable(newSql, conn);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayBooksByGenreTable(Connection conn, String genre) {
        String sql = ("SELECT * FROM Book WHERE genre = ?");
        try (conn; PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, genre);
            String newSql = pstmt.toString();
            buildTable(newSql, conn);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayCart(Connection conn) {
        String sql = ("SELECT Book.name, Book.author, Book.isbn, Book.stock, Book.price, Book.genre FROM Book INNER JOIN Cart ON Book.isbn = Cart.isbn");
        try (conn; PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String newSql = pstmt.toString();
            buildTable(newSql, conn);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAllOrders(Connection conn) {
        String sql = ("SELECT * FROM BookOrder");
        buildTable(sql, conn);
    }

    public void getOrder(int number) {

    }

    public void buildTable(String sql, Connection conn) {
        try {
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(sql);
            ResultSetMetaData metaData = result.getMetaData();

            int columnsNumber = metaData.getColumnCount();

            while (result.next()) {
                for(int i = 1 ; i <= columnsNumber; i++){
                    System.out.print(result.getString(i) + " ");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
