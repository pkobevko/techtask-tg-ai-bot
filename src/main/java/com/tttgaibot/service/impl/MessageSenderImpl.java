package com.tttgaibot.service.impl;

import com.tttgaibot.exception.TelegramBotResponseException;
import com.tttgaibot.service.MessageSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSenderImpl implements MessageSender {
    private TelegramBot telegramBot;

    @Override
    public void sendMessage(SendMessage sendMessage) {
        try {
            telegramBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new TelegramBotResponseException("Responding error", e);
        }
    }

    @Autowired
    public void setTelegramBot(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }
}
