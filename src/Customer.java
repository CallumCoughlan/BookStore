import java.sql.*;

public class Customer {
    String customerName;
    String userName;
    String password;
    String address;
    String postalCode;
    String paymentInfo;
    private int cartID;

    /**This is the constructor for Customer with parameters
     * @param conn The connection to the database
     * @param customerName The name of the customer
     * @param userName The customers username
     * @param password The customers password
     * @param address The address of the customer
     * @param paymentInfo The payment information for the customer
     * @param postalCode The customers postal code
     */
    public Customer(String customerName, String userName, String password, String address, String postalCode, String paymentInfo, Connection conn) {
        this.customerName = customerName;
        this.userName = userName;
        this.password = password;
        this.address = address;
        this.postalCode = postalCode;
        this.paymentInfo = paymentInfo;
        this.cartID = getRows(conn);
    }

    /**This method adds a customer to the customer tavle in the database
     * @param conn The connection to the database
     */
    public void addCustomer(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Customer values('" + this.customerName + "','" + this.userName + "','" + this.password + "','" + this.address + "','" + this.postalCode + "','" + this.paymentInfo + "','" + this.cartID + "')");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**This method checks to see if the customer exists in the customer table when they try to login
     * @param conn The connection to the database
     */
    public boolean checkExists(Connection conn) {
        try {
            String sql = ("SELECT * FROM Customer WHERE username = '" + this.userName + "' AND password = '" + this.password + "'");
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(sql);

            return result.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**This method gets the number of rows in the customer table
     * @param conn The connection to the database
     */
    public int getRows(Connection conn) {
        try {
            String sql = ("SELECT * FROM Customer");
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(sql);
            int rows = 1;

            while (!result.next()) {
                rows += 1;
            }
            return rows;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 1;
    }
}
