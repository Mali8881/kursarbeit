package org.example.kursarbeit.controllers;

import database.Database;

public class AuthController {
    // Метод для регистрации пользователя
    public boolean register(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }
        return Database.registerUser(username, password);
    }

    // Метод для аутентификации пользователя
    public boolean authenticate(String username, String password) {
        return Database.authenticateUser(username, password);
    }
}
