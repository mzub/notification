package ru.geekbrains.notification.service;

import com.sun.istack.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.entity.bot.BotData;
import ru.geekbrains.repository.BotStateRepository;
import ru.geekbrains.repository.UserRepository;

@Service
@Slf4j
public class BotStateService {

    private final BotStateRepository botStateRepository;
    private final UserRepository userRepository;

    @Autowired
    public BotStateService(BotStateRepository botStateRepository, UserRepository userRepository) {
        this.botStateRepository = botStateRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveState(@NotNull BotData botData) {
        if(botData.getUser() == null){
            botData.setUser(userRepository.findByLogin("test").get());
        }
        log.info(String.format("save bot state with chatID = {%s}", botData.getChatId()));
        botStateRepository.save(botData);
    }

    @Transactional(readOnly = true)
    public BotData findStateByChatId(long chatId) {
        log.info(String.format("find bot state with chatID = {%s}", chatId));
        return botStateRepository.findByChatId(chatId).orElse(null);
    }

    private boolean isConstrainsChatId(long chatId) {
        return botStateRepository.findByChatId(chatId).isPresent();
    }
}
