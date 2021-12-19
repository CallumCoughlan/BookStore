import java.sql.*;

public class Customer {
    String customerName;
    String userName;
    String password;
    String address;
    String postalCode;
    String paymentInfo;
    private int cartID;

    public Customer(String customerName, String userName, String password, String address, String postalCode, String paymentInfo, Connection conn) {
        this.customerName = customerName;
        this.userName = userName;
        this.password = password;
        this.address = address;
        this.postalCode = postalCode;
        this.paymentInfo = paymentInfo;
        this.cartID = getRows(conn);
    }

    public void addCustomer(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Customer values('" + this.customerName + "','" + this.userName + "','" + this.password + "','" + this.address + "','" + this.postalCode + "','" + this.paymentInfo + "','" + this.cartID + "')");
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
