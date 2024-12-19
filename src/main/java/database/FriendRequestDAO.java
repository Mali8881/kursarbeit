

package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static database.ContactsDAO.DatabaseConnection;

public class FriendRequestDAO {

    public static boolean saveFriendRequest(int fromUserId, int toUserId) {
        String sql = "INSERT INTO friend_requests (from_user_id, to_user_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, fromUserId);
            stmt.setInt(2, toUserId);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean sendFriendRequest(int fromUserId, String recipientEmail) {
        String sql = "INSERT INTO friend_requests (from_user_id, to_user_id) " +
                "SELECT ?, id FROM users WHERE email = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, fromUserId);
            stmt.setString(2, recipientEmail);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
