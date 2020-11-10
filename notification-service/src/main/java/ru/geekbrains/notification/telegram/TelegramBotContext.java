package ru.geekbrains.notification.telegram;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.geekbrains.entity.bot.BotData;
import ru.geekbrains.entity.user.User;
import ru.geekbrains.notification.service.TaskService;
import ru.geekbrains.repository.UserRepository;

import java.util.Optional;

@Data
@AllArgsConstructor
public class TelegramBotContext {
    private final TelegramBot bot; //реализация бота
    private final BotData user; //сущность пользователя для которого работают все стадии
    private final String input; //получаем сообщения
    private final TaskService taskService; // зупускаем задачу

    private final UserRepository userRepository; // репозиторий для поиска

    public static TelegramBotContext of (TelegramBot bot, BotData user, String text, TaskService taskService, UserRepository userRepository) {
        return new TelegramBotContext(bot, user, text, taskService, userRepository);
    }

    public void sendAnswer(){
        taskService.send(user);
    }

    public Optional<User> findByLogin(String login){
        return userRepository.findByLogin(login);
    }
}
