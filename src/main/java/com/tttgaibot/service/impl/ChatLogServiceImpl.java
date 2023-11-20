package com.tttgaibot.service.impl;

import com.tttgaibot.model.ChatLog;
import com.tttgaibot.repository.ChatLogRepository;
import com.tttgaibot.service.ChatLogService;
import java.time.ZonedDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatLogServiceImpl implements ChatLogService {
    private final ChatLogRepository chatLogRepository;

    @Override
    public ChatLog log(String text, Long chatId, ChatLog.Type type) {
        ChatLog chatLog = new ChatLog();
        chatLog.setText(text);
        chatLog.setChatId(chatId);
        chatLog.setType(type);
        chatLog.setTimestamp(ZonedDateTime.now());
        return chatLogRepository.save(chatLog);
    }
}
