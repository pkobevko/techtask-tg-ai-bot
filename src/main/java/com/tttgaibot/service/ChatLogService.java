package com.tttgaibot.service;

import com.tttgaibot.model.ChatLog;

public interface ChatLogService {
    ChatLog log(String text, Long chatId, ChatLog.Type type);
}
