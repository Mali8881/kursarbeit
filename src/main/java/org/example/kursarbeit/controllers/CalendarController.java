package org.example.kursarbeit.controllers;

import database.Database;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.io.IOException; // Добавлен импорт для IOException

public class CalendarController {
    private Map<LocalDate, List<String>> taskReminders = new HashMap<>();

    public Map<LocalDate, List<String>> loadReminders() {
        String query = "SELECT date, description FROM tasks";
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                LocalDate date = rs.getDate("date").toLocalDate();
                String description = rs.getString("description");
                taskReminders.computeIfAbsent(date, k -> new ArrayList<>()).add(description);
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return taskReminders;
    }
}
