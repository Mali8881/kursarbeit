module org.example.kursarbeit {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    exports org.example.kursarbeit;
    exports org.example.kursarbeit.controllers;  // Добавьте эту строку для экспорта контроллеров
    opens org.example.kursarbeit.controllers to javafx.fxml;  // Разрешает доступ к контроллеру через рефлексию
    exports views;
}
