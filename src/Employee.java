import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Employee {
    private String userName;
    private String password;

    /**The constructor for employee with parameters
     * @param userName The username of the employee
     * @param password The password of the employee
     */
    public Employee(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**This method checks to see if the employee exists in the employee table when they try to login
     * @param conn The connection to the database
     */
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
