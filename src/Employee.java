import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Employee {
    private String userName;
    private String password;

    public Employee(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public boolean checkExists(Connection conn) {
        try {
            String sql = ("SELECT * FROM Employee WHERE username = '" + this.userName + "' AND password = '" + this.password + "'");
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(sql);

            return result.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
