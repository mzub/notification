package ru.geekbrains.notification.service;

import ru.geekbrains.notification.model.User;

import java.util.List;

public interface Notification {
    void sendMessage(String chatId, String response);
}
