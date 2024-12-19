package database;

import Manager_models.ProfilePage;

import java.sql.*;

import static database.ContactsDAO.DatabaseConnection;

public class ProfileDAO {

    public static ProfilePage getProfileById(int userId) {
        ProfilePage profile = null;
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM profile WHERE user_id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, userId);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                profile = new ProfilePage(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("photo_path")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profile;
    }

    public static void updateProfile(ProfilePage profile) {
        String sql = "UPDATE profiles SET username = ?, email = ?, password = ?, photo_path = ? WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, profile.getUsername());
            stmt.setString(2, profile.getEmail());
            stmt.setString(3, profile.getPassword());
            stmt.setString(4, profile.getPhotoPath());
            stmt.setInt(5, profile.getUserId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
