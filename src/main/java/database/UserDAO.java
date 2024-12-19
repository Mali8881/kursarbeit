package database;



import Manager_models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static database.ContactsDAO.DatabaseConnection;


public class UserDAO {

    // Метод для аутентификации пользователя
    public static User authenticateUser(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    // Метод для регистрации нового пользователя
    public static boolean registerUser(String name, String email, String phone, String password) {
        String sql = "INSERT INTO users (name, email, phone, password) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, phone);
            stmt.setString(4, password);

            return stmt.executeUpdate() > 0; // Возвращает true, если пользователь успешно добавлен
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Возвращает false, если произошла ошибка
        }
    }
}

