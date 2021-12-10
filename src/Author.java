import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Author {
    private String name;
    private String authorID;

    public Author(String name, String authorID) {
        this.name = name;
        this.authorID = authorID;
    }

    public void addAuthor(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("insert into Author values('" + this.name + "','" + this.authorID + "')");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeAuthor(Connection conn) {
        String sql = ("DELETE FROM Author WHERE authorID = ?");
        try (conn; PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, getAuthorID());
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

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }
}
