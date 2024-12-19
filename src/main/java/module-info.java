module org.example.kursarbeit {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.mail;
    exports org.example.kursarbeit;
    exports org.example.kursarbeit.components;

     // Добавьте эту строку для экспорта контроллеров
    // Разрешает доступ к контроллеру через рефлексию
    exports controllers;
    exports database;

    exports Manager_models;
    opens Manager_models to javafx.fxml;

    opens controllers to javafx.fxml;
}
