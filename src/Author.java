import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Author {
    private String name;
    private int authorID;

    /**This is the constructor of Author with parameters
     * @param name The name of the author
     * @param authorID The authors ID
     */
    public Author(String name, int authorID) {
        this.name = name;
        this.authorID = authorID;
    }

    /**This method is used to add an author to the author table
     * @param conn The connection to the database
     */
    public void addAuthor(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("insert into Author values('" + this.name + "','" + this.authorID + "')");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**This method is used to remove an author from the author table
     * @param conn The connection to the database
     */
    public void removeAuthor(Connection conn) {
        String sql = ("DELETE FROM Author WHERE authorID = ?");
        try (conn; PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, getAuthorID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**This method is used to get the authorID of an instance of author
     */
    public int getAuthorID() {
        return authorID;
    }

}
