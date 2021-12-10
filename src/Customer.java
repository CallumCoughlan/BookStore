import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Customer {
    String customerName;
    int customerID;
    String userName;
    String password;
    String address;
    String postalCode;
    String paymentInfo;

    public Customer(String customerName, int customerID, String userName, String password, String address, String postalCode) {
        this.customerName = customerName;
        this.customerID = customerID;
        this.userName = userName;
        this.password = password;
        this.address = address;
        this.postalCode = postalCode;
    }

    public void addCustomer(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("insert into Book values('" + this.customerName + "','" + this.customerID + "','" + this.userName + "','" + this.password + "','" + this.address + "','" + "','" + this.postalCode + "')");
    }

    public void removeCustomer(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("delete from Book where values('" + this.customerName + "','" + this.customerID + "','" + this.userName + "','" + this.password + "','" + this.address + "','" + "','" + this.postalCode + "')");
    }
}
