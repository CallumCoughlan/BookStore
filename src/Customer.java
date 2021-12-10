import java.sql.*;

public class Customer {
    String customerName;
    String userName;
    String password;
    String address;
    String postalCode;
    String phoneNumber;
    String paymentInfo;

    public Customer(String customerName, String userName, String password, String address, String postalCode, String paymentInfo, String phoneNumber) {
        this.customerName = customerName;
        this.userName = userName;
        this.password = password;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.paymentInfo = paymentInfo;
    }

    public void addCustomer(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("INSERT INTO Customer values('" + this.customerName + "','" + this.userName + "','" + this.password + "','" + this.address + "','" + "','" + this.postalCode + "','" + "','" + this.phoneNumber + "','" + this.paymentInfo + "')");
    }

    public boolean checkExists(Connection conn) {
        try {
            String sql = ("SELECT * FROM Customer WHERE username = " + this.userName + " AND password = " + this.password);
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(sql);

            if (!result.next()) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
