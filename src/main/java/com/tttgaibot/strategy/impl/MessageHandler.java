package com.tttgaibot.strategy.impl;

import com.tttgaibot.model.ChatLog;
import com.tttgaibot.service.ChatLogService;
import com.tttgaibot.service.ChatgptService;
import com.tttgaibot.service.MessageSender;
import com.tttgaibot.service.QuestionService;
import com.tttgaibot.strategy.UpdateHandler;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageHandler implements UpdateHandler {
    private static final String ADMIN_QUESTION_PREFIX = "/askAdmin ";
    private static final String EMPTY_STRING = "";
    private final ChatgptService chatgptService;
    private final MessageSender messageSender;
    private final QuestionService questionService;
    private final ChatLogService chatLogService;

    @Override
    public void handle(Update update) {
        Message message = update.getMessage();
        if (message.hasText()) {
            if (message.getText().startsWith(ADMIN_QUESTION_PREFIX)) {
                String question = message.getText().replaceFirst(ADMIN_QUESTION_PREFIX,
                        EMPTY_STRING);
                chatLogService.log(question, message.getChatId(),
                        ChatLog.Type.QUESTION_TO_ADMIN);
                questionService.add(message);
                messageSender.sendMessage(SendMessage.builder()
                        .chatId(message.getChatId().toString())
                        .text("Thank you for your question! The administrator will respond ASAP!")
                        .build());
            } else {
                chatLogService.log(message.getText(), message.getChatId(),
                        ChatLog.Type.QUESTION_TO_AI);
                String answer = chatgptService.ask(message.getText());
                chatLogService.log(answer, message.getChatId(), ChatLog.Type.AIS_ANSWER);
                messageSender.sendMessage(SendMessage.builder()
                        .chatId(message.getChatId().toString())
                        .text(answer)
                        .build());
            }
        }
    }

    @Override
    public boolean isApplicable(Update update) {
        return update.hasMessage();
    }
}
