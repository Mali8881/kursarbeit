package database;

import Manager_models.TaskManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

import static database.ContactsDAO.DatabaseConnection;

public class TaskManagerDAO {

    // Получение всех задач
    public static ObservableList<TaskManager> getAllTasks() {
        ObservableList<TaskManager> tasks = FXCollections.observableArrayList();
        String query = "SELECT * FROM tasks";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                tasks.add(new TaskManager(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("category"),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("due_date").toLocalDate(),
                        rs.getString("priority"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    // Добавление задачи
    public static boolean addTask(TaskManager task) {
        String sql = "INSERT INTO tasks (title, description, category, start_date, due_date, priority, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setString(3, task.getCategory());
            stmt.setDate(4, Date.valueOf(task.getStartDate()));
            stmt.setDate(5, Date.valueOf(task.getDueDate()));
            stmt.setString(6, task.getPriority());
            stmt.setString(7, task.getStatus());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // Удаление задачи
    public static boolean deleteTask(int id) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/personal_manager";
        String user = "root";
        String password = "malika2005";
        return DriverManager.getConnection(url, user, password);
    }


    public static boolean updateTask(TaskManager updatedTask) {
        String sql = "UPDATE tasks SET title = ?, description = ?, category = ?, start_date = ?, due_date = ?, priority = ?, status = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Устанавливаем параметры для SQL-запроса
            stmt.setString(1, updatedTask.getTitle());
            stmt.setString(2, updatedTask.getDescription());
            stmt.setString(3, updatedTask.getCategory());
            stmt.setDate(4, Date.valueOf(updatedTask.getStartDate()));
            stmt.setDate(5, Date.valueOf(updatedTask.getDueDate()));
            stmt.setString(6, updatedTask.getPriority());
            stmt.setString(7, updatedTask.getStatus());
            stmt.setInt(8, updatedTask.getId()); // WHERE id = ?

            // Выполняем запрос и возвращаем результат
            return stmt.executeUpdate() > 0; // Возвращает true, если хотя бы одна строка обновлена
        } catch (SQLException e) {
            System.err.println("Ошибка при обновлении задачи: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    public static int getTaskCount() {
        String query = "SELECT COUNT(*) AS count FROM tasks";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {
                return resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


}


