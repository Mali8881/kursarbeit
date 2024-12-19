package database;

import Manager_models.Remainder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.ContactsDAO.DatabaseConnection;

public class ReminderDAO {

    public static List<Remainder> getAllReminders(int userId) {
        List<Remainder> reminders = new ArrayList<>();
        String query = "SELECT id, title, description FROM reminders WHERE user_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");

                reminders.add(new Remainder(id, title, description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reminders;
    }
    public static int getReminderCount() {
        String query = "SELECT COUNT(*) AS count FROM reminders";
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

    public static int getRemindersCount() {
        String query = "SELECT COUNT(*) AS count FROM reminders";
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