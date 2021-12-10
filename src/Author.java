import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Author {
    private String name;
    private int authorID;

    public Author(String name, int authorID) {
        this.name = name;
        this.authorID = authorID;
    }

    public void addAuthor(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("insert into Author values('" + this.name + "','" + this.authorID + "')");
    }

    public void removeAuthor(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("delete from Author where values('" + this.name + "','" + this.authorID + "')");
    }
}
