import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Types;
public class Main {
    public static void main(String[] args) throws SQLException {
        Connection conn = TryConnection.connect();

        TextInterface ti = new TextInterface();
        Scanner sc = new Scanner(System.in);
        String userOption = "";
        String mainCommand = "";
        String subCommand;
        boolean userExists = false;

        while (!userOption.equalsIgnoreCase("Customer") && !userOption.equalsIgnoreCase("Employee")) {
            ti.userOption();
            userOption = sc.next();
        }

        while (!mainCommand.equalsIgnoreCase("Exit")) {
            if (userOption.equalsIgnoreCase("Customer")) {
                while (!userExists) {
                    ti.customerLogin();
                    mainCommand = sc.next();
                    if (mainCommand.equalsIgnoreCase("Login")) {
                        System.out.println("Enter your Username:");
                        String username = sc.next();
                        System.out.println("Enter your Password");
                        String password = sc.next();
                        Customer currentCustomer = new Customer("", username, password, "", "", "");
                        if (currentCustomer.checkExists(conn)) {
                            userExists = true;
                        }
                    } else if (mainCommand.equalsIgnoreCase("Register")) {
                        System.out.println("Enter your full name:");
                        String name = sc.next();
                        System.out.println("Enter your Username:");
                        String username = sc.next();
                        System.out.println("Enter your desired Password:");
                        String password = sc.next();
                        System.out.println("Enter your Address:");
                        String address = sc.next();
                        System.out.println("Enter your Postal code:");
                        String postalCode = sc.next();
                        System.out.println("Enter your Phone number:");
                        String phoneNumber = sc.next();
                        System.out.println("Enter your Credit Card number:");
                        String paymentInfo = sc.next();
                        Customer newCustomer = new Customer(name, username, password, address, postalCode, paymentInfo);
                        newCustomer.addCustomer(conn);
                        userExists = true;
                    } else {
                        ti.invalidLogin();
                    }
                }
                ti.customerMenu();
                mainCommand = sc.next();

                if (mainCommand.equalsIgnoreCase("DisplayBooks")) {
                    ti.displayBooksTable(conn);
                } else if (mainCommand.equalsIgnoreCase("SearchBooks")) {
                    ti.searchBookText();
                    subCommand = sc.next();
                    if(subCommand.equalsIgnoreCase("BookName")) {

                    } else if (subCommand.equalsIgnoreCase("AuthorName")) {
                        System.out.println("Enter the name of an author");
                        String author = sc.next();
                        ti.displayBooksByAuthorTable(conn, author);
                    } else if (subCommand.equalsIgnoreCase("ISBN")) {
                        System.out.println("Enter the book's ISBN");
                        String isbn = sc.next();
                        ti.displayBooksByISBNTable(conn, isbn);
                    } else if (subCommand.equalsIgnoreCase("Genre")) {
                        System.out.println("Enter the Genre of books");
                        String genre = sc.next();
                        ti.displayBooksByGenreTable(conn, genre);
                    }
                } else if (mainCommand.equalsIgnoreCase("AddBook")) {
                    System.out.println("Enter the ISBN of the book you would like to add to cart");
                    String isbn = sc.next();
                    Cart cart = new Cart();
                    cart.addToCart(isbn, conn);
                    System.out.println("The book has been added to cart");

                } else if (mainCommand.equalsIgnoreCase("RemoveBook")) {
                    System.out.println("Enter the ISBN of the book you would like to remove from cart");
                    String isbn = sc.next();
                    Cart cart = new Cart();
                    cart.removeFromCart(isbn, conn);
                    System.out.println("The book has been removed from the cart");

                } else if (mainCommand.equalsIgnoreCase("DisplayCart")) {
                    ti.displayCart(conn);

                } else if (mainCommand.equalsIgnoreCase("Checkout")) {
                    System.out.println("Enter your credit card number");
                    String paymentInfo = sc.next();
                    System.out.println("Enter the desired shipping address");
                    String address = sc.next();
                    BookOrder newOrder = new BookOrder(address, paymentInfo);
                    newOrder.makeOrder(conn);
                    System.out.println("Your order has been placed");

                } else if (mainCommand.equalsIgnoreCase("DisplayAllOrders")) {
                    ti.getAllOrders(conn);

                } else if (mainCommand.equalsIgnoreCase("DisplayAnOrdersInfo")) {
                    System.out.println("Enter your order number");
                    int number = sc.nextInt();
                    ti.getOrder(number);

                } else if (!mainCommand.equalsIgnoreCase("Exit")) {
                    ti.invalidCommand();
                }
            } else if (userOption.equalsIgnoreCase("Employee")) {
                ti.employeeMenu();
                mainCommand = sc.next();

                if (mainCommand.equalsIgnoreCase("AddBook")) {
                    ti.addBookText(conn, sc);
                } else if (mainCommand.equalsIgnoreCase("RemoveBooks")) {
                    ti.removeBookText(conn, sc);
                } else if (mainCommand.equalsIgnoreCase("AddPublisher")) {
                    ti.addPublisherText(conn, sc);
                } else if (mainCommand.equalsIgnoreCase("RemoverPublisher")) {
                    ti.removePublisherText(conn, sc);
                } else if (mainCommand.equalsIgnoreCase("AddAuthor")) {
                    ti.addAuthorText(conn, sc);
                } else if (mainCommand.equalsIgnoreCase("RemoveAuthor")) {
                    ti.removeAuthorText(conn, sc);
                } else if (mainCommand.equalsIgnoreCase("CriticalStock")) {
                    ti.displayCriticalStock(conn);
                } else if (mainCommand.equalsIgnoreCase("Restock")) {
                    ti.restockText(conn, sc);
                } else if (!mainCommand.equalsIgnoreCase("Exit")) {
                        ti.invalidCommand();
                }
            }
        }
    }
}
