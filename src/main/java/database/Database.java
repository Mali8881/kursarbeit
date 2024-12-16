package database;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import views.ReminderManager;
import views.Task;

public class Database {

    private static final String URL = "jdbc:mysql://localhost:3306/personal_manager"; // Обновите URL
    private static final String USER = "root";  // Обновите с вашим пользователем
    private static final String PASSWORD = "malika2005";  // Обновите с вашим паролем

    // Метод для получения соединения с базой данных
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Метод для регистрации пользователя
    public static boolean registerUser(String username, String password, String email) {
        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Метод для аутентификации пользователя
    public static boolean authenticateUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            return rs.next();  // Если результат не пустой, пользователь найден
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Метод для добавления задачи
    public static boolean addTask(String title, String description, String category, LocalDate startDate, LocalDate dueDate, String priority, String status) {
        String sql = "INSERT INTO tasks (title, description, category, start_date, due_date, priority, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setString(3, category);
            stmt.setDate(4, java.sql.Date.valueOf(startDate));  // Начальная дата
            stmt.setDate(5, java.sql.Date.valueOf(dueDate));    // Дата завершения
            stmt.setString(6, priority);
            stmt.setString(7, status);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        Task newTask = new Task(String.valueOf(generatedId), title, description, category, startDate, dueDate, priority, status);
                        // Добавьте новую задачу в ObservableList или выполните другие действия
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Метод для удаления задачи
    public static boolean removeTask(int taskId) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, taskId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Метод для обновления задачи
    public static boolean updateTask(int id, String title, String description, String category, LocalDate startDate, LocalDate dueDate, String priority, String status) {
        String sql = "UPDATE tasks SET title = ?, description = ?, category = ?, start_date = ?, due_date = ?, priority = ?, status = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setString(3, category);
            stmt.setDate(4, java.sql.Date.valueOf(startDate));
            stmt.setDate(5, java.sql.Date.valueOf(dueDate));
            stmt.setString(6, priority);
            stmt.setString(7, status);
            stmt.setInt(8, id);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Метод для получения всех задач
    public static ObservableList<Task> getTasks() {
        ObservableList<Task> tasks = FXCollections.observableArrayList();
        String sql = "SELECT * FROM tasks";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String category = rs.getString("category");
                LocalDate startDate = rs.getDate("start_date").toLocalDate();  // Начальная дата
                LocalDate dueDate = rs.getDate("due_date").toLocalDate();  // Дата завершения
                String priority = rs.getString("priority");
                String status = rs.getString("status");

                tasks.add(new Task(String.valueOf(id), title, description, category, startDate, dueDate, priority, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    // Метод для получения напоминаний
    public static ObservableList<ReminderManager> getReminders() {
        ObservableList<ReminderManager> reminders = FXCollections.observableArrayList();
        String sql = "SELECT * FROM reminders";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String content = rs.getString("content");
                LocalDateTime dateTime = rs.getTimestamp("date_time").toLocalDateTime();
                reminders.add(new ReminderManager(content, dateTime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reminders;
    }
    public static void createUserProfileTable() {
        String sql = "CREATE TABLE IF NOT EXISTS user_profile ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(255) NOT NULL, "
                + "email VARCHAR(255) NOT NULL UNIQUE, "
                + "password VARCHAR(255) NOT NULL, "
                + "theme ENUM('light', 'dark') DEFAULT 'light', "
                + "language VARCHAR(50) DEFAULT 'en', "
                + "notifications BOOLEAN DEFAULT TRUE"
                + ")";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Таблица user_profile создана или уже существует.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Метод для добавления напоминания
    public static boolean addReminder(String content, LocalDateTime dateTime) {
        String sql = "INSERT INTO reminders (content, date_time) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, content);
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(dateTime));

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
