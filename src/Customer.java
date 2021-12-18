import java.sql.*;

public class Customer {
    String customerName;
    String userName;
    String password;
    String address;
    String postalCode;
    String paymentInfo;
    private int cartID;

    public Customer(String customerName, String userName, String password, String address, String postalCode, String paymentInfo) {
        this.customerName = customerName;
        this.userName = userName;
        this.password = password;
        this.address = address;
        this.postalCode = postalCode;
        this.paymentInfo = paymentInfo;
    }

    public void addCustomer(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Customer values('" + this.customerName + "','" + this.userName + "','" + this.password + "','" + this.address + "','" + this.postalCode + "','" + this.paymentInfo + "')");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

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
}
