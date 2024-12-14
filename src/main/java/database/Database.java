package database;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Properties;

public class Database {
    private static Connection connection;

    // Получение соединения
    public static Connection getConnection() throws SQLException, ClassNotFoundException, IOException {
        if (connection == null || connection.isClosed()) {
            Properties properties = new Properties();
            try (InputStream input = Database.class.getClassLoader().getResourceAsStream("sql.properties")) {
                if (input == null) {
                    throw new IOException("Файл sql.properties не найден.");
                }
                properties.load(input);
            }

            String dbUrl = properties.getProperty("database.url");
            String dbUser = properties.getProperty("database.login");
            String dbPassword = properties.getProperty("database.pass");

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        }
        return connection;
    }

    // Хэширование пароля
    private static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Ошибка хеширования пароля", e);
        }
    }

    // Регистрация пользователя
    public static boolean registerUser(String username, String password) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, hashPassword(password));
            pstmt.executeUpdate();
            return true;

        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("Пользователь уже существует: " + username);
        } catch (SQLException e) {
            System.err.println("Ошибка SQL при регистрации пользователя: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка при подключении к базе данных: " + e.getMessage());
        }
        return false;
    }

    // Авторизация пользователя
    public static boolean authenticateUser(String username, String password) {
        String sql = "SELECT password FROM users WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return hashPassword(password).equals(storedPassword);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка SQL при авторизации: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка при подключении к базе данных: " + e.getMessage());
        }
        return false;
    }

    // Добавление задачи
    public static void addTask(String date, String time, String description, String priority) {
        String insert = "INSERT INTO tasks (date, time, description, priority) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(insert)) {
            stmt.setString(1, date);
            stmt.setString(2, time);
            stmt.setString(3, description);
            stmt.setString(4, priority);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Ошибка при добавлении задачи: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка при подключении к базе данных: " + e.getMessage());
        }
    }
}
