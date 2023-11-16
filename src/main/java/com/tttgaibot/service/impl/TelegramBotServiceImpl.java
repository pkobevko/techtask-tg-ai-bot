package com.tttgaibot.service.impl;

import com.tttgaibot.exception.TelegramBotResponseException;
import com.tttgaibot.service.ChatgptService;
import com.tttgaibot.service.TelegramBotService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
@RequiredArgsConstructor
public class TelegramBotServiceImpl extends TelegramLongPollingBot
        implements TelegramBotService {
    @Value("${telegram.bot.username}")
    private String username;
    @Value("${telegram.bot.token}")
    private String token;
    private final ChatgptService chatgptService;

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
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            String answer = chatgptService.ask(message.getText());
            response(answer, message.getChatId());
        }
    }

    @Override
    public void response(String text, Long chatId) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatId.toString())
                .text(text).build();
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new TelegramBotResponseException("Error responding", e);
        }
    }
}
