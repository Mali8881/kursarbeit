package database;

import Manager_models.RegisterForm;

import java.sql.*;

import static database.ContactsDAO.DatabaseConnection;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/personal_manager";
    private static final String USER = "root";
    private static final String PASSWORD = "malika2005";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean registerUser(String name, String email, String phone, String password) {
        String sql = "INSERT INTO users (name, email, phone, password) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, phone);
            stmt.setString(4, password);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }





    // Аутентификация пользователя



        // Метод для проверки пользователя
        public static boolean authenticateUser(String name, String password) {
            String sql = "SELECT * FROM users WHERE name = ? AND password = ?";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, name);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();
                return rs.next();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

}
// Метод для получения всех задач
