package database;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/personal_manager"; // Замените 'kursarbeit' на вашу базу данных
    private static final String USER = "root"; // Замените на вашего пользователя MySQL
    private static final String PASSWORD = "malika2005"; // Замените на ваш пароль MySQL

    // Метод для подключения к базе данных
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Метод для регистрации пользователя
    public static boolean registerUser(String username, String password) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Хешируем пароль перед сохранением
            String hashedPassword = hashPassword(password);

            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; // Если хотя бы 1 строка добавлена, возвращаем true

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Метод для хеширования пароля (SHA-256)
    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Ошибка хеширования пароля", e);
        }
    }

    public static boolean authenticateUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, hashPassword(password)); // Хешируем пароль перед проверкой

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Если есть запись, то пользователь найден и пароль совпадает

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // Метод для добавления заметки в базу данных
    public static boolean addNoteToDatabase(int userId, String noteContent) {
        String query = "INSERT INTO Notes (content, date_created, user_id) VALUES (?, NOW(), ?)";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, noteContent);
            stmt.setInt(2, userId);  // Передаем userId текущего пользователя
            int rowsInserted = stmt.executeUpdate();

            return rowsInserted > 0; // Возвращаем true, если заметка была успешно добавлена
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    }

