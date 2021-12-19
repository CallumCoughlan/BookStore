import java.sql.*;

public class Collection {
    private String collectionName;
    private String username;

    /**The constructor of Collection with parameters
     * @param collectionName The name of the collection
     * @param username The username of the employee making the collection
     */
    public Collection(String collectionName, String username) {
        this.collectionName = collectionName;
        this.username = username;
    }

    /**This method adds to the instance of collection to the collection table
     * @param isbn The isbn of the book that starts the collection
     * @param conn The connection to the database
     */
    public void createCollection(String isbn, Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Collection VALUES('" + this.collectionName + "','" + isbn + "','" + this.username + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**This method adds to the book to the collection table under a certain collection
     * @param isbn The isbn of the book that starts the collection
     * @param conn The connection to the database
     */
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
