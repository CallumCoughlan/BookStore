import java.sql.Connection;
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

    public void addPublisher(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("insert into Book values('" + this.name + "','" + this.address + "','" + this.phoneNumber + "','" + this.bankAccount + "','" + this.email + "')");
    }
}
