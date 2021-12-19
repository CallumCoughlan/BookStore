import java.sql.*;

public class Collection {
    private String collectionName;
    private String isbn;
    private String username;

    public Collection(String collectionName, String username) {
        this.collectionName = collectionName;
        this.username = username;
    }

    public void createCollection(String isbn, Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Collection VALUES('" + this.collectionName + "','" + isbn + "','" + this.username + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addToCollection(String isbn, Connection conn) {
        try {
            Statement st = conn.createStatement();
            String sql = ("SELECT username FROM Collection WHERE collectionName = '" + this.collectionName + "'");
            ResultSet result = st.executeQuery(sql);
            result.next();
            this.username = result.getString(1);
            st.executeUpdate("INSERT INTO Collection VALUES('" + this.collectionName + "','" + isbn + "','" + this.username + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
