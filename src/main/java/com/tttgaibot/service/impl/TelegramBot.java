package com.tttgaibot.service.impl;

import com.tttgaibot.strategy.UpdateHandlerStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    @Value("${telegram.bot.username}")
    private String username;
    @Value("${telegram.bot.token}")
    private String token;
    private UpdateHandlerStrategy updateHandlerStrategy;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        updateHandlerStrategy.getUpdateHandler(update).handle(update);
    }

    @Autowired
    public void setUpdateHandlerStrategy(@Lazy UpdateHandlerStrategy updateHandlerStrategy) {
        this.updateHandlerStrategy = updateHandlerStrategy;
    }
}
