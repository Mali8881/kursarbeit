
package org.example.kursarbeit.controllers;

import java.util.HashMap;
import java.util.Map;

public class AuthController {
    private Map<String, String> users = new HashMap<>(); // Хранение пользователей в памяти

    public boolean register(String username, String password) {
        if (users.containsKey(username)) {
            return false; // Пользователь уже существует
        }
        users.put(username, password); // Сохраняем пользователя
        return true;
    }

    public boolean login(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password); // Проверка логина и пароля
    }
}
