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

        while(!userOption.equalsIgnoreCase("Customer") || !userOption.equalsIgnoreCase("Employee")) {
            ti.userOption();
            userOption = sc.next();
        }

        while(!mainCommand.equalsIgnoreCase("Exit")) {
            if (userOption.equalsIgnoreCase("Customer")) {
                ti.customerMenu();
                mainCommand = sc.next();

                if(mainCommand.equalsIgnoreCase("DisplayBooks")) {
                    ti.displayBooksTable(conn);
                } else if (mainCommand.equalsIgnoreCase("SearchBooks")) {
                    ti.searchBookText();
                    subCommand = sc.next();
                    if(subCommand.equalsIgnoreCase("BookName")) {

                    }
                } else if (mainCommand.equalsIgnoreCase("AddBook")) {

                } else if (mainCommand.equalsIgnoreCase("RemoveBook")) {

                } else if (mainCommand.equalsIgnoreCase("Checkout")) {

                } else {
                    ti.invalidCommand();
                }
            } else if (userOption.equalsIgnoreCase("Employee")) {
                ti.employeeMenu();
                mainCommand = sc.next();

                if(mainCommand.equalsIgnoreCase("AddBook")) {
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
                } else {
                        ti.invalidCommand();
                }
            }
        }
    }
}
