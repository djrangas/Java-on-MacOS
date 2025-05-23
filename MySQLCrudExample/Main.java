import java.sql.*;

public class MySQLCrudExample {

    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/djrangas_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // JDBC variables for opening and managing a connection
    private static Connection connection;
    private static Statement statement;

    // Establish connection to the database
    public static void connect() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();
            System.out.println("Connected to the database.");
        } catch (SQLException e) {
            System.out.println("Error while connecting: " + e.getMessage());
        }
    }

    // Create a new user
    public static void createUser(String id, String name) {
        try {
            String query = "INSERT INTO users (id, name) VALUES ('" + id + "', '" + name + "')";
            statement.executeUpdate(query);
            System.out.println("User created successfully.");
        } catch (SQLException e) {
            System.out.println("Error while creating user: " + e.getMessage());
        }
    }

    // Read users from the database
    public static void readUsers() {
        try {
            String query = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                System.out.println("ID: " + id + ", Name: " + name);
            }
        } catch (SQLException e) {
            System.out.println("Error while reading users: " + e.getMessage());
        }
    }

    // Update a user's name
    public static void updateUser(String id, String newName) {
        try {
            String query = "UPDATE users SET name = '" + newName + "' WHERE id = '" + id + "'";
            statement.executeUpdate(query);
            System.out.println("User updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error while updating user: " + e.getMessage());
        }
    }

    // Delete a user
    public static void deleteUser(String id) {
        try {
            String query = "DELETE FROM users WHERE id = '" + id + "'";
            statement.executeUpdate(query);
            System.out.println("User deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error while deleting user: " + e.getMessage());
        }
    }

    // Close the connection
    public static void close() {
        try {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
            System.out.println("Connection closed.");
        } catch (SQLException e) {
            System.out.println("Error while closing connection: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        connect();

        // Test the CRUD operations
        createUser("1", "John Doe");
        createUser("2", "Jane Smith");
        readUsers();
        updateUser("1", "Rangas");
        readUsers();
        deleteUser("2");
        readUsers();

        close();
    }
}
