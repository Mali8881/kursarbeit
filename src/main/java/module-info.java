module org.example.kursarbeit {
    requires javafx.controls;  // Добавьте для использования JavaFX controls
    requires javafx.fxml;     // Добавьте для работы с FXML
    requires java.sql;         // Добавьте для работы с базой данных

    exports views;  // Экспортируйте необходимые пакеты
    exports database;
    exports org.example.kursarbeit;
}
