import java.sql.*;
import java.util.Scanner;

public class TextInterface {

    /**The constructor of TextInterface
     */
    public TextInterface() {

    }

    /**This method prints the option to login as a customer or employee
     */
    public void userOption() {
        System.out.println("To login as Customer type \"Customer\", To login as Employee type \"Employee\"");
    }

    /**This method prints the options a customer has before logging in
     */
    public void customerLogin() {
        System.out.println("Would you like to:" +
                "\n\"Login\": Allows you to shop" +
                "\n\"Register\": Lets you register so you can shop");
    }

    /**This method prints a message if the user entered incorrect credentials
     */
    public void invalidLogin() {
        System.out.println("Would you like to you login detail were invalid");
    }

    /**This method prints the options a customer has upon logging in
     */
    public void customerMenu() {
        System.out.println("These are the actions you can preform:" +
                "\n\"DisplayBooks\": Display all books" +
                "\n\"SearchBooks\": Display books that match search" +
                "\n\"AddBook\": Add book to cart" +
                "\n\"RemoveBook\": Remove book from cart" +
                "\n\"DisplayCart\": Shows all items in cart" +
                "\n\"DisplayAllOrders\": Shows all Orders" +
                "\n\"Checkout\": Creates order" +
                "\n\"Exit\": Ends Program");
    }

    /**This method prints the login message for employees
     */
    public void employeeLogin() {
        System.out.println("PLease login:");
    }

    /**This method prints the options an employee has upon logging in
     */
    public void employeeMenu() {
        System.out.println("These are the actions you can preform:" +
                "\n\"AddBook\": Add book to catalog" +
                "\n\"RemoveBook\": Remove book from catalog" +
                "\n\"AddPublisher\": Add publisher to database" +
                "\n\"RemovePublisher\": Remove publisher from database" +
                "\n\"AddAuthor\": Add author to database" +
                "\n\"RemoveAuthor\": Remove author from database" +
                "\n\"CreateCollection\": Creates a book collection" +
                "\n\"AddToCollection\": adds a book to a book collection" +
                "\n\"CriticalStock\": Shows books where the warehouse has less than 5 copies of them" +
                "\n\"Restock\": Restocks a book by a specified amount" +
                "\n\"ViewSales\": Shows options as to how to view the total store sales" +
                "\n\"Exit\": Ends Program");
    }

    /**This method prints the options an employee can select to view sales by
     */
    public void viewSalesText() {
        System.out.println("Type how you would like to search for sales:" +
                "\n\"AllSales\": Display all money made from all sales" +
                "\n\"SalesPerGenre\": Display all money made from a single genre" +
                "\n\"SalesPerBook\": Display all money made by a single book" +
                "\n\"SalesPerAuthor\": Display all money made from books by a given author" +
                "\n\"SalesPerPublisher\": Display all money made from books by a given publisher" +
                "\n\"Exit\": Ends Program");
    }

    /**This method prints an error if the command entered is not one of the listed
     */
    public void invalidCommand() {
        System.out.println("The entered command is not valid");
    }

    /**This method prints the options a customer has to search for a book by
     */
    public void searchBookText() {
        System.out.println("Type how you would like to search for books:" +
                "\n\"BookName\": Display all books with the given name" +
                "\n\"AuthorName\": Display all books by given author" +
                "\n\"ISBN\": Display the book with this isbn" +
                "\n\"Genre\": Display all books in genre" +
                "\n\"Collection\": Display all books in collection" +
                "\n\"Exit\": Ends Program");
    }

    /**This method gets the inputs from an employee and adds a book to the store
     * @param conn the connection to the database
     * @param sc the scanner getting user input
     */
    public void addBookText(Connection conn, Scanner sc) {
        System.out.println("Enter the book's name");
        String bookName = sc.nextLine();
        System.out.println("Enter the Author's ID");
        int authorID = sc.nextInt();
        System.out.println("Enter the number of pages");
        int numberOfPages = sc.nextInt();
        System.out.println("Enter the initial amount of stock");
        int stock = sc.nextInt();
        System.out.println("Enter the price(Integer) of the book");
        int price = sc.nextInt();
        System.out.println("Enter the ISBN of the book");
        String isbn = sc.nextLine();
        System.out.println("Enter the Genre of the book");
        String genre = sc.nextLine();
        System.out.println("Enter the number of remaining books to restock at");
        int threshold = sc.nextInt();
        System.out.println("Enter the royalty the publisher gets");
        int bookRoyalty = sc.nextInt();
        System.out.println("Enter the email of the publisher");
        String email = sc.nextLine();

        Book newBook = new Book(authorID, bookName, numberOfPages, stock, price, isbn, genre, threshold, bookRoyalty, email, stock);
        newBook.addBook(conn);

        System.out.println(bookName + " has been added to store!");
    }

    /**This method gets the inputs from an employee and removes a book to the store
     * @param conn the connection to the database
     * @param sc the scanner getting user input
     */
    public void removeBookText(Connection conn, Scanner sc) {
        System.out.println("Enter the ISBN of the book");
        Book oldBook = new Book(0, "", 0, 0, 0, sc.nextLine(), "", 0, 0, "", 0);
        oldBook.removeBook(conn);
        System.out.println("The book has been removed");
    }

    /**This method gets the inputs from an employee and adds a publisher to the store database
     * @param conn the connection to the database
     * @param sc the scanner getting user input
     */
    public void addPublisherText(Connection conn, Scanner sc) {
        System.out.println("Enter the publisher's name");
        String publisherName = sc.nextLine();
        System.out.println("Enter the address of the publisher");
        String publisherAddress = sc.nextLine();
        System.out.println("Enter the phone number of the publisher");
        String phoneNumber = sc.nextLine();
        System.out.println("Enter the bank account number of the publisher");
        String bankAccount = sc.nextLine();
        System.out.println("Enter email of the publisher");
        String email = sc.nextLine();

        Publisher newPublisher = new Publisher(publisherName, publisherAddress, phoneNumber, bankAccount, email);
        newPublisher.addPublisher(conn);
    }

    /**This method gets the inputs from an employee and removes a publisher from the store database
     * @param conn the connection to the database
     * @param sc the scanner getting user input
     */
    public void removePublisherText(Connection conn, Scanner sc) {
        System.out.println("Enter the email of the publisher");
        Publisher oldPublisher = new Publisher("", "", "", "", sc.nextLine());
        oldPublisher.removePublisher(conn);
        System.out.println("The book has been removed");
    }

    /**This method gets the inputs from an employee and adds an author to the store database
     * @param conn the connection to the database
     * @param sc the scanner getting user input
     */
    public void addAuthorText(Connection conn, Scanner sc) {
        System.out.println("Enter the author's name");
        String authorName = sc.nextLine();
        System.out.println("Enter the author's ID");
        int authorID = sc.nextInt();
        Author newAuthor = new Author(authorName, authorID);
        newAuthor.addAuthor(conn);
    }

    /**This method gets the inputs from an employee and removes an author from the store database
     * @param conn the connection to the database
     * @param sc the scanner getting user input
     */
    public void removeAuthorText(Connection conn, Scanner sc) {
        System.out.println("Enter the author's ID");
        int authorID = sc.nextInt();
        Author oldAuthor = new Author("", authorID);
        oldAuthor.removeAuthor(conn);
    }

    /**This method displays all books in the store
     * @param conn the connection to the database
     */
    public void displayBooksTable(Connection conn) {
        String sql = ("SELECT * FROM Book");
        buildTable(sql, conn);
    }

    /**This method displays all books with a stock below or equal to 5
     * @param conn the connection to the database
     */
    public void displayCriticalStock(Connection conn) {
        String sql = ("SELECT name, stock FROM Book WHERE stock <= 5");
        buildTable(sql, conn);
    }

    /**This method add a value to the stock of a book to restock
     * @param conn the connection to the database
     * @param sc the scanner getting user input
     */
    public void restockText(Connection conn, Scanner sc) {
        System.out.println("Enter book ISBN");
        String isbn = sc.nextLine();
        System.out.println("Enter amount to buy");
        int amount = sc.nextInt();
        Book oldBook = new Book(0, "", 0, 0, 0, isbn, "", 0, 0, "", 0);
        oldBook.restock(conn, amount);
    }

    /**This method displays all books by a certain author
     * @param conn the connection to the database
     * @param author the author to search for books by
     */
    public void displayBooksByAuthorTable(Connection conn, String author) {
        String sql = ("SELECT * FROM Author INNER JOIN Book ON Author.authorid = Book.authorid WHERE Author.name = ?");
        try (conn; PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, author);
            String newSql = pstmt.toString();
            buildTable(newSql, conn);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**This method displays the book with a given isbn
     * @param conn the connection to the database
     * @param isbn the isbn of a book to find
     */
    public void displayBooksByISBNTable(Connection conn, String isbn) {
        String sql = ("SELECT Book.name, Book.authorID, Book.isbn, Book.stock, Book.price, Book.genre FROM Book WHERE isbn = ?");
        try (conn; PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, isbn);
            String newSql = pstmt.toString();
            buildTable(newSql, conn);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**This method displays all books with a certain genre associated with them
     * @param conn the connection to the database
     * @param genre the genre of books to search for
     */
    public void displayBooksByGenreTable(Connection conn, String genre) {
        String sql = ("SELECT Book.name, Book.authorID, Book.isbn, Book.stock, Book.price, Book.genre FROM Book WHERE genre = ?");
        try (conn; PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, genre);
            String newSql = pstmt.toString();
            buildTable(newSql, conn);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**This method displays all books with a given name
     * @param conn the connection to the database
     * @param bookName the name of the book to search fo
     */
    public void displayBooksByBookNameTable(Connection conn, String bookName) {
        String sql = ("SELECT Book.name, Book.authorID, Book.isbn, Book.stock, Book.price, Book.genre FROM Book WHERE name = ?");
        try (conn; PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, bookName);
            String newSql = pstmt.toString();
            buildTable(newSql, conn);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**This method displays all books in a certain collection
     * @param conn the connection to the database
     * @param collection the collection of books to search for
     */
    public void displayBooksByCollectionTable(Connection conn, String collection) {
        String sql = ("SELECT Book.name, Book.authorID, Book.isbn, Book.stock, Book.price, Book.genre FROM Book INNER JOIN Collection ON book.isbn = collection.isbn WHERE collection.collectionname = ?");
        try (conn; PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, collection);
            String newSql = pstmt.toString();
            buildTable(newSql, conn);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**This method gets the inputs from an employee and adds a collection to the store database
     * @param conn the connection to the database
     * @param sc the scanner getting user input
     * @param username the username of the employee creating the collection
     */
    public void createCollection(Connection conn, Scanner sc, String username) {
        System.out.println("Enter the name of the new collection");
        String collectionName = sc.nextLine();
        System.out.println("Enter the isbn of the first book to add to the collection");
        String isbn = sc.nextLine();
        Collection collection = new Collection(collectionName, username);
        collection.createCollection(isbn, conn);
    }

    /**This method gets the inputs from an employee and adds a book to a collection
     * @param conn the connection to the database
     * @param sc the scanner getting user input
     */
    public void addToCollection(Connection conn, Scanner sc) {
        System.out.println("Enter the name of the collection to add to");
        String collectionName = sc.nextLine();
        System.out.println("Enter the isbn of the book to add to the collection");
        String isbn = sc.nextLine();
        Collection collection = new Collection(collectionName, "");
        collection.addToCollection(isbn, conn);
    }

    /**This method displays all books in the customers cart
     * @param conn the connection to the database
     */
    public void displayCart(Connection conn) {
        String sql = ("SELECT Book.name, Book.authorID, Book.isbn, Book.stock, Book.price, Book.genre FROM Book INNER JOIN Cart ON Book.isbn = Cart.isbn");
        try (conn; PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String newSql = pstmt.toString();
            buildTable(newSql, conn);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**This method displays all sales made by the store
     * @param conn the connection to the database
     */
    public void getAllSales(Connection conn) {
        String sql = ("SELECT SUM(price * amount) as all_sales FROM (SELECT * FROM Book INNER JOIN OrderItem ON Book.isbn = OrderItem.isbn) AS book_order");
        buildTable(sql, conn);
    }

    /**This method displays the net profit after the publishers are paid
     * @param conn the connection to the database
     */
    public void getNetProfit(Connection conn) {
        String sql = ("SELECT SUM((price * amount) - (price * bookRoyalty * 0.01)) as net_profit FROM (SELECT * FROM Book INNER JOIN OrderItem ON Book.isbn = OrderItem.isbn) AS book_order");
        buildTable(sql, conn);
    }

    /**This method displays the sales made by a specific genre
     * @param genre the genre of book being searched for
     * @param conn the connection to the database
     */
    public void getSalesPerGenre(Connection conn, String genre) {
        String sql = ("SELECT SUM(price * amount) AS genre_sales FROM (SELECT * FROM Book INNER JOIN OrderItem ON Book.isbn = OrderItem.isbn WHERE Book.genre = ?) AS book_order");
        try (conn; PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, genre);
            String newSql = pstmt.toString();
            buildTable(newSql, conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**This method displays the sales made by a specific book
     * @param isbn the isbn of the book
     * @param conn the connection to the database
     */
    public void getSalesPerBook(Connection conn, String isbn) {
        String sql = ("SELECT SUM(price * amount) AS book_sales FROM (SELECT * FROM Book INNER JOIN OrderItem ON Book.isbn = OrderItem.isbn WHERE OrderItem.isbn = ?) AS book_orders");
        try (conn; PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, isbn);
            String newSql = pstmt.toString();
            buildTable(newSql, conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**This method displays the sales made by an author
     * @param authorID the id of the author
     * @param conn the connection to the database
     */
    public void getSalesPerAuthor(Connection conn, int authorID) {
        String sql = ("SELECT SUM(price * amount) as author_sales FROM (SELECT * FROM Book INNER JOIN OrderItem ON Book.isbn = OrderItem.isbn WHERE authorID = ?) AS book_order");
        try (conn; PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, authorID);
            String newSql = pstmt.toString();
            buildTable(newSql, conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**This method displays the sales made by a publisher
     * @param email the email of the publisher
     * @param conn the connection to the database
     */
    public void getSalesPerPublisher(Connection conn, String email) {
        String sql = ("SELECT SUM(price * amount) as all_sales FROM (SELECT * FROM Book INNER JOIN OrderItem ON Book.isbn = OrderItem.isbn WHERE email = ?) AS book_order");
        try (conn; PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            String newSql = pstmt.toString();
            buildTable(newSql, conn);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**This method displays all the orders from the store
     * @param conn the connection to the database
     */
    public void getAllOrders(Connection conn) {
        String sql = ("SELECT * FROM BookOrder");
        buildTable(sql, conn);
    }

    /**This method gets the inputs from a customer and creates a new customer in the table
     * @param sc the scanner getting user input
     * @param conn the connection to the database
     */
    public void register(Connection conn, Scanner sc) {
        System.out.println("Enter your full name:");
        String name = sc.nextLine();
        System.out.println("Enter your Username:");
        String username = sc.nextLine();
        System.out.println("Enter your desired Password:");
        String password = sc.nextLine();
        System.out.println("Enter your Address:");
        String address = sc.nextLine();
        System.out.println("Enter your Postal code:");
        String postalCode = sc.nextLine();
        System.out.println("Enter your Credit Card number:");
        String paymentInfo = sc.nextLine();
        Customer newCustomer = new Customer(name, username, password, address, postalCode, paymentInfo, conn);
        newCustomer.addCustomer(conn);
    }

    /**This method displays a table for any retrieved sql table
     * @param sql the table gotten by a sql statement
     * @param conn the connection to the database
     */
    public void buildTable(String sql, Connection conn) {
        try {
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(sql);
            ResultSetMetaData metaData = result.getMetaData();

            int columnsNumber = metaData.getColumnCount();

            System.out.println("================================================");
            while (result.next()) {
                for(int i = 1 ; i < columnsNumber; i++) {
                    System.out.print(metaData.getColumnName(i) + ": " + result.getString(i) + ", ");
                }
                System.out.println(metaData.getColumnName(columnsNumber) + ": " + result.getString(columnsNumber));
            }
            System.out.println("================================================");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
