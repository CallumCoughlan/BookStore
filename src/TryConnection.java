import java.sql.DriverManager;
import java.sql.SQLException;

public class TryConnection {
    public TryConnection() {

    }

    public static java.sql.Connection connect() {
        java.sql.Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:5432/Bookstore", "postgres", "MacDuff7476!");
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}