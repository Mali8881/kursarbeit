package org.example.kursarbeit.manager;

import database.Database;
import views.ReminderManager;

import java.sql.*;
import java.time.LocalDateTime;
import javafx.scene.control.ListView;

public class Remainder_manager {
    private Database database;
    private ListView<String> remindersListView;

    public Remainder_manager(ListView<String> remindersListView) {
        this.database = new Database();
        this.remindersListView = remindersListView;  // Инициализация ListView
    }

    public void loadRemaindersFromDatabase() {
        String sql = "SELECT * FROM reminders";
        try (Connection conn = database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String content = rs.getString("content");
                LocalDateTime dateTime = rs.getTimestamp("date_time").toLocalDateTime();
                ReminderManager reminder = new ReminderManager(content, dateTime);
                remindersListView.getItems().add(reminder.toString());  // Добавляем напоминание в список
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка при загрузке напоминаний из базы данных.");
        }
    }
}
