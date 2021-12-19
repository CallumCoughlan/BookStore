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
            stmt.executeUpdate("INSERT INTO Publisher VALUES('" + this.name + "','" + this.address + "','" + this.phoneNumber + "','" + this.bankAccount + "','" + this.email + "')");
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

    public String getEmail() {
        return email;
    }
}
