import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Publisher {
    private String name;
    private String address;
    private String phoneNumber;
    private String bankAccount;
    private String email;

    public Publisher(String name, String address, String phoneNumber, String bankAccount, String email) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.bankAccount = bankAccount;
        this.email = email;
    }

    public void addPublisher(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("insert into Book values('" + this.name + "','" + this.address + "','" + this.phoneNumber + "','" + this.bankAccount + "','" + this.email + "')");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removePublisher(Connection conn) {
        String sql = ("DELETE FROM Publisher WHERE email = ?");

        try (conn; PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, getEmail());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
