package views;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestDatabaseConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/personal_manager";
        String user = "root";
        String password = "malika2005";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Подключение к базе данных успешно!");
        } catch (Exception e) {
            System.err.println("Ошибка подключения: " + e.getMessage());
        }
    }
}
