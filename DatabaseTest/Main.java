import java.sql.*;

public class DatabaseTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/djrangas_db";
        String user = "root";
        String password = "";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database.");
            
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error while connecting: " + e.getMessage());
        }
    }
}