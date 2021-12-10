import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Types;
public class Main {
    public static void main(String args[]) throws SQLException {
        connect();

        TextInterface ti = new TextInterface();
        Scanner sc = new Scanner(System.in);
        String userOption = "";
        String command = "";

        while(!userOption.equalsIgnoreCase("Customer") || !userOption.equalsIgnoreCase("Employee")) {
            ti.userOption();
            userOption = sc.next();
        }

        while(!command.equalsIgnoreCase("Exit")) {
            if (userOption.equalsIgnoreCase("Customer")) {
                ti.customerMenu();
                command = sc.next();

                if(command.equalsIgnoreCase("DisplayBooks")) {

                } else if (command.equalsIgnoreCase("SearchBooks")) {

                } else if (command.equalsIgnoreCase("AddBook")) {

                } else if (command.equalsIgnoreCase("RemoveBook")) {

                } else if (command.equalsIgnoreCase("Checkout")) {

                } else {
                    ti.invalidCommand();
                }
            } else if (userOption.equalsIgnoreCase("Employee")) {
                ti.employeeMenu();
            }
        }
    }

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:5432/Bookstore", "postgres", "MacDuff7476!");
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}
