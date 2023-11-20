package com.tttgaibot.service;

import com.tttgaibot.model.ChatLog;
import java.util.List;

public interface ChatLogService {
    ChatLog log(String text, Long chatId, ChatLog.Type type);

    List<ChatLog> getAllByChatId(Long chatId);
}
