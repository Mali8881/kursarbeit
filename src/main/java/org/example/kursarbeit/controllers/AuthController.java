package org.example.kursarbeit.controllers;

import database.Database;

public class AuthController {
    // Метод для регистрации пользователя
    public boolean register(String username, String password, String email) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty() || email == null || email.isEmpty()) {
            return false;  // Проверка на пустые значения
        }
        return Database.registerUser(username, password, email);  // Передаем email в метод регистрации
    }

    // Метод для аутентификации пользователя
    public boolean authenticate(String username, String password) {
        return Database.authenticateUser(username, password);
    }
}
