import java.sql.Connection;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Connection conn = TryConnection.connect();

        TextInterface ti = new TextInterface();
        Scanner sc = new Scanner(System.in);
        String userOption = "";
        String mainCommand = "";
        String subCommand = "";
        String username = "";
        boolean userExists = false;

        while (!userOption.equalsIgnoreCase("Customer") && !userOption.equalsIgnoreCase("Employee")) {
            ti.userOption();
            userOption = sc.nextLine();
        }

        while (!mainCommand.equalsIgnoreCase("Exit")) {
            if (userOption.equalsIgnoreCase("Customer")) {
                while (!userExists) {
                    ti.customerLogin();
                    mainCommand = sc.nextLine();
                    if (mainCommand.equalsIgnoreCase("Login")) {
                        System.out.println("Enter your Username:");
                        username = sc.nextLine();
                        System.out.println("Enter your Password");
                        String password = sc.nextLine();
                        Customer currentCustomer = new Customer("", username, password, "", "", "", conn);
                        if (currentCustomer.checkExists(conn)) {
                            userExists = true;
                        }
                    } else if (mainCommand.equalsIgnoreCase("Register")) {
                        ti.register(conn, sc);
                        userExists = true;
                    } else {
                        ti.invalidLogin();
                    }
                }
                ti.customerMenu();
                mainCommand = sc.nextLine();

                if (mainCommand.equalsIgnoreCase("DisplayBooks")) {
                    ti.displayBooksTable(conn);
                } else if (mainCommand.equalsIgnoreCase("SearchBooks")) {
                    ti.searchBookText();
                    subCommand = sc.nextLine();
                    if(subCommand.equalsIgnoreCase("BookName")) {
                        System.out.println("Enter the name of a Book");
                        String bookName = sc.nextLine();
                        ti.displayBooksByBookNameTable(conn, bookName);
                    } else if (subCommand.equalsIgnoreCase("AuthorName")) {
                        System.out.println("Enter the name of an author");
                        String author = sc.nextLine();
                        ti.displayBooksByAuthorTable(conn, author);
                    } else if (subCommand.equalsIgnoreCase("ISBN")) {
                        System.out.println("Enter the book's ISBN");
                        String isbn = sc.nextLine();
                        ti.displayBooksByISBNTable(conn, isbn);
                    } else if (subCommand.equalsIgnoreCase("Genre")) {
                        System.out.println("Enter the Genre of books");
                        String genre = sc.nextLine();
                        ti.displayBooksByGenreTable(conn, genre);
                    } else if (subCommand.equalsIgnoreCase("Collection")) {
                        System.out.println("Enter the Collection of books");
                        String collection = sc.nextLine();
                        ti.displayBooksByCollectionTable(conn, collection);
                    }
                } else if (mainCommand.equalsIgnoreCase("AddBook")) {
                    System.out.println("Enter the ISBN of the book you would like to add to cart");
                    String isbn = sc.nextLine();
                    System.out.println("Enter the amount of that book");
                    int amount = sc.nextInt();
                    Cart cart = new Cart(username, conn);
                    cart.addToCart(isbn, conn, amount);
                    System.out.println("The book has been added to cart");

                } else if (mainCommand.equalsIgnoreCase("RemoveBook")) {
                    System.out.println("Enter the ISBN of the book you would like to remove from cart");
                    String isbn = sc.nextLine();
                    Cart cart = new Cart(username, conn);
                    cart.removeFromCart(isbn, conn);
                    System.out.println("The book has been removed from the cart");

                } else if (mainCommand.equalsIgnoreCase("DisplayCart")) {
                    ti.displayCart(conn);
                } else if (mainCommand.equalsIgnoreCase("Checkout")) {
                    System.out.println("Enter your credit card number");
                    String paymentInfo = sc.nextLine();
                    System.out.println("Enter the desired shipping address");
                    String shippingAddress = sc.nextLine();
                    Cart cart = new Cart(username, conn);
                    cart.makePurchase(conn, paymentInfo, shippingAddress);
                    System.out.println("Your order has been placed");

                } else if (mainCommand.equalsIgnoreCase("DisplayAllOrders")) {
                    ti.getAllOrders(conn);

                } else if (!mainCommand.equalsIgnoreCase("Exit")) {
                    ti.invalidCommand();
                }
            } else if (userOption.equalsIgnoreCase("Employee")) {
                while (!userExists) {
                    ti.employeeLogin();
                    System.out.println("Enter your Username:");
                    username = sc.nextLine();
                    System.out.println("Enter your Password");
                    String password = sc.nextLine();
                    Employee currentEmployee = new Employee(username, password);
                    if (currentEmployee.checkExists(conn)) {
                        userExists = true;
                    }
                }

                ti.employeeMenu();
                mainCommand = sc.nextLine();
                if (mainCommand.equalsIgnoreCase("AddBook")) {
                    ti.addBookText(conn, sc);
                } else if (mainCommand.equalsIgnoreCase("RemoveBook")) {
                    ti.removeBookText(conn, sc);
                } else if (mainCommand.equalsIgnoreCase("AddPublisher")) {
                    ti.addPublisherText(conn, sc);
                } else if (mainCommand.equalsIgnoreCase("RemovePublisher")) {
                    ti.removePublisherText(conn, sc);
                } else if (mainCommand.equalsIgnoreCase("AddAuthor")) {
                    ti.addAuthorText(conn, sc);
                } else if (mainCommand.equalsIgnoreCase("RemoveAuthor")) {
                    ti.removeAuthorText(conn, sc);
                } else if (mainCommand.equalsIgnoreCase("AddToCollection")) {
                    ti.addToCollection(conn, sc);
                } else if (mainCommand.equalsIgnoreCase("CreateCollection")) {
                    ti.createCollection(conn, sc, username);
                } else if (mainCommand.equalsIgnoreCase("CriticalStock")) {
                    ti.displayCriticalStock(conn);
                } else if (mainCommand.equalsIgnoreCase("Restock")) {
                    ti.restockText(conn, sc);
                } else if (mainCommand.equalsIgnoreCase("ViewSales")) {
                    ti.viewSalesText();
                    subCommand = sc.nextLine();
                    if (subCommand.equalsIgnoreCase("AllSales")) {
                        ti.getAllSales(conn);
                    } else if (subCommand.equalsIgnoreCase("NetProfit")) {
                        ti.getNetProfit(conn);
                    } else if (subCommand.equalsIgnoreCase("SalesPerGenre")) {
                        System.out.println("Please enter the name of a genre");
                        ti.getSalesPerGenre(conn, sc.nextLine());
                    } else if (subCommand.equalsIgnoreCase("SalesPerBook")) {
                        System.out.println("Please enter the isbn of a book");
                        ti.getSalesPerBook(conn, sc.nextLine());
                    } else if (subCommand.equalsIgnoreCase("SalesPerAuthor")) {
                        System.out.println("Please enter the authorID of an Author");
                        ti.getSalesPerAuthor(conn, sc.nextInt());
                    } else if (subCommand.equalsIgnoreCase("SalesPerPublisher")) {
                        System.out.println("Please enter the email of a Publisher");
                        ti.getSalesPerPublisher(conn, sc.nextLine());
                    } else if (!mainCommand.equalsIgnoreCase("Exit")) {
                        ti.invalidCommand();
                    }
                } else if (!mainCommand.equalsIgnoreCase("Exit")) {
                        ti.invalidCommand();
                }
            }
        }
    }
}
