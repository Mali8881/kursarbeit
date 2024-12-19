package database;

import Manager_models.NotesManager;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.ContactsDAO.DatabaseConnection;

public class NotesDAO {
    public static List<NotesManager> getAllNotes() {
        List<NotesManager> notes = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM notes";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                notes.add(new NotesManager(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notes;
    }

    public static boolean addNote(NotesManager note) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO notes (title, content) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, note.getTitle());
            statement.setString(2, note.getContent());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateNote(NotesManager note) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "UPDATE notes SET title = ?, content = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, note.getTitle());
            statement.setString(2, note.getContent());
            statement.setInt(3, note.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteNote(int id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM notes WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static int getNoteCount() {
        String query = "SELECT COUNT(*) AS count FROM notes";
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

    public static int getNotesCount() {
        String query = "SELECT COUNT(*) AS count FROM notes";
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

